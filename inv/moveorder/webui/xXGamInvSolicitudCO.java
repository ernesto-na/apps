package xxgam.oracle.apps.inv.moveorder.webui;

import com.sun.java.util.collections.HashMap;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAUrl;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.nav.OALinkBean;

import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAQueryBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageDateFieldBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
import oracle.apps.fnd.framework.webui.beans.nav.OAButtonBean;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.Number;
import oracle.jdbc.OracleCallableStatement;
import oracle.sql.CLOB;
import xxgam.oracle.apps.inv.moveorder.xxDebugger;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliSummaryVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliSummaryVORowImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSolicitudAMImpl;
import xxgam.oracle.apps.inv.moveorder.utils.XxGamInvConstantes;

@SuppressWarnings({"unchecked", "deprecation","rawtypes","static"})
public class xXGamInvSolicitudCO extends OAControllerImpl {

   public static final String RCS_ID = "$Header: xXGamInvSolicitudCO.java 1.1 2013/02/07 10:51:48 eroncoroni ship $";
   public static final boolean RCS_ID_RECORDED = VersionInfo.recordClassVersion("$Header: xXGamInvSolicitudCO.java 1.1 2013/02/07 10:51:48 eroncoroni ship $", "%packagename%");


   public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
      super.processRequest(pageContext, webBean);
      
      OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();
      OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
      xxDebugger.oadbtransaction = oaapplicationmodule.getOADBTransaction();
      pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - Version 1.0 - processRequest", 3);
      pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO ", 3);
      
       /**
        * Guillermo Sandoval Sabas (Multisolutions) 21/Septiembre/2016
        * Se agrega codigo para habilitar setOnClick y que se dispare varias veces
        * el boton con varios click.
        * */
        OAButtonBean crearSolicitudBtn = ((OAButtonBean)webBean.findChildRecursive("crearsolicitud"));
        OAButtonBean generarPedidoBtn = ((OAButtonBean)webBean.findChildRecursive("generarpedido"));
        OAButtonBean cancelarPedidoBtn = ((OAButtonBean)webBean.findChildRecursive("cancelarpedido"));
        
        crearSolicitudBtn.setOnClick("this.disabled=true;");
        generarPedidoBtn.setOnClick("this.disabled=true;");
        cancelarPedidoBtn.setOnClick("this.disabled=true;");
        
