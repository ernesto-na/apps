/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.xbol.maf.webui;

import com.sun.java.util.collections.HashMap;

import java.sql.SQLException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAFwkConstants;
import oracle.apps.fnd.framework.OARow;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.TransactionUnitHelper;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;


import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;

import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaCurrencyLovVORowImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaTemplatePaymentFlexLovVOImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaTemplatePaymentFlexLovVORowImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaTypePaymentLovVORowImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamModAntLovAMImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaGeneralReqVOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaGeneralReqVORowImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaPaymentReqVOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaPaymentReqVORowImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamModAntAMImpl;
import xxgam.oracle.apps.xbol.maf.utils.XxGamAOLMessages;
import xxgam.oracle.apps.xbol.maf.utils.XxGamConstantsUtil;
import xxgam.oracle.apps.xbol.maf.utils.XxGamMAnticiposUtil;
import xxgam.oracle.apps.xbol.maf.utils.XxGamMAnticiposUtil2;

import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Hashtable;

import oracle.apps.fnd.common.MessageToken;

import xxgam.oracle.apps.xbol.maf.utils.CcManagementValidation;


/**
 * Clase controlador de la pagina de Informacion General, de solicitud de anticipo.
 * Pagina definida por el XML XxGamPaymentReqInfoGeneralPG
 * 
 * @author Aldo López de Nava.
 */
public class XxGamPaymentReqInfoGeneralCO extends OAControllerImpl {
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

