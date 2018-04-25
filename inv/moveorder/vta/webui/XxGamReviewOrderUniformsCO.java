/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.inv.moveorder.vta.webui;

import java.sql.SQLException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAProcessingPage;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import xxgam.oracle.apps.inv.moveorder.vta.server.XxGamOrderUniformsAMImpl;

/**
 * Controller for ...
 */
public class XxGamReviewOrderUniformsCO extends OAControllerImpl
{
  public static final String RCS_ID="$Header$";
  public static final boolean RCS_ID_RECORDED =
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

  /**
   * Layout and page setup logic for a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  public void processRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processRequest(pageContext, webBean);
    if(null==pageContext||null==webBean){
     return;
    }
    enviromentCurrencyFormat(pageContext,webBean);
    XxGamOrderUniformsAMImpl OrderUniformsAMImpl = (XxGamOrderUniformsAMImpl)pageContext.getApplicationModule(webBean);
    if(!pageContext.isFormSubmission()){
      if(null!=OrderUniformsAMImpl){
       String strCleanReviewInputInfo = OrderUniformsAMImpl.cleanReviewInputInfo();
       if(null!=strCleanReviewInputInfo){
        if(strCleanReviewInputInfo.contains("EXCEPTION")){
          throw new OAException(strCleanReviewInputInfo,OAException.ERROR);
        }
       } /** END if(null!=strCleanReviewInputInfo){ **/
       
       com.sun.java.util.collections.List listReviewInputInfo = OrderUniformsAMImpl.initReviewInputInfo();
       if(listReviewInputInfo.size()>0){
         OAWebBean EjecutarBtnBean = webBean.findChildRecursive("EjecutarBtn");
         if(null!=EjecutarBtnBean){
           EjecutarBtnBean.setRendered(false);
         }
         
         OAWebBean TotalesImpuestosRNBean = webBean.findChildRecursive("TotalesImpuestosRN");
         if(null!=TotalesImpuestosRNBean){
           TotalesImpuestosRNBean.setRendered(false);
         }
         
         OAException.raiseBundledOAException(listReviewInputInfo); 
       } /** END if(null!=strReviewInputInfo){ **/
       
       if(null!=OrderUniformsAMImpl.getXxGamSalesOrderHdrInfoVO1()){
         if(0==OrderUniformsAMImpl.getXxGamSalesOrderHdrInfoVO1().getFetchedRowCount()){
           String strSalesOrderHdrInfo = OrderUniformsAMImpl.fillSalesOrderHdrInfo();
           if(null!=strSalesOrderHdrInfo){
             if(strSalesOrderHdrInfo.contains("EXCEPTION")){
               disablePageRegions(pageContext,webBean);
              throw new OAException(strSalesOrderHdrInfo,OAException.ERROR); 
             }
           } /** END if(null!=strSalesOrderHdrInfo){ **/
         } /** END if(0==OrderUniformsAMImpl.getXxGamSalesOrderHdrInfoVO1().getFetchedRowCount()){ **/
       } /** END if(null!=OrderUniformsAMImpl.getXxGamSalesOrderHdrInfoVO1()){ **/
       
