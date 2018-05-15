/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.xbol.maf.webui;

import com.sun.java.util.collections.HashMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Hashtable;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageRadioButtonBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.domain.Number;

import xxgam.oracle.apps.xbol.maf.utils.XxGamAOLMessages;
import xxgam.oracle.apps.xbol.maf.utils.XxGamConstantsUtil;
import xxgam.oracle.apps.xbol.maf.utils.XxGamMAnticiposUtil;
import xxgam.oracle.apps.xbol.maf.utils.XxGamMAnticiposUtil2;


/**
 * Clase controlador para la pagina de solicitud de boleto de avion AM.
 * definida por el XML XxGamPaymentReqTicketPlanePG
 * 
 * @author Gustavo Ramirez Baizabal.
 * @author Gabino Hernï¿½ndez Canales Uso de nuevas tecnologias en Work Flow y ajuste para que sea MultiOrg
 */
public class XxGamPaymentReqTicketCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    private String status;
    private String requestType = null;
    private OAMessageRadioButtonBean messageRadioElectronic = null;
    private OAMessageRadioButtonBean messageRadioPresencial = null;

    OATableBean pRFlight;

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        //Obtiene el URL de la pagina que lo invoco investigar
         XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"franchiseType TickPG: "+pageContext.getParameter("pfranchiseType"),pageContext,webBean);
         XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"Request TickPG: "+pageContext.getParameter("pRequest"),pageContext,webBean);
        
        if (pageContext != null && webBean != null) {

          if (!pageContext.isFormSubmission()) {
            System.out.println("FormSubmissionBug1");
            
            if(null!=pageContext.getParameter("pfranchiseType")&&!"".equals(pageContext.getParameter("pfranchiseType"))){
              pageContext.putSessionValue("sfranchiseType",pageContext.getParameter("pfranchiseType")); 
            }
            if(null!=pageContext.getParameter("pRequest")&&!"".equals(pageContext.getParameter("pRequest"))){
              pageContext.putSessionValue("sRequest",pageContext.getParameter("pRequest")); 
            } 
            
            /*String flightId = null;
            Number flightIdNumber = null;*/
            String NotiCode = pageContext.getParameter("StatusNotCod");
            System.out.println("***NotiCode0: "+NotiCode+" ***");
            messageRadioElectronic = (OAMessageRadioButtonBean)webBean.findIndexedChildRecursive("TypeEmissionElectronico");
            messageRadioPresencial = (OAMessageRadioButtonBean)webBean.findIndexedChildRecursive("TypeEmissionPresencial");
            rendererComponent(pageContext, webBean);
            
              /**Agregado para mostrar los campos si es filial valida AGAA /**/ 
              String operatingUnit = pageContext.getParameter(XxGamConstantsUtil.OPERATING_UNIT);
              getRenderedFilialFields(pageContext,webBean, operatingUnit);
              
            /*String accessForm = (String)pageContext.getTransactionValue(XxGamConstantsUtil.STATUS);
                System.out.println("***accessForm: "+accessForm+" ***");
            
            String statusNotiCode = pageContext.getParameter("statusNotiCode");
                System.out.println("***statusNotiCode1: "+statusNotiCode+" ***");
                
            String ReqId = pageContext.getParameter(XxGamConstantsUtil.GENERAL_ID);
                System.out.println("***ReqId: "+ReqId+" ***");
            
            flightId = pageContext.getParameter("Id");
                    System.out.println("***flightId: "+flightId+" ***");
                    
            flightIdNumber = XxGamMAnticiposUtil.converteNumber(flightId);
                    System.out.println("***flightIdNumber: "+flightIdNumber+" ***");*/

            //Inicializa valores descriptivos
            XxGamMAnticiposUtil.setFlightRouteDescription(pageContext, 
                                                          webBean);
          }else{
             
             if(null==pageContext.getParameter("pfranchiseType")){
               if(null!=pageContext.getSessionValue("sfranchiseType")){
                 pageContext.putParameter("pfranchiseType",pageContext.getSessionValue("sfranchiseType"));
               }
             } 
             if(null==pageContext.getParameter("pRequest")){
              if(null!=pageContext.getSessionValue("sRequest")){
                pageContext.putParameter("pRequest",pageContext.getSessionValue("sRequest"));
              }
             }
          
             String FirePartialEvent = null; 
             String strEventButtonYes = null; 
             String strEventButtonNo = null; 
             System.out.println("FormSubmissionBug2");
             System.out.println("FormSubmissionBug3: "+pageContext.getParameter(XxGamConstantsUtil.BUTTON_NO));
             System.out.println("FormSubmissionBug3.1: "+ pageContext.getParameter(XxGamConstantsUtil.BUTTON_YES));
             System.out.println("FormSubmissionBug4: "+pageContext.getParameter(XxGamConstantsUtil.SEND_CONFIRMATION)); 
             FirePartialEvent = pageContext.getParameter(XxGamConstantsUtil.SEND_CONFIRMATION); 
             strEventButtonYes = pageContext.getParameter(XxGamConstantsUtil.BUTTON_YES); 
             strEventButtonNo = pageContext.getParameter(XxGamConstantsUtil.BUTTON_NO); 
             
             if("".equals(strEventButtonNo)){
               System.out.println("FormSubmissionBug5: '\\0' "); 
             }
             
             if(null!=FirePartialEvent){
                 if(FirePartialEvent.equals(XxGamConstantsUtil.CANCEL)||FirePartialEvent.equals(XxGamConstantsUtil.SEND_CONFIRMATION)||FirePartialEvent.equals(XxGamConstantsUtil.CANCEL_REQUEST)){
                   if(null==strEventButtonYes){
                     XxGamMAnticiposUtil.cleanTickestValues(pageContext,webBean); 
                   }
                 }
              }
              
             String NotiCode = pageContext.getParameter("StatusNotCod");
             System.out.println("***NotiCode0: "+NotiCode+" ***");
             messageRadioElectronic =  (OAMessageRadioButtonBean)webBean.findIndexedChildRecursive("TypeEmissionElectronico");
             messageRadioPresencial =  (OAMessageRadioButtonBean)webBean.findIndexedChildRecursive("TypeEmissionPresencial");
             rendererComponent(pageContext, webBean);
             
           }
           
          /******* Metodos Agregados para Inicializar Clase y Tarifa *****/ 
          XxGamMAnticiposUtil.initTicketClassLov(pageContext, webBean);
          XxGamMAnticiposUtil.initTicketRateLov(pageContext, webBean);
            
        }
    }// fin metodo processRequest

    /**
     * Procedure to handle form submissions for form elements in
     * a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        
        String NotiCode = pageContext.getParameter("StatusNotCod");
        System.out.println("***NotiCode1: "+NotiCode+" ***");
        String Ticket_Office = pageContext.getParameter("TicketOfficeDescription");
        System.out.println("***Ticket Office: "+Ticket_Office+" ***");
        
        String Number_payment = pageContext.getParameter(XxGamConstantsUtil.NUMBER_PAYMENT);
        System.out.println("***Number Payment: "+Number_payment+" ***");
        
        checkEvents(pageContext, webBean);    
    }// fin metodo processFormRequest


    /**
     * Captura todos los eventos generados por los botones en la pagina
     * @param pageContext
     * @param webBean
     */
    private void checkEvents(OAPageContext pageContext, OAWebBean webBean) {
        boolean isRegisteredFlight = false;
        String responsibility = null;
        responsibility = pageContext.getResponsibilityName(); //"GAM_ANTICIPOS_AUDITOR";//pageContext.getResponsibilityName();
        String sURL = null;
        //ADDROW PARA EMPLEADOS
         System.out.println("***Exception1");
         
         /***********************************/
          String strFranchiseType = null; 
          String strRequestType = null; 
          String nvlFranchiseType = null; 
          String nvlRequestType = null; 
                      
                 
          if(null!=pageContext.getParameter("pfranchiseType")&&!"".equals(pageContext.getParameter("pfranchiseType"))){
          strFranchiseType = pageContext.getParameter("pfranchiseType"); 
          }
                       
          if(null!=pageContext.getParameter("pRequest")&&!"".equals(pageContext.getParameter("pRequest"))){
          strRequestType = pageContext.getParameter("pRequest"); 
          } 
                     
          nvlFranchiseType = (null==strFranchiseType)?(String)pageContext.getSessionValue("sfranchiseType"):strFranchiseType; 
          nvlRequestType = (null==strRequestType)?(String)pageContext.getSessionValue("sRequest"):strRequestType; 
         /*************************************/ 
         
        if (XxGamConstantsUtil.BUTTON_ADD_ROW_EMPLOYEE.equals(pageContext.getParameter(EVENT_PARAM))) {
            XxGamMAnticiposUtil.createRowFlight(pageContext, webBean);
            
            /** Obtiene el importe total del boleto AGAA **/
            XxGamMAnticiposUtil.getTotalAmount(pageContext,webBean);
            
            System.out.println("***Exception2");
        }

        if (XxGamConstantsUtil.BUTTON_ADD_ROW_FRANCHISE.equals(pageContext.getParameter(EVENT_PARAM))) {
            XxGamMAnticiposUtil.createRowFlightForFranchise(pageContext, 
                                                            webBean);
            System.out.println("***Exception3");
        }
        
        if("AddRow2".equals(pageContext.getParameter(EVENT_PARAM))){
          System.out.println("Comienza Crear PasajeroInfo Record");    
          XxGamMAnticiposUtil.createRowPasajerosInfoForFranchise(pageContext, 
                                                          webBean);
        }
        
      if("delete41".equals(pageContext.getParameter(EVENT_PARAM))){
        System.out.println("Comienza Borrar PasajeroInfo Record");    
        String sInfoPasajeroId = null;
        Number nInfoPasajeroId = null;
        sInfoPasajeroId = pageContext.getParameter("IdInfoPasajero");
        nInfoPasajeroId = XxGamMAnticiposUtil.converteNumber(sInfoPasajeroId);
        
        java.util.Hashtable params = new java.util.Hashtable(1);
        
        //Asigna los parametros
        params.put("IdInfoPasajero", nInfoPasajeroId);
        
        String msg = null;
        msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DEL_CONF_QN, null);
        
        /***************************************************************************
        XxGamMAnticiposUtil.setMessageDialog(pageContext, 
                                             msg, 
                                             OAException.WARNING, params);
        ***************************************************************************/                                     
        
        XxGamMAnticiposUtil.deleteRowPasajerosInfoForFranchise(pageContext, 
                                                               webBean,nInfoPasajeroId);
      }

        HashMap hmap = null;
        System.out.println("***Exception4: "+pageContext.getParameter(EVENT_PARAM));
        
        //Verifica el evento para cancelar cambios en la solicitud de anticipo
        if (XxGamConstantsUtil.CANCEL.equals(pageContext.getParameter(EVENT_PARAM))) {
            System.out.println("***Exception5");
            java.util.Hashtable params = new java.util.Hashtable(1);
            params.put(XxGamConstantsUtil.SEND_CONFIRMATION, XxGamConstantsUtil.CANCEL);
            String msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_CANCEL_QN, null);
            XxGamMAnticiposUtil.setMessageDialog(pageContext, msg, OAException.INFORMATION, params);
        }
        
        //Verifica el evento para cancelar cambios en la solicitud de anticipo
        if (XxGamConstantsUtil.SEND_CONFIRMATION.equals(pageContext.getParameter(EVENT_PARAM))) {
            System.out.println("***Exception5");
            
            /*** Agregado por Gnosis HCM 06Jul2015     ***/
            XxGamMAnticiposUtil.validateTicketsSendConfirmation(pageContext,webBean); 
            
            java.util.Hashtable params = new java.util.Hashtable(1);
            params.put(XxGamConstantsUtil.SEND_CONFIRMATION, XxGamConstantsUtil.SEND_CONFIRMATION);
            String msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_SEND_C_QN, null);
            XxGamMAnticiposUtil.setMessageDialog(pageContext, msg, OAException.INFORMATION, params);
        }
        
        //Verifica el evento para cancelar la solicitud de anticipo
        if (XxGamConstantsUtil.CANCEL_REQUEST.equals(pageContext.getParameter(EVENT_PARAM))) {
            System.out.println("***Exception7");
          
            /*** Agregado por Gnosis HCM 02Jul2015 ****/
            XxGamMAnticiposUtil.validateTicketsCancel(pageContext,webBean); 
          
            java.util.Hashtable params = new java.util.Hashtable(1);
            params.put(XxGamConstantsUtil.SEND_CONFIRMATION, XxGamConstantsUtil.CANCEL_REQUEST);
            String msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_SEND_X_QN, null);
            XxGamMAnticiposUtil.setMessageDialog(pageContext, msg, OAException.INFORMATION, params);
        }
        
        //Verifica el evento para cancelar cambios en la solicitud de anticipo
        if (XxGamConstantsUtil.SEND.equals(pageContext.getParameter(EVENT_PARAM))) {
          /** Validar que una franquicia se envie con al menos un pasajero  13Aug2015 **/
          /** ï¿½ Funciona para Franquicias ? R= Si solamente en franquicias esta la opcion de Enviar **/
          
          boolean isRegisterPassengers = false; 
          isRegisterPassengers = XxGamMAnticiposUtil.isRegisterPassengers(pageContext
                                                                         ,webBean 
                                                                         ,XxGamConstantsUtil.REQUEST_TYPE_ADVANCE);
          if(!isRegisterPassengers){         
               MessageToken[] arrayOfMessageToken = null;
               OAException localOAException = new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_OIE_NODATAF_PASSENGERS, arrayOfMessageToken, OAException.ERROR, null);        
               OADialogPage localOADialogPage = new OADialogPage(OAException.ERROR , localOAException, null, "", null);
               localOADialogPage.setOkButtonToPost(true);
               localOADialogPage.setOkButtonLabel("Ok");
               localOADialogPage.setPostToCallingPage(true);
               Hashtable localHashtable = new Hashtable(1);
               localOADialogPage.setFormParameters(localHashtable);
               pageContext.redirectToDialogPage(localOADialogPage);
               
              // throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
              //                                                          XxGamAOLMessages.Validation.XXGAM_OIE_NODATAF_PASSENGERS,
              //                                                          null, OAException.ERROR, null);
             }else{      
               System.out.println("***Exception8");
               java.util.Hashtable params = new java.util.Hashtable(1);
               params.put(XxGamConstantsUtil.SEND_CONFIRMATION, XxGamConstantsUtil.SEND);
               String msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_SEND_QN, null);
               XxGamMAnticiposUtil.setMessageDialog(pageContext, msg, OAException.INFORMATION, params);         
             }                                                               
        }
        
        if (XxGamConstantsUtil.NEXT.equals(pageContext.getParameter(EVENT_PARAM))) {
            System.out.println("***Exception9");
            /** Obtiene el importe total del boleto AGAA **/
            XxGamMAnticiposUtil.getTotalAmount(pageContext,webBean);
            
            isRegisteredFlight = 
                    XxGamMAnticiposUtil.isRegisteredFlight(pageContext, 
                                                           webBean, 
                                                           XxGamConstantsUtil.REQUEST_TYPE_ADVANCE);
            if (isRegisteredFlight) {
                System.out.println("***Exception10");
                hmap = new HashMap();
                String calledFrom = 
                    (String)pageContext.getTransactionValue(XxGamConstantsUtil.CALLED_FROM);
                if (XxGamConstantsUtil.VIEW_STEP_TWO.equals(calledFrom)) {
                    System.out.println("***Exception11");
                    sURL = 
        XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_PAYMENT_REQ_DETAIL;
                } else {
                    System.out.println("***Exception12");
                    if (XxGamConstantsUtil.VIEW_STEP_THREE.equals(calledFrom)) {
                        System.out.println("***Exception13");
                        sURL = 
        XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_PAYMENT_REVIEW;
                    }
                }

                XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                             webBean, hmap, 
                                                             sURL);
                System.out.println("***Exception14");
            } else {
                System.out.println("***Exception15");
                throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                  XxGamAOLMessages.Validation.XXGAM_MAF_TKT_F_NE_ERROR,
                                                  null, OAException.WARNING, null);
            }
        }
        
        //Verifica si realmente quiere verificar la operacion reservar fondos
        if (pageContext.getParameter(XxGamConstantsUtil.BUTTON_YES) !=
            null) {
            System.out.println("***OptionYesException16");
            
            String fromConfirm = null;
            fromConfirm = pageContext.getParameter(XxGamConstantsUtil.SEND_CONFIRMATION);
            
            if(XxGamConstantsUtil.CANCEL.equals(fromConfirm)){
                System.out.println("***OptionYesException17");
                
                if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                webBean, 
                                                                new Number(pageContext.getResponsibilityId()), 
                                                                XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)) {
                    System.out.println("***OptionYesException18");

                    isRegisteredFlight = 
                            XxGamMAnticiposUtil.isRegisteredFlight(pageContext, 
                                                                   webBean, 
                                                                   XxGamConstantsUtil.REQUEST_TYPE_ADVANCE);
                    if (isRegisteredFlight) {
                        System.out.println("***OptionYesException19");
                        hmap = new HashMap();
                        //  SI LA RESPONSABILIDAD ES DE EMPLEADO REDIRIGIRA HACIA EL DETALLE
                        sURL = 
                XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_PAYMENT_REQ_DETAIL;
                        XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                     webBean, 
                                                                     hmap, 
                                                                     sURL);
                    } else {
                        System.out.println("***OptionYesException10");
                        throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                          XxGamAOLMessages.Validation.XXGAM_MAF_TKT_F_NE_ERROR,
                                                          null, OAException.WARNING, null);
                    }
                } else if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                       webBean, 
                                                                       new Number(pageContext.getResponsibilityId()), 
                                                                       XxGamConstantsUtil.RESPONSABILITY_FRANCHISE)
                           ||XxGamConstantsUtil.RESPONSABILITY_FRANCHISE.equals(pageContext.getParameter("pRequest"))) {
                    System.out.println("***OptionYesException21");
                    XxGamMAnticiposUtil.removeTransactionValueFromPaymentAdvRequest(pageContext);
                    XxGamMAnticiposUtil.setRollback(pageContext, webBean);
                    //SI LA RESPONSABILIDAD ES DE FRANQUICIA SE REDIRIGIRA HACIA EL ENCABEZADO
                    hmap = new HashMap();
                    sURL = 
                XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_INIT_PAGE;
                    XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                 webBean, hmap, 
                                                                 sURL);
                } else if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                       webBean, 
                                                                       new Number(pageContext.getResponsibilityId()), 
                                                                       XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET)
                          ||XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET.equals(pageContext.getParameter("pRequest"))) {
                    System.out.println("***OptionYesException22");
                    XxGamMAnticiposUtil.removeTransactionValueFromPaymentAdvRequest(pageContext);
                    XxGamMAnticiposUtil.setRollback(pageContext, webBean);
                    sURL = 
                XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_A_TICKET_CONSULTATION;
                    XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                 webBean, null,
                                                                 sURL);
                }
            }else{
                System.out.println("***OptionYesException23");
                //Seccion que verifica si el boton send confirmation de oficina de boletas ha sido accionado
                if (XxGamConstantsUtil.SEND_CONFIRMATION.equals(fromConfirm)) {
                    System.out.println("***OptionYesException24");
                    //Verificamos que el ticket contenga ya un nfolio
                    if (XxGamMAnticiposUtil.isRegisteredFolio(pageContext, 
                                                                   webBean, 
                                                              getRequestType())
                                                              ||XxGamMAnticiposUtil.getEstatusFoliosTicketsBoolean(pageContext, 
                                                                                                                   webBean, 
                                                                                                                   getRequestType())) {
                        System.out.println("***OptionYesException25");
                        String msgConfirm = null;
                        String errorMsg = null;
                        pageContext.getApplicationModule(webBean).getOADBTransaction().putTransientValue("IsValidateEntityFlight", "true");
                        errorMsg = XxGamMAnticiposUtil.setCommit(pageContext, webBean);

                        if (errorMsg == null) {
                            System.out.println("***OptionYesException26");
                            
                            boolean isSuccess = false;
                            isSuccess = callWorkFlowOfficeTicket(pageContext, webBean);
                            if (isSuccess) {
                                System.out.println("***OptionYesException27");
                                msgConfirm = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_FOLIO_SAVE, null);
                            } else {
                                System.out.println("***OptionYesException28");
                                errorMsg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_SAVE_NOTF_ERR, null);
                            }
                        } else {
                            System.out.println("***OptionYesException29");
                            String msgLoadPage = null;
                            msgLoadPage = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_SAVE_CONF_ERROR, null);
                            errorMsg = msgLoadPage + ". " + errorMsg;
                        }

                        try {
                            System.out.println("***OptionYesException30");
                            hmap = new HashMap();
                            if(msgConfirm != null){
                                System.out.println("***OptionYesException31");
                                hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_INFO,
                                         msgConfirm);    
                            }else{
                                System.out.println("***OptionYesException32");
                                if(errorMsg != null){
                                    System.out.println("***OptionYesException33");
                                    hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR,
                                             errorMsg);
                                }
                            }
                            
                            sURL = XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_A_TICKET_CONSULTATION;
                            XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                         webBean, hmap, 
                                                                         sURL);
                            System.out.println("***OptionYesException34");
                        } catch (Exception e) {
                            System.out.println("***OptionYesException35");
                            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                  XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_NAV_DETAIL_ER,
                                                  null, OAException.ERROR, null);
                        }
                    } else {
                        System.out.println("***OptionYesException36");
                        throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                              XxGamAOLMessages.Validation.XXGAM_MAF_TKT_FOLIO_NF_ERRO,
                                              null, OAException.WARNING, null);
                    }
                }else{
                    System.out.println("***OptionYesException37");
                    //Seccion que verifica si el boton send ha sido accionado
                    if (XxGamConstantsUtil.SEND.equals(fromConfirm)) {
                        System.out.println("***OptionYesException38");
                        isRegisteredFlight = 
                                XxGamMAnticiposUtil.isRegisteredFlight(pageContext, 
                                                                       webBean, 
                                                                       XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE);
                        if (isRegisteredFlight) {
                            System.out.println("***OptionYesException39");
                            XxGamMAnticiposUtil.removeTransactionValueFromPaymentAdvRequest(pageContext);
                            hmap = new HashMap();
                            boolean isSuccess = false;
                            String msgLoadPage = null;
                            isSuccess = 
                                    XxGamMAnticiposUtil.setNumberPayment(pageContext, webBean);
                            if (isSuccess) {
                                System.out.println("***OptionYesException40");
                                isSuccess = 
                                        XxGamMAnticiposUtil.setValuesStatusInProgress(pageContext, 
                                                                                      webBean);
                                if (isSuccess) {
                                    System.out.println("***OptionYesException41");

                                    isSuccess = 
                                            XxGamMAnticiposUtil.setRequestPaymentDate(pageContext, 
                                                                                      webBean);
                                    if (isSuccess) {
                                        System.out.println("***OptionYesException42");
                                        XxGamMAnticiposUtil.calculateAmountTotalGeneralReq(pageContext, 
                                                                                           webBean);
                                        pageContext.getApplicationModule(webBean).getOADBTransaction().putTransientValue("IsValidateEntityFlight", "true");
                                        String errorMsg = 
                                            XxGamMAnticiposUtil.setCommit(pageContext, 
                                                                          webBean);
                                        if (errorMsg == null) {
                                            System.out.println("***OptionYesException43");
                                            isSuccess = 
                                                    callWorkFlowOfficeTicket(pageContext, 
                                                                             webBean);
                                                                             
                                            String strNumberPayment = XxGamMAnticiposUtil.getNumberPayment(pageContext, webBean);
                                            if(strNumberPayment == null){
                                                System.out.println("***OptionYesException44");
                                                strNumberPayment = "";  
                                            }
                                            
                                            MessageToken[] tokens = {new MessageToken("NUMBER_PAYMENT", strNumberPayment)};
                                            msgLoadPage = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_SAVE_CONF_INFO, tokens);
                                            System.out.println("***OptionYesException45");
                                            if (!isSuccess) {
                                                System.out.println("***OptionYesException46");
                                                msgLoadPage += ". " + pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_NOTF_ERROR, null);
                                            }else{
                                                System.out.println("***OptionYesException47");
                                                msgLoadPage += ". " + pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_NOTF_INFO, null);
                                            }
                                            hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_INFO, 
                                                     msgLoadPage);
                                        } else {
                                            System.out.println("***OptionYesException48");
                                            msgLoadPage = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_SAVE_CONF_ERROR, null);
                                            msgLoadPage = msgLoadPage + ". " + errorMsg;
                                            hmap = new HashMap();
                                            hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                                     msgLoadPage);
                                        }
                                    } else {
                                        System.out.println("***OptionYesException49");
                                        msgLoadPage = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_SAVE_CONF_ERROR, null);
                                        msgLoadPage += ". " + pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DATE_CFIG_ERROR, null);
                                        hmap = new HashMap();
                                        hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                                 msgLoadPage);
                                    }
                                } else {
                                    System.out.println("***OptionYesException50");
                                    msgLoadPage = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_SAVE_CONF_ERROR, null);
                                    msgLoadPage += ". " + pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_STATUS_CFIG_ERRO, null);
                                    hmap = new HashMap();
                                    hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                             msgLoadPage);
                                }
                            } else {
                                System.out.println("***OptionYesException51");
                                msgLoadPage = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_SAVE_CONF_ERROR, null);
                                msgLoadPage += ". " + pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_NMPAY_CFIG_ERROR, null);
                                hmap = new HashMap();
                                hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                         msgLoadPage);
                            }

                            sURL = 
                    XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_INIT_PAGE;
                            XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                         webBean, hmap, 
                                                                         sURL);
                        } else {
                            System.out.println("***OptionYesException52");
                            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                              XxGamAOLMessages.Validation.XXGAM_MAF_TKT_F_NE_ERROR,
                                                              null, OAException.WARNING, null);
                        }
                    } else {
                        System.out.println("***OptionYesException53");
                        if (XxGamConstantsUtil.CANCEL_REQUEST.equals(fromConfirm)) {
                            System.out.println("***OptionYesException54: "+pageContext.getParameter(EVENT_PARAM));
                                            
                            String msgConfirm = null;
                            String errorMsg = null;
                            String flightId = null;
                            Number flightIdNumber = null;
                            boolean isFranchise = false;
                           //pageContext.getApplicationModule(webBean).getOADBTransaction().putTransientValue("IsValidateEntityFlight", "false");
                           // if (XxGamConstantsUtil.CANCEL_REQUEST.equals(pageContext.getParameter(EVENT_PARAM))) {
                                System.out.println("***OptionYesException55");
                                flightId = pageContext.getParameter("Id");
                                
                                        System.out.println("***OptionYesxxflightId: "+flightId+" ***");
                                    if (flightId == null) {
                                        System.out.println("***OptionYesException56");
                                            flightId = pageContext.getParameter(XxGamConstantsUtil.FLIGHT_ID_FRANCHISE);
                                            isFranchise = true;
                                    }
                                        System.out.println("***OptionYesException57");
                                flightIdNumber = XxGamMAnticiposUtil.converteNumber(flightId);
                                        System.out.println("***OptionYesxxflightIdNumber: "+flightIdNumber+" ***");
                        
                            /****** Comienza nueva manera de cancelacion en los boletos**********/  
                             errorMsg = XxGamMAnticiposUtil.setCommit(pageContext, webBean);
                             if(null==errorMsg){
                               boolean isSuccesCancelWf = false; 
                               isSuccesCancelWf = callWorkFlowToCancelTickets(pageContext, webBean); 
                               if (isSuccesCancelWf) {
                                    msgConfirm = "La cancelacion de boletos ha sido procesada";
                               } else {
                                   errorMsg = "A ocurrido una excepcion al procesar la cancelacion de boletos";
                               }
                               
                             }else{
                               errorMsg="A ocurrido una excepcion al slavar los cambios en una cancelacion de Boletos";
                             }
                            
                            /****** Finaliza nueva manera de cancelacion en los boletos**********/     
                      /*********************************************************************************************************
                            UpdateStatusRequest(pageContext, webBean);
                            System.out.println("***Exception58.TicketId: ");
                            errorMsg = XxGamMAnticiposUtil.setCommit(pageContext, webBean);
                            System.out.println("**************WFException0*********************");
                            
                            boolean isSuccesswf = false;
                            isSuccesswf = callWorkFlowCancelNotification(pageContext, webBean);
                            
                            System.out.println("**************WFException1*********************: "+isSuccesswf);
                            UpdateStatusRequest(pageContext, webBean);
                      ****************************************************************************************************/      
                            try {
                                sURL = XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_A_TICKET_CONSULTATION;
                                
                              hmap = new HashMap();
                              if(msgConfirm != null){
                                  hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_INFO,
                                           msgConfirm);    
                              }else{
                                  if(errorMsg != null){
                                      hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR,
                                               errorMsg);
                                  }
                              }
                              
                                XxGamMAnticiposUtil.setForwardWhitParameters(pageContext,  webBean, hmap, sURL);
                                        
                            } catch (Exception e) {
                                System.out.println("***OptionYesException61");
                                    throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                                              XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_NAV_DETAIL_ER,
                                                                              null, OAException.ERROR, null);
                            }
                        }
                    }
                }
            }
        }

        //Seccion que verifica si el boton de return ha sido accionado
        if (XxGamConstantsUtil.RETURN.equals(pageContext.getParameter(EVENT_PARAM))) {
            System.out.println("***Exception62");
            if (responsibility != null) {
                System.out.println("***Exception63");
                XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"Exception63.2"+nvlRequestType,pageContext,webBean);
                if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                webBean, 
                                                                new Number(pageContext.getResponsibilityId()), 
                                                                XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET)
                   ||XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET.equals(nvlRequestType)) {
                    System.out.println("***Exception64");
                    XxGamMAnticiposUtil.removeTransactionValueFromPaymentAdvRequest(pageContext);
                    sURL = 
                    XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_A_TICKET_CONSULTATION;
                    XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                 webBean, null, 
                                                                 sURL);
                }
                if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                webBean, 
                                                                new Number(pageContext.getResponsibilityId()), 
                                                                XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)) {
                    System.out.println("***Exception65");
                    //  SI LA RESPONSABILIDAD ES DE EMPLEADO REDIRIGIRA HACIA EL DETALLE
                    sURL = XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_PAYMENT_REVIEW;
                    XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                 webBean, null, 
                                                                 sURL);
                } else if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                       webBean, 
                                                                       new Number(pageContext.getResponsibilityId()), 
                                                                       XxGamConstantsUtil.RESPONSABILITY_FRANCHISE)
                          ||XxGamConstantsUtil.RESPONSABILITY_FRANCHISE.equals(pageContext.getParameter("pRequest"))) {
                    System.out.println("***Exception66");
                    String accessForm = 
                        (String)pageContext.getTransactionValue(XxGamConstantsUtil.STATUS);
                    if(XxGamConstantsUtil.CREATE.equals(accessForm) || XxGamConstantsUtil.UPDATE.equals(accessForm)){
                        System.out.println("***Exception67");
                    
                        isRegisteredFlight = 
                                XxGamMAnticiposUtil.isRegisteredFlight(pageContext, 
                                                                       webBean, 
                                                                       XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE);
                        if (isRegisteredFlight) {
                            System.out.println("***Exception68");
                            hmap = new HashMap();
                            //  SI LA RESPONSABILIDAD ES DE EMPLEADO REDIRIGIRA HACIA EL DETALLE
                            sURL = 
                        XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_PAYMENT_REQ_INFO_GENERAL_PG;
                            XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                         webBean, 
                                                                         hmap, 
                                                                         sURL);
                        } else {
                            System.out.println("***Exception69");
                            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                              XxGamAOLMessages.Validation.XXGAM_MAF_TKT_F_NE_ERROR,
                                                              null, OAException.WARNING, null);
                        }
                    }else{
                        System.out.println("***Exception70");
                        //SI LA RESPONSABILIDAD ES DE FRANQUICIA SE REDIRIGIRA HACIA EL ENCABEZADO
                        sURL = 
                        XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_PAYMENT_REQ_INFO_GENERAL_PG;
                        XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                     webBean, null, 
                                                                     sURL);    
                    }
                } else if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                       webBean, 
                                                                       new Number(pageContext.getResponsibilityId()), 
                                                                       XxGamConstantsUtil.RESPONSABILITY_AUDITOR)) {
                    System.out.println("***Exception71");
                    
                    String typeRequest = (String)pageContext.getTransactionValue(XxGamConstantsUtil.TYPE_REQUEST_TICKET);
                    if(typeRequest != null && XxGamConstantsUtil.REQUEST_TYPE_ADVANCE.equals(typeRequest)){
                        System.out.println("***Exception72");
                        sURL = XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_PAYMENT_REVIEW;
                    }else{
                        if(typeRequest != null && XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE.equals(typeRequest)){
                            System.out.println("***Exception73");
                            sURL = XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_PAYMENT_REQ_INFO_GENERAL_PG;
                        }
                    }
                    XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                 webBean, null, 
                                                                 sURL);
                }
            }
        }

        if (XxGamConstantsUtil.DELETE_ROW_EMPLOYEE.equals(pageContext.getParameter(EVENT_PARAM))) {
            System.out.println("***Exception74");
            String flightId = null;
            Number flightIdNumber = null;
            flightId = pageContext.getParameter(XxGamConstantsUtil.FLIGHT_ID);
            flightIdNumber = XxGamMAnticiposUtil.converteNumber(flightId);
            XxGamMAnticiposUtil.deleteRowEmployee(pageContext, webBean, 
                                                  flightIdNumber);
        }

        if (XxGamConstantsUtil.DELETE_ROW_FRANCHISE.equals(pageContext.getParameter(EVENT_PARAM))) {
            System.out.println("***Exception75");
            String flightId = null;
            Number flightIdNumber = null;
            flightId = 
                    pageContext.getParameter(XxGamConstantsUtil.FLIGHT_ID_FRANCHISE);
            flightIdNumber = XxGamMAnticiposUtil.converteNumber(flightId);
            XxGamMAnticiposUtil.deleteRowFranchise(pageContext, webBean, 
                                                   flightIdNumber);
        }

        if ("changeDepartureDate".equals(pageContext.getParameter(EVENT_PARAM))) {
            System.out.println("***Exception76");
            validateDates(pageContext, webBean);
        }
        if ("changeArrivalDate".equals(pageContext.getParameter(EVENT_PARAM))) {
            System.out.println("***Exception77");
            validateDates(pageContext, webBean);
        }
        
      if (pageContext.getParameter(XxGamConstantsUtil.BUTTON_NO) !=
          null) {
           System.out.println("OptionNoBug1"); 
           /**XxGamMAnticiposUtil.setRollback(pageContext,webBean);**/
          }

      if (XxGamConstantsUtil.CALCULATE_TOTAL_AMOUNT.equals(pageContext.getParameter(EVENT_PARAM))) {
          XxGamMAnticiposUtil.getTotalAmount(pageContext,webBean);
      }
        
        /** manejo de los lov  *no utilizado*
        if (pageContext.isLovEvent()) {
            String lovEvent = pageContext.getParameter(EVENT_PARAM);
            if ("lovUpdate".equals(lovEvent)) {
                String lovInputSourceId = pageContext.getLovInputSourceId();
                if ("ClassPass".equals(lovInputSourceId)) {
                    XxGamMAnticiposUtil.getAmountDiscount(pageContext,webBean);                    
                }
            }
        } **/

    }//fin metodo checkEvents()

    /**
     * Renderiza los componentes segun la resposnsabilidad y la accion que se vaya a realizar
     * @param pageContext
     * @param webBean
     */
    private void rendererComponent(OAPageContext pageContext, 
                                   OAWebBean webBean) {

        String msgError = null;
        String urlBase = XxGamConstantsUtil.URL_PAGE_OAF;

        String accessForm = (String)pageContext.getTransactionValue(XxGamConstantsUtil.STATUS);
            
        String statusNotiCode = pageContext.getParameter("statusNotiCode");    
        System.out.println("***Bug1:***");
        //Verifico al responsabilidad 
        if (XxGamMAnticiposUtil.validatesResponsability(pageContext, webBean, 
                                                        new Number(pageContext.getResponsibilityId()), 
                                                        XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)) {
            //VERIFICAR SI EL TICKET PAYMENT TIENE HIJOS
            boolean isRegisteredFlight = 
                XxGamMAnticiposUtil.isRegisteredFlight(pageContext, webBean,
                                                       XxGamConstantsUtil.REQUEST_TYPE_ADVANCE);
            System.out.println("***Bug2:***");
            pageContext.getApplicationModule(webBean).getOADBTransaction().putTransientValue("IsValidateEntityFlight", "false");
            System.out.println("***xxaccessForm: "+accessForm+"***");
            System.out.println("***xxstatusNotiCode2: "+statusNotiCode+"***");
            if (XxGamConstantsUtil.CREATE.equals(accessForm)) {
                System.out.println("***Bug3:***");
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_CREATE_UPDATE, 
                                                         true);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_ANTICIPO_CRATE_UPDATE, 
                                                         true);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_BUTTON_NEXT, 
                                                         true);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_ANTICIPO_READ_ONLY, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_GENERAL_REQ_INDO, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_ANTICIPO_READ_ONLY, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_ANTICIPO, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_FRANCHISE_CREATE_UPDATE, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_READ_ONLY, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_READ_ONLY, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_CREATE_UPDATE, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_BUTTON_SEND, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                         false);
              /** Ocultar nuevas regiones GNOSISHCM/AHH**/
              XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                        "XxGamMaPasajerosInfoVO4", 
                                                        false);
               XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                       "XxGamMaPasajerosInfoVO41", 
                                                       false); 
               XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                       "XxGamMaPasajerosInfoVO411", 
                                                       false);                                              
               XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                        "numBolTableLayout", 
                                                        false);                                                      
               /** Final ocultar nuevas regiones **/              
                                                         
                if (!isRegisteredFlight) {
                    System.out.println("***Bug4:***");
                    try {
                        createFirstNewTicketRow(pageContext, webBean);
                    } catch (OAException exception) {
                    
                        throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                          XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_CREATE_ERROR,
                                                          null, OAException.ERROR, null);
                    }
                }
            } else {
                System.out.println("***Bug5: previo***");
                if (XxGamConstantsUtil.UPDATE.equals(accessForm)) {
                    System.out.println("***Bug5:***");
                    XxGamMAnticiposUtil.setValidatesAllDatesFlight(pageContext, webBean, XxGamConstantsUtil.REQUEST_TYPE_ADVANCE);
                
                    //Obtiene el Status para discrimiar si se muestran los datos en solo lectura o en insercion 
                    status = 
                            XxGamMAnticiposUtil.getGeneralStatus(pageContext, webBean);
                    //Settea el currente row de ticket 
                    XxGamMAnticiposUtil.getMeaningsForAdvance(pageContext, 
                                                              webBean);
                    System.out.println("***Bug6: status "+status+"***");

                    if (status == null || 
                        (status != null && status.equals("INPROGRESS"))) {
                        System.out.println("***Bug7:***");

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_CRATE_UPDATE, 
                                                                 true);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_CREATE_UPDATE, 
                                                                 true);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_NEXT, 
                                                                 true);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_READ_ONLY, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_GENERAL_REQ_INDO, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_ANTICIPO, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_CREATE_UPDATE, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_READ_ONLY, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_READ_ONLY, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_CREATE_UPDATE, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_SEND, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                                 false);
                      /** Ocultar nuevas regiones GNOSISHCM/AHH**/
                      XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                "XxGamMaPasajerosInfoVO4", 
                                                                false);
                       XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                               "XxGamMaPasajerosInfoVO41", 
                                                               false); 
                       XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                               "XxGamMaPasajerosInfoVO411", 
                                                               false);                                              
                       XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                "numBolTableLayout", 
                                                                false);                                                      
                       /**   Final ocultar nuevas regiones **/                                         
                    } else {
                        System.out.println("***Bug8: previo***");
                        if (status != null && !status.equals("INPROGRESS")) {
                            //Se muestran todos los datos en READ-ONLY
                             System.out.println("***Bug8:***");
                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_READ_ONLY, 
                                                                     true);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_ANTICIPO_READ_ONLY, 
                                                                     true);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                                     false);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                                     true);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_GENERAL_REQ_INDO, 
                                                                     false);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_ANTICIPO_CRATE_UPDATE, 
                                                                     false);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_ANTICIPO, 
                                                                     false);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_FRANCHISE_CREATE_UPDATE, 
                                                                     false);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_CREATE_UPDATE, 
                                                                     false);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_READ_ONLY, 
                                                                     false);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_CREATE_UPDATE, 
                                                                     false);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                                     false);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_BUTTON_SEND, 
                                                                     false);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                                     false);
                                                                     
                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                                     false);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                                     false);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_BUTTON_NEXT, 
                                                                     false);

                            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                     XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                                     false);
                           
                          /** Ocultar nuevas regiones GNOSISHCM/AHH**/
                          XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                    "XxGamMaPasajerosInfoVO4", 
                                                                    false);
                           XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                   "XxGamMaPasajerosInfoVO41", 
                                                                   false); 
                           XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                   "XxGamMaPasajerosInfoVO411", 
                                                                   false);                                              
                           XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                    "numBolTableLayout", 
                                                                    false);                                                      
                           /**   Final ocultar nuevas regiones **/
                           
                        }
                    }
                    System.out.println("***Bug9:previo***");
                    if (!isRegisteredFlight) {
                        System.out.println("***Bug9:***");
                        try {
                            createFirstNewTicketRow(pageContext, webBean);
                        } catch (OAException exception) {
                            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                              XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_CREATE_ERROR,
                                                              null, OAException.ERROR, null);
                        }
                    }
                } else {System.out.println("***Bug510: previo***");
                    if (XxGamConstantsUtil.READ_ONLY.equals(accessForm)) {
                        System.out.println("***Bug10:***");
                        XxGamMAnticiposUtil.getMeaningsForAdvance(pageContext, 
                                                                  webBean);
                        OnlyReadDataForAdvance(webBean);
                    } else {
                        System.out.println("***Bug11:***");
                        HashMap hmap = new HashMap();
                        //Redirige la navegacion a la pagina inicial del empleado
                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_ACCESS_DEN_NA_ERROR, null);
                        hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                 msgError);
                        //Redirige a la pagina correspondiente a la responsabilidad 
                        urlBase += XxGamConstantsUtil.XX_GAM_BLANK_PAGE_PG;
                        XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                     webBean, 
                                                                     hmap, 
                                                                     urlBase);
                    }
                }
            }

        } else if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                               webBean, 
                                                               new Number(pageContext.getResponsibilityId()), 
                                                               XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET)
                  ||XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET.equals(pageContext.getParameter("pRequest"))) {
            String folio = null;
            //Obtenemos el Tipo de solicitud "Solicitud de anticipos" o "Franquicias"
            /*
            String typeRequest = pageContext.getParameter(XxGamConstantsUtil.TYPE_REQUEST_TICKET);
            
            pageContext.putTransactionValue(XxGamConstantsUtil.TYPE_REQUEST_TICKET, typeRequest);
             */
             System.out.println("***Bug12:***");
             String typeRequest = pageContext.getParameter(XxGamConstantsUtil.TYPE_REQUEST_TICKET);   // add by DIHU (CCT) 21-Oct-2014
              if (typeRequest != null)
             pageContext.putTransactionValue(XxGamConstantsUtil.TYPE_REQUEST_TICKET,typeRequest); // add by DIHU (CCT) 21-Oct-2014
             else
                 requestType =(String)pageContext.getTransactionValue(XxGamConstantsUtil.TYPE_REQUEST_TICKET); // ADD BY DIHU ONLY FOR TEST
            
            setRequestType(typeRequest);
            System.out.println("***Bug12.010: typeRequest"+typeRequest+"***");
            String errorMsg = null;

            if (getRequestType() != null && 
                getRequestType().equals(XxGamConstantsUtil.REQUEST_TYPE_ADVANCE)) {
                Number generalId = null;
                Number paymentId = null;
                Number ticketId = null;
                try {
                    System.out.println("***Bug12.01:***");
                    generalId = 
                            new Number(pageContext.getParameter(XxGamConstantsUtil.GENERAL_ID));
                    paymentId = 
                            new Number(pageContext.getParameter(XxGamConstantsUtil.PAYMENT_ID));
                    ticketId = 
                            new Number(pageContext.getParameter(XxGamConstantsUtil.TICKET_ID));
                } catch (SQLException e) {
                    errorMsg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_REQ_NF_ERROR, null);
                }

                //Validamos que el folio este setteados
                XxGamMAnticiposUtil.setGeneralAndPayment(pageContext, webBean, 
                                                         generalId, paymentId, 
                                                         ticketId, 
                                                         getRequestType());
                
                                                       
                try {
                    //Obtenemos Folio para discriminar si los datos se muestran en solo lectura o para capturar
                    folio = 
                            XxGamMAnticiposUtil.getFolioTicket(pageContext, webBean, 
                                                               getRequestType());
                    System.out.println("***Bug13:folio "+folio+"***");
                    
                } catch (OAException exception) {
                    errorMsg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_FOLIO_NF_ERROR, null);
                    folio = null;
                }
                
                System.out.println("Comentado ya que para filiales no aparece el centro de costos \n XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, webBean);"); 
                XxGamMAnticiposUtil2.setPaymentReqDescriptionsReadOnly(pageContext, webBean);
                
                if (XxGamConstantsUtil.REQUEST_TYPE_ADVANCE.equals(getRequestType())) {
                    System.out.println("***Bug13.001:***");
                    XxGamMAnticiposUtil.getMeaningsForAdvance(pageContext, webBean);
                } else if (XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE.equals(getRequestType())) {
                    System.out.println("***Bug13.002:***");
                    XxGamMAnticiposUtil.getMeaningsForFranchise(pageContext, webBean);
                }
                
                if (true) {//if (folio == null || folio != null && folio.equals("")) {
                 /* Change By DIHU 15 Octubre 2014
                  * El if Fue forzado a siempre entrar, para dar solucion temporal a un error
                  * de que no se dispara el workflow.
                  */
                  System.out.println("***Bug14: folio "+folio+"***");
                  TransactionUnitHelper.startTransactionUnit(pageContext, "EditRequestTicket");
                  
                  /*** Comienza Agregado informacion de pasajeros no/aplica para anticipos **/
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                              "XxGamMaPasajerosInfoVO4", 
                                                              false);
                     XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             "XxGamMaPasajerosInfoVO41", 
                                                             false); 
                     XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             "XxGamMaPasajerosInfoVO411", 
                                                             false);                                              
                     XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                              "numBolTableLayout", 
                                                              false);             
                  /*** Finaliza Agregado informacion de pasajeros no/aplica para anticipos**/
                  
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_GENERAL_REQ_INDO, 
                                                             true);
                    if ("CAN".equals(statusNotiCode) || folio == "0") {
                        System.out.println("***Bug14.001:***");
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_ANTICIPO, 
                                                                 false); //false
                                                                 
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                                 true); //true
                                                        
                    } else {
                        System.out.println("***Bug14.002:***");
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                                 false);
                                                                 
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_ANTICIPO, 
                                                                 true); //true
                    }
                    
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_READ_ONLY, 
                                                             true);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_READ_ONLY, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_CRATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_NEXT, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_CREATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_CREATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_READ_ONLY, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_CREATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_SEND, 
                                                             false);

                    
                    if ("CAN".equals(statusNotiCode) || folio != null || folio == "0") {
                    
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                                 false);
                        
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                                 false);
                                                                 
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                                 false);
                        
                    } else {
                        
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                                 true);
                        
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                                 true);
                                                                 
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                                 true);
                                                                 
                    }

                    

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                             false);
                    
                    /* Change By DIHU 15 Octubre 2014
                     * El if Fue agregado para colocar en solo lectura el campo de boleto cuando este tenga dato.
                     */
                    
                    if (folio != null && !folio.equals("") || "CAN".equals(statusNotiCode) || folio == "0") 
                    {
                        System.out.println("***Bug15:***");
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_ANTICIPO, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                                 true);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                                 true);
                    }
                    System.out.println("***Bug16: previo***");
                } else if (folio != null && !folio.equals("") || "CAN".equals(statusNotiCode) || folio == "0") {
                    //LISTO
                    // si esta setteado mostramos los datos en solo lectura  
                     System.out.println("***Bug16:***");
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_GENERAL_REQ_INDO, 
                                                             true);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                             true);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_READ_ONLY, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_READ_ONLY, 
                                                             true);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_READ_ONLY, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_CRATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_NEXT, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_ANTICIPO, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_CREATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_CREATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_CREATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                             true);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_SEND, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                             false);
                }
                
                if(errorMsg != null){
                    throw new OAException(errorMsg, 
                                          OAException.ERROR);
                }
            } else if (getRequestType() != null && 
                       getRequestType().equals(XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE)) {
                System.out.println("***Bug17:***");
                Number generalId = null;
                Number ticketId = null;
                try {
                    generalId = 
                            new Number(pageContext.getParameter(XxGamConstantsUtil.GENERAL_ID));
                    ticketId = 
                            new Number(pageContext.getParameter(XxGamConstantsUtil.TICKET_ID));
                } catch (SQLException e) {
                    errorMsg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_REQ_NF_ERROR, null);
                }

                XxGamMAnticiposUtil.setGeneralAndPayment(pageContext, webBean, 
                                                         generalId, null, 
                                                         ticketId, 
                                                         getRequestType());

                /**
                 * Agregado para Inicializar la informacion de Pasajeros par nuevos requiriemientos de franquisias 
                 * 02Jul2015
                 */
                XxGamMAnticiposUtil.setPasajerosInfo(pageContext, webBean, 
                                                       generalId, null, 
                                                       ticketId, 
                                                       getRequestType());
                                                       
               boolean isNewFranchise = false;         
               String  estatusBoletos = null; 
               isNewFranchise = XxGamMAnticiposUtil.validateOldNewFranchise(pageContext, webBean, 
                                                         generalId, null, 
                                                         ticketId, 
                                                         getRequestType());                                      
              /**
               * Obtener Folios para Franquisias recientes y lejanas 
               */
               if(!isNewFranchise){
                   try {
                       //Obtenemos Folio para discriminar si los datos se muestran en solo lectura o para capturar
                       folio = 
                               XxGamMAnticiposUtil.getFolioTicket(pageContext, webBean, getRequestType());
                       System.out.println("***Bug18: folio "+folio+"***");
                       
                   } catch (OAException exception) {
                       errorMsg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_FOLIO_NF_ERROR, null);
                       System.out.println("EXCEPCION al "+exception.getMessage()); 
                       folio = null;
                   }
                   
                 }else if(isNewFranchise){     
                     // Obtenemos Folios para discriminar si la informacion se muestra en modo lectura o para capturar 
                     System.out.println("***GetFoliosTicketsBug1***");
                     estatusBoletos = XxGamMAnticiposUtil.getEstatusFoliosTickets(pageContext, webBean, getRequestType());
                     System.out.println("***GetFoliosTicketsBug2***: "+estatusBoletos);
                 }
               
                
               System.out.println("Comentado ya que para filiales no aparece el centro de costos \n XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, webBean);"); 
               XxGamMAnticiposUtil2.setPaymentReqDescriptionsReadOnly(pageContext, webBean);
              
                System.out.println("***Bug19: folio "+folio+"***");
                //Buscamos los Meaning de algunos Items   
                XxGamMAnticiposUtil.getMeaningsForFranchise(pageContext, 
                                                            webBean);
                //Validamos que el folio este setteados
                    if(true){ //if (folio == null || (folio != null && "".equals(folio))) { 
                    /* Change By DIHU 15 Octubre 2014
                     * El if Fue forzado a siempre entrar, para dar solucion temporal a un error
                     * de que no se dispara el workflow.
                     */
                    //si no  ha sido setteado mostramos todos los compos en solo lectura a excepcion de Folio
                     System.out.println("***Bug20:***");
                     String NotiCode = pageContext.getParameter("StatusNotCod");
                    System.out.println("***statusNotiCode3: "+statusNotiCode+" ***");
                    System.out.println("***statusNotiCode4: "+NotiCode+" ***");
                    
                  /*** Comienza Agregado para que solo se vea en la responsabilidad de Oficina de Boletos modo asociar boletos**/
                 
                  XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                          "XxGamMaPasajerosInfoVO4", 
                                                         false);
                  XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         "XxGamMaPasajerosInfoVO41", 
                                                        false);      
                  if(!isNewFranchise){
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                           "XxGamMaPasajerosInfoVO411", 
                                                          false);  
                                        
                  }else{
                    // Se ocupa de la parte del beneficiario y del numero de boleto
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                           "Beneficiary1", 
                                                          false);  
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                           "Beneficiary11", 
                                                          false);                                        
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                           "folio", 
                                                          false);                                        
                  
                  } 
                  
                  XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         "numBolTableLayout", 
                                                        false);                                       
                  /*** Finaliza Agregado para que solo se vea en la responsabilidad de Oficina de Boletos en modo asociar boletos**/
                    
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_GENERAL_REQ_INDO, 
                                                             true);
                  
                   
                    
                    if ("CAN".equals(statusNotiCode) || folio != null || folio == "0") {
                      System.out.println("***RenderComponentBugOldNewFranchise1:***");
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                                 false);
                                                                 
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                                 false);
                    } /*** Comienza agregado para que no se visualicen los botones de no Usado y Enviar confirmacion en caso de que exista una asociacion en Oficina de boletos***/  
                  else if(null!=estatusBoletos){
                    if("AlMenosUnBoletoCancelado".equals(estatusBoletos)||"AlMenosUnBoletoAsociado".equals(estatusBoletos)){
                        System.out.println("***RenderComponentBugOldNewFranchise2.1:***");
                      XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                               XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                               false);
                                                               
                      XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                               XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                               false);
                      XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                               XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                               false);
                      }else if("AlMenosUnBoletoSinAsociar".equals(estatusBoletos)){
                        System.out.println("***RenderComponentBugOldNewFranchise2.2:***");
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                                 true); //true
                                                                 
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                                true);
                                                                
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                                 true);
                      }
                      
                    }
                  /*** Finaliza agregado para que no se visualicen los botones de no Usado y Enviar confirmacion en caso de que exista una asociacion en Oficina de boletos***/  
                  else {
                      System.out.println("***RenderComponentBugOldNewFranchise3:***");
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                                 true); //true
                                                                 
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                                true);
                                                                
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                                 true);
                    }
                   
                    

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_READ_ONLY, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_READ_ONLY, 
                                                             true);
                                                             
                    if ("CAN".equals(statusNotiCode) || folio == "0") {
                      System.out.println("***RenderComponentBugOldNewFranchise4:***");
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                             false); //false
															 
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                             true); //true
			
                    } else if (null!=estatusBoletos){
                          if("AlMenosUnBoletoCancelado".equals(estatusBoletos)||"AlMenosUnBoletoAsociado".equals(estatusBoletos)){
                          System.out.println("***RenderComponentBugOldNewFranchise5.1:***");
                          XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                               XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                               false); //false
                                 
                          XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                               XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                               true); //true
                          OAMessageTextInputBean mtib = null; 
                          mtib =(OAMessageTextInputBean)webBean.findIndexedChildRecursive("TicketNumberItem411"); 
                          if(null!=mtib){
                            mtib.setReadOnly(true);
                            mtib.setShowRequired(false);
                          }
                        }else if("AlMenosUnBoletoSinAsociar".equals(estatusBoletos)){
                        System.out.println("***RenderComponentBugOldNewFranchise5.2:***");
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                             true); //true
                        
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                             false); //false
                      }
                      
                    } else {
                      System.out.println("***RenderComponentBugOldNewFranchise6:***");
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                             true); //true
						
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                             false); //false
                        
                    }
			
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                             false);
					
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_ANTICIPO, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_READ_ONLY, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_CRATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_NEXT, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_CREATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_CREATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_CREATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_SEND, 
                                                             false);

                    /* Change By DIHU 15 Octubre 2014
                     * El if Fue agregado para colocar en solo lectura el campo de boleto cuando este tenga dato.
                     */
                    if (folio != null && !"".equals(folio)) 
                    {
                        System.out.println("***Bug21:***");
                      System.out.println("***RenderComponentBugOldNewFranchise7:***");
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_GENERAL_REQ_INDO, 
                                                                 true);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                                 true);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_READ_ONLY, 
                                                                 true);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                                 true);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                                 true);
                    
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                                 false);
                    }else if (null!=estatusBoletos){
                          if("AlMenosUnBoletoCancelado".equals(estatusBoletos)||"AlMenosUnBoletoAsociado".equals(estatusBoletos)){
                          System.out.println("***RenderComponentBugOldNewFranchise8:***");
                          XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                   XxGamConstantsUtil.XX_GAM_GENERAL_REQ_INDO, 
                                                                   true);
                          XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                   XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                                   true);
                          XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                   XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_READ_ONLY, 
                                                                   true);
                          XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                   XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                                   true);
                          XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                   "ticketNumberLB", 
                                                                   false);
                        }
                    }

                } else if (folio != null && !"".equals(folio)) {
                    System.out.println("***Bug22:***");
                    // si esta setteado mostramos los datos en solo lectura  
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_GENERAL_REQ_INDO, 
                                                             true);
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                             true);
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_READ_ONLY, 
                                                             true);
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                             true);
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                             true);
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_READ_ONLY, 
                                                             false);
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                             false);
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_CRATE_UPDATE, 
                                                             false);
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_NEXT, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_ANTICIPO, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_CREATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_CREATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_CREATE_UPDATE, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_READ_ONLY, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_SEND, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                             false);

                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                             false);
                }  // END if(true){   
                
                System.out.println("***Bug23:***");
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         "CurrencyDesc", 
                                                         false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         "TemplatePayment", 
                                                         false);
                if(errorMsg != null){
                    throw new OAException(errorMsg, 
                                          OAException.ERROR);
                }
            } else {
                System.out.println("***Bug24:***");
                HashMap hmap = new HashMap();
                //Redirige la navegacion a la pagina inicial del empleado
                msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_ACCESS_TR_ND_ERROR, null);
                hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, msgError);
                //Redirige a la pagina correspondiente a la responsabilidad 
                urlBase += XxGamConstantsUtil.XX_GAM_BLANK_PAGE_PG;
                XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                             webBean, hmap, 
                                                             urlBase);
            }   // END else if (getRequestType() != null && getRequestType().equals(XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE))
            
        } else if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                               webBean, 
                                                               new Number(pageContext.getResponsibilityId()), 
                                                               XxGamConstantsUtil.RESPONSABILITY_AUDITOR)) {
            //Obtenemos el Tipo de solicitud "Solicitud de anticipos" o "Facturas"
            String requestType = null;
            System.out.println("***Bug25:***");
            /*
            requestType = pageContext.getParameter(XxGamConstantsUtil.TYPE_REQUEST_TICKET);
            pageContext.putTransactionValue(XxGamConstantsUtil.TYPE_REQUEST_TICKET, requestType);
             */
             requestType = pageContext.getParameter(XxGamConstantsUtil.TYPE_REQUEST_TICKET);   // add by DIHU (CCT) 21-Oct-2014
              if (requestType != null)
             pageContext.putTransactionValue(XxGamConstantsUtil.TYPE_REQUEST_TICKET,requestType); // add by DIHU (CCT) 21-Oct-2014
             else
                 requestType =(String)pageContext.getTransactionValue(XxGamConstantsUtil.TYPE_REQUEST_TICKET); // ADD BY DIHU ONLY FOR TEST
            
            if (requestType != null && 
                XxGamConstantsUtil.REQUEST_TYPE_ADVANCE.equals(requestType) &&
                XxGamConstantsUtil.READ_ONLY.equals(accessForm)) {
                //Buscamos los meanig de algunos Itmes para anticipos
                XxGamMAnticiposUtil.getMeaningsForAdvance(pageContext, 
                                                          webBean);
                System.out.println("***Bug26:***");
                
                /**
                 * @Author EAB Se comento el debido a que no mostraba en el detalle de auditor las respectivas
                 * descripciones.
                 */
                 
                 /*
                XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, 
                                                                      webBean);
                */
                
                
                OnlyReadDataForAdvance(webBean);
            } else {
                if (requestType != null && 
                    XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE.equals(requestType) &&
                    XxGamConstantsUtil.READ_ONLY.equals(accessForm)) {
                    System.out.println("***Bug27:***");
                    //Buscamos los Meaning de algunos Items para Franquicias
                    XxGamMAnticiposUtil.getMeaningsForFranchise(pageContext, 
                                                                webBean);
                  
                  /**
                   * @Author EAB Se comento el debido a que no mostraba en el detalle de auditor las respectivas
                   * descripciones.
                   */
                   
                   /*
                  XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, 
                                                                        webBean);
                  */                                              
                    
                    OnlyReadDataForFranchise(webBean);
                    
                    
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             "CurrencyDesc", 
                                                             false);
                    XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             "TemplatePayment", 
                                                             false);
                } else {
                    System.out.println("***Bug28:***");
                    HashMap hmap = new HashMap();
                    //Redirige la navegacion a la pagina inicial del empleado
                    msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_ACCESS_TR_ND_ERROR, null);
                    hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, msgError);
                    //Redirige a la pagina correspondiente a la responsabilidad 
                    urlBase += XxGamConstantsUtil.XX_GAM_BLANK_PAGE_PG;
                    XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                 webBean, hmap, 
                                                                 urlBase);
                }
            }
        } else if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                               webBean, 
                                                               new Number(pageContext.getResponsibilityId()), 
                                                               XxGamConstantsUtil.RESPONSABILITY_FRANCHISE)
                  ||XxGamConstantsUtil.RESPONSABILITY_FRANCHISE.equals(pageContext.getParameter("pRequest"))) {
            System.out.println("***Bug29:***");

            boolean isRegisteredFlight = 
                XxGamMAnticiposUtil.isRegisteredFlight(pageContext, webBean, 
                                                       XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE);
                                                       
            pageContext.getApplicationModule(webBean).getOADBTransaction().putTransientValue("IsValidateEntityFlight", "false");

            if (XxGamConstantsUtil.CREATE.equals(accessForm)) {

                
                /*** Comienza Agregado para que solo se vea en la responsabilidad de franquicias en modo crear**/
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                        "XxGamMaPasajerosInfoVO41", 
                                                       false);
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                       "XxGamMaPasajerosInfoVO411", 
                                                      false); 
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                        "ticketNumberItem", 
                                                        false);         
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                        "Beneficiary", 
                                                        false);                                     
              /*** Finaliza Agregado para que solo se vea en la responsabilidad de franquisias en modo crear**/
               
                System.out.println("***Bug30:***");
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                         true);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_CREATE_UPDATE, 
                                                         true);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_ANTICIPO_READ_ONLY, 
                                                         false);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                         false);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_GENERAL_REQ_INDO, 
                                                         false);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                         false);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_READ_ONLY, 
                                                         false);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                         true);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_ANTICIPO_CRATE_UPDATE, 
                                                         false);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_ANTICIPO, 
                                                         false);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_CREATE_UPDATE, 
                                                         false);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_READ_ONLY, 
                                                         false);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_BUTTON_SEND, 
                                                         true);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_BUTTON_NEXT, 
                                                         false);


                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                         false);
                                                         
                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                         false);

                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                         true);

                XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                         XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                         false);
                
                if (!isRegisteredFlight) {
                    createTicketWithGeneralReq(pageContext, webBean);
                }
                
            } else {
                System.out.println("***Bug31:***");
                
              /**************************************************************
               * Ajuste por GNOSISHCM/AHH - 06/07/2015
               * Conocer si es nueva franquicia o vieja
               **************************************************************/                
              System.out.println("AJUSTE GNOSISHCM/AHH, Obtiene generalId y ticketId ");
              
              Number requestId = null;
              Number ticketId = null;
              String errorMsg = null;
              boolean isNewFranchise = false;
              
              try {
                   requestId =  new Number(pageContext.getParameter(XxGamConstantsUtil.REQUEST_ID));
                   ticketId = XxGamMAnticiposUtil.getTicketId(pageContext, webBean, requestId);
                  } catch (SQLException e) {
                        errorMsg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_REQ_NF_ERROR, null);
                  }
              
              System.out.println("generalId: " + requestId);
              System.out.println("ticketId: " + ticketId);
              
              isNewFranchise = XxGamMAnticiposUtil.validateOldNewFranchise(pageContext, webBean, 
                                                                           requestId, null, 
                                                                           ticketId, 
                                                                           XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE);
                
                if (XxGamConstantsUtil.UPDATE.equals(accessForm)) {
                    System.out.println("***Bug32:***");
                    XxGamMAnticiposUtil.setValidatesAllDatesFlight(pageContext, webBean, XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE);
                
                    status = 
                            XxGamMAnticiposUtil.getGeneralStatus(pageContext, webBean);
                    //Busca los meaning para Franquicias de los items
                    XxGamMAnticiposUtil.getMeaningsForFranchise(pageContext, 
                                                                webBean);

                    if (status == null || 
                        (status != null && "INPROGRESS".equals(status))) {
                        
                      /*** Comienza Agregado para que solo se vea en la responsabilidad de franquisias en modo Update**/
                      XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                              "XxGamMaPasajerosInfoVO41", 
                                                             false);
                      XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             "XxGamMaPasajerosInfoVO411", 
                                                            false);  
                      XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             "ticketNumberItem", 
                                                            false);  
                      XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             "Beneficiary", 
                                                            false);                                      
                      /*** Finaliza Agregado para que solo se vea en la responsabilidad de franquisias en modo Update**/                                      
                        
                        System.out.println("***Bug33:***");
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_CREATE_UPDATE, 
                                                                 true);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_CREATE_UPDATE, 
                                                                 true);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_READ_ONLY, 
                                                                 false);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                                 false);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_GENERAL_REQ_INDO, 
                                                                 false);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                                 false);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_READ_ONLY, 
                                                                 false);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_CRATE_UPDATE, 
                                                                 false);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_ANTICIPO, 
                                                                 false);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_CREATE_UPDATE, 
                                                                 false);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_READ_ONLY, 
                                                                 false);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_SEND, 
                                                                 true);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_NEXT, 
                                                                 false);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                                 false);
                                                                 
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                                 false);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                                 true);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                                 true);

                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                                 false);

                    } else if (status != null && 
                               !"INPROGRESS".equals(status)) {
                        OnlyReadDataForFranchise(webBean);
                        System.out.println("***Bug34:***");
                    }
                    
                    if (!isRegisteredFlight) {
                        System.out.println("***Bug35:***");
                        createTicketWithGeneralReq(pageContext, webBean);
                    }
                    
                } else {
                    System.out.println("***Bug36:***");
                    if (XxGamConstantsUtil.READ_ONLY.equals(accessForm)) {
                        
                      /*** Comienza Agregado para que solo se vea en la responsabilidad de franquisias en modo solo lectura**/
                      XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                              "XxGamMaPasajerosInfoVO4", 
                                                             false);
                      XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             "XxGamMaPasajerosInfoVO411", 
                                                            false);  
                      XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                             "numBolTableLayout", 
                                                            false);  
                       
                      if(!isNewFranchise){
                        System.out.println("FRANQUICIA VIEJA");
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 "XxGamMaPasajerosInfoVO41", 
                                                                 false);
                      }else{
                        System.out.println("FRANQUICIA NUEVA");
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 "Beneficiary1", 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 "ticketNumberLB",   
                                                                 false);
                      }
                      
                      /*** Finaliza Agregado para que solo se vea en la responsabilidad de franquisias en modo solo lectura**/
                      
                        System.out.println("***Bug37:***");
                        XxGamMAnticiposUtil.getMeaningsForFranchise(pageContext, 
                                                                    webBean);
                        OnlyReadDataForFranchise(webBean);
                        
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 "CurrencyDesc", 
                                                                 false);
                        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                                 "TemplatePayment", 
                                                                 false);
                    } else {
                        System.out.println("***Bug38:***");
                        HashMap hmap = new HashMap();
                        //Redirige la navegacion a la pagina inicial del empleado
                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_ACCESS_DEN_NA_ERROR, null);
                        hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                 msgError);
                        //Redirige a la pagina correspondiente a la responsabilidad 
                        urlBase += XxGamConstantsUtil.XX_GAM_BLANK_PAGE_PG;
                        XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                     webBean, 
                                                                     hmap, 
                                                                     urlBase);
                    }
                }
            }
        } else {
          System.out.println("***Bug39:***");
          msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_ACCESS_DEN_NR_ERROR, null);
          
          HashMap hParameters = new HashMap();
          hParameters.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR,"InvalidRespTicketPG");
          String sURL = urlBase+"XxGamMaBlankPagePG";
          pageContext.setForwardURL(sURL
                                 ,null //  not necessary with KEEP_MENU_CONTEXT
                                 ,OAWebBeanConstants.KEEP_MENU_CONTEXT
                                 ,null // No need to specify since weï¿½re keeping menu context
                                 ,hParameters
                                 ,true  //retain AM 
                                 ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO
                                 ,OAWebBeanConstants.IGNORE_MESSAGES);
        }
    }

    /**
     * Genera un nuevo Ticket apartir dek currentRow de Payment para Empleados
     * @param pageContext
     * @param webBean
     */
    private void createFirstNewTicketRow(OAPageContext pageContext, 
                                         OAWebBean webBean) {
        XxGamMAnticiposUtil.CreateNewTicket(pageContext, webBean);
        System.out.println("***Bug40:***");
    }


    /**
     * Prepara la creacion de un nuevo Ticket apartir del currentRow de General Req Solo Franquicias
     * @param pageContext
     * @param webBean
     */
    private void createTicketWithGeneralReq(OAPageContext pageContext, 
                                            OAWebBean webBean) {
        XxGamMAnticiposUtil.createTicketWithGeneralReq(pageContext, webBean);
        System.out.println("***Bug41:***");
    }

    /**
     * Ejecuta el WorkFlow de la EBS para confirmar el boletos de la oficina de boletos
     * @param pageContext
     * @param webBean
     */
    private boolean callWorkFlowOfficeTicket(OAPageContext pageContext, 
                                             OAWebBean webBean) {
        System.out.println("***Bug42:***");

        String dataForWorkFlow[] = null;
        boolean isSuccess = false;

        try {
            
            dataForWorkFlow = XxGamMAnticiposUtil.getDataForWorkFlowOffieceTicket(pageContext, 
                                                                                  webBean);
            if(dataForWorkFlow != null){
               /****** Agregado por Gabino Hernï¿½ndez Canales Gnosis HCM ****/ 
               System.out.println("Comentado para Utilizar nuevas tecnologias de WF               " +
               "isSuccess =  XxGamMAnticiposUtil.callWorkFlowToSendConfirmationOfficeTicket(pageContext, \n" + 
               " webBean, \n" + 
               " dataForWorkFlow); ");
                  
               isSuccess =  XxGamMAnticiposUtil2.callWorkFlowToSendConfirmationOfficeTicket(pageContext, 
                                                                                             webBean, 
                                                                                     dataForWorkFlow);                                                                          
            }
        } catch (Exception e) {
            isSuccess = false;
        }
        return isSuccess;
    }
    
    /**
     * Ejecuta el WorkFlow de la EBS para cancelar el boletos de la oficina de boletos
     * @param pageContext
     * @param webBean
     */
    private boolean callWorkFlowCancelNotification(OAPageContext pageContext, 
                                             OAWebBean webBean) {
        System.out.println("***WFBug1:***");
                       
        boolean isSuccess = false;

        try {
            isSuccess = 
                        XxGamMAnticiposUtil.sendCancelNotificationTicketPWorkFlow(pageContext, 
                                                                                       webBean);   
            System.out.println("***WFBug2: "+isSuccess+" ***");
        } catch (Exception e) {
            isSuccess = false;
        }
        System.out.println("***WFBug3:***");
        return isSuccess;
    }

    private void OnlyReadDataForAdvance(OAWebBean webBean) {
        System.out.println("***Bug43:***");
        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_GENERAL_REQ_INDO, 
                                                 true);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                 true);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_READ_ONLY, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_READ_ONLY, 
                                                 true);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_READ_ONLY, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_CRATE_UPDATE, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_BUTTON_NEXT, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_ANTICIPO, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_CREATE_UPDATE, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_CREATE_UPDATE, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_CREATE_UPDATE, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                 true);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_BUTTON_SEND, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                 false);
                                                 
        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                 false);
        
      /** Ocultar nuevas regiones GNOSISHCM/AHH**/
      XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                "XxGamMaPasajerosInfoVO4", 
                                                false);
       XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                               "XxGamMaPasajerosInfoVO41", 
                                               false); 
       XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                               "XxGamMaPasajerosInfoVO411", 
                                               false);                                              
       XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                "numBolTableLayout", 
                                                false);                                                      
       /**   Final ocultar nuevas regiones **/                                         
    }


    private void OnlyReadDataForFranchise(OAWebBean webBean) {
        System.out.println("***Bug44:***");
        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_GENERAL_REQ_INDO, 
                                                 true);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_READ_ONLY, 
                                                 true);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_READ_ONLY, 
                                                 true);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_BUTTON_RETURN, 
                                                 true);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                 true);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_READ_ONLY, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_AUDITOR_READ_ONLY, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_CRATE_UPDATE, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_BUTTON_NEXT, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_ANTICIPO, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_CREATE_UPDATE, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_CREATE_UPDATE, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_TICKET_OFFICE_SET_FOLIO_FRANCHISE, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_FRANCHISE_VUELOS_CREATE_UPDATE, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_ANTICIPO_VUELOS_READ_ONLY, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_BUTTON_SEND, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_BUTTON_SEND_CONFIRMATION, 
                                                 false);
                                                 
        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL_REQUEST, 
                                                 false);

        XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                 XxGamConstantsUtil.XX_GAM_BUTTON_CANCEL, 
                                                 false);

    }

    /**
     * Valida las fechas de salida y fin de llegada del vuelo
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     * @return
     */
    private boolean validateDates(OAPageContext pageContext, 
                                  OAWebBean webBean) {
        System.out.println("***Bug45:***");
        boolean isValid = false;
        if (pageContext != null && webBean != null) {

            String rowRef = getRowRefFromTable(pageContext);

            if (rowRef != null) {
            
                isValid = XxGamMAnticiposUtil.validateNotLessCurrentDateFlightFromPage(pageContext,
                                                                                       webBean,
                                                                                       rowRef);
                if(isValid){
                    isValid = 
                            XxGamMAnticiposUtil.validateDatesFlightFromPage(pageContext, 
                                                                            webBean, 
                                                                            rowRef);
                    if (!isValid) {
                        throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                          XxGamAOLMessages.Validation.XXGAM_MAF_FHD_LESS_FHA_ERROR,
                                                          null, OAException.WARNING, null);
                    }
                }else{
                    throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                      XxGamAOLMessages.Validation.XXGAM_MAF_TKT_VAL_CURR_DATE,
                                                      null, OAException.WARNING, null);
                }
            }
        }
        return isValid;
    }
    
    public void UpdateStatusRequest(OAPageContext pageContext, 
                                        OAWebBean webBean) {
                    String ReqId = pageContext.getParameter(XxGamConstantsUtil.GENERAL_ID);
                    String newStatus = "CAN";
                    try {
                            Connection conn = 
                                    pageContext.getApplicationModule(webBean).getOADBTransaction().getJdbcConnection();
                            String Query = 
                                    "UPDATE XXGAM_MA_GENERAL_REQ\n" + "SET STATUS_NOTIFICATION = :1\n" + 
                                    "WHERE ID = :2";
                            PreparedStatement stmt = conn.prepareStatement(Query);
                            stmt.setString(1, newStatus);
                            stmt.setString(2, ReqId);
                            stmt.executeUpdate();
                            conn.commit();

                    } catch (SQLException e) {
                            // do something appropriate with the exception, *at least*:
                            e.printStackTrace();
                    }
        }

    /**
     * Obtiene el registro del vuelo correspondiente a la fila de la tabla
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @return devuelve la referencia del registro
     */
    public String getRowRefFromTable(OAPageContext pageContext) {
        System.out.println("***Bug46:***");
        String rowRef = null;
        rowRef = 
                pageContext.getParameter(OAWebBeanConstants.EVENT_SOURCE_ROW_REFERENCE);
        return rowRef;
    }

    private void setRequestType(String requestType) {
        this.requestType = requestType;
        System.out.println("***Bug47:***");
    }

    private String getRequestType() {
        return requestType;
    }

  /**
   * Metodo para mandar llamar el workflow que cancela los boletos desde oficina de Boletos 
   * @param pageContext
   * @param webBean
   * @return
   */
  private boolean callWorkFlowToCancelTickets(OAPageContext pageContext, 
                                              OAWebBean webBean)
  {
    System.out.println("***CallCancelWFBug1:***");
    boolean isSuccess = false;
    try
        {
          isSuccess = XxGamMAnticiposUtil.callWorkFlowToCancelTickets(pageContext, webBean);
          System.out.println("***CallCancelWFBug2: " + isSuccess + " ***");
        } catch (Exception e) {
          System.out.println("EXCEPCION Al "+e.getMessage()); 
          isSuccess = false;
        }
        System.out.println("***CallCancelWFBug3:***");
        
    return isSuccess; 
  }

    /**
     * muestra o no los campos agregados para filial y descuento segun la 
     * clase de boleto
     * AGAA
     * @param pageContext
     * @param webBean
     */
    public void getRenderedFilialFields(OAPageContext pageContext, 
                                              OAWebBean webBean, String filial){
      if(null == filial)
        filial =(String)pageContext.getTransactionValue("orgNameEmp");
      
      int exists = 
          XxGamMAnticiposUtil.callFunctionExistsFilial(pageContext, 
                                                      webBean, 
                                                      filial);
      boolean isRendered = true;                
      if (exists == 1) {
          isRendered = true;
      } else {
          isRendered = false;
      }
      String items[] = 
        { "Filial",  "ClassPass",     "TotalAmount",   "AmountDiscount",  "Tax",
          "Filial6",  "ClassPass6",   "TotalAmount6",  "AmountDiscount6",  "Tax6", 
          "Filial61", "ClassPass61",  "TotalAmount61", "AmountDiscount61", "Tax61", 
          "ClassPassFV", "DiscountRateFV", "AmountDiscountFV"};
        for (int inc = 0; inc < items.length; inc++) {
            //System.out.println("<<< ITEM: "+ items[inc]);
            XxGamMAnticiposUtil.setRenderedComponent(webBean, items[inc], 
                                                     isRendered);
        }
  }

    
  
}

