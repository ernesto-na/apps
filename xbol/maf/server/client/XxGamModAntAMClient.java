package xxgam.oracle.apps.xbol.maf.server.client;

import oracle.jbo.ApplicationModule;
import oracle.jbo.client.remote.ApplicationModuleImpl;

import xxgam.oracle.apps.xbol.maf.server.common.XxGamModAntAM;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamModAntAMClient extends ApplicationModuleImpl implements XxGamModAntAM {
    /**This is the default constructor (do not remove)
     */
    public XxGamModAntAMClient() {
    }


    public ApplicationModule getXxGamModAntLovAM() {
        return (ApplicationModule)findApplicationModule("XxGamModAntLovAM1");
    }

  public void duplicatePayment(String sReference)
  {
    Object _ret = 
      this.riInvokeExportedMethod(this,"duplicatePayment",new String [] {"java.lang.String"},new Object[] {sReference});
    return;
  }
}