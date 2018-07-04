package xxgam.oracle.apps.xbol.maf.server;

import oracle.apps.fnd.framework.OAException;
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
public class XxGamMaTicketPEOImpl extends OAEntityImpl {
    public static final int ID = 0;
    public static final int PAYMENTREQID = 1;
    public static final int PNR = 2;
    public static final int TICKETOFFICE = 3;
    public static final int CONTACTPHONE = 4;
    public static final int EMAIL = 5;
    public static final int TYPEEMISSION = 6;
    public static final int FOLIO = 7;
    public static final int CREATEDBY = 8;
    public static final int CREATIONDATE = 9;
    public static final int LASTUPDATEDATE = 10;
    public static final int LASTUPDATELOGIN = 11;
    public static final int GENERALREQID = 12;
    public static final int BENEFICIARY = 13;
    public static final int RELATIONSHIP = 14;
    public static final int BENEFITS = 15;
    public static final int COMPANY = 16;
    public static final int LASTUPDATEDBY = 17;
    public static final int TICKETCLASS = 18;
    public static final int TICKETRATE = 19;
    public static final int TICKETAVAILABLE = 20;
    public static final int TICKETEMBARGOED = 21;
    public static final int CLASSPASS = 22;
    public static final int DISCOUNTRATE = 23;
    public static final int FILIAL = 24;
    public static final int AMOUNTBASE = 25;
    public static final int AMOUNTDISCOUNT = 26;
    public static final int TAXDISCOUNT = 27;
    public static final int XXGAMMAPAYMENTREQEO = 28;
    public static final int XXGAMMAFLIGHTINF0EO = 29;
    public static final int XXGAMMAPAYMENTREQEO1 = 30;
    public static final int XXGAMMAGENERALREQEO = 31;
    public static final int XXGAMMAPASAJEROSINFOEO = 32;