      xXGamInvSolicitudAMImpl am = (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
      OADBTransactionImpl oadbtransactionimpl = (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
      System.out.println("ORG_ID: " + pageContext.getOrgId());
      
      System.out.println("Se omptiene getUserId: " + oadbtransactionimpl.getUserId() );
      System.out.println("Se optiene employeeNumber: " + am.obtieneEmployeeNumber(oadbtransactionimpl.getUserId())); 
      System.out.println("Se optiene UserId" + pageContext.getUserId());
      System.out.println("Se imprime personId: " + am.obtienePersonId(pageContext.getUserId()));
      System.out.println("retainAM: " + pageContext.getParameter("retainAM"));
      OALinkBean linkDownLoadPDFBean = (OALinkBean)oapagelayoutbean.findIndexedChildRecursive("linkDownLoadPDF");            
      OAQueryBean queryBean = (OAQueryBean)webBean.findChildRecursive("QuerySolicitudRN");  
       String currentPanel = queryBean.getCurrentSearchPanel();
       System.out.println("********************currentPanel: " + currentPanel);
      //queryBean.clearSearchPersistenceCache(pageContext); 
      
       //clearSearchPanel(pageContext, webBean);
       
      //am.executeInitialReport(am.obtieneEmployeeNumber(oadbtransactionimpl.getUserId())); 
       //am.executeInitialReport(oadbtransactionimpl.getUserId()); 
       xXGamInvSoliSummaryVOImpl invSoliVOImpl = (xXGamInvSoliSummaryVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliSummaryVO1");
       //invSoliVOImpl.setQuery(null);
       //invSoliVOImpl.setWhereClauseParams(null);
       int validaSearchPanel = 0;
       try{
            validaSearchPanel = validQueryInit(invSoliVOImpl.getQuery().toString());
       }catch(Exception e){
        System.out.println("");
       }
       
       System.out.println("validaSearchPanel: " + validaSearchPanel);
       if(validaSearchPanel <= 0 ){
           try{
               String filter =  am.validaInitReport("" + oadbtransactionimpl.getUserId());
               System.out.println("********filter: " + filter);


               invSoliVOImpl.setWhereClause("SOLI_ID IN (" + filter + ")");
               
                //invSoliVOImpl.setWhereClause("CREATED_BY = " +  oadbtransactionimpl.getUserId());
               invSoliVOImpl.executeQuery();
               /*while (invSoliVOImpl.hasNext() && contador < 6) {
                         Row row = invSoliVOImpl.next();
                   contador = contador + 1; 
               }*/          
               
           }catch(Exception e){
                System.out.println("ERROR invSoliVOImpl... ");
               
               SearchPanelAltaernative(pageContext, webBean);
               
               invSoliVOImpl.setQuery(null);
               invSoliVOImpl.setWhereClauseParams(null);
               invSoliVOImpl.executeQuery();
           }
        
       }

      
      int lUserId = oadbtransactionimpl.getUserId();
      String lSuperUser = oadbtransactionimpl.getProfile("PERFIL_UNIFORMES");
      String lperfil = oadbtransactionimpl.getProfile("XXGAM_INV_USUARIO_UNIFORME");
 
      if(lperfil == null) {
         lperfil = "N";
      }

      if(lSuperUser == null) {
         lSuperUser = "N";
      }

      if(lSuperUser.equals("Y")) {
         am.setRenderOptions("ClvEmplRender", true);
         am.setRenderOptions("BtnGeneraPedidoRender", true);
         am.setRenderOptions("BtnCancelarPedidoRender", true);
          linkDownLoadPDFBean.setRendered(false);
      } else {
          linkDownLoadPDFBean.setRendered(true);
         am.setRenderOptions("ClvEmplRender", false);
         am.setRenderOptions("BtnGeneraPedidoRender", false); 
         am.setRenderOptions("BtnCancelarPedidoRender", false);
      }
      System.out.println("lperfil: " + lperfil );
      System.out.println("lSuperUser: " + lSuperUser);
      
      if(!lSuperUser.equals("Y") && !lperfil.equals("Y")) {
         Number personId = null;
         String estaActiva = "NO";
         personId = am.obtienePersonId(lUserId);
         //Valida 
         estaActiva = am.validaSolicitudFechaActiva(personId);
         
         System.out.println("personId: " + personId);
         System.out.println("estaActiva: " + estaActiva);
          //estaActiva - existeSolicitudValida
         if(estaActiva == null) {
            am.setRenderOptions("BtnCrearSolicitudRender", false);
         } else {
            if(estaActiva.equals("YES")) {
                System.out.println("Invalida boton con validacion: estaActiva.equals(\"YES\") ");
               am.setRenderOptions("BtnCrearSolicitudRender", true);
            } else {
               am.setRenderOptions("BtnCrearSolicitudRender", false);
            }

            String existeSolicitudValida = am.comparaFechaCreacionUser(personId);
            System.out.println("existeSolicitudValida: " + existeSolicitudValida);
            if(existeSolicitudValida != null && "SI".equals(existeSolicitudValida)) {
                System.out.println("Invalida boton con validacion:  existeSolicitudValida != null && \"SI\".equals(existeSolicitudValida)");
               am.setRenderOptions("BtnCrearSolicitudRender", true ); 
            } else {
               am.setRenderOptions("BtnCrearSolicitudRender", false);
            }
         }
      } else {
         am.setRenderOptions("BtnCrearSolicitudRender", false);
      }
/*      String v_estatusEmployee = "NA";
      try{
       v_estatusEmployee = validaEmpleadoActivo(am.obtienePersonId(pageContext.getUserId()).toString(),oaapplicationmodule );        
      }catch(Exception e){
          v_estatusEmployee = "NA";
      }


          System.out.println("Estado del Empleado: "  + v_estatusEmployee);
                
          XxGamInvConstantes constantesErrors = new XxGamInvConstantes(); 
          String lMessageError = ""; 
              if(v_estatusEmployee.toString().equals("NA")){
                //am.setRenderOptions("BtnCrearSolicitudRender", true);  
                // throw new OAException(" No se encontro configuraci\u00f3n como empleado.", OAException.INFORMATION);  
                System.out.println("Uusuario no configurado comoempleado, no aplica.");
              }else if (!v_estatusEmployee.toString().equals("1")){
              am.setRenderOptions("BtnCrearSolicitudRender", true);
              
              lMessageError = constantesErrors.ERROR_EMPLOYEE_NOT_ACTIVE.toString();      
              MessageToken[] tokens = new MessageToken[]{new MessageToken("MESSAGE", lMessageError)};
            throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens);        
          } */
     disableRegionsByProfiles(pageContext,webBean);
     
   }

   public void processFormRequest(OAPageContext pageContext, OAWebBean webBean) {
      super.processFormRequest(pageContext, webBean);
              
      xXGamInvSolicitudAMImpl am = (xXGamInvSolicitudAMImpl)pageContext.getApplicationModule(webBean);
       
      OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
       xXGamInvSoliSummaryVOImpl invSoliVOImpl = (xXGamInvSoliSummaryVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliSummaryVO1");
       
      pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - Version 1.0 - processFormRequest", 3);
      OADBTransactionImpl oadbtransactionimpl = (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
      OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();
      OAQueryBean queryBean = (OAQueryBean)webBean.findIndexedChildRecursive("QuerySolicitudRN");
      String lEvent = pageContext.getParameter("event");
      String lSource = pageContext.getParameter("source");
      Number lUserId = new Number(0);
      Number lSoliId = new Number(0);
      Number lSoliDtlId = new Number(0);
      String lMessageError = null;
      String lperfil = oadbtransactionimpl.getProfile("XXGAM_INV_USUARIO_UNIFORME");
      String lSuperUser = oadbtransactionimpl.getProfile("PERFIL_UNIFORMES");

                       

        
      System.out.println("lEvent: " + lEvent);
      if(lperfil == null) {
         lperfil = "N";
      }

      if(lSuperUser == null) {
         lSuperUser = "N";
      }

      String idGo = queryBean.getGoButtonName();
      if(pageContext.getParameter(idGo) != null && !"NroSolicitudDesde".equals(lSource) && !"NroSolicitudDesde".equals(lSource)) {
         pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - Evento Go o Ir ", 3);
         OAMessageLovInputBean oaNroSoliDesdeBean = (OAMessageLovInputBean)oapagelayoutbean.findIndexedChildRecursive("NroSolicitudDesde");
         String lNroSoliD = (String)oaNroSoliDesdeBean.getValue(pageContext);
         OAMessageLovInputBean oaNroSoliHastaBean = (OAMessageLovInputBean)oapagelayoutbean.findIndexedChildRecursive("NroSolicitudHasta");
         String lNroSoliH = (String)oaNroSoliHastaBean.getValue(pageContext);
         OAMessageDateFieldBean oaFechaDesdeBEEBean = (OAMessageDateFieldBean)oapagelayoutbean.findIndexedChildRecursive("FechaSolicitudDesde");
         Date lFecha = null;
         oracle.jbo.domain.Date lFechaDesde = null;
         if(oaFechaDesdeBEEBean.getValue(pageContext) != null) {
            Timestamp ts = (Timestamp)oaFechaDesdeBEEBean.getValue(pageContext);
            lFecha = new Date(ts.getTime());
            lFechaDesde = new oracle.jbo.domain.Date(lFecha);
         }

         OAMessageDateFieldBean oaFechaHastaBEEBean = (OAMessageDateFieldBean)oapagelayoutbean.findIndexedChildRecursive("FechaSolicitudHasta");
         Date lFecha1 = null;
         oracle.jbo.domain.Date lFechaHasta = null;
         if(oaFechaHastaBEEBean.getValue(pageContext) != null) {
            Timestamp ts1 = (Timestamp)oaFechaHastaBEEBean.getValue(pageContext);
            lFecha1 = new Date(ts1.getTime());
            lFechaHasta = new oracle.jbo.domain.Date(lFecha1);
         }

         OAMessageChoiceBean oaStatusBean = (OAMessageChoiceBean)oapagelayoutbean.findIndexedChildRecursive("StatusChoice");
         String lStatus = (String)oaStatusBean.getValue(pageContext);
         OAMessageLovInputBean lovCvEmp = (OAMessageLovInputBean)webBean.findChildRecursive("ClaveEmpleado");
         String lCvEmp = (String)lovCvEmp.getValue(pageContext);
         if(!"Y".equals(lSuperUser) && !"Y".equals(lperfil)) {
            int lUserid = oadbtransactionimpl.getUserId();
            lUserId = new Number(lUserid);
            lCvEmp = am.buscaEmpleadoPorUserID(lUserId);
         } else if(lCvEmp != null) {
            boolean isEmplValido = am.buscaEmpleadoPorClave(lCvEmp);
            if(!isEmplValido) {
               throw new OAException("Clave de empleado no v\u00e1lido");
            }
         }

         if(!"Y".equals(lSuperUser) || !"Y".equals(lperfil)) {
            int lUserid1 = oadbtransactionimpl.getUserId();
            lUserId = new Number(lUserid1);
         }

         pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - ejecutamos XXGAM_INV_MOVEORDER_PKG.REFRESH_SOLICITUD ", 3);
         String sqlRefresh = "BEGIN ? := XXGAM_INV_MOVEORDER_PKG.REFRESH_SOLICITUD(?,?,?,?,?,?,?); END; ";
         OracleCallableStatement pstmtRRSH = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(sqlRefresh, 1);

         try {
            pstmtRRSH.registerOutParameter(1, 12);
            pstmtRRSH.setNUMBER(2, lSoliId);
            pstmtRRSH.setNUMBER(3, lSoliDtlId);
            pstmtRRSH.setNUMBER(4, lUserId);
            pstmtRRSH.setString(5, lNroSoliD);
            pstmtRRSH.setString(6, lNroSoliH);
            if(oaFechaDesdeBEEBean.getValue(pageContext) != null) {
               pstmtRRSH.setDate(7, lFecha);
            } else {
               pstmtRRSH.setDate(7, (Date)null);
            }

            if(oaFechaHastaBEEBean.getValue(pageContext) != null) {
               pstmtRRSH.setDate(8, lFecha1);
            } else {
               pstmtRRSH.setDate(8, (Date)null);
            }

            pstmtRRSH.execute();
            lMessageError = pstmtRRSH.getString(1);
         } catch (Exception var59) {
            if(oadbtransactionimpl.isLoggingEnabled(4)) {
               oadbtransactionimpl.writeDiagnostics(this, "Exception in calling XXGAM_INV_MOVEORDER_PKG.REFRESH_MOVE_ORDER: " + var59.getMessage(), 4);
            }

            pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - sqlRefresh Error: " + var59.getMessage().toString(), 3);
            throw OAException.wrapperException(var59);
         } finally {
            try {
               pstmtRRSH.close();
            } catch (Exception var58) {
               pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - sqlRefresh Error: " + var58.getMessage().toString(), 3);
               throw OAException.wrapperException(var58);
            }
         }

         xXGamInvSoliSummaryVOImpl invSummaryVOImpl = (xXGamInvSoliSummaryVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliSummaryVO1");
         invSummaryVOImpl.clearCache();
         invSummaryVOImpl.initQuery(lNroSoliD, lNroSoliH, lFechaDesde, lFechaHasta, lStatus, lCvEmp);
         pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - consuta VO  xXGamInvSoliSummaryVO1 con parametros seleccionados", 3);
      }

      if("update".equals(lEvent)) {
         pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - Event update", 3);
         pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - call:OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudActualizaPG", 3);
         pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudActualizaPG", (String)null, (byte)0, (String)null, (HashMap)null, true, "N", (byte)99);
      }

      if("print".equals(lEvent)) {
         pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - Event print", 3);
         pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - call:/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudImprimePG", 3);
         String lSoliIdSTR = pageContext.getParameter("SoliId");
         StringBuffer l_buffer = new StringBuffer();
         StringBuffer l_buffer1 = new StringBuffer();
         l_buffer.append("javascript:var mywin = openWindow(top, \'");
         l_buffer1.append("/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudImprimePG");
         l_buffer1.append("&retainAM=Y");
         l_buffer1.append("&SoliId=" + lSoliIdSTR);
         String url = "/OA_HTML/OA.jsp?page=" + l_buffer1.toString();
         OAUrl popupUrl = new OAUrl(url, "S");
         String strUrl = popupUrl.createURL(pageContext);
         l_buffer.append(strUrl.toString());
         l_buffer.append("\', \'ReportWindow\', {width:800, height:600},false,\'dialog\',null);");
         pageContext.putJavaScriptFunction("RepSolicitud", l_buffer.toString());
      }

      if("crearsolicitud".equals(lEvent)) {
         pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - Event crearsolicitud", 3);
          
          System.out.println("lSuperUser: " + lSuperUser);
          System.out.println("lperfil: " + lperfil);
         
         if(!"Y".equals(lSuperUser) && !"Y".equals(lperfil)) {
         
            System.out.println("Condicion OK");
            pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - call:OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudCrearPG", 3);
            pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudCrearPG", (String)null, (byte)0, (String)null, (HashMap)null, true, "N", (byte)99);
         } else {
            //Region donde se muestar 
            System.out.println("Condicion N");
            System.out.println("Se direcciona a xXGamSolicitudCrearSuperUserPG");
            pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - call:OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudCrearSuperUserPG", 3);
            pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudCrearSuperUserPG", (String)null, (byte)0, (String)null, (HashMap)null, true, "N", (byte)99); 
         }
      }

      if("generarpedido".equals(lEvent)) {
         

             pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - Event generarpedido", 3);
             pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - call:checkSelectedrow", 3);
             /*Validación de empleado activo*/
             this.checkSelectedrow(pageContext, webBean, "G");
        
      }

      if("cancelarpedido".equals(lEvent)) {
         pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - Event cancelarpedido", 3);
         pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - call:checkSelectedrow", 3);
         this.checkSelectedrow(pageContext, webBean, "C");
      }

      if("linkPage".equals(lEvent)) {
         System.out.println("Quey: " + invSoliVOImpl.getRowCount());
         pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - Event linkPage", 3);
         String esDevolucion = null;
         String numSoli = null;
         Number idSoli = null;
         String rowRef = pageContext.getParameter("evtSrcRowRef");
         xXGamInvSoliSummaryVORowImpl row = (xXGamInvSoliSummaryVORowImpl)am.findRowByRef(rowRef);
         if(row != null) {
            numSoli = row.getNroSolicitud();
            idSoli = row.getSoliId();
            esDevolucion = am.validaLineas(pageContext, webBean, lSuperUser, numSoli);
         }

         HashMap params = new HashMap();
         params.put("SoliId", idSoli);
         if(esDevolucion != null && "NO".equals(esDevolucion)) {
            pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - go to CONSULTA", 3);
            pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudConsultaPG", (String)null, (byte)0, (String)null, params, true, "N", (byte)99);
         } else if(esDevolucion != null && "YES".equals(esDevolucion)) {
            pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - go to DEVOLUCION", 3);
            pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamInvDevolucionesPG", (String)null, (byte)0, (String)null, params, true, "N", (byte)99);
         } else {
            pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - go to CONSULTA", 3);
            pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudConsultaPG", (String)null, (byte)0, (String)null, params, true, "N", (byte)99);
         }
      }
      
      if("lovPrepare".equals(lEvent)) {
          String valueLov = "";
          System.out.println("Evento en el que se prepara a abrir Lov Exsternal.");
          OAMessageLovInputBean NroSolicitudDesdeLov = ((OAMessageLovInputBean)webBean.findChildRecursive("NroSolicitudDesde"));
          try{
              valueLov = NroSolicitudDesdeLov.getValue(pageContext).toString();
              System.out.println("valueLov: " + valueLov);
          }catch(Exception e){
              //NroSolicitudDesdeLov.setText(pageContext, "%");
               //NroSolicitudDesdeLov.setVAlign("%");
               
                System.out.println("Un error encontrado al recuperar le valor del Lov");          
          }
      }
      
      if("downLoadPDF".equals(lEvent)) {
        System.out.println("Inicia proceso para la descarga de PDF.");
          downloadFileFromServer( pageContext
                                , "/interface/i_aemx60/PAEMXI/incoming/Uniformes/Politicas/POGAM-RH-014_(uso de uniforme).pdf"//"/taemxi/applmgr/1200/xbol/12.0.0/pdf/POGAM-RH-014_(uso de uniforme).pdf",
                                , "POGAM-RH-014_(uso de uniforme).pdf" 
                                ); 
                      
      }
      
   }

   public void checkSelectedrow(OAPageContext pageContext, OAWebBean webBean, String action) {
      OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
      OAViewObject invSoliSummVO = (OAViewObject)oaapplicationmodule.findViewObject("xXGamInvSoliSummaryVO1");
      OADBTransactionImpl oadbtransactionimpl = (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
      String lSoliIdSTR = null;
      Number lReqId = new Number(-1);
      /*Validacion de empleado activo*/
      String v_estatusEmployee = "NA";
       XxGamInvConstantes constantesErrors = new XxGamInvConstantes(); 
      int fetchedRowCount = invSoliSummVO.getFetchedRowCount();
      RowSetIterator selectIter = invSoliSummVO.createRowSetIterator("selectIter");
      if(fetchedRowCount > 0) {
         selectIter.setRangeStart(0);
         selectIter.setRangeSize(fetchedRowCount);

         for(int i = 0; i < fetchedRowCount; ++i) {
            xXGamInvSoliSummaryVORowImpl row = (xXGamInvSoliSummaryVORowImpl)selectIter.getRowAtRangeIndex(i);
            String selectFlag1 = row.getSelectFlag1() + "";
            if("Y".equals(selectFlag1)) {
               pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - selecciono Solicitud Id: " + row.getSoliId().toString(), 3);
                try{
                     v_estatusEmployee = validaEmpleadoActivo(row.getPersonId().toString(), oaapplicationmodule);        
                }catch(Exception e){
                    v_estatusEmployee = "NA";
                    System.out.println("Un error al recuperar v_estatusEmployee");  
                }         
               
               if("G".equals(action)) {
                 
                    if (!v_estatusEmployee.toString().equals("1") && !v_estatusEmployee.toString().equals("NA")){
                        String lMessageErrorEmpInctv = constantesErrors.ERROR_EMPLOYEE_NOT_ACTIVE.toString();      
                        selectIter.closeRowSetIterator();
                        MessageToken[] tokens = new MessageToken[]{new MessageToken("MESSAGE", lMessageErrorEmpInctv)};
                        throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens);
                    }else if("CREADO".equals(row.getStatus()) || "EJECUCION".equals(row.getStatus()) || "CANCELADO".equals(row.getStatus()) || "CERRADO".equals(row.getStatus()) || "PREAPROBADO".equals(row.getStatus())) {
                         String lMessageE = "La solicitud no se pudo generar por que ya fue Aprobada, Cerrada o Cancelada.";
                         pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - error: " + lMessageE, 3);
                         MessageToken[] tokens = new MessageToken[]{new MessageToken("MESSAGE", lMessageE)};
                         throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens);
                  }

                  lSoliIdSTR = row.getSoliId().toString();
                  String sqlGen = "SELECT header_id FROM xxgam_inv_soli WHERE header_id IS NOT NULL AND soli_id = ? ";
                  OracleCallableStatement pstmtGen = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(sqlGen, 1);

                  try {
                     pstmtGen.setNUMBER(1, row.getSoliId());
                     ResultSet rsGen = pstmtGen.executeQuery();
                     if(rsGen.next()) {
                        String lMessageE1 = "La solicitud no se puedo generar por que ya tiene un Pedido de Movimiento generado.";
                        pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - error: " + lMessageE1, 3);
                        MessageToken[] tokens1 = new MessageToken[]{new MessageToken("MESSAGE", lMessageE1)};
                        throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens1);
                     }

                     rsGen.close();
                  } catch (SQLException var59) {
                     pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - sqlGen Error: " + var59.getMessage().toString(), 3);
                  } finally {
                     try {
                        pstmtGen.close();
                     } catch (Exception var58) {
                        pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - sqlGen Error: " + var58.getMessage().toString(), 3);
                        throw OAException.wrapperException(var58);
                     }
                  }

                  lReqId = this.callConcMoveOrder(lSoliIdSTR, "XXGAM_INV_CREATE_MV", "XXGAM INV Crear Move Order", pageContext, webBean);
               } else if("C".equals(action)) {  
                      if (!v_estatusEmployee.toString().equals("1") && !v_estatusEmployee.toString().equals("NA")){
                          String lMessageErrorEmpInctv = constantesErrors.ERROR_EMPLOYEE_NOT_ACTIVE.toString();      
                          selectIter.closeRowSetIterator();
                          MessageToken[] tokens = new MessageToken[]{new MessageToken("MESSAGE", lMessageErrorEmpInctv)};
                          throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens);
                      }else if("CREADO".equals(row.getStatus()) || "EJECUCION".equals(row.getStatus()) || "CANCELADO".equals(row.getStatus()) || "CERRADO".equals(row.getStatus())) {
                     String lMessageE2 = "La solicitud no se puede cancelar por que ya fue Cerrada o Cancelada.";
                     pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - error: " + lMessageE2, 3);
                     MessageToken[] tokens2 = new MessageToken[]{new MessageToken("MESSAGE", lMessageE2)};
                     throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens2);
                  }

                  lSoliIdSTR = row.getSoliId().toString();
                  String sqlCancel = "SELECT line_id FROM xxgam_inv_soli_dtl xisd WHERE EXISTS (SELECT 1       FROM mtl_txn_request_lines mtrl       WHERE mtrl.line_id = xisd.line_id       AND (nvl(mtrl.line_status,-1) = 5             OR           mtrl.QUANTITY_DELIVERED IS NOT NULL)) AND xisd.line_id       IS NOT NULL AND xisd.soli_id      = ?  ";
                  OracleCallableStatement pstmtCancel = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(sqlCancel, 1);

                  try {
                     pstmtCancel.setNUMBER(1, row.getSoliId());
                     ResultSet rsCancel = pstmtCancel.executeQuery();
                     if(rsCancel.next()) {
                        String lMessageE3 = "La solicitud no se puede cancelar por que alguna de sus lineas esta Cerrada o se recepciono uniforme.";
                        pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - error: " + lMessageE3, 3);
                        MessageToken[] tokens3 = new MessageToken[]{new MessageToken("MESSAGE", lMessageE3)};
                        throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens3);
                     }

                     rsCancel.close();
                  } catch (SQLException var61) {
                     pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - sqlCancel Error: " + var61.getMessage().toString(), 3);
                  } finally {
                     try {
                        pstmtCancel.close();
                     } catch (Exception var57) {
                        pageContext.writeDiagnostics(this, "xXGamInvSolicitudCO - sqlCancel Error: " + var57.getMessage().toString(), 3);
                        throw OAException.wrapperException(var57);
                     }
                  }

                  lReqId = this.callConcMoveOrder(lSoliIdSTR, "XXGAM_INV_CANCEL_MV", "XXGAM INV Cancelar Move Order", pageContext, webBean);
               }
            }
         }

         selectIter.closeRowSetIterator();
      }

      if(lReqId.compareTo(0) > 0) {
         if("C".equals(action)) {
            MessageToken[] tokens4 = new MessageToken[]{new MessageToken("MESSAGE", "Se ejecuto la cancelaci\u00f3n del pedido verifique el concurrente REQUEST ID: " + lReqId.toString())};
            OAException message = new OAException("FND", "FND_GENERIC_MESSAGE", tokens4, (byte)3, (Exception[])null);
            pageContext.putDialogMessage(message);
         } else {
            MessageToken[] tokens5 = new MessageToken[]{new MessageToken("MESSAGE", "Se ejecuto la generaci\u00f3n del pedido verifique el concurrente REQUEST ID: " + lReqId.toString())};
            OAException message1 = new OAException("FND", "FND_GENERIC_MESSAGE", tokens5, (byte)3, (Exception[])null);
            pageContext.putDialogMessage(message1);
         }

      } else if(lReqId.compareTo(-1) > 0) {
         String lMessageE4 = "Error al ejecutar el concurrente.";
         MessageToken[] tokens6 = new MessageToken[]{new MessageToken("MESSAGE", lMessageE4)};
         throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens6);
      } else {
         String lMessageErr = "No se ha seleccionado ninguna Solicitud.";
         MessageToken[] tokens7 = new MessageToken[]{new MessageToken("MESSAGE", lMessageErr)};
         throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens7);
      }
   }

   public BlobDomain getSoliData(String strSoliId, OAPageContext pageContext, OAWebBean webBean) {
      BlobDomain blobdomain = new BlobDomain();
      OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
      OADBTransactionImpl oadbtransactionimpl = (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
      OracleCallableStatement pstmt = null;

      try {
         Number lSoliId = new Number(strSoliId);
             String sqlCBlob = "BEGIN ? := XXGAM_INV_SOLI_DOTA_PKG.GeneraXML(?); END; ";
         pstmt = (OracleCallableStatement)oadbtransactionimpl.createCallableStatement(sqlCBlob, 1);
         pstmt.registerOutParameter(1, 2005);
         pstmt.setNUMBER(2, lSoliId);
         pstmt.execute();
         CLOB clob = pstmt.getCLOB(1);
         OutputStream outputstream = blobdomain.getBinaryOutputStream();
         long lLength = clob.length();
         byte[] abyte0 = new byte[(int)lLength];
         String s13 = clob.getSubString(1L, (int)lLength);
         abyte0 = s13.getBytes("UTF-8");
         outputstream.write(abyte0);
         outputstream.close();
         pstmt.close();
         pstmt = null;
         return blobdomain;
      } catch (SQLException var18) {
         throw new OAException("SQL Error=" + var18.getMessage(), (byte)0);
      } catch (Exception var19) {
         throw new OAException("Exception=" + var19.getMessage(), (byte)0);
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
      } catch (RequestSubmissionException var14) {
         pageContext.writeDiagnostics(this, "xXGamSolicitudActualizaCO - " + var14.toString(), 3);
      } catch (Exception var15) {
         pageContext.writeDiagnostics(this, "xXGamSolicitudActualizaCO - " + var15.toString(), 3);
      }

      return new Number(lRequestId);
   }

   public void callConcSoli(String strSoliId, OAPageContext pageContext, OAWebBean webBean) {
      OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
      Vector lParam = new Vector();
      lParam.addElement(strSoliId);
      String lAppCode = "XBOL";
      String lConcReqCode = "XXGAM_INV_SOLI_DOTA";
      int lRequestId = 0;

      try {
         ConcurrentRequest lConc = new ConcurrentRequest(oaapplicationmodule.getOADBTransaction().getJdbcConnection());
         lConc.addLayout(lAppCode, lConcReqCode, "en", "US", "PDF", "FF");
         lRequestId = lConc.submitRequest(lAppCode, lConcReqCode, "XXGAM INV Dotaciones", (String)null, false, lParam);
         oaapplicationmodule.getOADBTransaction().commit();
      } catch (RequestSubmissionException var15) {
         pageContext.writeDiagnostics(this, "xXGamSolicitudActualizaCO - " + var15.toString(), 3);
      } catch (Exception var16) {
         pageContext.writeDiagnostics(this, "xXGamSolicitudActualizaCO - " + var16.toString(), 3);
      }

      String lRequestIdD = String.valueOf(lRequestId);
      HashMap parameters = new HashMap();
      String url = "OA.jsp";
      parameters.put("akRegionApplicationId", "0");
      parameters.put("akRegionCode", "FNDCPREQUESTVIEWPAGE");
      parameters.put("requestMode", "DEFERRED");
      parameters.put("requestId", lRequestIdD);
      pageContext.setForwardURL(url, (String)null, (byte)0, (String)null, parameters, true, "N", (byte)99);
   }

    /**
    * @param pageContext the current OA page context
    * @param file_name_with_path - this is fully qualified file name with its path on unix application
    * server. eg "/"
    * @param file_name_with_ext - this is file name with extension, you wanna display user
    * for download. eg- i wanna display the abc.pdf file download with name five_point_someone.pdf
    * then I can pass this as "five_point_someone.pdf"
    */
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

    /**
    * @param s
    * @return file mime type from its name
    */
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
  public void clearSearchPanel(OAPageContext pageContext, OAWebBean webBean){
      OAMessageChoiceBean StatusChoiceBean = (OAMessageChoiceBean)webBean.findChildRecursive("StatusChoice"); 
      OAMessageLovInputBean NroSolicitudDesdeBean = (OAMessageLovInputBean)webBean.findChildRecursive("NroSolicitudDesde");
      
      OAMessageLovInputBean NroSolicitudHastaBean = (OAMessageLovInputBean)webBean.findChildRecursive("NroSolicitudHasta");
      
      OAMessageLovInputBean ClaveEmpleadoBean = (OAMessageLovInputBean)webBean.findChildRecursive("ClaveEmpleado");
      OAMessageDateFieldBean FechaSolicitudHastaBean = (OAMessageDateFieldBean)webBean.findChildRecursive("FechaSolicitudHasta");
      OAMessageDateFieldBean FechaSolicitudDesdeBean = (OAMessageDateFieldBean)webBean.findChildRecursive("FechaSolicitudDesde");
      
      StatusChoiceBean.setText(pageContext, "");
      NroSolicitudDesdeBean.setText(pageContext, "");
      NroSolicitudHastaBean.setText(pageContext, "");
      ClaveEmpleadoBean.setText(pageContext, "");
      FechaSolicitudHastaBean.setText("");
      FechaSolicitudHastaBean.setValue(pageContext, "");
      
      FechaSolicitudDesdeBean.setText( "");
      FechaSolicitudDesdeBean.setValue(pageContext, "");
  }  

    public void SearchPanelAltaernative(OAPageContext pageContext, OAWebBean webBean){
        
        String WhereClause = " 1 = 1 ";
        
        OAMessageChoiceBean StatusChoiceBean = (OAMessageChoiceBean)webBean.findChildRecursive("StatusChoice"); 
        
        OAMessageLovInputBean NroSolicitudDesdeBean = (OAMessageLovInputBean)webBean.findChildRecursive("NroSolicitudDesde");
        OAMessageLovInputBean NroSolicitudHastaBean = (OAMessageLovInputBean)webBean.findChildRecursive("NroSolicitudHasta");
        OAMessageLovInputBean ClaveEmpleadoBean = (OAMessageLovInputBean)webBean.findChildRecursive("ClaveEmpleado");
        OAMessageDateFieldBean FechaSolicitudHastaBean = (OAMessageDateFieldBean)webBean.findChildRecursive("FechaSolicitudHasta");
        OAMessageDateFieldBean FechaSolicitudDesdeBean = (OAMessageDateFieldBean)webBean.findChildRecursive("FechaSolicitudDesde");

        /*Filtro de Estatus */
        try{
            if (!StatusChoiceBean.getValue(pageContext).toString().equals(null)){
                WhereClause = WhereClause + " AND status LIKE " + StatusChoiceBean.getValue(pageContext).toString();
            }        
        }catch(Exception e){
            System.out.println("");
        }        
        /*Filtro de Clave Empleado */
        try{
            if (!ClaveEmpleadoBean.getValue(pageContext).toString().equals(null)){
                WhereClause = WhereClause + " AND status LIKE " + ClaveEmpleadoBean.getValue(pageContext).toString();
            }        
        }catch(Exception e){
            System.out.println("");
        }


        /*Filtro de Fecha Solicitud desde/ hasta */
        try{
            String SolicitudDesde = "";  
            String SolicitudHasta = "";  
            try{
                if (!NroSolicitudDesdeBean.getValue(pageContext).toString().equals(null)){
                    SolicitudDesde = NroSolicitudDesdeBean.getValue(pageContext).toString();
                }
            }catch(Exception e){
                SolicitudDesde = "";
            }
            try{
                if (!NroSolicitudHastaBean.getValue(pageContext).toString().equals(null)){
                    SolicitudHasta = NroSolicitudHastaBean.getValue(pageContext).toString();
                }
            }catch(Exception e){
                SolicitudHasta = "";
            }
            
            if (SolicitudHasta.equals("")){
                WhereClause = WhereClause + "" + SolicitudHasta;
            }
            
            
        }catch(Exception e){
            System.out.println("");
        }



      System.out.println("WhereClause: " + WhereClause);  

    } 

    public String validSearchPanel(OAPageContext pageContext, OAWebBean webBean){
    
        String validData = "Y";        
/*
        
        OAMessageChoiceBean StatusChoiceBean = (OAMessageChoiceBean)webBean.findChildRecursive("StatusChoice"); 
        OAMessageLovInputBean NroSolicitudDesdeBean = (OAMessageLovInputBean)webBean.findChildRecursive("NroSolicitudDesde");
        OAMessageLovInputBean NroSolicitudHastaBean = (OAMessageLovInputBean)webBean.findChildRecursive("NroSolicitudHasta");
        OAMessageLovInputBean ClaveEmpleadoBean = (OAMessageLovInputBean)webBean.findChildRecursive("ClaveEmpleado");
        OAMessageDateFieldBean FechaSolicitudHastaBean = (OAMessageDateFieldBean)webBean.findChildRecursive("FechaSolicitudHasta");
        OAMessageDateFieldBean FechaSolicitudDesdeBean = (OAMessageDateFieldBean)webBean.findChildRecursive("FechaSolicitudDesde");

        
        try{
            if (!StatusChoiceBean.getValue(pageContext).toString().equals(null)){
                
                validData = "N";
            }        
        }catch(Exception e){
            validData = "Y";
        }        
        
        try{
            if (!ClaveEmpleadoBean.getValue(pageContext).toString().equals(null)){
                validData = "N";
            }        
        }catch(Exception e){
            validData = "Y";
        }

        
        try{
            if (!NroSolicitudDesdeBean.getValue(pageContext).toString().equals(null)){
                validData = "N";
            }
        }catch(Exception e){
            validData = "Y";
        }
        
        
        try{
            if (!NroSolicitudHastaBean.getValue(pageContext).toString().equals(null)){
                validData = "N";
            }
        }catch(Exception e){
            validData = "Y";
        }
            

        
        try{
            if (!FechaSolicitudHastaBean.getValue(pageContext).toString().equals(null)){
                validData = "N";
            }
        }catch(Exception e){
            validData = "Y";
        }
        
        
        try{
            if (!FechaSolicitudDesdeBean.getValue(pageContext).toString().equals(null)){
                validData = "N";
            }
        }catch(Exception e){
            validData = "Y";
        }        */
        
        if(!pageContext.getParameter("filter1").equals("")){
            validData = "N";
        }    
        if(!pageContext.getParameter("filter2").equals("")){
            validData = "N";
        }    
        if(!pageContext.getParameter("filter3").equals("")){
            validData = "N";
        }    
        if(!pageContext.getParameter("filter4").equals("")){
            validData = "N";
        }    
        if(!pageContext.getParameter("filter5").equals("")){
            validData = "N";
        }    
        if(!pageContext.getParameter("filter6").equals("")){
            validData = "N";
        }    
        
        
    System.out.println("validData: " + validData);
        return validData;
    }

    public int validQueryInit(String pCadena){
        
        int i;
        int indice = 0;
        int contador = 0;
        int m=0;
        
        boolean  y;
        y = true;
        System.out.println("y: " + y); 
        
        for(i=0;i<pCadena.length();i++){
            if(pCadena.substring(m,i+1).equals(":")){
                //System.out.println("i: " + i);
                indice = i + 1;
                contador = contador + 1;
            }
            
            m++;
        }    
    
    return contador;
    }


  /**
   * Metodo que deshabilita regiones en funcion de los 
   * perfiles que tenga asignados el usuario de aplicacion
   * @param pageContext
   * @param webBean
   */
  private void disableRegionsByProfiles(OAPageContext pageContext, 
                                        OAWebBean webBean)
  {
    String lperfil = pageContext.getProfile("XXGAM_INV_USUARIO_UNIFORME");
    if(null!=lperfil&&!"".equals(lperfil)){
      if(!"Y".equals(lperfil)){
        OAWebBean columnUpdateBean = webBean.findChildRecursive("columnUpdate"); 
        if(null!=columnUpdateBean){
          columnUpdateBean.setRendered(false);
        }
        
        OAWebBean crearsolicitudBean = webBean.findChildRecursive("crearsolicitud"); 
        if(null!=crearsolicitudBean){
          crearsolicitudBean.setRendered(false);
        }
        
      }
    }else{
      OAWebBean columnUpdateBean = webBean.findChildRecursive("columnUpdate"); 
      if(null!=columnUpdateBean){
        columnUpdateBean.setRendered(false);
      }
      
      OAWebBean crearsolicitudBean = webBean.findChildRecursive("crearsolicitud"); 
      if(null!=crearsolicitudBean){
        crearsolicitudBean.setRendered(false);
      }
      
    }

  } /** End private void disableRegionsByProfiles **/
  
}
