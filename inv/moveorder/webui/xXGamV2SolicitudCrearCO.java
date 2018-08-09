package xxgam.oracle.apps.inv.moveorder.webui;

import java.sql.SQLException;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OARow;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAProcessingPage;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;

import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSolicitudAMImpl;


public class xXGamV2SolicitudCrearCO extends OAControllerImpl {
    @Override
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        if (null == pageContext || null == webBean) {
            return;
        }

        OAMessageChoiceBean AvailableKitsBean = 
            (OAMessageChoiceBean)webBean.findChildRecursive("AvailableKits");
        if (null != AvailableKitsBean) {
            System.out.println("Style:" + AvailableKitsBean.getStyle());
        }

        if (null != pageContext.getParameter("pExceptValHistoryInfo") && 
            !"".equals(pageContext.getParameter("pExceptValHistoryInfo"))) {
            disablePageRegions(pageContext, webBean);
            throw new OAException(pageContext.getParameter("pExceptValHistoryInfo").toString(), 
                                  OAException.ERROR);
        }

        xXGamInvSolicitudAMImpl InvSolicitudAMImpl = 
            (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
        if (!pageContext.isFormSubmission()) {
            if (null != InvSolicitudAMImpl) {
                String strValFillSolCrearCabeInfo = 
                    InvSolicitudAMImpl.valFillSolCrearCabeInfo();
                if (null != strValFillSolCrearCabeInfo) {
                    if ("Y".equals(strValFillSolCrearCabeInfo)) {
                        String getFillSolCrearCabeInfo = 
                            InvSolicitudAMImpl.fillSolCrearCabeInfo(pageContext, 
                                                                    webBean);
                        if (null != getFillSolCrearCabeInfo) {
                            if (getFillSolCrearCabeInfo.contains("EXCEPTION")) {
                                disablePageRegions(pageContext, webBean);
                                throw new OAException(getFillSolCrearCabeInfo, 
                                                      OAException.ERROR);
                            }
                        } /** END if(null!=getFillSolCrearCabeInfo){ **/

                    }
                } /** END if(null!=strValFillSolCrearCabeInfo){ **/

                String strGetAvailableKitsInfo = 
                    InvSolicitudAMImpl.fillAvailableKitsInfo();
                if (null != strGetAvailableKitsInfo) {
                    if (strGetAvailableKitsInfo.contains("EXCEPTION")) {
                        disablePageRegions(pageContext, webBean);
                        throw new OAException(strGetAvailableKitsInfo, 
                                              OAException.ERROR);
                    }
                }

                String strValGamHrUniformesInfo = 
                    InvSolicitudAMImpl.valGamHrUniformesInfo(strGetAvailableKitsInfo);
                if (null != strValGamHrUniformesInfo) {
                    if (strValGamHrUniformesInfo.contains("EXCEPTION")) {
                        throw new OAException(strValGamHrUniformesInfo, 
                                              OAException.ERROR);
                    }
                }

                if (null != pageContext.getParameter("pExceptValPeriodKit") && 
                    !"".equals(pageContext.getParameter("pExceptValPeriodKit"))) {
                    /*************************************************************************************
            OAButtonBean V2GrabarBtnBean = (OAButtonBean)webBean.findChildRecursive("V2GrabarBtn");
            if(null!=V2GrabarBtnBean){
              V2GrabarBtnBean.setDisabled(true);
            }
            *************************************************************************************/
                    throw new OAException(pageContext.getParameter("pExceptValPeriodKit").toString(), 
                                          OAException.ERROR);
                }

                if (null != pageContext.getParameter("pExceptValSoloUnaUNI") && 
                    !"".equals(pageContext.getParameter("pExceptValSoloUnaUNI"))) {
                    /*************************************************************************************
            OAButtonBean V2GrabarBtnBean = (OAButtonBean)webBean.findChildRecursive("V2GrabarBtn");
            if(null!=V2GrabarBtnBean){
              V2GrabarBtnBean.setDisabled(true);
            }
            *************************************************************************************/
                    throw new OAException(pageContext.getParameter("pExceptValSoloUnaUNI").toString(), 
                                          OAException.ERROR);
                }


            } /** END if(null!=InvSolicitudAMImpl){ **/
        } else {
            System.out.println("isFormSubmission -->");
        } /** END if(!pageContext.isFormSubmission()){ **/
    }

