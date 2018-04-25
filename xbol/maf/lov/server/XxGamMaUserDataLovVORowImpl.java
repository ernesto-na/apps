package xxgam.oracle.apps.xbol.maf.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaUserDataLovVORowImpl extends OAViewRowImpl {
    public static final int USERID = 0;
    public static final int USERNAME = 1;
    public static final int PERSONID = 2;
    public static final int EMPLOYEENUMBER = 3;
    public static final int EMAILADDRESS = 4;
    public static final int FULLNAME = 5;
    public static final int LASTNAME = 6;
    public static final int FIRSTNAME = 7;
    public static final int WORKTELEPHONE = 8;
    public static final int VENDORTYPELOOKUPCODE = 9;

    /**This is the default constructor (do not remove)
     */
    public XxGamMaUserDataLovVORowImpl() {
    }

    /**Gets the attribute value for the calculated attribute UserId
     */
    public Number getUserId() {
        return (Number) getAttributeInternal(USERID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute UserId
     */
    public void setUserId(Number value) {
        setAttributeInternal(USERID, value);
    }

    /**Gets the attribute value for the calculated attribute UserName
     */
    public String getUserName() {
        return (String) getAttributeInternal(USERNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute UserName
     */
    public void setUserName(String value) {
        setAttributeInternal(USERNAME, value);
    }

    /**Gets the attribute value for the calculated attribute PersonId
     */
    public Number getPersonId() {
        return (Number) getAttributeInternal(PERSONID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PersonId
     */
    public void setPersonId(Number value) {
        setAttributeInternal(PERSONID, value);
    }

    /**Gets the attribute value for the calculated attribute EmployeeNumber
     */
    public String getEmployeeNumber() {
        return (String) getAttributeInternal(EMPLOYEENUMBER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmployeeNumber
     */
    public void setEmployeeNumber(String value) {
        setAttributeInternal(EMPLOYEENUMBER, value);
    }

    /**Gets the attribute value for the calculated attribute EmailAddress
     */
    public String getEmailAddress() {
        return (String) getAttributeInternal(EMAILADDRESS);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmailAddress
     */
    public void setEmailAddress(String value) {
        setAttributeInternal(EMAILADDRESS, value);
    }

    /**Gets the attribute value for the calculated attribute FullName
     */
    public String getFullName() {
        return (String) getAttributeInternal(FULLNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute FullName
     */
    public void setFullName(String value) {
        setAttributeInternal(FULLNAME, value);
    }

    /**Gets the attribute value for the calculated attribute LastName
     */
    public String getLastName() {
        return (String) getAttributeInternal(LASTNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute LastName
     */
    public void setLastName(String value) {
        setAttributeInternal(LASTNAME, value);
    }

    /**Gets the attribute value for the calculated attribute FirstName
     */
    public String getFirstName() {
        return (String) getAttributeInternal(FIRSTNAME);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute FirstName
     */
    public void setFirstName(String value) {
        setAttributeInternal(FIRSTNAME, value);
    }

    /**Gets the attribute value for the calculated attribute WorkTelephone
     */
    public String getWorkTelephone() {
        return (String) getAttributeInternal(WORKTELEPHONE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute WorkTelephone
     */
    public void setWorkTelephone(String value) {
        setAttributeInternal(WORKTELEPHONE, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case USERID:
            return getUserId();
        case USERNAME:
            return getUserName();
        case PERSONID:
            return getPersonId();
        case EMPLOYEENUMBER:
            return getEmployeeNumber();
        case EMAILADDRESS:
            return getEmailAddress();
        case FULLNAME:
            return getFullName();
        case LASTNAME:
            return getLastName();
        case FIRSTNAME:
            return getFirstName();
        case WORKTELEPHONE:
            return getWorkTelephone();
        case VENDORTYPELOOKUPCODE:
            return getVendorTypeLookupCode();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case VENDORTYPELOOKUPCODE:
            setVendorTypeLookupCode((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the attribute value for the calculated attribute VendorTypeLookupCode
     */
    public String getVendorTypeLookupCode() {
        return (String) getAttributeInternal(VENDORTYPELOOKUPCODE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute VendorTypeLookupCode
     */
    public void setVendorTypeLookupCode(String value) {
        setAttributeInternal(VENDORTYPELOOKUPCODE, value);
    }
}
