/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.inv.moveorder.vta.webui;

import java.sql.SQLException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.table.OATableBean;

import oracle.jbo.SQLStmtException;

import xxgam.oracle.apps.inv.moveorder.vta.server.XxGamOrderUniformsAMImpl;

/**
 * Controller for ...
 */
public class XxGamFollowOrderUniformsLinCO extends OAControllerImpl
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
    if(null==pageContext&&null==webBean){
     return; 
    }
    enviromentCurrencyFormat(pageContext,webBean);
    
    XxGamOrderUniformsAMImpl OrderUniformsAMImpl = (XxGamOrderUniformsAMImpl)pageContext.getApplicationModule(webBean);
    oracle.jbo.domain.Number numOperatingUnitID = null; 
    oracle.jbo.domain.Number numSoHeaderId = null; 
    if(null!=pageContext.getParameter("pOperatingUnitId")
      &&null!=pageContext.getParameter("pSoHeaderId")){
     
      try
      {
        numOperatingUnitID = new oracle.jbo.domain.Number (pageContext.getParameter("pOperatingUnitId"));
        numSoHeaderId = new oracle.jbo.domain.Number (pageContext.getParameter("pSoHeaderId"));
      } catch (SQLException sqle)
      {
        throw new OAException("EXCEPTION: "+sqle.getErrorCode()+" , "+sqle.getMessage());
      }
    }
    
    
    if(!pageContext.isFormSubmission()){
      if(null!=OrderUniformsAMImpl){
       try{
        OrderUniformsAMImpl.exeFollowOrderUniformsLines(numOperatingUnitID,numSoHeaderId);
       }catch(SQLStmtException sqlStmt){
        throw new OAException("EXCEPTION Al Ejecutar View Object XxGamFollOrderUnifLinVO:"+sqlStmt.getErrorCode()+" , "+sqlStmt.getMessage(),OAException.ERROR);
       }
      }
    }
    System.out.println("pXxGamHdrId:"+pageContext.getParameter("pXxGamHdrId"));
    
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
    if(null==pageContext&&null==webBean){
      return;
    }
    
    if("ToHdrEvt".equals(pageContext.getParameter(this.EVENT_PARAM))){
      pageContext.forwardImmediately("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/vta/webui/XxGamFollowOrderUniformsHdrPG" /** String url **/
                              ,null /** String functionName **/
                              ,OAWebBeanConstants.KEEP_MENU_CONTEXT /** byte menuContextAction **/
                              ,null /** String menuName **/
                              ,null /** com.sun.java.util.collections.HashMap parameters **/
                              ,true /** boolean retainAM **/
                              ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO /** String addBreadCrumb **/ 
                              );
    }
    
  }
  
  /**
   * Metodo que ambienta los formatos de moneda para los campos de precio y monto.
   * @param pageContext
   * @param webBean
   */
  private void enviromentCurrencyFormat(OAPageContext pageContext, 
                                        OAWebBean webBean)
  {
   OATableBean oaTableBean = (OATableBean)webBean.findChildRecursive("XxGamFollOrderUnifLinVO");
    if(null!=oaTableBean){
      
      OAMessageStyledTextBean UnitListPriceBean = (OAMessageStyledTextBean)oaTableBean.findChildRecursive("UnitListPrice");
      if(null!=UnitListPriceBean){
        UnitListPriceBean.setAttributeValue(OAWebBeanConstants.CURRENCY_CODE,"USD");
      }
      OAMessageStyledTextBean TotalAmountBean  = (OAMessageStyledTextBean)oaTableBean.findChildRecursive("TotalAmount");
      if(null!=TotalAmountBean){
       
        TotalAmountBean.setAttributeValue(OAWebBeanConstants.CURRENCY_CODE,"USD");
      }
    } /*** END if(null!=oaTableBean){ **/
  
  }

}
