package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.common.MessageToken;
import oracle.apps.fnd.framework.OARowValException;
import oracle.apps.fnd.framework.server.OAEntityDefImpl;
import oracle.apps.fnd.framework.server.OAEntityImpl;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.TransactionEvent;


public class xXGamInvSoliDtlEOImpl extends OAEntityImpl {

    public static final int SOLIID = 0;
    public static final int SOLIDTLID = 1;
    public static final int DOTAID = 2;
    public static final int TALLAID = 3;
    public static final int QTYNBR = 4;
    public static final int LASTDELIVDATE = 5;
    public static final int LINENUMBER = 6;
    public static final int STATUS = 7;
    public static final int QTYPLANTA = 8;
    public static final int QTYEVENTUAL = 9;
    public static final int MONTHDOTA = 10;
    public static final int LINEID = 11;
    public static final int MESSAGEERROR = 12;
    public static final int DOTAIDF = 13;
    public static final int ATTRIBUTE1 = 14;
    public static final int ATTRIBUTE2 = 15;
    public static final int ATTRIBUTE3 = 16;
    public static final int ATTRIBUTE4 = 17;
    public static final int ATTRIBUTE5 = 18;
    public static final int ATTRIBUTE6 = 19;
    public static final int ATTRIBUTE7 = 20;
    public static final int ATTRIBUTE8 = 21;
    public static final int ATTRIBUTE9 = 22;
    public static final int ATTRIBUTE10 = 23;
    public static final int CREATEDBY = 24;
    public static final int CREATIONDATE = 25;
    public static final int LASTUPDATEDBY = 26;
    public static final int LASTUPDATEDATE = 27;
    public static final int LASTUPDATELOGIN = 28;
    public static final int OBSERVACIONESSLTUD = 29;
    public static final int MOTIVO = 30;
    public static final int TALLADEVID = 31;
    public static final int QTYDEV = 32;
    public static final int OBSERVACIONESDEV = 33;
    public static final int XXGAMINVSOLIEO = 34;


    private static OAEntityDefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public xXGamInvSoliDtlEOImpl() {
    }


    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliDtlEO");
        }
        return mDefinitionObject;
    }

    public Number getSoliId() {
        return (Number)this.getAttributeInternal(0);
    }

    public void setSoliId(Number value) {
        this.setAttributeInternal(0, value);
    }

    public Number getSoliDtlId() {
        return (Number)this.getAttributeInternal(1);
    }

    public void setSoliDtlId(Number value) {
        this.setAttributeInternal(1, value);
    }

    public Number getDotaId() {
        return (Number)this.getAttributeInternal(2);
    }

    public void setDotaId(Number value) {
        this.setAttributeInternal(2, value);
    }

    public Number getTallaId() {
        return (Number)this.getAttributeInternal(3);
    }

    public void setTallaId(Number value) {
        this.setAttributeInternal(3, value);
    }

    public Number getQtyNbr() {
        return (Number)this.getAttributeInternal(4);
    }

    public void setQtyNbr(Number value) {
        this.setAttributeInternal(4, value);
    }

    public Date getLastDelivDate() {
        return (Date)this.getAttributeInternal(5);
    }

    public void setLastDelivDate(Date value) {
        this.setAttributeInternal(5, value);
    }

    public Number getLineNumber() {
        return (Number)this.getAttributeInternal(6);
    }

    public void setLineNumber(Number value) {
        this.setAttributeInternal(6, value);
    }

    public String getStatus() {
        return (String)this.getAttributeInternal(7);
    }

    public void setStatus(String value) {
        this.setAttributeInternal(7, value);
    }

    public Number getQtyPlanta() {
        return (Number)this.getAttributeInternal(8);
    }

    public void setQtyPlanta(Number value) {
        this.setAttributeInternal(8, value);
    }

    public Number getQtyEventual() {
        return (Number)this.getAttributeInternal(9);
    }

    public void setQtyEventual(Number value) {
        this.setAttributeInternal(9, value);
    }

    public String getMonthDota() {
        return (String)this.getAttributeInternal(10);
    }

    public void setMonthDota(String value) {
        this.setAttributeInternal(10, value);
    }

    public Number getLineId() {
        return (Number)this.getAttributeInternal(11);
    }

    public void setLineId(Number value) {
        this.setAttributeInternal(11, value);
    }

    public String getMessageError() {
        return (String)this.getAttributeInternal(12);
    }

    public void setMessageError(String value) {
        this.setAttributeInternal(12, value);
    }

    public Number getDotaIdF() {
        return (Number)this.getAttributeInternal(13);
    }

    public void setDotaIdF(Number value) {
        this.setAttributeInternal(13, value);
    }

    public String getAttribute1() {
        return (String)this.getAttributeInternal(14);
    }

    public void setAttribute1(String value) {
        this.setAttributeInternal(14, value);
    }

    public String getAttribute2() {
        return (String)this.getAttributeInternal(15);
    }

    public void setAttribute2(String value) {
        this.setAttributeInternal(15, value);
    }

    public String getAttribute3() {
        return (String)this.getAttributeInternal(16);
    }

    public void setAttribute3(String value) {
        this.setAttributeInternal(16, value);
    }

    public String getAttribute4() {
        return (String)this.getAttributeInternal(17);
    }

    public void setAttribute4(String value) {
        this.setAttributeInternal(17, value);
    }

    public String getAttribute5() {
        return (String)this.getAttributeInternal(18);
    }

    public void setAttribute5(String value) {
        this.setAttributeInternal(18, value);
    }

    public String getAttribute6() {
        return (String)this.getAttributeInternal(19);
    }

    public void setAttribute6(String value) {
        this.setAttributeInternal(19, value);
    }

    public String getAttribute7() {
        return (String)this.getAttributeInternal(20);
    }

    public void setAttribute7(String value) {
        this.setAttributeInternal(20, value);
    }

    public String getAttribute8() {
        return (String)this.getAttributeInternal(21);
    }

    public void setAttribute8(String value) {
        this.setAttributeInternal(21, value);
    }

    public String getAttribute9() {
        return (String)this.getAttributeInternal(22);
    }

    public void setAttribute9(String value) {
        this.setAttributeInternal(22, value);
    }

    public String getAttribute10() {
        return (String)this.getAttributeInternal(23);
    }

    public void setAttribute10(String value) {
        this.setAttributeInternal(23, value);
    }

    public Number getCreatedBy() {
        return (Number)this.getAttributeInternal(24);
    }

    public void setCreatedBy(Number value) {
        this.setAttributeInternal(24, value);
    }

    public Date getCreationDate() {
        return (Date)this.getAttributeInternal(25);
    }

    public void setCreationDate(Date value) {
        this.setAttributeInternal(25, value);
    }

    public Number getLastUpdatedBy() {
        return (Number)this.getAttributeInternal(26);
    }

    public void setLastUpdatedBy(Number value) {
        this.setAttributeInternal(26, value);
    }

    public Date getLastUpdateDate() {
        return (Date)this.getAttributeInternal(27);
    }

    public void setLastUpdateDate(Date value) {
        this.setAttributeInternal(27, value);
    }

    public Number getLastUpdateLogin() {
        return (Number)this.getAttributeInternal(28);
    }

    public void setLastUpdateLogin(Number value) {
        this.setAttributeInternal(28, value);
    }

    public String getObservacionesSltud() {
        return (String)this.getAttributeInternal(29);
    }

    public void setObservacionesSltud(String value) {
        this.setAttributeInternal(29, value);
    }

    public String getMotivo() {
        return (String)this.getAttributeInternal(30);
    }

    public void setMotivo(String value) {
        this.setAttributeInternal(30, value);
    }

    public Number getTallaDevId() {
        return (Number)this.getAttributeInternal(31);
    }

    public void setTallaDevId(Number value) {
        this.setAttributeInternal(31, value);
    }

    public Number getQtyDev() {
        return (Number)this.getAttributeInternal(32);
    }

    public void setQtyDev(Number value) {
        this.setAttributeInternal(32, value);
    }

    public String getObservacionesDev() {
        return (String)this.getAttributeInternal(33);
    }

    public void setObservacionesDev(String value) {
        this.setAttributeInternal(33, value);
    }

    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case SOLIID:
            return getSoliId();
        case SOLIDTLID:
            return getSoliDtlId();
        case DOTAID:
            return getDotaId();
        case TALLAID:
            return getTallaId();
        case QTYNBR:
            return getQtyNbr();
        case LASTDELIVDATE:
            return getLastDelivDate();
        case LINENUMBER:
            return getLineNumber();
        case STATUS:
            return getStatus();
        case QTYPLANTA:
            return getQtyPlanta();
        case QTYEVENTUAL:
            return getQtyEventual();
        case MONTHDOTA:
            return getMonthDota();
        case LINEID:
            return getLineId();
        case MESSAGEERROR:
            return getMessageError();
        case DOTAIDF:
            return getDotaIdF();
        case ATTRIBUTE1:
            return getAttribute1();
        case ATTRIBUTE2:
            return getAttribute2();
        case ATTRIBUTE3:
            return getAttribute3();
        case ATTRIBUTE4:
            return getAttribute4();
        case ATTRIBUTE5:
            return getAttribute5();
        case ATTRIBUTE6:
            return getAttribute6();
        case ATTRIBUTE7:
            return getAttribute7();
        case ATTRIBUTE8:
            return getAttribute8();
        case ATTRIBUTE9:
            return getAttribute9();
        case ATTRIBUTE10:
            return getAttribute10();
        case CREATEDBY:
            return getCreatedBy();
        case CREATIONDATE:
            return getCreationDate();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case OBSERVACIONESSLTUD:
            return getObservacionesSltud();
        case MOTIVO:
            return getMotivo();
        case TALLADEVID:
            return getTallaDevId();
        case QTYDEV:
            return getQtyDev();
        case OBSERVACIONESDEV:
            return getObservacionesDev();
        case XXGAMINVSOLIEO:
            return getXXGamInvSoliEO();
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
        case SOLIDTLID:
            setSoliDtlId((Number)value);
            return;
        case DOTAID:
            setDotaId((Number)value);
            return;
        case TALLAID:
            setTallaId((Number)value);
            return;
        case QTYNBR:
            setQtyNbr((Number)value);
            return;
        case LASTDELIVDATE:
            setLastDelivDate((Date)value);
            return;
        case LINENUMBER:
            setLineNumber((Number)value);
            return;
        case STATUS:
            setStatus((String)value);
            return;
        case QTYPLANTA:
            setQtyPlanta((Number)value);
            return;
        case QTYEVENTUAL:
            setQtyEventual((Number)value);
            return;
        case MONTHDOTA:
            setMonthDota((String)value);
            return;
        case LINEID:
            setLineId((Number)value);
            return;
        case MESSAGEERROR:
            setMessageError((String)value);
            return;
        case DOTAIDF:
            setDotaIdF((Number)value);
            return;
        case ATTRIBUTE1:
            setAttribute1((String)value);
            return;
        case ATTRIBUTE2:
            setAttribute2((String)value);
            return;
        case ATTRIBUTE3:
            setAttribute3((String)value);
            return;
        case ATTRIBUTE4:
            setAttribute4((String)value);
            return;
        case ATTRIBUTE5:
            setAttribute5((String)value);
            return;
        case ATTRIBUTE6:
            setAttribute6((String)value);
            return;
        case ATTRIBUTE7:
            setAttribute7((String)value);
            return;
        case ATTRIBUTE8:
            setAttribute8((String)value);
            return;
        case ATTRIBUTE9:
            setAttribute9((String)value);
            return;
        case ATTRIBUTE10:
            setAttribute10((String)value);
            return;
        case CREATEDBY:
            setCreatedBy((Number)value);
            return;
        case CREATIONDATE:
            setCreationDate((Date)value);
            return;
        case LASTUPDATEDBY:
            setLastUpdatedBy((Number)value);
            return;
        case LASTUPDATEDATE:
            setLastUpdateDate((Date)value);
            return;
        case LASTUPDATELOGIN:
            setLastUpdateLogin((Number)value);
            return;
        case OBSERVACIONESSLTUD:
            setObservacionesSltud((String)value);
            return;
        case MOTIVO:
            setMotivo((String)value);
            return;
        case TALLADEVID:
            setTallaDevId((Number)value);
            return;
        case QTYDEV:
            setQtyDev((Number)value);
            return;
        case OBSERVACIONESDEV:
            setObservacionesDev((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    public void create(AttributeList attributeList) {
        super.create(attributeList);
    }

    public void remove() {
        super.remove();
    }

    protected void validateEntity() {
        super.validateEntity();

        if (null == this.getAttribute2() || "".equals(this.getAttribute2())) {
            MessageToken[] tokens5 = 
                new MessageToken[] { new MessageToken("MESSAGE", 
                                                      "El attributo2 esta vacio.") };
            throw new OARowValException(121, this.getEntityDef().getFullName(), 
                                        this.getPrimaryKey(), "FND", 
                                        "FND_GENERIC_MESSAGE", tokens5);
        }

        if (this.getQtyNbr() != null) {
            Long lCntIngresada = Long.valueOf(this.getQtyNbr().longValue());
            /************************** Se excluye validacion por aquellos kits con cantidades en 0 ******
         if(this.getQtyNbr().compareTo(0) == 0) {
            MessageToken[] tokens = new MessageToken[]{new MessageToken("MESSAGE", "La cantidad ingresada no debe ser cero.")};
            throw new OARowValException(121, this.getEntityDef().getFullName(), this.getPrimaryKey(), "FND", "FND_GENERIC_MESSAGE", tokens);
         }
         *****************************************************************************************/

            if (this.getTallaId() == null) {
                MessageToken[] tokens1 = 
                    new MessageToken[] { new MessageToken("MESSAGE", 
                                                          "Si ingresa la Cantidad, debe seleccionar la Talla.") };
                throw new OARowValException(121, 
                                            this.getEntityDef().getFullName(), 
                                            this.getPrimaryKey(), "FND", 
                                            "FND_GENERIC_MESSAGE", tokens1);
            }

            if (lCntIngresada != null) {
                Long lpermitida = Long.valueOf(this.getAttribute2());
                if (this.getAttribute3() != null) {
                    Long lTopeSust = Long.valueOf(this.getAttribute3());
                    if (lTopeSust.compareTo(lCntIngresada) < 0) {
                        MessageToken[] tokens2 = 
                            new MessageToken[] { new MessageToken("MESSAGE", 
                                                                  "La cantidad ingresada " + 
                                                                  lCntIngresada.toString() + 
                                                                  " no puede mayor que la permitida por sustitucion " + 
                                                                  lTopeSust.toString() + 
                                                                  ".") };
                        throw new OARowValException(121, 
                                                    this.getEntityDef().getFullName(), 
                                                    this.getPrimaryKey(), 
                                                    "FND", 
                                                    "FND_GENERIC_MESSAGE", 
                                                    tokens2);
                    }
                } else if (lpermitida.compareTo(lCntIngresada) < 0) {
                    MessageToken[] tokens3 = 
                        new MessageToken[] { new MessageToken("MESSAGE", 
                                                              "La cantidad ingresada " + 
                                                              lCntIngresada.toString() + 
                                                              " no puede mayor que la permitida " + 
                                                              lpermitida.toString() + 
                                                              ".") };
                    throw new OARowValException(121, 
                                                this.getEntityDef().getFullName(), 
                                                this.getPrimaryKey(), "FND", 
                                                "FND_GENERIC_MESSAGE", 
                                                tokens3);
                }
            }
        } else if (this.getTallaId() != null) {
            MessageToken[] tokens4 = 
                new MessageToken[] { new MessageToken("MESSAGE", 
                                                      "Si ingresa la Talla, debe seleccionar la Cantidad.") };
            throw new OARowValException(121, this.getEntityDef().getFullName(), 
                                        this.getPrimaryKey(), "FND", 
                                        "FND_GENERIC_MESSAGE", tokens4);
        }

    }

    public void lock() {
        super.lock();
    }

    protected void doDML(int operation, TransactionEvent e) {
        super.doDML(operation, e);
    }

    /**Gets the associated entity xXGamInvSoliEOImpl
     */
    public xXGamInvSoliEOImpl getXXGamInvSoliEO() {
        return (xXGamInvSoliEOImpl)getAttributeInternal(XXGAMINVSOLIEO);
    }

    /**Sets <code>value</code> as the associated entity xXGamInvSoliEOImpl
     */
    public void setXXGamInvSoliEO(xXGamInvSoliEOImpl value) {
        setAttributeInternal(XXGAMINVSOLIEO, value);
    }

    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number soliDtlId) {
        return new Key(new Object[] { soliDtlId });
    }
}
