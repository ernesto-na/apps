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
import oracle.apps.fnd.framework.webui.beans.nav.OANavigationBarBean;
import oracle.apps.fnd.framework.webui.beans.nav.OATrainBean;


/**
 * Clase controlador para región de barra de botones que se incluye en las
 * páginas de creación y actualización de solicitudes de anticipo de tipo Empleado.
 * Región definida por el XML XxGamPaymentReqPageButtonBarRN
 * 
 * @author Aldo López de Nava.
 */
public class XxGamPaymentReqPageButtonBarCO extends OAControllerImpl {
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
        OATrainBean trainBean = 
            (OATrainBean)pageContext.getPageLayoutBean().getLocation();
        trainBean.prepareForRendering(pageContext);
        int step = trainBean.getSelectedTrainStepRenderedIndex();
        OANavigationBarBean navBean = 
            (OANavigationBarBean)webBean.findChildRecursive("XxGamPaymentReqNavBarRN");
        navBean.setValue(step + 1);
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
