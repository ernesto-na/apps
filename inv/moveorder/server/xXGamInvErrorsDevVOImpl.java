package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.domain.Number;


public class xXGamInvErrorsDevVOImpl extends OAViewObjectImpl {

    public void obtieneMsgErrorConsurrente(String soliId, Number status) {
        String clause = " NRO_SOLICITUD = :1 AND REQ_ID = :2  ";
        this.setWhereClause((String)null);
        this.setWhereClause(clause);
        this.setWhereClauseParams((Object[])null);
        this.setWhereClauseParam(0, soliId);
        this.setWhereClauseParam(1, status);
        this.executeQuery();
    }
}
