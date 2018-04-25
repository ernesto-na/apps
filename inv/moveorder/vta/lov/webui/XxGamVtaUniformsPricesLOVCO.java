/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.inv.moveorder.vta.lov.webui;

import java.sql.SQLException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import xxgam.oracle.apps.inv.moveorder.vta.lov.server.XxGamUniformsLOVAMImpl;

/**
 * Controller for ...
 */
public class XxGamVtaUniformsPricesLOVCO extends OAControllerImpl
{
  public static final String RCS_ID="$Header$";
  public static final boolean RCS_ID_RECORDED =
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

  /**
   * Layout and page setup logic for a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  public void processRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processRequest(pageContext, webBean);
    if(null==pageContext||null==webBean){
     return; 
    }
    System.out.println("In XxGamVtaUniformsPricesLOVCO..");
    System.out.println("searchText: "  + pageContext.getParameter("searchText"));
    String strPriceListHdrID = (String)pageContext.getSessionValue("sPriceListHdrID");
    String strDotaId = (String)pageContext.getSessionValue("sDotaID");
    oracle.jbo.domain.Number numPriceListHdrID = null;
    oracle.jbo.domain.Number numDotaID = null;
    if(null!=strPriceListHdrID&&!"".equals(strPriceListHdrID)){
      try
      {
        numPriceListHdrID = new oracle.jbo.domain.Number(strPriceListHdrID);
      } catch (SQLException sqle)
      {
        throw new OAException(sqle.getMessage(),OAException.ERROR);
      }
    }
    
    if(null!=strDotaId&&!"".equals(strDotaId)){
      try
      {
        numDotaID = new oracle.jbo.domain.Number(strDotaId);
      } catch (SQLException sqle)
      {
        throw new OAException(sqle.getMessage(),OAException.ERROR);
      }
    }
    
    XxGamUniformsLOVAMImpl UniformsLOVAMImpl = (XxGamUniformsLOVAMImpl)pageContext.getApplicationModule(webBean);
    if(null!=UniformsLOVAMImpl){
      if(null!=numPriceListHdrID&&null!=numDotaID){
      UniformsLOVAMImpl.initVtaUniformsPricesLOV(numPriceListHdrID,numDotaID); 
        pageContext.removeSessionValue("sPriceListHdrID");
        pageContext.removeSessionValue("sDotaID");
        pageContext.putSessionValue("sPriceListHdrID","");
        pageContext.putSessionValue("sDotaID","");
      }else{
        UniformsLOVAMImpl.initVtaUniformsPricesLOVEmpty();
      }
    } /** END if(null!=UniformsLOVAMImpl){ **/
    
  }

  /**
   * Procedure to handle form submissions for form elements in
   * a region.
   * @param pageContext the current OA page context
   * @param webBean the web bean corresponding to the region
   */
  public void processFormRequest(OAPageContext pageContext, OAWebBean webBean)
  {
    super.processFormRequest(pageContext, webBean);
  }

}
