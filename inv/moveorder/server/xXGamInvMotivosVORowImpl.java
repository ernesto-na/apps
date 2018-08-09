package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;


public class xXGamInvMotivosVORowImpl extends OAViewRowImpl {

    public static final int REASONID = 0;
    public static final int REASONNAME = 1;


    public Number getReasonId() {
        return (Number)this.getAttributeInternal(0);
    }

    public void setReasonId(Number value) {
        this.setAttributeInternal(0, value);
    }

    public String getReasonName() {
        return (String)this.getAttributeInternal(1);
    }

    public void setReasonName(String value) {
        this.setAttributeInternal(1, value);
    }

    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case 0:
            return this.getReasonId();
        case 1:
            return this.getReasonName();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case 0:
            this.setReasonId((Number)value);
            return;
        case 1:
            this.setReasonName((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
        }
    }
}
