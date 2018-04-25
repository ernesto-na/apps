package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

public class xXGamInvSoliVOImpl extends OAViewObjectImpl
{

    /**This is the default constructor (do not remove)
     */
    public xXGamInvSoliVOImpl() {
    }

    public void initQuery(String pSoliID) {
      this.setWhereClauseParams((Object[])null);
      this.setWhereClauseParam(0, pSoliID);
      this.executeQuery();
   }

   public String obtieneStatusporNroSolicitud(String lpSoliId) {
      String status = null;
      this.setWhereClause((String)null);
      this.setWhereClauseParams((Object[])null);
      this.setWhereClauseParams((Object[])null);
      this.setWhereClauseParam(0, lpSoliId);
      this.executeQuery();
      if(this.getEstimatedRowCount() > 0L) {
         this.setCurrentRow(this.first());
         status = String.valueOf(this.first().getAttribute("StatusDsp"));
      }

      return status;
   }

  /**
   * Metodo que remueve el registro que se crea en la pantalla de Crear Uni 
   * "Proyecto Historico de Tallas"
   */
  public void removeRowsWithoutID()
  {
    xXGamInvSoliVORowImpl InvSoliVORowImpl = (xXGamInvSoliVORowImpl)this.getCurrentRow();
    if(null!=InvSoliVORowImpl){
      System.out.println("In removeRowsWithoutID");
      oracle.jbo.domain.Number numSoliID = InvSoliVORowImpl.getSoliId();
      System.out.println("numSoliID:"+numSoliID);
      if(null==numSoliID){
        this.removeCurrentRow();
        this.clearCache();
        this.setMaxFetchSize(-1);
      }else if(null!=numSoliID){
         if(0!=numSoliID.compareTo(0)){
           this.setMaxFetchSize(-1);
         }
       }
      
      System.out.println("Out removeRowsWithoutID");
    }
    
  }
  
}
