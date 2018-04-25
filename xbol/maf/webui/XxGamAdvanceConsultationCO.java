/*===========================================================================+
 |   Copyright (c) 2001, 2005 Oracle Corporation, Redwood Shores, CA, USA    |
 |                         All rights reserved.                              |
 +===========================================================================+
 |  HISTORY                                                                  |
 +===========================================================================*/
package xxgam.oracle.apps.xbol.maf.webui;

import com.sun.java.util.collections.HashMap;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAFwkConstants;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;


import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaOperatingUnitLovVOImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamModAntLovAMImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamModAntAMImpl;
import xxgam.oracle.apps.xbol.maf.utils.XxGamAOLMessages;
import xxgam.oracle.apps.xbol.maf.utils.XxGamConstantsUtil;
import xxgam.oracle.apps.xbol.maf.utils.XxGamMAnticiposUtil;

/**
 * Controlador de la pagina de consulta de solicitudes de anticipo para Auditor.
 * Definida por XML XxGamAdvanceConsultationPG
 * 
 * @author Manuel Guinto.
 */
public class XxGamAdvanceConsultationCO extends OAControllerImpl {
    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED =
        VersionInfo.recordClassVersion(RCS_ID, "%packagename%");

    /**
     * Resumen de la pagina de pago de la solicitud de anticipo.
     */
    public static final String XX_GAM_PAYMENT_REQ_REVIEW_PG =
        "XxGamPaymentReqReviewPG";