    private static OAEntityDefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public XxGamMaTicketPEOImpl() {
    }


    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("xxgam.oracle.apps.xbol.maf.server.XxGamMaTicketPEO");
        }
        return mDefinitionObject;
    }

    public void postChanges(TransactionEvent transactionEvent) {
        XxGamMaGeneralReqEOImpl generalReq = getXxGamMaGeneralReqEO();
        if (generalReq != null && 
            (generalReq.getPostState() == STATUS_NEW || generalReq.getPostState() == 
             STATUS_MODIFIED)) {
            generalReq.postChanges(transactionEvent);
        } else {
            XxGamMaPaymentReqEOImpl detailReq = getXxGamMaPaymentReqEO();
            if (detailReq != null && 
                (detailReq.getPostState() == STATUS_NEW || detailReq.getPostState() == 
                 STATUS_MODIFIED)) {
                detailReq.postChanges(transactionEvent);
            }
        }
        super.postChanges(transactionEvent);
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

    /**Gets the attribute value for PaymentReqId, using the alias name PaymentReqId
     */
    public Number getPaymentReqId() {
        return (Number)getAttributeInternal(PAYMENTREQID);
    }

    /**Sets <code>value</code> as the attribute value for PaymentReqId
     */
    public void setPaymentReqId(Number value) {
        setAttributeInternal(PAYMENTREQID, value);
    }

    /**Gets the attribute value for Pnr, using the alias name Pnr
     */
    public String getPnr() {
        return (String)getAttributeInternal(PNR);
    }

    /**Sets <code>value</code> as the attribute value for Pnr
     */
    public void setPnr(String value) {
        setAttributeInternal(PNR, value);
    }

    /**Gets the attribute value for TicketOffice, using the alias name TicketOffice
     */
    public String getTicketOffice() {
        return (String)getAttributeInternal(TICKETOFFICE);
    }

    /**Sets <code>value</code> as the attribute value for TicketOffice
     */
    public void setTicketOffice(String value) {
        setAttributeInternal(TICKETOFFICE, value);
    }

    /**Gets the attribute value for ContactPhone, using the alias name ContactPhone
     */
    public String getContactPhone() {
        return (String)getAttributeInternal(CONTACTPHONE);
    }

    /**Sets <code>value</code> as the attribute value for ContactPhone
     */
    public void setContactPhone(String value) {
        setAttributeInternal(CONTACTPHONE, value);
    }

    /**Gets the attribute value for Email, using the alias name Email
     */
    public String getEmail() {
        return (String)getAttributeInternal(EMAIL);
    }

    /**Sets <code>value</code> as the attribute value for Email
     */
    public void setEmail(String value) {
        setAttributeInternal(EMAIL, value);
    }

    /**Gets the attribute value for TypeEmission, using the alias name TypeEmission
     */
    public String getTypeEmission() {
        return (String)getAttributeInternal(TYPEEMISSION);
    }

    /**Sets <code>value</code> as the attribute value for TypeEmission
     */
    public void setTypeEmission(String value) {
        setAttributeInternal(TYPEEMISSION, value);
    }

    /**Gets the attribute value for Folio, using the alias name Folio
     */
    public String getFolio() {
        return (String)getAttributeInternal(FOLIO);
    }

    /**Sets <code>value</code> as the attribute value for Folio
     */
    public void setFolio(String value) {
        setAttributeInternal(FOLIO, value);
    }

    /**Gets the attribute value for CreatedBy, using the alias name CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**Gets the attribute value for CreationDate, using the alias name CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**Gets the attribute value for LastUpdateDate, using the alias name LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**Gets the attribute value for LastUpdateLogin, using the alias name LastUpdateLogin
     */
    public Number getLastUpdateLogin() {
        return (Number)getAttributeInternal(LASTUPDATELOGIN);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case ID:
            return getId();
        case PAYMENTREQID:
            return getPaymentReqId();
        case PNR:
            return getPnr();
        case TICKETOFFICE:
            return getTicketOffice();
        case CONTACTPHONE:
            return getContactPhone();
        case EMAIL:
            return getEmail();
        case TYPEEMISSION:
            return getTypeEmission();
        case FOLIO:
            return getFolio();
        case CREATEDBY:
            return getCreatedBy();
        case CREATIONDATE:
            return getCreationDate();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case GENERALREQID:
            return getGeneralReqId();
        case BENEFICIARY:
            return getBeneficiary();
        case RELATIONSHIP:
            return getRelationship();
        case BENEFITS:
            return getBenefits();
        case COMPANY:
            return getCompany();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case TICKETCLASS:
            return getTicketClass();
        case TICKETRATE:
            return getTicketRate();
        case TICKETAVAILABLE:
            return getTicketAvailable();
        case TICKETEMBARGOED:
            return getTicketEmbargoed();
        case CLASSPASS:
            return getClassPass();
        case DISCOUNTRATE:
            return getDiscountRate();
        case FILIAL:
            return getFilial();
        case AMOUNTBASE:
            return getAmountBase();
        case AMOUNTDISCOUNT:
            return getAmountDiscount();
        case TAXDISCOUNT:
            return getTaxDiscount();
        case XXGAMMAFLIGHTINF0EO:
            return getXxGamMaFlightInf0EO();
        case XXGAMMAPASAJEROSINFOEO:
            return getXxGamMaPasajerosInfoEO();
        case XXGAMMAGENERALREQEO:
            return getXxGamMaGeneralReqEO();
        case XXGAMMAPAYMENTREQEO1:
            return getXxGamMaPaymentReqEO1();
        case XXGAMMAPAYMENTREQEO:
            return getXxGamMaPaymentReqEO();
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
        case PAYMENTREQID:
            setPaymentReqId((Number)value);
            return;
        case PNR:
            setPnr((String)value);
            return;
        case TICKETOFFICE:
            setTicketOffice((String)value);
            return;
        case CONTACTPHONE:
            setContactPhone((String)value);
            return;
        case EMAIL:
            setEmail((String)value);
            return;
        case TYPEEMISSION:
            setTypeEmission((String)value);
            return;
        case FOLIO:
            setFolio((String)value);
            return;
        case GENERALREQID:
            setGeneralReqId((Number)value);
            return;
        case BENEFICIARY:
            setBeneficiary((String)value);
            return;
        case RELATIONSHIP:
            setRelationship((String)value);
            return;
        case BENEFITS:
            setBenefits((String)value);
            return;
        case COMPANY:
            setCompany((String)value);
            return;
        case TICKETCLASS:
            setTicketClass((String)value);
            return;
        case TICKETRATE:
            setTicketRate((String)value);
            return;
        case TICKETAVAILABLE:
            setTicketAvailable((String)value);
            return;
        case TICKETEMBARGOED:
            setTicketEmbargoed((String)value);
            return;
        case CLASSPASS:
            setClassPass((String)value);
            return;
        case DISCOUNTRATE:
            setDiscountRate((String)value);
            return;
        case FILIAL:
            setFilial((String)value);
            return;
        case AMOUNTBASE:
            setAmountBase((Number)value);
            return;
        case AMOUNTDISCOUNT:
            setAmountDiscount((Number)value);
            return;
        case TAXDISCOUNT:
            setTaxDiscount((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the associated entity XxGamMaPaymentReqEOImpl
     */
    public XxGamMaPaymentReqEOImpl getXxGamMaPaymentReqEO() {
        return (XxGamMaPaymentReqEOImpl)getAttributeInternal(XXGAMMAPAYMENTREQEO);
    }

    /**Sets <code>value</code> as the associated entity XxGamMaPaymentReqEOImpl
     */
    public void setXxGamMaPaymentReqEO(XxGamMaPaymentReqEOImpl value) {
        setAttributeInternal(XXGAMMAPAYMENTREQEO, value);
    }


    public void setCreationDate(Date date) {
    }

    public void setCreatedBy(Number number) {
    }

    public void setLastUpdateDate(Date date) {
    }

    public void setLastUpdatedBy(Number number) {
    }

    public void setLastUpdateLogin(Number number) {
    }

    /**Gets the associated entity oracle.jbo.RowIterator
     */
    public RowIterator getXxGamMaFlightInf0EO() {
        return (RowIterator)getAttributeInternal(XXGAMMAFLIGHTINF0EO);
    }

    /**Add attribute defaulting logic in this method.
     */
    public void create(AttributeList attributeList) {
        //Obtiene la clave primaria del registro
        Number id = null;
        String sSecuencia = null;
        sSecuencia = XxGamConstantsUtil.XXGAM_MA_TICKET_P_S;
        id = getOADBTransaction().getSequenceValue(sSecuencia);
        if (id == null)
            throw new OAException("No es posible generar la clave del registro", 
                                  OAException.ERROR);
        setId(id);
        setTypeEmission("PRESENCIAL");
        super.create(attributeList);
    }

    /**Gets the associated entity XxGamMaPaymentReqEOImpl
     */
    public XxGamMaPaymentReqEOImpl getXxGamMaPaymentReqEO1() {
        return (XxGamMaPaymentReqEOImpl)getAttributeInternal(XXGAMMAPAYMENTREQEO1);
    }

    /**Sets <code>value</code> as the associated entity XxGamMaPaymentReqEOImpl
     */
    public void setXxGamMaPaymentReqEO1(XxGamMaPaymentReqEOImpl value) {
        setAttributeInternal(XXGAMMAPAYMENTREQEO1, value);
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

    /**Gets the attribute value for Beneficiary, using the alias name Beneficiary
     */
    public String getBeneficiary() {
        return (String)getAttributeInternal(BENEFICIARY);
    }

    /**Sets <code>value</code> as the attribute value for Beneficiary
     */
    public void setBeneficiary(String value) {
        setAttributeInternal(BENEFICIARY, value);
    }

    /**Gets the attribute value for Relationship, using the alias name Relationship
     */
    public String getRelationship() {
        return (String)getAttributeInternal(RELATIONSHIP);
    }

    /**Sets <code>value</code> as the attribute value for Relationship
     */
    public void setRelationship(String value) {
        setAttributeInternal(RELATIONSHIP, value);
    }

    /**Gets the attribute value for Benefits, using the alias name Benefits
     */
    public String getBenefits() {
        return (String)getAttributeInternal(BENEFITS);
    }

    /**Sets <code>value</code> as the attribute value for Benefits
     */
    public void setBenefits(String value) {
        setAttributeInternal(BENEFITS, value);
    }

    /**Gets the attribute value for Company, using the alias name Company
     */
    public String getCompany() {
        return (String)getAttributeInternal(COMPANY);
    }

    /**Sets <code>value</code> as the attribute value for Company
     */
    public void setCompany(String value) {
        setAttributeInternal(COMPANY, value);
    }

    /**Gets the attribute value for LastUpdatedBy, using the alias name LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
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
    public RowIterator getXxGamMaPasajerosInfoEO() {
        return (RowIterator)getAttributeInternal(XXGAMMAPASAJEROSINFOEO);
    }

    /**Gets the attribute value for TicketClass, using the alias name TicketClass
     */
    public String getTicketClass() {
        return (String)getAttributeInternal(TICKETCLASS);
    }

    /**Sets <code>value</code> as the attribute value for TicketClass
     */
    public void setTicketClass(String value) {
        setAttributeInternal(TICKETCLASS, value);
    }

    /**Gets the attribute value for TicketRate, using the alias name TicketRate
     */
    public String getTicketRate() {
        return (String)getAttributeInternal(TICKETRATE);
    }

    /**Sets <code>value</code> as the attribute value for TicketRate
     */
    public void setTicketRate(String value) {
        setAttributeInternal(TICKETRATE, value);
    }

    /**Gets the attribute value for TicketAvailable, using the alias name TicketAvailable
     */
    public String getTicketAvailable() {
        return (String)getAttributeInternal(TICKETAVAILABLE);
    }

    /**Sets <code>value</code> as the attribute value for TicketAvailable
     */
    public void setTicketAvailable(String value) {
        setAttributeInternal(TICKETAVAILABLE, value);
    }

    /**Gets the attribute value for TicketEmbargoed, using the alias name TicketEmbargoed
     */
    public String getTicketEmbargoed() {
        return (String)getAttributeInternal(TICKETEMBARGOED);
    }

    /**Sets <code>value</code> as the attribute value for TicketEmbargoed
     */
    public void setTicketEmbargoed(String value) {
        setAttributeInternal(TICKETEMBARGOED, value);
    }

    /**Gets the attribute value for ClassPass, using the alias name ClassPass
     */
    public String getClassPass() {
        return (String)getAttributeInternal(CLASSPASS);
    }

    /**Sets <code>value</code> as the attribute value for ClassPass
     */
    public void setClassPass(String value) {
        setAttributeInternal(CLASSPASS, value);
    }

    /**Gets the attribute value for DiscountRate, using the alias name DiscountRate
     */
    public String getDiscountRate() {
        return (String)getAttributeInternal(DISCOUNTRATE);
    }

    /**Sets <code>value</code> as the attribute value for DiscountRate
     */
    public void setDiscountRate(String value) {
        setAttributeInternal(DISCOUNTRATE, value);
    }

    /**Gets the attribute value for Filial, using the alias name Filial
     */
    public String getFilial() {
        return (String)getAttributeInternal(FILIAL);
    }

    /**Sets <code>value</code> as the attribute value for Filial
     */
    public void setFilial(String value) {
        setAttributeInternal(FILIAL, value);
    }

    /**Gets the attribute value for AmountBase, using the alias name AmountBase
     */
    public Number getAmountBase() {
        return (Number)getAttributeInternal(AMOUNTBASE);
    }

    /**Sets <code>value</code> as the attribute value for AmountBase
     */
    public void setAmountBase(Number value) {
        setAttributeInternal(AMOUNTBASE, value);
    }

    /**Gets the attribute value for AmountDiscount, using the alias name AmountDiscount
     */
    public Number getAmountDiscount() {
        return (Number)getAttributeInternal(AMOUNTDISCOUNT);
    }

    /**Sets <code>value</code> as the attribute value for AmountDiscount
     */
    public void setAmountDiscount(Number value) {
        setAttributeInternal(AMOUNTDISCOUNT, value);
    }

    /**Gets the attribute value for TaxDiscount, using the alias name TaxDiscount
     */
    public Number getTaxDiscount() {
        return (Number)getAttributeInternal(TAXDISCOUNT);
    }

    /**Sets <code>value</code> as the attribute value for TaxDiscount
     */
    public void setTaxDiscount(Number value) {
        setAttributeInternal(TAXDISCOUNT, value);
    }

    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number id) {
        return new Key(new Object[] { id });
    }
}
