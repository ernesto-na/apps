package xxgam.oracle.apps.xbol.maf.utils;

import java.sql.SQLException;
import java.sql.Types;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;

import oracle.jbo.server.DBTransaction;

import oracle.jdbc.OracleCallableStatement;

import xxgam.oracle.apps.xbol.maf.server.XxGamMaGeneralReqVOImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamMaGeneralReqVORowImpl;
import xxgam.oracle.apps.xbol.maf.server.XxGamModAntAMImpl;

public class CcManagementValidation {

   private boolean CcManagementFlag = false;
   private int EmployeeId = 0;
   private int ApproverAltId = 0;
   private int CcEmployee = 0;
   private int CcApproverAlt = 0;
      
   private String errmsgOUT = null; 
   private String errcodOUT = null;

    public CcManagementValidation() {
    }
    
    public CcManagementValidation(OAPageContext pageContext, OAWebBean webBean) {
      
      XxGamModAntAMImpl ModAntAM = null;
      XxGamMaGeneralReqVOImpl GeneralReqVoImpl = null;
      XxGamMaGeneralReqVORowImpl GeneralReqVoRowImpl = null;
      
      System.out.println("Debug CMV-AHH 1: Start Constructor CcManagerValidation");
      
      System.out.println("Debug CMV-AHH 2: Get ModAntAM");
      ModAntAM = (XxGamModAntAMImpl)pageContext.getApplicationModule(webBean);
      if(null != ModAntAM){
        System.out.println("Debug CMV-AHH 3: Get GeneralReqVO");
        GeneralReqVoImpl = ModAntAM.getXxGamMaGeneralReqVO1();
        if(null != GeneralReqVoImpl){
          System.out.println("Debug CMV-AHH 4: Get GeneralReqVORow");
          GeneralReqVoRowImpl = (XxGamMaGeneralReqVORowImpl)GeneralReqVoImpl.getCurrentRow();
          if(null != GeneralReqVoRowImpl){
            
            if(null != GeneralReqVoRowImpl.getEmployeeId()){
               setEmployeeId(GeneralReqVoRowImpl.getEmployeeId().intValue());
            }
            
            System.out.println("Debug CMV-AHH 5: Set CcEmployee " + GeneralReqVoRowImpl.getCostCenterFlex());
            if(null != GeneralReqVoRowImpl.getCostCenterFlex()){
              
              System.out.println("IndexOF: " + GeneralReqVoRowImpl.getCostCenterFlex().indexOf("-"));
              
              setCcEmployee(Integer.parseInt(GeneralReqVoRowImpl.getCostCenterFlex().substring(0,GeneralReqVoRowImpl.getCostCenterFlex().indexOf("-"))));
              System.out.println("Debug CMV-AHH 6: Get CcEmployee "+ getCcEmployee());
              
              System.out.println("Debug CMV-AHH 7: ApproverAltId " + GeneralReqVoRowImpl.getApproverAltId());
              if(null != GeneralReqVoRowImpl.getApproverAltId()){
                setApproverAltId(GeneralReqVoRowImpl.getApproverAltId().intValue());
                setCcApproverAlt(findCcValueByPersonId(ModAntAM,getApproverAltId()));
                System.out.println("Debug CMV-AHH 8: find Cc Value " + getCcApproverAlt());
              }else{
                  System.out.println("No se ha podido obtener id del aprobador alterno");
                  setErrmsgOUT("No se ha podido obtener id del aprobador alterno");
                  System.out.println("XXGAM_MAF_CMV_001");
                  setErrcodOUT("XXGAM_MAF_CMV_001");
              }
            }else{
              setCcManagementFlag(true);
            }
          }
        }
      }
    }
    
    public int findCcValueByPersonId(XxGamModAntAMImpl pModAntAM,int pPersonId){
              
      StringBuffer stmt= new StringBuffer(); 
      stmt.append("BEGIN "); 
      stmt.append("apps.xxgam_ap_mod_ant_utils2_pkg.find_cc_by_person_id("); 
      stmt.append(" pni_person_id => :1"); 
      stmt.append(",pno_cc_value   => :2"); 
      stmt.append(",pso_errmsg     => :3");
      stmt.append(",pso_errcod     => :4");
      stmt.append(");");
      stmt.append(" END; ");
      
      OADBTransaction oadbtrans = (OADBTransaction)pModAntAM.getTransaction();
      OracleCallableStatement cs = (OracleCallableStatement)oadbtrans.createCallableStatement(stmt.toString(),DBTransaction.DEFAULT);
      
      try{
        cs.setInt(1,pPersonId);
        cs.registerOutParameter(2,Types.NUMERIC);
        cs.registerOutParameter(3,Types.VARCHAR);
        cs.registerOutParameter(4,Types.VARCHAR);
        cs.execute();
        
        System.out.println("Debug CMV-AHH 001: pni_person_id: " + pPersonId);
        System.out.println("Debug CMV-AHH 001: pno_cc_value: " + cs.getInt(2));
        System.out.println("Debug CMV-AHH 001: pso_errmsg: " + cs.getString(3));
        System.out.println("Debug CMV-AHH 001: pso_errcod: " + cs.getString(4));
        
        setErrmsgOUT(cs.getString(3));
        setErrcodOUT(cs.getString(4));
        
        return cs.getInt(2);
        
      } catch(Exception exception2){
          throw OAException.wrapperException(exception2);
        }         
    }
    
