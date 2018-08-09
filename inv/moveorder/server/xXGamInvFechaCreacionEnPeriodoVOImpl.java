package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.domain.Number;


public class xXGamInvFechaCreacionEnPeriodoVOImpl extends OAViewObjectImpl {

    /**This is the default constructor (do not remove)
     */
    public xXGamInvFechaCreacionEnPeriodoVOImpl() {
    }

    public void validaFechaEnRango(String lpSoliId) {
        String clause = " soli_id = :1 ";
        this.setWhereClause((String)null);
        this.setWhereClause(clause);
        this.setWhereClauseParams((Object[])null);
        this.setWhereClauseParam(0, lpSoliId);
        this.executeQuery();
    }

    public void validaFechaEnRangoPersonId(Number personId) {
        String clause = " person_id = :1 ";
        this.setWhereClause((String)null);
        this.setWhereClause(clause);
        this.setWhereClauseParams((Object[])null);
        this.setWhereClauseParam(0, personId);
        this.executeQuery();
    }
}
