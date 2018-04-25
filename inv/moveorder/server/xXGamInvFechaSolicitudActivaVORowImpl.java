package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;

public class xXGamInvFechaSolicitudActivaVORowImpl extends OAViewRowImpl {

    public static final int KITID = 0;
    public static final int KITCOD = 1;
    public static final int PERSONID = 2;
    public static final int FECHAINICIAL = 3;
    public static final int FECHAFINAL = 4;
    public static final int CREATIONDATE = 5;
    public static final int STATUS = 6;

    /**This is the default constructor (do not remove)
     */
    public xXGamInvFechaSolicitudActivaVORowImpl() {
    }


    public Number getKitId() {
      return (Number)this.getAttributeInternal(0);
   }

   public void setKitId(Number value) {
      this.setAttributeInternal(0, value);
   }

   public String getKitCod() {
      return (String)this.getAttributeInternal(1);
   }

   public void setKitCod(String value) {
      this.setAttributeInternal(1, value);
   }

   public Number getPersonId() {
      return (Number)this.getAttributeInternal(2);
   }

   public void setPersonId(Number value) {
      this.setAttributeInternal(2, value);
   }

   public Date getFechaInicial() {
      return (Date)this.getAttributeInternal(3);
   }

   public void setFechaInicial(Date value) {
      this.setAttributeInternal(3, value);
   }

   public Date getFechaFinal() {
      return (Date)this.getAttributeInternal(4);
   }

   public void setFechaFinal(Date value) {
      this.setAttributeInternal(4, value);
   }

   public Date getCreationDate() {
      return (Date)this.getAttributeInternal(5);
   }

   public void setCreationDate(Date value) {
      this.setAttributeInternal(5, value);
   }

   public String getStatus() {
      return (String)this.getAttributeInternal(6);
   }

   public void setStatus(String value) {
      this.setAttributeInternal(6, value);
   }

   protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case KITID:
            return getKitId();
        case KITCOD:
            return getKitCod();
        case PERSONID:
            return getPersonId();
        case FECHAINICIAL:
            return getFechaInicial();
        case FECHAFINAL:
            return getFechaFinal();
        case CREATIONDATE:
            return getCreationDate();
        case STATUS:
            return getStatus();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

   protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case KITID:
            setKitId((Number)value);
            return;
        case KITCOD:
            setKitCod((String)value);
            return;
        case PERSONID:
            setPersonId((Number)value);
            return;
        case FECHAINICIAL:
            setFechaInicial((Date)value);
            return;
        case FECHAFINAL:
            setFechaFinal((Date)value);
            return;
        case CREATIONDATE:
            setCreationDate((Date)value);
            return;
        case STATUS:
            setStatus((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}