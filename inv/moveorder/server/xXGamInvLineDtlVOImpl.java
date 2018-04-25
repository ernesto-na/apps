package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

public class xXGamInvLineDtlVOImpl extends OAViewObjectImpl {

    /**This is the default constructor (do not remove)
     */
    public xXGamInvLineDtlVOImpl() {
    }

    public void initQuery(String pSoliDtlID) {
      this.setWhereClauseParams((Object[])null);
      this.setWhereClauseParam(0, pSoliDtlID);
      this.executeQuery();
   }
}