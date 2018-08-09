package xxgam.oracle.apps.inv.moveorder.webui;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.ResultSet;
import java.sql.SQLException;

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
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import oracle.jdbc.OracleCallableStatement;

import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliDtlVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSolicitudAMImpl;
import xxgam.oracle.apps.inv.moveorder.utils.XxGamInvConstantes;
import xxgam.oracle.apps.inv.moveorder.xxDebugger;


@SuppressWarnings( { "unchecked", "deprecation", "rawtypes", "static" })
public class xXGamSolicitudCrearCO extends OAControllerImpl {

    public static final String RCS_ID = 
        "$Header: xXGamSolicitudCrearCO.java 1.1 2013/02/27 10:51:48 eroncoroni ship $";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header: xXGamSolicitudCrearCO.java 1.1 2013/02/27 10:51:48 eroncoroni ship $", 
                                       "%packagename%");
    public static final String sqlKit = 
        "SELECT NVL(XIK.KIT_ID,-1) , XUK.KIT_CODE , FLV.MEANING FROM XXGAM_HR_UNIFORMS_KIT_V XUK , XXGAM_INV_KIT XIK , FND_LOOKUP_VALUES FLV , FND_LOOKUP_VALUES FLV2 , FND_USER FU WHERE 1 = 1 AND XUK.KIT_CODE = XIK.KIT_COD(+) AND FLV.LOOKUP_TYPE = \'GAM_HR_UNIFORMES\' AND XUK.KIT_CODE = FLV.LOOKUP_CODE AND XUK.LANGUAGE = FLV.LANGUAGE AND FLV2.LOOKUP_TYPE = \'XXGAM_APERTURA_DE_PERIODOS\' AND FLV.LOOKUP_CODE = FLV2.MEANING AND FLV.LANGUAGE = FLV2.LANGUAGE AND (FLV2.START_DATE_ACTIVE IS NULL OR FLV2.START_DATE_ACTIVE <= TRUNC(SYSDATE)) AND (FLV2.END_DATE_ACTIVE IS NULL OR FLV2.END_DATE_ACTIVE >= TRUNC(SYSDATE)) AND XUK.LANGUAGE = \'ESA\' AND XUK.PERSON_ID = FU.EMPLOYEE_ID AND FU.USER_ID = ?";
    public static final String sqlNombreEmpleado = 
        "SELECT xxgam_inv_soli_s.nextval,  ppf.person_id,  ppf.full_name AS nombre_empleado,   ppf.employee_number AS clave_empleado,   ppf.per_information2 AS rfc,  \'NA\' as categoria,   CASE   WHEN UPPER(PPT.USER_PERSON_TYPE) LIKE \'%EVENTUAL%\' THEN \'Eventual\'  WHEN UPPER(PPT.USER_PERSON_TYPE) LIKE \'%PLANTA%\' THEN \'Planta\'  ELSE PPT.USER_PERSON_TYPE  END AS adscripcion,  paaf.date_probation_end AS fecha_termino_contrato,   gcc.segment4 AS estacion,   gcc.segment3 AS centro_costos,   gcc.concatenated_segments AS cuenta,   gcc.code_combination_id,  sysdate as solidate,  \'PENDIENTE\' as pendiente,   pgd.segment4 as categoria_gr FROM per_all_people_f ppf,   fnd_user fu,   per_all_assignments_f paaf,   gl_code_combinations_kfv gcc,   per_grade_definitions pgd,  per_grades pg,  PER_PERSON_TYPES PPT , PER_PERSON_TYPE_USAGES_F PPTU , PAY_USER_TABLES PUT , PAY_USER_COLUMN_INSTANCES_F PUCIF , PAY_USER_ROWS_F PURF , PAY_USER_ROWS_F_VL PURFV , PAY_USER_COLUMNS PUC WHERE fu.employee_id = ppf.person_id    AND SYSDATE BETWEEN ppf.effective_start_date   AND ppf.effective_end_date    AND SYSDATE BETWEEN paaf.effective_start_date  AND paaf.effective_end_date   AND ppf.person_id = paaf.person_id   AND gcc.code_combination_id (+) = paaf.default_code_comb_id     AND pgd.grade_definition_id (+) = pg.grade_definition_id  AND pg.grade_id (+)= paaf.grade_id  AND PPF.PERSON_ID = PPTU.PERSON_ID  AND (PPTU.EFFECTIVE_END_DATE IS NULL OR PPTU.EFFECTIVE_END_DATE >= TRUNC(SYSDATE))  AND PPTU.PERSON_TYPE_ID = PPT.PERSON_TYPE_ID AND UPPER(PPT.USER_PERSON_TYPE) LIKE (\'%\'||UPPER(PURFV.ROW_LOW_RANGE_OR_NAME)||\'%\') AND PUT.USER_TABLE_NAME = \'XXGAM_PARAMETROS_UNIFORMES\' AND PUT.BUSINESS_GROUP_ID = 81 AND PUT.USER_TABLE_ID = PURF.USER_TABLE_ID AND PUT.USER_TABLE_ID = PUC.USER_TABLE_ID AND PUC.USER_COLUMN_NAME = \'Tipo de Persona\' AND PUCIF.USER_ROW_ID = PURF.USER_ROW_ID AND PUCIF.USER_COLUMN_ID = PUC.USER_COLUMN_ID AND SYSDATE BETWEEN PURF.EFFECTIVE_START_DATE AND PURF.EFFECTIVE_END_DATE AND PURF.USER_ROW_ID = PURFV.USER_ROW_ID AND PUT.USER_TABLE_ID = PURFV.USER_TABLE_ID AND SYSDATE BETWEEN PUCIF.EFFECTIVE_START_DATE AND PUCIF.EFFECTIVE_END_DATE  AND fu.user_id = ?";
    public static final String sqlDota = 
        "SELECT xxgam_inv_soli_dtl_s.nextval as secuencia,  xid.DOTA_ID,  xid.uniform_type_cod,  xid.nomenclature,  xid.measure_unit_cod,  xid.cycle_cod,  xid.planta_qty,  xid.event_qty, xid.sust_flag,  \'PENDIENTE\' as status  FROM xxgam_inv_dota     xid  WHERE  xid.kit_id = ?";


    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudCrearCO - Version 1.1  - processRequest", 
                                     3);
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudCrearCO - Initial Variable", 
                                     3);
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        xXGamInvSolicitudAMImpl am = 
            (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
        XxGamInvConstantes constantesErrors = new XxGamInvConstantes();
        OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();

        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        int lUserId = oadbtransactionimpl.getUserId();
        xxDebugger.oadbtransaction = oaapplicationmodule.getOADBTransaction();
        String lAdscr = "NO ENCONTRO";
        Long lSoliId = Long.valueOf(0L); //Valor por default 0
        Long lKitId = Long.valueOf(1L); //Valor por default 1 
        String lKitCode = "NO ENCONTRO";
        String lKitDesc = "NO ENCONTRO";
        /**Guillermo Sandoval(MS-4/OCT/2016) variables para valirdar periodos del kit
       * y solicitud unica para el Employee*/
        String lPeriodValid = "NO ENCONTRO";
        //Variable para validar si el perio esta cerrado(NCLOSE) o si aun no se abre (NPOST) 
        String lPeriodDetail = "NO ENCONTRO";
        String lsolicitudAnterior = "UNICO";
        String lFechaInicial = "UNICO";
        String lFechaFinal = "UNICO";

        String lAccountDefault = "0";
        String v_estatusEmployee = "NA";
        try {
            v_estatusEmployee = 
                    validaEmpleadoActivo(am.obtienePersonId(pageContext.getUserId()).toString(), 
                                         oaapplicationmodule);
        } catch (Exception e) {
            v_estatusEmployee = "NA";
        }

        System.out.println("Estado del Empleado: " + v_estatusEmployee);

        String lMessageErrorEmpInctv = "";


        OAMessageStyledTextBean oanombreEmpleadoBean = 
            (OAMessageStyledTextBean)oapagelayoutbean.findIndexedChildRecursive("nombreEmpleado");
        oanombreEmpleadoBean.setValue(pageContext, "");
        OAMessageStyledTextBean oakitBean = 
            (OAMessageStyledTextBean)oapagelayoutbean.findIndexedChildRecursive("kit");
        OAMessageStyledTextBean oakitDescripcionBean = 
            (OAMessageStyledTextBean)oapagelayoutbean.findIndexedChildRecursive("kitDescripcion");
        xXGamInvSoliVOImpl invSoliVOImplClear = 
            (xXGamInvSoliVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliVO1");
        xXGamInvSoliDtlVOImpl invSoliDtlVOpImplClear = 
            (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
        invSoliVOImplClear.clearCache();
        invSoliDtlVOpImplClear.clearCache();
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudCrearCO - Inicializo las VO", 
                                     3);
        invSoliVOImplClear.initQuery("-1");
        invSoliDtlVOpImplClear.initQuery("-1");
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudCrearCO - Crea Solicitud", 
                                     3);
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudCrearCO - buscamos el KIT - sqlKi por User_idt", 
                                     3);
        //      OracleCallableStatement pstmtKit = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT NVL(XIK.KIT_ID,-1) , XUK.KIT_CODE , FLV.MEANING FROM XXGAM_HR_UNIFORMS_KIT_V XUK , XXGAM_INV_KIT XIK , FND_LOOKUP_VALUES FLV , FND_LOOKUP_VALUES FLV2 , FND_USER FU WHERE 1 = 1 AND XUK.KIT_CODE = XIK.KIT_COD(+) AND FLV.LOOKUP_TYPE = \'GAM_HR_UNIFORMES\' AND XUK.KIT_CODE = FLV.LOOKUP_CODE AND XUK.LANGUAGE = FLV.LANGUAGE AND FLV2.LOOKUP_TYPE = \'XXGAM_APERTURA_DE_PERIODOS\' AND FLV.LOOKUP_CODE = FLV2.MEANING AND FLV.LANGUAGE = FLV2.LANGUAGE AND (FLV2.START_DATE_ACTIVE IS NULL OR FLV2.START_DATE_ACTIVE <= TRUNC(SYSDATE)) AND (FLV2.END_DATE_ACTIVE IS NULL OR FLV2.END_DATE_ACTIVE >= TRUNC(SYSDATE)) AND XUK.LANGUAGE = \'ESA\' AND XUK.PERSON_ID = FU.EMPLOYEE_ID AND FU.USER_ID = ?", 1);
        /**Guillermo Sandoval(MS) se modifica query para saber caundo el periodo del kit se encuentra cerrado.**/
        OracleCallableStatement pstmtKit = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT NVL (XIK.KIT_ID, -1)" + 
                                                                                 "     , XUK.KIT_CODE" + 
                                                                                 "     , FLV.MEANING" + 
                                                                                 "     , CASE " + 
                                                                                 "        WHEN TRUNC(SYSDATE) BETWEEN NVL(FLV2.START_DATE_ACTIVE, SYSDATE) AND NVL(FLV2.END_DATE_ACTIVE, SYSDATE) AND FLV2.ENABLED_FLAG = 'Y'" + 
                                                                                 "        THEN 'Y' " + 
                                                                                 "        ELSE 'N' " + 
                                                                                 "        END VALID_PERIOD" + 
                                                                                 "      , CASE WHEN TRUNC(SYSDATE) < TRUNC(FLV2.START_DATE_ACTIVE) THEN 'NPOST' " + 
                                                                                 "        ELSE 'NCLOSE' " + 
                                                                                 "        END DET_PER     " + 
                                                                                 "      , TO_CHAR(FLV2.START_DATE_ACTIVE, 'DD/MON/YYYY') " + 
                                                                                 "      , TO_CHAR(FLV2.END_DATE_ACTIVE, 'DD/MON/YYYY')    " + 
                                                                                 "  FROM XXGAM_HR_UNIFORMS_KIT_V XUK," + 
                                                                                 "       XXGAM_INV_KIT XIK," + 
                                                                                 "       FND_LOOKUP_VALUES FLV," + 
                                                                                 "       FND_LOOKUP_VALUES FLV2," + 
                                                                                 "       FND_USER FU" + 
                                                                                 " WHERE     1 = 1" + 
                                                                                 "       AND XUK.KIT_CODE = XIK.KIT_COD(+)" + 
                                                                                 "       AND FLV.LOOKUP_TYPE = 'GAM_HR_UNIFORMES'" + 
                                                                                 "       AND XUK.KIT_CODE = FLV.LOOKUP_CODE" + 
                                                                                 "       AND XUK.LANGUAGE = FLV.LANGUAGE" + 
                                                                                 "       AND FLV2.LOOKUP_TYPE = 'XXGAM_APERTURA_DE_PERIODOS'" + 
                                                                                 "       AND FLV.LOOKUP_CODE = FLV2.MEANING" + 
                                                                                 "       AND FLV.LANGUAGE = FLV2.LANGUAGE" + 
                                                                                 "       AND XUK.LANGUAGE = 'ESA'" + 
                                                                                 "       AND XUK.PERSON_ID = FU.EMPLOYEE_ID" + 
                                                                                 "       AND FU.USER_ID = ?", 
                                                                                 1);

        try {
            String rr = String.valueOf(lUserId);
            pstmtKit.setInt(1, lUserId);
            ResultSet rsK = pstmtKit.executeQuery();
            if (rsK.next()) {

                lKitId = Long.valueOf(rsK.getLong(1));
                lKitCode = rsK.getString(2);
                lKitDesc = rsK.getString(3);
                lPeriodValid = rsK.getString(4);
                lPeriodDetail = rsK.getString(5);
                lFechaInicial = rsK.getString(6);
                lFechaFinal = rsK.getString(7);

                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudCrearCO - KIT: " + 
                                             lKitCode, 3);
            } else {
                System.out.println("...No se encontraron datos que leer en ejecucion de query con lUserId: " + 
                                   lUserId);
            }
            System.out.println("lKitId: " + lKitId);
            System.out.println("lKitCode: " + lKitCode);
            System.out.println("lKitDesc: " + lKitDesc);
            System.out.println("lPeriodValid: " + lPeriodValid);
        } catch (SQLException var83) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - sqlKit Error: " + 
                                         var83.getMessage().toString(), 3);
        } finally {
            try {
                pstmtKit.close();
            } catch (Exception var79) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudCrearCO - sqlKit Error: " + 
                                             var79.getMessage().toString(), 3);
                throw OAException.wrapperException(var79);
            }
        }

        /**Guillermo Sandoval(MS) - Query para validar si el usuario tiene solicitudes realizadas anteriormente**/
        OracleCallableStatement callablePeriodos = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(" SELECT NRO_SOLICITUD            " + 
                                                                                 "    FROM XXGAM_INV_SOLI           " + 
                                                                                 "   WHERE EMPLOYEE_NUMBER = ? " + 
                                                                                 "     AND PERSON_ID = ?", 
                                                                                 1);

        try {

            callablePeriodos.setString(1, 
                                       am.obtieneEmployeeNumber(oadbtransactionimpl.getUserId()).toString());
            callablePeriodos.setInt(2, 
                                    Integer.parseInt(am.obtienePersonId(pageContext.getUserId()).toString()));
            ResultSet resultQuery = callablePeriodos.executeQuery();

            if (resultQuery.next()) {
                lsolicitudAnterior = resultQuery.getString(1);
                System.out.println("Solicitud anterior: " + 
                                   lsolicitudAnterior);
            } else {
                System.out.println("...No se encontraron solicitudes anteriores");
            }
        } catch (SQLException var83) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - sql get request to Employee Error: " + 
                                         var83.getMessage().toString(), 3);
            System.out.println("Error  SQLException " + 
                               var83.getMessage().toString());
        } catch (Exception e) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - sql get request to Employee Global Error: " + 
                                         e.getMessage().toString(), 3);
            System.out.println("Error  SQLException " + 
                               e.getMessage().toString());
        } finally {
            try {
                callablePeriodos.close();
            } catch (Exception var79) {
                System.out.println("Error Exception: " + 
                                   var79.getMessage().toString());
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudCrearCO - sql get request to Employee Error: " + 
                                             var79.getMessage().toString(), 3);
                throw OAException.wrapperException(var79);
            }
        }
        /**
         * Validacion para la cuentas de gasto por defecto
         * */
        /*OracleCallableStatement pstmAccounDefault = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(" SELECT NVL(default_code_comb_id,0)                                   " +
                                                                                                  "   FROM per_all_assignments_f                                         " +
                                                                                                  "  WHERE TRUNC(SYSDATE) BETWEEN TRUNC(EFFECTIVE_START_DATE)            " +
                                                                                                  "                           AND TRUNC(NVL(EFFECTIVE_END_DATE,SYSDATE)) " +
                                                                                                  "    AND person_id = ? ", 1);*/


        OracleCallableStatement pstmAccounDefault = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(" SELECT NVL (default_code_comb_id, 0) " + 
                                                                                 "  FROM per_all_assignments_f paaf " + 
                                                                                 "     , PER_ORGANIZATION_UNITS POU " + 
                                                                                 " WHERE TRUNC (SYSDATE) BETWEEN TRUNC (EFFECTIVE_START_DATE) " + 
                                                                                 "                           AND TRUNC (NVL (EFFECTIVE_END_DATE, SYSDATE)) " + 
                                                                                 "   AND PAAF.ORGANIZATION_ID = POU.ORGANIZATION_ID " + 
                                                                                 "   AND TRUNC (SYSDATE) BETWEEN TRUNC (POU.DATE_FROM) " + 
                                                                                 "                          AND NVL (TRUNC (POU.DATE_TO), TRUNC (SYSDATE))" + 
                                                                                 "   AND PAAF.primary_flag = 'Y' " + 
                                                                                 "   AND person_id = ?", 
                                                                                 1);
        try {
            pstmAccounDefault.setInt(1, 
                                     Integer.parseInt(am.getPersonId(pageContext.getUserId()) + 
                                                      ""));
            ResultSet rsAccountDefault = pstmAccounDefault.executeQuery();

            //Cliclo para obteber datos si existe Cuenta de Gasto por defecto
            if (rsAccountDefault.next()) {
                lAccountDefault = rsAccountDefault.getString(1);
            }

        } catch (SQLException var129) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearSuperUserCO - sqlAccountDefault Error: " + 
                                         var129.getMessage().toString(), 3);
            System.out.println("Error al recuperar valor de la cuenta contable.");
        } finally {
            try {
                pstmAccounDefault.close();
            } catch (Exception var124) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudCrearSuperUserCO - sqlAccountDefault Error: " + 
                                             var124.getMessage().toString(), 
                                             3);
                throw OAException.wrapperException(var124);
            }
        }

        System.out.println("lAccountDefault: " + lAccountDefault);
        System.out.println("lPeriodValid: " + lPeriodValid);
        System.out.println("lsolicitudAnterior: " + lsolicitudAnterior);

        String lMessageError;
        //Validacion con valor  -1 en caso de que no se encuentren datos con el lookup GAM_HR_UNIFORMES
        if (!v_estatusEmployee.toString().equals("1") && 
            !v_estatusEmployee.toString().equals("NA")) {
            lMessageErrorEmpInctv = 
                    constantesErrors.ERROR_EMPLOYEE_NOT_ACTIVE.toString();
            MessageToken[] tokens = 
                new MessageToken[] { new MessageToken("MESSAGE", 
                                                      lMessageErrorEmpInctv) };
            throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens);
        } else if (lKitId.longValue() == (long)-1) {
            /**Guillermo Sandoval(MS 4/Oct/2016) se agrega detalle en mensaje de error.*/
            String lmensage = 
                am.getMessageError(pageContext, webBean, Integer.parseInt(am.obtienePersonId(pageContext.getUserId()).toString()));
            System.out.println("Se recupera mensaje : " + lmensage);
            //lMessageError = "El Kit " + lKitCode + " - " + lKitDesc + " no se encuentra configurado. Realice la configuracion del mismo.";
            lMessageError = lmensage;
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - Error: " + 
                                         lMessageError, 3);
            MessageToken[] tokens = 
                new MessageToken[] { new MessageToken("MESSAGE", 
                                                      lMessageError) };
            throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens);

            /**Guillermo Sandoval(MS) se agrega validacion para pintar en pantalla cuando este cerrado el periodo del KIt
       * asi como se agrega la validacion - Si el periodo esta cerrado pero el usuario nunca ha tramitado una solicitud
       * debera dejar que se cree la solictud.*/
        } else if ((lPeriodValid.equals("N")) && 
                   !("UNICO".equals(lsolicitudAnterior))) {
            if (lPeriodDetail.equals("NPOST")) {
                lMessageError = 
                        " Error a\u00fan no se encuentra abierto tu periodo de registro de Uniformes, comprende del " + 
                        lFechaInicial + " al " + lFechaFinal + ".";
                lMessageError = 
                        " Error a\u00fan no se encuentra abierto tu periodo de registro de Uniformes, comprende del " + 
                        lFechaInicial + " al " + lFechaFinal + ".";
            } else {
                lMessageError = 
                        " Error en el periodo de solicitud de Uniformes, el kit " + 
                        lKitCode + " ha vencido, comprende del " + 
                        lFechaInicial + " al " + lFechaFinal + ".";
            }


            MessageToken[] tokens1 = 
                new MessageToken[] { new MessageToken("MESSAGE", 
                                                      lMessageError) };
            throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens1);

        } else if ("NO ENCONTRO".equals(lKitCode)) { //Cuando cae en esta excepcion significa que los periodos del kit se encuentran cerrados.

            /**
         * INGRESAR VALIDACION DE NUEVO EMPLEADO PERO CON KIT FUERA DE PERIODO.
         * SE INGRESAN MENSJES DE EXCEPCION DETALLADA
        ***/
            /*
         lMessageError = "El usuario no tiene asignado un KIT o el mismo esta fuera de vigencia.";
         pageContext.writeDiagnostics(this, "xXGamSolicitudCrearCO - Error: " + lMessageError, 3);
         MessageToken[] tokens1 = new MessageToken[]{new MessageToken("MESSAGE", lMessageError)};
         throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens1);*/
            /**Guillermo Sandoval(MS 4/Oct/2016) se agrega detalle en mensaje de error.*/
            String lmensage = 
                am.getMessageError(pageContext, webBean, Integer.parseInt(am.obtienePersonId(pageContext.getUserId()).toString()));
            System.out.println("Se recupera mensaje : " + lmensage);
            //lMessageError = "El Kit " + lKitCode + " - " + lKitDesc + " no se encuentra configurado. Realice la configuracion del mismo.";
            lMessageError = lmensage;
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - Error: " + 
                                         lMessageError, 3);
            MessageToken[] tokens = 
                new MessageToken[] { new MessageToken("MESSAGE", 
                                                      lMessageError) };
            throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens);
        } else if ("0".equals(lAccountDefault)) {
            System.out.println("Se envia mensaje de faltante en valorde cuenta de gasto por defecto.");
            //lMessageError = "El empleado no tiene asignado un KIT o el mismo esta fuera de vigencia.";
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearSuperUserCO - Error: " + 
                                         constantesErrors.ERROR_NOT_ACCOUNT_DEF, 
                                         3);
            MessageToken[] tokens2 = 
                new MessageToken[] { new MessageToken("MESSAGE", 
                                                      constantesErrors.ERROR_NOT_ACCOUNT_DEF) };
            throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens2);
        } else {
            xXGamInvSoliVOImpl invSoliVOImpl = 
                (xXGamInvSoliVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliVO1");
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - Buscamos los datos del empleado sqlNombreEmpleado", 
                                         3);
            OracleCallableStatement pstmtSoli = 
                (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT xxgam_inv_soli_s.nextval,  ppf.person_id,  ppf.full_name AS nombre_empleado,   ppf.employee_number AS clave_empleado,   ppf.per_information2 AS rfc,  \'NA\' as categoria,   CASE   WHEN UPPER(PPT.USER_PERSON_TYPE) LIKE \'%EVENTUAL%\' THEN \'Eventual\'  WHEN UPPER(PPT.USER_PERSON_TYPE) LIKE \'%PLANTA%\' THEN \'Planta\'  ELSE PPT.USER_PERSON_TYPE  END AS adscripcion,  paaf.date_probation_end AS fecha_termino_contrato,   gcc.segment4 AS estacion,   gcc.segment3 AS centro_costos,   gcc.concatenated_segments AS cuenta,   gcc.code_combination_id,  sysdate as solidate,  \'PENDIENTE\' as pendiente,   pgd.segment4 as categoria_gr FROM per_all_people_f ppf,   fnd_user fu,   per_all_assignments_f paaf,   gl_code_combinations_kfv gcc,   per_grade_definitions pgd,  per_grades pg,  PER_PERSON_TYPES PPT , PER_PERSON_TYPE_USAGES_F PPTU , PAY_USER_TABLES PUT , PAY_USER_COLUMN_INSTANCES_F PUCIF , PAY_USER_ROWS_F PURF , PAY_USER_ROWS_F_VL PURFV , PAY_USER_COLUMNS PUC WHERE fu.employee_id = ppf.person_id    AND SYSDATE BETWEEN ppf.effective_start_date   AND ppf.effective_end_date    AND SYSDATE BETWEEN paaf.effective_start_date  AND paaf.effective_end_date   AND ppf.person_id = paaf.person_id   AND gcc.code_combination_id (+) = paaf.default_code_comb_id     AND pgd.grade_definition_id (+) = pg.grade_definition_id  AND pg.grade_id (+)= paaf.grade_id  AND PPF.PERSON_ID = PPTU.PERSON_ID  AND (PPTU.EFFECTIVE_END_DATE IS NULL OR PPTU.EFFECTIVE_END_DATE >= TRUNC(SYSDATE))  AND PPTU.PERSON_TYPE_ID = PPT.PERSON_TYPE_ID AND UPPER(PPT.USER_PERSON_TYPE) LIKE (\'%\'||UPPER(PURFV.ROW_LOW_RANGE_OR_NAME)||\'%\') AND PUT.USER_TABLE_NAME = \'XXGAM_PARAMETROS_UNIFORMES\' AND PUT.BUSINESS_GROUP_ID = 81 AND PUT.USER_TABLE_ID = PURF.USER_TABLE_ID AND PUT.USER_TABLE_ID = PUC.USER_TABLE_ID AND PUC.USER_COLUMN_NAME = \'Tipo de Persona\' AND PUCIF.USER_ROW_ID = PURF.USER_ROW_ID AND PUCIF.USER_COLUMN_ID = PUC.USER_COLUMN_ID AND SYSDATE BETWEEN PURF.EFFECTIVE_START_DATE AND PURF.EFFECTIVE_END_DATE AND PURF.USER_ROW_ID = PURFV.USER_ROW_ID AND PUT.USER_TABLE_ID = PURFV.USER_TABLE_ID AND SYSDATE BETWEEN PUCIF.EFFECTIVE_START_DATE AND PUCIF.EFFECTIVE_END_DATE  AND fu.user_id = ?", 
                                                                                     1);

            try {
                pstmtSoli.setInt(1, lUserId);
                ResultSet rsS = pstmtSoli.executeQuery();
                if (!rsS.next()) {
                    lMessageError = 
                            "Los datos del empleado estan fuera de vigencia.";
                    pageContext.writeDiagnostics(this, 
                                                 "xXGamSolicitudCrearCO - Error: " + 
                                                 lMessageError, 3);
                    MessageToken[] tokens2 = 
                        new MessageToken[] { new MessageToken("MESSAGE", 
                                                              lMessageError) };
                    throw new OAException("FND", "FND_GENERIC_MESSAGE", 
                                          tokens2);
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
                rowSoli.setAttribute("ExpenseAcc", 
                                     Long.valueOf(rsS.getLong(12)));
                rowSoli.setAttribute("SoliDate", rsS.getDate(13));
                rowSoli.setAttribute("Status", rsS.getString(14));
                rowSoli.setAttribute("Category", rsS.getString(15));
                rowSoli.setAttribute("NroSolicitud", "UNI-" + soliD.trim());
                rowSoli.setAttribute("KitId", lKitId);
                oakitBean.setValue(pageContext, lKitCode);
                oakitDescripcionBean.setValue(pageContext, lKitDesc);
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudCrearCO - Crea Nro de Solicitud: " + 
                                             soliD.trim().toString(), 3);
            } catch (SQLException var81) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudCrearCO - sqlNombreEmpleado Error: " + 
                                             var81.getMessage().toString(), 3);
            } finally {
                try {
                    pstmtSoli.close();
                } catch (Exception var80) {
                    pageContext.writeDiagnostics(this, 
                                                 "xXGamSolicitudCrearCO - sqlNombreEmpleado Error: " + 
                                                 var80.getMessage().toString(), 
                                                 3);
                    throw OAException.wrapperException(var80);
                }
            }

            boolean lFlagLoop = false;
            xXGamInvSoliDtlVOImpl invSoliDtlVOImpl = 
                (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
            invSoliDtlVOImpl.initQuery(lSoliId.toString());
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - Buscamos las dotaciones configuradas-sqlDota", 
                                         3);
            OracleCallableStatement pstmtDota = 
                (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT xxgam_inv_soli_dtl_s.nextval as secuencia,  xid.DOTA_ID,  xid.uniform_type_cod,  xid.nomenclature,  xid.measure_unit_cod,  xid.cycle_cod,  xid.planta_qty,  xid.event_qty, xid.sust_flag,  \'PENDIENTE\' as status  FROM xxgam_inv_dota     xid  WHERE  xid.kit_id = ?", 
                                                                                     1);

            try {
                pstmtDota.setLong(1, lKitId.longValue());
                ResultSet rsD = pstmtDota.executeQuery();

                while (rsD.next()) {
                    Row rowDot = invSoliDtlVOImpl.createRow();
                    invSoliDtlVOImpl.insertRow(rowDot);
                    rowDot.setNewRowState((byte)-1);
                    invSoliDtlVOImpl.setCurrentRow(rowDot);
                    invSoliDtlVOImpl.getCurrentRow().setAttribute("SoliId", 
                                                                  lSoliId);
                    invSoliDtlVOImpl.getCurrentRow().setAttribute("SoliDtlId", 
                                                                  Long.valueOf(rsD.getLong(1)));
                    invSoliDtlVOImpl.getCurrentRow().setAttribute("DotaId", 
                                                                  Long.valueOf(rsD.getLong(2)));
                    invSoliDtlVOImpl.getCurrentRow().setAttribute("UniformTypeCod", 
                                                                  rsD.getString(3));
                    invSoliDtlVOImpl.getCurrentRow().setAttribute("Nomenclature", 
                                                                  rsD.getString(4));
                    invSoliDtlVOImpl.getCurrentRow().setAttribute("MeasureUnitCod", 
                                                                  rsD.getString(5));
                    invSoliDtlVOImpl.getCurrentRow().setAttribute("CycleCod", 
                                                                  rsD.getString(6));
                    invSoliDtlVOImpl.getCurrentRow().setAttribute("QtyPlanta", 
                                                                  Long.valueOf(rsD.getLong(7)));
                    invSoliDtlVOImpl.getCurrentRow().setAttribute("QtyEventual", 
                                                                  Long.valueOf(rsD.getLong(8)));
                    invSoliDtlVOImpl.getCurrentRow().setAttribute("SustitucionSwitcher", 
                                                                  rsD.getString(9));
                    invSoliDtlVOImpl.getCurrentRow().setAttribute("Status", 
                                                                  rsD.getString(10));
                    invSoliDtlVOImpl.getCurrentRow().setAttribute("Attribute1", 
                                                                  lAdscr);
                    if ("Planta".equals(lAdscr)) {
                        invSoliDtlVOImpl.getCurrentRow().setAttribute("Attribute2", 
                                                                      rsD.getString(7));
                    } else {
                        invSoliDtlVOImpl.getCurrentRow().setAttribute("Attribute2", 
                                                                      rsD.getString(8));
                    }

                    lFlagLoop = true;
                    pageContext.writeDiagnostics(this, 
                                                 "xXGamSolicitudCrearCO - Crea Dotacion Detalle Id : " + 
                                                 rsD.getString(1), 3);
                }

                if (!lFlagLoop) {
                    lMessageError = 
                            "No se encontro configurado las dotaciones para el KIT.";
                    pageContext.writeDiagnostics(this, 
                                                 "xXGamSolicitudCrearCO - Error: " + 
                                                 lMessageError, 3);
                    MessageToken[] tokens3 = 
                        new MessageToken[] { new MessageToken("MESSAGE", 
                                                              lMessageError) };
                    throw new OAException("FND", "FND_GENERIC_MESSAGE", 
                                          tokens3);
                }

                oadbtransactionimpl.commit();
                invSoliDtlVOImpl.initQuery(lSoliId.toString());
            } catch (SQLException var85) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudCrearCO - sqlDota Error: " + 
                                             var85.getMessage().toString(), 3);
            } finally {
                try {
                    pstmtDota.close();
                } catch (Exception var78) {
                    pageContext.writeDiagnostics(this, 
                                                 "xXGamSolicitudCrearCO - sqlDota Error: " + 
                                                 var78.getMessage().toString(), 
                                                 3);
                    throw OAException.wrapperException(var78);
                }
            }
            /**
       * Validación para inactivar casillas en las que aun no correspode renovacion de prenda por ciclo.
       * */

            am.validaCicloConFechaEntrega(pageContext, webBean);

        }


    }

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();
        xXGamInvSolicitudAMImpl am = 
            (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
        String lpDotaId = "NOENCONTRO";
        String lpSoliId = "NOENCONTRO";
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        String lEvent = pageContext.getParameter("event");
        String lSource = pageContext.getParameter("source");
        if ("grabar".equals(lEvent)) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - Event Grabar", 
                                         3);
            OAFormValueBean oaSoliBean = 
                (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("SoliIdH");
            String lSoliIdC = (String)oaSoliBean.getValue(pageContext);
            oadbtransactionimpl.commit();
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - ejecuta XXGAM_INV_MOVEORDER_PKG.VALIDA_SUSTITUTOS", 
                                         3);
            String lSql = 
                "BEGIN XXGAM_INV_MOVEORDER_PKG.VALIDA_SUSTITUTOS(?); END;";
            OracleCallableStatement pstmtSust = 
                (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(lSql, 
                                                                                     1);

            try {
                pstmtSust.setLong(1, Long.valueOf(lSoliIdC).longValue());
                pstmtSust.execute();
                pstmtSust.close();
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudCrearCO - ejecutado con exito", 
                                             3);
            } catch (SQLException var33) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudCrearCO - pstmtSust Error: " + 
                                             var33.getMessage().toString(), 3);

                try {
                    pstmtSust.close();
                } catch (Exception var32) {
                    pageContext.writeDiagnostics(this, 
                                                 "xXGamSolicitudCrearCO - pstmtSust Error: " + 
                                                 var32.getMessage().toString(), 
                                                 3);
                }

                throw OAException.wrapperException(var33);
            }

            oadbtransactionimpl.commit();
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - Commit Fin Grabar", 
                                         3);
            xXGamInvSoliDtlVOImpl invSoliDtlVOImpl1 = 
                (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - Reconsulto la VO para el Soli Id" + 
                                         lSoliIdC.toString(), 3);
            invSoliDtlVOImpl1.clearCache();
            invSoliDtlVOImpl1.initQuery(lSoliIdC);
            MessageToken[] tokens = 
                new MessageToken[] { new MessageToken("MESSAGE", 
                                                      "Se han guardado los cambios con \u00e9xito.") };
            OAException message = 
                new OAException("FND", "FND_GENERIC_MESSAGE", tokens, (byte)3, 
                                (Exception[])null);
            pageContext.putDialogMessage(message);
            am.validaCicloConFechaEntrega(pageContext, webBean);
        }

        if ("generarpedido".equals(lEvent)) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - Event generarpedido", 
                                         3);
            OAFormValueBean oaStatusBean = 
                (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("StatusHDR");
            String lStatus = (String)oaStatusBean.getValue(pageContext);
            if ("CERRADO".equals(lStatus) || "PREAPROBADO".equals(lStatus)) {
                String lMessageE = 
                    "La solicitud ya fue Aprobada o Cerrada. Estado actual: " + 
                    lStatus.toString();
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudCrearCO - Error: " + 
                                             lMessageE, 3);
                MessageToken[] tokens1 = 
                    new MessageToken[] { new MessageToken("MESSAGE", 
                                                          lMessageE) };
                throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens1);
            }

            oadbtransactionimpl.commit();
            String lSoliIdSTR = pageContext.getParameter("pgSoliId");
            new Number(0);
            xXGamInvSoliDtlVOImpl invSoliDtlVOImpl11 = 
                (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
            invSoliDtlVOImpl11.clearCache();
            invSoliDtlVOImpl11.initQuery(lSoliIdSTR);
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - Ejecuta concurrente para SOLI ID " + 
                                         lSoliIdSTR, 3);
            Number lReqId = 
                this.callConcMoveOrder(lSoliIdSTR, "XXGAM_INV_CREATE_MV", 
                                       "XXGAM INV Crear Move Order", 
                                       pageContext, webBean);
            if (lReqId.compareTo(0) <= 0) {
                String lMessageE1 = "No se ha ejecutado el concurrente.";
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudCrearCO - Error al ejecutar concurrente", 
                                             3);
                MessageToken[] tokens3 = 
                    new MessageToken[] { new MessageToken("MESSAGE", 
                                                          lMessageE1) };
                throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens3);
            }

            MessageToken[] tokens2 = 
                new MessageToken[] { new MessageToken("MESSAGE", 
                                                      "Se ejecuto la creaci\u00f3n del pedido verifique el concurrente REQUEST ID: " + 
                                                      lReqId.toString()) };
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudCrearCO - verifique el concurrente REQUEST ID: " + 
                                         lReqId.toString(), 3);
            OAException message1 = 
                new OAException("FND", "FND_GENERIC_MESSAGE", tokens2, (byte)3, 
                                (Exception[])null);
            pageContext.putDialogMessage(message1);
        }
        if ("downLoadPDF".equals(lEvent)) {
            System.out.println("Inicia proceso para la descarga de PDF."); //"/taemxi/applmgr/1200/xbol/12.0.0/pdf/POGAM-RH-014_(uso de uniforme).pdf",
                downloadFileFromServer(pageContext, 
                                       "/interface/i_aemx60/PAEMXI/incoming/Uniformes/Politicas/POGAM-RH-014_(uso de uniforme).pdf", 
                                       "POGAM-RH-014_(uso de uniforme).pdf");
            throw new OAException("Se ha descargado archivo POGAM-RH-014_(uso de uniforme).pdf.", 
                                  OAException.INFORMATION);

        }

        if ("lovPrepare".equals(lEvent)) {
            pageContext.putSessionValue("SoliDtlValue", 
                                        getDotaId(getSoliDtl(pageContext.getParameter("evtSrcRowRef")).toString(), 
                                                  oaapplicationmodule));
        }

    }

    public Number callConcMoveOrder(String strSoliId, String reqCode, 
                                    String title, OAPageContext pageContext, 
                                    OAWebBean webBean) {
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        Vector lParam = new Vector();
        lParam.addElement(strSoliId);
        String lAppCode = "XBOL";
        String lConcReqCode = reqCode;
        int lRequestId = 0;

        try {
            ConcurrentRequest lConc = 
                new ConcurrentRequest(oaapplicationmodule.getOADBTransaction().getJdbcConnection());
            lRequestId = 
                    lConc.submitRequest(lAppCode, lConcReqCode, title, (String)null, 
                                        false, lParam);
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
                                       String file_name_with_ext) {
        HttpServletResponse response = 
            (HttpServletResponse)pageContext.getRenderingContext().getServletResponse();
        if (((file_name_with_path == null) || 
             ("".equals(file_name_with_path)))) {
            throw new OAException("La ruta del achivo no es valida.");
        }

        File fileToDownload = null;
        try {
            fileToDownload = new File(file_name_with_path);
        } catch (Exception e) {
            throw new OAException("No existe la reta del archivo.");
        }

        if (!fileToDownload.exists()) {
            throw new OAException("No se encontro archivo a descargar.");
        }

        if (!fileToDownload.canRead()) {
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

        try {
            outs = response.getOutputStream();
            in = new BufferedInputStream(new FileInputStream(fileToDownload));
            int ch;
            while ((ch = in.read()) != -1) {
                outs.write(ch);
            }

        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        } finally {
            try {
                outs.flush();
                outs.close();
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getMimeType(String s) {
        int i = s.lastIndexOf(".");
        if (i > 0 && i < s.length() - 1) {
            String s1 = s.substring(i + 1);
            if (s1.equalsIgnoreCase("amr")) {
                return "audio/amr";
            }
            if (s1.equalsIgnoreCase("mid")) {
                return "audio/midi";
            }
            if (s1.equalsIgnoreCase("mmf")) {
                return "application/vnd.smaf";
            }
            if (s1.equalsIgnoreCase("qcp")) {
                return "audio/vnd.qcelp";
            }
            if (s1.equalsIgnoreCase("hqx")) {
                return "application/mac-binhex40";
            }
            if (s1.equalsIgnoreCase("cpt")) {
                return "application/mac-compactpro";
            }
            if (s1.equalsIgnoreCase("doc")) {
                return "application/msword";
            }
            if (s1.equalsIgnoreCase("jsp")) {
                return "application/jsp";
            }
            if (s1.equalsIgnoreCase("oda")) {
                return "application/oda";
            }
            if (s1.equalsIgnoreCase("pdf")) {
                return "application/pdf";
            }
            if (s1.equalsIgnoreCase("ai")) {
                return "application/postscript";
            }
            if (s1.equalsIgnoreCase("eps")) {
                return "application/postscript";
            }
            if (s1.equalsIgnoreCase("ps")) {
                return "application/postscript";
            }
            if (s1.equalsIgnoreCase("ppt")) {
                return "application/powerpoint";
            }
            if (s1.equalsIgnoreCase("rtf")) {
                return "application/rtf";
            }
            if (s1.equalsIgnoreCase("bcpio")) {
                return "application/x-bcpio";
            }
            if (s1.equalsIgnoreCase("vcd")) {
                return "application/x-cdlink";
            }
            if (s1.equalsIgnoreCase("Z")) {
                return "application/x-compress";
            }
            if (s1.equalsIgnoreCase("cpio")) {
                return "application/x-cpio";
            }
            if (s1.equalsIgnoreCase("csh")) {
                return "application/x-csh";
            }
            if (s1.equalsIgnoreCase("dcr")) {
                return "application/x-director";
            }
            if (s1.equalsIgnoreCase("dir")) {
                return "application/x-director";
            }
            if (s1.equalsIgnoreCase("dxr")) {
                return "application/x-director";
            }
            if (s1.equalsIgnoreCase("dvi")) {
                return "application/x-dvi";
            }
            if (s1.equalsIgnoreCase("gtar")) {
                return "application/x-gtar";
            }
            if (s1.equalsIgnoreCase("gz")) {
                return "application/x-gzip";
            }
            if (s1.equalsIgnoreCase("hdf")) {
                return "application/x-hdf";
            }
            if (s1.equalsIgnoreCase("cgi")) {
                return "application/x-httpd-cgi";
            }
            if (s1.equalsIgnoreCase("jnlp")) {
                return "application/x-java-jnlp-file";
            }
            if (s1.equalsIgnoreCase("skp")) {
                return "application/x-koan";
            }
            if (s1.equalsIgnoreCase("skd")) {
                return "application/x-koan";
            }
            if (s1.equalsIgnoreCase("skt")) {
                return "application/x-koan";
            }
            if (s1.equalsIgnoreCase("skm")) {
                return "application/x-koan";
            }
            if (s1.equalsIgnoreCase("latex")) {
                return "application/x-latex";
            }
            if (s1.equalsIgnoreCase("mif")) {
                return "application/x-mif";
            }
            if (s1.equalsIgnoreCase("nc")) {
                return "application/x-netcdf";
            }
            if (s1.equalsIgnoreCase("cdf")) {
                return "application/x-netcdf";
            }
            if (s1.equalsIgnoreCase("sh")) {
                return "application/x-sh";
            }
            if (s1.equalsIgnoreCase("shar")) {
                return "application/x-shar";
            }
            if (s1.equalsIgnoreCase("sit")) {
                return "application/x-stuffit";
            }
            if (s1.equalsIgnoreCase("sv4cpio")) {
                return "application/x-sv4cpio";
            }
            if (s1.equalsIgnoreCase("sv4crc")) {
                return "application/x-sv4crc";
            }
            if (s1.equalsIgnoreCase("tar")) {
                return "application/x-tar";
            }
            if (s1.equalsIgnoreCase("tcl")) {
                return "application/x-tcl";
            }
            if (s1.equalsIgnoreCase("tex")) {
                return "application/x-tex";
            }
            if (s1.equalsIgnoreCase("textinfo")) {
                return "application/x-texinfo";
            }
            if (s1.equalsIgnoreCase("texi")) {
                return "application/x-texinfo";
            }
            if (s1.equalsIgnoreCase("t")) {
                return "application/x-troff";
            }
            if (s1.equalsIgnoreCase("tr")) {
                return "application/x-troff";
            }
            if (s1.equalsIgnoreCase("roff")) {
                return "application/x-troff";
            }
            if (s1.equalsIgnoreCase("man")) {
                return "application/x-troff-man";
            }
            if (s1.equalsIgnoreCase("me")) {
                return "application/x-troff-me";
            }
            if (s1.equalsIgnoreCase("ms")) {
                return "application/x-troff-ms";
            }
            if (s1.equalsIgnoreCase("ustar")) {
                return "application/x-ustar";
            }
            if (s1.equalsIgnoreCase("src")) {
                return "application/x-wais-source";
            }
            if (s1.equalsIgnoreCase("xml")) {
                return "text/xml";
            }
            if (s1.equalsIgnoreCase("ent")) {
                return "text/xml";
            }
            if (s1.equalsIgnoreCase("cat")) {
                return "text/xml";
            }
            if (s1.equalsIgnoreCase("sty")) {
                return "text/xml";
            }
            if (s1.equalsIgnoreCase("dtd")) {
                return "text/dtd";
            }
            if (s1.equalsIgnoreCase("xsl")) {
                return "text/xsl";
            }
            if (s1.equalsIgnoreCase("zip")) {
                return "application/zip";
            }
            if (s1.equalsIgnoreCase("au")) {
                return "audio/basic";
            }
            if (s1.equalsIgnoreCase("snd")) {
                return "audio/basic";
            }
            if (s1.equalsIgnoreCase("mpga")) {
                return "audio/mpeg";
            }
            if (s1.equalsIgnoreCase("mp2")) {
                return "audio/mpeg";
            }
            if (s1.equalsIgnoreCase("mp3")) {
                return "audio/mpeg";
            }
            if (s1.equalsIgnoreCase("aif")) {
                return "audio/x-aiff";
            }
            if (s1.equalsIgnoreCase("aiff")) {
                return "audio/x-aiff";
            }
            if (s1.equalsIgnoreCase("aifc")) {
                return "audio/x-aiff";
            }
            if (s1.equalsIgnoreCase("ram")) {
                return "audio/x-pn-realaudio";
            }
            if (s1.equalsIgnoreCase("rpm")) {
                return "audio/x-pn-realaudio-plugin";
            }
            if (s1.equalsIgnoreCase("ra")) {
                return "audio/x-realaudio";
            }
            if (s1.equalsIgnoreCase("wav")) {
                return "audio/x-wav";
            }
            if (s1.equalsIgnoreCase("pdb")) {
                return "chemical/x-pdb";
            }
            if (s1.equalsIgnoreCase("xyz")) {
                return "chemical/x-pdb";
            }
            if (s1.equalsIgnoreCase("gif")) {
                return "image/gif";
            }
            if (s1.equalsIgnoreCase("ief")) {
                return "image/ief";
            }
            if (s1.equalsIgnoreCase("jpeg")) {
                return "image/jpeg";
            }
            if (s1.equalsIgnoreCase("jpg")) {
                return "image/jpeg";
            }
            if (s1.equalsIgnoreCase("jpe")) {
                return "image/jpeg";
            }
            if (s1.equalsIgnoreCase("png")) {
                return "image/png";
            }
            if (s1.equalsIgnoreCase("tiff")) {
                return "image/tiff";
            }
            if (s1.equalsIgnoreCase("tif")) {
                return "image/tiff";
            }
            if (s1.equalsIgnoreCase("ras")) {
                return "image/x-cmu-raster";
            }
            if (s1.equalsIgnoreCase("pnm")) {
                return "image/x-portable-anymap";
            }
            if (s1.equalsIgnoreCase("pbm")) {
                return "image/x-portable-bitmap";
            }
            if (s1.equalsIgnoreCase("pgm")) {
                return "image/x-portable-graymap";
            }
            if (s1.equalsIgnoreCase("ppm")) {
                return "image/x-portable-pixmap";
            }
            if (s1.equalsIgnoreCase("rgb")) {
                return "image/x-rgb";
            }
            if (s1.equalsIgnoreCase("xbm")) {
                return "image/x-xbitmap";
            }
            if (s1.equalsIgnoreCase("xpm")) {
                return "image/x-xpixmap";
            }
            if (s1.equalsIgnoreCase("xwd")) {
                return "image/x-xwindowdump";
            }
            if (s1.equalsIgnoreCase("html")) {
                return "text/html";
            }
            if (s1.equalsIgnoreCase("htm")) {
                return "text/html";
            }
            if (s1.equalsIgnoreCase("txt")) {
                return "text/plain";
            }
            if (s1.equalsIgnoreCase("rtx")) {
                return "text/richtext";
            }
            if (s1.equalsIgnoreCase("tsv")) {
                return "text/tab-separated-values";
            }
            if (s1.equalsIgnoreCase("etx")) {
                return "text/x-setext";
            }
            if (s1.equalsIgnoreCase("sgml")) {
                return "text/x-sgml";
            }
            if (s1.equalsIgnoreCase("sgm")) {
                return "text/x-sgml";
            }
            if (s1.equalsIgnoreCase("mpeg")) {
                return "video/mpeg";
            }
            if (s1.equalsIgnoreCase("mpg")) {
                return "video/mpeg";
            }
            if (s1.equalsIgnoreCase("mpe")) {
                return "video/mpeg";
            }
            if (s1.equalsIgnoreCase("qt")) {
                return "video/quicktime";
            }
            if (s1.equalsIgnoreCase("mov")) {
                return "video/quicktime";
            }
            if (s1.equalsIgnoreCase("avi")) {
                return "video/x-msvideo";
            }
            if (s1.equalsIgnoreCase("movie")) {
                return "video/x-sgi-movie";
            }
            if (s1.equalsIgnoreCase("ice")) {
                return "x-conference/x-cooltalk";
            }
            if (s1.equalsIgnoreCase("wrl")) {
                return "x-world/x-vrml";
            }
            if (s1.equalsIgnoreCase("vrml")) {
                return "x-world/x-vrml";
            }
            if (s1.equalsIgnoreCase("wml")) {
                return "text/vnd.wap.wml";
            }
            if (s1.equalsIgnoreCase("wmlc")) {
                return "application/vnd.wap.wmlc";
            }
            if (s1.equalsIgnoreCase("wmls")) {
                return "text/vnd.wap.wmlscript";
            }
            if (s1.equalsIgnoreCase("wmlsc")) {
                return "application/vnd.wap.wmlscriptc";
            }
            if (s1.equalsIgnoreCase("wbmp")) {
                return "image/vnd.wap.wbmp";
            }
            if (s1.equalsIgnoreCase("css")) {
                return "text/css";
            }
            if (s1.equalsIgnoreCase("jad")) {
                return "text/vnd.sun.j2me.app-descriptor";
            }
            if (s1.equalsIgnoreCase("jar")) {
                return "application/java-archive";
            }
            if (s1.equalsIgnoreCase("3gp")) {
                return "video/3gp";
            }
            if (s1.equalsIgnoreCase("3g2")) {
                return "video/3gpp2";
            }
            if (s1.equalsIgnoreCase("mp4")) {
                return "video/3gpp";
            }
        }
        return "application/octet-stream";
    }

    public String validaEmpleadoActivo(String pPersonId, 
                                       OAApplicationModule oaapplicationmodule) {
        String v_estatusEmployee = "NA";
        System.out.println("Inicia Proceso validaEmpleadoActivo, se recibe pPersonId: " + 
                           pPersonId);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
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
                                                                                 "    AND PAAF.primary_flag = 'Y'                                                                                      ", 
                                                                                 1);

        try {
            pstmtEmployeeActive.setString(1, pPersonId);
            pstmtEmployeeActive.setString(2, pPersonId);
            ResultSet rsK = pstmtEmployeeActive.executeQuery();
            if (rsK.next()) {
                v_estatusEmployee = rsK.getString(2).toString();
                System.out.println("Se recupera valor 1: " + 
                                   rsK.getString(1).toString());
                System.out.println("Se recupera valor 2: " + 
                                   v_estatusEmployee);

            } else {
                System.out.println("...No se encontraron datos que leer.");
            }
        } catch (SQLException var83) {
            System.out.println("Exception 01  - Un error encotrado en al recuperar el valor del Estatus del Empleado: " + 
                               var83.getMessage().toString());
        } finally {
            try {
                pstmtEmployeeActive.close();
            } catch (Exception var79) {
                System.out.println("Exception 02  - Un error encotrado en al recuperar el valor del Estatus del Empleado: " + 
                                   var79.getMessage().toString());
                throw OAException.wrapperException(var79);
            }
        }
        return v_estatusEmployee;

    }

    public static String getSoliDtl(String p_cadena) {
        int m = 0;
        int i;
        int v_indice = 0;

        for (i = 0; i < p_cadena.length(); i++) {
            if (p_cadena.substring(m, i + 1).equals("{")) {
                v_indice = i + 1;
            }
            m++;
        }

        return p_cadena.substring(v_indice, p_cadena.length() - 1).toString();
    }

    public static String getDotaId(String p_soliDtl, 
                                   OAApplicationModule oaapplicationmodule) {
        System.out.println("Inicia getDotaId...");
        System.out.println("Se recibe p_soliDtl: " + p_soliDtl);
        String v_dotaId = "";

        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();

        OracleCallableStatement pstmtKit = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT DOTA_ID FROM XXGAM_INV_SOLI_DTL WHERE SOLI_DTL_ID = ?", 
                                                                                 1);

        try {
            pstmtKit.setString(1, p_soliDtl);
            ResultSet rsK = pstmtKit.executeQuery();
            if (rsK.next()) {
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
