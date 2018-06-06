/*    */ package xxgam.oracle.apps.xbol.bref.webui;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */
import java.math.BigDecimal;

import java.sql.Types;

import oracle.apps.fnd.common.VersionInfo;
/*    */
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAFwkConstants;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
/*    */ import oracle.apps.fnd.framework.webui.OAPageContext;
/*    */ import oracle.apps.fnd.framework.webui.beans.OAWebBean;
/*    */ import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;
/*    */ import oracle.jbo.Row;
/*    */
import oracle.jbo.server.DBTransaction;

import oracle.jdbc.OracleCallableStatement;

import xxgam.oracle.apps.xbol.bref.server.BankReferenceAMImpl;
/*    */ import xxgam.oracle.apps.xbol.bref.server.BankReferenceVOImpl;
import xxgam.oracle.apps.xbol.maf.utils.XxGamMAnticiposUtil2;
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
             String BancoS = null;
/* 40 */     String ClabeS = null;
/*    */     String CuentaS = null;
/*    */     String SucursalS = null;
             String ReferenciaS = null;
/*    */     String AccountS = null;
/*    */     String BankS = null;
             String BankCodeS = null;
/*    */     String BranchCodeS = null;
/*    */     String IbanS = null;
             String AbaS = null;
             String SwiftS = null;
             String CurrencyS = null;
