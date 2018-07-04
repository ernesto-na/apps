/*     */package xxgam.oracle.apps.xbol.bref.server;

import com.sun.java.util.collections.Hashtable;

import java.sql.SQLException;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.OAViewObject;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;
import oracle.apps.xdo.XDOException;
import oracle.apps.xdo.oa.util.DataTemplate;

import oracle.jbo.domain.BlobDomain;

import oracle.jdbc.internal.OracleCallableStatement;
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */public class BankReferenceAMImpl
    /*     */ extends OAApplicationModuleImpl {
    /*     */

    public static void main(String[] args) {
        /*  37 */launchTester("xxgam.oracle.apps.xbol.bref.server", 
                              "BankReferenceAMLocal");
        /*     */
    }
    /*     */
    /*     */
    /*     */

    public void FindInfo() {
        /*  43 */OADBTransaction oadbtransaction = 
            (OADBTransaction)getTransaction();
        /*  44 */OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)getTransaction();
        /*     */
        /*  46 */StringBuffer str = new StringBuffer();
        /*  47 */str.append(" DECLARE out_bool boolean:=FALSE; BEGIN ");
        /*  48 */str.append(" out_bool := apps.xxgam_ap_emp_prov_pkg.llena_ficha_deposito(); ");
        /*  49 */str.append(" END; ");
        /*     */
        /*  51 */OracleCallableStatement oraclecallablestatement = 
            (OracleCallableStatement)oadbtransaction.createCallableStatement(str.toString(), 
                                                                             1);
        /*     */try
        /*     */ {
            /*  54 */oraclecallablestatement.execute();
            /*     */
        } catch (Exception e) {
            /*  56 */System.out.println("Error maligno:" + e.getMessage());
            /*     */
        }
        /*     */
        /*  59 */OAViewObject vo = (OAViewObject)getBankReferenceVO1();
        /*  60 */vo.executeQuery();
        /*  61 */if (!vo.hasNext()) {
            /*  62 */System.out.println("Error que yo quiero");
            /*     */
        } else {
            /*  64 */System.out.println("El view Object a sido llenado");
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */

    public void ClearInfo() {
        /*  70 */OADBTransaction oadbtransaction = 
            (OADBTransaction)getTransaction();
        /*  71 */OADBTransactionImpl oadbtransactionimpl = 
            (OADBTransactionImpl)getTransaction();
        /*     */
        /*  73 */StringBuffer str = new StringBuffer();
        /*  74 */str.append(" BEGIN ");
        /*  75 */str.append(" apps.xxgam_ap_emp_prov_pkg.llena_ficha_deposito_limpia(); ");
        /*  76 */str.append(" END; ");
        /*     */
        /*  78 */OracleCallableStatement oraclecallablestatement = 
            (OracleCallableStatement)oadbtransaction.createCallableStatement(str.toString(), 
                                                                             1);
        /*     */try
        /*     */ {
            /*  81 */oraclecallablestatement.execute();
            /*     */
        } catch (Exception e) {
            /*  83 */System.out.println("Error maligno:" + e.getMessage());
            /*     */
        }
        /*     */
    }
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public BankReferenceVOImpl getBankReferenceVO1() {
        /*  95 */return (BankReferenceVOImpl)findViewObject("BankReferenceVO1");
        /*     */
    }
    /*     */
    /*     */

    public BlobDomain getGVSData(String strPersonId) {
        /* 100 */System.out.println("bug3");
        /* 101 */BlobDomain blobDomain = new BlobDomain();
        /*     */try
        /*     */ {
            /* 104 */System.out.println("bug3.1");
            /* 105 */DataTemplate datatemplate = 
                new DataTemplate(((OADBTransactionImpl)getOADBTransaction()).getAppsContext(), 
                                 "XBOL", "XXGAM_AP_FICHA");
            /* 106 */System.out.println("bug3.2");
            /* 107 */Hashtable parameters = new Hashtable();
            /* 108 */System.out.println("bug3.3");
            /*     */
            /* 110 */datatemplate.setParameters(parameters);
            /* 111 */System.out.println("bug3.4");
            /* 112 */datatemplate.setOutput(blobDomain.getBinaryOutputStream());
            /* 113 */System.out.println("bug3.5");
            /* 114 */datatemplate.processData();
            /* 115 */System.out.println("bug3.6");
            /*     */
        }
        /*     */ catch (SQLException e)
        /*     */ {
            /* 119 */throw new OAException("SQL Error=" + e.getMessage(), 
                                           (byte)0);
            /*     */
        }
        /*     */ catch (XDOException e)
        /*     */ {
            /* 123 */throw new OAException("XDOException" + e.getMessage(), 
                                           (byte)0);
            /*     */
        }
        /*     */ catch (Exception e)
        /*     */ {
            /* 127 */throw new OAException("Exception" + e.getMessage(), 
                                           (byte)0);
            /*     */
        }
        /*     */
        /* 130 */return blobDomain;
        /*     */
    }
    /*     */
}


/* Location:              C:\Users\GHCM-T430-06_2\Documents\Aeromexico\iExpenses\Fuentes\Link_referencia\bref_01_06_18.zip!\bref\server\BankReferenceAMImpl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */