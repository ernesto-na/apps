package xxgam.oracle.apps.inv.moveorder.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.server.AttributeDefImpl;


public class xXGamInvEstadosVORowImpl extends OAViewRowImpl {

    public static final int MEANING = 0;
    public static final int LOOKUPCODE = 1;


    public String getMeaning() {
        return (String)this.getAttributeInternal(0);
    }

    public void setMeaning(String value) {
        this.setAttributeInternal(0, value);
    }

    public String getLookupCode() {
        return (String)this.getAttributeInternal(1);
    }

    public void setLookupCode(String value) {
        this.setAttributeInternal(1, value);
    }

    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case 0:
            return this.getMeaning();
        case 1:
            return this.getLookupCode();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        super.setAttrInvokeAccessor(index, value, attrDef);
    }
}