    /**
     * Layout and page setup logic for a region.
     * @param pageContext the current OA page context
     * @param webBean the web bean corresponding to the region
     */
    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);        
        initValues(pageContext, webBean);
        
      //Procesa los mensajes entrantes al cargar la pagina
      msgLoadPageProcess(pageContext, webBean);
      
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

         //Validar Eventos de los List View Objects 
          if (pageContext.isLovEvent()) 
          { 
           System.out.println("Event: "+pageContext.getParameter(OAWebBeanConstants.EVENT_PARAM));
          }
          
        //Obtiene el evento de busqueda de la solicitud.
        String searchButton = null;
        searchButton =
                pageContext.getParameter(XxGamConstantsUtil.SEARCH_BUTTON);
        if (searchButton != null) {
            //Validar lista de Valores de unidad operativa 
            validarLOV(pageContext,webBean);
            //Inicia busqueda avanzada
            searchAdvanceConsultantion(pageContext, webBean);
        }

        //Redirecciona a la siguiente pagina enviando el detalle.
        if (XxGamConstantsUtil.SHOW_DETAIL_REQUEST.equals(pageContext.getParameter(EVENT_PARAM)))
            setForwardWhitParameters(pageContext, webBean);


        //Obtiene el evento para exportar los datos
        String exportButton = null;
        exportButton =
                pageContext.getParameter(XxGamConstantsUtil.EXPORT_BUTTON);

        //Exporta la información
        if (exportButton != null)
            XxGamMAnticiposUtil.exportXML(pageContext, webBean);

    }

    /**
     * Busqueda avanzada de consulta de solciitudes.
     *
     * @param pageContext Contexto de la pagina.
     * @param webBean web bean.
     */
    private void searchAdvanceConsultantion(OAPageContext pageContext,
                                            OAWebBean webBean) {

        //Verifica nulidad
        if (pageContext == null || webBean == null)
            return;


        //Declara los recursos
        String advanceNum = null;
        String employeeName = null;
        String nameApprover = null;
        String nameApproverByH = null;
        String nameSupervisor = null;
        String costCenter = null;
        String costCenterFlex = null;
        String virtualCard = null;
        String statusRequest = null;
        String statusTicket = null;
        Date dateFrom = null;
        Date dateTo = null;
        String operatingUnit = null; 

        //Numero de anticipo
        advanceNum =
                pageContext.getParameter(XxGamConstantsUtil.ADVANCE_NUM);
        //Nombre del empleado
        employeeName =
                pageContext.getParameter(XxGamConstantsUtil.EMPLOYEE_NAME);

        //Nombre del aprovador
        nameApproverByH =
                pageContext.getParameter("ApproverAllHierarchyLov");
                
        nameSupervisor = pageContext.getParameter("SupervisorName");

        //Centro de costos
        costCenter = pageContext.getParameter("CostCenterEdit");
        
        costCenterFlex = pageContext.getParameter("CostCenterFlexLov");

        //Tarjeta virtual
        virtualCard =
                pageContext.getParameter(XxGamConstantsUtil.VIRTUAL_CARD);

        //Estatus de la solicitud.
        statusRequest =
                pageContext.getParameter(XxGamConstantsUtil.REQUEST_STATE);

        //Estatus del boleto
        statusTicket =
                pageContext.getParameter(XxGamConstantsUtil.TICKET_STATE);

        //Fecha inicio.
        dateFrom =
                XxGamMAnticiposUtil.converteDate(pageContext, pageContext.getParameter(XxGamConstantsUtil.FROM_DATE));

        //Fecha Fin.
        dateTo =
                XxGamMAnticiposUtil.converteDate(pageContext, pageContext.getParameter(XxGamConstantsUtil.TO_DATE));

        if(nameApproverByH != null){
            nameApprover = nameApproverByH;
        }else{
            nameApprover = nameSupervisor;
        }

      operatingUnit = pageContext.getParameter("OperatingUnitSearch");
      System.out.println("Valor de virtualCard: "+virtualCard);    
        System.out.println("Valor de la Unidad Operativa:"+operatingUnit);     
        System.out.println("Valor de costCenterFlex :"+costCenterFlex);     
      
        //Inicia la busqueda.
        XxGamMAnticiposUtil.searchAdvanceConsultantion(pageContext,
                                                       webBean, advanceNum,
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
    }

    /**
     * Inicializan los parametros que se van a enviar al detalle.
     *
     * @param pageContext Contexto de la pagina
     * @param webBean Web bean.
     */
    private void setForwardWhitParameters(OAPageContext pageContext,
                                          OAWebBean webBean) {
        //VErifica nulidad
        if (pageContext == null || webBean == null)
            return;

        //Inicializa los parametros
        HashMap hParameters = new HashMap();
        String sURL = null;
            
        //Obtiene los parametros y los inicializa
        String strRequestId = null;
        strRequestId = pageContext.getParameter(XxGamConstantsUtil.REQUEST_ID);
        Number requestId = XxGamMAnticiposUtil.converteNumber(strRequestId);
        
        //Busca el registro
        boolean foundReq = 
            XxGamMAnticiposUtil.searchRequest(pageContext, webBean, 
                                              requestId);
        
        String statusRequest = null;
        String statusFranchise = null;
        String typeRequest = null;
        
        statusRequest = pageContext.getParameter("statusRequest");
        statusFranchise = pageContext.getParameter("statusFranchise");

        if(statusRequest != null && !"".equals(statusRequest)){
            sURL = XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_PAYMENT_REVIEW;
            typeRequest = XxGamConstantsUtil.REQUEST_TYPE_ADVANCE;
        }else{
            if(statusFranchise != null && !"".equals(statusFranchise)){
                sURL = XxGamConstantsUtil.URL_PAGE_OAF + XxGamConstantsUtil.XX_GAM_PAYMENT_REQ_INFO_GENERAL_PG;
                typeRequest = XxGamConstantsUtil.REQUEST_TYPE_FRANCHISE;
            }
        }
        
        hParameters.put(XxGamConstantsUtil.REQUEST_ID, strRequestId);
        hParameters.put(XxGamConstantsUtil.STATUS, XxGamConstantsUtil.READ_ONLY);
        hParameters.put(XxGamConstantsUtil.TYPE_REQUEST_TICKET, typeRequest);

        //Inicializa los parametros
        if (foundReq && sURL != null)
            XxGamMAnticiposUtil.setForwardWhitParameters(pageContext,
                                                         webBean,
                                                         hParameters,
                                                         sURL);
        else
            throw new OAException(XxGamAOLMessages.GenericType.SHORT_NAME_XBOL,
                                          XxGamAOLMessages.GenericType.XXGAM_MAF_REQ_DATANF_ERROR,
                                          null, OAException.WARNING, null);
    }
    
    private void initValues(OAPageContext pageContext, OAWebBean webBean){
        
        if(pageContext != null && webBean != null){
            
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = XxGamMAnticiposUtil.getApplicationModule(pageContext, webBean);
            
            if(amXxGamMod != null){
                amXxGamMod.initCostCenterFlex();
            }
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
         
         System.out.println("Capa Webui Parameter localErrcodOUT:"+localErrcodOUT); 
         
         pageContext.writeDiagnostics(XxGamPaymentInitAdvanceCO.class,"Capa Webui Parameter localErrcodOUT:"+localErrcodOUT,OAFwkConstants.STATEMENT);
         pageContext.writeDiagnostics(XxGamPaymentInitAdvanceCO.class,"Capa Webui Parameter localErrmsgOUT:"+localErrmsgOUT,OAFwkConstants.STATEMENT);
         
         if(!"0".equals(localErrcodOUT)&&(null!=localErrcodOUT)){
          //pageContext.removeTransactionValue(XxGamConstantsUtil.VIEW_STEP_ONE);
          throw new OAException("No se Ha encontrado organizacion para este empleado",OAException.ERROR);
         }
         
       }
      
  
    }

  /**
   * Validar los valores que se ingresen a la lista de valores Operating Unit
   *
   * @param pageContext Contexto de la pagina.
   * @param webBean web bean.
   */
  private void validarLOV(OAPageContext pageContext, OAWebBean webBean)
  {
      if(null==pageContext&&null==webBean)
        return;
     
    XxGamModAntAMImpl AntAMImpl = null;
    AntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
    XxGamModAntLovAMImpl AntLovAMImpl = null; 
    AntLovAMImpl = (XxGamModAntLovAMImpl)AntAMImpl.getXxGamModAntLovAM1();
    
    String operatingUnit = null;
    operatingUnit = pageContext.getParameter("OperatingUnitSearch"); 
    boolean searchOperatingUnit = false;
    
    if(null!=operatingUnit&&!"".equals(operatingUnit)){
      XxGamMaOperatingUnitLovVOImpl OperatingUnitLovVO = null; 
      OperatingUnitLovVO = (XxGamMaOperatingUnitLovVOImpl)AntLovAMImpl.getXxGamMaOperatingUnitLovVO1(); 
      if (null!=OperatingUnitLovVO){
        // 2. Create a view criteria row set for this view object
        ViewCriteria vc = OperatingUnitLovVO.createViewCriteria();
        // 3. Use the view criteria to create one or more view criteria rows
        ViewCriteriaRow vcr1 = vc.createViewCriteriaRow();
        // 4. Set attribute values to filter on in appropriate view criteria rows
        vcr1.setAttribute("OrgName",operatingUnit);
        // 5. Add the view criteria rows to the view critera row set
        vc.add(vcr1);
        // 6. Apply the view criteria to the view object
         OperatingUnitLovVO.applyViewCriteria(vc);
        // 7. Execute the query
        OperatingUnitLovVO.executeQuery();
              if (OperatingUnitLovVO.hasNext()){
                searchOperatingUnit =true;
              }
        // 8. Execute the query      
        OperatingUnitLovVO.clearViewCriterias();      
      }
    } 
    
    if(null!=operatingUnit&&!"".equals(operatingUnit)){
      if  (!searchOperatingUnit) { 
        String getData = "Eliga un valor valido para la lista de valores Operating Unit";
        throw new OAException(getData,OAException.ERROR);
      } 
    }
    
  }
  
}
