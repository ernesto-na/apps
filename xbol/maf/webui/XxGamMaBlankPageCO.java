/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.xbol.maf.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import xxgam.oracle.apps.xbol.maf.utils.XxGamAOLMessages;
import xxgam.oracle.apps.xbol.maf.utils.XxGamConstantsUtil;


/**
 * Clase controlador de la página en blanco de uso generico
 * Página definida por el XML XxGamMaBlankPagePG
 * 
 * @author Aldo López de Nava
 */
public class XxGamMaBlankPageCO extends OAControllerImpl {
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
        System.out.println("Carga pagina XxGamMaBlankPage.");

        String msgNoDataFoundGP = null;

        if (pageContext != null && webBean != null) {
            String msgError = null;
            String msgNoDataFoundPersonId = null;
            String msgNoPersonTypeValid = null;

            msgError = 
                    pageContext.getParameter(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR);

            if (msgError != null) {
                if ("InvalidRespTicketPG".equals(msgError)) {
                    throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, 
                                          XxGamAOLMessages.Validation.XXGAM_MAF_ACCESS_DEN_NR_ERROR, 
                                          null, OAException.ERROR, null);

                } else {
                    throw new OAException(msgError, OAException.ERROR);
                }
            }
            msgNoDataFoundPersonId = 
                    pageContext.getParameter("NoDataFoundPersonId");
            if (null != msgNoDataFoundPersonId) {
                System.out.println("msgNoDataFoundPersonId: " + 
                                   msgNoDataFoundPersonId);
                throw new OAException(msgNoDataFoundPersonId, 
                                      OAException.ERROR);
            }

            msgNoDataFoundGP = pageContext.getParameter("NoDataFoundPG");
            if (null != msgNoDataFoundGP && 
                "NoDataFoundGeneralPage".equals(msgNoDataFoundGP)) {
                throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, 
                                      XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_NF_ERROR, 
                                      null, OAException.ERROR, null);
            }

            msgNoPersonTypeValid = 
                    pageContext.getParameter("NoPersonTypeValid");
            if (null != msgNoPersonTypeValid) {
                System.out.println("msgNoPersonTypeValid: " + 
                                   msgNoPersonTypeValid);
                throw new OAException(msgNoPersonTypeValid, OAException.ERROR);
            }

        }
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
    }

}
