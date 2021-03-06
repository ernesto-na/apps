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
public class XxGamMaVsMcpPeriodsAmountVORowImpl extends OAViewRowImpl {
    public static final int PERIODNAME = 0;
    public static final int LASTPERIODNAME = 1;
    public static final int STARTDATE = 2;
    public static final int ENDDATE = 3;
    public static final int YEARSTARTDATE = 4;
    public static final int QUARTERSTARTDATE = 5;
    public static final int PERIODTYPE = 6;
    public static final int PERIODYEAR = 7;
    public static final int PERIODNUM = 8;
    public static final int QUARTERNUM = 9;
    public static final int BAMOUNT = 10;
    public static final int EAMOUNT = 11;
    public static final int RAMOUNT = 12;
    public static final int TAMOUNT = 13;
    public static final int LASTUPDATEDATE = 14;
    public static final int LASTUPDATEDBY = 15;
    public static final int LASTUPDATELOGIN = 16;
    public static final int CREATIONDATE = 17;
    public static final int CREATEDBY = 18;
    public static final int PERIODSETNAME = 19;
    public static final int CLOSURESTATUS = 20;
    public static final int AMOUNTTYPE = 21;

    /**This is the default constructor (do not remove)
     */
    public XxGamMaVsMcpPeriodsAmountVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute PeriodName
     */
    public String getPeriodName() {
        return (String)getAttributeInternal(PERIODNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PeriodName
     */
    public void setPeriodName(String value) {
        setAttributeInternal(PERIODNAME, value);
    }

    /**Gets the attribute value for the calculated attribute LastPeriodName
     */
    public String getLastPeriodName() {
        return (String)getAttributeInternal(LASTPERIODNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastPeriodName
     */
    public void setLastPeriodName(String value) {
        setAttributeInternal(LASTPERIODNAME, value);
    }

    /**Gets the attribute value for the calculated attribute StartDate
     */
    public Date getStartDate() {
        return (Date)getAttributeInternal(STARTDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StartDate
     */
    public void setStartDate(Date value) {
        setAttributeInternal(STARTDATE, value);
    }

    /**Gets the attribute value for the calculated attribute EndDate
     */
    public Date getEndDate() {
        return (Date)getAttributeInternal(ENDDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EndDate
     */
    public void setEndDate(Date value) {
        setAttributeInternal(ENDDATE, value);
    }

    /**Gets the attribute value for the calculated attribute YearStartDate
     */
    public Date getYearStartDate() {
        return (Date)getAttributeInternal(YEARSTARTDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute YearStartDate
     */
    public void setYearStartDate(Date value) {
        setAttributeInternal(YEARSTARTDATE, value);
    }

    /**Gets the attribute value for the calculated attribute QuarterStartDate
     */
    public Date getQuarterStartDate() {
        return (Date)getAttributeInternal(QUARTERSTARTDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute QuarterStartDate
     */
    public void setQuarterStartDate(Date value) {
        setAttributeInternal(QUARTERSTARTDATE, value);
    }

    /**Gets the attribute value for the calculated attribute PeriodType
     */
    public String getPeriodType() {
        return (String)getAttributeInternal(PERIODTYPE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PeriodType
     */
    public void setPeriodType(String value) {
        setAttributeInternal(PERIODTYPE, value);
    }

    /**Gets the attribute value for the calculated attribute PeriodYear
     */
    public Number getPeriodYear() {
        return (Number)getAttributeInternal(PERIODYEAR);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PeriodYear
     */
    public void setPeriodYear(Number value) {
        setAttributeInternal(PERIODYEAR, value);
    }

    /**Gets the attribute value for the calculated attribute PeriodNum
     */
    public Number getPeriodNum() {
        return (Number)getAttributeInternal(PERIODNUM);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PeriodNum
     */
    public void setPeriodNum(Number value) {
        setAttributeInternal(PERIODNUM, value);
    }

    /**Gets the attribute value for the calculated attribute QuarterNum
     */
    public Number getQuarterNum() {
        return (Number)getAttributeInternal(QUARTERNUM);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute QuarterNum
     */
    public void setQuarterNum(Number value) {
        setAttributeInternal(QUARTERNUM, value);
    }

    /**Gets the attribute value for the calculated attribute BAmount
     */
    public Number getBAmount() {
        return (Number)getAttributeInternal(BAMOUNT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute BAmount
     */
    public void setBAmount(Number value) {
        setAttributeInternal(BAMOUNT, value);
    }

    /**Gets the attribute value for the calculated attribute EAmount
     */
    public Number getEAmount() {
        return (Number)getAttributeInternal(EAMOUNT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EAmount
     */
    public void setEAmount(Number value) {
        setAttributeInternal(EAMOUNT, value);
    }

    /**Gets the attribute value for the calculated attribute RAmount
     */
    public Number getRAmount() {
        return (Number)getAttributeInternal(RAMOUNT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute RAmount
     */
    public void setRAmount(Number value) {
        setAttributeInternal(RAMOUNT, value);
    }

    /**Gets the attribute value for the calculated attribute TAmount
     */
    public Number getTAmount() {
        return (Number)getAttributeInternal(TAMOUNT);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TAmount
     */
    public void setTAmount(Number value) {
        setAttributeInternal(TAMOUNT, value);
    }

    /**Gets the attribute value for the calculated attribute LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**Gets the attribute value for the calculated attribute LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**Gets the attribute value for the calculated attribute LastUpdateLogin
     */
    public Number getLastUpdateLogin() {
        return (Number)getAttributeInternal(LASTUPDATELOGIN);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastUpdateLogin
     */
    public void setLastUpdateLogin(Number value) {
        setAttributeInternal(LASTUPDATELOGIN, value);
    }

    /**Gets the attribute value for the calculated attribute CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**Gets the attribute value for the calculated attribute CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**Gets the attribute value for the calculated attribute PeriodSetName
     */
    public String getPeriodSetName() {
        return (String)getAttributeInternal(PERIODSETNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PeriodSetName
     */
    public void setPeriodSetName(String value) {
        setAttributeInternal(PERIODSETNAME, value);
    }

    /**Gets the attribute value for the calculated attribute ClosureStatus
     */
    public String getClosureStatus() {
        return (String)getAttributeInternal(CLOSURESTATUS);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ClosureStatus
     */
    public void setClosureStatus(String value) {
        setAttributeInternal(CLOSURESTATUS, value);
    }

    /**Gets the attribute value for the calculated attribute AmountType
     */
    public String getAmountType() {
        return (String)getAttributeInternal(AMOUNTTYPE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute AmountType
     */
    public void setAmountType(String value) {
        setAttributeInternal(AMOUNTTYPE, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case PERIODNAME:
            return getPeriodName();
        case LASTPERIODNAME:
            return getLastPeriodName();
        case STARTDATE:
            return getStartDate();
        case ENDDATE:
            return getEndDate();
        case YEARSTARTDATE:
            return getYearStartDate();
        case QUARTERSTARTDATE:
            return getQuarterStartDate();
        case PERIODTYPE:
            return getPeriodType();
        case PERIODYEAR:
            return getPeriodYear();
        case PERIODNUM:
            return getPeriodNum();
        case QUARTERNUM:
            return getQuarterNum();
        case BAMOUNT:
            return getBAmount();
        case EAMOUNT:
            return getEAmount();
        case RAMOUNT:
            return getRAmount();
        case TAMOUNT:
            return getTAmount();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case CREATIONDATE:
            return getCreationDate();
        case CREATEDBY:
            return getCreatedBy();
        case PERIODSETNAME:
            return getPeriodSetName();
        case CLOSURESTATUS:
            return getClosureStatus();
        case AMOUNTTYPE:
            return getAmountType();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}