    @Override
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        if (null == pageContext || null == webBean) {
            return;
        }
        System.out.println(pageContext.getParameter(this.EVENT_PARAM));
        String strEvent = pageContext.getParameter(this.EVENT_PARAM);
        String strEvtSrcRowRef = 
            pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE);
        String strLovInputSourceId = pageContext.getLovInputSourceId();

        xXGamInvSolicitudAMImpl InvSolicitudAMImpl = 
            (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
        if ("AvailableKitEvt".equals(pageContext.getParameter(this.EVENT_PARAM))) {
            String strKitCode = null;
            oracle.jbo.domain.Number numPersonID = null;

            OAMessageChoiceBean AvailableKitsBean = 
                (OAMessageChoiceBean)webBean.findChildRecursive("AvailableKits");
            if (null != AvailableKitsBean) {
                strKitCode = (String)AvailableKitsBean.getValue(pageContext);
            }

            if (null == strKitCode || "".equals(strKitCode)) {
                InvSolicitudAMImpl.cleanV2InvSoliDtlVOInfo();
                return;
            }

            OAFormValueBean personIdBean = 
                (OAFormValueBean)webBean.findChildRecursive("personId");
            if (null != personIdBean) {
                try {
                    numPersonID = 
                            new oracle.jbo.domain.Number(personIdBean.getText(pageContext));
                } catch (SQLException sqle) {
                    throw new OAException("EXCEPTION al obttener la variable personID: " + 
                                          sqle.getErrorCode() + " , " + 
                                          sqle.getMessage());
                }
            } /** END if(null!=personIdBean){ **/
            if (null != strKitCode && !"".equals(strKitCode) && 
                (null != numPersonID)) {

                /************************************************************************
           * Regla de negocio: Si un empleado no tiene una UNI creada puede seguir el flujo
           * de captura de tallas y creacion de UNIS sin validar la apertura de periodos
           *****************************************************************************/
                String strNumUNIS = InvSolicitudAMImpl.getNumberUNIS();
                System.out.println("strNumUNIS:" + strNumUNIS);
                if (null != strNumUNIS) {
                    if (strNumUNIS.contains("EXCEPTION")) {
                        throw new OAException(strNumUNIS, OAException.ERROR);
                    } else if (!"0".equals(strNumUNIS)) {

                        String strvalPeriodActiveKit = 
                            InvSolicitudAMImpl.valPeriodActiveKit(strKitCode);
                        if (null != strvalPeriodActiveKit) {
                            if (strvalPeriodActiveKit.contains("EXCEPTION")) {
                                com.sun.java.util.collections.HashMap hashMap = 
                                    new com.sun.java.util.collections.HashMap();
                                hashMap.put("pExceptValPeriodKit", 
                                            strvalPeriodActiveKit); /* com.sun.java.util.collections.HashMap parameters, */
                                    /* boolean retainAM, */
                                    /* String addBreadCrumb*/
                                    pageContext.forwardImmediatelyToCurrentPage(hashMap, 
                                                                                false, 
                                                                                OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
                                /* return; */
                                /* throw new OAException(strvalPeriodActiveKit,OAException.ERROR); */
                            }
                        } /** END  if(null!=strvalPeriodActiveKit){ **/

                    }
                }


                String strKitID = InvSolicitudAMImpl.getKitIDInfo(strKitCode);
                if (null != strKitID) {
                    if (strKitID.contains("EXCEPTION")) {
                        throw new OAException(strKitID, OAException.ERROR);
                    }

                    String strValSoloUnaUNI = 
                        InvSolicitudAMImpl.valSoloUnaUNI(numPersonID, 
                                                         strKitCode);
                    if (null != strValSoloUnaUNI) {
                        if (strValSoloUnaUNI.contains("EXCEPTION")) {
                            com.sun.java.util.collections.HashMap hashMap = 
                                new com.sun.java.util.collections.HashMap();
                            hashMap.put("pExceptValSoloUnaUNI", 
                                        strValSoloUnaUNI); /* com.sun.java.util.collections.HashMap parameters, */
                                /* boolean retainAM, */
                                /* String addBreadCrumb*/
                                pageContext.forwardImmediatelyToCurrentPage(hashMap, 
                                                                            false, 
                                                                            OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
                        }
                    }

                    InvSolicitudAMImpl.cleanV2InvSoliDtlVOInfo();
                    String strFillV2InvSoliDtlVOInfo = 
                        InvSolicitudAMImpl.fillV2InvSoliDtlVOInfo(pageContext, 
                                                                  strKitCode, 
                                                                  numPersonID, 
                                                                  strKitID);
                    if (null != strFillV2InvSoliDtlVOInfo) {
                        if (strFillV2InvSoliDtlVOInfo.contains("EXCEPTION")) {
                            com.sun.java.util.collections.HashMap hashMap = 
                                new com.sun.java.util.collections.HashMap();
                            hashMap.put("pExceptValHistoryInfo", 
                                        strFillV2InvSoliDtlVOInfo); /* com.sun.java.util.collections.HashMap parameters, */
                                /* boolean retainAM, */
                                /* String addBreadCrumb*/
                                pageContext.forwardImmediatelyToCurrentPage(hashMap, 
                                                                            false, 
                                                                            OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
                            /*  throw new OAException(strFillV2InvSoliDtlVOInfo,OAException.ERROR); */
                        }
                    } /** END if(null!=strFillV2InvSoliDtlVOInfo){ **/

                } /** END if(null!=strKitID){ **/
            } /** END  if(null!=strKitCode&&!"".equals(strKitCode) **/

        } /** END  if("AvailableKitEvt".equals(pageContext.getParameter(this.EVENT_PARAM))){ **/

        if (this.LOV_PREPARE.equals(strEvent)) {
            if ("V2TallaNbr".equals(strLovInputSourceId)) {
                OARow oaRow = 
                    (OARow)InvSolicitudAMImpl.findRowByRef(strEvtSrcRowRef);
                if (null != oaRow) {
                    oracle.jbo.domain.Number numDotaID = 
                        (oracle.jbo.domain.Number)oaRow.getAttribute("DotaId");
                    pageContext.putSessionValue("SoliDtlValue", 
                                                numDotaID.toString());
                } /** End  if(null!=oaRow){ **/
            }
        } /** End if(this.LOV_PREPARE.equals(strEvent)){ **/

        if ("V2GrabarEvt".equals(pageContext.getParameter(this.EVENT_PARAM))) {
            OAMessageChoiceBean AvailableKitsBean = 
                (OAMessageChoiceBean)webBean.findChildRecursive("AvailableKits");
            String strGrabKitCode = null;
            if (null != AvailableKitsBean) {
                strGrabKitCode = 
                        (String)AvailableKitsBean.getValue(pageContext);
            }
            if (null != strGrabKitCode && !"".equals(strGrabKitCode)) {

                /**********************************************************************************
         String strValAllLinesV2InvSoliDtlVO = InvSolicitudAMImpl.valAllLinesV2InvSoliDtl();
         if(null!=strValAllLinesV2InvSoliDtlVO){
           if(strValAllLinesV2InvSoliDtlVO.contains("EXCEPTION")){
            throw new OAException(strValAllLinesV2InvSoliDtlVO,OAException.ERROR);
           }
          }
        **********************************************************************************/
                com.sun.java.util.collections.List listValAllLinesV2InvSoliDtlVO = 
                    InvSolicitudAMImpl.valAllLinesV2InvSoliDtl();
                if (null != listValAllLinesV2InvSoliDtlVO) {
                    if (listValAllLinesV2InvSoliDtlVO.size() > 0) {
                        OAException.raiseBundledOAException(listValAllLinesV2InvSoliDtlVO);
                    }
                } /** END if(null!=listValAllLinesV2InvSoliDtlVO){ **/

                com.sun.java.util.collections.List listValAmountsV2InvSoliDtl = 
                    InvSolicitudAMImpl.valAmountsV2InvSoliDtl();
                if (null != listValAmountsV2InvSoliDtl) {
                    if (listValAmountsV2InvSoliDtl.size() > 0) {
                        OAException.raiseBundledOAException(listValAmountsV2InvSoliDtl);
                    }
                } /** END  if(null!=listValAmountsV2InvSoliDtl){ **/

                /************ Mudar al Controlador Processing Request ******
          *****************************************************************
         String strFillSoliIDKitIDInfo = InvSolicitudAMImpl.fillSoliIDKitIDInfo(strGrabKitCode);
         if(null!=strFillSoliIDKitIDInfo){
           if(strFillSoliIDKitIDInfo.contains("EXCEPTION")){
            throw new OAException(strFillSoliIDKitIDInfo,OAException.ERROR);
           }

          ** Mudar mas abajo InvSolicitudAMImpl.getOADBTransaction().commit(); **
         } *** END if(null!=strFillSoliIDKitIDInfo){ **

         String strFillSoliDtlIDInfo = InvSolicitudAMImpl.fillSoliDtlIDInfo();
         if(null!=strFillSoliDtlIDInfo){
           if(strFillSoliDtlIDInfo.contains("EXCEPTION")){
             throw new OAException(strFillSoliDtlIDInfo,OAException.ERROR);
           }
           InvSolicitudAMImpl.getOADBTransaction().commit();
         }

         String strCallValidarSustitutos = InvSolicitudAMImpl.callValidarSustitutos();
         if(null!=strCallValidarSustitutos){
           if(strCallValidarSustitutos.contains("EXCEPTION")){
             throw new OAException(strCallValidarSustitutos,OAException.ERROR);
           }

           xXGamInvSoliDtlVOImpl invSoliDtlVOImpl1 = (xXGamInvSoliDtlVOImpl)InvSolicitudAMImpl.findViewObject("xXGamInvSoliDtlVO1");
           invSoliDtlVOImpl1.clearCache();
           invSoliDtlVOImpl1.initQuery(strCallValidarSustitutos);
           Finaliza Mudar al Controlador Processing Request ******
           *********************************************************************/
                /****
           MessageToken[] tokens = new MessageToken[]{new MessageToken("MESSAGE", "Se han guardado los cambios con \u00e9xito.")};
           OAException message = new OAException("FND", "FND_GENERIC_MESSAGE", tokens, (byte)3, (Exception[])null);
           pageContext.putDialogMessage(message);
           InvSolicitudAMImpl.validaCicloConFechaEntrega(pageContext, webBean);
           ****/

                pageContext.putSessionValue("sGrabKitCode", strGrabKitCode);

                OAProcessingPage processingPage = 
                    new OAProcessingPage("xxgam.oracle.apps.inv.moveorder.webui.XxGamProcessingSolCrearCO");
                String msg = "Procesando...";
                processingPage.setConciseMessage(msg);
                msg = "Esperar un momento...";
                processingPage.setDetailedMessage(msg);
                msg = "Dotacion de Prendas";
                processingPage.setProcessName(msg);
                processingPage.setRetainAMValue(true);
                pageContext.forwardToProcessingPage(processingPage);

            } /** End if(null!=strGrabKitCode&&!"".equals(strGrabKitCode)){ **/
            else {
                throw new OAException("No se ha seleccionado un kit.", 
                                      OAException.ERROR);
            }

        }

    } /** END method processFormRequest **/

    /**
     * Metodo que deshabilita las regiones de la pagina 
     * @param pageContext
     * @param webBean
     */
    private void disablePageRegions(OAPageContext pageContext, 
                                    OAWebBean webBean) {
        OAWebBean SingleColumnCreaRG1Bean = 
            webBean.findChildRecursive("SingleColumnCreaRG1");
        if (null != SingleColumnCreaRG1Bean) {
            SingleColumnCreaRG1Bean.setRendered(false);
        }
        OAWebBean SingleColumnCreaRG2Bean = 
            webBean.findChildRecursive("SingleColumnCreaRG2");
        if (null != SingleColumnCreaRG2Bean) {
            SingleColumnCreaRG2Bean.setRendered(false);
        }
        OAWebBean SingleColumnCreaRG3Bean = 
            webBean.findChildRecursive("SingleColumnCreaRG3");
        if (null != SingleColumnCreaRG3Bean) {
            SingleColumnCreaRG3Bean.setRendered(false);
        }
        OAWebBean SingleColumnCreaRG4Bean = 
            webBean.findChildRecursive("SingleColumnCreaRG4");
        if (null != SingleColumnCreaRG4Bean) {
            SingleColumnCreaRG4Bean.setRendered(false);
        }
    } /** END Method disablePageRegions **/

}


