package xxgam.oracle.apps.inv.moveorder.lov.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;


public class xXGamInvEmplVORowImpl extends OAViewRowImpl {

    public static final int FULLNAME = 0;
    public static final int EMPLOYEENUMBER = 1;
    public static final int LASTNAME = 2;
    public static final int FIRSTNAME = 3;
    public static final int PERSONID = 4;
    public static final int USERID = 5;

    /**This is the default constructor (do not remove)
     */
    public xXGamInvEmplVORowImpl() {
    }


    public String getFullName() {
        return (String)this.getAttributeInternal(0);
    }

    public void setFullName(String value) {
        this.setAttributeInternal(0, value);
    }

    public Number getPersonId() {
        return (Number)this.getAttributeInternal(4);
    }

    public void setPersonId(Number value) {
        this.setAttributeInternal(4, value);
    }

    public Number getUserId() {
        return (Number)this.getAttributeInternal(5);
    }

    public void setUserId(Number value) {
        this.setAttributeInternal(5, value);
    }

    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case FULLNAME:
            return getFullName();
        case EMPLOYEENUMBER:
            return getEmployeeNumber();
        case LASTNAME:
            return getLastName();
        case FIRSTNAME:
            return getFirstName();
        case PERSONID:
            return getPersonId();
        case USERID:
            return getUserId();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case EMPLOYEENUMBER:
            setEmployeeNumber((String)value);
            return;
        case LASTNAME:
            setLastName((String)value);
            return;
        case FIRSTNAME:
            setFirstName((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    public String getEmployeeNumber() {
        return (String)this.getAttributeInternal(1);
    }

    public void setEmployeeNumber(String value) {
        this.setAttributeInternal(1, value);
    }

    public String getLastName() {
        return (String)this.getAttributeInternal(2);
    }

    public void setLastName(String value) {
        this.setAttributeInternal(2, value);
    }

    public String getFirstName() {
        return (String)this.getAttributeInternal(3);
    }

    public void setFirstName(String value) {
        this.setAttributeInternal(3, value);
    }
}
