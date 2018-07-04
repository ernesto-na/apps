/*     */package xxgam.oracle.apps.xbol.bref.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import oracle.apps.fnd.common.VersionInfo;
import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransactionImpl;
import oracle.apps.fnd.framework.webui.OAControllerImpl;
import oracle.apps.fnd.framework.webui.OAPageContext;
import oracle.apps.fnd.framework.webui.beans.OAWebBean;
import oracle.apps.xdo.oa.schema.server.TemplateHelper;

import oracle.cabo.ui.data.DataObject;

import oracle.xml.parser.v2.XMLNode;

import xxgam.oracle.apps.xbol.bref.server.BankReferenceAMImpl;
import xxgam.oracle.apps.xbol.bref.server.BankReferenceVOImpl;
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
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */public class gvsDocCO
    /*     */ extends OAControllerImpl {
    /*     */
    public static final String RCS_ID = "$Header$";
    /*  43 */
    public static final boolean RCS_ID_RECORDED = 
        VersionInfo.recordClassVersion("$Header$", "%packagename%");
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */
    /*     */

    public void processRequest(OAPageContext pageContext, OAWebBean webBean) {
        /*  52 */super.processRequest(pageContext, webBean);
        /*  53 */BankReferenceAMImpl BankRefAM = null;
        /*  54 */BankRefAM = 
                (BankReferenceAMImpl)pageContext.getApplicationModule(webBean);
        /*  55 */if (BankRefAM != null)
        /*     */ {
            /*     */
            /*  58 */HttpServletResponse response = null;
            /*  59 */DataObject sessionDictionary = null;
            /*     */
            /*     */
            /*     */
            /*  63 */BankReferenceVOImpl ReferenceVOImpl = null;
            /*  64 */ReferenceVOImpl = BankRefAM.getBankReferenceVO1();
            /*  65 */XMLNode xMLNode = null;
            /*     */
            /*     */try
            /*     */ {
                /*     */try
                /*     */ {
                    /*  71 */xMLNode = 
                            (XMLNode)ReferenceVOImpl.writeXML(0, -2);
                    /*  72 */xMLNode.print(System.out);
                    /*     */
                }
                /*     */ catch (Exception exception)
                /*     */ {
                    /*  76 */System.out.println("Excepcion 1.1.- al: " + 
                                                exception.getMessage());
                    /*  77 */throw new OAException("No es posible obtener los datos solicitados", 
                                                   (byte)1);
                    /*     */
                }
                /*     */
                /*     */
            }
            /*     */ catch (Exception exception)
            /*     */ {
                /*  83 */System.out.println("Excepcion 1.2.- al: " + 
                                            exception.getMessage());
                /*  84 */throw new OAException("XBOL", 
                                               "XXGAM_MAF_AD_DATA_XPORT_ERROR", 
                                               null, (byte)1, null);
                /*     */
            }
            /*     */
            /*     */
            /*     */
            /*  89 */System.out.println(xMLNode);
            /*     */
            /*     */
            /*     */
            /*     */try
            /*     */ {
                /*  95 */sessionDictionary = 
                        pageContext.getNamedDataObject("_SessionParameters");
                /*  96 */response = 
                        (HttpServletResponse)sessionDictionary.selectValue(null, 
                                                                           "HttpServletResponse");
                /*     */
                /*  98 */ServletOutputStream servletOutStream = 
                    response.getOutputStream();
                /*     */
                /*     */
                /*     */
                /*     */
                /* 103 */String contentDisposition = 
                    "attachment;filename=Solicitud_ficha.pdf";
                /* 104 */response.setHeader("Content-Disposition", 
                                            contentDisposition);
                /*     */
                /* 106 */response.setContentType("application/pdf");
                /*     */
                /* 108 */ByteArrayOutputStream outputStream = 
                    new ByteArrayOutputStream();
                /* 109 */xMLNode.print(outputStream);
                /*     */
                /*     */
                /* 112 */ByteArrayInputStream inputStream = 
                    new ByteArrayInputStream(outputStream.toByteArray());
                /*     */
                /*     */
                /* 115 */ByteArrayOutputStream pdfFile = 
                    new ByteArrayOutputStream();
                /* 116 */String TemplateCode = null;
                /* 117 */TemplateCode = "XXGAM_AP_FICHA";
                /*     */
                /* 119 */TemplateHelper.processTemplate(((OADBTransactionImpl)BankRefAM.getOADBTransaction()).getAppsContext(), 
                                                        "XBOL", TemplateCode, 
                                                        ((OADBTransactionImpl)pageContext.getApplicationModule(webBean).getOADBTransaction()).getUserLocale().getLanguage(), 
                                                        ((OADBTransactionImpl)pageContext.getApplicationModule(webBean).getOADBTransaction()).getUserLocale().getCountry(), 
                                                        inputStream, (byte)1, 
                                                        null, pdfFile);
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
                /* 131 */byte[] bytesPDF = pdfFile.toByteArray();
                /* 132 */response.setContentLength(bytesPDF.length);
                /* 133 */servletOutStream.write(bytesPDF, 0, bytesPDF.length);
                /* 134 */servletOutStream.flush();
                /* 135 */servletOutStream.close();
                /*     */
                /*     */
                /* 138 */pageContext.setDocumentRendered(false);
                /*     */
            }
            /*     */ catch (Exception exception)
            /*     */ {
                /* 142 */System.out.println("Excepcion 1.3.- al " + 
                                            exception.getMessage());
                /* 143 */throw new OAException("XBOL", 
                                               "XXGAM_MAF_AD_XPORT_ERROR", 
                                               null, (byte)1, null);
                /*     */
            }
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

    public void processFormRequest(OAPageContext pageContext, 
                                   OAWebBean webBean) {
        /* 155 */super.processFormRequest(pageContext, webBean);
        /*     */
    }
    /*     */
}
