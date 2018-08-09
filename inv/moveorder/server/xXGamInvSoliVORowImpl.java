package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;

import xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliEOImpl;


public class xXGamInvSoliVORowImpl extends OAViewRowImpl {

    public static final int KITID = 0;
    public static final int KITCOD = 1;
    public static final int KITDES = 2;
    public static final int SOLIID = 3;
    public static final int NROSOLICITUD = 4;
    public static final int PERSONID = 5;
    public static final int EMPLOYEENUMBER = 6;
    public static final int RFC = 7;
    public static final int CATEGORY = 8;
    public static final int ADSCRIPTION = 9;
    public static final int CONTDUEDATE = 10;
    public static final int SOLIDATE = 11;
    public static final int STATION = 12;
    public static final int COSTCENTER = 13;
    public static final int EXPENSEACC = 14;
    public static final int HEADERID = 15;
    public static final int STATUS = 16;
    public static final int STATUSDSP = 17;
    public static final int EXPENSEDESC = 18;
    public static final int CREATEDBY = 19;
    public static final int CREATIONDATE = 20;
    public static final int LASTUPDATEDBY = 21;
    public static final int LASTUPDATEDATE = 22;
    public static final int LASTUPDATELOGIN = 23;
    public static final int OBSERVACIONESUNI = 24;
    public static final int FULLNAME = 25;
    public static final int XXGAMINVASSIGNEXCEPT = 26;
    public static final int XXGAMINVSOLISUMMARYVO = 27;
    public static final int XXGAMINVSOLIDTLVO = 28;

    /**This is the default constructor (do not remove)
     */
    public xXGamInvSoliVORowImpl() {
    }


    public xXGamInvSoliEOImpl getxXGamInvSoliEO() {
        return (xXGamInvSoliEOImpl)this.getEntity(0);
    }

    public Number getKitId() {
        return (Number)this.getAttributeInternal(0);
    }

    public void setKitId(Number value) {
        this.setAttributeInternal(0, value);
    }

    public Number getSoliId() {
        return (Number)this.getAttributeInternal(3);
    }

    public void setSoliId(Number value) {
        this.setAttributeInternal(3, value);
    }

    public String getNroSolicitud() {
        return (String)this.getAttributeInternal(4);
    }

    public void setNroSolicitud(String value) {
        this.setAttributeInternal(4, value);
    }

    public Number getPersonId() {
        return (Number)this.getAttributeInternal(5);
    }

    public void setPersonId(Number value) {
        this.setAttributeInternal(5, value);
    }

    public String getEmployeeNumber() {
        return (String)this.getAttributeInternal(6);
    }

    public void setEmployeeNumber(String value) {
        this.setAttributeInternal(6, value);
    }

    public String getRfc() {
        return (String)this.getAttributeInternal(7);
    }

    public void setRfc(String value) {
        this.setAttributeInternal(7, value);
    }

    public String getCategory() {
        return (String)this.getAttributeInternal(8);
    }

    public void setCategory(String value) {
        this.setAttributeInternal(8, value);
    }

    public String getAdscription() {
        return (String)this.getAttributeInternal(9);
    }

    public void setAdscription(String value) {
        this.setAttributeInternal(9, value);
    }

    public Date getContDueDate() {
        return (Date)this.getAttributeInternal(10);
    }

    public void setContDueDate(Date value) {
        this.setAttributeInternal(10, value);
    }

    public Date getSoliDate() {
        return (Date)this.getAttributeInternal(11);
    }

    public void setSoliDate(Date value) {
        this.setAttributeInternal(11, value);
    }

    public String getStation() {
        return (String)this.getAttributeInternal(12);
    }

    public void setStation(String value) {
        this.setAttributeInternal(12, value);
    }

    public String getCostCenter() {
        return (String)this.getAttributeInternal(13);
    }

    public void setCostCenter(String value) {
        this.setAttributeInternal(13, value);
    }

    public Number getExpenseAcc() {
        return (Number)this.getAttributeInternal(14);
    }

    public void setExpenseAcc(Number value) {
        this.setAttributeInternal(14, value);
    }

    public String getExpenseDesc() {
        return (String)this.getAttributeInternal(18);
    }

    public void setExpenseDesc(String value) {
        this.setAttributeInternal(18, value);
    }

    public Number getHeaderId() {
        return (Number)this.getAttributeInternal(15);
    }

    public void setHeaderId(Number value) {
        this.setAttributeInternal(15, value);
    }

    public String getStatus() {
        return (String)this.getAttributeInternal(16);
    }

    public void setStatus(String value) {
        this.setAttributeInternal(16, value);
    }

    public Number getCreatedBy() {
        return (Number)this.getAttributeInternal(19);
    }

    public void setCreatedBy(Number value) {
        this.setAttributeInternal(19, value);
    }

    public Date getCreationDate() {
        return (Date)this.getAttributeInternal(20);
    }

    public void setCreationDate(Date value) {
        this.setAttributeInternal(20, value);
    }

    public Number getLastUpdatedBy() {
        return (Number)this.getAttributeInternal(21);
    }

