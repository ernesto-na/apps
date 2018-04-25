package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;
import oracle.jbo.domain.Number;

public class xXGamInvFechaNvoEmplVOImpl extends OAViewObjectImpl {

   public void validaNuevoEmpleado(Number personId, long lKitId) {
      this.setWhereClause((String)null);
      this.setWhereClauseParams((Object[])null);
      this.setWhereClauseParam(0, personId);
      this.setWhereClauseParam(1, Long.valueOf(lKitId));
      this.executeQuery();
   }
}