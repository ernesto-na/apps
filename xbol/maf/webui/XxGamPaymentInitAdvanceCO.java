/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.xbol.maf.webui;

import com.sun.java.util.collections.HashMap;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAFwkConstants;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.domain.Number;

import oracle.jdbc.OracleCallableStatement;

/*import xxgam.oracle.apps.ar.facturas.agrupadas.server.principalAMImpl;//Commented by SEJR 19042018*/
import xxgam.oracle.apps.xbol.maf.server.XxGamModAntAMImpl;
import xxgam.oracle.apps.xbol.maf.utils.XxGamAOLMessages;
import xxgam.oracle.apps.xbol.maf.utils.XxGamConstantsUtil;
import xxgam.oracle.apps.xbol.maf.utils.XxGamMAnticiposUtil;


/**
 * Controlador de la pagina OAF de consulta de solicitud de anticipo empleado/franquicia.
 * Pagina definida por el XML XxGamPaymentInitAdvancePG
 *
 * @author Manuel Guinto.
 */
public class XxGamPaymentInitAdvanceCO extends OAControllerImpl {

    /**
     * Resumen de la pagina de pago de la solicitud de anticipo.
     */
    public static final String XX_GAM_PAYMENT_REQ_REVIEW_PG = 
        "XxGamPaymentReqReviewPG";

    /**
     * Resumen de la pagina de solicitud de informacion general
     */
    public static final String XX_GAM_PAYMENT_REQ_INFO_GENERAL_PG = 
        "XxGamPaymentReqInfoGeneralPG";

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
        
        XxGamModAntAMImpl XxGamModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
        XxGamMAnticiposUtil XxGamMAnticiposUtil = new XxGamMAnticiposUtil();
        
        int nUserId = 0;
        String sUserName = null;
        sUserName = pageContext.getUserName();
        System.out.println("Recuperando sUserName: "  + sUserName);
        nUserId = pageContext.getUserId();//XxGamMAnticiposUtil.getPersonIdByUserName(pageContext, webBean, sUserName, XxGamConstantsUtil.VENDOR_TYPE_EMPLOYEE);
        System.out.println("Recuperado nUserId: " + nUserId);

        
        
        if (!XxGamModAntAMImpl.obtieneTipoPerson(nUserId)){
            System.out.println("El tramite para viajes en comision de servicio por CORE, aplica para personal con contrato de Planta, favor de contactar a la asistente de tu Direccion Ejecutiva / Corporativa. .");
            HashMap hParameters = new HashMap();
            
             hParameters.put("NoPersonTypeValid","El tramite para viajes en comision de servicio por CORE, aplica para personal con contrato de Planta, favor de contactar a la asistente de tu Direccion Ejecutiva / Corporativa."); 
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
        }else{
        
        
        
       if(!pageContext.isFormSubmission()){
         System.out.println("Carga XxGamPaymentInitAdvanceCO in !pageContext.isFormSubmission()");
           pageContext.putParameter("pRequest","GAM_FRANQUICIAS");
         /**
          * *Codigo que valida el caso el que se pierda el valor de Origen desde la Funcion.
          * */ 
          
          if(null==pageContext.getParameter("pfranchiseType")){
            System.out.println("Invocacion para obtenere datos desde pageContext.getFunctionId()");
            pageContext.getFunctionId();
             pageContext.putParameter("pfranchiseType",getValueFunction(pageContext,webBean));;
            
          }
          
           XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"FormSubmissionBug1 AdvPG: "+pageContext.getParameter("pfranchiseType"),pageContext,webBean);
           XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"FormSubmissionBug1 AdvPG: "+pageContext.getParameter("pRequest"),pageContext,webBean); 
         
         
         
         String strFranchiseType = null; 
         String strRequestType = null; 
         strFranchiseType = pageContext.getParameter("pfranchiseType");
         strRequestType = pageContext.getParameter("pRequest");
         
         if(null!=strFranchiseType&&!"".equals(strFranchiseType)){
            pageContext.putSessionValue("sfranchiseType",pageContext.getParameter("pfranchiseType"));
         }
         
