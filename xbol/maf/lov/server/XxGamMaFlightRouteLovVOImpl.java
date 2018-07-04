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
public class XxGamMaFlightRouteLovVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public XxGamMaFlightRouteLovVOImpl() {
    }

    public String getMeaning(String lookUpCode) {
        //Verifica nulidad
        String description = null;
        if (lookUpCode == null)
            return null;
        try {
            clearViewCriterias();
            //Declara los recursos    
            ViewCriteria vcFlightRoute = null;
            ViewCriteriaRow rowVCflightRoute = null;

            //Crea el criterio de busqueda
            vcFlightRoute = createViewCriteria();
            rowVCflightRoute = vcFlightRoute.createViewCriteriaRow();
            rowVCflightRoute.setAttribute("LookupCode", lookUpCode);

            //Aplica el criterio
            vcFlightRoute.addElement(rowVCflightRoute);
            applyViewCriteria(vcFlightRoute);
            executeQuery();
            clearViewCriterias();
            //Inicializa el current row
            if (getEstimatedRowCount() > 0) {
                setCurrentRow(first());
                description = String.valueOf(first().getAttribute("Meaning"));
            }
        } catch (Exception exception) {
            clearViewCriterias();
            System.out.println("Erro " + exception.getMessage());
            //Propaga la excepcion.
            throw new OAException("No es posible encontrar el registro", 
                                  OAException.WARNING);
        }
        return description;
    }
}