/*    */     
/* 42 */     if (!pageContext.isFormSubmission()) {
/* 43 */       BankRefAM = (BankReferenceAMImpl)pageContext.getApplicationModule(webBean);
/*    */       
/* 45 */       if (BankRefAM != null) {
/* 46 */         BankRefAM.ClearInfo();
/* 47 */         BankRefAM.FindInfo();
/* 48 */         BankRefVO = BankRefAM.getBankReferenceVO1();
/*    */         
/* 50 */         if (BankRefVO.first().getAttribute("Nombrebanco") != null) {
/* 51 */           BancoS = BankRefVO.first().getAttribute("Nombrebanco").toString();
/*    */         }
/*    */         if (BankRefVO.first().getAttribute("Cuenta") != null) {
/*    */           CuentaS = BankRefVO.first().getAttribute("Cuenta").toString();
/*    */         }
/*    */         if (BankRefVO.first().getAttribute("Sucursal") != null) {
/*    */             SucursalS = BankRefVO.first().getAttribute("Sucursal").toString();
/*    */         }
                 if (BankRefVO.first().getAttribute("Referencia") != null) {
/*    */             SucursalS = BankRefVO.first().getAttribute("Referencia").toString();
/*    */         }
                 if (BankRefVO.first().getAttribute("Account") != null) {
/*    */             AccountS = BankRefVO.first().getAttribute("Account").toString();
/*    */         }
                 if (BankRefVO.first().getAttribute("Bank") != null) {
/*    */             BankS = BankRefVO.first().getAttribute("Bank").toString();
/*    */         }
                 if (BankRefVO.first().getAttribute("Bankcode") != null) {
/*    */             BankCodeS = BankRefVO.first().getAttribute("Bankcode").toString();
/*    */         }
                 if (BankRefVO.first().getAttribute("Branchcode") != null) {
/*    */             BranchCodeS = BankRefVO.first().getAttribute("Branchcode").toString();
/*    */         }
                 if (BankRefVO.first().getAttribute("Iban") != null) {
/*    */             IbanS = BankRefVO.first().getAttribute("Iban").toString();
/*    */         }
                 if (BankRefVO.first().getAttribute("Aba") != null) {
/*    */             AbaS = BankRefVO.first().getAttribute("Aba").toString();
/*    */         }
                 if (BankRefVO.first().getAttribute("Swift") != null) {
/*    */             SwiftS = BankRefVO.first().getAttribute("Swift").toString();
/*    */         }
                 if (BankRefVO.first().getAttribute("Currency") != null) {
/*    */             CurrencyS = BankRefVO.first().getAttribute("Currency").toString();
/*    */         }
                 
/*    */       
/* 57 */       //System.out.println("Clabe: " + ClabeS);
/*    */       
/* 59 */       if (BancoS == null) {
/* 60 */         System.out.println("ocultando campo Banco ");
/* 61 */         oaWebBean = webBean.findChildRecursive("Nombrebanco");
/* 62 */         oaWebBean.setRendered(false);
/*    */       }
               
               if (CuentaS == null){
 /*    */            System.out.println("ocultando campo Cuenta ");
 /*    */            oaWebBean = webBean.findChildRecursive("Cuenta");
 /* 68 */            oaWebBean.setRendered(false);
 /*    */      }
               if (SucursalS == null){
  /*    */            System.out.println("ocultando campo Sucursal ");
  /*    */            oaWebBean = webBean.findChildRecursive("Sucursal");
  /* 68 */            oaWebBean.setRendered(false);
  /*    */     }  
               if (ReferenciaS == null){
   /*    */            System.out.println("ocultando campo Referencia ");
   /*    */            oaWebBean = webBean.findChildRecursive("Referencia");
   /* 68 */            oaWebBean.setRendered(false);
   /*    */    }  
               if (AccountS == null){
   /*    */            System.out.println("ocultando campo Account ");
   /*    */            oaWebBean = webBean.findChildRecursive("Account");
   /* 68 */            oaWebBean.setRendered(false);
   /*    */    }  
               if (BankS == null){
   /*    */            System.out.println("ocultando campo Bank ");
   /*    */            oaWebBean = webBean.findChildRecursive("Bank");
   /* 68 */            oaWebBean.setRendered(false);
   /*    */    }  
               if (BankCodeS == null){
   /*    */            System.out.println("ocultando campo Bank Code ");
   /*    */            oaWebBean = webBean.findChildRecursive("Bankcode");
   /* 68 */            oaWebBean.setRendered(false);
   /*    */    }  
               if (BranchCodeS == null){
   /*    */            System.out.println("ocultando campo Branch Code ");
   /*    */            oaWebBean = webBean.findChildRecursive("Branchcode");
   /* 68 */            oaWebBean.setRendered(false);
   /*    */    }  
               if (IbanS == null){
   /*    */            System.out.println("ocultando campo Iban ");
   /*    */            oaWebBean = webBean.findChildRecursive("Iban");
   /* 68 */            oaWebBean.setRendered(false);
   /*    */    }  
               if (AbaS == null){
   /*    */            System.out.println("ocultando campo Aba ");
   /*    */            oaWebBean = webBean.findChildRecursive("Aba");
   /* 68 */            oaWebBean.setRendered(false);
   /*    */    }  
               if (SwiftS == null){
   /*    */            System.out.println("ocultando campo Swift ");
   /*    */            oaWebBean = webBean.findChildRecursive("Swift");
   /* 68 */            oaWebBean.setRendered(false);
   /*    */    }  
               if (CurrencyS == null){
   /*    */            System.out.println("ocultando campo Currency ");
   /*    */            oaWebBean = webBean.findChildRecursive("Currency");
   /* 68 */            oaWebBean.setRendered(false);
   /*    */    }
   
/*    */      }
/*    */    }
/*    */  }
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




 /*public static String get_kind_Employee( Integer personID, 
                                           OAPageContext pageContext, 
                                           OADBTransaction oadbTransaction)
 {
    String retval = null;
   
   String errmsgOUT = null; 
   String errcodOUT = null;
   String orgName = null; 
              
   StringBuffer stmt= new StringBuffer(); 
   stmt.append("BEGIN "); 
   stmt.append("apps.xxgam_ap_mod_ant_utils2_pkg.get_kind_Employee("); 
   stmt.append(" pni_person_id          => :1"); 
   stmt.append(",pso_orgName            => :2"); 
   stmt.append(",pso_errmsg              => :3");
   stmt.append(",pso_errcod              => :4");
   stmt.append(");");
   stmt.append(" END; ");
   
   OracleCallableStatement cs = (OracleCallableStatement)oadbTransaction.createCallableStatement(stmt.toString(),DBTransaction.DEFAULT);
   
   try{
         cs.setInt(1,personID.intValue());
         cs.registerOutParameter(2,Types.VARCHAR);
         cs.registerOutParameter(3,Types.VARCHAR);
         cs.registerOutParameter(4,Types.VARCHAR);
         cs.execute();
         
              String strAttribute = null;
              BigDecimal bigAttribute = null;
               
              orgName = cs.getString(2);
            errmsgOUT = cs.getString(3); 
            errcodOUT = cs.getString(4); 
      
    } catch (Exception exception2)
   {
      System.out.println("EXCEPTION FROM JAVA AL -->"+exception2.getMessage());    
       pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"EXCEPTION FROM JAVA AL -->"+exception2.getMessage(),OAFwkConstants.PROCEDURE) ;
       throw OAException.wrapperException(exception2);
    }    
    
   if (errcodOUT.equals("0")){ 
        String getData = "Completed Transaction Susess"; 
          retval = orgName; 
          }
      else{
         String getData = errmsgOUT;
          throw new OAException(getData,OAException.ERROR);
        }
        
    return retval;
    
 }*/

/* Location:              C:\Users\GHCM-T430-06_2\Documents\Aeromexico\iExpenses\Fuentes\Link_referencia\bref_01_06_18.zip!\bref\webui\BankReferenceCO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */