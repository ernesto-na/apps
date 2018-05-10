package xxgam.oracle.apps.xbol.maf.utils;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import java.util.HashMap;

import java.util.Map;

import oracle.apps.fnd.common.AppsContext;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAFwkConstants;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.OAWebBeanConstants;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.apps.fnd.framework.webui.beans.message.OAMessageLovInputBean;
import oracle.apps.fnd.wf.WFContext;

import oracle.apps.fnd.wf.WFDB;

import oracle.apps.fnd.wf.engine.WFEngineAPI;

import oracle.jbo.RowSetIterator;
import oracle.jbo.server.DBTransaction;

import oracle.jdbc.OracleCallableStatement;

import xxgam.oracle.apps.xbol.maf.lov.server.XxGamModAntLovAMImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaGeneralReqVOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamModAntAMImpl;
import oracle.jbo.domain.Number;

import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaLimitScheduleOptionVORowImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaTemplatePaymentFlexLovVOImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaTemplatePaymentLovVOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaGeneralReqVORowImpl;


public class XxGamMAnticiposUtil2
{
  /**
   * Constructor por defecto.
   */
  public XxGamMAnticiposUtil2() {
  }


  public static  boolean setOriginApproverInGeneral(OAPageContext pageContext, 
                                                OAWebBean webBean)
  {
    //pageContext.writeDiagnostics(this,"Comienza XxGamMAnticiposUtil2.setOriginApproverInGeneral",OAFwkConstants.PROCEDURE) ;
    System.out.println("Comienza XxGamMAnticiposUtil2.setOriginApproverInGeneral"); 
    
    boolean retval = false; 
    
    int lUserdID = 0; 
    lUserdID = pageContext.getUserId(); 
    Number empPositionID = null; 
    Number mgrPositionID = null; 
    Number mgrPersonID = null; 
    String empFullName = null; 
    String mgrFullName = null; 
    
    String errmsgOUT = null; 
    String errcodOUT = null;
       
    
    if ((pageContext != null) && (webBean != null)) {
         XxGamModAntAMImpl amXxGamMod = null;
      amXxGamMod = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean); 
        if(amXxGamMod!=null){
          XxGamModAntLovAMImpl amLov = null;
          amLov = (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();
         
          if(amLov!=null){
            //pageContext.writeDiagnostics(this,"Comienza XxGamMAnticiposUtil2.setOriginApproverInGeneral --> amXxGamMod.setAppproverByHierarchy ",OAFwkConstants.PROCEDURE) ;
            System.out.println("Comienza XxGamMAnticiposUtil2.setOriginApproverInGeneral --> amXxGamMod.setAppproverByHierarchy "); 
                boolean isSuccess = false;
                
                XxGamMaGeneralReqVOImpl generalImpl = amXxGamMod.getXxGamMaGeneralReqVO1();
                XxGamMaGeneralReqVORowImpl generalRow = null;
                generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
                
            
                   StringBuffer stmt= new StringBuffer(); 
                   stmt.append("BEGIN "); 
                   stmt.append("apps.xxgam_ap_mod_ant_utils2_pkg.get_Appprover_By_Hierarchy("); 
                   stmt.append(" pi_user_id          => :1"); 
                   stmt.append(",po_emp_position_id  => :2"); 
                   stmt.append(",po_mgr_position_id  => :3");
                   stmt.append(",po_emp_full_name    => :4");
                   stmt.append(",po_mgr_full_name    => :5");
                   stmt.append(",po_mgr_person_id    => :6");
                   stmt.append(",po_errmsg           => :7");
                   stmt.append(",po_errcod           => :8");
                   stmt.append(");");
                   stmt.append(" END; ");
                   
                OADBTransaction oadbtrans = (OADBTransaction)amXxGamMod.getTransaction();
                OracleCallableStatement cs = (OracleCallableStatement)oadbtrans.createCallableStatement(stmt.toString(),DBTransaction.DEFAULT);
            
            try{
                  cs.setInt(1,lUserdID);
                  cs.registerOutParameter(2,Types.NUMERIC);
                  cs.registerOutParameter(3,Types.NUMERIC);
                  cs.registerOutParameter(4,Types.VARCHAR);
                  cs.registerOutParameter(5,Types.VARCHAR);
                  cs.registerOutParameter(6,Types.NUMERIC);
                  cs.registerOutParameter(7,Types.VARCHAR);
                  cs.registerOutParameter(8,Types.VARCHAR);
                  cs.execute();
                  
                       String strAttribute = null;
                       BigDecimal bigAttribute = null;
                        
                        strAttribute = cs.getString(2);
                        if (strAttribute != null) {
                          bigAttribute = new BigDecimal(strAttribute);
                          if (bigAttribute != null) {
                            empPositionID = new Number (bigAttribute);
                          }
                        }
                        
                       strAttribute = cs.getString(3);
                       if (strAttribute != null) {
                         bigAttribute = new BigDecimal(strAttribute);
                         if (bigAttribute != null) {
                           mgrPositionID = new Number (bigAttribute);
                         }
                       }
                      
                     empFullName = cs.getString(4); 
                     mgrFullName = cs.getString(5); 
                     
                     strAttribute = cs.getString(6);
                     if (strAttribute != null) {
                       bigAttribute = new BigDecimal(strAttribute);
                       if (bigAttribute != null) {
                         mgrPersonID = new Number (bigAttribute);
                       }
                     }
               
                    errmsgOUT = cs.getString(7); 
                    errcodOUT = cs.getString(8); 
               
             } catch (Exception exception2)
            {
            throw OAException.wrapperException(exception2);
             }        
                   
            System.out.println("errmsgOUT:"+errmsgOUT+"\n"+"errcodOUT:"+errcodOUT); 
            
            if (errcodOUT.equals("0")){ 
                 String getData = "Completed Transaction Susess"; 
                // throw new OAException(getData,OAException.CONFIRMATION);
                 System.out.println("mgrPositionID:\n"+mgrPositionID); 
                   System.out.println("mgrFullName:\n"+mgrFullName); 
                   System.out.println("mgrPersonID:\n"+mgrPersonID); 
                 generalRow.setApproverId(mgrPersonID); 
                 generalRow.setFullNameApprover(mgrFullName);
                  /**
                 generalRow.setIsApproverBySys(Boolean.valueOf(false));
                **/
                   generalRow.setIsApproverBySys(Boolean.valueOf(true));
                   
                   retval = true; 
                   
                   if(retval&&(empPositionID!=null)){
                     pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"Comienza  LovAMImpl.initApproverAltLov" ,OAFwkConstants.PROCEDURE);
                     System.out.println("Comienza  LovAMImpl.initApproverAltLov"); 
                     java.util.Map mapResult = new HashMap();
                     Number jobNameId  = null; 
                     Number versionId = null; 
                     boolean isSuccessTmp = false; 
                     XxGamModAntLovAMImpl LovAMImpl  = null; 
                     LovAMImpl = (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();
                      if (LovAMImpl!=null){
                        mapResult = amXxGamMod.callProceduceGetPositionEmployee(empPositionID); 
                        System.out.println("mapResult!=null"+mapResult!=null); 
                        if (mapResult!=null){
                          jobNameId = (Number)mapResult.get("jobNameId"); 
                           versionId = (Number)mapResult.get("versionId");
                           System.out.println("jobNameId"+jobNameId);
                           System.out.println("versionId"+versionId);
                           isSuccessTmp = LovAMImpl.initApproverAltLov(jobNameId, versionId);
                           System.out.println("isSuccessTmp"+isSuccessTmp);
                           pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"Resulatado  LovAMImpl.initApproverAltLov: "+isSuccessTmp ,OAFwkConstants.PROCEDURE);
                         }    
                      }
                     System.out.println("Finaliza  LovAMImpl.initApproverAltLov"); 
                     pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"Finaliza  LovAMImpl.initApproverAltLov " ,OAFwkConstants.PROCEDURE);
                     
                   }
                   
                 }else{
                   String getData = errmsgOUT;
                   throw new OAException(getData,OAException.ERROR);
                 }
                 
             System.out.println("Finaliza XxGamMAnticiposUtil2.setOriginApproverInGeneral --> amXxGamMod.setAppproverByHierarchy");    
           //pageContext.writeDiagnostics(this,"Finaliza XxGamMAnticiposUtil2.setOriginApproverInGeneral --> amXxGamMod.setAppproverByHierarchy ",OAFwkConstants.PROCEDURE) ;
            
          }
        }
    }
            System.out.println("Finaliza XxGamMAnticiposUtil2.setOriginApproverInGeneral");    
   // pageContext.writeDiagnostics(this,"Finaliza XxGamMAnticiposUtil2.setOriginApproverInGeneral",OAFwkConstants.PROCEDURE) ;
    return retval; 
  }

  public static String getOrgNameByPersonId(String responsibility, 
                                            Number personID, 
                                            OAPageContext pageContext, 
                                            OADBTransaction oadbTransaction)
  {
     String retval = null;
    
    String errmsgOUT = null; 
    String errcodOUT = null;
    String orgName = null; 
               
    StringBuffer stmt= new StringBuffer(); 
    stmt.append("BEGIN "); 
    stmt.append("apps.xxgam_ap_mod_ant_utils2_pkg.get_orgName_By_PersonId("); 
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
     
  }

  
  public static Map getMapMaCostCenterByPersonId(String responsibility, 
                                                 Number personID, 
                                                 OAPageContext pageContext, 
                                                 OADBTransaction oadbTransaction,
                                                 String OrgName)
  {
    Map retval = new HashMap();
    String errmsgOUT = null; 
    String errcodOUT = null;
    String localSegment3 = null; 
    Number localCodComID = null; 
    String localCurCode = null; 
    String localCurName = null; 
    String localVclDesc = null; 
    String localVclType = null; 
    String localVclCode = null; 
    
    StringBuffer stmt= new StringBuffer(); 
    stmt.append("BEGIN "); 
    stmt.append("apps.xxgam_ap_mod_ant_utils2_pkg.get_genInfo_for_Branches("); 
    stmt.append(" pni_person_id          => :1"); 
    stmt.append(",psi_orgName            => :2"); 
    stmt.append(",psi_RespKey           => :3"); 
    stmt.append(",pso_segment3           => :4");
    stmt.append(",pno_codCombId          => :5");
    stmt.append(",pso_curCode            => :6");
    stmt.append(",pso_curName            => :7");
    stmt.append(",pso_vclDesc            => :8");
    stmt.append(",pso_vclType            => :9");
    stmt.append(",pso_vclCode            => :10");
    stmt.append(",pso_errmsg              => :11");
    stmt.append(",pso_errcod              => :12");
    stmt.append(");");
    stmt.append(" END; ");
    
    OracleCallableStatement cs = (OracleCallableStatement)oadbTransaction.createCallableStatement(stmt.toString(),DBTransaction.DEFAULT);
    
    try{
          cs.setInt(1,personID.intValue());
          cs.setString(2,OrgName);
          cs.setString(3,responsibility);
          cs.registerOutParameter(4,Types.VARCHAR);
          cs.registerOutParameter(5,Types.NUMERIC);
          cs.registerOutParameter(6,Types.VARCHAR);
          cs.registerOutParameter(7,Types.VARCHAR);
          cs.registerOutParameter(8,Types.VARCHAR);
          cs.registerOutParameter(9,Types.VARCHAR);
          cs.registerOutParameter(10,Types.VARCHAR);
          cs.registerOutParameter(11,Types.VARCHAR);
          cs.registerOutParameter(12,Types.VARCHAR);
       
          cs.execute();
          
               String strAttribute = null;
               BigDecimal bigAttribute = null;
                
        localSegment3 = cs.getString(4);
       
       strAttribute = cs.getString(5);
       if (strAttribute != null) {
         bigAttribute = new BigDecimal(strAttribute);
         if (bigAttribute != null) {
           localCodComID = new Number (bigAttribute);
         }
       }
       
        localCurCode = cs.getString(6);
        localCurName = cs.getString(7);
        localVclDesc = cs.getString(8);
        localVclType = cs.getString(9); 
        localVclCode =cs.getString(10);
        errmsgOUT = cs.getString(11); 
        errcodOUT = cs.getString(12); 
       
     } catch (Exception exception2)
    {
       System.out.println("EXCEPTION FROM JAVA AL -->"+exception2.getMessage());    
        pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"EXCEPTION FROM JAVA AL -->"+exception2.getMessage(),OAFwkConstants.PROCEDURE) ;
        retval = null; 
        throw OAException.wrapperException(exception2);
     }    
     
    if (errcodOUT.equals("0")){ 
         String getData = "Completed Transaction Susess"; 
             retval.put("segment3",localSegment3); 
             retval.put("codCombId",localCodComID);
             retval.put("curCode",localCurCode);
             retval.put("curName",localCurName);
             retval.put("vclDesc",localVclDesc);
             retval.put("vclType",localVclType);
             retval.put("vclCode",localVclCode);
             retval.put("isInitSuccess",true);
           }
       else{
           
           retval = null; 
           String getData = errmsgOUT;
           String sURL = XxGamConstantsUtil.URL_PAGE_OAF + "XxGamPaymentInitAdvancePG";
           com.sun.java.util.collections.HashMap  hParameters = new com.sun.java.util.collections.HashMap();
           hParameters.put("errcodOUT",errcodOUT);
           hParameters.put("errmsgOUT",errmsgOUT);
           
           pageContext.putTransactionValue("errcodOUT",errcodOUT);
           pageContext.putTransactionValue("errmsgOUT",errmsgOUT);
          
                           pageContext.setForwardURL(sURL
                                                    ,null //  not necessary with KEEP_MENU_CONTEXT
                                                    ,OAWebBeanConstants.KEEP_MENU_CONTEXT
                                                    ,null // No need to specify since we’re keeping menu context
                                                    ,hParameters
                                                    ,true  //retain AM 
                                                    ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO
                                                    ,OAWebBeanConstants.IGNORE_MESSAGES);
                                                  
            /**Comentado para un mejor manejo de errores**/                                        
           //throw new OAException(getData,OAException.ERROR);
         }
         
     
    
    return retval; 
  }

 ///Procedimiento por Usuario de Aplicaciones 
 /*1*/
  public static String getOrgNameByUserId(int UserID
                                        , OAPageContext pageContext
                                        ,  OADBTransaction oadbTransaction
                                        , OAWebBean webBean)
  {
    String retval = null;
    
    String errmsgOUT = null;
    String errcodOUT = null;
    String orgName = null;
              
    StringBuffer stmt= new StringBuffer();
    stmt.append("BEGIN ");
    stmt.append("apps.xxgam_ap_mod_ant_utils2_pkg.get_orgName_By_UserId(");
    stmt.append(" pni_user_id          => :1");
    stmt.append(",pso_orgName            => :2");
    stmt.append(",pso_errmsg              => :3");
    stmt.append(",pso_errcod              => :4");
    stmt.append(");");
    stmt.append(" END; ");
    
    OracleCallableStatement cs = (OracleCallableStatement)oadbTransaction.createCallableStatement(stmt.toString(),DBTransaction.DEFAULT);
    
    try{
         cs.setInt(1,UserID);
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
      retval = null;
      System.out.println("EXCEPTION FROM JAVA AL -->"+exception2.getMessage());    
       pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"EXCEPTION FROM JAVA AL -->"+exception2.getMessage(),OAFwkConstants.PROCEDURE) ;
       throw OAException.wrapperException(exception2);
    }    
    
    if (errcodOUT.equals("0")){
        String getData = "Completed Transaction Susess"; 
          retval = orgName; 
          }
      else{
          retval = null; 
          String getData = errmsgOUT;
          String sURL = null;
          com.sun.java.util.collections.HashMap  hParameters = new com.sun.java.util.collections.HashMap();

          System.out.println("Exception XxGamMAnticiposUtil2.getOrgNameByPersonId "); 
          pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class, "Exception XxGamMAnticiposUtil2.getOrgNameByPersonId", OAFwkConstants.PROCEDURE);
          
          String sURLPaymentInitAdvancePG = XxGamConstantsUtil.URL_PAGE_OAF + "XxGamPaymentInitAdvancePG";
          String sURLAdvanceConsultationPG = XxGamConstantsUtil.URL_PAGE_OAF + "XxGamAdvanceConsultationPG";
          
          if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                          webBean, 
                                                          new Number(pageContext.getResponsibilityId()), 
                                                          XxGamConstantsUtil.RESPONSABILITY_AUDITOR)) {
                                                        
                                                        retval = "02_AEROVIAS";
                                                        sURLAdvanceConsultationPG=sURLAdvanceConsultationPG+"&errcodOUT="+errcodOUT;
                                                        sURL = sURLAdvanceConsultationPG;
                                                      }else{
                                                        retval = null;
                                                        sURLPaymentInitAdvancePG=sURLPaymentInitAdvancePG+"&errcodOUT="+errcodOUT;
                                                        sURL = sURLPaymentInitAdvancePG;
                                                        throw new OAException(getData,OAException.ERROR); 
                                                      }
          /**             
          System.out.println(" pageContext.setForwardURL:" +sURL); 
          pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class, " pageContext.setForwardURL:" +sURL, OAFwkConstants.PROCEDURE);
          
                      pageContext.setForwardURL(sURL
                                               ,null //  not necessary with KEEP_MENU_CONTEXT
                                               ,OAWebBeanConstants.KEEP_MENU_CONTEXT
                                               ,null // No need to specify since we’re keeping menu context
                                               ,hParameters
                                               ,true  //retain AM 
                                               ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO
                                               ,OAWebBeanConstants.IGNORE_MESSAGES);
                                                                   
           Comentado para un mejor manejo de errores**/
          //throw new OAException(getData,OAException.ERROR);          
        }
        
    return retval;
  
  }

  public static boolean LaunchWfReservarFondos(Number requestId
                                               ,Number costCenter 
                                               ,OADBTransaction oadbTransaction
                                               ,OADBTransactionImpl oadbTransactionImpl
                                               ,String FullNameEmployee)
  {
    boolean retval = false; 
    
    String itemTypeSET ="XXGAMMAF";
    String WfProcessSET = "RESERVAR_FONDOS_PROC";
    
    WFContext ctxSET = null;
    ctxSET = (WFContext)oadbTransactionImpl.findObject("wfContext");
    String wfItemKeyGET =oadbTransaction.getSequenceValue("XXGAM_MA_NUMPAY_EMP_S").toString();
    
     
    if (ctxSET == null)
    {
      System.out.println("Transaction Context is Null!!");
      // throw new OAException ("Transaction Context is Null!!");
    }
    
     setUpWfContext(oadbTransactionImpl);
     ctxSET = (WFContext)oadbTransactionImpl.findObject("wfContext");
     if (ctxSET == null)
       {
       System.out.println("Transaction Context is Null!!");
       // throw new OAException ("Transaction Context is Null!!");
       }else{
       System.out.println("Transaction Context is not Null!!");
         
         WFEngineAPI.createProcess((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, WfProcessSET );
         // set item owner
         
         if (WFEngineAPI.setItemOwner((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "SYSADMIN"))
           System.out.println("Set Item Owner: "+"SYSADMIN");
         else
         {
           System.out.println("Cannot set owner.");
         }
         
         String strAttribute = null;
         BigDecimal bigAttribute = null;
         // set item Attribute Payment Id 
         bigAttribute = new BigDecimal(requestId.toString());
         WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext")
                                      ,itemTypeSET
                                      ,wfItemKeyGET
                                      ,"XXGAM_ITA_PAYMENT_ID"
                                      ,bigAttribute);
         
         // set item Attribute Cost Center ID 
         bigAttribute = new BigDecimal(costCenter.toString());
         WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext")
                                      ,itemTypeSET
                                      ,wfItemKeyGET
                                      ,"XXGAM_ITA_CC_ID"
                                      ,bigAttribute);
        
         WFEngineAPI.startProcess((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET);
         
       }
       
    return retval; 
  }

  private static void setUpWfContext(OADBTransactionImpl oadbTransactionImpl)
  {
    WFDB wfdb = new WFDB();
    AppsContext appsContextGET = oadbTransactionImpl.getAppsContext();
    if (appsContextGET == null){
      System.out.println("Transaction AppsContext is Null!!");
    }else{
      System.out.println("Transaction AppsContext is not Null!!");
    }
    String enc = appsContextGET.getCurrLangCode();
    System.out.println(String.valueOf("LANG [1]: ").concat(String.valueOf(enc)));
         if (enc == null)
         {
           enc = "US";
         }
    Connection connection = appsContextGET.getExtraJDBCConnection(wfdb, appsContextGET.getSessionId());
    wfdb.setConnection(connection);
    WFContext wfcontext = new WFContext(wfdb, "WE8ISO8859P1");
         oadbTransactionImpl.registerObject("wfContext", wfcontext);
         
  }

  /******** Puntos a Considerar 
   ******** Al cambiar un centro de costos Alterno, el empleado al pertenecer a una organizacion Distinta 
   ******** Se le debe Asignar una nueva tarjeta Virtual 
   ********
   ********
   ********
   ********
   ***************************************************************/
  public static boolean initTemplatePayment(OAPageContext pageContext, 
                                            OAWebBean webBean)
  {
    System.out.println("Inicia Utilitarios2 initTemplatePayment"); 
    
    
    OAMessageLovInputBean templatePaymentLovBean = (OAMessageLovInputBean)webBean.findChildRecursive("TemplatePaymentLov");
    OAMessageLovInputBean templatePaymentFlexLovBean = (OAMessageLovInputBean)webBean.findChildRecursive("TemplatePaymentFlexLov");
    
    boolean retval = false; 
    boolean isSuccess = false; 
    XxGamModAntAMImpl mModAntAMImpl = null; 
    mModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean); 
    // Simplficar public boolean initTemplatePayment()
    XxGamModAntLovAMImpl amLov = null;
    amLov = (XxGamModAntLovAMImpl)mModAntAMImpl.getXxGamModAntLovAM1();
    
    Number costCenterId = null;
    String costCenterFlex = null;
    XxGamMaGeneralReqVOImpl generalImpl = null;
    XxGamMaGeneralReqVORowImpl generalRow = null;
    generalImpl = mModAntAMImpl.getXxGamMaGeneralReqVO1();
    
    if (generalImpl != null) {
          
          generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
      if (generalRow != null){
        if (generalRow.getCostCenterFlex() != null) {
          costCenterFlex = generalRow.getCostCenterFlex();
        } else if (generalRow.getCostCenter() != null) {
          costCenterId = generalRow.getCostCenter();
          String virtualCard = null;
          if (costCenterId != null) {
          //TODO 04: si
            System.out.println("Ya no es necesario buscar el valor de la tarjeta virtual ya que este valor se establece" +
            "\n al principio Hay que ver el comportamiento para cuando se elige un centro de costos Alterno"); 
          } //END if (costCenterId != null) {   
        }else {
          generalRow.setTemplatePayment(null);
          generalRow.setTypeTemplateDesc(null);
          isSuccess = true;
        } //END if (generalRow.getCostCenterFlex() != null) {      
      } //END if (generalRow != null){
    } // END if (generalImpl != null) {
    
     if (costCenterFlex != null) {
       templatePaymentLovBean.setValue(pageContext,null);
       templatePaymentFlexLovBean.setValue(pageContext,null);
       generalRow.setTemplatePayment(null);
       generalRow.setTypeTemplateDesc(null);
       isSuccess = XxGamMAnticiposUtil2.initTemplatePaymentFlexLov(pageContext,webBean,costCenterFlex);
     }
     
     if (costCenterId != null) {
       templatePaymentLovBean.setValue(pageContext,null);
       templatePaymentFlexLovBean.setValue(pageContext,null);
       generalRow.setTemplatePayment(null);
       generalRow.setTypeTemplateDesc(null);
      isSuccess = XxGamMAnticiposUtil2.initTemplatePaymentLov(pageContext,webBean,costCenterId);          
     }
     
     retval = isSuccess;
    System.out.println("Finaliza Utilitarios2 initTemplatePayment"); 
    return retval; 
  }

  private static boolean initTemplatePaymentFlexLov(OAPageContext pageContext, 
                                                    OAWebBean webBean, 
                                                    String costCenterFlex)
  {
    System.out.println("Inicia Utilitarios2 initTemplatePaymentFlexLov"); 
    System.out.println("Parametro  costCenterFlex-->"+costCenterFlex); 
    
    String paramorgNameEmp = null; 
    String orgShortNameEmp = null; 
    paramorgNameEmp = (String)pageContext.getTransactionValue("orgNameEmp");
    if (paramorgNameEmp!=null&&!"".equals(paramorgNameEmp)){
      orgShortNameEmp = paramorgNameEmp.substring(0,2); 
      System.out.println("paramorgNameEmp-->"+paramorgNameEmp);  
    }
    
    boolean retval = false; 
    boolean isSuccess = false; 
    XxGamModAntAMImpl mModAntAMImpl = null; 
    mModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean); 
    // Simplficar public boolean initTemplatePayment() para el modo Flex
    XxGamModAntLovAMImpl amLov = null;
    amLov = (XxGamModAntLovAMImpl)mModAntAMImpl.getXxGamModAntLovAM1();
    XxGamMaTemplatePaymentFlexLovVOImpl templateFlexImpl = amLov.getXxGamMaTemplatePaymentFlexLovVO1();
    
      
      if (orgShortNameEmp!=null&&!"".equals(orgShortNameEmp)){
        if (templateFlexImpl != null) {
          //Se manda Llamar un nuevo procedimiento sobrecargado con la variable de Organizacion Extra 
          templateFlexImpl.searchTemplatePaymentByFlex(null,costCenterFlex,orgShortNameEmp);
          isSuccess = true; /***Muy raro ***/
          if (templateFlexImpl.hasNext()) {
            isSuccess = true;
          }
        }
      }else{
        isSuccess = false; 
      }
    
    retval = isSuccess; 
    return retval; 
  }

  private static boolean initTemplatePaymentLov(OAPageContext pageContext, 
                                                OAWebBean webBean, 
                                                Number costCenterId)
  {
    System.out.println("Inicia Utilitarios2 initTemplatePaymentLov"); 
    pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"Inicia Utilitarios2 initTemplatePaymentLov",OAFwkConstants.STATEMENT);
    
    String paramorgNameEmp = null; 
    String orgShortNameEmp = null; 
    paramorgNameEmp = (String)pageContext.getTransactionValue("orgNameEmp");
    if (paramorgNameEmp!=null&&!"".equals(paramorgNameEmp)){
      orgShortNameEmp = paramorgNameEmp.substring(0,2); 
      System.out.println("paramorgNameEmp-->"+paramorgNameEmp);  
      pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class," Utilitarios2 initTemplatePaymentLov paramorgNameEmp-->"+paramorgNameEmp,OAFwkConstants.STATEMENT);
    }
   
    
    boolean retval = false; 
    boolean isSuccess = false; 
    XxGamModAntAMImpl mModAntAMImpl = null; 
    mModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean); 
    // Simplficar public boolean initTemplatePayment() para el modo Normal
    XxGamModAntLovAMImpl amLov = null;
    amLov = (XxGamModAntLovAMImpl)mModAntAMImpl.getXxGamModAntLovAM1();
    XxGamMaTemplatePaymentLovVOImpl templateImpl = amLov.getXxGamMaTemplatePaymentLovVO1();
    
  
      
      if (orgShortNameEmp!=null&&!"".equals(orgShortNameEmp)){
        if (templateImpl != null) {
          pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"Se manda Llamar un nuevo procedimiento sobrecargado con la variable de Organizacion Extra " +
                                                                  "\n templateImpl.searchTemplatePaymentById(null, costCenterId,orgShortNameEmp) " +
                                                                  "Tambien se agrego una variable de enlace al query del View Object",OAFwkConstants.STATEMENT);
          templateImpl.searchTemplatePaymentById(null, costCenterId,orgShortNameEmp);
          isSuccess = true;  /***Muy Raro**/
          if (templateImpl.hasNext()) {
            isSuccess = true;
          }
        }
      }else{
        isSuccess = false; 
      }
  
    
    System.out.println("Finaliza Utilitarios2 initTemplatePaymentLov"); 
    retval = isSuccess; 
    return retval; 
    
  }

  public static boolean setPaymentReqDescriptionsReadOnly(OAPageContext pageContext, 
                                                          OAWebBean webBean)
  {
    System.out.println("Comienza XxGamMAnticiposUtil2.setPaymentReqDescriptionsReadOnly "); 
    /*********** Añadido para Nuevos tipos de franquicias *****/
     String strFranchiseType = null; 
     String strRequestType = null; 
     String nvlFranchiseType = null; 
     String nvlRequestType = null; 
                 
            
     if(null!=pageContext.getParameter("pfranchiseType")&&!"".equals(pageContext.getParameter("pfranchiseType"))){
     strFranchiseType = pageContext.getParameter("pfranchiseType"); 
     }
                  
     if(null!=pageContext.getParameter("pRequest")&&!"".equals(pageContext.getParameter("pRequest"))){
     strRequestType = pageContext.getParameter("pRequest"); 
     } 
                
     nvlFranchiseType = (null==strFranchiseType)?(String)pageContext.getSessionValue("sfranchiseType"):strFranchiseType; 
     nvlRequestType = (null==strRequestType)?(String)pageContext.getSessionValue("sRequest"):strRequestType; 
     /****************************************************************/
    
     boolean retval = false; 
    String errmsgOUT = null; 
    String errcodOUT = null;
    String l_IsApproverBySys  = null; 
    String l_IsRespFranchise =  null; 
    String l_FullNameEmployee = null; 
    String l_FullNameApprover = null; 
    String l_FullNameApproverAlt = null; 
    String l_PurposeDesc = null; 
    String l_CostCenterDesc = null; 
    String l_CurrencyDesc = null; 
    
    XxGamModAntAMImpl ModAntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean); 
    XxGamMaGeneralReqVOImpl generalImpl = ModAntAMImpl.getXxGamMaGeneralReqVO1();
    XxGamMaGeneralReqVORowImpl generalRow = null;
    generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();


     StringBuffer stmt= new StringBuffer(); 
     stmt.append("BEGIN "); 
     stmt.append("apps.XXGAM_AP_MOD_ANT_UTILS2_PKG.XXGAM_OIE_GET_VIEW_PARENT_INFO("); 
     stmt.append(" po_errmsg              => :1"); 
     stmt.append(",po_errcod              => :2"); 
     stmt.append(",pi_request_id          => :3"); 
     stmt.append(",po_IsApproverBySys     => :4"); 
     stmt.append(",po_IsRespFranchise     => :5");
     stmt.append(",po_FullNameEmployee    => :6");
     stmt.append(",po_FullNameApprover    => :7");
     stmt.append(",po_FullNameApproverAlt => :8");
     stmt.append(",po_PurposeDesc         => :9");
     stmt.append(",po_CostCenterDesc      => :10");
     stmt.append(",po_CurrencyDesc        => :11");
     stmt.append(");");
     stmt.append(" END; ");
    
    String  strRequestId = null;  
    if (XxGamMAnticiposUtil.validatesResponsability(pageContext   
                                                    ,webBean 
                                                    ,new Number(pageContext.getResponsibilityId())
                                                    ,XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET)
      ||XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET.equals(nvlRequestType)){
     strRequestId = pageContext.getParameter(XxGamConstantsUtil.GENERAL_ID);                                                               
   }else {
     strRequestId = pageContext.getParameter(XxGamConstantsUtil.REQUEST_ID);
   }   
   
   System.out.println("Capa Utilitarios2 setPaymentReqDescriptionsReadOnly strRequestId: "+strRequestId); 
  System.out.println("Capa Utilitarios2 setPaymentReqDescriptionsReadOnly STATUS: "+ (String)pageContext.getTransactionValue(XxGamConstantsUtil.STATUS)); 
  
  if (null==strRequestId){
    if ("update".equals((String)pageContext.getTransactionValue(XxGamConstantsUtil.STATUS))){
      strRequestId = (String)pageContext.getTransactionValue("StrRequestIdUpdate");
    }
  }  //NOTA: Observar el comportamiento para las responsabilidades de Anticipos y Franquicias el if (null==strRequestId)se agrego porque en la responsabilidad de franquisias 
     //no se estaban llenando los campos de la pantalla "Informacion General al momento de Actualizar" 02/06/2015
  
                                    
    Number numRequestId = null;
    if(strRequestId!=null&&!(strRequestId.equals(""))){
      try
      {
        numRequestId = new Number(strRequestId);
      } catch (SQLException e) {
        numRequestId = null;
        System.out.println("Error AL -->"+e.getMessage()); 
      }
      
    OADBTransaction oadbtrans = (OADBTransaction)ModAntAMImpl.getTransaction();
    OracleCallableStatement cs = (OracleCallableStatement)oadbtrans.createCallableStatement(stmt.toString(),DBTransaction.DEFAULT);
    
    try{
        
         cs.registerOutParameter(1,Types.VARCHAR);
         cs.registerOutParameter(2,Types.VARCHAR);
         cs.setInt(3,numRequestId.intValue());
         cs.registerOutParameter(4,Types.VARCHAR);
         cs.registerOutParameter(5,Types.VARCHAR);
         cs.registerOutParameter(6,Types.VARCHAR);
         cs.registerOutParameter(7,Types.VARCHAR);
         cs.registerOutParameter(8,Types.VARCHAR);
         cs.registerOutParameter(9,Types.VARCHAR);
         cs.registerOutParameter(10,Types.VARCHAR);
         cs.registerOutParameter(11,Types.VARCHAR);
      
         cs.execute();
         
        
       errmsgOUT = cs.getString(1); 
       errcodOUT = cs.getString(2); 
      
       l_IsApproverBySys = cs.getString(4); 
       l_IsRespFranchise = cs.getString(5);
       l_FullNameEmployee = cs.getString(6);
       l_FullNameApprover = cs.getString(7);
       l_FullNameApproverAlt = cs.getString(8);
       l_PurposeDesc = cs.getString(9);
       l_CostCenterDesc = cs.getString(10);
       l_CurrencyDesc = cs.getString(11);
       
       
    } catch (Exception exception2)
    {
    throw OAException.wrapperException(exception2);
     } 
     
     
    if (errcodOUT.equals("0")){
     String getData = "Completed Transaction Susess"; 
    
     if (l_IsApproverBySys.equals("true")){
         generalRow.setIsApproverBySys(Boolean.valueOf(true));
       }
     else{
       generalRow.setIsApproverBySys(Boolean.valueOf(false));
     }
     
       if (l_IsRespFranchise.equals("true")){
           generalRow.setIsRespFranchise(Boolean.valueOf(true));
         }
       else{
         generalRow.setIsRespFranchise(Boolean.valueOf(false));
       }
       
    generalRow.setFullNameEmployee(l_FullNameEmployee);
    generalRow.setFullNameApprover(l_FullNameApprover);
    generalRow.setFullNameApproverAlt(l_FullNameApproverAlt);
    generalRow.setPurposeDesc(l_PurposeDesc);
    generalRow.setCostCenterDesc(l_CostCenterDesc); 
    generalRow.setCurrencyDesc(l_CurrencyDesc);
    generalRow.setTypeTemplateDesc(generalRow.getReportType()); 
    retval = true; 
       
       }else{
       
        System.out.println("Exception XxGamMAnticiposUtil2.setPaymentReqDescriptionsReadOnly "); 
        
       String getData = errmsgOUT;
       String sURL = XxGamConstantsUtil.URL_PAGE_OAF + "XxGamPaymentInitAdvancePG";
       com.sun.java.util.collections.HashMap  hParameters = new com.sun.java.util.collections.HashMap();
       
       /** Comentados ya que regresan nulos a la hr de tratar los errores
       hParameters.put("errcodOUT",errcodOUT);
       hParameters.put("errmsgOUT",errmsgOUT);
       **/
       
       sURL=sURL+"&errcodOUT="+errcodOUT;
       sURL=sURL+"&errmsgOUT="+errmsgOUT;
       
       pageContext.putTransactionValue("errcodOUT",errcodOUT);
       pageContext.putTransactionValue("errmsgOUT",errmsgOUT);
       
                       pageContext.setForwardURL(sURL
                                                ,null //  not necessary with KEEP_MENU_CONTEXT
                                                ,OAWebBeanConstants.KEEP_MENU_CONTEXT
                                                ,null // No need to specify since we’re keeping menu context
                                                ,hParameters
                                                ,true  //retain AM 
                                                ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO
                                                ,OAWebBeanConstants.IGNORE_MESSAGES);
                                                
       /**Comentado para un mejor manejo de errores**/                                        
       //throw new OAException(getData,OAException.ERROR); 
      } 
    } //END if(strRequestId!=null&&!(strRequestId.equals(""))){
    else{
    retval =true; 
      System.out.println("Casos en los que se  viene de regreso de la pagina XxGamPaymentReqTicketPlanePG ");
    }
    
    System.out.println("Finaliza XxGamMAnticiposUtil2.setPaymentReqDescriptionsReadOnly "); 
    
     return retval; 
  }


  /********Este procedimiento sustituye al procedimiento xxgam_ap_anticipos_pkg.boletos_wf
   ********
   ********
   ********
   ********
   ***************************/
  public static boolean callWorkFlowToSendConfirmationOfficeTicket(OAPageContext pageContext, 
                                                                   OAWebBean webBean, 
                                                                   String[] dataForWorkFlow)
  {
  
    XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"franchiseType WFUtils2: "+pageContext.getParameter("pfranchiseType"),pageContext,webBean);
    XxGamMAnticiposUtil.debugMessage(XxGamConstantsUtil.DEBUG_OUTLINE_MODE,"Request WFUtils2: "+pageContext.getParameter("pRequest"),pageContext,webBean);
    
    boolean retval = false; 
    String itemTypeSET = null; 
    String WfProcessSET = null;
    String wfItemKeyGET = null;
    
    XxGamModAntAMImpl AntAMImpl = null;
    OADBTransaction oadbTransaction = null; 
    OADBTransactionImpl oadbTransactionImpl = null; 
    WFContext ctxSET = null;
    
    String routes = null; 
    String office = null; 
    String employeeIdToStr = null; 
    String numTicket = null; 
    String approverIdToStr = null; 
    String requestIdToStr = null;
    BigDecimal bigAttribute = null;
    
    AntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean); 
    oadbTransaction = AntAMImpl.getOADBTransaction(); 
    oadbTransactionImpl = (OADBTransactionImpl)AntAMImpl.getTransaction(); 
   
    
    routes = dataForWorkFlow[0]; 
    employeeIdToStr = dataForWorkFlow[1];
    office = dataForWorkFlow[2]; 
    numTicket = dataForWorkFlow[3];
    approverIdToStr = dataForWorkFlow[4]; 
    requestIdToStr =  dataForWorkFlow[5]; 
    
    
   
    ctxSET = (WFContext)oadbTransactionImpl.findObject("wfContext");
    
    if (ctxSET == null) {
          System.out.println("Transaction Context is Null!! setUpWfContext(oadbTransactionImpl); ");
          setUpWfContext(oadbTransactionImpl);
           ctxSET = (WFContext)oadbTransactionImpl.findObject("wfContext");
        }
     
    if (ctxSET == null) {
          System.out.println("Transaction Context is Null!!");
        }else{
        
      if (XxGamMAnticiposUtil.validatesResponsability(pageContext   
                                                      ,webBean 
                                                      ,new Number(pageContext.getResponsibilityId())
                                                      ,XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET)
        ||XxGamConstantsUtil.RESPONSABILITY_OFFICE_TICKET.equals(pageContext.getParameter("pRequest"))){    
        
        itemTypeSET = "BOLETOSF";
        WfProcessSET = "XXGAM_PRC_OFFICE_EMI_2015";                                                      
        wfItemKeyGET = oadbTransaction.getSequenceValue("XXGAM_MA_WF_S").toString();
        wfItemKeyGET = itemTypeSET+"_"+wfItemKeyGET;
        
        WFEngineAPI.createProcess((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, WfProcessSET);  
        
        if (WFEngineAPI.setItemOwner((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, pageContext.getUserName())) {
                System.out.println("Set Item Owner: "+pageContext.getUserName());
        }
        
        if (WFEngineAPI.setItemUserKey((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, pageContext.getUserName())){
          System.out.println("Set Item User Key:"+pageContext.getUserName());
        }    
        
        bigAttribute = new BigDecimal(requestIdToStr);
        WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "ANTICIPO_ID", bigAttribute);
        
        bigAttribute = new BigDecimal(employeeIdToStr);
        WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "EMPLEADO_ID", bigAttribute);
        
        bigAttribute = new BigDecimal(approverIdToStr);
        WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "APROBADOR_ID", bigAttribute);
        
        WFEngineAPI.setItemAttrText((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "RUTA", routes);
        
        /**  17Jul2015 se comenta porque se agrego un nuevo proceso al wf BOLETOSF dejando obsoleto WF BOLETOS
        WFEngineAPI.setItemAttrText((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "NUM_BOLETO", numTicket);
        **/ 
        WFEngineAPI.setItemAttrText((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "OFICINA", office);
        
        WFEngineAPI.setItemAttrText((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "#FROM_ROLE", pageContext.getUserName());
        
        WFEngineAPI.startProcess((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET);
        
        retval = true;
        
      }else if (XxGamMAnticiposUtil.validatesResponsability(pageContext, 
                                                               webBean, 
                                                               new Number(pageContext.getResponsibilityId()), 
                                                               XxGamConstantsUtil.RESPONSABILITY_FRANCHISE)
                ||XxGamConstantsUtil.RESPONSABILITY_FRANCHISE.equals(pageContext.getParameter("pRequest"))) {
        /** Begin Find Approver Alt **/
        
        Number approverAltId = null; 
        String approverAltIdToStr = null; 
        approverAltId = AntAMImpl.findApproverAltId();
        if(null!=approverAltId){
          approverAltIdToStr = approverAltId.toString();
        }
        System.out.println("Antes operador Ternario ApproverId:"+approverIdToStr+"\n ApproverAltId:"+approverAltIdToStr); 
        approverIdToStr = (null!=approverAltIdToStr)? approverAltIdToStr:approverIdToStr;
        // NVL (l_approver_alt_id, p_aprobador_id));
        System.out.println("Depues Operador Ternario ApproverId:"+approverIdToStr+"\n ApproverAltId:"+approverAltIdToStr); 
        
        /** End Find Approver Alt **/
        
        itemTypeSET = "BOLETOSF";
        WfProcessSET = "XXGAM_PRC_FRANCHISES_2015";
        /**wfItemKeyGET = oadbTransaction.getSequenceValue("XXGAM_MA_WFFRAN_S").toString(); Cambiar los consecutivos ya que mas adelante pueden causar Conflictos **/
        wfItemKeyGET = oadbTransaction.getSequenceValue("XXGAM_MA_WF_S").toString();
        wfItemKeyGET = itemTypeSET+"_"+wfItemKeyGET;
        
        WFEngineAPI.createProcess((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, WfProcessSET);  
        
        if (WFEngineAPI.setItemOwner((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, pageContext.getUserName())) {
                System.out.println("Set Item Owner: "+pageContext.getUserName());
        }
        
        if (WFEngineAPI.setItemUserKey((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, pageContext.getUserName())){
          System.out.println("Set Item User Key:"+pageContext.getUserName());
        }
        
        bigAttribute = new BigDecimal(employeeIdToStr);
        WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "EMPLEADO_ID", bigAttribute);
        
        bigAttribute = new BigDecimal(approverIdToStr);
        WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "APROBADOR_ID", bigAttribute);
        
        bigAttribute = new BigDecimal(requestIdToStr);
        WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "ANTICIPO_ID", bigAttribute);
        
        WFEngineAPI.setItemAttrText((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "OFICINA", office);
        
        WFEngineAPI.setItemAttrText((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "#FROM_ROLE", pageContext.getUserName());
        
        WFEngineAPI.setItemAttrText((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "RUTA", routes);
        
        WFEngineAPI.startProcess((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET);
        
        retval = true;
        
      }

   }
        
        
    return retval; 
  }

  static int getReserveFunds(OAPageContext pageContext, OAWebBean webBean)
  {
     int retval = 0; 
     int intStatus = -1;
     //Declara los recursos
     XxGamMaGeneralReqVOImpl voGeneralReq = null;
     XxGamMaGeneralReqVORowImpl rowGeneralReq = null;
     Number requestId = null;
     Number costCenter = null;
     String fullNameEmployee = null; 
     Number templateId = null; 
     Number paymentType = null; 
     Number employeeId = null; 
     
    String itemTypeSET = null; 
    String WfProcessSET = null;
    String wfItemKeyGET = null;
    
    XxGamModAntAMImpl AntAMImpl = null;
    OADBTransaction oadbTransaction = null; 
    OADBTransactionImpl oadbTransactionImpl = null; 
    WFContext ctxSET = null;
    
    
    AntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean); 
    oadbTransaction = AntAMImpl.getOADBTransaction(); 
    oadbTransactionImpl = (OADBTransactionImpl)AntAMImpl.getTransaction(); 
   
    voGeneralReq = AntAMImpl.getXxGamMaGeneralReqVO1();
    
    if (voGeneralReq != null) {
        rowGeneralReq = (XxGamMaGeneralReqVORowImpl)voGeneralReq.getCurrentRow();

        if (rowGeneralReq != null) {
            requestId = rowGeneralReq.getId();
            costCenter = rowGeneralReq.getCostCenter();
            fullNameEmployee = rowGeneralReq.getFullNameEmployee(); 
            templateId = rowGeneralReq.getTemplatePayment(); 
          employeeId = rowGeneralReq.getEmployeeId();
        }
    }
    
    ctxSET = (WFContext)oadbTransactionImpl.findObject("wfContext");
    
    if (ctxSET == null) {
          System.out.println("Transaction Context is Null!! setUpWfContext(oadbTransactionImpl); ");
          setUpWfContext(oadbTransactionImpl);
           ctxSET = (WFContext)oadbTransactionImpl.findObject("wfContext");
        }
     
    if (ctxSET == null) {
          System.out.println("Transaction Context is Null!!");
        }else{
        
      if (XxGamMAnticiposUtil.validatesResponsability(pageContext   
                                                      ,webBean 
                                                      ,new Number(pageContext.getResponsibilityId())
                                                      ,XxGamConstantsUtil.RESPONSABILITY_EMPLOYEE)){
                                                      
                                                        itemTypeSET = "XXGAMMAF";
                                                        WfProcessSET = "RESERVAR_FONDOS_PROC";                                                      
                                                        wfItemKeyGET = oadbTransaction.getSequenceValue("XXGAM_MA_NUMPAY_EMP_S").toString();
                                                        wfItemKeyGET = itemTypeSET+"_"+wfItemKeyGET;  
                                                        
                                                        WFEngineAPI.createProcess((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, WfProcessSET);  
                                                        
                                                        if (WFEngineAPI.setItemOwner((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, pageContext.getUserName())) {
                                                                System.out.println("Set Item Owner: "+pageContext.getUserName());
                                                        }
                                                        
                                                        if (WFEngineAPI.setItemUserKey((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, pageContext.getUserName())){
                                                          System.out.println("Set Item User Key:"+pageContext.getUserName());
                                                        }
                                                        
                                                        WFEngineAPI.setItemAttrText((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "#FROM_ROLE", pageContext.getUserName());
                                                     
                                                        String strAttribute = null;
                                                        BigDecimal bigAttribute = null;
                                                        // set item Attribute Payment Id 
                                                        bigAttribute = new BigDecimal(requestId.toString());
                                                        WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext")
                                                                                     ,itemTypeSET
                                                                                     ,wfItemKeyGET
                                                                                     ,"XXGAM_ITA_PAYMENT_ID"
                                                                                     ,bigAttribute);
                                                        
                                                        // set item Attribute Cost Center ID 
                                                        bigAttribute = new BigDecimal(costCenter.toString());
                                                        WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext")
                                                                                     ,itemTypeSET
                                                                                     ,wfItemKeyGET
                                                                                     ,"XXGAM_ITA_CC_ID"
                                                                                     ,bigAttribute);
                                                         
                                                        bigAttribute = new BigDecimal(employeeId.toString()); 
                                                        WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext")
                                                                                     ,itemTypeSET
                                                                                     ,wfItemKeyGET
                                                                                     ,"XXGAM_ITA_PER_PERSON_ID"
                                                                                     ,bigAttribute);                            
                                                                                     
                                                        WFEngineAPI.startProcess((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET);
                                                        
                                                        retval = 0; 
                                                      } // END  if (XxGamMAnticiposUtil.validatesResponsability(pageContext  
           
           } //END if (ctxSET == null) {       
           
    
     return retval; 
  }
  
  public static boolean setOriginApproverInGeneralUpdate(OAPageContext pageContext, OAWebBean webBean)
    {
      pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"Comienza XxGamMAnticiposUtil2.setOriginApproverInGeneralUpdate",OAFwkConstants.PROCEDURE);
      System.out.println("Comienza XxGamMAnticiposUtil2.setOriginApproverInGeneralUpdate");
       
      boolean retval = false;

          int lUserdID = 0;
          lUserdID = pageContext.getUserId();
          Number empPositionID = null;
          Number mgrPositionID = null;
          Number mgrPersonID = null;
          String empFullName = null;
          String mgrFullName = null;

          String errmsgOUT = null;
          String errcodOUT = null;

          if ((pageContext != null) && (webBean != null)) {
            XxGamModAntAMImpl amXxGamMod = null;
            amXxGamMod = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
            if (amXxGamMod != null) {
              XxGamModAntLovAMImpl amLov = null;
              amLov = (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();

              if (amLov != null)
              {
                pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"Comienza XxGamMAnticiposUtil2.setOriginApproverInGeneralUpdate --> amXxGamMod.setAppproverByHierarchy ",OAFwkConstants.PROCEDURE);
                System.out.println("Comienza XxGamMAnticiposUtil2.setOriginApproverInGeneralUpdate --> amXxGamMod.setAppproverByHierarchy ");
                boolean isSuccess = false;

                XxGamMaGeneralReqVOImpl generalImpl = amXxGamMod.getXxGamMaGeneralReqVO1();
                XxGamMaGeneralReqVORowImpl generalRow = null;
                generalRow = (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();

                StringBuffer stmt = new StringBuffer();
                stmt.append("BEGIN ");
                stmt.append("apps.xxgam_ap_mod_ant_utils2_pkg.get_Appprover_By_Hierarchy(");
                stmt.append(" pi_user_id          => :1");
                stmt.append(",po_emp_position_id  => :2");
                stmt.append(",po_mgr_position_id  => :3");
                stmt.append(",po_emp_full_name    => :4");
                stmt.append(",po_mgr_full_name    => :5");
                stmt.append(",po_mgr_person_id    => :6");
                stmt.append(",po_errmsg           => :7");
                stmt.append(",po_errcod           => :8");
                stmt.append(");");
                stmt.append(" END; ");

                OADBTransaction oadbtrans = (OADBTransaction)amXxGamMod.getTransaction();
                OracleCallableStatement cs = (OracleCallableStatement)oadbtrans.createCallableStatement(stmt.toString(), -1);
                try
                {
                  cs.setInt(1, lUserdID);
                  cs.registerOutParameter(2, 2);
                  cs.registerOutParameter(3, 2);
                  cs.registerOutParameter(4, 12);
                  cs.registerOutParameter(5, 12);
                  cs.registerOutParameter(6, 2);
                  cs.registerOutParameter(7, 12);
                  cs.registerOutParameter(8, 12);
                  cs.execute();

                  String strAttribute = null;
                  BigDecimal bigAttribute = null;

                  strAttribute = cs.getString(2);
                  if (strAttribute != null) {
                    bigAttribute = new BigDecimal(strAttribute);
                    if (bigAttribute != null) {
                      empPositionID = new Number(bigAttribute);
                    }
                  }

                  strAttribute = cs.getString(3);
                  if (strAttribute != null) {
                    bigAttribute = new BigDecimal(strAttribute);
                    if (bigAttribute != null) {
                      mgrPositionID = new Number(bigAttribute);
                    }
                  }

                  empFullName = cs.getString(4);
                  mgrFullName = cs.getString(5);

                  strAttribute = cs.getString(6);
                  if (strAttribute != null) {
                    bigAttribute = new BigDecimal(strAttribute);
                    if (bigAttribute != null) {
                      mgrPersonID = new Number(bigAttribute);
                    }
                  }

                  errmsgOUT = cs.getString(7);
                  errcodOUT = cs.getString(8);
                }
                catch (Exception exception2)
                {
                  throw OAException.wrapperException(exception2);
                }
               
                pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"errmsgOUT:" + errmsgOUT + "\n" + "errcodOUT:" + errcodOUT,OAFwkConstants.PROCEDURE); 
                System.out.println("errmsgOUT:" + errmsgOUT + "\n" + "errcodOUT:" + errcodOUT);

                if ("0"!=errcodOUT) {
                  String getData = "Completed Transaction Susess";

                  System.out.println("mgrPositionID:\n" + mgrPositionID);
                  System.out.println("mgrFullName:\n" + mgrFullName);
                  System.out.println("mgrPersonID:\n" + mgrPersonID);
                  generalRow.setApproverId(mgrPersonID);
                  generalRow.setFullNameApprover(mgrFullName);
                  generalRow.setIsApproverBySys(Boolean.valueOf(true));
                  retval = true;

                  if ((retval) && (empPositionID != null)) {
                    pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class, "Comienza  LovAMImpl.initApproverAltLov", OAFwkConstants.PROCEDURE);
                    System.out.println("Comienza  LovAMImpl.initApproverAltLov");
                    Map mapResult = new java.util.HashMap();
                    Number jobNameId = null;
                    Number versionId = null;
                    boolean isSuccessTmp = false;
                    XxGamModAntLovAMImpl LovAMImpl = null;
                    LovAMImpl = (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();
                    if (LovAMImpl != null) {
                      mapResult = amXxGamMod.callProceduceGetPositionEmployee(empPositionID);
                      System.out.println("mapResult!=null" + mapResult != null);
                      if (mapResult != null) {
                        jobNameId = (Number)mapResult.get("jobNameId");
                        versionId = (Number)mapResult.get("versionId");
                        System.out.println("jobNameId" + jobNameId);
                        System.out.println("versionId" + versionId);
                        
                        pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"jobNameId" + jobNameId,OAFwkConstants.PROCEDURE); 
                        pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"versionId" + versionId,OAFwkConstants.PROCEDURE); 
                        
                        isSuccessTmp = LovAMImpl.initApproverAltLov(jobNameId, versionId);
                        System.out.println("isSuccessTmp LovAMImpl.initApproverAltLov(jobNameId, versionId); " + isSuccessTmp);
                        pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class, "Resulatado  LovAMImpl.initApproverAltLov: " + versionId, OAFwkConstants.PROCEDURE);
                      }
                    }
                    System.out.println("Finaliza  LovAMImpl.initApproverAltLov");
                    pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class, "Finaliza  LovAMImpl.initApproverAltLov ", 2);
                  }
                }
                else
                { 
                  System.out.println("Exception XxGamMAnticiposUtil2.setOriginApproverInGeneralUpdate "); 
                  pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class, "Exception XxGamMAnticiposUtil2.setOriginApproverInGeneralUpdate", OAFwkConstants.PROCEDURE);
                  
                  String getData = errmsgOUT;
                  String sURL = XxGamConstantsUtil.URL_PAGE_OAF + "XxGamPaymentInitAdvancePG";
                  com.sun.java.util.collections.HashMap  hParameters = new com.sun.java.util.collections.HashMap();
                  
                  /** Comentados ya que regresan nulos a la hr de tratar los errores
                  hParameters.put("errcodOUT",errcodOUT);
                  hParameters.put("errmsgOUT",errmsgOUT);
                  **/
                  
                  sURL=sURL+"&errcodOUT="+errcodOUT;
                  sURL=sURL+"&errmsgOUT="+errmsgOUT;
                  
                  pageContext.putTransactionValue("errcodOUT",errcodOUT);
                  pageContext.putTransactionValue("errmsgOUT",errmsgOUT);
                  
                                 pageContext.setForwardURL(sURL
                                                          ,null //  not necessary with KEEP_MENU_CONTEXT
                                                          ,OAWebBeanConstants.KEEP_MENU_CONTEXT
                                                          ,null // No need to specify since we’re keeping menu context
                                                          ,hParameters
                                                          ,true  //retain AM 
                                                          ,OAWebBeanConstants.ADD_BREAD_CRUMB_NO
                                                          ,OAWebBeanConstants.IGNORE_MESSAGES);
                                                          
                  /**Comentado para un mejor manejo de errores**/
                  //throw new OAException(getData,OAException.ERROR);
                }

                System.out.println("Finaliza XxGamMAnticiposUtil2.setOriginApproverInGeneral --> amXxGamMod.setAppproverByHierarchy");
              }
            }

          }  
        
      pageContext.writeDiagnostics(XxGamMAnticiposUtil2.class,"Finaliza XxGamMAnticiposUtil2.setOriginApproverInGeneralUpdate",OAFwkConstants.PROCEDURE);
      System.out.println("Finaliza XxGamMAnticiposUtil2.setOriginApproverInGeneralUpdate");
      
     return retval;  
    }

  public static boolean initCostCenterFlexUpdate(OAPageContext pageContext, 
                                                 OAWebBean webBean)
  {
    boolean isSuccess = false;
        if ((pageContext != null) && (webBean != null)) {
          XxGamModAntAMImpl am = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);

          if (am != null) {
            Number responsabilityId = null;
            responsabilityId = new Number(pageContext.getResponsibilityId());
            isSuccess = am.initCostCenterFlexUpdate(responsabilityId,pageContext,webBean);
          }
        }
   return isSuccess;
  }

  public static Map getLimitScheduleOption(OAPageContext pageContext
                                    ,OAWebBean webBean
                                    ,Number templateId
                                    ,Number typePayment
                                    ,String typePaymentDesc
                                    ,String currencyCode)
  {
     Map retval = new java.util.HashMap();
     
     retval.put("Rate",new Number(0));
     retval.put("errcodOUT", "0");
     retval.put("errmsgOUT", "");
     
     XxGamModAntAMImpl amXxGamMod = null;
     XxGamModAntLovAMImpl amLov = null;
     XxGamMaLimitScheduleOptionVORowImpl limitScheduleOption = null;
     Number rateLimit = null;
    
     amXxGamMod = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
    
     if(null!=amXxGamMod){
       amLov = (XxGamModAntLovAMImpl)amXxGamMod.getXxGamModAntLovAM1();
     }
     
     if(null!=amLov){
     
       limitScheduleOption = amLov.getLimitScheduleOption(pageContext
                                                         ,webBean
                                                         ,templateId
                                                         ,typePayment
                                                         ,typePaymentDesc
                                                         ,currencyCode);
        if (null!=limitScheduleOption){
             System.out.println("\"Rate\": "+limitScheduleOption.getRate()); 
             rateLimit = limitScheduleOption.getRate();
             retval.put("Rate",rateLimit);
             } else{
             retval.put("errcodOUT", "1");
             retval.put("errmsgOUT", "A Ocurrido una Exeption al obtener el limite para el Schedule");
           }                                                       
     }
       
     return retval; 
  }
 /**
   * Metodo para llamar el workflow de cancelacion desde Oficina de Boletos Capa Utilitarios 2
   * @param pageContext
   * @param webBean
   * @param RequestId
   * @param employeeId
   * @param approverId
   * @param TicketNum
   * @return
   */
  static boolean callWorkFlowToCancelTicketsOffice(OAPageContext pageContext, 
                                                   OAWebBean webBean, 
                                                   Number RequestId, 
                                                   Number employeeId, 
                                                   Number approverId, 
                                                   String TicketNum)
  {
    boolean isSuccess = false;
    String itemTypeSET = null;
    String WfProcessSET = null;
    String wfItemKeyGET = null;

    XxGamModAntAMImpl AntAMImpl = null;
    OADBTransaction oadbTransaction = null;
    OADBTransactionImpl oadbTransactionImpl = null;
    WFContext ctxSET = null;
    
    Number numEmpleadoId = null;
    Number numApproverId = null; 
    Number numRequestId = null; 
    String strTicketNum = null; 
    BigDecimal bigAttribute = null;
    
    AntAMImpl = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
    oadbTransaction = AntAMImpl.getOADBTransaction();
    oadbTransactionImpl = (OADBTransactionImpl)AntAMImpl.getTransaction();
    
    numEmpleadoId = employeeId; 
    numApproverId = approverId; 
    numRequestId = RequestId; 
    strTicketNum = TicketNum; 
     
    ctxSET = (WFContext)oadbTransactionImpl.findObject("wfContext");

    if (ctxSET == null) {
      System.out.println("Transaction Context is Null!! setUpWfContext(oadbTransactionImpl); ");
      setUpWfContext(oadbTransactionImpl);
      ctxSET = (WFContext)oadbTransactionImpl.findObject("wfContext");
    }    
    
    if (ctxSET == null) {
          System.out.println("Transaction Context is Null!!");
   }else{
          itemTypeSET = "BOLETOSF";
          WfProcessSET = "XXGAM_PRC_OFFICE_CAN_2015";
          wfItemKeyGET = oadbTransaction.getSequenceValue("XXGAM_MA_WF_S").toString();
          wfItemKeyGET = itemTypeSET + "_" + wfItemKeyGET;
          
          WFEngineAPI.createProcess((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, WfProcessSET);

              if (WFEngineAPI.setItemOwner((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, pageContext.getUserName())) {
                System.out.println("CallCancelWF Set Item Owner: " + pageContext.getUserName());
              }
              
              if (WFEngineAPI.setItemUserKey((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, pageContext.getUserName())) {
                System.out.println("CallCancelWF Set Item User Key:" + pageContext.getUserName());
              }
              
             WFEngineAPI.setItemAttrText((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "#FROM_ROLE", pageContext.getUserName());

              
              bigAttribute = new BigDecimal(numEmpleadoId.toString());
              WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "EMPLEADO_ID", bigAttribute);

              bigAttribute = new BigDecimal(numApproverId.toString());
              WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "APROBADOR_ID", bigAttribute);
              
              bigAttribute = new BigDecimal(numRequestId.toString());
              WFEngineAPI.setItemAttrNumber((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET, "ANTICIPO_ID", bigAttribute);
             
              WFEngineAPI.startProcess((WFContext)oadbTransactionImpl.findObject("wfContext"), itemTypeSET, wfItemKeyGET);
          isSuccess = true; 
        }
        
    System.out.println("***CallCancelWFBug13:*** "+isSuccess);
    return isSuccess; 
  }
  
    public static String getDivisa (OAPageContext pageContext, OAWebBean webBean){
        
       int userId=0;
       userId=pageContext.getUserId();
       String userCurrency=null;
       XxGamModAntAMImpl amXxGamModAnt=null;
       
       amXxGamModAnt =  (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
       StringBuffer procedure= new StringBuffer(); 
       procedure.append("BEGIN "); 
       procedure.append("apps.XXGAM_AP_MOD_ANT_UTILS2_PKG.GET_CURRENCY("); 
       procedure.append(" P_USER_ID              => :1"); 
       procedure.append(",P_CURRENCY              => :2"); 
       procedure.append(");");
       procedure.append(" END; ");

      OADBTransaction dbTransaction = (OADBTransaction)amXxGamModAnt.getTransaction();
      OracleCallableStatement CallProcedure = (OracleCallableStatement)dbTransaction.createCallableStatement(procedure.toString(), 1);
      
        try
        {
          CallProcedure.setInt(1, userId);
          CallProcedure.registerOutParameter(2,Types.VARCHAR);
          CallProcedure.execute();
        
          userCurrency=CallProcedure.getString(2);                   
          
          }
        catch (Exception excepcion)
        {
          throw OAException.wrapperException(excepcion);
               
        } 
    
        return userCurrency;
    }
    
    
    public static String getDivisaDesc (OAPageContext pageContext, OAWebBean webBean){
        
       int userId=0;
       userId=pageContext.getUserId();
       String userCurrency=null;
       XxGamModAntAMImpl amXxGamModAnt=null;
       
       amXxGamModAnt =  (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
       StringBuffer procedure= new StringBuffer(); 
       procedure.append("BEGIN "); 
       procedure.append("apps.XXGAM_AP_MOD_ANT_UTILS2_PKG.GET_CURRENCY_DESC("); 
       procedure.append(" P_USER_ID              => :1"); 
       procedure.append(",P_CURRENCY_DESC              => :2"); 
       procedure.append(");");
       procedure.append(" END; ");

      OADBTransaction dbTransaction = (OADBTransaction)amXxGamModAnt.getTransaction();
      OracleCallableStatement CallProcedure = (OracleCallableStatement)dbTransaction.createCallableStatement(procedure.toString(), 1);
      
        try
        {
          CallProcedure.setInt(1, userId);
          CallProcedure.registerOutParameter(2,Types.VARCHAR);
          CallProcedure.execute();
        
          userCurrency=CallProcedure.getString(2);                   
          
          }
        catch (Exception excepcion)
        {
          throw OAException.wrapperException(excepcion);
               
        } 
    
        return userCurrency;
    }
}
