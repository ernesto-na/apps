package xxgam.oracle.apps.xbol.maf.lov.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaChiefByPositionIdLovVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public XxGamMaChiefByPositionIdLovVOImpl() {
    }

    /**Gets the bind variable value for pPositionId
     */
    public Number getpPositionId() {
        return (Number)getNamedWhereClauseParam("pPositionId");
    }

    /**Sets <code>value</code> for bind variable pPositionId
     */
    public void setpPositionId(Number value) {
        setNamedWhereClauseParam("pPositionId", value);
    }

    /**
     * Asigna los valores para los parametros de la consulta
     * @param positionId contiene la posición del empleado
     */
    public void setSearchChiefByPositionId(Number positionId){
        
        if(positionId != null){
            
            setWhereClause(null);
            setWhereClauseParams(null);
            
            setpPositionId(positionId);
            
            executeQuery();
        }
    }
    
    public void executeQuery() {
        setWhereClause(null);
        setWhereClauseParams(null);
        super.executeQuery();
    }
}
