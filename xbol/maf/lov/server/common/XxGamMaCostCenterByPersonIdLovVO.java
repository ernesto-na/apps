package xxgam.oracle.apps.xbol.maf.lov.server.common;

import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---------------------------------------------------------------------
public interface XxGamMaCostCenterByPersonIdLovVO extends ViewObject {

    void setEmployeePersonId(Number employeePersonId);

    void searchCostCenterByPersonId(Number numPersonId, String vcLookupType);

    void setVcLookupType(String vcLookupType);
}
