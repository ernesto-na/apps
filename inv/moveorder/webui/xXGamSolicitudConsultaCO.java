package xxgam.oracle.apps.inv.moveorder.webui;

import com.sun.java.util.collections.HashMap;

import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;

import oracle.jdbc.OracleCallableStatement;

import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliDtlVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliVOImpl;
import xxgam.oracle.apps.inv.moveorder.xxDebugger;


public class xXGamSolicitudConsultaCO extends OAControllerImpl {

    public static final String sqlNombreEmpleado = 
        "SELECT distinct ppf.full_name FROM per_all_people_f ppf,     xxgam_inv_soli xis WHERE xis.person_id = ppf.person_id  AND SYSDATE BETWEEN ppf.effective_start_date   AND ppf.effective_end_date  AND xis.soli_id = ?";
    public static final String RCS_ID = 
        "$Header: xXGamSolicitudConsultaCO.java 1.1 2013/02/27 10:51:48 eroncoroni ship $";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header: xXGamSolicitudConsultaCO.java 1.1 2013/02/27 10:51:48 eroncoroni ship $", 
                                       "%packagename%");


    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudConsultaCO - Version 1.1 - processRequest", 
                                     3);
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        xxDebugger.oadbtransaction = oaapplicationmodule.getOADBTransaction();
        String lpSoliId = pageContext.getParameter("SoliId");
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudConsultaCO - Consulta Soli Id: " + 
                                     lpSoliId, 3);
        OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();
        OAMessageStyledTextBean oanombreEmpleadoBean = 
            (OAMessageStyledTextBean)oapagelayoutbean.findIndexedChildRecursive("nombreEmpleado");
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudConsultaCO - Buscamos el nombre del empleado sqlNombreEmpleado", 
                                     3);
        OracleCallableStatement pstmt = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT distinct ppf.full_name FROM per_all_people_f ppf,     xxgam_inv_soli xis WHERE xis.person_id = ppf.person_id  AND SYSDATE BETWEEN ppf.effective_start_date   AND ppf.effective_end_date  AND xis.soli_id = ?", 
                                                                                 1);

        try {
            pstmt.setLong(1, (new Long(lpSoliId)).longValue());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String lNombreEmpleado = rs.getString(1);
                oanombreEmpleadoBean.setValue(pageContext, lNombreEmpleado);
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudConsultaCO - lNombreEmpleado: " + 
                                             lNombreEmpleado, 3);
            }
        } catch (SQLException var22) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudConsultaCO - sqlNombreEmpleado Error: " + 
                                         var22.getMessage().toString(), 3);
        } finally {
            try {
                pstmt.close();
            } catch (Exception var21) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudConsultaCO - sqlNombreEmpleado Error: " + 
                                             var21.getMessage().toString(), 3);
                throw OAException.wrapperException(var21);
            }
        }

        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudConsultaCO - Incia Consulta en VO xXGamInvSoliVO1 ", 
                                     3);
        xXGamInvSoliVOImpl invSoliVOImpl = 
            (xXGamInvSoliVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliVO1");
        invSoliVOImpl.removeRowsWithoutID();
        invSoliVOImpl.initQuery(lpSoliId);
        xXGamInvSoliDtlVOImpl invSoliDtlVOImpl = 
            (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
        invSoliDtlVOImpl.initQuery(lpSoliId);
    }

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        String lEvent = pageContext.getParameter("event");
        String lSource = pageContext.getParameter("source");
        String lDotaId = pageContext.getParameter("pDotaId");

        if ("linkPage".equals(lEvent)) {

            String SoliId = pageContext.getParameter("SoliIdH");
            System.out.println("SoliId: " + SoliId);
            HashMap params = new HashMap();
            params.put("pSoliDtlId", 
                       getSoliDtl(pageContext.getParameter("evtSrcRowRef")).toString());
            params.put("pSoliId", SoliId);
            params.put("pOrigen", "O");
            pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudLineDtlPG", 
                                      (String)null, (byte)0, (String)null, 
                                      params, true, "N", (byte)99);
        }


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
        System.out.println("p_cadena.substring(v_indice,p_cadena.length() -1).toString(): " + 
                           p_cadena.substring(v_indice, 
                                              p_cadena.length() - 1).toString());
        return p_cadena.substring(v_indice, p_cadena.length() - 1).toString();
    }


}
