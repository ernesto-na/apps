package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;


public class xXGamInvErrorsDevVORowImpl extends OAViewRowImpl {

    public static final int ERRORID = 0;
    public static final int REQID = 1;
    public static final int ERROR = 2;
    public static final int CODERROR = 3;
    public static final int NROSOLICITUD = 4;
    public static final int CREATEDBY = 5;


    public Number getErrorId() {
        return (Number)this.getAttributeInternal(0);
    }

    public void setErrorId(Number value) {
        this.setAttributeInternal(0, value);
    }

    public Number getReqId() {
        return (Number)this.getAttributeInternal(1);
    }

    public void setReqId(Number value) {
        this.setAttributeInternal(1, value);
    }

    public String getError() {
        return (String)this.getAttributeInternal(2);
    }

    public void setError(String value) {
        this.setAttributeInternal(2, value);
    }

    public String getCodError() {
        return (String)this.getAttributeInternal(3);
    }

    public void setCodError(String value) {
        this.setAttributeInternal(3, value);
    }

    public String getNroSolicitud() {
        return (String)this.getAttributeInternal(4);
    }

    public void setNroSolicitud(String value) {
        this.setAttributeInternal(4, value);
    }

    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case 0:
            return this.getErrorId();
        case 1:
            return this.getReqId();
        case 2:
            return this.getError();
        case 3:
            return this.getCodError();
        case 4:
            return this.getNroSolicitud();
        case 5:
            return this.getCreatedBy();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case 5:
            this.setCreatedBy((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
        }
    }

    public Number getCreatedBy() {
        return (Number)this.getAttributeInternal(5);
    }

    public void setCreatedBy(Number value) {
        this.setAttributeInternal(5, value);
    }
}