      if(!pageContext.isFormSubmission()){
           XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"franchiseType GralPG: "+pageContext.getParameter("pfranchiseType"),pageContext,webBean);
           XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"Request GralPG: "+pageContext.getParameter("pRequest"),pageContext,webBean);
           
           if(null!=pageContext.getParameter("pfranchiseType")){
             pageContext.putSessionValue("sfranchiseType",pageContext.getParameter("pfranchiseType"));
           }
           
           if(null!=pageContext.getParameter("pRequest")){
             pageContext.putSessionValue("sRequest",pageContext.getParameter("pRequest"));
           }
         } else{
           XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"franchiseType GralPG: "+pageContext.getParameter("pfranchiseType"),pageContext,webBean);
           XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"Request GralPG: "+pageContext.getParameter("pRequest"),pageContext,webBean);
           XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"franchiseType GralPG: "+pageContext.getSessionValue("sfranchiseType"),pageContext,webBean);
           XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"Request GralPG: "+pageContext.getSessionValue("sRequest"),pageContext,webBean);
          
           if(null!=pageContext.getSessionValue("sfranchiseType")&&!"".equals(pageContext.getSessionValue("sfranchiseType"))){
              pageContext.putParameter("pfranchiseType",pageContext.getSessionValue("sfranchiseType"));
            }
            if(null!=pageContext.getSessionValue("sRequest")&&!"".equals(pageContext.getSessionValue("sRequest"))){
              pageContext.putParameter("pRequest",pageContext.getSessionValue("sRequest"));
            }
         }
        
        String msgError = null;
        String msgErroraux = null;
        String urlBase = XxGamConstantsUtil.URL_PAGE_OAF;
        HashMap hmap = new HashMap();
        String statusPage = null; 

        if (pageContext != null && webBean != null) {
            
            //Lineas Agregadas para obtener la Unidad Operativa
            //Para posteriormente Utilizarla
            String getOrgName = null; 
           
          getOrgName = (String)pageContext.getTransactionValue("orgNameEmp");
          
          System.out.println("getOrgName-->"+getOrgName); 
          pageContext.writeDiagnostics(this,"Capa CO processRequest getOrgName-->"+getOrgName,OAFwkConstants.STATEMENT);
           
          if(getOrgName==null){
                      getOrgName = XxGamMAnticiposUtil2.getOrgNameByUserId(pageContext.getUserId(),pageContext, pageContext.getApplicationModule(webBean).getOADBTransaction(),webBean);
                      pageContext.putTransactionValue("orgNameEmp"
                                                      ,getOrgName); 
                     }
           else if (getOrgName!=null&&!(getOrgName.equals(""))){
             pageContext.putTransactionValue("orgNameEmp"
                                            ,getOrgName);    
             } 
          
           
           TransactionUnitHelper.startTransactionUnit(pageContext, 
                                                       "CreateAndEditRequest");
                                              
            pageContext.getApplicationModule(webBean).getOADBTransaction().putTransientValue("IsValidateEntityFlight", "true");
            pageContext.getApplicationModule(webBean).getOADBTransaction().putTransientValue("IsValidateEntityDetail", "true");

            //Obtiene los componentes de pantalla para la responsabilidad de empleado y aprobador
            OAWebBean trainBean = webBean.findChildRecursive("TrainNavRN");
            OAWebBean navBarBean = webBean.findChildRecursive("ButtonNavRN");
            OAWebBean saveButton = webBean.findChildRecursive("SaveButton");
            OAWebBean cancelButton = 
                webBean.findChildRecursive("CancelButton");
            OAWebBean nextFbutton = webBean.findChildRecursive("NextFButton");
            OAWebBean formGeneralReq = 
                webBean.findChildRecursive("FormEditVO");
            OAWebBean formGeneralReqReadOnly = 
                webBean.findChildRecursive("FormReadOnly");
            /*OAWebBean currencyName = 
                webBean.findChildRecursive("CurrencyNameR");*/
             OAWebBean currencyName= webBean.findChildRecursive("CurrencyNameR");
            
             
            //webBean.findChildRecursive("CurrencyNameR");
                System.out.println("--divisa--> "+currencyName.toString());
            OAWebBean templateLov = 
                webBean.findChildRecursive("TemplatePaymentLov");
            OAWebBean templateFlexLov = 
                webBean.findChildRecursive("TemplatePaymentFlexLov");
            OAWebBean currencyNameRO = 
                webBean.findChildRecursive("CurrencyDescRO");
            System.out.println("--divisa--> "+currencyNameRO.toString());
            OAWebBean templateDescRO = 
                webBean.findChildRecursive("TemplateDescRO");
            OAWebBean returnButton = 
                webBean.findChildRecursive("ReturnButton");
            OAWebBean Reason = webBean.findChildRecursive("Reason");
            OAWebBean Comments = webBean.findChildRecursive("Comments");

            statusPage = pageContext.getParameter(XxGamConstantsUtil.STATUS);
            if (statusPage != null) {
                pageContext.putTransactionValue(XxGamConstantsUtil.STATUS, 
                                                statusPage);
            }

            String step1Flag = //--> la primera vez que lanza el error este dato viene null posteriormente biene como create
                (String)pageContext.getTransactionValue(XxGamConstantsUtil.VIEW_STEP_ONE);

            //Verifica la responsabilidad de empleado TODO:01
            if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                            webBean, 
                                                            new Number(pageContext.getResponsibilityId()), 
                                                            XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)) {

                //Deshabilita tabla solo lectura
                if (formGeneralReqReadOnly != null) {
                    formGeneralReqReadOnly.setRendered(false);
                }
                //Deshabilita componentes de franquicia
                if (nextFbutton != null) {
                    nextFbutton.setRendered(false);
                }
                if (currencyName != null) {
                    currencyName.setRendered(true);
                }
                if (returnButton != null) {
                    returnButton.setRendered(false);
                }
                if (Reason != null) {
                    Reason.setRendered(false);
                }
                if (Comments != null) {
                    Comments.setRendered(false);
                }
                
                //Verifica estado de la pagina para creacion de una nueva solicitud de anticipo                 
                if (XxGamConstantsUtil.CREATE.equals(pageContext.getTransactionValue(XxGamConstantsUtil.STATUS))) {
                    //Verifica si anteriormente se accedio a la primer pagina para no volver a configurar valores iniciales
                     
                    if (step1Flag == null) {
                        boolean isErrorInit = 
                            XxGamMAnticiposUtil.initNewPaymentRequest(pageContext, 
                                                                      webBean);
                        String msgErrorApprover = null;
                        if (isErrorInit) {
                            System.out.println("Comentado por GnosisHCM 24/03/2015"
                                               +"\n isErrorInit = XxGamMAnticiposUtil.setOriginApproverInGeneral(pageContext, webBean); " ); 
           
                          isErrorInit = XxGamMAnticiposUtil2.setOriginApproverInGeneral(pageContext,webBean); 
                                                    
                            if (!isErrorInit) {
                                msgErrorApprover = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_APPROVER_NF_ERROR, null);                                
                            }
                        }
                        if (!isErrorInit) {
                            //Deshace cambios en la BD
                            XxGamMAnticiposUtil.setRollback(pageContext, 
                                                            webBean);
                            //Muestra mensaje de error al inicializar valores
                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_REQ_INIT_ERROR, null);
                            msgErroraux =  pageContext.getParameter("ErrorEspecifico");
                            if (msgErrorApprover != null) {
                                msgError += ". " + msgErrorApprover;
                                msgErroraux =  pageContext.getParameter("ErrorEspecificoAp");
                                /* Add by DIHU (CCT) 17-Oct-2014
                                 * Se agrego un codigo al final de la cadena con la finalidad de identificar el error de forma mas especifica. 
                                 */
                                if (pageContext.getParameter("ErrorEspecificoAp") != null && !"".equals(pageContext.getParameter("ErrorEspecificoAp").toString().trim()))
                                    msgError += ". " + msgErroraux;
                            }
                            /* Add by DIHU (CCT) 17-Oct-2014
                             * Se agrego un codigo al final de la cadena con la finalidad de identificar el error de forma mas especifica. 
                             */
                            if (pageContext.getParameter("ErrorEspecifico") != null && !"".equals(pageContext.getParameter("ErrorEspecifico").toString().trim()))
                                msgError += ". " + msgErroraux;
                                                            
                            hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                     msgError);
                             System.out.println(" Descripcion del error en la pantalla de informacion general "+msgError); 
                            //Redirige la navegacion a la pagina inicial del empleado
                            XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                         webBean, 
                                                                         hmap, 
                                                                         urlBase + 
                                                                         "XxGamPaymentInitAdvancePG");
                        }
                        
                        pageContext.putTransactionValue(XxGamConstantsUtil.VIEW_STEP_ONE, 
                                                        "true");
                    }
                    //Habilita train y botones de navegacion
                    if (trainBean != null) {
                        trainBean.setRendered(true);
                    }
                    if (navBarBean != null) {
                        navBarBean.setRendered(true);
                    }
                    //Habilita el boton de guardar y cancelar
                    if (saveButton != null) {
                        saveButton.setRendered(true);
                    }
                    if (cancelButton != null) {
                        cancelButton.setRendered(true);
                    }
                    //Habilita tabla editable
                    if (formGeneralReq != null) {
                        formGeneralReq.setRendered(true);
                    }
                } else {
                    //Verifica el estado de la pagina para actualizar la solicitud de anticipo
                    if (XxGamConstantsUtil.UPDATE.equals(pageContext.getTransactionValue(XxGamConstantsUtil.STATUS))) {
                        if (step1Flag == null) {
                          
                          /****** Agregado por GnosisHCM ****/
                          String StrRequestIdUpdate = null; 
                          StrRequestIdUpdate = pageContext.getParameter("requestId"); 
                          if (null!=StrRequestIdUpdate&&""!=StrRequestIdUpdate){
                            pageContext.putTransactionValue("StrRequestIdUpdate",StrRequestIdUpdate); 
                          }
                          /******************/
                          
                            //Valida que el anticipo es apto para edicion
                            boolean isEditable = false;
                            isEditable = 
                                    XxGamMAnticiposUtil.isPaymentReqEditable(pageContext, 
                                                                             webBean);
                            if (isEditable) {

                                //Ejecuta la configuracion de valores descriptivos
                                 System.out.println("Se comenta para un mejor trato a la informacion " +"\n    boolean isInitSuccess  = XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, webBean);");      
                                 boolean isInitSuccess =  XxGamMAnticiposUtil2.setPaymentReqDescriptionsReadOnly(pageContext
                                                                                                    ,webBean);
                               
                               
                                XxGamModAntAMImpl ModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
                                XxGamMaGeneralReqVOImpl generalImpl = ModAntAMImpl.getXxGamMaGeneralReqVO1();
                                XxGamMaGeneralReqVORowImpl generalRow = null;
                                generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();

                                if (isInitSuccess) {
                                  XxGamMaPaymentReqVOImpl voPaymentRequest = null;
                                  voPaymentRequest = ModAntAMImpl.getXxGamMaPaymentReqVO2();
                                  XxGamModAntLovAMImpl amLov = null;
                                  amLov = (XxGamModAntLovAMImpl)ModAntAMImpl.getXxGamModAntLovAM1();
                                  if ((voPaymentRequest != null) && (amLov != null))
                                  {
                                    RowSetIterator rowSetIter = voPaymentRequest.getRowSetIterator();
                                    if (rowSetIter != null) {
                                      rowSetIter.reset();
                                      while (rowSetIter.hasNext()) {
                                        Row rowDetail = rowSetIter.next();
                                        XxGamMaPaymentReqVORowImpl rowPaymentRDetail = (XxGamMaPaymentReqVORowImpl)rowDetail;
                                        if (rowPaymentRDetail != null)
                                        {
                                          String typePaymentDesc = null;
                                          XxGamMaTypePaymentLovVORowImpl typePaymentRow = amLov.getTypePaymentById(rowPaymentRDetail.getTypePayment(), generalRow.getTemplatePayment());
                                          if ((typePaymentRow != null) && 
                                            (typePaymentRow.getTypePaymentDesc() != null))
                                          {
                                            typePaymentDesc = typePaymentRow.getTypePaymentDesc();
                                          }
                                         // rowPaymentRDetail.setTypePymentDesc(typePaymentDesc);
                                          String currencyDetail = null;
                                          XxGamMaCurrencyLovVORowImpl currencyDetailRow = amLov.getCurrencyByCode(rowPaymentRDetail.getCurrencyCode());
                                          if ((currencyDetailRow != null) && 
                                            (currencyDetailRow.getCurrencyName() != null))
                                          {
                                            currencyDetail = currencyDetailRow.getCurrencyName();
                                          }
                                          rowPaymentRDetail.setCurrencyDesc(currencyDetail);
                                          rowPaymentRDetail.setIsPaymentValid(Boolean.valueOf(false));
                                          rowPaymentRDetail.setIsPaymentNotValid(Boolean.valueOf(false));
                                        }
                                      }
                                    }
                                  }
                                
                                 if(null!=getOrgName){
                                   generalRow.setOperatingUnit(getOrgName);
                                 }
                                
                               } // END if (isInitSuccess) {
                              System.out.println("Finaliza lo anterior se agrega para que en la pantalla de detalles, se tome informacion que anteriormente se regsitro Modo Update");
                              isInitSuccess = XxGamMAnticiposUtil2.setOriginApproverInGeneralUpdate(pageContext, webBean);
                              
                                if (isInitSuccess) {
                                    //Habilita train y botones de navegacion
                                    if (trainBean != null) {
                                        trainBean.setRendered(true);
                                    }
                                    if (navBarBean != null) {
                                        navBarBean.setRendered(true);
                                    }
                                    //Habilita el boton de guardar y cancelar
                                    if (saveButton != null) {
                                        saveButton.setRendered(true);
                                    }
                                    if (cancelButton != null) {
                                        cancelButton.setRendered(true);
                                    }
                                    //Habilita tabla editable
                                    if (formGeneralReq != null) {
                                        formGeneralReq.setRendered(true);
                                    }
                                } else {
                                    //Redirige la navegacion a la pagina de inicio del empleado y muestra mensaje de error por registro de solicitud no esta disponible para su edicion
                                    //Deshace cambios en la BD
                                    XxGamMAnticiposUtil.setRollback(pageContext, 
                                                                    webBean);
                                    //Muestra mensaje de error al inicializar valores
                                    msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_REQ_INIT_ERROR, null);
                                    hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                             msgError);
                                    //Redirige la navegacion a la pagina inicial del empleado
                                    XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                                 webBean, 
                                                                                 hmap, 
                                                                                 urlBase + 
                                                                                 "XxGamPaymentInitAdvancePG");
                                }
                            } else {
                                //Redirige la navegacion a la pagina de inicio del empleado y muestra mensaje de error por registro de solicitud no esta disponible para su edicion
                                //Deshace cambios en la BD
                                XxGamMAnticiposUtil.setRollback(pageContext, 
                                                                webBean);
                                //Muestra mensaje de error al inicializar valores
                                msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_REQ_NOT_EDIT_WARN, null);
                                hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                         msgError);
                                //Redirige la navegacion a la pagina inicial del empleado
                                XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                             webBean, 
                                                                             hmap, 
                                                                             urlBase + 
                                                                             "XxGamPaymentInitAdvancePG");
                            }
                        }
                    } else {
                        //Redirige la navegacion a la pagina inicial del empleado
                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_ACCESS_DEN_NA_ERROR, null);
                        hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                 msgError);
                        //Redirige a la pagina correspondiente a la responsabilidad 
                        urlBase += "XxGamMaBlankPagePG";
                        XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                     webBean, 
                                                                     hmap, 
                                                                     urlBase);
                    }
                }
            } else {
                //Verifica la responsabilidad de franquicias
                if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                webBean, 
                                                                new Number(pageContext.getResponsibilityId()), 
                                                                XxGamConstantsUtil.RESPONSABILITY_FRANCHISE)
                   ||XxGamConstantsUtil.RESPONSABILITY_FRANCHISE.equals(pageContext.getParameter("pRequest"))) {
                    //Deshabilita los componentes de empleado
                    if (trainBean != null) {
                        trainBean.setRendered(false);
                    }
                    if (navBarBean != null) {
                        navBarBean.setRendered(false);
                    }

                    if (currencyName != null) {
                        currencyName.setRendered(false);
                    }
                    if (templateLov != null) {
                        templateLov.setRendered(false);
                    }
                    if (templateFlexLov != null) {
                        templateFlexLov.setRendered(false);
                    }

                    if (currencyNameRO != null) {
                        currencyNameRO.setRendered(false);
                    }
                    if (templateDescRO != null) {
                        templateDescRO.setRendered(false);
                    }

                    //Verifica estado de la pagina para creacion de una nueva solicitud de anticipo
                    if (XxGamConstantsUtil.CREATE.equals(pageContext.getTransactionValue(XxGamConstantsUtil.STATUS))) {
                        //Deshabilita tabla solo lectura
                        if (formGeneralReqReadOnly != null) {
                            formGeneralReqReadOnly.setRendered(false);
                        }
                        //Habilita tabla editable
                        if (formGeneralReq != null) {
                            formGeneralReq.setRendered(true);
                        }
                        if (returnButton != null) {
                            returnButton.setRendered(false);
                        }

                        //Verifica si anteriormente se accedio a la primer pagina para no volver a configurar valores iniciales
                        if (step1Flag == null) {

                            boolean isErrorInit = 
                                XxGamMAnticiposUtil.initNewPaymentRequest(pageContext, 
                                                                          webBean);
                            String msgErrorApprover = null;
                            if (isErrorInit) {
                              System.out.println("Comentado por GnosisHCM 04/05/2015 para la responsabilidad de Franquisias"
                                                 +"\n isErrorInit = XxGamMAnticiposUtil.setOriginApproverInGeneral(pageContext, webBean); " ); 
                              
                              isErrorInit = XxGamMAnticiposUtil2.setOriginApproverInGeneral(pageContext,webBean);
                              
                                if (!isErrorInit) {
                                    msgErrorApprover = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_APPROVER_NF_ERROR, null);
                                }
                            }

                            if (!isErrorInit) {
                                //Deshace cambios en la BD
                                XxGamMAnticiposUtil.setRollback(pageContext, 
                                                                webBean);
                                //Muestra mensaje de error al inicializar valores
                                msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_REQ_INIT_ERROR, null);
                                msgErroraux =  pageContext.getParameter("ErrorEspecifico");
                                if (msgErrorApprover != null) {
                                    msgError += ". " + msgErrorApprover;
                                    msgErroraux =  pageContext.getParameter("ErrorEspecificoAp");
                                    /* Add by DIHU (CCT) 17-Oct-2014
                                     * Se agrego un codigo al final de la cadena con la finalidad de identificar el error de forma mas especifica. 
                                     */
                                    if (pageContext.getParameter("ErrorEspecificoAp") != null && !"".equals(pageContext.getParameter("ErrorEspecificoAp").toString().trim()))
                                        msgError += ". " + msgErroraux;
                                }
                                
                                /* Add by DIHU (CCT) 17-Oct-2014
                                 * Se agrego un codigo al final de la cadena con la finalidad de identificar el error de forma mas especifica. 
                                 */
                                if (pageContext.getParameter("ErrorEspecifico") != null && !"".equals(pageContext.getParameter("ErrorEspecifico").toString().trim()))
                                    msgError += ". " + msgErroraux;
                                    
                                hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                         msgError);
                                //Redirige la navegacion a la pagina inicial del empleado
                                XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                             webBean, 
                                                                             hmap, 
                                                                             urlBase + 
                                                                             "XxGamPaymentInitAdvancePG");
                            }
                            
                            pageContext.putTransactionValue(XxGamConstantsUtil.VIEW_STEP_ONE, 
                                                            "true");
                        }
                        //Habilita componentes de franquicia
                        if (nextFbutton != null) {
                            nextFbutton.setRendered(true);
                        }
                        //Habilita el boton de guardar y cancelar
                        if (saveButton != null) {
                            saveButton.setRendered(true);
                        }
                        if (cancelButton != null) {
                            cancelButton.setRendered(true);
                        }
                        if (Reason != null) {
                            Reason.setRendered(true);
                        }
                        if (Comments != null) {
                            Comments.setRendered(true);
                        }
                    } else {
                        //Verifica el estado de la pagina para actualizar la solicitud de franquicia
                        if (XxGamConstantsUtil.UPDATE.equals(pageContext.getTransactionValue(XxGamConstantsUtil.STATUS))) {
                            //Deshabilita tabla solo lectura
                            if (formGeneralReqReadOnly != null) {
                                formGeneralReqReadOnly.setRendered(false);
                            }
                            //Habilita tabla editable
                            if (formGeneralReq != null) {
                                formGeneralReq.setRendered(true);
                            }
                            if (returnButton != null) {
                                returnButton.setRendered(false);
                            }

                            if (step1Flag == null) {

                                //Valida que el anticipo es apto para edicion
                                boolean isEditable = false;
                                isEditable = 
                                        XxGamMAnticiposUtil.isPaymentReqEditable(pageContext, 
                                                                                 webBean);
                                if (isEditable) {

                                    boolean isSuccess = false;
                                    //Ejecuta la configuracion de valores descriptivos
                                 System.out.println("Se comenta para un mejor trato a la informacion   " +
                                                     "boolean isSuccess  = XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, webBean);");      
                                   isSuccess =  XxGamMAnticiposUtil2.setPaymentReqDescriptionsReadOnly(pageContext
                                                                                                     ,webBean);
                                  
                                  
                                  XxGamModAntAMImpl ModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
                                  XxGamMaGeneralReqVOImpl generalImpl = ModAntAMImpl.getXxGamMaGeneralReqVO1();
                                  XxGamMaGeneralReqVORowImpl generalRow = null;
                                  generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
                                  
                                  if (isSuccess) {
                                   XxGamMaPaymentReqVOImpl voPaymentRequest = null;
                                   voPaymentRequest = ModAntAMImpl.getXxGamMaPaymentReqVO2();
                                   XxGamModAntLovAMImpl amLov = null;
                                   amLov = (XxGamModAntLovAMImpl)ModAntAMImpl.getXxGamModAntLovAM1();
                                   if ((voPaymentRequest != null) && (amLov != null))
                                   {
                                     RowSetIterator rowSetIter = voPaymentRequest.getRowSetIterator();
                                     if (rowSetIter != null) {
                                       rowSetIter.reset();
                                       while (rowSetIter.hasNext()) {
                                         Row rowDetail = rowSetIter.next();
                                         XxGamMaPaymentReqVORowImpl rowPaymentRDetail = (XxGamMaPaymentReqVORowImpl)rowDetail;
                                         if (rowPaymentRDetail != null)
                                         {
                                           String typePaymentDesc = null;
                                           XxGamMaTypePaymentLovVORowImpl typePaymentRow = amLov.getTypePaymentById(rowPaymentRDetail.getTypePayment(), generalRow.getTemplatePayment());
                                           if ((typePaymentRow != null) && 
                                             (typePaymentRow.getTypePaymentDesc() != null))
                                           {
                                             typePaymentDesc = typePaymentRow.getTypePaymentDesc();
                                           }
                                          // rowPaymentRDetail.setTypePymentDesc(typePaymentDesc);
                                           String currencyDetail = null;
                                           XxGamMaCurrencyLovVORowImpl currencyDetailRow = amLov.getCurrencyByCode(rowPaymentRDetail.getCurrencyCode());
                                           if ((currencyDetailRow != null) && 
                                             (currencyDetailRow.getCurrencyName() != null))
                                           {
                                             currencyDetail = currencyDetailRow.getCurrencyName();
                                           }
                                           rowPaymentRDetail.setCurrencyDesc(currencyDetail);
                                           rowPaymentRDetail.setIsPaymentValid(Boolean.valueOf(false));
                                           rowPaymentRDetail.setIsPaymentNotValid(Boolean.valueOf(false));
                                         }
                                       }
                                     }
                                   }
                                   
                                    if(null!=getOrgName){
                                      generalRow.setOperatingUnit(getOrgName);
                                    }
                                   
                                  } //END if (isSuccess) {
                                  
                                  System.out.println("Finaliza lo anterior se agrega para que en la pantalla de detalles, se tome informacion que anteriormente se regsitro Modo Update");
                                  /**Agregado 14Jul2015 **/
                                  isSuccess = XxGamMAnticiposUtil2.setOriginApproverInGeneralUpdate(pageContext, webBean); 

                                    if (isSuccess) {
                                        //Habilita componentes de franquicia
                                        if (nextFbutton != null) {
                                            nextFbutton.setRendered(true);
                                        }
                                        //Habilita el boton de guardar y cancelar
                                        if (saveButton != null) {
                                            saveButton.setRendered(true);
                                        }
                                        if (cancelButton != null) {
                                            cancelButton.setRendered(true);
                                        }
                                        if (Reason != null) {
                                            Reason.setRendered(true);
                                        }
                                        if (Comments != null) {
                                            Comments.setRendered(true);
                                        }
                                    } else {
                                        //Redirige la navegacion a la pagina de inicio del empleado y muestra mensaje de error por registro de solicitud no esta disponible para su edicion
                                        //Deshace cambios en la BD
                                        XxGamMAnticiposUtil.setRollback(pageContext, 
                                                                        webBean);
                                        //Muestra mensaje de error al inicializar valores
                                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_REQ_INIT_ERROR, null);
                                        hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                                 msgError);
                                        //Redirige la navegacion a la pagina inicial del empleado
                                        XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                                     webBean, 
                                                                                     hmap, 
                                                                                     urlBase + 
                                                                                     "XxGamPaymentInitAdvancePG");
                                    }
                                } else {
                                    //Redirige la navegacion a la pagina de inicio del empleado y muestra mensaje de error por registro de solicitud no esta disponible para su edicion
                                    //Deshace cambios en la BD
                                    XxGamMAnticiposUtil.setRollback(pageContext, 
                                                                    webBean);
                                    //Muestra mensaje de error al inicializar valores
                                    msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_REQ_NOT_EDIT_WARN, null);
                                    hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                             msgError);
                                    //Redirige la navegacion a la pagina inicial del empleado
                                    XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                                 webBean, 
                                                                                 hmap, 
                                                                                 urlBase + 
                                                                                 "XxGamPaymentInitAdvancePG");
                                }
                            }
                        } else {
                            //Verifica el estado de la pagina para solo lectura de la solicitud de franquicia
                            if (XxGamConstantsUtil.READ_ONLY.equals(pageContext.getTransactionValue(XxGamConstantsUtil.STATUS))) {
                                //Deshabilita tabla solo lectura
                                if (formGeneralReqReadOnly != null) {
                                    formGeneralReqReadOnly.setRendered(true);
                                }
                                //Deshabilita tabla editable
                                if (formGeneralReq != null) {
                                    formGeneralReq.setRendered(false);
                                }
                                //Deshabilita el boton de guardar
                                if (saveButton != null) {
                                    saveButton.setRendered(false);
                                }
                                //Habilita el boton de cancelar
                                if (cancelButton != null) {
                                    cancelButton.setRendered(false);
                                }
                                if (returnButton != null) {
                                    returnButton.setRendered(true);
                                }
                                //Deshabilita los campos de Motivo (Reason) y Observaciones (Comments)
                                if (Reason != null) {
                                    Reason.setRendered(false);
                                }
                                if (Comments != null) {
                                    Comments.setRendered(false);
                                }

                                if (step1Flag == null) {

                                    String strIdRequest = null;
                                    strIdRequest = 
                                            pageContext.getParameter(XxGamConstantsUtil.REQUEST_ID);

                                    Number idRequest = null;
                                    try {
                                        idRequest = new Number(strIdRequest);
                                    } catch (SQLException e) {
                                        idRequest = null;
                                    }

                                    if (idRequest != null) {

                                        boolean isFound = false;
                                        //Busca el registro de franquicia correspondientes al id de la solicitud
                                        isFound = 
                                                XxGamMAnticiposUtil.setCurrentRowPaymentReqById(idRequest, 
                                                                                                pageContext, 
                                                                                                webBean);
                                        //Valida la existencia del registro de la solicitud
                                        if (isFound) {

                                            boolean isSuccess = false;
                                            //Ejecuta la configuracion de valores descriptivos
                                            
                                             System.out.println("Se comenta para un mejor trato a la informacion " +
                                             "\n isSuccess = XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, webBean);"); 
                                             
                                             isSuccess =  XxGamMAnticiposUtil2.setPaymentReqDescriptionsReadOnly(pageContext
                                                                                                                ,webBean);
                                            
                                            if (isSuccess) {
                                                //Habilita el boton de siguiente
                                                if (nextFbutton != null) {
                                                    nextFbutton.setRendered(true);
                                                }
                                            } else {
                                                //Deshace cambios en la BD
                                                XxGamMAnticiposUtil.setRollback(pageContext, 
                                                                                webBean);
                                                //Muestra mensaje de error al inicializar valores
                                                msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_REQ_INIT_ERROR, null);
                                                hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                                         msgError);
                                                //Redirige la navegacion a la pagina inicial del empleado
                                                XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                                             webBean, 
                                                                                             hmap, 
                                                                                             urlBase + 
                                                                                             "XxGamPaymentInitAdvancePG");
                                            }
                                        } else {
                                            //Muestra mensaje de error de solicitud no encontrada
                                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_NF_ERROR, null);
                                            hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                                     msgError);
                                            //Redirige la navegacion a la pagina inicial del empleado
                                            XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                                         webBean, 
                                                                                         hmap, 
                                                                                         urlBase + 
                                                                                         "XxGamPaymentInitAdvancePG");
                                        }
                                    }
                                }
                            } else {
                                //Redirige la navegacion a la pagina inicial del empleado o franquicia
                                msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_ACCESS_DEN_NA_ERROR, null);
                                hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                         msgError);
                                //Redirige a la pagina correspondiente a la responsabilidad 
                                urlBase += "XxGamMaBlankPagePG";
                                XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                             webBean, 
                                                                             hmap, 
                                                                             urlBase);
                            }
                        }
                    }
                } else {
                
                    if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                webBean, 
                                                                new Number(pageContext.getResponsibilityId()), 
                                                                XxGamConstantsUtil.RESPONSABILITY_AUDITOR)) {
                        
                        //Deshabilita los componentes de empleado
                        if (trainBean != null) {
                            trainBean.setRendered(false);
                        }
                        if (navBarBean != null) {
                            navBarBean.setRendered(false);
                        }
                        if (currencyName != null) {
                            currencyName.setRendered(false);
                        }
                        if (templateLov != null) {
                            templateLov.setRendered(false);
                        }
                        if (templateFlexLov != null) {
                            templateFlexLov.setRendered(false);
                        }

                        if (currencyNameRO != null) {
                            currencyNameRO.setRendered(false);
                        }
                        if (templateDescRO != null) {
                            templateDescRO.setRendered(false);
                        }
                        
                        //Verifica el estado de la pagina para solo lectura de la solicitud de franquicia
                        if (XxGamConstantsUtil.READ_ONLY.equals(pageContext.getTransactionValue(XxGamConstantsUtil.STATUS))) {
                            //Deshabilita tabla solo lectura
                            if (formGeneralReqReadOnly != null) {
                                formGeneralReqReadOnly.setRendered(true);
                            }
                            //Deshabilita tabla editable
                            if (formGeneralReq != null) {
                                formGeneralReq.setRendered(false);
                            }
                            //Deshabilita el boton de guardar
                            if (saveButton != null) {
                                saveButton.setRendered(false);
                            }
                            //Habilita el boton de cancelar
                            if (cancelButton != null) {
                                cancelButton.setRendered(false);
                            }
                            if (returnButton != null) {
                                returnButton.setRendered(true);
                            }
                            //Deshabilita los campos de Motivo (Reason) y Observaciones (Comments)
                            if (Reason != null) {
                                Reason.setRendered(false);
                            }
                            if (Comments != null) {
                                Comments.setRendered(false);
                            }

                            if (step1Flag == null) {

                                String strIdRequest = null;
                                strIdRequest = 
                                        pageContext.getParameter(XxGamConstantsUtil.REQUEST_ID);

                                Number idRequest = null;
                                try {
                                    idRequest = new Number(strIdRequest);
                                } catch (SQLException e) {
                                    idRequest = null;
                                }

                                if (idRequest != null) {

                                    boolean isFound = false;
                                    //Busca el registro de franquicia correspondientes al id de la solicitud
                                    isFound = 
                                            XxGamMAnticiposUtil.setCurrentRowPaymentReqById(idRequest, 
                                                                                            pageContext, 
                                                                                            webBean);
                                    //Valida la existencia del registro de la solicitud
                                    if (isFound) {

                                        boolean isSuccess = false;
                                        //Ejecuta la configuracion de valores descriptivos
                                                    System.out.println("Se comenta para un mejor trato a la informacion " +
                                                                     "\n isSuccess = XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, webBean);"); 
                                      
                                       isSuccess =  XxGamMAnticiposUtil2.setPaymentReqDescriptionsReadOnly(pageContext
                                                                                                          ,webBean);
                                                                                                         
                                        if (isSuccess) {
                                            //Habilita el boton de siguiente
                                            if (nextFbutton != null) {
                                                nextFbutton.setRendered(true);
                                            }
                                        } else {
                                            //Deshace cambios en la BD
                                            XxGamMAnticiposUtil.setRollback(pageContext, 
                                                                            webBean);
                                            //Muestra mensaje de error al inicializar valores
                                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_REQ_INIT_ERROR, null);
                                            hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                                     msgError);
                                            //Redirige la navegacion a la pagina inicial del empleado
                                            XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                                         webBean, 
                                                                                         hmap, 
                                                                                         urlBase + 
                                                                                         "XxGamAdvanceConsultationPG");
                                        }
                                    } else {
                                        //Muestra mensaje de error de solicitud no encontrada
                                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_NF_ERROR, null);
                                        hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                                 msgError);
                                        //Redirige la navegacion a la pagina inicial del empleado
                                        XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                                     webBean, 
                                                                                     hmap, 
                                                                                     urlBase + 
                                                                                     "XxGamAdvanceConsultationPG");
                                    }
                                }
                            }
                        } else {
                            //Redirige la navegacion a la pagina inicial del empleado o franquicia
                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_ACCESS_DEN_NA_ERROR, null);
                            hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, 
                                     msgError);
                            //Redirige a la pagina correspondiente a la responsabilidad 
                            urlBase += "XxGamMaBlankPagePG";
                            XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                         webBean, 
                                                                         hmap, 
                                                                         urlBase);
                        }
                    }else{
                        //Configura mensaje de error para cuando el usuario no tiene responsabilidad para acceder a la pagina
                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_ACCESS_DEN_NR_ERROR, null);
                        hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, msgError);
                        //Redirige a la pagina correspondiente a la responsabilidad 
                        urlBase += "XxGamMaBlankPagePG";
                        XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                     webBean, hmap, 
                                                                     urlBase);
                    }
                }
            }
        }
    }

    /**
     * Procedure to handle form submissions for form elements in
     * a region.
     * @param pageContext contiene el objeto OA page context correspondiente a la pagina actual
     * @param webBean contiene el objeto de web bean correspondiente a la region
     */
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);

        if (pageContext.getTransactionValue(XxGamConstantsUtil.VIEW_STEP_ONE) == 
            null) {
            pageContext.putTransactionValue(XxGamConstantsUtil.VIEW_STEP_ONE, 
                                            "true");
        }
        proccessLovInputAction(pageContext, webBean);
        
      //Inicia Ajuste validacion plantila-centro de costos by GnosisHCM/AHH
      Pattern pat;
      Matcher mat;
      String patValue;
      String strEvntContBttn= pageContext.getParameter(EVENT_PARAM);
      System.out.println("-->Event: " + strEvntContBttn);
      XxGamModAntAMImpl ModAntAMImpl = null;
     
      if(null!=strEvntContBttn){
        if ( strEvntContBttn.equals("goto"))
              {
                OAMessageStyledTextBean ccMSTextBean = (OAMessageStyledTextBean)webBean.findChildRecursive("CostCenterDescRO");
                OAMessageStyledTextBean ccaMSTextBean = (OAMessageStyledTextBean)webBean.findChildRecursive("CostCenterFlexRO");
                OAMessageStyledTextBean temMSTextBean = (OAMessageStyledTextBean)webBean.findChildRecursive("TemplateDescRO");
                  OAMessageStyledTextBean tvMSTextBean = (OAMessageStyledTextBean)webBean.findChildRecursive("VirtualCardRO");
                  OAMessageStyledTextBean tvMSTextBean2 = (OAMessageStyledTextBean)webBean.findChildRecursive("VirtualCardROXX");
                  System.out.println("TV1: "+tvMSTextBean.getValue(pageContext));
                  System.out.println("TV2: "+tvMSTextBean2.getValue(pageContext));
                System.out.println("CC: " + ccMSTextBean.getValue(pageContext));
                System.out.println("CCA: " + ccaMSTextBean.getValue(pageContext));
                System.out.println("TEMPLATE: " + temMSTextBean.getValue(pageContext));
                
                if(ccaMSTextBean.getValue(pageContext) != null)
                   {
                    /**AHH 29Jul2015  patValue = ((String)ccaMSTextBean.getValue(pageContext)).substring(0,6); **/
                    if(((String)ccaMSTextBean.getValue(pageContext)).length()==11){
                      patValue = ((String)ccaMSTextBean.getValue(pageContext)).substring(0,6)+"_"+((String)ccaMSTextBean.getValue(pageContext)).substring(7,((String)ccaMSTextBean.getValue(pageContext)).length());
                    }else{
                      patValue="-1";
                    }  
                   }
                else
                   {
                    /** patValue = ((String)ccMSTextBean.getValue(pageContext)).substring(0,6); Valicacion Ubicacion y CC**/
                     if(((String)ccMSTextBean.getValue(pageContext)).length()==11){
                    patValue = ((String)ccMSTextBean.getValue(pageContext)).substring(0,6)+"_"+((String)ccMSTextBean.getValue(pageContext)).substring(7,((String)ccMSTextBean.getValue(pageContext)).length());
                     }else{
                      patValue="-1";
                    } 
                    
                   }
                
                if(!"-1".equals(patValue)&&(null!=patValue)){
                   
                pat = Pattern.compile(patValue);
                mat = pat.matcher((CharSequence)temMSTextBean.getValue(pageContext));
                
                if (mat.find()) 
                   {
                    System.out.println("template - cc validation: True");
                   }
                else 
                   {
                    System.out.println("template - cc validation: False");
                    
                    MessageToken[] arrayOfMessageToken = { new MessageToken("PARAM1", (String)temMSTextBean.getValue(pageContext)), new MessageToken("PARAM2", patValue) };
                    OAException localOAException = new OAException("SQLAP", "XXGAM_AP_OIE_VALIDA_CCTEMP", arrayOfMessageToken, OAException.ERROR, null);
                     
                    OADialogPage localOADialogPage = new OADialogPage(OAException.ERROR , localOAException, null, "", null);
                    localOADialogPage.setOkButtonToPost(true);
                    localOADialogPage.setOkButtonLabel("Ok");
                    localOADialogPage.setPostToCallingPage(true);
                    Hashtable localHashtable = new Hashtable(1);
                    localOADialogPage.setFormParameters(localHashtable);
                    pageContext.redirectToDialogPage(localOADialogPage);
                   }
                }  
                  /* */
                  
                  ModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
                  
                    if(null != ModAntAMImpl){
                      System.out.println("Debug CMV-AHH 00: Make class validation");
                      CcManagementValidation CcManagementValidation1 = new CcManagementValidation(pageContext, webBean);
                      System.out.println("Debug CMV-AHH 01: Post constructor class validation");
                    
                      if(!CcManagementValidation1.getCcManagementFlag() && null == CcManagementValidation1.getErrmsgOUT() && null == CcManagementValidation1.getErrcodOUT()){
                        CcManagementValidation1.setCcManagementFlag(CcManagementValidation1.doManagementValidation(ModAntAMImpl
                                                                                                                  ,CcManagementValidation1.getCcEmployee()
                                                                                                                  ,CcManagementValidation1.getCcApproverAlt()
                                                                                                                  ,CcManagementValidation1.getApproverAltId()));
                        System.out.println("Debug CMV-AHH 02: Post do Management Validation");                                                                                          
                        
                        if(!CcManagementValidation1.getCcManagementFlag() && null == CcManagementValidation1.getErrmsgOUT() && null == CcManagementValidation1.getErrcodOUT()){
                          //MANDAR MENSAJE DE QUE NO PERTENECEN A LA MISMA DIRECCION
                          throw new OAException("XBOL", "XXGAM_OIE_MAF_APPROV_CC_VAL_S", null, OAException.ERROR, null);
                        }
                      }
                      
                      if(null != CcManagementValidation1.getErrmsgOUT() || null != CcManagementValidation1.getErrcodOUT()){
                        //MANDAR MENSAJE DE ERROR POR VARIABLES
                        MessageToken[] messageTokenError = { new MessageToken("PARAM1", CcManagementValidation1.getErrcodOUT()), new MessageToken("PARAM2", CcManagementValidation1.getErrmsgOUT()) };
                        throw new OAException("XBOL", "XXGAM_OIE_MAF_APPROV_CC_VAL_E", messageTokenError, OAException.ERROR, null);
                      }
                      
                    }
                  /* */
              }
        //Fin Ajuste validacion plantila-centro de costos by GnosisHCM/AHH 
      }
     
        

        XxGamMAnticiposUtil.exePaymentReqProcessFromRequest(pageContext, 
                                                            webBean, 
                                                            pageContext.getParameter(EVENT_PARAM));
    }

    /**
     * Procesa los eventos disparados por accion de los componentes de tipo input lov
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    private void proccessLovInputAction(OAPageContext pageContext, 
                                        OAWebBean webBean) {
        
      String paramorgNameEmp = null; 
      paramorgNameEmp = (String)pageContext.getTransactionValue("orgNameEmp");
      String orgShortNameEmp = paramorgNameEmp.substring(0,2); 
      System.out.println("paramorgNameEmp-->"+paramorgNameEmp); 
      
      System.out.println("pageContext.getLovInputSourceId()-->"+pageContext.getLovInputSourceId()); 
    
        if (pageContext != null && webBean != null) {
            //Verifica accion en componentes de LOV
            if (pageContext.getLovInputSourceId() != null) {
                //Obtiene el id de la lov que dispara el evento
                String lovInputSourceId = pageContext.getLovInputSourceId();

                boolean initSuccess = false;
                //Verifica el id de la lov si corresponde a propuesta de pago
                if ("PurposeLov".equals(lovInputSourceId)) {
                    //Inicializa lista de valores para proposito de la solicitud de anticipo o franquicia
                    XxGamMAnticiposUtil.initPorpuseLov(pageContext, webBean);
                }

                //Verifica el id de la lov si corresponde a centro de costos
                if ("CostCenterEmpLov".equals(lovInputSourceId) || 
                    "CostCenterFraLov".equals(lovInputSourceId)) {
                    initSuccess = false;
                    //Inicializa lista de valores para centro de costos
                    initSuccess = 
                            XxGamMAnticiposUtil.initCostCenter(pageContext, 
                                                               webBean);
                    if (!initSuccess) {
                    
                        throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                          XxGamAOLMessages.Validation.XXGAM_MAF_CC_LOV_INIT_ERROR,
                                                          null, OAException.WARNING, null);
                    }

                    //Inicializa la lista de valores de la plantilla de anticipo
                    initSuccess = 
                            XxGamMAnticiposUtil.initTemplatePayment(pageContext, 
                                                                    webBean);
                    if (!initSuccess) {
                        throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                          XxGamAOLMessages.Validation.XXGAM_MAF_TPT_LOV_NF_ERROR,
                                                          null, OAException.WARNING, null);
                    }
                }

                if ("CostCenterFlexLov".equals(lovInputSourceId)) {

                    initSuccess = false;
                    pageContext.putSessionValue("ListVal",""); //Agregado para que al seleccionar un nuevo centro de costos la pantilla del anterior no se conserve
                  
                  if( (null!=orgShortNameEmp)&&(!"02".equals(orgShortNameEmp))){
                    System.out.println("Inicializa lista de valores para Centro de Costos Alternos y Sus Plantillas Correspondientes por Unidad Operativa:"+paramorgNameEmp);
                     System.out.println("Se omite la inicializacion de la lista de valores para centro de costos alternos deacuerdo a la unidad operativa" +
                                        "\n ya que esta lista de valores se ha inicializado en el metodo initNewPaymentRequest de la Capa AM"); 

                         if (XxGamConstantsUtil.UPDATE.equals(pageContext.getTransactionValue(XxGamConstantsUtil.STATUS)))
                            {
                              initSuccess = XxGamMAnticiposUtil2.initCostCenterFlexUpdate(pageContext, webBean); 
                              if(initSuccess){
                                initSuccess = XxGamMAnticiposUtil2.setOriginApproverInGeneralUpdate (pageContext, webBean); 
                               }else{
                                 throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                                   XxGamAOLMessages.Validation.XXGAM_MAF_CCF_LOV_NF_ERROR,
                                                                   null, OAException.WARNING, null);
                               }     
                            }
                                       
                   
                    System.out.println( "Sin Embargo Cada Vez Que Se Selecione un nuevo Centro de Costos las plantillas tambien deben Cambiar");
                    initSuccess = XxGamMAnticiposUtil2.initTemplatePayment(pageContext,webBean);
                   
                    if (!initSuccess) {
                        throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                          XxGamAOLMessages.Validation.XXGAM_MAF_TPT_LOV_NF_ERROR,
                                                          null, OAException.WARNING, null);
                    }
                    
                    
                  }else if((null!=orgShortNameEmp)&&("02".equals(orgShortNameEmp))){
                    System.out.println("Inicializa lista de valores para centro de costos Alternos Unidad Operativa:"+paramorgNameEmp);
                    //Inicializa lista de valores para centro de costos
                     /**  BEGIN  *********************************************/
                    initSuccess = 
                            XxGamMAnticiposUtil.initCostCenterFlex(pageContext, 
                                                                   webBean);
                    if (!initSuccess) {
                        throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                          XxGamAOLMessages.Validation.XXGAM_MAF_CCF_LOV_NF_ERROR,
                                                          null, OAException.WARNING, null);
                    }

                    if (initSuccess) {
                        //Inicializa la lista de valores de la plantilla de anticipo
                         System.out.println( "Comentado por recustrucion del metodo para que sea MultiOrg "+
                         "\n initSuccess = XxGamMAnticiposUtil.initTemplatePayment(pageContext, webBean);");
                         //initSuccess = XxGamMAnticiposUtil.initTemplatePayment(pageContext, webBean);
                         initSuccess = XxGamMAnticiposUtil2.initTemplatePayment(pageContext,webBean);
                     
                        if (!initSuccess) {
                            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                              XxGamAOLMessages.Validation.XXGAM_MAF_TPT_LOV_NF_ERROR,
                                                              null, OAException.WARNING, null);
                        }
                        /**
                         * @Author EAB se comento el codigo para que se pueda volver a elegir la lista de valores un nuevo dato
                         */
                        /*
                        if(pageContext.getParameter("CostCenterFlexLov")!= null && !"".equals(pageContext.getParameter("CostCenterFlexLov"))){
                            pageContext.forwardImmediatelyToCurrentPage(null,true,OAWebBeanConstants.ADD_BREAD_CRUMB_NO);
                        }else{
                            OAMessageLovInputBean lovBean = (OAMessageLovInputBean)webBean.findChildRecursive("CostCenterFlexLov");
                            if(lovBean != null){
                                if(!lovBean.isShowWindow()){
                                    pageContext.forwardImmediatelyToCurrentPage(null,true,OAWebBeanConstants.ADD_BREAD_CRUMB_NO);      
                                }
                            }    
                        }
                        
                        */
                    }  
                      /**  END *********************************************/
                  } 
                  
                }

                if ("ApproverHierarchyLov".equals(lovInputSourceId)) {
                    initSuccess = false;
                    //Inicializa lista de valores para aprobador
                    initSuccess = 
                            XxGamMAnticiposUtil.initApproverHierarchy(pageContext, 
                                                                      webBean);
                    if (!initSuccess) {
                        throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                          XxGamAOLMessages.Validation.XXGAM_MAF_APH_LOV_INIT_ERROR,
                                                          null, OAException.WARNING, null);
                    }
                }

                if ("TemplatePaymentLov".equals(lovInputSourceId) || 
                    "TemplatePaymentFlexLov".equals(lovInputSourceId)) {
                    initSuccess = false;
                    String valFelx = pageContext.getParameter("TemplatePaymentFlexLov");
                    if(valFelx != null && !"".equals(valFelx)) {
                       pageContext.putSessionValue("ListVal",valFelx);     
                    }
                    else{
                        valFelx = pageContext.getParameter("TemplatePaymentLov");
                        if(valFelx != null && !"".equals(valFelx)) {
                           pageContext.putSessionValue("ListVal",valFelx);     
                        }
                        else{
                            pageContext.putSessionValue("ListVal","");
                        }
                    }
                        
                    //Inicializa la lista de valores de la plantilla de anticipo
                    System.out.println( "Comentado por recustrucion del metodo para que sea MultiOrg "+
                    "\n initSuccess = XxGamMAnticiposUtil.initTemplatePayment(pageContext, webBean);");
                  //initSuccess = XxGamMAnticiposUtil.initTemplatePayment(pageContext, webBean);
                  initSuccess = XxGamMAnticiposUtil2.initTemplatePayment(pageContext,webBean);                                                
                    if (!initSuccess) {
                        
                        throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                          XxGamAOLMessages.Validation.XXGAM_MAF_TPT_LOV_NF_ERROR,
                                                          null, OAException.WARNING, null);
                    }
                    
                }  // END  if ("TemplatePaymentLov".equals(lovInputSourceId) ||   "TemplatePaymentFlexLov".equals(lovInputSourceId)) {
                
                if("ApproverAlternateLov".equals(lovInputSourceId)){
                System.out.println("");  
                }
                
                XxGamModAntAMImpl am = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
                XxGamModAntLovAMImpl amLov = (XxGamModAntLovAMImpl)am.getXxGamModAntLovAM1();
                try{
                    String listFlex = pageContext.getSessionValue("ListVal").toString();
                    System.out.println("listFlex: "+listFlex); 
                    if("".equals(listFlex)){
                      System.out.println("si es"); 
                    }else{
                      System.out.println("no es"); 
                    }
                    if( listFlex != null && !"".equals(listFlex)) {
                            OAViewObject vo = (OAViewObject)am.findViewObject("XxGamMaGeneralReqVO1");
                            OARow fila = (OARow)vo.getCurrentRow();
                            fila.setAttribute("TypeTemplateDesc", listFlex);
                        if("TemplatePaymentFlexLov".equals(lovInputSourceId)){
                            XxGamMaTemplatePaymentFlexLovVOImpl voFlex = amLov.getXxGamMaTemplatePaymentFlexLovVO1();
                            if(voFlex != null && voFlex.getEstimatedRowCount() > 0){
                                XxGamMaTemplatePaymentFlexLovVORowImpl rowFlex = null;
                                RowSetIterator itr = voFlex.createRowSetIterator(null);
                                itr.setRangeStart(0);
                                while(itr.hasNext()){
                                    rowFlex = (XxGamMaTemplatePaymentFlexLovVORowImpl)itr.next();
                                  System.out.println("listFlex.equals: "+rowFlex.getTemplateDesc()); 
                                    if(listFlex.equals(rowFlex.getTemplateDesc())){
                                        fila.setAttribute("TemplatePayment", rowFlex.getTemplateId());
                                        break;
                                    }
                                }
                                itr.closeRowSetIterator();
                            }
                        }
                    }
                }
                catch(Exception e){
                    e.getMessage();
                }
                
            }
        }
    }
}
