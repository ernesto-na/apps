package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;

public class xXGamInvLineDtlVORowImpl extends OAViewRowImpl {

    public static final int NROSOLICITUD = 0;
    public static final int MONTHDOTA = 1;
    public static final int LASTDELIVDATE = 2;
    public static final int LINENUMBER = 3;
    public static final int INVENTORYCOD = 4;
    public static final int UNIFORMTYPECOD = 5;
    public static final int QTYNBR = 6;
    public static final int ATTRIBUTE5 = 7;
    public static final int DESCRIPTION = 8;
    public static final int SOLIID = 9;

    /**This is the default constructor (do not remove)
     */
    public xXGamInvLineDtlVORowImpl() {
    }


    public String getNroSolicitud() {
      return (String)this.getAttributeInternal(0);
   }

   public void setNroSolicitud(String value) {
      this.setAttributeInternal(0, value);
   }

   public String getMonthDota() {
      return (String)this.getAttributeInternal(1);
   }

   public void setMonthDota(String value) {
      this.setAttributeInternal(1, value);
   }

   public Date getLastDelivDate() {
      return (Date)this.getAttributeInternal(2);
   }

   public void setLastDelivDate(Date value) {
      this.setAttributeInternal(2, value);
   }

   public Number getLineNumber() {
      return (Number)this.getAttributeInternal(3);
   }

   public void setLineNumber(Number value) {
      this.setAttributeInternal(3, value);
   }

   public String getInventoryCod() {
      return (String)this.getAttributeInternal(4);
   }

   public void setInventoryCod(String value) {
      this.setAttributeInternal(4, value);
   }

   public String getUniformTypeCod() {
      return (String)this.getAttributeInternal(5);
   }

   public void setUniformTypeCod(String value) {
      this.setAttributeInternal(5, value);
   }

   public Number getQtyNbr() {
      return (Number)this.getAttributeInternal(6);
   }

   public void setQtyNbr(Number value) {
      this.setAttributeInternal(6, value);
   }

   public String getDescription() {
      return (String)this.getAttributeInternal(8);
   }

   public void setDescription(String value) {
      this.setAttributeInternal(8, value);
   }

   protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case NROSOLICITUD:
            return getNroSolicitud();
        case MONTHDOTA:
            return getMonthDota();
        case LASTDELIVDATE:
            return getLastDelivDate();
        case LINENUMBER:
            return getLineNumber();
        case INVENTORYCOD:
            return getInventoryCod();
        case UNIFORMTYPECOD:
            return getUniformTypeCod();
        case QTYNBR:
            return getQtyNbr();
        case ATTRIBUTE5:
            return getAttribute5();
        case DESCRIPTION:
            return getDescription();
        case SOLIID:
            return getSoliId();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

   protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case NROSOLICITUD:
            setNroSolicitud((String)value);
            return;
        case MONTHDOTA:
            setMonthDota((String)value);
            return;
        case LASTDELIVDATE:
            setLastDelivDate((Date)value);
            return;
        case LINENUMBER:
            setLineNumber((Number)value);
            return;
        case INVENTORYCOD:
            setInventoryCod((String)value);
            return;
        case UNIFORMTYPECOD:
            setUniformTypeCod((String)value);
            return;
        case QTYNBR:
            setQtyNbr((Number)value);
            return;
        case ATTRIBUTE5:
            setAttribute5((String)value);
            return;
        case DESCRIPTION:
            setDescription((String)value);
            return;
        case SOLIID:
            setSoliId((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

   public String getAttribute5() {
      return (String)this.getAttributeInternal(7);
   }

   public void setAttribute5(String value) {
      this.setAttributeInternal(7, value);
   }

    /**Gets the attribute value for the calculated attribute SoliId
     */
    public Number getSoliId() {
        return (Number) getAttributeInternal(SOLIID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute SoliId
     */
    public void setSoliId(Number value) {
        setAttributeInternal(SOLIID, value);
    }
}
