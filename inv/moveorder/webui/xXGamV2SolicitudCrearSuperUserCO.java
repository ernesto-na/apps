package xxgam.oracle.apps.inv.moveorder.webui;

import java.sql.SQLException;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAProcessingPage;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;

import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSolicitudAMImpl;

public class xXGamV2SolicitudCrearSuperUserCO  extends OAControllerImpl {
  @Override
  public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
    super.processRequest(pageContext, webBean);
    
    disableOldRegions(pageContext,webBean); 
    
    String strIsSuperUser = "N"; 
    if(null!=pageContext.getProfile("XXGAM_INV_USUARIO_UNIFORME")&&!"".equals(pageContext.getProfile("XXGAM_INV_USUARIO_UNIFORME"))){
      strIsSuperUser = pageContext.getProfile("XXGAM_INV_USUARIO_UNIFORME"); 
      if("Y".equals(strIsSuperUser)){
       System.out.println("Se trata de un Super Usuario.");
      }else if("N".equals(strIsSuperUser)){
        System.out.println("Se trata de Usuario Normal.");
      }else{
        disableRegions(pageContext,webBean); 
        throw new OAException("EXCEPTION Valor:"+strIsSuperUser+" no valido para el perfil XXGAM_INV_USUARIO_UNIFORME.",OAException.ERROR); 
      }
    }
    
    if(!"Y".equals(strIsSuperUser)){
       disableRegions(pageContext,webBean); 
      throw new OAException("EXCEPTION Pantalla de uso exclusivo para usuarios que contengan asignado el Perfil XXGAM_INV_USUARIO_UNIFORME.",OAException.ERROR); 
     }
    
