package xxgam.oracle.apps.inv.moveorder.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;


public class xXgamInvEmpleadosVORowImpl extends OAViewRowImpl {

    public static final int PERSONID = 0;
    public static final int USERID = 1;
    public static final int NOMBREEMPLEADO = 2;
    public static final int CLAVEEMPLEADO = 3;
    public static final int RFC = 4;
    public static final int FECHATERMINOCONTRATO = 5;
    public static final int ADSCRIPCION = 6;
    public static final int ESTACION = 7;
    public static final int CENTROCOSTOS = 8;
    public static final int CATEGORIA = 9;
    public static final int CUENTA = 10;
    public static final int PAYROLLID = 11;
    public static final int PAYBASISID = 12;
    public static final int GRADEID = 13;
    public static final int POSITIONID = 14;
    public static final int JOBID = 15;

    /**This is the default constructor (do not remove)
     */
    public xXgamInvEmpleadosVORowImpl() {
    }


    public Number getPersonId() {
        return (Number)this.getAttributeInternal(0);
    }

    public void setPersonId(Number value) {
        this.setAttributeInternal(0, value);
    }

    public Number getUserId() {
        return (Number)this.getAttributeInternal(1);
    }

    public void setUserId(Number value) {
        this.setAttributeInternal(1, value);
    }

    public String getNombreEmpleado() {
        return (String)this.getAttributeInternal(2);
    }

    public void setNombreEmpleado(String value) {
        this.setAttributeInternal(2, value);
    }

    public String getClaveEmpleado() {
        return (String)this.getAttributeInternal(3);
    }

    public void setClaveEmpleado(String value) {
        this.setAttributeInternal(3, value);
    }

    public String getRfc() {
        return (String)this.getAttributeInternal(4);
    }

    public void setRfc(String value) {
        this.setAttributeInternal(4, value);
    }

    public Date getFechaTerminoContrato() {
        return (Date)this.getAttributeInternal(5);
    }

    public void setFechaTerminoContrato(Date value) {
        this.setAttributeInternal(5, value);
    }

    public String getAdscripcion() {
        return (String)this.getAttributeInternal(6);
    }

    public void setAdscripcion(String value) {
        this.setAttributeInternal(6, value);
    }

    public String getEstacion() {
        return (String)this.getAttributeInternal(7);
    }

    public void setEstacion(String value) {
        this.setAttributeInternal(7, value);
    }

    public String getCentroCostos() {
        return (String)this.getAttributeInternal(8);
    }

    public void setCentroCostos(String value) {
        this.setAttributeInternal(8, value);
    }

    public String getCategoria() {
        return (String)this.getAttributeInternal(9);
    }

    public void setCategoria(String value) {
        this.setAttributeInternal(9, value);
    }

    public String getCuenta() {
        return (String)this.getAttributeInternal(10);
    }

    public void setCuenta(String value) {
        this.setAttributeInternal(10, value);
    }

    public Number getPayrollId() {
        return (Number)this.getAttributeInternal(11);
    }

    public void setPayrollId(Number value) {
        this.setAttributeInternal(11, value);
    }

    public Number getPayBasisId() {
        return (Number)this.getAttributeInternal(12);
    }

    public void setPayBasisId(Number value) {
        this.setAttributeInternal(12, value);
    }

    public Number getGradeId() {
        return (Number)this.getAttributeInternal(13);
    }

    public void setGradeId(Number value) {
        this.setAttributeInternal(13, value);
    }

    public Number getPositionId() {
        return (Number)this.getAttributeInternal(14);
    }

    public void setPositionId(Number value) {
        this.setAttributeInternal(14, value);
    }

    public Number getJobId() {
        return (Number)this.getAttributeInternal(15);
    }

    public void setJobId(Number value) {
        this.setAttributeInternal(15, value);
    }

    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PERSONID:
            return getPersonId();
        case USERID:
            return getUserId();
        case NOMBREEMPLEADO:
            return getNombreEmpleado();
        case CLAVEEMPLEADO:
            return getClaveEmpleado();
        case RFC:
            return getRfc();
        case FECHATERMINOCONTRATO:
            return getFechaTerminoContrato();
        case ADSCRIPCION:
            return getAdscripcion();
        case ESTACION:
            return getEstacion();
        case CENTROCOSTOS:
            return getCentroCostos();
        case CATEGORIA:
            return getCategoria();
        case CUENTA:
            return getCuenta();
        case PAYROLLID:
            return getPayrollId();
        case PAYBASISID:
            return getPayBasisId();
        case GRADEID:
            return getGradeId();
        case POSITIONID:
            return getPositionId();
        case JOBID:
            return getJobId();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}
