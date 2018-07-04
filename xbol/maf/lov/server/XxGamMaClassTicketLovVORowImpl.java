package xxgam.oracle.apps.xbol.maf.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.server.AttributeDefImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaClassTicketLovVORowImpl extends OAViewRowImpl {
    public static final int LOOKUPCODE = 0;
    public static final int DESCRIPTION = 1;
    public static final int DISPLAY = 2;
    public static final int TAG = 3;

    /**This is the default constructor (do not remove)
     */
    public XxGamMaClassTicketLovVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute LookupCode
     */
    public String getLookupCode() {
        return (String)getAttributeInternal(LOOKUPCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LookupCode
     */
    public void setLookupCode(String value) {
        setAttributeInternal(LOOKUPCODE, value);
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

    /**Gets the attribute value for the calculated attribute Description
     */
    public String getDescription() {
        return (String)getAttributeInternal(DESCRIPTION);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Description
     */
    public void setDescription(String value) {
        setAttributeInternal(DESCRIPTION, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case LOOKUPCODE:
            return getLookupCode();
        case DESCRIPTION:
            return getDescription();
        case DISPLAY:
            return getDisplay();
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
        case DISPLAY:
            setDisplay((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for the calculated attribute Display
     */
    public String getDisplay() {
        return (String)getAttributeInternal(DISPLAY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Display
     */
    public void setDisplay(String value) {
        setAttributeInternal(DISPLAY, value);
    }
}
