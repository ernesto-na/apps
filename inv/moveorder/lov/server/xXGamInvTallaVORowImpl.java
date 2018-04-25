package xxgam.oracle.apps.inv.moveorder.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;

public class xXGamInvTallaVORowImpl extends OAViewRowImpl
{

  public static final int TALLANBR = 0;
  public static final int INVENTORYCOD = 1;
  public static final int NPCOD = 2;
  public static final int TALLAID = 3;
  public static final int DOTAID = 4;
  public static final int DESCRIPTION = 5;

  /**This is the default constructor (do not remove)
   */
  public xXGamInvTallaVORowImpl()
  {
  }


  public String getTallaNbr() {
      return (String)this.getAttributeInternal(0);
   }

   public void setTallaNbr(String value) {
      this.setAttributeInternal(0, value);
   }

   public String getNpCod() {
      return (String)this.getAttributeInternal(2);
   }

   public void setNpCod(String value) {
      this.setAttributeInternal(2, value);
   }

   public String getTallaId() {
      return (String)this.getAttributeInternal(3);
   }

   public void setTallaId(String value) {
      this.setAttributeInternal(3, value);
   }

   public Number getDotaId() {
      return (Number)this.getAttributeInternal(4);
   }

   public void setDotaId(Number value) {
      this.setAttributeInternal(4, value);
   }

   protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
    switch (index)
    {
    case TALLANBR:
      return getTallaNbr();
    case INVENTORYCOD:
      return getInventoryCod();
    case NPCOD:
      return getNpCod();
    case TALLAID:
      return getTallaId();
    case DOTAID:
      return getDotaId();
    case DESCRIPTION:
      return getDescription();
    default:
      return super.getAttrInvokeAccessor(index, attrDef);
    }
  }

   protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
    switch (index)
    {
    case INVENTORYCOD:
      setInventoryCod((String)value);
      return;
    case DESCRIPTION:
      setDescription((String)value);
      return;
    default:
      super.setAttrInvokeAccessor(index, value, attrDef);
      return;
    }
  }

   public String getInventoryCod() {
      return (String)this.getAttributeInternal(1);
   }

   public void setInventoryCod(String value) {
      this.setAttributeInternal(1, value);
   }

   public String getDescription() {
      return (String)this.getAttributeInternal(5);
   }

   public void setDescription(String value) {
      this.setAttributeInternal(5, value);
   }
}