    public void setLastUpdatedBy(Number value) {
        this.setAttributeInternal(21, value);
    }

    public Date getLastUpdateDate() {
        return (Date)this.getAttributeInternal(22);
    }

    public void setLastUpdateDate(Date value) {
        this.setAttributeInternal(22, value);
    }

    public Number getLastUpdateLogin() {
        return (Number)this.getAttributeInternal(23);
    }

    public void setLastUpdateLogin(Number value) {
        this.setAttributeInternal(23, value);
    }

    public String getObservacionesUni() {
        return (String)this.getAttributeInternal(24);
    }

    public void setObservacionesUni(String value) {
        this.setAttributeInternal(24, value);
    }

    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case KITID:
            return getKitId();
        case KITCOD:
            return getKitCod();
        case KITDES:
            return getKitDes();
        case SOLIID:
            return getSoliId();
        case NROSOLICITUD:
            return getNroSolicitud();
        case PERSONID:
            return getPersonId();
        case EMPLOYEENUMBER:
            return getEmployeeNumber();
        case RFC:
            return getRfc();
        case CATEGORY:
            return getCategory();
        case ADSCRIPTION:
            return getAdscription();
        case CONTDUEDATE:
            return getContDueDate();
        case SOLIDATE:
            return getSoliDate();
        case STATION:
            return getStation();
        case COSTCENTER:
            return getCostCenter();
        case EXPENSEACC:
            return getExpenseAcc();
        case HEADERID:
            return getHeaderId();
        case STATUS:
            return getStatus();
        case STATUSDSP:
            return getStatusDsp();
        case EXPENSEDESC:
            return getExpenseDesc();
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
        case OBSERVACIONESUNI:
            return getObservacionesUni();
        case FULLNAME:
            return getFullName();
        case XXGAMINVASSIGNEXCEPT:
            return getXxGamInvAssignExcept();
        case XXGAMINVSOLIDTLVO:
            return getxXGamInvSoliDtlVO();
        case XXGAMINVSOLISUMMARYVO:
            return getxXGamInvSoliSummaryVO();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case KITID:
            setKitId((Number)value);
            return;
        case KITCOD:
            setKitCod((String)value);
            return;
        case KITDES:
            setKitDes((String)value);
            return;
        case SOLIID:
            setSoliId((Number)value);
            return;
        case NROSOLICITUD:
            setNroSolicitud((String)value);
            return;
        case PERSONID:
            setPersonId((Number)value);
            return;
        case EMPLOYEENUMBER:
            setEmployeeNumber((String)value);
            return;
        case RFC:
            setRfc((String)value);
            return;
        case CATEGORY:
            setCategory((String)value);
            return;
        case ADSCRIPTION:
            setAdscription((String)value);
            return;
        case CONTDUEDATE:
            setContDueDate((Date)value);
            return;
        case SOLIDATE:
            setSoliDate((Date)value);
            return;
        case STATION:
            setStation((String)value);
            return;
        case COSTCENTER:
            setCostCenter((String)value);
            return;
        case EXPENSEACC:
            setExpenseAcc((Number)value);
            return;
        case HEADERID:
            setHeaderId((Number)value);
            return;
        case STATUS:
            setStatus((String)value);
            return;
        case STATUSDSP:
            setStatusDsp((String)value);
            return;
        case EXPENSEDESC:
            setExpenseDesc((String)value);
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
        case OBSERVACIONESUNI:
            setObservacionesUni((String)value);
            return;
        case FULLNAME:
            setFullName((String)value);
            return;
        case XXGAMINVASSIGNEXCEPT:
            setXxGamInvAssignExcept((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    public String getKitCod() {
        return (String)this.getAttributeInternal(1);
    }

    public void setKitCod(String value) {
        this.setAttributeInternal(1, value);
    }

    public String getKitDes() {
        return (String)this.getAttributeInternal(2);
    }

    public void setKitDes(String value) {
        this.setAttributeInternal(2, value);
    }

    public String getStatusDsp() {
        return (String)this.getAttributeInternal(17);
    }

    public void setStatusDsp(String value) {
        this.setAttributeInternal(17, value);
    }

    public Row getxXGamInvSoliSummaryVO() {
        return (Row)this.getAttributeInternal(25);
    }

    public RowIterator getxXGamInvSoliDtlVO() {
        return (RowIterator)this.getAttributeInternal(26);
    }

    /**Gets the attribute value for the calculated attribute FullName
     */
    public String getFullName() {
        return (String)getAttributeInternal(FULLNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute FullName
     */
    public void setFullName(String value) {
        setAttributeInternal(FULLNAME, value);
    }

    /**Gets the attribute value for the calculated attribute XxGamInvAssignExcept
     */
    public String getXxGamInvAssignExcept() {
        return (String)getAttributeInternal(XXGAMINVASSIGNEXCEPT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute XxGamInvAssignExcept
     */
    public void setXxGamInvAssignExcept(String value) {
        setAttributeInternal(XXGAMINVASSIGNEXCEPT, value);
    }
}
