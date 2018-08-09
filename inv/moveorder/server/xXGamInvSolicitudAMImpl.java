package xxgam.oracle.apps.inv.moveorder.server;

import com.sun.java.util.collections.HashMap;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OAFormValueBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageStyledTextBean;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import oracle.jdbc.OracleCallableStatement;

import xxgam.oracle.apps.inv.moveorder.lov.server.xXGamInvKitVOImpl;
import xxgam.oracle.apps.inv.moveorder.lov.server.xXGamInvKitVORowImpl;
import xxgam.oracle.apps.inv.moveorder.lov.server.xXgamInvEmpleadosVOImpl;
import xxgam.oracle.apps.inv.moveorder.lov.server.xXgamInvEmpleadosVORowImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvErrorsDevVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvErrorsDevVORowImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvFechaCreacionEnPeriodoVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvFechaCreacionEnPeriodoVORowImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvFechaNvoEmplVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvFechaNvoEmplVORowImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvFechaSolicitudActivaVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvFechaSolicitudActivaVORowImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvLineDtlVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvMotivosVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliDtlDisabledVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliDtlVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliDtlVORowImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliRenderedVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliSummaryVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliVORowImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamLookupAperPeriodVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamLookupAperPeriodVORowImpl;
import xxgam.oracle.apps.inv.moveorder.utils.XxGamInvConstantes;
import xxgam.oracle.apps.inv.moveorder.utils.XxGamInvUtils;


@SuppressWarnings( { "unchecked", "deprecation", "rawtypes", "static" })
public class xXGamInvSolicitudAMImpl extends OAApplicationModuleImpl {

    /**This is the default constructor (do not remove)
     */
    public xXGamInvSolicitudAMImpl() {
    }

    public static void main(String[] args) {
        launchTester("xxgam.oracle.apps.inv.moveorder.server", 
                     "xXGamInvSolicitudAMLocal");
    }

    public xXGamInvSoliSummaryVOImpl getxXGamInvSoliSummaryVO1() {
        return (xXGamInvSoliSummaryVOImpl)this.findViewObject("xXGamInvSoliSummaryVO1");
    }

    public xXGamInvSoliVOImpl getxXGamInvSoliVO1() {
        return (xXGamInvSoliVOImpl)this.findViewObject("xXGamInvSoliVO1");
    }

    public xXGamInvSoliDtlVOImpl getxXGamInvSoliDtlVO1() {
        return (xXGamInvSoliDtlVOImpl)this.findViewObject("xXGamInvSoliDtlVO1");
    }

    public xXGamInvLineDtlVOImpl getxXGamInvLineDtlVO1() {
        return (xXGamInvLineDtlVOImpl)this.findViewObject("xXGamInvLineDtlVO1");
    }

    public xXGamInvSoliRenderedVOImpl getxXGamInvSoliRenderedVO1() {
        return (xXGamInvSoliRenderedVOImpl)this.findViewObject("xXGamInvSoliRenderedVO1");
    }

    public xXgamInvEmpleadosVOImpl getxXgamInvEmpleadosVO1() {
        return (xXgamInvEmpleadosVOImpl)this.findViewObject("xXgamInvEmpleadosVO1");
    }

    public xXGamLookupAperPeriodVOImpl getxXGamLookupAperPeriodVO1() {
        return (xXGamLookupAperPeriodVOImpl)this.findViewObject("xXGamLookupAperPeriodVO1");
    }

    public xXGamInvMotivosVOImpl getxXGamInvMotivosVO1() {
        return (xXGamInvMotivosVOImpl)this.findViewObject("xXGamInvMotivosVO1");
    }

    public xXGamInvFechaNvoEmplVOImpl getxXGamInvFechaNvoEmplVO1() {
        return (xXGamInvFechaNvoEmplVOImpl)this.findViewObject("xXGamInvFechaNvoEmplVO1");
    }

    public xXGamInvFechaCreacionEnPeriodoVOImpl getxXGamInvFechaCreacionEnPeriodoVO1() {
        return (xXGamInvFechaCreacionEnPeriodoVOImpl)this.findViewObject("xXGamInvFechaCreacionEnPeriodoVO1");
    }

    public xXGamInvSoliDtlDisabledVOImpl getxXGamInvSoliDtlDisabledVO1() {
        return (xXGamInvSoliDtlDisabledVOImpl)this.findViewObject("xXGamInvSoliDtlDisabledVO1");
    }

    public xXGamInvFechaSolicitudActivaVOImpl getxXGamInvFechaSolicitudActivaVO1() {
        return (xXGamInvFechaSolicitudActivaVOImpl)this.findViewObject("xXGamInvFechaSolicitudActivaVO1");
    }

    public xXGamInvKitVOImpl getxXGamInvKitVO1() {
        return (xXGamInvKitVOImpl)this.findViewObject("xXGamInvKitVO1");
    }

    public xXgamInvEmpleadosVOImpl getxXgamInvEmpleadosVO2() {
        return (xXgamInvEmpleadosVOImpl)this.findViewObject("xXgamInvEmpleadosVO2");
    }

    public xXGamInvSoliDtlVOImpl getxXGamInvSoliDtlVO2() {
        return (xXGamInvSoliDtlVOImpl)this.findViewObject("xXGamInvSoliDtlVO2");
    }

    public xXGamInvSoliDtlVOImpl getxXGamInvSoliDtlVO3() {
        return (xXGamInvSoliDtlVOImpl)this.findViewObject("xXGamInvSoliDtlVO3");
    }

    public xXGamInvErrorsDevVOImpl getxXGamInvErrorsDevVO1() {
        return (xXGamInvErrorsDevVOImpl)this.findViewObject("xXGamInvErrorsDevVO1");
    }

    public xXGamInvAvailableKitsVOImpl getxXGamInvAvailableKitsVO1() {
        return (xXGamInvAvailableKitsVOImpl)findViewObject("xXGamInvAvailableKitsVO1");
    }

    public xXGamV2InvSoliDtlVOImpl getxXGamV2InvSoliDtlVO1() {
        return (xXGamV2InvSoliDtlVOImpl)findViewObject("xXGamV2InvSoliDtlVO1");
    }

    /**Container's getter for xXGamV2InvSoliVO1
     */
    public xXGamV2InvSoliVOImpl getxXGamV2InvSoliVO1() {
        return (xXGamV2InvSoliVOImpl)findViewObject("xXGamV2InvSoliVO1");
    }

    public void setRenderOptions(String attributeName, boolean option) {
        System.out.println("Inicia proceso - setRenderOptions");
        System.out.println("attributeName: " + attributeName);
        System.out.println("option: " + option);

        xXGamInvSoliRenderedVOImpl renderImpl = 
            this.getxXGamInvSoliRenderedVO1();

        Row renderRow = null;

        if (renderImpl.getEstimatedRowCount() > 0L) {
            renderRow = renderImpl.first();
        } else {
            renderRow = renderImpl.createRow();
            renderImpl.setMaxFetchSize(0);
            renderRow.setNewRowState((byte)0);
        }

        renderRow.setAttribute(attributeName, Boolean.valueOf(option));
        renderImpl.insertRow(renderRow);
    }

    public void deshabilitaFilas(OAPageContext pageContext) {
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - deshabilita lineas no Cerradas ", 
                                     3);
        xXGamInvSoliDtlVOImpl soliDtlImpl = this.getxXGamInvSoliDtlVO1();
        String status = null;
        RowSetIterator iterator = null;
        Date fechaEntrega = null;
        Number qtyEntregada = null;
        iterator = soliDtlImpl.createRowSetIterator((String)null);
        xXGamInvSoliDtlVORowImpl rowImpl = null;

