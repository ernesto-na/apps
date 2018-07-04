package xxgam.oracle.apps.xbol.maf.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;

import xxgam.oracle.apps.xbol.maf.lov.server.XxGamMaTypePaymentLovVORowImpl;
import xxgam.oracle.apps.xbol.maf.lov.server.XxGamModAntLovAMImpl;


// Referenced classes of package xxgam.oracle.apps.xbol.maf.server:
//            XxGamModAntAMImpl, XxGamMaGeneralReqVOImpl, XxGamMaGeneralReqVORowImpl, XxGamMaPaymentReqEOImpl

public class XxGamMaPaymentReqVORowImpl extends OAViewRowImpl {

    public XxGamMaPaymentReqVORowImpl() {
    }

    public XxGamMaPaymentReqEOImpl getXxGamMaPaymentReqEO() {
        return (XxGamMaPaymentReqEOImpl)getEntity(0);
    }

    public Number getId() {
        return (Number)getAttributeInternal(0);
    }

    public void setId(Number value) {
        setAttributeInternal(0, value);
    }

    public Number getGeneralReqId() {
        return (Number)getAttributeInternal(1);
    }

    public void setGeneralReqId(Number value) {
        setAttributeInternal(1, value);
    }

    public Date getInitialDate() {
        return (Date)getAttributeInternal(2);
    }

    public void setInitialDate(Date value) {
        setAttributeInternal(2, value);
    }

    public Date getFinalDate() {
        return (Date)getAttributeInternal(12);
    }

    public void setFinalDate(Date value) {
        setAttributeInternal(12, value);
    }

    public Number getTypePayment() {
        return (Number)getAttributeInternal(3);
    }

    public void setTypePayment(Number value) {
        if (value != null)
            setAttributeInternal(3, value);
    }

    public Number getAmount() {
        return (Number)getAttributeInternal(4);
    }

    public void setAmount(Number value) {
        setAttributeInternal(4, value);
    }

    public Number getAmountMx() {
        return (Number)getAttributeInternal(5);
    }

    public void setAmountMx(Number value) {
        setAttributeInternal(5, value);
    }

    public String getJustification() {
        return (String)getAttributeInternal(6);
    }

    public void setJustification(String value) {
        setAttributeInternal(6, value);
    }

    public Number getCodeCombinationId() {
        return (Number)getAttributeInternal(7);
    }

    public void setCodeCombinationId(Number value) {
        setAttributeInternal(7, value);
    }

    public String getCurrencyCode() {
        return (String)getAttributeInternal(8);
    }

    public void setCurrencyCode(String value) {
        setAttributeInternal(8, value);
    }

    public Date getCreationDate() {
        return (Date)getAttributeInternal(9);
    }

    public void setCreationDate(Date value) {
        setAttributeInternal(9, value);
    }

    public Number getCreatedBy() {
        return (Number)getAttributeInternal(10);
    }

