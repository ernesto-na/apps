/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.inv.moveorder.siz.webui;

import java.sql.Connection;

import java.util.ArrayList;

import java.util.HashSet;

import java.util.Iterator;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.layout.OADefaultSingleColumnBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAHeaderBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAMessageComponentLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.nav.OAButtonBean;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import xxgam.oracle.apps.inv.moveorder.siz.server.XxGamKitItemsInfoVOImpl;
import xxgam.oracle.apps.inv.moveorder.siz.server.XxGamKitItemsInfoVORowImpl;
import xxgam.oracle.apps.inv.moveorder.siz.server.XxGamPersonalUniformInfoVOImpl;
import xxgam.oracle.apps.inv.moveorder.siz.server.XxGamPersonalUniformInfoVORowImpl;
import xxgam.oracle.apps.inv.moveorder.siz.server.XxGamSizesUniformsAMImpl;

/**
 * Controller for ...
 */
public class XxGamCaptureSizesUniformsCO extends OAControllerImpl
{
  public static final String RCS_ID="$Header$";
  public static final boolean RCS_ID_RECORDED = VersionInfo.recordClassVersion(RCS_ID, "%packagename%");
  private static ArrayList<String> kitsAssig = null;
  
  private final String messageConfirmation = "Las tallas se guardaron satisfactoriamente.";
  private final String messageExceptionUNI = "Lo sentimos. Ya existe creada una UNI.";
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
    
    this.disableGenerarDotBtn(pageContext,webBean);
    
    OAHeaderBean HeaderEmployeeRNBean = (OAHeaderBean)webBean.findChildRecursive("HeaderEmployeeRN");
    if(null!=HeaderEmployeeRNBean){
     System.out.println("TTT");
      HeaderEmployeeRNBean.setWrapEnabled(true);
      HeaderEmployeeRNBean.setAttributeValue(OAWebBeanConstants.BACKGROUND_ATTR,OAWebBeanConstants.BACKGROUND_TRANSPARENT); 

    }
    OAMessageComponentLayoutBean MsgCompLayEmployeeRNBean = (OAMessageComponentLayoutBean)webBean.findChildRecursive("MsgCompLayEmployeeRN");
    
    if(null!=MsgCompLayEmployeeRNBean){
      MsgCompLayEmployeeRNBean.setAttributeValue(OAWebBeanConstants.BACKGROUND_ATTR,OAWebBeanConstants.BACKGROUND_TRANSPARENT); 
    }
    
    String methodName = "processRequest";
    String[] errorMessages = new String[3];
    errorMessages[0] = null;
    errorMessages[1] = null;
    errorMessages[2] = null;
    
    /*ArrayList<String> kitsAssig = null;*/
    
    System.out.println("DEBUG GNOSISHCM " + XxGamCaptureSizesUniformsCO.class.getName() + " " + methodName);
    