        try {
            if (iterator == null || iterator.getRowCount() == 0) {
                iterator.closeRowSetIterator();
                return;
            }

            while (iterator.hasNext()) {
                rowImpl = (xXGamInvSoliDtlVORowImpl)iterator.next();
                if (rowImpl != null) {
                    status = rowImpl.getStatusDsp();
                    fechaEntrega = rowImpl.getLastDelivDate();
                    qtyEntregada = rowImpl.getQuantityDelivered();
                    if (qtyEntregada == null) {
                        qtyEntregada = new Number(-1);
                    }

                    if ("CERRADO".equals(status.toUpperCase()) && 
                        qtyEntregada.compareTo(0) > 0) {
                        rowImpl.setCheckBoxDisabled(Boolean.valueOf(false));
                        rowImpl.setMotivoDisabled(Boolean.valueOf(true));
                        rowImpl.setTallaDevDisabled(Boolean.valueOf(true));
                        rowImpl.setCantidadDevDisabled(Boolean.valueOf(true));
                        rowImpl.setObservacionesDevDisabled(Boolean.valueOf(true));
                    } else {
                        rowImpl.setCheckBoxDisabled(Boolean.valueOf(true));
                        rowImpl.setMotivoDisabled(Boolean.valueOf(true));
                        rowImpl.setTallaDevDisabled(Boolean.valueOf(true));
                        rowImpl.setCantidadDevDisabled(Boolean.valueOf(true));
                        rowImpl.setObservacionesDevDisabled(Boolean.valueOf(true));
                    }
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            iterator.closeRowSetIterator();
        }

    }

    public void setDisabledRowCheckbox() {
        xXGamInvSoliDtlVOImpl vo = this.getxXGamInvSoliDtlVO1();
        Row[] row = vo.getAllRowsInRange();

        for (int inc = 0; inc < row.length; ++inc) {
            xXGamInvSoliDtlVORowImpl rowi = (xXGamInvSoliDtlVORowImpl)row[inc];
            if (rowi.getCheckBoxSelected() != null && 
                rowi.getCheckBoxSelected().equals("Y")) {
                rowi.setMotivoDisabled(Boolean.FALSE);
                rowi.setCantidadDevDisabled(Boolean.FALSE);
                rowi.setTallaDevDisabled(Boolean.FALSE);
                rowi.setObservacionesDevDisabled(Boolean.FALSE);
            } else {
                rowi.setMotivoDisabled(Boolean.TRUE);
                rowi.setCantidadDevDisabled(Boolean.TRUE);
                rowi.setTallaDevDisabled(Boolean.TRUE);
                rowi.setObservacionesDevDisabled(Boolean.TRUE);
                rowi.setObservacionesDev((String)null);
                rowi.setQtyDev((Number)null);
                rowi.setTallaNbrDev((String)null);
                rowi.setMotivo((String)null);
            }
        }

    }

    public void setDisabledErrorRowCheckbox() {
        xXGamInvSoliDtlVOImpl vo = this.getxXGamInvSoliDtlVO1();
        Row[] row = vo.getAllRowsInRange();

        for (int inc = 0; inc < row.length; ++inc) {
            xXGamInvSoliDtlVORowImpl rowi = (xXGamInvSoliDtlVORowImpl)row[inc];
            if (rowi.getCheckBoxSelected() != null && 
                rowi.getCheckBoxSelected().equals("Y")) {
                rowi.setCheckBoxSelected("N");
                rowi.setMotivoDisabled(Boolean.TRUE);
                rowi.setCantidadDevDisabled(Boolean.TRUE);
                rowi.setTallaDevDisabled(Boolean.TRUE);
                rowi.setObservacionesDevDisabled(Boolean.TRUE);
                rowi.setObservacionesDev((String)null);
                rowi.setQtyDev((Number)null);
                rowi.setTallaNbrDev((String)null);
                rowi.setMotivo((String)null);
            }
        }

    }

    public boolean buscaEmpleadoPorClave(String cveEmpl) {
        boolean isEmplValido = false;
        isEmplValido = 
                this.getxXgamInvEmpleadosVO1().buscaEmpleadoPorClave(cveEmpl);
        return isEmplValido;
    }

    public String buscaEmpleadoPorUserID(Number lUserId) {
        String cveEmpleado = "";
        cveEmpleado = 
                this.getxXgamInvEmpleadosVO1().buscaEmpleadoPorUserID(lUserId);
        return cveEmpleado;
    }

    public String buscaCuentaPorClaveEmpl(String cveEmpl) {
        String ctaempl = null;
        ctaempl = 
                this.getxXgamInvEmpleadosVO1().buscaCuentaPorClaveEmpl(cveEmpl);
        return ctaempl;
    }

    public String obtieneCompania(OAPageContext pageContext, OAWebBean webBean, 
                                  String sPersonId) {
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - ejecuto XXGAM_INV_DEV_PROCESS_PKG.xxgam_get_compania", 
                                     3);
        String compania = null;
        Number personId = null;

        try {
            personId = new Number(sPersonId);
        } catch (Exception var15) {
            var15.printStackTrace();
        }

        String str = 
            " BEGIN ? :=  XXGAM_INV_DEV_PROCESS_PKG.xxgam_get_compania(?); END; ";
        OracleCallableStatement oraclecallablestatement = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(str, 
                                                                                 1);

        try {
            oraclecallablestatement.registerOutParameter(1, 12);
            oraclecallablestatement.setNUMBER(2, personId);
            oraclecallablestatement.execute();
            compania = oraclecallablestatement.getString(1);
            oraclecallablestatement.close();
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - ejecuto con exito", 
                                         3);
        } catch (Exception var14) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                         var14.getMessage().toString(), 3);

            try {
                oraclecallablestatement.close();
            } catch (Exception var13) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                             var13.getMessage().toString(), 3);
            }

            throw OAException.wrapperException(var14);
        }

        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - Commit al package: ", 
                                     3);
        return compania;
    }

    public String obtieneSubInventario(OAPageContext pageContext, 
                                       OAWebBean webBean, String compania) {
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - ejecuto XXGAM_INV_DEV_PROCESS_PKG.xxgam_get_subinventory", 
                                     3);
        String subInventario = null;
        String str = 
            " BEGIN ? :=  XXGAM_INV_DEV_PROCESS_PKG.xxgam_get_subinventory(?); END; ";
        OracleCallableStatement oraclecallablestatement = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(str, 
                                                                                 1);

        try {
            oraclecallablestatement.registerOutParameter(1, 12);
            oraclecallablestatement.setString(2, compania);
            oraclecallablestatement.execute();
            subInventario = oraclecallablestatement.getString(1);
            oraclecallablestatement.close();
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - ejecuto con exito", 
                                         3);
        } catch (Exception var12) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                         var12.getMessage().toString(), 3);

            try {
                oraclecallablestatement.close();
            } catch (Exception var11) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                             var11.getMessage().toString(), 3);
            }

            throw OAException.wrapperException(var12);
        }

        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - Commit al package: ", 
                                     3);
        return subInventario;
    }

    public Number obtieneOrganizationId(OAPageContext pageContext, 
                                        OAWebBean webBean, String compania) {
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - ejecuto XXGAM_INV_DEV_PROCESS_PKG.xxgam_get_organization_id", 
                                     3);
        Number orgId = null;
        String str = 
            " BEGIN ? :=  XXGAM_INV_DEV_PROCESS_PKG.xxgam_get_organization_id(?); END; ";
        OracleCallableStatement oraclecallablestatement = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(str, 
                                                                                 1);

        try {
            oraclecallablestatement.registerOutParameter(1, 2);
            oraclecallablestatement.setString(2, compania);
            oraclecallablestatement.execute();
            orgId = new Number(oraclecallablestatement.getNUMBER(1));
            oraclecallablestatement.close();
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - ejecuto con exito", 
                                         3);
        } catch (Exception var12) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                         var12.getMessage().toString(), 3);

            try {
                oraclecallablestatement.close();
            } catch (Exception var11) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                             var11.getMessage().toString(), 3);
            }

            throw OAException.wrapperException(var12);
        }

        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - Commit al package: ", 
                                     3);
        return orgId;
    }

    @SuppressWarnings("deprecation")
    public List validaExistencia(OAPageContext pageContext, OAWebBean webBean, 
                                 Number orgId, String subinventario, 
                                 String lSoliIdC, String compania) {
        pageContext.writeDiagnostics(this, 
                                     "validaExistencia - soli_id: " + lSoliIdC, 
                                     3);
        xXGamInvSoliDtlVOImpl newLinesVO = this.getxXGamInvSoliDtlVO3();
        newLinesVO.initQuery(lSoliIdC);
        RowSetIterator iterNewLines = null;
        xXGamInvSoliDtlVORowImpl rowNewLines = null;
        xXGamInvSoliDtlVOImpl soliDtlImpl = this.getxXGamInvSoliDtlVO1();
        RowSetIterator iterator = null;
        iterator = soliDtlImpl.createRowSetIterator((String)null);
        xXGamInvSoliDtlVORowImpl rowImpl = null;
        ArrayList listanoNombreSinExistencia = new ArrayList();
        ArrayList listaIdSinExistencia = new ArrayList();
        Number inventoryId = null;
        Number existencia = null;
        Number soliDtlId = null;
        String nomenclatura = null;
        Number qtyDev = null;

        try {
            if (soliDtlImpl != null && iterator != null && 
                iterator.getRowCount() > 0) {
                while (iterator.hasNext()) {
                    rowImpl = (xXGamInvSoliDtlVORowImpl)iterator.next();
                    soliDtlId = rowImpl.getSoliDtlId();
                    if (rowImpl != null && 
                        rowImpl.getCheckBoxSelected() != null && 
                        "Y".equals(rowImpl.getCheckBoxSelected())) {
                        iterNewLines = 
                                newLinesVO.createRowSetIterator((String)null);

                        try {
                            if (iterNewLines != null && 
                                iterNewLines.getRowCount() > 0) {
                                while (iterNewLines.hasNext()) {
                                    rowNewLines = 
                                            (xXGamInvSoliDtlVORowImpl)iterNewLines.next();
                                    if (rowNewLines != null && 
                                        soliDtlId.compareTo(rowNewLines.getSoliDtlId()) == 
                                        0) {
                                        nomenclatura = 
                                                rowNewLines.getNomenclature();
                                        inventoryId = 
                                                rowNewLines.getInventoryIdDev();
                                        soliDtlId = rowNewLines.getSoliDtlId();
                                        qtyDev = rowNewLines.getQtyDev();
                                        if (inventoryId != null) {
                                            existencia = 
                                                    this.obtieneExistencias(pageContext, 
                                                                            webBean, 
                                                                            orgId, 
                                                                            inventoryId, 
                                                                            subinventario, 
                                                                            compania);
                                            pageContext.writeDiagnostics(this, 
                                                                         nomenclatura + 
                                                                         ": " + 
                                                                         existencia, 
                                                                         3);
                                        }

                                        if (existencia != null && 
                                            existencia.compareTo(qtyDev) < 0) {
                                            listanoNombreSinExistencia.add(nomenclatura);
                                            listaIdSinExistencia.add(soliDtlId);
                                        }
                                    }
                                }
                            }
                        } catch (Exception var32) {
                            var32.printStackTrace();
                        } finally {
                            iterNewLines.closeRowSetIterator();
                        }
                    }
                }
            }
        } catch (Exception var34) {
            var34.printStackTrace();
        } finally {
            iterator.closeRowSetIterator();
        }

        pageContext.putSessionValueDirect("listaIdSinExistencia", 
                                          listaIdSinExistencia);
        return listanoNombreSinExistencia;
    }

    public Number obtieneExistencias(OAPageContext pageContext, 
                                     OAWebBean webBean, Number orgId, 
                                     Number inventoryId, String subinventario, 
                                     String compania) {
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - ejecuto XXGAM_INV_DEV_PROCESS_PKG.xxgam_valida_onhand_string", 
                                     3);
        Number existencia = null;
        String str = 
            " BEGIN ? :=  XXGAM_INV_DEV_PROCESS_PKG.xxgam_valida_onhand_string(?,?,?,?); END; ";
        OracleCallableStatement oraclecallablestatement = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(str, 
                                                                                 1);

        try {
            oraclecallablestatement.registerOutParameter(1, 2);
            oraclecallablestatement.setNUMBER(2, orgId);
            oraclecallablestatement.setNUMBER(3, inventoryId);
            oraclecallablestatement.setString(4, subinventario);
            oraclecallablestatement.setString(5, compania);
            oraclecallablestatement.execute();
            existencia = new Number(oraclecallablestatement.getNUMBER(1));
            oraclecallablestatement.close();
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - ejecuto con exito", 
                                         3);
        } catch (Exception var15) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                         var15.getMessage().toString(), 3);

            try {
                oraclecallablestatement.close();
            } catch (Exception var14) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                             var14.getMessage().toString(), 3);
            }

            throw OAException.wrapperException(var15);
        }

        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - Commit al package: ", 
                                     3);
        return existencia;
    }

    public Number procesaMiscelanea(OAPageContext pageContext, 
                                    OAWebBean webBean, String uniId) {
        xXGamInvSoliDtlVOImpl soliDtlImpl = this.getxXGamInvSoliDtlVO1();
        RowSetIterator iterator = null;
        iterator = soliDtlImpl.createRowSetIterator((String)null);
        xXGamInvSoliDtlVORowImpl rowImpl = null;
        Number status = new Number(100);
        boolean vacio = true;

        try {
            if (soliDtlImpl != null && iterator != null && 
                iterator.getRowCount() > 0) {
                while (iterator.hasNext()) {
                    rowImpl = (xXGamInvSoliDtlVORowImpl)iterator.next();
                    if (rowImpl != null && rowImpl.getMotivo() != null) {
                        vacio = false;
                        status = new Number(100);
                    }
                }
            }
        } catch (Exception var23) {
            var23.printStackTrace();
        } finally {
            iterator.closeRowSetIterator();
        }

        if (!vacio) {
            OAApplicationModule oaapplicationmodule = 
                pageContext.getApplicationModule(webBean);
            OADBTransactionImpl oadbtransactionimpl = 
                (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - ejecuto XXGAM_INV_DEV_PROCESS_PKG.xxgam_create_miscellaneous", 
                                         3);
            String str = 
                " BEGIN XXGAM_INV_DEV_PROCESS_PKG.xxgam_create_miscellaneous(:1,:2); END; ";
            OracleCallableStatement oraclecallablestatement = 
                (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(str, 
                                                                                     1);

            try {
                oraclecallablestatement.setString(1, uniId);
                oraclecallablestatement.registerOutParameter(2, 2);
                oraclecallablestatement.execute();
                status = new Number(oraclecallablestatement.getNUMBER(2));
                oraclecallablestatement.close();
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvSolicitudAMImpl - ejecuto con exito", 
                                             3);
            } catch (Exception var22) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                             var22.getMessage().toString(), 3);

                try {
                    oraclecallablestatement.close();
                } catch (Exception var21) {
                    pageContext.writeDiagnostics(this, 
                                                 "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                                 var21.getMessage().toString(), 
                                                 3);
                }

                throw OAException.wrapperException(var22);
            }
        }

        return status;
    }

    public String validaAperturaPeriodos(String lKitCode) {
        xXGamLookupAperPeriodVOImpl lookupVOImpl = 
            this.getxXGamLookupAperPeriodVO1();
        lookupVOImpl.clearCache();
        lookupVOImpl.validaAperturaPeriodo(lKitCode);
        String statusPeriodo = "NO ENCONTRADO";
        if (lookupVOImpl != null) {
            xXGamLookupAperPeriodVORowImpl row = 
                (xXGamLookupAperPeriodVORowImpl)lookupVOImpl.first();
            if (row != null) {
                statusPeriodo = row.getStatusPeriodo();
            }
        }

        return statusPeriodo;
    }

    public boolean validaNuevoEmpleado(Number personId, long lKitId) {
        xXGamInvFechaNvoEmplVOImpl fechaNvoEmplImp = 
            this.getxXGamInvFechaNvoEmplVO1();
        fechaNvoEmplImp.clearCache();
        fechaNvoEmplImp.validaNuevoEmpleado(personId, lKitId);
        boolean esNvoEmpl = false;
        if (fechaNvoEmplImp != null) {
            xXGamInvFechaNvoEmplVORowImpl row = 
                (xXGamInvFechaNvoEmplVORowImpl)fechaNvoEmplImp.first();
            if (row != null) {
                esNvoEmpl = true;
            }
        }

        return esNvoEmpl;
    }

    public String comparaFechaCreacion(String lpSoliId) {
        xXGamInvFechaCreacionEnPeriodoVOImpl fechaCreacionVOImpl = 
            this.getxXGamInvFechaCreacionEnPeriodoVO1();
        fechaCreacionVOImpl.clearCache();
        fechaCreacionVOImpl.validaFechaEnRango(lpSoliId);
        String esValido = "SI";
        if (fechaCreacionVOImpl != null) {
            xXGamInvFechaCreacionEnPeriodoVORowImpl row = 
                (xXGamInvFechaCreacionEnPeriodoVORowImpl)fechaCreacionVOImpl.first();
            if (row != null) {
                esValido = row.getEsValido();
            }
        }

        return esValido;
    }

    public String comparaFechaCreacionUser(Number personId) {
        xXGamInvFechaCreacionEnPeriodoVOImpl fechaCreacionVOImpl = 
            this.getxXGamInvFechaCreacionEnPeriodoVO1();
        fechaCreacionVOImpl.clearCache();
        fechaCreacionVOImpl.validaFechaEnRangoPersonId(personId);
        String esValido = "SI";
        if (fechaCreacionVOImpl != null) {
            xXGamInvFechaCreacionEnPeriodoVORowImpl row = 
                (xXGamInvFechaCreacionEnPeriodoVORowImpl)fechaCreacionVOImpl.first();
            if (row != null) {
                esValido = row.getEsValido();
            }
        }

        return esValido;
    }

    public void setDisabledSoli(String attributeName, boolean option) {
        xXGamInvSoliDtlDisabledVOImpl renderImpl = 
            this.getxXGamInvSoliDtlDisabledVO1();
        Row renderRow = null;
        if (renderImpl.getEstimatedRowCount() > 0L) {
            renderRow = renderImpl.first();
        } else {
            renderRow = renderImpl.createRow();
            renderImpl.setMaxFetchSize(0);
            renderRow.setNewRowState((byte)0);
        }

        renderRow.setAttribute(attributeName, Boolean.valueOf(option));
        renderImpl.insertRow(renderRow);
    }

    public String validaLineas(OAPageContext pageContext, OAWebBean webBean, 
                               String lSuperUser, String numSoli) {
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - method validaLineas", 
                                     3);
        String esValid = "NO";
        if (lSuperUser != null && "Y".equals(lSuperUser.trim()) && 
            numSoli != null) {
            esValid = 
                    this.validaLineasCerradoAprobado(pageContext, webBean, numSoli);
        }

        return esValid;
    }

    public String validaLineasCerradoAprobado(OAPageContext pageContext, 
                                              OAWebBean webBean, 
                                              String noSolicitud) {
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - ejecuto XXGAM_INV_DEV_PROCESS_PKG.", 
                                     3);
        String isValid = null;
        String str = 
            " BEGIN ? :=  XXGAM_INV_DEV_PROCESS_PKG.xxgam_valida_devolucion(?); END; ";
        OracleCallableStatement oraclecallablestatement = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(str, 
                                                                                 1);

        try {
            oraclecallablestatement.registerOutParameter(1, 12);
            oraclecallablestatement.setString(2, noSolicitud);
            oraclecallablestatement.execute();
            isValid = oraclecallablestatement.getString(1);
            oraclecallablestatement.close();
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - ejecuto con exito", 
                                         3);
        } catch (Exception var12) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                         var12.getMessage().toString(), 3);

            try {
                oraclecallablestatement.close();
            } catch (Exception var11) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                             var11.getMessage().toString(), 3);
            }

            throw OAException.wrapperException(var12);
        }

        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - Commit al package: ", 
                                     3);
        return isValid;
    }

    public Number validaAsigAutoKit(OAPageContext pageContext, 
                                    OAWebBean webBean, String sPersonId) {
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - ejecuto xxgam_inv_kit_util_pkg.xxgam_get_kit_auto", 
                                     3);
        Number idKit = null;
        Number personId = null;

        try {
            personId = new Number(sPersonId);
        } catch (Exception var15) {
            var15.printStackTrace();
        }

        String str = 
            " BEGIN ? :=  xxgam_inv_kit_util_pkg.xxgam_get_kit_auto(?); END; ";
        OracleCallableStatement oraclecallablestatement = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(str, 
                                                                                 1);

        try {
            oraclecallablestatement.registerOutParameter(1, 2);
            oraclecallablestatement.setNUMBER(2, personId);
            oraclecallablestatement.execute();
            idKit = new Number(oraclecallablestatement.getNUMBER(1));
            oraclecallablestatement.close();
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - ejecuto con exito. idkit: " + 
                                         idKit, 3);
            return idKit;
        } catch (Exception var14) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                         var14.getMessage().toString(), 3);

            try {
                oraclecallablestatement.close();
            } catch (Exception var13) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                             var13.getMessage().toString(), 3);
            }

            throw OAException.wrapperException(var14);
        }
    }

    public String validaSolicitudFechaActiva(Number personId) {
        xXGamInvFechaSolicitudActivaVOImpl solActivaVOImpl = 
            this.getxXGamInvFechaSolicitudActivaVO1();
        solActivaVOImpl.clearCache();
        solActivaVOImpl.validaSolicitudFechaActiva(personId);
        String estaActiva = null;
        if (solActivaVOImpl != null) {
            xXGamInvFechaSolicitudActivaVORowImpl row = 
                (xXGamInvFechaSolicitudActivaVORowImpl)solActivaVOImpl.first();
            if (row != null) {
                estaActiva = row.getStatus();
            }
        }

        return estaActiva;
    }

    public void validaCicloConFechaEntrega(OAPageContext pageContext, 
                                           OAWebBean webBean) {
        System.out.println("Inicia proceso para validaCicloConFechaEntrega, habilita y deshabilita campos.");
        xXGamInvSoliDtlVOImpl solActivaVOImpl = this.getxXGamInvSoliDtlVO1();
        xXGamInvSoliVOImpl invSoliVOImpl = this.getxXGamInvSoliVO1();
        String ciclo = null;
        Number dotaId = null;
        Number kitId = null;
        Number esValido = null;
        String cveEmpl = null;
        if (invSoliVOImpl != null) {
            RowSetIterator iterator = null;
            iterator = invSoliVOImpl.createRowSetIterator((String)null);
            xXGamInvSoliVORowImpl rowImpl = null;

            try {
                if (iterator == null || iterator.getRowCount() == 0) {
                    iterator.closeRowSetIterator();
                    return;
                }

                while (iterator.hasNext()) {
                    rowImpl = (xXGamInvSoliVORowImpl)iterator.next();
                    if (rowImpl != null) {
                        cveEmpl = rowImpl.getEmployeeNumber();
                        kitId = rowImpl.getKitId();
                    }
                }
            } catch (Exception var28) {
                var28.printStackTrace();
            } finally {
                iterator.closeRowSetIterator();
            }
        }

        if (solActivaVOImpl != null) {
            RowSetIterator iterator1 = null;
            iterator1 = solActivaVOImpl.createRowSetIterator((String)null);
            xXGamInvSoliDtlVORowImpl rowImpl1 = null;

            try {
                if (iterator1 == null || iterator1.getRowCount() == 0) {
                    iterator1.closeRowSetIterator();
                    return;
                }

                while (iterator1.hasNext()) {
                    rowImpl1 = (xXGamInvSoliDtlVORowImpl)iterator1.next();
                    if (rowImpl1 != null) {
                        dotaId = rowImpl1.getDotaId();
                        ciclo = rowImpl1.getCycleCod();
                        esValido = 
                                this.validaCiclo(pageContext, webBean, dotaId, 
                                                 cveEmpl, kitId, ciclo);
                        System.out.println("Nomenclature: " + 
                                           rowImpl1.getNomenclature().toString() + 
                                           " esValido : " + esValido);
                        System.out.println("dotaId: " + dotaId);
                        System.out.println("cveEmpl: " + cveEmpl);
                        System.out.println("kitId: " + kitId);
                        System.out.println("ciclo: " + ciclo);
                        System.out.println("esValido: " + esValido);

                        if (esValido.compareTo(1) == 0) {
                            rowImpl1.setObservacionesDevDisabled(Boolean.valueOf(false));
                            rowImpl1.setTallaDevDisabled(Boolean.valueOf(false));
                            rowImpl1.setCantidadDevDisabled(Boolean.valueOf(false));
                        } else {
                            rowImpl1.setObservacionesDevDisabled(Boolean.valueOf(true));
                            rowImpl1.setTallaDevDisabled(Boolean.valueOf(true));
                            rowImpl1.setCantidadDevDisabled(Boolean.valueOf(true));
                        }
                    }
                }
            } catch (Exception var26) {
                var26.printStackTrace();
            } finally {
                iterator1.closeRowSetIterator();
            }
        }

    }

    public Number validaCiclo(OAPageContext pageContext, OAWebBean webBean, 
                              Number dotaId, String cveEmpl, Number kitId, 
                              String ciclo) {
        System.out.println("Inicia validaCiclo, se recibe: ");
        System.out.println("dotaId: " + dotaId);
        System.out.println("cveEmpl: " + cveEmpl);
        System.out.println("kitId: " + kitId);
        System.out.println("ciclo: " + ciclo);

        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - ejecuto xxgam_inv_kit_util_pkg.validate_cicle", 
                                     3);
        Number esValido = null;
        String str = 
            " BEGIN ? :=  xxgam_inv_kit_util_pkg.validate_cicle(?,?,?,?); END; ";
        OracleCallableStatement oraclecallablestatement = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(str, 
                                                                                 1);

        try {
            oraclecallablestatement.registerOutParameter(1, 2);
            oraclecallablestatement.setNUMBER(2, dotaId);
            oraclecallablestatement.setString(3, cveEmpl);
            oraclecallablestatement.setNUMBER(4, kitId);
            oraclecallablestatement.setString(5, ciclo);
            oraclecallablestatement.execute();
            esValido = new Number(oraclecallablestatement.getNUMBER(1));
            oraclecallablestatement.close();
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - ejecuto con exito", 
                                         3);
        } catch (Exception var15) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                         var15.getMessage().toString(), 3);

            try {
                oraclecallablestatement.close();
            } catch (Exception var14) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                             var14.getMessage().toString(), 3);
            }

            throw OAException.wrapperException(var15);
        }

        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - Commit al package: ", 
                                     3);
        return esValido;
    }

    public void deshabilitaTodasLineasActializacion(boolean valor) {
        xXGamInvSoliDtlVOImpl solActivaVOImpl = this.getxXGamInvSoliDtlVO1();
        if (solActivaVOImpl != null) {
            RowSetIterator iterator = null;
            iterator = solActivaVOImpl.createRowSetIterator((String)null);
            xXGamInvSoliDtlVORowImpl rowImpl = null;

            try {
                if (iterator == null || iterator.getRowCount() == 0) {
                    iterator.closeRowSetIterator();
                    return;
                }

                while (iterator.hasNext()) {
                    rowImpl = (xXGamInvSoliDtlVORowImpl)iterator.next();
                    if (rowImpl != null) {
                        rowImpl.setObservacionesDevDisabled(Boolean.valueOf(valor));
                        rowImpl.setTallaDevDisabled(Boolean.valueOf(valor));
                        rowImpl.setCantidadDevDisabled(Boolean.valueOf(valor));
                    }
                }
            } catch (Exception var9) {
                var9.printStackTrace();
            } finally {
                iterator.closeRowSetIterator();
            }
        }

    }

    public void quitaPrendasSinExistencia(OAPageContext pageContext) {
        xXGamInvSoliDtlVOImpl soliDtlImpl = this.getxXGamInvSoliDtlVO1();
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - quitaPrendasSinExistencia", 
                                     3);
        List listaIdSinExistencia = 
            (List)pageContext.getSessionValueDirect("listaIdSinExistencia");
        RowSetIterator iterator = null;
        iterator = soliDtlImpl.createRowSetIterator((String)null);
        xXGamInvSoliDtlVORowImpl rowImpl = null;
        Iterator iterSinExistencia = null;
        Number soliDtlId = null;

        try {
            if (iterator != null && iterator.getRowCount() != 0) {
                while (iterator.hasNext()) {
                    rowImpl = (xXGamInvSoliDtlVORowImpl)iterator.next();
                    if (rowImpl != null && rowImpl.getSoliDtlId() != null) {
                        iterSinExistencia = listaIdSinExistencia.iterator();

                        while (iterSinExistencia.hasNext()) {
                            soliDtlId = (Number)iterSinExistencia.next();
                            if (rowImpl.getSoliDtlId().compareTo(soliDtlId) == 
                                0) {
                                pageContext.writeDiagnostics(this, 
                                                             "quitaPrendasSinExistencia - SOLI_DTL_ID " + 
                                                             soliDtlId, 3);
                                rowImpl.setMotivo((String)null);
                                rowImpl.setObservacionesDev((String)null);
                                rowImpl.setQtyDev((Number)null);
                                rowImpl.setTallaDevId((Number)null);
                                rowImpl.setTallaNbrDev((String)null);
                            }
                        }
                    }
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            iterator.closeRowSetIterator();
        }

    }

    public HashMap obtieneKit(Number idKit) {
        xXGamInvKitVOImpl kitVO = this.getxXGamInvKitVO1();
        kitVO.clearCache();
        kitVO.obtieneKit(idKit);
        HashMap params = new HashMap();
        if (kitVO != null) {
            xXGamInvKitVORowImpl row = (xXGamInvKitVORowImpl)kitVO.first();
            if (row != null) {
                params.put("kidId", row.getKitId().toString());
                params.put("kidCod", row.getKitCod());
                params.put("kidDesc", row.getKitDes());
            }
        }

        return params;
    }

    public Number obtienePersonId(int userId) {
        Number personId = null;
        xXgamInvEmpleadosVOImpl emplVO = this.getxXgamInvEmpleadosVO2();
        emplVO.clearCache();
        Number idUser = new Number(userId);
        emplVO.obtienePersonId(idUser);
        if (emplVO != null) {
            xXgamInvEmpleadosVORowImpl row = 
                (xXgamInvEmpleadosVORowImpl)emplVO.first();
            if (row != null) {
                personId = row.getPersonId();
            }
        }

        return personId;
    }

    public String obtieneEmployeeNumber(int userId) {
        String employeeNumber = "-1";
        xXgamInvEmpleadosVOImpl emplVO = this.getxXgamInvEmpleadosVO2();
        emplVO.clearCache();
        Number idUser = new Number(userId);
        emplVO.obtienePersonId(idUser);
        if (emplVO != null) {
            xXgamInvEmpleadosVORowImpl row = 
                (xXgamInvEmpleadosVORowImpl)emplVO.first();
            if (row != null) {
                try {
                    if (row.getClaveEmpleado().toString().equals("")) {
                        employeeNumber = "-1";
                    } else {
                        employeeNumber = row.getClaveEmpleado();
                    }

                } catch (Exception e) {
                    employeeNumber = "-1";
                }

            }
        }

        System.out.println("obtieneEmployeeNumber retorna:  " + 
                           employeeNumber);
        return employeeNumber;
    }

    public int getPersonId(int userId) {
        int personId = 0;
        xXgamInvEmpleadosVOImpl emplVO = this.getxXgamInvEmpleadosVO2();
        emplVO.clearCache();
        Number idUser = new Number(userId);
        emplVO.obtienePersonId(idUser);

        if (emplVO != null) {
            xXgamInvEmpleadosVORowImpl row = 
                (xXgamInvEmpleadosVORowImpl)emplVO.first();
            if (row != null) {
                personId = Integer.parseInt(row.getPersonId().toString());
            }
        }

        return personId;
    }

    public void deshabilitaTodasFilas(OAPageContext pageContext) {
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - deshabilita todas lineas ", 
                                     3);
        xXGamInvSoliDtlVOImpl soliDtlImpl = this.getxXGamInvSoliDtlVO1();
        RowSetIterator iterator = null;
        iterator = soliDtlImpl.createRowSetIterator((String)null);
        xXGamInvSoliDtlVORowImpl rowImpl = null;

        try {
            if (iterator == null || iterator.getRowCount() == 0) {
                iterator.closeRowSetIterator();
                return;
            }

            while (iterator.hasNext()) {
                rowImpl = (xXGamInvSoliDtlVORowImpl)iterator.next();
                if (rowImpl != null) {
                    rowImpl.setMotivoDisabled(Boolean.valueOf(true));
                    rowImpl.setTallaDevDisabled(Boolean.valueOf(true));
                    rowImpl.setCantidadDevDisabled(Boolean.valueOf(true));
                    rowImpl.setObservacionesDevDisabled(Boolean.valueOf(true));
                }
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        } finally {
            iterator.closeRowSetIterator();
        }

    }

    public String validaStatusSolicitud(String lpSoliId) {
        xXGamInvSoliVOImpl soliImpl = this.getxXGamInvSoliVO1();
        soliImpl.clearCache();
        String statusSoli = soliImpl.obtieneStatusporNroSolicitud(lpSoliId);
        xXGamInvSoliDtlVOImpl soliDtlImpl = this.getxXGamInvSoliDtlVO2();
        soliDtlImpl.clearCache();
        soliDtlImpl.initQuery(lpSoliId);
        RowSetIterator iterator = null;
        iterator = soliDtlImpl.createRowSetIterator((String)null);
        xXGamInvSoliDtlVORowImpl rowImpl = null;

        try {
            if (iterator == null || iterator.getRowCount() == 0) {
                Object var8 = null;
                iterator.closeRowSetIterator();
                return (String)var8;
            }

            while (iterator.hasNext()) {
                rowImpl = (xXGamInvSoliDtlVORowImpl)iterator.next();
                if (rowImpl != null) {
                    if (!"ERROR".equals(statusSoli.toUpperCase()) && 
                        !"CANCELADO".equals(statusSoli.toUpperCase())) {
                        rowImpl.setMotivoDisabled(Boolean.valueOf(false));
                        rowImpl.setTallaDevDisabled(Boolean.valueOf(false));
                        rowImpl.setCantidadDevDisabled(Boolean.valueOf(false));
                        rowImpl.setObservacionesDevDisabled(Boolean.valueOf(false));
                    } else {
                        rowImpl.setMotivoDisabled(Boolean.valueOf(true));
                        rowImpl.setTallaDevDisabled(Boolean.valueOf(true));
                        rowImpl.setCantidadDevDisabled(Boolean.valueOf(true));
                        rowImpl.setObservacionesDevDisabled(Boolean.valueOf(true));
                    }
                }
            }
        } catch (Exception var12) {
            var12.printStackTrace();
        } finally {
            iterator.closeRowSetIterator();
        }

        return statusSoli;
    }

    public Number updateWrongSoli(OAPageContext pageContext, OAWebBean webBean, 
                                  String noSolicitud) {
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - ejecuto XXGAM_INV_DEV_PROCESS_PKG.xxgam_update_wrong_solititud", 
                                     3);
        Number status = null;
        String str = 
            " BEGIN ? :=  XXGAM_INV_DEV_PROCESS_PKG.xxgam_update_wrong_solititud(?); END; ";
        OracleCallableStatement oraclecallablestatement = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(str, 
                                                                                 1);

        try {
            oraclecallablestatement.registerOutParameter(1, 2);
            oraclecallablestatement.setString(2, noSolicitud);
            oraclecallablestatement.execute();
            status = new Number(oraclecallablestatement.getNUMBER(1));
            oraclecallablestatement.close();
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - ejecuto con exito", 
                                         3);
        } catch (Exception var12) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                         var12.getMessage().toString(), 3);

            try {
                oraclecallablestatement.close();
            } catch (Exception var11) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamInvSolicitudAMImpl - lSql Error: " + 
                                             var11.getMessage().toString(), 3);
            }

            throw OAException.wrapperException(var12);
        }

        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - Commit al package: ", 
                                     3);
        return status;
    }

    public String obtieneMsgErrorConsurrente(OAPageContext pageContext, 
                                             String noSolicitud, 
                                             Number status) {
        StringBuffer bufferMsg = new StringBuffer();
        pageContext.writeDiagnostics(this, 
                                     "xXGamInvSolicitudAMImpl - obtieneMsgErrorConsurrente:" + 
                                     noSolicitud, 3);
        xXGamInvErrorsDevVOImpl errorDevVO = this.getxXGamInvErrorsDevVO1();
        errorDevVO.clearCache();
        errorDevVO.obtieneMsgErrorConsurrente(noSolicitud, status);
        RowSetIterator iterator = null;
        iterator = errorDevVO.createRowSetIterator((String)null);
        xXGamInvErrorsDevVORowImpl rowImpl = null;

        try {
            if (iterator == null || iterator.getRowCount() == 0) {
                Object var9 = null;
                iterator.closeRowSetIterator();
                return (String)var9;
            }

            while (iterator.hasNext()) {
                rowImpl = (xXGamInvErrorsDevVORowImpl)iterator.next();
                if (rowImpl != null) {
                    bufferMsg.append(rowImpl.getError() + ", ");
                }
            }
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            iterator.closeRowSetIterator();
        }

        return bufferMsg.toString();
    }


    public void executeInitialReport(int p_userId) {
        String whereSentece;
        String whereRemplace;

        whereSentece = " CREATED_BY = " + p_userId + " AND ROWNUM < 6";
        whereRemplace = whereSentece.replaceFirst("&lt;", "<");

        System.out.println("Inicia proceso para executeInitialReport");
        System.out.println("Se recibe p_userId: " + p_userId);
        System.out.println("whereRemplace: " + whereRemplace);
        System.out.println("Inicia muestra de los ultimos 5 datos en pantalla");
        xXGamInvSoliSummaryVOImpl vo = getxXGamInvSoliSummaryVO1();
        try {
            vo.setWhereClause("");
            vo.setWhereClause("CREATED_BY = " + p_userId + " AND ROWNUM < 6");
            vo.executeQuery();
        } catch (Exception exp) {
            System.out.println("Un error encontrado en executeInitialReport: " + 
                               exp.getMessage().toString());
        }

        System.out.println("Termina muestra de los ultimos 5 datos en pantalla");
    }

    /**Guillermo Sandoval(MS 03/Oct/2016)Proceso para obtener detalle de error cuando se encuentra uno generico*/
    public String getMessageError(OAPageContext pageContext, OAWebBean webBean, 
                                  int p_personId) {
        System.out.println("**Inicia proceso getMessageError, se recibe el parametro p_personId: " + 
                           p_personId);
        String v_mensage = "INICIAL";
        /**Creacion de objeto de la clase donde se encuentran los errores.*/
        XxGamInvConstantes constantesErrors = new XxGamInvConstantes();

        OADBTransaction txn = (OADBTransaction)getDBTransaction();
        String plsql =

            " DECLARE\n" + "V_EMPLOYEE_TYPE VARCHAR2(4000);\n" + 
            "V_SEX VARCHAR2(4000);\n" + 
            "V_EMPLOYEE_PAYROLL VARCHAR2(4000);\n" + 
            "V_PAYROLL_COD VARCHAR2(4000);\n" + "V_ZONA VARCHAR2(4000);\n" + 
            "V_COMPANIA VARCHAR2(4000);\n" + "V_POSITION VARCHAR2(4000);\n" + 
            "V_ERROR_COUNT NUMBER;\n" + 
            "VAL_COMPANIA VARCHAR2(4000) := NULL;\n" + 
            "VAL_TIPO_PERSONA VARCHAR2(4000) := NULL;\n" + 
            "VAL_SEXO VARCHAR2(4000) := NULL;\n" + 
            "VAL_NOMINA VARCHAR2(4000) := NULL;\n" + 
            "VAL_LOCALIDAD VARCHAR2(4000) := NULL;\n" + 
            "VAL_PUESTO VARCHAR2(4000) := NULL;\n" + 
            "l_person_type   per_person_types.user_person_type%TYPE;\n" + 
            "l_genero        fnd_lookup_values.meaning%TYPE;\n" + 
            "l_gpopago       PAY_ALL_PAYROLLS_F.payroll_name%TYPE;\n" + 
            "l_local         hr_locations_all_tl.location_code%TYPE;\n" + 
            "v_debug VARCHAR2(4000);\n" + "p_person_id number;\n" + "\n" + 
            "BEGIN     \n" + "    p_person_id := ?; \n" + "\n" + 
            "    V_ERROR_COUNT := 0;\n" + "    \n" + 
            "    l_person_type   := xxgam_inv_kit_util_pkg.xxgam_get_tipopersona(p_person_id);\n" + 
            "    IF INSTR(l_person_type,'ERR_',1)>0 THEN\n" + 
            "        V_ERROR_COUNT := V_ERROR_COUNT + 1;\n" + 
            "        v_debug := v_debug || '" + 
            constantesErrors.ERROR_KIT_DETAIL_1 + "';\n" + "    ELSE\n" + 
            "        V_EMPLOYEE_TYPE := xxgam_inv_kit_util_pkg.xxgam_get_tablaValor('TIPO DE PERSONA',l_person_type);\n" + 
            "        IF INSTR(V_EMPLOYEE_TYPE,'ERR_',1)>0 THEN\n" + 
            "            V_ERROR_COUNT := V_ERROR_COUNT + 1;\n" + 
            "            v_debug := v_debug || '" + 
            constantesErrors.ERROR_KIT_DETAIL_1 + "';\n" + 
            "        END IF;    \n" + "    END IF;\n" + "\n" + 
            "    l_genero  := xxgam_inv_kit_util_pkg.xxgam_get_genero(p_person_id);\n" + 
            "    IF INSTR(l_genero,'ERR_',1)>0 THEN\n" + 
            "        V_ERROR_COUNT := V_ERROR_COUNT + 1;\n" + 
            "        v_debug := v_debug || '" + 
            constantesErrors.ERROR_KIT_DETAIL_2 + "';\n" + "    ELSE\n" + 
            "        V_SEX  := xxgam_inv_kit_util_pkg.xxgam_get_tablaValor('GENERO',l_genero);\n" + 
            "        IF INSTR(V_SEX,'ERR_',1)>0 THEN\n" + 
            "            V_ERROR_COUNT := V_ERROR_COUNT + 1;\n" + 
            "            v_debug := v_debug || '" + 
            constantesErrors.ERROR_KIT_DETAIL_2 + "';\n" + 
            "        END IF;    \n" + "    END IF;\n" + "\n" + 
            "    l_gpopago := xxgam_inv_kit_util_pkg.xxgam_get_gpoPago(p_person_id);\n" + 
            "    IF INSTR(l_gpopago,'ERR_',1)>0 THEN\n" + 
            "        V_ERROR_COUNT := V_ERROR_COUNT + 1;\n" + 
            "        v_debug := v_debug || '" + 
            constantesErrors.ERROR_KIT_DETAIL_3 + "';\n" + "    ELSE\n" + 
            "        V_PAYROLL_COD  := xxgam_inv_kit_util_pkg.xxgam_get_tablaValor('GRUPO DE PAGO',l_gpopago);\n" + 
            "        IF INSTR(V_PAYROLL_COD,'ERR_',1)>0 THEN\n" + 
            "            v_debug := v_debug || '" + 
            constantesErrors.ERROR_KIT_DETAIL_3 + "';\n" + 
            "        END IF;    \n" + "    END IF;\n" + "\n" + 
            "    l_local   := xxgam_inv_kit_util_pkg.xxgam_get_localidad(p_person_id);\n" + 
            "    IF INSTR(l_local,'ERR_',1)>0 THEN\n" + 
            "        V_ERROR_COUNT := V_ERROR_COUNT + 1;\n" + 
            "        v_debug := v_debug || '" + 
            constantesErrors.ERROR_KIT_DETAIL_4 + "';\n" + "    ELSE\n" + 
            "        V_ZONA := xxgam_inv_kit_util_pkg.xxgam_get_tablaValor('LOCALIDAD',l_local);\n" + 
            "        IF INSTR(V_ZONA,'ERR_',1)>0 THEN\n" + 
            "            V_ERROR_COUNT := V_ERROR_COUNT + 1;\n" + 
            "            v_debug := v_debug || '" + 
            constantesErrors.ERROR_KIT_DETAIL_4 + "';\n" + 
            "        END IF;    \n" + "    END IF;\n" + "\n" + 
            "    V_POSITION  := xxgam_inv_kit_util_pkg.xxgam_get_puesto(p_person_id);\n" + 
            "    IF INSTR(V_POSITION,'ERR_',1)>0 THEN\n" + 
            "        V_ERROR_COUNT := V_ERROR_COUNT + 1;\n" + 
            "        v_debug := v_debug || '" + 
            constantesErrors.ERROR_KIT_DETAIL_5 + "';\n" + "    END IF;\n" + 
            "\n" + "    V_COMPANIA   := SUBSTR(l_gpopago,1,2);\n" + "    \n" + 
            "    IF V_ERROR_COUNT = 0 THEN\n" + 
            "        V_ERROR_COUNT := V_ERROR_COUNT + 1;\n" + "       \n" + 
            "           BEGIN\n" + "           \n" + 
            "               SELECT COUNT(1)\n" + 
            "                 INTO VAL_COMPANIA\n" + 
            "                 FROM fnd_lookup_values\n" + 
            "                WHERE     1 = 1\n" + 
            "                  AND lookup_type = 'XXGAM_ASIG_KIT_UNIF_' || '%'             \n" + 
            "                  AND SUBSTR (lookup_code, 8) = V_EMPLOYEE_TYPE || '.' || V_SEX\n" + 
            "                  AND SUBSTR (meaning, 8) = V_PAYROLL_COD || '.' || V_ZONA \n" + 
            "                  AND description LIKE V_POSITION || '.%'               \n" + 
            "                  AND enabled_flag = 'Y'\n" + 
            "                  AND LANGUAGE = USERENV ('LANG');\n" + 
            "                             \n" + 
            "           EXCEPTION WHEN OTHERS THEN\n" + 
            "               VAL_COMPANIA := NULL;\n" + "           END; \n" + 
            "        \n" + "           BEGIN\n" + "\n" + 
            "               SELECT COUNT(1)\n" + 
            "                 INTO VAL_TIPO_PERSONA\n" + 
            "                 FROM fnd_lookup_values\n" + 
            "                WHERE     1 = 1\n" + 
            "                  AND lookup_type = 'XXGAM_ASIG_KIT_UNIF_' || V_COMPANIA             \n" + 
            "                  AND SUBSTR (lookup_code, 8) LIKE '%' || '.' || V_SEX   \n" + 
            "                  AND SUBSTR (meaning, 8) = V_PAYROLL_COD || '.' || V_ZONA \n" + 
            "                  AND description LIKE V_POSITION || '.%'               \n" + 
            "                  AND enabled_flag = 'Y'\n" + 
            "                  AND LANGUAGE = USERENV ('LANG');\n" + "\n" + 
            "           \n" + "           EXCEPTION WHEN OTHERS THEN\n" + 
            "               VAL_TIPO_PERSONA := NULL;\n" + 
            "           END;\n" + "        \n" + "           BEGIN\n" + "\n" + 
            "               SELECT COUNT(1)\n" + 
            "                 INTO VAL_SEXO\n" + 
            "                 FROM fnd_lookup_values\n" + 
            "                WHERE     1 = 1\n" + 
            "                  AND lookup_type = 'XXGAM_ASIG_KIT_UNIF_' || V_COMPANIA             \n" + 
            "                  AND SUBSTR (lookup_code, 8) LIKE V_EMPLOYEE_TYPE || '.' || '%'   \n" + 
            "                  AND SUBSTR (meaning, 8) = V_PAYROLL_COD|| '.' || V_ZONA \n" + 
            "                  AND description LIKE V_POSITION || '.%'              \n" + 
            "                  AND enabled_flag = 'Y'\n" + 
            "                  AND LANGUAGE = USERENV ('LANG');\n" + 
            "                             \n" + 
            "           EXCEPTION WHEN OTHERS THEN\n" + 
            "               VAL_SEXO := NULL;\n" + 
            "           END;        \n" + "        \n" + "           BEGIN\n" + 
            "               SELECT COUNT(1)\n" + 
            "                 INTO VAL_NOMINA\n" + 
            "                 FROM fnd_lookup_values\n" + 
            "                WHERE     1 = 1\n" + 
            "                  AND lookup_type = 'XXGAM_ASIG_KIT_UNIF_' || V_COMPANIA            \n" + 
            "                  AND SUBSTR (lookup_code, 8) = V_EMPLOYEE_TYPE || '.' || V_SEX   \n" + 
            "                  AND SUBSTR (meaning, 8) LIKE '%' || '.' || V_ZONA \n" + 
            "                  AND description LIKE V_POSITION || '.%'             \n" + 
            "                  AND enabled_flag = 'Y'\n" + 
            "                  AND LANGUAGE = USERENV ('LANG');           \n" + 
            "           EXCEPTION WHEN OTHERS THEN\n" + 
            "               VAL_NOMINA := NULL;\n" + 
            "           END;        \n" + "        \n" + "           BEGIN\n" + 
            "\n" + "               SELECT COUNT(1)\n" + 
            "                 INTO VAL_LOCALIDAD\n" + 
            "                 FROM fnd_lookup_values\n" + 
            "                WHERE     1 = 1\n" + 
            "                  AND lookup_type = 'XXGAM_ASIG_KIT_UNIF_' || V_COMPANIA           \n" + 
            "                  AND SUBSTR (lookup_code, 8) = V_EMPLOYEE_TYPE || '.' || V_SEX   \n" + 
            "                  AND SUBSTR (meaning, 8) LIKE V_PAYROLL_COD || '.' || '%' \n" + 
            "                  AND description LIKE V_POSITION || '.%'               \n" + 
            "                  AND enabled_flag = 'Y'\n" + 
            "                  AND LANGUAGE = USERENV ('LANG');           \n" + 
            "\n" + "           EXCEPTION WHEN OTHERS THEN\n" + 
            "               VAL_LOCALIDAD := NULL;\n" + 
            "           END;        \n" + "        \n" + "           BEGIN\n" + 
            "               SELECT COUNT(1)\n" + 
            "                 INTO VAL_PUESTO\n" + 
            "                 FROM fnd_lookup_values\n" + 
            "                WHERE     1 = 1\n" + 
            "                  AND lookup_type = 'XXGAM_ASIG_KIT_UNIF_' || V_COMPANIA            \n" + 
            "                  AND SUBSTR (lookup_code, 8) = V_EMPLOYEE_TYPE || '.' || V_SEX   \n" + 
            "                  AND SUBSTR (meaning, 8) = V_PAYROLL_COD || '.' || V_ZONA \n" + 
            "                  AND description LIKE '%' || '.%'               \n" + 
            "                  AND enabled_flag = 'Y'\n" + 
            "                  AND LANGUAGE = USERENV ('LANG');              \n" + 
            "           EXCEPTION WHEN OTHERS THEN\n" + 
            "               VAL_PUESTO := NULL;\n" + "           END;   \n" + 
            "           \n" + "           \n" + 
            "           IF VAL_COMPANIA <> '0' " + "               THEN " + 
            "               v_debug := v_debug || ''; " + 
            "           END IF;    " + 
            "           IF VAL_TIPO_PERSONA <> '0' " + "               THEN " + 
            "               v_debug := v_debug || '" + 
            constantesErrors.ERROR_KIT_DETAIL_1 + "'; " + 
            "           END IF;    " + "            " + 
            "           IF VAL_NOMINA <> '0' " + "               THEN " + 
            "               v_debug := v_debug || '" + 
            constantesErrors.ERROR_KIT_DETAIL_3 + "'; " + 
            "           END IF;     " + "           " + 
            "           IF VAL_LOCALIDAD <> '0' " + "               THEN " + 
            "               v_debug := v_debug || '" + 
            constantesErrors.ERROR_KIT_DETAIL_4 + "'; " + 
            "           END IF;    " + "            " + 
            "           IF VAL_PUESTO <> '0' " + "               THEN " + 
            "               v_debug := v_debug || '" + 
            constantesErrors.ERROR_KIT_DETAIL_5 + "'; " + 
            "           END IF;     " + "           IF VAL_SEXO <> '0' " + 
            "               THEN " + "               v_debug := '" + 
            constantesErrors.ERROR_KIT_DETAIL_6 + "'; " + 
            "           END IF;    " + "     \n" + "    END IF;\n" + 
            "    IF VAL_COMPANIA = 0 AND VAL_TIPO_PERSONA = 0 AND VAL_SEXO = 0 AND VAL_NOMINA  = 0 AND VAL_LOCALIDAD  = 0 AND VAL_PUESTO = 0 \n" + 
            "        THEN \n" + "        v_debug := '" + 
            constantesErrors.ERROR_KIT_DETAIL_6 + "';    \n" + 
            "    END IF;    \n" + " ? :=  v_debug;    " + "END;   \n" + 
            "   \n" + "   ";
        /*
        String  plsql  = " DECLARE " +
                       " V_PERSON_ID VARCHAR2(4000); " +
                        " V_FULL_NAME VARCHAR2(4000); " +
                        " V_EMPLOYEE_NUMBER VARCHAR2(4000); " +
                        " V_EMPLOYEE_TYPE VARCHAR2(4000); " +
                        " V_SEX VARCHAR2(4000); " +
                        " V_EMPLOYEE_TYPE_DESC VARCHAR2(4000); " +
                        " V_EMPLOYEE_PAYROLL VARCHAR2(4000); " +
                        " V_PAYROLL_COD VARCHAR2(4000); " +
                        " V_ZONA VARCHAR2(4000); " +
                        " V_COMPANIA VARCHAR2(4000); " +
                        " V_POSITION VARCHAR2(4000); " +
                        " V_LOCATION_DESC VARCHAR2(4000); " +
                        " V_KIT_CODE VARCHAR2(4000); " +
                        " V_ERROR_COUNT NUMBER; " +

                        " VAL_COMPANIA VARCHAR2(4000) := NULL; " +
                        " VAL_TIPO_PERSONA VARCHAR2(4000) := NULL; " +
                        " VAL_SEXO VARCHAR2(4000) := NULL; " +
                        " VAL_NOMINA VARCHAR2(4000) := NULL; " +
                        " VAL_LOCALIDAD VARCHAR2(4000) := NULL; " +
                        " VAL_PUESTO VARCHAR2(4000) := NULL; " +
                        "v_debug VARCHAR2(4000) := NULL; " +
                        "BEGIN     " +
                        " SELECT PERSON_ID " +
                        "     , FULL_NAME " +
                        "     , EMPLOYEE_NUMBER " +
                        "     , EMPLOYEE_TYPE " +
                        "     , SEX " +
                        "     , EMPLOYEE_TYPE_DESC " +
                        "     , EMPLOYEE_PAYROLL " +
                        "     , PAYROLL_COD " +
                        "     , ZONA " +
                        "     , COMPANIA " +
                        "     , POSITION " +
                        "     , LOCATION_DESC " +
                        "     , KIT_CODE " +
                        "  INTO V_PERSON_ID " +
                        "     , V_FULL_NAME " +
                        "     , V_EMPLOYEE_NUMBER " +
                        "     , V_EMPLOYEE_TYPE " +
                        "     , V_SEX " +
                        "     , V_EMPLOYEE_TYPE_DESC " +
                        "     , V_EMPLOYEE_PAYROLL " +
                        "     , V_PAYROLL_COD " +
                        "     , V_ZONA " +
                        "     , V_COMPANIA " +
                        "     , V_POSITION " +
                        "     , V_LOCATION_DESC " +
                        "     , V_KIT_CODE " +
                        "  FROM XXGAM_HR_UNIFORMS_KIT_V " +
                        " WHERE LANGUAGE = 'ESA' " +
                        " AND UPPER(EMPLOYEE_TYPE_DESC)NOT LIKE '%EX%' " +
                        " AND PERSON_ID = ?; " +
                        "    V_ERROR_COUNT := 0; " +
                        "    " +
                        "    IF V_PERSON_ID IS NULL THEN " +
                        "        V_ERROR_COUNT := V_ERROR_COUNT + 1; " +
                        "        v_debug := v_debug || ''; " +
                        "    END IF; " +
                        "    " +
                        "    IF V_FULL_NAME IS NULL THEN " +
                        "        V_ERROR_COUNT := V_ERROR_COUNT + 1; " +
                        "        v_debug := v_debug || ''; " +
                        "    END IF; " +
                        "     " +
                        "    IF V_EMPLOYEE_NUMBER IS NULL THEN " +
                        "        V_ERROR_COUNT := V_ERROR_COUNT + 1; " +
                        "        v_debug := v_debug || ''; " +
                        "    END IF; " +
                        "    " +
                        "    IF V_EMPLOYEE_TYPE IS NULL THEN " +
                        "        V_ERROR_COUNT := V_ERROR_COUNT + 1; " +
                        "        v_debug := v_debug || '" + constantesErrors.ERROR_KIT_1 + "'; " +
                        "    END IF; " +
                        "    " +
                        "    IF V_SEX IS NULL THEN " +
                        "        V_ERROR_COUNT := V_ERROR_COUNT + 1; " +
                        "        v_debug := v_debug || '" + constantesErrors.ERROR_KIT_2 + "'; " +
                        "    END IF; " +
                        "    " +
                        "    IF V_EMPLOYEE_TYPE_DESC IS NULL THEN " +
                        "        V_ERROR_COUNT := V_ERROR_COUNT + 1; " +
                        "        v_debug := v_debug || ''; " +
                        "    END IF; " +
                        "    " +
                        "    IF V_EMPLOYEE_PAYROLL IS NULL THEN " +
                        "        V_ERROR_COUNT := V_ERROR_COUNT + 1; " +
                        "        v_debug := v_debug || '" + constantesErrors.ERROR_KIT_3 + "'; " +
                        "    END IF; " +
                        "    " +
                        "    IF V_PAYROLL_COD IS NULL THEN " +
                        "        V_ERROR_COUNT := V_ERROR_COUNT + 1; " +
                        "        v_debug := v_debug || ''; " +
                        "    END IF; " +
                        "    " +
                        "    IF V_ZONA IS NULL THEN " +
                        "        V_ERROR_COUNT := V_ERROR_COUNT + 1; " +
                        "        v_debug := v_debug || '" + constantesErrors.ERROR_KIT_4  + "'; " +
                        "    END IF; " +
                        "    " +
                        "    IF V_COMPANIA IS NULL THEN " +
                        "        V_ERROR_COUNT := V_ERROR_COUNT + 1; " +
                        "        v_debug := v_debug || ''; " +
                        "    END IF; " +
                        "    " +
                        "    IF V_POSITION IS NULL THEN " +
                        "        V_ERROR_COUNT := V_ERROR_COUNT + 1; " +
                        "        v_debug := v_debug || '" + constantesErrors.ERROR_KIT_5 +"'; " +
                        "    END IF; " +
                        "    " +
                        "    IF V_LOCATION_DESC IS NULL THEN " +
                        "        V_ERROR_COUNT := V_ERROR_COUNT + 1; " +
                        "        v_debug := v_debug || ''; " +
                        "    END IF; " +
                        "    " +
                        "    IF V_KIT_CODE IS NULL THEN " +
                        "        V_ERROR_COUNT := V_ERROR_COUNT + 1; " +
                        "           BEGIN " +
                        "           " +
                        "               SELECT COUNT(1) " +
                        "                 INTO VAL_COMPANIA " +
                        "                 FROM fnd_lookup_values " +
                        "                WHERE     1 = 1 " +
                        "                  AND lookup_type = 'XXGAM_ASIG_KIT_UNIF_' || '%'   " +
                        "                  AND SUBSTR (lookup_code, 8) = V_EMPLOYEE_TYPE || '.' || V_SEX  " +
                        "                  AND SUBSTR (meaning, 8) = V_PAYROLL_COD || '.' || V_ZONA " +
                        "                  AND description LIKE V_POSITION || '.%'  " +
                        "                  AND enabled_flag = 'Y' " +
                        "                  AND LANGUAGE = USERENV ('LANG'); " +
                        "                             " +
                        "           EXCEPTION WHEN OTHERS THEN " +
                        "               VAL_COMPANIA := NULL; " +
                        "           END; " +
                        "           BEGIN " +

                        "               SELECT COUNT(1) " +
                        "                 INTO VAL_TIPO_PERSONA " +
                        "                 FROM fnd_lookup_values  " +
                        "                WHERE     1 = 1 " +
                        "                  AND lookup_type = 'XXGAM_ASIG_KIT_UNIF_' || V_COMPANIA " +
                        "                  AND SUBSTR (lookup_code, 8) LIKE '%' || '.' || V_SEX " +
                        "                  AND SUBSTR (meaning, 8) = V_PAYROLL_COD || '.' || V_ZONA " +
                        "                  AND description LIKE V_POSITION || '.%'               " +
                        "                  AND enabled_flag = 'Y' " +
                        "                  AND LANGUAGE = USERENV ('LANG'); " +

                        "           " +
                        "           EXCEPTION WHEN OTHERS THEN " +
                        "               VAL_TIPO_PERSONA := NULL; " +
                        "           END; " +
                        "           BEGIN " +

                        "               SELECT COUNT(1) " +
                        "                 INTO VAL_SEXO " +
                        "                 FROM fnd_lookup_values " +
                        "                WHERE     1 = 1 " +
                        "                  AND lookup_type = 'XXGAM_ASIG_KIT_UNIF_' || V_COMPANIA    " +
                        "                  AND SUBSTR (lookup_code, 8) LIKE V_EMPLOYEE_TYPE || '.' || '%' " +
                        "                  AND SUBSTR (meaning, 8) = V_PAYROLL_COD|| '.' || V_ZONA " +
                        "                  AND description LIKE V_POSITION || '.%'      " +
                        "                  AND enabled_flag = 'Y' " +
                        "                  AND LANGUAGE = USERENV ('LANG'); " +
                        "                             " +
                        "           EXCEPTION WHEN OTHERS THEN " +
                        "               VAL_SEXO := NULL; " +
                        "           END;        " +
                        "           BEGIN " +
                        "               SELECT COUNT(1) " +
                        "                 INTO VAL_NOMINA " +
                        "                 FROM fnd_lookup_values " +
                        "                WHERE     1 = 1 " +
                        "                  AND lookup_type = 'XXGAM_ASIG_KIT_UNIF_' || V_COMPANIA  " +
                        "                  AND SUBSTR (lookup_code, 8) = V_EMPLOYEE_TYPE || '.' || V_SEX " +
                        "                  AND SUBSTR (meaning, 8) LIKE '%' || '.' || V_ZONA " +
                        "                  AND description LIKE V_POSITION || '.%'       " +
                        "                  AND enabled_flag = 'Y' " +
                        "                  AND LANGUAGE = USERENV ('LANG');   " +
                        "           EXCEPTION WHEN OTHERS THEN " +
                        "               VAL_NOMINA := NULL; " +
                        "           END;        " +
                        "           BEGIN" +

                        "               SELECT COUNT(1) " +
                        "                 INTO VAL_LOCALIDAD " +
                        "                 FROM fnd_lookup_values " +
                        "                WHERE     1 = 1 " +
                        "                  AND lookup_type = 'XXGAM_ASIG_KIT_UNIF_' || V_COMPANIA     " +
                        "                  AND SUBSTR (lookup_code, 8) = V_EMPLOYEE_TYPE || '.' || V_SEX   " +
                        "                  AND SUBSTR (meaning, 8) LIKE V_PAYROLL_COD || '.' || '%' " +
                        "                  AND description LIKE V_POSITION || '.%'   " +
                        "                  AND enabled_flag = 'Y' " +
                        "                  AND LANGUAGE = USERENV ('LANG');           " +

                        "           EXCEPTION WHEN OTHERS THEN " +
                        "               VAL_LOCALIDAD := NULL; " +
                        "           END;        " +
                        "           BEGIN " +
                        "               SELECT COUNT(1) " +
                        "                 INTO VAL_LOCALIDAD " +
                        "                 FROM fnd_lookup_values " +
                        "                WHERE     1 = 1 " +
                        "                  AND lookup_type = 'XXGAM_ASIG_KIT_UNIF_' || V_COMPANIA     " +
                        "                  AND SUBSTR (lookup_code, 8) = V_EMPLOYEE_TYPE || '.' || '%' " +
                        "                  AND SUBSTR (meaning, 8) = V_PAYROLL_COD || '.' || V_ZONA " +
                        "                  AND description LIKE '%' || '.%'     " +
                        "                  AND enabled_flag = 'Y' " +
                        "                  AND LANGUAGE = USERENV ('LANG');              " +
                        "           EXCEPTION WHEN OTHERS THEN " +
                        "               VAL_PUESTO := NULL; " +
                        "           END;   " +
                        "           " +
                        "           " +
                        "           IF VAL_COMPANIA <> '0' " +
                        "               THEN " +
                        "               v_debug := v_debug || ''; " +
                        "           END IF;    " +

                        "           IF VAL_TIPO_PERSONA <> '0' " +
                        "               THEN " +
                        "               v_debug := v_debug || '" + constantesErrors.ERROR_KIT_1 + "'; " +
                        "           END IF;    " +

                        "           IF VAL_SEXO <> '0' " +
                        "               THEN " +
                        "               v_debug := v_debug || '" + constantesErrors.ERROR_KIT_2 + "'; " +
                        "           END IF;    " +
                        "            " +
                        "           IF VAL_NOMINA <> '0' " +
                        "               THEN " +
                        "               v_debug := v_debug || '" + constantesErrors. ERROR_KIT_3 + "'; " +
                        "           END IF;     " +
                        "           " +
                        "           IF VAL_LOCALIDAD <> '0' " +
                        "               THEN " +
                        "               v_debug := v_debug || '" + constantesErrors.ERROR_KIT_4 + "'; " +
                        "           END IF;    " +
                        "            " +
                        "           IF VAL_PUESTO <> '0' " +
                        "               THEN " +
                        "               v_debug := v_debug || '" + constantesErrors.ERROR_KIT_5 + "'; " +
                        "           END IF;     " +
                        "     " +
                        "    END IF; " +
                        " IF v_debug IS NULL THEN " +
                        "     v_debug := '" + constantesErrors.ERROR_GLOBAL + " '; " +
                        " END IF; " +
                        " ? :=  v_debug;    " +
                        "END; ";
        */
        /*        " DECLARE                                                                                            " +
                        "   l_tipo_persona   per_person_types.user_person_type%TYPE;                                          " +
                        "    l_sex           fnd_lookup_values.meaning%TYPE;                                                 " +
                        "    l_nomina        PAY_ALL_PAYROLLS_F.payroll_name%TYPE;                                           " +
                        "    l_localidad     hr_locations_all_tl.location_code%TYPE;                                         " +
                        "    l_puesto        hr_all_positions_f_tl.NAME%TYPE;                                                " +
                        "    l_person_type   per_person_types.user_person_type%TYPE;                                         " +
                        "    l_genero        fnd_lookup_values.meaning%TYPE;                                                 " +
                        "    l_gpopago       PAY_ALL_PAYROLLS_F.payroll_name%TYPE;                                           " +
                        "    l_local         hr_locations_all_tl.location_code%TYPE;                                         " +
                        "    l_position      hr_all_positions_f_tl.NAME%TYPE;                                                " +
                        "    l_cia           varchar(3);                                                                     " +
                        "    l_kit_code      fnd_lookup_values.description%TYPE;                                             " +
                        "    l_kit_id        xxgam_inv_kit.kit_id%TYPE;                                                      " +
                        "    persontype_exc  EXCEPTION;                                                                      " +
                        "    sex_exc         EXCEPTION;                                                                      " +
                        "    gpopago_exc     EXCEPTION;                                                                      " +
                        "    localidad_exc   EXCEPTION;                                                                      " +
                        "    puesto_exc      EXCEPTION;                                                                      " +
                        "    table_exc       EXCEPTION;                                                                      " +
                        "    p_person_id     NUMBER;                                                                         " +
                        "    v_debug         VARCHAR2(4000) := NULL;                                                         " +
                        "    v_errors        NUMBER;                                                                         " +
                        " BEGIN                                                                                              " +
                        "    p_person_id := ?;                                                                               " +
                        "    v_errors := 0;                                                                                  " +
                        "    l_person_type   := xxgam_inv_kit_util_pkg.xxgam_get_tipopersona(p_person_id);                   " +
                        "                                                                                                    " +
                        "    IF INSTR(l_person_type,'ERR_',1)>0 THEN                                                         " +
                        "        v_errors := v_errors + 1;                                                                   " +
                        "        v_debug := '" +  constantesErrors.ERROR_KIT_1 + "';                                         " +
                        "    END IF;                                                                                         " +
                        "    l_genero  := xxgam_inv_kit_util_pkg.xxgam_get_genero(p_person_id);                              " +
                        "    IF INSTR(l_genero,'ERR_',1)>0 THEN                                                              " +
                        "       v_errors := v_errors + 1;                                                                    " +
                        "       v_debug := '" + constantesErrors.ERROR_KIT_2 + "';                                           " +
                        "    END IF;                                                                                         " +
                        "    l_gpopago := xxgam_inv_kit_util_pkg.xxgam_get_gpoPago(p_person_id);                             " +
                        "    IF INSTR(l_gpopago,'ERR_',1)>0 THEN                                                             " +
                        "         v_errors := v_errors + 1;                                                                  " +
                        "         v_debug := '" +  constantesErrors.ERROR_KIT_3 +"';                                         " +
                        "    END IF;                                                                                         " +
                        "    l_local   := xxgam_inv_kit_util_pkg.xxgam_get_localidad(p_person_id);                           " +
                        "    IF INSTR(l_local,'ERR_',1)>0 THEN                                                               " +
                        "         v_errors := v_errors + 1;                                                                  " +
                        "         v_debug := '" + constantesErrors.ERROR_KIT_4+ "';                                          " +
                        "    END IF;                                                                                         " +
                        "    l_puesto  := xxgam_inv_kit_util_pkg.xxgam_get_puesto(p_person_id);                              " +
                        "    IF INSTR(l_puesto,'ERR_',1)>0 THEN                                                              " +
                        "         v_errors := v_errors + 1;                                                                  " +
                        "         v_debug := '" + constantesErrors.ERROR_KIT_5 + "';                                         " +
                        "    END IF;                                                                                         " +
                        "    l_cia   := SUBSTR(l_gpopago,1,2);                                                               " +
                        "    l_tipo_persona := xxgam_inv_kit_util_pkg.xxgam_get_tablaValor('TIPO DE PERSONA',l_person_type); " +
                        "    IF INSTR(l_tipo_persona,'ERR_',1)>0 THEN                                                        " +
                        "        v_errors := v_errors + 1;                                                                   " +
                        "        v_debug := '" + constantesErrors.ERROR_KIT_6 + "';                                          " +
                        "    END IF;                                                                                         " +
                        "    l_sex  := xxgam_inv_kit_util_pkg.xxgam_get_tablaValor('GENERO',l_genero);                       " +
                        "    IF INSTR(l_sex,'ERR_',1)>0 THEN                                                                 " +
                        "        v_errors := v_errors + 1;                                                                   " +
                        "        v_debug := '" + constantesErrors.ERROR_KIT_7 + "';                                          " +
                        "    END IF;                                                                                         " +
                        "                                                                                                    " +
                        "    l_nomina  := xxgam_inv_kit_util_pkg.xxgam_get_tablaValor('GRUPO DE PAGO',l_gpopago);            " +
                        "    IF INSTR(l_nomina,'ERR_',1)>0 THEN                                                              " +
                        "        v_errors := v_errors + 1;                                                                   " +
                        "        v_debug := '" + constantesErrors.ERROR_KIT_8 + "';                                          " +
                        "    END IF;                                                                                         " +
                        "                                                                                                    " +
                        "    l_localidad := xxgam_inv_kit_util_pkg.xxgam_get_tablaValor('LOCALIDAD',l_local);                " +
                        "    IF INSTR(l_localidad,'ERR_',1)>0 THEN                                                           " +
                        "        v_errors := v_errors + 1;                                                                   " +
                        "        v_debug := '" + constantesErrors.ERROR_KIT_9 + "';                                          " +
                        "    END IF;                                                                                         " +
                        "                                                                                                    " +
                        "    IF v_errors <= 0 THEN                                                                           " +
                        "        BEGIN                                                                                       " +
                        "            SELECT SUBSTR(description,INSTR(description,'.',1)+1)                                   " +
                        "                INTO l_kit_code                                                                     " +
                        "            FROM fnd_lookup_values                                                                  " +
                        "            WHERE 1=1                                                                               " +
                        "            AND lookup_type = 'XXGAM_ASIG_KIT_UNIF_'||l_cia                                         " +
                        "            AND SUBSTR(lookup_code,8) = l_tipo_persona||'.'||l_sex                                  " +
                        "            AND SUBSTR(meaning,8) = l_nomina||'.'||l_localidad                                      " +
                        "            AND description LIKE l_puesto||'.%'                                                     " +
                        "            AND enabled_flag = 'Y'                                                                  " +
                        "            AND LANGUAGE = USERENV('LANG');                                                         " +
                        "        EXCEPTION                                                                                   " +
                        "            WHEN NO_DATA_FOUND THEN                                                                 " +
                        "             v_debug := '" + constantesErrors.ERROR_KIT_10 + "' ;                                   " +
                        "            WHEN TOO_MANY_ROWS THEN                                                                 " +
                        "              v_debug := '" + constantesErrors.ERROR_KIT_10 + "';                                   " +
                        "        END;                                                                                        " +
                        "                                                                                                    " +
                        "                                                                                                    " +
                        "        IF l_kit_code IS NOT NULL THEN                                                              " +
                        "            BEGIN                                                                                   " +
                        "                SELECT kit_id                                                                       " +
                        "                    INTO l_kit_id                                                                   " +
                        "                FROM xxgam_inv_kit                                                                  " +
                        "                WHERE 1=1                                                                           " +
                        "                AND kit_cod = l_kit_code;                                                           " +
                        "                v_debug := 'Y';                                                                     " +
                        "            EXCEPTION                                                                               " +
                        "                WHEN OTHERS THEN                                                                    " +
                        "                 v_debug := '" + constantesErrors.ERROR_KIT_11 + "';                                " +
                        "            END;                                                                                    " +
                        "        ELSE                                                                                        " +
                        "            v_debug := '" + constantesErrors.ERROR_KIT_10 +"';                                      " +
                        "        END IF;                                                                                     " +
                        "    END IF;                                                                                         " +
                        "                                                                                                    " +
                        " ? :=  v_debug;                                                                                     " +
                        "END;                                                                                                ";
  */

        CallableStatement cs = txn.createCallableStatement(plsql, 1);
        //OracleCallableStatement oracleStatemen = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(plsql, 1);
        try {
            cs.setInt(1, p_personId);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            v_mensage = cs.getObject(2).toString();
            System.out.println("Result = " + v_mensage);
            cs.close();
        } catch (SQLException e) {
            System.out.println("UN ERROR NO CONTROLADO: " + 
                               e.getMessage().toString());
            v_mensage = e.getMessage().toString();
        }
        return v_mensage;
    }

    public String getDotacionId(String p_soliDtblId) {
        String v_dotaId = "";
        xXGamInvSoliDtlVOImpl vo = getxXGamInvSoliDtlVO1();
        vo.setWhereClause("soli_dtl_id = " + p_soliDtblId);
        vo.executeQuery();
        if (vo.hasNext()) {
            Row row = vo.next();
            v_dotaId = row.getAttribute("DotaId").toString();
        }

        return v_dotaId;
    }

    public String validaInitReport(String pUserId) {
        String vReturn = "";

        OADBTransaction txn = (OADBTransaction)getDBTransaction();
        String plsql = 
            "DECLARE\n" + "\n" + "CURSOR SOLI_CUR\n" + "    IS \n" + "SELECT SOLI_ID \n" + 
            "  FROM XXGAM_INV_SOLI \n" + " WHERE CREATED_BY = ? \n" + 
            " ORDER BY SOLI_DATE DESC;\n" + " \n" + " V_COUNT NUMBER;\n" + 
            " V_RETURN VARCHAR2(200) := '';\n" + " \n" + "BEGIN\n" + 
            "    V_COUNT := 0;\n" + "    \n" + 
            "    FOR L_SOLI_CUR IN SOLI_CUR\n" + "        LOOP\n" + 
            "        IF V_COUNT = 5 THEN\n" + "            EXIT;\n" + 
            "        ELSE   \n" + 
            "        V_RETURN := V_RETURN || L_SOLI_CUR.SOLI_ID || ', ';\n" + 
            "        END IF;\n" + "        V_COUNT := V_COUNT + 1;\n" + 
            "    END LOOP;\n" + "    \n" + "    \n" + 
            "    V_RETURN := V_RETURN || '0';\n" + "        \n" + 
            "    ? := V_RETURN; " + "END; ";
        CallableStatement cs = txn.createCallableStatement(plsql, 1);
        try {
            cs.setString(1, pUserId);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            vReturn = cs.getObject(2).toString();
            System.out.println("Result = " + vReturn);
            cs.close();
        } catch (SQLException e) {
            System.out.println("UN ERROR NO CONTROLADO: " + 
                               e.getMessage().toString());
            vReturn = e.getMessage().toString();
        }


        return vReturn;
    }

    /**
     * Metodo Fill Inventory Solicitud Crear Cabecera Info "Proyecto Historico Tallas"
     * @param pageContext
     * @param webBean
     */
    public String fillSolCrearCabeInfo(OAPageContext pageContext, 
                                       OAWebBean webBean) {
        String retval = null;
        OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();
        OAMessageStyledTextBean oanombreEmpleadoBean = 
            (OAMessageStyledTextBean)oapagelayoutbean.findIndexedChildRecursive("nombreEmpleado");
        oanombreEmpleadoBean.setText(pageContext, "NombreEmpleadoBean");
        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();
        String pStrUserName = null;
        String strPersonID = null;
        pStrUserName = pageContext.getUserName();


        xXGamInvSoliVOImpl InvSoliVOImpl = getxXGamInvSoliVO1();
        xXGamInvSoliVORowImpl InvSoliVORowImpl = null;
        if (null != InvSoliVOImpl) {

            if (!InvSoliVOImpl.isPreparedForExecution()) {
                InvSoliVOImpl.setMaxFetchSize(0); /** Instead
         InvSoliVOImpl.setWhereClauseParams((Object[])null);
         InvSoliVOImpl.setWhereClauseParam(0, "-1");
         InvSoliVOImpl.executeQuery();
       **/
            }
            InvSoliVORowImpl = 
                    (xXGamInvSoliVORowImpl)InvSoliVOImpl.createRow();

            strPersonID = getPersonIdFromUserID(pStrUserName, connection);
            if (null != strPersonID) {
                if (strPersonID.contains("EXCEPTION")) {
                    return strPersonID;
                }
                try {
                    InvSoliVORowImpl.setPersonId(new oracle.jbo.domain.Number(strPersonID));
                } catch (SQLException sqle) {
                    return "EXCEPTION al establecer el atributo person_id:" + 
                        sqle.getErrorCode() + " , " + sqle.getMessage();
                }
            } /** END if(null!=strPersonID){ **/

            String[] arrayActiveAssignmentInfo = 
                this.getActiveAssignmentInfo(strPersonID, connection);
            if (null != arrayActiveAssignmentInfo[0]) {
                if (arrayActiveAssignmentInfo[0].contains("EXCEPTION")) {
                    return arrayActiveAssignmentInfo[0];
                }
            }

            String[] arrayFulNameEmpNumSex = 
                getFulNameEmpNumSex(strPersonID, connection);

            if (null != arrayFulNameEmpNumSex[0]) {
                if (arrayFulNameEmpNumSex[0].contains("EXCEPTION")) {
                    return arrayFulNameEmpNumSex[0];
                }
                oanombreEmpleadoBean.setText(pageContext, 
                                             arrayFulNameEmpNumSex[1]);
                InvSoliVORowImpl.setEmployeeNumber(arrayFulNameEmpNumSex[2]);
                InvSoliVORowImpl.setRfc(arrayFulNameEmpNumSex[4]);
                /** PersonFullName  arrayFulNameEmpNumSex[1] **/
                /** EmployeeNumber  arrayFulNameEmpNumSex[2] **/
                /** Sex             arrayFulNameEmpNumSex[3] **/
                /** rfc             arrayFulNameEmpNumSex[4] **/
            }

            String[] arrayPersonTypeID = 
                getPersonTypeID(strPersonID, connection);
            if (null != arrayPersonTypeID[0]) {
                if (arrayPersonTypeID[0].contains("EXCEPTION")) {
                    return arrayPersonTypeID[0];
                }
                InvSoliVORowImpl.setAdscription(arrayPersonTypeID[2]);
                /** PersonTypeID arrayPersonTypeID[1] **/
                /** PersonType   arrayPersonTypeID[2] **/
            }

            String strPersonType = arrayPersonTypeID[2];
            System.out.println("arrayPersonTypeID[2]:" + arrayPersonTypeID[2]);
            String[] arrayInvAssignExcept = 
                getInvAssignExcept(strPersonType, connection);
            System.out.println("arrayInvAssignExcept[1]:" + 
                               arrayInvAssignExcept[1]);

            if (null != arrayInvAssignExcept[0]) {
                if (arrayInvAssignExcept[0].contains("EXCEPTION")) {
                    return arrayInvAssignExcept[0];
                }
                InvSoliVORowImpl.setXxGamInvAssignExcept(arrayInvAssignExcept[1]);
            }

            String[] arrayGradeInfo = getGradeInfo(strPersonID, connection);
            if (null != arrayGradeInfo[0]) {
                if (arrayGradeInfo[0].contains("EXCEPTION")) {
                    return arrayGradeInfo[0];
                }
                InvSoliVORowImpl.setCategory(arrayGradeInfo[3]);
                /** GradeID        arrayGradeInfo[1] **/
                /** Grade          arrayGradeInfo[2] **/
                /** Categoria_gr   arrayGradeInfo[3] **/
            }

            String[] arrayAccountingInfo = 
                getAccountingInfo(strPersonID, connection);
            if (null != arrayAccountingInfo[0]) {
                if (arrayAccountingInfo[0].contains("EXCEPTION")) {
                    return arrayAccountingInfo[0];
                }
                InvSoliVORowImpl.setStation(arrayAccountingInfo[2]);
                InvSoliVORowImpl.setCostCenter(arrayAccountingInfo[4]);
                InvSoliVORowImpl.setExpenseDesc(arrayAccountingInfo[3]);

                try {
                    InvSoliVORowImpl.setExpenseAcc(new oracle.jbo.domain.Number(arrayAccountingInfo[1]));
                } catch (SQLException sqle) {
                    return "EXCEPTION al establecer el Id de la cuenta de gasto:" + 
                        sqle.getErrorCode() + " , " + sqle.getMessage();
                }

                /**  CodeCombinationID    arrayAccountingInfo[1] **/
                /**  Station              arrayAccountingInfo[2] **/
                /**  AccountCombination   arrayAccountingInfo[3] **/
                /**  CenterCost           arrayAccountingInfo[4] **/
            } /** END  if(null!=arrayAccountingInfo[0]){ **/
            InvSoliVOImpl.insertRow(InvSoliVORowImpl);

        } /** END if(null!=InvSoliVOImpl){ **/

        return retval;

    }


    /**
     * Metodo que recupera el person id a partir del user ID. "Proyecto Historico Tallas"
     * @return
     */
    private String getPersonIdFromUserID(String pStrUserName, 
                                         Connection pConnection) {
        String retval = null;
        int counter = 0;
        String strPrepStmt = 
            " select employee_id " + "       ,start_date " + "       ,end_date " + 
            "   from fnd_user " + "  where user_name = ? " + 
            "    and trunc(sysdate) between start_date " + 
            "    and nvl(end_date,TO_DATE('31/12/4712', 'DD/MM/YYYY')) ";

        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        try {
            prepStmt = 
                    pConnection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                 ResultSet.CONCUR_READ_ONLY);
            prepStmt.setString(1, pStrUserName);
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                retval = resultSet.getString("employee_id");
                counter = counter + 1;
            }

        } catch (SQLException sqle) {
            retval = 
                    "EXCEPTION al obtener el Person ID:" + sqle.getErrorCode() + 
                    " , " + sqle.getMessage();
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);

        if (counter > 1) {
            retval = "EXCEPTION al obtener el Person ID:Mas de un registro.";
        }

        return retval;
    }

    /**
     * Metodo que cierra un Result Set "Proyecto Historico Tallas"
     * @param pResultSet
     */
    private void closeResultSet(ResultSet pResultSet) {
        if (null != pResultSet) {
            try {
                pResultSet.close();
            } catch (SQLException sqle) {
                throw new OAException(sqle.getErrorCode() + " , " + 
                                      sqle.getMessage(), OAException.ERROR);
            }
        }
    }

    /**
     * Metodo que cierra un Prepared Statement "Proyecto Historico Tallas"
     * @param pPrepStmt
     */
    private void closePreparedStatement(PreparedStatement pPrepStmt) {
        if (null != pPrepStmt) {
            try {
                pPrepStmt.close();
            } catch (SQLException sqle) {
                throw new OAException(sqle.getErrorCode() + " , " + 
                                      sqle.getMessage(), OAException.ERROR);
            }
        }
    }


    /**
     * metodo que valida si el empleado tiene  una asignacion activa o no
     * @param strPersonID
     * @return
     */
    private String[] getActiveAssignmentInfo(String strPersonID, 
                                             Connection pConnection) {

        String[] retval = new String[3];
        retval[0] = null;
        retval[1] = null;
        retval[2] = null;

        int counter = 0;

        String strPrepStmt = 
            " select PASTT.USER_STATUS " + "      ,PASTT.ASSIGNMENT_STATUS_TYPE_ID " + 
            "  from PER_ALL_ASSIGNMENTS_F paaf " + 
            "      ,PER_ASSIGNMENT_STATUS_TYPES_TL PASTT " + " where 1 =1 " + 
            "  AND PAAF.ASSIGNMENT_STATUS_TYPE_ID = PASTT.ASSIGNMENT_STATUS_TYPE_ID " + 
            "  and trunc(sysdate) between  paaf.effective_start_date " + 
            "  and paaf.effective_end_date " + 
            "  and PASTT.user_status IN ('Active Assignment', 'Suspensi'||chr(50099)||'n Con IMSS y PyD') " + 
            "  and paaf.person_id = ? ";

        PreparedStatement prpStmt = null;
        ResultSet resulSet = null;

        try {
            prpStmt = 
                    pConnection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                 ResultSet.CONCUR_READ_ONLY);
            prpStmt.setDouble(1, new Double(strPersonID));
            resulSet = prpStmt.executeQuery();

            while (resulSet.next()) {
                retval[0] = "SUCESFULLY";
                retval[1] = resulSet.getString("USER_STATUS");
                retval[2] = resulSet.getString("ASSIGNMENT_STATUS_TYPE_ID");

                counter = counter + 1;
            }

        } catch (SQLException sqle) {
            retval[0] = 
                    "EXCEPTION al validar si el empleado tiene  asignacion activa o no:" + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
        }

        this.closeResultSet(resulSet);
        this.closePreparedStatement(prpStmt);

        if (counter > 1) {
            retval[0] = 
                    "EXCEPTION al validar si el empleado tiene  asignacion activa o no: Mas de un registro";
        }

        if (0 == counter) {
            retval[0] = 
                    "EXCEPTION Empleado Inactivo, acudir al area de Recursos Humanos para su Reactivacion.";
        }

        return retval;
    }


    /**
     * metodo que recupera el nombre del empleado, el numero de empleado y su genero "Proyecto Historico Tallas"
     * @param strPersonID
     * @return
     */
    private String[] getFulNameEmpNumSex(String strPersonID, 
                                         Connection pConnection) {
        String[] retval = new String[5];
        retval[0] = null;
        retval[1] = null;
        retval[2] = null;
        retval[3] = null;
        retval[4] = null;

        int counter = 0;
        String strPrepStmt = 
            " SELECT   papf.full_name " + "       , papf.employee_number " + 
            "       , papf.sex " + "       , papf.per_information2 rfc " + 
            " FROM per_all_people_f papf " + " WHERE 1=1 " + 
            "    and  trunc(sysdate) >= papf.effective_start_date " + 
            "    and  trunc(sysdate) <= papf.effective_end_date " + 
            "    and  papf.employee_number is not null " + 
            "    and  papf.person_id = ?";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;


        try {
            prepStmt = 
                    pConnection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                 ResultSet.CONCUR_READ_ONLY);
            prepStmt.setDouble(1, new Double(strPersonID));
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                retval[0] = "SUCESFULL";
                retval[1] = resultSet.getString("full_name");
                retval[2] = resultSet.getString("employee_number");
                retval[3] = resultSet.getString("sex");
                retval[4] = resultSet.getString("rfc");
                counter = counter + 1;
            }

        } catch (SQLException sqle) {
            retval[0] = 
                    "EXCEPTION al obtener el Nombre del empleado, numero de empleado y genero:" + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);

        if (counter > 1) {
            retval[0] = 
                    "EXCEPTION al obtener Nombre del empleado, numero de empleado y genero:Mas de un registro.";
        }
        return retval;

    }

    /**
     * Metodo que recupera el Person Type ID y Adscripcion.
     * @param strPersonID
     * @param pConnection
     * @return
     */
    private String[] getPersonTypeID(String strPersonID, 
                                     Connection pConnection) {
        String[] retval = new String[3];
        retval[0] = "SUCCESS";
        retval[1] = null;
        retval[2] = "";

        int counter = 0;

        String strPrepStmt = 
            " SELECT   pt.person_type_id   " + "       , pttl.user_person_type   " + 
            "  FROM   per_person_type_usages_f ptu,   " + 
            "         per_person_types pt,   " + 
            "         per_person_types_tl pttl, " + 
            "         per_all_people_f papf   " + 
            " WHERE   ptu.person_type_id = pt.person_type_id   " + 
            "         AND pt.person_type_id = pttl.person_type_id   " + 
            "         AND TRUNC (SYSDATE) BETWEEN ptu.effective_start_date   " + 
            "                                 AND  ptu.effective_end_date " + 
            "         AND TRUNC (SYSDATE) BETWEEN papf.effective_start_date   " + 
            "         AND  papf.effective_end_date   " + 
            "         AND pttl.language = USERENV ('LANG') " + 
            "         and papf.employee_number is not null " + 
            "         and papf.person_id = ptu.person_id " + 
            "         AND papf.person_id = ? " + 
            "         AND pt.system_person_type IN ('APL','EMP','EX_APL','EX_EMP','CWK','EX_CWK','OTHER') " + 
            "         ORDER BY DECODE(pt.system_person_type " + 
            "                        ,'EMP'   ,1 " + 
            "                        ,'CWK'   ,2 " + 
            "                        ,'APL'   ,3 " + 
            "                        ,'EX_EMP',4 " + 
            "                        ,'EX_CWK',5 " + 
            "                        ,'EX_APL',6 " + 
            "                                 ,7 " + 
            "                        ) ";

        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;


        try {
            prepStmt = 
                    pConnection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                 ResultSet.CONCUR_READ_ONLY);
            prepStmt.setDouble(1, new Double(strPersonID));
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                retval[0] = "SUCESFULL";
                retval[1] = resultSet.getString("person_type_id");
                retval[2] = 
                        retval[2] + "." + resultSet.getString("user_person_type");
                counter = counter + 1;

            }

        } catch (SQLException sqle) {
            retval[0] = 
                    "EXCEPTION al obtener el tipo de persona:" + sqle.getErrorCode() + 
                    " , " + sqle.getMessage();
            return retval;
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);

        if (0 == counter) {
            retval[0] = 
                    "EXCEPTION al obtener el tipo de persona: No se encontraron registros.";
            return retval;
        }

        if (counter >= 1) {
            retval[2] = retval[2].substring(1, retval[2].length());
            return retval;
        }

        return retval;
    }

    /**
     * Metodo que recupera informacion de grado del empleado. "Proyecto Historico Tallas"
     * @param strPersonID
     * @param pConnection
     * @return
     */
    private String[] getGradeInfo(String strPersonID, Connection pConnection) {

        String[] retval = new String[4];
        retval[0] = null;
        retval[1] = null;
        retval[2] = null;
        retval[3] = null;

        int counter = 0;
        String strPrepStmt = 
            " SELECT  pj.job_id   " + "       ,pj.name job_name " + 
            "       ,pgd.segment4  categoria_gr " + " FROM   per_jobs pj  " + 
            "       ,per_all_assignments_f paaf   " + 
            "       ,per_grade_definitions pgd " + "       ,per_grades pg " + 
            " WHERE 1=1   " + "   AND pj.job_id = paaf.job_id   " + 
            "   AND paaf.primary_flag = 'Y'   " + 
            "   AND pgd.grade_definition_id(+) = pg.grade_definition_id " + 
            "   AND pg.grade_id(+) = paaf.grade_id " + 
            "   AND TRUNC(SYSDATE) >= paaf.effective_start_date   " + 
            "   AND TRUNC(SYSDATE) <= paaf.effective_end_date   " + 
            "   AND TRUNC(SYSDATE) BETWEEN NVL(TRUNC(pj.date_from), TRUNC(SYSDATE))   " + 
            "                          AND NVL(TRUNC(pj.date_to), TO_DATE('31/12/4712', 'DD/MM/YYYY'))   " + 
            "   AND paaf.person_id = ? ";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        try {
            prepStmt = 
                    pConnection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                 ResultSet.CONCUR_READ_ONLY);
            prepStmt.setDouble(1, new Double(strPersonID));
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                retval[0] = "SUCESFULL";
                retval[1] = resultSet.getString("job_id");
                retval[2] = resultSet.getString("job_name");
                retval[3] = resultSet.getString("categoria_gr");
                counter = counter + 1;

            }
        } catch (SQLException sqle) {
            retval[0] = 
                    "EXCEPTION al obtener el grado de asignacion:" + sqle.getErrorCode() + 
                    " , " + sqle.getMessage();
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);


        if (counter > 1) {
            retval[0] = 
                    "EXCEPTION al obtener el grado de asignacion: mas de un registro.";
        }

        return retval;

    }

    /**
     * Metodo que recupera la informacion del puesto.
     * @param strPersonID
     * @param pConnection
     * @return
     */
    private String[] getPositionInfo(String strPersonID, 
                                     Connection pConnection) {

        String[] retval = new String[3];
        retval[0] = null;
        retval[1] = null;
        retval[2] = null;

        int counter = 0;
        String strPrepStmt = 
            " SELECT  pap.position_id " + "       ,pap.name position_name " + 
            "  FROM   per_all_positions pap " + 
            "        ,per_all_assignments_f paaf " + " WHERE 1=1 " + 
            "    AND pap.position_id = paaf.position_id " + 
            "    AND paaf.primary_flag = 'Y' " + 
            "    AND TRUNC(SYSDATE) >= paaf.effective_start_date " + 
            "    AND TRUNC(SYSDATE) <= paaf.effective_end_date " + 
            "    AND TRUNC(SYSDATE) BETWEEN NVL(TRUNC(pap.date_effective), TRUNC(SYSDATE)) " + 
            "                           AND NVL(TRUNC(pap.date_end), TO_DATE('31/12/4712', 'DD/MM/YYYY')) " + 
            "    AND paaf.person_id = ? ";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        try {
            prepStmt = 
                    pConnection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                 ResultSet.CONCUR_READ_ONLY);
            prepStmt.setDouble(1, new Double(strPersonID));
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                retval[0] = "SUCESFULL";
                retval[1] = resultSet.getString("position_id");
                retval[2] = resultSet.getString("position_name");
                counter = counter + 1;

            }
        } catch (SQLException sqle) {
            retval[0] = 
                    "EXCEPTION al obtener el puesto de asignacion:" + sqle.getErrorCode() + 
                    " , " + sqle.getMessage();
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);


        if (counter > 1) {
            retval[0] = 
                    "EXCEPTION al obtener el puesto de asignacion: mas de un registro.";
        }

        if (0 == counter) {
            retval[0] = 
                    "EXCEPTION al obtener el puesto de asignacion: No se encontraron registros.";
        }

        return retval;

    }


    /**
     * Metodo que recupera la informacion del grupo de pago. "Proyecto Historico Tallas"
     * @param strPersonID
     * @param pConnection
     * @return
     */
    private String[] getPayrollInfo(String strPersonID, 
                                    Connection pConnection) {
        String[] retval = new String[3];
        retval[0] = null;
        retval[1] = null;
        retval[2] = null;

        int counter = 0;
        String strPrepStmt = 
            " select papf.payroll_id " + "      ,papf.payroll_name " + 
            " from pay_all_payrolls_f papf " + 
            "     ,per_all_assignments_f paaf " + 
            " where trunc(sysdate) between papf.effective_start_date " + 
            "                       and papf.effective_end_date " + 
            "  and trunc(sysdate)  between paaf.effective_start_date " + 
            "                       and paaf.effective_end_date " + 
            "  and paaf.payroll_id = papf.payroll_id " + 
            "    AND paaf.primary_flag = 'Y' " + "  and paaf.person_id = ? ";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        try {
            prepStmt = 
                    pConnection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                 ResultSet.CONCUR_READ_ONLY);
            prepStmt.setDouble(1, new Double(strPersonID));
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                retval[0] = "SUCESFULL";
                retval[1] = resultSet.getString("payroll_id");
                retval[2] = resultSet.getString("payroll_name");
                counter = counter + 1;
            }
        } catch (SQLException sqle) {
            retval[0] = 
                    "EXCEPTION al obtener el grupo de pago de asignacion:" + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);


        if (counter > 1) {
            retval[0] = 
                    "EXCEPTION al obtener el grupo de pago de asignacion: mas de un registro.";
        }

        return retval;

    }

    /**
     * Metodo que recupera informacion de contabilidad  "Proyecto Historico Tallas"
     * @param strPersonID
     * @param pConnection
     * @return
     */
    private String[] getAccountingInfo(String strPersonID, 
                                       Connection pConnection) {

        String[] retval = new String[5];
        retval[0] = null;
        retval[1] = null;
        retval[2] = null;
        retval[3] = null;
        retval[4] = null;

        int counter = 0;
        String strPrepStmt = 
            " SELECT gcc.code_combination_id " + "   ,gcc.segment4 station " + 
            "   ,gcc.segment1||'-'|| " + "    gcc.segment2||'-'|| " + 
            "    gcc.segment3||'-'|| " + "    gcc.segment4||'-'|| " + 
            "    gcc.segment5||'-'|| " + "    gcc.segment6||'-'|| " + 
            "    gcc.segment7||'-'|| " + 
            "    gcc.segment8 account_combination " + 
            "   ,gcc.segment3 center_cost " + 
            " FROM per_all_assignments_f paaf " + 
            "    ,gl_code_combinations gcc " + " WHERE 1=1 " + 
            "    AND paaf.default_code_comb_id = gcc.code_combination_id " + 
            "    AND paaf.primary_flag = 'Y' " + 
            "    AND TRUNC(SYSDATE) >= paaf.effective_start_date " + 
            "    AND TRUNC(SYSDATE) <= paaf.effective_end_date " + 
            "    AND paaf.person_id = ? ";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;


        try {
            prepStmt = 
                    pConnection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                 ResultSet.CONCUR_READ_ONLY);
            prepStmt.setDouble(1, new Double(strPersonID));
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                retval[0] = "SUCESFULL";
                retval[1] = resultSet.getString("code_combination_id");
                retval[2] = resultSet.getString("station");
                retval[3] = resultSet.getString("account_combination");
                retval[4] = resultSet.getString("center_cost");
                counter = counter + 1;
            }

        } catch (SQLException sqle) {
            retval[0] = 
                    "EXCEPTION al obtener informacion de contabilidad de la asignacion:" + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);

        if (counter > 1) {
            retval[0] = 
                    "EXCEPTION al obtener informacion de contabilidad de la asignacion: Mas de un registro.";
        }

        if (0 == counter) {
            retval[0] = 
                    "EXCEPTION El empleado no tiene asignado una cuenta contable.";
        }

        return retval;

    }

    /**
     * Metodo que recupera informacion le localidad y Zona
     * @param strPersonID
     * @param pConnection
     * @return
     */
    private String[] getHrLocationZonaInfo(String strPersonID, 
                                           Connection pConnection) {

        String[] retval = new String[4];
        retval[0] = null;
        retval[1] = null;
        retval[2] = null;
        retval[3] = null;

        int counter = 0;
        String strPrepStmt = 
            " select  hlt.LOCATION_CODE " + "     ,hlt.DESCRIPTION LOCATION_DESC " + 
            "     ,pucif.value zona " + " from   per_all_assignments_f paaf " + 
            "      ,hr_locations_all_tl hlt " + 
            "      ,pay_user_column_instances_f pucif " + 
            "      ,pay_user_tables put " + "      ,pay_user_rows_f purf " + 
            "      ,pay_user_columns puc " + " where 1=1  " + 
            " and paaf.location_id = hlt.location_id " + 
            " and trunc(sysdate) " + 
            " between paaf.effective_start_date and paaf.effective_end_date " + 
            " and hlt.language =  SYS_CONTEXT('USER', 'LANG')  " + 
            "  and put.user_table_name = 'XXGAM_PARAMETROS_UNIFORMES' " + 
            " and put.business_group_id = 81 " + 
            " and put.user_table_id = purf.user_table_id " + 
            " and put.user_table_id = puc.user_table_id " + 
            " and puc.user_column_name = 'Localidad' " + 
            " and pucif.user_row_id = purf.user_row_id " + 
            " and pucif.user_column_id = puc.user_column_id " + 
            " and trunc(sysdate) between purf.effective_start_date " + 
            "                        and purf.effective_end_date " + 
            " and trunc(sysdate) between pucif.effective_start_date " + 
            "                        and pucif.effective_end_date" + 
            " and purf.row_low_range_or_name = substr (hlt.location_code, 0, 3) " + 
            "    AND paaf.primary_flag = 'Y' " + " and paaf.person_id = ? ";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;


        try {
            prepStmt = 
                    pConnection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                 ResultSet.CONCUR_READ_ONLY);
            prepStmt.setDouble(1, new Double(strPersonID));
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                retval[0] = "SUCESFULL";
                retval[1] = resultSet.getString("LOCATION_CODE");
                retval[2] = resultSet.getString("LOCATION_DESC");
                retval[3] = resultSet.getString("zona");
                counter = counter + 1;
            }

        } catch (SQLException sqle) {
            retval[0] = 
                    "EXCEPTION al obtener localidad y zona del empleado:" + sqle.getErrorCode() + 
                    " , " + sqle.getMessage();
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);

        if (counter > 1) {
            retval[0] = 
                    "EXCEPTION al obtener localidad y zona del empleado: Mas de un registro.";
        }

        if (0 == counter) {
            retval[0] = 
                    "EXCEPTION al obtener localidad y zona del empleado: No se encontraron registros.";
        }

        return retval;

    }

    /**
     * Metodo que valida si es necesario agregar un registro al View Object 
     * xXGamInvSoliVOImpl "Proyecto Historico Tallas"
     * @return
     */
    public String valFillSolCrearCabeInfo() {
        String retval = "Y";
        xXGamInvSoliVOImpl InvSoliVOImpl = getxXGamInvSoliVO1();
        xXGamInvSoliDtlVOImpl InvSoliDtlVOImpl = getxXGamInvSoliDtlVO1();
        if (null != InvSoliVOImpl) {
            System.out.println("FetchedRowCount InvSoliVO:" + 
                               InvSoliVOImpl.getFetchedRowCount());
            if (InvSoliVOImpl.getFetchedRowCount() > 0) {

                retval = "N";
                return retval;
            }
        }
        return retval;
    }

    /**
     * Metodo que recupera los kits disponibles de un empleado en 
     * funcion del person_id, HrOrganization, Gender, y Position
     * @return
     */
    public String fillAvailableKitsInfo() {
        String retval = null;
        xXGamInvSoliVOImpl InvSoliVOImpl = getxXGamInvSoliVO1();
        xXGamInvSoliVORowImpl InvSoliVORowImpl = null;
        String strPersonID = null;
        int counter = 0;
        String strAvailableKits = "";
        String strShortPosition = null;
        String strShortOrganization = null;
        String strSex = null;
        String strShortPayroll = null;
        String strZona = null;
        String strInvAssignExcept = null;

        if (null != InvSoliVOImpl) {
            InvSoliVORowImpl = 
                    (xXGamInvSoliVORowImpl)InvSoliVOImpl.getCurrentRow();
            if (null != InvSoliVORowImpl) {
                strPersonID = InvSoliVORowImpl.getPersonId().toString();
                strShortPosition = InvSoliVORowImpl.getCategory();
                strInvAssignExcept = 
                        InvSoliVORowImpl.getXxGamInvAssignExcept();
            }
        }

        if (null == strPersonID || "".equals(strPersonID)) {
            retval = "EXCEPTION No se pudo recupera el attributo PersonID.";
            return retval;
        }

        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();

        String[] arrayOrganizationInfo = 
            getHROrganizationInfo(strPersonID, connection);
        if (null != arrayOrganizationInfo[0]) {
            if (arrayOrganizationInfo[0].contains("EXCEPTION")) {
                return arrayOrganizationInfo[0];
            }
            strShortOrganization = arrayOrganizationInfo[2].substring(0, 2);
            /** OrganizationID  arrayOrganizationInfo[1]   **/
            /** Organization    arrayOrganizationInfo[2]   **/
        }

        String[] arrayFulNameEmpNumSex = 
            getFulNameEmpNumSex(strPersonID, connection);
        if (null != arrayFulNameEmpNumSex[0]) {
            if (arrayFulNameEmpNumSex[0].contains("EXCEPTION")) {
                return arrayFulNameEmpNumSex[0];
            }
            strSex = arrayFulNameEmpNumSex[3];
            /** PersonFullName  arrayFulNameEmpNumSex[1] **/
            /** EmployeeNumber  arrayFulNameEmpNumSex[2] **/
            /** Sex             arrayFulNameEmpNumSex[3] **/
            /** rfc             arrayFulNameEmpNumSex[4] **/
        }

        String[] arrayPayrollInfo = getPayrollInfo(strPersonID, connection);
        if (null != arrayPayrollInfo[0]) {
            if (arrayPayrollInfo[0].contains("EXCEPTION")) {
                return arrayPayrollInfo[0];
            }
            strShortPayroll = arrayPayrollInfo[2].substring(0, 6);
            /** PayrollID  arrayPayrollInfo[1]    **/
            /** Payroll    arrayPayrollInfo[2]    **/
        }

        String[] arrayPositionInfo = getPositionInfo(strPersonID, connection);
        if (null != arrayPositionInfo[0]) {
            if (arrayPositionInfo[0].contains("EXCEPTION")) {
                return arrayPositionInfo[0];
            }
            strShortPosition = 
                    arrayPositionInfo[2].substring(arrayPositionInfo[2].indexOf('.', 
                                                                                3) + 
                                                   1);
            if (null == InvSoliVORowImpl.getCategory() || 
                "".equals(InvSoliVORowImpl.getCategory())) {
                InvSoliVORowImpl.setCategory(strShortPosition);
            }
            /** PositionID   arrayPositionInfo[1] **/
            /** Position     arrayPositionInfo[2]   **/
        }

        String[] arrayHrLocationZonaInfo = 
            getHrLocationZonaInfo(strPersonID, connection);
        if (null != arrayHrLocationZonaInfo[0]) {
            if (arrayHrLocationZonaInfo[0].contains("EXCEPTION")) {
                return arrayHrLocationZonaInfo[0];
            }
            strZona = arrayHrLocationZonaInfo[3];
            /** HrLocationCode  arrayHrLocationZonaInfo[1]  **/
            /** HrLocationDesc  arrayHrLocationZonaInfo[2]  **/
            /** Zona            arrayHrLocationZonaInfo[3]  **/
        }

        System.out.println("strPersonID:" + strPersonID);
        System.out.println("strShortOrganization:" + strShortOrganization);
        System.out.println("strSex:" + strSex);
        System.out.println("strShortPayroll:" + strShortPayroll);
        System.out.println("strShortPosition:" + strShortPosition);
        System.out.println("strZona:" + strZona);
        System.out.println("strInvAssignExcept:" + strInvAssignExcept);

        String strPrepStmt =
            /* "    and substr(flv.meaning,instr(flv.meaning,'.')+1,6) = ? " */
            " select  substr(flv.description, instr(flv.description, '.', 1)+1) kit " + 
            "    from  fnd_lookup_values flv " + "   where  1=1 " + 
            "    AND flv.lookup_type = 'XXGAM_ASIG_KIT_UNIF_" + 
            strShortOrganization + "' " + "    AND flv.enabled_flag = 'Y' " + 
            "    AND flv.language = SYS_CONTEXT('USER', 'LANG') " + 
            "    and trunc(sysdate) between nvl(flv.start_date_active,trunc(sysdate)) " + 
            "                           and nvl(flv.end_date_active,TO_DATE('31/12/4712', 'DD/MM/YYYY')) " + 
            "    and substr(flv.description, 0, instr(flv.description, '.', 1)) = ? " + 
            "    AND substr(flv.lookup_code, instr(flv.lookup_code, '.', 1, 2)+1) = ? " + 
            "    and substr(flv.meaning,instr(flv.meaning,'.')+1) = ? " + 
            "    and flv.lookup_code like ?";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        try {
            prepStmt = 
                    connection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                ResultSet.CONCUR_READ_ONLY);
            prepStmt.setString(1, strShortPosition + ".");
            prepStmt.setString(2, strSex);
            prepStmt.setString(3, strShortPayroll + "." + strZona);
            prepStmt.setString(4, "%" + strInvAssignExcept + "%");
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("kit"));
                counter = counter + 1;
                strAvailableKits = 
                        strAvailableKits + "'" + resultSet.getString("kit") + 
                        "',";
            }
        } catch (SQLException sqle) {
            retval = 
                    "EXCEPTION al obtener los kits disponibles en funcion de organizacion, puesto, genero. " + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);
        if (0 == counter) {
            retval = 
                    "EXCEPTION al obtener los kits disponibles en funcion de organizacion, puesto, genero: No se encontraron registros.";
            return retval;
        } else {
            retval = strAvailableKits;
            InvSoliVORowImpl.setKitCod(strAvailableKits);
        }

        return retval;
    }

    /**
     * Metodo que recupera la organizacion a la que pertence un empleado 
     * dentro de HR "Proyecto Historico Tallas"
     * @param strPersonID
     * @param pConnection
     * @return
     */
    private String[] getHROrganizationInfo(String strPersonID, 
                                           Connection pConnection) {
        String[] retval = new String[3];
        retval[0] = null;
        retval[1] = null;
        retval[2] = null;

        int counter = 0;
        String strPrepStmt = 
            " SELECT  hrao.organization_id  org_id   " + "        ,hrao.name             org_name " + 
            " FROM  hr_all_organization_units   hrao " + 
            "      ,per_all_assignments_f       paaf " + 
            "      ,per_assignment_status_types past " + 
            " WHERE  hrao.organization_id = paaf.organization_id " + 
            " AND  paaf.person_id = ? " + 
            " AND  TRUNC(SYSDATE) BETWEEN paaf.effective_start_date " + 
            "                        AND paaf.effective_end_date " + 
            " AND  paaf.assignment_status_type_id = past.assignment_status_type_id " + 
            " and paaf.primary_flag = 'Y'  ";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        try {
            prepStmt = 
                    pConnection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                 ResultSet.CONCUR_READ_ONLY);
            prepStmt.setDouble(1, new Double(strPersonID));
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                retval[0] = "SUCESFULL";
                retval[1] = resultSet.getString("org_id");
                retval[2] = resultSet.getString("org_name");
                counter = counter + 1;

            }
        } catch (SQLException sqle) {
            retval[0] = 
                    "EXCEPTION al obtener la organizacion:" + sqle.getErrorCode() + 
                    " , " + sqle.getMessage();
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);


        if (counter > 1) {
            retval[0] = 
                    "EXCEPTION al obtener la organizacion: mas de un registro.";
        }

        return retval;
    }

    /**
     * Metodo que valida la informacion en el lookup GAM_HR_UNIFORMES
     * y ademas pobla el view Object xXGamAvailableKitsVO
     * "Proyecto Historico Tallas"
     * @param strGetAvailableKitsInfo
     * @return
     */
    public String valGamHrUniformesInfo(String strGetAvailableKitsInfo) {
        String retval = null;
        xXGamInvAvailableKitsVOImpl InvAvailableKitsVOImpl = 
            getxXGamInvAvailableKitsVO1();
        ViewCriteria vc = InvAvailableKitsVOImpl.createViewCriteria();
        ViewCriteriaRow vcr1 = vc.createViewCriteriaRow();
        vcr1.setAttribute("KitCode", 
                          "IN (" + strGetAvailableKitsInfo.substring(0, 
                                                                     strGetAvailableKitsInfo.length() - 
                                                                     1) + ")");
        vc.add(vcr1);
        InvAvailableKitsVOImpl.applyViewCriteria(vc);
        InvAvailableKitsVOImpl.executeQuery();
        while (InvAvailableKitsVOImpl.hasNext()) {
            InvAvailableKitsVOImpl.next();
        }

        return retval;

    }


    /**
     * Metodo que deposita informacion en el View Object xXGamV2InvSoliDtlVO
     * "Proyecto Historico Tallas"
     * @param pStrKitCode
     * @param pNumPersonID
     * @param pStrKitID
     * @return
     */
    public String fillV2InvSoliDtlVOInfo(OAPageContext pageContext, 
                                         String pStrKitCode, 
                                         Number pNumPersonID, 
                                         String pStrKitID) {
        String retval = null;
        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();
        xXGamInvSoliVOImpl InvSoliVOImpl = getxXGamInvSoliVO1();
        xXGamInvSoliVORowImpl InvSoliVORowImpl = null;
        String strEmployeeNumber = null;
        String strAdscription = null;
        String strInvAssignExcept = null;
        oracle.jbo.domain.Number numKitID = null;
        xXGamV2InvSoliDtlVOImpl V2InvSoliDtlVOImpl = getxXGamV2InvSoliDtlVO1();
        xXGamV2InvSoliDtlVORowImpl V2InvSoliDtlVORowImpl = null;

        String strIsSuperUser = "N";
        System.out.println("XXGAM_INV_USUARIO_UNIFORME Value:" + 
                           pageContext.getProfile("XXGAM_INV_USUARIO_UNIFORME"));
        if (null != pageContext.getProfile("XXGAM_INV_USUARIO_UNIFORME") && 
            !"".equals(pageContext.getProfile("XXGAM_INV_USUARIO_UNIFORME"))) {
            strIsSuperUser = 
                    pageContext.getProfile("XXGAM_INV_USUARIO_UNIFORME");
            if ("Y".equals(strIsSuperUser)) {
                System.out.println("Se trata de un Super Usuario Uniformes..");
            } else if ("N".equals(strIsSuperUser)) {
                System.out.println("Se trata de un Usuario Uniformes..");
            } else {
                retval = 
                        "EXCEPTION Valor:" + strIsSuperUser + " no valido para el Perfil XXGAM_INV_USUARIO_UNIFORME.";
                return retval;
            }
        }

        System.out.println("strIsSuperUser:" + strIsSuperUser);


        if (null != InvSoliVOImpl) {
            InvSoliVORowImpl = 
                    (xXGamInvSoliVORowImpl)InvSoliVOImpl.getCurrentRow();
            strEmployeeNumber = InvSoliVORowImpl.getEmployeeNumber();
            strAdscription = InvSoliVORowImpl.getAdscription();
            strInvAssignExcept = InvSoliVORowImpl.getXxGamInvAssignExcept();
        }

        if (null != pStrKitID) {
            try {
                numKitID = new oracle.jbo.domain.Number(pStrKitID);
            } catch (SQLException sqle) {
                retval = 
                        "EXCEPTION al convertir pStrKitID:" + pStrKitID + " a Number";
            }
        }

        String strPrepStmt = 
            " select  xid.dota_id " + "          ,xid.uniform_type_cod " + 
            "          ,xid.planta_qty " + "          ,xid.event_qty " + 
            "          ,xid.measure_unit_cod " + "          ,xid.cycle_cod " + 
            "          ,xid.nomenclature " + "          ,xid.sust_flag " + 
            "          ,xid.np_cod " + 
            "          ,( select history.talla_nbr " + 
            "               from xxgam_inv_siz_unif_history history " + 
            "              where history.person_id = " + 
            pNumPersonID.toString() + 
            "                and history.kit_cod = '" + pStrKitCode + "'" + 
            "                and history.dota_id = xid.dota_id " + 
            "                and history.last_update_date in (  select max(tmp.last_update_date)" + 
            "                                                     from xxgam_inv_siz_unif_history tmp   " + 
            "                                                    where tmp.person_id = " + 
            pNumPersonID.toString() + 
            "                                                      and tmp.kit_cod = '" + 
            pStrKitCode + "'" + 
            "                                                      and tmp.dota_id = xid.dota_id  " + 
            "                                                      ) " + 
            "                and rownum=1  ) talla_nbr " + 
            "          ,( select history.talla_id  " + 
            "               from xxgam_inv_siz_unif_history history " + 
            "              where history.person_id = " + 
            pNumPersonID.toString() + 
            "                and history.kit_cod = '" + pStrKitCode + "'" + 
            "                and history.dota_id = xid.dota_id " + 
            "                and history.last_update_date in (  select max(tmp.last_update_date)" + 
            "                                                     from xxgam_inv_siz_unif_history tmp   " + 
            "                                                    where tmp.person_id = " + 
            pNumPersonID.toString() + 
            "                                                      and tmp.kit_cod = '" + 
            pStrKitCode + "'" + 
            "                                                      and tmp.dota_id = xid.dota_id  " + 
            "                                                      ) " + 
            "               and rownum=1 ) talla_id      " + 
            "          ,( select history.inventory_id " + 
            "               from xxgam_inv_siz_unif_history history " + 
            "              where history.person_id = " + 
            pNumPersonID.toString() + 
            "                and history.kit_cod = '" + pStrKitCode + "'" + 
            "                and history.dota_id = xid.dota_id" + 
            "                and history.last_update_date in (  select max(tmp.last_update_date)" + 
            "                                                     from xxgam_inv_siz_unif_history tmp   " + 
            "                                                    where tmp.person_id = " + 
            pNumPersonID.toString() + 
            "                                                      and tmp.kit_cod = '" + 
            pStrKitCode + "'" + 
            "                                                      and tmp.dota_id = xid.dota_id  " + 
            "                                                      ) " + 
            "            and rownum=1 ) inventory_id     " + 
            "         ,( select history.inv_org_id  " + 
            "               from xxgam_inv_siz_unif_history history " + 
            "              where history.person_id = " + 
            pNumPersonID.toString() + 
            "                and history.kit_cod = '" + pStrKitCode + "'" + 
            "                and history.dota_id = xid.dota_id " + 
            "                and history.last_update_date in (  select max(tmp.last_update_date)" + 
            "                                                     from xxgam_inv_siz_unif_history tmp   " + 
            "                                                    where tmp.person_id = " + 
            pNumPersonID.toString() + 
            "                                                      and tmp.kit_cod = '" + 
            pStrKitCode + "'" + 
            "                                                      and tmp.dota_id = xid.dota_id  " + 
            "                                                      ) " + 
            "           and rownum=1 ) inv_org_id        " + 
            "          ,NVL(dota_default,'X')  dota_default " + 
            "    from  xxgam_inv_dota xid " + "   where  xid.kit_cod = '" + 
            pStrKitCode + "'" + "     and NVL(dota_default,'X') in ('Y') ";

        /*** Regla de Negocio and NVL(dota_default,'X') in ('Y')  11042017
                           *** Para considerar solo aquellos registros marcados como default
                           *** **/

        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        try {
            prepStmt = 
                    connection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                ResultSet.CONCUR_READ_ONLY);
            /** prepStmt.setDouble(1,pNumPersonID.doubleValue()); **/
            /** prepStmt.setString(2,pStrKitCode); **/
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                if (null != V2InvSoliDtlVOImpl) {
                    if (!V2InvSoliDtlVOImpl.isPreparedForExecution()) {
                        V2InvSoliDtlVOImpl.executeQuery();
                    }
                    V2InvSoliDtlVORowImpl = 
                            (xXGamV2InvSoliDtlVORowImpl)V2InvSoliDtlVOImpl.createRow();


                    V2InvSoliDtlVORowImpl.setUniformTypeCod(resultSet.getString("uniform_type_cod"));

                    V2InvSoliDtlVORowImpl.setPlantaQty(new oracle.jbo.domain.Number(resultSet.getDouble("planta_qty")));
                    V2InvSoliDtlVORowImpl.setEventQty(new oracle.jbo.domain.Number(resultSet.getDouble("event_qty")));
                    if ("P".equalsIgnoreCase(strInvAssignExcept)) {
                        V2InvSoliDtlVORowImpl.setQtyNbr(new oracle.jbo.domain.Number(resultSet.getDouble("planta_qty")));
                    } else if ("E".equalsIgnoreCase(strInvAssignExcept)) {
                        V2InvSoliDtlVORowImpl.setQtyNbr(new oracle.jbo.domain.Number(resultSet.getDouble("event_qty")));
                    }
                    V2InvSoliDtlVORowImpl.setMeasureUnitCod(resultSet.getString("measure_unit_cod"));
                    V2InvSoliDtlVORowImpl.setCycleCod(resultSet.getString("cycle_cod"));
                    V2InvSoliDtlVORowImpl.setNomenclature(resultSet.getString("nomenclature"));

                    if (0d != resultSet.getDouble("talla_id")) {
                        if (null == resultSet.getString("talla_nbr") || 
                            "".equals(resultSet.getString("talla_nbr"))) {
                            retval = 
                                    "EXCEPTION se tiene el talla_id:" + resultSet.getDouble("talla_id") + 
                                    " pero no se tiene el talla_nbr";
                            return retval;
                        }
                    }

                    V2InvSoliDtlVORowImpl.setNpCod(resultSet.getString("np_cod"));
                    V2InvSoliDtlVORowImpl.setTallaNbr(resultSet.getString("talla_nbr"));
                    V2InvSoliDtlVORowImpl.setTallaId(new oracle.jbo.domain.Number(resultSet.getDouble("talla_id")));
                    V2InvSoliDtlVORowImpl.setInventoryId(new oracle.jbo.domain.Number(resultSet.getDouble("inventory_id")));
                    V2InvSoliDtlVORowImpl.setInvOrgId(new oracle.jbo.domain.Number(resultSet.getDouble("inv_org_id")));
                    V2InvSoliDtlVORowImpl.setDotaId(new oracle.jbo.domain.Number(resultSet.getDouble("dota_id")));


                    /******************** Validar Ciclo Por Kit y Dota ID
          oracle.jbo.domain.Number numValidaCiclo = this.callValidarCiclo(V2InvSoliDtlVORowImpl.getDotaId()
                                                                         ,strEmployeeNumber
                                                                         ,numKitID
                                                                         ,V2InvSoliDtlVORowImpl.getCycleCod());
          ***************************************************************************************************/

                    /** Regla de Negocio: Validar el ciclo de entrega de cada una de las prendas por nomenclatura **/

                    oracle.jbo.domain.Number numValidaCicloByNomen = 
                        this.callValidarCicloByNomen(InvSoliVORowImpl.getPersonId(), 
                                                     resultSet.getString("nomenclature"), 
                                                     resultSet.getString("cycle_cod"));

                    if (0 == numValidaCicloByNomen.compareTo(1)) {
                        V2InvSoliDtlVORowImpl.setTallaDevDisabled(false);
                        V2InvSoliDtlVORowImpl.setCantidadDevDisabledFlag("CantidadDevDisabledFlagN");
                    } else {
                        V2InvSoliDtlVORowImpl.setTallaNbr(null);
                        V2InvSoliDtlVORowImpl.setTallaId(null);
                        V2InvSoliDtlVORowImpl.setQtyNbr(null);
                        V2InvSoliDtlVORowImpl.setInventoryId(null);
                        V2InvSoliDtlVORowImpl.setTallaDevDisabled(true);
                        V2InvSoliDtlVORowImpl.setCantidadDevDisabledFlag("CantidadDevDisabledFlagY");
                    }

                    /** Regla de Negocio: El campo talla y cantidad deben estar inhabilitados para aquellos usuarios
           * que no tengan el perfil XXGAM_INV_USUARIO_UNIFORME **/
                    if ("N".equals(strIsSuperUser)) {
                        V2InvSoliDtlVORowImpl.setTallaDevDisabled(true);
                        V2InvSoliDtlVORowImpl.setCantidadDevDisabledFlag("CantidadDevDisabledFlagY");
                    }

                    /** Regla de Negocio: Excluir aquellas prendas que contengan configuradas prendas sustitutas **/
                    if ("Y".equals(resultSet.getString("sust_flag"))) {
                        V2InvSoliDtlVORowImpl.setSustitucionSwitcher("V2SustitucionEnabled");
                        V2InvSoliDtlVORowImpl.setTallaNbr(null);
                        V2InvSoliDtlVORowImpl.setTallaId(null);
                        V2InvSoliDtlVORowImpl.setQtyNbr(null);
                        V2InvSoliDtlVORowImpl.setInventoryId(null);
                        V2InvSoliDtlVORowImpl.setTallaDevDisabled(true);
                        V2InvSoliDtlVORowImpl.setCantidadDevDisabledFlag("CantidadDevDisabledFlagY");
                    } else {
                        V2InvSoliDtlVORowImpl.setSustitucionSwitcher("SustitucionEmpty");
                    }


                    /** Regla de Negocio: Las prendas que les correponda una cantidad igual a 0
           ** Deben estar vacias para que los move order no se creen con cantidades en 0**/
                    if (null != V2InvSoliDtlVORowImpl.getQtyNbr()) {
                        if (0 == 
                            V2InvSoliDtlVORowImpl.getQtyNbr().compareTo(0)) {
                            V2InvSoliDtlVORowImpl.setTallaNbr(null);
                            V2InvSoliDtlVORowImpl.setTallaId(null);
                            V2InvSoliDtlVORowImpl.setQtyNbr(null);
                            V2InvSoliDtlVORowImpl.setInventoryId(null);
                        }
                    }

                    /**** Regla de Negocio: no coloque cantidad ni talla si el valor en el campo DOTA_DEFAULT es vacio o N ****/
                    if (null != resultSet.getString("dota_default")) {
                        if ("N".equals(resultSet.getString("dota_default")) || 
                            "X".equals(resultSet.getString("dota_default"))) {
                            V2InvSoliDtlVORowImpl.setTallaNbr(null);
                            V2InvSoliDtlVORowImpl.setTallaId(null);
                            V2InvSoliDtlVORowImpl.setQtyNbr(null);
                            V2InvSoliDtlVORowImpl.setInventoryId(null);
                            /** V2InvSoliDtlVORowImpl.setTallaDevDisabled(true);   ya considerado por el perfil XXGAM_INV_USUARIO_UNIFORME **/
                            /** V2InvSoliDtlVORowImpl.setCantidadDevDisabledFlag("CantidadDevDisabledFlagY"); ya considerado por el perfil XXGAM_INV_USUARIO_UNIFORME **/
                        }
                    }

                    V2InvSoliDtlVOImpl.insertRow(V2InvSoliDtlVORowImpl);
                } /** END if(null!=V2InvSoliDtlVOImpl){ **/
            } /** END  while(resultSet.next()){ **/
        } catch (SQLException sqle) {
            retval = 
                    "EXCEPTION al recuperar informacion de la tabla XXGAM_INV_SIZ_UNIF_HISTORY: " + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
            return retval;
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);

        return retval;
    }

    /**
     * Metodo que limpia el View Object xXGamV2InvSoliDtlVO
     * "Proyecto Historico Tallas"
     */
    public void cleanV2InvSoliDtlVOInfo() {
        xXGamV2InvSoliDtlVOImpl V2InvSoliDtlVOImpl = getxXGamV2InvSoliDtlVO1();
        if (null != V2InvSoliDtlVOImpl) {
            V2InvSoliDtlVOImpl.executeQuery();
            while (V2InvSoliDtlVOImpl.hasNext()) {
                V2InvSoliDtlVOImpl.next();
                V2InvSoliDtlVOImpl.removeCurrentRow();
            }
        }
    }

    /**
     * Metodo que asigna un consecutivo al View Object xXGamInvSoliVOImpl
     * "Proyecto Historico Tallas"
     */
    public String fillSoliIDKitIDInfo(String pStrGrabKitCode) {
        String retval = null;
        String strKitID = null;
        xXGamInvSoliVOImpl InvSoliVOImpl = getxXGamInvSoliVO1();
        xXGamInvSoliVORowImpl InvSoliVORowImpl = null;

        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();


        String strPrepStmt = 
            " select kit_id " + "   from XXGAM_INV_KIT " + "  where kit_cod = ?";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;


        try {
            prepStmt = 
                    connection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                ResultSet.CONCUR_READ_ONLY);
            prepStmt.setString(1, pStrGrabKitCode);
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                strKitID = resultSet.getString("kit_id");
            }
        } catch (SQLException sqle) {
            retval = 
                    "EXCEPTION al obtener el ID del Kit:" + pStrGrabKitCode + "," + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
            return retval;
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);

        System.out.println("InvSoliVOImpl:" + InvSoliVOImpl);

        if (null != InvSoliVOImpl) {
            InvSoliVORowImpl = 
                    (xXGamInvSoliVORowImpl)InvSoliVOImpl.getCurrentRow();

            System.out.println("InvSoliVORowImpl:" + InvSoliVORowImpl);

            oracle.jbo.domain.Number numSoliID = 
                oaDBTransaction.getSequenceValue("XXGAM_INV_SOLI_S");
            oracle.jbo.domain.Number numKitID = null;
            try {
                numKitID = new oracle.jbo.domain.Number(strKitID);
            } catch (SQLException e) {
                retval = 
                        "EXCEPTION al convertir:" + strKitID + " a oracle.jbo.domain.Number";
                return retval;
            }
            retval = 
                    "SoliID:" + numSoliID.toString() + ",KitID:" + numKitID.toString();
            InvSoliVORowImpl.setSoliId(numSoliID);
            InvSoliVORowImpl.setNroSolicitud("UNI-" + numSoliID.toString());
            InvSoliVORowImpl.setStatus("PENDIENTE");

            System.out.println("numKitID:" + numKitID);

            InvSoliVORowImpl.setKitId(numKitID);
            InvSoliVORowImpl.setSoliDate(oaDBTransaction.getCurrentDBDate());
        }

        return retval;

    }

    /**
     * Metodo que asigna un consecutivo al View Object xXGamV2InvSoliDtlVO
     * @return
     * "Proyecto Historico Tallas"
     */
    public String fillSoliDtlIDInfo() {
        String retval = null;
        xXGamInvSoliVOImpl InvSoliVOImpl = getxXGamInvSoliVO1();
        xXGamInvSoliVORowImpl InvSoliVORowImpl = null;
        xXGamInvSoliDtlVOImpl InvSoliDtlVOImpl = getxXGamInvSoliDtlVO1();
        xXGamInvSoliDtlVORowImpl InvSoliDtlVORowImpl = null;
        xXGamV2InvSoliDtlVOImpl V2InvSoliDtlVOImpl = getxXGamV2InvSoliDtlVO1();
        xXGamV2InvSoliDtlVORowImpl V2InvSoliDtlVORowImpl = null;
        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();
        oracle.jbo.domain.Number numSoliDtlID = null;

        if (null != InvSoliVOImpl) {
            InvSoliVORowImpl = 
                    (xXGamInvSoliVORowImpl)InvSoliVOImpl.getCurrentRow();
        }

        RowSetIterator V2InvSoliDtlVOIterator = 
            V2InvSoliDtlVOImpl.createRowSetIterator(null);
        while (V2InvSoliDtlVOIterator.hasNext()) {
            V2InvSoliDtlVORowImpl = 
                    (xXGamV2InvSoliDtlVORowImpl)V2InvSoliDtlVOIterator.next();
            if (null != InvSoliDtlVOImpl) {
                if (!InvSoliDtlVOImpl.isPreparedForExecution()) {
                    /* InvSoliDtlVOImpl.executeQuery(); */
                    InvSoliDtlVOImpl.initQuery("-1");
                }
                numSoliDtlID = 
                        oaDBTransaction.getSequenceValue("XXGAM_INV_SOLI_DTL_S");
                System.out.println("numSoliDtlID:" + numSoliDtlID.toString());
                InvSoliDtlVORowImpl = 
                        (xXGamInvSoliDtlVORowImpl)InvSoliDtlVOImpl.createRow();
                InvSoliDtlVORowImpl.setDotaId(V2InvSoliDtlVORowImpl.getDotaId());
                InvSoliDtlVORowImpl.setUniformTypeCod(V2InvSoliDtlVORowImpl.getUniformTypeCod());
                InvSoliDtlVORowImpl.setNomenclature(V2InvSoliDtlVORowImpl.getNomenclature());
                InvSoliDtlVORowImpl.setMeasureUnitCod(V2InvSoliDtlVORowImpl.getMeasureUnitCod());
                InvSoliDtlVORowImpl.setCycleCod(V2InvSoliDtlVORowImpl.getCycleCod());
                InvSoliDtlVORowImpl.setQtyPlanta(V2InvSoliDtlVORowImpl.getPlantaQty());
                InvSoliDtlVORowImpl.setQtyEventual(V2InvSoliDtlVORowImpl.getEventQty());
                InvSoliDtlVORowImpl.setSustitucionSwitcher(InvSoliDtlVORowImpl.getSustitucionSwitcher());
                V2InvSoliDtlVORowImpl.setStatusDsp("Pendiente");
                InvSoliDtlVORowImpl.setStatus("PENDIENTE");
                InvSoliDtlVORowImpl.setAttribute1(InvSoliVORowImpl.getAdscription());
                /** if("EMPLEADO PLANTA".equals(InvSoliVORowImpl.getAdscription())){ **/
                if ("P".equalsIgnoreCase(InvSoliVORowImpl.getXxGamInvAssignExcept())) {
                    InvSoliDtlVORowImpl.setAttribute2(V2InvSoliDtlVORowImpl.getPlantaQty().toString());
                } else if ("E".equalsIgnoreCase(InvSoliVORowImpl.getXxGamInvAssignExcept())) {
                    InvSoliDtlVORowImpl.setAttribute2(V2InvSoliDtlVORowImpl.getEventQty().toString());
                }
                System.out.println("setQtyNbr:" + 
                                   V2InvSoliDtlVORowImpl.getQtyNbr());
                System.out.println("setTallaId:" + 
                                   V2InvSoliDtlVORowImpl.getTallaId());
                if (null != V2InvSoliDtlVORowImpl.getTallaId() && 
                    (0 != V2InvSoliDtlVORowImpl.getTallaId().compareTo(0))) {
                    InvSoliDtlVORowImpl.setTallaId(V2InvSoliDtlVORowImpl.getTallaId());
                }
                if (null != V2InvSoliDtlVORowImpl.getQtyNbr() && 
                    !"".equals(V2InvSoliDtlVORowImpl.getQtyNbr())) {
                    InvSoliDtlVORowImpl.setQtyNbr(V2InvSoliDtlVORowImpl.getQtyNbr());
                }
                InvSoliDtlVORowImpl.setSoliId(InvSoliVORowImpl.getSoliId());
                InvSoliDtlVORowImpl.setSoliDtlId(numSoliDtlID);
                InvSoliDtlVOImpl.insertRow(InvSoliDtlVORowImpl);
            } /** END if(null!=InvSoliDtlVOImpl){ **/

        } /** END  while(V2InvSoliDtlVOIterator.hasNext()){ **/
        V2InvSoliDtlVOIterator.closeRowSetIterator();

        /******************** Regla de Negocio 11042017    **********/
        /******************** Insertar aquellos que no esten marcados como default ********/

        String strPrepStmt = 
            " select  KIT_ID            " + "        ,KIT_COD           " + 
            "        ,DOTA_ID           " + "        ,UNIFORM_TYPE_COD  " + 
            "        ,PLANTA_QTY        " + "        ,EVENT_QTY         " + 
            "        ,MEASURE_UNIT_COD  " + "        ,CYCLE_COD         " + 
            "        ,NOMENCLATURE      " + "        ,NP_COD            " + 
            "        ,CREATED_BY        " + "        ,CREATION_DATE     " + 
            "        ,LAST_UPDATED_BY   " + "        ,LAST_UPDATE_DATE  " + 
            "        ,LAST_UPDATE_LOGIN " + "        ,SUST_FLAG         " + 
            "        ,DOTA_DEFAULT      " + "    from bolinf.xxgam_inv_dota " + 
            "   where 1=1  " + "     and kit_id = ? " + 
            "     and nvl(DOTA_DEFAULT,'X') in ('N','X') ";

        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        try {
            prepStmt = 
                    connection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                ResultSet.CONCUR_READ_ONLY);
            prepStmt.setDouble(1, InvSoliVORowImpl.getKitId().doubleValue());
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {

                if (null != InvSoliDtlVOImpl) {
                    if (!InvSoliDtlVOImpl.isPreparedForExecution()) {
                        /* InvSoliDtlVOImpl.executeQuery(); */
                        InvSoliDtlVOImpl.initQuery("-1");
                    }
                    numSoliDtlID = 
                            oaDBTransaction.getSequenceValue("XXGAM_INV_SOLI_DTL_S");
                    InvSoliDtlVORowImpl = 
                            (xXGamInvSoliDtlVORowImpl)InvSoliDtlVOImpl.createRow();
                    InvSoliDtlVORowImpl.setDotaId(new oracle.jbo.domain.Number(resultSet.getDouble("DOTA_ID")));
                    InvSoliDtlVORowImpl.setUniformTypeCod(resultSet.getString("UNIFORM_TYPE_COD"));
                    InvSoliDtlVORowImpl.setNomenclature(resultSet.getString("NOMENCLATURE"));
                    InvSoliDtlVORowImpl.setMeasureUnitCod(resultSet.getString("MEASURE_UNIT_COD"));
                    InvSoliDtlVORowImpl.setCycleCod(resultSet.getString("CYCLE_COD"));
                    InvSoliDtlVORowImpl.setQtyPlanta(new oracle.jbo.domain.Number(resultSet.getDouble("PLANTA_QTY")));
                    InvSoliDtlVORowImpl.setQtyEventual(new oracle.jbo.domain.Number(resultSet.getDouble("EVENT_QTY")));
                    InvSoliDtlVORowImpl.setSustitucionSwitcher(InvSoliDtlVORowImpl.getSustitucionSwitcher());

                    InvSoliDtlVORowImpl.setStatus("PENDIENTE");
                    InvSoliDtlVORowImpl.setAttribute1(InvSoliVORowImpl.getAdscription());

                    if ("P".equalsIgnoreCase(InvSoliVORowImpl.getXxGamInvAssignExcept())) {
                        InvSoliDtlVORowImpl.setAttribute2(resultSet.getString("PLANTA_QTY"));
                    } else if ("E".equalsIgnoreCase(InvSoliVORowImpl.getXxGamInvAssignExcept())) {
                        InvSoliDtlVORowImpl.setAttribute2(resultSet.getString("EVENT_QTY"));
                    }
                    /** setTallaId No Aplica **/
                    /** setQtyNbr No Aplica **/
                    InvSoliDtlVORowImpl.setSoliId(InvSoliVORowImpl.getSoliId());
                    InvSoliDtlVORowImpl.setSoliDtlId(numSoliDtlID);

                    InvSoliDtlVOImpl.insertRow(InvSoliDtlVORowImpl);

                } /** END if(null!=InvSoliDtlVOImpl){ **/

            }
        } catch (SQLException sqle) {
            closeResultSet(resultSet);
            closePreparedStatement(prepStmt);
            retval = 
                    "EXCEPTION Insertar aquellos registros que no esten marcados como default." + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
            return retval;
        } /** END Try **/

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);

        retval = "SUCCESS";
        return retval;

    }

    /**
     * Metodo que valida los registros detalle del View Object xXGamV2InvSoliDtlVO
     * "Proyecto Historico Tallas"
     * @return
     */
    public com.sun.java.util.collections.List valAllLinesV2InvSoliDtl() {
        com.sun.java.util.collections.List retval = 
            new com.sun.java.util.collections.ArrayList();
        xXGamV2InvSoliDtlVOImpl V2InvSoliDtlVOImpl = getxXGamV2InvSoliDtlVO1();
        xXGamV2InvSoliDtlVORowImpl V2InvSoliDtlVORowImpl = null;


        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();

        String strPrepStmt = 
            " select xit.talla_id " + "   from xxgam_inv_talla xit " + 
            "  where talla_nbr = ? " + "    and xit.dota_id = ? " + 
            "    and xit.np_cod = ? ";

        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        if (null != V2InvSoliDtlVOImpl) {
            RowSetIterator V2InvSoliDtlIterator = 
                V2InvSoliDtlVOImpl.createRowSetIterator(null);
            while (V2InvSoliDtlIterator.hasNext()) {
                String strTallaNbr = null;
                String strNomenclature = null;
                String strNpCod = null;
                oracle.jbo.domain.Number numDotaID = null;
                String strTallaID = null;

                V2InvSoliDtlVORowImpl = 
                        (xXGamV2InvSoliDtlVORowImpl)V2InvSoliDtlIterator.next();
                strTallaNbr = V2InvSoliDtlVORowImpl.getTallaNbr();
                strNomenclature = V2InvSoliDtlVORowImpl.getNomenclature();
                numDotaID = V2InvSoliDtlVORowImpl.getDotaId();
                strNpCod = V2InvSoliDtlVORowImpl.getNpCod();

                System.out.println("strNomenclature:" + strNomenclature);
                System.out.println("strTallaNbr:" + strTallaNbr);
                System.out.println("numDotaID:" + numDotaID);
                System.out.println("strNpCod:" + strNpCod);

                if (null != strTallaNbr && !"".equals(strTallaNbr) && 
                    (null != numDotaID)) {
                    try {
                        prepStmt = 
                                connection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                            ResultSet.CONCUR_READ_ONLY);
                        prepStmt.setString(1, strTallaNbr);
                        prepStmt.setDouble(2, numDotaID.doubleValue());
                        prepStmt.setString(3, strNpCod);
                        resultSet = prepStmt.executeQuery();
                        if (resultSet.next()) {
                            strTallaID = resultSet.getString("talla_id");
                        }
                    } catch (SQLException sqle) {
                        closeResultSet(resultSet);
                        closePreparedStatement(prepStmt);
                        retval.add(new OAException("EXCEPTION AL validar la talla de la prenda" + 
                                                   sqle.getErrorCode() + 
                                                   " , " + sqle.getMessage(), 
                                                   OAException.ERROR));
                        /* return retval; */
                    } /** END Try **/

                    closeResultSet(resultSet);
                    closePreparedStatement(prepStmt);

                    if (null != strTallaID) {
                        try {
                            V2InvSoliDtlVORowImpl.setTallaId(new oracle.jbo.domain.Number(strTallaID));
                        } catch (SQLException sqle) {
                            retval.add(new OAException("EXCEPTION al convertir:" + 
                                                       strTallaID + 
                                                       " a Number", 
                                                       OAException.ERROR));
                            /* return retval; */
                        }
                    } else {
                        retval.add(new OAException("EXCEPTION La talla:" + 
                                                   strTallaNbr + 
                                                   " de la prenda:" + 
                                                   strNomenclature + 
                                                   " no es valida", 
                                                   OAException.ERROR));
                        /* return retval;  */
                    }

                } /** END If **/
            } /** END  while(V2InvSoliDtlIterator.hasNext()){ **/
            V2InvSoliDtlVOImpl.closeRowSetIterator();

        } /** END if(null!=V2InvSoliDtlVOImpl){ **/

        return retval;
    }

    /**
     * Metodo que valida los montos en caso de que se haya elegido una prenda 
     * "Proyecto Historico Tallas"
     * @return
     */
    public com.sun.java.util.collections.List valAmountsV2InvSoliDtl() {
        com.sun.java.util.collections.List retval = 
            new com.sun.java.util.collections.ArrayList();
        xXGamInvSoliVOImpl InvSoliVOImpl = getxXGamInvSoliVO1();
        xXGamInvSoliVORowImpl InvSoliVORowImpl = null;
        xXGamV2InvSoliDtlVOImpl V2InvSoliDtlVOImpl = getxXGamV2InvSoliDtlVO1();
        xXGamV2InvSoliDtlVORowImpl V2InvSoliDtlVORowImpl = null;
        String strAdscription = null;
        String InvAssignExcept = null;

        if (null != InvSoliVOImpl) {
            InvSoliVORowImpl = 
                    (xXGamInvSoliVORowImpl)InvSoliVOImpl.getCurrentRow();
            strAdscription = InvSoliVORowImpl.getAdscription();
            InvAssignExcept = InvSoliVORowImpl.getXxGamInvAssignExcept();
        }

        if (null != V2InvSoliDtlVOImpl) {
            RowSetIterator V2InvSoliDtlIterator = 
                V2InvSoliDtlVOImpl.createRowSetIterator(null);
            while (V2InvSoliDtlIterator.hasNext()) {
                oracle.jbo.domain.Number numQtyEentered = null;
                oracle.jbo.domain.Number numQtyPermitida = null;
                oracle.jbo.domain.Number numTallaID = null;
                String strTallaNbr = null;
                String strNomenclature = null;
                V2InvSoliDtlVORowImpl = 
                        (xXGamV2InvSoliDtlVORowImpl)V2InvSoliDtlIterator.next();
                numQtyEentered = V2InvSoliDtlVORowImpl.getQtyNbr();
                strTallaNbr = V2InvSoliDtlVORowImpl.getTallaNbr();
                strNomenclature = V2InvSoliDtlVORowImpl.getNomenclature();
                numTallaID = V2InvSoliDtlVORowImpl.getTallaId();
                if ("P".equalsIgnoreCase(InvAssignExcept)) {
                    numQtyPermitida = V2InvSoliDtlVORowImpl.getPlantaQty();
                } else if ("E".equalsIgnoreCase(InvAssignExcept)) {
                    numQtyPermitida = V2InvSoliDtlVORowImpl.getEventQty();
                }
                System.out.println("numQtyEentered:" + numQtyEentered);
                System.out.println("strTallaNbr:" + strTallaNbr);
                System.out.println("strNomenclature:" + strNomenclature);
                System.out.println("numTallaID:" + numTallaID);

                if (null != numQtyEentered) {
                    /********************************** Existen Empleados Eventuales con cantidades igual a 0
         if(0==numQtyEentered.compareTo(0)){
           retval.add(new OAException(strNomenclature+": La cantidad ingresada no debe ser cero.",OAException.ERROR));
           * return retval; *
         }
        ******************************************************************************/
                    if (-1 == numQtyEentered.compareTo(0)) {
                        retval.add(new OAException(strNomenclature + 
                                                   ": La cantidad ingresada debe ser mayor que cero.", 
                                                   OAException.ERROR));
                    }

                    try {
                        java.lang.Integer.parseInt(numQtyEentered.toString());
                    } catch (java.lang.NumberFormatException nfexcp) {
                        retval.add(new OAException(strNomenclature + 
                                                   ": La cantidad ingresada no debe contener decimales.", 
                                                   OAException.ERROR));
                    }

                    if (null == strTallaNbr || "".equals(strTallaNbr)) {
                        retval.add(new OAException(strNomenclature + 
                                                   ": Si ingresa la Cantidad, debe seleccionar la Talla.", 
                                                   OAException.ERROR));
                        /* return retval; */
                    }
                    if (null != numQtyPermitida) {
                        if (numQtyPermitida.compareTo(numQtyEentered) < 0) {
                            retval.add(new OAException(strNomenclature + 
                                                       ": La cantidad ingresada " + 
                                                       numQtyEentered.toString() + 
                                                       " no puede ser mayor que la permitida " + 
                                                       numQtyPermitida.toString() + 
                                                       ".", 
                                                       OAException.ERROR));
                        }
                    }
                } else if (null != numTallaID) {
                    if (!(0 == numTallaID.compareTo(0))) {
                        retval.add(new OAException(strNomenclature + 
                                                   ": Si ingresa la Talla, debe seleccionar la Cantidad.", 
                                                   OAException.ERROR));
                        /* return retval; */
                    }
                }
            } /** End  while(V2InvSoliDtlIterator.hasNext()){ **/
            V2InvSoliDtlIterator.closeRowSetIterator();
        } /** END if(null!=V2InvSoliDtlVOImpl){ **/

        return retval;

    }

    /**
     * Metodo que llama al siguiente procedimiento almacenado 
     * XXGAM_INV_MOVEORDER_PKG.VALIDA_SUSTITUTOS
     * "Proyecto Historico Tallas"
     * @return
     */
    public String callValidarSustitutos() {
        String retval = null;
        xXGamInvSoliVOImpl InvSoliVOImpl = getxXGamInvSoliVO1();
        xXGamInvSoliVORowImpl InvSoliVORowImpl = null;
        oracle.jbo.domain.Number numSoliID = null;
        if (null != InvSoliVOImpl) {
            InvSoliVORowImpl = 
                    (xXGamInvSoliVORowImpl)InvSoliVOImpl.getCurrentRow();
            numSoliID = InvSoliVORowImpl.getSoliId();
            retval = numSoliID.toString();
        }

        String lSql = 
            "BEGIN XXGAM_INV_MOVEORDER_PKG.VALIDA_SUSTITUTOS(p_soli_id => ?); END;";
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)this.getOADBTransaction();
        OracleCallableStatement pstmtSust = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(lSql, 
                                                                                 1);
        try {
            pstmtSust.setDouble(1, numSoliID.doubleValue());
            pstmtSust.execute();
            pstmtSust.close();
        } catch (SQLException sqle1) {
            try {
                pstmtSust.close();
            } catch (SQLException sqle2) {
                throw OAException.wrapperException(sqle2);
            }
            retval = 
                    "EXCEPTION Al validar Sustitutos:" + sqle1.getErrorCode() + 
                    " , " + sqle1.getMessage();
            return retval;
            /** throw OAException.wrapperException(sqle1); **/
        }

        oadbtransactionimpl.commit();
        return retval;
    }

    /**
     * Metodo para validar los ciclos 
     * @param pNumDotaID
     * @param pStrEmployeeNumber
     * @param pNumKitID
     * @param strCicleCod
     * "Proyecto Historico Tallas"
     * @return
     */
    private Number callValidarCiclo(Number pNumDotaID, 
                                    String pStrEmployeeNumber, 
                                    oracle.jbo.domain.Number pNumKitID, 
                                    String strCicleCod) {
        oracle.jbo.domain.Number esValido = null;
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)this.getOADBTransaction();
        String str = 
            " BEGIN ? :=  xxgam_inv_kit_util_pkg.validate_cicle(?,?,?,?); END; ";
        OracleCallableStatement oraclecallablestatement = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(str, 
                                                                                 1);
        try {
            oraclecallablestatement.registerOutParameter(1, Types.DOUBLE);
            oraclecallablestatement.setDouble(2, pNumDotaID.doubleValue());
            oraclecallablestatement.setString(3, pStrEmployeeNumber);
            oraclecallablestatement.setDouble(4, pNumKitID.doubleValue());
            oraclecallablestatement.setString(5, strCicleCod);
            oraclecallablestatement.execute();
            esValido = new Number(oraclecallablestatement.getDouble(1));
        } catch (Exception var15) {
            throw OAException.wrapperException(var15);
        }

        return esValido;

    }

    /**
     * Metodo que obtiene el Kit ID 
     * "Proyecto Historico Tallas" 
     * @param pStrKitCode
     * @return
     */
    public String getKitIDInfo(String pStrKitCode) {
        String retval = null;
        int counter = 0;
        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();

        String strPrepStmt = 
            " select kit_id " + "   from XXGAM_INV_KIT " + "  where kit_cod = ?";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;


        try {
            prepStmt = 
                    connection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                ResultSet.CONCUR_READ_ONLY);
            prepStmt.setString(1, pStrKitCode);
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                counter = counter + 1;
                retval = resultSet.getString("kit_id");
            }
        } catch (SQLException sqle) {
            retval = 
                    "EXCEPTION al obtener el ID del Kit:" + pStrKitCode + "," + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
            return retval;
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);

        if (0 == counter) {
            retval = 
                    "EXCEPTION al obtener el ID del Kit:" + pStrKitCode + " , no se encontraron registros.";
            return retval;
        }

        return retval;

    }

    /**
     * Metodo que valida que el periodo del kit no se encuentre cerrado 
     * @param strKitCode
     * @return
     */
    public String valPeriodActiveKit(String pStrKitCode) {
        String retval = null;
        int counter = 0;
        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();

        java.sql.Date sqlDateStartDateActive = null;
        java.sql.Date sqlDateEndDateActive = null;
        oracle.jbo.domain.Date jboDateSysdate = 
            oaDBTransaction.getCurrentDBDate();
        oracle.jbo.domain.Date jboDateStartActive = null;
        oracle.jbo.domain.Date jboDateEndActive = null;

        String strPrepStmt = 
            " select flv.lookup_code " + "       ,flv.meaning " + 
            "       ,flv.description " + "       ,flv.enabled_flag " + 
            "       ,flv.start_date_active " + "       ,flv.end_date_active " + 
            "   from fnd_lookup_values flv " + 
            "  where flv.lookup_type = 'XXGAM_APERTURA_DE_PERIODOS' " + 
            "    and flv.language = sys_context('USER','LANG') " + 
            "    and flv.meaning = ? ";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        try {
            prepStmt = 
                    connection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                ResultSet.CONCUR_READ_ONLY);
            prepStmt.setString(1, pStrKitCode);
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                counter = counter + 1;
                retval = "SUCCESS";
                sqlDateStartDateActive = 
                        resultSet.getDate("start_date_active");
                sqlDateEndDateActive = resultSet.getDate("end_date_active");
                jboDateStartActive = 
                        new oracle.jbo.domain.Date(sqlDateStartDateActive);
                jboDateEndActive = 
                        new oracle.jbo.domain.Date(sqlDateEndDateActive);
                break;
            }
        } catch (SQLException sqle) {
            retval = 
                    "EXCEPTION al validar que el periodo del kit:" + pStrKitCode + 
                    " no se encuentre cerrado:" + sqle.getErrorCode() + " , " + 
                    sqle.getMessage();
            return retval;
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);

        if (0 == counter) {
            retval = 
                    "EXCEPTION al validar que el periodo del kit:" + pStrKitCode + 
                    " no se encuentre cerrado: No se encontraron registros.";
            return retval;
        }

        System.out.println("jboDateSysdate:" + 
                           jboDateSysdate.dateValue().getTime());
        System.out.println("jboDateStartActive:" + 
                           jboDateStartActive.dateValue().getTime());
        System.out.println("jboDateEndActive:" + 
                           jboDateEndActive.dateValue().getTime());

        if (null != jboDateSysdate && null != jboDateStartActive && 
            null != jboDateEndActive) {
            if (jboDateSysdate.dateValue().getTime() >= 
                jboDateStartActive.dateValue().getTime() && 
                (jboDateSysdate.dateValue().getTime() <= 
                 jboDateEndActive.dateValue().getTime())) {
            } else {
                retval = 
                        "EXCEPTION el periodo:" + jboDateStartActive.toString() + 
                        " - " + jboDateEndActive.toString() + " del Kit:" + 
                        pStrKitCode + "  se encuentra cerrado.";
                return retval;
            }
        }

        return retval;

    }

    /**
     * Metodo que recupera la asignacion por excepcion de Uniformes
     * @param strPersonType
     * @param connection
     * @return
     */
    private String[] getInvAssignExcept(String strPersonType, 
                                        Connection connection) {
        String[] retval = new String[2];
        retval[0] = "SUCCESS";
        retval[1] = null;
        int counter = 0;
        String strPrepStmt = 
            " select flv.lookup_code " + "        ,flv.meaning " + 
            "        ,flv.description " + "        ,flv.language " + 
            "        ,flv.start_date_active " + 
            "        ,flv.end_date_active " + "  from fnd_lookup_values flv " + 
            " where flv.lookup_type = 'XXGAM_INV_ASSIGN_EXCEPT' " + 
            "   and flv.language=sys_context('USER','LANG') " + 
            "   and flv.enabled_flag ='Y' " + 
            "   and trunc(sysdate) between nvl(flv.start_date_active,trunc(sysdate)) " + 
            "   and NVL(flv.end_date_active,to_date('31/12/4712','DD/MM/YYYY')) " + 
            "   and upper(flv.meaning) = upper(?) ";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        try {
            prepStmt = 
                    connection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                ResultSet.CONCUR_READ_ONLY);
            prepStmt.setString(1, strPersonType);
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                counter = counter + 1;
                retval[1] = resultSet.getString("description");
            }
        } catch (SQLException sqle) {
            retval[0] = 
                    "EXCEPTION al validar el tipo de empleado:" + strPersonType + 
                    ", en el lookup XXGAM_INV_ASSIGN_EXCEPT. " + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
            return retval;
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);

        if (0 == counter) {
            retval[0] = 
                    "EXCEPTION al validar el tipo de empleado:" + strPersonType + 
                    ", en el lookup XXGAM_INV_ASSIGN_EXCEPT. No se encontraron registros.";
            return retval;
        }

        if (counter > 1) {
            retval[0] = 
                    "EXCEPTION al validar el tipo de empleado:" + strPersonType + 
                    ", en el lookup XXGAM_INV_ASSIGN_EXCEPT. Mas de un registro.";
            return retval;
        }

        return retval;

    }

    /**
     * Metodo que valida no generar una UNI en el mismo Periodo
     * @param numPersonID
     * @param strKitCode
     * @return
     */
    public String valSoloUnaUNI(Number numPersonID, String strKitCode) {

        xXGamInvSoliVOImpl InvSoliVOImpl = getxXGamInvSoliVO1();
        xXGamInvSoliVORowImpl InvSoliVORowImpl = null;
        String strCategory = null;
        if (null != InvSoliVOImpl) {
            InvSoliVORowImpl = 
                    (xXGamInvSoliVORowImpl)InvSoliVOImpl.getCurrentRow();
            strCategory = InvSoliVORowImpl.getCategory();
        }

        String retval = "";
        int counter = 0;
        String strPrepStmt = 
            "  select xis.NRO_SOLICITUD " + "       ,xis.employee_number " + 
            "       ,xis.person_id " + "   from xxgam_inv_soli xis " + 
            "  where xis.person_id = ? " + "   and exists (select 'Y'    " + 
            "                  from fnd_lookup_values flv  " + 
            "                 where flv.lookup_type = 'XXGAM_APERTURA_DE_PERIODOS'  " + 
            "                   and flv.language = sys_context('USER','LANG')   " + 
            "                   and flv.meaning = ?  " + 
            "                   and trunc(xis.soli_date) between flv.start_date_active and flv.end_date_active  " + 
            "                   )  " + "  and xis.category = ? ";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;
        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();
        try {
            prepStmt = 
                    connection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                ResultSet.CONCUR_READ_ONLY);
            prepStmt.setDouble(1, numPersonID.doubleValue());
            prepStmt.setString(2, strKitCode);
            prepStmt.setString(3, strCategory);
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                counter = counter + 1;
                retval = retval + resultSet.getString("NRO_SOLICITUD");
            }
        } catch (SQLException sqle) {
            retval = 
                    "EXCEPTION al validar solicitudes unicas dentro del periodo en el lookup XXGAM_APERTURA_DE_PERIODOS. " + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
            return retval;
        }

        if (counter >= 1) {
            retval = "EXCEPTION Ya existe una solicitud en el mismo periodo.";
            return retval;
        }

        return retval;

    }

    /**
     * Metodo que valida ciclos por nomenclatura de prenda
     * @param pNumPersonID
     * @param pStrNomenclature
     * @param pStrCycleCod
     * @return
     */
    private Number callValidarCicloByNomen(Number pNumPersonID, 
                                           String pStrNomenclature, 
                                           String pStrCycleCod) {
        oracle.jbo.domain.Number esValido = null;
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)this.getOADBTransaction();
        String str = 
            " BEGIN ? :=  XXGAM_INV_VTASIZ_UTIL_PKG.valida_ciclos_by_nomen(pni_person_id      => ?" + 
            " ,psi_nomenclature   => ?" + " ,psi_cycle_varchar  => ?" + 
            " ); END; ";
        OracleCallableStatement oraclecallablestatement = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(str, 
                                                                                 1);
        try {
            oraclecallablestatement.registerOutParameter(1, Types.DOUBLE);
            oraclecallablestatement.setDouble(2, pNumPersonID.doubleValue());
            oraclecallablestatement.setString(3, pStrNomenclature);
            oraclecallablestatement.setString(4, pStrCycleCod);
            oraclecallablestatement.execute();
            esValido = new Number(oraclecallablestatement.getDouble(1));
        } catch (Exception var15) {
            throw OAException.wrapperException(var15);
        }

        return esValido;

    }


    /**
     * Metodo que llena la informacion de cabecera en la pagina de Super Usuario.
     * @param pageContext
     * @param webBean
     */
    public String fillSolCrearCabeInfoSU(OAPageContext pageContext, 
                                         OAWebBean webBean) {
        String retval = null;
        xXGamV2InvSoliVOImpl V2InvSoliVOImpl = getxXGamV2InvSoliVO1();
        xXGamV2InvSoliVORowImpl V2InvSoliVORowImpl = null;

        oracle.jbo.domain.Number numPersonID = null;
        oracle.jbo.domain.Number numUserID = null;

        OAFormValueBean PersonIdSLBean = 
            (OAFormValueBean)webBean.findChildRecursive("PersonIdSL");
        if (null != PersonIdSLBean) {
            try {
                System.out.println("PersonIdSLBean.getValue():" + 
                                   PersonIdSLBean.getValue());
                System.out.println("PersonIdSLBean.getValue(pageContext):" + 
                                   PersonIdSLBean.getValue(pageContext));
                if (null != PersonIdSLBean.getValue(pageContext)) {
                    numPersonID = 
                            new oracle.jbo.domain.Number(PersonIdSLBean.getValue(pageContext));
                }
            } catch (SQLException sqle) {
                retval = 
                        "EXCEPTION al covertir a numerico el PersonID:" + sqle.getMessage() + 
                        ", " + sqle.getErrorCode();
                return retval;
            }
        }

        OAFormValueBean userIdSLBean = 
            (OAFormValueBean)webBean.findChildRecursive("userIdSL");
        if (null != userIdSLBean) {
            if (null != userIdSLBean.getValue(pageContext)) {
                try {
                    numUserID = 
                            new oracle.jbo.domain.Number(userIdSLBean.getValue(pageContext));
                } catch (SQLException sqle) {
                    retval = 
                            "EXCEPTION al covertir a numerico el UserID:" + sqle.getMessage() + 
                            ", " + sqle.getErrorCode();
                    return retval;
                }
            }
        }

        System.out.println("PersonID:" + numPersonID);
        System.out.println("UserID:" + numUserID);

        if (null == numPersonID || null == numUserID) {
            retval = 
                    "EXCEPTION No se pudieron recuperar las variables numPersonID y numUserID.";
            return retval;
        }

        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();

        if (null != V2InvSoliVOImpl) {
            if (!V2InvSoliVOImpl.isPreparedForExecution()) {
                V2InvSoliVOImpl.setMaxFetchSize(0);
                V2InvSoliVOImpl.executeQuery();
            }


            String[] arrayActiveAssignmentInfo = 
                this.getActiveAssignmentInfo(numPersonID.toString(), 
                                             connection);
            if (null != arrayActiveAssignmentInfo[0]) {
                if (arrayActiveAssignmentInfo[0].contains("EXCEPTION")) {
                    return arrayActiveAssignmentInfo[0];
                }
            }

            String[] arrayFulNameEmpNumSex = 
                getFulNameEmpNumSex(numPersonID.toString(), connection);

            if (null != arrayFulNameEmpNumSex[0]) {
                if (arrayFulNameEmpNumSex[0].contains("EXCEPTION")) {
                    return arrayFulNameEmpNumSex[0];
                }
            }

            String[] arrayPersonTypeID = 
                getPersonTypeID(numPersonID.toString(), connection);
            if (null != arrayPersonTypeID[0]) {
                if (arrayPersonTypeID[0].contains("EXCEPTION")) {
                    return arrayPersonTypeID[0];
                }
            }

            String strPersonType = arrayPersonTypeID[2];
            System.out.println("arrayPersonTypeID[2]:" + arrayPersonTypeID[2]);
            String[] arrayInvAssignExcept = 
                getInvAssignExcept(strPersonType, connection);
            System.out.println("arrayInvAssignExcept[1]:" + 
                               arrayInvAssignExcept[1]);

            if (null != arrayInvAssignExcept[0]) {
                if (arrayInvAssignExcept[0].contains("EXCEPTION")) {
                    return arrayInvAssignExcept[0];
                }
            }

            String[] arrayGradeInfo = 
                getGradeInfo(numPersonID.toString(), connection);
            if (null != arrayGradeInfo[0]) {
                if (arrayGradeInfo[0].contains("EXCEPTION")) {
                    return arrayGradeInfo[0];
                }
            }

            String strShortPosition = null;
            String[] arrayPositionInfo = 
                getPositionInfo(numPersonID.toString(), connection);
            if (null != arrayPositionInfo[0]) {
                if (arrayPositionInfo[0].contains("EXCEPTION")) {
                    return arrayPositionInfo[0];
                    /***********************************************
          arrayPositionInfo[1] = "position_id"
          arrayPositionInfo[2] = "position_name"
          ************************************************/
                }
                strShortPosition = 
                        arrayPositionInfo[2].substring(arrayPositionInfo[2].indexOf('.', 
                                                                                    3) + 
                                                       1);
            }

            String[] arrayAccountingInfo = 
                getAccountingInfo(numPersonID.toString(), connection);
            if (null != arrayAccountingInfo[0]) {
                if (arrayAccountingInfo[0].contains("EXCEPTION")) {
                    return arrayAccountingInfo[0];
                }
            } /** END  if(null!=arrayAccountingInfo[0]){ **/

            oracle.jbo.domain.Date ContDueDate = null;
            oracle.jbo.domain.Date currentDate = 
                oaDBTransaction.getCurrentDBDate();
            String[] arrayContDueDateInfo = 
                getContDueDateInfo(numPersonID.toString(), connection);
            if (null != arrayContDueDateInfo[0]) {
                if (arrayContDueDateInfo[0].contains("EXCEPTION")) {
                    return arrayContDueDateInfo[0];
                }
                /**  FechaTerminoContrato         arrayContDueDateInfo[1] **/
                System.out.println("arrayContDueDateInfo[1]:" + 
                                   arrayContDueDateInfo[1]);
                if (null != arrayContDueDateInfo[1] && 
                    !"".equals(arrayContDueDateInfo[1])) {
                    ContDueDate = 
                            new oracle.jbo.domain.Date(arrayContDueDateInfo[1]);
                }
            }


            V2InvSoliVORowImpl = 
                    (xXGamV2InvSoliVORowImpl)V2InvSoliVOImpl.createRow();

            V2InvSoliVORowImpl.setPersonId(numPersonID);
            V2InvSoliVORowImpl.setUserId(numUserID);

            V2InvSoliVORowImpl.setFullName(arrayFulNameEmpNumSex[1]);
            V2InvSoliVORowImpl.setEmployeeNumber(arrayFulNameEmpNumSex[2]);
            V2InvSoliVORowImpl.setRfc(arrayFulNameEmpNumSex[4]);
            /** PersonFullName  arrayFulNameEmpNumSex[1] **/
            /** EmployeeNumber  arrayFulNameEmpNumSex[2] **/
            /** Sex             arrayFulNameEmpNumSex[3] **/
            /** rfc             arrayFulNameEmpNumSex[4] **/

            V2InvSoliVORowImpl.setAdscription(arrayPersonTypeID[2]);
            /** PersonTypeID arrayPersonTypeID[1] **/
            /** PersonType   arrayPersonTypeID[2] **/
            V2InvSoliVORowImpl.setXxGamInvAssignExcept(arrayInvAssignExcept[1]);

            V2InvSoliVORowImpl.setCategory(arrayGradeInfo[3]);
            /** GradeID        arrayGradeInfo[1] **/
            /** Grade          arrayGradeInfo[2] **/
            /** Categoria_gr   arrayGradeInfo[3] **/

            if (null == V2InvSoliVORowImpl.getCategory() || 
                "".equals(V2InvSoliVORowImpl.getCategory())) {
                V2InvSoliVORowImpl.setCategory(strShortPosition);
            }


            V2InvSoliVORowImpl.setStation(arrayAccountingInfo[2]);
            V2InvSoliVORowImpl.setCostCenter(arrayAccountingInfo[4]);
            V2InvSoliVORowImpl.setExpenseDesc(arrayAccountingInfo[3]);

            try {
                V2InvSoliVORowImpl.setExpenseAcc(new oracle.jbo.domain.Number(arrayAccountingInfo[1]));
            } catch (SQLException sqle) {
                return "EXCEPTION al establecer el Id de la cuenta de gasto:" + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
            }

            /**  CodeCombinationID    arrayAccountingInfo[1] **/
            /**  Station              arrayAccountingInfo[2] **/
            /**  AccountCombination   arrayAccountingInfo[3] **/
            /**  CenterCost           arrayAccountingInfo[4] **/

            V2InvSoliVORowImpl.setFechaSolicitud(currentDate);
            V2InvSoliVORowImpl.setContDueDate(ContDueDate);


            V2InvSoliVOImpl.insertRow(V2InvSoliVORowImpl);

        } /** End if(null!=V2InvSoliVOImpl){ **/

        return retval;
    }

    /**
     * Metodo que recupera la fecha de finalizacion de contrato para informacion 
     * del Super Usuario de Uniformes
     * @param pStrPersonID
     * @param pConnection
     * @return
     */
    private String[] getContDueDateInfo(String pStrPersonID, 
                                        Connection pConnection) {

        String[] retval = new String[2];
        retval[0] = "SUCCESS";
        retval[1] = null;

        int counter = 0;

        String strPrepStmt = 
            " select paaf.date_probation_end  fecha_termino_contrato " + 
            "   from per_all_assignments_f paaf " + 
            "  where paaf.primary_flag = 'Y' " + 
            "    and trunc(sysdate) between paaf.effective_start_date and paaf.effective_end_date " + 
            "    and paaf.person_id = ?";

        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;


        try {
            prepStmt = 
                    pConnection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                 ResultSet.CONCUR_READ_ONLY);
            prepStmt.setDouble(1, new Double(pStrPersonID));
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                retval[0] = "SUCESFULL";
                retval[1] = resultSet.getString("fecha_termino_contrato");
                counter = counter + 1;

            }

        } catch (SQLException sqle) {
            retval[0] = 
                    "EXCEPTION al obtener la fecha de finalizacion de contrato:" + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
            return retval;
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);

        if (0 == counter) {
            retval[0] = 
                    "EXCEPTION al obtener la fecha de finalizacion de contrato: No se encontraron registros.";
            return retval;
        }

        if (counter > 1) {
            retval[0] = 
                    "EXCEPTION al obtener la fecha de finalizacion de contrato: Mas de un Registro.";
            return retval;
        }

        return retval;

    }

    /**
     * Metodo que recupera los kits diponibles de un empleado en la pantalla de
     * Super Usuario de Tallas de Uniformes..
     * @return
     */
    public String getAvailableKitsInfoSU() {
        String retval = null;
        xXGamV2InvSoliVOImpl V2InvSoliVOImpl = getxXGamV2InvSoliVO1();
        xXGamV2InvSoliVORowImpl V2InvSoliVORowImpl = null;
        String strPersonID = null;
        int counter = 0;
        String strAvailableKits = "";
        String strShortPosition = null;
        String strShortOrganization = null;
        String strSex = null;
        String strShortPayroll = null;
        String strZona = null;
        String strInvAssignExcept = null;

        if (null != V2InvSoliVOImpl) {
            V2InvSoliVORowImpl = 
                    (xXGamV2InvSoliVORowImpl)V2InvSoliVOImpl.getCurrentRow();
            if (null != V2InvSoliVORowImpl) {
                strPersonID = V2InvSoliVORowImpl.getPersonId().toString();
                strShortPosition = V2InvSoliVORowImpl.getCategory();
                strInvAssignExcept = 
                        V2InvSoliVORowImpl.getXxGamInvAssignExcept();
            }
        }

        if (null == strPersonID || "".equals(strPersonID)) {
            retval = "EXCEPTION No se pudo recupera el attributo PersonID.";
            return retval;
        }

        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();

        String[] arrayOrganizationInfo = 
            getHROrganizationInfo(strPersonID, connection);
        if (null != arrayOrganizationInfo[0]) {
            if (arrayOrganizationInfo[0].contains("EXCEPTION")) {
                return arrayOrganizationInfo[0];
            }
            strShortOrganization = arrayOrganizationInfo[2].substring(0, 2);
            /** OrganizationID  arrayOrganizationInfo[1]   **/
            /** Organization    arrayOrganizationInfo[2]   **/
        }

        String[] arrayFulNameEmpNumSex = 
            getFulNameEmpNumSex(strPersonID, connection);
        if (null != arrayFulNameEmpNumSex[0]) {
            if (arrayFulNameEmpNumSex[0].contains("EXCEPTION")) {
                return arrayFulNameEmpNumSex[0];
            }
            strSex = arrayFulNameEmpNumSex[3];
            /** PersonFullName  arrayFulNameEmpNumSex[1] **/
            /** EmployeeNumber  arrayFulNameEmpNumSex[2] **/
            /** Sex             arrayFulNameEmpNumSex[3] **/
            /** rfc             arrayFulNameEmpNumSex[4] **/
        }

        String[] arrayPayrollInfo = getPayrollInfo(strPersonID, connection);
        if (null != arrayPayrollInfo[0]) {
            if (arrayPayrollInfo[0].contains("EXCEPTION")) {
                return arrayPayrollInfo[0];
            }
            strShortPayroll = arrayPayrollInfo[2].substring(0, 6);
            /** PayrollID  arrayPayrollInfo[1]    **/
            /** Payroll    arrayPayrollInfo[2]    **/
        }

        String[] arrayPositionInfo = getPositionInfo(strPersonID, connection);
        if (null != arrayPositionInfo[0]) {
            if (arrayPositionInfo[0].contains("EXCEPTION")) {
                return arrayPositionInfo[0];
            }
            strShortPosition = 
                    arrayPositionInfo[2].substring(arrayPositionInfo[2].indexOf('.', 
                                                                                3) + 
                                                   1);
            if (null == V2InvSoliVORowImpl.getCategory() || 
                "".equals(V2InvSoliVORowImpl.getCategory())) {
                V2InvSoliVORowImpl.setCategory(strShortPosition);
            }
            /** PositionID   arrayPositionInfo[1] **/
            /** Position     arrayPositionInfo[2]   **/
        }

        String[] arrayHrLocationZonaInfo = 
            getHrLocationZonaInfo(strPersonID, connection);
        if (null != arrayHrLocationZonaInfo[0]) {
            if (arrayHrLocationZonaInfo[0].contains("EXCEPTION")) {
                return arrayHrLocationZonaInfo[0];
            }
            strZona = arrayHrLocationZonaInfo[3];
            /** HrLocationCode  arrayHrLocationZonaInfo[1]  **/
            /** HrLocationDesc  arrayHrLocationZonaInfo[2]  **/
            /** Zona            arrayHrLocationZonaInfo[3]  **/
        }

        System.out.println("strPersonID:" + strPersonID);
        System.out.println("strShortOrganization:" + strShortOrganization);
        System.out.println("strSex:" + strSex);
        System.out.println("strShortPayroll:" + strShortPayroll);
        System.out.println("strShortPosition:" + strShortPosition);
        System.out.println("strZona:" + strZona);
        System.out.println("strInvAssignExcept:" + strInvAssignExcept);

        String strPrepStmt =
            /* "    and substr(flv.meaning,instr(flv.meaning,'.')+1,6) = ? " */
            " select  substr(flv.description, instr(flv.description, '.', 1)+1) kit " + 
            "    from  fnd_lookup_values flv " + "   where  1=1 " + 
            "    AND flv.lookup_type = 'XXGAM_ASIG_KIT_UNIF_" + 
            strShortOrganization + "' " + "    AND flv.enabled_flag = 'Y' " + 
            "    AND flv.language = SYS_CONTEXT('USER', 'LANG') " + 
            "    and trunc(sysdate) between nvl(flv.start_date_active,trunc(sysdate)) " + 
            "                           and nvl(flv.end_date_active,TO_DATE('31/12/4712', 'DD/MM/YYYY')) " + 
            "    and substr(flv.description, 0, instr(flv.description, '.', 1)) = ? " + 
            "    AND substr(flv.lookup_code, instr(flv.lookup_code, '.', 1, 2)+1) = ? " + 
            "    and substr(flv.meaning,instr(flv.meaning,'.')+1) = ? " + 
            "    and flv.lookup_code like ?";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        try {
            prepStmt = 
                    connection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                ResultSet.CONCUR_READ_ONLY);
            prepStmt.setString(1, strShortPosition + ".");
            prepStmt.setString(2, strSex);
            prepStmt.setString(3, strShortPayroll + "." + strZona);
            prepStmt.setString(4, "%" + strInvAssignExcept + "%");
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("kit"));
                counter = counter + 1;
                strAvailableKits = 
                        strAvailableKits + "'" + resultSet.getString("kit") + 
                        "',";
            }
        } catch (SQLException sqle) {
            retval = 
                    "EXCEPTION al obtener los kits disponibles en funcion de organizacion, puesto, genero. " + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);
        if (0 == counter) {
            retval = 
                    "EXCEPTION al obtener los kits disponibles en funcion de organizacion, puesto, genero: No se encontraron registros.";
            return retval;
        } else {
            retval = strAvailableKits;
            V2InvSoliVORowImpl.setKitCod(strAvailableKits);
        }

        return retval;
    }

    /**
     * Metodo que deposita informacion en el View Object xXGamV2InvSoliDtlVO
     * "Proyecto Historico Tallas"  Responsabilidad Super User
     * @param pStrKitCode
     * @param pNumPersonID
     * @param pStrKitID
     * @return
     */
    public String fillV2InvSoliDtlVOInfoSU(OAPageContext pageContext, 
                                           String pStrKitCode, 
                                           Number pNumPersonID, 
                                           String pStrKitID) {
        String retval = null;
        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();
        xXGamV2InvSoliVOImpl V2InvSoliVOImpl = getxXGamV2InvSoliVO1();
        xXGamV2InvSoliVORowImpl V2InvSoliVORowImpl = null;
        String strEmployeeNumber = null;
        String strAdscription = null;
        String strInvAssignExcept = null;
        oracle.jbo.domain.Number numKitID = null;
        xXGamV2InvSoliDtlVOImpl V2InvSoliDtlVOImpl = getxXGamV2InvSoliDtlVO1();
        xXGamV2InvSoliDtlVORowImpl V2InvSoliDtlVORowImpl = null;

        if (null != V2InvSoliVOImpl) {
            V2InvSoliVORowImpl = 
                    (xXGamV2InvSoliVORowImpl)V2InvSoliVOImpl.getCurrentRow();
            strEmployeeNumber = V2InvSoliVORowImpl.getEmployeeNumber();
            strAdscription = V2InvSoliVORowImpl.getAdscription();
            strInvAssignExcept = V2InvSoliVORowImpl.getXxGamInvAssignExcept();
        }


        if (null != pStrKitID) {
            try {
                numKitID = new oracle.jbo.domain.Number(pStrKitID);
            } catch (SQLException sqle) {
                retval = 
                        "EXCEPTION al convertir pStrKitID:" + pStrKitID + " a Number";
            }
        }

        String strPrepStmt = 
            " select  xid.dota_id " + "          ,xid.uniform_type_cod " + 
            "          ,xid.planta_qty " + "          ,xid.event_qty " + 
            "          ,xid.measure_unit_cod " + "          ,xid.cycle_cod " + 
            "          ,xid.nomenclature " + "          ,xid.sust_flag " + 
            "          ,xid.np_cod " + 
            "          ,( select history.talla_nbr " + 
            "               from xxgam_inv_siz_unif_history history " + 
            "              where history.person_id = " + 
            pNumPersonID.toString() + 
            "                and history.kit_cod = '" + pStrKitCode + "'" + 
            "                and history.dota_id = xid.dota_id " + 
            "                and history.last_update_date in (  select max(tmp.last_update_date)" + 
            "                                                     from xxgam_inv_siz_unif_history tmp   " + 
            "                                                    where tmp.person_id = " + 
            pNumPersonID.toString() + 
            "                                                      and tmp.kit_cod = '" + 
            pStrKitCode + "'" + 
            "                                                      and tmp.dota_id = xid.dota_id  " + 
            "                                                      ) " + 
            "                and rownum=1  ) talla_nbr " + 
            "          ,( select history.talla_id  " + 
            "               from xxgam_inv_siz_unif_history history " + 
            "              where history.person_id = " + 
            pNumPersonID.toString() + 
            "                and history.kit_cod = '" + pStrKitCode + "'" + 
            "                and history.dota_id = xid.dota_id " + 
            "                and history.last_update_date in (  select max(tmp.last_update_date)" + 
            "                                                     from xxgam_inv_siz_unif_history tmp   " + 
            "                                                    where tmp.person_id = " + 
            pNumPersonID.toString() + 
            "                                                      and tmp.kit_cod = '" + 
            pStrKitCode + "'" + 
            "                                                      and tmp.dota_id = xid.dota_id  " + 
            "                                                      ) " + 
            "               and rownum=1 ) talla_id      " + 
            "          ,( select history.inventory_id " + 
            "               from xxgam_inv_siz_unif_history history " + 
            "              where history.person_id = " + 
            pNumPersonID.toString() + 
            "                and history.kit_cod = '" + pStrKitCode + "'" + 
            "                and history.dota_id = xid.dota_id" + 
            "                and history.last_update_date in (  select max(tmp.last_update_date)" + 
            "                                                     from xxgam_inv_siz_unif_history tmp   " + 
            "                                                    where tmp.person_id = " + 
            pNumPersonID.toString() + 
            "                                                      and tmp.kit_cod = '" + 
            pStrKitCode + "'" + 
            "                                                      and tmp.dota_id = xid.dota_id  " + 
            "                                                      ) " + 
            "            and rownum=1 ) inventory_id     " + 
            "         ,( select history.inv_org_id  " + 
            "               from xxgam_inv_siz_unif_history history " + 
            "              where history.person_id = " + 
            pNumPersonID.toString() + 
            "                and history.kit_cod = '" + pStrKitCode + "'" + 
            "                and history.dota_id = xid.dota_id " + 
            "                and history.last_update_date in (  select max(tmp.last_update_date)" + 
            "                                                     from xxgam_inv_siz_unif_history tmp   " + 
            "                                                    where tmp.person_id = " + 
            pNumPersonID.toString() + 
            "                                                      and tmp.kit_cod = '" + 
            pStrKitCode + "'" + 
            "                                                      and tmp.dota_id = xid.dota_id  " + 
            "                                                      ) " + 
            "           and rownum=1 ) inv_org_id        " + 
            "          ,NVL(dota_default,'X')  dota_default " + 
            "    from  xxgam_inv_dota xid " + "   where  xid.kit_cod = '" + 
            pStrKitCode + "'";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;

        try {
            prepStmt = 
                    connection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                ResultSet.CONCUR_READ_ONLY);
            /** prepStmt.setDouble(1,pNumPersonID.doubleValue()); **/
            /** prepStmt.setString(2,pStrKitCode); **/
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                if (null != V2InvSoliDtlVOImpl) {
                    if (!V2InvSoliDtlVOImpl.isPreparedForExecution()) {
                        V2InvSoliDtlVOImpl.executeQuery();
                    }
                    V2InvSoliDtlVORowImpl = 
                            (xXGamV2InvSoliDtlVORowImpl)V2InvSoliDtlVOImpl.createRow();


                    V2InvSoliDtlVORowImpl.setUniformTypeCod(resultSet.getString("uniform_type_cod"));

                    V2InvSoliDtlVORowImpl.setPlantaQty(new oracle.jbo.domain.Number(resultSet.getDouble("planta_qty")));
                    V2InvSoliDtlVORowImpl.setEventQty(new oracle.jbo.domain.Number(resultSet.getDouble("event_qty")));
                    if ("P".equalsIgnoreCase(strInvAssignExcept)) {
                        V2InvSoliDtlVORowImpl.setQtyNbr(new oracle.jbo.domain.Number(resultSet.getDouble("planta_qty")));
                    } else if ("E".equalsIgnoreCase(strInvAssignExcept)) {
                        V2InvSoliDtlVORowImpl.setQtyNbr(new oracle.jbo.domain.Number(resultSet.getDouble("event_qty")));
                    }
                    V2InvSoliDtlVORowImpl.setMeasureUnitCod(resultSet.getString("measure_unit_cod"));
                    V2InvSoliDtlVORowImpl.setCycleCod(resultSet.getString("cycle_cod"));
                    V2InvSoliDtlVORowImpl.setNomenclature(resultSet.getString("nomenclature"));


                    if (0d != resultSet.getDouble("talla_id")) {
                        if (null == resultSet.getString("talla_nbr") || 
                            "".equals(resultSet.getString("talla_nbr"))) {
                            retval = 
                                    "EXCEPTION se tiene el talla_id:" + resultSet.getDouble("talla_id") + 
                                    " pero no se tiene el talla_nbr";
                            return retval;
                        }
                    }

                    V2InvSoliDtlVORowImpl.setNpCod(resultSet.getString("np_cod"));
                    V2InvSoliDtlVORowImpl.setTallaNbr(resultSet.getString("talla_nbr"));
                    V2InvSoliDtlVORowImpl.setTallaId(new oracle.jbo.domain.Number(resultSet.getDouble("talla_id")));
                    V2InvSoliDtlVORowImpl.setInventoryId(new oracle.jbo.domain.Number(resultSet.getDouble("inventory_id")));
                    V2InvSoliDtlVORowImpl.setInvOrgId(new oracle.jbo.domain.Number(resultSet.getDouble("inv_org_id")));
                    V2InvSoliDtlVORowImpl.setDotaId(new oracle.jbo.domain.Number(resultSet.getDouble("dota_id")));


                    /******************** Validar Ciclo Por Kit y Dota ID
          oracle.jbo.domain.Number numValidaCiclo = this.callValidarCiclo(V2InvSoliDtlVORowImpl.getDotaId()
                                                                         ,strEmployeeNumber
                                                                         ,numKitID
                                                                         ,V2InvSoliDtlVORowImpl.getCycleCod());
          ***************************************************************************************************/

                    oracle.jbo.domain.Number numValidaCicloByNomen = 
                        this.callValidarCicloByNomen(V2InvSoliVORowImpl.getPersonId(), 
                                                     resultSet.getString("nomenclature"), 
                                                     resultSet.getString("cycle_cod"));

                    V2InvSoliDtlVORowImpl.setTallaDevDisabled(false);
                    V2InvSoliDtlVORowImpl.setCantidadDevDisabledFlag("CantidadDevDisabledFlagN");

                    /** Regla de Negocio: Validacion de Ciclos no aplican para pantalla de Super Usuario
          if(0==numValidaCicloByNomen.compareTo(1)){
            V2InvSoliDtlVORowImpl.setTallaDevDisabled(false);
            V2InvSoliDtlVORowImpl.setCantidadDevDisabledFlag("CantidadDevDisabledFlagN");
          }else{
            V2InvSoliDtlVORowImpl.setTallaNbr(null);
            V2InvSoliDtlVORowImpl.setTallaId(null);
            V2InvSoliDtlVORowImpl.setQtyNbr(null);
            V2InvSoliDtlVORowImpl.setInventoryId(null);
            V2InvSoliDtlVORowImpl.setTallaDevDisabled(true);
            V2InvSoliDtlVORowImpl.setCantidadDevDisabledFlag("CantidadDevDisabledFlagY");
          }
          ***********************************************************************/

                    /** Regla de Negocio: Excluir Prendas de la pantalla que tengan configuradas Prendas Sustitutas **/
                    if ("Y".equals(resultSet.getString("sust_flag"))) {
                        V2InvSoliDtlVORowImpl.setTallaNbr(null);
                        V2InvSoliDtlVORowImpl.setTallaId(null);
                        V2InvSoliDtlVORowImpl.setQtyNbr(null);
                        V2InvSoliDtlVORowImpl.setInventoryId(null);
                        V2InvSoliDtlVORowImpl.setSustitucionSwitcher("V2SustitucionEnabled");
                    } else {
                        V2InvSoliDtlVORowImpl.setSustitucionSwitcher("SustitucionEmpty");
                    }

                    /** Regla de Negocio: Prendas que les corresponda cantidad 0 deben estar vacias **/
                    if (null != V2InvSoliDtlVORowImpl.getQtyNbr()) {
                        if (0 == 
                            V2InvSoliDtlVORowImpl.getQtyNbr().compareTo(0)) {
                            V2InvSoliDtlVORowImpl.setTallaNbr(null);
                            V2InvSoliDtlVORowImpl.setTallaId(null);
                            V2InvSoliDtlVORowImpl.setQtyNbr(null);
                            V2InvSoliDtlVORowImpl.setInventoryId(null);
                        }
                    }

                    /**** Regla de negocio:  no coloque cantidad ni talla si el valor en el campo DOTA_DEFAULT es vacio o N  ***/
                    if (null != resultSet.getString("dota_default")) {
                        if ("N".equals(resultSet.getString("dota_default")) || 
                            "X".equals(resultSet.getString("dota_default"))) {
                            V2InvSoliDtlVORowImpl.setTallaNbr(null);
                            V2InvSoliDtlVORowImpl.setTallaId(null);
                            V2InvSoliDtlVORowImpl.setQtyNbr(null);
                            V2InvSoliDtlVORowImpl.setInventoryId(null);
                        }
                    }

                    V2InvSoliDtlVOImpl.insertRow(V2InvSoliDtlVORowImpl);
                } /** END if(null!=V2InvSoliDtlVOImpl){ **/
            } /** END  while(resultSet.next()){ **/
        } catch (SQLException sqle) {
            retval = 
                    "EXCEPTION al recuperar informacion de la tabla XXGAM_INV_SIZ_UNIF_HISTORY: " + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
            return retval;
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);

        return retval;
    }

    /**
     * Metodo que limpia informacion del View Object V2InvSoliVOInfoVO
     * Proyecto Historico de Tallas. 
     * @return
     */
    public void cleanV2InvSoliVOInfo() {
        xXGamV2InvSoliVOImpl V2InvSoliVOImpl = getxXGamV2InvSoliVO1();
        if (null != V2InvSoliVOImpl) {
            V2InvSoliVOImpl.executeQuery();
            while (V2InvSoliVOImpl.hasNext()) {
                V2InvSoliVOImpl.next();
                V2InvSoliVOImpl.removeCurrentRow();
            }
        }
    }

    /**
     * Metodo que filtra el View Object xXGamInvAvailableKitsVO para que no muestre 
     * registros.
     */
    public void filterInvAvailableKitsVO() {
        xXGamInvAvailableKitsVOImpl InvAvailableKitsVOImpl = 
            getxXGamInvAvailableKitsVO1();
        ViewCriteria vc = InvAvailableKitsVOImpl.createViewCriteria();
        ViewCriteriaRow vcr1 = vc.createViewCriteriaRow();
        vcr1.setAttribute("KitCode", "IN ('X')");
        vc.add(vcr1);
        InvAvailableKitsVOImpl.applyViewCriteria(vc);
        InvAvailableKitsVOImpl.executeQuery();
        while (InvAvailableKitsVOImpl.hasNext()) {
            InvAvailableKitsVOImpl.next();
        }


    }

    /**
     * Metodo que pobla el View Object xXGamInvSoliVO
     * Proyecto Historico de Tallas  Pantalla Super Usuario
     * @param pStrGrabKitCode
     * @return
     */
    public String fillInvSoliVOInfoSU(String pStrGrabKitCode) {
        String retval = "SUCCESS";
        String strKitID = null;

        xXGamV2InvSoliVOImpl V2InvSoliVOImpl = getxXGamV2InvSoliVO1();
        xXGamV2InvSoliVORowImpl V2InvSoliVORowImpl = null;


        xXGamInvSoliVOImpl InvSoliVOImpl = getxXGamInvSoliVO1();
        xXGamInvSoliVORowImpl InvSoliVORowImpl = null;

        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();

        String strPrepStmt = 
            " select kit_id " + "   from XXGAM_INV_KIT " + "  where kit_cod = ?";
        PreparedStatement prepStmt = null;
        ResultSet resultSet = null;


        try {
            prepStmt = 
                    connection.prepareStatement(strPrepStmt, ResultSet.TYPE_FORWARD_ONLY, 
                                                ResultSet.CONCUR_READ_ONLY);
            prepStmt.setString(1, pStrGrabKitCode);
            resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                strKitID = resultSet.getString("kit_id");
            }
        } catch (SQLException sqle) {
            retval = 
                    "EXCEPTION al obtener el ID del Kit:" + pStrGrabKitCode + "," + 
                    sqle.getErrorCode() + " , " + sqle.getMessage();
            return retval;
        }

        closeResultSet(resultSet);
        closePreparedStatement(prepStmt);


        oracle.jbo.domain.Number numKitID = null;
        try {
            numKitID = new oracle.jbo.domain.Number(strKitID);
        } catch (SQLException e) {
            retval = 
                    "EXCEPTION al convertir:" + strKitID + " a oracle.jbo.domain.Number";
            return retval;
        }


        if (null != V2InvSoliVOImpl) {
            V2InvSoliVORowImpl = 
                    (xXGamV2InvSoliVORowImpl)V2InvSoliVOImpl.getCurrentRow();
        }

        if (null != InvSoliVOImpl) {
            if (!InvSoliVOImpl.isPreparedForExecution()) {
                /* InvSoliVOImpl.executeQuery(); */
                InvSoliVOImpl.clearCache();
                InvSoliVOImpl.initQuery("-1");
            }
            InvSoliVORowImpl = 
                    (xXGamInvSoliVORowImpl)InvSoliVOImpl.createRow();

            oracle.jbo.domain.Number numSoliID = 
                oaDBTransaction.getSequenceValue("XXGAM_INV_SOLI_S");

            InvSoliVORowImpl.setSoliId(numSoliID);
            InvSoliVORowImpl.setNroSolicitud("UNI-" + numSoliID.toString());
            InvSoliVORowImpl.setStatus("PENDIENTE");
            InvSoliVORowImpl.setKitId(numKitID);
            InvSoliVORowImpl.setSoliDate(oaDBTransaction.getCurrentDBDate());

            InvSoliVORowImpl.setPersonId(V2InvSoliVORowImpl.getPersonId());
            InvSoliVORowImpl.setEmployeeNumber(V2InvSoliVORowImpl.getEmployeeNumber());
            InvSoliVORowImpl.setRfc(V2InvSoliVORowImpl.getRfc());
            InvSoliVORowImpl.setCategory(V2InvSoliVORowImpl.getCategory());
            InvSoliVORowImpl.setAdscription(V2InvSoliVORowImpl.getAdscription());
            InvSoliVORowImpl.setContDueDate(V2InvSoliVORowImpl.getContDueDate());
            InvSoliVORowImpl.setStation(V2InvSoliVORowImpl.getStation());
            InvSoliVORowImpl.setCostCenter(V2InvSoliVORowImpl.getCostCenter());
            InvSoliVORowImpl.setExpenseAcc(V2InvSoliVORowImpl.getExpenseAcc());
            InvSoliVORowImpl.setExpenseDesc(V2InvSoliVORowImpl.getExpenseDesc());

            InvSoliVOImpl.insertRow(InvSoliVORowImpl);

            retval = numSoliID.toString();

        }


        return retval;

    } /** End Mehod **/

    /**
     * Metodo que llena la informacion del View Object SoliDtlIDInfoVO 
     * Pantalla Super Usuario.. 
     * Proyecto Historico de Tallas 
     * @return
     */
    public String fillSoliDtlIDInfoSU(String pStrSoliID) {
        String retval = null;
        xXGamV2InvSoliVOImpl V2InvSoliVOImpl = getxXGamV2InvSoliVO1();
        xXGamV2InvSoliVORowImpl V2InvSoliVORowImpl = null;
        xXGamInvSoliDtlVOImpl InvSoliDtlVOImpl = getxXGamInvSoliDtlVO1();
        xXGamInvSoliDtlVORowImpl InvSoliDtlVORowImpl = null;
        xXGamV2InvSoliDtlVOImpl V2InvSoliDtlVOImpl = getxXGamV2InvSoliDtlVO1();
        xXGamV2InvSoliDtlVORowImpl V2InvSoliDtlVORowImpl = null;
        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        oracle.jbo.domain.Number numSoliDtlID = null;

        oracle.jbo.domain.Number numSoliID = null;


        try {
            numSoliID = new oracle.jbo.domain.Number(pStrSoliID);
        } catch (SQLException sqle) {
            retval = 
                    "EXCEPTION al convertir a numerico elvalor pStrSoliID:" + pStrSoliID + 
                    " " + sqle.getMessage() + " ," + sqle.getErrorCode();
        }

        if (null != V2InvSoliVOImpl) {
            V2InvSoliVORowImpl = 
                    (xXGamV2InvSoliVORowImpl)V2InvSoliVOImpl.getCurrentRow();
        }

        RowSetIterator V2InvSoliDtlVOIterator = 
            V2InvSoliDtlVOImpl.createRowSetIterator(null);
        while (V2InvSoliDtlVOIterator.hasNext()) {
            V2InvSoliDtlVORowImpl = 
                    (xXGamV2InvSoliDtlVORowImpl)V2InvSoliDtlVOIterator.next();
            if (null != InvSoliDtlVOImpl) {
                if (!InvSoliDtlVOImpl.isPreparedForExecution()) {
                    /* InvSoliDtlVOImpl.executeQuery(); */
                    InvSoliDtlVOImpl.initQuery("-1");
                }
                numSoliDtlID = 
                        oaDBTransaction.getSequenceValue("XXGAM_INV_SOLI_DTL_S");
                System.out.println("numSoliDtlID:" + numSoliDtlID.toString());
                InvSoliDtlVORowImpl = 
                        (xXGamInvSoliDtlVORowImpl)InvSoliDtlVOImpl.createRow();
                InvSoliDtlVORowImpl.setDotaId(V2InvSoliDtlVORowImpl.getDotaId());
                InvSoliDtlVORowImpl.setUniformTypeCod(V2InvSoliDtlVORowImpl.getUniformTypeCod());
                InvSoliDtlVORowImpl.setNomenclature(V2InvSoliDtlVORowImpl.getNomenclature());
                InvSoliDtlVORowImpl.setMeasureUnitCod(V2InvSoliDtlVORowImpl.getMeasureUnitCod());
                InvSoliDtlVORowImpl.setCycleCod(V2InvSoliDtlVORowImpl.getCycleCod());
                InvSoliDtlVORowImpl.setQtyPlanta(V2InvSoliDtlVORowImpl.getPlantaQty());
                InvSoliDtlVORowImpl.setQtyEventual(V2InvSoliDtlVORowImpl.getEventQty());
                InvSoliDtlVORowImpl.setSustitucionSwitcher(InvSoliDtlVORowImpl.getSustitucionSwitcher());
                V2InvSoliDtlVORowImpl.setStatusDsp("Pendiente");
                InvSoliDtlVORowImpl.setStatus("PENDIENTE");
                InvSoliDtlVORowImpl.setAttribute1(V2InvSoliVORowImpl.getAdscription());
                /** if("EMPLEADO PLANTA".equals(InvSoliVORowImpl.getAdscription())){ **/
                if ("P".equalsIgnoreCase(V2InvSoliVORowImpl.getXxGamInvAssignExcept())) {
                    InvSoliDtlVORowImpl.setAttribute2(V2InvSoliDtlVORowImpl.getPlantaQty().toString());
                } else if ("E".equalsIgnoreCase(V2InvSoliVORowImpl.getXxGamInvAssignExcept())) {
                    InvSoliDtlVORowImpl.setAttribute2(V2InvSoliDtlVORowImpl.getEventQty().toString());
                }
                System.out.println("setQtyNbr:" + 
                                   V2InvSoliDtlVORowImpl.getQtyNbr());
                System.out.println("setTallaId:" + 
                                   V2InvSoliDtlVORowImpl.getTallaId());
                if (null != V2InvSoliDtlVORowImpl.getTallaId() && 
                    (0 != V2InvSoliDtlVORowImpl.getTallaId().compareTo(0))) {
                    InvSoliDtlVORowImpl.setTallaId(V2InvSoliDtlVORowImpl.getTallaId());
                }
                if (null != V2InvSoliDtlVORowImpl.getQtyNbr() && 
                    !"".equals(V2InvSoliDtlVORowImpl.getQtyNbr())) {
                    InvSoliDtlVORowImpl.setQtyNbr(V2InvSoliDtlVORowImpl.getQtyNbr());
                } /**InvSoliVORowImpl.getSoliId()**/
                    InvSoliDtlVORowImpl.setSoliId(numSoliID);
                InvSoliDtlVORowImpl.setSoliDtlId(numSoliDtlID);

                InvSoliDtlVORowImpl.setObservacionesSltud(V2InvSoliDtlVORowImpl.getObservacionesSltud());

                InvSoliDtlVOImpl.insertRow(InvSoliDtlVORowImpl);
            } /** END if(null!=InvSoliDtlVOImpl){ **/

        } /** END  while(V2InvSoliDtlVOIterator.hasNext()){ **/
        V2InvSoliDtlVOIterator.closeRowSetIterator();

        retval = "SUCCESS";
        return retval;

    }


    /**
     * Metodo que valida los montos en caso de que se haya elegido una prenda 
     * "Proyecto Historico Tallas"
     * Pantalla Super Usuario
     * @return
     */
    public com.sun.java.util.collections.List valAmountsV2InvSoliDtlSU() {
        com.sun.java.util.collections.List retval = 
            new com.sun.java.util.collections.ArrayList();
        xXGamV2InvSoliVOImpl V2InvSoliVOImpl = getxXGamV2InvSoliVO1();
        xXGamV2InvSoliVORowImpl V2InvSoliVORowImpl = null;
        xXGamV2InvSoliDtlVOImpl V2InvSoliDtlVOImpl = getxXGamV2InvSoliDtlVO1();
        xXGamV2InvSoliDtlVORowImpl V2InvSoliDtlVORowImpl = null;
        String strAdscription = null;
        String InvAssignExcept = null;

        if (null != V2InvSoliVOImpl) {
            V2InvSoliVORowImpl = 
                    (xXGamV2InvSoliVORowImpl)V2InvSoliVOImpl.getCurrentRow();
            strAdscription = V2InvSoliVORowImpl.getAdscription();
            InvAssignExcept = V2InvSoliVORowImpl.getXxGamInvAssignExcept();
        }

        if (null != V2InvSoliDtlVOImpl) {
            RowSetIterator V2InvSoliDtlIterator = 
                V2InvSoliDtlVOImpl.createRowSetIterator(null);
            while (V2InvSoliDtlIterator.hasNext()) {
                oracle.jbo.domain.Number numQtyEentered = null;
                oracle.jbo.domain.Number numQtyPermitida = null;
                oracle.jbo.domain.Number numTallaID = null;
                String strTallaNbr = null;
                String strNomenclature = null;
                V2InvSoliDtlVORowImpl = 
                        (xXGamV2InvSoliDtlVORowImpl)V2InvSoliDtlIterator.next();
                numQtyEentered = V2InvSoliDtlVORowImpl.getQtyNbr();
                strTallaNbr = V2InvSoliDtlVORowImpl.getTallaNbr();
                strNomenclature = V2InvSoliDtlVORowImpl.getNomenclature();
                numTallaID = V2InvSoliDtlVORowImpl.getTallaId();
                if ("P".equalsIgnoreCase(InvAssignExcept)) {
                    numQtyPermitida = V2InvSoliDtlVORowImpl.getPlantaQty();
                } else if ("E".equalsIgnoreCase(InvAssignExcept)) {
                    numQtyPermitida = V2InvSoliDtlVORowImpl.getEventQty();
                }
                System.out.println("numQtyEentered:" + numQtyEentered);
                System.out.println("strTallaNbr:" + strTallaNbr);
                System.out.println("strNomenclature:" + strNomenclature);
                System.out.println("numTallaID:" + numTallaID);

                if (null != numQtyEentered) {
                    /********************************** Existen Empleados Eventuales con cantidades igual a 0
         if(0==numQtyEentered.compareTo(0)){
           retval.add(new OAException(strNomenclature+": La cantidad ingresada no debe ser cero.",OAException.ERROR));
           * return retval; *
         }
        ******************************************************************************/
                    if (-1 == numQtyEentered.compareTo(0)) {
                        retval.add(new OAException(strNomenclature + 
                                                   ": La cantidad ingresada debe ser mayor que cero.", 
                                                   OAException.ERROR));
                    }

                    try {
                        java.lang.Integer.parseInt(numQtyEentered.toString());
                    } catch (java.lang.NumberFormatException nfexcp) {
                        retval.add(new OAException(strNomenclature + 
                                                   ": La cantidad ingresada no debe contener decimales.", 
                                                   OAException.ERROR));
                    }

                    if (null == strTallaNbr || "".equals(strTallaNbr)) {
                        retval.add(new OAException(strNomenclature + 
                                                   ": Si ingresa la Cantidad, debe seleccionar la Talla.", 
                                                   OAException.ERROR));
                        /* return retval; */
                    }
                    if (null != numQtyPermitida) {
                        if (numQtyPermitida.compareTo(numQtyEentered) < 0) {
                            retval.add(new OAException(strNomenclature + 
                                                       ": La cantidad ingresada " + 
                                                       numQtyEentered.toString() + 
                                                       " no puede ser mayor que la permitida " + 
                                                       numQtyPermitida.toString() + 
                                                       ".", 
                                                       OAException.ERROR));
                        }
                    }
                } else if (null != numTallaID) {
                    if (!(0 == numTallaID.compareTo(0))) {
                        retval.add(new OAException(strNomenclature + 
                                                   ": Si ingresa la Talla, debe seleccionar la Cantidad.", 
                                                   OAException.ERROR));
                        /* return retval; */
                    }
                }
            } /** End  while(V2InvSoliDtlIterator.hasNext()){ **/
            V2InvSoliDtlIterator.closeRowSetIterator();
        } /** END if(null!=V2InvSoliDtlVOImpl){ **/

        return retval;

    }

    /**
     * metodo que obtiene el numero de UNIS creadas por empleado
     * @return
     */
    public String getNumberUNIS() {
        String retval = null;
        xXGamInvSoliVOImpl InvSoliVOImpl = getxXGamInvSoliVO1();
        xXGamInvSoliVORowImpl InvSoliVORowImpl = null;

        OADBTransaction oaDBTransaction = this.getOADBTransaction();
        Connection connection = oaDBTransaction.getJdbcConnection();

        if (null != InvSoliVOImpl) {
            InvSoliVORowImpl = 
                    (xXGamInvSoliVORowImpl)InvSoliVOImpl.getCurrentRow();
            if (null != InvSoliVORowImpl) {
                oracle.jbo.domain.Number numPersonID = 
                    InvSoliVORowImpl.getPersonId();
                String retval2nd = 
                    XxGamInvUtils.getNumberUNIS(numPersonID, connection);
                if (null != retval2nd) {
                    if (retval2nd.contains("EXCEPTION")) {
                        return retval2nd;
                    } else {
                        retval = retval2nd;
                    }
                } /** End if(null!=retval2nd){ **/

            } /** End if(null!=InvSoliVORowImpl){ **/
        } /** End if(null!=InvSoliVOImpl){ **/
        return retval;

    }

}
