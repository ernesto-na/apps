package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.server.AttributeDefImpl;


public class xXGamInvFechaNvoEmplVORowImpl extends OAViewRowImpl {

    public static final int FECHAINICIO = 0;
    public static final int FECHAFINAL = 1;
    public static final int FECHAINGRESO = 2;


    public Date getFechaInicio() {
        return (Date)this.getAttributeInternal(0);
    }

    public void setFechaInicio(Date value) {
        this.setAttributeInternal(0, value);
    }

    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case 0:
            return this.getFechaInicio();
        case 1:
            return this.getFechaFinal();
        case 2:
            return this.getFechaIngreso();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case 0:
            this.setFechaInicio((Date)value);
            return;
        case 1:
            this.setFechaFinal((Date)value);
            return;
        case 2:
            this.setFechaIngreso((Date)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
        }
    }

    public Date getFechaFinal() {
        return (Date)this.getAttributeInternal(1);
    }

    public void setFechaFinal(Date value) {
        this.setAttributeInternal(1, value);
    }

    public Date getFechaIngreso() {
        return (Date)this.getAttributeInternal(2);
    }

    public void setFechaIngreso(Date value) {
        this.setAttributeInternal(2, value);
    }
}
