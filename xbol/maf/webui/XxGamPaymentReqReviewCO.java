/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.xbol.maf.webui;

import com.sun.java.util.collections.HashMap;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.Hashtable;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.layout.OACellFormatBean;
import oracle.apps.fnd.framework.webui.beans.layout.OATableLayoutBean;

import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Number;

import oracle.jbo.server.DBTransaction;
import oracle.jdbc.OracleCallableStatement;

import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaCurrencyLovVORowImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaTypePaymentLovVORowImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamModAntLovAMImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaGeneralReqVOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaGeneralReqVORowImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaPaymentReqVOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaPaymentReqVORowImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaVsMcpBudgetControlVOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaVsMcpBudgetControlVORowImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaVsMcpPeriodsAmountVOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaVsMcpPeriodsAmountVORowImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamModAntAMImpl;
import xxgam.oracle.apps.xbol.maf.utils.XxGamAOLMessages;
import xxgam.oracle.apps.xbol.maf.utils.XxGamConstantsUtil;
import xxgam.oracle.apps.xbol.maf.utils.XxGamMAnticiposUtil;
import xxgam.oracle.apps.xbol.maf.utils.XxGamMAnticiposUtil2;


/**
 * Clase controlador de la pÃ¡gina de RevisiÃ³n y propuesta de Pago.
 * Definida por el XML XxGamPaymentReqReviewPG
 * 
 * @author Aldo LÃ³pez de Nava
 */
public class XxGamPaymentReqReviewCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    /**
     * Resumen de los boletos de avión
     */
    public static final String PAGE_DETAIL_AIRPLANE = 
        "XxGamPaymentReqTicketPlanePG";

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);

        String msgError = null;
        String urlBase = XxGamConstantsUtil.URL_PAGE_OAF;
        HashMap hmap = new HashMap();
        String bandera = pageContext.getParameter("returnDialogPage");

        if (pageContext != null && webBean != null && (bandera == null || "".equals(bandera))) {
            
          /** Inicia segmento de codigo para validar los montos de los anticipos
           * contra los fondos disponibles Modulo Control Presupuestal 07/12/2015 **/
           
          String strMcpAplicaValVsMaf = null; 
          strMcpAplicaValVsMaf = pageContext.getProfile("XXGAM_MCP_VAL_FONDOS_DISPONIBLES");
          System.out.println("Perfil XXGAM_MCP_VAL_FONDOS_DISPONIBLES strMcpAplicaValVsMaf:"+strMcpAplicaValVsMaf);
          if(null!=strMcpAplicaValVsMaf||!"".equals(strMcpAplicaValVsMaf)){ 
          if("Y".equals(strMcpAplicaValVsMaf)){
          
            if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                      webBean, 
                                                                      new Number(pageContext.getResponsibilityId()), 
                                                                      XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)) {
              if(!pageContext.isFormSubmission()){
                System.out.println("Debug67: !pageContext.isFormSubmission()");
                createButtonToConectXxgamMcpModule(pageContext,webBean);
                fillXxgamMcpFundsInfo(pageContext,webBean);
              }else{
                System.out.println("Debug67: pageContext.isFormSubmission()");
                 /** Se agrega Metodo para validaciones de control presupuestal 23/09/2015**/
                 fillXxgamMcpFundsInfo(pageContext,webBean);
                 
                 String lRequestFundsOver = null; 
                 lRequestFundsOver = null!=(String)pageContext.getSessionValue("sRequestFundsOver")?(String)pageContext.getSessionValue("sRequestFundsOver"):(String)pageContext.getParameter("pRequestFundsOver");
                 System.out.println("Debug68: lRequestFundsOver "+lRequestFundsOver);
                 if(null!=lRequestFundsOver&&"valueRequestFundsOver".equals(lRequestFundsOver)){
                   createButtonToConectXxgamMcpModule(pageContext,webBean); 
                   RenderCreateButtonToConectXxgamMcpModule(pageContext,webBean,true);
                 }
                             
              } // End if(!pageContext.isFormSubmission()){                                                       
            } // END Valida Responsabilidad
          
          } // END if("Y".equals(strMcpAplicaValVsMaf)){
          } //END if(null!=strMcpAplicaValVsMaf||!"".equals(strMcpAplicaValVsMaf)){
          /** Finaliza segmento de codigo para validar los montos de los anticipos
           * contra los fondos disponibles Modulo Control Presupuestal 07/12/2015 **/
          
            String statusPage = 
                pageContext.getParameter(XxGamConstantsUtil.STATUS);
                
                System.out.println("Comienza XxGamPaymentReqReviewCO processRequest "); 
                System.out.println("Comienza Informacion XxGamPaymentReqReviewCO processRequest statusPage-->"+statusPage); 
            if (statusPage != null) {
                pageContext.putTransactionValue(XxGamConstantsUtil.STATUS, 
                                                statusPage);
            }
            
            pageContext.getApplicationModule(webBean).getOADBTransaction().putTransientValue("IsValidateEntityFlight", "true");
            pageContext.getApplicationModule(webBean).getOADBTransaction().putTransientValue("IsValidateEntityDetail", "true");

            //Obtiene los componentes de pantalla para la responsabilidad de empleado y aprobador
            OAWebBean trainBean = webBean.findChildRecursive("TrainNavRN");
            OAWebBean navBarBean = webBean.findChildRecursive("ButtonNavRN");
            OAWebBean saveButton = webBean.findChildRecursive("SaveButton");
            OAWebBean reserveFundsButton = 
                webBean.findChildRecursive("ReserveFundsButton");
            OAWebBean cancelButton = 
                webBean.findChildRecursive("CancelButton");
            OAWebBean returnButton = 
                webBean.findChildRecursive("ReturnButton");

            //Valida la responsabilidad de usuario
            if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                            webBean, 
                                                            new Number(pageContext.getResponsibilityId()), 
                                                            XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)) {


                String step2Flag = 
                    (String)pageContext.getTransactionValue(XxGamConstantsUtil.VIEW_STEP_TWO);

                //Valida bandera para ejecutar el procedimiento de creacion de un nuevo registro de solicitud de anticipo
                if (XxGamConstantsUtil.CREATE.equals(pageContext.getTransactionValue(XxGamConstantsUtil.STATUS))) {
                
                  Boolean isSuccess = false;
                  
                    if (step2Flag != null) {
                        
                        
                        boolean isConv = false;
                        isSuccess = XxGamMAnticiposUtil.refreshAllValidationRepeat(pageContext, webBean);
                        
                         /**************************************Agregado para cuando precionen Next valide Categorias Modo Create*****************/
                         if(isSuccess) {
                            isSuccess = XxGamMAnticiposUtil.refreshAllValidationByLineCategory(pageContext, webBean);
                                       } 
                        
                        if(isSuccess){
                            isSuccess = 
                                    XxGamMAnticiposUtil.refreshAllValidationByLine(pageContext, 
                                                                                   webBean);
                            if (isSuccess == null) {
                                isSuccess = false;
                            }
                            
                            isConv = XxGamMAnticiposUtil.calculateAmountMxAllPaymentDetail(pageContext, 
                                                                                          webBean);    
                        }
                        
                        if (!isSuccess || !isConv) {
                            String msg = null;
                            msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_REQ_GORPP_LINEVAL, null);
                            hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, msg);
                            //Redirige la navegacion a la pagina inicial del empleado
                            XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                         webBean, 
                                                                         hmap, 
                                                                         urlBase + 
                                                                         "XxGamPaymentReqDetailPG");
                        }
                    }

                    //Ejecuta la configuracion de valores descriptivos
                    
                    /** Se comenta para que no vuelva a reconstruir el formulario a acambio se añaden una lineas mas la Informacion por GnosisHCM
                    XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, 
                                                                          webBean);
                    **/
                    
                     XxGamModAntAMImpl ModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean); 
                     XxGamMaGeneralReqVOImpl generalImpl = ModAntAMImpl.getXxGamMaGeneralReqVO1();
                     XxGamMaGeneralReqVORowImpl generalRow = null;
                     generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
                  
                     if (generalRow.getTypeTemplateDesc()==null||generalRow.getTypeTemplateDesc().equals("")){
                       if(generalRow.getReportType()!=null||!(generalRow.getReportType().equals(""))){
                         generalRow.setTypeTemplateDesc(generalRow.getReportType()); 
                       }
                     }
                     
                    
                  //Configura el detalle de la solicitud de anticipo
                   Boolean isInitSuccess = false;
                  isInitSuccess = isSuccess; 
                  
                   if (isInitSuccess) {
                      XxGamMaPaymentReqVOImpl voPaymentRequest = null;
                      voPaymentRequest = ModAntAMImpl.getXxGamMaPaymentReqVO2();
                      XxGamModAntLovAMImpl amLov = null;
                     amLov = (XxGamModAntLovAMImpl)ModAntAMImpl.getXxGamModAntLovAM1();

                  if (voPaymentRequest != null && amLov != null) {

                      RowSetIterator rowSetIter =
                          voPaymentRequest.getRowSetIterator();
                      if (rowSetIter != null) {
                          rowSetIter.reset();
                          while (rowSetIter.hasNext()) {
                              Row rowDetail = rowSetIter.next();
                              XxGamMaPaymentReqVORowImpl rowPaymentRDetail =
                                  (XxGamMaPaymentReqVORowImpl)rowDetail;
                              if (rowPaymentRDetail != null) {

                                  String typePaymentDesc = null;
                                  XxGamMaTypePaymentLovVORowImpl typePaymentRow =
                                      amLov.getTypePaymentById(rowPaymentRDetail.getTypePayment(),
                                                               generalRow.getTemplatePayment());
                                  if (typePaymentRow != null) {
                                      if (typePaymentRow.getTypePaymentDesc() !=
                                          null) {
                                          typePaymentDesc =
                                                  typePaymentRow.getTypePaymentDesc();
                                      }
                                  }
                                  rowPaymentRDetail.setTypePymentDesc(typePaymentDesc);

                                  String currencyDetail = null;
                                  XxGamMaCurrencyLovVORowImpl currencyDetailRow =
                                      amLov.getCurrencyByCode(rowPaymentRDetail.getCurrencyCode());
                                  if (currencyDetailRow != null) {
                                      if (currencyDetailRow.getCurrencyName() !=
                                          null) {
                                          currencyDetail =
                                                  currencyDetailRow.getCurrencyName();
                                      }
                                  }
                                  rowPaymentRDetail.setCurrencyDesc(currencyDetail);
                                  rowPaymentRDetail.setIsPaymentValid(false);
                                  rowPaymentRDetail.setIsPaymentNotValid(false);
                              }
                          }
                      }
                  } 
                          
                  }  //  END if (isInitSuccess) {
                  
                    
                     
                    
                    //Habilita train y botones de navegacion
                    if (trainBean != null) {
                        trainBean.setRendered(true);
                    }
                    if (navBarBean != null) {
                        navBarBean.setRendered(true);
                    }

                    //Habilita el boton de guardar y de reservar fondos
                    if (saveButton != null) {
                        saveButton.setRendered(true);
                    }
                    if (reserveFundsButton != null) {
                        reserveFundsButton.setRendered(true);
                    }
                    if (cancelButton != null) {
                        cancelButton.setRendered(true);
                    }

                    //Deshabilita boton regresar para retornar a la pantalla inicial del empleado
                    if (returnButton != null) {
                        returnButton.setRendered(false);
                    }
                } else {
                    //Valida bandera para ejecutar el procedimiento de solo lectura de una solicitud de anticipo
                    if (XxGamConstantsUtil.READ_ONLY.equals(pageContext.getTransactionValue(XxGamConstantsUtil.STATUS))) {

                        //Ejecuta la configuracion de valores descriptivos
                      
                      System.out.println("Se comenta para un mejor trato a la informacion " +"\n    boolean isInitSuccess  = XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, webBean);");      
                      boolean isInitSuccess =  XxGamMAnticiposUtil2.setPaymentReqDescriptionsReadOnly(pageContext
                                                                                         ,webBean);
                      
                         XxGamModAntAMImpl ModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean); 
                         XxGamMaGeneralReqVOImpl generalImpl = ModAntAMImpl.getXxGamMaGeneralReqVO1();
                         XxGamMaGeneralReqVORowImpl generalRow = null;
                         generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
     
                      //Configura el detalle de la solicitud de anticipo
                       if (isInitSuccess) {
                          XxGamMaPaymentReqVOImpl voPaymentRequest = null;
                          voPaymentRequest = ModAntAMImpl.getXxGamMaPaymentReqVO2();
                          XxGamModAntLovAMImpl amLov = null;
                         amLov = (XxGamModAntLovAMImpl)ModAntAMImpl.getXxGamModAntLovAM1();

                      if (voPaymentRequest != null && amLov != null) {

                          RowSetIterator rowSetIter =
                              voPaymentRequest.getRowSetIterator();
                          if (rowSetIter != null) {
                              rowSetIter.reset();
                              while (rowSetIter.hasNext()) {
                                  Row rowDetail = rowSetIter.next();
                                  XxGamMaPaymentReqVORowImpl rowPaymentRDetail =
                                      (XxGamMaPaymentReqVORowImpl)rowDetail;
                                  if (rowPaymentRDetail != null) {

                                      String typePaymentDesc = null;
                                      XxGamMaTypePaymentLovVORowImpl typePaymentRow =
                                          amLov.getTypePaymentById(rowPaymentRDetail.getTypePayment(),
                                                                   generalRow.getTemplatePayment());
                                      if (typePaymentRow != null) {
                                          if (typePaymentRow.getTypePaymentDesc() !=
                                              null) {
                                              typePaymentDesc =
                                                      typePaymentRow.getTypePaymentDesc();
                                          }
                                      }
                                      rowPaymentRDetail.setTypePymentDesc(typePaymentDesc);

                                      String currencyDetail = null;
                                      XxGamMaCurrencyLovVORowImpl currencyDetailRow =
                                          amLov.getCurrencyByCode(rowPaymentRDetail.getCurrencyCode());
                                      if (currencyDetailRow != null) {
                                          if (currencyDetailRow.getCurrencyName() !=
                                              null) {
                                              currencyDetail =
                                                      currencyDetailRow.getCurrencyName();
                                          }
                                      }
                                      rowPaymentRDetail.setCurrencyDesc(currencyDetail);
                                      rowPaymentRDetail.setIsPaymentValid(false);
                                      rowPaymentRDetail.setIsPaymentNotValid(false);
                                  }
                              }
                          }
                      } 
                              
                   }  //  END if (isInitSuccess) {
                    
                    System.out.println("Finaliza Se comenta para que la informacion que se muetre se la que en su momento se registro " +
                    "\n isInitSuccess = XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, webBean);"); 
    
                          
                        if (isInitSuccess) {
                            //Deshabilita train
                            if (trainBean != null) {
                                trainBean.setRendered(false);
                            }
                            //Deshabilita botones de navegacion
                            if (navBarBean != null) {
                                navBarBean.setRendered(false);
                            }
                            //Deshabilita botones de guardar, reservar fondor y cancelar
                            saveButton.setRendered(false);
                            reserveFundsButton.setRendered(false);
                            cancelButton.setRendered(false);

                            //Habilita boton regresar para retornar a la pantalla inicial del empleado
                            returnButton.setRendered(true);
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
                        //Verifica el estado de la pagina para actualizar la solicitud de anticipo
                        if (XxGamConstantsUtil.UPDATE.equals(pageContext.getTransactionValue(XxGamConstantsUtil.STATUS))) {

                            if (step2Flag != null) {
                                
                                Boolean isSuccess = false;
                                boolean isConv = false;
                                isSuccess = XxGamMAnticiposUtil.refreshAllValidationRepeat(pageContext, webBean);
                               
                               /****************************Agregado para validar categorias Modo Update*********************/
                               if(isSuccess) {
                                  isSuccess = XxGamMAnticiposUtil.refreshAllValidationByLineCategory(pageContext, webBean);
                                  } 
                                                            
                                if(isSuccess){
                                    isSuccess = 
                                            XxGamMAnticiposUtil.refreshAllValidationByLine(pageContext, 
                                                                                           webBean);
                                    if (isSuccess == null) {
                                        isSuccess = false;
                                    }
                                    
                                    isConv = XxGamMAnticiposUtil.calculateAmountMxAllPaymentDetail(pageContext, 
                                                                                                  webBean);    
                                }
                                
                                if (!isSuccess || !isConv) {
                                    String msg = null;
                                    msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_REQ_GORPP_LINEVAL, null);
                                    hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, msg);
                                    //Redirige la navegacion a la pagina inicial del empleado
                                    XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                                 webBean, 
                                                                                 hmap, 
                                                                                 urlBase + 
                                                                                 "XxGamPaymentReqDetailPG");
                                }
                            }

                            //Valida que el anticipo es apto para edicion
                            boolean isEditable = false;
                            isEditable = 
                                    XxGamMAnticiposUtil.isPaymentReqEditable(pageContext, 
                                                                             webBean);
                            if (isEditable) {
                                //Ejecuta la configuracion de valores descriptivos
                                 System.out.println("Comienza Se comenta para un mejor trato a la informacion " +"\n    boolean isInitSuccess  = XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, webBean);");      
                                 boolean isInitSuccess =  XxGamMAnticiposUtil2.setPaymentReqDescriptionsReadOnly(pageContext
                                                                                                    ,webBean);
                                                                                                    
                              XxGamModAntAMImpl ModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean); 
                              XxGamMaGeneralReqVOImpl generalImpl = ModAntAMImpl.getXxGamMaGeneralReqVO1();
                              XxGamMaGeneralReqVORowImpl generalRow = null;
                              generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
                              
                              //Configura el detalle de la solicitud de anticipo
                              if (isInitSuccess) {
                               XxGamMaPaymentReqVOImpl voPaymentRequest = null;
                               voPaymentRequest = ModAntAMImpl.getXxGamMaPaymentReqVO2();
                               XxGamModAntLovAMImpl amLov = null;
                              amLov = (XxGamModAntLovAMImpl)ModAntAMImpl.getXxGamModAntLovAM1();

                              if (voPaymentRequest != null && amLov != null) {

                               RowSetIterator rowSetIter =
                                   voPaymentRequest.getRowSetIterator();
                               if (rowSetIter != null) {
                                   rowSetIter.reset();
                                   while (rowSetIter.hasNext()) {
                                       Row rowDetail = rowSetIter.next();
                                       XxGamMaPaymentReqVORowImpl rowPaymentRDetail =
                                           (XxGamMaPaymentReqVORowImpl)rowDetail;
                                       if (rowPaymentRDetail != null) {

                                           String typePaymentDesc = null;
                                           XxGamMaTypePaymentLovVORowImpl typePaymentRow =
                                               amLov.getTypePaymentById(rowPaymentRDetail.getTypePayment(),
                                                                        generalRow.getTemplatePayment());
                                           if (typePaymentRow != null) {
                                               if (typePaymentRow.getTypePaymentDesc() !=
                                                   null) {
                                                   typePaymentDesc =
                                                           typePaymentRow.getTypePaymentDesc();
                                               }
                                           }
                                           rowPaymentRDetail.setTypePymentDesc(typePaymentDesc);

                                           String currencyDetail = null;
                                           XxGamMaCurrencyLovVORowImpl currencyDetailRow =
                                               amLov.getCurrencyByCode(rowPaymentRDetail.getCurrencyCode());
                                           if (currencyDetailRow != null) {
                                               if (currencyDetailRow.getCurrencyName() !=
                                                   null) {
                                                   currencyDetail =
                                                           currencyDetailRow.getCurrencyName();
                                               }
                                           }
                                           rowPaymentRDetail.setCurrencyDesc(currencyDetail);
                                           rowPaymentRDetail.setIsPaymentValid(false);
                                           rowPaymentRDetail.setIsPaymentNotValid(false);
                                       }
                                   }
                               }
                              }
                                   
                              }  //  END if (isInitSuccess) {
                              
                              System.out.println("Finaliza Se comenta para que la informacion que se muetre se la que en su momento se registro " +
                              "\n isInitSuccess = XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, webBean);");
                                                                  

                                if (isInitSuccess) {
                                    //Habilita train y botones de navegacion
                                    if (trainBean != null) {
                                        trainBean.setRendered(true);
                                    }
                                    if (navBarBean != null) {
                                        navBarBean.setRendered(true);
                                    }

                                    //Habilita el boton de guardar y de reservar fondos
                                    if (saveButton != null) {
                                        saveButton.setRendered(true);
                                    }
                                    if (reserveFundsButton != null) {
                                        reserveFundsButton.setRendered(true);
                                    }
                                    if (cancelButton != null) {
                                        cancelButton.setRendered(true);
                                    }

                                    //Deshabilita boton regresar para retornar a la pantalla inicial del empleado
                                    if (returnButton != null) {
                                        returnButton.setRendered(false);
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
                }
            } else {

                if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                            webBean, 
                                                            new Number(pageContext.getResponsibilityId()), 
                                                            XxGamConstantsUtil.RESPONSABILITY_AUDITOR)) {
                                                            
                    //Valida bandera para ejecutar el procedimiento de solo lectura de una solicitud de anticipo
                    if (XxGamConstantsUtil.READ_ONLY.equals(pageContext.getTransactionValue(XxGamConstantsUtil.STATUS))) {

                        //Ejecuta la configuracion de valores descriptivos
                         System.out.println("Comienza Se comenta para un mejor trato a la informacion"
                                             +"boolean isInitSuccess  = XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, webBean); ");
                                             
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

                                         rowPaymentRDetail.setTypePymentDesc(typePaymentDesc);

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

                               }

                               System.out.println("Finaliza Se comenta para que la informacion que se muetre se la que en su momento se registro \n isInitSuccess = XxGamMAnticiposUtil.setPaymentReqDescriptionsReadOnly(pageContext, webBean);");

                                                               
                        if (isInitSuccess) {
                            //Deshabilita train
                            if (trainBean != null) {
                                trainBean.setRendered(false);
                            }
                            //Deshabilita botones de navegacion
                            if (navBarBean != null) {
                                navBarBean.setRendered(false);
                            }
                            //Deshabilita botones de guardar, reservar fondor y cancelar
                            saveButton.setRendered(false);
                            reserveFundsButton.setRendered(false);
                            cancelButton.setRendered(false);

                            //Habilita boton regresar para retornar a la pantalla inicial del empleado
                            returnButton.setRendered(true);
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
                        //Configura mensaje de error para cuando el usuario no tiene responsabilidad para acceder a la pagina
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
                }else {
                        //Configura mensaje de error para cuando el usuario no tiene responsabilidad para acceder a la pagina
                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_ACCESS_DEN_NR_ERROR, null);
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
        
        
        
     if(null!=pageContext&&null!=webBean){
     
       if (pageContext.getTransactionValue(XxGamConstantsUtil.VIEW_STEP_THREE) == null) {
           pageContext.putTransactionValue(XxGamConstantsUtil.VIEW_STEP_THREE, 
                                           "true");
       } // END Transaction value VIEW_STEP_THREE
       
       /** Inicia segmento de codigo para validar los montos de los anticipos
        * contra los fondos disponibles Modulo Control Presupuestal 07/12/2015 **/
        
       String strMcpAplicaValVsMaf = null; 
       strMcpAplicaValVsMaf = pageContext.getProfile("XXGAM_MCP_VAL_FONDOS_DISPONIBLES");
       System.out.println("strMcpAplicaValVsMaf:"+strMcpAplicaValVsMaf);
       if(null!=strMcpAplicaValVsMaf||!"".equals(strMcpAplicaValVsMaf)){ 
       if("Y".equals(strMcpAplicaValVsMaf)){
          if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                 webBean, 
                                                                 new Number(pageContext.getResponsibilityId()), 
                                                                 XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)) {
           System.out.println("Debug41:"+pageContext.getParameter(EVENT_PARAM));
           if("mcpToGeneralPGSubmitButton".equals(pageContext.getParameter(EVENT_PARAM))){
                      setForwardXxgamMcpGeneralPG(pageContext,webBean);
          
           }else if(XxGamConstantsUtil.RESERVE_FUNDS.equals(pageContext.getParameter(EVENT_PARAM))){
                String lMcpBudgetSummaryControlType = null; 
          
                lMcpBudgetSummaryControlType = getMcpBudgetSummaryControlType(pageContext,webBean);
               
                if(null==lMcpBudgetSummaryControlType){
                    throw new OAException("Excepcion al recuperar el tipo de control presupuestal, Modulo XxgamMcp", OAException.ERROR);
                }else{
                    System.out.println("Debug63: lMcpBudgetSummaryControlType "+lMcpBudgetSummaryControlType);
                    valXxgamMaTotalAmountVsXxgamMcpAvailableFunds(pageContext,webBean,lMcpBudgetSummaryControlType);
                }
          }
          
         } // END Validate Responsability
        } // END Valida perfil de seguridad
       } // END if(null!=strMcpAplicaValVsMaf||!"".equals(strMcpAplicaValVsMaf)){ 
        /** Finaliza segmento de codigo para validar los montos de los anticipos
         * contra los fondos disponibles Modulo Control Presupuestal 07/12/2015 **/
      
         XxGamMAnticiposUtil.exePaymentReqProcessFromRequest(pageContext, 
                                                             webBean, 
                                                             pageContext.getParameter(EVENT_PARAM));

         //Muestra el detalle de la solicitud
         //
         if (XxGamConstantsUtil.SHOW_DETAIL_REQUEST.equals(pageContext.getParameter(EVENT_PARAM))) {
             pageContext.putTransactionValue(XxGamConstantsUtil.CALLED_FROM, 
                                             XxGamConstantsUtil.VIEW_STEP_THREE);
             setForwardAirPlaneDetail(pageContext, webBean);
         } // END Show detail 
      } // END if(null!=pageContext&&null!=webBean){
    
      
      
      
    } // END public void processFormRequest

    /**
     * Inicializan los parametros que se van a enviar al detalle.
     *
     * @param pageContext Contexto de la pagina
     * @param webBean Web bean.
     */
    private void setForwardAirPlaneDetail(OAPageContext pageContext, 
                                          OAWebBean webBean) {

        //VErifica nulidad
        if (pageContext == null || webBean == null)
            return;

        //Inicializa los parametros
        String sURL = null;
        String sParam = null;
        Number nPaymentId = null;

        //Obtiene los parametros y los inicializa
        sParam = pageContext.getParameter(XxGamConstantsUtil.PAYMENT_ID);
        nPaymentId = XxGamMAnticiposUtil.converteNumber(sParam);

        if (nPaymentId != null) {
            boolean isSuccess = 
                XxGamMAnticiposUtil.setCurrentRowDetailPayment(pageContext, 
                                                               webBean, 
                                                               nPaymentId);

            if (isSuccess) {
                sURL = XxGamConstantsUtil.URL_PAGE_OAF + PAGE_DETAIL_AIRPLANE;

                HashMap hmap = null;

                //Inicializa los parametros
                XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                             webBean, hmap, 
                                                             sURL);
            } else {
                throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                  XxGamAOLMessages.GenericType.XXGAM_MAF_DETAIL_IDTOTICKET_ER,
                                                  null, OAException.ERROR, null);
            }
        } else {
            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                              XxGamAOLMessages.GenericType.XXGAM_MAF_DETAIL_ID_NF_ERROR,
                                              null, OAException.ERROR, null);
        }
    }

  /**
     * Metodo que crea el boton que sirve para conectar al modulo de 
     * Solicitud y Compensacion de Fondos - Control Presupuestal
     * @param pageContext
     * @param webBean
     */
  private void createButtonToConectXxgamMcpModule(OAPageContext pageContext, 
                                                  OAWebBean webBean)
  {
  
  String strText = null; 
  strText = pageContext.getMessage("XBOL","XXGAM_MCP_CONNECTION_IT_BTN",null);
  
  System.out.println("Debug41:Mcp Comienza creacion boton en anticipos");
       OATableLayoutBean lTableLayout = (OATableLayoutBean)webBean.findChildRecursive("BarButtons");
       if(null!=lTableLayout){
         System.out.println("Debug42: se encontro el itemStyle lTableLayout");
         OACellFormatBean lCellFormat = (OACellFormatBean)lTableLayout.findChildRecursive("CellBarButton");
         if(null!=lCellFormat){
           System.out.println("Debug43: se encontro el itemStyle lCellFormat");
           OASubmitButtonBean mcpOasb= (OASubmitButtonBean)pageContext.getWebBeanFactory().createWebBean(pageContext,OAWebBeanConstants.BUTTON_SUBMIT_BEAN,null,"mcpToGeneralPGSubmitButton");
           mcpOasb.setID("mcpToGeneralPGSubmitButton");
           mcpOasb.setUINodeName("mcpToGeneralPGSubmitButton");
           mcpOasb.setText(strText/*"Solicitar o compensar fondos"*/);
           mcpOasb.setEvent("mcpToGeneralPGSubmitButton");
           mcpOasb.setRendered(false);
           lCellFormat.addIndexedChild(mcpOasb);
         }
       }
    } // END createButtonToConectXxgamMcpModule

  /**
   * Metodo que llena informacion para realizar validaciones de control presupuestal
   * @param pageContext
   * @param webBean
   */
  private void fillXxgamMcpFundsInfo(OAPageContext pageContext, 
                                     OAWebBean webBean)
  {
    String retval = null; 
         if(null!=pageContext&&null!=webBean){
           XxGamModAntAMImpl ModAntAMImpl = null; 
           Number lExpenseReportID = null;
           Number lParameterID = null;
           oracle.jbo.domain.Date  lInitialDate = null;
           oracle.jbo.domain.Date  lFinalDate = null;
           String lCurrencyCode = null; 
           
           ModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
           if(null!=ModAntAMImpl){
             XxGamMaGeneralReqVOImpl GeneralReqVOImpl = null; 
             XxGamMaGeneralReqVORowImpl GeneralReqVORowImpl = null;
             GeneralReqVOImpl = ModAntAMImpl.getXxGamMaGeneralReqVO1();
             
             RowSetIterator iter = GeneralReqVOImpl.createRowSetIterator(null);
             iter.reset();
             if (iter.hasNext()) {
               Row r = iter.next();
               GeneralReqVORowImpl = (XxGamMaGeneralReqVORowImpl)r;
               if(null!=GeneralReqVORowImpl.getTemplatePayment()&&!"".equals(GeneralReqVORowImpl.getTemplatePayment())){
                lExpenseReportID = GeneralReqVORowImpl.getTemplatePayment();
               }
             }else{
               retval = "NO_DATA_FOUND GeneralReqVO"+ ", Modulo XxgamMcp.";
               throw new OAException(retval, OAException.ERROR);
             }
             // close secondary row set iterator
             iter.closeRowSetIterator();
             
             /***** Comentado por ser codigo deficiente 07/12/2015 ***
             if(GeneralReqVOImpl.getRowCount()>0){
               GeneralReqVORowImpl = (XxGamMaGeneralReqVORowImpl)GeneralReqVOImpl.getCurrentRow();
               lExpenseReportID = GeneralReqVORowImpl.getTemplatePayment();
             }else{
               retval = "NO_DATA_FOUND GeneralReqVO"+ ", Modulo XxgamMcp.";
               throw new OAException(retval, OAException.ERROR);
             }
             *********************************************************/
             
             System.out.println("Debug44: lExpenseReportID "+lExpenseReportID); 
             XxGamMaPaymentReqVOImpl PaymentReqVOImpl = null; 
             XxGamMaPaymentReqVORowImpl PaymentReqVORowImpl = null; 
             PaymentReqVOImpl= ModAntAMImpl.getXxGamMaPaymentReqVO2();
             if(null==retval&&PaymentReqVOImpl.getRowCount()>0){
               RowSetIterator rowSetIter = PaymentReqVOImpl.getRowSetIterator();
               if (rowSetIter != null) {
                 rowSetIter.reset();
                 while (rowSetIter.hasNext()) {
                   Row row = rowSetIter.next();
                   PaymentReqVORowImpl = (XxGamMaPaymentReqVORowImpl)row;
                   if(null!=PaymentReqVORowImpl){
                     lParameterID = PaymentReqVORowImpl.getTypePayment();
                     lInitialDate = PaymentReqVORowImpl.getInitialDate();
                     lFinalDate   = PaymentReqVORowImpl.getFinalDate();
                     lCurrencyCode = PaymentReqVORowImpl.getCurrencyCode();
                     System.out.println("Debug45: lParameterID "+lParameterID); 
                     System.out.println("Debug46: lInitialDate "+lInitialDate); 
                     System.out.println("Debug47: lFinalDate "+lFinalDate); 
                     System.out.println("Debug48: currencyCode "+lCurrencyCode); 
                     if(null!=lExpenseReportID
                       &&null!=lParameterID
                       &&null!=lCurrencyCode){
                         String validateExpenseCategoryVsXxgamMcpArr[] = new String [5];
                         validateExpenseCategoryVsXxgamMcpArr = ModAntAMImpl.validateExpenseCategoryVsXxgamMcp(lExpenseReportID
                                                                                                              ,lParameterID
                                                                                                              ,lInitialDate
                                                                                                              ,lFinalDate
                                                                                                              ,lCurrencyCode); 
                         System.out.println("Debug49:"+validateExpenseCategoryVsXxgamMcpArr[0]);
                         System.out.println("Debug50:"+validateExpenseCategoryVsXxgamMcpArr[1]);
                         System.out.println("Debug50:"+validateExpenseCategoryVsXxgamMcpArr[2]);
                         System.out.println("Debug50:"+validateExpenseCategoryVsXxgamMcpArr[3]);
                        
                         OAMessageStyledTextBean flexConcatenatedBean = (OAMessageStyledTextBean)webBean.findChildRecursive("flexConcatenated");
                         if(null!=flexConcatenatedBean){
                          /** flexConcatenatedBean.setReadOnly(true); **/
                           flexConcatenatedBean.setText(pageContext,"");
                           if(null!=validateExpenseCategoryVsXxgamMcpArr[3]){
                             flexConcatenatedBean.setText(pageContext,validateExpenseCategoryVsXxgamMcpArr[3]);
                            }
                         }
                        
                         XxGamMaVsMcpPeriodsAmountVOImpl MaVsMcpPeriodsAmountVOImpl = null;
                         MaVsMcpPeriodsAmountVOImpl = ModAntAMImpl.getXxGamMaVsMcpPeriodsAmountVO1();
                         
                        if("100".equals(validateExpenseCategoryVsXxgamMcpArr[1])){
                          System.out.println("Omitir ya que no se tiene un flex concatenated para esta categoria");
                          MaVsMcpPeriodsAmountVOImpl.executeQuery(); /** Ejecutar para que no se quede el view object lleno por si primero se elige una categoria que si genere lineas en GL**/
                        }else{
                          if(null==validateExpenseCategoryVsXxgamMcpArr[0]&&"0".equals(validateExpenseCategoryVsXxgamMcpArr[1])){
                            if(null!=MaVsMcpPeriodsAmountVOImpl){
                              MaVsMcpPeriodsAmountVOImpl.executeQuery();
                              pageContext.putSessionValue("sCodeCombID",validateExpenseCategoryVsXxgamMcpArr[2]);
                              pageContext.putParameter("pCodeCombID",validateExpenseCategoryVsXxgamMcpArr[2]);
                              break;  
                            } 
                          }else{
                            System.out.println("Ocurrio un error");
                           retval = validateExpenseCategoryVsXxgamMcpArr[1]+"->"+validateExpenseCategoryVsXxgamMcpArr[0]+ ", Modulo XxgamMcp.";
                            throw new OAException(retval,OAException.ERROR);
                         }
                        } // END if("100".equals(validateExpenseCategoryVsXxgamMcpArr[1])){
                         
                       }else{
                         retval = "No se encontraron los valores para llamar el procedimiento pl/sql que valida informacion de presupuesto "+ ", Modulo XxgamMcp.";
                         throw new OAException(retval, OAException.ERROR);
                       }
                   }
                    
                 } //End While
               }
             }else{
               retval = "NO_DATA_FOUND PaymentReqVO "+ ", Modulo XxgamMcp.";
               throw new OAException(retval, OAException.ERROR);
             }
             
           }else{
             retval =" Excepcion al buscar el modelo de aplicaciones"+ ", Modulo XxgamMcp.";
             throw new OAException(retval, OAException.ERROR);
           }   
           
         }// END if(null!=pageContext&&null!=webBean){
       retval = retval+ ", Modulo XxgamMcp.";
  }

  
  /**
     * Metodo para mostrar el boton que conecta la pantalla
     * de solicitud de anticipos con la pantalla de solicitud y compensacion 
     * de fondos 
     * @param pageContext
     * @param webBean
     * @param isRender
     */
     private void RenderCreateButtonToConectXxgamMcpModule(OAPageContext pageContext, 
                                                           OAWebBean webBean,
                                                           boolean isRender)
     {
       OATableLayoutBean lTableLayout = (OATableLayoutBean)webBean.findChildRecursive("BarButtons");
       if(null!=lTableLayout){
         OACellFormatBean lCellFormat = (OACellFormatBean)lTableLayout.findChildRecursive("CellBarButton");
         if(null!=lCellFormat){
           OASubmitButtonBean mcpOasb = (OASubmitButtonBean)lCellFormat.findChildRecursive("mcpToGeneralPGSubmitButton");
           if(null!=mcpOasb){
             mcpOasb.setRendered(isRender);
           }
         }
       }
       
     } // END private void RenderCreateButtonToConectXxgamMcpModule(

      /**
         * metodo para Inicializar parametros que se enviaran a la pagina
         * de solicitud y compensacion de fondos
         * @param pageContext
         * @param webBean
         */
        private void setForwardXxgamMcpGeneralPG(OAPageContext pageContext, 
                                                 OAWebBean webBean)
        {
          //VErifica nulidad
          if (pageContext == null || webBean == null)
          return;
          //Inicializa los parametros
           HashMap hParameters = new HashMap();
          String sURL = null;
          /** Get lCodeCombID **/
          String lCodeCombIDStr = null;
          String lpCodeCombIDStr = null;
          String lsCodeCombIDStr = null;

          lpCodeCombIDStr = pageContext.getParameter("pCodeCombID");
          lsCodeCombIDStr = (String)pageContext.getSessionValue("sCodeCombID");
          //NLV 
           lCodeCombIDStr = lpCodeCombIDStr!=null?lpCodeCombIDStr:lsCodeCombIDStr;
          
          hParameters.put("pCodeCombId", lCodeCombIDStr /**"18991"**/);
          sURL = "OA.jsp?page=/xxgam/oracle/apps/xbol/mcp/webui/XxgamMcpGeneralPG";
          pageContext.setForwardURL(sURL
                                  , null
                                  ,OAWebBeanConstants.KEEP_MENU_CONTEXT
                                  , null
                                  , hParameters
                                  , true
                                  ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO
                                  ,OAWebBeanConstants.IGNORE_MESSAGES);
              
        } // END setForwardXxgamMcpGeneralPG

    
   /**
      * Metodo que recupera el el tipo de control que se tiene para acumular 
      * los fondos disponibles en general segun el modulo de control presupuestal
      * @param pageContext
      * @param webBean
      * @return
      */
     private String getMcpBudgetSummaryControlType(OAPageContext pageContext, 
                                                   OAWebBean webBean)
     {
       XxGamMaVsMcpBudgetControlVOImpl MaVsMcpBudgetControlVOImpl = null; 
       XxGamMaVsMcpBudgetControlVORowImpl MaVsMcpBudgetControlVORowImpl = null; 
       XxGamModAntAMImpl  ModAntAMImpl = null;
       String retval = null; 
       if(null!=pageContext&&null!=webBean){
       ModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
                if(null!=ModAntAMImpl){
                  MaVsMcpBudgetControlVOImpl = ModAntAMImpl.getXxGamMaVsMcpBudgetControlVO1();
                  
                  if(null!=MaVsMcpBudgetControlVOImpl){
                    MaVsMcpBudgetControlVOImpl.executeQuery();
                  }
                  
                  RowSetIterator iter = MaVsMcpBudgetControlVOImpl.createRowSetIterator(null);
                  iter.reset();
                  if (iter.hasNext()) {
                    Row r = iter.next();
                    MaVsMcpBudgetControlVORowImpl = (XxGamMaVsMcpBudgetControlVORowImpl)r;
                    if(null!=MaVsMcpBudgetControlVORowImpl.getLookupCode()&&!"".equals(MaVsMcpBudgetControlVORowImpl.getLookupCode())){
                      retval = MaVsMcpBudgetControlVORowImpl.getLookupCode();
                    }
                  }
                  // close secondary row set iterator
                  iter.closeRowSetIterator();
                  
                  /**** Comentado por deficiencia de codigo 07/12/2015 ***
                  if(MaVsMcpBudgetControlVOImpl.getRowCount()>0){
                    MaVsMcpBudgetControlVORowImpl = (XxGamMaVsMcpBudgetControlVORowImpl)MaVsMcpBudgetControlVOImpl.first();
                    retval = MaVsMcpBudgetControlVORowImpl.getLookupCode();
                  }
                  ******************************************************/
                  
                }
       }
       
       return retval;
     }

 
  /**
     * Metodo para validar el monto total del anticipo vs
     * los fondos disponibles de la combinacion contable de gasto 
     * asociada a la plantilla del anticipo 
     * @param pageContext
     * @param webBean
     */
    private void valXxgamMaTotalAmountVsXxgamMcpAvailableFunds(OAPageContext pageContext 
                                                              ,OAWebBean webBean
                                                              ,String lMcpBudgetSummaryControlType)
    { 
      String retval; 
      System.out.println("Debug52:Comienza validacion del monto tota vs los fondos disponibles de la combinacion contable asociada a la platilla de reporte de gasto");
      XxGamModAntAMImpl  ModAntAMImpl = null;
      Number lTotalPayment = null;
      Number lTotalFunds = null;
      Number lPaymentAmount = null; 
      Number lPaymentAmountToMXN = null;
      String lPaymentCurrencyCode = null;
      oracle.jbo.domain.Date  lsysdate = null; 
      oracle.jbo.domain.Date  lStartDate = null;
      oracle.jbo.domain.Date  lEndDate = null;
      oracle.jbo.domain.Date  lQuarterStartDate = null; 
      oracle.jbo.domain.Date  lYearStartDate = null; 
      float lSumPaymentAmountFloat = 0;
      float llTotalFundsFloat = 0;
      Number lSumPaymentAmountNumber = new Number(0); 
      NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

      
     DecimalFormat df = new DecimalFormat("$###,###,###,###,##0.00");
     String lSumPaymentAmountStrDf = null; 
     String lTotalFundsStrDf = null; 
      
      
      ModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
             if(null!=ModAntAMImpl){
               
              OADBTransaction txn = ModAntAMImpl.getOADBTransaction();
              lsysdate = txn.getCurrentDBDate();
             
               XxGamMaGeneralReqVOImpl GeneralReqVOImpl = null; 
               XxGamMaGeneralReqVORowImpl GeneralReqVORowImpl = null;
               GeneralReqVOImpl = ModAntAMImpl.getXxGamMaGeneralReqVO1();
         
         if(null!=GeneralReqVOImpl){
           // create secondary row set iterator with system-assigned name
         RowSetIterator iterMaGeneralReqVO = GeneralReqVOImpl.createRowSetIterator(null);
         iterMaGeneralReqVO.reset();
         if(iterMaGeneralReqVO.hasNext()){
              Row r = iterMaGeneralReqVO.next();
              GeneralReqVORowImpl = (XxGamMaGeneralReqVORowImpl)r;
               /*** Se comenta por deficiencia de codigo
               if(GeneralReqVOImpl.getRowCount()>0){
                 GeneralReqVORowImpl = (XxGamMaGeneralReqVORowImpl)GeneralReqVOImpl.getCurrentRow();
                 ******************************************************/
                 lTotalPayment = GeneralReqVORowImpl.getTotalPayment();
                 System.out.println("Debug53:lTotalPayment "+lTotalPayment);
                 /*** Get Total Payment **/
                    XxGamMaPaymentReqVOImpl MaPaymentReqVOImpl = null;
                    XxGamMaPaymentReqVORowImpl MaPaymentReqVORowImpl = null;
                     MaPaymentReqVOImpl = ModAntAMImpl.getXxGamMaPaymentReqVO2();
                     
                     if(null!=MaPaymentReqVOImpl&&MaPaymentReqVOImpl.getRowCount()>0){
                       // create secondary row set iterator with system-assigned name
                       RowSetIterator iterMaPaymentReqVO = MaPaymentReqVOImpl.createRowSetIterator(null);
                       iterMaPaymentReqVO.reset();
                       while (iterMaPaymentReqVO.hasNext()) {
                       Row rowIterMaPaymentReqVO = iterMaPaymentReqVO.next();
                       MaPaymentReqVORowImpl = (XxGamMaPaymentReqVORowImpl)rowIterMaPaymentReqVO;
                         lPaymentCurrencyCode = MaPaymentReqVORowImpl.getCurrencyCode();
                         lPaymentAmount = MaPaymentReqVORowImpl.getAmount();
                         lPaymentAmountToMXN = MaPaymentReqVORowImpl.getAmountMx();
                         System.out.println("Debug61: lPaymentAmount "+lPaymentAmount+", lPaymentCurrencyCode "+lPaymentCurrencyCode+", lPaymentAmountToMXN "+lPaymentAmountToMXN);  
                         //TODO BUG01
                         if("MXN".equals(lPaymentAmountToMXN)){  /** Validacion de la moneda **/
                           lSumPaymentAmountFloat = lSumPaymentAmountFloat + Float.valueOf(lPaymentAmount.toString());
                           lSumPaymentAmountNumber = lSumPaymentAmountNumber.add(lPaymentAmount); 
                           lSumPaymentAmountStrDf = df.format(lSumPaymentAmountNumber.doubleValue()); 
                           System.out.println(" defaultFormat lSumPaymentAmountNumber "+defaultFormat.format(lSumPaymentAmountNumber.doubleValue()));
                         }else{
                           lSumPaymentAmountFloat = lSumPaymentAmountFloat + Float.valueOf(lPaymentAmountToMXN.toString());
                           lSumPaymentAmountNumber = lSumPaymentAmountNumber.add(lPaymentAmountToMXN); 
                           lSumPaymentAmountStrDf = df.format(lSumPaymentAmountNumber.doubleValue()); 
                           System.out.println("defaultFormat lSumPaymentAmountNumber "+defaultFormat.format(lSumPaymentAmountNumber.doubleValue()));
                         }
        
                       // Do something with the current row.
                       }
                       // close secondary row set iterator
                       iterMaPaymentReqVO.closeRowSetIterator();
                       
                       System.out.println("Debug62: lSumPaymentAmountFloat"+lSumPaymentAmountFloat);
                     } // END if(null!=MaPaymentReqVOImpl&&MaPaymentReqVOImpl.getRowCount()>0){
                     else{
                       retval = "NO_DATA_FOUND MaPaymentReqVO"+" Modulo XxgamMcp";
                       throw new OAException(retval, OAException.ERROR);
                     }
                 /*** End Total Payment **/
                 
                 
                 XxGamMaVsMcpPeriodsAmountVOImpl MaVsMcpPeriodsAmountVOImpl = null; 
                 XxGamMaVsMcpPeriodsAmountVORowImpl MaVsMcpPeriodsAmountVORowImpl = null;
                 
                 MaVsMcpPeriodsAmountVOImpl = ModAntAMImpl.getXxGamMaVsMcpPeriodsAmountVO1();
                 
                 if(null!=MaVsMcpPeriodsAmountVOImpl&&MaVsMcpPeriodsAmountVOImpl.getRowCount()>0){
                   System.out.println("Debug54: validar las categorias asociadas a la plantilla que si generan lineas en la poliza de anticipo");
                   // create secondary row set iterator with system-assigned name
                   RowSetIterator iter = MaVsMcpPeriodsAmountVOImpl.createRowSetIterator(null);
                   while(iter.hasNext()){
                     Row iterRow = iter.next();
                     // Do something with the current row.
                     MaVsMcpPeriodsAmountVORowImpl = (XxGamMaVsMcpPeriodsAmountVORowImpl)iterRow;
                      lStartDate = MaVsMcpPeriodsAmountVORowImpl.getStartDate();
                        lEndDate = MaVsMcpPeriodsAmountVORowImpl.getEndDate();
                     lTotalFunds = MaVsMcpPeriodsAmountVORowImpl.getTAmount();
                     lQuarterStartDate = MaVsMcpPeriodsAmountVORowImpl.getQuarterStartDate();
                     
                     lYearStartDate = MaVsMcpPeriodsAmountVORowImpl.getYearStartDate();
                     
                     System.out.println("Debug57: lStartDate "+lStartDate);
                     System.out.println("Debug58: lEndDate "+lEndDate);
                     System.out.println("Debug59: lTotalFunds "+lTotalFunds);
                     System.out.println("Debug59: lQuarterStartDate "+lQuarterStartDate);
                     System.out.println("Debug59: lgetYearStartDate "+lYearStartDate);
                     
                     if("PTD".equals(lMcpBudgetSummaryControlType)){
                       if (lsysdate.dateValue().getTime() >= lStartDate.dateValue().getTime()&&(lsysdate.dateValue().getTime() <= lEndDate.dateValue().getTime())){
                          /** "Debug57: validar el monto total del anticipo vs los fondos disponibles para este periodo que puede cambiar dinamicamente" +
                          "deacuerdo a la configuracion de acumulados en el modulo del control presupuestal" **/
                           llTotalFundsFloat = Float.valueOf(lTotalFunds.toString());
                           lTotalFundsStrDf = df.format(lTotalFunds.doubleValue());
                           System.out.println("defaultFormat lTotalFunds"+defaultFormat.format(lTotalFunds.doubleValue()));
                           break;
                       }
                     } //END if("PTD".equals(lMcpBudgetSummaryControlType)){
                     else if("QTDE".equals(lMcpBudgetSummaryControlType)){
                       if(lsysdate.dateValue().getTime() >= lQuarterStartDate.dateValue().getTime() &&( lsysdate.dateValue().getTime() <= lEndDate.dateValue().getTime() ) ){
                         llTotalFundsFloat = Float.valueOf(lTotalFunds.toString());
                         lTotalFundsStrDf = df.format(lTotalFunds.doubleValue());
                         System.out.println("defaultFormat lTotalFunds"+defaultFormat.format(lTotalFunds.doubleValue()));
                         break;
                       }
                     }else if("YTDE".equals(lMcpBudgetSummaryControlType)){
                         if( lsysdate.dateValue().getTime() >= lYearStartDate.dateValue().getTime() &&( lsysdate.dateValue().getTime() <= lEndDate.dateValue().getTime() ) ){
                       /**
                        * Ajsute para obtener fondos disponibles correctamente
                        * *if( lsysdate.dateValue().getTime() >= lQuarterStartDate.dateValue().getTime() &&( lsysdate.dateValue().getTime() <= lEndDate.dateValue().getTime() ) ){*
                        * */
                         llTotalFundsFloat = Float.valueOf(lTotalFunds.toString());
                         lTotalFundsStrDf = df.format(lTotalFunds.doubleValue());
                         System.out.println("defaultFormat lTotalFunds"+defaultFormat.format(lTotalFunds.doubleValue()));
                         break;
                       }
                     
                     }  //END else if("YTDE".equals(lMcpBudgetSummaryControlType)){
                     else{
                      System.out.println("Debug64: no se encontro el tipo de control presupuestal ");
                      retval = "No se encontro el tipo de control  presupuestal "+" ,Modulo XxgamMcp";
                       throw new OAException(retval, OAException.ERROR);
                     }
                     
                   }   // END while(iter.hasNext()){
                   // close secondary row set iterator
                   iter.closeRowSetIterator();
                   
                 }else{
                   System.out.println("Debug54: NoAplica - validar las categorias asociadas a la plantilla que si generan lineas en la poliza de anticipo");
                 }
                 
               } // END if(iterMaGeneralReqVO.hasNext()){
               else{
                 retval = "NO_DATA_FOUND GeneralReqVO"+", modulo XxgamMcp";
               }
         
           // close secondary row set iterator      
         iterMaGeneralReqVO.closeRowSetIterator();
         } // END if(null!=GeneralReqVOImpl){
         //TODO GNOSIS2305 06 Aqui se hace la comprobacion del ctrl presupuestal con el anticipo
         if(lSumPaymentAmountFloat>llTotalFundsFloat){
           
           System.out.println("Debug65: lSumPaymentAmountFloat "+lSumPaymentAmountFloat);       
           System.out.println("Debug66: llTotalFundsFloat "+llTotalFundsFloat);  
           System.out.println("Debug65.1: lSumPaymentAmountStrDf "+lSumPaymentAmountStrDf);       
           System.out.println("Debug66.1: lTotalFundsStrDf "+lTotalFundsStrDf);  
           
           retval ="El monto total del anticipo:"+lSumPaymentAmountStrDf+" MXN sobrepasa los fondos disponibles:"+lTotalFundsStrDf+" MXN ";
           
           pageContext.putParameter("pRequestFundsOver","valueRequestFundsOver");
           pageContext.putSessionValue("sRequestFundsOver","valueRequestFundsOver");
           
            OAException localOAException = new OAException( retval, OAException.ERROR);
            OADialogPage localOADialogPage = new OADialogPage(OAException.ERROR , localOAException, null, "", null);
            localOADialogPage.setOkButtonToPost(true);
            localOADialogPage.setOkButtonLabel("Ok");
            localOADialogPage.setPostToCallingPage(true);
            Hashtable localHashtable = new Hashtable(1);
            localOADialogPage.setFormParameters(localHashtable);
            pageContext.redirectToDialogPage(localOADialogPage);
                                  
          /** throw new OAException(retval, OAException.ERROR); **/
         
         }
       
       } // END if(null!=ModAntAMImpl){
       else{
         retval = " Excepcion al encontrar el modelo de aplicacion"+", modulo XxgamMcp";
         throw new OAException(retval, OAException.ERROR);
       }
       
   }// END private void valXxgamMaTotalAmountVsXxgamMcpAvailableFunds
   
 
}
