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
public class XxGamRequestConsultationVORowImpl extends OAViewRowImpl {
    public static final int ID = 0;
    public static final int NUMBERPAYMENT = 1;
    public static final int EMPLOYEEID = 2;
    public static final int REQUESTPAYMENTDATE = 3;
    public static final int STATUSREQUEST = 4;
    public static final int TOTALPAYMENT = 5;
    public static final int TIPO = 6;
    public static final int PURPOSE = 7;
    public static final int APPROVALDATE = 8;
    public static final int APPROVERID = 9;
    public static final int COSTCENTER = 10;
    public static final int COSTCENTERFLEX = 11;
    public static final int GROUPPAYMENT = 12;
    public static final int TEMPLATEPAYMENT = 13;
    public static final int CURRENCYCODE = 14;
    public static final int VIRTUALCARD = 15;
    public static final int VIRTUALCARDMASK = 16;
    public static final int STATUSNOTIFICATION = 17;
    public static final int CREATIONDATE = 18;
    public static final int CREATEDBY = 19;
    public static final int LASTUPDATELOGIN = 20;
    public static final int LASTUPDATEDBY = 21;
    public static final int LASTUPDATEDATE = 22;
    public static final int ISVIEW = 23;
    public static final int PURPOSEDESCRIPTION = 24;
    public static final int STATUSREQUESTDSC = 25;
    public static final int STATUSDESCRIPTION = 26;
    public static final int CURRENCYNAME = 27;
    public static final int EMPLOYENAME = 28;
    public static final int APPROVERNAME = 29;
    public static final int APPROVERNAMEALT = 30;
    public static final int STATUSTICKET = 31;
    public static final int REPORTTYPE = 32;
    public static final int STATUSFRANCHISE = 33;
    public static final int STATUSFRANCHISEDSC = 34;
    public static final int SEGMENT3 = 35;
    public static final int TICKETID = 36;
    public static final int BENEFICIARY = 37;
    public static final int COMPANY = 38;
    public static final int BENEFITSDSC = 39;
    public static final int RELATIONSHIPDSC = 40;
    public static final int ISFRANCHISE = 41;
    public static final int OPERATINGUNIT = 42;
    public static final int BLNISFRANCHISE = 43;
    public static final int ISDISCLOSURE = 44;

