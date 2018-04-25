package xxgam.oracle.apps.xbol.maf.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaApproverByEmployeeIdLovVORowImpl extends OAViewRowImpl {
    public static final int PERSONID = 0;
    public static final int EMPLOYEENUMBER = 1;
    public static final int EMAILADDRESS = 2;
    public static final int FULLNAME = 3;
    public static final int LASTNAME = 4;
    public static final int FIRSTNAME = 5;
    public static final int WORKTELEPHONE = 6;
    public static final int EMPLOYEEPERSONID = 7;
    public static final int CODECOMBINATIONID = 8;

    /**This is the default constructor (do not remove)
     */
    public XxGamMaApproverByEmployeeIdLovVORowImpl() {
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
        case EMPLOYEEPERSONID:
            return getEmployeePersonId();
        case CODECOMBINATIONID:
            return getCodeCombinationId();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EMPLOYEEPERSONID:
            setEmployeePersonId((Number)value);
            return;
        case CODECOMBINATIONID:
            setCodeCombinationId((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }


    /**Gets the attribute value for the calculated attribute CodeCombinationId
     */
    public Number getCodeCombinationId() {
        return (Number) getAttributeInternal(CODECOMBINATIONID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CodeCombinationId
     */
    public void setCodeCombinationId(Number value) {
        setAttributeInternal(CODECOMBINATIONID, value);
    }

    /**Gets the attribute value for the calculated attribute EmployeePersonId
     */
    public Number getEmployeePersonId() {
        return (Number) getAttributeInternal(EMPLOYEEPERSONID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EmployeePersonId
     */
    public void setEmployeePersonId(Number value) {
        setAttributeInternal(EMPLOYEEPERSONID, value);
    }
}
