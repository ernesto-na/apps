package xxgam.oracle.apps.inv.moveorder.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;

public class xXGamInvSolVORowImpl extends OAViewRowImpl {

    public static final int NROSOLICITUD = 0;
    public static final int SOLIID = 1;

    /**This is the default constructor (do not remove)
     */
    public xXGamInvSolVORowImpl() {
    }


    public String getNroSolicitud() {
      return (String)this.getAttributeInternal(0);
   }

   public void setNroSolicitud(String value) {
      this.setAttributeInternal(0, value);
   }

   public Number getSoliId() {
      return (Number)this.getAttributeInternal(1);
   }

   public void setSoliId(Number value) {
      this.setAttributeInternal(1, value);
   }

   protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case NROSOLICITUD:
            return getNroSolicitud();
        case SOLIID:
            return getSoliId();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

   protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}