    /**This is the default constructor (do not remove)
     */
    public XxGamRequestConsultationVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute Id
     */
    public Number getId() {
        return (Number) getAttributeInternal(ID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Id
     */
    public void setId(Number value) {
        setAttributeInternal(ID, value);
    }

    /**Gets the attribute value for the calculated attribute NumberPayment
     */
    public String getNumberPayment() {
        return (String) getAttributeInternal(NUMBERPAYMENT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute NumberPayment
     */
    public void setNumberPayment(String value) {
        setAttributeInternal(NUMBERPAYMENT, value);
    }

    /**Gets the attribute value for the calculated attribute EmployeeId
     */
    public Number getEmployeeId() {
        return (Number) getAttributeInternal(EMPLOYEEID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmployeeId
     */
    public void setEmployeeId(Number value) {
        setAttributeInternal(EMPLOYEEID, value);
    }

    /**Gets the attribute value for the calculated attribute RequestPaymentDate
     */
    public Date getRequestPaymentDate() {
        return (Date) getAttributeInternal(REQUESTPAYMENTDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RequestPaymentDate
     */
    public void setRequestPaymentDate(Date value) {
        setAttributeInternal(REQUESTPAYMENTDATE, value);
    }

    /**Gets the attribute value for the calculated attribute StatusRequest
     */
    public String getStatusRequest() {
        return (String) getAttributeInternal(STATUSREQUEST);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusRequest
     */
    public void setStatusRequest(String value) {
        setAttributeInternal(STATUSREQUEST, value);
    }

    /**Gets the attribute value for the calculated attribute TotalPayment
     */
    public String getTotalPayment() {
        return (String) getAttributeInternal(TOTALPAYMENT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TotalPayment
     */
    public void setTotalPayment(String value) {
        setAttributeInternal(TOTALPAYMENT, value);
    }

    /**Gets the attribute value for the calculated attribute Purpose
     */
    public String getPurpose() {
        return (String) getAttributeInternal(PURPOSE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Purpose
     */
    public void setPurpose(String value) {
        setAttributeInternal(PURPOSE, value);
    }

    /**Gets the attribute value for the calculated attribute ApprovalDate
     */
    public Date getApprovalDate() {
        return (Date) getAttributeInternal(APPROVALDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApprovalDate
     */
    public void setApprovalDate(Date value) {
        setAttributeInternal(APPROVALDATE, value);
    }

    /**Gets the attribute value for the calculated attribute ApproverId
     */
    public Number getApproverId() {
        return (Number) getAttributeInternal(APPROVERID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApproverId
     */
    public void setApproverId(Number value) {
        setAttributeInternal(APPROVERID, value);
    }

    /**Gets the attribute value for the calculated attribute CostCenter
     */
    public Number getCostCenter() {
        return (Number) getAttributeInternal(COSTCENTER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CostCenter
     */
    public void setCostCenter(Number value) {
        setAttributeInternal(COSTCENTER, value);
    }

    /**Gets the attribute value for the calculated attribute CostCenterFlex
     */
    public String getCostCenterFlex() {
        return (String) getAttributeInternal(COSTCENTERFLEX);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CostCenterFlex
     */
    public void setCostCenterFlex(String value) {
        setAttributeInternal(COSTCENTERFLEX, value);
    }

    /**Gets the attribute value for the calculated attribute GroupPayment
     */
    public String getGroupPayment() {
        return (String) getAttributeInternal(GROUPPAYMENT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute GroupPayment
     */
    public void setGroupPayment(String value) {
        setAttributeInternal(GROUPPAYMENT, value);
    }

    /**Gets the attribute value for the calculated attribute TemplatePayment
     */
    public Number getTemplatePayment() {
        return (Number) getAttributeInternal(TEMPLATEPAYMENT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TemplatePayment
     */
    public void setTemplatePayment(Number value) {
        setAttributeInternal(TEMPLATEPAYMENT, value);
    }

    /**Gets the attribute value for the calculated attribute CurrencyCode
     */
    public String getCurrencyCode() {
        return (String) getAttributeInternal(CURRENCYCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CurrencyCode
     */
    public void setCurrencyCode(String value) {
        setAttributeInternal(CURRENCYCODE, value);
    }

    /**Gets the attribute value for the calculated attribute VirtualCard
     */
    public String getVirtualCard() {
        return (String) getAttributeInternal(VIRTUALCARD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute VirtualCard
     */
    public void setVirtualCard(String value) {
        setAttributeInternal(VIRTUALCARD, value);
    }

    /**Gets the attribute value for the calculated attribute StatusNotification
     */
    public String getStatusNotification() {
        return (String) getAttributeInternal(STATUSNOTIFICATION);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusNotification
     */
    public void setStatusNotification(String value) {
        setAttributeInternal(STATUSNOTIFICATION, value);
    }

    /**Gets the attribute value for the calculated attribute CreationDate
     */
    public Date getCreationDate() {
        return (Date) getAttributeInternal(CREATIONDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**Gets the attribute value for the calculated attribute CreatedBy
     */
    public Number getCreatedBy() {
        return (Number) getAttributeInternal(CREATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**Gets the attribute value for the calculated attribute LastUpdateLogin
     */
    public Number getLastUpdateLogin() {
        return (Number) getAttributeInternal(LASTUPDATELOGIN);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastUpdateLogin
     */
    public void setLastUpdateLogin(Number value) {
        setAttributeInternal(LASTUPDATELOGIN, value);
    }

    /**Gets the attribute value for the calculated attribute LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number) getAttributeInternal(LASTUPDATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**Gets the attribute value for the calculated attribute LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date) getAttributeInternal(LASTUPDATEDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**Gets the attribute value for the calculated attribute IsView
     */
    public String getIsView() {
        return (String) getAttributeInternal(ISVIEW);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute IsView
     */
    public void setIsView(String value) {
        setAttributeInternal(ISVIEW, value);
    }

    /**Gets the attribute value for the calculated attribute PurposeDescription
     */
    public String getPurposeDescription() {
        return (String) getAttributeInternal(PURPOSEDESCRIPTION);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PurposeDescription
     */
    public void setPurposeDescription(String value) {
        setAttributeInternal(PURPOSEDESCRIPTION, value);
    }

    /**Gets the attribute value for the calculated attribute StatusDescription
     */
    public String getStatusDescription() {
        return (String) getAttributeInternal(STATUSDESCRIPTION);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusDescription
     */
    public void setStatusDescription(String value) {
        setAttributeInternal(STATUSDESCRIPTION, value);
    }

    /**Gets the attribute value for the calculated attribute CurrencyName
     */
    public String getCurrencyName() {
        return (String) getAttributeInternal(CURRENCYNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CurrencyName
     */
    public void setCurrencyName(String value) {
        setAttributeInternal(CURRENCYNAME, value);
    }

    /**Gets the attribute value for the calculated attribute EmployeName
     */
    public String getEmployeName() {
        return (String) getAttributeInternal(EMPLOYENAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmployeName
     */
    public void setEmployeName(String value) {
        setAttributeInternal(EMPLOYENAME, value);
    }

    /**Gets the attribute value for the calculated attribute ApproverName
     */
    public String getApproverName() {
        return (String) getAttributeInternal(APPROVERNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApproverName
     */
    public void setApproverName(String value) {
        setAttributeInternal(APPROVERNAME, value);
    }

    /**Gets the attribute value for the calculated attribute StatusTicket
     */
    public String getStatusTicket() {
        return (String) getAttributeInternal(STATUSTICKET);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusTicket
     */
    public void setStatusTicket(String value) {
        setAttributeInternal(STATUSTICKET, value);
    }

    /**Gets the attribute value for the calculated attribute ReportType
     */
    public String getReportType() {
        return (String) getAttributeInternal(REPORTTYPE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ReportType
     */
    public void setReportType(String value) {
        setAttributeInternal(REPORTTYPE, value);
    }

    /**Gets the attribute value for the calculated attribute StatusFranchise
     */
    public String getStatusFranchise() {
        return (String) getAttributeInternal(STATUSFRANCHISE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusFranchise
     */
    public void setStatusFranchise(String value) {
        setAttributeInternal(STATUSFRANCHISE, value);
    }

    /**Gets the attribute value for the calculated attribute StatusFranchiseDsc
     */
    public String getStatusFranchiseDsc() {
        return (String) getAttributeInternal(STATUSFRANCHISEDSC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusFranchiseDsc
     */
    public void setStatusFranchiseDsc(String value) {
        setAttributeInternal(STATUSFRANCHISEDSC, value);
    }

    /**Gets the attribute value for the calculated attribute Segment3
     */
    public String getSegment3() {
        return (String) getAttributeInternal(SEGMENT3);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Segment3
     */
    public void setSegment3(String value) {
        setAttributeInternal(SEGMENT3, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case ID:
            return getId();
        case NUMBERPAYMENT:
            return getNumberPayment();
        case EMPLOYEEID:
            return getEmployeeId();
        case REQUESTPAYMENTDATE:
            return getRequestPaymentDate();
        case STATUSREQUEST:
            return getStatusRequest();
        case TOTALPAYMENT:
            return getTotalPayment();
        case TIPO:
            return getTipo();
        case PURPOSE:
            return getPurpose();
        case APPROVALDATE:
            return getApprovalDate();
        case APPROVERID:
            return getApproverId();
        case COSTCENTER:
            return getCostCenter();
        case COSTCENTERFLEX:
            return getCostCenterFlex();
        case GROUPPAYMENT:
            return getGroupPayment();
        case TEMPLATEPAYMENT:
            return getTemplatePayment();
        case CURRENCYCODE:
            return getCurrencyCode();
        case VIRTUALCARD:
            return getVirtualCard();
        case VIRTUALCARDMASK:
            return getVirtualCardMask();
        case STATUSNOTIFICATION:
            return getStatusNotification();
        case CREATIONDATE:
            return getCreationDate();
        case CREATEDBY:
            return getCreatedBy();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case ISVIEW:
            return getIsView();
        case PURPOSEDESCRIPTION:
            return getPurposeDescription();
        case STATUSREQUESTDSC:
            return getStatusRequestDsc();
        case STATUSDESCRIPTION:
            return getStatusDescription();
        case CURRENCYNAME:
            return getCurrencyName();
        case EMPLOYENAME:
            return getEmployeName();
        case APPROVERNAME:
            return getApproverName();
        case APPROVERNAMEALT:
            return getApproverNameAlt();
        case STATUSTICKET:
            return getStatusTicket();
        case REPORTTYPE:
            return getReportType();
        case STATUSFRANCHISE:
            return getStatusFranchise();
        case STATUSFRANCHISEDSC:
            return getStatusFranchiseDsc();
        case SEGMENT3:
            return getSegment3();
        case TICKETID:
            return getTicketId();
        case BENEFICIARY:
            return getBeneficiary();
        case COMPANY:
            return getCompany();
        case BENEFITSDSC:
            return getBenefitsDsc();
        case RELATIONSHIPDSC:
            return getRelationShipDsc();
        case ISFRANCHISE:
            return getIsFranchise();
        case OPERATINGUNIT:
            return getOperatingUnit();
        case BLNISFRANCHISE:
            return getBlnIsFranchise();
        case ISDISCLOSURE:
            return getisDisclosure();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case TIPO:
            setTipo((String)value);
            return;
        case VIRTUALCARDMASK:
            setVirtualCardMask((String)value);
            return;
        case STATUSREQUESTDSC:
            setStatusRequestDsc((String)value);
            return;
        case APPROVERNAMEALT:
            setApproverNameAlt((String)value);
            return;
        case TICKETID:
            setTicketId((Number)value);
            return;
        case BENEFICIARY:
            setBeneficiary((String)value);
            return;
        case COMPANY:
            setCompany((String)value);
            return;
        case BENEFITSDSC:
            setBenefitsDsc((String)value);
            return;
        case RELATIONSHIPDSC:
            setRelationShipDsc((String)value);
            return;
        case ISFRANCHISE:
            setIsFranchise((String)value);
            return;
        case OPERATINGUNIT:
            setOperatingUnit((String)value);
            return;
        case BLNISFRANCHISE:
            setBlnIsFranchise((Boolean)value);
            return;
        case ISDISCLOSURE:
            setisDisclosure((Boolean)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for the calculated attribute StatusRequestDsc
     */
    public String getStatusRequestDsc() {
        return (String) getAttributeInternal(STATUSREQUESTDSC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusRequestDsc
     */
    public void setStatusRequestDsc(String value) {
        setAttributeInternal(STATUSREQUESTDSC, value);
    }

    /**Gets the attribute value for the calculated attribute TicketId
     */
    public Number getTicketId() {
        return (Number) getAttributeInternal(TICKETID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TicketId
     */
    public void setTicketId(Number value) {
        setAttributeInternal(TICKETID, value);
    }

    /**Gets the attribute value for the calculated attribute Beneficiary
     */
    public String getBeneficiary() {
        return (String) getAttributeInternal(BENEFICIARY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Beneficiary
     */
    public void setBeneficiary(String value) {
        setAttributeInternal(BENEFICIARY, value);
    }

    /**Gets the attribute value for the calculated attribute Company
     */
    public String getCompany() {
        return (String) getAttributeInternal(COMPANY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Company
     */
    public void setCompany(String value) {
        setAttributeInternal(COMPANY, value);
    }

    /**Gets the attribute value for the calculated attribute BenefitsDsc
     */
    public String getBenefitsDsc() {
        return (String) getAttributeInternal(BENEFITSDSC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute BenefitsDsc
     */
    public void setBenefitsDsc(String value) {
        setAttributeInternal(BENEFITSDSC, value);
    }

    /**Gets the attribute value for the calculated attribute RelationShipDsc
     */
    public String getRelationShipDsc() {
        return (String) getAttributeInternal(RELATIONSHIPDSC);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RelationShipDsc
     */
    public void setRelationShipDsc(String value) {
        setAttributeInternal(RELATIONSHIPDSC, value);
    }

    /**Gets the attribute value for the calculated attribute IsFranchise
     */
    public String getIsFranchise() {
        return (String) getAttributeInternal(ISFRANCHISE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute IsFranchise
     */
    public void setIsFranchise(String value) {
        setAttributeInternal(ISFRANCHISE, value);
    }

    /**Gets the attribute value for the calculated attribute BlnIsFranchise
     */
    public Boolean getBlnIsFranchise() {
        boolean isFranchise = false;
        String strIsFranchise = getIsFranchise();
        if(strIsFranchise != null && "Y".equals(strIsFranchise)){
            isFranchise = true;
        }
        setBlnIsFranchise(isFranchise);
        
        return (Boolean) getAttributeInternal(BLNISFRANCHISE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute BlnIsFranchise
     */
    public void setBlnIsFranchise(Boolean value) {
        setAttributeInternal(BLNISFRANCHISE, value);
    }

    /**Gets the attribute value for the calculated attribute isDisclosure
     */
    public Boolean getisDisclosure() {
    
        boolean isDisclosure = false;
        isDisclosure = getBlnIsFranchise();
        setisDisclosure(isDisclosure);
        return (Boolean) getAttributeInternal(ISDISCLOSURE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute isDisclosure
     */
    public void setisDisclosure(Boolean value) {
        setAttributeInternal(ISDISCLOSURE, value);
    }

    /**Gets the attribute value for the calculated attribute ApproverNameAlt
     */
    public String getApproverNameAlt() {
        return (String) getAttributeInternal(APPROVERNAMEALT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ApproverNameAlt
     */
    public void setApproverNameAlt(String value) {
        setAttributeInternal(APPROVERNAMEALT, value);
    }

  /**Gets the attribute value for the calculated attribute OperatingUnit
   */
  public String getOperatingUnit()
  {
    return (String) getAttributeInternal(OPERATINGUNIT);
  }

  /**Sets <code>value</code> as the attribute value for the calculated attribute OperatingUnit
   */
  public void setOperatingUnit(String value)
  {
    setAttributeInternal(OPERATINGUNIT, value);
  }

    /**Gets the attribute value for the calculated attribute VirtualCardMask
     */
    public String getVirtualCardMask() {
        return (String) getAttributeInternal(VIRTUALCARDMASK);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute VirtualCardMask
     */
    public void setVirtualCardMask(String value) {
        setAttributeInternal(VIRTUALCARDMASK, value);
    }

    /**Gets the attribute value for the calculated attribute Tipo
     */
    public String getTipo() {
        return (String) getAttributeInternal(TIPO);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Tipo
     */
    public void setTipo(String value) {
        setAttributeInternal(TIPO, value);
    }
}
