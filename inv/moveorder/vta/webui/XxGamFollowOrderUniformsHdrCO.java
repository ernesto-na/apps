/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.inv.moveorder.vta.webui;

import java.sql.SQLException;

import java.text.ParseException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.SQLStmtException;

import xxgam.oracle.apps.inv.moveorder.vta.server.XxGamFollOrderUnifHdrVOImpl;
import xxgam.oracle.apps.inv.moveorder.vta.server.XxGamOrderUniformsAMImpl;

/**
 * Controller for ...
 */
public class XxGamFollowOrderUniformsHdrCO extends OAControllerImpl
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
    
    String strSuperUserUnif = pageContext.getProfile("XXGAM_INV_USUARIO_UNIFORME");
    String strUserID = ""+pageContext.getUserId();
    
    if("Y".equals(strSuperUserUnif)){
    }else{
      OAWebBean PNumeroEmpleadoBean = webBean.findChildRecursive("PNumeroEmpleado");
      if(null!=PNumeroEmpleadoBean){
        PNumeroEmpleadoBean.setRendered(false);
      }
    }
    
    System.out.println("param"+pageContext.getParameter("pSuccessCallApi"));
    if(!pageContext.isFormSubmission()){    
      if(null!=OrderUniformsAMImpl){
        try{
        
        if("N".equals(strSuperUserUnif)){
          OrderUniformsAMImpl.filterFollOrderUnifHdrVO(strUserID);
        }else if("Y".equals(strSuperUserUnif)){
          OrderUniformsAMImpl.getXxGamFollOrderUnifHdrVO1().executeQuery();
        }
        
        }catch(SQLStmtException sqlStmt){
         throw new OAException("EXCEPTION al ejecutar el viewObject XxGamFollOrderUnifHdrVO:"+sqlStmt.getErrorCode()+" , "+sqlStmt.getMessage(),OAException.ERROR);
        }
      }
      if(null!=pageContext.getParameter("pSuccessCallApi")&&!"".equals(pageContext.getParameter("pSuccessCallApi"))){
       throw new OAException(pageContext.getParameter("pSuccessCallApi"),OAException.INFORMATION);
      }
    }
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
    
    String strEventParam = pageContext.getParameter(this.EVENT_PARAM);
    System.out.println("strEventParam:"+strEventParam);
    if(null!=pageContext.getParameter("CrearOrdenBtn")){
      pageContext.forwardImmediately("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/vta/webui/XxGamCreateOrderUniformsPG" /** String url **/
                              ,null /** String functionName **/
                              ,OAWebBeanConstants.KEEP_MENU_CONTEXT /** byte menuContextAction **/
                              ,null /** String menuName **/
                              ,null /** com.sun.java.util.collections.HashMap parameters **/
                              ,false /** boolean retainAM **/
                              ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO /** String addBreadCrumb **/ 
                              );
    
    }
    
    if(null!=strEventParam){
      if("ToLinesEvt".equals(strEventParam)){
        System.out.println("pXxGamHdrId:"+pageContext.getParameter("pXxGamHdrId"));
        pageContext.forwardImmediately("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/vta/webui/XxGamFollowOrderUniformsLinPG" /** String url **/
                                ,null /** String functionName **/
                                ,OAWebBeanConstants.KEEP_MENU_CONTEXT /** byte menuContextAction **/
                                ,null /** String menuName **/
                                ,null /** com.sun.java.util.collections.HashMap parameters **/
                                ,true /** boolean retainAM **/
                                ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO /** String addBreadCrumb **/ 
                                );
      }
      
      if("GoBtnEvt".equals(strEventParam)){
        filterByParameters(pageContext,webBean);
      }
      
    }/** END if(null!=strEventParam){ **/
    
  }
  
     /**
      * Metodo que ambienta los formatos de moneda para los campos de precio y monto.
      * @param pageContext
      * @param webBean
      */
     private void enviromentCurrencyFormat(OAPageContext pageContext, 
                                           OAWebBean webBean)
     {
      OATableBean oaTableBean = (OATableBean)webBean.findChildRecursive("XxGamFollOrderUnifHdrVO");
       if(null!=oaTableBean){
         System.out.println("YYYYYYYY");
         OAMessageStyledTextBean AmountBean = (OAMessageStyledTextBean)oaTableBean.findChildRecursive("TotalPedido");
         if(null!=AmountBean){
           System.out.println("XXXXX");
           AmountBean.setAttributeValue(OAWebBeanConstants.CURRENCY_CODE,"USD");
         }
       } /*** END if(null!=oaTableBean){ **/
       
     }

  /**
   * Metodo que filtra por parametros que se ingresan en la region de busqueda
   * @param pageContext
   * @param webBean
   */
  private void filterByParameters(OAPageContext pageContext, OAWebBean webBean)
  {
    XxGamOrderUniformsAMImpl OrderUniformsAMImpl = (XxGamOrderUniformsAMImpl)pageContext.getApplicationModule(webBean);
   String strPersonID = pageContext.getParameter("fvPersonID");
    oracle.jbo.domain.Number numPersonID = null; 
   String strNumeroOrden =  pageContext.getParameter("PSoNumeroOrden");
   String strEstatus = pageContext.getParameter("PSoEstatus");
   String strFechaOrdenDesde =  pageContext.getParameter("PFechaOrdenDesde");
   String strFechaOrdenHasta =  pageContext.getParameter("PFechaOrdenHasta");
   
   if(null==strPersonID){
    OAFormValueBean PersonIDBean = (OAFormValueBean)webBean.findChildRecursive("fvPersonID");
    if(null!=PersonIDBean){
      strPersonID = PersonIDBean.getValue();
    }
   } /** END if(null==strPersonID){ **/
   
    /**Modificado por NRC 13-feb-2017 mod1*/
       String strSuperUserUnif = pageContext.getProfile("XXGAM_INV_USUARIO_UNIFORME");
       String strUserID = ""+pageContext.getUserId();
       
       if(null==strPersonID || "".equals(strPersonID)){
        System.out.println("Into null==strPersonID");
        if("Y".equals(strSuperUserUnif)){
            OAFormValueBean PersonIDBean = (OAFormValueBean)webBean.findChildRecursive("fvPersonID");
            if(null!=PersonIDBean){
              strPersonID = PersonIDBean.getValue();
              System.out.println("NRCstrPersonID: " + strPersonID);
            }
        }
        else if("N".equals(strSuperUserUnif)){
            System.out.println("Profile Disabled");
            if(null!=OrderUniformsAMImpl){
                strPersonID = OrderUniformsAMImpl.findPersonId(strUserID);
                if(strPersonID.contains("EXCEPTION")){
                    strPersonID = "";
                    throw new OAException(strPersonID,OAException.ERROR); 
                }
                System.out.println("strPersonID = "+strPersonID);
            }
        }
       } /* END if(null==strPersonID){ */
       /**Fin de modificacion mod2*/
   
   
   if(null==strNumeroOrden){
     OAMessageTextInputBean NumeroOrdenBean = (OAMessageTextInputBean)webBean.findChildRecursive("PSoNumeroOrden");
     if(null!=NumeroOrdenBean){
       strNumeroOrden = (String)NumeroOrdenBean.getValue(pageContext);
     }
   } /** END if(null==strNumeroOrden){ **/
   
   if(null==strEstatus){
    OAMessageChoiceBean EstatusBean = (OAMessageChoiceBean)webBean.findChildRecursive("PSoEstatus");
    if(null!=EstatusBean){
      strEstatus = (String)EstatusBean.getValue(pageContext);
    }
   } /** END if(null==strEstatus){ **/
   
   if(null==strFechaOrdenDesde){
     strFechaOrdenDesde = (String)pageContext.getSessionValue("sFechaOrdenDesde");
   }else{
     pageContext.putSessionValue("sFechaOrdenDesde",strFechaOrdenDesde);
   } /** End  if(null==strFechaOrdenDesde){ **/
   
   if(null==strFechaOrdenHasta){
     strFechaOrdenHasta = (String)pageContext.getSessionValue("sFechaOrdenHasta");
   }else{
      pageContext.putSessionValue("sFechaOrdenHasta",strFechaOrdenHasta);
    }
   
   System.out.println("strPersonID:"+strPersonID);
   System.out.println("strNumeroOrden:"+strNumeroOrden);
   System.out.println("strEstatus:"+strEstatus);
   System.out.println("sFechaOrdenDesde:"+strFechaOrdenDesde);
   System.out.println("strFechaOrdenHasta:"+strFechaOrdenHasta);
    
    if(null!=strPersonID&&!"".equals(strPersonID)){
    try
    {
      numPersonID = new oracle.jbo.domain.Number(strPersonID);                            
    } catch (SQLException sqle)
    {
      throw new OAException("EXCEPTION al filtrar por parametros:"+sqle.getErrorCode()+" , "+sqle.getMessage());
    }
    } /*** END if(null!=strPersonID&&!"".equals(strPersonID)){ **/
    
    String strCurrentLanguage = pageContext.getCurrentLanguage();
    java.util.Locale currentLocale = new java.util.Locale(strCurrentLanguage);
    
    System.out.println("strCurrentLanguage:"+strCurrentLanguage);
    
    if("US".equals(strCurrentLanguage)||"ESA".equals(strCurrentLanguage)){
      strFechaOrdenDesde = failInstallationOnLinux(strFechaOrdenDesde);
      strFechaOrdenHasta = failInstallationOnLinux(strFechaOrdenHasta);
      currentLocale = new java.util.Locale("en","US");
    }
    
    java.text.SimpleDateFormat dateFormat =  new java.text.SimpleDateFormat("dd-MMM-yyyy",currentLocale);
    java.util.Date utilFromDate=null;
    java.util.Date utilToDate=null;
    java.sql.Date sqlFromDate = null;
    java.sql.Date sqlToDate = null;  
    oracle.jbo.domain.Date dateFromDate = null;
    oracle.jbo.domain.Date dateToDate = null;
    
    try {
       if(null!=strFechaOrdenDesde&&!"".equals(strFechaOrdenDesde)){
           utilFromDate = dateFormat.parse(strFechaOrdenDesde);
           sqlFromDate = new java.sql.Date(utilFromDate.getTime());
           dateFromDate = new oracle.jbo.domain.Date(sqlFromDate);
       }
       if(null!=strFechaOrdenHasta&&!"".equals(strFechaOrdenHasta)){
           utilToDate = dateFormat.parse(strFechaOrdenHasta);
           sqlToDate = new java.sql.Date(utilToDate.getTime());
           dateToDate = new oracle.jbo.domain.Date(sqlToDate);
       }
       
    } catch (ParseException e) {
      throw new OAException("EXCEPTION en conversion de fechas:"+e.toString()+e.getMessage());
    }
   
   System.out.println("dateFromDate:"+dateFromDate); 
   System.out.println("dateToDate:"+dateToDate);  
    
   if(null!=numPersonID
     ||(null!=strNumeroOrden&&!"".equals(strNumeroOrden))
     ||(null!=strEstatus&&!"".equals(strEstatus)
     ||(null!=dateFromDate))
     ||(null!=dateToDate)
     ){
     if(null!=OrderUniformsAMImpl){
        
        XxGamFollOrderUnifHdrVOImpl FollOrderUnifHdrVOImpl = OrderUniformsAMImpl.getXxGamFollOrderUnifHdrVO1(); 
        FollOrderUnifHdrVOImpl.filterByParameters(numPersonID
                                                 ,strNumeroOrden
                                                 ,strEstatus
                                                 ,dateFromDate
                                                 ,dateToDate);
        
      }
   }
   
  }

  /**
   * Metodo que regresa fechas en Locale.en.US
   * @param pStrFecha
   * @return
   */
  private String failInstallationOnLinux(String pStrFecha)
  {
    String retval = null; 
     if(null!=pStrFecha){
       if(pStrFecha.contains("Ene")){
        retval = pStrFecha.replace("Ene","Jan");
        return retval;
       }
       if(pStrFecha.contains("Abr")){
        retval = pStrFecha.replace("Abr","Apr");
         return retval;
       }
       if(pStrFecha.contains("Ago")){
         retval = pStrFecha.replace("Ago","Aug");
          return retval;
        }
        if(pStrFecha.contains("Dic")){
          retval = pStrFecha.replace("Dic","Dec");
           return retval;
         }
       retval = pStrFecha;
     }
     return retval;
  }
  
}
