package xxgam.oracle.apps.inv.moveorder.vta.webui;

import com.sun.java.util.collections.HashMap;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import xxgam.oracle.apps.inv.moveorder.vta.server.XxGamOrderUniformsAMImpl;


public class XxGamProcessingOrderUniformsCO extends OAControllerImpl {
    @Override
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        if (null == pageContext || null == webBean) {
            return;
        }

        XxGamOrderUniformsAMImpl OrderUniformsAMImpl = 
            (XxGamOrderUniformsAMImpl)pageContext.getApplicationModule(webBean);
        if (null != OrderUniformsAMImpl) {
            String strPopulateSOHeaderInfo = 
                OrderUniformsAMImpl.populateSOHeaderInfo();
            if (null != strPopulateSOHeaderInfo) {
                if (strPopulateSOHeaderInfo.contains("EXCEPTION")) {
                    throw new OAException(strPopulateSOHeaderInfo, 
                                          OAException.ERROR);
                }
                /** OrderUniformsAMImpl.getTransaction().commit();  Migrar un paso mas adelante **/
            } /** end strPopulateSOHeaderInfo **/

            String strPopulateSOLinesInfo = 
                OrderUniformsAMImpl.populateSOLinesInfo(strPopulateSOHeaderInfo);
            if (null != strPopulateSOLinesInfo) {
                if (strPopulateSOLinesInfo.contains("EXCEPTION")) {
                    throw new OAException(strPopulateSOLinesInfo, 
                                          OAException.ERROR);
                }
                OrderUniformsAMImpl.getTransaction().commit();
            }

            String strCreateSalesOrderByApi = 
                OrderUniformsAMImpl.createSalesOrderByApi(strPopulateSOHeaderInfo);
            if (null != strCreateSalesOrderByApi) {
                if (strCreateSalesOrderByApi.contains("EXCEPTION")) {
                    throw new OAException(strCreateSalesOrderByApi, 
                                          OAException.ERROR);
                }
            }

            String sURL = 
                "OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/vta/webui/XxGamFollowOrderUniformsHdrPG";
            HashMap hParameters = null;
            hParameters = new HashMap();
            hParameters.put("pSuccessCallApi", 
                            strCreateSalesOrderByApi); /** String url **/
                /** String functionName **/
                /** byte menuContextAction **/
                /** String menuName **/
                /** com.sun.java.util.collections.HashMap parameters **/
                /** boolean retainAM **/
                /** String addBreadCrumb **/
                pageContext.forwardImmediately(sURL, null, 
                                               OAWebBeanConstants.KEEP_MENU_CONTEXT, 
                                               null, hParameters, true, 
                                               OAWebBeanConstants.ADD_BREAD_CRUMB_NO);

        } /** END if(null!=OrderUniformsAMImpl){ **/

    }

}
