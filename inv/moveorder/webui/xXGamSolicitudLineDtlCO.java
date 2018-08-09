package xxgam.oracle.apps.inv.moveorder.webui;

import com.sun.java.util.collections.HashMap;

import java.sql.Date;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.domain.Number;

import oracle.jdbc.OracleCallableStatement;

import xxgam.oracle.apps.inv.moveorder.server.xXGamInvLineDtlVOImpl;
import xxgam.oracle.apps.inv.moveorder.xxDebugger;


public class xXGamSolicitudLineDtlCO extends OAControllerImpl {

    public static final String RCS_ID = 
        "$Header: xXGamSolicitudLineDtlCO.java 1.1 2013/02/27 10:51:48 eroncoroni ship $";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header: xXGamSolicitudLineDtlCO.java 1.1 2013/02/27 10:51:48 eroncoroni ship $", 
                                       "%packagename%");

    String pSoliId = "";
    String pOrigen = "";

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        super.processRequest(pageContext, webBean);
        Number lUserId = new Number(0);
        Number lSoliId = new Number(0);
        new Number(0);
        String lMessageError = null;

        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        xxDebugger.oadbtransaction = oaapplicationmodule.getOADBTransaction();
        String lpSoliDtlId = pageContext.getParameter("pSoliDtlId");
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        pSoliId = pageContext.getParameter("pSoliId");
        pOrigen = pageContext.getParameter("pOrigen");
        System.out.println("Pantalla Detalle Linea : " + pSoliId);
        System.out.println("Se recibe pOrigen: " + pOrigen);

        Object lNull = null;
        String lSuperUser = oadbtransactionimpl.getProfile("PERFIL_UNIFORMES");
        String lperfil = 
            oadbtransactionimpl.getProfile("XXGAM_INV_USUARIO_UNIFORME");
        if (lperfil == null) {
            lperfil = "N";
        }

        if (lSuperUser == null) {
            lSuperUser = "N";
        }

        if (!"Y".equals(lSuperUser) || !"Y".equals(lperfil)) {
            int lUserid = oadbtransactionimpl.getUserId();
            lUserId = new Number(lUserid);
        }

        String sqlRefresh = 
            "BEGIN ? := XXGAM_INV_MOVEORDER_PKG.REFRESH_SOLICITUD(?,?,?,?,?,?,?); END; ";
        OracleCallableStatement pstmtRRSH = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(sqlRefresh, 
                                                                                 1);

        try {
            Number lSoliDtlId = new Number(lpSoliDtlId);
            pstmtRRSH.registerOutParameter(1, 12);
            pstmtRRSH.setNUMBER(2, lSoliId);
            pstmtRRSH.setNUMBER(3, lSoliDtlId);
            pstmtRRSH.setNUMBER(4, lUserId);
            pstmtRRSH.setString(5, (String)lNull);
            pstmtRRSH.setString(6, (String)lNull);
            pstmtRRSH.setDate(7, (Date)null);
            pstmtRRSH.setDate(8, (Date)null);
            pstmtRRSH.execute();
            lMessageError = pstmtRRSH.getString(1);
        } catch (Exception var25) {
            if (oadbtransactionimpl.isLoggingEnabled(4)) {
                oadbtransactionimpl.writeDiagnostics(this, 
                                                     "Exception in calling XXGAM_INV_MOVEORDER_PKG.REFRESH_MOVE_ORDER: " + 
                                                     var25.getMessage(), 4);
            }

            throw OAException.wrapperException(var25);
        } finally {
            try {
                pstmtRRSH.close();
            } catch (Exception var24) {
                throw OAException.wrapperException(var24);
            }
        }

        xXGamInvLineDtlVOImpl invSoliLineDtlVOImpl = 
            (xXGamInvLineDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvLineDtlVO1");
        invSoliLineDtlVOImpl.clearCache();
        invSoliLineDtlVOImpl.initQuery(lpSoliDtlId);
    }

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);
        String lEvent = pageContext.getParameter("event");
        if ("linkPage".equals(lEvent)) {
            System.out.println("SoliId: " + pSoliId);
            HashMap params = new HashMap();
            params.put("SoliId", pSoliId);

            if (pOrigen.equals("A")) {
                pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudActualizaPG", 
                                          (String)null, (byte)0, (String)null, 
                                          params, true, "N", (byte)99);
            } else if (pOrigen.equals("O")) {
                pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudConsultaPG", 
                                          (String)null, (byte)0, (String)null, 
                                          params, true, "N", (byte)99);
            }


        }

    }
}
