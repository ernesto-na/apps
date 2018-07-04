/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.inv.moveorder.vta.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OARow;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.RowSetIterator;

import xxgam.oracle.apps.inv.moveorder.vta.server.XxGamOrderUniformsAMImpl;
import xxgam.oracle.apps.inv.moveorder.vta.server.XxGamPersonVtaUniformInfoVOImpl;
import xxgam.oracle.apps.inv.moveorder.vta.server.XxGamPersonVtaUniformInfoVORowImpl;


/**
 * Controller for ...
 */
public class XxGamCreateOrderUniformsCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        if (null == pageContext || null == webBean) {
            return;
        }

        /*********************************************************************
   // OATipBean Tip1Bean = (OATipBean)webBean.findChildRecursive("Tip1");
   // OATipBean Tip1Bean = (OATipBean)webBean.findIndexedChildRecursive("Tip1");
    if(null!=Tip1Bean){
     System.out.println("...........");
      CSSStyle customStyle = new CSSStyle();
       customStyle.setProperty("color","#cc0000");
       customStyle.setProperty("font-size", "16pt");
       //Tip1Bean.setInlineStyle(customStyle);
        Tip1Bean.setAttributeValue(OAWebBeanConstants.STYLE_CLASS_ATTR,customStyle);

      oracle.apps.fnd.framework.webui.beans.OAFormattedTextBean Tip3 = (OAFormattedTextBean)this.createWebBean(pageContext,OAWebBeanConstants.FORMATTED_TEXT_BEAN,null,"Tip3");
      Tip3.setText("Si no encuentras tu talla envía un correo a amplanmatgral@aeromexico.com con la prenda y talla requerida.");
      Tip3.setInlineStyle(customStyle);
     // Tip3.setAttributeValue(OAWebBeanConstants.STYLE_CLASS_ATTR,customStyle);

      OAPageLayoutBean pageLayoutBean = pageContext.getPageLayoutBean();
      if(null!=pageLayoutBean){
        pageLayoutBean.addIndexedChild(Tip3);
        OAHeaderBean PrendasDefSinColRNBean = (OAHeaderBean)pageLayoutBean.findIndexedChild(webBean,"PrendasDefSinColRN");
        if(null!=PrendasDefSinColRNBean){
           System.out.println("************");
         // PrendasDefSinColRNBean.addIndexedChild(Tip3);
        }
      }

    }
    ***********************************************************************/


        enviromentCurrencyFormat(pageContext, webBean);
        XxGamOrderUniformsAMImpl OrderUniformsAMImpl = 
            (XxGamOrderUniformsAMImpl)pageContext.getApplicationModule(webBean);
        String strAvailableKits = null;

        /**Inicio de modificacion NRC 11-feb-2017*/
        String strisInDateClose = OrderUniformsAMImpl.isInDateClose();
        if (null != strisInDateClose) {
            if (strisInDateClose.contains("EXCEPTION")) {
                disablePageRegions(pageContext, webBean);
                throw new OAException(strisInDateClose, OAException.ERROR);
            }
            if (strisInDateClose.equals("Y")) {
                disablePageRegions(pageContext, webBean);
                String strCierreContable = 
                    "No es posible realizar la compra por motivo de cierre contable, favor de volver a intentar el siguiente día hábil.";
                throw new OAException(strCierreContable, OAException.WARNING);
            }
        }
        /**fin de modificacion*/

        if (!pageContext.isFormSubmission()) {
            if (null != OrderUniformsAMImpl) {
                String strValFillPersonVtaUniforms = 
                    OrderUniformsAMImpl.valFillPersonVtaUniforms();
                if ("Y".equals(strValFillPersonVtaUniforms)) {
                    String strGetVal = 
                        OrderUniformsAMImpl.fillPersonVtaUniformsInfo(pageContext.getUserName());
                    if (null != strGetVal) {
                        if (strGetVal.contains("EXCEPTION")) {
                            disablePageRegions(pageContext, webBean);
                            throw new OAException(strGetVal, 
                                                  OAException.ERROR);
                        }

                    } /** ENd if(null!=strGetVal){ **/
                } /** End if("Y".equals(strValFillPersonVtaUniforms)){ **/

                String strSessionPriceListHdrID = 
                    (String)pageContext.getSessionValue("sPriceListHdrID");
                if (null == strSessionPriceListHdrID || 
                    "".equals(strSessionPriceListHdrID)) {
                    String strPutPriceListHdrID = 
                        OrderUniformsAMImpl.putPriceListHdrID(pageContext);
                    if (null != strPutPriceListHdrID) {
                        if (strPutPriceListHdrID.contains("EXCEPTION")) {
                            disablePageRegions(pageContext, webBean);
                            throw new OAException(strPutPriceListHdrID, 
                                                  OAException.ERROR);
                        }
                    } /*** END if(null!=strPutPriceListHdrID){ **/
                } /** END if(null==strSessionPriceListHdrID||"".equals(strSessionPriceListHdrID)){ **/

                XxGamPersonVtaUniformInfoVOImpl PersonVtaUniformInfoVOImpl = 
                    OrderUniformsAMImpl.getXxGamPersonVtaUniformInfoVO1();
                XxGamPersonVtaUniformInfoVORowImpl PersonVtaUniformInfoVORowImpl = 
                    null;
                RowSetIterator PersonVtaUniformInfoIterator = 
                    PersonVtaUniformInfoVOImpl.createRowSetIterator(null);
                if (PersonVtaUniformInfoIterator.hasNext()) {
                    PersonVtaUniformInfoVORowImpl = 
                            (XxGamPersonVtaUniformInfoVORowImpl)PersonVtaUniformInfoIterator.next();
                    String strGetAvailableKitsInfo = 
                        OrderUniformsAMImpl.fillAvailableKitsInfo(PersonVtaUniformInfoVORowImpl.getOrganization(), 
                                                                  PersonVtaUniformInfoVORowImpl.getPosition(), 
                                                                  PersonVtaUniformInfoVORowImpl.getSex(), 
                                                                  PersonVtaUniformInfoVORowImpl.getPayroll(), 
                                                                  PersonVtaUniformInfoVORowImpl.getZona(), 
                                                                  PersonVtaUniformInfoVORowImpl.getXxGamInvAssignExcept());
                    if (null != strGetAvailableKitsInfo) {
                        if (strGetAvailableKitsInfo.contains("EXCEPTION")) {
                            throw new OAException(strGetAvailableKitsInfo, 
                                                  OAException.ERROR);
                        }
                        strAvailableKits = strGetAvailableKitsInfo;
                    }
                } /** End if(PersonVtaUniformInfoIterator.hasNext()){ **/
                PersonVtaUniformInfoIterator.closeRowSetIterator();

                String strValFillInputOrderUniforms = 
                    OrderUniformsAMImpl.valFillInputOrderUniforms();
                if ("Y".equals(strValFillInputOrderUniforms)) {
                    String strGetInputOrderUniforms = 
                        OrderUniformsAMImpl.fillInputOrderUniforms(strAvailableKits);
                    if (null != strGetInputOrderUniforms) {
                        if (strGetInputOrderUniforms.contains("EXCEPTION")) {
                            throw new OAException(strGetInputOrderUniforms, 
                                                  OAException.ERROR);
                        }
                    }
                    OrderUniformsAMImpl.sortInputOrderUniformsVO();
                } /** END if("Y".equals(strValFillInputOrderUniforms)){ **/


            } /** END if(null!=OrderUniformsAMImpl){ **/
        } else {

        } /** END if(!pageContext.isFormSubmission()){ **/


    }

    /**
     * Procedure to handle form submissions for form elements in
     * a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        if (null == pageContext || null == webBean) {
            return;
        }

        XxGamOrderUniformsAMImpl OrderUniformsAMImpl = 
            (XxGamOrderUniformsAMImpl)pageContext.getApplicationModule(webBean);
        if (null != OrderUniformsAMImpl) {
            String strSessionPriceListHdrID = 
                (String)pageContext.getSessionValue("sPriceListHdrID");
            if (null == strSessionPriceListHdrID || 
                "".equals(strSessionPriceListHdrID)) {
                String strPutPriceListHdrID = 
                    OrderUniformsAMImpl.putPriceListHdrID(pageContext);
                if (null != strPutPriceListHdrID) {
                    if (strPutPriceListHdrID.contains("EXCEPTION")) {
                        throw new OAException(strPutPriceListHdrID, 
                                              OAException.ERROR);
                    }
                } /*** END if(null!=strPutPriceListHdrID){ **/
            } /** END if(null==strSessionPriceListHdrID||"".equals(strSessionPriceListHdrID)){ **/
        } /** END  if(null!=OrderUniformsAMImpl){ **/

        System.out.println(this.EVENT_PARAM);
        System.out.println(this.EVENT_SOURCE_ROW_REFERENCE);
        System.out.println(this.LOV_PREPARE);
        System.out.println(this.LOV_UPDATE);
        System.out.println(this.LOV_VALIDATE);

        String strEvent = pageContext.getParameter(this.EVENT_PARAM);
        String strEvtSrcRowRef = 
            pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE);
        String strLovInputSourceId = pageContext.getLovInputSourceId();

        System.out.println("strEvent:" + strEvent);
        System.out.println("strEvtSrcRowRef:" + strEvtSrcRowRef);
        System.out.println("strLovInputSourceId:" + strLovInputSourceId);

        if (null != strEvent) {
            if (this.LOV_PREPARE.equals(strEvent)) {
                if ("TallaNbr".equals(strLovInputSourceId) || 
                    "TallaNbrPren".equals(strLovInputSourceId)) {
                    OARow oaRow = 
                        (OARow)OrderUniformsAMImpl.findRowByRef(strEvtSrcRowRef);
                    if (null != oaRow) {
                        String strDotaID = 
                            (String)oaRow.getAttribute("DotaID");
                        pageContext.putSessionValue("sDotaID", strDotaID);
                    }
                }
            } else if (this.LOV_UPDATE.equals(strEvent)) {

            } else if (this.LOV_VALIDATE.equals(strEvent)) {

            }
        } /** End if(null!=strEvent){ **/

        if (null != pageContext.getParameter("ComprarBtn")) {
            com.sun.java.util.collections.List listValAmntInputOrderUnif = 
                OrderUniformsAMImpl.valAmntInputOrderUnif();
            if (null != listValAmntInputOrderUnif) {
                if (listValAmntInputOrderUnif.size() > 0) {
                    OAException.raiseBundledOAException(listValAmntInputOrderUnif);
                    ;
                }
            } /** retain AM **/
                pageContext.forwardImmediately("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/vta/webui/XxGamReviewOrderUniformsPG", 
                                               null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, null, true, 
                                               OAWebBeanConstants.ADD_BREAD_CRUMB_NO);

        } /** END if(null!=pageContext.getParameter("ComprarBtn")){ **/


    } /** END processFormRequest(OAPageContext pageContext, OAWebBean webBean) **/


    /**
     * Metodo que ambienta los formatos de moneda para los campos de precio y monto.
     * @param pageContext
     * @param webBean
     */
    private void enviromentCurrencyFormat(OAPageContext pageContext, 
                                          OAWebBean webBean) {
        OATableBean oaTableBean = 
            (OATableBean)webBean.findChildRecursive("XxGamInputOrderUniformsVO");
        if (null != oaTableBean) {
            OAMessageStyledTextBean AmountBean = 
                (OAMessageStyledTextBean)oaTableBean.findChildRecursive("Amount");
            if (null != AmountBean) {
                AmountBean.setAttributeValue(OAWebBeanConstants.CURRENCY_CODE, 
                                             "USD");
            }
            OAMessageStyledTextBean PriceBean = 
                (OAMessageStyledTextBean)oaTableBean.findChildRecursive("Price");
            if (null != PriceBean) {
                PriceBean.setAttributeValue(OAWebBeanConstants.CURRENCY_CODE, 
                                            "USD");
            }
        } /*** END if(null!=oaTableBean){ **/

    }

    /**
     * Metodo que deshabilita regiones en la pagina 
     * @param pageContext
     * @param webBean
     */
    private void disablePageRegions(OAPageContext pageContext, 
                                    OAWebBean webBean) {
        OAWebBean CabeceraMsgComLayRNBean = 
            webBean.findChildRecursive("CabeceraMsgComLayRN");
        if (null != CabeceraMsgComLayRNBean) {
            CabeceraMsgComLayRNBean.setRendered(false);
        }

        OAWebBean XxGamInputOrderUniformsVOBean = 
            webBean.findChildRecursive("XxGamInputOrderUniformsVO");
        if (null != XxGamInputOrderUniformsVOBean) {
            XxGamInputOrderUniformsVOBean.setRendered(false);
        }

        OAWebBean XxGamPersonVtaUniformInfoVOBean = 
            webBean.findChildRecursive("XxGamPersonVtaUniformInfoVO");
        if (null != XxGamPersonVtaUniformInfoVOBean) {
            XxGamPersonVtaUniformInfoVOBean.setRendered(false);
        }

        OAWebBean PageButtonBarRNBean = 
            webBean.findChildRecursive("PageButtonBarRN");
        if (null != PageButtonBarRNBean) {
            PageButtonBarRNBean.setRendered(false);
        }

    }

}
