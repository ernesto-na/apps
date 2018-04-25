package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;
import oracle.jbo.server.AttributeDefImpl;

public class xXGamInvSoliDtlDisabledVORowImpl extends OAViewRowImpl {

   public static final int TALLANBRRENDERED = 0;
   public static final int QTYNBRRENDERED = 1;


   protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
      switch(index) {
      case 0:
         return this.getTallaNbrRendered();
      case 1:
         return this.getQtyNbrRendered();
      default:
         return super.getAttrInvokeAccessor(index, attrDef);
      }
   }

   protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
      switch(index) {
      case 0:
         this.setTallaNbrRendered((Boolean)value);
         return;
      case 1:
         this.setQtyNbrRendered((Boolean)value);
         return;
      default:
         super.setAttrInvokeAccessor(index, value, attrDef);
      }
   }

   public Boolean getTallaNbrRendered() {
      return (Boolean)this.getAttributeInternal(0);
   }

   public void setTallaNbrRendered(Boolean value) {
      this.setAttributeInternal(0, value);
   }

   public Boolean getQtyNbrRendered() {
      return (Boolean)this.getAttributeInternal(1);
   }

   public void setQtyNbrRendered(Boolean value) {
      this.setAttributeInternal(1, value);
   }
}