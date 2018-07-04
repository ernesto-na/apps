package xxgam.oracle.apps.inv.moveorder.lov.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.domain.Number;


public class xXgamInvEmpleadosVOImpl extends OAViewObjectImpl {

    /**This is the default constructor (do not remove)
     */
    public xXgamInvEmpleadosVOImpl() {
    }

    public boolean buscaEmpleadoPorClave(String cveEmpl) {
        boolean isEmplValido = false;
        this.setWhereClause((String)null);
        this.setWhereClauseParams((Object[])null);
        String whereClause = " CLAVE_EMPLEADO = \'" + cveEmpl + "\'";
        this.setWhereClause(whereClause);
        this.executeQuery();
        if (this.getEstimatedRowCount() > 0L) {
            isEmplValido = true;
        }

        return isEmplValido;
    }

    public String buscaCuentaPorClaveEmpl(String cveEmpl) {
        String ctaEmpl = null;
        this.setWhereClause((String)null);
        this.setWhereClauseParams((Object[])null);
        String whereClause = " CLAVE_EMPLEADO = \'" + cveEmpl + "\'";
        this.setWhereClause(whereClause);
        this.executeQuery();
        if (this.getEstimatedRowCount() > 0L) {
            this.setCurrentRow(this.first());
            ctaEmpl = String.valueOf(this.first().getAttribute("Cuenta"));
        }

        return ctaEmpl;
    }

    public void obtienePersonId(Number userId) {
        this.setWhereClause((String)null);
        this.setWhereClauseParams((Object[])null);
        String whereClause = " user_id = \'" + userId + "\'";
        this.setWhereClause(whereClause);
        this.executeQuery();
    }

    public String buscaEmpleadoPorUserID(Number lUserId) {
        String cveEmpleado = null;
        this.setWhereClause((String)null);
        this.setWhereClauseParams((Object[])null);
        String whereClause = " user_id = " + lUserId + "";
        this.setWhereClause(whereClause);
        this.executeQuery();
        if (this.getEstimatedRowCount() > 0L) {
            this.setCurrentRow(this.first());
            cveEmpleado = 
                    String.valueOf(this.first().getAttribute("ClaveEmpleado"));
        }

        return cveEmpleado;
    }
}
