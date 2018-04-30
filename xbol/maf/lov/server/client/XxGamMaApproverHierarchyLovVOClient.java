package xxgam.oracle.apps.xbol.maf.lov.server.client;

import oracle.jbo.client.remote.ViewUsageImpl;
import oracle.jbo.domain.Number;

import xxgam.oracle.apps.xbol.maf.lov.server.common.XxGamMaApproverHierarchyLovVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaApproverHierarchyLovVOClient extends ViewUsageImpl implements XxGamMaApproverHierarchyLovVO {
    /**This is the default constructor (do not remove)
     */
    public XxGamMaApproverHierarchyLovVOClient() {
    }

    public void setSearchApproverHierarchy(Number jobNameId, 
                                           Number versionId) {
        Object _ret = 
            getApplicationModuleProxy().riInvokeExportedMethod(this,"setSearchApproverHierarchy",new String [] {"oracle.jbo.domain.Number","oracle.jbo.domain.Number"},new Object[] {jobNameId, versionId});
        return;
    }
}