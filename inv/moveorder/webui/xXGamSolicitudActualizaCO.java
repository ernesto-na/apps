package xxgam.oracle.apps.inv.moveorder.webui;

import com.sun.java.util.collections.HashMap;

import java.sql.ResultSet;
import java.sql.SQLException;

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
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.nav.OAButtonBean;
import oracle.apps.fnd.framework.webui.beans.table.OAColumnBean;

import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import oracle.jdbc.OracleCallableStatement;

import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliDtlVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSolicitudAMImpl;
import xxgam.oracle.apps.inv.moveorder.utils.XxGamInvConstantes;
import xxgam.oracle.apps.inv.moveorder.xxDebugger;


@SuppressWarnings( { "unchecked", "deprecation", "rawtypes", "static" })
public class xXGamSolicitudActualizaCO extends OAControllerImpl {

    public static final String sqlNombreEmpleado = 
        "SELECT distinct ppf.full_name FROM per_all_people_f ppf,     xxgam_inv_soli xis WHERE xis.person_id = ppf.person_id  AND SYSDATE BETWEEN ppf.effective_start_date   AND ppf.effective_end_date  AND xis.soli_id = ?";
    public static final String RCS_ID = 
        "$Header: xXGamSolicitudActualizaCO.java 1.1 2013/02/27 10:51:48 eroncoroni ship $";
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header: xXGamSolicitudActualizaCO.java 1.1 2013/02/27 10:51:48 eroncoroni ship $", 
                                       "%packagename%");

    String lSuperUser;
    String lperfil;
    String statusSolicitud;
    String esFechaValida;
    String kit_Asignado;

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        int lUserid = 0;
        super.processRequest(pageContext, webBean);

        System.out.println("Inicia pagina xXGamSolicitudActualizaPG.");
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudActualizaCO - Version 1.1 - processRequest", 
                                     3);
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        xXGamInvSolicitudAMImpl am = 
            (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
        xxDebugger.oadbtransaction = oaapplicationmodule.getOADBTransaction();

        String lpSoliId = pageContext.getParameter("SoliId");
        if (null == lpSoliId || "".equals(lpSoliId)) {
            throw new OAException("EXCEPTION Al recuperar el parametro SoliId.", 
                                  OAException.ERROR);
        }

        System.out.println("lpSoliId:" + lpSoliId);

        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudActualizaCO - Consulta Soli Id: " + 
                                     lpSoliId, 3);
        OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();

        OAMessageStyledTextBean oanombreEmpleadoBean = 
            (OAMessageStyledTextBean)oapagelayoutbean.findIndexedChildRecursive("nombreEmpleado");

        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudActualizaCO - Buscamos el nombre del empleado sqlNombreEmpleado", 
                                     3);

        OracleCallableStatement pstmt = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT distinct ppf.full_name FROM per_all_people_f ppf,     xxgam_inv_soli xis WHERE xis.person_id = ppf.person_id  AND TRUNC(SYSDATE) BETWEEN ppf.effective_start_date   AND ppf.effective_end_date  AND xis.soli_id = ?", 
                                                                                 1);

        XxGamInvConstantes constantesErrors = new XxGamInvConstantes();
        String lMessageKitOutPeriodError = 
            constantesErrors.ERROR_KIT_PERIOD_CLOSE.toString();

        OAMessageTextInputBean observBean = 
            (OAMessageTextInputBean)webBean.findChildRecursive("Observaciones");
        OAColumnBean columObservBean = 
            (OAColumnBean)webBean.findChildRecursive("column12");
        OAButtonBean buttonBean = 
            (OAButtonBean)webBean.findChildRecursive("generarpedido");
        OAButtonBean buttonBeanSave = 
            (OAButtonBean)webBean.findChildRecursive("save");

        lperfil = oadbtransactionimpl.getProfile("XXGAM_INV_USUARIO_UNIFORME");
        lSuperUser = oadbtransactionimpl.getProfile("PERFIL_UNIFORMES");
        //String lSuperUser = oadbtransactionimpl.getProfile("PERFIL_UNIFORMES");
        System.out.println("lperfil : " + lperfil);
        System.out.println("lSuperUser: " + lSuperUser);

        if (lperfil == null) {
            lperfil = "N";
        }

        if (lSuperUser == null) {
            lSuperUser = "N";
        }

        //String statusSolicitud = null;
        if (lSuperUser.equals("Y")) {
            if (observBean != null) {
                observBean.setRendered(true);
            }

            if (columObservBean != null) {
                columObservBean.setRendered(true);
            }

            if (buttonBean != null) {
                buttonBean.setRendered(true);
            }
        } else {
            if (observBean != null) {
                observBean.setRendered(false);
            }

            if (columObservBean != null) {
                columObservBean.setRendered(false);
            }

            if (buttonBean != null) {
                buttonBean.setRendered(false);
            }
        }

        try {
            pstmt.setLong(1, (new Long(lpSoliId)).longValue());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String lNombreEmpleado = rs.getString(1);
                oanombreEmpleadoBean.setValue(pageContext, lNombreEmpleado);
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudActualizaCO - lNombreEmpleado: " + 
                                             lNombreEmpleado, 3);
            }
        } catch (SQLException var30) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudActualizaCO - sqlNombreEmpleado Error: " + 
                                         var30.getMessage().toString(), 3);
        } finally {
            try {
                pstmt.close();
            } catch (Exception var29) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudActualizaCO - sqlNombreEmpleado Error: " + 
                                             var29.getMessage().toString(), 3);
                throw OAException.wrapperException(var29);
            }
        }

        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudActualizaCO - Incia Consulta en VO xXGamInvSoliVO1 ", 
                                     3);
        xXGamInvSoliVOImpl invSoliVOImpl = 
            (xXGamInvSoliVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliVO1");
        invSoliVOImpl.removeRowsWithoutID();
        invSoliVOImpl.initQuery(lpSoliId);

        System.out.println("RowCount:" + invSoliVOImpl.getRowCount());
        System.out.println("FechedRowCount:" + 
                           invSoliVOImpl.getFetchedRowCount());

        xXGamInvSoliDtlVOImpl invSoliDtlVOImpl = 
            (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
        invSoliDtlVOImpl.initQuery(lpSoliId);
        boolean enRango = false;

        String v_estatusEmployee = "NA";
        try {
            v_estatusEmployee = 
                    validaEmpleadoActivo(lpSoliId, oaapplicationmodule);
        } catch (Exception e) {
            v_estatusEmployee = "NA";
        }

        System.out.println("v_estatusEmployee: " + v_estatusEmployee);
        String lMessageErrorEmpInctv = "";
        if (!v_estatusEmployee.toString().equals("1") && 
            !v_estatusEmployee.toString().equals("NA")) {

            am.deshabilitaTodasLineasActializacion(true);
            buttonBeanSave.setDisabled(true);
            buttonBean.setDisabled(true);
            lMessageErrorEmpInctv = 
                    constantesErrors.ERROR_EMPLOYEE_NOT_ACTIVE.toString();

            MessageToken[] tokens = 
                new MessageToken[] { new MessageToken("MESSAGE", 
                                                      lMessageErrorEmpInctv) };
            throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens);
        } else {


            statusSolicitud = am.validaStatusSolicitud(lpSoliId);
            if (statusSolicitud == null) {
                statusSolicitud = "";
            }


            //String esFechaValida = null;


            if (lSuperUser != null && lSuperUser.equals("Y")) {
                System.out.println("Invoca deshabilitar lineas");
                //am.validaCicloConFechaEntrega(pageContext, webBean);
            } else {
                esFechaValida = am.comparaFechaCreacion(lpSoliId);
                System.out.println("---esFechaValida: " + esFechaValida);
                if (esFechaValida != null && 
                    "NO".equals(esFechaValida.trim())) {
                    System.out.println("Entra a valiadación esFechaValida");

                    am.deshabilitaTodasLineasActializacion(true);
                    System.out.println("am.deshabilitaTodasLineasActializacion");
                    enRango = false;
                    buttonBeanSave.setDisabled(true);
                    throw new OAException(lMessageKitOutPeriodError, 
                                          OAException.INFORMATION);
                } else {
                    am.validaCicloConFechaEntrega(pageContext, webBean);
                    System.out.println("Invoca deshabilitar lineas");
                }
            }
            if (!"Y".equals(lSuperUser) || !"Y".equals(lperfil)) {
                lUserid = oadbtransactionimpl.getUserId();
            }

            OracleCallableStatement pstmAccounDefault;
            pstmAccounDefault = 
                    (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("DECLARE\n" + 
                                                                                         "CURSOR SOLI_DETAIL_CUR \n" + 
                                                                                         "    IS\n" + 
                                                                                         "SELECT SOLI_ID\n" + 
                                                                                         "     , SOLI_DTL_ID \n" + 
                                                                                         "  FROM XXGAM_INV_SOLI_DTL WHERE SOLI_ID = ? ;\n" + 
                                                                                         "V_TEST VARCHAR2(4000);\n" + 
                                                                                         "BEGIN\n" + 
                                                                                         "    FOR L_SOLI_DETAIL_CUR IN SOLI_DETAIL_CUR\n" + 
                                                                                         "        LOOP\n" + 
                                                                                         "        V_TEST := XXGAM_INV_MOVEORDER_PKG.REFRESH_SOLICITUD( p_soli_id       => L_SOLI_DETAIL_CUR.SOLI_ID\n" + 
                                                                                         "                                                 , p_soli_dtl_id   => L_SOLI_DETAIL_CUR.SOLI_DTL_ID\n" + 
                                                                                         "                                                 , p_user_id       => ? " + 
                                                                                         "                                                 , p_nro_desde     => NULL " + 
                                                                                         "                                                 , p_nro_hasta     => NULL " + 
                                                                                         "                                                  , p_fecha_desde   => NULL " + 
                                                                                         "                                                  , p_fecha_hasta   => NULL); " + 
                                                                                         "        \n" + 
                                                                                         "            \n" + 
                                                                                         "    END LOOP;    " + 
                                                                                         "EXCEPTION WHEN OTHERS THEN " + 
                                                                                         "    NULL;\n" + 
                                                                                         "END;", 
                                                                                         1);


            try {
                pstmAccounDefault.setString(1, lpSoliId);
                pstmAccounDefault.setInt(2, lUserid);
                pstmAccounDefault.execute();

            } catch (SQLException var129) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudActualizaCO - Refresh Error: " + 
                                             var129.getMessage().toString(), 
                                             3);
                System.out.println("Error en refrescar estatus de paginas: " + 
                                   var129.getMessage().toString());
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

            if ("ERROR".equals(statusSolicitud.toUpperCase()) || 
                "CANCELADO".equals(statusSolicitud.toUpperCase())) {
                System.out.println("Entra validación para  validacion de estatus ERROR O Cancelado.");
                am.deshabilitaTodasLineasActializacion(true);
            }


            OAFormValueBean handleKitFormValue = 
                (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("KitAsignadoForm");
            if (null != handleKitFormValue) {
                System.out.println("handleKitFormValue.getValue(pageContext):" + 
                                   handleKitFormValue.getValue(pageContext));
                if (null == handleKitFormValue.getValue(pageContext) || 
                    "".equals(handleKitFormValue.getValue(pageContext))) {
                    Row row = invSoliDtlVOImpl.first();
                    kit_Asignado = (String)row.getAttribute("KitCod");
                } else {
                    kit_Asignado = 
                            (String)handleKitFormValue.getValue(pageContext); /*.toString()*/
                }
            } /** END  if(null!=handleKitFormValue){ **/

            String vResultKitAsignado = 
                validaPeriodValid(kit_Asignado, oaapplicationmodule);
            System.out.println("Kit Correspondiente: " + kit_Asignado);
            System.out.println("validaPeriodValid: " + vResultKitAsignado);

            if (vResultKitAsignado.equals("0")) {
                if (lSuperUser == null && !lSuperUser.equals("Y")) {
                    am.deshabilitaTodasLineasActializacion(true);
                    buttonBeanSave.setDisabled(true);
                    throw new OAException(lMessageKitOutPeriodError, 
                                          OAException.INFORMATION);

                }
            }
        }
    }

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        super.processFormRequest(pageContext, webBean);

        /**
        * Stilos de nueva region*/
        /*CSSStyle cssAlingConceptLbl = new CSSStyle();
        cssAlingConceptLbl.setProperty("display", "block");
        cssAlingConceptLbl.setProperty("width", "190px");
        cssAlingConceptLbl.setProperty("margin-right", "10px");
        cssAlingConceptLbl.setProperty("text-align", "right");

        OAMessageTextInputBean nroSolicitudTextBean = (OAMessageTextInputBean)webBean.findChildRecursive("nroSolicitudText");
        nroSolicitudTextBean.setInlineStyle(cssAlingConceptLbl);

        OAMessageStyledTextBean nroSolicitudLabBean = (OAMessageStyledTextBean)webBean.findChildRecursive("nroSolicitudLab");
        nroSolicitudLabBean.setText(pageContext,"Nro. Solicitud");
        nroSolicitudLabBean.setInlineStyle(cssAlingConceptLbl);
        OAMessageStyledTextBean mesDatacionLabBean = (OAMessageStyledTextBean)webBean.findChildRecursive("mesDatacionLab");
        mesDatacionLabBean.setText(pageContext,"Mes Dotaci\u00f3n");
        mesDatacionLabBean.setInlineStyle(cssAlingConceptLbl);*/

        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudActualizaCO - Version 1.1 - processFormRequest", 
                                     3);
        OAApplicationModule oaapplicationmodule = 
            pageContext.getApplicationModule(webBean);
        xXGamInvSolicitudAMImpl am = 
            (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();
        String lEvent = pageContext.getParameter("event");
        Number lUserId = new Number(0);
        Number lSoliId = new Number(0);
        Number lSoliDtlId = new Number(0);

        String lMessageError = null;
        //String lSuperUser = oadbtransactionimpl.getProfile("PERFIL_zUNIFORMES");
        //String lperfil = oadbtransactionimpl.getProfile("XXGAM_INV_USUARIO_UNIFORME");  
        Object lNull = null;

        System.out.println("lEvent: " + lEvent);


        /**Refresh para ver el detalle de la linea*/
        if ("show".equals(lEvent)) {
            int fetchRowCount;
            System.out.println("SoliIdH " + 
                               pageContext.getParameter("SoliIdH"));
            xXGamInvSoliDtlVOImpl invSoliDtlVOImpl = 
                (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
            invSoliDtlVOImpl.initQuery(pageContext.getParameter("SoliIdH"));

            /*xXGamInvSoliDtlVOImpl vo = (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");

        fetchRowCount = vo.getFetchedRowCount();
        RowSetIterator selectIter = vo.createRowSetIterator("getSoliDtlId");
        System.out.println("fetchRowCount: " + fetchRowCount);
        if (fetchRowCount > 0) {
            for (int i = 0; i < fetchRowCount; i++) {

                xXGamInvSoliDtlVORowImpl rowi = (xXGamInvSoliDtlVORowImpl)selectIter.getRowAtRangeIndex(i);
                System.out.println("getSoliDtlId: " + rowi.getSoliDtlId().toString());

            }
        }*/


            /* System.out.println("SoliDtl: " + getSoliDtl(pageContext.getParameter("evtSrcRowRef")).toString());

           lSoliId = new Number(Integer.parseInt(pageContext.getParameter("SoliIdH")));
           lSoliDtlId = new Number(Integer.parseInt(getSoliDtl(pageContext.getParameter("evtSrcRowRef")).toString()));

           if(lperfil == null) {
              lperfil = "N";
           }

           if(lSuperUser == null) {
              lSuperUser = "N";
           }

           if(!"Y".equals(lSuperUser) || !"Y".equals(lperfil)) {
              int lUserid = oadbtransactionimpl.getUserId();
              lUserId = new Number(lUserid);
           }

           String sqlRefresh = "BEGIN ? := XXGAM_INV_MOVEORDER_PKG.REFRESH_SOLICITUD(?,?,?,?,?,?,?); END; ";
           OracleCallableStatement pstmtRRSH = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(sqlRefresh, 1);
           try {
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
              if(oadbtransactionimpl.isLoggingEnabled(4)) {
                 oadbtransactionimpl.writeDiagnostics(this, "Exception in calling XXGAM_INV_MOVEORDER_PKG.REFRESH_MOVE_ORDER: " + var25.getMessage(), 4);
              }

              throw OAException.wrapperException(var25);
           } finally {
              try {
                 pstmtRRSH.close();
              } catch (Exception var24) {
                 throw OAException.wrapperException(var24);
              }
           }


           */
        }

        if ("lovPrepare".equals(lEvent)) {
            pageContext.putSessionValue("SoliDtlValue", 
                                        getDotaId(getSoliDtl(pageContext.getParameter("evtSrcRowRef")).toString(), 
                                                  oaapplicationmodule));
        }
        //String lperfil = oadbtransactionimpl.getProfile("XXGAM_INV_USUARIO_UNIFORME");
        //String lSuperUser = oadbtransactionimpl.getProfile("PERFIL_UNIFORMES");
        //String esFechaValida = null;
        String lpSoliId = pageContext.getParameter("SoliId");
        //String statusSolicitud = null;

        //statusSolicitud = am.validaStatusSolicitud(lpSoliId);
        //System.out.println("evtSrcRowRef Fuera: " + pageContext.getParameter("evtSrcRowRef"));
        /*if("lovPrepare".equals(lEvent)) {
        System.out.println("--------------Nomenclature1:" + pageContext.getParameter("Nomenclature1"));
        System.out.println("--------------LineNumber: " + pageContext.getParameter("LineNumber"));
        System.out.println("evtSrcRowRef Dentro: " + pageContext.getParameter("evtSrcRowRef"));
       }/*

       /*Enumeration enums = pageContext.getParameterNames();
               while(enums.hasMoreElements()){
                 String paramName = enums.nextElement().toString();
                 System.out.println("Param name:-->" +paramName+ ";Value:-->"+pageContext.getParameter(paramName));
               }*/

        if ("grabar".equals(lEvent)) {
            System.out.println("lSuperUser: " + lSuperUser);
            System.out.println("Se recupera statusSolicitud: " + 
                               statusSolicitud);
            String vResultKitAsignado = 
                validaPeriodValid(kit_Asignado, oaapplicationmodule);
            XxGamInvConstantes constantesErrors = new XxGamInvConstantes();
            String lMessagePeriodError = 
                constantesErrors.ERROR_KIT_PERIOD_CLOSE.toString();

            if (esFechaValida != null && "NO".equals(esFechaValida.trim())) {
                am.deshabilitaTodasLineasActializacion(true);
            } else if ("ERROR".equals(statusSolicitud.toUpperCase()) || 
                       "CANCELADO".equals(statusSolicitud.toUpperCase())) {
                am.deshabilitaTodasLineasActializacion(true);
            } else {
                updateProcess(pageContext, webBean, oadbtransactionimpl, 
                              oaapplicationmodule);
                if (!lSuperUser.equals("Y")) {
                    am.validaCicloConFechaEntrega(pageContext, webBean);
                }

            }
            //updateProcess(pageContext, webBean, oadbtransactionimpl, oaapplicationmodule );
            //am.validaCicloConFechaEntrega(pageContext, webBean);


            //if(!lSuperUser.equals("Y")) {
            //    System.out.println("esFechaValida: " + esFechaValida);
            /*if(vResultKitAsignado.equals("0")){
                am.deshabilitaTodasLineasActializacion(true);
                throw new OAException(lMessagePeriodError, OAException.INFORMATION);
            }else*/
            //  if(esFechaValida != null && "NO".equals(esFechaValida.trim())) {
            //   am.deshabilitaTodasLineasActializacion(true);
            //}else if ("ERROR".equals(statusSolicitud.toUpperCase()) || "CANCELADO".equals(statusSolicitud.toUpperCase())){
            //  am.deshabilitaTodasLineasActializacion(true);
            //}else{
            //    updateProcess(pageContext, webBean, oadbtransactionimpl, oaapplicationmodule );
            //    am.validaCicloConFechaEntrega(pageContext, webBean);
            // }

            //}else{
            /*if(vResultKitAsignado.equals("0")){
                am.deshabilitaTodasLineasActializacion(true);
                throw new OAException(lMessagePeriodError, OAException.INFORMATION);
            }else{*/
            //      updateProcess(pageContext, webBean, oadbtransactionimpl, oaapplicationmodule );
            //      am.validaCicloConFechaEntrega(pageContext, webBean);
            //}
            //}

            /*pageContext.writeDiagnostics(this, "xXGamSolicitudActualizaCO - Evento Grabar", 3);
         OAFormValueBean oaSoliBean = (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("SoliIdH");
         String lSoliIdC = (String)oaSoliBean.getValue(pageContext);
         oadbtransactionimpl.commit();
         pageContext.writeDiagnostics(this, "xXGamSolicitudActualizaCO - Commit para Soli Id " + lSoliIdC, 3);
         String lSql = "BEGIN XXGAM_INV_MOVEORDER_PKG.VALIDA_SUSTITUTOS(?); END;";
         pageContext.writeDiagnostics(this, "xXGamSolicitudActualizaCO - ejecuto XXGAM_INV_MOVEORDER_PKG.VALIDA_SUSTITUTOS", 3);
         OracleCallableStatement pstmtSust = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(lSql, 1);

         try {
            pstmtSust.setLong(1, Long.valueOf(lSoliIdC).longValue());
            pstmtSust.execute();
            pstmtSust.close();
            pageContext.writeDiagnostics(this, "xXGamSolicitudActualizaCO - ejecuto con exito", 3);
         } catch (SQLException var28) {
            pageContext.writeDiagnostics(this, "xXGamSolicitudActualizaCO - lSql Error: " + var28.getMessage().toString(), 3);

            try {
               pstmtSust.close();
            } catch (Exception var27) {
               pageContext.writeDiagnostics(this, "xXGamSolicitudActualizaCO - lSql Error: " + var27.getMessage().toString(), 3);
            }

            throw OAException.wrapperException(var28);
         }

         oadbtransactionimpl.commit();
         pageContext.writeDiagnostics(this, "xXGamSolicitudActualizaCO - Commit al package: ", 3);
         xXGamInvSoliDtlVOImpl invSoliDtlVOImpl1 = (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
         invSoliDtlVOImpl1.clearCache();
         invSoliDtlVOImpl1.initQuery(lSoliIdC);
         pageContext.writeDiagnostics(this, "xXGamSolicitudActualizaCO - reejecuta el query VO xXGamInvSoliDtlVO1", 3);
         MessageToken[] tokens = new MessageToken[]{new MessageToken("MESSAGE", "Se han guardado los cambios con \u00e9xito.")};
         OAException message = new OAException("FND", "FND_GENERIC_MESSAGE", tokens, (byte)3, (Exception[])null);
         pageContext.putDialogMessage(message);*/


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
                MessageToken[] tokens3 = 
                    new MessageToken[] { new MessageToken("MESSAGE", 
                                                          lMessageE1) };
                throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens3);
            }

            MessageToken[] tokens2 = 
                new MessageToken[] { new MessageToken("MESSAGE", 
                                                      "Se ejecuto la actualizacion del pedido verifique el concurrente REQUEST ID: " + 
                                                      lReqId.toString()) };
            OAException message1 = 
                new OAException("FND", "FND_GENERIC_MESSAGE", tokens2, (byte)3, 
                                (Exception[])null);
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudActualizaCO -  verifique el concurrente REQUEST ID: " + 
                                         lReqId.toString(), 3);
            pageContext.putDialogMessage(message1);
            //am.validaCicloConFechaEntrega(pageContext, webBean);
        }
        /**Envento para inhabilitar las lineas despues de mostrar u ocultar los detalles solicitud*/
        if ("show".equals(lEvent) || "hide".equals(lEvent)) {
            System.out.println("Ejecuta evnto de hide/show");
            if (esFechaValida != null && "NO".equals(esFechaValida.trim())) {
                am.deshabilitaTodasLineasActializacion(true);
            } else if ("ERROR".equals(statusSolicitud.toUpperCase()) || 
                       "CANCELADO".equals(statusSolicitud.toUpperCase())) {
                am.deshabilitaTodasLineasActializacion(true);
            } else {
                updateProcess(pageContext, webBean, oadbtransactionimpl, 
                              oaapplicationmodule);
                am.validaCicloConFechaEntrega(pageContext, webBean);
            }
        }

        if ("linkPage".equals(lEvent)) {

            String SoliId = pageContext.getParameter("SoliIdH");
            System.out.println("SoliId: " + SoliId);
            HashMap params = new HashMap();
            params.put("pSoliDtlId", 
                       getSoliDtl(pageContext.getParameter("evtSrcRowRef")).toString());
            params.put("pSoliId", SoliId);
            params.put("pOrigen", "A");

            pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudLineDtlPG", 
                                      (String)null, (byte)0, (String)null, 
                                      params, true, "N", (byte)99);
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

    public void updateProcess(OAPageContext pageContext, OAWebBean webBean, 
                              OADBTransactionImpl oadbtransactionimpl, 
                              OAApplicationModule oaapplicationmodule) {
        OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudActualizaCO - Evento Grabar", 
                                     3);
        OAFormValueBean oaSoliBean = 
            (OAFormValueBean)oapagelayoutbean.findIndexedChildRecursive("SoliIdH");
        String lSoliIdC = (String)oaSoliBean.getValue(pageContext);
        oadbtransactionimpl.commit();
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudActualizaCO - Commit para Soli Id " + 
                                     lSoliIdC, 3);
        String lSql = 
            "BEGIN XXGAM_INV_MOVEORDER_PKG.VALIDA_SUSTITUTOS(?); END;";
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudActualizaCO - ejecuto XXGAM_INV_MOVEORDER_PKG.VALIDA_SUSTITUTOS", 
                                     3);
        OracleCallableStatement pstmtSust = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(lSql, 
                                                                                 1);

        try {
            pstmtSust.setLong(1, Long.valueOf(lSoliIdC).longValue());
            pstmtSust.execute();
            pstmtSust.close();
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudActualizaCO - ejecuto con exito", 
                                         3);
        } catch (SQLException var28) {
            pageContext.writeDiagnostics(this, 
                                         "xXGamSolicitudActualizaCO - lSql Error: " + 
                                         var28.getMessage().toString(), 3);

            try {
                pstmtSust.close();
            } catch (Exception var27) {
                pageContext.writeDiagnostics(this, 
                                             "xXGamSolicitudActualizaCO - lSql Error: " + 
                                             var27.getMessage().toString(), 3);
            }

            throw OAException.wrapperException(var28);
        }

        oadbtransactionimpl.commit();
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudActualizaCO - Commit al package: ", 
                                     3);
        xXGamInvSoliDtlVOImpl invSoliDtlVOImpl1 = 
            (xXGamInvSoliDtlVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliDtlVO1");
        invSoliDtlVOImpl1.clearCache();
        invSoliDtlVOImpl1.initQuery(lSoliIdC);
        pageContext.writeDiagnostics(this, 
                                     "xXGamSolicitudActualizaCO - reejecuta el query VO xXGamInvSoliDtlVO1", 
                                     3);
        MessageToken[] tokens = 
            new MessageToken[] { new MessageToken("MESSAGE", 
                                                  "Se han guardado los cambios con \u00e9xito.") };
        OAException message = 
            new OAException("FND", "FND_GENERIC_MESSAGE", tokens, (byte)3, 
                            (Exception[])null);
        pageContext.putDialogMessage(message);
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

    public String validaPeriodValid(String pKitName, 
                                    OAApplicationModule oaapplicationmodule) {

        String val = null;
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();

        OracleCallableStatement pstmtKit = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT COUNT(1)                                                                                 " + 
                                                                                 "  FROM FND_LOOKUP_VALUES                                                                        " + 
                                                                                 " WHERE LOOKUP_TYPE = 'XXGAM_APERTURA_DE_PERIODOS'                                               " + 
                                                                                 "   AND LANGUAGE = USERENV('LANG')                                                               " + 
                                                                                 "   AND TRUNC(SYSDATE) BETWEEN TRUNC(START_DATE_ACTIVE) AND TRUNC(NVL(END_DATE_ACTIVE, SYSDATE)) " + 
                                                                                 "   AND ENABLED_FLAG = 'Y'                                                                       " + 
                                                                                 "   AND MEANING = ?                                                                            ", 
                                                                                 1);

        try {
            pstmtKit.setString(1, pKitName);
            ResultSet resulstQuery = pstmtKit.executeQuery();
            if (resulstQuery.next()) {
                val = "" + resulstQuery.getInt(1);
            }

        } catch (SQLException var01) {
            System.out.println("Un error encontrado en Validar periodo Activo: " + 
                               var01.getMessage().toString());
        } finally {
            try {
                pstmtKit.close();
            } catch (Exception var02) {
                System.out.println("Un error encontrado en Validar periodo Activo: " + 
                                   var02.getMessage().toString());
            }
        }
        return val;
    }

    public String validaEmpleadoActivo(String pSoliId, 
                                       OAApplicationModule oaapplicationmodule) {
        String v_estatusEmployee = "NA";
        System.out.println("Inicia Proceso validaEmpleadoActivo, se recibe pSoliId: " + 
                           pSoliId);
        OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
        OracleCallableStatement pstmtEmployeeActive = 
            (OracleCallableStatement)oadbtransactionimpl.createCallableStatement("SELECT PASTT.USER_STATUS, " + 
                                                                                 "       CASE " + 
                                                                                 "          WHEN (   UPPER (PASTT.USER_STATUS) = 'ACTIVE ASSIGNMENT' " + 
                                                                                 "                OR UPPER (PASTT.USER_STATUS) = 'ASIGNACIÓN ACTIVA') " + 
                                                                                 "          THEN " + 
                                                                                 "             '1' " + 
                                                                                 "          ELSE " + 
                                                                                 "             UPPER (PASTT.USER_STATUS) " + 
                                                                                 "       END " + 
                                                                                 "          RESULT " + 
                                                                                 "  FROM PER_ALL_ASSIGNMENTS_F PAAF, " + 
                                                                                 "       PER_ASSIGNMENT_STATUS_TYPES_TL PASTT, " + 
                                                                                 "       PER_ALL_PEOPLE_F PAPF, " + 
                                                                                 "       PER_ORGANIZATION_UNITS POU " + 
                                                                                 " WHERE     PAAF.PERSON_ID = (SELECT PERSON_ID " + 
                                                                                 "                               FROM BOLINF.XXGAM_INV_SOLI " + 
                                                                                 "                              WHERE SOLI_ID = ?) " + 
                                                                                 "       AND PAAF.ASSIGNMENT_STATUS_TYPE_ID = PASTT.ASSIGNMENT_STATUS_TYPE_ID " + 
                                                                                 "       AND PAPF.PERSON_ID = PAAF.PERSON_ID " + 
                                                                                 "       AND PASTT.LANGUAGE = USERENV ('LANG') " + 
                                                                                 "       AND PAAF.ORGANIZATION_ID = POU.ORGANIZATION_ID " + 
                                                                                 "       AND TRUNC (SYSDATE) BETWEEN TRUNC (POU.DATE_FROM) " + 
                                                                                 "                               AND NVL (TRUNC (POU.DATE_TO), TRUNC (SYSDATE)) " + 
                                                                                 "       AND TRUNC (SYSDATE) BETWEEN TRUNC (PAAF.EFFECTIVE_START_DATE) " + 
                                                                                 "                               AND TRUNC ( " + 
                                                                                 "                                      NVL (TRUNC (PAAF.EFFECTIVE_END_DATE), " + 
                                                                                 "                                           TRUNC (SYSDATE))) " + 
                                                                                 "       AND TRUNC (PAPF.EFFECTIVE_START_DATE) = " + 
                                                                                 "              (SELECT MAX (TRUNC (EFFECTIVE_START_DATE)) " + 
                                                                                 "                 FROM PER_ALL_PEOPLE_F " + 
                                                                                 "                WHERE PERSON_ID = (SELECT PERSON_ID " + 
                                                                                 "                               FROM BOLINF.XXGAM_INV_SOLI " + 
                                                                                 "                              WHERE SOLI_ID = ?)) " + 
                                                                                 "       AND PAAF.primary_flag = 'Y'", 
                                                                                 1);

        try {
            pstmtEmployeeActive.setString(1, pSoliId);
            pstmtEmployeeActive.setString(2, pSoliId);
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

}
