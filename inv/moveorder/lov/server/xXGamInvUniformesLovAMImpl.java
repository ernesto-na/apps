package xxgam.oracle.apps.inv.moveorder.lov.server;

import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import xxgam.oracle.apps.inv.moveorder.lov.server.xXGamInvEmplVOImpl;
import xxgam.oracle.apps.inv.moveorder.lov.server.xXGamInvEstadosVOImpl;
import xxgam.oracle.apps.inv.moveorder.lov.server.xXGamInvKitVOImpl;
import xxgam.oracle.apps.inv.moveorder.lov.server.xXGamInvSolVOImpl;
import xxgam.oracle.apps.inv.moveorder.lov.server.xXGamInvTallaVOImpl;
import xxgam.oracle.apps.inv.moveorder.lov.server.xXgamInvEmpleadosVOImpl;
import oracle.jbo.Row;
public class xXGamInvUniformesLovAMImpl extends OAApplicationModuleImpl {

   public xXgamInvEmpleadosVOImpl getxXgamInvEmpleadosVO1() {
      return (xXgamInvEmpleadosVOImpl)this.findViewObject("xXgamInvEmpleadosVO1");
   }

   public xXGamInvSolVOImpl getxXGamInvSolVO1() {
      return (xXGamInvSolVOImpl)this.findViewObject("xXGamInvSolVO1");
   }

   public xXGamInvTallaVOImpl getxXGamInvTallaVO1() {
      return (xXGamInvTallaVOImpl)this.findViewObject("xXGamInvTallaVO1");
   }

   public static void main(String[] args) {
      launchTester("xxgam.oracle.apps.inv.moveorder.lov.server", "xXGamInvUniformesLovAMLocal");
   }

   public xXGamInvKitVOImpl getxXGamInvKitVO1() {
      return (xXGamInvKitVOImpl)this.findViewObject("xXGamInvKitVO1");
   }

   public xXGamInvEstadosVOImpl getxXGamInvEstadosVO1() {
      return (xXGamInvEstadosVOImpl)this.findViewObject("xXGamInvEstadosVO1");
   }

   public xXGamInvEmplVOImpl getxXGamInvEmplVO1() {
      return (xXGamInvEmplVOImpl)this.findViewObject("xXGamInvEmplVO1");
   }
   
   
   public void executeInitial(String p_talla, String p_dotaiD){
       int count;
       xXGamInvTallaVOImpl vo = getxXGamInvTallaVO1();
       vo.setWhereClause("dota_id = '" + p_dotaiD + "' AND talla_nbr LIKE '"  + p_talla + "%'");
       vo.executeQuery();
       count = 1;
      
       for (int i = 0; i < 11; i++) {
           Row row = vo.next();
           //System.out.println("TallaNbr: " + row.getAttribute("TallaNbr") );
           //System.out.println("InventoryCod: " + row.getAttribute("InventoryCod"));
       }
     
       
       System.out.println("Se ejecuta reporte inicial.");
   }
}