    public boolean doManagementValidation(XxGamModAntAMImpl pModAntAM,int pCcEmployee,int pCcApproverAlt,int pApproverAltId){
        
        boolean ManagementValidationFlag = false;
        
        StringBuffer stmt= new StringBuffer(); 
        stmt.append("BEGIN "); 
        stmt.append("apps.xxgam_ap_mod_ant_utils2_pkg.do_management_validation("); 
        stmt.append("pni_cc_employee => :1"); 
        stmt.append(",pni_cc_approver_alt => :2");
        stmt.append(",pni_approver_alt_id => :3");
        stmt.append(",pbo_cc_management_flag   => :4");
        stmt.append(",pso_errmsg     => :5");
        stmt.append(",pso_errcod     => :6");
        stmt.append(");");
        stmt.append(" END;");
        
        OADBTransaction oadbtrans = (OADBTransaction)pModAntAM.getTransaction();
        OracleCallableStatement cs = (OracleCallableStatement)oadbtrans.createCallableStatement(stmt.toString(),DBTransaction.DEFAULT);
        
        try{
          cs.setInt(1,pCcEmployee);
          cs.setInt(2,pCcApproverAlt);
          cs.setInt(3,pApproverAltId);
          cs.registerOutParameter(4,Types.VARCHAR);
          cs.registerOutParameter(5,Types.VARCHAR);
          cs.registerOutParameter(6,Types.VARCHAR);
          
          System.out.println("Previo a ejecutar plsql procedure");
          cs.execute();
          System.out.println("Post ejecutar plsql procedure");
          
          System.out.println("Debug CMV-AHH 002: pni_cc_employee: " + pCcEmployee);
          System.out.println("Debug CMV-AHH 002: pni_cc_approver_alt: " + pCcApproverAlt);
          System.out.println("Debug CMV-AHH 002: pni_approver_alt_id: " + pApproverAltId);
          System.out.println("Debug CMV-AHH 002: pbo_cc_management_flag: " + cs.getString(4));
          System.out.println("Debug CMV-AHH 002: pso_errmsg: " + cs.getString(5));
          System.out.println("Debug CMV-AHH 002: pso_errcod: " + cs.getString(6));
          
          setErrmsgOUT(cs.getString(5));
          setErrcodOUT(cs.getString(6));
          
          if(null != cs.getString(4) && cs.getString(4).equals("true")){
              ManagementValidationFlag = true;
          }else{
              ManagementValidationFlag = false;
          }
          
          return ManagementValidationFlag;
          
        } catch(SQLException sqlException1){
            System.out.println("SQL Exception - try call apps.xxgam_ap_mod_ant_utils2_pkg.do_management_validation");
            System.out.println(sqlException1.getMessage());
            throw OAException.wrapperException(sqlException1);
          }         
    }
    
    /*Getters */
    public int getEmployeeId(){
      return EmployeeId;
    }
     
    public int getApproverAltId(){
      return ApproverAltId;
    }
     
    public int getCcEmployee(){
      return CcEmployee;
    }
    
    public int getCcApproverAlt(){
      return CcApproverAlt;
    }
    
    public boolean getCcManagementFlag(){
      return CcManagementFlag;
    }
    
    public String getErrmsgOUT(){
      return errmsgOUT;
    }
    
    public String getErrcodOUT(){
      return errcodOUT;
    }
    
    /*Setters*/
    public void setEmployeeId(int pEmployeeId){
      EmployeeId = pEmployeeId;
    }
     
    public void setApproverAltId(int pApproverAltId){
      ApproverAltId = pApproverAltId;
    }
    
    public void setCcEmployee(int pCcEmployee){
      CcEmployee = pCcEmployee;
    }
    
    public void setCcApproverAlt(int pCcApproverAlt){
      CcApproverAlt = pCcApproverAlt;
    }
    
    public void setCcManagementFlag(boolean pCcManagementFlag){
      CcManagementFlag = pCcManagementFlag;
    }
    
    public void setErrmsgOUT(String pErrmsgOUT){
      errmsgOUT = pErrmsgOUT;
    }
    
    public void setErrcodOUT(String pErrcodOUT){
      errcodOUT = pErrcodOUT;
    }
    
}
