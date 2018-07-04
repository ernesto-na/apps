/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.xbol.maf.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.domain.Number;

import xxgam.oracle.apps.xbol.maf.utils.XxGamConstantsUtil;
import xxgam.oracle.apps.xbol.maf.utils.XxGamMAnticiposUtil;


/**
 * Clase controladora del detalle de aprobaciÃ³n de la solicitud de anticipo.
 * Definida por el XML XxGamApprovalRequestDetailPG
 * 
 * @author Manuel Guinto.
 */
public class XxGamApprovalRequestDetailCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    /**
     * Resumen de la pagina de pago de la solicitud de anticipo.
     */
    public static final String XX_GAM_PAYMENT_REQ_REVIEW_PG = 
        "XxGamApprovalRequestPG";

    /**
     * Resumen de los boletos de avión
     */
    public static final String PAGE_DETAIL_AIRPLANE = 
        "XxGamPaymentReqTicketPlanePG";

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        setRenderedElements(pageContext, webBean);
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

        //Busca los registros
        String control = null;
        control = pageContext.getParameter(XxGamConstantsUtil.CANCEL);
        if (control != null) {

            String sURL = null;
            //Aplica rollback
            XxGamMAnticiposUtil.setRollback(pageContext, webBean);
            sURL = 
XxGamConstantsUtil.URL_PAGE_OAF + XX_GAM_PAYMENT_REQ_REVIEW_PG;

            //Redirecciona a la pagina principal
            XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, webBean, 
                                                         null, sURL);
        }

        control = pageContext.getParameter(XxGamConstantsUtil.APPROVE);

        //Muestra el detalle de la solicitud
        if (XxGamConstantsUtil.SHOW_DETAIL_REQUEST.equals(pageContext.getParameter(EVENT_PARAM)))
            setForwardWhitParameters(pageContext, webBean);

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
        String sURL = null;
        String sParam = null;
        Number nPaymentId = null;

        //Obtiene los parametros y los inicializa


        sURL = XxGamConstantsUtil.URL_PAGE_OAF + PAGE_DETAIL_AIRPLANE;
        String requestType = null;
        requestType = XxGamMAnticiposUtil.getRequestType(pageContext, webBean);
        if (requestType != null && 
            requestType.equals("Solicitud de anticipos")) {

            sParam = pageContext.getParameter(XxGamConstantsUtil.PAYMENT_ID);
            nPaymentId = XxGamMAnticiposUtil.converteNumber(sParam);
            XxGamMAnticiposUtil.searchPayment(pageContext, webBean, 
                                              nPaymentId);

        }
        //Inicializa los parametros
        XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, webBean, 
                                                     null, sURL);
    }


    private void setRenderedElements(OAPageContext pageContext, 
                                     OAWebBean webBean) {
        String requestType = null;
        requestType = XxGamMAnticiposUtil.getRequestType(pageContext, webBean);
        if (requestType != null && 
            requestType.equals("Solicitud de anticipos")) {
            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                     "XxGamMaPaymentReqVO2", 
                                                     true);
            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                     "XxGamMaGeneralReqVO1", 
                                                     false);
        } else if (requestType != null && requestType.equals("Franquicias")) {

            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                     "XxGamMaGeneralReqVO1", 
                                                     true);
            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                     "XxGamMaPaymentReqVO2", 
                                                     false);
        }
    }
}
