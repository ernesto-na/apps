/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.inv.moveorder.lov.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import xxgam.oracle.apps.inv.moveorder.lov.server.xXGamInvUniformesLovAMImpl;


/**
 * Controller for ...
 */
public class xXGamInvTallasAllCO extends OAControllerImpl {
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
        String strSoliDtlValue = 
            (String)pageContext.getSessionValue("SoliDtlValue");
        String strSearchText = pageContext.getParameter("searchText");
        System.out.println("SoliDtlValue: " + strSoliDtlValue);
        System.out.println("searchText: " + strSearchText);

        xXGamInvUniformesLovAMImpl am = 
            (xXGamInvUniformesLovAMImpl)pageContext.getApplicationModule(webBean);
        if (null != strSearchText && null != strSoliDtlValue) {
            am.executeInitial(pageContext.getParameter("searchText"), 
                              pageContext.getSessionValue("SoliDtlValue").toString());
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
