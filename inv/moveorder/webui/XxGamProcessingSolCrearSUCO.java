package xxgam.oracle.apps.inv.moveorder.webui;

import com.sun.java.util.collections.HashMap;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSolicitudAMImpl;

public class XxGamProcessingSolCrearSUCO extends OAControllerImpl
{
  @Override
  public void processFormRequest(OAPageContext pageContext, OAWebBean webBean)
   {
    super.processFormRequest(pageContext, webBean);
    if(null==pageContext||null==webBean){
     return;
    }
     xXGamInvSolicitudAMImpl InvSolicitudAMImpl = (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
     if(null!=InvSolicitudAMImpl){
       String strSVGrabKitCode = (String)pageContext.getSessionValue("sGrabKitCode");
       pageContext.removeSessionValue("sGrabKitCode");
       pageContext.putSessionValue("sGrabKitCode","");
         
       String strFillInvSoliVOInfo =  InvSolicitudAMImpl.fillInvSoliVOInfoSU(strSVGrabKitCode); 
       if(null!=strFillInvSoliVOInfo){
         if(strFillInvSoliVOInfo.contains("EXCEPTION")){
          throw new OAException(strFillInvSoliVOInfo,OAException.ERROR); 
         }
       } 
       
       String strFillSoliDtlIDInfoSU = InvSolicitudAMImpl.fillSoliDtlIDInfoSU(strFillInvSoliVOInfo);
       if(null!=strFillSoliDtlIDInfoSU){
         if(strFillSoliDtlIDInfoSU.contains("EXCEPTION")){
           throw new OAException(strFillSoliDtlIDInfoSU,OAException.ERROR); 
         }
         InvSolicitudAMImpl.getOADBTransaction().commit();
       }
       
       
       String sURL = "OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudCrearSuperUserPG"; 
       HashMap hParameters = null; 
       hParameters =new HashMap();
       hParameters.put("pSuccessSoliID",strFillInvSoliVOInfo); 
       /* hParameters.put("pSuccessCallApi",strCreateSalesOrderByApi); */
       pageContext.forwardImmediately(sURL /** String url **/
                               ,null /** String functionName **/
                               ,OAWebBeanConstants.KEEP_MENU_CONTEXT /** byte menuContextAction **/
                               ,null /** String menuName **/
                               ,hParameters /** com.sun.java.util.collections.HashMap parameters **/
                               ,true /** boolean retainAM **/
                               ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO /** String addBreadCrumb **/ 
                               );
                               
     }
   }     
}
