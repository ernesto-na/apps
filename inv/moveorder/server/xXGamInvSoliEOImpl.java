package xxgam.oracle.apps.inv.moveorder.server;

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

public class xXGamInvSoliEOImpl extends OAEntityImpl
{

  public static final int KITID = 0;
  public static final int SOLIID = 1;
  public static final int NROSOLICITUD = 2;
  public static final int PERSONID = 3;
  public static final int EMPLOYEENUMBER = 4;
  public static final int RFC = 5;
  public static final int CATEGORY = 6;
  public static final int ADSCRIPTION = 7;
  public static final int CONTDUEDATE = 8;
  public static final int SOLIDATE = 9;
  public static final int STATION = 10;
  public static final int COSTCENTER = 11;
  public static final int EXPENSEACC = 12;
  public static final int EXPENSEDESC = 13;
  public static final int HEADERID = 14;
  public static final int STATUS = 15;
  public static final int MESSAGEERROR = 16;
  public static final int ATTRIBUTE1 = 17;
  public static final int ATTRIBUTE2 = 18;
  public static final int ATTRIBUTE3 = 19;
  public static final int ATTRIBUTE4 = 20;
  public static final int ATTRIBUTE5 = 21;
  public static final int ATTRIBUTE6 = 22;
  public static final int ATTRIBUTE7 = 23;
  public static final int ATTRIBUTE8 = 24;
  public static final int ATTRIBUTE9 = 25;
  public static final int ATTRIBUTE10 = 26;
  public static final int CREATEDBY = 27;
  public static final int CREATIONDATE = 28;
  public static final int LASTUPDATEDBY = 29;
  public static final int LASTUPDATEDATE = 30;
  public static final int LASTUPDATELOGIN = 31;
  public static final int OBSERVACIONESUNI = 32;
  public static final int XXGAMINVSOLIDTLEO = 33;


  private static OAEntityDefImpl mDefinitionObject;

  /**This is the default constructor (do not remove)
   */
  public xXGamInvSoliEOImpl() {
    }


  /**Retrieves the definition object for this instance class.
   */
  public static synchronized EntityDefImpl getDefinitionObject()
  {
    if (mDefinitionObject == null)
    {
      mDefinitionObject = 
          (OAEntityDefImpl)EntityDefImpl.findDefObject("xxgam.oracle.apps.inv.moveorder.server.xXGamInvSoliEO");
    }
    return mDefinitionObject;
  }

  public Number getKitId() {
      return (Number)this.getAttributeInternal(0);
   }

   public void setKitId(Number value) {
      this.setAttributeInternal(0, value);
   }

   public Number getSoliId() {
      return (Number)this.getAttributeInternal(1);
   }

   public void setSoliId(Number value) {
      this.setAttributeInternal(1, value);
   }

   public String getNroSolicitud() {
      return (String)this.getAttributeInternal(2);
   }

   public void setNroSolicitud(String value) {
      this.setAttributeInternal(2, value);
   }

   public Number getPersonId() {
      return (Number)this.getAttributeInternal(3);
   }

   public void setPersonId(Number value) {
      this.setAttributeInternal(3, value);
   }

   public String getEmployeeNumber() {
      return (String)this.getAttributeInternal(4);
   }

   public void setEmployeeNumber(String value) {
      this.setAttributeInternal(4, value);
   }

   public String getRfc() {
      return (String)this.getAttributeInternal(5);
   }

   public void setRfc(String value) {
      this.setAttributeInternal(5, value);
   }

   public String getCategory() {
      return (String)this.getAttributeInternal(6);
   }

   public void setCategory(String value) {
      this.setAttributeInternal(6, value);
   }

   public String getAdscription() {
      return (String)this.getAttributeInternal(7);
   }

   public void setAdscription(String value) {
      this.setAttributeInternal(7, value);
   }

   public Date getContDueDate() {
      return (Date)this.getAttributeInternal(8);
   }

   public void setContDueDate(Date value) {
      this.setAttributeInternal(8, value);
   }

   public Date getSoliDate() {
      return (Date)this.getAttributeInternal(9);
   }

   public void setSoliDate(Date value) {
      this.setAttributeInternal(9, value);
   }

   public String getStation() {
      return (String)this.getAttributeInternal(10);
   }

   public void setStation(String value) {
      this.setAttributeInternal(10, value);
   }

   public String getCostCenter() {
      return (String)this.getAttributeInternal(11);
   }

   public void setCostCenter(String value) {
      this.setAttributeInternal(11, value);
   }

   public Number getExpenseAcc() {
      return (Number)this.getAttributeInternal(12);
   }

   public void setExpenseAcc(Number value) {
      this.setAttributeInternal(12, value);
   }

   public String getExpenseDesc() {
      return (String)this.getAttributeInternal(13);
   }

   public void setExpenseDesc(String value) {
      this.setAttributeInternal(13, value);
   }

