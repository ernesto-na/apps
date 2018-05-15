package xxgam.oracle.apps.xbol.maf.utils;

import com.sun.java.util.collections.HashMap;
import java.text.DecimalFormat;
import java.io.ByteArrayOutputStream;

import java.math.BigDecimal;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAFwkConstants;
import oracle.apps.fnd.framework.webui.OADialogPage;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAProcessingPage;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OABodyBean;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAQueryBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;

import oracle.cabo.ui.data.DataObject;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import oracle.xml.parser.v2.XMLNode;

import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaCategoriaGastoLovVOImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaCategoriaGastoLovVORowImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaChiefByPositionIdLovVORowImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaCurrencyLovVORowImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaResponsibilityAppLovVORowImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaTypePaymentLovVORowImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaUserDataLovVORowImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamModAntLovAMImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaFlightInf0VOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaFlightInf0VORowImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaGeneralReqVOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaGeneralReqVORowImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaPasajerosInfoVOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaPaymentReqVOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaPaymentReqVORowImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaTicketPVOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaTicketPVORowImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamModAntAMImpl;


//import oracle.apps.xdo.oa.schema.server.TemplateHelper;


/**
 * La clase <class>XxGamMAnticiposUtil</class> es utilizada como
 * utilerias de la aplicacion
 *
 * @author Manuel Guinto.
 */
public class XxGamMAnticiposUtil {

    /**
     * Constructor por defecto.
     */
    public XxGamMAnticiposUtil() {
    }

    /**
     * Inicializa los valores la creacion de una nueva solicitud de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OA
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OA
     * @return devuelve true cuando todas las validasciones fueron exitosas
     */
    public static boolean initNewPaymentRequest(OAPageContext pageContext,
                                                OAWebBean webBean) {
        boolean isInitSucess = false;
      System.out.println("Comienza initNewPaymentRequest Capa Utilitarios"); 
        XxGamModAntAMImpl am = getApplicationModule(pageContext, webBean);
        if (am != null && pageContext != null) {
            String userName = pageContext.getUserName();
           /** Comentado para llamar un metodo sobrecargado **/
           /** isInitSucess = am.initNewPaymentRequest(userName, new Number(pageContext.getResponsibilityId()),pageContext); **/
           isInitSucess = am.initNewPaymentRequest(userName, new Number(pageContext.getResponsibilityId()),pageContext,webBean);
         }
        System.out.println("Finaliza initNewPaymentRequest Capa Utilitarios isInitSucess:"+isInitSucess); 
        return isInitSucess;
    }

    /**
     * Crea un nuevo registro para el detalle de la solicitud de anticipo, para capturar
     * las lineas de anticipo que especifican el tipo de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OA
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OA
     */
    public static void createNewPaymentRequestDetail(OAPageContext pageContext,
                                                     OAWebBean webBean) {
        XxGamModAntAMImpl am = getApplicationModule(pageContext, webBean);
        if (am != null) {
            am.createNewPaymentRequestDetail();
        }
    }

