package xxgam.oracle.apps.xbol.maf.webui;

import com.sun.java.util.collections.HashMap;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import xxgam.oracle.apps.xbol.maf.utils.XxGamAOLMessages;
import xxgam.oracle.apps.xbol.maf.utils.XxGamConstantsUtil;
import xxgam.oracle.apps.xbol.maf.utils.XxGamMAnticiposUtil;


/**
 * Clase controlador, para proceso interno de reserva de fondos de la página de Revisión y Propuesta de Pago.
 * Página definida por el XML XxGamPaymentReqReviewPG
 * 
 * @author Aldo López de Nava.
 */
public class XxGamMaProcessingPageCO extends OAControllerImpl{
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");
    
    /**
     * Procedure to handle form submissions for form elements in
     * a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        reservedFounds(pageContext, webBean);
    }

    /**
     * Ejecuta el proceso de reservar fondos
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void reservedFounds(OAPageContext pageContext, OAWebBean webBean){
        // Se forzo el seteo del TemplatePayment ya que se perdia en la navegación de las pantallas.
        // MAGG Julio 3, 2014
        XxGamMAnticiposUtil.forceSetPaymentId(pageContext, webBean);
        
        HashMap hmap = null;
        String msgLoadPage = null;
        
        Boolean isAllValidLine = XxGamMAnticiposUtil.refreshAllValidationByLine(pageContext, webBean);
        if(isAllValidLine == null){
            isAllValidLine = false;
        }
        
        String errorMsg = XxGamMAnticiposUtil.savePaymentRequest(pageContext, webBean);
        if(errorMsg == null){
            if (isAllValidLine) {
                boolean isValid = XxGamMAnticiposUtil.isValidToReservedFound(pageContext, webBean);
                
                    if(isValid){
                        boolean isSuccess = false;
                        String msgError = null;
                        try {
                            msgError = XxGamMAnticiposUtil.getReservedFounds(pageContext,
                                                                  webBean);
                            if(msgError != null){
                                isSuccess = false;
                            }else{
                                isSuccess = true;
                            }
                        } catch (Exception exception) {
                            isSuccess = false;
                        }
                    
                        if(isSuccess){
                                    
                            String strNumberPayment = XxGamMAnticiposUtil.getNumberPayment(pageContext, webBean);
                            if(strNumberPayment == null){
                                strNumberPayment = "";
                            }
                            
                            MessageToken[] tokens = {new MessageToken("NUMBER_PAYMENT", strNumberPayment)};
                            msgLoadPage = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_RFUNDS_CONF_INFO, tokens);
                            
                            hmap = new HashMap();
                            hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_INFO,
                                     "NO");
                        }else{
                            //Deshace los cambios por efecto de reserva de fondos
                            XxGamMAnticiposUtil.setRollback(pageContext, webBean);
                            msgLoadPage = msgError;
                            hmap = new HashMap();
                            hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR,
                                     msgLoadPage);
                        }
                    }else{
                        XxGamMAnticiposUtil.setRollback(pageContext, webBean);
                        // TODO GNOSISHCM RB
                        String strNumberPayment = XxGamMAnticiposUtil.getNumberPayment(pageContext, webBean);
                        if(strNumberPayment == null){
                            strNumberPayment = "";
                        }
                        
                        MessageToken[] tokens = {new MessageToken("NUMBER_PAYMENT", strNumberPayment)};
                        msgLoadPage = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_RFUNDS_CONF_ERROR1, tokens);
                        
                        hmap = new HashMap();
                        hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR,
                                 msgLoadPage);
                    }
                }else{
                    XxGamMAnticiposUtil.setRollback(pageContext, webBean);
                    
                    String strNumberPayment = XxGamMAnticiposUtil.getNumberPayment(pageContext, webBean);
                    if(strNumberPayment == null){
                        strNumberPayment = "";
                    }
                    
                    MessageToken[] tokens = {new MessageToken("NUMBER_PAYMENT", strNumberPayment)};
                    msgLoadPage = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_RFUNDS_CONF_ERROR2, tokens);

                    hmap = new HashMap();
                    hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR,
                             msgLoadPage);
                }
        }else{
            XxGamMAnticiposUtil.setRollback(pageContext, webBean);
            
            MessageToken[] tokens = {new MessageToken("MSG_ERROR", errorMsg)};
            msgLoadPage = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_RFUNDS_CONF_ERROR3, tokens);
            hmap = new HashMap();
            hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR,
                     msgLoadPage);
        }
        //Redirecciona la navegacion a la pantalla inicial del empleado
        String urlReturn = XxGamConstantsUtil.URL_PAGE_OAF;
        urlReturn += "XxGamPaymentInitAdvancePG";
        XxGamMAnticiposUtil.setForwardWhitParameters(pageContext,
                                                     webBean, hmap,
                                                     urlReturn);
    }
}
