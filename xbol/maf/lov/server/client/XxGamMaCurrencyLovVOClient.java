package xxgam.oracle.apps.xbol.maf.lov.server.client;

import oracle.jbo.client.remote.ViewUsageImpl;

import xxgam.oracle.apps.xbol.maf.lov.server.common.XxGamMaCurrencyLovVO;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaCurrencyLovVOClient extends ViewUsageImpl implements XxGamMaCurrencyLovVO {
    /**This is the default constructor (do not remove)
     */
    public XxGamMaCurrencyLovVOClient() {
    }

    public void searchCurrencyByCode(String currencyCode) {
        Object _ret = 
            getApplicationModuleProxy().riInvokeExportedMethod(this, "searchCurrencyByCode", 
                                                               new String[] { "java.lang.String" }, 
                                                               new Object[] { currencyCode });
        return;
    }
}
