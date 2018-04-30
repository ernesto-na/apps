package xxgam.oracle.apps.xbol.maf.server;

import oracle.apps.fnd.framework.server.OAEntityDefImpl;
import oracle.apps.fnd.framework.server.OAEntityImpl;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;

import oracle.jbo.server.TransactionEvent;

import xxgam.oracle.apps.xbol.maf.utils.XxGamConstantsUtil;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaPaymentReqEOImpl extends OAEntityImpl {
    public static final int ID = 0;
    public static final int GENERALREQID = 1;
    public static final int INITIALDATE = 2;
    public static final int FINALDATE = 3;
    public static final int TYPEPAYMENT = 4;
    public static final int AMOUNT = 5;
    public static final int AMOUNTMX = 6;
    public static final int JUSTIFICATION = 7;
    public static final int CODECOMBINATIONID = 8;
    public static final int CURRENCYCODE = 9;
    public static final int CREATIONDATE = 10;
    public static final int CREATEDBY = 11;
    public static final int LASTUPDATEDATE = 12;
    public static final int LASTUPDATELOGIN = 13;
    public static final int LASTUPDATEDBY = 14;
    public static final int XXGAMMATICKETPEO = 15;
    public static final int XXGAMMAGENERALREQEO = 16;
    public static final int XXGAMMAGENERALREQEO1 = 17;
    public static final int XXGAMMATICKETPEO1 = 18;


    private static OAEntityDefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public XxGamMaPaymentReqEOImpl() {
    }


    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("xxgam.oracle.apps.xbol.maf.server.XxGamMaPaymentReqEO");
        }
        return mDefinitionObject;
    }

    public void postChanges(TransactionEvent transactionEvent) {
            XxGamMaGeneralReqEOImpl generalReq = getXxGamMaGeneralReqEO();
            if (generalReq != null && (generalReq.getPostState() == STATUS_NEW || generalReq.getPostState() == STATUS_MODIFIED)){
                generalReq.postChanges(transactionEvent);
            }
            super.postChanges(transactionEvent);
    }
    
    protected void validateEntity() { 

        String isValidate = (String)getOADBTransaction().getTransientValue("IsValidateEntityDetail");
        if(isValidate == null || !"false".equals(isValidate)){
            super.validateEntity();
        }
    }


    /**Gets the attribute value for Id, using the alias name Id
     */
    public Number getId() {
        return (Number)getAttributeInternal(ID);
    }

    /**Sets <code>value</code> as the attribute value for Id
     */
    public void setId(Number value) {
        setAttributeInternal(ID, value);
    }

    /**Gets the attribute value for GeneralReqId, using the alias name GeneralReqId
     */
    public Number getGeneralReqId() {
        return (Number)getAttributeInternal(GENERALREQID);
    }

    /**Sets <code>value</code> as the attribute value for GeneralReqId
     */
    public void setGeneralReqId(Number value) {
        setAttributeInternal(GENERALREQID, value);
    }

    /**Gets the attribute value for InitialDate, using the alias name InitialDate
     */
    public Date getInitialDate() {
        return (Date)getAttributeInternal(INITIALDATE);
    }

    /**Sets <code>value</code> as the attribute value for InitialDate
     */
    public void setInitialDate(Date value) {
        setAttributeInternal(INITIALDATE, value);
    }

    /**Gets the attribute value for FinalDate, using the alias name FinalDate
     */
    public Date getFinalDate() {
        return (Date)getAttributeInternal(FINALDATE);
    }

    /**Sets <code>value</code> as the attribute value for FinalDate
     */
    public void setFinalDate(Date value) {
        setAttributeInternal(FINALDATE, value);
    }

    /**Gets the attribute value for TypePayment, using the alias name TypePayment
     */
    public Number getTypePayment() {
        return (Number)getAttributeInternal(TYPEPAYMENT);
    }

    /**Sets <code>value</code> as the attribute value for TypePayment
     */
    public void setTypePayment(Number value) {
        setAttributeInternal(TYPEPAYMENT, value);
    }

    /**Gets the attribute value for Amount, using the alias name Amount
     */
    public Number getAmount() {
        return (Number)getAttributeInternal(AMOUNT);
    }

    /**Sets <code>value</code> as the attribute value for Amount
     */
    public void setAmount(Number value) {
        setAttributeInternal(AMOUNT, value);
    }

    /**Gets the attribute value for AmountMx, using the alias name AmountMx
     */
    public Number getAmountMx() {
        return (Number)getAttributeInternal(AMOUNTMX);
    }

    /**Sets <code>value</code> as the attribute value for AmountMx
     */
    public void setAmountMx(Number value) {
        setAttributeInternal(AMOUNTMX, value);
    }

    /**Gets the attribute value for Justification, using the alias name Justification
     */
    public String getJustification() {
        return (String)getAttributeInternal(JUSTIFICATION);
    }

    /**Sets <code>value</code> as the attribute value for Justification
     */
    public void setJustification(String value) {
        setAttributeInternal(JUSTIFICATION, value);
    }

    /**Gets the attribute value for CodeCombinationId, using the alias name CodeCombinationId
     */
    public Number getCodeCombinationId() {
        return (Number)getAttributeInternal(CODECOMBINATIONID);
    }

    /**Sets <code>value</code> as the attribute value for CodeCombinationId
     */
    public void setCodeCombinationId(Number value) {
        setAttributeInternal(CODECOMBINATIONID, value);
    }

    /**Gets the attribute value for CurrencyCode, using the alias name CurrencyCode
     */
    public String getCurrencyCode() {
        return (String)getAttributeInternal(CURRENCYCODE);
    }

    /**Sets <code>value</code> as the attribute value for CurrencyCode
     */
    public void setCurrencyCode(String value) {
        setAttributeInternal(CURRENCYCODE, value);
    }

    /**Gets the attribute value for CreationDate, using the alias name CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**Sets <code>value</code> as the attribute value for CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**Gets the attribute value for CreatedBy, using the alias name CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**Gets the attribute value for LastUpdateDate, using the alias name LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**Sets <code>value</code> as the attribute value for LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }


    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index,
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case ID:
            return getId();
        case GENERALREQID:
            return getGeneralReqId();
        case INITIALDATE:
            return getInitialDate();
        case FINALDATE:
            return getFinalDate();
        case TYPEPAYMENT:
            return getTypePayment();
        case AMOUNT:
            return getAmount();
        case AMOUNTMX:
            return getAmountMx();
        case JUSTIFICATION:
            return getJustification();
        case CODECOMBINATIONID:
            return getCodeCombinationId();
        case CURRENCYCODE:
            return getCurrencyCode();
        case CREATIONDATE:
            return getCreationDate();
        case CREATEDBY:
            return getCreatedBy();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case XXGAMMATICKETPEO1:
            return getXxGamMaTicketPEO1();
        case XXGAMMATICKETPEO:
            return getXxGamMaTicketPEO();
        case XXGAMMAGENERALREQEO1:
            return getXxGamMaGeneralReqEO1();
        case XXGAMMAGENERALREQEO:
            return getXxGamMaGeneralReqEO();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value,
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case ID:
            setId((Number)value);
            return;
        case GENERALREQID:
            setGeneralReqId((Number)value);
            return;
        case INITIALDATE:
            setInitialDate((Date)value);
            return;
        case FINALDATE:
            setFinalDate((Date)value);
            return;
        case TYPEPAYMENT:
            setTypePayment((Number)value);
            return;
        case AMOUNT:
            setAmount((Number)value);
            return;
        case AMOUNTMX:
            setAmountMx((Number)value);
            return;
        case JUSTIFICATION:
            setJustification((String)value);
            return;
        case CODECOMBINATIONID:
            setCodeCombinationId((Number)value);
            return;
        case CURRENCYCODE:
            setCurrencyCode((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }


    public void setLastUpdatedBy(Number number) {
    }

    /**Add attribute defaulting logic in this method.
     */
    public void create(AttributeList attributeList) {


        Number PaymentId = null;
        PaymentId =
                getOADBTransaction().getSequenceValue(XxGamConstantsUtil.XXGAM_MA_PAYMENT_REQ_S);

        //Inicializa la clave primaria
        setId(PaymentId);

        super.create(attributeList);
    }

    /**Gets the associated entity XxGamMaGeneralReqEOImpl
     */
    public XxGamMaGeneralReqEOImpl getXxGamMaGeneralReqEO1() {
        return (XxGamMaGeneralReqEOImpl)getAttributeInternal(XXGAMMAGENERALREQEO1);
    }

    /**Sets <code>value</code> as the associated entity XxGamMaGeneralReqEOImpl
     */
    public void setXxGamMaGeneralReqEO1(XxGamMaGeneralReqEOImpl value) {
        setAttributeInternal(XXGAMMAGENERALREQEO1, value);
    }

    /**Gets the attribute value for LastUpdateLogin, using the alias name LastUpdateLogin
     */
    public Number getLastUpdateLogin() {
        return (Number)getAttributeInternal(LASTUPDATELOGIN);
    }


    public void setLastUpdateLogin(Number number) {
    }

    /**Gets the associated entity XxGamMaGeneralReqEOImpl
     */
    public XxGamMaGeneralReqEOImpl getXxGamMaGeneralReqEO() {
        return (XxGamMaGeneralReqEOImpl)getAttributeInternal(XXGAMMAGENERALREQEO);
    }

    /**Sets <code>value</code> as the associated entity XxGamMaGeneralReqEOImpl
     */
    public void setXxGamMaGeneralReqEO(XxGamMaGeneralReqEOImpl value) {
        setAttributeInternal(XXGAMMAGENERALREQEO, value);
    }


    /**Gets the associated entity oracle.jbo.RowIterator
     */
    public RowIterator getXxGamMaTicketPEO1() {
        return (RowIterator)getAttributeInternal(XXGAMMATICKETPEO1);
    }

    /**Gets the attribute value for LastUpdatedBy, using the alias name LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**Gets the associated entity oracle.jbo.RowIterator
     */
    public RowIterator getXxGamMaTicketPEO() {
        return (RowIterator)getAttributeInternal(XXGAMMATICKETPEO);
    }


    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number id) {
        return new Key(new Object[]{id});
    }
}