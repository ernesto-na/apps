package xxgam.oracle.apps.inv.moveorder.webui;

import java.math.BigDecimal;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

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

import oracle.jbo.domain.Number;

import oracle.jdbc.OracleCallableStatement;

import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliDtlVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSolicitudAMImpl;
import xxgam.oracle.apps.inv.moveorder.utils.XxGamInvUtils;
import xxgam.oracle.apps.inv.moveorder.xxDebugger;


@SuppressWarnings( { "unchecked", "deprecation", "rawtypes", "static" })
public class xXGamInvDevolucionesCO extends OAControllerImpl {

    public static final String RCS_ID = "$Header$";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header$", "%packagename%");
    public static final String sqlNombreEmpleado = 
        "SELECT distinct ppf.full_name FROM per_all_people_f ppf,  xxgam_inv_soli xis WHERE xis.person_id = ppf.person_id  AND SYSDATE BETWEEN ppf.effective_start_date   AND ppf.effective_end_date  AND xis.soli_id = ?";


    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvDevolucionesCO - Version 1.1 - processRequest", 
                                     3);
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        xxDebugger.oadbtransaction = oaapplicationmodule.getOADBTransaction();
        String lpSoliId = pageContext.getParameter("SoliId");
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvDevolucionesCO - Consulta Soli Id: " + 
                                     lpSoliId, 3);
        OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();
        OAMessageStyledTextBean oanombreEmpleadoBean = 
            (OAMessageStyledTextBean)oapagelayoutbean.findIndexedChildRecursive("nombreEmpleado");
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvDevolucionesCO - Buscamos el nombre del empleado sqlNombreEmpleado", 
                                     3);
        OracleCallableStatement pstmt = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT distinct ppf.full_name FROM per_all_people_f ppf,  xxgam_inv_soli xis WHERE xis.person_id = ppf.person_id  AND SYSDATE BETWEEN ppf.effective_start_date   AND ppf.effective_end_date  AND xis.soli_id = ?", 
                                                                                 1);

        try {
            pstmt.setLong(1, (new Long(lpSoliId)).longValue());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String lNombreEmpleado = rs.getString(1);
                oanombreEmpleadoBean.setValue(pageContext, lNombreEmpleado);
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvDevolucionesCO - lNombreEmpleado: " + 
                                             lNombreEmpleado, 3);
            }
        } catch (SQLException var22) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvDevolucionesCO - sqlNombreEmpleado Error: " + 
                                         var22.getMessage().toString(), 3);
        } finally {
            try {
                pstmt.close();
            } catch (Exception var21) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvDevolucionesCO - sqlNombreEmpleado Error: " + 
                                             var21.getMessage().toString(), 3);
                throw OAException.wrapperException(var21);
            }
        }

        xXGamInvSolicitudAMImpl am = 
            (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvDevolucionesCO - Incia Consulta en VO xXGamInvSoliVO1 ", 
                                     3);
        xXGamInvSoliVOImpl invSoliVOImpl = 
            (xXGamInvSoliVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliVO1");
        invSoliVOImpl.initQuery(lpSoliId);
        xXGamInvSoliDtlVOImpl invSoliDtlVOImpl = 
            (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
        invSoliDtlVOImpl.initQuery(lpSoliId);
        am.deshabilitaFilas(pageContext);
    }

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        XxGamInvUtils utils = new XxGamInvUtils();
        super.processFormRequest(pageContext, webBean);
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvDevolucionesCO - Version 1.0 - processFormRequest", 
                                     3);
        xXGamInvSolicitudAMImpl am = 
            (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();
        String lEvent = pageContext.getParameter("event");
        if ("grabar".equals(lEvent)) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvDevolucionesCO - Evento Grabar", 
                                         3);
            OAFormValueBean oaSoliBean = 
                (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("SoliIdH");
            String lSoliIdC = (String)oaSoliBean.getValue(pageContext);
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvDevolucionesCO - Commit para Soli Id " + 
                                         lSoliIdC, 3);
            oadbtransactionimpl.commit();
            String personId = null;
            OAFormValueBean oaPersonIdBean = 
                (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("PersonId");
            if (oaPersonIdBean != null) {
                personId = (String)oaPersonIdBean.getValue(pageContext);
            }

            String compania = 
                am.obtieneCompania(pageContext, webBean, personId);
            String subinventario = 
                am.obtieneSubInventario(pageContext, webBean, compania);
            Number orgId = 
                am.obtieneOrganizationId(pageContext, webBean, compania);
            new ArrayList();
            List listanoNombreSinExistencia = 
                am.validaExistencia(pageContext, webBean, orgId, subinventario, 
                                    lSoliIdC, compania);
            Iterator iterSinExistencia = listanoNombreSinExistencia.iterator();
            String prenda = "";
            StringBuffer prendas = new StringBuffer();

            boolean flag;
            for (flag = false; iterSinExistencia.hasNext(); flag = true) {
                prenda = (String)iterSinExistencia.next();
                prendas.append(prenda + ", ");
            }

            String msgPrendas = "";
            if (flag) {
                msgPrendas = 
                        "La(s) prenda(s): " + prendas.toString() + " no tienen cantidad en mano disponible. ";
            }

            pageContext.writeDiagnostics(this, 
                                         "xXGamInvDevolucionesCO - PRENDAS: " + 
                                         msgPrendas, 3);
            am.quitaPrendasSinExistencia(pageContext);
            oadbtransactionimpl.commit();
            String noSolicitud = null;
            OAFormValueBean soliIdHBean = 
                (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("SoliIdH");
            if (soliIdHBean != null) {
                noSolicitud = 
                        "UNI-" + (String)soliIdHBean.getValue(pageContext);
            }

            pageContext.writeDiagnostics(this, 
                                         "xXGamInvDevolucionesCO - noSolicitud: " + 
                                         noSolicitud, 3);
            Number status = 
                am.procesaMiscelanea(pageContext, webBean, noSolicitud);
            if (status == null) {
                status = new Number(-100);
            }

            pageContext.writeDiagnostics(this, 
                                         "xXGamInvDevolucionesCO - cod estatus: " + 
                                         status, 3);
            String msgProceso = utils.manejoErroresDev(status);
            am.setDisabledErrorRowCheckbox();
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvDevolucionesCO - " + msgProceso, 
                                         3);
            if (msgProceso.equals(" Se realizo el Proceso de forma Exitosa. ")) {
                String lSql = 
                    "BEGIN XXGAM_INV_MOVEORDER_PKG.VALIDA_SUSTITUTOS(?); END;";
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvDevolucionesCO - ejecuto XXGAM_INV_MOVEORDER_PKG.VALIDA_SUSTITUTOS", 
                                             3);
                OracleCallableStatement pstmtSust = 
                    (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(lSql, 
                                                                                         1);

                try {
                    pstmtSust.setLong(1, Long.valueOf(lSoliIdC).longValue());
                    pstmtSust.execute();
                    pstmtSust.close();
                    pageContext.writeDiagnostics(this, 
                                                 "xXGamInvDevolucionesCO - ejecuto con exito", 
                                                 3);
                } catch (SQLException var52) {
                    pageContext.writeDiagnostics(this, 
                                                 "xXGamInvDevolucionesCO - lSql Error: " + 
                                                 var52.getMessage().toString(), 
                                                 3);

                    try {
                        pstmtSust.close();
                    } catch (Exception var51) {
                        pageContext.writeDiagnostics(this, 
                                                     "xXGamInvDevolucionesCO - lSql Error: " + 
                                                     var51.getMessage().toString(), 
                                                     3);
                    }

                    throw OAException.wrapperException(var52);
                }

                pageContext.writeDiagnostics(this, 
                                             "xXGamInvDevolucionesCO - Commit al package: ", 
                                             3);
                xXGamInvSoliDtlVOImpl invSoliDtlVOImpl1 = 
                    (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
                invSoliDtlVOImpl1.clearCache();
                invSoliDtlVOImpl1.initQuery(lSoliIdC);
                am.deshabilitaTodasFilas(pageContext);
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvDevolucionesCO - reejecuta el query VO xXGamInvSoliDtlVO1", 
                                             3);
                MessageToken[] tokens = 
                    new MessageToken[] { new MessageToken("MESSAGE", 
                                                          msgPrendas + 
                                                          msgProceso) };
                OAException message = 
                    new OAException("FND", "FND_GENERIC_MESSAGE", tokens, 
                                    (byte)3, (Exception[])null);
                pageContext.putDialogMessage(message);
            } else if (msgProceso.equals("PRENDA SIN EXISTENCIA")) {
                MessageToken[] tokens1 = 
                    new MessageToken[] { new MessageToken("MESSAGE", 
                                                          msgPrendas) };
                OAException message1 = 
                    new OAException("FND", "FND_GENERIC_MESSAGE", tokens1, 
                                    (byte)0, (Exception[])null);
                pageContext.putDialogMessage(message1);
            } else if (!msgProceso.equals(" Se presento un error AL INICIAR EL CONCURRENTE en el Proceso de Devoluciones. ") && 
                       !msgProceso.equals(" El concurrente \'Process transaction interface\' termina en error (1er concurrente) en el Proceso de Devoluciones. ") && 
                       !msgProceso.equals(" El concurrente \'Inventory transaction worker\' termina en error (2do concurrente) en el Proceso de Devoluciones. ") && 
                       !msgProceso.equals(" Se presento un error en el Proceso de Devoluciones. ")) {
                am.updateWrongSoli(pageContext, webBean, 
                                   (String)soliIdHBean.getValue(pageContext));
                MessageToken[] tokens3 = 
                    new MessageToken[] { new MessageToken("MESSAGE", 
                                                          msgProceso) };
                OAException message3 = 
                    new OAException("FND", "FND_GENERIC_MESSAGE", tokens3, 
                                    (byte)0, (Exception[])null);
                pageContext.putDialogMessage(message3);
            } else {
                am.updateWrongSoli(pageContext, webBean, 
                                   (String)soliIdHBean.getValue(pageContext));
                String msg = 
                    am.obtieneMsgErrorConsurrente(pageContext, noSolicitud, 
                                                  status);
                MessageToken[] tokens2 = 
                    new MessageToken[] { new MessageToken("MESSAGE", msg) };
                OAException message2 = 
                    new OAException("FND", "FND_GENERIC_MESSAGE", tokens2, 
                                    (byte)0, (Exception[])null);
                pageContext.putDialogMessage(message2);
            }

            am.deshabilitaFilas(pageContext);
        }

        if ("generarpedido".equals(lEvent)) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudActualizaCO - Evento generarpedido", 
                                         3);
            OAFormValueBean oaStatusBean = 
                (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("StatusHDR");
            String lStatus = (String)oaStatusBean.getValue(pageContext);
            if ("CERRADO".equals(lStatus) || "PENDIENTE".equals(lStatus)) {
                String lMessageE = 
                    "La solicitud debe estar en Estado Aprobado. Estado actual: " + 
                    lStatus.toString();
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudActualizaCO - Error " + 
                                             lMessageE, 3);
                MessageToken[] tokens4 = 
                    new MessageToken[] { new MessageToken("MESSAGE", 
                                                          lMessageE) };
                throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens4);
            }

            oadbtransactionimpl.commit();
            String lSoliIdSTR = pageContext.getParameter("pgSoliId");
            new Number(0);
            xXGamInvSoliDtlVOImpl invSoliDtlVOImpl11 = 
                (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
            invSoliDtlVOImpl11.clearCache();
            invSoliDtlVOImpl11.initQuery(lSoliIdSTR);
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudActualizaCO - Llama concurrente XXGAM INV Actualizar Move Order", 
                                         3);
            Number lReqId = 
                this.callConcMoveOrder(lSoliIdSTR, "XXGAM_INV_CHANGE_MV", 
                                       "XXGAM INV Actualizar Move Order", 
                                       pageContext, webBean);
            if (lReqId.compareTo(0) <= 0) {
                String lMessageE1 = "No se ha ejecutado el concurrente.";
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudActualizaCO - error al ejecutar concurrente XXGAM INV Actualizar Move Order", 
                                             3);
                MessageToken[] tokens6 = 
                    new MessageToken[] { new MessageToken("MESSAGE", 
                                                          lMessageE1) };
                throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens6);
            }

            MessageToken[] tokens5 = 
                new MessageToken[] { new MessageToken("MESSAGE", 
                                                      "Se ejecuto la actualizacion del pedido verifique el concurrente REQUEST ID: " + 
                                                      lReqId.toString()) };
            OAException message4 = 
                new OAException("FND", "FND_GENERIC_MESSAGE", tokens5, (byte)3, 
                                (Exception[])null);
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudActualizaCO -  verifique el concurrente REQUEST ID: " + 
                                         lReqId.toString(), 3);
            pageContext.putDialogMessage(message4);
        }

        if ("EnableRow".equals(pageContext.getParameter("event"))) {
            am.setDisabledRowCheckbox();
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
        } catch (RequestSubmissionException var14) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudActualizaCO - " + var14.toString(), 
                                         3);
        } catch (Exception var15) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudActualizaCO - " + var15.toString(), 
                                         3);
        }

        return new Number(lRequestId);
    }

    public int compare(Number a, Number b) {
        return (new BigDecimal(a.toString())).compareTo(new BigDecimal(b.toString()));
    }

}
