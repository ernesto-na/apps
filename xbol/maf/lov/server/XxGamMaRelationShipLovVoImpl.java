package xxgam.oracle.apps.xbol.maf.lov.server;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaRelationShipLovVoImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public XxGamMaRelationShipLovVoImpl() {
    }
    
    
    public String getRelationshipDescription(String lookUpCode){
        //Verifica nulidad
         String description = null;
        if (lookUpCode == null)
            return null;
        try {
            setWhereClause("LOOKUP_CODE = :1");
            setWhereClauseParams(null); // Always reset
            setWhereClauseParam(0,lookUpCode);
            executeQuery();
            //Inicializa el current row
            if (getRowCount() > 0) {
               description  = String.valueOf(first().getAttribute("Meaning"));
            } 
            setWhereClause(null);
            setWhereClauseParams(null); // Always reset
        } catch (Exception exception) {
            setWhereClause(null);
            setWhereClauseParams(null); 
        }   
        return description;
    }
}