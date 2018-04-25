package xxgam.oracle.apps.inv.moveorder.lov.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAListOfValuesBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;

import xxgam.oracle.apps.inv.moveorder.lov.server.xXGamInvUniformesLovAMImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSolicitudAMImpl;

public class xXGamInvSolCO extends OAControllerImpl {

   public static final String RCS_ID = "$Header$";
   public static final boolean RCS_ID_RECORDED = VersionInfo.recordClassVersion("$Header$", "%packagename%");


   public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
      super.processRequest(pageContext, webBean);
   }

   public void processFormRequest(OAPageContext pageContext, OAWebBean webBean) {
      super.processFormRequest(pageContext, webBean);
   }

}