       String[] arrayFillSubtotalIvaInfo = OrderUniformsAMImpl.fillSubtotalIvaInfo();
       if(null!=arrayFillSubtotalIvaInfo[0]){
          if(arrayFillSubtotalIvaInfo[0].contains("EXCEPTION")){
            disablePageRegions(pageContext,webBean);
            throw new OAException(arrayFillSubtotalIvaInfo[0],OAException.ERROR); 
          }
          
          try
         {
          OAMessageStyledTextBean SubTotalBean = (OAMessageStyledTextBean)webBean.findChildRecursive("Subtotal"); 
          if(null!=SubTotalBean){
            System.out.println("SubTotalBean is not null.");
           /* SubTotalBean.setText(arrayFillSubtotalIvaInfo[1]); */
            
              SubTotalBean.setValue(pageContext,new oracle.jbo.domain.Number(arrayFillSubtotalIvaInfo[1]) );
            SubTotalBean.setAttributeValue(OAWebBeanConstants.CURRENCY_CODE,"USD");
          }
          
         OAMessageStyledTextBean ImpuestoBean = (OAMessageStyledTextBean)webBean.findChildRecursive("Impuesto");
         if(null!=ImpuestoBean){
         /*  ImpuestoBean.setText(arrayFillSubtotalIvaInfo[2]); */
           ImpuestoBean.setValue(pageContext,new oracle.jbo.domain.Number(arrayFillSubtotalIvaInfo[2]));
           ImpuestoBean.setAttributeValue(OAWebBeanConstants.CURRENCY_CODE,"USD");
         }
         
         OAMessageStyledTextBean TotalBean = (OAMessageStyledTextBean)webBean.findChildRecursive("Total");
         if(null!=TotalBean){
         /*  TotalBean.setText(arrayFillSubtotalIvaInfo[3]); */
           TotalBean.setValue(pageContext,new oracle.jbo.domain.Number(arrayFillSubtotalIvaInfo[3]));
           TotalBean.setAttributeValue(OAWebBeanConstants.CURRENCY_CODE,"USD");
         }
         
         } catch (SQLException sqle)
         {
           throw new OAException("Error al recuperar subtotal,impuesto al convertir a Number.",OAException.ERROR); 
         }
          
       } /** END if(null!=arrayFillSubtotalIvaInfo[0]){ **/
       
       
      } /** END if(null!=OrderUniformsAMImpl){ **/
    }else{
    
    }
    
    throw new OAException("Descuento catorcenal via nomina.",OAException.INFORMATION);
    
  }

  /**
   * Procedure to handle form submissions for form elements in
   * a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  public void processFormRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processFormRequest(pageContext, webBean);
    if(null==pageContext||null==webBean){
     return;
    }
    
    if("RegresarBtnEvt".equals(pageContext.getParameter(this.EVENT_PARAM))){
      pageContext.forwardImmediately("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/vta/webui/XxGamCreateOrderUniformsPG" /** String url **/
                              ,null /** String functionName **/
                              ,OAWebBeanConstants.KEEP_MENU_CONTEXT /** byte menuContextAction **/
                              ,null /** String menuName **/
                              ,null /** com.sun.java.util.collections.HashMap parameters **/
                              ,true /** boolean retainAM **/
                              ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO /** String addBreadCrumb **/ 
                              );
    }
    
    if(null!=pageContext.getParameter("EjecutarBtn")){
      /** Genera el mensaje **/
       OAException oaException = null;
       oaException = new OAException("¿Esta usted seguro de que quiere realizar la compra?");
       /**Crea el dialog **/
        OADialogPage oaDialogPage = null;
        oaDialogPage = new OADialogPage(OAException.INFORMATION, oaException, null, "", "");
       
      java.util.Hashtable params = new java.util.Hashtable(1);
      
      oaDialogPage.setOkButtonItemName("YesButton");
      oaDialogPage.setNoButtonItemName("NoButton");
      oaDialogPage.setOkButtonToPost(true);
      oaDialogPage.setNoButtonToPost(true);
      oaDialogPage.setRetainAMValue(true);
      oaDialogPage.setPostToCallingPage(true);
      oaDialogPage.setFormParameters(params);
      /* Redirecciona a la siguiente pagina **/
      pageContext.redirectToDialogPage(oaDialogPage);
           
    }
    
    if(null!=pageContext.getParameter("YesButton")){
            OAProcessingPage processingPage = new OAProcessingPage("xxgam.oracle.apps.inv.moveorder.vta.webui.XxGamProcessingOrderUniformsCO");
            String msg = "Procesando...";
            processingPage.setConciseMessage(msg);
            msg = "Esperar un momento...";
            processingPage.setDetailedMessage(msg);
            msg = "Venta de prendas";
            processingPage.setProcessName(msg);
            processingPage.setRetainAMValue(true);
            pageContext.forwardToProcessingPage(processingPage);
            
    }
    if(null!=pageContext.getParameter("NoButton")){
    
    }
    
  }

  /**
   * Metodo que ambienta los formatos de moneda para los campos de precio y monto.
   * @param pageContext
   * @param webBean
   */
  private void enviromentCurrencyFormat(OAPageContext pageContext, 
                                        OAWebBean webBean)
  {
   OATableBean oaTableBean = (OATableBean)webBean.findChildRecursive("XxGamReviewInpOrderUniformsVO");
    if(null!=oaTableBean){
      OAMessageStyledTextBean AmountBean = (OAMessageStyledTextBean)oaTableBean.findChildRecursive("TotAmount");
      if(null!=AmountBean){
        AmountBean.setAttributeValue(OAWebBeanConstants.CURRENCY_CODE,"USD");
      }
      OAMessageStyledTextBean PriceBean  = (OAMessageStyledTextBean)oaTableBean.findChildRecursive("Price");
      if(null!=PriceBean){
        PriceBean.setAttributeValue(OAWebBeanConstants.CURRENCY_CODE,"USD");
      }
    } /*** END if(null!=oaTableBean){ **/
    
  }

 /**
   * Metodo que deshabilita las regiones de la pagina de revision 
   * @param pageContext
   * @param webBean
   */
  private void disablePageRegions(OAPageContext pageContext, OAWebBean webBean)
  {
    OAWebBean PageButtonBarRNBean = webBean.findChildRecursive("PageButtonBarRN");
    if(null!=PageButtonBarRNBean){
      PageButtonBarRNBean.setRendered(false);
    }
    OAWebBean XxGamReviewInpOrderUniformsVOBean = webBean.findChildRecursive("XxGamReviewInpOrderUniformsVO");
    
    if(null!=XxGamReviewInpOrderUniformsVOBean){
      XxGamReviewInpOrderUniformsVOBean.setRendered(false);
    }
    
    OAWebBean XxGamSalesOrderHdrInfoVOBean = webBean.findChildRecursive("XxGamSalesOrderHdrInfoVO");
    if(null!=XxGamSalesOrderHdrInfoVOBean){
      XxGamSalesOrderHdrInfoVOBean.setRendered(false);
    }
    
  }
  
}