         if(null!=strRequestType&&!"".equals(strRequestType)){
            pageContext.putSessionValue("sRequest",pageContext.getParameter("pRequest"));
         }
         
       }else{
         System.out.println("Carga XxGamPaymentInitAdvanceCO in !pageContext.isFormSubmission() else");
         XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"FormSubmissionBug2 AdvPG: "+pageContext.getParameter("pfranchiseType"),pageContext,webBean);
         XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"FormSubmissionBug2 AdvPG: "+pageContext.getParameter("pRequest"),pageContext,webBean); 
         XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"SessionValue AdvPG: "+pageContext.getSessionValue("sfranchiseType"),pageContext,webBean); 
         XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"SessionValue AdvPG: "+pageContext.getSessionValue("sRequest"),pageContext,webBean); 
  
         if(null==pageContext.getParameter("pfranchiseType")){
           if(null!=pageContext.getSessionValue("sfranchiseType")){
             pageContext.putParameter("pfranchiseType",pageContext.getSessionValue("sfranchiseType"));
           }
         }
         
         if(null==pageContext.getParameter("pRequest")){
           if(null!=pageContext.getSessionValue("sRequest")){
             pageContext.putParameter("pRequest",pageContext.getSessionValue("sRequest"));
           }
         }
         
       }     
            
        pageContext.putSessionValue("ListVal","");
        pageContext.putTransactionValue("tmpID", 0);
        
        //if (!pageContext.isBackNavigationFired(false)) {
            //Inicializa los valores
            setRenderComponentForResponsability(pageContext, webBean);

            //Ejecuta la solicitud de anticipo
            executeQueryAdvanceReq(pageContext, webBean);

            //Procesa los mensajes entrantes al cargar la pagina
            msgLoadPageProcess(pageContext, webBean);
        //}
        }
    }

    /**
     * Inicializa componentes por responsabilidad.
     *
     * @param pageContext contexto de la pagina.
     * @param webBean web bean.
     */
    private void setRenderComponentForResponsability(OAPageContext pageContext, 
                                                     OAWebBean webBean) {

        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return;

        //setea los componentes por responsabilidad
        if (XxGamMAnticiposUtil.validatesResponsability(pageContext, webBean, 
                                                        new Number(pageContext.getResponsibilityId()), 
                                                        XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)) { //Aplica para solicitudes

            //Empleado
            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                     "EstatusRequest11", true);
            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                     "EstatusRequest1", true);
            XxGamMAnticiposUtil.setRenderedComponent(webBean, "irCreateReq", 
                                                     true);

            //Franquicia
            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                     "statusFranchise", false);
            XxGamMAnticiposUtil.setRenderedComponent(webBean, "StatusFra", 
                                                     false);
            XxGamMAnticiposUtil.setRenderedComponent(webBean, "irCreateReq1", 
                                                     false);

        } else if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                               webBean, 
                                                               new Number(pageContext.getResponsibilityId()), 
                                                               XxGamConstantsUtil.RESPONSABILITY_FRANCHISE)
                 ||XxGamConstantsUtil.RESPONSABILITY_FRANCHISE.equals(pageContext.getParameter("pRequest"))) { //Aplica para franquicia

            //Empleado
            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                     "EstatusRequest11", 
                                                     false);
            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                     "EstatusRequest1", false);
            XxGamMAnticiposUtil.setRenderedComponent(webBean, "irCreateReq", 
                                                     false);

            //Franquicia
            XxGamMAnticiposUtil.setRenderedComponent(webBean, 
                                                     "statusFranchise", true);
            XxGamMAnticiposUtil.setRenderedComponent(webBean, "StatusFra", 
                                                     true);
            XxGamMAnticiposUtil.setRenderedComponent(webBean, "irCreateReq1", 
                                                     true);

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


        //Redirecciona a la siguiente pagina enviando el detalle.
        if (XxGamConstantsUtil.SHOW_DETAIL_REQUEST.equals(pageContext.getParameter(EVENT_PARAM)))
            setForwardWhitParameters(pageContext, webBean, 1, 1, null);

        //Redirecciona a la siguiente pagina enviando el detalle.
        if (XxGamConstantsUtil.SHOW_DETAIL_REQUEST2.equals(pageContext.getParameter(EVENT_PARAM)))
            setForwardWhitParameters(pageContext, webBean, 1, 2, null);

        //Redirecciona a la pagina de creacion de la solicitud.
        String sGoCreateReq = null;
        sGoCreateReq = 
                pageContext.getParameter(XxGamConstantsUtil.GO_CREATE_REQ);

        //Verifica nulidad
        if (sGoCreateReq != null){
            goCreateRequest(pageContext, webBean);
        }else{
            
            sGoCreateReq = pageContext.getParameter(XxGamConstantsUtil.GO_CREATE_REQ + "1");
            if(sGoCreateReq != null){
                goCreateRequest(pageContext, webBean);
            }
        }
            
        //Duplica el registro seleccionado
        if (XxGamConstantsUtil.DUPLICATE.equals(pageContext.getParameter(EVENT_PARAM))) {

            duplicateRequest(pageContext, webBean, 1);
            String errorMsg = XxGamMAnticiposUtil.setCommit(pageContext, webBean);
            if (errorMsg == null) {
                
                String strNumberPayment = null;
                strNumberPayment = XxGamMAnticiposUtil.getNumberPayment(pageContext, webBean);
                if (strNumberPayment == null) {
                    strNumberPayment = "";
                }
                XxGamMAnticiposUtil.getXxGamModAntAM(pageContext, webBean);
                MessageToken[] tokens = {new MessageToken("NUMBER_PAYMENT", strNumberPayment)};
                throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                  XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DUP_CONF_INFO,
                                                  tokens, OAException.INFORMATION, null);
            } else {
                XxGamMAnticiposUtil.setRollback(pageContext, webBean);
                XxGamMAnticiposUtil.getXxGamModAntAM(pageContext, webBean);
                MessageToken[] tokens = {new MessageToken("MSG_ERROR", errorMsg)};
                throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                  XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DUP_SAVE_ERROR,
                                                  tokens, OAException.ERROR, null);
            }
        }

        //Duplica el registro seleccionado
        if (XxGamConstantsUtil.DUPLICATE2.equals(pageContext.getParameter(EVENT_PARAM))) {

            duplicateRequest(pageContext, webBean, 2);
            String errorMsg = 
                XxGamMAnticiposUtil.setCommit(pageContext, webBean);
            if (errorMsg == null) {
                XxGamMAnticiposUtil.getXxGamModAntAM(pageContext, webBean);
                
                String strNumberPayment = null;
                strNumberPayment = XxGamMAnticiposUtil.getNumberPayment(pageContext, webBean);
                if (strNumberPayment == null) {
                    strNumberPayment = "";
                }
                XxGamMAnticiposUtil.getXxGamModAntAM(pageContext, webBean);
                MessageToken[] tokens = {new MessageToken("NUMBER_PAYMENT", strNumberPayment)};
                throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                  XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DUP_CONF_INFO,
                                                  tokens, OAException.INFORMATION, null);
            } else {
                XxGamMAnticiposUtil.setRollback(pageContext, webBean);
                XxGamMAnticiposUtil.getXxGamModAntAM(pageContext, webBean);
                MessageToken[] tokens = {new MessageToken("MSG_ERROR", errorMsg)};
                throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                  XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DUP_SAVE_ERROR,
                                                  tokens, OAException.ERROR, null);                                                  
            }
        }

        //Actualizar el registro
        if (XxGamConstantsUtil.UPDATE.equals(pageContext.getParameter(EVENT_PARAM))) {
            String sURL = null;
            sURL = 
XxGamConstantsUtil.URL_PAGE_OAF + XX_GAM_PAYMENT_REQ_INFO_GENERAL_PG;
            setForwardWhitParameters(pageContext, webBean, 2, 1, sURL);
        }

        //Actualizar el registro
        if (XxGamConstantsUtil.DELETE.equals(pageContext.getParameter(EVENT_PARAM)))
            deleteRequest(pageContext);

        //Verifica eliminar el registro
        if (pageContext.getParameter(XxGamConstantsUtil.BUTTON_YES) != null)
            deleteRequest(pageContext, webBean);


    }

    /**
     * Verifica si realmente quiere eliminar el registro.
     *
     * @param pageContext contexto de la pagina.
     */
    public void deleteRequest(OAPageContext pageContext) {

        String sRequestId = null;
        Number nRequestId = null;
        Number costCenter = null;
        java.util.Hashtable params = new java.util.Hashtable(1);

        //Inicializa los parametros
        sRequestId = pageContext.getParameter(XxGamConstantsUtil.REQUEST_ID);
        nRequestId = XxGamMAnticiposUtil.converteNumber(sRequestId);
        costCenter = 
                XxGamMAnticiposUtil.converteNumber(pageContext.getParameter(XxGamConstantsUtil.COST_CENTER));
        
        String strNumberPayment = pageContext.getParameter(XxGamConstantsUtil.NUMBER_PAYMENT);

        //Asigna los parametros
        params.put(XxGamConstantsUtil.REQUEST_ID, nRequestId);
        params.put(XxGamConstantsUtil.COST_CENTER, costCenter);
        params.put(XxGamConstantsUtil.NUMBER_PAYMENT, strNumberPayment);
        
        String msg = null;
        msg = pageContext.getMessage(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL, XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DEL_CONF_QN, null);
        
        XxGamMAnticiposUtil.setMessageDialog(pageContext, 
                                             msg, 
                                             OAException.WARNING, params);
    }

    /**
     * Dulpica la solicitud seleccionada.
     *
     * @param pageContext pagina de contexto.
     * @param webBean web bean.
     * @param iTable Numero.
     */
    public void duplicateRequest(OAPageContext pageContext, OAWebBean webBean, 
                                 int iTable) {

        //VErifica nulidad
        if (pageContext == null || webBean == null)
            return;

        //Obtiene los parametros y los inicializa
        String sParam = null;

        if (iTable == 1)
            sParam = pageContext.getParameter(XxGamConstantsUtil.REQUEST_ID);
        else
            sParam = pageContext.getParameter(XxGamConstantsUtil.REQUEST_ID2);

        XxGamMAnticiposUtil.duplicateRequest(pageContext, webBean, sParam);
    }

    /**
     * Ejecuta el query de solicitud de anticipo.
     *
     * @param pageContext Pagina de contexto.
     * @param webBean web bean.
     */
    private void executeQueryAdvanceReq(OAPageContext pageContext, 
                                        OAWebBean webBean) {

        if (pageContext == null || webBean == null)
            return;

        //Ejecuta el query de solicitud  de anticipo
        XxGamMAnticiposUtil.getXxGamModAntAM(pageContext, webBean);
    }

    /**
     * Inicializan los parametros que se van a enviar al detalle.
     *
     * @param pageContext Contexto de la pagina
     * @param webBean Web bean.
     */
    private void setForwardWhitParameters(OAPageContext pageContext, 
                                          OAWebBean webBean, int op, 
                                          int iTable, String url) {
         
      System.out.println("Comienza setForwardWhitParameters "); 
      
      String strFranchiseType = null; 
      String strRequestType = null; 
      String nvlFranchiseType = null; 
      String nvlRequestType = null; 
                  
      XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"View franchiseType AdvPG: "+pageContext.getParameter("pfranchiseType"),pageContext,webBean);
      XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"View Request AdvPG: "+pageContext.getParameter("pRequest"),pageContext,webBean);
      XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"View Session franchiseType AdvPG: "+pageContext.getSessionValue("sfranchiseType"),pageContext,webBean);
      XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"View Session Request AdvPG: "+pageContext.getSessionValue("sRequest"),pageContext,webBean);
             
      if(null!=pageContext.getParameter("pfranchiseType")&&!"".equals(pageContext.getParameter("pfranchiseType"))){
      strFranchiseType = pageContext.getParameter("pfranchiseType"); 
      }
                   
      if(null!=pageContext.getParameter("pRequest")&&!"".equals(pageContext.getParameter("pRequest"))){
      strRequestType = pageContext.getParameter("pRequest"); 
      } 
                 
      nvlFranchiseType = (null==strFranchiseType)?(String)pageContext.getSessionValue("sfranchiseType"):strFranchiseType; 
      nvlRequestType = (null==strRequestType)?(String)pageContext.getSessionValue("sRequest"):strRequestType; 
      
        //VErifica nulidad
        if (pageContext == null || webBean == null)
            return;

        //Inicializa los parametros
        HashMap hParameters = new HashMap();
        String sURL = null;
        String sParam = null;
        Number nRequestId = null;

        //Obtiene los parametros y los inicializa

        if (iTable == 1)
            sParam = pageContext.getParameter(XxGamConstantsUtil.REQUEST_ID);
        else
            sParam = pageContext.getParameter(XxGamConstantsUtil.REQUEST_ID2);

        nRequestId = XxGamMAnticiposUtil.converteNumber(sParam);
        
      System.out.println("Informacion setForwardWhitParameters nRequestId-->"+nRequestId); 
        //Verifica que URL usar
        if (url == null) {

            if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                            webBean, 
                                                            new Number(pageContext.getResponsibilityId()), 
                                                            XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)) {
                sURL = XxGamConstantsUtil.URL_PAGE_OAF + XX_GAM_PAYMENT_REQ_REVIEW_PG;
            } else {
                if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                                webBean, 
                                                                new Number(pageContext.getResponsibilityId()), 
                                                                XxGamConstantsUtil.RESPONSABILITY_FRANCHISE)
                   ||XxGamConstantsUtil.RESPONSABILITY_FRANCHISE.equals(nvlRequestType)) {
                    sURL = XxGamConstantsUtil.URL_PAGE_OAF + XX_GAM_PAYMENT_REQ_INFO_GENERAL_PG;
                }
            }
        } else {
            sURL = url;
        }
      hParameters.put(XxGamConstantsUtil.REQUEST_ID, sParam);
      
      if(null==pageContext.getParameter("pfranchiseType")){
        hParameters.put("pfranchiseType",nvlFranchiseType);
      }
      if(null==pageContext.getParameter("pRequest")){
        hParameters.put("pRequest",nvlRequestType); 
      }
     
      
        //Busca el registro
        boolean foundReq = 
            XxGamMAnticiposUtil.searchRequest(pageContext, webBean, 
                                              nRequestId);

        if (op == 1)
            hParameters.put(XxGamConstantsUtil.STATUS, 
                            XxGamConstantsUtil.READ_ONLY);

        else if (op == 2)
            hParameters.put(XxGamConstantsUtil.STATUS, 
                            XxGamConstantsUtil.UPDATE);

        //Inicializa los parametros
        if (foundReq)
            XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, webBean, 
                                                         hParameters, sURL);

    }

    /**
     * Redirecciona a la pagina de creacion de la solicitud.
     *
     * @param pageContext Pagina de contexto.
     * @param webBean web bean.
     */
    private void goCreateRequest(OAPageContext pageContext, 
                                 OAWebBean webBean) {

        //VErifica nulidad
        if (pageContext == null || webBean == null)
            return;

      XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"franchiseType AdvPGGC: "+pageContext.getParameter("pfranchiseType"),pageContext,webBean);
      XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"Request AdvPGGC: "+pageContext.getParameter("pRequest"),pageContext,webBean);
      
      HashMap hmap = new HashMap();
        //Obtiene los parametros y los inicializa
        String sURL = null;
        sURL = XxGamConstantsUtil.URL_PAGE_OAF + XX_GAM_PAYMENT_REQ_INFO_GENERAL_PG;
        

        //Setea parametros en caso de que se haya originado una franquicia y se requiera solicictar una nueva 
         String strSFranchiseType = null; 
         String strSRequestType = null; 
         
         strSFranchiseType = (String)pageContext.getSessionValue("sfranchiseType"); 
         strSRequestType = (String)pageContext.getSessionValue("sRequest"); 
         
         if(null!=strSFranchiseType&&!"".equals(strSFranchiseType)){
            if(null==pageContext.getParameter("pfranchiseType")){
              hmap.put("pfranchiseType",strSFranchiseType);
            }
         }
         
         if(null!=strSRequestType&&!"".equals(strSRequestType)){
            if(null==pageContext.getParameter("pRequest")){
              hmap.put("pRequest",strSRequestType);
            }
         }
        
        hmap.put(XxGamConstantsUtil.STATUS, XxGamConstantsUtil.CREATE);

        System.out.println("Direcciona a la pagina: " + sURL);
        XxGamMAnticiposUtil.setForwardWhitParameters(pageContext, webBean, 
                                                     hmap, sURL);
      
    }
  
     /**
     * Elimina la slicitud seleccionada.
     * @param pageContext contiene el objeto de OAPageContext procedente de la pagina inicial de empleado
     * @param webBean contiene el objeto de OAWebBeab procedente de la pagina inicial de empleado
     */
    public void deleteRequest(OAPageContext pageContext, OAWebBean webBean) {


        //Verifica nulidad
        if (pageContext == null || webBean == null){
            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                              XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DEL_CONF_ERROR,
                                              null, OAException.ERROR, null);
        }
        
        //Mensaje de confirmaciÃ³n

        String sRequestId = null;
        Number nRequestId = null;
        String sStatus = null;
        Number costCenter = null;
        
        String strNumberPayment = pageContext.getParameter(XxGamConstantsUtil.NUMBER_PAYMENT);

        sRequestId = pageContext.getParameter(XxGamConstantsUtil.REQUEST_ID);
        nRequestId = XxGamMAnticiposUtil.converteNumber(sRequestId);
        costCenter = 
                XxGamMAnticiposUtil.converteNumber(pageContext.getParameter(XxGamConstantsUtil.COST_CENTER));
        sStatus = "DELETED";

        boolean isSuccess = false;
        //Cambia de estatus la solicitud
        isSuccess = 
                XxGamMAnticiposUtil.setStatusRequestReverseFunds(pageContext, 
                                                                 webBean, 
                                                                 sStatus, 
                                                                 nRequestId, 
                                                                 costCenter, 
                                                                 XxGamConstantsUtil.TYPE_PROCEDURE_R);
        if (isSuccess) {
            try {
                //Ejecuta commit
                XxGamMAnticiposUtil.setCommit(pageContext, webBean);
            } catch (Exception exception) {
                isSuccess = false;
                 throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                                   XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DEL_SAVE_ERROR,
                                                   null, OAException.ERROR, null);
            }
            
            //Reinicia los valores
            XxGamMAnticiposUtil.getXxGamModAntAM(pageContext, webBean);
        } else {
             throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                               XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DEL_CONF_ERROR,
                                               null, OAException.ERROR, null);
        }


        if (isSuccess) {
            MessageToken[] tokens = {new MessageToken("NUMBER_PAYMENT", strNumberPayment)};
            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                              XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DEL_CONF_INFO,
                                              tokens, OAException.INFORMATION, null);
        }
    }

    /**
     * Procesa y muestra mensajes informativos y de error
     * @param pageContext contiene el objeto de OAPageContext procedente de la pagina inicial de empleado
     * @param webBean contiene el objeto de OAWebBeab procedente de la pagina inicial de empleado
     */
    private void msgLoadPageProcess(OAPageContext pageContext, 
                                    OAWebBean webBean) {

        if (pageContext != null && webBean != null) {

          String localErrcodOUT = null; 
          String localErrmsgOUT = null; 
          
          localErrcodOUT =  pageContext.getParameter("errcodOUT");
          localErrmsgOUT = pageContext.getParameter("errmsgOUT");
          
          System.out.println("Capa Webui Parameter localErrcodOUT:"+localErrcodOUT); 
          System.out.println("Capa Webui Parameter localErrmsgOUT:"+localErrmsgOUT);
          
          pageContext.writeDiagnostics(XxGamPaymentInitAdvanceCO.class,"Capa Webui Parameter localErrcodOUT:"+localErrcodOUT,OAFwkConstants.STATEMENT);
          pageContext.writeDiagnostics(XxGamPaymentInitAdvanceCO.class,"Capa Webui Parameter localErrmsgOUT:"+localErrmsgOUT,OAFwkConstants.STATEMENT);
          
          if (localErrcodOUT==null){
            localErrcodOUT = (String)pageContext.getTransactionValue("errcodOUT");
          }
          
          if (localErrmsgOUT==null){
            localErrmsgOUT = (String)pageContext.getTransactionValue("errmsgOUT");
          }
          
          pageContext.writeDiagnostics(XxGamPaymentInitAdvanceCO.class,"Capa Webui TransactionValue localErrcodOUT:"+localErrcodOUT,OAFwkConstants.STATEMENT);
          pageContext.writeDiagnostics(XxGamPaymentInitAdvanceCO.class,"Capa Webui TransactionValue localErrmsgOUT:"+localErrmsgOUT,OAFwkConstants.STATEMENT);
          
          if((!"0".equals(localErrcodOUT))&&(localErrmsgOUT!=null)){
           pageContext.removeTransactionValue(XxGamConstantsUtil.VIEW_STEP_ONE);
           throw new OAException(localErrmsgOUT,OAException.ERROR);
          }
          
            String msg = null;
            msg = pageContext.getParameter(XxGamConstantsUtil.LOAD_PAGE_MSG_INFO);
            if (msg != null && !"NO".equals(msg)) {
                //Propaga la excepcion.
                throw new OAException(msg, OAException.INFORMATION);
            }

            msg = null;
            msg = pageContext.getParameter(XxGamConstantsUtil.LOAD_PAGE_MSG_ERROR);
            if (msg != null) {
                //pageContext.putTransactionValue(XxGamConstantsUtil.VIEW_STEP_ONE, "");
                pageContext.removeTransactionValue(XxGamConstantsUtil.VIEW_STEP_ONE);
                //Propaga la excepcion.
                throw new OAException(msg, OAException.WARNING);
            }
            
           
          
        }
    }
