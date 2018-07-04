/**/
/**/
package xxgam.oracle.apps.inv.moveorder.siz.lov.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import xxgam.oracle.apps.inv.moveorder.siz.lov.server.XxGamSizesInfoLOVAMImpl;


/**
 * Controller for ...
 */
public class XxGamSizUniformsSizesLOVCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {

        String methodName = "processRequest";
        String strRetval = null;

        System.out.println("Controlador Tabla Lista de Valores.");
        super.processRequest(pageContext, webBean);
        if (null == pageContext || null == webBean) {
            return;
        }

        String strSVDotaID = (String)pageContext.getSessionValue("sDotaID");

        XxGamSizesInfoLOVAMImpl SizesInfoAM = 
            (XxGamSizesInfoLOVAMImpl)pageContext.getApplicationModule(webBean);

        if (null != SizesInfoAM) {
            /*strRetval = SizesInfoAM.initSizesInfo((String)pageContext.getSessionValue("sDotaID"));*/
            if (null != strSVDotaID && !"".equals(strSVDotaID)) {
                strRetval = SizesInfoAM.initSizesInfoNew(strSVDotaID);
                pageContext.removeSessionValue("sDotaID");
                pageContext.putSessionValue("sDotaID", "");
            } else {
                SizesInfoAM.initSizesInfoEmpty();
                pageContext.removeSessionValue("sDotaID");
                pageContext.putSessionValue("sDotaID", "");
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
