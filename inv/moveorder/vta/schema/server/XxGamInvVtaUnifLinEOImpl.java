package xxgam.oracle.apps.inv.moveorder.vta.schema.server;

import oracle.apps.fnd.framework.server.OAEntityDefImpl;
import oracle.apps.fnd.framework.server.OAEntityImpl;

import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamInvVtaUnifLinEOImpl extends OAEntityImpl {
    public static final int LINID = 0;
    public static final int HDRID = 1;
    public static final int SOINVITEMID = 2;
    public static final int SOORDEREDQTY = 3;
    public static final int SOSHIPFROMORGID = 4;
    public static final int ATTRIBUTECATEGORY = 5;
    public static final int ATTRIBUTE1 = 6;
    public static final int ATTRIBUTE2 = 7;
    public static final int ATTRIBUTE3 = 8;
    public static final int ATTRIBUTE4 = 9;
    public static final int ATTRIBUTE5 = 10;
    public static final int ATTRIBUTE6 = 11;
    public static final int ATTRIBUTE7 = 12;
    public static final int ATTRIBUTE8 = 13;
    public static final int CREATEDBY = 14;
    public static final int CREATIONDATE = 15;
    public static final int LASTUPDATEDATE = 16;
    public static final int LASTUPDATEDBY = 17;
    public static final int LASTUPDATELOGIN = 18;
    public static final int KITID = 19;
    public static final int KITCOD = 20;
    public static final int DOTAID = 21;
    public static final int TALLAID = 22;
    public static final int TALLANBR = 23;
    public static final int XXGAMINVVTAUNIFHDREO = 24;


    private static OAEntityDefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public XxGamInvVtaUnifLinEOImpl() {
    }


    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("xxgam.oracle.apps.inv.moveorder.vta.schema.server.XxGamInvVtaUnifLinEO");
        }
        return mDefinitionObject;
    }

    /**Gets the attribute value for LinId, using the alias name LinId
     */
    public Number getLinId() {
        return (Number)getAttributeInternal(LINID);
    }

    /**Sets <code>value</code> as the attribute value for LinId
     */
    public void setLinId(Number value) {
        setAttributeInternal(LINID, value);
    }

    /**Gets the attribute value for HdrId, using the alias name HdrId
     */
    public Number getHdrId() {
        return (Number)getAttributeInternal(HDRID);
    }

    /**Sets <code>value</code> as the attribute value for HdrId
     */
    public void setHdrId(Number value) {
        setAttributeInternal(HDRID, value);
    }

    /**Gets the attribute value for SoInvItemId, using the alias name SoInvItemId
     */
    public Number getSoInvItemId() {
        return (Number)getAttributeInternal(SOINVITEMID);
    }

    /**Sets <code>value</code> as the attribute value for SoInvItemId
     */
    public void setSoInvItemId(Number value) {
        setAttributeInternal(SOINVITEMID, value);
    }

    /**Gets the attribute value for SoOrderedQty, using the alias name SoOrderedQty
     */
    public Number getSoOrderedQty() {
        return (Number)getAttributeInternal(SOORDEREDQTY);
    }

    /**Sets <code>value</code> as the attribute value for SoOrderedQty
     */
    public void setSoOrderedQty(Number value) {
        setAttributeInternal(SOORDEREDQTY, value);
    }

    /**Gets the attribute value for SoShipFromOrgId, using the alias name SoShipFromOrgId
     */
    public Number getSoShipFromOrgId() {
        return (Number)getAttributeInternal(SOSHIPFROMORGID);
    }

    /**Sets <code>value</code> as the attribute value for SoShipFromOrgId
     */
    public void setSoShipFromOrgId(Number value) {
        setAttributeInternal(SOSHIPFROMORGID, value);
    }

    /**Gets the attribute value for AttributeCategory, using the alias name AttributeCategory
     */
    public String getAttributeCategory() {
        return (String)getAttributeInternal(ATTRIBUTECATEGORY);
    }

    /**Sets <code>value</code> as the attribute value for AttributeCategory
     */
    public void setAttributeCategory(String value) {
        setAttributeInternal(ATTRIBUTECATEGORY, value);
    }

    /**Gets the attribute value for Attribute1, using the alias name Attribute1
     */
    public String getAttribute1() {
        return (String)getAttributeInternal(ATTRIBUTE1);
    }

    /**Sets <code>value</code> as the attribute value for Attribute1
     */
    public void setAttribute1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
    }

    /**Gets the attribute value for Attribute2, using the alias name Attribute2
     */
    public String getAttribute2() {
        return (String)getAttributeInternal(ATTRIBUTE2);
    }

    /**Sets <code>value</code> as the attribute value for Attribute2
     */
    public void setAttribute2(String value) {
        setAttributeInternal(ATTRIBUTE2, value);
    }

    /**Gets the attribute value for Attribute3, using the alias name Attribute3
     */
    public String getAttribute3() {
        return (String)getAttributeInternal(ATTRIBUTE3);
    }

    /**Sets <code>value</code> as the attribute value for Attribute3
     */
    public void setAttribute3(String value) {
        setAttributeInternal(ATTRIBUTE3, value);
    }

    /**Gets the attribute value for Attribute4, using the alias name Attribute4
     */
    public String getAttribute4() {
        return (String)getAttributeInternal(ATTRIBUTE4);
    }

    /**Sets <code>value</code> as the attribute value for Attribute4
     */
    public void setAttribute4(String value) {
        setAttributeInternal(ATTRIBUTE4, value);
    }

    /**Gets the attribute value for Attribute5, using the alias name Attribute5
     */
    public String getAttribute5() {
        return (String)getAttributeInternal(ATTRIBUTE5);
    }

    /**Sets <code>value</code> as the attribute value for Attribute5
     */
    public void setAttribute5(String value) {
        setAttributeInternal(ATTRIBUTE5, value);
    }

    /**Gets the attribute value for Attribute6, using the alias name Attribute6
     */
    public String getAttribute6() {
        return (String)getAttributeInternal(ATTRIBUTE6);
    }

    /**Sets <code>value</code> as the attribute value for Attribute6
     */
    public void setAttribute6(String value) {
        setAttributeInternal(ATTRIBUTE6, value);
    }

    /**Gets the attribute value for Attribute7, using the alias name Attribute7
     */
    public String getAttribute7() {
        return (String)getAttributeInternal(ATTRIBUTE7);
    }

    /**Sets <code>value</code> as the attribute value for Attribute7
     */
    public void setAttribute7(String value) {
        setAttributeInternal(ATTRIBUTE7, value);
    }

    /**Gets the attribute value for Attribute8, using the alias name Attribute8
     */
    public String getAttribute8() {
        return (String)getAttributeInternal(ATTRIBUTE8);
    }

    /**Sets <code>value</code> as the attribute value for Attribute8
     */
    public void setAttribute8(String value) {
        setAttributeInternal(ATTRIBUTE8, value);
    }

    /**Gets the attribute value for CreatedBy, using the alias name CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**Gets the attribute value for CreationDate, using the alias name CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**Sets <code>value</code> as the attribute value for CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**Gets the attribute value for LastUpdateDate, using the alias name LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**Sets <code>value</code> as the attribute value for LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**Gets the attribute value for LastUpdatedBy, using the alias name LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**Sets <code>value</code> as the attribute value for LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**Gets the attribute value for LastUpdateLogin, using the alias name LastUpdateLogin
     */
    public Number getLastUpdateLogin() {
        return (Number)getAttributeInternal(LASTUPDATELOGIN);
    }

    /**Sets <code>value</code> as the attribute value for LastUpdateLogin
     */
    public void setLastUpdateLogin(Number value) {
        setAttributeInternal(LASTUPDATELOGIN, value);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case LINID:
            return getLinId();
        case HDRID:
            return getHdrId();
        case SOINVITEMID:
            return getSoInvItemId();
        case SOORDEREDQTY:
            return getSoOrderedQty();
        case SOSHIPFROMORGID:
            return getSoShipFromOrgId();
        case ATTRIBUTECATEGORY:
            return getAttributeCategory();
        case ATTRIBUTE1:
            return getAttribute1();
        case ATTRIBUTE2:
            return getAttribute2();
        case ATTRIBUTE3:
            return getAttribute3();
        case ATTRIBUTE4:
            return getAttribute4();
        case ATTRIBUTE5:
            return getAttribute5();
        case ATTRIBUTE6:
            return getAttribute6();
        case ATTRIBUTE7:
            return getAttribute7();
        case ATTRIBUTE8:
            return getAttribute8();
        case CREATEDBY:
            return getCreatedBy();
        case CREATIONDATE:
            return getCreationDate();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case KITID:
            return getKitId();
        case KITCOD:
            return getKitCod();
        case DOTAID:
            return getDotaId();
        case TALLAID:
            return getTallaId();
        case TALLANBR:
            return getTallaNbr();
        case XXGAMINVVTAUNIFHDREO:
            return getXxGamInvVtaUnifHdrEO();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case LINID:
            setLinId((Number)value);
            return;
        case HDRID:
            setHdrId((Number)value);
            return;
        case SOINVITEMID:
            setSoInvItemId((Number)value);
            return;
        case SOORDEREDQTY:
            setSoOrderedQty((Number)value);
            return;
        case SOSHIPFROMORGID:
            setSoShipFromOrgId((Number)value);
            return;
        case ATTRIBUTECATEGORY:
            setAttributeCategory((String)value);
            return;
        case ATTRIBUTE1:
            setAttribute1((String)value);
            return;
        case ATTRIBUTE2:
            setAttribute2((String)value);
            return;
        case ATTRIBUTE3:
            setAttribute3((String)value);
            return;
        case ATTRIBUTE4:
            setAttribute4((String)value);
            return;
        case ATTRIBUTE5:
            setAttribute5((String)value);
            return;
        case ATTRIBUTE6:
            setAttribute6((String)value);
            return;
        case ATTRIBUTE7:
            setAttribute7((String)value);
            return;
        case ATTRIBUTE8:
            setAttribute8((String)value);
            return;
        case CREATEDBY:
            setCreatedBy((Number)value);
            return;
        case CREATIONDATE:
            setCreationDate((Date)value);
            return;
        case LASTUPDATEDATE:
            setLastUpdateDate((Date)value);
            return;
        case LASTUPDATEDBY:
            setLastUpdatedBy((Number)value);
            return;
        case LASTUPDATELOGIN:
            setLastUpdateLogin((Number)value);
            return;
        case KITID:
            setKitId((Number)value);
            return;
        case KITCOD:
            setKitCod((String)value);
            return;
        case DOTAID:
            setDotaId((Number)value);
            return;
        case TALLAID:
            setTallaId((Number)value);
            return;
        case TALLANBR:
            setTallaNbr((String)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the associated entity XxGamInvVtaUnifHdrEOImpl
     */
    public XxGamInvVtaUnifHdrEOImpl getXxGamInvVtaUnifHdrEO() {
        return (XxGamInvVtaUnifHdrEOImpl)getAttributeInternal(XXGAMINVVTAUNIFHDREO);
    }

    /**Sets <code>value</code> as the associated entity XxGamInvVtaUnifHdrEOImpl
     */
    public void setXxGamInvVtaUnifHdrEO(XxGamInvVtaUnifHdrEOImpl value) {
        setAttributeInternal(XXGAMINVVTAUNIFHDREO, value);
    }

    /**Gets the attribute value for KitId, using the alias name KitId
     */
    public Number getKitId() {
        return (Number)getAttributeInternal(KITID);
    }

    /**Sets <code>value</code> as the attribute value for KitId
     */
    public void setKitId(Number value) {
        setAttributeInternal(KITID, value);
    }


    /**Gets the attribute value for DotaId, using the alias name DotaId
     */
    public Number getDotaId() {
        return (Number)getAttributeInternal(DOTAID);
    }

    /**Sets <code>value</code> as the attribute value for DotaId
     */
    public void setDotaId(Number value) {
        setAttributeInternal(DOTAID, value);
    }

    /**Gets the attribute value for TallaId, using the alias name TallaId
     */
    public Number getTallaId() {
        return (Number)getAttributeInternal(TALLAID);
    }

    /**Sets <code>value</code> as the attribute value for TallaId
     */
    public void setTallaId(Number value) {
        setAttributeInternal(TALLAID, value);
    }

    /**Gets the attribute value for TallaNbr, using the alias name TallaNbr
     */
    public String getTallaNbr() {
        return (String)getAttributeInternal(TALLANBR);
    }

    /**Sets <code>value</code> as the attribute value for TallaNbr
     */
    public void setTallaNbr(String value) {
        setAttributeInternal(TALLANBR, value);
    }

    /**Gets the attribute value for KitCod, using the alias name KitCod
     */
    public String getKitCod() {
        return (String)getAttributeInternal(KITCOD);
    }

    /**Sets <code>value</code> as the attribute value for KitCod
     */
    public void setKitCod(String value) {
        setAttributeInternal(KITCOD, value);
    }

    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number linId) {
        return new Key(new Object[] { linId });
    }
}