    XxGamSizesUniformsAMImpl SizesUniformsAM = (XxGamSizesUniformsAMImpl)pageContext.getApplicationModule(webBean);
    String strAvailableKits = null;
    String strKitAssig = null;
    
     
    if(null!=SizesUniformsAM){    
        if (!pageContext.isFormSubmission()) {
            String strGetVal = SizesUniformsAM.fillPersonSizeUniformsInfo(pageContext.getUserName(), pageContext.getUserId());
            if(null!=strGetVal){
               if(strGetVal.contains("EXCEPTION")){
                disablePageRegions(pageContext,webBean);
                throw new OAException(strGetVal,OAException.ERROR);
               }
            }
            
            XxGamPersonalUniformInfoVOImpl PersonalUniformInfoVOImpl = SizesUniformsAM.getXxGamPersonalUniformInfoVO1();
            XxGamPersonalUniformInfoVORowImpl PersonalUniformInfoVORowImpl = null;
            
            RowSetIterator PersonSizeUniformInfoIterator = PersonalUniformInfoVOImpl.createRowSetIterator(null);
            
            if(PersonSizeUniformInfoIterator.hasNext()){
                PersonalUniformInfoVORowImpl = (XxGamPersonalUniformInfoVORowImpl)PersonSizeUniformInfoIterator.next();
                /*Recuperacion de los kits disponibles en un ArrayList*/
                kitsAssig = SizesUniformsAM.fillAvailableKitsInfo(PersonalUniformInfoVORowImpl.getOrganization(),
                                                      PersonalUniformInfoVORowImpl.getPosition(),
                                                      PersonalUniformInfoVORowImpl.getSex(),
                                                      PersonalUniformInfoVORowImpl.getPayroll(),
                                                      PersonalUniformInfoVORowImpl.getZona(),
                                                      PersonalUniformInfoVORowImpl.getXxGamInvAssignExcept());
              System.out.println("[processRequest]Grupo de Pago de Empleado: "+PersonalUniformInfoVORowImpl.getPayroll());//Add by SEJR 30032017
              //Regla: Permitir solo captura de Observaciones a empleados con Grupo de Pago definido en Lookup
              OAMessageTextInputBean txtObservaciones = (OAMessageTextInputBean) webBean.findChildRecursive("Observaciones");//Add by SEJR 30032017
              if( SizesUniformsAM.getValidGrupoPago(PersonalUniformInfoVORowImpl.getPayroll()) == true) //Add by SEJR 30032017
              { 
                  System.out.println("[processRequest] Se habilita captura de Observaciones");
                  txtObservaciones.setReadOnly(false);                  
              }
              else
              {
                  System.out.println("[processRequest] Se DesHabilita captura de Observaciones");
                  txtObservaciones.setReadOnly(true);
              }
              Iterator<String> iterkitsAssig = kitsAssig.iterator();   
              while(iterkitsAssig.hasNext()){
               String getVal= iterkitsAssig.next(); 
               if(null!=getVal){
                 if(getVal.contains("EXCEPTION")){
                   OAWebBean EmployeeKitSizesRNBean = webBean.findChildRecursive("EmployeeKitSizesRN"); 
                   if(null!=EmployeeKitSizesRNBean){
                     EmployeeKitSizesRNBean.setRendered(false);
                   }
                   
                   OAWebBean PageButtonBarRNBean = webBean.findChildRecursive("PageButtonBarRN"); 
                   if(null!=PageButtonBarRNBean){
                     PageButtonBarRNBean.setRendered(false);
                   }
                   
                   throw new OAException(getVal,OAException.ERROR);
                 }
               }
              } /** End  while(iterkitsAssig.hasNext()){ **/
             /****************************** 
              if(null!=kitsAssig){
                   if(kitsAssig.contains("EXCEPTION")){
                     throw new OAException("ERROR",OAException.ERROR);
                   }
              }
              *****************************/
              
            } /** END messageConfirmation **/  
          PersonSizeUniformInfoIterator.closeRowSetIterator();
            
          System.out.println("DEBUG GNOSISHCM "+"Post closeRowSetIterator() encbezado");
          
          HashSet<String> ElementsHashSet = new HashSet<String>();
          
          /*Recuperacion de Prendas, si existe alguna repetida no accede*/
          System.out.println("[processRequest] Inicia Recuperacion de Prendas para DataGrid: fillPersonSizeKitAssig");//Add by SEJR 30032017
          for(String kitsAssigTemp: kitsAssig){
              strKitAssig = SizesUniformsAM.fillPersonSizeKitAssig(ElementsHashSet, kitsAssigTemp);
          }
          System.out.println("[processRequest] Finaliza Recuperacion de Prendas para DataGrid: fillPersonSizeKitAssig");//Add by SEJR 30032017
          System.out.println("DEBUG GNOSISHCM HashSet Print");
          System.out.println(ElementsHashSet);
          System.out.println("strAvailableKits:"+strAvailableKits);
          if(null!=strKitAssig){
                 if(strKitAssig.contains("EXCEPTION")){
                          System.out.println("DEBUG GNOSISHCM Exception in VO Assign-->"+strKitAssig);
                            throw new OAException(strKitAssig,OAException.ERROR);
                  }
          } /** END  if(null!=strKitAssig){ **/
          PersonSizeUniformInfoIterator.closeRowSetIterator(); /** Close rowSetIterator **/
            
        } else {
            System.out.println("DEBUG GNOSISHCM "+"pageContext.isFormSubmission() ERROR");
        } /** END if(null!=SizesUniformsAM){ **/
        
        
        
         /**Modificado por NRC 23-feb-2017*/
          String strpSavePrendasInfo = pageContext.getParameter("pSavePrendasInfo");
          System.out.println("strpSavePrendasInfo:"+strpSavePrendasInfo);
          if(null!=strpSavePrendasInfo&&!"".equals(strpSavePrendasInfo)){
                System.out.println("Into strpSavePrendasInfo not null");
                
              /************************************************************************
              * Regla de negocio: Si un empleado no tiene una UNI creada puede seguir el flujo 
              * de captura de tallas y creacion de UNIS sin validar la apertura de periodos
              *****************************************************************************/
              String strNumUNIS = SizesUniformsAM.getNumberUNIS(); 
              System.out.println("strNumUNIS:"+strNumUNIS);
              if(null!=strNumUNIS){
                if(strNumUNIS.contains("EXCEPTION")){
                  throw new OAException(strNumUNIS,OAException.ERROR); 
                }else if(!"0".equals(strNumUNIS)){
                  /*******************************************************
                  *** Regla de negocio:validacion para periodo abierto ***
                  *******************************************************/
                  OADBTransaction oaDBTransaction =  SizesUniformsAM.getOADBTransaction();
                  Connection connection = oaDBTransaction.getJdbcConnection();
                  for(String kitsAssigTemp: kitsAssig){
                     String flagPeriod = SizesUniformsAM.valAperturaPeriodsKitsV2(kitsAssigTemp,connection);
                     if(!flagPeriod.contains("EXCEPTION")){
                              this.enableGenerarDotBtn(pageContext,webBean);
                              pageContext.removeParameter("pSavePrendasInfo");
                              pageContext.putParameter("pSavePrendasInfo","");
                         break;
                     }
                  } /** End for(String kitsAssigTemp: kitsAssig){ **/
                }else if("0".equals(strNumUNIS)){
                  this.enableGenerarDotBtn(pageContext,webBean);
                  pageContext.removeParameter("pSavePrendasInfo");
                  pageContext.putParameter("pSavePrendasInfo","");
                }
                
              } /** END if(null!=strNumUNIS){ **/
                
                
                
                throw new OAException(messageConfirmation,OAException.CONFIRMATION);
            }
         /**Fin de modificacion*/
        
    }
    
    
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
      String methodName = "processFormRequest";
      
