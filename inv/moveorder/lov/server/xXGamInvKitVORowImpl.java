package xxgam.oracle.apps.inv.moveorder.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;


public class xXGamInvKitVORowImpl extends OAViewRowImpl {

    public static final int KITDES = 0;
    public static final int KITCOD = 1;
    public static final int KITID = 2;


    public String getKitDes() {
        return (String)this.getAttributeInternal(0);
    }

    public void setKitDes(String value) {
        this.setAttributeInternal(0, value);
    }

    public String getKitCod() {
        return (String)this.getAttributeInternal(1);
    }

    public void setKitCod(String value) {
        this.setAttributeInternal(1, value);
    }

    public Number getKitId() {
        return (Number)this.getAttributeInternal(2);
    }

    public void setKitId(Number value) {
        this.setAttributeInternal(2, value);
    }

    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case 0:
            return this.getKitDes();
        case 1:
            return this.getKitCod();
        case 2:
            return this.getKitId();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        super.setAttrInvokeAccessor(index, value, attrDef);
    }
}