    public void setCreatedBy(Number value) {
        setAttributeInternal(10, value);
    }

    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(11);
    }

    public void setLastUpdateDate(Date value) {
        setAttributeInternal(11, value);
    }

    public Number getLastUpdateLogin() {
        return (Number)getAttributeInternal(13);
    }

    public void setLastUpdateLogin(Number value) {
        setAttributeInternal(13, value);
    }

    public String getCurrencyDesc() {
        return (String)getAttributeInternal(14);
    }

    public void setCurrencyDesc(String value) {
        setAttributeInternal(14, value);
    }

    public String getTypePymentDesc() {
        return (String)getAttributeInternal(15);
    }

    public void setTypePymentDesc(String value) {
        setAttributeInternal(15, value);
    }

    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case 0: // '\0'
            return getId();

        case 1: // '\001'
            return getGeneralReqId();

        case 2: // '\002'
            return getInitialDate();

        case 3: // '\003'
            return getTypePayment();

        case 4: // '\004'
            return getAmount();

        case 5: // '\005'
            return getAmountMx();

        case 6: // '\006'
            return getJustification();

        case 7: // '\007'
            return getCodeCombinationId();

        case 8: // '\b'
            return getCurrencyCode();

        case 9: // '\t'
            return getCreationDate();

        case 10: // '\n'
            return getCreatedBy();

        case 11: // '\013'
            return getLastUpdateDate();

        case 12: // '\f'
            return getFinalDate();

        case 13: // '\r'
            return getIsDetail();

        case 14: // '\016'
            return getCurrencyDesc();

        case 15: // '\017'
            return getTypePymentDesc();

        case 16: // '\020'
            return getCurrencyCodeDesc();

        case 17: // '\021'
            return getIsView();

        case 18: // '\022'
            return getisCalculated();

        case 19: // '\023'
            return getisNotCalculated();

        case 20: // '\024'
            return getIsPaymentValid();

        case 21: // '\025'
            return getIsPaymentNotValid();

        case 22: // '\026'
            return getIsTicketPlane();

        case 23: // '\027'
            return getisDisableSelectTypePayment();

        case 24: // '\030'
            return getObservations();

        case 26: // '\032'
            return getXxGamMaTicketPVO();

        case 25: // '\031'
            return getXxGamMaGeneralReqVO1();
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case 0: // '\0'
            setId((Number)value);
            return;

        case 1: // '\001'
            setGeneralReqId((Number)value);
            return;

        case 2: // '\002'
            setInitialDate((Date)value);
            return;

        case 3: // '\003'
            setTypePayment((Number)value);
            return;

        case 4: // '\004'
            setAmount((Number)value);
            return;

        case 5: // '\005'
            setAmountMx((Number)value);
            return;

        case 6: // '\006'
            setJustification((String)value);
            return;

        case 7: // '\007'
            setCodeCombinationId((Number)value);
            return;

        case 8: // '\b'
            setCurrencyCode((String)value);
            return;

        case 12: // '\f'
            setFinalDate((Date)value);
            return;

        case 13: // '\r'
            setIsDetail((String)value);
            return;

        case 14: // '\016'
            setCurrencyDesc((String)value);
            return;

        case 15: // '\017'
            setTypePymentDesc((String)value);
            return;

        case 16: // '\020'
            setCurrencyCodeDesc((String)value);
            return;

        case 17: // '\021'
            setIsView((String)value);
            return;

        case 18: // '\022'
            setisCalculated((Boolean)value);
            return;

        case 19: // '\023'
            setisNotCalculated((Boolean)value);
            return;

        case 20: // '\024'
            setIsPaymentValid((Boolean)value);
            return;

        case 21: // '\025'
            setIsPaymentNotValid((Boolean)value);
            return;

        case 22: // '\026'
            setIsTicketPlane((Boolean)value);
            return;

        case 23: // '\027'
            setisDisableSelectTypePayment((Boolean)value);
            return;

        case 24: // '\030'
            setObservations((String)value);
            return;

        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    public String getCurrencyCodeDesc() {
        return (String)getAttributeInternal(16);
    }

    public void setCurrencyCodeDesc(String value) {
        setAttributeInternal(16, value);
    }

    public String getIsView() {
        return (String)getAttributeInternal(17);
    }

    public void setIsView(String value) {
        setAttributeInternal(17, value);
    }

    public Row getXxGamMaGeneralReqVO1() {
        return (Row)getAttributeInternal(25);
    }

    public String getIsDetail() {
        return (String)getAttributeInternal(13);
    }

    public void setIsDetail(String value) {
        setAttributeInternal(13, value);
    }

    public Boolean getisCalculated() {
        Number typePaymentId = null;
        typePaymentId = getTypePayment();
        boolean isCalculated = true;
        if (typePaymentId != null) {
            XxGamModAntAMImpl am = (XxGamModAntAMImpl)getApplicationModule();
            if (am != null) {
                Number templateId = null;
                XxGamModAntLovAMImpl amLov = 
                    (XxGamModAntLovAMImpl)am.getXxGamModAntLovAM1();
                XxGamMaGeneralReqVOImpl generalImpl = 
                    am.getXxGamMaGeneralReqVO1();
                XxGamMaGeneralReqVORowImpl generalRow = null;
                if (generalImpl != null)
                    generalRow = 
                            (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
                if (generalRow != null)
                    templateId = generalRow.getTemplatePayment();
                if (amLov != null && templateId != null) {
                    XxGamMaTypePaymentLovVORowImpl typePaymentRow = 
                        amLov.getTypePaymentById(typePaymentId, templateId);
                    if (typePaymentRow != null) {
                        String typePaymentDesc = null;
                        typePaymentDesc = typePaymentRow.getTypePaymentDesc();
                        if (typePaymentDesc != null) {
                            if (typePaymentDesc.toUpperCase().trim().toUpperCase().indexOf("Boleto Avi\363n otra compa\361".toUpperCase()) != 
                                -1 || 
                                typePaymentDesc.toUpperCase().trim().toUpperCase().indexOf("Ant. Hospedaje".toUpperCase()) != 
                                -1 || 
                                typePaymentDesc.toUpperCase().trim().indexOf("Transporte".toUpperCase()) != 
                                -1)
                                isCalculated = false;
                            if (typePaymentDesc.toUpperCase().trim().indexOf("TRANSPORTACION".toUpperCase()) != 
                                -1 || 
                                typePaymentDesc.toUpperCase().trim().indexOf("OTROS GASTOS DE VIAJE".toUpperCase()) != 
                                -1)
                                isCalculated = false;
                        }
                    }
                }
            }
        }
        setisCalculated(Boolean.valueOf(isCalculated));
        return (Boolean)getAttributeInternal(18);
    }

    public void setisCalculated(Boolean value) {
        populateAttribute(18, value);
    }

    public Boolean getisNotCalculated() {
        setisNotCalculated(Boolean.valueOf(getisCalculated().booleanValue() ^ 
                                           true));
        return (Boolean)getAttributeInternal(19);
    }

    public void setisNotCalculated(Boolean value) {
        populateAttribute(19, value);
    }

    public Boolean getIsPaymentValid() {
        return (Boolean)getAttributeInternal(20);
    }

    public void setIsPaymentValid(Boolean value) {
        populateAttribute(20, value);
    }

    public Boolean getIsPaymentNotValid() {
        return (Boolean)getAttributeInternal(21);
    }

    public void setIsPaymentNotValid(Boolean value) {
        populateAttribute(21, value);
    }

    public Boolean getIsTicketPlane() {
        boolean isTicketPlane = false;
        XxGamModAntAMImpl am = (XxGamModAntAMImpl)getApplicationModule();
        if (am != null) {
            XxGamModAntLovAMImpl amLov = 
                (XxGamModAntLovAMImpl)am.getXxGamModAntLovAM1();
            XxGamMaGeneralReqVOImpl generalImpl = am.getXxGamMaGeneralReqVO1();
            XxGamMaGeneralReqVORowImpl generalRow = null;
            if (generalImpl != null)
                generalRow = 
                        (XxGamMaGeneralReqVORowImpl)generalImpl.getCurrentRow();
            Number templateId = null;
            if (generalRow != null)
                templateId = generalRow.getTemplatePayment();
            Number typePaymentId = null;
            typePaymentId = getTypePayment();
            if (amLov != null && templateId != null && typePaymentId != null) {
                XxGamMaTypePaymentLovVORowImpl typePaymentRow = 
                    amLov.getTypePaymentById(typePaymentId, templateId);
                if (typePaymentRow != null) {
                    String typePaymentDesc = null;
                    typePaymentDesc = typePaymentRow.getTypePaymentDesc();
                    if (typePaymentDesc != null && 
                        typePaymentRow.getTypePaymentDesc().toUpperCase().trim().indexOf("Boleto Avi\363n AM".toUpperCase()) != 
                        -1)
                        isTicketPlane = true;
                }
            }
        }
        setIsTicketPlane(Boolean.valueOf(isTicketPlane));
        return (Boolean)getAttributeInternal(22);
    }

    public void setIsTicketPlane(Boolean value) {
        populateAttribute(22, value);
    }

    public Boolean getisDisableSelectTypePayment() {
        if (getInitialDate() != null && getFinalDate() != null && 
            getTypePayment() != null)
            setisDisableSelectTypePayment(Boolean.valueOf(true));
        else
            setisDisableSelectTypePayment(Boolean.valueOf(false));
        return (Boolean)getAttributeInternal(23);
    }

    public void setisDisableSelectTypePayment(Boolean value) {
        populateAttribute(23, value);
    }

    public RowIterator getXxGamMaTicketPVO() {
        return (RowIterator)getAttributeInternal(26);
    }

    public String getObservations() {
        return (String)getAttributeInternal(24);
    }

    public void setObservations(String value) {
        setAttributeInternal(24, value);
    }

    public static final int ID = 0;
    public static final int LASTUPDATELOGIN = 13;
    public static final int GENERALREQID = 1;
    public static final int INITIALDATE = 2;
    public static final int TYPEPAYMENT = 3;
    public static final int AMOUNT = 4;
    public static final int AMOUNTMX = 5;
    public static final int JUSTIFICATION = 6;
    public static final int CODECOMBINATIONID = 7;
    public static final int CURRENCYCODE = 8;
    public static final int CREATIONDATE = 9;
    public static final int CREATEDBY = 10;
    public static final int LASTUPDATEDATE = 11;
    public static final int FINALDATE = 12;
    public static final int ISDETAIL = 13;
    public static final int CURRENCYDESC = 14;
    public static final int TYPEPYMENTDESC = 15;
    public static final int CURRENCYCODEDESC = 16;
    public static final int ISVIEW = 17;
    public static final int ISCALCULATED = 18;
    public static final int ISNOTCALCULATED = 19;
    public static final int ISPAYMENTVALID = 20;
    public static final int ISPAYMENTNOTVALID = 21;
    public static final int ISTICKETPLANE = 22;
    public static final int ISDISABLESELECTTYPEPAYMENT = 23;
    public static final int OBSERVATIONS = 24;
    public static final int XXGAMMAGENERALREQVO1 = 25;
    public static final int XXGAMMATICKETPVO = 26;
}
