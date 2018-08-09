package xxgam.oracle.apps.xbol.maf.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaGeneralPaymentTicketPVORowImpl extends OAViewRowImpl {
    public static final int GI = 0;
    public static final int PI = 1;
    public static final int TI = 2;
    public static final int REQUESTERNAME = 3;
    public static final int TIPO = 4;
    public static final int FOLIO = 5;
    public static final int STATUSREQUEST = 6;
    public static final int STATUSDESC = 7;
    public static final int REQUESTPAYMENTDATE = 8;
    public static final int ID = 9;
    public static final int TYPEEMISSION = 10;
    public static final int TYPEEMISSIONDSC = 11;
    public static final int APPROVERID = 12;
    public static final int BENEFICIARY = 13;
    public static final int RELATIONSHIP = 14;
    public static final int BENEFITS = 15;
    public static final int COMPANY = 16;
    public static final int GENERALREQID = 17;
    public static final int PAYMENTREQID = 18;
    public static final int TIPOSOLICITUD = 19;
    public static final int STATUSNOTIFICATION = 20;
    public static final int STATUSNOTIFICATIONDESC = 21;
    public static final int TICKETOFFICE = 22;
    public static final int TICKETOFFICECODE = 23;
    public static final int NUMBERPAYMENT = 24;
    public static final int OPERATINGUNIT = 25;
    public static final int ORIGIN = 26;

    /**This is the default constructor (do not remove)
     */
    public XxGamMaGeneralPaymentTicketPVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute Gi
     */
    public Number getGi() {
        return (Number)getAttributeInternal(GI);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Gi
     */
    public void setGi(Number value) {
        setAttributeInternal(GI, value);
    }

    /**Gets the attribute value for the calculated attribute Pi
     */
    public Number getPi() {
        return (Number)getAttributeInternal(PI);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Pi
     */
    public void setPi(Number value) {
        setAttributeInternal(PI, value);
    }

    /**Gets the attribute value for the calculated attribute Ti
     */
    public Number getTi() {
        return (Number)getAttributeInternal(TI);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Ti
     */
    public void setTi(Number value) {
        setAttributeInternal(TI, value);
    }

    /**Gets the attribute value for the calculated attribute RequesterName
     */
    public String getRequesterName() {
        return (String)getAttributeInternal(REQUESTERNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RequesterName
     */
    public void setRequesterName(String value) {
        setAttributeInternal(REQUESTERNAME, value);
    }

    /**Gets the attribute value for the calculated attribute Folio
     */
    public String getFolio() {
        return (String)getAttributeInternal(FOLIO);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Folio
     */
    public void setFolio(String value) {
        setAttributeInternal(FOLIO, value);
    }

    /**Gets the attribute value for the calculated attribute StatusRequest
     */
    public String getStatusRequest() {
        return (String)getAttributeInternal(STATUSREQUEST);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusRequest
     */
    public void setStatusRequest(String value) {
        setAttributeInternal(STATUSREQUEST, value);
    }

    /**Gets the attribute value for the calculated attribute StatusDesc
     */
    public String getStatusDesc() {
        return (String)getAttributeInternal(STATUSDESC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusDesc
     */
    public void setStatusDesc(String value) {
        setAttributeInternal(STATUSDESC, value);
    }

    /**Gets the attribute value for the calculated attribute RequestPaymentDate
     */
    public Date getRequestPaymentDate() {
        return (Date)getAttributeInternal(REQUESTPAYMENTDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RequestPaymentDate
     */
    public void setRequestPaymentDate(Date value) {
        setAttributeInternal(REQUESTPAYMENTDATE, value);
    }

    /**Gets the attribute value for the calculated attribute Id
     */
    public Number getId() {
        return (Number)getAttributeInternal(ID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Id
     */
    public void setId(Number value) {
        setAttributeInternal(ID, value);
    }

    /**Gets the attribute value for the calculated attribute TypeEmission
     */
    public String getTypeEmission() {
        return (String)getAttributeInternal(TYPEEMISSION);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TypeEmission
     */
    public void setTypeEmission(String value) {
        setAttributeInternal(TYPEEMISSION, value);
    }

    /**Gets the attribute value for the calculated attribute TypeEmissionDsc
     */
    public String getTypeEmissionDsc() {
        return (String)getAttributeInternal(TYPEEMISSIONDSC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TypeEmissionDsc
     */
    public void setTypeEmissionDsc(String value) {
        setAttributeInternal(TYPEEMISSIONDSC, value);
    }

    /**Gets the attribute value for the calculated attribute ApproverId
     */
    public Number getApproverId() {
        return (Number)getAttributeInternal(APPROVERID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApproverId
     */
    public void setApproverId(Number value) {
        setAttributeInternal(APPROVERID, value);
    }

    /**Gets the attribute value for the calculated attribute Beneficiary
     */
    public String getBeneficiary() {
        return (String)getAttributeInternal(BENEFICIARY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Beneficiary
     */
    public void setBeneficiary(String value) {
        setAttributeInternal(BENEFICIARY, value);
    }

    /**Gets the attribute value for the calculated attribute Relationship
     */
    public String getRelationship() {
        return (String)getAttributeInternal(RELATIONSHIP);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Relationship
     */
    public void setRelationship(String value) {
        setAttributeInternal(RELATIONSHIP, value);
    }

    /**Gets the attribute value for the calculated attribute Benefits
     */
    public String getBenefits() {
        return (String)getAttributeInternal(BENEFITS);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Benefits
     */
    public void setBenefits(String value) {
        setAttributeInternal(BENEFITS, value);
    }

    /**Gets the attribute value for the calculated attribute Company
     */
    public String getCompany() {
        return (String)getAttributeInternal(COMPANY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Company
     */
    public void setCompany(String value) {
        setAttributeInternal(COMPANY, value);
    }

    /**Gets the attribute value for the calculated attribute GeneralReqId
     */
    public Number getGeneralReqId() {
        return (Number)getAttributeInternal(GENERALREQID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute GeneralReqId
     */
    public void setGeneralReqId(Number value) {
        setAttributeInternal(GENERALREQID, value);
    }

    /**Gets the attribute value for the calculated attribute PaymentReqId
     */
    public Number getPaymentReqId() {
        return (Number)getAttributeInternal(PAYMENTREQID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PaymentReqId
     */
    public void setPaymentReqId(Number value) {
        setAttributeInternal(PAYMENTREQID, value);
    }

    /**Gets the attribute value for the calculated attribute TipoSolicitud
     */
    public String getTipoSolicitud() {
        return (String)getAttributeInternal(TIPOSOLICITUD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TipoSolicitud
     */
    public void setTipoSolicitud(String value) {
        setAttributeInternal(TIPOSOLICITUD, value);
    }

    /**Gets the attribute value for the calculated attribute StatusNotification
     */
    public String getStatusNotification() {
        return (String)getAttributeInternal(STATUSNOTIFICATION);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusNotification
     */
    public void setStatusNotification(String value) {
        setAttributeInternal(STATUSNOTIFICATION, value);
    }

    /**Gets the attribute value for the calculated attribute StatusNotificationDesc
     */
    public String getStatusNotificationDesc() {
        return (String)getAttributeInternal(STATUSNOTIFICATIONDESC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusNotificationDesc
     */
    public void setStatusNotificationDesc(String value) {
        setAttributeInternal(STATUSNOTIFICATIONDESC, value);
    }

    /**Gets the attribute value for the calculated attribute TicketOffice
     */
    public String getTicketOffice() {
        return (String)getAttributeInternal(TICKETOFFICE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TicketOffice
     */
    public void setTicketOffice(String value) {
        setAttributeInternal(TICKETOFFICE, value);
    }

    /**Gets the attribute value for the calculated attribute TicketOfficeCode
     */
    public String getTicketOfficeCode() {
        return (String)getAttributeInternal(TICKETOFFICECODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TicketOfficeCode
     */
    public void setTicketOfficeCode(String value) {
        setAttributeInternal(TICKETOFFICECODE, value);
    }

    /**Gets the attribute value for the calculated attribute NumberPayment
     */
    public String getNumberPayment() {
        return (String)getAttributeInternal(NUMBERPAYMENT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute NumberPayment
     */
    public void setNumberPayment(String value) {
        setAttributeInternal(NUMBERPAYMENT, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case GI:
            return getGi();
        case PI:
            return getPi();
        case TI:
            return getTi();
        case REQUESTERNAME:
            return getRequesterName();
        case TIPO:
            return getTipo();
        case FOLIO:
            return getFolio();
        case STATUSREQUEST:
            return getStatusRequest();
        case STATUSDESC:
            return getStatusDesc();
        case REQUESTPAYMENTDATE:
            return getRequestPaymentDate();
        case ID:
            return getId();
        case TYPEEMISSION:
            return getTypeEmission();
        case TYPEEMISSIONDSC:
            return getTypeEmissionDsc();
        case APPROVERID:
            return getApproverId();
        case BENEFICIARY:
            return getBeneficiary();
        case RELATIONSHIP:
            return getRelationship();
        case BENEFITS:
            return getBenefits();
        case COMPANY:
            return getCompany();
        case GENERALREQID:
            return getGeneralReqId();
        case PAYMENTREQID:
            return getPaymentReqId();
        case TIPOSOLICITUD:
            return getTipoSolicitud();
        case STATUSNOTIFICATION:
            return getStatusNotification();
        case STATUSNOTIFICATIONDESC:
            return getStatusNotificationDesc();
        case TICKETOFFICE:
            return getTicketOffice();
        case TICKETOFFICECODE:
            return getTicketOfficeCode();
        case NUMBERPAYMENT:
            return getNumberPayment();
        case OPERATINGUNIT:
            return getOperatingUnit();
        case ORIGIN:
            return getOrigin();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case GI:
            setGi((Number)value);
            return;
        case PI:
            setPi((Number)value);
            return;
        case TI:
            setTi((Number)value);
            return;
        case TIPOSOLICITUD:
            setTipoSolicitud((String)value);
            return;
        case STATUSNOTIFICATION:
            setStatusNotification((String)value);
            return;
        case STATUSNOTIFICATIONDESC:
            setStatusNotificationDesc((String)value);
            return;
        case TICKETOFFICE:
            setTicketOffice((String)value);
            return;
        case TICKETOFFICECODE:
            setTicketOfficeCode((String)value);
            return;
        case NUMBERPAYMENT:
            setNumberPayment((String)value);
            return;
        case OPERATINGUNIT:
            setOperatingUnit((String)value);
            return;
        case ORIGIN:
            setOrigin((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for the calculated attribute OperatingUnit
     */
    public String getOperatingUnit() {
        return (String)getAttributeInternal(OPERATINGUNIT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute OperatingUnit
     */
    public void setOperatingUnit(String value) {
        setAttributeInternal(OPERATINGUNIT, value);
    }

    /**Gets the attribute value for the calculated attribute Origin
     */
    public String getOrigin() {
        return (String)getAttributeInternal(ORIGIN);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Origin
     */
    public void setOrigin(String value) {
        setAttributeInternal(ORIGIN, value);
    }

    /**Gets the attribute value for the calculated attribute Tipo
     */
    public String getTipo() {
        return (String)getAttributeInternal(TIPO);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Tipo
     */
    public void setTipo(String value) {
        setAttributeInternal(TIPO, value);
    }
}
