package xxgam.oracle.apps.xbol.maf.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.server.AttributeDefImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaTicketClassLovVORowImpl extends OAViewRowImpl {
    public static final int MEANING = 0;
    public static final int ID = 1;
    public static final int TAG = 2;

    /**This is the default constructor (do not remove)
     */
    public XxGamMaTicketClassLovVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute Meaning
     */
    public String getMeaning() {
        return (String)getAttributeInternal(MEANING);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Meaning
     */
    public void setMeaning(String value) {
        setAttributeInternal(MEANING, value);
    }

    /**Gets the attribute value for the calculated attribute Id
     */
    public String getId() {
        return (String)getAttributeInternal(ID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Id
     */
    public void setId(String value) {
        setAttributeInternal(ID, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case MEANING:
            return getMeaning();
        case ID:
            return getId();
        case TAG:
            return getTag();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case TAG:
            setTag((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for the calculated attribute Tag
     */
    public String getTag() {
        return (String)getAttributeInternal(TAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Tag
     */
    public void setTag(String value) {
        setAttributeInternal(TAG, value);
    }
}
