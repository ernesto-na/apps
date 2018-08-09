/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.xbol.maf.webui;

import com.sun.java.util.collections.HashMap;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import xxgam.oracle.apps.xbol.maf.utils.XxGamAOLMessages;
import xxgam.oracle.apps.xbol.maf.utils.XxGamConstantsUtil;
import xxgam.oracle.apps.xbol.maf.utils.XxGamMAnticiposUtil;


/**
 * Clase controladora de la página de consulta de boletos de avión.
 * Página definida por el XML XxGamATicketConsultationPG
 * @author Manuel Guinto.
 */
public class XxGamATicketConsultationCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    /**
     * Resumen de la pagina de pago de la solicitud de anticipo.
     */
    public static final String XX_GAM_PAYMENT_REQ_REVIEW_PG = 
        "XxGamPaymentReqTicketPlanePG";

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);

        if (!pageContext.isFormSubmission()) {
            System.out.println("FormSubmissionTickConsulPGBug1");
            if (null != pageContext.getParameter("pfranchiseType") && 
                !"".equals(pageContext.getParameter("pfranchiseType"))) {
                pageContext.putSessionValue("sfranchiseType", 
                                            pageContext.getParameter("pfranchiseType"));
            }
            if (null != pageContext.getParameter("pRequest") && 
                !"".equals(pageContext.getParameter("pRequest"))) {
                pageContext.putSessionValue("sRequest", 
                                            pageContext.getParameter("pRequest"));
            }

            /**** Inicializar View Objects por tipo de franquicias *****/
            exeQueryConsultationTickets(pageContext, webBean);

        } else {
            System.out.println("FormSubmissionTickConsulPGBug2");
            if (null == pageContext.getParameter("pfranchiseType")) {
                if (null != pageContext.getSessionValue("sfranchiseType")) {
                    pageContext.putParameter("pfranchiseType", 
                                             pageContext.getSessionValue("sfranchiseType"));
                }
            }
            if (null == pageContext.getParameter("pRequest")) {
                if (null != pageContext.getSessionValue("sRequest")) {
                    pageContext.putParameter("pRequest", 
                                             pageContext.getSessionValue("sRequest"));
                }
            }
        }

        //deshabilita la cache de la lista para tipos de anticipos

        OAMessageChoiceBean statusNotificationLov = 
            (OAMessageChoiceBean)webBean.findChildRecursive("StatusNotificationLov");
        if (statusNotificationLov != null) {
            statusNotificationLov.setPickListCacheEnabled(false);
            statusNotificationLov.setValue(pageContext, null);
        }

        //searchTicketRequest(pageContext, webBean);
        msgLoadPageProcess(pageContext, webBean);
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

        //Busqueda de la solicitud.
        String searchButton = null;
        searchButton = pageContext.getParameter(XxGamConstantsUtil.SEARCH);
        if (searchButton != null)
            searchTicketRequest(pageContext, webBean);

        //showDetailReq
        //Redirecciona a la siguiente pagina enviando el detalle.
        if (XxGamConstantsUtil.SHOW_DETAIL_REQUEST.equals(pageContext.getParameter(EVENT_PARAM))) {
            System.out.println("miEventoShow" + 
                               XxGamConstantsUtil.SHOW_DETAIL_REQUEST.toString() + 
                               " EVENT_PARAM ");
            setForwardWhitParameters(pageContext, webBean);
        } else {
            System.out.println("miEventoShow" + 
                               XxGamConstantsUtil.SHOW_DETAIL_REQUEST.toString() + 
                               " EVENT_PARAM ");
            setForwardWhitParameters(pageContext, webBean);
        }

    }

    /**
     * Busca las solicitudes de boletos.
     *
     * @param pageContext Contexto de la pagina.
     * @param webBean Web bean.
     */
    public void searchTicketRequest(OAPageContext pageContext, 
                                    OAWebBean webBean) {
        //Verifica nulidad
        System.out.println("Comienza searchTicketRequest CAPA CO ");
        if (pageContext == null || webBean == null)
            return;

        String nameRequester = null;
        String numberPayment = null;
        String typeEm = null;
        String statusReq = null;
        Date fromDate = null;
        Date toDate = null;
        String statusNotiCode = null;
        String operatingUnit = null;

        //Nombre del solicitante
        nameRequester = pageContext.getParameter(XxGamConstantsUtil.NAME_MI);

        //Folio de la solicitud.
        numberPayment = 
                pageContext.getParameter(XxGamConstantsUtil.NUMBER_PAYMENT);

        //Tipo de emisión
        typeEm = pageContext.getParameter(XxGamConstantsUtil.TYPE_EMP_MI);

        //Fecha inicio.
        fromDate = 
                XxGamMAnticiposUtil.converteDate(pageContext, pageContext.getParameter(XxGamConstantsUtil.FROM_DATE));

        //Fecha fin.
        toDate = 
                XxGamMAnticiposUtil.converteDate(pageContext, pageContext.getParameter(XxGamConstantsUtil.TO_DATE));

        //deshabilita la cache de la lista para tipos de anticipos
        OAMessageChoiceBean statusNotificationLov = 
            (OAMessageChoiceBean)webBean.findChildRecursive("StatusNotificationLov");
        if (statusNotificationLov != null) {
            statusNotiCode = 
                    statusNotificationLov.getSelectionValue(pageContext);
        }
        //Unidad operativa 

        operatingUnit = pageContext.getParameter("OperatingUnitSearch");
        System.out.println("Criterio Unidad Operativa:" + operatingUnit);
        //Inicia la busqueda de la solicitud
        /**
        XxGamMAnticiposUtil.searchTicketRequest(pageContext
                                                ,webBean
                                                ,nameRequester
                                                ,numberPayment
                                                ,typeEm
                                                ,statusReq
                                                ,fromDate
                                                ,toDate
                                                ,statusNotiCode);
        **/
        // Llamar a un metodo sobrecargado 
        XxGamMAnticiposUtil.searchTicketRequest(pageContext, webBean, 
                                                nameRequester, numberPayment, 
                                                typeEm, statusReq, fromDate, 
                                                toDate, statusNotiCode, 
                                                operatingUnit);


        System.out.println("Finaliza searchTicketRequest CAPA CO ");
    }

    /**
     * Inicializan los parametros que se van a enviar al detalle.
     *
     * @param pageContext Contexto de la pagina
     * @param webBean Web bean.
     */
    private void setForwardWhitParameters(OAPageContext pageContext, 
                                          OAWebBean webBean) {

        //VErifica nulidad
        if (pageContext == null || webBean == null)
            return;

        //Inicializa los parametros
        HashMap hParameters = new HashMap();
        String sURL = null;
        String sParam = null;
        Number nRequestId = null;
        Number generalId = null;
        Number paymentId = null;
        String typeRequest = null;
        String operatingUnit = null;
        String statusNotiCode = null;
        try {

            //Obtiene los parametros y los inicializa
            sParam = pageContext.getParameter(XxGamConstantsUtil.TICKET_ID);
            nRequestId = XxGamMAnticiposUtil.converteNumber(sParam);
            typeRequest = 
                    pageContext.getParameter(XxGamConstantsUtil.TYPE_REQUEST_TICKET);
            paymentId = 
                    XxGamMAnticiposUtil.converteNumber(pageContext.getParameter(XxGamConstantsUtil.PAYMENT_ID));
            generalId = 
                    XxGamMAnticiposUtil.converteNumber(pageContext.getParameter(XxGamConstantsUtil.GENERAL_ID));
            operatingUnit = 
                    pageContext.getParameter(XxGamConstantsUtil.OPERATING_UNIT);


            OAMessageChoiceBean statusNotificationLov = 
                (OAMessageChoiceBean)webBean.findChildRecursive("StatusNotificationLov");
            /*if (statusNotificationLov != null) {
                statusNotiCode = statusNotificationLov.getSelectionValue(pageContext);

            }*/
            System.out.println("***typeRequest: " + typeRequest + " ***");
            System.out.println("***operatingUnit: " + operatingUnit + " ***");

            if (typeRequest.equals(XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE)) {
                statusNotiCode = 
                        pageContext.getParameter("StatusNotificationDesc");
            } else {
                statusNotiCode = 
                        pageContext.getParameter("StatusNotificationDesc1");
            }
            System.out.println("***statusNotiC: " + statusNotiCode + " ***");

            sURL = 
XxGamConstantsUtil.URL_PAGE_OAF + XX_GAM_PAYMENT_REQ_REVIEW_PG;
            System.out.println("URL: " + sURL);

            hParameters.put(XxGamConstantsUtil.TICKET_ID, sParam);
            hParameters.put(XxGamConstantsUtil.PAYMENT_ID, paymentId);
            hParameters.put(XxGamConstantsUtil.GENERAL_ID, generalId);
            hParameters.put(XxGamConstantsUtil.OPERATING_UNIT, operatingUnit);
            hParameters.put(XxGamConstantsUtil.TYPE_REQUEST_TICKET, 
                            typeRequest);
            hParameters.put("statusNotiCode", statusNotiCode);

            /*******/
            String strFranchiseType = null;
            String strRequestType = null;
            String nvlFranchiseType = null;
            String nvlRequestType = null;


            if (null != pageContext.getParameter("pfranchiseType") && 
                !"".equals(pageContext.getParameter("pfranchiseType"))) {
                strFranchiseType = pageContext.getParameter("pfranchiseType");
            }

            if (null != pageContext.getParameter("pRequest") && 
                !"".equals(pageContext.getParameter("pRequest"))) {
                strRequestType = pageContext.getParameter("pRequest");
            }

            nvlFranchiseType = 
                    (null == strFranchiseType) ? (String)pageContext.getSessionValue("sfranchiseType") : 
                    strFranchiseType;
            nvlRequestType = 
                    (null == strRequestType) ? (String)pageContext.getSessionValue("sRequest") : 
                    strRequestType;

            XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE, 
                                             "franchiseType TickConPG: " + 
                                             nvlFranchiseType, pageContext, 
                                             webBean);
            XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE, 
                                             "Request TickConPG: " + 
                                             nvlRequestType, pageContext, 
                                             webBean);


            if (null == pageContext.getParameter("pfranchiseType")) {
                pageContext.putParameter("pfranchiseType", nvlFranchiseType);
            }
            if (null == pageContext.getParameter("pRequest")) {
                pageContext.putParameter("pRequest", nvlRequestType);
            }
            /*******/


            System.out.println("***statusNotiCode0: " + statusNotiCode + 
                               " ***");
            //Inicializa los parametros
            XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, webBean, 
                                                         hParameters, sURL);

        } catch (Exception exception) {
            System.out.println("--- " + exception.getMessage());
            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, 
                                  XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DATANF_ERROR, 
                                  null, OAException.WARNING, null);
        }
    }

    /**
     * Procesa y muestra mensajes informativos y de error
     * @param pageContext contiene el objeto de OAPageContext procedente de la pantalla de solicitud de boletos
     * @param webBean contiene el objeto de OAWebBeab procedente de la pantalla de solicitud de boletos
     */
    private void msgLoadPageProcess(OAPageContext pageContext, 
                                    OAWebBean webBean) {

        if (pageContext != null && webBean != null) {

            String msg = null;
            msg = 
pageContext.getParameter(XxGamConstantsUtil.LOAD_PAGE_MSG_INFO);
            if (msg != null) {
                //Propaga la excepcion.
                throw new OAException(msg, OAException.INFORMATION);
            }

            msg = null;
            msg = 
pageContext.getParameter(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR);
            if (msg != null) {
                //Propaga la excepcion.
                throw new OAException(msg, OAException.WARNING);
            }
        }
    }

    /**
     * Metodo para inicializar view Objects por tipos de franquicias
     * @param pageContext
     * @param webBean
     */
    private void exeQueryConsultationTickets(OAPageContext pageContext, 
                                             OAWebBean webBean) {
        if (pageContext == null || webBean == null)
            return;

        String nameRequester = null;
        String numberPayment = null;
        String typeEm = null;
        String statusReq = null;
        Date fromDate = null;
        Date toDate = null;
        String statusNotiCode = null;
        String operatingUnit = null;
        // Llamar a un metodo sobrecargado 
        XxGamMAnticiposUtil.searchTicketRequest(pageContext, webBean, 
                                                nameRequester, numberPayment, 
                                                typeEm, statusReq, fromDate, 
                                                toDate, statusNotiCode, 
                                                operatingUnit);

    }

}
