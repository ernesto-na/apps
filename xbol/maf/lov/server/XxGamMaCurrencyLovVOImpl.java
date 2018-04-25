package xxgam.oracle.apps.xbol.maf.lov.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import xxgam.oracle.apps.xbol.maf.lov.server.common.XxGamMaCurrencyLovVO;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaCurrencyLovVOImpl extends OAViewObjectImpl implements XxGamMaCurrencyLovVO {
    /**This is the default constructor (do not remove)
     */
    public XxGamMaCurrencyLovVOImpl() {
    }


    /**
     * Realiza la busqueda de valores de la divisa por codigo de divisa
     * @param currencyCode contiene el codigo de la divisa
     */
    public void searchCurrencyByCode(String currencyCode){
        
        if(currencyCode != null){
            setWhereClause("currency_code = :1");
            setWhereClauseParams(null);
            setWhereClauseParam(0, currencyCode);
            executeQuery();
        }
    }
}
