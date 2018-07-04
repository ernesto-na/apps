/*    */package xxgam.oracle.apps.xbol.bref.webui;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.fnd.framework.webui.beans.form.OASubmitButtonBean;
import oracle.apps.fnd.framework.webui.beans.layout.OAHeaderBean;
import oracle.apps.fnd.framework.webui.beans.message.OAMessageTextInputBean;

import xxgam.oracle.apps.xbol.bref.server.BankReferenceAMImpl;
import xxgam.oracle.apps.xbol.bref.server.BankReferenceVOImpl;
/*    */ //*Hola Julian **/
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/* 10 */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/* 22 */public class BankReferenceCO
    /*    */ extends OAControllerImpl {
    /* 25 */
    public static final String RCS_ID = "$Header$";
    /*    */
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header$", "%packagename%");
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /*    */
    /* 34 */

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        /*    */super.processRequest(pageContext, webBean);
        /*    */
        /* 38 */BankReferenceAMImpl BankRefAM = null;
        /* 39 */BankReferenceVOImpl BankRefVO = null;
        /* 40 */OAWebBean oaWebBean = null;
        /*    */OAWebBean oaWebBean1 = null;
        /*    */String BancoS = null;
        /*    */String ClabeS = null;
        /*    */String CuentaS = null;
        /*    */String SucursalS = null;
        /*    */String ReferenciaS = null;
        /*    */String AccountS = null;
        /*    */String BankS = null;
        /*    */String BankCodeS = null;
        /*    */String BranchCodeS = null;
        /*    */String IbanS = null;
        /*    */String AbaS = null;
        /*    */String SwiftS = null;
        /*    */String CurrencyS = null;
        /*    */String CuentatransS = null;
        /*    */String NumeroempS = null;
        /*    */OAHeaderBean Region = null;
        /*    */OASubmitButtonBean Button = null;
        /* 56 */if (!pageContext.isFormSubmission()) {
            /* 57 */BankRefAM = 
                    (BankReferenceAMImpl)pageContext.getApplicationModule(webBean);
            /* 58 */Region = 
                    (OAHeaderBean)webBean.findChildRecursive("VentanillaRN");
            /*    */Button = 
                    (OASubmitButtonBean)webBean.findChildRecursive("download");
            /*    */
            /* 60 */if (BankRefAM != null) {
                /* 61 */BankRefAM.ClearInfo();
                /* 62 */BankRefAM.FindInfo();
                /* 63 */BankRefVO = BankRefAM.getBankReferenceVO1();
                /*    */
                /* 65 */if (BankRefVO.first().getAttribute("Nombrebanco") != 
                            null) {
                    /* 66 */BancoS = 
                            BankRefVO.first().getAttribute("Nombrebanco").toString();
                    /*    */
                }
                /* 68 */if (BankRefVO.first().getAttribute("Cuenta") != null) {
                    /*    */CuentaS = 
                            BankRefVO.first().getAttribute("Cuenta").toString();
                    /*    */
                }
                /* 71 */if (BankRefVO.first().getAttribute("Sucursal") != 
                            null) {
                    /*    */SucursalS = 
                            BankRefVO.first().getAttribute("Sucursal").toString();
                    /*    */
                }
                /* 74 */if (BankRefVO.first().getAttribute("Referencia") != 
                            null) {
                    /*    */SucursalS = 
                            BankRefVO.first().getAttribute("Referencia").toString();
                    /*    */
                }
                /* 77 */if (BankRefVO.first().getAttribute("Account") != 
                            null) {
                    /*    */AccountS = 
                            BankRefVO.first().getAttribute("Account").toString();
                    /*    */
                }
                /* 80 */if (BankRefVO.first().getAttribute("Bank") != null) {
                    /*    */BankS = 
                            BankRefVO.first().getAttribute("Bank").toString();
                    /*    */
                }
                /* 83 */if (BankRefVO.first().getAttribute("Bankcode") != 
                            null) {
                    /*    */BankCodeS = 
                            BankRefVO.first().getAttribute("Bankcode").toString();
                    /*    */
                }
                /* 86 */if (BankRefVO.first().getAttribute("Branchcode") != 
                            null) {
                    /*    */BranchCodeS = 
                            BankRefVO.first().getAttribute("Branchcode").toString();
                    /*    */
                }
                /* 89 */if (BankRefVO.first().getAttribute("Iban") != null) {
                    /*    */IbanS = 
                            BankRefVO.first().getAttribute("Iban").toString();
                    /*    */
                }
                /* 92 */if (BankRefVO.first().getAttribute("Aba") != null) {
                    /*    */AbaS = 
                            BankRefVO.first().getAttribute("Aba").toString();
                    /*    */
                }
                /* 95 */if (BankRefVO.first().getAttribute("Swift") != null) {
                    /*    */SwiftS = 
                            BankRefVO.first().getAttribute("Swift").toString();
                    /*    */
                }
                /* 98 */if (BankRefVO.first().getAttribute("Currency") != 
                            null) {
                    /*    */CurrencyS = 
                            BankRefVO.first().getAttribute("Currency").toString();
                    /*    */
                }
                /*101 */if (BankRefVO.first().getAttribute("Clabe") != null) {
                    /*    */ClabeS = 
                            BankRefVO.first().getAttribute("Clabe").toString();
                    /*    */
                }
                /*101 */if (BankRefVO.first().getAttribute("CuentaTransferencia") != 
                            null) {
                    /*    */CuentatransS = 
                            BankRefVO.first().getAttribute("CuentaTransferencia").toString();
                    /*    */
                }
                /*101 */if (BankRefVO.first().getAttribute("NumeroEmpleado") != 
                            null) {
                    /*    */NumeroempS = 
                            BankRefVO.first().getAttribute("NumeroEmpleado").toString();
                    /*    */
                }
                /*    */
                /*    */
                /*106 */System.out.println("Banco: " + BancoS);
                /*107 */System.out.println("Bank: " + BankS);
                /*108 */System.out.println("Sucursal: " + SucursalS);
                System.out.println("Cuenta: " + CuentaS);
                System.out.println("Account: " + AccountS);
                System.out.println("Referencia: " + ReferenciaS);
                System.out.println("Bank Code: " + BankCodeS);
                System.out.println("Branch Code: " + BranchCodeS);
                System.out.println("Iban: " + IbanS);
                System.out.println("Swift: " + SwiftS);
                System.out.println("Aba: " + AbaS);
                System.out.println("Currency: " + CurrencyS);
                System.out.println("Clabe: " + ClabeS);
                System.out.println("Cuenta Tranferencia: " + CuentatransS);
                System.out.println("Numero Empleado: " + NumeroempS);

                /* 59 */if (BancoS == null) {
                    /* 60 */System.out.println("ocultando campo Banco ");
                    /* 61 */oaWebBean = 
                            webBean.findChildRecursive("Nombrebanco");
                    /* 62 */oaWebBean.setRendered(false);
                    Region.setRendered(false);
                    System.out.println("ocultando campo Referencia ");
                    /*    */oaWebBean1 = 
                            webBean.findChildRecursive("Transfer_Reference");
                    /* 68 */oaWebBean1.setRendered(false);
                    Button.setRendered(false);
                    System.out.println("ocultando boton Download para internacionales");
                    /*    */
                }
                if (CuentaS == null) {
                    /*    */System.out.println("ocultando campo Cuenta ");
                    /*    */oaWebBean = webBean.findChildRecursive("Cuenta");
                    /* 68 */oaWebBean.setRendered(false);
                    /*    */
                }
                if (SucursalS == null) {
                    /*    */System.out.println("ocultando campo Sucursal ");
                    /*    */oaWebBean = webBean.findChildRecursive("Sucursal");
                    /* 68 */oaWebBean.setRendered(false);
                    /*    */
                }
                if (ReferenciaS == null) {
                    /*    */System.out.println("ocultando campo Referencia ");
                    /*    */oaWebBean = 
                            webBean.findChildRecursive("Referencia");
                    /* 68 */oaWebBean.setRendered(false);
                    /*    */
                }
                if (CuentatransS == null) {
                    /*    */System.out.println("ocultando campo Cuenta Transferencia ");
                    /*    */oaWebBean = 
                            webBean.findChildRecursive("CuentaTransferencia");
                    /* 68 */oaWebBean.setRendered(false);
                    /*    */
                }
                if (NumeroempS == null) {
                    /*    */System.out.println("ocultando campo Numero Empleado ");
                    /*    */oaWebBean = 
                            webBean.findChildRecursive("NumeroEmpleado");
                    /* 68 */oaWebBean.setRendered(false);
                    /*    */
                }
                if (AccountS == null) {
                    /*    */System.out.println("ocultando campo Account ");
                    /*    */oaWebBean = webBean.findChildRecursive("Account");
                    oaWebBean1 = webBean.findChildRecursive("Account1");
                    /* 68 */oaWebBean.setRendered(false);
                    /* 69 */oaWebBean1.setRendered(false);
                    /*    */
                }
                if (BankS == null) {
                    /*    */System.out.println("ocultando campo Bank ");
                    /*    */oaWebBean = webBean.findChildRecursive("Bank");
                    /* 68 */oaWebBean.setRendered(false);
                    /*    */
                }
                if (BankCodeS == null) {
                    /*    */System.out.println("ocultando campo Bank Code ");
                    /*    */oaWebBean = webBean.findChildRecursive("Bankcode");
                    oaWebBean1 = webBean.findChildRecursive("Bankcode1");
                    /* 68 */oaWebBean.setRendered(false);
                    /* 68 */oaWebBean1.setRendered(false);
                    /*    */
                }
                if (BranchCodeS == null) {
                    /*    */System.out.println("ocultando campo Branch Code ");
                    /*    */oaWebBean = 
                            webBean.findChildRecursive("Branchcode");
                    oaWebBean1 = webBean.findChildRecursive("Branchcode1");
                    /* 68 */oaWebBean.setRendered(false);
                    /* 68 */oaWebBean1.setRendered(false);
                    /*    */
                }
                if (IbanS == null) {
                    /*    */System.out.println("ocultando campo Iban ");
                    /*    */oaWebBean = webBean.findChildRecursive("Iban");
                    oaWebBean1 = webBean.findChildRecursive("Iban1");
                    /* 68 */oaWebBean.setRendered(false);
                    /* 68 */oaWebBean1.setRendered(false);
                    /*    */
                }
                if (AbaS == null) {
                    /*    */System.out.println("ocultando campo Aba ");
                    /*    */oaWebBean = webBean.findChildRecursive("Aba");
                    /*    */oaWebBean1 = webBean.findChildRecursive("Aba1");
                    /* 68 */oaWebBean.setRendered(false);
                    /* 68 */oaWebBean1.setRendered(false);
                    /*    */
                }
                if (SwiftS == null) {
                    /*    */System.out.println("ocultando campo Swift ");
                    /*    */oaWebBean = webBean.findChildRecursive("Swift");
                    /*    */oaWebBean1 = webBean.findChildRecursive("Swift1");
                    /* 68 */oaWebBean.setRendered(false);
                    /* 68 */oaWebBean1.setRendered(false);
                    /*    */
                }
                if (CurrencyS == null) {
                    /*    */System.out.println("ocultando campo Currency ");
                    /*    */oaWebBean = webBean.findChildRecursive("Currency");
                    /*    */oaWebBean1 = 
                            webBean.findChildRecursive("Currency1");
                    /* 68 */oaWebBean.setRendered(false);
                    /* 68 */oaWebBean1.setRendered(false);
                    /*    */
                }
                if (ClabeS == null) {
                    /*    */System.out.println("ocultando campo Clabe ");
                    /*    */oaWebBean = webBean.findChildRecursive("Clabe");
                    /* 68 */oaWebBean.setRendered(false);
                    /*    */
                }
                /*    */
            }
            /*    */
        }
        /*    */
    }
    /*    */
    /*    */
    /*    */
    /*    */

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        /* 74 */super.processFormRequest(pageContext, webBean);
        /* 75 */if (pageContext.getParameter("download") != null) {
            /* 76 */System.out.println(pageContext.getParameter("download"));
            /*    */
        }
        /*    */
        /* 79 */BankReferenceAMImpl BankRefAM = null;
        /* 80 */BankReferenceVOImpl BankRefVO = null;
        /*    */
        /* 82 */BankRefAM = 
                (BankReferenceAMImpl)pageContext.getApplicationModule(webBean);
        /*    */
        /* 84 */if (BankRefAM != null)
        /*    */ {
            /* 86 */BankRefVO = BankRefAM.getBankReferenceVO1();
            /*    */
            /* 88 */OAMessageTextInputBean ImporteMTIB = 
                (OAMessageTextInputBean)webBean.findChildRecursive("Importe");
            /*    */
            /* 90 */if (ImporteMTIB.getValue(pageContext) != null) {
                /* 91 */System.out.println("Importe: " + 
                                           ImporteMTIB.getValue(pageContext));
                /* 92 */BankRefVO.first().setAttribute("Importe", 
                                                       ImporteMTIB.getValue(pageContext));
                /*    */
            }
            /*    */
            /* 95 */pageContext.setForwardURL("OA.jsp?page=/xxgam/oracle/apps/xbol/bref/webui/PrintPG", 
                                              null, (byte)0, null, null, true, 
                                              "N", (byte)99);
            /*    */
        }
        /*    */
    }
    /*    */
}


/* Location:              C:\Users\GHCM-T430-06_2\Documents\Aeromexico\iExpenses\Fuentes\Link_referencia\bref_01_06_18.zip!\bref\webui\BankReferenceCO.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */