package xxgam.oracle.apps.xbol.maf.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaApproverHierarchyLovVORowImpl extends OAViewRowImpl {
    public static final int APPROVERID = 0;
    public static final int APPROVERFULLNAME = 1;
    public static final int APPROVERFIRTNAME = 2;
    public static final int APPROVERLASTNAME = 3;
    public static final int APPROVERJOBNAME = 4;

    /**This is the default constructor (do not remove)
     */
    public XxGamMaApproverHierarchyLovVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute ApproverId
     */
    public Number getApproverId() {
        return (Number)getAttributeInternal(APPROVERID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApproverId
     */
    public void setApproverId(Number value) {
        setAttributeInternal(APPROVERID, value);
    }

    /**Gets the attribute value for the calculated attribute ApproverFullName
     */
    public String getApproverFullName() {
        return (String)getAttributeInternal(APPROVERFULLNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApproverFullName
     */
    public void setApproverFullName(String value) {
        setAttributeInternal(APPROVERFULLNAME, value);
    }

    /**Gets the attribute value for the calculated attribute ApproverFirtName
     */
    public String getApproverFirtName() {
        return (String)getAttributeInternal(APPROVERFIRTNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApproverFirtName
     */
    public void setApproverFirtName(String value) {
        setAttributeInternal(APPROVERFIRTNAME, value);
    }

    /**Gets the attribute value for the calculated attribute ApproverLastName
     */
    public String getApproverLastName() {
        return (String)getAttributeInternal(APPROVERLASTNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApproverLastName
     */
    public void setApproverLastName(String value) {
        setAttributeInternal(APPROVERLASTNAME, value);
    }

    /**Gets the attribute value for the calculated attribute ApproverJobName
     */
    public String getApproverJobName() {
        return (String)getAttributeInternal(APPROVERJOBNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApproverJobName
     */
    public void setApproverJobName(String value) {
        setAttributeInternal(APPROVERJOBNAME, value);
    }


    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case APPROVERID:
            return getApproverId();
        case APPROVERFULLNAME:
            return getApproverFullName();
        case APPROVERFIRTNAME:
            return getApproverFirtName();
        case APPROVERLASTNAME:
            return getApproverLastName();
        case APPROVERJOBNAME:
            return getApproverJobName();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}
