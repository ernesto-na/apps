/*    */ package xxgam.oracle.apps.xbol.bref.webui;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import oracle.apps.fnd.common.VersionInfo;
/*    */ import oracle.apps.fnd.framework.webui.OAControllerImpl;
/*    */ import oracle.apps.fnd.framework.webui.OAPageContext;
/*    */ import oracle.apps.fnd.framework.webui.beans.OAWebBean;
/*    */ import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
/*    */ import oracle.jbo.Row;
/*    */ import xxgam.oracle.apps.xbol.bref.server.BankReferenceAMImpl;
/*    */ import xxgam.oracle.apps.xbol.bref.server.BankReferenceVOImpl;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BankReferenceCO
/*    */   extends OAControllerImpl
/*    */ {
/*    */   public static final String RCS_ID = "$Header$";
/* 25 */   public static final boolean RCS_ID_RECORDED = VersionInfo.recordClassVersion("$Header$", "%packagename%");
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void processRequest(OAPageContext pageContext, OAWebBean webBean)
/*    */   {
/* 35 */     super.processRequest(pageContext, webBean);
/*    */     
/* 37 */     BankReferenceAMImpl BankRefAM = null;
/* 38 */     BankReferenceVOImpl BankRefVO = null;
/* 39 */     OAWebBean oaWebBean = null;
/* 40 */     String ClabeS = null;
/*    */     
/* 42 */     if (!pageContext.isFormSubmission()) {
/* 43 */       BankRefAM = (BankReferenceAMImpl)pageContext.getApplicationModule(webBean);
/*    */       
/* 45 */       if (BankRefAM != null) {
/* 46 */         BankRefAM.ClearInfo();
/* 47 */         BankRefAM.FindInfo();
/* 48 */         BankRefVO = BankRefAM.getBankReferenceVO1();
/*    */         
/* 50 */         if (BankRefVO.first().getAttribute("Clabe") != null) {
/* 51 */           ClabeS = BankRefVO.first().getAttribute("Clabe").toString();
/*    */         }
/*    */       }
/*    */       
/* 55 */       System.out.println("Clabe: " + ClabeS);
/*    */       
/* 57 */       if (ClabeS == null) {
/* 58 */         System.out.println("ocultando campo Clabe ");
/* 59 */         oaWebBean = webBean.findChildRecursive("Clabe");
/* 60 */         oaWebBean.setRendered(false);
/* 61 */         BankRefVO.first().setAttribute("Clabe", "X");
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public void processFormRequest(OAPageContext pageContext, OAWebBean webBean)
/*    */   {
/* 74 */     super.processFormRequest(pageContext, webBean);
/* 75 */     if (pageContext.getParameter("download") != null) {
/* 76 */       System.out.println(pageContext.getParameter("download"));
/*    */     }
/*    */     
/* 79 */     BankReferenceAMImpl BankRefAM = null;
/* 80 */     BankReferenceVOImpl BankRefVO = null;
/*    */     
/* 82 */     BankRefAM = (BankReferenceAMImpl)pageContext.getApplicationModule(webBean);
/*    */     
/* 84 */     if (BankRefAM != null)
/*    */     {
/* 86 */       BankRefVO = BankRefAM.getBankReferenceVO1();
/*    */       
/* 88 */       OAMessageTextInputBean ImporteMTIB = (OAMessageTextInputBean)webBean.findChildRecursive("Importe");
/*    */       
/* 90 */       if (ImporteMTIB.getValue(pageContext) != null) {
/* 91 */         System.out.println("Importe: " + ImporteMTIB.getValue(pageContext));
/* 92 */         BankRefVO.first().setAttribute("Importe", ImporteMTIB.getValue(pageContext));
/*    */       }
/*    */       
/* 95 */       pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/xbol/bref/webui/PrintPG", null, (byte)0, null, null, true, "N", (byte)99);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\GHCM-T430-06_2\Documents\Aeromexico\iExpenses\Fuentes\Link_referencia\bref_01_06_18.zip!\bref\webui\BankReferenceCO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */