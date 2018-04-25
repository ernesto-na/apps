package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

public class xXGamLookupAperPeriodVOImpl extends OAViewObjectImpl {

   public void validaAperturaPeriodo(String kitCode) {
      String clause = " MEANING = :1 ";
      this.setWhereClause((String)null);
      this.setWhereClause(clause);
      this.setWhereClauseParams((Object[])null);
      this.setWhereClauseParam(0, kitCode);
      this.executeQuery();
   }
}