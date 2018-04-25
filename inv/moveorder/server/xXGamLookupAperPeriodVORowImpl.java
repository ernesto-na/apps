package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;
import oracle.jbo.domain.Date;
import oracle.jbo.server.AttributeDefImpl;

public class xXGamLookupAperPeriodVORowImpl extends OAViewRowImpl {

   public static final int LOOKUPCODE = 0;
   public static final int MEANING = 1;
   public static final int DESCRIPTION = 2;
   public static final int FECHAINICIAL = 3;
   public static final int FECHAFINAL = 4;
   public static final int FECHAACTUAL = 5;
   public static final int STATUSPERIODO = 6;


   public String getLookupCode() {
      return (String)this.getAttributeInternal(0);
   }

   public void setLookupCode(String value) {
      this.setAttributeInternal(0, value);
   }

   public String getMeaning() {
      return (String)this.getAttributeInternal(1);
   }

   public void setMeaning(String value) {
      this.setAttributeInternal(1, value);
   }

   public String getDescription() {
      return (String)this.getAttributeInternal(2);
   }

   public void setDescription(String value) {
      this.setAttributeInternal(2, value);
   }

   protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
      switch(index) {
      case 0:
         return this.getLookupCode();
      case 1:
         return this.getMeaning();
      case 2:
         return this.getDescription();
      case 3:
         return this.getFechaInicial();
      case 4:
         return this.getFechaFinal();
      case 5:
         return this.getFechaActual();
      case 6:
         return this.getStatusPeriodo();
      default:
         return super.getAttrInvokeAccessor(index, attrDef);
      }
   }

   protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
      switch(index) {
      case 3:
         this.setFechaInicial((Date)value);
         return;
      case 4:
         this.setFechaFinal((Date)value);
         return;
      case 5:
         this.setFechaActual((Date)value);
         return;
      case 6:
         this.setStatusPeriodo((String)value);
         return;
      default:
         super.setAttrInvokeAccessor(index, value, attrDef);
      }
   }

   public Date getFechaActual() {
      return (Date)this.getAttributeInternal(5);
   }

   public void setFechaActual(Date value) {
      this.setAttributeInternal(5, value);
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

   public String getStatusPeriodo() {
      return (String)this.getAttributeInternal(6);
   }

   public void setStatusPeriodo(String value) {
      this.setAttributeInternal(6, value);
   }
}