      System.out.println("DEBUG GNOSISHCM " + methodName);
     /** System.out.println(this.EVENT_PARAM);                **/
     /** System.out.println(this.EVENT_SOURCE_ROW_REFERENCE); **/
     /** System.out.println(this.LOV_PREPARE);                **/
     /** System.out.println(this.LOV_UPDATE);                 **/
     /** System.out.println(this.LOV_VALIDATE);               **/
      
      String strSuccessTransaction = null;
      
      String strEvent = pageContext.getParameter(this.EVENT_PARAM);
      String strEvtSrcRowRef = pageContext.getParameter(this.EVENT_SOURCE_ROW_REFERENCE); 
      String strLovInputSourceId = pageContext.getLovInputSourceId();
    
      System.out.println("strEvent:"+strEvent);
      
      XxGamSizesUniformsAMImpl SizesUniformsAM = (XxGamSizesUniformsAMImpl)pageContext.getApplicationModule(webBean);
      
      if(null!=SizesUniformsAM){
      
        if(null!=strEvent){
            if(this.LOV_PREPARE.equals(strEvent)){
              System.out.println("LOV_PREPARE");
              if("SizesLOV".equals(strLovInputSourceId)){
                  Row RowSizesInfo = SizesUniformsAM.findRowByRef(strEvtSrcRowRef);
                  
                  pageContext.putSessionValue("sDotaID",(String)RowSizesInfo.getAttribute("DotaId"));
               }
            }else if(this.LOV_UPDATE.equals(strEvent)){
              System.out.println("LOV_UPDATE");
                if("SizesLOV".equals(strLovInputSourceId)){
                     /** Row RowSizesInfo = SizesUniformsAM.findRowByRef(strEvtSrcRowRef); **/                                      
                    /**  pageContext.putSessionValue("sDotaID",(String)RowSizesInfo.getAttribute("DotaId")); **/                              
                 }
            }else if(this.LOV_VALIDATE.equals(strEvent)){
              System.out.println("LOV_VALIDATE");
            }
            
            if(this.GOTO_EVENT.equals(strEvent)){
              /****
              com.sun.java.util.collections.List listValidValuesRowByRow =  SizesUniformsAM.validValuesRowByRow();
              if(listValidValuesRowByRow.size()>0){
               OAException.raiseBundledOAException(listValidValuesRowByRow);
              }
              No deja avanzar cuando en registro mayor a 10 marca una excepcion
              ******/ 
            } /** End if(this.GOTO_EVENT.equals(strEvent)){ **/
            
            if("updateSizes".equals(strEvent)){
              
              com.sun.java.util.collections.List listValidValuesRowByRow =  SizesUniformsAM.validValuesRowByRow();
              if(listValidValuesRowByRow.size()>0){
               OAException.raiseBundledOAException(listValidValuesRowByRow);
              }
              
             com.sun.java.util.collections.List listValAllTallasFilled = SizesUniformsAM.valAllTallasFilled(); 
             if(listValAllTallasFilled.size()>0){
              OAException.raiseBundledOAException(listValAllTallasFilled);
             }
             
                 
                int counterRegisters = 0;
                String[] kitValues = new String[10]; //Update by SEJR 30032017
                System.out.println("DEBUG GNOSISHCM Into Update/Insert Sizes Event");
                XxGamKitItemsInfoVOImpl KitItemsInfoVOImpl = SizesUniformsAM.getXxGamKitItemsInfoVO1();
                if(null!=KitItemsInfoVOImpl){
                    /*Recuperamos todo lo depositado en VO*/
                    RowSetIterator KitsSizesInfo = KitItemsInfoVOImpl.createRowSetIterator(null);
                    if(KitsSizesInfo.hasNext()){
                        System.out.println("DEBUG GNOSISHCM Into RowSetIterator");
                        /*Recuperamos registro por registro para Hacer Update/Insert*/
                        Row row = null;
                        while((row=KitsSizesInfo.next())!=null){
                            System.out.println("RowSet Sucess!");
                            System.out.println("DotaId: "+row.getAttribute("DotaId"));
                            System.out.println("SizeLOV: "+row.getAttribute("SizeLOV"));
                            System.out.println("TallaId: "+row.getAttribute("TallaId"));
                            System.out.println("[get from table ]Observaciones:"+row.getAttribute("Observaciones")); //Add by SEJR 30032017
                            kitValues[0]=null;
                            kitValues[1]=(String)row.getAttribute("DotaId");
                            kitValues[2]=(String)row.getAttribute("TallaId");
                            kitValues[3]=(String)row.getAttribute("SizeLOV");
                            kitValues[4]=(String)row.getAttribute("InventoryId");
                            kitValues[5]=(String)row.getAttribute("NPCode");
                            kitValues[6]=(String)row.getAttribute("Nomenclature");
                            kitValues[7]=(String)row.getAttribute("PlantaQTY");
                            kitValues[8]=(String)row.getAttribute("EventQTY");
                            kitValues[9]=(String)row.getAttribute("Observaciones"); //Add by SEJR 30032017
                            /**strSuccessTransaction = SizesUniformsAM.setHistorySizes(XxGamCaptureSizesUniformsCO.kitsAssig, kitValues,*/
                                                                                       /**  pageContext); */
                            
                            strSuccessTransaction = SizesUniformsAM.setSizesUpdateInsert(XxGamCaptureSizesUniformsCO.kitsAssig, kitValues,
                                                                                         pageContext);
                            if(strSuccessTransaction.contains("EXCEPTION")){
                                System.out.println("DEBUG GNOSISHCM "+strSuccessTransaction);
                            }
                            
                            System.out.println("strSuccessTransaction   "+strSuccessTransaction);
                            counterRegisters = counterRegisters + 1;
                            System.out.println("counterRegisters:  "+counterRegisters);
                            /**break;*/
                        } /** END While **/
                        if(!strSuccessTransaction.contains("EXCEPTION")){
                           
                          com.sun.java.util.collections.HashMap hashMap = new com.sun.java.util.collections.HashMap();
                          hashMap.put("pSavePrendasInfo",strSuccessTransaction);
                          pageContext.forwardImmediatelyToCurrentPage(hashMap /* com.sun.java.util.collections.HashMap parameters, */
                                                                     ,false   /* boolean retainAM, */
                                                                     , OAWebBeanConstants.ADD_BREAD_CRUMB_NO       /* String addBreadCrumb*/ 
                                                                     );
                            
                          /**  throw new OAException(messageConfirmation,OAException.CONFIRMATION); **/
                            
                        }
                    }
                  KitsSizesInfo.closeRowSetIterator();
                }
            }
          
          if("GenerarDotEvt".equals(strEvent)){
          
            com.sun.java.util.collections.List listValidValuesRowByRow =  SizesUniformsAM.validValuesRowByRow();
            if(listValidValuesRowByRow.size()>0){
             OAException.raiseBundledOAException(listValidValuesRowByRow);
            }
            
            com.sun.java.util.collections.List listValAllTallasFilled = SizesUniformsAM.valAllTallasFilled();
            if(listValAllTallasFilled.size()>0){
            OAException.raiseBundledOAException(listValAllTallasFilled);
            }
            
            System.out.println("Into strEvent");
            

            /**Inicio de modificacion NRC 23-feb-2017*/  

             OADBTransaction oaDBTransaction =  SizesUniformsAM.getOADBTransaction();
             Connection connection = oaDBTransaction.getJdbcConnection();
             XxGamPersonalUniformInfoVOImpl PersonalUniformInfoVOImpl = SizesUniformsAM.getXxGamPersonalUniformInfoVO1();
             XxGamPersonalUniformInfoVORowImpl PersonalUniformInfoVORowImpl = (XxGamPersonalUniformInfoVORowImpl)PersonalUniformInfoVOImpl.first();

             String flagUni = "EXCEPTION";
                        
             for(String kitsAssigTemp: kitsAssig){
                            
                flagUni = SizesUniformsAM.validateExistUnis(PersonalUniformInfoVORowImpl.getPersonID()
                                                           ,PersonalUniformInfoVORowImpl.getCategoriaGr()
                                                           ,kitsAssigTemp
                                                           ,connection);
                              
             }
                        
             if(!flagUni.contains("EXCEPTION")){ 
                            String messageConfirmation = pageContext.getMessage("XBOL",
                                                                      "XXGAM_INV_SIZ_MESSAGE01",
                                                                      null);
                            OAException messageConfirmExc = new OAException(messageConfirmation,OAException.CONFIRMATION);
                            OADialogPage confirmationPage = new OADialogPage(  OAException.CONFIRMATION
                                                                             , messageConfirmExc
                                                                             , null
                                                                             , "OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudCrearPG"
                                                                             , "OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/siz/webui/XxGamCaptureSizesUniformsPG");
                            confirmationPage.setOkButtonItemName("CreateOkButton");
                            confirmationPage.setNoButtonItemName("CreateNoButton");
                            confirmationPage.setOkButtonLabel("Acepto");
                            confirmationPage.setNoButtonLabel("Regresar");
                            confirmationPage.setPostToCallingPage(true);
                            pageContext.redirectToDialogPage(confirmationPage);
            }else{
                throw new OAException(messageExceptionUNI,OAException.WARNING);
             }/**end if(!flagUni.contains("EXCEPTION"))*/         

          } /** end if("GenerarDotEvt".equals(strEvent)){ **/
            
        } /** end  if(null!=strEvent){ **/
        
      }
  }

  /**
   * Metodo para deshabilitar regiones de la pantalla en caso de que 
   * ocurra una excepcion.
   * @param pageContext
   * @param webBean
   */
  private void disablePageRegions(OAPageContext pageContext, OAWebBean webBean)
  {
    OAWebBean EmployeeInfoHeaderBean = webBean.findChildRecursive("EmployeeInfoHeader"); 
    if(null!=EmployeeInfoHeaderBean){
      EmployeeInfoHeaderBean.setRendered(false);
    }
    OAWebBean EmployeeInfoBean = webBean.findChildRecursive("EmployeeInfo"); 
    if(null!=EmployeeInfoBean){
      EmployeeInfoBean.setRendered(false);
    }
    
    OAWebBean EmployeeKitSizesRNBean = webBean.findChildRecursive("EmployeeKitSizesRN"); 
    if(null!=EmployeeKitSizesRNBean){
      EmployeeKitSizesRNBean.setRendered(false);
    }
    
    OAWebBean PageButtonBarRNBean = webBean.findChildRecursive("PageButtonBarRN"); 
    if(null!=PageButtonBarRNBean){
      PageButtonBarRNBean.setRendered(false);
    }
    
  }


  /**
   * Metodo que deshabilita el boton GenerarDotBtn
   * @param pageContext
   * @param webBean
   */
  private void disableGenerarDotBtn(OAPageContext pageContext, 
                                    OAWebBean webBean)
  {
   OAButtonBean GenerarDotBtnBean = (OAButtonBean)webBean.findChildRecursive("GenerarDotBtn");
   if(null!=GenerarDotBtnBean){
     GenerarDotBtnBean.setDisabled(true);
   }
  }

  /**
   * Metodo que habilita el boton GenerarDotBtn
   * @param pageContext
   * @param webBean
   */
  private void enableGenerarDotBtn(OAPageContext pageContext, 
                                   OAWebBean webBean)
  {
    OAButtonBean GenerarDotBtnBean = (OAButtonBean)webBean.findChildRecursive("GenerarDotBtn");
    if(null!=GenerarDotBtnBean){
      GenerarDotBtnBean.setDisabled(false);
    }
  }
  
}
