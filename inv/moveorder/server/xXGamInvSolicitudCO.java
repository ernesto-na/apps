package xxgam.oracle.apps.inv.moveorder.server;

import com.sun.java.util.collections.HashMap;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
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
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAPageLayoutBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAQueryBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageChoiceBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageDateFieldBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.jbo.RowSetIterator;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.Number;
import oracle.jdbc.OracleCallableStatement;
import oracle.sql.CLOB;
 /** import xxgam.oracle.apps.fnd.xxDebugger;  se quita 09012017 **/
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliSummaryVOImpl;
import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliSummaryVORowImpl;

@SuppressWarnings({"unchecked", "deprecation","rawtypes","static"})
public class xXGamInvSolicitudCO extends OAControllerImpl {

   public static final String RCS_ID = "$Header: xXGamInvSolicitudCO.java 1.1 2013/02/07 10:51:48 eroncoroni ship $";
   public static final boolean RCS_ID_RECORDED = VersionInfo.recordClassVersion("$Header: xXGamInvSolicitudCO.java 1.1 2013/02/07 10:51:48 eroncoroni ship $", "%packagename%");


   public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
      super.processRequest(pageContext, webBean);
      OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
   /**   xxDebugger.oadbtransaction = oaapplicationmodule.getOADBTransaction();    Se quita 09/01/2017**/ 
      pageContext.writeDiagnostics(this, "XXGAM INV Solicitudes - Version 1.0", 3);
      String SoliId = pageContext.getParameter("SoliId");
      pageContext.writeDiagnostics(this, "XXGAM INV Solicitudes ", 3);
   }

   public void processFormRequest(OAPageContext pageContext, OAWebBean webBean) {
      super.processFormRequest(pageContext, webBean);
      OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
      OADBTransactionImpl oadbtransactionimpl = (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
      OAPageLayoutBean oapagelayoutbean = pageContext.getPageLayoutBean();
      OAQueryBean queryBean = (OAQueryBean)webBean.findIndexedChildRecursive("QuerySolicitudRN");
      String lEvent = pageContext.getParameter("event");
      String lSource = pageContext.getParameter("source");
      Number lUserId = new Number(0);
      Number lSoliId = new Number(0);
      Number lSoliDtlId = new Number(0);
      String lMessageError = null;
      String idClear = queryBean.getClearButtonName();
      OASubmitButtonBean clear = (OASubmitButtonBean)queryBean.findChildRecursive(idClear);
      String idGo = queryBean.getGoButtonName();
      OASubmitButtonBean go = (OASubmitButtonBean)queryBean.findChildRecursive(idGo);
      if(pageContext.getParameter(idGo) != null && !"NroSolicitudDesde".equals(lSource) && !"NroSolicitudDesde".equals(lSource)) {
         OAMessageLovInputBean oaNroSoliDesdeBean = (OAMessageLovInputBean)oapagelayoutbean.findIndexedChildRecursive("NroSolicitudDesde");
         String lNroSoliD = (String)oaNroSoliDesdeBean.getValue(pageContext);
         OAMessageLovInputBean oaNroSoliHastaBean = (OAMessageLovInputBean)oapagelayoutbean.findIndexedChildRecursive("NroSolicitudHasta");
         String lNroSoliH = (String)oaNroSoliHastaBean.getValue(pageContext);
         OAMessageDateFieldBean oaFechaDesdeBEEBean = (OAMessageDateFieldBean)oapagelayoutbean.findIndexedChildRecursive("FechaSolicitudDesde");
         Date lFecha = null;
         Object lFechaDesde = null;
         if(oaFechaDesdeBEEBean.getValue(pageContext) != null) {
            Timestamp ts = (Timestamp)oaFechaDesdeBEEBean.getValue(pageContext);
            lFecha = new Date(ts.getTime());
            new oracle.jbo.domain.Date(lFecha);
         }

         OAMessageDateFieldBean oaFechaHastaBEEBean = (OAMessageDateFieldBean)oapagelayoutbean.findIndexedChildRecursive("FechaSolicitudHasta");
         Date lFecha1 = null;
         Object lFechaHasta = null;
         if(oaFechaHastaBEEBean.getValue(pageContext) != null) {
            Timestamp ts1 = (Timestamp)oaFechaHastaBEEBean.getValue(pageContext);
            lFecha1 = new Date(ts1.getTime());
            new oracle.jbo.domain.Date(lFecha1);
         }

         OAMessageChoiceBean oaStatusBean = (OAMessageChoiceBean)oapagelayoutbean.findIndexedChildRecursive("StatusChoice");
         String lStatus = (String)oaStatusBean.getValue(pageContext);
         String lSuperUser = oadbtransactionimpl.getProfile("XXGAM_INV_USUARIO_UNIFORME");
         if(!"Y".equals(lSuperUser)) {
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
         } catch (Exception var51) {
            if(oadbtransactionimpl.isLoggingEnabled(4)) {
               oadbtransactionimpl.writeDiagnostics(this, "Exception in calling XXGAM_INV_MOVEORDER_PKG.REFRESH_MOVE_ORDER: " + var51.getMessage(), 4);
            }

            throw OAException.wrapperException(var51);
         } finally {
            try {
               pstmtRRSH.close();
            } catch (Exception var50) {
               throw OAException.wrapperException(var50);
            }
         }

         xXGamInvSoliSummaryVOImpl invSummaryVOImpl = (xXGamInvSoliSummaryVOImpl)oaapplicationmodule.findViewObject("xXGamInvSoliSummaryVO1");
         invSummaryVOImpl.clearCache();
      }

      if("update".equals(lEvent)) {
         pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudActualizaPG", (String)null, (byte)0, (String)null, (HashMap)null, true, "N", (byte)99);
      }

      if("print".equals(lEvent)) {
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
         String lSuperUser1 = oadbtransactionimpl.getProfile("XXGAM_INV_USUARIO_UNIFORME");
         if("Y".equals(lSuperUser1)) {
            pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudCrearSuperUserPG", (String)null, (byte)0, (String)null, (HashMap)null, true, "N", (byte)99);
         } else {
            pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudCrearPG", (String)null, (byte)0, (String)null, (HashMap)null, true, "N", (byte)99);
         }
      }

      if("generarpedido".equals(lEvent)) {
         this.checkSelectedrow(pageContext, webBean, "G");
      }

      if("cancelarpedido".equals(lEvent)) {
         this.checkSelectedrow(pageContext, webBean, "C");
      }

   }

   public void checkSelectedrow(OAPageContext pageContext, OAWebBean webBean, String action) {
      OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
      OAViewObject invSoliSummVO = (OAViewObject)oaapplicationmodule.findViewObject("xXGamInvSoliSummaryVO1");
      String lSoliIdSTR = null;
      Number lReqId = new Number(-1);
      int fetchedRowCount = invSoliSummVO.getFetchedRowCount();
      RowSetIterator selectIter = invSoliSummVO.createRowSetIterator("selectIter");
      if(fetchedRowCount > 0) {
         selectIter.setRangeStart(0);
         selectIter.setRangeSize(fetchedRowCount);

         for(int i = 0; i < fetchedRowCount; ++i) {
            xXGamInvSoliSummaryVORowImpl row = (xXGamInvSoliSummaryVORowImpl)selectIter.getRowAtRangeIndex(i);
            String selectFlag1 = row.getSelectFlag1() + "";
            if("Y".equals(selectFlag1)) {
               if("G".equals(action)) {
                  if("CREADO".equals(row.getStatus()) || "EJECUCION".equals(row.getStatus()) || "CANCELADO".equals(row.getStatus()) || "CERRADO".equals(row.getStatus()) || "PREAPROBADO".equals(row.getStatus())) {
                     String lMessageE = "La solicitud no se puedo generar por que  ya fue Aprobada, Cerrada o Cancelada.";
                     MessageToken[] tokens = new MessageToken[]{new MessageToken("MESSAGE", lMessageE)};
                     throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens);
                  }

                  lSoliIdSTR = row.getSoliId().toString();
                  lReqId = this.callConcMoveOrder(lSoliIdSTR, "XXGAM_INV_CREATE_MV", "XXGAM INV Crear Move Order", pageContext, webBean);
               } else if("C".equals(action)) {
                  if("CREADO".equals(row.getStatus()) || "EJECUCION".equals(row.getStatus()) || "CANCELADO".equals(row.getStatus()) || "CERRADO".equals(row.getStatus())) {
                     String lMessageE1 = "La solicitud no se puede cancela por que ya fue Cerrada o Cancelada.";
                     MessageToken[] tokens1 = new MessageToken[]{new MessageToken("MESSAGE", lMessageE1)};
                     throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens1);
                  }

                  lSoliIdSTR = row.getSoliId().toString();
                  lReqId = this.callConcMoveOrder(lSoliIdSTR, "XXGAM_INV_CANCEL_MV", "XXGAM INV Cancelar Move Order", pageContext, webBean);
               }
            }
         }

         selectIter.closeRowSetIterator();
      }

      if(lReqId.compareTo(0) > 0) {
         if("C".equals(action)) {
            MessageToken[] tokens2 = new MessageToken[]{new MessageToken("MESSAGE", "Se ejecuto la cancelaci\u00f3n del pedido verifique el concurrente REQUEST ID: " + lReqId.toString())};
            OAException message = new OAException("FND", "FND_GENERIC_MESSAGE", tokens2, (byte)3, (Exception[])null);
            pageContext.putDialogMessage(message);
         } else {
            MessageToken[] tokens3 = new MessageToken[]{new MessageToken("MESSAGE", "Se ejecuto la generaci\u00f3n del pedido verifique el concurrente REQUEST ID: " + lReqId.toString())};
            OAException message1 = new OAException("FND", "FND_GENERIC_MESSAGE", tokens3, (byte)3, (Exception[])null);
            pageContext.putDialogMessage(message1);
         }

      } else if(lReqId.compareTo(-1) > 0) {
         String lMessageE2 = "Error al ejecutar el concurrente.";
         MessageToken[] tokens4 = new MessageToken[]{new MessageToken("MESSAGE", lMessageE2)};
         throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens4);
      } else {
         String lMessageErr = "No se ha seleccionado ninguna Solicitud.";
         MessageToken[] tokens5 = new MessageToken[]{new MessageToken("MESSAGE", lMessageErr)};
         throw new OAException("FND", "FND_GENERIC_MESSAGE", tokens5);
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
      } catch (RequestSubmissionException var16) {
         new OAException(var16.toString());
      } catch (Exception var17) {
         ;
      }

      String lRequestIdD = String.valueOf(lRequestId);
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
      } catch (RequestSubmissionException var16) {
         new OAException(var16.toString());
      } catch (Exception var17) {
         ;
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

}