    xXGamInvSolicitudAMImpl InvSolicitudAMImpl = (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
    
    if(!pageContext.isFormSubmission()){
    
    String strExceptValPeriodKit = pageContext.getParameter("pExceptValPeriodKit"); 
    
    if(null!=strExceptValPeriodKit&&!"".equals(strExceptValPeriodKit)){
      pageContext.removeParameter("pExceptValPeriodKit");
      pageContext.putParameter("pExceptValPeriodKit","");
      if(null!=InvSolicitudAMImpl){
      InvSolicitudAMImpl.filterInvAvailableKitsVO();
      }
      throw new OAException(strExceptValPeriodKit,OAException.ERROR); 
    } 
    
    String strExceptValSoloUnaUNI = pageContext.getParameter("pExceptValSoloUnaUNI");
    if(null!=strExceptValSoloUnaUNI&&!"".equals(strExceptValSoloUnaUNI)){
      pageContext.removeParameter("pExceptValSoloUnaUNI");
      pageContext.putParameter("pExceptValSoloUnaUNI","");
      if(null!=InvSolicitudAMImpl){
      InvSolicitudAMImpl.filterInvAvailableKitsVO();
      }
      throw new OAException(strExceptValSoloUnaUNI,OAException.ERROR); 
    } 
    
    String strExceptValHistoryInfo = pageContext.getParameter("pExceptValHistoryInfo"); 
    if(null!=strExceptValHistoryInfo&&!"".equals(strExceptValHistoryInfo)){
      pageContext.removeParameter("pExceptValHistoryInfo");
      pageContext.putParameter("pExceptValHistoryInfo","");
      if(null!=InvSolicitudAMImpl){
      InvSolicitudAMImpl.filterInvAvailableKitsVO();
      }
      throw new OAException(strExceptValHistoryInfo,OAException.ERROR);
    } 
    
    String strSucessSoliID = pageContext.getParameter("pSuccessSoliID"); 
    if(null!=strSucessSoliID&&!"".equals(strSucessSoliID)){
      pageContext.removeParameter("pSuccessSoliID");
      pageContext.putParameter("pSuccessSoliID","");
      
      if(null!=InvSolicitudAMImpl){
      InvSolicitudAMImpl.filterInvAvailableKitsVO();
     /** InvSolicitudAMImpl.cleanV2InvSoliVOInfo(); **/
     /** InvSolicitudAMImpl.cleanV2InvSoliDtlVOInfo(); **/
      InvSolicitudAMImpl.getxXGamInvSoliVO1().clearCache();
      InvSolicitudAMImpl.getxXGamInvSoliVO1().initQuery(strSucessSoliID);
      InvSolicitudAMImpl.getxXGamInvSoliDtlVO1().clearCache();
      InvSolicitudAMImpl.getxXGamInvSoliDtlVO1().initQuery(strSucessSoliID);
      } /** END if(null!=InvSolicitudAMImpl){ **/
      throw new OAException("UNI-"+strSucessSoliID+" creada satisfactoriamente.",OAException.CONFIRMATION);
    }else{
      if(null!=InvSolicitudAMImpl){
        InvSolicitudAMImpl.filterInvAvailableKitsVO(); 
        InvSolicitudAMImpl.cleanV2InvSoliVOInfo();
        InvSolicitudAMImpl.cleanV2InvSoliDtlVOInfo();
        InvSolicitudAMImpl.getxXGamInvSoliVO1().clearCache();
        InvSolicitudAMImpl.getxXGamInvSoliVO1().initQuery("-1");
        InvSolicitudAMImpl.getxXGamInvSoliDtlVO1().clearCache();
        InvSolicitudAMImpl.getxXGamInvSoliDtlVO1().initQuery("-1");
      } /** END if(null!=InvSolicitudAMImpl){ **/
    }
    
   }else{
     
   } /** End if(!pageContext.isFormSubmission()){ **/
    
    
  }
  @Override
  public void processFormRequest(OAPageContext pageContext, OAWebBean webBean) {
     super.processFormRequest(pageContext, webBean);
     if(null==pageContext||null==webBean){
      return;
    }
    xXGamInvSolicitudAMImpl InvSolicitudAMImpl = (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
     if("crea".equals(pageContext.getParameter(this.EVENT_PARAM))){
       if(null!=InvSolicitudAMImpl){
       
         
         InvSolicitudAMImpl.cleanV2InvSoliVOInfo();
         InvSolicitudAMImpl.cleanV2InvSoliDtlVOInfo();
         InvSolicitudAMImpl.getxXGamInvSoliVO1().clearCache();
         InvSolicitudAMImpl.getxXGamInvSoliVO1().initQuery("-1");
         InvSolicitudAMImpl.getxXGamInvSoliDtlVO1().clearCache();
         InvSolicitudAMImpl.getxXGamInvSoliDtlVO1().initQuery("-1");
         
         
         String strFillSolCrearCabeInfoSU = InvSolicitudAMImpl.fillSolCrearCabeInfoSU(pageContext,webBean); 
         if(null!=strFillSolCrearCabeInfoSU){
           if(strFillSolCrearCabeInfoSU.contains("EXCEPTION")){
             throw new OAException(strFillSolCrearCabeInfoSU,OAException.ERROR); 
           }
         }
         
         String strGetAvailableKitsInfoSU = InvSolicitudAMImpl.getAvailableKitsInfoSU();
         if(null!=strGetAvailableKitsInfoSU){
           if(strGetAvailableKitsInfoSU.contains("EXCEPTION")){
             throw new OAException(strGetAvailableKitsInfoSU,OAException.ERROR); 
           }
         }
         
         String strValGamHrUniformesInfo = InvSolicitudAMImpl.valGamHrUniformesInfo(strGetAvailableKitsInfoSU);
         if(null!=strValGamHrUniformesInfo){
           if(strValGamHrUniformesInfo.contains("EXCEPTION")){
             throw new OAException(strValGamHrUniformesInfo,OAException.ERROR);
           }
         }
         
         
       } 
     }  /** End  if("crea".equals(pageContext.getParameter(this.EVENT_PARAM))){ **/
     
      if("AvailableKitEvt".equals(pageContext.getParameter(this.EVENT_PARAM))){
      
        String strKitCode = null; 
        oracle.jbo.domain.Number numPersonID = null; 
        
        OAMessageChoiceBean AvailableKitsBean = (OAMessageChoiceBean)webBean.findChildRecursive("AvailableKits");
        if(null!=AvailableKitsBean){
          strKitCode = (String)AvailableKitsBean.getValue(pageContext);
        }
        
        if(null==strKitCode||"".equals(strKitCode)){
          InvSolicitudAMImpl.cleanV2InvSoliDtlVOInfo(); 
          return; 
        }
        
        OAFormValueBean personIdBean = (OAFormValueBean)webBean.findChildRecursive("PersonIdSL");
        if(null!=personIdBean){
           try
           {
             numPersonID = new oracle.jbo.domain.Number(personIdBean.getText(pageContext));
           } catch (SQLException sqle)
           {
            throw new OAException("EXCEPTION al obttener la variable personID: "+sqle.getErrorCode()+" , "+sqle.getMessage());
           }
        } /** END if(null!=personIdBean){ **/
        
         if(null!=strKitCode&&!"".equals(strKitCode)&&(null!=numPersonID)){
           
           /********************************************************************
            * Regla de Negocio: Omitir validacion que los periodos de los kits esten abiertos
            * Para un Super Usuario
           String strvalPeriodActiveKit =  InvSolicitudAMImpl.valPeriodActiveKit(strKitCode);
           if(null!=strvalPeriodActiveKit){
             if(strvalPeriodActiveKit.contains("EXCEPTION")){
               com.sun.java.util.collections.HashMap hashMap = new com.sun.java.util.collections.HashMap();
               hashMap.put("pExceptValPeriodKit",strvalPeriodActiveKit);
               pageContext.forwardImmediatelyToCurrentPage(hashMap                                      * com.sun.java.util.collections.HashMap parameters, *
                                                          ,false                                        * boolean retainAM, *
                                                          , OAWebBeanConstants.ADD_BREAD_CRUMB_NO       * String addBreadCrumb* 
                                                          );
             }
           } ** End if(null!=strvalPeriodActiveKit){ **
           **********************************************************************/
           
            String strKitID = InvSolicitudAMImpl.getKitIDInfo(strKitCode);
            if(null!=strKitID){
              if(strKitID.contains("EXCEPTION")){
                throw new OAException(strKitID,OAException.ERROR);
              } 
              
              /*****************************************************************
               * Regla de Negocio: Omitir Validacion de solo una UNI por periodo abierto
               * Solo en la pantalla de Super Usuario
              String strValSoloUnaUNI =   InvSolicitudAMImpl.valSoloUnaUNI(numPersonID,strKitCode);
              if(null!=strValSoloUnaUNI){
                if(strValSoloUnaUNI.contains("EXCEPTION")){
                  com.sun.java.util.collections.HashMap hashMap = new com.sun.java.util.collections.HashMap();
                  hashMap.put("pExceptValSoloUnaUNI",strValSoloUnaUNI);
                  pageContext.forwardImmediatelyToCurrentPage(hashMap                                      * com.sun.java.util.collections.HashMap parameters, *
                                                             ,false                                        * boolean retainAM, *
                                                             , OAWebBeanConstants.ADD_BREAD_CRUMB_NO       * String addBreadCrumb* 
                                                             );
                }
              }  ** End  if(null!=strValSoloUnaUNI){ **
              *****************************************************************/
              
              
               InvSolicitudAMImpl.cleanV2InvSoliDtlVOInfo(); 
               String strFillV2InvSoliDtlVOInfoSU = InvSolicitudAMImpl.fillV2InvSoliDtlVOInfoSU(pageContext,strKitCode,numPersonID,strKitID); 
               if(null!=strFillV2InvSoliDtlVOInfoSU){
                 if(strFillV2InvSoliDtlVOInfoSU.contains("EXCEPTION")){
                   com.sun.java.util.collections.HashMap hashMap = new com.sun.java.util.collections.HashMap();
                   hashMap.put("pExceptValHistoryInfo",strFillV2InvSoliDtlVOInfoSU);
                   pageContext.forwardImmediatelyToCurrentPage(hashMap /* com.sun.java.util.collections.HashMap parameters, */
                                                              ,false   /* boolean retainAM, */
                                                              , OAWebBeanConstants.ADD_BREAD_CRUMB_NO       /* String addBreadCrumb*/ 
                                                              );
                  /*  throw new OAException(strFillV2InvSoliDtlVOInfo,OAException.ERROR); */
                 }
                } /** END if(null!=strFillV2InvSoliDtlVOInfo){ **/
              
              
            } /** END if(null!=strKitID){ **/
            
         }/** End if(null!=strKitCode&&!"".equals(strKitCode)&&(null!=numPersonID)){ **/
        
      } /** End if("AvailableKitEvt".equals(pageContext.getParameter(this.EVENT_PARAM))){ **/
      
      
       if("V2GrabarEvt".equals(pageContext.getParameter(this.EVENT_PARAM))){
         OAMessageChoiceBean AvailableKitsBean = (OAMessageChoiceBean)webBean.findChildRecursive("AvailableKits");
         String strGrabKitCode = null; 
         if(null!=AvailableKitsBean){
           strGrabKitCode = (String)AvailableKitsBean.getValue(pageContext);
         }
         if(null!=strGrabKitCode&&!"".equals(strGrabKitCode)){
         
           com.sun.java.util.collections.List listValAllLinesV2InvSoliDtlVO = InvSolicitudAMImpl.valAllLinesV2InvSoliDtl();
           if(null!=listValAllLinesV2InvSoliDtlVO){
            if(listValAllLinesV2InvSoliDtlVO.size()>0){
             OAException.raiseBundledOAException(listValAllLinesV2InvSoliDtlVO);
             }  
           } /** END if(null!=listValAllLinesV2InvSoliDtlVO){ **/
           
           com.sun.java.util.collections.List listValAmountsV2InvSoliDtlSU = InvSolicitudAMImpl.valAmountsV2InvSoliDtlSU();
           if(null!=listValAmountsV2InvSoliDtlSU){
             if(listValAmountsV2InvSoliDtlSU.size()>0){
             OAException.raiseBundledOAException(listValAmountsV2InvSoliDtlSU);
             }
           } /** END  if(null!=listValAmountsV2InvSoliDtl){ **/
         
           pageContext.putSessionValue("sGrabKitCode",strGrabKitCode);
           
           OAProcessingPage processingPage = new OAProcessingPage("xxgam.oracle.apps.inv.moveorder.webui.XxGamProcessingSolCrearSUCO");
           String msg = "Procesando...";
           processingPage.setConciseMessage(msg);
           msg = "Esperar un momento...";
           processingPage.setDetailedMessage(msg);
           msg = "Dotacion de Prendas";
           processingPage.setProcessName(msg);
           processingPage.setRetainAMValue(true);
           pageContext.forwardToProcessingPage(processingPage);
         
         }else{
           throw new OAException("No se ha seleccionado un kit.",OAException.ERROR);
         }
         
       } /** End if("V2GrabarEvt".equals(pageContext.getParameter(this.EVENT_PARAM))){ **/
     
  }

  private void disableOldRegions(OAPageContext pageContext, OAWebBean webBean)
  {
   OAWebBean SingleColumnCreaSuperUserRG2Bean  = webBean.findChildRecursive("SingleColumnCreaSuperUserRG2"); 
   if(null!=SingleColumnCreaSuperUserRG2Bean){
     SingleColumnCreaSuperUserRG2Bean.setRendered(false);
   }
   
   OAWebBean SingleColumnCreaSuperUserRG3Bean = webBean.findChildRecursive("SingleColumnCreaSuperUserRG3");
   if(null!=SingleColumnCreaSuperUserRG3Bean){
     SingleColumnCreaSuperUserRG3Bean.setRendered(false); 
   }
   
  }

  /**
   * Metodo que deshabilita regiones
   * @param pageContext
   * @param webBean
   */
  private void disableRegions(OAPageContext pageContext, OAWebBean webBean)
  {
    OAWebBean region5SingleColumnCreaSuperUserRG0Bean = webBean.findChildRecursive("region5SingleColumnCreaSuperUserRG0");
    if(null!=region5SingleColumnCreaSuperUserRG0Bean){
      region5SingleColumnCreaSuperUserRG0Bean.setRendered(false);
    }
    OAWebBean SingleColumnCreaSuperUserRG1Bean = webBean.findChildRecursive("SingleColumnCreaSuperUserRG1");
    if(null!=SingleColumnCreaSuperUserRG1Bean){
      SingleColumnCreaSuperUserRG1Bean.setRendered(false);
    }
    OAWebBean SingleColumnCreaSuperUserRG2Bean = webBean.findChildRecursive("SingleColumnCreaSuperUserRG2");
    if(null!=SingleColumnCreaSuperUserRG2Bean){
      SingleColumnCreaSuperUserRG2Bean.setRendered(false);
    }
    OAWebBean SingleColumnCreaSuperUserRG3Bean = webBean.findChildRecursive("SingleColumnCreaSuperUserRG3");
    if(null!=SingleColumnCreaSuperUserRG3Bean){
      SingleColumnCreaSuperUserRG3Bean.setRendered(false);
    }
    OAWebBean SingColEmpInfoBean = webBean.findChildRecursive("SingColEmpInfo");
    if(null!=SingColEmpInfoBean){
      SingColEmpInfoBean.setRendered(false);
    }
    OAWebBean SingColDotacionInfoBean = webBean.findChildRecursive("SingColDotacionInfo");
    if(null!=SingColDotacionInfoBean){
      SingColDotacionInfoBean.setRendered(false);
    }
  }
  
}
