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

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import xxgam.oracle.apps.xbol.maf.utils.XxGamAOLMessages;
import xxgam.oracle.apps.xbol.maf.utils.XxGamConstantsUtil;
import xxgam.oracle.apps.xbol.maf.utils.XxGamMAnticiposUtil;


/**
 * Controlador de la pagina de aprobacion de la solicitud de anticipo.
 * Definida por XML XxGamApprovalRequestPG
 *
 * @author Manuel Guinto.
 */
public class XxGamApprovalRequestCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    /**
     * Resumen de la pagina de pago de la solicitud de anticipo.
     */
    public static final String XX_GAM_PAYMENT_REQ_REVIEW_PG = 
        "XxGamApprovalRequestDetailPG";

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);

        //Reinicia la tabla
        XxGamMAnticiposUtil.resetQueryRN(pageContext, webBean, 
                                         "searchReuqestRN");
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
        String sSearch = null;
        sSearch = pageContext.getParameter(XxGamConstantsUtil.SEARCH);
        if (sSearch != null) {
            searchRequest(pageContext, webBean);

        }


        //Redirecciona a la siguiente pagina enviando el detalle.
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
        HashMap hParameters = new HashMap();
        String sURL = null;
        String sParam = null;
        Number nRequestId = null;

        try {

            //Obtiene los parametros y los inicializa
            sParam = pageContext.getParameter(XxGamConstantsUtil.REQUEST_ID);
            nRequestId = XxGamMAnticiposUtil.converteNumber(sParam);
            boolean isRows = false;
            isRows = 
                    XxGamMAnticiposUtil.searchRequest(pageContext, webBean, nRequestId);

            sURL = 
XxGamConstantsUtil.URL_PAGE_OAF + XX_GAM_PAYMENT_REQ_REVIEW_PG;
            hParameters.put(XxGamConstantsUtil.REQUEST_ID, sParam);

            //Inicializa los parametros
            if (isRows)
                XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                             webBean, 
                                                             hParameters, 
                                                             sURL);
            else
                throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, 
                                      XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DATANF_ERROR, 
                                      null, OAException.WARNING, null);

        } catch (Exception exception) {

            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, 
                                  XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DATANF_ERROR, 
                                  null, OAException.WARNING, null);
        }

    }

    /**
     * Busca el registro por el parametro de busqueda.
     *
     * @param pageContext Contexto de la pagina.
     * @param webBean Web bean.
     */
    private void searchRequest(OAPageContext pageContext, OAWebBean webBean) {

        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return;

        //Declara los recursos
        String idSolicitud = null;
        String sName = null;
        String nCostCenter = null;
        String nAdvanceTemplate = null;
        String purpose = null;
        Date dStartDate = null;
        Date dEndDate = null;
        String sTypeReuqest = null;


        try {

            //Clave de la solicitud
            idSolicitud = 
                    pageContext.getParameter(XxGamConstantsUtil.NUM_REQ_MI);

            //Nombre del solicitante
            sName = 
                    pageContext.getParameter(XxGamConstantsUtil.NAME_EMPLOY_MI);

            //Centro de costos
            nCostCenter = 
                    pageContext.getParameter(XxGamConstantsUtil.COST_CENTER_MI);

            //Plantilla de solicitud
            nAdvanceTemplate = 
                    pageContext.getParameter(XxGamConstantsUtil.ADVANCE_TEMP_MI);

            //Proposito
            purpose = pageContext.getParameter(XxGamConstantsUtil.PURPOSE_MI);

            //Fecha Inicio
            dStartDate = 
                    XxGamMAnticiposUtil.converteDate(pageContext, pageContext.getParameter(XxGamConstantsUtil.START_DATE_MI));

            //Fecha fin
            dEndDate = 
                    XxGamMAnticiposUtil.converteDate(pageContext, pageContext.getParameter(XxGamConstantsUtil.END_DATE_MI));

            sTypeReuqest = 
                    pageContext.getParameter(XxGamConstantsUtil.TYPE_REQUEST);


            //Inicia el proceso de busqueda
            XxGamMAnticiposUtil.searchRequest(pageContext, webBean, 
                                              idSolicitud, sName, nCostCenter, 
                                              nAdvanceTemplate, purpose, 
                                              dStartDate, dEndDate, 
                                              sTypeReuqest);

        } catch (Exception exception) {
            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, 
                                  XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DATANF_ERROR, 
                                  null, OAException.WARNING, null);
        }
    }

}