    /**
     * Obtiene el AM de modulo de anticipos.
     *
     * @param pageContext Pagina de contexto.
     * @param webBean Web bean.
     */
    public static void getXxGamModAntAM(OAPageContext pageContext,
                                        OAWebBean webBean) {

        //Verifica nulidad
        System.out.println("Comienza Capa Utilitarios getXxGamModAntAM"); 
        if (pageContext == null || webBean == null)
            return;

        XxGamModAntAMImpl amXxGamMod = null;
        Number nUserId = null;
        String sUserName = null;

        amXxGamMod = getApplicationModule(pageContext, webBean);
        sUserName = pageContext.getUserName();

      System.out.println("Informacion Capa Utilitarios getXxGamModAntAM sUserName: "+sUserName);
        //Verifica nulidad
        if (amXxGamMod == null)
            return;

        try {
            nUserId = getPersonIdByUserName(pageContext, webBean, sUserName, XxGamConstantsUtil.VENDOR_TYPE_EMPLOYEE);
          System.out.println("Informacion Capa Utilitarios getXxGamModAntAM nUserId: "+nUserId);    
            
            if(null==nUserId){
              
              HashMap hParameters = new HashMap();
              
              hParameters.put("NoDataFoundPersonId","No se encontro empleado para este usuario favor de contactar al administrador del sistema"); 
               String urlBase = XxGamConstantsUtil.URL_PAGE_OAF;
               String sURL = urlBase+"XxGamMaBlankPagePG";
               pageContext.setForwardURL(sURL
                                      ,null //  not necessary with KEEP_MENU_CONTEXT
                                      ,OAWebBeanConstants.KEEP_MENU_CONTEXT
                                      ,null // No need to specify since we�re keeping menu context
                                      ,hParameters
                                      ,true  //retain AM 
                                      ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO
                                      ,OAWebBeanConstants.IGNORE_MESSAGES);
            }
            
            amXxGamMod.executeQueryAdvanceReq(nUserId,pageContext, webBean);
        } catch (Exception exception) {
          XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"EXCEPTION1 AL "+exception.getMessage(),pageContext,webBean);
                    
                    HashMap hParameters = new HashMap();
                    hParameters.put("NoDataFoundPG","NoDataFoundGeneralPage");
                    String urlBase = XxGamConstantsUtil.URL_PAGE_OAF;
                    String sURL = urlBase+"XxGamMaBlankPagePG";
                    pageContext.setForwardURL(sURL
                                             ,null //  not necessary with KEEP_MENU_CONTEXT
                                             ,OAWebBeanConstants.KEEP_MENU_CONTEXT
                                             ,null // No need to specify since we�re keeping menu context
                                             ,hParameters
                                             ,true  //retain AM 
                                             ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO
                                             ,OAWebBeanConstants.IGNORE_MESSAGES);
                    /**
                      throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                        XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_NF_ERROR,
                                                        null, OAException.ERROR, null);  **/
        }
      System.out.println("Finaliza Capa Utilitarios getXxGamModAntAM"); 
    }

    /**
     * Obtiene la clave primaria de la persona filtrado por usuario.
     *
     * @param pageContext Pagina del contexto.
     * @param webBean web bean.
     * @param userName Nombre de usuario.
     * @param vendorType Tipo de usuairo.
     *
     * @return Clave primaria del empleado.
     */
    public static Number getPersonIdByUserName(OAPageContext pageContext,
                                               OAWebBean webBean,
                                               String userName,
                                               String vendorType) {

        //Verifica nulidad
        if (userName == null || vendorType == null)
            return null;

        XxGamModAntAMImpl amXxGamMod = null;
        XxGamModAntLovAMImpl amXxGamModAntLovAM = null;
        Number nUserId = null;
        String sUserName = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);

        //Obtiene el nombre de usuario
        sUserName = pageContext.getUserName();

        try {

            //Ejecuta el query de solicitud de anticipos.
            amXxGamModAntLovAM =
                    (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();
            nUserId = amXxGamModAntLovAM.getPersonId(sUserName, vendorType);

        } catch (Exception exception) {
            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                              XxGamAOLMessages.GenericType.XXGAM_MAF_USRDAT_NF_ERROR,
                                              null, OAException.ERROR, null);
        }

        //Regresa el usuario
        return nUserId;
    }

    /**
     * Obtiene el AM.
     *
     * @param pageContext Pagina del contexto.
     * @param webBean web bean.
     * @return application module.
     */
    public static XxGamModAntAMImpl getApplicationModule(OAPageContext pageContext,
                                                         OAWebBean webBean) {

        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return null;

        //Obtiene el AM
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod =
                (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);

        //Regresa la respuesta
        return amXxGamMod;
    }

    /**
     * Obtiene el AM de las LOV.
     *
     * @param pageContext Pagina del contexto.
     * @param webBean web bean.
     * @return application module.
     */
    public static XxGamModAntLovAMImpl getApplicationModuleLov(OAPageContext pageContext,
                                                               OAWebBean webBean) {

        //Verifica nulidad
        if (webBean == null)
            return null;

        //Obtiene el AM
        XxGamModAntLovAMImpl amXxGamModLov = null;
        amXxGamModLov =
                (XxGamModAntLovAMImpl)pageContext.getApplicationModule(webBean);

        //Regresa la respuesta
        return amXxGamModLov;
    }


    /**
     * Inicializan los parametros que se van a enviar al detalle.
     * @param pageContext Pagina del contexto.
     * @param webBean web bean.
     * @param hParameters Parametros de la pagina
     */
    public static void setForwardWhitParameters(OAPageContext pageContext,
                                                OAWebBean webBean,
                                                com.sun.java.util.collections.HashMap hParameters,
                                                String sURL) {

        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return;

        //Redirecciona al detalle de la solicitud
        pageContext.setForwardURL(sURL, null,
                                  OAWebBeanConstants.KEEP_MENU_CONTEXT, null,
                                  hParameters, true,
                                  OAWebBeanConstants.ADD_BREAD_CRUMB_NO,
                                  OAWebBeanConstants.IGNORE_MESSAGES);
    }

    /**
     * Duplica el registro de la solicitud.
     *
     * @param pageContext Pagina de contexto.
     * @param webBean web bean.
     * @param sSolicitudId SolicitudId.
     */
    public static void duplicateRequest(OAPageContext pageContext,
                                        OAWebBean webBean,
                                        String sSolicitudId) {

        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return;

        //Declaracion de los recursos
        XxGamModAntAMImpl amXxGamMod = null;

        Number nSolicitud = null;

        //Controla cualquier excepcion ocurrida
        try {
            nSolicitud = converteNumber(sSolicitudId);

            //Obtiene el AM y ejecuta la accion
            amXxGamMod = getApplicationModule(pageContext, webBean);
            amXxGamMod.duplicateRequest(pageContext,webBean, nSolicitud);

        } catch (Exception exception) {
            XxGamMAnticiposUtil.setRollback(pageContext, webBean);
            //Propaga el error generado
            throw new OAException("Error al duplicar los datos de la solicitud",
                                  OAException.ERROR);
        }

    }

    /**
     * Ejecuta el commit.
     * 
     * @param pageContext pagina de contexto.
     * @param webBean web bean
     * @return devuelve nullo cuando el evento commit se realiza correctamente o una descripcion del error
     */
    public static String setCommit(OAPageContext pageContext,
                                 OAWebBean webBean) {
        
        String errorMsg = null;
        //Verifica nulidad
        if (pageContext == null || webBean == null)
            errorMsg = "Par\u00E1metros de p\u00E1gina nullos";

        //Obtiene el AM
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);
        //Ejecuta el commit
        try {
            amXxGamMod.executeCommit();
            errorMsg = null;
        } catch (Exception exception) {
        
            if(exception != null){
                if(exception.getMessage() != null){
                    errorMsg = exception.getMessage();
                }else{
                    if(exception.getClass() != null){
                        errorMsg = exception.getClass().toString();    
                    }else{
                        errorMsg = "Ocurre Exception";
                    }
                }
            }
        }
        
        return errorMsg;
    }

    /**
     * Ejecuta el rollback.
     *
     * @param pageContext pagina de contexto.
     * @param webBean web bean
     */
    public static void setRollback(OAPageContext pageContext,
                                   OAWebBean webBean) {

        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return;

        //Obtiene el AM
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);

        //Ejecuta el rollback
        amXxGamMod.executeRollback();
    }

    /**
     * Convierte el objeto a number.
     *
     * @param object Valor a convertir.
     * @return number.
     */
    public static Number converteNumber(Object object) {

        //Verifica nulidad
        if (object == null || object.equals(""))
            return null;

        Number nNumber = null;

        try {

            //Convierte el objeto a number.
            nNumber = new Number(object);
        } catch (SQLException e) {
            nNumber = null;
        }

        //Regresa la respuesta
        return nNumber;
    }

    /**
     * Obtiene el user id del usuario logueado.
     *
     * @param pageContext pagina del contexto
     * @param webBean web bean
     * @return clave del usuario
     */
    public static Number getUserId(OAPageContext pageContext,
                                   OAWebBean webBean) {

        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return null;

        Number nUserId = null;
        int iUserId = 0;

        try {

            //Obtiene el user id
            iUserId = pageContext.getUserId();
            nUserId = new Number(iUserId);

        } catch (Exception exception) {

            //Propaga la excepcion
            throw new OAException("No es posible obtener el usuario logueado en la instancia",
                                  OAException.ERROR);
        }

        //Regresa la respuesta.
        return nUserId;

    }

    /**
     * Obtiene la fecha actual.
     *
     * @return Fecha actual
     */
    public static oracle.jbo.domain.Date getFechaActual() {

        //Declara los recursos
        Timestamp timeStamp = null;
        oracle.jbo.domain.Date fechaActualOracle = null;

        try {
            //Obtiene la fecha actual.
            timeStamp = new Timestamp(System.currentTimeMillis());
            fechaActualOracle = new oracle.jbo.domain.Date(timeStamp);

        } catch (Exception exception) {

            //Propaga la excepcion
            throw new OAException("No es posible obtener la fecha actual",
                                  OAException.ERROR);

        }

        //Regresa la fecha.
        return fechaActualOracle;

    }


    /**
     * Cambia el estatus de la solicitud.
     * 
     * @param pageContext contexto de la pagina.
     * @param webBean web bean.
     * @param sStatus estatus nuevo.
     * @param nRequestId clave de la solicitud
     * @param costCenter contiene el id de codigo de combinacion contable
     * @param stype contiene el tipo para reserva de fondos
     * @return devuleve true para una ejecucion exitosa y false en caso contrario
     */
    public static boolean setStatusRequestReverseFunds(OAPageContext pageContext,
                                        OAWebBean webBean, String sStatus,
                                        Number nRequestId, Number costCenter,
                                        String stype) {
        boolean isSuccess = false;
        //Verifica nulidad
        if (pageContext == null || webBean == null || nRequestId == null ||
            sStatus == null || costCenter == null)   
            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                          XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_STATUS_MOD_ERROR,
                                          null, OAException.WARNING, null);

        //Obtiene el AM
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);
        if(amXxGamMod != null){
            isSuccess = amXxGamMod.setStatusRequestReverseFunds(nRequestId, sStatus, costCenter, stype);    
        }
        return isSuccess;
    }

     /**
     * Genera un mensaje de confirmaci�n.
     * @param pageContext Contexto de la pagina.
     * @param sMessage Mensaje
     * @param bTypeMessage tipo de mensaje
     * @param params Hashtable
     */
    public static void setMessageDialog(OAPageContext pageContext,
                                        String sMessage, byte bTypeMessage,
                                        Hashtable params) {

        //Genera el mensaje
        OAException oaException = null;
        oaException = new OAException(sMessage);

        //Crea el dialog
        OADialogPage oaDialogPage = null;
        oaDialogPage =
                new OADialogPage(bTypeMessage, oaException, null, "", "");

        oaDialogPage.setOkButtonItemName("YesButton");
        oaDialogPage.setNoButtonItemName("NoButton");
        oaDialogPage.setOkButtonToPost(true);
        oaDialogPage.setNoButtonToPost(true);
        oaDialogPage.setRetainAMValue(true);
        oaDialogPage.setPostToCallingPage(true);
        oaDialogPage.setFormParameters(params);


        //Redirecciona a la siguiente pagina
        pageContext.redirectToDialogPage(oaDialogPage);
    }

    /**
     * Obtiene el id de persona por medio del nombre de usuario
     * @param userName contiene el nombre de usuario
     * @param pageContext contiene el objeto OAPageContext
     * @param webBean contiene el objeto OAWebBean
     * @return devuelve el id de persona
     */
    public static Number getPersonIdByUserName(String userName,
                                               OAPageContext pageContext,
                                               OAWebBean webBean) {

        Number numPersonId = null;
        if (pageContext != null && webBean != null && userName != null) {
            XxGamModAntLovAMImpl amLov =
                getApplicationModuleLov(pageContext, webBean);
            if (amLov != null) {
                numPersonId = amLov.getPersonIdByUserName(userName);
            }
        }
        return numPersonId;
    }

    /**
     * Ejecuta el procedimiento de inicializacion la creacion del detalle de la solicitud de anticipo, configurando
     * cada linea de anticipo que contendra la solicitud de anticipos
     * @param pageContext contiene el objeto OAPageContext
     * @param webBean contiene el objeto OAWebBean
     * @return devuelve true cuando todas las validaciones fueron exitosas
     */
    public static boolean initTypePaymentRequestDetail(OAPageContext pageContext,
                                                      OAWebBean webBean) {
        boolean isPaymentReqSucess = false;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if (amXxGamMod != null) {
                XxGamMaGeneralReqVOImpl voGeneralReq = null;
                voGeneralReq = amXxGamMod.getXxGamMaGeneralReqVO1();

                if (voGeneralReq != null) {
                    Row row = voGeneralReq.getCurrentRow();
                    if (row != null) {
                        XxGamMaGeneralReqVORowImpl generalReqRow =
                            (XxGamMaGeneralReqVORowImpl)row;
                        isPaymentReqSucess = amXxGamMod.initTypePaymentRequestDetail(generalReqRow.getTemplatePayment());


                    }
                }
            }
        }
        return isPaymentReqSucess;
    }

    /**
     * Ejecuta el procedimiento de inicializacion para editar el detalle de la solicitud de anticipo, configurando
     * cada linea de anticipo que contendra la solicitud de anticipos
     * @param pageContext contiene el objeto OAPageContext
     * @param webBean contiene el objeto OAWebBean
     * @return codeLang
     */
    public static String initEditPaymentRequestDetail(OAPageContext pageContext,
                                                    OAWebBean webBean) {
        String codeLang = "";
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if (amXxGamMod != null) {
                codeLang = amXxGamMod.initEditPaymentRequestDetail();
            }
        }
        return codeLang;
    }

     /**
     * Asigna el valor de id de persona a la variable bin de la lov de centro de costos
     * @param personId Number
     * @param responsability String
     * @param pageContext contiene el objeto OAPageContext
     * @param webBean contiene el objeto OAWebBean
     */
    public static void searchCostCenterLovVO1(Number personId,
                                              String responsability,
                                              OAPageContext pageContext,
                                              OAWebBean webBean) {

        if (pageContext != null && webBean != null) {
            XxGamModAntLovAMImpl am =
                getApplicationModuleLov(pageContext, webBean);

            String vcLookupType = null;
            if (XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE.equals(responsability)) {
                vcLookupType =
                        XxGamConstantsUtil.LOOKUP_TYPE_TARJETA_VIRTUAL_COMISION;
            } else {
                if (XxGamConstantsUtil.RESPONSABILITY_FRANCHISE.equals(responsability)) {
                    vcLookupType =
                            XxGamConstantsUtil.LOOKUP_TYPE_TARJETA_VIRTUAL_FRANQUICIAS;
                }
            }

            if (am != null && vcLookupType != null) {
                if (am.getXxGamMaCostCenterByPersonIdLovVO1() != null) {
                    am.getXxGamMaCostCenterByPersonIdLovVO1().searchCostCenterByPersonId(personId,
                                                                                         vcLookupType);
                }
            }
        }
    }

    /**
     * Busca y establece el registro actual de la solicitud de anticipo por id
     * @param idRequest contiene el id de la solicitud de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OA
     * @param webBean contiene el objeto OAWebBean procedente dela pagina OA
     * @return devuelve false si el registro no es encontrado y verdadero si la solicitud es encontrada
     */
    public static boolean setCurrentRowPaymentReqById(Number idRequest,
                                                      OAPageContext pageContext,
                                                      OAWebBean webBean) {
        boolean isFound = false;
        if (idRequest != null && pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if (amXxGamMod != null) {

                isFound = amXxGamMod.setCurrentRowPaymentReqById(idRequest);
            }
        }
        return isFound;
    }

    /**
     * Configura y asigna los valores descriptivos de los registros de una solicitud de anticipo para
     * su consulta de solo lectura
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OA
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OA
     * @return devuelve true en caso de inicializacion correcta y false en caso contrario
     */
    public static boolean setPaymentReqDescriptionsReadOnly(OAPageContext pageContext,
                                                         OAWebBean webBean) {
        
        System.out.println("Ingresa XxGamAnticiposUtil.setPaymentReqDescriptionsReadOnly ");
        boolean isSuccess = false;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if (amXxGamMod != null) {
            
                isSuccess = setIsApproverSysInGeneral(pageContext, webBean);
                
                if(isSuccess){

                    XxGamMaGeneralReqVOImpl voGeneralReq = null;
                    voGeneralReq = amXxGamMod.getXxGamMaGeneralReqVO1();

                    if (voGeneralReq != null) {
                        Row row = voGeneralReq.getCurrentRow();
                        if (row != null) {
                            XxGamMaGeneralReqVORowImpl generalReqRow =
                                (XxGamMaGeneralReqVORowImpl)row;
                            
                            if(generalReqRow != null){
                            
                                if(!generalReqRow.getIsApproverBySys() && generalReqRow.getEmployeeId() != null){
                                    
                                    XxGamMaUserDataLovVORowImpl userDataFound = null;
                                    
                                    XxGamModAntLovAMImpl amLov = (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();
                                    if(amLov != null){
                                        userDataFound = amLov.getUserDataByPersonId(generalReqRow.getEmployeeId());
                                        
                                        if(userDataFound != null){
                                            Number userId = userDataFound.getUserId();
                                            isSuccess = initApproverHierarchyLov(pageContext, webBean, userId);
                                        }
                                    }
                                }else{
                                    isSuccess = true;
                                }
                            }
                        }
                    }
                    
                    Number responsibilityId = null;
                    responsibilityId = new Number(pageContext.getResponsibilityId());
                    
                    if(isSuccess){
                        isSuccess = amXxGamMod.setPaymentReqDescriptionsReadOnly(responsibilityId, pageContext, webBean);        
                    }
                }
            }
        }
        return isSuccess;
    }

    /**
     * Reinicia los resultados del query.
     *
     * @param pageContext contexto de la pagina.
     * @param webBean Web bean,
     * @param sQueryRN Nombre de la región
     */
    public static void resetQueryRN(OAPageContext pageContext,
                                    OAWebBean webBean, String sQueryRN) {

        //Verifica nulidad
         if (sQueryRN == null || pageContext == null || webBean == null)
            return;

        //Reinicia la tabla de resultados.
        OAQueryBean queryBean =
            (OAQueryBean)webBean.findChildRecursive(sQueryRN);

        if (queryBean == null)
            return;
        queryBean.clearSearchPersistenceCache(pageContext);

    }

     /**
     * searchRequest
     * @param pageContext Pagina de contexto.
     * @param webBean Web bean.
     * @param idSolicitud clave del registro.
     * @param sName String
     * @param nCostCenter String
     * @param nAdvanceTemplate String
     * @param purpose String
     * @param dStartDate Date
     * @param dEndDate Date
     * @param sTypeReuqest String
     */
    public static void searchRequest(OAPageContext pageContext,
                                     OAWebBean webBean, String idSolicitud,
                                     String sName, String nCostCenter,
                                     String nAdvanceTemplate, String purpose,
                                     Date dStartDate, Date dEndDate,
                                     String sTypeReuqest) {

        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return;

        //Obtiene la solicitud
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);

        amXxGamMod.searchRequest(idSolicitud, sName, nCostCenter,
                                 nAdvanceTemplate, purpose, dStartDate,
                                 dEndDate, sTypeReuqest);
    }

    /**
     * Convierte el objeto a number.
     * @param pageContext Pagina de contexto.
     * @param object Valor a convertir.
     * @return number.
     */
    public static oracle.jbo.domain.Date converteDate(OAPageContext pageContext,
                                                      Object object) {

        //Verifica nulidad
        if (object == null || object.equals(""))
            return null;

        oracle.jbo.domain.Date dDate = null;
        java.sql.Date jdFecha = null;

        try {

            //Convierte el objeto a number.
            jdFecha =
                    pageContext.getOANLSServices().stringToDate(object.toString());
            dDate =
new oracle.jbo.domain.Date(new Timestamp(jdFecha.getTime()));
        } catch (Exception e) {
             dDate = null;
        }
        //Regresa la respuesta
        return dDate;
    }


    /**
     * Llama al metodo del AM createRowFlight para iniciar el proceso de creacion de registros
     * de la Tabla de informaciÃ³n de vuelos.
     * @param pageContext Pagina de contexto.
     * @param webBean Web bean.
     */
    public static

    void createRowFlight(OAPageContext pageContext, OAWebBean webBean) {
        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return;
        //Declaracion de los recursos
        XxGamModAntAMImpl amXxGamMod = null;
        //Controla cualquier excepcion ocurrida
        try {
            //Obtiene el AM y ejecuta la accion
            amXxGamMod = getApplicationModule(pageContext, webBean);
            XxGamMaTicketPVOImpl ticket = amXxGamMod.getXxGamMaTicketPVO3();
            
            if(ticket != null){
                
                XxGamMaTicketPVORowImpl rowim = (XxGamMaTicketPVORowImpl) ticket.getCurrentRow();
                if(rowim != null){
                    amXxGamMod.createFligthRowByEmployee();    
                }
            }
            
        } catch (Exception exception) {
            //Propaga el error generado
            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                               XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_CREATE_F_ERROR,
                                               null, OAException.ERROR, null);
        }
    }


    /**
     * Llama al metodo del AM createRowFlight para iniciar el proceso de creacion de registros
     * de la Tabla de informaciÃ³n de vuelos.
     * @param pageContext Pagina de contexto.
     * @param webBean Web bean.
     */
    public static void createRowFlightForFranchise(OAPageContext pageContext,
                                     OAWebBean webBean) {
        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return;
        //Declaracion de los recursos
        XxGamModAntAMImpl amXxGamMod = null;
        //Controla cualquier excepcion ocurrida
        try {
            //Obtiene el AM y ejecuta la accion
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
            
                XxGamMaGeneralReqVOImpl generalImpl = amXxGamMod.getXxGamMaGeneralReqVO1();
                if(generalImpl != null){
                    if(generalImpl.getCurrentRow() != null){
                        amXxGamMod.createFligthRowByFranchise();        
                    }
                }
            }
            
        } catch (Exception exception) {
            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                               XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_CREATE_F_ERROR,
                                               null, OAException.ERROR, null);
        }
    }


    /**
     * Busca solicitud seleccionada.
     *
     * @param pageContext Contexto de la pagina.
     * @param webBean Web bean,
     * @param nRequestId Clave de la solicitud.
     * @return isRows
     */
    public static boolean searchRequest(OAPageContext pageContext,
                                        OAWebBean webBean, Number nRequestId) {

        //Verifica nulidad
        if (nRequestId == null || pageContext == null || webBean == null)
            return false;

        //Obtiene el AM
        boolean isRows = false;
        try {

            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            isRows = amXxGamMod.searchRequests(pageContext,webBean,nRequestId);
        } catch (Exception exception) {
            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                              XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_NF_ERROR,
                                              null, OAException.ERROR, null);
        }
        return isRows;
    }

    /**
     * Busca el registro por id.
     * @param pageContext Pagina de contexto.
     * @param webBean Web bean
     * @param nPayment Clave primaria.
     */
    public static void searchPayment(OAPageContext pageContext,
                                     OAWebBean webBean, Number nPayment) {

        //Verifica nulidad
        if (pageContext == null || webBean == null || nPayment == null)
            return;

        try {
            //Busca el registro
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            amXxGamMod.searchPayment(nPayment);

        } catch (Exception exception) {

            throw new OAException("No es posible encontrar el registro",
                                  OAException.WARNING);
        }
    }



    /**
     * Busca el registro por id.
     * @param pageContext Pagina de contexto.
     * @param webBean Web bean
     * @param nGeneral Clave primaria.
     */
    public static void searchTicketFranchise(OAPageContext pageContext,
                                     OAWebBean webBean, Number nGeneral) {

        //Verifica nulidad
        if (pageContext == null || webBean == null || nGeneral == null)
            return;

        try {
            //Busca el registro
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            amXxGamMod.searchTicketFranchise(nGeneral);

        } catch (Exception exception) {

            throw new OAException("No es posible encontrar el registro",
                                  OAException.WARNING);
        }
    }
    
    /**
     * Método para setear el valor del template id ya que se pierde en la navegación
     * de las pantallas.
     * @param pageContext Contexto de la pagina.
     * @param webBean bean.
     */
    public static void forceSetPaymentId(OAPageContext pageContext, OAWebBean webBean){
        if(pageContext != null){
            XxGamModAntAMImpl am = 
                    (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
            XxGamMaGeneralReqVOImpl vo = am.getXxGamMaGeneralReqVO1();
            XxGamMaGeneralReqVORowImpl fila = (XxGamMaGeneralReqVORowImpl)vo.getCurrentRow();
            if(fila != null){
                String cadTmpID = pageContext.getTransactionValue("tmpID").toString();
                int intTmpID = Integer.parseInt(cadTmpID);
                Number tmpID = new Number(intTmpID);
                if(tmpID != null){
                    fila.setTemplatePayment(tmpID);
                }
            }
        }
    }


    /**
     * Busqueda avanzada de consulta de solciitudes.
     *
     * @param pageContext Contexto de la pagina.
     * @param webBean Web bean.
     * @param advanceNum N�mero de anticipo.
     * @param employeeName Nombre del empleado.
     * @param nameApprover Nombre del proveedor.
     * @param costCenter Centro de costos.
     * @param costCenterFlex Flex del centro de costos
     * @param virtualCard Tarjeta virtual.
     * @param statusRequest Estatus de la solicitud.
     * @param statusTicket Estatus del boleto.
     * @param dateFrom Fecha inicio.
     * @param dateTo Fecha fin.
     */
    public static void searchAdvanceConsultantion(OAPageContext pageContext,
                                                  OAWebBean webBean,
                                                  String advanceNum,
                                                  String employeeName,
                                                  String nameApprover,
                                                  String costCenter,
                                                  String costCenterFlex,
                                                  String virtualCard,
                                                  String statusRequest,
                                                  String statusTicket,
                                                  Date dateFrom, Date dateTo) {

        //Busca el registro
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);

        try {

            //Inicia busqueda avanzada
            amXxGamMod.searchAdvanceConsultantion(advanceNum, 
                                                  employeeName,
                                                  nameApprover, 
                                                  costCenter,
                                                  costCenterFlex, 
                                                  virtualCard, 
                                                  statusRequest,
                                                  statusTicket, 
                                                  dateFrom,
                                                  dateTo);

        } catch (Exception exception) {
             throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                               XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_NF_ERROR,
                                               null, OAException.WARNING, null);
        }
    }

    /**
     * Verifica si el registro actual de solicitud de anticipo es apta para modificaciones o reserva de fondos
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OA
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OA
     * @return devuelve valor booleano, true si la solicitud es apta para modificaciones y false si no lo es
     */
    public static boolean isPaymentReqEditable(OAPageContext pageContext,
                                               OAWebBean webBean) {
        boolean isEditable = false;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            isEditable = amXxGamMod.isPaymentReqEditable();
        }
        return isEditable;
    }

    /**
     * Ejecuta el proceso correspondiente a los eventos definidos en el flujo de gestion de solicitudes de anticipo por parte
     * del empleado
     * @param pageContext contiene el objeto OAPageContext procedente de las paginas del empleado para gestion de solicitudes de anticipo
     * @param webBean contiene el objeto OAWebBean procedente de las paginas del empleado para gestion de solicitudes de anticipo
     * @param event contiene el nombre del evento
     */
    public static void exePaymentReqProcessFromRequest(OAPageContext pageContext,
                                                       OAWebBean webBean,
                                                       String event) {
        HashMap hmap = null;
        String msgLoadPage = null;
        
        if (event != null) {

            java.util.Hashtable params = new java.util.Hashtable(1);
            if(pageContext != null){
                pageContext.putParameter(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR, null);
            }

            //Verifica el evento para guardar cambios en la solicitud de anticipo
            if (XxGamConstantsUtil.SAVE.equals(event)) {
                params.put(XxGamConstantsUtil.SEND_CONFIRMATION, XxGamConstantsUtil.SAVE);
                String msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_SAVE_CONF_QN, null);
                setMessageDialog(pageContext,
                                 msg,
                                 OAException.INFORMATION, params);
            }

            //Verifica el evento para cancelar cambios en la solicitud de anticipo
            if (XxGamConstantsUtil.CANCEL.equals(event)) {
                params.put(XxGamConstantsUtil.SEND_CONFIRMATION, XxGamConstantsUtil.CANCEL);
                String msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_CANCEL_CONF_QN, null);
                setMessageDialog(pageContext,
                                 msg,
                                 OAException.INFORMATION, params);
            }
            
            //Verifica el evento para reservar fondos para la solicitud de anticipo
            if (XxGamConstantsUtil.RESERVE_FUNDS.equals(event)){
                params.put(XxGamConstantsUtil.SEND_CONFIRMATION, XxGamConstantsUtil.RESERVE_FUNDS);
                params.put("returnDialogPage", "return");
                String msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_RFUNDS_CONF_QN, null);
                setMessageDialog(pageContext,
                                 msg,
                                 OAException.INFORMATION, params);
            }


            //Verifica el evento de para regresar a la pantalla inicial del empleado
            if (XxGamConstantsUtil.RETURN.equals(event)) {
                /*** Inicializar la variable para que conserve los parametros en las pilas 10Jul2015***/
                hmap = new HashMap(); 
                
               XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"franchiseType GralPGRet: "+pageContext.getParameter("pfranchiseType"),pageContext,webBean);
               XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"Request: GralPGRet"+pageContext.getParameter("pRequest"),pageContext,webBean);
                    
                removeTransactionValueFromPaymentAdvRequest(pageContext);
                //Redirecciona la navegacion a la pantalla inicial del empleado
                String urlReturn = XxGamConstantsUtil.URL_PAGE_OAF;
                if(XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                            webBean, 
                                                            new Number(pageContext.getResponsibilityId()), 
                                                            XxGamConstantsUtil.RESPONSABILITY_AUDITOR)){
                    urlReturn += "XxGamAdvanceConsultationPG";
                }else{
                    if(XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                            webBean, 
                                                            new Number(pageContext.getResponsibilityId()), 
                                                            XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET)){
                        urlReturn += "XxGamATicketConsultationPG";
                    }else{
                        urlReturn += "XxGamPaymentInitAdvancePG";       
                    }
                }
                
              String strSFranchiseType = null; 
              String strSRequestType = null; 
              
              strSFranchiseType = (String)pageContext.getSessionValue("sfranchiseType"); 
              strSRequestType = (String)pageContext.getSessionValue("sRequest"); 
          
              if(null!=strSFranchiseType&&!"".equals(strSFranchiseType)){
                hmap.put("pfranchiseType",strSFranchiseType); 
              }
              if(null!=strSRequestType&&!"".equals(strSRequestType)){
                hmap.put("pRequest",strSRequestType);
              }
          
                XxGamMAnticiposUtil.setForwardWhitParameters(pageContext,
                                                             webBean, hmap,
                                                             urlReturn);
            }

            //Verifica el evento de siguiente para las solicitudes de franquicia
            if (XxGamConstantsUtil.NEXT_FRANCHISE.equals(event)) {
                //Redirecciona la navegacion a la pantalla inicial del empleado
                
                 hmap = new HashMap();

                String strSFranchiseType = null; 
                strSFranchiseType = (String)pageContext.getSessionValue("sfranchiseType");
                String strSRequest = null; 
                strSRequest = (String)pageContext.getSessionValue("sRequest");
                
                XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"franchiseType GralPGFormReq: "+strSFranchiseType,pageContext,webBean);
                XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"Request GralPGFormReq: "+strSRequest,pageContext,webBean);
               
               
                hmap.put("pfranchiseType",strSFranchiseType);
                hmap.put("pRequest",strSRequest);
                
                String urlReturn = XxGamConstantsUtil.URL_PAGE_OAF;
                urlReturn += "XxGamPaymentReqTicketPlanePG";
                //String typeRequest = pageContext.getParameter(XxGamConstantsUtil.TYPE_REQUEST_TICKET);// add by DIHU (CCT) 21-Oct-2014
                //hmap.put(XxGamConstantsUtil.TYPE_REQUEST_TICKET, typeRequest);// add by DIHU (CCT) 21-Oct-2014
                XxGamMAnticiposUtil.setForwardWhitParameters(pageContext,
                                                             webBean, hmap,
                                                             urlReturn);
            }
            
            if("deleteRow".equals(event)){
                params.put(XxGamConstantsUtil.SEND_CONFIRMATION, XxGamConstantsUtil.DELETE);
                
                String strRowId = pageContext.getParameter("detailRowId");
                if(strRowId != null){
                    params.put("detailRowId", strRowId);
                }
                String msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_ROW_DEL_CONF_QN, null);
                setMessageDialog(pageContext,
                                 msg,
                                 OAException.INFORMATION, params);
            }
        }

        //Verifica si realmente quiere verificar la operación reservar fondos
        if (pageContext.getParameter(XxGamConstantsUtil.BUTTON_YES) !=
            null) {
            
            String fromConfirm = null;
            fromConfirm = pageContext.getParameter(XxGamConstantsUtil.SEND_CONFIRMATION);
            
            if(XxGamConstantsUtil.RESERVE_FUNDS.equals(fromConfirm)){
                removeTransactionValueFromPaymentAdvRequest(pageContext);
                
                OAProcessingPage processingPage = new OAProcessingPage("xxgam.oracle.apps.xbol.maf.webui.XxGamMaProcessingPageCO");
                
                String msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_RFUNDS_CMSG_INFO, null);
                processingPage.setConciseMessage(msg);
                
                msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_RFUNDS_DMSG_INFO, null);
                processingPage.setDetailedMessage(msg);
                
                msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_RFUNDS_PNMSG_INFO, null);
                processingPage.setProcessName(msg);
                
                processingPage.setRetainAMValue(true);
                pageContext.forwardToProcessingPage(processingPage);
                
            }else{
                if(XxGamConstantsUtil.SAVE.equals(fromConfirm)){
                    
                  XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"SaveBug1",pageContext,webBean);
                  
                    removeTransactionValueFromPaymentAdvRequest(pageContext);
                    
                    XxGamMAnticiposUtil.refreshAllValidationByLine(pageContext, webBean);
                    
                    String errorMsg = savePaymentRequest(pageContext, webBean);
                    if (errorMsg == null) {
                      XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"SaveBug2",pageContext,webBean);
                        String strNumberPayment = XxGamMAnticiposUtil.getNumberPayment(pageContext, webBean);
                        if(strNumberPayment == null){
                            strNumberPayment = "";  
                        }
                        
                        MessageToken[] tokens = {new MessageToken("NUMBER_PAYMENT", strNumberPayment)};
                        msgLoadPage = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_SAVE_CONF_INFO, tokens);
                        
                        hmap = new HashMap();
                        hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_INFO,
                                 msgLoadPage);
                    } else {
                        XxGamMAnticiposUtil.setRollback(pageContext, webBean);
                        msgLoadPage = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_SAVE_CONF_ERROR, null);
                        msgLoadPage = msgLoadPage + ". " + errorMsg;
                                
                        hmap = new HashMap();
                        hmap.put(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR,
                                 msgLoadPage);
                    }

                    //Redirecciona la navegacion a la pantalla inicial del empleado
                    String urlReturn = XxGamConstantsUtil.URL_PAGE_OAF;
                    urlReturn += "XxGamPaymentInitAdvancePG";
                    XxGamMAnticiposUtil.setForwardWhitParameters(pageContext,
                                                                 webBean, 
                                                                 hmap,
                                                                 urlReturn);
                }else{
                
                    if(XxGamConstantsUtil.CANCEL.equals(fromConfirm)){
                        removeTransactionValueFromPaymentAdvRequest(pageContext);
                        
                        XxGamMAnticiposUtil.setRollback(pageContext, webBean);
                        
                        //Redirecciona la navegacion a la pantalla inicial del empleado
                        String urlReturn = XxGamConstantsUtil.URL_PAGE_OAF;
                        urlReturn += "XxGamPaymentInitAdvancePG";
                        XxGamMAnticiposUtil.setForwardWhitParameters(pageContext,
                                                                     webBean, hmap,
                                                                     urlReturn);
                    }else{
                        
                        if(XxGamConstantsUtil.DELETE.equals(fromConfirm)){
                            String strRowId = pageContext.getParameter("detailRowId");
                            Number rowId;
                            try {
                                rowId = new Number(strRowId);
                            } catch (SQLException e) {
                                rowId = null;
                            }
                            
                            boolean isSuccess = XxGamMAnticiposUtil.deleteRowPaymentDetail(pageContext, webBean, rowId);
                            if(isSuccess){
                            
                                throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                                  XxGamAOLMessages.GenericType.XXGAM_MAF_ROW_DEL_CONF_INFO,
                                                                  null, OAException.INFORMATION, null);
                            }else{
                                throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                                  XxGamAOLMessages.GenericType.XXGAM_MAF_ROW_DEL_CONF_ERROR,
                                                                  null, OAException.WARNING, null);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Elimina todos los parametros de transaccion creados para uso temporal en el proceso
     * de creacion y edicion de una solicitud de anticipo
     * @param pageContext
     */
    public static void removeTransactionValueFromPaymentAdvRequest(OAPageContext pageContext){
        if(pageContext != null){
            pageContext.removeTransactionValue(XxGamConstantsUtil.STATUS);
            pageContext.removeTransactionValue(XxGamConstantsUtil.VIEW_STEP_ONE);
            pageContext.removeTransactionValue(XxGamConstantsUtil.VIEW_STEP_TWO);
            pageContext.removeTransactionValue(XxGamConstantsUtil.VIEW_STEP_THREE);
            pageContext.removeTransactionValue(XxGamConstantsUtil.CALLED_FROM);
            pageContext.removeTransactionValue(XxGamConstantsUtil.TYPE_REQUEST_TICKET);
        }
    }

    /**
     * Ejecuta el work flow para el envio de notificaciones de solicitud de anticipos al aprobador
     * @param pageContext contiene el objeto OAPageContext procedente de las paginas del empleado para gestion de solicitudes de anticipo
     * @param webBean contiene el objeto OAWebBean procedente de las paginas del empleado para gestion de solicitudes de anticipo
     * @return devuelve true si la ejecucion fue correcta o false en caso contrario
     */
    public static boolean sendNotificationPaymentAdvWf(OAPageContext pageContext, OAWebBean webBean){
        
        boolean isSuccess = false;
        if(pageContext != null && webBean != null){
            
            if(XxGamMAnticiposUtil.validatesResponsability(pageContext, webBean, new Number(pageContext.getResponsibilityId()), XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)){
                
                 //Mapea los valores de los parametros del work flow
                 XxGamModAntAMImpl amXxGamMod = null;
                 amXxGamMod = getApplicationModule(pageContext, webBean);
                 
                 Number requestId = null;
                 Number employeeId = null;
                 Number approverId = null;
                 XxGamMaGeneralReqVOImpl generalImpl = null;
                 if(amXxGamMod != null){
                     
                     generalImpl = amXxGamMod.getXxGamMaGeneralReqVO1();
                     if(generalImpl != null){
                         XxGamMaGeneralReqVORowImpl generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
                         if(generalRow != null){
                             requestId = generalRow.getId();
                             employeeId = generalRow.getEmployeeId();
                             approverId = generalRow.getApproverId();
                         }
                     }
                 }
                 
                 if(requestId != null && employeeId != null && approverId != null){
                     
                     int intResult = amXxGamMod.callProcedureRequestPaymentAdv(requestId, employeeId, approverId);
                     if(intResult == 0){
                         isSuccess = true;
                     }
                 }
            }
        }
        return isSuccess;
    }

    /**
     * Ejecuta el envio de notificaciones de boletos de aviÃ³n en caso de existir
     * @param pageContext contiene el objeto OAPageContext procedente de las paginas del empleado para gestion de solicitudes de anticipo
     * @param webBean contiene el objeto OAWebBean procedente de las paginas del empleado para gestion de solicitudes de anticipo
     * @return devuelve un valor menor o igual a 0 cuando no ocurren errores en el proceso, si el valor es mayor a 0 representa el nÃºmero
     *         de notificaciones con error
     */
    private static int sendNotificationTicketPWorkFlow(OAPageContext pageContext,
                                                    OAWebBean webBean){
                                                    
        int countError = 0;
        if(pageContext != null && webBean != null){
        
            if(XxGamMAnticiposUtil.validatesResponsability(pageContext, webBean, new Number(pageContext.getResponsibilityId()), XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)){
            
                XxGamModAntAMImpl amXxGamMod = null;
                amXxGamMod = getApplicationModule(pageContext, webBean);
                
                if(amXxGamMod != null){
                    XxGamMaGeneralReqVOImpl generalImpl = null;
                    generalImpl = amXxGamMod.getXxGamMaGeneralReqVO1();
                    
                    XxGamMaGeneralReqVORowImpl generalRow = null;
                    if(generalImpl != null){
                        generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
                        
                        if(generalRow != null){
                            
                            Number requestId = generalRow.getId();
                            XxGamMaPaymentReqVOImpl detailImpl = null;
                            detailImpl = amXxGamMod.getXxGamMaPaymentReqVO2();
                            
                            if(detailImpl != null){
                                
                                RowSetIterator rowSetIter = detailImpl.getRowSetIterator();
                                if(rowSetIter != null){
                                    rowSetIter.reset();
                                    while(rowSetIter.hasNext()){
                                        Row detailRow = rowSetIter.next();
                                        XxGamMaPaymentReqVORowImpl detailRowVo = (XxGamMaPaymentReqVORowImpl)detailRow;
                                        if(detailRowVo != null){
                                            
                                            detailImpl.setCurrentRow(detailRowVo);
                                            
                                            XxGamMaTicketPVOImpl ticketImpl = null;
                                            ticketImpl = amXxGamMod.getXxGamMaTicketPVO3();
                                            
                                            if(ticketImpl != null){
                                                
                                                RowSetIterator rowSetIterTicket = ticketImpl.getRowSetIterator();
                                                if(rowSetIterTicket != null){
                                                    rowSetIterTicket.reset();
                                                    while(rowSetIterTicket.hasNext()){
                                                        Row ticketRow = rowSetIterTicket.next();
                                                        XxGamMaTicketPVORowImpl ticketRowVo = (XxGamMaTicketPVORowImpl)ticketRow;
                                                        if(ticketRowVo != null){
                                                        
                                                            if(detailRowVo.getId().equals(ticketRowVo.getPaymentReqId())){
                                                                ticketImpl.setCurrentRow(ticketRowVo);
                                                                
                                                                String route = "";
                                                                XxGamMaFlightInf0VOImpl flightImpl = amXxGamMod.getXxGamMaFlightInf0VO3();
                                                                if(flightImpl != null){
                                                                    RowSetIterator rowSetIterFlight = flightImpl.getRowSetIterator();
                                                                    if(rowSetIterFlight != null){
                                                                        rowSetIterFlight.reset();
                                                                        while(rowSetIterFlight.hasNext()){
                                                                            Row flighRow = rowSetIterFlight.next();
                                                                            XxGamMaFlightInf0VORowImpl flightRowVo = (XxGamMaFlightInf0VORowImpl)flighRow;
                                                                            if(flightRowVo != null){
                                                                                if(ticketRowVo.getId().equals(flightRowVo.getTicketPId())){
                                                                                    route += "";
                                                                                    String travelingFrom = "";
                                                                                    String travelingTo = "";
                                                                                    if(flightRowVo.getTravelingFrom() != null){
                                                                                        travelingFrom = flightRowVo.getTravelingFrom();
                                                                                    }
                                                                                    if(flightRowVo.getTravelingTo() != null){
                                                                                        travelingTo = flightRowVo.getTravelingTo();
                                                                                    }
                                                                                    route += travelingFrom + "-" + travelingTo + ";";
                                                                                }
                                                                            }
                                                                        }
                                                                        rowSetIterFlight.reset();
                                                                    }
                                                                }
                                                                
                                                                if("".equals(route)){
                                                                    route = ";";
                                                                }
                                                                
                                                                boolean isSuccess = false;
                                                                try{
                                                                    isSuccess = callWorkFlowCreateTicket(pageContext, webBean, generalRow, ticketRowVo, route);
                                                                }catch(Exception e){
                                                                    isSuccess = false;
                                                                }
                                                                
                                                                if(!isSuccess){
                                                                    countError = countError + 1;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    rowSetIterTicket.reset();
                                                }
                                            }
                                        }
                                    }
                                    rowSetIter.reset();
                                }
                            }
                            if(countError <= 0){
                                generalImpl.searchGeneralReq(requestId);
                            }
                        }
                    }
                }    
            }
        }
        
        return countError;
    }

    /**
     * Guarda los datos de la solicitud de anticipo
     * @param pageContext
     * @param webBean
     * @return devuelve nullo cuando se guarda correctamente o una descripcion del error en caso contrario
     */
    public static String savePaymentRequest(OAPageContext pageContext,
                                              OAWebBean webBean) {
        String errorMsg = null;
        boolean isSuccess = false;
        if (pageContext != null && webBean != null) {
        
            pageContext.getApplicationModule(webBean).getOADBTransaction().putTransientValue("IsValidateEntityFlight", "true");
            pageContext.getApplicationModule(webBean).getOADBTransaction().putTransientValue("IsValidateEntityDetail", "true");
            
            //Configura el estatus de la solicitud de anticipo o franquicia
            if(setValuesStatusInProgress(pageContext, webBean)){
                //Asigna el valor del atributo codeCombinationId al detalle
                setCodeCombinationIdPaymentReq(pageContext, webBean);
                
                //Configura el importe total de la solicitud de anticipo
                isSuccess = calculateAmountTotalGeneralReq(pageContext, webBean);

                if (isSuccess) {
                    
                    isSuccess = XxGamMAnticiposUtil.calculateAmountMxAllPaymentDetail(pageContext, 
                                                                                  webBean);
                    if(isSuccess){
                        //Configura numero de documento para solicitud de anticipo
                        isSuccess = setNumberPayment(pageContext, webBean);
                        
                        if(isSuccess){
                            isSuccess = setRequestPaymentDate(pageContext, webBean);
                            if(!isSuccess){
                                errorMsg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DATE_CFIG_ERROR, null);
                            }
                        }else{
                            errorMsg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_NMPAY_CFIG_ERROR, null);
                        }
                    }else{
                        errorMsg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_AMX_CFIG_ERROR, null);
                    }
                }else{
                    errorMsg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_TOTAL_CFIG_ERROR, null);
                }
            }else{
                errorMsg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_STATUS_CFIG_ERRO, null);
            }
            
            if (errorMsg == null) {
                errorMsg = XxGamMAnticiposUtil.setCommit(pageContext, webBean);
            } else {
                XxGamMAnticiposUtil.setRollback(pageContext, webBean);
            }
        }
        return errorMsg;
    }

    /**
     * Asigna el valor del n�mero de documento de la solicitud de anticipo o franquicia
     * @param pageContext contiene el objeto OAPageContext procedente de la p�gina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la p�gina OAF
     * @return devuelve true cuando cuando todas las validaciones fueron exitosas
     */
    public static boolean setNumberPayment(OAPageContext pageContext, OAWebBean webBean){
        
        boolean isSuccess = false;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if(amXxGamMod != null){
              /**
               * Comentado 08Jul2015 para usar metodo sobrecargado
               *  isSuccess = amXxGamMod.setNumberPayment(new Number(pageContext.getResponsibilityId()));  
               */
               isSuccess = amXxGamMod.setNumberPayment(new Number(pageContext.getResponsibilityId()),pageContext,webBean);   
            }
        }
        
        return isSuccess;
    }

    /**
     * Devuelve el valor del numero de documento de la solicitud de anticipo actual
     * @param pageContext contiene el objeto OAPageContext procedente de la página OAF
     * @param webBean contiene el objeto OAWebBean procedente de la página OAF
     * @return devuelve valor cadena del numero de documento
     */
    public static String getNumberPayment(OAPageContext pageContext, OAWebBean webBean){
        String strNumberPayment = "";
        if(pageContext != null && webBean != null){
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if(amXxGamMod != null){
                strNumberPayment = amXxGamMod.getNumberPayment();    
            }
        }
        return strNumberPayment;
    }

    /**
     * Asigna la fecha de la solicitud de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @return devuelve true en caso de una asignacion correcta o false en caso contrario
     */
    public static boolean setRequestPaymentDate(OAPageContext pageContext, OAWebBean webBean){
        boolean isSuccess = false;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if(amXxGamMod != null){
                isSuccess = amXxGamMod.setRequestPaymentDate();    
            }
        }
        return isSuccess;
    }

    /**
     * Asigna el valor del atributo codeCombinationId del detalle procedente de
     * informacion general
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     */
    public static void setCodeCombinationIdPaymentReq(OAPageContext pageContext, OAWebBean webBean){
        
        if(pageContext != null && webBean != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                amXxGamMod.setCodeCombinationsIdDetailVO2();
            }
        }
    }

    /**
     * Configura los valores cuando la solicitud de anticipo obtiene el estado de "En progreso"
     * @param pageContext contiene el objeto OAPageContext procedente de las paginas de solicitud de anticipo
     * @param webBean contiene el objeto OAWebBean procedente de las paginas de solicitud de anticipo
     * @return devuelve true cuando cuando todas las validaciones fueron exitosas
     */
    public static boolean setValuesStatusInProgress(OAPageContext pageContext,
                                                     OAWebBean webBean) {
        boolean isSuccess = false;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if(amXxGamMod != null){
              /**
               * Comentado por un metodo Sobrecargado para franquicias que no son nacionales 08Jul2015
               * isSuccess = amXxGamMod.setValuesStatusInProgress(new Number(pageContext.getResponsibilityId()));    
               */
              isSuccess = amXxGamMod.setValuesStatusInProgress(new Number(pageContext.getResponsibilityId()),pageContext,webBean);    
            }
        }
        return isSuccess;
    }
    
     /**
     * Exporta los datos generados de la View object.
     * @param pageContext contiene el objeto OAPageContext procedente de las paginas de solicitud de anticipo
     * @param webBean contiene el objeto OAWebBean procedente de las paginas de solicitud de anticipo
     */
    public static void exportXML(OAPageContext pageContext,
                                 OAWebBean webBean) {

        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return;

        //Obtiene el AM
        XxGamModAntAMImpl amXxGamMod = null;
        HttpServletResponse response = null;
        DataObject sessionDictionary = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);

        //Obtiene el XML node
        XMLNode xmlNode = amXxGamMod.getDataXML();
        System.out.println(xmlNode);
        try {
            //Inicia la generaciÃ³n del reporte
            sessionDictionary =
                    pageContext.getNamedDataObject("_SessionParameters");
            response =
                    (HttpServletResponse)sessionDictionary.selectValue(null, "HttpServletResponse");

            ServletOutputStream servletOutStream = response.getOutputStream();


            //ConfiguraciÃ³n de los parametros del registro
            String contentDisposition =
                "attachment;filename=Solicitud_anticipo.xls";
            response.setHeader("Content-Disposition", contentDisposition);
            response.setContentType("application/vnd.ms-excel");
            
            
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            xmlNode.print(outputStream);

            //Obtiene el input stream
            //ByteArrayInputStream inputStream =new ByteArrayInputStream(outputStream.toByteArray());

            //Archivo pdf
            ByteArrayOutputStream pdfFile = new ByteArrayOutputStream();
            String TemplateCode = null;
            TemplateCode = "XXGAM_XBOL_CONSULTAS_SOL";

            /*TemplateHelper.processTemplate(((OADBTransactionImpl)amXxGamMod.getOADBTransaction()).getAppsContext(),
                                           "XBOL", 
                                           TemplateCode, 
                                           ((OADBTransactionImpl)pageContext.getApplicationModule(webBean).getOADBTransaction()).getUserLocale().getLanguage(), 
                                           ((OADBTransactionImpl)pageContext.getApplicationModule(webBean).getOADBTransaction()).getUserLocale().getCountry(),
                                           inputStream,
                                           TemplateHelper.OUTPUT_TYPE_EXCEL,
                                           null, 
                                           pdfFile);*/

            //Excribe el archivo
            byte[] bytesPDF = pdfFile.toByteArray();
            response.setContentLength(bytesPDF.length);
            servletOutStream.write(bytesPDF, 0, bytesPDF.length);
            servletOutStream.flush();
            servletOutStream.close();

            //Render document.
            pageContext.setDocumentRendered(false);


        } catch (Exception exception) {
             throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                               XxGamAOLMessages.GenericType.XXGAM_MAF_AD_XPORT_ERROR,
                                               null, OAException.WARNING, null);
        }
    }


    /**
     * Configura los valores del detalle de la solicitud de anticipo segun los valores
     * devueltos por los controles de la pantalla
     * @param pageContext contiene el objeto OAPageContext que procede de la pantalla
     * @param webBean contiene el objeto OAWebBean que procede de la pantalla
     * @param rowRef contiene referencia del row actual
     * @param typePaymentAdv contiene id del tipo de anticipo
     */
    public static void setValueTypePaymentReqDetail(OAPageContext pageContext,
                                                    OAWebBean webBean,
                                                    String rowRef,
                                                    Number typePaymentAdv) {

        if (pageContext != null && webBean != null && rowRef != null &&
            typePaymentAdv != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                amXxGamMod.setValueTypePaymentReqDetail(rowRef, typePaymentAdv);           
            }
        }
    }

    /**
     * Llama a ejecutar el procedimiento de validacion por linea por seleccion del 
     * tipo de anticipo del registro referenciado
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param rowRef contiene referencia del row procedente del componente tabla
     * @return devuelve un mapa con los resultado de la validacion. Contiene las siguientes claves:
     *         isSuccess. Contiene valor booleano. devuelve true si no ocurrio error y false en caso contrario
     *         msgError. Contiene cadena con mensaje de error.
     *         oaException. Contiene valor Byte del tipo de Exception.
     */
    public static Map setTypePaymentSelectProcess(OAPageContext pageContext,
                                                   OAWebBean webBean,
                                                   String rowRef){
        Map mapResult = null;
        if(pageContext != null && webBean != null && rowRef != null){
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                XxGamMaPaymentReqVORowImpl row =
                    (XxGamMaPaymentReqVORowImpl)amXxGamMod.findRowByRef(rowRef);
                mapResult = validationByLine(pageContext, webBean, row, true);
            }
        }
        return mapResult;
    }

     /**
     * Ejecuta el procedimiento de validacion por linea de anticipo, calculo de importe y de
     * solicitud de boleto de avión
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param detailRow contiene el registro del detalle de anticipo
     * @param isSelected contiene valor bandera para indicar si la validacion proviene de una previa seleccion
     *                   del tipo de anticipo
     * @return devuelve un mapa con los resultados de la validacion, contiene las siguientes claves:
     *         intResult. Contiene valor entero. Devuelve 0 si no ocurrio error, diferente de cero para errores
     *         msgError. Contiene cadena con mensaje de error.
     *         oaException. Contiene valor Byte del tipo de Exception.
     */
    public static java.util.Map validationByLine(OAPageContext pageContext,
                                        OAWebBean webBean,
                                        XxGamMaPaymentReqVORowImpl detailRow,
                                        boolean isSelected){
        pageContext.writeDiagnostics(XxGamMAnticiposUtil.class, "Entered validationByLine",
                                     OAFwkConstants.PROCEDURE);   
        System.out.println("Entered validationByLine");                              
        java.util.Map mapValid = new java.util.HashMap();
        int intResult = -1;
        if(pageContext != null && webBean != null && detailRow != null){
            
            String msgError = null;
            Byte oaException = null;
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
                        
            if(amXxGamMod != null){
                XxGamMaPaymentReqVORowImpl row = detailRow;
                if (row != null) {
                    Number typePaymentId = null;
                    typePaymentId = row.getTypePayment();
                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                 "Get type payment id of Detail Row: " + typePaymentId,
                                                 OAFwkConstants.STATEMENT);
                    row.setIsPaymentValid(false);
                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                 "Set Attribute IsPaymentValid of Detail Row in false",
                                                 OAFwkConstants.STATEMENT);
                    row.setIsPaymentNotValid(false);
                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                 "Set Attribute IsPaymentNotValid of Detail Row in false",
                                                 OAFwkConstants.STATEMENT);
                    
                    row.setObservations(null);
                    System.out.println("paymentReqDetailCO Valor de typePaymentId: "+typePaymentId);
                    if(typePaymentId != null){
                        
                        XxGamModAntLovAMImpl amLov = (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();    
                        
                        XxGamMaGeneralReqVOImpl generalImpl = amXxGamMod.getXxGamMaGeneralReqVO1();
                        XxGamMaGeneralReqVORowImpl generalRow = null;
                        if(generalImpl != null){
                            generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                         "Get current General Information Row: " + generalRow,
                                                         OAFwkConstants.STATEMENT);
                        }
                        
                        Number templateId = null;
                        if(generalRow != null){
                            templateId = generalRow.getTemplatePayment();
                            System.out.println("paymentReqDetailCO Valor de templateId: "+templateId);
                            if(templateId != null){
                                pageContext.putTransactionValue("tmpID",templateId);
                            }
                            else{
                                templateId = new Number(Integer.parseInt(pageContext.getTransactionValue("tmpID").toString()));
                                //pageContext.putTransactionValue("tmpID", 0);
                                System.out.println("paymentReqDetailCO Valor de templateId: "+templateId);
                            }
                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                         "Get template Id of Detail Row : " + templateId,
                                                         OAFwkConstants.STATEMENT);
                        }
                        
                        if(amLov != null && templateId != null){
                            XxGamMaTypePaymentLovVORowImpl typePaymentRow = amLov.getTypePaymentById(typePaymentId, templateId);
                            if(typePaymentRow != null){
                                String typePaymentDesc = null;
                                typePaymentDesc = typePaymentRow.getTypePaymentDesc();
                                if(typePaymentDesc != null){
                                    System.out.println("Valor de la variable typePaymentDesc: "+typePaymentDesc); 
                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                 "Validates type payment for \"Boleto de avion AM\"",
                                                                 OAFwkConstants.STATEMENT);
                                  System.out.println("Validates type payment for \"Boleto de avion AM\"");                             
                                    if(typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_TICKET_FLIGHT_AM.toUpperCase()) != -1){
                                        System.out.println("Es igual al boleto de avion");   
                                        if(row.getInitialDate() != null &&
                                           row.getFinalDate() != null){
                                           
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Validation is Success, for \"Boleto de avion AM\"",
                                                                         OAFwkConstants.STATEMENT);
                                            System.out.println("Validation is Success, for \"Boleto de avion AM\"");     
                                            row.setAmount(new Number(0));
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Set Attribute Amount of Detail Row with zero",
                                                                         OAFwkConstants.STATEMENT);
                                            row.setAmountMx(new Number(0));
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Set Attribute AmountMx of Detail Row with zero",
                                                                         OAFwkConstants.STATEMENT);
                                            if(row.getCurrencyCode() == null){
                                                row.setCurrencyCode(XxGamConstantsUtil.CURRENCY_CODE_MXN);
                                                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                             "Set Attribute Currency Code of Detail Row with: " + XxGamConstantsUtil.CURRENCY_CODE_MXN,
                                                                             OAFwkConstants.STATEMENT);
                                                XxGamMaCurrencyLovVORowImpl currencyRow = amLov.getCurrencyByCode(row.getCurrencyCode());
                                                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                             "Search Currency description of currency code",
                                                                             OAFwkConstants.STATEMENT);
                                                if(currencyRow != null){
                                                    row.setCurrencyDesc(currencyRow.getCurrencyName());
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Set Attribute Currency Desc of Detail Row with: " + currencyRow.getCurrencyName(),
                                                                                 OAFwkConstants.STATEMENT);
                                                }
                                            }
                                            
                                            
                                            if(row.getInitialDate() != null &&
                                                row.getFinalDate() != null &&
                                                templateId != null &&
                                                row.getTypePayment() != null &&
                                                row.getCurrencyCode() != null){
                                            
                                                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                             "Call method XxGamModAntAMImpl.callProcedureValidatesForLinePaymentAdv",
                                                                             OAFwkConstants.STATEMENT);
                                                //Llama procedimiento PLSQL para calcular importe
                                                Map mapResult = amXxGamMod.callProcedureValidatesForLinePaymentAdv(row.getInitialDate(), 
                                                                                                   row.getFinalDate(), 
                                                                                                   templateId, 
                                                                                                   row.getTypePayment(), 
                                                                                                   row.getCurrencyCode());
                                                Boolean isPaymentValid = false;
                                                
                                                intResult = (Integer)mapResult.get("intResult");
                                                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                             "Get int result of called procedure: " + intResult,
                                                                             OAFwkConstants.STATEMENT);
                                                if(intResult == 0){
                                                    isPaymentValid = true;
                                                }else{
                                                    isPaymentValid = false;
                                                }
                                                
                                                row.setIsPaymentValid(isPaymentValid);
                                                row.setIsPaymentNotValid(!isPaymentValid);
                                                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                             "Set attribute IsPaymentValid of Detail Row in: " + isPaymentValid,
                                                                             OAFwkConstants.STATEMENT);
                                                
                                                if(!isPaymentValid){
                                                    if(intResult == 1){
                                                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_1, null);
                                                    }
                                                    if(intResult == 2){
                                                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_2, null);
                                                        msgError = null; /** Agregado para que no aparezca el mensaje solo para boletos  01/JUNIO/2015 **/
                                                    }
                                                    if(intResult == 3){
                                                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_3, null);
                                                    }
                                                    if(intResult == 4){
                                                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_4, null);
                                                    }
                                                    if(intResult == 5){
                                                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_5, null);
                                                    }
                                                    if(intResult == 6){
                                                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_6, null);
                                                    }
                                                    if(intResult == -1){
                                                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_7, null);
                                                    }
                                                    oaException = OAException.WARNING;
                                                }
                                                
                                                Number amount = null;
                                                amount = (Number)mapResult.get("Amount");
                                                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                             "Get Amount of called procedure: " + amount,
                                                                             OAFwkConstants.STATEMENT);
                                                System.out.println("Monto antes del if "+amount);   
                                                if(amount != null){
                                                    if(amount.compareTo(new Number(0)) >= 0){
                                                        System.out.println("Monto dentro del if "+amount);   
                                                        row.setAmount(amount);
                                                        pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                     "Set attribute Amount of Detail Row in : " + amount.toString(),
                                                                                     OAFwkConstants.STATEMENT);
                                                    }else{
                                                        msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_AMOUNT_NEG, null);
                                                        oaException = OAException.WARNING;
                                                        row.setAmount(amount);
                                                    }
                                                }else{
                                                    msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_AMOUNT_NULL, null);
                                                    oaException = OAException.WARNING;
                                                    System.out.println("Monto en el else: oaException "+oaException);   
                                                    row.setAmount(null);
                                                }  // END if(amount != null){
                                            }
                                            
                                            
                                            
                                            row.setIsPaymentValid(true);
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Set Attribute IsPaymentValid of Detail Row in true ",
                                                                         OAFwkConstants.STATEMENT);
                                            row.setIsPaymentNotValid(false);
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Set Attribute IsPaymentNotValid of Detail Row in false ",
                                                                         OAFwkConstants.STATEMENT);
                                            intResult = 0;
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Validation process is Success",
                                                                         OAFwkConstants.STATEMENT);
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Validates if origins of call is OAF Page",
                                                                         OAFwkConstants.STATEMENT);
                                            if(isSelected){
                                                XxGamMaPaymentReqVOImpl detailImpl =  null;
                                                detailImpl = amXxGamMod.getXxGamMaPaymentReqVO2();
                                                
                                                if(detailImpl != null){
                                                    detailImpl.setCurrentRow(row);
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Set current Detail row ",
                                                                                 OAFwkConstants.STATEMENT);
                                                    
                                                    pageContext.putTransactionValue(XxGamConstantsUtil.CALLED_FROM, XxGamConstantsUtil.VIEW_STEP_TWO);
                                                    String urlBase = XxGamConstantsUtil.URL_PAGE_OAF;
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Forward to page: " + urlBase,
                                                                                 OAFwkConstants.STATEMENT);
                                                    //Redirige la navegacion a la pantalla de solicitud de boletos de avion
                                                    XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, 
                                                                                                 webBean, 
                                                                                                 null, 
                                                                                                 urlBase + 
                                                                                                 "XxGamPaymentReqTicketPlanePG");
                                                }
                                            }
                                        }else{
                                            if(row.getInitialDate() == null){
                                                msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VAL_GET_FHINI, null);
                                            }else{
                                                if(row.getFinalDate() == null){
                                                    msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VAL_GET_FHFIN, null);
                                                }
                                            }
                                            oaException = OAException.WARNING;
                                        }
                                    }else  // Else if(typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_TICKET_FLIGHT_AM.toUpperCase()) != -1){
                                    {
                                        pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                     "Validates type payment for \"Hospedaje Convenio\"",
                                                                     OAFwkConstants.STATEMENT);
                                       System.out.println(" Validates type payment for \"Hospedaje Convenio\"");                             
                                        if(typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_HOSTING_AGREEMENT.toUpperCase()) != -1){
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Validation is Success, for \"Hospedaje Convenio\"",
                                                                         OAFwkConstants.STATEMENT);
                                          
                                          System.out.println( "Validation is Success, for \"Hospedaje Convenio\"");
                                          
                                            row.setAmount(new Number(0));
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Set Attribute Amount of Detail Row with zero",
                                                                         OAFwkConstants.STATEMENT);
                                            row.setAmountMx(new Number(0));
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Set Attribute AmountMx of Detail Row with zero",
                                                                         OAFwkConstants.STATEMENT);
                                            row.setIsPaymentValid(true);
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Set Attribute IsPaymentValid of Detail Row in true",
                                                                         OAFwkConstants.STATEMENT);
                                            row.setIsPaymentNotValid(false);
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Set Attribute IsPaymentNotValid of Detail Row in false",
                                                                         OAFwkConstants.STATEMENT);
                                            intResult = 0;
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Validation process is Success",
                                                                         OAFwkConstants.STATEMENT);
                                        }else // else if(typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_HOSTING_AGREEMENT.toUpperCase()) != -1){
                                        {
                                        
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                     "Validates type payment for enable calculated or open the field Amount",
                                                                     OAFwkConstants.STATEMENT);
                                             System.out.println( "Validates type payment for enable calculated or open the field Amount TRANSPORT2 OTROS_GASTOS ");                       
                                             if(typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_TRANSPORT2.toUpperCase()) != -1 ||
                                                    typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_OTROS_GASTOS.toUpperCase()) != -1 ){
                                                 
                                                     pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                  " TRANSPORT2 OTROS_GASTOS Validation in process, for calculated field of Amount, validates params of detail row for call procedure",
                                                                                  OAFwkConstants.STATEMENT);
                                                   System.out.println( " TRANSPORT2 OTROS_GASTOS Validation in process, for calculated field of Amount, validates params of detail row for call procedure");                               
                                                     if(row.getInitialDate() != null &&
                                                         row.getFinalDate() != null &&
                                                         row.getTypePayment() != null){
                                                         
                                                        Map mapResult = amXxGamMod.callProcedureValidatesForLinePaymentAdv(row.getInitialDate(), 
                                                                                                       row.getFinalDate(), 
                                                                                                       templateId, 
                                                                                                       row.getTypePayment(), 
                                                                                                       row.getCurrencyCode());
														                             Boolean isPaymentValid = false;
                                                         Map limitScheduleOptionMap = new java.util.HashMap();
                                                         Number limitAmount = null;
                                                         Number limitAmountOrigin = null;
                                                         String errcodlocal = null; 
                                                         String errmsglocal = null; 
                                                        
                                                       if (typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_TRANSPORT2.toUpperCase()) != -1) {
                                                               //String Currency = row.getCurrencyCode();
                                                               
                                                               /*********************** Comienza Validacion monto limite Transportacion **********************************/
                                                                
                                                                limitScheduleOptionMap = XxGamMAnticiposUtil2.getLimitScheduleOption(pageContext
                                                                                                               ,webBean
                                                                                                               ,templateId
                                                                                                               ,row.getTypePayment()
                                                                                                               ,typePaymentDesc
                                                                                                               ,row.getCurrencyCode()); 
                                                                                                               
                                                               limitAmount = (Number)limitScheduleOptionMap.get("Rate");
                                                               limitAmountOrigin = limitAmount;
                                                               limitAmount = limitAmount.add(1);
                                                               System.out.println("limitAmount.add(1): "+limitAmount);
                                                               errcodlocal = (String)limitScheduleOptionMap.get("errcodOUT");
                                                               errmsglocal = (String)limitScheduleOptionMap.get("errmsgOUT");
                                                              /*********************** Finaliza Validacion monto limite Transportacion **********************************/
                                                              
                                                       } else {
                                                               isPaymentValid = true;
                                                       }
                                                       
                                                        
                                                         System.out.println("Comienza Agregado por GnosisHCM para mandar un comentario en la pantalla de details");   
                                                         intResult = (Integer)mapResult.get("intResult");
                                                          System.out.println("Get int result of called procedure: " + intResult); 
                                                          pageContext.writeDiagnostics("XxGamMAnticiposUtil","Get int result of called procedure: " + intResult,OAFwkConstants.STATEMENT);
                                                            if(intResult == 0 || typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_OTROS_GASTOS.toUpperCase()) != -1){
                                                              if (typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_TRANSPORT2.toUpperCase()) != -1) {
                                                                String Currency = row.getCurrencyCode();
                                                                if("0".equals(errcodlocal)){
                                                                /**  isPaymentValid = true; 
                                                                 **  Agregado porque no se estaban validando los montos para las filiales 
                                                                 **  Aparte hay que validar Aquellas que no tienen asociado un policy schedule
                                                                 **  01/JUNIO/2015 esta fecha influyo en los periodos abiertos y cerrados
                                                                 ***/
                                                                 if (row.getAmount() != null) {
                                                                                 Number amount = row.getAmount();
                                                                                 
                                                                                 if (amount.compareTo(limitAmount) == -1 && "MXN".equals(Currency)) {
                                                                                     isPaymentValid = true;
                                                                                 } else if (amount.compareTo(limitAmount) == -1 &&  "EUR".equals(row.getCurrencyCode())) {
                                                                                     isPaymentValid = true;
                                                                                 } else if (amount.compareTo(limitAmount) == -1 &&  "USD".equals(row.getCurrencyCode())) {
                                                                                     isPaymentValid = true;
                                                                                 } else {
                                                                                     isPaymentValid = false;
                                                                                     intResult = 7;
                                                                                 }
                                                                  }
                                                                 
                                                                 } // END if("0".equals(errcodlocal)){
                                                                 else{
                                                                   isPaymentValid = false;
                                                                   intResult = 8;
                                                                 }
                                                               } else {
                                                                      isPaymentValid = true;
                                                                      /**Para el caso de Otros Gastos**/
                                                              }
                                                           }else{
                                                                isPaymentValid = false;
                                                            } //END if(intResult == 0 || typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_OTROS_GASTOS.toUpperCase()) != -1){
                                                         
                                                         
                                                          
                                                           row.setIsPaymentValid(isPaymentValid);
                                                           row.setIsPaymentNotValid(!isPaymentValid);
                                                            
                                                             System.out.println("Set attribute IsPaymentValid of Detail Row in: " + isPaymentValid); 
                                                             pageContext.writeDiagnostics("XxGamMAnticiposUtil","Set attribute IsPaymentValid of Detail Row in: " + isPaymentValid,OAFwkConstants.STATEMENT);
                                                                                                 
                                                             if(!isPaymentValid){
                                                                 if(intResult == 1){
                                                                     msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_1, null);
                                                                 }
                                                                 if(intResult == 2){
                                                                     msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_2, null);
                                                                 }
                                                                 if(intResult == 3){
                                                                     msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_3, null);
                                                                 }
                                                                 if(intResult == 4){
                                                                     msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_4, null);
                                                                 }
                                                                 if(intResult == 5){
                                                                     msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_5, null);
                                                                 }
                                                                 if(intResult == 6){
                                                                     msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_6, null);
                                                                 }
                                                                 if(intResult == -1){
                                                                     msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_7, null);
                                                                 }
                                                               if(intResult == 7){
                                                                   MessageToken[] arrayOfMessageToken = { new MessageToken("PARAM1",limitAmountOrigin.toString()), new MessageToken("PARAM2", row.getCurrencyCode() ) };
                                                                   msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_VAL_LIMIT_AMOUNT_POL, arrayOfMessageToken);
                                                               }
                                                               if(intResult == 8){
                                                                 msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_GET_LIMIT_AMOUNT_POL, null);
                                                               }
                                                                 oaException = OAException.WARNING;
                                                             }
                                                                                                         
                                                          
                                                       System.out.println("Finaliza Agregado por GnosisHCM para mandar un comentario en la pantalla de details");      
                                                         
                                                         
                                                         row.setIsPaymentValid(isPaymentValid);
                                                         pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                        "Set Attribute IsPaymentValid of Detail Row in true",
                                                                                        OAFwkConstants.STATEMENT);
                                                         row.setIsPaymentNotValid(!isPaymentValid);
                                                         pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                        "Set Attribute IsPaymentNotValid of Detail Row in false",
                                                                                        OAFwkConstants.STATEMENT);
                                                         intResult = 0;
                                                         pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                        "Validation process is Success",
                                                                                        OAFwkConstants.STATEMENT);

                                                         Number amount = null;                                                    
                                                         amount = (Number)mapResult.get("Amount");                                                    
                                                         pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                      "Get Amount of called procedure: " + amount,
                                                                                      OAFwkConstants.STATEMENT);
                                                                                      
                                                         /*String Currency = row.getCurrencyCode();
                                                            
                                                     if (Currency != "MXN" && Currency != "EUR"&& Currency != "USD") {
                                                                    
                                                             if(amount != null){
                                                                             if(amount.compareTo(new Number(0)) >= 0){
                                                                            
                                                                                             row.setAmount(amount);
                                                                                             pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                                                                                                                                                     "Set attribute Amount of Detail Row in : " + amount.toString(),
                                                                                                                                                                                                                     OAFwkConstants.STATEMENT);
                                                                             }else{
                                                                                             msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_AMOUNT_NEG, null);
                                                                                             oaException = OAException.WARNING;
                                                                                             row.setAmount(amount);
                                                                             }
                                                             }else{
                                                                             msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_AMOUNT_NULL, null);
                                                                             oaException = OAException.WARNING;
                                                                             row.setAmount(null);
                                                             }
                                                                    
                                                     }*/
                                                     }else // else if(row.getInitialDate() != null &&row.getFinalDate() != null &&row.getTypePayment() != null){
                                                     {
                                                         pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                      "Occurs Error",
                                                                                      OAFwkConstants.ERROR);
                                                         row.setAmount(null);
                                                         pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                      "Set attribute Amount of Detail Row in null",
                                                                                      OAFwkConstants.STATEMENT);
                                                         row.setAmountMx(null);
                                                         pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                      "Set attribute AmountMx of Detail Row in null",
                                                                                      OAFwkConstants.STATEMENT);
                                                     }  // END if(row.getInitialDate() != null &&  row.getFinalDate() != null && row.getTypePayment() != null){
                                                     
                                                     
                                                 }else // else if(typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_TRANSPORT2.toUpperCase()) != -1 ||
                                                       //  typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_OTROS_GASTOS.toUpperCase()) != -1 ){
                                                 {                    
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                     "Validates type payment for enable calculated or open the field Amount",
                                                                     OAFwkConstants.STATEMENT);
                                             System.out.println("Validates type payment for enable calculated or open the field Amount, no tienen != ");
                                            if(typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_TICKET_FLIGHT_OTHER.toUpperCase()) == -1 &&
                                               /*typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_TRANSPORT.toUpperCase()) == -1 &&*/
                                               typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_HOSTING_ANT.toUpperCase()) == -1 &&
                                               typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_TRANSPORT2.toUpperCase()) == -1 &&
                                               typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_OTROS_GASTOS.toUpperCase()) == -1 ){
                                            
                                                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                             "Validation in process, for calculated field of Amount, validates params of detail row for call procedure",
                                                                             OAFwkConstants.STATEMENT);
                                              System.out.println("Validation in process, for calculated field of Amount, validates params of detail row for call procedure, no tienen != ");
                                                 if(row.getInitialDate() != null &&
                                                    row.getFinalDate() != null &&
                                                    templateId != null &&
                                                    row.getTypePayment() != null &&
                                                    row.getCurrencyCode() != null){
                                                
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Call method XxGamModAntAMImpl.callProcedureValidatesForLinePaymentAdv",
                                                                                 OAFwkConstants.STATEMENT);
                                                    //Llama procedimiento PLSQL para calcular importe
                                                    Map mapResult = amXxGamMod.callProcedureValidatesForLinePaymentAdv(row.getInitialDate(), 
                                                                                                       row.getFinalDate(), 
                                                                                                       templateId, 
                                                                                                       row.getTypePayment(), 
                                                                                                       row.getCurrencyCode());
                                                    Boolean isPaymentValid = false;
                                                    
                                                    intResult = (Integer)mapResult.get("intResult");
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Get int result of called procedure: " + intResult,
                                                                                 OAFwkConstants.STATEMENT);
                                                    if(intResult == 0){
                                                        /*if (typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_TRANSPORT2.toUpperCase()) != -1) {
                                                                //Number amount = null;
                                                                //String Currency = null;
                                                                //Number amount = (Number)mapResult.get("Amount");
                                                                String Currency = row.getCurrencyCode();
                                                                if (row.getAmount() != null) {
                                                                    Number amount = row.getAmount();
                                                                    
                                                                    if (amount.compareTo(2001) == -1 && 
                                                                                "MXN".equals(Currency)) {
                                                                            isPaymentValid = true;
                                                                    } else if (amount.compareTo(232) == -1 && 
                                                                                "EUR".equals(row.getCurrencyCode())) {
                                                                            isPaymentValid = true;
                                                                    } else if (amount.compareTo(301) == -1 && 
                                                                                "USD".equals(row.getCurrencyCode())) {
                                                                            isPaymentValid = true;
                                                                    } else {
                                                                        
                                                                    }
                                                                }
                                                                
                                                        } else {*/
                                                            isPaymentValid = true;
                                                        //}
                                                    }else{
                                                        isPaymentValid = false;
                                                    }
                                                    
                                                    row.setIsPaymentValid(isPaymentValid);
                                                    row.setIsPaymentNotValid(!isPaymentValid);
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Set attribute IsPaymentValid of Detail Row in: " + isPaymentValid,
                                                                                 OAFwkConstants.STATEMENT);
                                                    
                                                    if(!isPaymentValid){
                                                        if(intResult == 1){
                                                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_1, null);
                                                        }
                                                        if(intResult == 2){
                                                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_2, null);
                                                        }
                                                        if(intResult == 3){
                                                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_3, null);
                                                        }
                                                        if(intResult == 4){
                                                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_4, null);
                                                        }
                                                        if(intResult == 5){
                                                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_5, null);
                                                        }
                                                        if(intResult == 6){
                                                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_6, null);
                                                        }
                                                        if(intResult == -1){
                                                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_CAL_7, null);
                                                        }
                                                        oaException = OAException.WARNING;
                                                    }
                                                    
                                                    Number amount = null;                                                    
                                                    amount = (Number)mapResult.get("Amount");                                                    
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Get Amount of called procedure: " + amount,
                                                                                 OAFwkConstants.STATEMENT);
                                                                                 
                                                   //if (typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_TRANSPORT2.toUpperCase()) < 0) {
                                                       
                                                       //if (typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_OTROS_GASTOS.toUpperCase()) < 0) {
                                                        
                                                           //String Currency = row.getCurrencyCode();
                                                           
                                                           //if (Currency != "MXN" && Currency != "EUR"&& Currency != "USD") {
                                                               
                                                               //if(amount != null){
                                                                   if(amount.compareTo(new Number(0)) >= 0){
                                                                   
                                                                       row.setAmount(amount);
                                                                       pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                                    "Set attribute Amount of Detail Row in : " + amount.toString(),
                                                                                                    OAFwkConstants.STATEMENT);
                                                                   }else{
                                                                       msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_AMOUNT_NEG, null);
                                                                       oaException = OAException.WARNING;
                                                                       row.setAmount(amount);
                                                                   }
                                                               /*}else{
                                                                   msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_AMOUNT_NULL, null);
                                                                   oaException = OAException.WARNING;
                                                                   row.setAmount(null);
                                                               }*/
                                                               
                                                           //}
                                                           
                                                       //}
                                                       
                                                   //}
                                                }else{
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Occurs Error",
                                                                                 OAFwkConstants.ERROR);
                                                    row.setAmount(null);
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Set attribute Amount of Detail Row in null",
                                                                                 OAFwkConstants.STATEMENT);
                                                    row.setAmountMx(null);
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Set attribute AmountMx of Detail Row in null",
                                                                                 OAFwkConstants.STATEMENT);
                                                }
                                            }else{
                                                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                            "Validates for open field of Amount",
                                                                             OAFwkConstants.STATEMENT);
                                                System.out.println("Se validara para aquellas cronogramas/schedules que no esten consideradas "); 
                                                if(templateId != null &&
                                                   row.getTypePayment() != null &&
                                                   row.getAmount() != null &&
                                                   row.getCurrencyCode() != null){
                                                   
                                                    boolean isValid = false;
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Call method XxGamModAntAMImpl.callPValidatesAmountByLinePaymentAdv",
                                                                                 OAFwkConstants.STATEMENT);
                                                    intResult = amXxGamMod.callPValidatesAmountByLinePaymentAdv(templateId,
                                                                                                               row.getTypePayment(),
                                                                                                               row.getAmount(),
                                                                                                               row.getCurrencyCode());
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Get int result of called procedure: " + intResult,
                                                                                 OAFwkConstants.STATEMENT);
                                                                                 
                                                  System.out.println("Valor de la variable intResult: "+intResult);                               
                                                    if(intResult == 0){
                                                        isValid = true;
                                                        msgError = null;
                                                        oaException = null;
                                                    }else{
                                                        if(intResult == 1){
                                                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_NCAL1, null);
                                                        }
                                                        if(intResult == 2){
                                                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_NCAL2, null);
                                                        }
                                                        if(intResult == 3){
                                                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_NCAL3, null);
                                                        }
                                                        if(intResult == 4){
                                                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_NCAL4, null);
                                                        }
                                                        oaException = OAException.WARNING;
                                                    }                  
                                                                                 
                                                    row.setIsPaymentValid(isValid);
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Set attribute IsPaymentValid of Detail Row in " + isValid,
                                                                                 OAFwkConstants.STATEMENT);
                                                    row.setIsPaymentNotValid(!isValid);
                                                }else{
                                                    row.setAmountMx(null);
                                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                                 "Set attribute AmountMx of Detail Row in null",
                                                                                 OAFwkConstants.STATEMENT);
                                                }
                                            }
                                         } // END if(typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_TRANSPORT2.toUpperCase()) != -1 ||
                                           //  typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_OTROS_GASTOS.toUpperCase()) != -1 ){
                                        } // END if(typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_HOSTING_AGREEMENT.toUpperCase()) != -1){    
                                    } // END if(typePaymentDesc.toUpperCase().trim().indexOf(XxGamConstantsUtil.TYPE_PAYMENT_TICKET_FLIGHT_AM.toUpperCase()) != -1){  
                                }else // else if(typePaymentDesc != null){
                                {
                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                 "Occurs Error",
                                                                 OAFwkConstants.ERROR);
                                    msgError =  pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_TD_NF, null);
                                    oaException = OAException.WARNING;
                                } // END if(typePaymentDesc != null){
                            }else{
                                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                             "Occurs Error",
                                                             OAFwkConstants.ERROR);
                                msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLINE_T_NF, null);
                                oaException = OAException.WARNING;
                            }
                        }else{
                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                         "Occurs Error",
                                                         OAFwkConstants.ERROR);
                            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.Validation.XXGAM_MAF_DETAIL_VALLIN_TP_NF, null);
                            oaException = OAException.WARNING;
                        }  //END if(amLov != null && templateId != null){
                        
                    } // END if(typePaymentId != null){
                } //END if (row != null) {
            }  // END if(amXxGamMod != null){
            
            if(msgError != null){
                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                             "Validates if the call origins direct of OAF Page",
                                             OAFwkConstants.STATEMENT);
                detailRow.setObservations(msgError);                
                
                mapValid.put("msgError", msgError);
                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                             "Put msgError with value in return Map Result: " + msgError,
                                             OAFwkConstants.STATEMENT);
                mapValid.put("oaException", oaException);
                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                             "Put oaException with value in return Map Result: " + oaException,
                                             OAFwkConstants.STATEMENT);            
            }
        }
        mapValid.put("intResult", intResult);
        pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                     "Put isSuccess with value in return Map Result: " + intResult,
                                     OAFwkConstants.STATEMENT);
                                     
        pageContext.writeDiagnostics(XxGamMAnticiposUtil.class, "Leaving validationByLine and Return Map",
                                     OAFwkConstants.PROCEDURE);
                                     
        System.out.println("Leaving validationByLine and Return Map");                              
        return mapValid;
    }

    /**
     * Llama la validacion por linea de todo el detalle de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     * @return devuelve true si todos los registros fueron validos, devuelve false en caso contrario
     */
    public static Boolean refreshAllValidationByLine(OAPageContext pageContext,
                                                  OAWebBean webBean){
        pageContext.writeDiagnostics(XxGamMAnticiposUtil.class, "Entered refreshAllValidationByLine",
                                     OAFwkConstants.PROCEDURE);
        
        Boolean isSuccessAll = true;
        pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                     "Return boolean parameter initialised whit: " + isSuccessAll.toString(),
                                     OAFwkConstants.STATEMENT);
                                     
        if(pageContext != null && webBean != null){

            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                XxGamMaPaymentReqVOImpl detailImpl = amXxGamMod.getXxGamMaPaymentReqVO2();
                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                             "Get detail Implementation",
                                             OAFwkConstants.STATEMENT);
                if(detailImpl != null){
                   
                    RowSetIterator rowSetIter = detailImpl.getRowSetIterator();
                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                 "Get detail Iterator",
                                                 OAFwkConstants.STATEMENT);
                    if(rowSetIter != null){
                        pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                     "Row count of details payment advance : " + rowSetIter.getRowCount(),
                                                     OAFwkConstants.STATEMENT);
                        rowSetIter.reset();
                        pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                     "Reset detail Iterator",
                                                     OAFwkConstants.STATEMENT);
                        while(rowSetIter.hasNext()){
                            Row row = rowSetIter.next();
                            XxGamMaPaymentReqVORowImpl detailRow = (XxGamMaPaymentReqVORowImpl)row;
                            if(detailRow != null){
                                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                             "Get current row of details payment advance : " + detailRow.toString(),
                                                             OAFwkConstants.STATEMENT);
                                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                             "Call method XxGamMAnticiposUtil.validationByLine",
                                                             OAFwkConstants.STATEMENT);
                                                             
                                Map mapResult = validationByLine(pageContext, webBean, detailRow, false);
                                Boolean isSuccess = false;
                                if(mapResult != null){
                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                 "Get return Map result of method XxGamMAnticiposUtil.validationByLine: " + mapResult.toString(),
                                                                 OAFwkConstants.STATEMENT);
                                    int intResult = (Integer)mapResult.get("intResult");
                                    if(intResult == 0){
                                        isSuccess = true;
                                    }
                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                 "Get value of key isSuccess of Map: " + isSuccess,
                                                                 OAFwkConstants.STATEMENT);
                                }
                                
                                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                             "Validates key isSuccess of Map result",
                                                             OAFwkConstants.STATEMENT);
                                if(isSuccess){
                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                 "Validates attribute isPaymentValid of Detail Row",
                                                                 OAFwkConstants.STATEMENT);
                                    if(detailRow.getIsPaymentValid() != null){
                                        if(!detailRow.getIsPaymentValid().booleanValue()){
                                            isSuccessAll = false;
                                            pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                         "Set Return parameter in false",
                                                                         OAFwkConstants.STATEMENT);
                                            break;
                                        }
                                    }else{
                                        isSuccessAll = false; 
                                        pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                     "Set Return parameter in false",
                                                                     OAFwkConstants.STATEMENT);
                                        break;
                                    }
                                }else{
                                    isSuccessAll = false;
                                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                                 "Set Return parameter in false and break details row iteration",
                                                                 OAFwkConstants.STATEMENT);
                                    break;
                                }
                            }else{
                                isSuccessAll = false;
                                break;
                            }
                        }
                        pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                     "Reset detail Iterator",
                                                     OAFwkConstants.STATEMENT);
                        rowSetIter.reset();
                    }
                }
            }
        }
        pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                     "Return parameter : " + isSuccessAll,
                                     OAFwkConstants.STATEMENT);
        pageContext.writeDiagnostics(XxGamMAnticiposUtil.class, "Leaving refreshAllValidationByLine",
                                     OAFwkConstants.PROCEDURE);
        return isSuccessAll;
    }

    /**
     * Actualiza la validacion de todas las lineas de anticipo para verificar que no sea de tipo repetido
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     * @return devuelve true si la validacion es correcta o false en caso contrario
     */
    public static boolean refreshAllValidationRepeat(OAPageContext pageContext,
                                              OAWebBean webBean){
        
        boolean isSuccessAll = true;
        if(pageContext != null && webBean != null){
        
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                XxGamMaPaymentReqVOImpl detailImpl = amXxGamMod.getXxGamMaPaymentReqVO2();
                pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                             "Get detail Implementation",
                                             OAFwkConstants.STATEMENT);
                if(detailImpl != null){
                   
                    RowSetIterator rowSetIter = detailImpl.getRowSetIterator();
                    pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                 "Get detail Iterator",
                                                 OAFwkConstants.STATEMENT);
                    if(rowSetIter != null){
                        pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                     "Row count of details payment advance : " + rowSetIter.getRowCount(),
                                                     OAFwkConstants.STATEMENT);
                        rowSetIter.reset();
                        pageContext.writeDiagnostics("XxGamMAnticiposUtil",
                                                     "Reset detail Iterator",
                                                     OAFwkConstants.STATEMENT);
                        while(rowSetIter.hasNext()){
                            Row row = rowSetIter.next();
                            XxGamMaPaymentReqVORowImpl detailRow = (XxGamMaPaymentReqVORowImpl)row;
                            if(detailRow != null){
                                boolean isValid = false;
                                isValid = XxGamMAnticiposUtil.validatesTypePaymentRepeated(pageContext, webBean, null, detailRow);
                                if(!isValid){
                                    isSuccessAll = false;
                                    break;
                                }
                            }
                        }
                        rowSetIter.reset();
                    }
                }
            }
        }
        
        return isSuccessAll;
    }

    /**
     * Calcula el importe a divisa mexicana por cada linea de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     * @param rowRef contiene la referencia del registro del detalle de anticipo
     * @param detailRow contiene el registro del detalle del anticipo
     * @return devuelve true cuando fue posible calcular el importe
     */
    public static boolean calculateAmountMxPaymentReqDetail(OAPageContext pageContext,
                                                         OAWebBean webBean,
                                                         String rowRef,
                                                         XxGamMaPaymentReqVORowImpl detailRow) {
        boolean isSuccess = false;                                                         
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if(amXxGamMod != null){
                isSuccess = amXxGamMod.calculateAmountMxPaymentReqDetail(rowRef, detailRow);    
            }
        }
        return isSuccess;
    }

    /**
     * Calcula el total del importe de la solicitud de anticipo, realiza la suma y conversion de cada importe de linea de anticipo
     * a la divisa de reembolso
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     * @return devuelve true si el calculo se asigno correctamente, falso en caso contrario
     */
    public static boolean calculateAmountTotalGeneralReq(OAPageContext pageContext,
                                                         OAWebBean webBean) {
        boolean isSuccess = false;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if(amXxGamMod != null){
                isSuccess = amXxGamMod.calculateAmountTotalGeneralReq();    
            }
        }
        return isSuccess;
    }


    /**
     * Busca las solicitudes de boleto de avi�n.
     * @param pageContext Pagina de contexto.
     * @param webBean web bean.
     * @param nameRequester Nombre del solicitante.
     * @param numberPayment contiene numero de documento de la solicitud de anticipo
     * @param typeEm Tipo de emisiÃ³n.
     * @param statusReq Estatus de la solicitud.
     * @param fromDate Fecha inicio.
     * @param toDate Fecha fin.
     * @param statusNotiCode contiene codigo del estatus de notificacion
     */
    public static void searchTicketRequest(OAPageContext pageContext,
                                           OAWebBean webBean,
                                           String nameRequester, String numberPayment,
                                           String typeEm, String statusReq,
                                           Date fromDate, Date toDate, String statusNotiCode) {

        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return;
        String sUserName = null;
        sUserName = pageContext.getUserName();


        XxGamModAntAMImpl amXxGamMod = null;
        try {
            amXxGamMod = getApplicationModule(pageContext, webBean);
            amXxGamMod.searchTicketRequest(nameRequester, numberPayment,
                                           typeEm, statusReq, fromDate,
                                           toDate,
                                           sUserName,
                                           statusNotiCode);
        } catch (Exception exception) {
            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                              XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_NF_ERROR,
                                              null, OAException.WARNING, null);
        }
    }


    /**
     *  Obtiene el folio del current ticket
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     * @return devuelve el folio del current ticket
     */
    public static String getFolioTicket(OAPageContext pageContext,
                                        OAWebBean webBean,String requestType) {
        String folio = null;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            folio = amXxGamMod.findTicketFolio(requestType);
        }
        return folio;
    }
    

     public static String getGeneralReqIdF(OAPageContext pageContext,
                                         OAWebBean webBean) {
         String GeneralReqId = null;
         if (pageContext != null && webBean != null) {
             XxGamModAntAMImpl amXxGamMod = null;
             amXxGamMod = getApplicationModule(pageContext, webBean);
             GeneralReqId = amXxGamMod.findGeneralReqIdF();
         }
         return GeneralReqId;
     }

    public static String getGeneralReqIdR(OAPageContext pageContext,
                                        OAWebBean webBean) {
        String GeneralReqId = null;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            GeneralReqId = amXxGamMod.findGeneralReqIdR();
        }
        return GeneralReqId;
    }
    
    /**
     *  Obtiene el id del currentticket
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     */
    public static void getMeaningsForAdvance(OAPageContext pageContext, OAWebBean webBean) {
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            amXxGamMod.getMeaningsForAdvance();
        }
    }

    /**
     *  Obtiene el id del currentticket
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     */
    public static void getMeaningsForFranchise(OAPageContext pageContext, OAWebBean webBean) {
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            amXxGamMod.getMeaningsForFranchise();
            
            
            
            
            
            amXxGamMod.getMeaningsForAdvance();
            
            
            
            
        }
    }
 /**
     * 
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     * @param nGeneralReq id
     */
    public static void searchGeneralReq(OAPageContext pageContext,
                                        OAWebBean webBean,
                                        Number nGeneralReq) {
        //Verifica nulidad
        if (pageContext == null || webBean == null || nGeneralReq == null)
            return;
        try {
            //Busca el registro
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            amXxGamMod.searchGeneralReq(nGeneralReq);
        } catch (Exception exception) {

            throw new OAException("No es posible encontrar el registro",
                                  OAException.WARNING);
        }

    }


    /**
     *  Obtiene el Status de la solicitud
     * @param pageContext
     * @param webBean
     * @return devuelve el status de la solicitud
     */
    public static String getGeneralStatus(OAPageContext pageContext, OAWebBean webBean) {
        String status = null;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            status = amXxGamMod.findGeneralStatus();
        }
        return status;
    }


    /**
     *  Obtiene el Status de la solicitud
     * @param pageContext
     * @param webBean
     * @return devuelve el tipo de solicitud
     */
    public static String getRequestType(OAPageContext pageContext, OAWebBean webBean) {
        String requestType = null;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if(amXxGamMod != null){
                requestType = amXxGamMod.getRequestType();    
                if(requestType == null){
                    if(XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                            webBean, 
                                                            new Number(pageContext.getResponsibilityId()), 
                                                            XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)){
                        requestType = XxGamConstantsUtil.REQUEST_TYPE_ADVANCE;
                    }else{
                        if(XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                            webBean, 
                                                            new Number(pageContext.getResponsibilityId()), 
                                                            XxGamConstantsUtil.RESPONSABILITY_FRANCHISE)
                          ||XxGamConstantsUtil.RESPONSABILITY_FRANCHISE.equals(pageContext.getParameter("pRequest"))){
                            requestType = XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE;
                        }
                    }
                }
            }
        }
        return requestType;
    }
        
        
        
    /**
     *  Obtiene el Status de la solicitud
     * @param pageContext
     * @param webBean
     * @return devuelve el request_id
     */
    public static Number getRequestId(OAPageContext pageContext, OAWebBean webBean) {
        Number requestId = null;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            requestId = amXxGamMod.getRequestId();
        }
        return requestId;
    }


    /**
     *  Obtiene Datos requeridos para invocar el workflow
     * @param pageContext
     * @param webBean
     * @return devuelve un areglo con los datos para ejecutar el workflow
     */
    public static String[] getDataForWorkFlowOffieceTicket(OAPageContext pageContext, OAWebBean webBean) {
        String infoForWorkFlow[] = new String[6];
        
        Number requestId = null;
        Number employeeId = null;
        Number approverId = null;
        String office = null;
        String numTicket = null;
        String routes = null;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod = null;
            
            String requestType = null;
            requestType = 
                    XxGamMAnticiposUtil.getRequestType(pageContext, webBean);
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            requestId = amXxGamMod.findRequestAdvId();
            employeeId = amXxGamMod.findEmployeeId();
            office = amXxGamMod.findOffice(requestType);
            numTicket = amXxGamMod.findFolio(requestType);
            approverId = amXxGamMod.findApproverId();
            routes = XxGamMAnticiposUtil.getNumberOfRoutes(pageContext, webBean, XxGamMAnticiposUtil.getRequestType(pageContext, 
                                                                                                                   webBean));
            if(requestId != null &&
               employeeId != null &&
               office != null &&
               approverId != null &&
               routes != null){
                infoForWorkFlow[0] = routes;
                infoForWorkFlow[1] = employeeId.toString();
                infoForWorkFlow[2] = office;
                infoForWorkFlow[3] = numTicket;
                infoForWorkFlow[4] = approverId.toString();
                infoForWorkFlow[5] = requestId.toString();    
            }
        }
        return infoForWorkFlow;
    }

    /**
     * Busca el registro por id.
     *
     */
    public static Number searchFlightByTicketId(OAPageContext pageContext,
                                                OAWebBean webBean,
                                                Number nTicket) {
        Number id = null;
        //Verifica nulidad
        if (pageContext == null || webBean == null || nTicket == null)
            return null;
        try {
            //Busca el registro
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            id = amXxGamMod.searchFlightByTicketId(nTicket);
        } catch (Exception exception) {

            throw new OAException("No es posible encontrar el registro",
                                  OAException.WARNING);
        }
        return id;
    }


    public static Number searchTicketByGeneralReq(OAPageContext pageContext,
                                                  OAWebBean webBean,
                                                  Number nGeneralReq) {

        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return null;
        try { //Busca el registro
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            amXxGamMod.searchFlightByGeneralReqId(nGeneralReq);
            //  id = amXxGamMod.searchFlightByTicketId(nTicket)
        } catch (Exception exception) {


            throw new OAException("No es posible encontrar el registro",
                                  OAException.WARNING);
        }
        return null;

    }

    /**
     * Inicializa los lookups para las listas de valores.
     *
     * @param pageContext Contexto de la pagina.
     * @param webBean web bean.
     * @param lookupType tipo de lookup.
     * @param applicationId Clave de la aplicaciÃ³n.
     */
    public static void setLookupType(OAPageContext pageContext,
                                     OAWebBean webBean, String lookupType,
                                     Number applicationId) {
        //Verifica nulidad
        if (pageContext == null || webBean == null || lookupType == null ||
            applicationId == null)
            return;

        try {

            //Declaracion de los recursos
            XxGamModAntAMImpl amXxGamMod = null;
            XxGamModAntLovAMImpl amLookups = null;

            //Obtiene el am de lookups
            amXxGamMod = getApplicationModule(pageContext, webBean);
            amLookups =
                    (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();

            //Inicializa los valores
            amLookups.setLookup(lookupType, applicationId);

        } catch (Exception exception) {

            //Propaga la excepcion
            throw new OAException("No es posible obtener la lista de valores",
                                  OAException.WARNING);
        }


    }

    /**
     * Crea un registro nuevo de Ticket del currentRow de Payments en la responsabilidad de Empleados
     * @param pageContext
     * @param webBean
     */
    public static void CreateNewTicket(OAPageContext pageContext,
                                       OAWebBean webBean) {
        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return;
            
        /** AGAA Obtiene la Filial **/
         String filial = (String)pageContext.getTransactionValue("orgNameEmp");
        
        //Declaracion de los recursos
        XxGamModAntAMImpl amXxGamMod = null;

        //Obtiene el AM y ejecuta la accion
        amXxGamMod = getApplicationModule(pageContext, webBean);
        amXxGamMod.createNewTicket(filial);
    }


    /**
     * Invoca la implementacion del AM para realizar la busqueda de los Tickets y vuelos de la responsabilidad de empleados
     * @param pageContext
     * @param webBean
     */
    public static void searchDataToUpdateEmployees(OAPageContext pageContext,
                                                   OAWebBean webBean,
                                                   Number idGeneralReq,
                                                   Number idPayment,
                                                   Number idTicket) {
        //Verifica nulidad
        if (pageContext == null || webBean == null || idPayment == null ||
            idTicket == null)
            return;

        //Declaracion de los recursos
        XxGamModAntAMImpl amXxGamMod = null;
        //Controla cualquier excepcion ocurrida
        try {
            //Obtiene el AM y ejecuta la accion
            amXxGamMod = getApplicationModule(pageContext, webBean);

            amXxGamMod.searchDataForUpdateEmployeesTicketsAndFlight(idGeneralReq,
                                                                    idPayment,
                                                                    idTicket);
        } catch (Exception exception) {
            System.out.println("CreateNewTicket " + exception.getMessage());
            //Propaga el error generado
            throw new OAException("Error al buscar los datos",
                                  OAException.ERROR);
        }
    }


    /**
     * Crea un registro nuevo de Ticket apartir del ID de General Req "Solo para Franquicias"
     * @param pageContext
     * @param webBean
     */
    public static void createTicketWithGeneralReq(OAPageContext pageContext,
                                                  OAWebBean webBean) {
        if (pageContext == null || webBean == null)
            return;
        try { //Busca el registro
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            amXxGamMod.createRowTicketFranchise();
        } catch (Exception exception) {
            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                              XxGamAOLMessages.GenericType.XXGAM_MAF_TKT_CREATE_ERROR,
                                              null, OAException.ERROR, null);
        }
    }

    /**
     * Inicializa la lov de proposito de la solicitud de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     */
    public static void initPorpuseLov(OAPageContext pageContext,
                                      OAWebBean webBean) {
        if (pageContext != null && webBean != null) {

            XxGamModAntAMImpl am =
                XxGamMAnticiposUtil.getApplicationModule(pageContext, webBean);

            if (am != null) {
                XxGamModAntLovAMImpl amLov = null;
                amLov = (XxGamModAntLovAMImpl)am.getXxGamModAntLovAM1();
                if (amLov != null) {
                    amLov.initPurposeLov();
                }
            }
        }
    }

    /**
     * Configura los valores iniciales de tarjeta virtual para anticipos y franquicias por medio
     * de la responsabilidad
     * @param responsability contiene la responsabilidad
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     * @return devuelve true si la inicializacion es correcta o false en caso contrario
     */
    public static boolean initVirtualCard(String responsability,
                                          OAPageContext pageContext,
                                          OAWebBean webBean) {

        boolean isSucces = false;
        if (responsability != null && pageContext != null && webBean != null) {

            XxGamModAntAMImpl am =
                XxGamMAnticiposUtil.getApplicationModule(pageContext, webBean);
            if (am != null) {
                isSucces = am.initVirtualCard(responsability);
            }
        } else {
            throw new OAException("Error al inicializar el valor de tarjeta virtual, parametros no validoss",
                                  OAException.ERROR);
        }
        return isSucces;
    }

    /**
     * Configura los valores iniciales para el centro de costo por defecto del usuario
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     * @return devuelve true si la inicializacion es correcta o false en caso contrario
     */
    public static boolean initCostCenter(OAPageContext pageContext,
                                         OAWebBean webBean) {
        boolean isSuccess = false;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl am =
                XxGamMAnticiposUtil.getApplicationModule(pageContext, webBean);
            if (am != null) {
                Number responsabilityId = null;
                responsabilityId = new Number(pageContext.getResponsibilityId());
                isSuccess = am.initCostCenter(pageContext.getUserName(),
                                  responsabilityId);
            }
        }
        return isSuccess;
    }
    
    /**
     * Configura los valores iniciales para el centro de costo alterno
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     * @return devuelve true si la inicializacion es correcta o false en caso contrario
     */
    public static boolean initCostCenterFlex(OAPageContext pageContext,
                                         OAWebBean webBean) {
        boolean isSuccess = false;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl am =
                XxGamMAnticiposUtil.getApplicationModule(pageContext, webBean);
            if (am != null) {
                Number responsabilityId = null;
                responsabilityId = new Number(pageContext.getResponsibilityId());
              /** Comenntado 09/07/2015 para usar un nuevo metodo sobrecargado
              isSuccess = am.initCostCenterFlex(responsabilityId);
              **/
               isSuccess = am.initCostCenterFlex(responsabilityId,pageContext,webBean);
            }
        }
        return isSuccess;
    }

    /**
     * Inicializa la lista de valores de platilla de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @return devuelve true en caso de una inicializacion correcta o false en caso contrario
     */
    public static boolean initTemplatePayment(OAPageContext pageContext, OAWebBean webBean){
        
        boolean isSuccess = false;
        if(pageContext != null && webBean != null){
            XxGamModAntAMImpl am =
                XxGamMAnticiposUtil.getApplicationModule(pageContext, webBean);
            
            if(am != null){
                isSuccess = am.initTemplatePayment();
            }
        }
        return isSuccess;
    }
    
    /**
     * Configura los valores iniciales para el aprobador de jerarquia RH
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     * @return devuelve true si la inicializacion es correcta o false en caso contrario
     */
    public static boolean initApproverHierarchy(OAPageContext pageContext,
                                                OAWebBean webBean){
        boolean isSuccessReturn = false;
        if (pageContext != null && webBean != null) {
            XxGamModAntAMImpl amXxGamMod =
                XxGamMAnticiposUtil.getApplicationModule(pageContext, webBean);
            if (amXxGamMod != null) {

                XxGamMaGeneralReqVOImpl voGeneralReq = null;
                voGeneralReq = amXxGamMod.getXxGamMaGeneralReqVO1();

                if (voGeneralReq != null) {
                    Row row = voGeneralReq.getCurrentRow();
                    if (row != null) {
                        XxGamMaGeneralReqVORowImpl generalReqRow =
                            (XxGamMaGeneralReqVORowImpl)row;
                        
                        if(generalReqRow != null){
                        
                            if(!generalReqRow.getIsApproverBySys() && generalReqRow.getEmployeeId() != null){
                                
                                XxGamMaUserDataLovVORowImpl userDataFound = null;
                                
                                XxGamModAntLovAMImpl amLov = (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();
                                if(amLov != null){
                                    userDataFound = amLov.getUserDataByPersonId(generalReqRow.getEmployeeId());
                                    
                                    if(userDataFound != null){
                                        Number userId = null;
                                        userId = userDataFound.getUserId();
                                        isSuccessReturn = initApproverHierarchyLov(pageContext, webBean, userId);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return isSuccessReturn;                                            
    }

    /**
     * Inicializa la lista de valores que contiene los aprobadores por jerarquia RH
     * @param pageContext contiene el objeto OAPageContext procedente de la pantalla
     * @param webBean contiene el objeto OAWebBean procedente de la pantalla
     * @param personId contiene el id persona del empleado
     * @return devuelve true si la inicializacion es correcta o false en caso contrario
     */
    public static boolean initApproverHierarchyLov(OAPageContext pageContext,
                                                   OAWebBean webBean,
                                                   Number personId
                                                   ){
        boolean isSuccessReturn = false;                                                   
        if(pageContext != null && webBean != null && personId != null){
            
            XxGamModAntAMImpl amXxGamMod =
                XxGamMAnticiposUtil.getApplicationModule(pageContext, webBean);
                
            if(amXxGamMod != null){
                Map map = validApproverTypeByEmployee(pageContext, webBean, personId);
                
                Number positionId = null;
                Boolean isApproverSys = null;
                
                if(map != null){
                    positionId = (Number)map.get("positionId");
                    isApproverSys = (Boolean)map.get("isApproverSys");
                }
                
                if(isApproverSys != null){
                
                    if(!isApproverSys.booleanValue()){
                        
                        if(positionId != null){
                        
                            Number jobNameId = null;
                            Number versionId = null;
                            
                            java.util.Map mapResult = amXxGamMod.callProceduceGetPositionEmployee(positionId);
                            if(mapResult != null){
                                jobNameId = (Number)mapResult.get("jobNameId");
                                versionId = (Number)mapResult.get("versionId");
                            }
                            
                            //Configura lista de valores para aprobadores
                            isSuccessReturn =
                                    amXxGamMod.initApproverHierarchy(jobNameId, versionId);
                        }
                    }
                }
            }
        }
        return isSuccessReturn;
    }

    /**
     * Elimina vuelos de la responsabilidad de empleados
     * @param pageContext
     * @param webBean
     * @param FlightId
     */
    public static void deleteRowEmployee(OAPageContext pageContext,
                                         OAWebBean webBean, Number FlightId) {

        if (pageContext == null || webBean == null)
            return;
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);
        amXxGamMod.deleteRowEmployee(FlightId);
    }

    /**
     * Elimina vuelos de la responsabilidad de empleados
     * @param pageContext
     * @param webBean
     * @param FlightId
     */
    public static void deleteRowFranchise(OAPageContext pageContext,
                                          OAWebBean webBean, Number FlightId) {

        if (pageContext == null || webBean == null)
            return;
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);
        amXxGamMod.deleteRowFranchise(FlightId);
    }
    
    /**
     * Cancela vuelos de la responsabilidad de empleados
     * @param pageContext
     * @param webBean
     * @param FlightId
     */
    public static void cancelRowEmployee(OAPageContext pageContext,
                                         OAWebBean webBean, Number FlightId) {

        if (pageContext == null || webBean == null)
            return;
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);
        amXxGamMod.cancelRowEmployee(FlightId);
    }
    
    /**
     * Cancela vuelos de la responsabilidad de empleados
     * @param pageContext
     * @param webBean
     * @param FlightId
     */
    public static void cancelRowFranchise(OAPageContext pageContext,
                                          OAWebBean webBean, Number FlightId) {

        if (pageContext == null || webBean == null)
            return;
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);
        amXxGamMod.cancelRowFranchise(FlightId);
    }

    /**
     * Obtiene la secuencia del workflow de oficinas de boletos
     * @param pageContext
     * @param webBean
     * @return devuelve la secuencia del workflow
     */
    public static

    Number getSequenceForWorkFlow(OAPageContext pageContext,
                                  OAWebBean webBean) {
        Number wfSequence = null;

        if (pageContext == null || webBean == null)
            return null;
        try { //Busca el registro
             XxGamModAntAMImpl amXxGamMod = null;
             amXxGamMod = getApplicationModule(pageContext, webBean);
             wfSequence = amXxGamMod.getSequenceForWorkFlow();
        } catch (Exception exception) {
        
           throw new OAException("No es posible crear el Ticket",
           OAException.WARNING);
        }
        return wfSequence;
    }

    /**
     * Calcula la diferencia de dias entre dos fechas
     * @param initDate contiene la fecha de inicio
     * @param finalDate contiene la fecha final
     * @return devuelve un valor numerico representativo del numero de dias
     */
    public long differenceDays(java.util.Date initDate, java.util.Date finalDate){
        long diff = 0;
        if(initDate != null && finalDate != null){
            
            Calendar calInitDate = Calendar.getInstance();
            Calendar calFinalDate = Calendar.getInstance();
            
            calInitDate.setTime(initDate);
            calFinalDate.setTime(finalDate);
            
            long miliInitDate = calInitDate.getTimeInMillis();
            long miliFinalDate = calFinalDate.getTimeInMillis();
            
            long diffAux = 0;
            diffAux = miliFinalDate - miliInitDate;
            
            diff = diffAux / (24 * 60 * 60 * 1000);
        }
        return diff;
    }

    /**
     * Valida dos fechas, verificando que la fecha inicio sea menor o igual a la fecha fin en formato
     * dd/MM/yyyy
     * @param initDate contiene la fecha inicio
     * @param finalDate contiene la fecha fin
     * @return devuelve true si la fecha inicio es menor o igual a la fecha fin y false en caso contrario
     */
    public static boolean validateDates(java.util.Date initDate, java.util.Date finalDate){
        boolean isValid = false;
        if(initDate != null && finalDate != null){
            
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            
            String strInitDate = null;
            String strFinalDate = null;
            
            try {
                strInitDate = formatDate.format(initDate);
                initDate = formatDate.parse(strInitDate);
            } catch (ParseException e) {
                strInitDate = null;
            }

            try {
                strFinalDate = formatDate.format(finalDate);
                finalDate = formatDate.parse(strFinalDate);
            } catch (ParseException e) {
                strFinalDate = null;
            }
            
            if (initDate != null && finalDate != null){
                if (initDate.compareTo(finalDate) <= 0){
                    isValid = true;
                }
            }
        }
        return isValid;
    }

    /**
     * Valida que la fecha sea igual o mayor a la fecha actual
     * @param date contiene la fecha a validar
     * @return devuelve true si es valida o false en caso contrario
     */
    public static boolean validateNotCurrentDate(java.util.Date date){
        
        boolean isValid = false;
        if(date != null){
        
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            
            String strDate = null;
            String strCurrentDate = null;
            
            try {
                strDate = formatDate.format(date);
                date = formatDate.parse(strDate);
            } catch (ParseException e) {
                date = null;
            }
        
            java.util.Date currentDate = null;
            oracle.jbo.domain.Date orcCurrentate = XxGamMAnticiposUtil.getFechaActual();
            if(orcCurrentate != null){
                currentDate = orcCurrentate.getValue();
            }
            
            try {
                strCurrentDate = formatDate.format(currentDate);
                currentDate = formatDate.parse(strCurrentDate);
            } catch (ParseException e) {
                currentDate = null;
            }
            
            if(date != null && currentDate != null){
                if (currentDate.compareTo(date) <= 0){
                    isValid = true;
                }
            }
        }
        return isValid;
    }

    /**
     * Valida la fecha inicio y fecha fin de una linea de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param rowRef contiene la referencia del registro
     * @return devuelve true en caso de que las fechas sean validas y false en caso contrario
     */
    public static boolean validateDatesDetailFromPage(OAPageContext pageContext, OAWebBean webBean, String rowRef){
        
        boolean isValid = false;
        if(pageContext != null && webBean != null && rowRef != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                
                XxGamMaPaymentReqVORowImpl detailRow = null;
                
                if(rowRef != null){
                    detailRow = (XxGamMaPaymentReqVORowImpl)amXxGamMod.findRowByRef(rowRef); 
                }
                
                if(detailRow != null){
                    
                    java.util.Date initDate = null;
                    java.util.Date finalDate = null;
                    
                    if(detailRow.getInitialDate() != null ){
                        initDate = detailRow.getInitialDate().getValue();
                    }
                    if(detailRow.getFinalDate() != null){
                        finalDate = detailRow.getFinalDate().getValue();
                    }
                    
                    if(initDate != null && finalDate != null){
                    
                        isValid = XxGamMAnticiposUtil.validateDates(initDate, finalDate);
                        if(!isValid){
                            detailRow.setFinalDate(null);    
                        }
                    }else{
                        isValid = true;
                    }
                }
            }            
        }
        return isValid;
    }
    
    /**
     * Valida la fecha inicio y fecha fin para ser mayor o igual a la fecha actual
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param rowRef contiene la referencia del registro
     * @param rowVo contiene el row de detalle de anticipo
     * @return devuelve true en caso de que las fechas sean validas y false en caso contrario
     */
    public static boolean validateNotLessCurrentDatesDetailFromPage(OAPageContext pageContext, OAWebBean webBean, String rowRef, XxGamMaPaymentReqVORowImpl rowVo){
        
        boolean isValid = false;
        if(pageContext != null && webBean != null && rowRef != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                
                XxGamMaPaymentReqVORowImpl detailRow = null;
                
                if(rowRef != null){
                    detailRow = (XxGamMaPaymentReqVORowImpl)amXxGamMod.findRowByRef(rowRef); 
                }else{
                    if(rowVo != null){
                        detailRow = rowVo;
                    }
                }
                
                if(detailRow != null){
                    isValid = amXxGamMod.validateNotLessCurrentDateDetail(detailRow);    
                }else{
                    isValid = true;
                }
            }            
        }
        return isValid;
    }

    /**
     * Inicializa los registros del detalle de la solicitud de anticipos
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     */
    public static void setIniEditDetail(OAPageContext pageContext, OAWebBean webBean){
        
        if(pageContext != null && webBean != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                amXxGamMod.setIniEditDetail();
            }
        }
    }

    /**
     * Valida y modifica el valor de las fechas de las lineas de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     */
    public static void setValidatesAllDatesPaymentDetail(OAPageContext pageContext, OAWebBean webBean){
        if(pageContext != null && webBean != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                amXxGamMod.setValidatesAllDatesPaymentDetail();
            }
        }
    }
    
    /**
     * Valida la fecha de salida y fecha de llegada de un registro de vuelo
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param rowRef contiene la referencia del registro
     * @return devuelve true en caso de que las fechas sean validas y false en caso contrario
     */
    public static boolean validateDatesFlightFromPage(OAPageContext pageContext, OAWebBean webBean, String rowRef){
        
        boolean isValid = false;
        if(pageContext != null && webBean != null && rowRef != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                
                XxGamMaFlightInf0VORowImpl flightRow = null;
                
                if(rowRef != null){
                    flightRow = (XxGamMaFlightInf0VORowImpl)amXxGamMod.findRowByRef(rowRef); 
                }
                
                if(flightRow != null){
                    
                    java.util.Date initDate = null;
                    java.util.Date finalDate = null;
                    
                    if(flightRow.getDepartureDate() != null ){
                        initDate = flightRow.getDepartureDate().getValue();
                    }
                    if(flightRow.getReturnDate() != null){
                        finalDate = flightRow.getReturnDate().getValue();
                    }
                    
                    if(initDate != null && finalDate != null){
                        isValid = XxGamMAnticiposUtil.validateDates(initDate, finalDate);
                        if(!isValid){
                            flightRow.setReturnDate(null);    
                        }
                    }else{
                        isValid = true;
                    }
                }
            }            
        }
        return isValid;
    }
    
    /**
     * Valida que la fecha de salida y fecha de llegada sean igual o mayor a la fecha actual
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param rowRef contiene la referencia del registro
     * @return devuelve true en caso de que las fechas sean validas y false en caso contrario
     */
    public static boolean validateNotLessCurrentDateFlightFromPage(OAPageContext pageContext, OAWebBean webBean, String rowRef){
        
        boolean isValid = false;
        if(pageContext != null && webBean != null && rowRef != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                
                XxGamMaFlightInf0VORowImpl flightRow = null;
                
                if(rowRef != null){
                    flightRow = (XxGamMaFlightInf0VORowImpl)amXxGamMod.findRowByRef(rowRef); 
                }
                
                if(flightRow != null){
                    isValid = amXxGamMod.validateNotLessCurrentDateFlight(flightRow);
                }else{
                    isValid = true;
                }
            }            
        }
        return isValid;
    }

    /**
     * Valida y modifica todas los registros de vuelos de la solicitud de boleto de avion AM
     * cuando las fechas de salida y llegada son menores a la fecha actual del sistema
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param typeRequest contiene el tipo de solicitud
     */
    public static void setValidatesAllDatesFlight(OAPageContext pageContext, OAWebBean webBean, String typeRequest){
        if(pageContext != null && webBean != null && typeRequest != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                amXxGamMod.setValidatesAllDatesFlight(typeRequest);
            }
        }
    }

    /**
     * Reserva fondos para la solicitud seleccionada.
     * 
     * @param pageContext Contexto de la pagina.
     * @param webBean web bean.
     * @return devuelve null cuando la reserva de fondos se ejecuta exitosamente y devuelve una cadena con mensaje de error
     *         cuando ocurren errores segun el caso en especifico
     */
    public static String getReservedFounds(OAPageContext pageContext,
                                         OAWebBean webBean) {
        String msgError = null;
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);
        
        //Instancia AM de LOV
        XxGamModAntLovAMImpl amLov = null;
        amLov = (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();
        
        String responsibility = null;
        if(amLov != null){
            XxGamMaResponsibilityAppLovVORowImpl respRow = null;
            respRow = amLov.getResponsibilityAppById(new Number(pageContext.getResponsibilityId()));
            
            if(respRow != null){
                responsibility = respRow.getResponsibilityKey().toUpperCase().trim();    
            }
        }
        
        //Valida contenido del detalle del anticipo
        boolean isContentValid = false;
        if (XxGamMAnticiposUtil.validatesResponsability(pageContext, webBean, new Number(pageContext.getResponsibilityId()), XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)) {
            if(amXxGamMod.getXxGamMaPaymentReqVO2() != null){
                if(amXxGamMod.getXxGamMaPaymentReqVO2().getRowCount() > 0 ){
                    isContentValid = true;    
                }
            }
        }
        
        if(isContentValid){
            
            XxGamMaGeneralReqVOImpl voGeneralReq = null;
            XxGamMaGeneralReqVORowImpl rowGeneralReq = null;
            Number requestId = null;
            
            //Obtiene la implementaciÃ³n
            voGeneralReq = amXxGamMod.getXxGamMaGeneralReqVO1();
            if(voGeneralReq != null){
                rowGeneralReq =
                        (XxGamMaGeneralReqVORowImpl)voGeneralReq.getCurrentRow();
                
                if(rowGeneralReq != null){
                    requestId = rowGeneralReq.getId();       
                }
            }
            
            if(requestId != null){
                //Ejecuta el procedimiento de reserva de fondos
                /** int intStatus = 0; **/
                int intStatus = amXxGamMod.getReserveFunds(); 
              pageContext.writeDiagnostics(XxGamMAnticiposUtil.class, "Agregado para Sistutuir al procedimiento de reservar Fondos XxGamMAnticiposUtil2.getReserveFunds(pageContext,webBean); ", OAFwkConstants.PROCEDURE); 
                System.out.println("Agregado para Sistutuir al procedimiento de reservar Fondos XxGamMAnticiposUtil2.getReserveFunds(pageContext,webBean); "); 
              //  int intStatus2 = XxGamMAnticiposUtil2.getReserveFunds(pageContext,webBean); 
                if(intStatus == 0){
                    msgError = null;
                }else{
                
                    String strNumberPayment = XxGamMAnticiposUtil.getNumberPayment(pageContext, webBean);
                    if(strNumberPayment == null){
                        strNumberPayment = "";
                    }
                    
                    MessageToken[] tokens = {new MessageToken("NUMBER_PAYMENT", strNumberPayment)};
                    msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_RFUNDS_INTER_ERROR1, tokens);
                }
            }else{
                String strNumberPayment = XxGamMAnticiposUtil.getNumberPayment(pageContext, webBean);
                if(strNumberPayment == null){
                    strNumberPayment = "";
                }
                
                MessageToken[] tokens = {new MessageToken("NUMBER_PAYMENT", strNumberPayment)};
                msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_RFUNDS_INTER_ERROR2, tokens);
            }
        }else{
            String strNumberPayment = XxGamMAnticiposUtil.getNumberPayment(pageContext, webBean);
            if(strNumberPayment == null){
                strNumberPayment = "";
            }
            
            MessageToken[] tokens = {new MessageToken("NUMBER_PAYMENT", strNumberPayment)};
            msgError = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_RFUNDS_INTER_ERROR3, tokens);            
        }
        return msgError;
    }

    /**
     * Inicializa el valor del componente con la propiedad render
     *
     * @param webBean Web bean.
     * @param sComponentName Nombre del componente.
     * @param isVisible Indica si es visible o no
     */
    public static void setRenderedComponent(OAWebBean webBean,
                                            String sComponentName,
                                            Boolean isVisible) {
        //Verifica nulidad
        if (webBean == null || sComponentName == null || isVisible == null)
            return;
        OAWebBean oaWebBean = null;
        try {
            //Obtiene el compoente
            
            oaWebBean = webBean.findChildRecursive(sComponentName);

            //Verifica si encontrÃ³ el componente
            if (oaWebBean == null)
                return;

            //Modifica la propiedad rendered
            oaWebBean.setRendered(isVisible);
        } catch (Exception exception) {
             oaWebBean = null;
        }
    }
    
  
    /**
     * habilita o desactiva el componente UI button 
     *
     * @param webBean Web bean.
     * @param sComponentName Nombre del componente.
     * @param isActive Indica si es activo o no
     */
    public static void setDisableSubmitButtonBean(OAWebBean webBean,
                                            String sComponentName,
                                            Boolean isActive) {
        //Verifica nulidad
        if (webBean == null || sComponentName == null || isActive == null)
            return;
        OASubmitButtonBean oaWebBean = null;
        try {
            //Obtiene el compoente
            
            oaWebBean = (OASubmitButtonBean)webBean.findChildRecursive(sComponentName);

            //Verifica si encontrÃ³ el componente
            if (oaWebBean == null)
                return;

            //Modifica la propiedad disabled
            //oaWebBean.setDisabled(isActive);
            oaWebBean.setAttributeValue(oaWebBean.DISABLED_ATTR, isActive);
        } catch (Exception exception) {
             oaWebBean = null;
        }
    }
    
    /**
     * Bloquea o desbloquea peticiones entrantes de las paginas OAF, cuando una peticion ya fue 
     * solicitada y esta transaccionando
     *
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF.
     */
    public static void setBlockOnSubmitBean(OAPageContext pageContext, boolean isBlock) {
        
        //Verifica nulidad
        if (pageContext == null)
            return;
        
        OAWebBean body = pageContext.getRootWebBean();
        if(body instanceof OABodyBean){
            ((OABodyBean)body).setBlockOnEverySubmit(isBlock);
        }
    }

    /**
     * Elimina el registro del detalle de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param rowId contiene el id del registro
     * @return devuelve true en caso de eliminacion exitosa o false en caso contrario
     */
    public static boolean deleteRowPaymentDetail(OAPageContext pageContext, OAWebBean webBean, Number rowId){
           
           boolean isSuccess = false;
           if(pageContext != null && webBean != null && rowId != null){
               XxGamModAntAMImpl amXxGamMod = null;
               amXxGamMod = getApplicationModule(pageContext, webBean);
               
               if(amXxGamMod != null){
                   XxGamMaPaymentReqVOImpl detailImpl = null;
                   detailImpl = amXxGamMod.getXxGamMaPaymentReqVO2();
                   
                   if(detailImpl != null){
                   
                       RowSetIterator rowSetIter = detailImpl.getRowSetIterator();
                       XxGamMaPaymentReqVORowImpl detailRowVo = null;
                       if(rowSetIter != null){
                           rowSetIter.reset();
                           while(rowSetIter.hasNext()){
                               Row row = rowSetIter.next();
                               detailRowVo = (XxGamMaPaymentReqVORowImpl)row;
                               if(detailRowVo != null){
                                   if(detailRowVo.getId().equals(rowId)){
                                       break;
                                   }
                               }
                           }
                           rowSetIter.reset();
                       }
                       
                       if(detailRowVo != null){
                           amXxGamMod.deleteTicketPlaneDetail(null, detailRowVo, true);
                           
                           detailImpl.deleteRowPaymentReq(rowId);
                           boolean isFound = false;
                           while(rowSetIter.hasNext()){
                               Row row = rowSetIter.next();
                               detailRowVo = (XxGamMaPaymentReqVORowImpl)row;
                               if(detailRowVo != null){
                                   if(detailRowVo.getId().equals(rowId)){
                                       isFound = true;
                                   }
                               }
                           }
                           if(!isFound){
                               isSuccess = true;
                           }
                       }
                   }
               }
           }
           return isSuccess;
       }

    /**
     * Elimina el detalle de solicitud de boleto de avion de una linea de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     */
    public static void deleteTicketPlaneDetail(OAPageContext pageContext, OAWebBean webBean, String rowRef){
        if(pageContext != null && webBean != null && rowRef != null){
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
            
                amXxGamMod.deleteTicketPlaneDetail(rowRef, null, false);
            }
        }
    }
    
    /**
     * Ejecuta el WorkFlow de la EBS al generar el ticket
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param generalRow contiene datos del registro de informaciÃ³n general
     * @param ticketRow contiene datos del registro de solicitud de boleto de aviÃ³n
     */
    public static boolean callWorkFlowCreateTicket(OAPageContext pageContext, 
                                          OAWebBean webBean,
                                          XxGamMaGeneralReqVORowImpl generalRow,
                                          XxGamMaTicketPVORowImpl ticketRow,
                                          String route) {
                                          
        boolean isSuccess = false;
        
        if(pageContext != null &&
           webBean != null &&
           generalRow != null &&
           ticketRow != null &&
           route != null){
            isSuccess = true;
        }else{
            isSuccess = false;
        }

        XxGamModAntAMImpl am = null;
        am = getApplicationModule(pageContext, webBean);

        if(isSuccess && am != null){
            int intResult = am.callProcedureNotificationTicketP(generalRow.getEmployeeId(),
                                                                generalRow.getApproverId(),
                                                                ticketRow.getTicketOffice(),
                                                                route);
            if(intResult == 0){
                isSuccess = true;
            }else{
                isSuccess = false;
            }
        }
       
        return isSuccess;
    }
    
    /**
     * Ejecuta el WorkFlow de la EBS al generar el ticket
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param data contiene un arreglo de valores de la solicitud y vuelos
     * @return devuelve true cuando fue posible ejecutar el workflow
     */
    public static boolean callWorkFlowToSendConfirmationOfficeTicket(OAPageContext pageContext, 
                                          OAWebBean webBean,
                                          String [] data) {
        XxGamModAntAMImpl am = null;
        am = getApplicationModule(pageContext, webBean);
        boolean isSuccess = false;
        if(data != null && am != null){
            int intResult = am.callProcedureSendNotificationTicketOffice(data);
            if(intResult == 0){
                isSuccess = true;
            }else{
                isSuccess = false;
            }
        }
        return isSuccess;
    }
    
    
    /**
     * Obtiene Datos requeridos para invocar el workflow
     * @param generalRow contiene el registro de informacion general
     * @param ticketRow contiene el registro de la solicitud de boleto
     * @return Arreglo de Strings que contienen los datos solicitados por el WorkFlow
     */
    public static String[] getDataForWorkFlowOffieceTicket(
                                        OAPageContext pageContext,
                                        OAWebBean webBean,
                                        XxGamMaGeneralReqVORowImpl generalRow,
                                        XxGamMaTicketPVORowImpl ticketRow) {
        String infoForWorkFlow[] = new String[5];
        Number combinationCode = null;
        String costCenter = null;
        String virtualCard = null;
        String pnr = null;
        String contactPhone = null;
        
        if (pageContext != null && 
            webBean != null && 
            generalRow != null && 
            ticketRow != null) {
        
            virtualCard = generalRow.getVirtualCard();
            combinationCode = generalRow.getCostCenter();
            contactPhone = ticketRow.getContactPhone();
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                costCenter = amXxGamMod.findCostCenter(generalRow.getCostCenter());    
            }
            
            pnr = ticketRow.getPnr();

            if (virtualCard == null && 
                combinationCode == null &&
                contactPhone == null &&
                costCenter == null &&
                pnr == null){
                
                infoForWorkFlow = null;
            }else{
                infoForWorkFlow[0]=virtualCard;
                infoForWorkFlow[1]=combinationCode.toString();
                infoForWorkFlow[2]=contactPhone;
                infoForWorkFlow[3]=costCenter;
                infoForWorkFlow[4]=pnr;
            }
        }
        return infoForWorkFlow;
    }

    /**
     * Ejecuta el llamado de la validacion del empleado para determinar como obtener el 
     * aprobador de la solicitud de anticipo o franquicia
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param userId 
     * @return devuelve objeto mapa de tipo <class>java.util.Map</class>, con las llaves:
     * isApproverSys. De tipo <class>java.lang.Boolean</class>. 
     * Su valor es null cuando ocurre error en la validacion, true 
     * cuando el aprobador se obtiene directamente por empleado del 
     * sistema o false cuando el aprobador se obtiene de una lista 
     * de valores proveniente de la jerarquia RH.
     * isSuccess.     De tipo boolean.
     * Indica si la llamada del procedimiento de validaciÃ³n fue 
     * ejecutado correctamente.
     * costCenter.    De tipo <class>java.lang.String</class>. 
     * Su valor contiene el centro de costos.
     * supervisorId.  De tipo <class>oracle.jbo.domain.Number</class>. 
     * Contiene el id del supervisor.
     * codeCombId.    De tipo <class>oracle.jbo.domain.Number</class>. 
     * Contiene el id del cÃ³digo de combinaciÃ³n contable.
     * ledgerId.      De tipo <class>oracle.jbo.domain.Number</class>. 
     * Contiene el id del libro contable.
     * payrollName.   De tipo <class>String</class>. 
     * Contiene descripciÃ³n del grupo de pago.
     * positionId.    De tipo <class>oracle.jbo.domain.Number</class>. 
     * Contiene el id de la posiciÃ³n del empleado en la jerarquia RH.
     * isEmployee.    De tipo <class>java.lang.Boolean</class>. 
     * Indica si el usuario es un empleado.
     */
    public static Map validApproverTypeByEmployee(OAPageContext pageContext, OAWebBean webBean, Number userId){
        
        System.out.println(" Comienza XxGamMAnticipos.validApproverTypeByEmployee "); 
        
        Boolean isApproverSys = null;
        Map map = new java.util.HashMap();
        if(pageContext != null && webBean != null && userId != null){
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                
                map = amXxGamMod.callProcedureValidEmployee(userId);
                
                boolean isSuccess = false;
                String payrollName = null;
                
                if(map != null){
                    isSuccess = (Boolean)map.get("isSuccess");
                    payrollName = (String)map.get("payrollName");
                }
                
                if(isSuccess){
                    
                    if(payrollName != null){
                        if(payrollName.indexOf(XxGamConstantsUtil.GROUP_PAYMENT_USER_SYSTEM) != -1){
                            //Configura bandera para obtener aprobador directamente
                            isApproverSys = true;
                        }else{
                            //Configura bandera para obtener lista de aprobadores   
                            isApproverSys = false;
                        }
                    }else{
                        //Configura bandera para obtener lista de aprobadores
                        isApproverSys = false;
                    }
                }else{
                    //Configura bandera para indicar que ocurrio un error en la validacion
                    isApproverSys = null;
                }
            }
        }
        map.put("isApproverSys", isApproverSys);
        return map;
    }

    /**
     * Configura y asigna los datos del aprobador según su origen correspondiente al
     * usuario
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @return devuelve true en caso de una asignacion exitosa, y false en caso contrario
     */
    public static boolean setOriginApproverInGeneral(OAPageContext pageContext, OAWebBean webBean){
        
        boolean isSuccessReturn = false;
        if(pageContext != null && webBean != null){
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                Number userId = null;
                
                //Instancia AM de LOV
                XxGamModAntLovAMImpl amLov = null;
                amLov = (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();
                
                if(amLov != null){
                    XxGamMaUserDataLovVORowImpl userDataFound =null;
                    userDataFound = amLov.getUserDataByUserName(pageContext.getUserName());
                    if(userDataFound != null){
                        userId = userDataFound.getUserId();
                    }
                }
                
                Map map = validApproverTypeByEmployee(pageContext, webBean, userId);
                
                boolean isSuccess = false;
                String costCenter = null;
                Number supervisorId = null;
                Number codeCombId = null;
                Number ledgerId = null;
                String payrollName = null;
                Number positionId = null;
                Boolean isEmployee = null;
                Boolean isApproverSys = null;
                
                if(map != null){
                    isSuccess = (Boolean)map.get("isSuccess");
                    costCenter = (String)map.get("costCenter");
                    supervisorId = (Number)map.get("supervisorId");
                    codeCombId = (Number)map.get("codeCombId");
                    ledgerId = (Number)map.get("ledgerId");
                    payrollName = (String)map.get("payrollName");
                    positionId = (Number)map.get("positionId");
                    isEmployee = (Boolean)map.get("isEmployee");
                    isApproverSys = (Boolean)map.get("isApproverSys");
                }
                
                if(isApproverSys != null){
                
                    if(isApproverSys.booleanValue()){
                        
                        if(supervisorId != null){
                            //Obtiene el aprobador correspondiente al id del supervisor
                            isSuccessReturn = amXxGamMod.setAppproverBySupervisorId(supervisorId);
                            if (!isSuccessReturn)
                            pageContext.putParameter("ErrorEspecificoAp"," Aprob #1 "); // agregar el put del error dihu 16 Octubre 2014 Error al obetener el aprovador
                        }else{
                             isSuccessReturn = false; 
                            // agregar el put del error dihu 16 Octubre 2014 Error No se encontro Supervisor ID
                             pageContext.putParameter("ErrorEspecificoAp"," Aprob #2 ");
                        }
                    }else{
                        if(positionId != null){
                        
                            Number inmediateApproverId = null;
                            inmediateApproverId = XxGamMAnticiposUtil.getApproverIdByEmpPositionId(pageContext, 
                                                                                                   webBean, 
                                                                                                   positionId);
                            //Configura la lista de valores de aprobadores
                            isSuccessReturn = amXxGamMod.setAppproverByHierarchy(positionId, inmediateApproverId);    
                            
                            if (!isSuccessReturn)
                            pageContext.putParameter("ErrorEspecificoAp"," Aprob #3 "); // agregar el put del error dihu 16 Octubre 2014 Error al configurar la lista de valores
                            
                        }else{
                            isSuccessReturn = false;
                            // agregar el put del error dihu 16 Octubre 2014 PositionId Null
                             pageContext.putParameter("ErrorEspecificoAp"," Aprob #4 ");
                        }
                    }
                }else{
                    isSuccessReturn = false;
                    // agregar el put del error dihu 16 Octubre 2014 error del Paquete.
                     pageContext.putParameter("ErrorEspecificoAp"," Aprob #5 ");
                }
            }
        }
        return isSuccessReturn;
    }

    /**
     * Configura el atributo bandera que indica el origen del aprobador
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @return devuelve true en caso de una asignacion exitosa y false en caso contrario
     */
    public static boolean setIsApproverSysInGeneral(OAPageContext pageContext, OAWebBean webBean){
        
        System.out.println("Ingresa XxGamMAnticiposUtil.setIsApproverSysInGeneral "); 
        
        boolean isSuccess = false;
        if(pageContext != null && webBean != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                XxGamMaGeneralReqVOImpl generalImpl = amXxGamMod.getXxGamMaGeneralReqVO1();
                XxGamMaGeneralReqVORowImpl generalRow = null;
                generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
                
                Number personId = generalRow.getEmployeeId();
                if(personId != null){
                    
                    Map map = validApproverTypeByEmployee(pageContext, webBean, personId);
                    Boolean isApproverSys = null;
                    
                    if(map != null){
                        isApproverSys = (Boolean)map.get("isApproverSys");
                    }
                    
                    if(isApproverSys != null){
                        if(isApproverSys.booleanValue()){
                            generalRow.setIsApproverBySys(true);
                            isSuccess = true;
                        }else{
                            generalRow.setIsApproverBySys(false);
                            isSuccess = true;
                        }
                    }
                }
            }
        }
        return isSuccess;
    }

    /**
     * Obtiene el importe total de la solicitud de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente dela pagina OAF
     * @return devuelve el valor numerico del importe total
     */
    public static Number getGeneralTotalAmount(OAPageContext pageContext, OAWebBean webBean){
        
        Number total = null;
        if(pageContext != null && webBean != null){
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                XxGamMaGeneralReqVOImpl generalImpl = amXxGamMod.getXxGamMaGeneralReqVO1();
                XxGamMaGeneralReqVORowImpl generalRow = null;
                generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
                
                if(generalRow != null){
                    total = generalRow.getTotalPayment();
                }
            }
        }
        return total;
    }
    
    
    /**
     * Verifica que el ticket tenga por lo menos un vuelo registrado
     * @param pageContext
     * @param webBean
     * @param typeEmission tipo de solicitud que son "Franquicia o Solicitud de Anticipo"
     * @return devuelve true cuando cuando todas las validaciones fueron exitosas
     */
    
    public static boolean isRegisteredFlight(OAPageContext pageContext, OAWebBean webBean,String typeEmission){
        boolean isRegisteredFlight = false;
        if(webBean != null && pageContext != null){
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if(amXxGamMod != null){
                XxGamMaFlightInf0VOImpl flightImpl =  null;
                if(typeEmission.equals(XxGamConstantsUtil.REQUEST_TYPE_ADVANCE)){
                    XxGamMaTicketPVOImpl voXxGamMaTicketPVOImpl =  amXxGamMod.getXxGamMaTicketPVO3();
                    voXxGamMaTicketPVOImpl.setCurrentRow(voXxGamMaTicketPVOImpl.first());
                    
                    flightImpl = amXxGamMod.getXxGamMaFlightInf0VO3();
                    if(flightImpl.getEstimatedRowCount() > 0){
                        isRegisteredFlight = true;
                    }
                    return isRegisteredFlight;
                }else if(typeEmission.equals(XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE)){
                    XxGamMaTicketPVOImpl voXxGamMaTicketPVOImpl =  amXxGamMod.getXxGamMaTicketPVO4();
                    voXxGamMaTicketPVOImpl.setCurrentRow(voXxGamMaTicketPVOImpl.first());
                    //isRegisteredFlight = voXxGamMaTicketPVOImpl.isRegisteredFlight();
                    flightImpl = amXxGamMod.getXxGamMaFlightInf0VO4();
                    if(flightImpl.getEstimatedRowCount() > 0){
                        isRegisteredFlight = true;
                    }
                    return isRegisteredFlight;
                }else{
                    return isRegisteredFlight;
                }
            }else{
                return isRegisteredFlight;
            }
        }else{
            return isRegisteredFlight;
        }
   }

    /**
     * Compara y valida la igualdad entre el id y la clave de una responsabilidad 
     * de la aplicación, obteniendo los datos de la responsabilidad por id y comparando
     * la clave de la responsabilidad obtenida con la clave de la responsabilidad a comparar
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param responsabilityId contiene el id de la responsabilidad
     * @param responsabilityKey contiene la clave de la responsabilidad
     * @return devuelve true si la comparacion y validacion es correcta y false en caso
     *         de diferencia entre id y clave o bien false en caso de que el id no exista
     */
    public static boolean validatesResponsability(OAPageContext pageContext, 
                                         OAWebBean webBean,
                                         Number responsabilityId, 
                                         String responsabilityKey){
       
       boolean isSuccess = false;
       if(pageContext != null &&
          webBean != null &&
          responsabilityId != null && 
          responsabilityKey != null){
       
           XxGamModAntAMImpl amXxGamMod = null;
           amXxGamMod = getApplicationModule(pageContext, webBean);
           
           if(amXxGamMod != null){
               //Instancia AM de LOV
               XxGamModAntLovAMImpl amLov = null;
               amLov = (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();
               
               if(amLov != null){
                   XxGamMaResponsibilityAppLovVORowImpl respRow = amLov.getResponsibilityAppById(responsabilityId);
                   if(respRow != null){
                       if(responsabilityKey.toUpperCase().trim().equals(respRow.getResponsibilityKey().toUpperCase().trim())){
                           isSuccess = true;
                       }
                       
                       
                   }
               }
           }
       }
       return isSuccess;
   }
   
   
   
   
    public static void setGeneralAndPayment(OAPageContext pageContext,OAWebBean webBean,
    Number generalId,Number paymentId,Number ticketId,String typeEmission){
       
        if(pageContext == null && webBean == null 
           && generalId == null)
           return ;
           
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);
        if(amXxGamMod == null)
            return ;
            
      amXxGamMod.searchGeneralReq(generalId);
      if(paymentId != null){
        amXxGamMod.searchPaymentReq(paymentId);
      }
      amXxGamMod.searchTicket(ticketId,typeEmission);
           
    }
    
    public static String getNumberOfRoutes(OAPageContext pageContext, OAWebBean webBean,String requestType){
        String vuelos = null;
        if(pageContext == null || webBean == null)
            return null;
        
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod =  getApplicationModule(pageContext,webBean);
        if(amXxGamMod == null)
            return null;
        
       vuelos = amXxGamMod.getNumberOfRoutes(requestType);
        
        return vuelos;
    }

    /**
     * Asigna el registro actual del detalle de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param paymentId contiene el id del detalle del anticipo
     * @return devuelve true si la asignacion fue correcta o false en caso contrario
     */
    public static boolean setCurrentRowDetailPayment(OAPageContext pageContext, OAWebBean webBean, Number paymentId){
        System.out.println("Comienza setCurrentRowDetailPayment"); 
        boolean isSuccess = false;
        if(pageContext != null && webBean != null && paymentId != null){
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod =  getApplicationModule(pageContext,webBean);
            
            if(amXxGamMod != null){

                XxGamMaPaymentReqVOImpl detailImpl = null;
                detailImpl = amXxGamMod.getXxGamMaPaymentReqVO2();
                
                if(detailImpl != null){
                
                    RowSetIterator iterPayment = detailImpl.createRowSetIterator(null);
                    iterPayment.setRangeStart(0);
                    
                    Row[] filteredRows = iterPayment.getFilteredRows("Id",paymentId);
                    if ( filteredRows != null ){
                        if ( filteredRows.length > 0){
                            detailImpl.setCurrentRow(filteredRows[0]);
                            isSuccess = true;
                        }else{
                            isSuccess = false;
                        }
                    }else{
                        isSuccess = false;
                    }
                    
                    if ( iterPayment != null ){
                        iterPayment.closeRowSetIterator();
                    }
                    
                }
            }
        }
      System.out.println("Finaliza setCurrentRowDetailPayment"); 
        return isSuccess;
    }
    
    /**
     * Revisa si el folio ha sido ingresado
     * @param pageContext
     * @param webBean
     * @param typeEmission tipo de solicitud que son "Franquicia o Solicitud de Anticipo"
     * @return devuelve true cuando cuando todas las validaciones fueron exitosas
     */
    public static boolean isRegisteredFolio(OAPageContext pageContext, OAWebBean webBean, String typeEmission){
        boolean isRegisteredFolio = false;
        if(webBean != null && pageContext != null){
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if(amXxGamMod != null){
                if(typeEmission.equals(XxGamConstantsUtil.REQUEST_TYPE_ADVANCE)){
                    XxGamMaTicketPVOImpl voXxGamMaTicketPVOImpl =  amXxGamMod.getXxGamMaTicketPVO3();
                    isRegisteredFolio = voXxGamMaTicketPVOImpl.isRegisteredFolio();
                    return isRegisteredFolio;
                }else if(typeEmission.equals(XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE)){
                    XxGamMaTicketPVOImpl voXxGamMaTicketPVOImpl =  amXxGamMod.getXxGamMaTicketPVO4();
                    isRegisteredFolio = voXxGamMaTicketPVOImpl.isRegisteredFolio();
                    return isRegisteredFolio;
                }else{
                    return isRegisteredFolio;
                }
            }else{
                return isRegisteredFolio;
            }
        }else{
            return isRegisteredFolio;
        }
    }

    /**
     * Inicializa las descripciones de las routas de los vuelos
     * @param pageContext 
     * @param webBean
     * @return devuelve true si la inicializacion es correcta o false en caso contrario
     */
    public static boolean setFlightRouteDescription(OAPageContext pageContext, OAWebBean webBean){
       System.out.println("Comienza Capa Utilitario setFlightRouteDescription"); 
        boolean isSuccess = false;
        if(pageContext != null && webBean != null){
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            if(amXxGamMod != null){
                XxGamMaFlightInf0VOImpl flightImpl = null;
                if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                webBean, 
                                                                new Number(pageContext.getResponsibilityId()), 
                                                                XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)) {
                    flightImpl = amXxGamMod.getXxGamMaFlightInf0VO3();
                }else{
                    if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                    webBean, 
                                                                    new Number(pageContext.getResponsibilityId()), 
                                                                    XxGamConstantsUtil.RESPONSABILITY_FRANCHISE)) {
                        
                        flightImpl = amXxGamMod.getXxGamMaFlightInf0VO4();
                    }
                }
                
                System.out.println("Entra para establecer las descripciones de ruta? "+(flightImpl != null));
                if(flightImpl != null){
                 isSuccess = flightImpl.setFlightRouteDescription();
                }
                
            }
        }
      System.out.println("Finaliza setFlightRouteDescription"); 
        return isSuccess;
    }

    /**
     * Realiza la asignacion de una divisa a todas las lineas de anticipo y refresca las validaciones de linea
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param currencyCode contiene el codigo de divisa
     * @param currencyDesc contiene la descripcion de la divisa
     * @return devuelve true si la asignacion en todas las lineas es correcta o false en caso contrario
     */
    public static boolean setCurrencyAllRowDetail(OAPageContext pageContext, OAWebBean webBean, String currencyCode, String currencyDesc){
        
        boolean isSuccessAll = false;
        if(pageContext != null && webBean != null && currencyCode != null && currencyDesc != null){
            isSuccessAll = setCurrencyAllDetailVO2(pageContext, webBean, currencyCode, currencyDesc);
            
            /* Se hace dinamico el tipo de Reembolso ya no depende de el empleado.
            if(isSuccessAll){
                XxGamModAntAMImpl amXxGamMod = null;
                amXxGamMod = getApplicationModule(pageContext, webBean);
                XxGamMaGeneralReqVOImpl vo = amXxGamMod.getXxGamMaGeneralReqVO1();
                if(vo != null){
                    XxGamMaGeneralReqVORowImpl fila = (XxGamMaGeneralReqVORowImpl)vo.getCurrentRow();
                    fila.setCurrencyCode(currencyCode);
                    fila.setCurrencyDesc(currencyDesc);
                }
            }*/
        }
        return isSuccessAll;
    }
    
    /**
     * Asigna el valor de la divisa para todas las lineas de la solicitud de anticipos
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param currencyCode contiene el codigo de divisa
     * @param currencyDesc contiene la descripcion de la divisa
     * @return devuelve true si no hay error en la asignacion de cada una de las lineas de anticipo, o false en caso contrario
     */
    public static boolean setCurrencyAllDetailVO2(OAPageContext pageContext, OAWebBean webBean, String currencyCode, String currencyDesc){
        boolean isSuccessAll = false;
        if(pageContext != null && webBean != null && currencyCode != null && currencyDesc != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                isSuccessAll = amXxGamMod.setCurrencyAllDetailVO2(currencyCode, currencyDesc);
            }
        }
        return isSuccessAll;
    }

    /**
     * Calcula el importe de la divisa mexicana de todas las lineas de anticipo
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @return devuelve true en caso de una conversion exitosa de todas las lineas de anticipo o false en caso contrario
     */
    public static boolean calculateAmountMxAllPaymentDetail(OAPageContext pageContext, OAWebBean webBean){
        boolean isSuccessAll = false;
        if(pageContext != null && webBean != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                isSuccessAll = amXxGamMod.calculateAmountMxAllPaymentDetail();
            }
        }
        return isSuccessAll;
    }

    /**
     * Valida si el importe total aplica para reserva de fondos
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @return devuelve true cuando cuando todas las validaciones fueron exitosas
     */
    public static boolean isValidToReservedFound(OAPageContext pageContext, OAWebBean webBean){
        boolean isSuccess = false;
        if(pageContext != null && webBean != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                isSuccess = amXxGamMod.isValidToReservedFound();
            }
        }
        return isSuccess;
    }

    /**
     * Valida los campos para realizar la conversion de divisa a divisa mexicana
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param rowRef contiene la referencia del registro
     * @return devuelve nullo si los datos son validos o un mensaje de error correspondiente a los valores validados
     */
    public static String getValidatesConversionMx(OAPageContext pageContext, OAWebBean webBean, String rowRef){
        String errorMsg = null;
        if(pageContext != null && webBean != null){
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
            
                XxGamMaPaymentReqVORowImpl detailRow = null;
                try{
                    detailRow = (XxGamMaPaymentReqVORowImpl)amXxGamMod.findRowByRef(rowRef);
                    errorMsg = amXxGamMod.getValidatesConversionMx(detailRow);
                }catch(Exception e){
                    errorMsg = e.getMessage();
                }
            }
        }
        return errorMsg;
    }
    
    /**
     * Valida que los registros de la solicitud de anticipo no contengan tipos de anticipos repetidos
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param rowRef contiene referencia del registro del detalle de la solicitud de anticipo
     * @return devuelve true en caso de ser valido o false en caso contrario
     */
    public static boolean validatesTypePaymentRepeated(OAPageContext pageContext, OAWebBean webBean, String rowRef, XxGamMaPaymentReqVORowImpl detailRow){
        
        boolean isValid = false;
        if(pageContext != null && webBean != null){
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                XxGamMaPaymentReqVORowImpl detailRowAux = null;
                if(detailRow != null){
                    detailRowAux = detailRow;
                }else{
                    if(rowRef != null){
                        detailRowAux = (XxGamMaPaymentReqVORowImpl)amXxGamMod.findRowByRef(rowRef);        
                    }
                }
                
                isValid = amXxGamMod.validatesTypePaymentRepeated(detailRowAux);
            }
        }
        return isValid;
    }

    /**
     * Obtiene el id del aprovador inmediato de un empleado por el id de su posicion
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param positionId contiene la posicion del empleado subordinado
     * @return devuelve numero del id del aprobador
     */
    public static Number getApproverIdByEmpPositionId(OAPageContext pageContext, 
                                                      OAWebBean webBean, 
                                                      Number positionId){
        
        Number approverId = null;
        if(positionId != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                
                //Instancia AM de LOV
                XxGamModAntLovAMImpl amLov = null;
                amLov = (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();
                
                if(amLov != null){
                    
                    boolean exit = false;
                    Number parepPositionId = positionId;
                    
                    while(!exit){
                        
                        XxGamMaChiefByPositionIdLovVORowImpl chiefRow = null;
                        chiefRow = amLov.getChiefDataByEmployeePositionId(parepPositionId);
                        
                        if(chiefRow != null){
                        
                            String strChiefJobNameId = chiefRow.getChiefJobNameId();
                            Number chiefJobNameId = null;

                            try {
                                chiefJobNameId = new Number(strChiefJobNameId);
                                
                                if (chiefJobNameId != null){

                                    if(chiefJobNameId.intValue() <= 5 &&
                                       chiefJobNameId.intValue() >= 1){
                                        exit = true;
                                        approverId = chiefRow.getChiefId();
                                    }else{   
                                        try  {
                                            parepPositionId = chiefRow.getChiefPositionId();   
                                        } catch (Exception ex)  {
                                            exit = true;
                                        } 
                                    }
                                    
                                }
                            } catch (SQLException e) {
                                exit = true;
                            }
                        }else{
                            exit = true;
                        }
                    }
                }
            }
        }
        return approverId;
    }
    
   
    /**
     * Ejecuta el WorkFlow de la EBS al cancelar el ticket
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF/
     */
    public static boolean callWorkFlowToSendCancellationOfficeTicket(OAPageContext pageContext,
                                                                    OAWebBean webBean, 
                                                                    Number RequestId,
                                                                    Number employeeId,
                                                                    Number approverId,
                                                                    String TicketNum) {
        
        boolean isSuccess = false;
        String route = null;
        
        if(RequestId != null &&
           employeeId != null &&
           approverId != null &&
           TicketNum != null){
            isSuccess = true;
            System.out.println("***WFBug: Success 18 ***");
        }else{
            isSuccess = false;
        }

        XxGamModAntAMImpl am = null;
        am = getApplicationModule(pageContext, webBean);
        System.out.println("***WFBug: Success 19 ***");
        if(isSuccess && am != null){
            System.out.println("***WFBug: Success 20 ***");
            int intResult = am.callProcedureCancelNotificationTicketP(RequestId,
                                                                employeeId,
                                                                approverId,
                                                                route,
                                                                TicketNum);
            if(intResult == 0){
                isSuccess = true;
            }else{
                isSuccess = false;
            }
        }
       
        return isSuccess;
    }

    /**
     * Ejecuta el envio de notificaciones de cancelacion de boletos de aviÃ³n en caso de existir
     * @param pageContext contiene el objeto OAPageContext procedente de las paginas del empleado para gestion de solicitudes de anticipo
     * @param webBean contiene el objeto OAWebBean procedente de las paginas del empleado para gestion de solicitudes de anticipo
     * @return devuelve un valor menor o igual a 0 cuando no ocurren errores en el proceso, si el valor es mayor a 0 representa el nÃºmero
     *         de notificaciones con error
     */
     public static boolean sendCancelNotificationTicketPWorkFlow(OAPageContext pageContext,
                                                     OAWebBean webBean){
                                                     
         boolean isSuccess = false;
         if(pageContext != null && webBean != null){
             System.out.println("***WFBug Success***");
             if(XxGamMAnticiposUtil.validatesResponsability(pageContext, webBean, new Number(pageContext.getResponsibilityId()), XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET)){
                 System.out.println("***WFBug: Success 1***");
                 XxGamModAntAMImpl amXxGamMod = null;
                 amXxGamMod = getApplicationModule(pageContext, webBean);
                 
                 if(amXxGamMod != null){
                     XxGamMaGeneralReqVOImpl generalImpl = null;
                     generalImpl = amXxGamMod.getXxGamMaGeneralReqVO1();
                     System.out.println("***WFBug: Success 2 ***");
                     
                     XxGamMaGeneralReqVORowImpl generalRow = null;
                     if(generalImpl != null){
                         generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
                         System.out.println("***WFBug: Success 3 "+generalRow.getId()+"; "+generalRow.getEmployeeId()+"; "+generalRow.getApproverId()+"; "+generalRow.getNumberPayment()+" ***");
                         
                         if(generalRow != null){
                             System.out.println("***WFBug: Success 4***");
                            
                             boolean isSuccess2 = false;
                              try{
                                  System.out.println("***WFBug: Success 5: ***");
                                  Number RequestId = generalRow.getId();
                                  Number employeeId = generalRow.getEmployeeId();
                                  Number approverId = generalRow.getApproverId();
                                  String TicketNum = generalRow.getNumberPayment();
                                  isSuccess2 = callWorkFlowToSendCancellationOfficeTicket(pageContext, webBean, RequestId,employeeId,approverId,TicketNum);
                              }catch(Exception e){
                                  isSuccess2 = false;
                              }
                              
                              if(!isSuccess2){
                                  isSuccess = false;
                              }
                         }
                     }
                 }    
             }
         }
         
         return isSuccess;
     }

  /**  10Jul2015
   * Metodo Sobrecargado para a�adir criterios des busqueda por unidad Operativa y Tipos de Franquisia
   * @param pageContext
   * @param webBean
   * @param nameRequester
   * @param numberPayment
   * @param typeEm
   * @param statusReq
   * @param fromDate
   * @param toDate
   * @param statusNotiCode
   * @param operatingUnit
   */
  public static void searchTicketRequest(OAPageContext pageContext, 
                                         OAWebBean webBean, 
                                         String nameRequester, 
                                         String numberPayment, String typeEm, 
                                         String statusReq, Date fromDate, 
                                         Date toDate, String statusNotiCode, 
                                         String operatingUnit)
  {
    //Verifica nulidad
    if (pageContext == null || webBean == null)
        return;
    String sUserName = null;
    sUserName = pageContext.getUserName();

    String strFranchiseType = null; 
    String strRequestType = null; 
    String nvlFranchiseType = null; 
    String nvlRequestType = null; 
                
           
    if(null!=pageContext.getParameter("pfranchiseType")&&!"".equals(pageContext.getParameter("pfranchiseType"))){
    strFranchiseType = pageContext.getParameter("pfranchiseType"); 
    }
                 
    if(null!=pageContext.getParameter("pRequest")&&!"".equals(pageContext.getParameter("pRequest"))){
    strRequestType = pageContext.getParameter("pRequest"); 
    } 
               
    nvlFranchiseType = (null==strFranchiseType)?(String)pageContext.getSessionValue("sfranchiseType"):strFranchiseType; 
    nvlRequestType = (null==strRequestType)?(String)pageContext.getSessionValue("sRequest"):strRequestType; 

    XxGamModAntAMImpl amXxGamMod = null;
    try {
        amXxGamMod = getApplicationModule(pageContext, webBean);
        amXxGamMod.searchTicketRequest(nameRequester, numberPayment,
                                       typeEm, statusReq, fromDate,
                                       toDate,
                                       sUserName,
                                       statusNotiCode,
                                       operatingUnit,
                                       nvlFranchiseType,
                                       nvlRequestType);
    } catch (Exception exception) {
        throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                          XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_NF_ERROR,
                                          null, OAException.WARNING, null);
    }
    
  }

  /**
   * Metodo Sobrecargado crear un criterio de busqueda con Unidad Operativa 
   * @param pageContext Contexto de la pagina.
    * @param webBean Web bean.
    * @param advanceNum N�mero de anticipo.
    * @param employeeName Nombre del empleado.
    * @param nameApprover Nombre del proveedor.
    * @param costCenter Centro de costos.
    * @param costCenterFlex Flex del centro de costos
    * @param virtualCard Tarjeta virtual.
    * @param statusRequest Estatus de la solicitud.
    * @param statusTicket Estatus del boleto.
    * @param dateFrom Fecha inicio.
    * @param dateTo Fecha fin.
    * @param operatingUnit Unidad Operativa
   */
  public static void searchAdvanceConsultantion(OAPageContext pageContext, 
                                                OAWebBean webBean, 
                                                String advanceNum, 
                                                String employeeName, 
                                                String nameApprover, 
                                                String costCenter, 
                                                String costCenterFlex, 
                                                String virtualCard, 
                                                String statusRequest, 
                                                String statusTicket, 
                                                Date dateFrom, Date dateTo, 
                                                String operatingUnit)
  {
   
    //Busca el registro
      XxGamModAntAMImpl amXxGamMod = null;
      amXxGamMod = getApplicationModule(pageContext, webBean);

      try {

          //Inicia busqueda avanzada
          amXxGamMod.searchAdvanceConsultantion(advanceNum, 
                                                employeeName,
                                                nameApprover, 
                                                costCenter,
                                                costCenterFlex, 
                                                virtualCard, 
                                                statusRequest,
                                                statusTicket, 
                                                dateFrom,
                                                dateTo,
                                                operatingUnit);

      } catch (Exception exception) {
           throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                             XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_NF_ERROR,
                                             null, OAException.WARNING, null);
      }
  
  }

  /**
   * Llama al metodo del AM createRowPasajerosInfo para iniciar el proceso de creacion de registros
   * de la Tabla de Informacion de pasajeros
   * @param pageContext Pagina de contexto.
   * @param webBean Web bean.
   */
   
  public static void createRowPasajerosInfoForFranchise(OAPageContext pageContext, 
                                                        OAWebBean webBean)
  {
    //Verifica nulidad
    if (pageContext == null || webBean == null)
        return;
    //Declaracion de los recursos
    XxGamModAntAMImpl amXxGamMod = null;
    int intNumeroPasajeros = 0; 
    //Controla cualquier excepcion ocurrida
    try {
        //Obtiene el AM y ejecuta la accion
        amXxGamMod = getApplicationModule(pageContext, webBean);
     
        if(amXxGamMod != null){
      
          XxGamMaGeneralReqVOImpl generalImpl = amXxGamMod.getXxGamMaGeneralReqVO1();
          if(generalImpl != null){
              if(generalImpl.getCurrentRow() != null){
                System.out.println("Llamar Cama AM createRowPasajerosInfoForFranchise"); 
                
                //Limpiar registros por si se cambia de opinion con el numero de pasajeros 
                /***********************************/
                
                    XxGamMaPasajerosInfoVOImpl PasajerosInfoVOImpl  = null; 
                    PasajerosInfoVOImpl =amXxGamMod.getXxGamMaPasajerosInfoVO4();
                    Row row[] = PasajerosInfoVOImpl.getAllRowsInRange();
                
                /***********************************/
                
                 String numPasajerosStr = null;
                 BigDecimal bigAttribute = null;
                 Number numPasajeros = null;
                        
                OAMessageTextInputBean textInputBean = null; 
                textInputBean = (OAMessageTextInputBean)webBean.findIndexedChildRecursive("NumeroBoletosEmitir");      
                numPasajerosStr = (String)textInputBean.getValue(pageContext);
                
                if (numPasajerosStr != null) {
                  bigAttribute = new BigDecimal(numPasajerosStr);
                  if (bigAttribute != null) {
                    numPasajeros = new Number(bigAttribute);
                    
                     intNumeroPasajeros = numPasajeros.intValue();
                    System.out.println("numeroDePasajeros: "+numPasajeros);
                    
                    //Validar numeros negativos 
                     if(intNumeroPasajeros<1 || intNumeroPasajeros>9){
                       throw new OAException("Ingresar un numero mayor que 0 y menor que 10",OAException.ERROR);
                     }
                     
                    int row_length = 0; 
                    row_length = row.length;
                    
                    // Sin los registros que existen son menores que los que se quiere
                    if (row_length < numPasajeros.intValue()){
                       int diferencia = 0; 
                       diferencia =  numPasajeros.intValue() - row.length; 
                      
                      for(int i =1; i<=diferencia;i++){
                        amXxGamMod.createRowPasajerosInfoForFranchise(); 
                      } 
                    }
                    
                    /*********************************
                    // Si los registros que existen son mayores que los que se quieren
                    if (row_length > numPasajeros.intValue()){
                      int diferencia = (-1)*( numPasajeros.intValue() - row.length);
                      for(int i = diferencia; i >=1;i--){
                        XxGamMaPasajerosInfoVORowImpl rowi = (XxGamMaPasajerosInfoVORowImpl) row[i];
                        rowi.remove();
                      } 
                    }
                    ******************************/
                    
                   } // END if (bigAttribute != null) {
                } // END if (numPasajerosStr != null) {
              }
          }
      }
      
        
    } catch (Exception exception) {
        System.out.println("EXCEPTION al "+exception.getMessage()); 
        throw new OAException("EXCEPTION al "+exception.getMessage(),OAException.ERROR);
    }
  }

  /** Funcion para inicializar los view objects que contienen la informacion de los pasajeros 
   **
   **/
  public static void setPasajerosInfo(OAPageContext pageContext, 
                                      OAWebBean webBean, Number generalId, 
                                      Number paymentId, Number ticketId, 
                                      String typeEmission)
  {
    System.out.println("Comienza Capa Utilitarios setPasajerosInfo ticketId: "+ticketId); 
    if ((pageContext == null) && (webBean == null) && (generalId == null))
        {
          return;
        }
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);
        if (amXxGamMod == null) {
          return;
        }
     
    amXxGamMod.searchPasajerosInfo(ticketId, typeEmission);
    
  }

  /** Funcion para borrar registros de los view objects que contienen la informacion de los pasajeros 
   **
   **/
  public static void deleteRowPasajerosInfoForFranchise(OAPageContext pageContext, 
                                                        OAWebBean webBean,
                                                         Number nInfoPasajeroId)
  {
   
    XxGamModAntAMImpl amXxGamMod = null; 
    if ((pageContext != null) && (webBean != null)) {
        
         amXxGamMod = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
         if (amXxGamMod != null) {
           String rowRef = null; 
           rowRef =pageContext.getParameter(OAWebBeanConstants.EVENT_SOURCE_ROW_REFERENCE); 
           
           if(null!=rowRef){
             amXxGamMod.deleteRowPasajerosInfoForFranchise(nInfoPasajeroId); 
           }
         }
      } 
      
  }


  /***
   * Metodo para obtener el Ticket ID apartir de requestID  
    ***/
    public static Number getTicketId(OAPageContext pageContext, 
                                     OAWebBean webBean, 
                                     Number requestId)
    {
      Number ticketId = null;
      XxGamModAntAMImpl amXxGamMod = null;
      
      if ((pageContext == null) && (webBean == null) && (requestId == null))
        {
          System.out.println("A ocurrido un error al validar getTicketId 1.1");
          return null;
        }else{
             amXxGamMod = getApplicationModule(pageContext, webBean);
             if (amXxGamMod == null) {
               System.out.println("A ocurrido un error al validar getTicketId 1.2");
               return null;
             }else{
               ticketId = amXxGamMod.searchTicketId(requestId);
               if(ticketId!=null){
                 return ticketId;
                   }else{
                     System.out.println("A ocurrido un error al validar getTicketId 1.3 retorno");
                     return null;
                   }
                 
               }
             }
    }
    
  /****
   * Metodo para validar si se trata de una nueva franquisia 
   * depues del RollOut Oracle Intenet Expenses
   * ****/
  public static boolean validateOldNewFranchise(OAPageContext pageContext, 
                                             OAWebBean webBean, 
                                             Number generalId, 
                                             Number paymentId, Number ticketId, 
                                             String typeEmission)
  {
      boolean retval = false;
    XxGamModAntAMImpl amXxGamMod = null;
    
    if ((pageContext == null) && (webBean == null) && (generalId == null))
        {
          System.out.println("A ocurrido un error al validar validateOldNewFranchise 1.1");
          return false;
        }else{
         
             amXxGamMod = getApplicationModule(pageContext, webBean);
             if (amXxGamMod == null) {
               System.out.println("A ocurrido un error al validar validateOldNewFranchise 1.2");
               return false;
             }else{
               String getResultStr = null; 
               getResultStr = amXxGamMod.searchOldNewTicket(ticketId, typeEmission);
               System.out.println("Se trata de una nueva franquicia? "+getResultStr);
               if(!"".equals(getResultStr)&&null!=getResultStr){
                 if("NEW".equals(getResultStr)){
                     retval = true;
                   }else{
                     retval = false; 
                   }
                 
               }
             }
        }
      
     return retval; 
  }

 /**
   * Metodo para validar la cancelacion de los boletos en la Oficina de boletos 
   * @param pageContext
   * @param webBean
   */
  public static void validateTicketsCancel(OAPageContext pageContext, 
                                           OAWebBean webBean)
  {
    
    System.out.println("Comienza validacion cancelacion de boletos capa Util"); 
    XxGamModAntAMImpl AntAMImpl = null; 
    AntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
    
    String ticketIdStr = null; 
    String paymentIdStr = null; 
    String generalIdStr = null; 
    String tipoSolicitudStr = null; 
    String statusNotiCodeStr = null; 
    Number  ticketIdNum = null; 
    Number  paymentIdNum = null; 
    Number generalIdNum = null; 
    
    
    ticketIdStr = pageContext.getParameter("ticketId");
    paymentIdStr = pageContext.getParameter("paymentId");
    generalIdStr = pageContext.getParameter("generalId");
    tipoSolicitudStr =pageContext.getParameter("TipoSolicitud");
    statusNotiCodeStr = pageContext.getParameter("statusNotiCode");
    
    if(null!=ticketIdStr){
    ticketIdNum = XxGamMAnticiposUtil.converteNumber(ticketIdStr);
    }
    if(null!=paymentIdStr){
    paymentIdNum = XxGamMAnticiposUtil.converteNumber(paymentIdStr);
    }
    if(null!=generalIdStr){
    generalIdNum = XxGamMAnticiposUtil.converteNumber(generalIdStr);
    }
    System.out.println(" ticketIdNum:"+ticketIdNum+" paymentIdNum:"+paymentIdNum+" generalIdNum:"+generalIdNum+" tipoSolicitudStr:"+tipoSolicitudStr+" statusNotiCodeStr:"+statusNotiCodeStr);
    
    if(null!=AntAMImpl){
      AntAMImpl.validateTicketsCancel(ticketIdNum,paymentIdNum,generalIdNum,tipoSolicitudStr); 
    }
   
    System.out.println("Finaliza validacion cancelacion de boletos capa Util");  
    
  }

 /**
   * Metodo para mandar llamar el workflow que cancela los boletos desde la oficina de boletos
   * @param pageContext
   * @param webBean
   * @return
   */
  public static boolean callWorkFlowToCancelTickets(OAPageContext pageContext, 
                                                    OAWebBean webBean)
  {
    boolean isSuccess = false;
    
    String strFranchiseType = null; 
    String strRequestType = null; 
    String nvlFranchiseType = null; 
    String nvlRequestType = null; 
                
           
    if(null!=pageContext.getParameter("pfranchiseType")&&!"".equals(pageContext.getParameter("pfranchiseType"))){
    strFranchiseType = pageContext.getParameter("pfranchiseType"); 
    }
                 
    if(null!=pageContext.getParameter("pRequest")&&!"".equals(pageContext.getParameter("pRequest"))){
    strRequestType = pageContext.getParameter("pRequest"); 
    } 
               
    nvlFranchiseType = (null==strFranchiseType)?(String)pageContext.getSessionValue("sfranchiseType"):strFranchiseType; 
    nvlRequestType = (null==strRequestType)?(String)pageContext.getSessionValue("sRequest"):strRequestType; 
    
    if ((pageContext != null) && (webBean != null)) {
      System.out.println("***CallCancelWFBug4:***");
      if(XxGamMAnticiposUtil.validatesResponsability(pageContext, webBean, new Number(pageContext.getResponsibilityId()), XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET)
         ||XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET.equals(nvlRequestType)){
        System.out.println("***CallCancelWFBug5:***");
        XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = getApplicationModule(pageContext, webBean);
        if(null!=amXxGamMod){
          System.out.println("***CallCancelWFBug6:***");
          XxGamMaGeneralReqVOImpl generalImpl = null;
          generalImpl = amXxGamMod.getXxGamMaGeneralReqVO1();
          XxGamMaGeneralReqVORowImpl generalRow = null;
          if(null!=generalImpl){
            System.out.println("***CallCancelWFBug7:***");
            generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
            System.out.println("***CallCancelWFBug8 " + generalRow.getId() + "; " + generalRow.getEmployeeId() + "; " + generalRow.getApproverId() + "; " + generalRow.getNumberPayment() + " ***");
            if (generalRow != null) {
             
                  boolean isSuccess2 = false;
                  try {
                    System.out.println("***CallCancelWFBug9:***");
                    Number RequestId = generalRow.getId();
                    Number employeeId = generalRow.getEmployeeId();
                    Number approverId = generalRow.getApproverId();
                    String TicketNum = generalRow.getNumberPayment();
                    isSuccess2 = callWorkFlowToCancelTicketsOffice(pageContext, webBean, RequestId, employeeId, approverId, TicketNum);
                  } catch (Exception e) {
                    System.out.println("***ExceptionCallCancelWFBug9:*** "+e.getMessage());
                    isSuccess2 = false;
                  }
                  
                  if (!isSuccess2) {
                   isSuccess = false;
                  }else{
                    isSuccess = true; 
                  }
                            
            }else{
              System.out.println("***ExceptionCallCancelWFBug9:***");
            }            
          }else{
            System.out.println("***ExceptionCallCancelWFBug7:***");
          }
        }else{
          System.out.println("***ExceptionCallCancelWFBug6:***");
        }
      }else{
        System.out.println("***ExceptionCallCancelWFBug5:***");
      }
    }else{
      System.out.println("***ExceptionCallCancelWFBug4:***");
    }
    System.out.println("***CallCancelWFBug6:***");
    return isSuccess ; 
  }

  /**
   * Metodo para lanzar el Workflow que cancela los tickets en la Oficina de Boletos
   * @param pageContext
   * @param webBean
   * @param RequestId
   * @param employeeId
   * @param approverId
   * @param TicketNum
   * @return
   */
  private static boolean callWorkFlowToCancelTicketsOffice(OAPageContext pageContext, 
                                                           OAWebBean webBean, 
                                                           Number RequestId, 
                                                           Number employeeId, 
                                                           Number approverId, 
                                                           String TicketNum)
  {
    boolean isSuccess = false;
    //String route = null;
    System.out.println("***CallCancelWFBug10:***");
    if ((RequestId != null) && (employeeId != null) && (approverId != null) && (TicketNum != null))
       {
         isSuccess = true;
         System.out.println("***CallCancelWFBug11:***");
          
            XxGamModAntAMImpl am = null;
            am = getApplicationModule(pageContext, webBean);
             if ((isSuccess) && (am != null)) {
               System.out.println("***CallCancelWFBug12:***");
               isSuccess = XxGamMAnticiposUtil2.callWorkFlowToCancelTicketsOffice(pageContext,webBean,RequestId,employeeId,approverId, TicketNum );
             }else{
               System.out.println("***ExceptionCallCancelWFBug12:***");
             }
            
       } else {
        System.out.println("***ExceptionCallCancelWFBug11:***");
         isSuccess = false;
    }  
    
    return isSuccess;     
  }


  /** Funcion Agregada Por GnosisHCM
   * Valida que todos los registros de la solicitud de anticipo no contengan tipos de anticipos con mas de una categoria 
   * Solo basta tomar el primer registro y validar a partir de este
   * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
   * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
   */
   
  public static Boolean refreshAllValidationByLineCategory(OAPageContext pageContext, 
                                                           OAWebBean webBean)
  { 
    System.out.println("Comienza Capa Utilitarios refreshAllValidationByLineCategory"); 
    
    boolean isSuccessAll = true;
    int count = 0; 
    int countCateg = 0; 
    int[] countCategory = new int[100];  
    
    for(int i=0; i < countCategory.length ; i++){
      countCategory[i] = 0; 
    }
    
        if ((pageContext != null) && (webBean != null))
        {
          XxGamModAntAMImpl amXxGamMod = null;
           amXxGamMod = getApplicationModule(pageContext, webBean);
           if (amXxGamMod != null) {
             XxGamMaPaymentReqVOImpl detailImpl = amXxGamMod.getXxGamMaPaymentReqVO2();
             XxGamMaGeneralReqVOImpl generalImpl = amXxGamMod.getXxGamMaGeneralReqVO1();
             XxGamMaGeneralReqVORowImpl generalRow = null;
             XxGamModAntLovAMImpl amLov = (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();
             //XxGamMaCategoriaGastoLovVOImpl CategoriaGastoLovVOImpl  = null;   
            
            /*** 
             CategoriaGastoLovVOImpl = amLov.getXxGamMaCategoriaGastoLovVO1();
             if(null!=CategoriaGastoLovVOImpl){
              CategoriaGastoLovVOImpl.executeQuery();
              RowSetIterator detailIterCatGasto = CategoriaGastoLovVOImpl.getRowSetIterator();
              if (detailIterCatGasto != null) {
                      detailIterCatGasto.reset();
                while (detailIterCatGasto.hasNext()) {
                  countCategory[countCateg] =0; 
                  countCateg = countCateg +1;
                }
              }
             }
             **/
             
             
             System.out.println( "Get detail Implementation");
             if (detailImpl != null) {
                 RowSetIterator rowSetIter = detailImpl.getRowSetIterator();
                 System.out.println("Get detail Iterator");
                if (rowSetIter != null) {
                    System.out.println( "Row count of details payment advance : " + rowSetIter.getRowCount());
                    rowSetIter.reset();
                    System.out.println( "Reset detail Iterator");
                    while(rowSetIter.hasNext()){
                  
                      System.out.println("Comienza validacion de Categoria: "+count); 
                      Row row = rowSetIter.next();
                      XxGamMaPaymentReqVORowImpl detailRow = (XxGamMaPaymentReqVORowImpl)row;
                      if (detailRow != null) {
                         /*****************************************************************/
                          Number typePaymentId = null;
                          typePaymentId = detailRow.getTypePayment();
                          
                          if (generalImpl != null) {
                            generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
                            System.out.println( "Get current General Information Row " );
                          }
                         Number templateId = null;
                         if (generalRow != null) {
                         templateId = generalRow.getTemplatePayment();
                         System.out.println( "Get template Id of Detail Row : " + templateId);
                         }
                          
                         if ((amLov != null) && (templateId != null)) {
                         XxGamMaTypePaymentLovVORowImpl typePaymentRow = amLov.getTypePaymentById(typePaymentId, templateId);
                           if (typePaymentRow != null) {
                             String typePaymentDesc = null;
                             typePaymentDesc = typePaymentRow.getTypePaymentDesc();
                             if(null!=typePaymentDesc&&!"".equals(typePaymentDesc)){
                               System.out.println("Get template Desc of Detail Row : " + typePaymentDesc); 
                                XxGamMaCategoriaGastoLovVOImpl CategoriaGastoLovVOImpl2  = null;   
                                CategoriaGastoLovVOImpl2 = amLov.getXxGamMaCategoriaGastoLovVO1();
                               if(null!=CategoriaGastoLovVOImpl2){
                                 CategoriaGastoLovVOImpl2.executeQuery();
                                 RowSetIterator detailIterCatGasto = CategoriaGastoLovVOImpl2.getRowSetIterator();
                                 if (detailIterCatGasto != null) {
                                         detailIterCatGasto.reset();
                                   countCateg = 0; 
                                   while (detailIterCatGasto.hasNext()) {
                                     XxGamMaCategoriaGastoLovVORowImpl CategoriaGastoLovVORowImpl = (XxGamMaCategoriaGastoLovVORowImpl)detailIterCatGasto.next();
                                     System.out.println(CategoriaGastoLovVORowImpl.getMeaning()+":"+CategoriaGastoLovVORowImpl.getLookupCode()); 
                                     String cadena = null; 
                                     String patron = null;
                                     cadena = typePaymentDesc;
                                     patron = CategoriaGastoLovVORowImpl.getMeaning();
                                     Pattern pat = Pattern.compile(".*"+patron+".*");
                                     Matcher mat = pat.matcher(cadena);
                                     if (mat.matches()) {
                                         System.out.println("SI COINCIDE");
                                         countCategory[countCateg]=countCategory[countCateg]+1;
                                     }else{
                                       System.out.println("NO COINCIDE");
                                     }
                                     countCateg = countCateg +1; 
                                   }
                                 }
                               }
                           
                             }
                           } 
                         } //END if ((amLov != null) && (templateId != null)) {
                        /*****************************************************************/
                       } // END if (detailRow != null) {
                        count = count +1; 
                    } //while(rowSetIter.hasNext()){
                }
              }
           }
        }
        
      for(int i=0;i<=countCateg;i++){
        System.out.println("CountCategory: "+countCategory[i]);
        if (countCategory[i]>=2){
          isSuccessAll = false;
          break; 
        }
      }  
        
    System.out.println("Finaliza Capa Utilitarios refreshAllValidationByLineCategory");      
  return isSuccessAll;
  }

  /**  Funcion agregada por GnosisHCM
     * Valida que los registros de la solicitud de anticipo no contengan tipos de anticipos con mas de una categoria 
     * @param pageContext contiene el objeto OAPageContext procedente de la pagina OAF
     * @param webBean contiene el objeto OAWebBean procedente de la pagina OAF
     * @param rowRef contiene referencia del registro del detalle de la solicitud de anticipo
     * @return devuelve true en caso de ser valido o false en caso contrario
     */
    public static boolean validatesTypePaymentCategory(OAPageContext pageContext, 
                                                       OAWebBean webBean, 
                                                       String rowRef, 
                                                       XxGamMaPaymentReqVORowImpl detailRow)
    {
      boolean isValid = false;
      if ((pageContext != null) && (webBean != null)) {
           XxGamModAntAMImpl amXxGamMod = null;
        amXxGamMod = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
        if (amXxGamMod != null) {
                XxGamMaPaymentReqVORowImpl detailRowAux = null;
                if (detailRow != null) {
                  detailRowAux = detailRow;
                }
                else if (rowRef != null) {
                  detailRowAux = (XxGamMaPaymentReqVORowImpl)amXxGamMod.findRowByRef(rowRef);
                }
                
                isValid = amXxGamMod.validatesTypePaymentCategory(pageContext,webBean,detailRowAux);
           }
      }    
      System.out.println("Finaliza validatesTypePaymentCategory isValid"+isValid);
      return isValid;
    }

  /**
   * Metodo Sobrecargado para validar si se ingresaron los Boletos
   * @param pageContext
   * @param webBean
   * @param requestType
   * @return
   */
  public static boolean getEstatusFoliosTicketsBoolean(OAPageContext pageContext, 
                                                       OAWebBean webBean, 
                                                       String requestType)
  {
      boolean retval = false;
      String getEstatusFoliosTickets = null; 
      
      getEstatusFoliosTickets = getEstatusFoliosTickets( pageContext, 
                                                webBean, 
                                                requestType);
    if("AlMenosUnBoletoAsociado".equals(getEstatusFoliosTickets)){
      retval = true; 
    }
    
    return retval;
  }
  
  /**
     * Metodo para recuperar el estatus de los boletos 
     * @param pageContext
     * @param webBean
     * @param requestType
     * @return
     */
  public static String getEstatusFoliosTickets(OAPageContext pageContext, 
                                               OAWebBean webBean, 
                                               String requestType)
  {
      String retval = null; 
      if ((pageContext != null) && (webBean != null)) {
          XxGamModAntAMImpl amXxGamMod = null;
          amXxGamMod = getApplicationModule(pageContext, webBean);
          retval = amXxGamMod.findEstatusFoliosTickets(requestType);
      }
      
      if(null!=retval){
         if("AlMenosUnBoletoSinAsociar".equals(retval)||"AlMenosUnBoletoCancelado".equals(retval)||"AlMenosUnBoletoAsociado".equals(retval)){
         }
         else{
           System.out.println("No se pudo determinar el estatus de los boletos capa Utilitarios");
          // throw new OAException("No se pudo determinar el estatus de los boletos capa Utilitarios",OAException.ERROR); 
         }
      }
      return retval; 
  }


  /**
   * Metodo para validar que los boletos a emitir en oficina de boletos no esten en 0
   * @param pageContext
   * @param webBean
   */
  public static void validateTicketsSendConfirmation(OAPageContext pageContext, 
                                                     OAWebBean webBean)
  {
   
    System.out.println("Comienza validacion emision de boletos capa Util"); 
    XxGamModAntAMImpl AntAMImpl = null; 
    AntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
    
    String ticketIdStr = null; 
    String paymentIdStr = null; 
    String generalIdStr = null; 
    String tipoSolicitudStr = null; 
    String statusNotiCodeStr = null; 
    Number  ticketIdNum = null; 
    Number  paymentIdNum = null; 
    Number generalIdNum = null; 
    
    
    ticketIdStr = pageContext.getParameter("ticketId");
    paymentIdStr = pageContext.getParameter("paymentId");
    generalIdStr = pageContext.getParameter("generalId");
    tipoSolicitudStr =pageContext.getParameter("TipoSolicitud");
    statusNotiCodeStr = pageContext.getParameter("statusNotiCode");
    
    if(null!=ticketIdStr){
    ticketIdNum = XxGamMAnticiposUtil.converteNumber(ticketIdStr);
    }
    if(null!=paymentIdStr){
    paymentIdNum = XxGamMAnticiposUtil.converteNumber(paymentIdStr);
    }
    if(null!=generalIdStr){
    generalIdNum = XxGamMAnticiposUtil.converteNumber(generalIdStr);
    }
    System.out.println(" ticketIdNum:"+ticketIdNum+" paymentIdNum:"+paymentIdNum+" generalIdNum:"+generalIdNum+" tipoSolicitudStr:"+tipoSolicitudStr+" statusNotiCodeStr:"+statusNotiCodeStr);
    
    if(null!=AntAMImpl){
      AntAMImpl.validateTicketsSendConfirmation(ticketIdNum,paymentIdNum,generalIdNum,tipoSolicitudStr); 
    }
    
    System.out.println("Finaliza validacion emision de boletos capa Util");  
        
  }

  /**
   * Metodo para limpiar los valores de los boletos para ayudar a no deshabilitar botones en caso de que no se 
   * efectue una cancelacion o un envio de confirmacion
   * @param pageContext
   * @param webBean
   */
  public static void cleanTickestValues(OAPageContext pageContext, 
                                        OAWebBean webBean)
  {
    
    System.out.println("Comienza limpieza de boletos capa Util"); 
    XxGamModAntAMImpl AntAMImpl = null; 
    AntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
    
    String ticketIdStr = null; 
    String paymentIdStr = null; 
    String generalIdStr = null; 
    String tipoSolicitudStr = null; 
    String statusNotiCodeStr = null; 
    Number  ticketIdNum = null; 
    Number  paymentIdNum = null; 
    Number generalIdNum = null; 
    
    /*******************************************/
     String strFranchiseType = null; 
     String strRequestType = null; 
     String nvlFranchiseType = null; 
     String nvlRequestType = null; 
                 
            
     if(null!=pageContext.getParameter("pfranchiseType")&&!"".equals(pageContext.getParameter("pfranchiseType"))){
     strFranchiseType = pageContext.getParameter("pfranchiseType"); 
     }
                  
     if(null!=pageContext.getParameter("pRequest")&&!"".equals(pageContext.getParameter("pRequest"))){
     strRequestType = pageContext.getParameter("pRequest"); 
     } 
                
     nvlFranchiseType = (null==strFranchiseType)?(String)pageContext.getSessionValue("sfranchiseType"):strFranchiseType; 
     nvlRequestType = (null==strRequestType)?(String)pageContext.getSessionValue("sRequest"):strRequestType; 
     /*********************************/
    
    
    ticketIdStr = pageContext.getParameter("ticketId");
    paymentIdStr = pageContext.getParameter("paymentId");
    generalIdStr = pageContext.getParameter("generalId");
    tipoSolicitudStr =pageContext.getParameter("TipoSolicitud");
    statusNotiCodeStr = pageContext.getParameter("statusNotiCode");
    
    /** Escenario se presiona el Boton Cancelar Y luego NO en la pantalla donde se registran las rutas y pasajeros **/
    /** � Aplica para creacion de boleto de avion en Anticipos ? R=No **/
    /** � Aplica para creacion de franquicias ? R=Si **/
    /** � Aplica para asociacion de Boletos en Oficina de Boletos ? R =Si el parametro TipoDeSolicitud No Viene  Vacio **/
    if(null==tipoSolicitudStr){
      if(XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                      webBean, 
                                                      new Number(pageContext.getResponsibilityId()), 
                                                      XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)){
         tipoSolicitudStr = XxGamConstantsUtil.REQUEST_TYPE_ADVANCE;
       }
      else if(XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                      webBean, 
                                                      new Number(pageContext.getResponsibilityId()), 
                                                      XxGamConstantsUtil.RESPONSABILITY_FRANCHISE)
             ||XxGamConstantsUtil.RESPONSABILITY_FRANCHISE.equals(nvlRequestType)){
        tipoSolicitudStr = XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE;                                              
      }
    }
      
    if(null!=ticketIdStr){
    ticketIdNum = XxGamMAnticiposUtil.converteNumber(ticketIdStr);
    }
    if(null!=paymentIdStr){
    paymentIdNum = XxGamMAnticiposUtil.converteNumber(paymentIdStr);
    }
    if(null!=generalIdStr){
    generalIdNum = XxGamMAnticiposUtil.converteNumber(generalIdStr);
    }
    System.out.println(" ticketIdNum:"+ticketIdNum+" paymentIdNum:"+paymentIdNum+" generalIdNum:"+generalIdNum+" tipoSolicitudStr:"+tipoSolicitudStr+" statusNotiCodeStr:"+statusNotiCodeStr);
    
    if(null!=AntAMImpl){
      AntAMImpl.cleanTickestValues(ticketIdNum,paymentIdNum,generalIdNum,tipoSolicitudStr); 
    }
    
    System.out.println("Finaliza limpieza de boletos capa Util");  
    
  }

  /**
   *  Metodo para imprimir logs
   * @param type
   * @param messageInfo
   * @param pageContext
   * @param webBean
   */
  public static void debugMessage(String type,
                                  String messageInfo,
                                  OAPageContext pageContext, 
                                  OAWebBean webBean)
  {
     if((null!=type&&!"".equals(type))&&(null!=messageInfo&&!"".equals(messageInfo))){
        if(type.equals(XxGamConstantsUtil.DEBUG_OUTLINE_MODE)){
          System.out.println(">>> " + messageInfo);
        }else if(type.equals(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE)){
          pageContext.writeDiagnostics("XxGamMAnticiposUtil",">>> " + messageInfo,OAFwkConstants.STATEMENT);
        }else if(type.equals(XxGamConstantsUtil.DEBUG_BOTH_MODE)){
          System.out.println(">>> " + messageInfo);
          pageContext.writeDiagnostics("XxGamMAnticiposUtil",">>> " + messageInfo,OAFwkConstants.STATEMENT);
        }else{
          System.out.println(">>> Debug mode invalid: " + type);
        } 
     }
  }

  /**
   * Metodo para inicializar la lista de valores TicketClassLov
   * @param pageContext
   * @param webBean
   */
  public static void initTicketClassLov(OAPageContext pageContext, 
                                        OAWebBean webBean)
  {
    if (pageContext != null && webBean != null) {

        XxGamModAntAMImpl am = XxGamMAnticiposUtil.getApplicationModule(pageContext, webBean);

        if (am != null) {
            XxGamModAntLovAMImpl amLov = null;
            amLov = (XxGamModAntLovAMImpl)am.getXxGamModAntLovAM1();
            if (amLov != null) {
                amLov.initTicketClassLov(pageContext,webBean);
            }
        }
    }
  }

  /**
   * Metodo para inicializar la lista de valores TicketRateLov
   * @param pageContext
   * @param webBean
   */
  public static void initTicketRateLov(OAPageContext pageContext, 
                                       OAWebBean webBean)
  {
    if (pageContext != null && webBean != null) {

        XxGamModAntAMImpl am =
            XxGamMAnticiposUtil.getApplicationModule(pageContext, webBean);

        if (am != null) {
            XxGamModAntLovAMImpl amLov = null;
            amLov = (XxGamModAntLovAMImpl)am.getXxGamModAntLovAM1();
            if (amLov != null) {
                amLov.initTicketRateLov(pageContext,webBean);
            }
        }
    }
  }

  
  /**
     * Verifica que la solicitud de franquicia tenga al menos un pasajero registrado. 
     * @param pageContext
     * @param webBean
     * @param typeEmission   tipo de solicitud que son "Franquicia o Solicitud de Anticipo"
     * @return  devuelve true cuando cuando todas las validaciones fueron exitosas
     */
    public static boolean isRegisterPassengers(OAPageContext pageContext
                                              ,OAWebBean webBean
                                              ,String typeEmission)
    {
      boolean bIsRegisterPassengers = false; 
      if(webBean != null && pageContext != null){
       /*******************************************/
        String strFranchiseType = null; 
        String strRequestType = null; 
        String nvlFranchiseType = null; 
        String nvlRequestType = null; 
                    
               
        if(null!=pageContext.getParameter("pfranchiseType")&&!"".equals(pageContext.getParameter("pfranchiseType"))){
        strFranchiseType = pageContext.getParameter("pfranchiseType"); 
        }
                     
        if(null!=pageContext.getParameter("pRequest")&&!"".equals(pageContext.getParameter("pRequest"))){
        strRequestType = pageContext.getParameter("pRequest"); 
        } 
                   
        nvlFranchiseType = (null==strFranchiseType)?(String)pageContext.getSessionValue("sfranchiseType"):strFranchiseType; 
        nvlRequestType = (null==strRequestType)?(String)pageContext.getSessionValue("sRequest"):strRequestType; 
        /*********************************/
      
      System.out.println("IRP nvlFranchiseType: "+nvlFranchiseType);
      System.out.println("IRP nvlRequestType: "+nvlRequestType);
      System.out.println("IRP typeEmission: "+typeEmission);
      System.out.println("IRP1");
        XxGamModAntAMImpl amXxGamMod = null;
         amXxGamMod = getApplicationModule(pageContext, webBean);
         if(amXxGamMod != null){
           System.out.println("IRP2");
          XxGamMaPasajerosInfoVOImpl PasajerosInfoImpl = null; 
             if(XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                             webBean, 
                                                             new Number(pageContext.getResponsibilityId()), 
                                                             XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)){
             System.out.println("IRP3");
             bIsRegisterPassengers = true; 
           }
           else if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                webBean, 
                                                                new Number(pageContext.getResponsibilityId()), 
                                                                XxGamConstantsUtil.RESPONSABILITY_FRANCHISE)
                  ||XxGamConstantsUtil.RESPONSABILITY_FRANCHISE.equals(nvlRequestType)) {
             System.out.println("IRP4");
             PasajerosInfoImpl = amXxGamMod.getXxGamMaPasajerosInfoVO4();
             if(null!=PasajerosInfoImpl){
               System.out.println("IRP4.1");
               if(PasajerosInfoImpl.getEstimatedRowCount()>0){
                 System.out.println("IRP4.2");
                 bIsRegisterPassengers = true; 
               }
             }
           }
         }
      }
      return bIsRegisterPassengers;
    }

    /** Llama a la funcion para verificar que la filial sea valida
     * AGAA
     * @param pageContext
     * @param webBean
     * @param filial nombre de filial
     * @return 1 si es valida. 0 si no es valida
     */
    public static int callFunctionExistsFilial(OAPageContext pageContext, 
                                               OAWebBean webBean, 
                                               String filial) {
        XxGamModAntAMImpl am = getApplicationModule(pageContext, webBean);
        int exists = am.callFunctionExistsFilial(filial);
        return exists;

    }

    /**
     * Obtiene el monto de descuento de acuerdo a la clase de boleto
     * AGAA
     * @param pageContext
     * @param webBean
     */
    public static void getAmountDiscount(OAPageContext pageContext, 
                                                    OAWebBean webBean) {

        XxGamModAntAMImpl am = getApplicationModule(pageContext, webBean);
        
        try {
            XxGamMaTicketPVOImpl ticketVoImpl = am.getXxGamMaTicketPVO3();
            XxGamMaTicketPVORowImpl rowImpl = (XxGamMaTicketPVORowImpl)ticketVoImpl.getCurrentRow();
            Number amountBase = null;
            Number amountDiscount = null;
            String discountRate = null;
            if (null != rowImpl) {
                if(null != rowImpl.getAmountBase()){
                    amountBase = new Number(rowImpl.getAmountBase());
                    discountRate = rowImpl.getDiscountRate();
                    
                    amountDiscount = 
                            XxGamMAnticiposUtil.calculateAmountDiscount(discountRate, 
                                                                        amountBase);
                    Number result = null;
                    DecimalFormat df = new DecimalFormat("###############");
                    if(null != amountDiscount){
                        try  {
                            String sNumber = df.format(amountDiscount.doubleValue());
                            System.out.println("sNumber: " + sNumber);
                            result = new Number(sNumber);
                        } catch (Exception ex)  {
                            ex.printStackTrace();
                            throw new OAException(ex.getMessage(),OAException.WARNING);
                        } 
                    }
                    rowImpl.setAmountDiscount(result);
                    
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
        

    /**
     * Calcula el monto de descuento, con el porcentaje y el monto
     * AGAA
     * @param percentage cantidad de porcentaje
     * @param amount monto al cual se realizara el descuento
     * @return monto de descuento
     */
    public static Number calculateAmountDiscount(String percentage, 
                                                 Number amount) {
        Number amountMX = null;
        String sPercent = null;
        if (null == percentage || null == amount) {
            return amountMX;
        }
        try {
            sPercent = percentage.substring(0, percentage.lastIndexOf("%"));

            Number nPercent = converteNumber(sPercent);
            Number aux = nPercent.divide(new Number("100"));
            Number nDiscount = amount.multiply(aux);
            amountMX = amount.subtract(nDiscount);

        } catch (StringIndexOutOfBoundsException siobex) {
            siobex.printStackTrace();
            //Propaga la excepcion
            throw new OAException(siobex.getMessage(),OAException.WARNING);
        } catch (Exception ex) {
            ex.printStackTrace();
            //Propaga la excepcion
             throw new OAException(ex.getMessage(),OAException.WARNING);
        }

        return amountMX;
    }


    /**
     * Obtiene el total del importe
     * AGAA
     * @param pageContext
     * @param webBean
     */
    public static void getTotalAmount(OAPageContext pageContext, 
                                      OAWebBean webBean) {

        XxGamModAntAMImpl am = getApplicationModule(pageContext, webBean);

        try {
            XxGamMaTicketPVOImpl ticketVoImpl = am.getXxGamMaTicketPVO3();
            XxGamMaTicketPVORowImpl rowImpl = 
                (XxGamMaTicketPVORowImpl)ticketVoImpl.getCurrentRow();
            Number tax = null;
            Number totalAmont = null;
            Number amountDiscount = null;
            if (null != rowImpl) {
                if (null != rowImpl.getTaxDiscount() && 
                    null != rowImpl.getAmountDiscount()) {
                    
                    tax = rowImpl.getTaxDiscount();
                    amountDiscount = rowImpl.getAmountDiscount();

                    totalAmont = amountDiscount.add(tax);

                    DecimalFormat df = new DecimalFormat("#############.##");
                    if (null != totalAmont) {
                        try {
                            String sNumber = 
                                df.format(totalAmont.doubleValue());
                            System.out.println("sNumber: " + sNumber);
                            totalAmont = new Number(sNumber);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            throw new OAException(ex.getMessage(), 
                                                  OAException.WARNING);
                        }
                    }
                    rowImpl.setAmountBase(totalAmont);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Calcula el importe total, con el importe y el impuesto
     * AGAA
     * @param percentage cantidad de porcentaje
     * @param amount monto al cual se realizara el descuento
     * @return monto de descuento
     */
    public static Number calculateTotalAmount(String percentage, 
                                              Number amount) {
        Number amountMX = null;
        String sPercent = null;
        if (null == percentage || null == amount) {
            return amountMX;
        }
        try {
            sPercent = percentage.substring(0, percentage.lastIndexOf("%"));

            Number nPercent = converteNumber(sPercent);
            Number aux = nPercent.divide(new Number("100"));
            Number nDiscount = amount.multiply(aux);
            amountMX = amount.subtract(nDiscount);

        } catch (StringIndexOutOfBoundsException siobex) {
            siobex.printStackTrace();
            //Propaga la excepcion
            throw new OAException(siobex.getMessage(), OAException.WARNING);
        } catch (Exception ex) {
            ex.printStackTrace();
            //Propaga la excepcion
            throw new OAException(ex.getMessage(), OAException.WARNING);
        }

        return amountMX;
    }
}