public String getValueFunction(OAPageContext pageContext, OAWebBean webBean){
  String OrcPrepStmt = " SELECT replace(substr(parameters, instr(PARAMETERS, 'pfranchiseType='),16),'pfranchiseType=', NULL)  " + 
                       "   FROM FND_FORM_FUNCTIONS                                                                            " + 
                       "   WHERE FUNCTION_ID =  ?                                                                ";
  
  String valueFuntionReturn = "G";
  int vFuctionPageContext = pageContext.getFunctionId();
  OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
  OADBTransactionImpl oadbtransactionimpl = (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
  
  
  
  ResultSet resultSet = null;
  OracleCallableStatement pstmt = null;
  
  try{
  
      pstmt = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(OrcPrepStmt,1);
      pstmt.setInt(1,vFuctionPageContext );
      resultSet = pstmt.executeQuery();
      if(resultSet.next()) {
         valueFuntionReturn = resultSet.getString(1);    
      }
      
  }catch(Exception e){
    System.out.println("Error al optener Valor de Function");
    XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"Error bug 01 al optener Valor de Function: " + e.getMessage() ,pageContext,webBean);
  }finally{
    try{ 
      pstmt.close();
      resultSet.close();  
    }catch(SQLException e){
      XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_DIAGNOSTICS_MODE,"Error bug 02 al cerrar PreparedStatement getValueFunction" ,pageContext,webBean);
    }
  }
  
  return valueFuntionReturn;
}

}
