package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;


public class xXGamInvFechaCreacionEnPeriodoVORowImpl extends OAViewRowImpl {

    public static final int SOLIID = 0;
    public static final int NROSOLICITUD = 1;
    public static final int PERSONID = 2;
    public static final int FECHACREACION = 3;
    public static final int FECHAINICIO = 4;
    public static final int FECHAFIN = 5;
    public static final int ESVALIDO = 6;

    /**This is the default constructor (do not remove)
     */
    public xXGamInvFechaCreacionEnPeriodoVORowImpl() {
    }


    public Number getSoliId() {
        return (Number)this.getAttributeInternal(0);
    }

    public void setSoliId(Number value) {
        this.setAttributeInternal(0, value);
    }

    public String getNroSolicitud() {
        return (String)this.getAttributeInternal(1);
    }

    public void setNroSolicitud(String value) {
        this.setAttributeInternal(1, value);
    }

    public Number getPersonId() {
        return (Number)this.getAttributeInternal(2);
    }

    public void setPersonId(Number value) {
        this.setAttributeInternal(2, value);
    }

    public Date getFechaCreacion() {
        return (Date)this.getAttributeInternal(3);
    }

    public void setFechaCreacion(Date value) {
        this.setAttributeInternal(3, value);
    }

    public Date getFechaInicio() {
        return (Date)this.getAttributeInternal(4);
    }

    public void setFechaInicio(Date value) {
        this.setAttributeInternal(4, value);
    }

    public Date getFechaFin() {
        return (Date)this.getAttributeInternal(5);
    }

    public void setFechaFin(Date value) {
        this.setAttributeInternal(5, value);
    }

    public String getEsValido() {
        return (String)this.getAttributeInternal(6);
    }

    public void setEsValido(String value) {
        this.setAttributeInternal(6, value);
    }

    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case SOLIID:
            return getSoliId();
        case NROSOLICITUD:
            return getNroSolicitud();
        case PERSONID:
            return getPersonId();
        case FECHACREACION:
            return getFechaCreacion();
        case FECHAINICIO:
            return getFechaInicio();
        case FECHAFIN:
            return getFechaFin();
        case ESVALIDO:
            return getEsValido();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case SOLIID:
            setSoliId((Number)value);
            return;
        case NROSOLICITUD:
            setNroSolicitud((String)value);
            return;
        case PERSONID:
            setPersonId((Number)value);
            return;
        case FECHACREACION:
            setFechaCreacion((Date)value);
            return;
        case FECHAINICIO:
            setFechaInicio((Date)value);
            return;
        case FECHAFIN:
            setFechaFin((Date)value);
            return;
        case ESVALIDO:
            setEsValido((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}
