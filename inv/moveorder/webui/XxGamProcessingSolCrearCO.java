package xxgam.oracle.apps.inv.moveorder.webui;

import com.sun.java.util.collections.HashMap;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliDtlVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSolicitudAMImpl;


public class XxGamProcessingSolCrearCO extends OAControllerImpl {
    @Override
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        if (null == pageContext || null == webBean) {
            return;
        }

        xXGamInvSolicitudAMImpl InvSolicitudAMImpl = 
            (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
        if (null != InvSolicitudAMImpl) {
            String strSVGrabKitCode = 
                (String)pageContext.getSessionValue("sGrabKitCode");
            pageContext.removeSessionValue("sGrabKitCode");
            pageContext.putSessionValue("sGrabKitCode", "");
            String strFillSoliIDKitIDInfo = 
                /*strGrabKitCode*/InvSolicitudAMImpl.fillSoliIDKitIDInfo(strSVGrabKitCode);
            if (null != strFillSoliIDKitIDInfo) {
                if (strFillSoliIDKitIDInfo.contains("EXCEPTION")) {
                    throw new OAException(strFillSoliIDKitIDInfo, 
                                          OAException.ERROR);
                }

                /** Mudar mas abajo InvSolicitudAMImpl.getOADBTransaction().commit(); **/
            } /*** END if(null!=strFillSoliIDKitIDInfo){ ***/

            String strFillSoliDtlIDInfo = 
                InvSolicitudAMImpl.fillSoliDtlIDInfo();
            if (null != strFillSoliDtlIDInfo) {
                if (strFillSoliDtlIDInfo.contains("EXCEPTION")) {
                    throw new OAException(strFillSoliDtlIDInfo, 
                                          OAException.ERROR);
                }
                InvSolicitudAMImpl.getOADBTransaction().commit();
            }

            String strCallValidarSustitutos = 
                InvSolicitudAMImpl.callValidarSustitutos();
            if (null != strCallValidarSustitutos) {
                if (strCallValidarSustitutos.contains("EXCEPTION")) {
                    throw new OAException(strCallValidarSustitutos, 
                                          OAException.ERROR);
                }

                xXGamInvSoliDtlVOImpl invSoliDtlVOImpl1 = 
                    (xXGamInvSoliDtlVOImpl)InvSolicitudAMImpl.findViewObject("xXGamInvSoliDtlVO1");
                invSoliDtlVOImpl1.clearCache();
                invSoliDtlVOImpl1.initQuery(strCallValidarSustitutos);

                String sURL = 
                    "OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudPG";
                HashMap hParameters = null;
                hParameters = new HashMap();
                /* hParameters.put("pSuccessCallApi",strCreateSalesOrderByApi); */
                /** String url **/
                    /** String functionName **/
                    /** byte menuContextAction **/
                    /** String menuName **/
                    /** com.sun.java.util.collections.HashMap parameters **/
                    /** boolean retainAM **/
                    /** String addBreadCrumb **/
                    pageContext.forwardImmediately(sURL, null, 
                                                   OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                                   null, hParameters, false, 
                                                   OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
            }

        } /** END if(null!=InvSolicitudAMImpl){ **/

    } /** End Method ProcessFormRequest **/

}
