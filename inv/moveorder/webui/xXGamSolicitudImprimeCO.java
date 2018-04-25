package xxgam.oracle.apps.inv.moveorder.webui;

import com.sun.java.util.collections.Hashtable;
import java.io.OutputStream;
import java.sql.SQLException;
import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAApplicationModule;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.xdo.XDOException;
import oracle.apps.xdo.oa.util.DataTemplate;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.Number;
import oracle.jdbc.OracleCallableStatement;
import oracle.sql.CLOB;
import xxgam.oracle.apps.inv.moveorder.xxDebugger;

public class xXGamSolicitudImprimeCO extends OAControllerImpl {

   public static final String RCS_ID = "$Header: xXGamSolicitudImprimeCO.java 1.1 2013/02/27 10:51:48 eroncoroni ship $";
   public static final boolean RCS_ID_RECORDED = VersionInfo.recordClassVersion("$Header: xXGamSolicitudImprimeCO.java 1.1 2013/02/27 10:51:48 eroncoroni ship $", "%packagename%");


   public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
      super.processRequest(pageContext, webBean);
      OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
      xxDebugger.oadbtransaction = oaapplicationmodule.getOADBTransaction();
      pageContext.writeDiagnostics(this, "XXGAM Dotaciones - Version 1.0", 3);
      String SoliId = pageContext.getParameter("SoliId");
      pageContext.writeDiagnostics(this, "XXGAM Dotaciones - SoliId" + SoliId, 3);
      pageContext.putParameter("p_DataSource", "BlobDomain");
      pageContext.putParameter("p_DataSourceCode", "XXGAM_INV_SOLI_DOTA");
      pageContext.putParameter("p_DataSourceAppsShortName", "XBOL");
      pageContext.putParameter("p_TemplateCode", "XXGAM_INV_SOLI_DOTA");
      pageContext.putParameter("p_TemplateAppsShortName", "XBOL");
      pageContext.putParameter("p_Locale", "English:United States");
      pageContext.putParameter("p_OutputType", "PDF");
      pageContext.putParameter("p_XDORegionHeight", "200%");
      BlobDomain blobDomain = null;

      try {
         blobDomain = this.getSoliDataPKG(SoliId, pageContext, webBean);
      } catch (Exception var8) {
         OAException oaexception = OAException.wrapperException(var8);
      }

      pageContext.putSessionValueDirect("XML_DATA_BLOB", blobDomain);
   }

   public void processFormRequest(OAPageContext pageContext, OAWebBean webBean) {
      super.processFormRequest(pageContext, webBean);
   }

   public BlobDomain getSoliDataPKG(String strSoliId, OAPageContext pageContext, OAWebBean webBean) {
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

   public BlobDomain getSoliDataTMPL(String strSoliId, OAPageContext pageContext, OAWebBean webBean) {
      OAApplicationModule oaapplicationmodule = pageContext.getApplicationModule(webBean);
      OADBTransactionImpl oadbtransactionimpl = (OADBTransactionImpl)oaapplicationmodule.getOADBTransaction();
      BlobDomain blobDomain = new BlobDomain();

      try {
         String lApps = "XBOL";
         String lTemp = "XXGAM_INV_SOLI_DOTA";
         DataTemplate datatemplate = new DataTemplate(oadbtransactionimpl.getAppsContext(), lApps, lTemp);
         Hashtable parameters = new Hashtable();
         parameters.put("p_soli_id", strSoliId);
         datatemplate.setParameters(parameters);
         datatemplate.setOutput(blobDomain.getBinaryOutputStream());
         datatemplate.processData();
         return blobDomain;
      } catch (SQLException var14) {
         throw new OAException("SQL Error=" + var14.getMessage(), (byte)0);
      } catch (XDOException var15) {
         throw new OAException("XDOException=" + var15.getMessage(), (byte)0);
      } catch (Exception var16) {
         throw new OAException("Exception=" + var16.getMessage(), (byte)0);
      }
   }

}