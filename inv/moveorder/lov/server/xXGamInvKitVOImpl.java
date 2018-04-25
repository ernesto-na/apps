package xxgam.oracle.apps.inv.moveorder.lov.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
import oracle.jbo.domain.Number;

public class xXGamInvKitVOImpl extends OAViewObjectImpl {

   public void obtieneKit(Number idKit) {
      String clause = " kit_id = :1 ";
      this.setWhereClause((String)null);
      this.setWhereClause(clause);
      this.setWhereClauseParams((Object[])null);
      this.setWhereClauseParam(0, idKit);
      this.executeQuery();
   }
}