   public Number getHeaderId() {
      return (Number)this.getAttributeInternal(14);
   }

   public void setHeaderId(Number value) {
      this.setAttributeInternal(14, value);
   }

   public String getStatus() {
      return (String)this.getAttributeInternal(15);
   }

   public void setStatus(String value) {
      this.setAttributeInternal(15, value);
   }

   public String getMessageError() {
      return (String)this.getAttributeInternal(16);
   }

   public void setMessageError(String value) {
      this.setAttributeInternal(16, value);
   }

   public String getAttribute1() {
      return (String)this.getAttributeInternal(17);
   }

   public void setAttribute1(String value) {
      this.setAttributeInternal(17, value);
   }

   public String getAttribute2() {
      return (String)this.getAttributeInternal(18);
   }

   public void setAttribute2(String value) {
      this.setAttributeInternal(18, value);
   }

   public String getAttribute3() {
      return (String)this.getAttributeInternal(19);
   }

   public void setAttribute3(String value) {
      this.setAttributeInternal(19, value);
   }

   public String getAttribute4() {
      return (String)this.getAttributeInternal(20);
   }

   public void setAttribute4(String value) {
      this.setAttributeInternal(20, value);
   }

   public String getAttribute5() {
      return (String)this.getAttributeInternal(21);
   }

   public void setAttribute5(String value) {
      this.setAttributeInternal(21, value);
   }

   public String getAttribute6() {
      return (String)this.getAttributeInternal(22);
   }

   public void setAttribute6(String value) {
      this.setAttributeInternal(22, value);
   }

   public String getAttribute7() {
      return (String)this.getAttributeInternal(23);
   }

   public void setAttribute7(String value) {
      this.setAttributeInternal(23, value);
   }

   public String getAttribute8() {
      return (String)this.getAttributeInternal(24);
   }

   public void setAttribute8(String value) {
      this.setAttributeInternal(24, value);
   }

   public String getAttribute9() {
      return (String)this.getAttributeInternal(25);
   }

   public void setAttribute9(String value) {
      this.setAttributeInternal(25, value);
   }

   public String getAttribute10() {
      return (String)this.getAttributeInternal(26);
   }

   public void setAttribute10(String value) {
      this.setAttributeInternal(26, value);
   }

   public Number getCreatedBy() {
      return (Number)this.getAttributeInternal(27);
   }

   public void setCreatedBy(Number value) {
      this.setAttributeInternal(27, value);
   }

   public Date getCreationDate() {
      return (Date)this.getAttributeInternal(28);
   }

   public void setCreationDate(Date value) {
      this.setAttributeInternal(28, value);
   }

   public Number getLastUpdatedBy() {
      return (Number)this.getAttributeInternal(29);
   }

   public void setLastUpdatedBy(Number value) {
      this.setAttributeInternal(29, value);
   }

   public Date getLastUpdateDate() {
      return (Date)this.getAttributeInternal(30);
   }

   public void setLastUpdateDate(Date value) {
      this.setAttributeInternal(30, value);
   }

   public Number getLastUpdateLogin() {
      return (Number)this.getAttributeInternal(31);
   }

   public void setLastUpdateLogin(Number value) {
      this.setAttributeInternal(31, value);
   }

   public String getObservacionesUni() {
      return (String)this.getAttributeInternal(32);
   }

   public void setObservacionesUni(String value) {
      this.setAttributeInternal(32, value);
   }

   protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
    switch (index)
    {
    case KITID:
      return getKitId();
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
    case EXPENSEDESC:
      return getExpenseDesc();
    case HEADERID:
      return getHeaderId();
    case STATUS:
      return getStatus();
    case MESSAGEERROR:
      return getMessageError();
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
    case OBSERVACIONESUNI:
      return getObservacionesUni();
    case XXGAMINVSOLIDTLEO:
      return getXXGamInvSoliDtlEO();
    default:
      return super.getAttrInvokeAccessor(index, attrDef);
    }
  }

   protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
    switch (index)
    {
    case KITID:
      setKitId((Number)value);
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
    case EXPENSEDESC:
      setExpenseDesc((String)value);
      return;
    case HEADERID:
      setHeaderId((Number)value);
      return;
    case STATUS:
      setStatus((String)value);
      return;
    case MESSAGEERROR:
      setMessageError((String)value);
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
    case OBSERVACIONESUNI:
      setObservacionesUni((String)value);
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
   }

   public void lock() {
      super.lock();
   }

   protected void doDML(int operation, TransactionEvent e) {
      super.doDML(operation, e);
   }

   public RowIterator getXXGamInvSoliDtlEO() {
      return (RowIterator)this.getAttributeInternal(33);
   }

  /**Creates a Key object based on given key constituents
   */
  public static Key createPrimaryKey(Number soliId)
  {
    return new Key(new Object[]{soliId});
  }
}
