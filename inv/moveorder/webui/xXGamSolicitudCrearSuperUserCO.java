package xxgam.oracle.apps.inv.moveorder.webui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.cp.request.ConcurrentRequest;
import oracle.apps.fnd.cp.request.RequestSubmissionException;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;
import oracle.apps.fnd.framework.webui.beans.nav.OAButtonBean;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;
import oracle.jdbc.OracleCallableStatement;
import xxgam.oracle.apps.inv.moveorder.xxDebugger;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliDtlVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSolicitudAMImpl;
import xxgam.oracle.apps.inv.moveorder.utils.XxGamInvConstantes;

@SuppressWarnings({"unchecked", "deprecation","rawtypes","static"})
public class xXGamSolicitudCrearSuperUserCO extends OAControllerImpl {
   String flagInsert = "Y";
   String globalEmployeeName = "XXX"; 
   public static final String RCS_ID = "$Header: xXGamSolicitudCrearSuperUserCO.java 1.1 2013/02/27 10:51:48 eroncoroni ship $";
   public static final boolean RCS_ID_RECORDED = VersionInfo.recordClassVersion("$Header: xXGamSolicitudCrearSuperUserCO.java 1.1 2013/02/27 10:51:48 eroncoroni ship $", "%packagename%");
   public static final String sqlKit = "SELECT NVL(XIK.KIT_ID,-1) , XUK.KIT_CODE , FLV.MEANING FROM XXGAM_HR_UNIFORMS_KIT_V XUK , XXGAM_INV_KIT XIK , FND_LOOKUP_VALUES FLV , FND_LOOKUP_VALUES FLV2 WHERE 1 = 1 AND XUK.KIT_CODE = XIK.KIT_COD(+) AND FLV.LOOKUP_TYPE = \'GAM_HR_UNIFORMES\' AND XUK.KIT_CODE = FLV.LOOKUP_CODE AND XUK.LANGUAGE = FLV.LANGUAGE AND FLV2.LOOKUP_TYPE = \'XXGAM_APERTURA_DE_PERIODOS\' AND FLV.LOOKUP_CODE = FLV2.MEANING AND FLV.LANGUAGE = FLV2.LANGUAGE AND (FLV2.START_DATE_ACTIVE IS NULL OR FLV2.START_DATE_ACTIVE <= TRUNC(SYSDATE)) AND (FLV2.END_DATE_ACTIVE IS NULL OR FLV2.END_DATE_ACTIVE >= TRUNC(SYSDATE)) AND XUK.LANGUAGE = \'ESA\' AND XUK.PERSON_ID = ?";
   public static final String sqlKit2 = "SELECT NVL(XIK.KIT_ID,-1) , XUK.KIT_CODE , FLV.MEANING FROM XXGAM_HR_UNIFORMS_KIT_V XUK , XXGAM_INV_KIT XIK , FND_LOOKUP_VALUES FLV WHERE 1 = 1 AND XUK.KIT_CODE = XIK.KIT_COD(+) AND FLV.LOOKUP_TYPE = \'GAM_HR_UNIFORMES\' AND XUK.KIT_CODE = FLV.LOOKUP_CODE AND XUK.LANGUAGE = FLV.LANGUAGE AND XUK.LANGUAGE = \'ESA\' AND XUK.PERSON_ID = ?";
   public static final String sqlNombreEmpleado = "SELECT xxgam_inv_soli_s.nextval,  ppf.person_id,  ppf.full_name AS nombre_empleado,   ppf.employee_number AS clave_empleado,   ppf.per_information2 AS rfc,  \'NA\' as categoria,   CASE   WHEN UPPER(PPT.USER_PERSON_TYPE) LIKE \'%EVENTUAL%\' THEN \'Eventual\'  WHEN UPPER(PPT.USER_PERSON_TYPE) LIKE \'%PLANTA%\' THEN \'Planta\'  ELSE PPT.USER_PERSON_TYPE  END AS adscripcion,  paaf.date_probation_end AS fecha_termino_contrato,   gcc.segment4 AS estacion,   gcc.segment3 AS centro_costos,   gcc.concatenated_segments AS cuenta,   gcc.code_combination_id,  sysdate as solidate,  \'PENDIENTE\' as pendiente,   pgd.segment4 as categoria_gr FROM per_all_people_f ppf,   per_all_assignments_f paaf,   gl_code_combinations_kfv gcc,   per_grade_definitions pgd,  per_grades pg,  PER_PERSON_TYPES PPT , PER_PERSON_TYPE_USAGES_F PPTU , PAY_USER_TABLES PUT , PAY_USER_COLUMN_INSTANCES_F PUCIF , PAY_USER_ROWS_F PURF , PAY_USER_ROWS_F_VL PURFV , PAY_USER_COLUMNS PUC WHERE SYSDATE BETWEEN ppf.effective_start_date   AND ppf.effective_end_date    AND SYSDATE BETWEEN paaf.effective_start_date  AND paaf.effective_end_date   AND ppf.person_id = paaf.person_id   AND gcc.code_combination_id (+) = paaf.default_code_comb_id     AND pgd.grade_definition_id(+) = pg.grade_definition_id  AND pg.grade_id (+)= paaf.grade_id  AND PPF.PERSON_ID = PPTU.PERSON_ID  AND (PPTU.EFFECTIVE_END_DATE IS NULL OR PPTU.EFFECTIVE_END_DATE >= TRUNC(SYSDATE))  AND PPTU.PERSON_TYPE_ID = PPT.PERSON_TYPE_ID AND UPPER(PPT.USER_PERSON_TYPE) LIKE (\'%\'||UPPER(PURFV.ROW_LOW_RANGE_OR_NAME)||\'%\') AND PUT.USER_TABLE_NAME = \'XXGAM_PARAMETROS_UNIFORMES\' AND PUT.BUSINESS_GROUP_ID = 81 AND PUT.USER_TABLE_ID = PURF.USER_TABLE_ID AND PUT.USER_TABLE_ID = PUC.USER_TABLE_ID AND PUC.USER_COLUMN_NAME = \'Tipo de Persona\' AND PUCIF.USER_ROW_ID = PURF.USER_ROW_ID AND PUCIF.USER_COLUMN_ID = PUC.USER_COLUMN_ID AND SYSDATE BETWEEN PURF.EFFECTIVE_START_DATE AND PURF.EFFECTIVE_END_DATE AND PURF.USER_ROW_ID = PURFV.USER_ROW_ID AND PUT.USER_TABLE_ID = PURFV.USER_TABLE_ID AND SYSDATE BETWEEN PUCIF.EFFECTIVE_START_DATE AND PUCIF.EFFECTIVE_END_DATE  AND ppf.person_id = ?";
   public static final String sqlDota = "SELECT xxgam_inv_soli_dtl_s.nextval as secuencia,  xid.DOTA_ID,  xid.uniform_type_cod,  xid.nomenclature,  xid.measure_unit_cod,  xid.cycle_cod,  xid.planta_qty,  xid.event_qty, xid.sust_flag,  \'PENDIENTE\' as status  FROM xxgam_inv_dota xid  WHERE  xid.kit_id = ?";


   public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
      super.processRequest(pageContext, webBean);
      System.out.println("Entra a pagina xXGamSolicitudCrearSuperUserPG");
       flagInsert = "Y";
       
      OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();
      OAFormValueBean oaUserIdBean = (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("userIdSL");
      oaUserIdBean.setValue(pageContext, (Object)null);
      OAMessageLovInputBean oaSelectEmployeeNameBean = (OAMessageLovInputBean)oapagelayoutbean.findIndexedChildRecursive("SelectEmployeeName");
      oaSelectEmployeeNameBean.setValue(pageContext, (Object)null);
      OAMessageStyledTextBean oanombreEmpleadoBean = (OAMessageStyledTextBean)oapagelayoutbean.findIndexedChildRecursive("nombreEmpleado");
      oanombreEmpleadoBean.setValue(pageContext, (Object)null);
      OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
      xxDebugger.oadbtransaction = oaapplicationmodule.getOADBTransaction();
      xXGamInvSoliVOImpl invSoliVOImpl = (xXGamInvSoliVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliVO1");
      xXGamInvSoliDtlVOImpl invSoliDtlVOpImpl = (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
      invSoliVOImpl.clearCache();
      invSoliDtlVOpImpl.clearCache();
      invSoliVOImpl.initQuery("-1");
      invSoliDtlVOpImpl.initQuery("-1");
      xXGamInvSolicitudAMImpl am = (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
      am.setRenderOptions("BtnCrearPedidoSuperRender", false);
      String v_estatusEmployee = "NA";
      String lMessageErrorEmpInctv = "";
      
       XxGamInvConstantes constantesErrors = new XxGamInvConstantes();      
       
       try{
        v_estatusEmployee = validaEmpleadoActivo(am.obtienePersonId(pageContext.getUserId()).toString(),oaapplicationmodule );        
       }catch(Exception e){
           v_estatusEmployee = "NA";
       }
       System.out.println("v_estatusEmployee: " + v_estatusEmployee);
       

       if (!v_estatusEmployee.toString().equals("1") && !v_estatusEmployee.toString().equals("NA")){
            OAButtonBean CrearPedidoBean =(OAButtonBean)webBean.findIndexedChildRecursive("CrearPedido");
            CrearPedidoBean.setDisabled(Boolean.TRUE);
        
            lMessageErrorEmpInctv = constantesErrors.ERROR_EMPLOYEE_NOT_ACTIVE.toString();      
            MessageToken[] tokens = new MessageToken[]{new MessageToken("MESSAGE", lMessageErrorEmpInctv)};
            throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens);
       }

       
       
   }

   public void processFormRequest(OAPageContext pageContext, OAWebBean webBean) {
      super.processFormRequest(pageContext, webBean);
      
       Enumeration enums = pageContext.getParameterNames();  
               while(enums.hasMoreElements()){  
                 String paramName = enums.nextElement().toString();  
                 System.out.println("Param name:-->" +paramName+ ";Value:-->"+pageContext.getParameter(paramName));  
               }
               
      /**
       * Codigo Guillermo Sandoval Sabas(Multisolutions)
       * invocacion de Aplication modulo para visualozaci\u00f3n de Objetos
       * */
      xXGamInvSolicitudAMImpl am = (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
      XxGamInvConstantes constantesErrors = new XxGamInvConstantes();
      
      System.out.println("Se ejecuta processFormRequest");
      pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Version 1.1 - processFormRequest ", 3);
      pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Initial Variable", 3);
      OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();
       OAMessageStyledTextBean nombreEmpleadoBean = (OAMessageStyledTextBean)oapagelayoutbean.findIndexedChildRecursive("nombreEmpleado");
      String lAdscr = "NOENCONTRO";
      String lpDotaId = "NOENCONTRO";
      String lpSoliId = "NOENCONTRO";
      OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
      OADBTransactionImpl oadbtransactionimpl = (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
      String lEvent = pageContext.getParameter("event");
      String lSource = pageContext.getParameter("source");
      pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - get Event", 3);
       
      System.out.println("Evento que se ejecuta..." + lEvent);
      
      
       
       
       
      if("lovPrepare".equals(lEvent) ) {
        if (pageContext.getLovInputSourceId().toString().equals("SelectEmployeeName")){
           System.out.println("Evento lovPrepare");
           try{
               globalEmployeeName = nombreEmpleadoBean.getValue(pageContext).toString();
               System.out.println("Valor en LovPrepare: " + globalEmployeeName );
           }catch(Exception e){
               System.out.println("ERROR No se recupera valor de nombreEmpleado");
               globalEmployeeName = "INICIAL";
           }
           
           
           System.out.println("Valor que se recupera lovPrepare: " + globalEmployeeName); 
       }else if(pageContext.getLovInputSourceId().toString().equals("TallaNbr")){
           pageContext.putSessionValue("SoliDtlValue", getDotaId(getSoliDtl(pageContext.getParameter("evtSrcRowRef")).toString(), oaapplicationmodule));      
       }
     }
       try{
           String lovInputSourceId = pageContext.getLovInputSourceId(); 
           System.out.println("lovInputSourceId: " + lovInputSourceId);
       }catch(Exception e){
        System.out.println("Error en getLovInputSourceId");
       } 
       if("lovUpdate".equals(lEvent) && pageContext.getLovInputSourceId().toString().equals("SelectEmployeeName")) {
      
           
        System.out.println("Se realiza validacion para compara si el empleado cambio.");
        System.out.println("Valor antiguo: " + globalEmployeeName);
        System.out.println("Nuevo valor: " + pageContext.getParameter("SelectEmployeeName").toString());
        
        if(flagInsert.equals("N")){
            if(!pageContext.getParameter("SelectEmployeeName").toString().equals(globalEmployeeName) && globalEmployeeName!= "INICIAL" ){
               flagInsert = "Y";
                am.setRenderOptions("BtnCrearPedidoSuperRender", false);
                System.out.println("Nombre de empleado setea null");
                
                xXGamInvSoliVOImpl invSoliVOImpl = (xXGamInvSoliVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliVO1");
                xXGamInvSoliDtlVOImpl invSoliDtlVOpImplclr = (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
                invSoliVOImpl.initQuery("-1");
                invSoliDtlVOpImplclr.initQuery("-1");
               
       
            }
        }
           
        
       }
       
       /*if("returnHome".equals(lEvent)) {
        System.out.println("Regresa a pagina original");
       }*/

       
       
       
      //&& flagInsert.equals("Y")
      if("crea".equals(lEvent)) {
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Event Crea", 3);
         OAFormValueBean oaUserIdBean = (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("userIdSL");
         OAFormValueBean oaPersonIdBean = (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("PersonIdSL");
         if(oaPersonIdBean.getValue(pageContext) == null) {
            pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - No selecciono un Empleado de la LOV correctamente", 3);
            String lMessageErr = "Debe Seleccionar un empleado valido.";
            MessageToken[] tokens = new MessageToken[]{new MessageToken("MESSAGE", lMessageErr)};
            throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens);
         }

         Number lUser = new Number(-1);
         Number lPerson = new Number(-1);

         try {
            lPerson = new Number((String)oaPersonIdBean.getValue(pageContext));
            lUser = new Number(oaUserIdBean.getValue(pageContext));
         } catch (SQLException var131) {
            pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - al convertir PersonId y UserId Error: " + var131.getMessage().toString(), 3);
         }

         int lUserId = lUser.intValue();
         int lPersonId = lPerson.intValue();
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - PersonId: " + lPerson.toString(), 3);
         xXGamInvSoliVOImpl invSoliVOImplclr = (xXGamInvSoliVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliVO1");
         xXGamInvSoliDtlVOImpl invSoliDtlVOpImplclr = (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
         invSoliVOImplclr.clearCache();
         invSoliDtlVOpImplclr.clearCache();
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Inicializamos las VO para que no traigan nada ", 3);
         invSoliVOImplclr.initQuery("-1");
         invSoliDtlVOpImplclr.initQuery("-1");
         Long lSoliId = Long.valueOf(0L);
         Long lKitId = Long.valueOf(1L);
         String lKitCode = "NO ENCONTRO";
         String lKitDesc = "NO ENCONTRO";
         String lAccountDefault = "0";
         
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - buscamos el KIT - sqlKit por Person_Id", 3);
         String lSuperUser = oadbtransactionimpl.getProfile("XXGAM_INV_USUARIO_UNIFORME");
         OracleCallableStatement pstmtKit;
         OracleCallableStatement pstmAccounDefault;
        
        /**
         * Validacion para la cuentas de gasto por defecto
         * */
         /*pstmAccounDefault = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(" SELECT NVL(default_code_comb_id,0)                                   " + 
                                                                                                  "   FROM per_all_assignments_f                                         " + 
                                                                                                  "  WHERE TRUNC(SYSDATE) BETWEEN TRUNC(EFFECTIVE_START_DATE)            " +
                                                                                                  "                           AND TRUNC(NVL(EFFECTIVE_END_DATE,SYSDATE)) " + 
                                                                                                  "    AND person_id = ? ", 1);*/
          pstmAccounDefault = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(" SELECT NVL (default_code_comb_id, 0) " + 
                                                                                                                          "  FROM per_all_assignments_f paaf " + 
                                                                                                                          "     , PER_ORGANIZATION_UNITS POU " + 
                                                                                                                          " WHERE TRUNC (SYSDATE) BETWEEN TRUNC (EFFECTIVE_START_DATE) " + 
                                                                                                                          "                           AND TRUNC (NVL (EFFECTIVE_END_DATE, SYSDATE)) " + 
                                                                                                                          "   AND PAAF.ORGANIZATION_ID = POU.ORGANIZATION_ID " + 
                                                                                                                          "   AND TRUNC (SYSDATE) BETWEEN TRUNC (POU.DATE_FROM) " + 
                                                                                                                          "                          AND NVL (TRUNC (POU.DATE_TO), TRUNC (SYSDATE))" + 
                                                                                                                          "   AND PAAF.primary_flag = 'Y' " + 
                                                                                                                          "   AND person_id = ?", 1);

         
         if("Y".equals(lSuperUser)) {
            pstmtKit = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT NVL(XIK.KIT_ID,-1) , XUK.KIT_CODE , FLV.MEANING FROM XXGAM_HR_UNIFORMS_KIT_V XUK , XXGAM_INV_KIT XIK , FND_LOOKUP_VALUES FLV WHERE 1 = 1 AND XUK.KIT_CODE = XIK.KIT_COD(+) AND FLV.LOOKUP_TYPE = \'GAM_HR_UNIFORMES\' AND XUK.KIT_CODE = FLV.LOOKUP_CODE AND XUK.LANGUAGE = FLV.LANGUAGE AND XUK.LANGUAGE = \'ESA\' AND XUK.PERSON_ID = ?", 1);
         } else {
            pstmtKit = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT NVL(XIK.KIT_ID,-1) , XUK.KIT_CODE , FLV.MEANING FROM XXGAM_HR_UNIFORMS_KIT_V XUK , XXGAM_INV_KIT XIK , FND_LOOKUP_VALUES FLV , FND_LOOKUP_VALUES FLV2 WHERE 1 = 1 AND XUK.KIT_CODE = XIK.KIT_COD(+) AND FLV.LOOKUP_TYPE = \'GAM_HR_UNIFORMES\' AND XUK.KIT_CODE = FLV.LOOKUP_CODE AND XUK.LANGUAGE = FLV.LANGUAGE AND FLV2.LOOKUP_TYPE = \'XXGAM_APERTURA_DE_PERIODOS\' AND FLV.LOOKUP_CODE = FLV2.MEANING AND FLV.LANGUAGE = FLV2.LANGUAGE AND (FLV2.START_DATE_ACTIVE IS NULL OR FLV2.START_DATE_ACTIVE <= TRUNC(SYSDATE)) AND (FLV2.END_DATE_ACTIVE IS NULL OR FLV2.END_DATE_ACTIVE >= TRUNC(SYSDATE)) AND XUK.LANGUAGE = \'ESA\' AND XUK.PERSON_ID = ?", 1);
         }
          
          try {
             pstmAccounDefault.setInt(1, lPersonId);
             ResultSet rsAccountDefault = pstmAccounDefault.executeQuery();
             
             //Cliclo para obteber datos si existe Cuenta de Gasto por defecto
             if(rsAccountDefault.next()) {
                 lAccountDefault = rsAccountDefault.getString(1);
              }
             
          } catch (SQLException var129) {
             pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - sqlAccountDefault Error: " + var129.getMessage().toString(), 3);
            System.out.println("Error al recuperar valor de la cuenta contable.");
          } finally {
             try {
                pstmAccounDefault.close();
             } catch (Exception var124) {
                pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - sqlAccountDefault Error: " + var124.getMessage().toString(), 3);
                throw OAException.wrapperException(var124);
             }
          }
          
         try {
            pstmtKit.setInt(1, lPersonId);
            ResultSet rsK = pstmtKit.executeQuery();
             
            /*Ciclo para valor errores y fechas efectivas*/
            if(rsK.next()) {
               lKitId = Long.valueOf(rsK.getLong(1));
               lKitCode = rsK.getString(2);
               lKitDesc = rsK.getString(3);
               pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - KIT: " + lKitCode, 3);
            }
            
         } catch (SQLException var129) {
            pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - sqlKit Error: " + var129.getMessage().toString(), 3);
         } finally {
            try {
               pstmtKit.close();
            } catch (Exception var124) {
               pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - sqlKit Error: " + var124.getMessage().toString(), 3);
               throw OAException.wrapperException(var124);
            }
         }
        
        System.out.println("Valor de cuenta por Defecto: " + lAccountDefault);
        
         String lMessageError;
             if(lKitId.longValue() == (long)-1) {
             String lmensage = am.getMessageError(pageContext, webBean, Integer.parseInt(pageContext.getParameter("PersonIdSL")));
             //am.obtienePersonId(pageContext.getUserId()).toString())); 
             System.out.println("Se recupera mensaje : " + lmensage);
             lMessageError = lmensage;
            //lMessageError = "El Kit " + lKitCode + " - " + lKitDesc + " no se encuentra configurado. Realice la configuracion del mismo.";
            pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Error: " + lMessageError, 3);
            MessageToken[] tokens1 = new MessageToken[]{new MessageToken("MESSAGE", lMessageError)};
            throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens1);
         }

         if("NO ENCONTRO".equals(lKitCode)) {
            String lmensage = am.getMessageError(pageContext, webBean, Integer.parseInt(pageContext.getParameter("PersonIdSL")));
            //am.obtienePersonId(pageContext.getUserId()).toString())); 
            System.out.println("Se recupera mensaje : " + lmensage);
            lMessageError = lmensage;
            //lMessageError = "El empleado no tiene asignado un KIT o el mismo esta fuera de vigencia.";
            pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Error: " + lMessageError, 3);
            MessageToken[] tokens2 = new MessageToken[]{new MessageToken("MESSAGE", lMessageError)};
            throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens2);
         }
        /**Validacion de asignacin de cuenta de gasto por defecto por defecto*/
        if("0".equals(lAccountDefault)) {
         System.out.println("Se envia mensaje de faltante en valorde cuenta de gasto por defecto.");
         //lMessageError = "El empleado no tiene asignado un KIT o el mismo esta fuera de vigencia.";
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Error: " + constantesErrors.ERROR_NOT_ACCOUNT_DEF, 3);
         MessageToken[] tokens2 = new MessageToken[]{new MessageToken("MESSAGE", constantesErrors.ERROR_NOT_ACCOUNT_DEF)};
         throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens2);
        }

         
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Completamos los datos del kit por pantalla", 3);
         OAMessageStyledTextBean oanombreEmpleadoBean = (OAMessageStyledTextBean)oapagelayoutbean.findIndexedChildRecursive("nombreEmpleado");
         OAMessageStyledTextBean oakitBean = (OAMessageStyledTextBean)oapagelayoutbean.findIndexedChildRecursive("kit");
         OAMessageStyledTextBean oakitDescripcionBean = (OAMessageStyledTextBean)oapagelayoutbean.findIndexedChildRecursive("kitDescripcion");
         xXGamInvSoliVOImpl invSoliVOImpl = (xXGamInvSoliVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliVO1");
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Buscamos los datos del empleado sqlNombreEmpleado", 3);
         OracleCallableStatement pstmtSoli = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT xxgam_inv_soli_s.nextval,  ppf.person_id,  ppf.full_name AS nombre_empleado,   ppf.employee_number AS clave_empleado,   ppf.per_information2 AS rfc,  \'NA\' as categoria,   CASE   WHEN UPPER(PPT.USER_PERSON_TYPE) LIKE \'%EVENTUAL%\' THEN \'Eventual\'  WHEN UPPER(PPT.USER_PERSON_TYPE) LIKE \'%PLANTA%\' THEN \'Planta\'  ELSE PPT.USER_PERSON_TYPE  END AS adscripcion,  paaf.date_probation_end AS fecha_termino_contrato,   gcc.segment4 AS estacion,   gcc.segment3 AS centro_costos,   gcc.concatenated_segments AS cuenta,   gcc.code_combination_id,  sysdate as solidate,  \'PENDIENTE\' as pendiente,   pgd.segment4 as categoria_gr FROM per_all_people_f ppf,   per_all_assignments_f paaf,   gl_code_combinations_kfv gcc,   per_grade_definitions pgd,  per_grades pg,  PER_PERSON_TYPES PPT , PER_PERSON_TYPE_USAGES_F PPTU , PAY_USER_TABLES PUT , PAY_USER_COLUMN_INSTANCES_F PUCIF , PAY_USER_ROWS_F PURF , PAY_USER_ROWS_F_VL PURFV , PAY_USER_COLUMNS PUC WHERE SYSDATE BETWEEN ppf.effective_start_date   AND ppf.effective_end_date    AND SYSDATE BETWEEN paaf.effective_start_date  AND paaf.effective_end_date   AND ppf.person_id = paaf.person_id   AND gcc.code_combination_id (+) = paaf.default_code_comb_id     AND pgd.grade_definition_id(+) = pg.grade_definition_id  AND pg.grade_id (+)= paaf.grade_id  AND PPF.PERSON_ID = PPTU.PERSON_ID  AND (PPTU.EFFECTIVE_END_DATE IS NULL OR PPTU.EFFECTIVE_END_DATE >= TRUNC(SYSDATE))  AND PPTU.PERSON_TYPE_ID = PPT.PERSON_TYPE_ID AND UPPER(PPT.USER_PERSON_TYPE) LIKE (\'%\'||UPPER(PURFV.ROW_LOW_RANGE_OR_NAME)||\'%\') AND PUT.USER_TABLE_NAME = \'XXGAM_PARAMETROS_UNIFORMES\' AND PUT.BUSINESS_GROUP_ID = 81 AND PUT.USER_TABLE_ID = PURF.USER_TABLE_ID AND PUT.USER_TABLE_ID = PUC.USER_TABLE_ID AND PUC.USER_COLUMN_NAME = \'Tipo de Persona\' AND PUCIF.USER_ROW_ID = PURF.USER_ROW_ID AND PUCIF.USER_COLUMN_ID = PUC.USER_COLUMN_ID AND SYSDATE BETWEEN PURF.EFFECTIVE_START_DATE AND PURF.EFFECTIVE_END_DATE AND PURF.USER_ROW_ID = PURFV.USER_ROW_ID AND PUT.USER_TABLE_ID = PURFV.USER_TABLE_ID AND SYSDATE BETWEEN PUCIF.EFFECTIVE_START_DATE AND PUCIF.EFFECTIVE_END_DATE  AND ppf.person_id = ?", 1);

         try {
            pstmtSoli.setInt(1, lPersonId);
            ResultSet rsS = pstmtSoli.executeQuery();
            if(!rsS.next()) {
               lMessageError = "Los datos del empleado estan fuera de vigencia.";
               pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Error: " + lMessageError, 3);
               MessageToken[] tokens3 = new MessageToken[]{new MessageToken("MESSAGE", lMessageError)};
               throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens3);
            }

            Row rowSoli = invSoliVOImpl.createRow();
            invSoliVOImpl.insertRow(rowSoli);
            rowSoli.setNewRowState((byte)-1);
            lSoliId = Long.valueOf(rsS.getLong(1));
            rowSoli.setAttribute("SoliId", lSoliId);
            String soliD = String.valueOf(lSoliId);
            rowSoli.setAttribute("PersonId", Long.valueOf(rsS.getLong(2)));
            String lNombreEmpleado = rsS.getString(3);
            oanombreEmpleadoBean.setValue(pageContext, lNombreEmpleado);
            rowSoli.setAttribute("EmployeeNumber", rsS.getString(4));
            rowSoli.setAttribute("Rfc", rsS.getString(5));
            rowSoli.setAttribute("Adscription", rsS.getString(7));
            lAdscr = rsS.getString(7);
            rowSoli.setAttribute("ContDueDate", rsS.getDate(8));
            rowSoli.setAttribute("Station", rsS.getString(9));
            rowSoli.setAttribute("CostCenter", rsS.getString(10));
            rowSoli.setAttribute("ExpenseDesc", rsS.getString(11));
            rowSoli.setAttribute("ExpenseAcc", Long.valueOf(rsS.getLong(12)));
            rowSoli.setAttribute("SoliDate", rsS.getDate(13));
            rowSoli.setAttribute("Status", rsS.getString(14));
            rowSoli.setAttribute("Category", rsS.getString(15));
            rowSoli.setAttribute("NroSolicitud", "UNI-" + soliD.trim());
            rowSoli.setAttribute("KitId", lKitId);
            oakitBean.setValue(pageContext, lKitCode);
            oakitDescripcionBean.setValue(pageContext, lKitDesc);
            pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Crea Nro de Solicitud: " + soliD.trim().toString(), 3);
         } catch (SQLException var127) {
            pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - sqlNombreEmpleado Error: " + var127.getMessage().toString(), 3);
         } finally {
            try {
               pstmtSoli.close();
            } catch (Exception var123) {
               pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - sqlNombreEmpleado Error: " + var123.getMessage().toString(), 3);
               throw OAException.wrapperException(var123);
            }
         }

         xXGamInvSoliDtlVOImpl invSoliDtlVOpImpl = (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
         boolean lFlagLoop = false;
          
         invSoliDtlVOpImpl.initQuery(lSoliId.toString());
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Buscamos las dotaciones configuradas-sqlDota", 3);
         OracleCallableStatement pstmtDota = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT xxgam_inv_soli_dtl_s.nextval as secuencia,  xid.DOTA_ID,  xid.uniform_type_cod,  xid.nomenclature,  xid.measure_unit_cod,  xid.cycle_cod,  xid.planta_qty,  xid.event_qty, xid.sust_flag,  \'PENDIENTE\' as status  FROM xxgam_inv_dota xid  WHERE  xid.kit_id = ?", 1);

         try {
            pstmtDota.setLong(1, lKitId.longValue());
            ResultSet rsD = pstmtDota.executeQuery();

            while(rsD.next()) {
               Row rowDot = invSoliDtlVOpImpl.createRow();
               invSoliDtlVOpImpl.insertRow(rowDot);
               rowDot.setNewRowState((byte)-1);
               invSoliDtlVOpImpl.setCurrentRow(rowDot);
               invSoliDtlVOpImpl.getCurrentRow().setAttribute("SoliId", lSoliId);
               invSoliDtlVOpImpl.getCurrentRow().setAttribute("SoliDtlId", Long.valueOf(rsD.getLong(1)));
               invSoliDtlVOpImpl.getCurrentRow().setAttribute("DotaId", Long.valueOf(rsD.getLong(2)));
               invSoliDtlVOpImpl.getCurrentRow().setAttribute("UniformTypeCod", rsD.getString(3));
               invSoliDtlVOpImpl.getCurrentRow().setAttribute("Nomenclature", rsD.getString(4));
               invSoliDtlVOpImpl.getCurrentRow().setAttribute("MeasureUnitCod", rsD.getString(5));
               invSoliDtlVOpImpl.getCurrentRow().setAttribute("CycleCod", rsD.getString(6));
               invSoliDtlVOpImpl.getCurrentRow().setAttribute("QtyPlanta", Long.valueOf(rsD.getLong(7)));
               invSoliDtlVOpImpl.getCurrentRow().setAttribute("QtyEventual", Long.valueOf(rsD.getLong(8)));
               invSoliDtlVOpImpl.getCurrentRow().setAttribute("SustitucionSwitcher", rsD.getString(9));
               invSoliDtlVOpImpl.getCurrentRow().setAttribute("Status", rsD.getString(10));
               invSoliDtlVOpImpl.getCurrentRow().setAttribute("Attribute1", lAdscr);
               if("Planta".equals(lAdscr)) {
                  invSoliDtlVOpImpl.getCurrentRow().setAttribute("Attribute2", rsD.getString(7));
               } else {
                  invSoliDtlVOpImpl.getCurrentRow().setAttribute("Attribute2", rsD.getString(8));
               }

               lFlagLoop = true;
               pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Crea Dotacion Detalle Id : " + rsD.getString(1), 3);
            }

            if(!lFlagLoop) {
               lMessageError = "No se encontro configurado las dotaciones para el KIT.";
               pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Error: " + lMessageError, 3);
               MessageToken[] tokens4 = new MessageToken[]{new MessageToken("MESSAGE", lMessageError)};
               throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens4);
            }

            if(flagInsert.equals("Y")){
                System.out.println("Codigo comentado para insertar");
                oadbtransactionimpl.commit();
                flagInsert = "N";
                am.setRenderOptions("BtnCrearPedidoSuperRender", true);
            }
            
            
            
            invSoliDtlVOpImpl.initQuery(lSoliId.toString());
         } catch (SQLException var132) {
            pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - sqlDota Error: " + var132.getMessage().toString(), 3);
         } finally {
            try {
               pstmtDota.close();
            } catch (Exception var125) {
               pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - sqlDota Error: " + var125.getMessage().toString(), 3);
               throw OAException.wrapperException(var125);
            }
         }

          /**
           * Validación para inactivar casillas en las que aun no correspode renovacion de prenda por ciclo.
           * */  
           
           am.validaCicloConFechaEntrega(pageContext, webBean);         
      }

      if("grabar".equals(lEvent)) {
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Event Grabar", 3);
         OAFormValueBean oaSoliBean = (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("SoliIdH");
         String lSoliIdC = (String)oaSoliBean.getValue(pageContext);
         oadbtransactionimpl.commit();
         String lSql = "BEGIN XXGAM_INV_MOVEORDER_PKG.VALIDA_SUSTITUTOS(?); END;";
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - ejecuta XXGAM_INV_MOVEORDER_PKG.VALIDA_SUSTITUTOS", 3);
         OracleCallableStatement pstmtSust = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(lSql, 1);

         try {
            pstmtSust.setLong(1, Long.valueOf(lSoliIdC).longValue());
            pstmtSust.execute();
            pstmtSust.close();
            pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - ejecutado con exito", 3);
         } catch (SQLException var126) {
            pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - pstmtSust Error: " + var126.getMessage().toString(), 3);

            try {
               pstmtSust.close();
            } catch (Exception var122) {
               pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - pstmtSust Error: " + var122.getMessage().toString(), 3);
            }

            throw OAException.wrapperException(var126);
         }

         oadbtransactionimpl.commit();
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Commit Fin Grabar", 3);
         xXGamInvSoliDtlVOImpl invSoliDtlVOImpl1 = (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Reconsulto la VO para el Soli Id" + lSoliIdC.toString(), 3);
         invSoliDtlVOImpl1.clearCache();
         invSoliDtlVOImpl1.initQuery(lSoliIdC);
         MessageToken[] tokens5 = new MessageToken[]{new MessageToken("MESSAGE", "Se han guardado los cambios con \u00e9xito.")};
         OAException message = new OAException("FND", "FND_GENERIC_MESSAGE", tokens5, (byte)3, (Exception[])null);
         pageContext.putDialogMessage(message);
         
          /**
           * Validación para inactivar casillas en las que aun no correspode renovacion de prenda por ciclo.
           * */         
          am.validaCicloConFechaEntrega(pageContext, webBean);
      }

      if("generarpedido".equals(lEvent)) {
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Event generarpedido", 3);
         OAFormValueBean oaStatusBean = (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("StatusHDR");
         OAButtonBean oaButtonBean = (OAButtonBean)oapagelayoutbean.findIndexedChildRecursive("generarpedido");
         String lStatus = (String)oaStatusBean.getValue(pageContext);
         if("CERRADO".equals(lStatus) || "PREAPROBADO".equals(lStatus)) {
            String lMessageE = "La solicitud ya fue Aprobada o Cerrada. Estado actual: " + lStatus.toString();
            pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Error: " + lMessageE, 3);
            MessageToken[] tokens6 = new MessageToken[]{new MessageToken("MESSAGE", lMessageE)};
            throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens6);
         }

         oadbtransactionimpl.commit();
         String lSoliIdSTR = pageContext.getParameter("pgSoliId");
         new Number(0);
         xXGamInvSoliDtlVOImpl invSoliDtlVOImpl11 = (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
         invSoliDtlVOImpl11.clearCache();
         invSoliDtlVOImpl11.initQuery(lSoliIdSTR);
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Ejecuta concurrente para SOLI ID " + lSoliIdSTR, 3);
         Number lReqId = this.callConcMoveOrder(lSoliIdSTR, "XXGAM_INV_CREATE_MV", "XXGAM INV Crear Move Order", pageContext, webBean);
         if(lReqId.compareTo(0) <= 0) {
            String lMessageE1 = "No se ha ejecutado el concurrente.";
            pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - Error al ejecutar concurrente", 3);
            MessageToken[] tokens8 = new MessageToken[]{new MessageToken("MESSAGE", lMessageE1)};
            throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens8);
         }

         MessageToken[] tokens7 = new MessageToken[]{new MessageToken("MESSAGE", "Se ejecuto la creaci\u00f3n del pedido verifique el concurrente REQUEST ID: " + lReqId.toString())};
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearSuperUserCO - verifique el concurrente REQUEST ID: " + lReqId.toString(), 3);
         OAException message1 = new OAException("FND", "FND_GENERIC_MESSAGE", tokens7, (byte)3, (Exception[])null);
         pageContext.putDialogMessage(message1);
      }
       if("downLoadPDF".equals(lEvent)) {
         System.out.println("Inicia proceso para la descarga de PDF.");
           downloadFileFromServer( pageContext
                                , "/interface/i_aemx60/PAEMXI/incoming/Uniformes/Politicas/POGAM-RH-014_(uso de uniforme).pdf"//"/taemxi/applmgr/1200/xbol/12.0.0/pdf/POGAM-RH-014_(uso de uniforme).pdf",
                                ,  "POGAM-RH-014_(uso de uniforme).pdf" 
                                 ); 
           throw new OAException("Se ha descargado archivo POGAM-RH-014_(uso de uniforme).pdf.", 
                                 OAException.INFORMATION);
                       
       }
   }

   public Number callConcMoveOrder(String strSoliId, String reqCode, String title, OAPageContext pageContext, OAWebBean webBean) {
      OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
      Vector lParam = new Vector();
      lParam.addElement(strSoliId);
      String lAppCode = "XBOL";
      String lConcReqCode = reqCode;
      int lRequestId = 0;

      try {
         ConcurrentRequest lConc = new ConcurrentRequest(oaapplicationmodule.getOADBTransaction().getJdbcConnection());
         lRequestId = lConc.submitRequest(lAppCode, lConcReqCode, title, (String)null, false, lParam);
         oaapplicationmodule.getOADBTransaction().commit();
      } catch (RequestSubmissionException var16) {
         new OAException(var16.toString());
      } catch (Exception var17) {
         ;
      }

      String lRequestIdD = String.valueOf(lRequestId);
      return new Number(lRequestId);
   }
    public void downloadFileFromServer(OAPageContext pageContext, 
    String file_name_with_path, 
    String file_name_with_ext)
    {
    HttpServletResponse response = 
    (HttpServletResponse) pageContext.getRenderingContext().getServletResponse();
    if (((file_name_with_path == null) || 
    ("".equals(file_name_with_path))))
    {
    throw new OAException("La ruta del achivo no es valida.");
    }

    File fileToDownload = null;
    try
    {
    fileToDownload = new File(file_name_with_path);
    }
    catch (Exception e)
    {
    throw new OAException("No existe la reta del archivo.");
    }

    if (!fileToDownload.exists())
    {
    throw new OAException("No se encontro archivo a descargar.");
    }

    if (!fileToDownload.canRead())
    {
    throw new OAException("No se puede leer el archivo.");
    }

    String fileType = getMimeType(file_name_with_ext);
    response.setContentType(fileType);
    response.setContentLength((int)fileToDownload.length()); 
    response.setHeader("Content-Disposition", 
    "attachment; filename=\"" + file_name_with_ext + 
    "\"");

    InputStream in = null;
    ServletOutputStream outs = null;

    try
    {
    outs = response.getOutputStream();
    in = new BufferedInputStream(new FileInputStream(fileToDownload));
    int ch;
    while ((ch = in.read()) != -1)
    {
    outs.write(ch);
    }

    }
    catch (IOException e)
    {
    // TODO
    e.printStackTrace();
    }
    finally
    {
    try
    {
    outs.flush();
    outs.close();
    if (in != null)
    {
    in.close();
    }
    }
    catch (Exception e)
    {
    e.printStackTrace();
    }
    }
    }
    
    public String getMimeType(String s)
    {
    int i = s.lastIndexOf(".");
    if (i > 0 && i < s.length() - 1)
    {
    String s1 = s.substring(i + 1);
    if (s1.equalsIgnoreCase("amr"))
    {
    return "audio/amr";
    }
    if (s1.equalsIgnoreCase("mid"))
    {
    return "audio/midi";
    }
    if (s1.equalsIgnoreCase("mmf"))
    {
    return "application/vnd.smaf";
    }
    if (s1.equalsIgnoreCase("qcp"))
    {
    return "audio/vnd.qcelp";
    }
    if (s1.equalsIgnoreCase("hqx"))
    {
    return "application/mac-binhex40";
    }
    if (s1.equalsIgnoreCase("cpt"))
    {
    return "application/mac-compactpro";
    }
    if (s1.equalsIgnoreCase("doc"))
    {
    return "application/msword";
    }
    if (s1.equalsIgnoreCase("jsp"))
    {
    return "application/jsp";
    }
    if (s1.equalsIgnoreCase("oda"))
    {
    return "application/oda";
    }
    if (s1.equalsIgnoreCase("pdf"))
    {
    return "application/pdf";
    }
    if (s1.equalsIgnoreCase("ai"))
    {
    return "application/postscript";
    }
    if (s1.equalsIgnoreCase("eps"))
    {
    return "application/postscript";
    }
    if (s1.equalsIgnoreCase("ps"))
    {
    return "application/postscript";
    }
    if (s1.equalsIgnoreCase("ppt"))
    {
    return "application/powerpoint";
    }
    if (s1.equalsIgnoreCase("rtf"))
    {
    return "application/rtf";
    }
    if (s1.equalsIgnoreCase("bcpio"))
    {
    return "application/x-bcpio";
    }
    if (s1.equalsIgnoreCase("vcd"))
    {
    return "application/x-cdlink";
    }
    if (s1.equalsIgnoreCase("Z"))
    {
    return "application/x-compress";
    }
    if (s1.equalsIgnoreCase("cpio"))
    {
    return "application/x-cpio";
    }
    if (s1.equalsIgnoreCase("csh"))
    {
    return "application/x-csh";
    }
    if (s1.equalsIgnoreCase("dcr"))
    {
    return "application/x-director";
    }
    if (s1.equalsIgnoreCase("dir"))
    {
    return "application/x-director";
    }
    if (s1.equalsIgnoreCase("dxr"))
    {
    return "application/x-director";
    }
    if (s1.equalsIgnoreCase("dvi"))
    {
    return "application/x-dvi";
    }
    if (s1.equalsIgnoreCase("gtar"))
    {
    return "application/x-gtar";
    }
    if (s1.equalsIgnoreCase("gz"))
    {
    return "application/x-gzip";
    }
    if (s1.equalsIgnoreCase("hdf"))
    {
    return "application/x-hdf";
    }
    if (s1.equalsIgnoreCase("cgi"))
    {
    return "application/x-httpd-cgi";
    }
    if (s1.equalsIgnoreCase("jnlp"))
    {
    return "application/x-java-jnlp-file";
    }
    if (s1.equalsIgnoreCase("skp"))
    {
    return "application/x-koan";
    }
    if (s1.equalsIgnoreCase("skd"))
    {
    return "application/x-koan";
    }
    if (s1.equalsIgnoreCase("skt"))
    {
    return "application/x-koan";
    }
    if (s1.equalsIgnoreCase("skm"))
    {
    return "application/x-koan";
    }
    if (s1.equalsIgnoreCase("latex"))
    {
    return "application/x-latex";
    }
    if (s1.equalsIgnoreCase("mif"))
    {
    return "application/x-mif";
    }
    if (s1.equalsIgnoreCase("nc"))
    {
    return "application/x-netcdf";
    }
    if (s1.equalsIgnoreCase("cdf"))
    {
    return "application/x-netcdf";
    }
    if (s1.equalsIgnoreCase("sh"))
    {
    return "application/x-sh";
    }
    if (s1.equalsIgnoreCase("shar"))
    {
    return "application/x-shar";
    }
    if (s1.equalsIgnoreCase("sit"))
    {
    return "application/x-stuffit";
    }
    if (s1.equalsIgnoreCase("sv4cpio"))
    {
    return "application/x-sv4cpio";
    }
    if (s1.equalsIgnoreCase("sv4crc"))
    {
    return "application/x-sv4crc";
    }
    if (s1.equalsIgnoreCase("tar"))
    {
    return "application/x-tar";
    }
    if (s1.equalsIgnoreCase("tcl"))
    {
    return "application/x-tcl";
    }
    if (s1.equalsIgnoreCase("tex"))
    {
    return "application/x-tex";
    }
    if (s1.equalsIgnoreCase("textinfo"))
    {
    return "application/x-texinfo";
    }
    if (s1.equalsIgnoreCase("texi"))
    {
    return "application/x-texinfo";
    }
    if (s1.equalsIgnoreCase("t"))
    {
    return "application/x-troff";
    }
    if (s1.equalsIgnoreCase("tr"))
    {
    return "application/x-troff";
    }
    if (s1.equalsIgnoreCase("roff"))
    {
    return "application/x-troff";
    }
    if (s1.equalsIgnoreCase("man"))
    {
    return "application/x-troff-man";
    }
    if (s1.equalsIgnoreCase("me"))
    {
    return "application/x-troff-me";
    }
    if (s1.equalsIgnoreCase("ms"))
    {
    return "application/x-troff-ms";
    }
    if (s1.equalsIgnoreCase("ustar"))
    {
    return "application/x-ustar";
    }
    if (s1.equalsIgnoreCase("src"))
    {
    return "application/x-wais-source";
    }
    if (s1.equalsIgnoreCase("xml"))
    {
    return "text/xml";
    }
    if (s1.equalsIgnoreCase("ent"))
    {
    return "text/xml";
    }
    if (s1.equalsIgnoreCase("cat"))
    {
    return "text/xml";
    }
    if (s1.equalsIgnoreCase("sty"))
    {
    return "text/xml";
    }
    if (s1.equalsIgnoreCase("dtd"))
    {
    return "text/dtd";
    }
    if (s1.equalsIgnoreCase("xsl"))
    {
    return "text/xsl";
    }
    if (s1.equalsIgnoreCase("zip"))
    {
    return "application/zip";
    }
    if (s1.equalsIgnoreCase("au"))
    {
    return "audio/basic";
    }
    if (s1.equalsIgnoreCase("snd"))
    {
    return "audio/basic";
    }
    if (s1.equalsIgnoreCase("mpga"))
    {
    return "audio/mpeg";
    }
    if (s1.equalsIgnoreCase("mp2"))
    {
    return "audio/mpeg";
    }
    if (s1.equalsIgnoreCase("mp3"))
    {
    return "audio/mpeg";
    }
    if (s1.equalsIgnoreCase("aif"))
    {
    return "audio/x-aiff";
    }
    if (s1.equalsIgnoreCase("aiff"))
    {
    return "audio/x-aiff";
    }
    if (s1.equalsIgnoreCase("aifc"))
    {
    return "audio/x-aiff";
    }
    if (s1.equalsIgnoreCase("ram"))
    {
    return "audio/x-pn-realaudio";
    }
    if (s1.equalsIgnoreCase("rpm"))
    {
    return "audio/x-pn-realaudio-plugin";
    }
    if (s1.equalsIgnoreCase("ra"))
    {
    return "audio/x-realaudio";
    }
    if (s1.equalsIgnoreCase("wav"))
    {
    return "audio/x-wav";
    }
    if (s1.equalsIgnoreCase("pdb"))
    {
    return "chemical/x-pdb";
    }
    if (s1.equalsIgnoreCase("xyz"))
    {
    return "chemical/x-pdb";
    }
    if (s1.equalsIgnoreCase("gif"))
    {
    return "image/gif";
    }
    if (s1.equalsIgnoreCase("ief"))
    {
    return "image/ief";
    }
    if (s1.equalsIgnoreCase("jpeg"))
    {
    return "image/jpeg";
    }
    if (s1.equalsIgnoreCase("jpg"))
    {
    return "image/jpeg";
    }
    if (s1.equalsIgnoreCase("jpe"))
    {
    return "image/jpeg";
    }
    if (s1.equalsIgnoreCase("png"))
    {
    return "image/png";
    }
    if (s1.equalsIgnoreCase("tiff"))
    {
    return "image/tiff";
    }
    if (s1.equalsIgnoreCase("tif"))
    {
    return "image/tiff";
    }
    if (s1.equalsIgnoreCase("ras"))
    {
    return "image/x-cmu-raster";
    }
    if (s1.equalsIgnoreCase("pnm"))
    {
    return "image/x-portable-anymap";
    }
    if (s1.equalsIgnoreCase("pbm"))
    {
    return "image/x-portable-bitmap";
    }
    if (s1.equalsIgnoreCase("pgm"))
    {
    return "image/x-portable-graymap";
    }
    if (s1.equalsIgnoreCase("ppm"))
    {
    return "image/x-portable-pixmap";
    }
    if (s1.equalsIgnoreCase("rgb"))
    {
    return "image/x-rgb";
    }
    if (s1.equalsIgnoreCase("xbm"))
    {
    return "image/x-xbitmap";
    }
    if (s1.equalsIgnoreCase("xpm"))
    {
    return "image/x-xpixmap";
    }
    if (s1.equalsIgnoreCase("xwd"))
    {
    return "image/x-xwindowdump";
    }
    if (s1.equalsIgnoreCase("html"))
    {
    return "text/html";
    }
    if (s1.equalsIgnoreCase("htm"))
    {
    return "text/html";
    }
    if (s1.equalsIgnoreCase("txt"))
    {
    return "text/plain";
    }
    if (s1.equalsIgnoreCase("rtx"))
    {
    return "text/richtext";
    }
    if (s1.equalsIgnoreCase("tsv"))
    {
    return "text/tab-separated-values";
    }
    if (s1.equalsIgnoreCase("etx"))
    {
    return "text/x-setext";
    }
    if (s1.equalsIgnoreCase("sgml"))
    {
    return "text/x-sgml";
    }
    if (s1.equalsIgnoreCase("sgm"))
    {
    return "text/x-sgml";
    }
    if (s1.equalsIgnoreCase("mpeg"))
    {
    return "video/mpeg";
    }
    if (s1.equalsIgnoreCase("mpg"))
    {
    return "video/mpeg";
    }
    if (s1.equalsIgnoreCase("mpe"))
    {
    return "video/mpeg";
    }
    if (s1.equalsIgnoreCase("qt"))
    {
    return "video/quicktime";
    }
    if (s1.equalsIgnoreCase("mov"))
    {
    return "video/quicktime";
    }
    if (s1.equalsIgnoreCase("avi"))
    {
    return "video/x-msvideo";
    }
    if (s1.equalsIgnoreCase("movie"))
    {
    return "video/x-sgi-movie";
    }
    if (s1.equalsIgnoreCase("ice"))
    {
    return "x-conference/x-cooltalk";
    }
    if (s1.equalsIgnoreCase("wrl"))
    {
    return "x-world/x-vrml";
    }
    if (s1.equalsIgnoreCase("vrml"))
    {
    return "x-world/x-vrml";
    }
    if (s1.equalsIgnoreCase("wml"))
    {
    return "text/vnd.wap.wml";
    }
    if (s1.equalsIgnoreCase("wmlc"))
    {
    return "application/vnd.wap.wmlc";
    }
    if (s1.equalsIgnoreCase("wmls"))
    {
    return "text/vnd.wap.wmlscript";
    }
    if (s1.equalsIgnoreCase("wmlsc"))
    {
    return "application/vnd.wap.wmlscriptc";
    }
    if (s1.equalsIgnoreCase("wbmp"))
    {
    return "image/vnd.wap.wbmp";
    }
    if (s1.equalsIgnoreCase("css"))
    {
    return "text/css";
    }
    if (s1.equalsIgnoreCase("jad"))
    {
    return "text/vnd.sun.j2me.app-descriptor";
    }
    if (s1.equalsIgnoreCase("jar"))
    {
    return "application/java-archive";
    }
    if (s1.equalsIgnoreCase("3gp"))
    {
    return "video/3gp";
    }
    if (s1.equalsIgnoreCase("3g2"))
    {
    return "video/3gpp2";
    }
    if (s1.equalsIgnoreCase("mp4"))
    {
    return "video/3gpp";
    }
    }
    return "application/octet-stream";
    }

    public String validaEmpleadoActivo(String pPersonId, OAApplicationModule oaapplicationmodule){
         String v_estatusEmployee = "NA";
         System.out.println("Inicia Proceso validaEmpleadoActivo, se recibe pPersonId: " + pPersonId);
         OADBTransactionImpl oadbtransactionimpl = (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
         OracleCallableStatement pstmtEmployeeActive = 
         (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(" SELECT PASTT.USER_STATUS                                                                                            " +
                                                                              "      , CASE WHEN (upper(PASTT.USER_STATUS) = 'ACTIVE ASSIGNMENT' OR upper(PASTT.USER_STATUS) = 'ASIGNACIÓN ACTIVA') " +
                                                                              "        THEN '1'                                                                                                     " +
                                                                              "        ELSE upper(PASTT.USER_STATUS)                                                                                " +
                                                                              "         END RESULT                                                                                                  " +
                                                                              "  FROM PER_ALL_ASSIGNMENTS_F PAAF,                                                                                   " + 
                                                                              "       PER_ASSIGNMENT_STATUS_TYPES_TL PASTT,                                                                         " + 
                                                                              "       PER_ALL_PEOPLE_F PAPF,                                                                                        " + 
                                                                              "       PER_ORGANIZATION_UNITS POU                                                                                    " + 
                                                                              " WHERE PAAF.PERSON_ID = ?                                                                                            " + 
                                                                              "       AND PAAF.ASSIGNMENT_STATUS_TYPE_ID = PASTT.ASSIGNMENT_STATUS_TYPE_ID                                          " + 
                                                                              "       AND PAPF.PERSON_ID = PAAF.PERSON_ID                                                                           " + 
                                                                              "       AND PASTT.LANGUAGE = USERENV ('LANG')                                                                         " + 
                                                                              "   AND PAAF.ORGANIZATION_ID = POU.ORGANIZATION_ID                                                                    " + 
                                                                              "   AND TRUNC (SYSDATE) BETWEEN TRUNC (POU.DATE_FROM)                                                                 " + 
                                                                              "                          AND NVL (TRUNC (POU.DATE_TO), TRUNC (SYSDATE))                                             " + 
                                                                              "   AND TRUNC(SYSDATE) BETWEEN TRUNC(PAAF.EFFECTIVE_START_DATE)                                                       " +
                                                                              "                          AND TRUNC(NVL(TRUNC(PAAF.EFFECTIVE_END_DATE),TRUNC(SYSDATE)))                              " + 
                                                                              "   AND TRUNC (PAPF.EFFECTIVE_START_DATE) =  (SELECT MAX (TRUNC (EFFECTIVE_START_DATE))                               " + 
                                                                              "                 FROM PER_ALL_PEOPLE_F                                                                               " + 
                                                                              "                WHERE PERSON_ID = ?)                                                                                 " + 
                                                                              "    AND PAAF.primary_flag = 'Y'                                                                                      " , 1); 

         try {
            pstmtEmployeeActive.setString(1, pPersonId);
            pstmtEmployeeActive.setString(2, pPersonId);
            ResultSet rsK = pstmtEmployeeActive.executeQuery();
           if(rsK.next()) {
               v_estatusEmployee = rsK.getString(2).toString();
                System.out.println("Se recupera valor 1: " + rsK.getString(1).toString());
                System.out.println("Se recupera valor 2: " + v_estatusEmployee);
             
            }else{
               System.out.println("...No se encontraron datos que leer.");
            }
         } catch (SQLException var83) {
            System.out.println("Exception 01  - Un error encotrado en al recuperar el valor del Estatus del Empleado: " + var83.getMessage().toString());
         } finally {
            try {
               pstmtEmployeeActive.close();
            } catch (Exception var79) {
                System.out.println("Exception 02  - Un error encotrado en al recuperar el valor del Estatus del Empleado: " + var79.getMessage().toString());
               throw OAException.wrapperException(var79);
            }
         }
         return v_estatusEmployee;
         
     } 
   
    public static String getSoliDtl (String p_cadena){
        int m=0;
        int i;
        int v_indice = 0;
        
        for(i=0;i<p_cadena.length();i++){
            if(p_cadena.substring(m,i+1).equals("{")){
                v_indice = i + 1;
            }
            m++;
        }
        
        return p_cadena.substring(v_indice,p_cadena.length() -1).toString();
    }
    public static String getDotaId( String p_soliDtl, OAApplicationModule oaapplicationmodule){
        System.out.println("Inicia getDotaId...");
        System.out.println("Se recibe p_soliDtl: " + p_soliDtl );
        String v_dotaId = "";
        
        OADBTransactionImpl oadbtransactionimpl = (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        
        OracleCallableStatement pstmtKit = 
        (OracleCallableStatement)oadbtransactionimpl.createCallableStatement
        ("SELECT DOTA_ID FROM XXGAM_INV_SOLI_DTL WHERE SOLI_DTL_ID = ?", 1);

        try {
           pstmtKit.setString(1, p_soliDtl);
           ResultSet rsK = pstmtKit.executeQuery();
          if(rsK.next()) {
               v_dotaId = rsK.getString(1);
           }
        } catch (SQLException var83) {
            throw OAException.wrapperException(var83);
        } finally {
           try {
              pstmtKit.close();
           } catch (Exception var79) {
            throw OAException.wrapperException(var79);
           }
        }    
        System.out.println("Retorna: " + v_dotaId);
    return v_dotaId;    
        
    }    
}
