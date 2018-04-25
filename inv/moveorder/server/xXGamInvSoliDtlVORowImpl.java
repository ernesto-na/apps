package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewRowImpl;

import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class xXGamInvSoliDtlVORowImpl extends OAViewRowImpl {
    public static final int SOLIID = 0;
    public static final int SOLIDTLID = 1;
    public static final int NROSOLICITUD = 2;
    public static final int KITID = 3;
    public static final int KITCOD = 4;
    public static final int PERSONID = 5;
    public static final int HEADERID = 6;
    public static final int DOTAID = 7;
    public static final int UNIFORMTYPECOD = 8;
    public static final int NOMENCLATURE = 9;
    public static final int NPCOD = 10;
    public static final int MEASUREUNITCOD = 11;
    public static final int CYCLECOD = 12;
    public static final int PLANTAQTY = 13;
    public static final int EVENTQTY = 14;
    public static final int QTYPLANTA = 15;
    public static final int QTYEVENTUAL = 16;
    public static final int TALLAID = 17;
    public static final int TALLANBR = 18;
    public static final int QTYNBR = 19;
    public static final int INVENTORYID = 20;
    public static final int LASTDELIVDATE = 21;
    public static final int LINENUMBER = 22;
    public static final int STATUS = 23;
    public static final int STATUSDSP = 24;
    public static final int DOTAIDF = 25;
    public static final int ATTRIBUTE1 = 26;
    public static final int ATTRIBUTE2 = 27;
    public static final int ATTRIBUTE3 = 28;
    public static final int ATTRIBUTE4 = 29;
    public static final int ATTRIBUTE5 = 30;
    public static final int CREATEDBY = 31;
    public static final int CREATIONDATE = 32;
    public static final int LASTUPDATEDBY = 33;
    public static final int LASTUPDATEDATE = 34;
    public static final int LASTUPDATELOGIN = 35;
    public static final int SUSTITUCIONSWITCHER = 36;
    public static final int MOSTRAR = 37;
    public static final int TALLAREADONLY = 38;
    public static final int OBSERVACIONESSLTUD = 39;
    public static final int MOTIVO = 40;
    public static final int TALLADEVID = 41;
    public static final int TALLANBRDEV = 42;
    public static final int QTYDEV = 43;
    public static final int OBSERVACIONESDEV = 44;
    public static final int INVENTORYIDDEV = 45;
    public static final int QUANTITYDELIVERED = 46;
    public static final int INVENTORYCOD = 47;
    public static final int DESCRIPTION = 48;
    public static final int MESDOTACION = 49;
    public static final int MONTHDOTA = 50;
    public static final int READONLYBOOL = 51;
    public static final int CHECKBOXSELECTED = 52;
    public static final int OBSERVACIONESDEVDISABLED = 53;
    public static final int CANTIDADDEVDISABLED = 54;
    public static final int TALLADEVDISABLED = 55;
    public static final int MOTIVODISABLED = 56;
    public static final int CHECKBOXDISABLED = 57;
    public static final int DETAILFLAG = 58;
    public static final int DUMMY = 59;
    public static final int XXGAMINVSOLIVO = 60;
    public static final int XXGAMINVSOLIDETAILVO = 61;

    /**This is the default constructor (do not remove)
     */
    public xXGamInvSoliDtlVORowImpl() {
    }

    /**Gets xXGamInvSoliDtlEO entity object.
     */
    public xXGamInvSoliDtlEOImpl getxXGamInvSoliDtlEO() {
        return (xXGamInvSoliDtlEOImpl)getEntity(0);
    }

    /**Gets the attribute value for SOLI_ID using the alias name SoliId
     */
    public Number getSoliId() {
        return (Number) getAttributeInternal(SOLIID);
    }

    /**Sets <code>value</code> as attribute value for SOLI_ID using the alias name SoliId
     */
    public void setSoliId(Number value) {
        setAttributeInternal(SOLIID, value);
    }

    /**Gets the attribute value for SOLI_DTL_ID using the alias name SoliDtlId
     */
    public Number getSoliDtlId() {
        return (Number) getAttributeInternal(SOLIDTLID);
    }

    /**Sets <code>value</code> as attribute value for SOLI_DTL_ID using the alias name SoliDtlId
     */
    public void setSoliDtlId(Number value) {
        setAttributeInternal(SOLIDTLID, value);
    }

    /**Gets the attribute value for the calculated attribute NroSolicitud
     */
    public String getNroSolicitud() {
        return (String) getAttributeInternal(NROSOLICITUD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute NroSolicitud
     */
    public void setNroSolicitud(String value) {
        setAttributeInternal(NROSOLICITUD, value);
    }

    /**Gets the attribute value for the calculated attribute KitId
     */
    public Number getKitId() {
        return (Number) getAttributeInternal(KITID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute KitId
     */
    public void setKitId(Number value) {
        setAttributeInternal(KITID, value);
    }

    /**Gets the attribute value for the calculated attribute KitCod
     */
    public String getKitCod() {
        return (String) getAttributeInternal(KITCOD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute KitCod
     */
    public void setKitCod(String value) {
        setAttributeInternal(KITCOD, value);
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

    /**Gets the attribute value for the calculated attribute HeaderId
     */
    public Number getHeaderId() {
        return (Number) getAttributeInternal(HEADERID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute HeaderId
     */
    public void setHeaderId(Number value) {
        setAttributeInternal(HEADERID, value);
    }

    /**Gets the attribute value for DOTA_ID using the alias name DotaId
     */
    public Number getDotaId() {
        return (Number) getAttributeInternal(DOTAID);
    }

    /**Sets <code>value</code> as attribute value for DOTA_ID using the alias name DotaId
     */
    public void setDotaId(Number value) {
        setAttributeInternal(DOTAID, value);
    }

    /**Gets the attribute value for the calculated attribute UniformTypeCod
     */
    public String getUniformTypeCod() {
        return (String) getAttributeInternal(UNIFORMTYPECOD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute UniformTypeCod
     */
    public void setUniformTypeCod(String value) {
        setAttributeInternal(UNIFORMTYPECOD, value);
    }

    /**Gets the attribute value for the calculated attribute Nomenclature
     */
    public String getNomenclature() {
        return (String) getAttributeInternal(NOMENCLATURE);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Nomenclature
     */
    public void setNomenclature(String value) {
        setAttributeInternal(NOMENCLATURE, value);
    }

    /**Gets the attribute value for the calculated attribute NpCod
     */
    public String getNpCod() {
        return (String) getAttributeInternal(NPCOD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute NpCod
     */
    public void setNpCod(String value) {
        setAttributeInternal(NPCOD, value);
    }

    /**Gets the attribute value for the calculated attribute MeasureUnitCod
     */
    public String getMeasureUnitCod() {
        return (String) getAttributeInternal(MEASUREUNITCOD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute MeasureUnitCod
     */
    public void setMeasureUnitCod(String value) {
        setAttributeInternal(MEASUREUNITCOD, value);
    }

    /**Gets the attribute value for the calculated attribute CycleCod
     */
    public String getCycleCod() {
        return (String) getAttributeInternal(CYCLECOD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CycleCod
     */
    public void setCycleCod(String value) {
        setAttributeInternal(CYCLECOD, value);
    }

    /**Gets the attribute value for the calculated attribute PlantaQty
     */
    public Number getPlantaQty() {
        return (Number) getAttributeInternal(PLANTAQTY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute PlantaQty
     */
    public void setPlantaQty(Number value) {
        setAttributeInternal(PLANTAQTY, value);
    }

    /**Gets the attribute value for the calculated attribute EventQty
     */
    public Number getEventQty() {
        return (Number) getAttributeInternal(EVENTQTY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute EventQty
     */
    public void setEventQty(Number value) {
        setAttributeInternal(EVENTQTY, value);
    }

    /**Gets the attribute value for QTY_PLANTA using the alias name QtyPlanta
     */
    public Number getQtyPlanta() {
        return (Number) getAttributeInternal(QTYPLANTA);
    }

    /**Sets <code>value</code> as attribute value for QTY_PLANTA using the alias name QtyPlanta
     */
    public void setQtyPlanta(Number value) {
        setAttributeInternal(QTYPLANTA, value);
    }

    /**Gets the attribute value for QTY_EVENTUAL using the alias name QtyEventual
     */
    public Number getQtyEventual() {
        return (Number) getAttributeInternal(QTYEVENTUAL);
    }

    /**Sets <code>value</code> as attribute value for QTY_EVENTUAL using the alias name QtyEventual
     */
    public void setQtyEventual(Number value) {
        setAttributeInternal(QTYEVENTUAL, value);
    }

    /**Gets the attribute value for TALLA_ID using the alias name TallaId
     */
    public Number getTallaId() {
        return (Number) getAttributeInternal(TALLAID);
    }

    /**Sets <code>value</code> as attribute value for TALLA_ID using the alias name TallaId
     */
    public void setTallaId(Number value) {
        setAttributeInternal(TALLAID, value);
    }

    /**Gets the attribute value for the calculated attribute TallaNbr
     */
    public String getTallaNbr() {
        return (String) getAttributeInternal(TALLANBR);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TallaNbr
     */
    public void setTallaNbr(String value) {
        setAttributeInternal(TALLANBR, value);
    }

    /**Gets the attribute value for QTY_NBR using the alias name QtyNbr
     */
    public Number getQtyNbr() {
        return (Number) getAttributeInternal(QTYNBR);
    }

    /**Sets <code>value</code> as attribute value for QTY_NBR using the alias name QtyNbr
     */
    public void setQtyNbr(Number value) {
        setAttributeInternal(QTYNBR, value);
    }

    /**Gets the attribute value for the calculated attribute InventoryId
     */
    public Number getInventoryId() {
        return (Number) getAttributeInternal(INVENTORYID);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute InventoryId
     */
    public void setInventoryId(Number value) {
        setAttributeInternal(INVENTORYID, value);
    }

    /**Gets the attribute value for LAST_DELIV_DATE using the alias name LastDelivDate
     */
    public Date getLastDelivDate() {
        return (Date) getAttributeInternal(LASTDELIVDATE);
    }

    /**Sets <code>value</code> as attribute value for LAST_DELIV_DATE using the alias name LastDelivDate
     */
    public void setLastDelivDate(Date value) {
        setAttributeInternal(LASTDELIVDATE, value);
    }

    /**Gets the attribute value for LINE_NUMBER using the alias name LineNumber
     */
    public Number getLineNumber() {
        return (Number) getAttributeInternal(LINENUMBER);
    }

    /**Sets <code>value</code> as attribute value for LINE_NUMBER using the alias name LineNumber
     */
    public void setLineNumber(Number value) {
        setAttributeInternal(LINENUMBER, value);
    }

    /**Gets the attribute value for STATUS using the alias name Status
     */
    public String getStatus() {
        return (String) getAttributeInternal(STATUS);
    }

    /**Sets <code>value</code> as attribute value for STATUS using the alias name Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**Gets the attribute value for the calculated attribute StatusDsp
     */
    public String getStatusDsp() {
        return (String) getAttributeInternal(STATUSDSP);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute StatusDsp
     */
    public void setStatusDsp(String value) {
        setAttributeInternal(STATUSDSP, value);
    }

    /**Gets the attribute value for DOTA_ID_F using the alias name DotaIdF
     */
    public Number getDotaIdF() {
        return (Number) getAttributeInternal(DOTAIDF);
    }

    /**Sets <code>value</code> as attribute value for DOTA_ID_F using the alias name DotaIdF
     */
    public void setDotaIdF(Number value) {
        setAttributeInternal(DOTAIDF, value);
    }

    /**Gets the attribute value for ATTRIBUTE1 using the alias name Attribute1
     */
    public String getAttribute1() {
        return (String) getAttributeInternal(ATTRIBUTE1);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE1 using the alias name Attribute1
     */
    public void setAttribute1(String value) {
        setAttributeInternal(ATTRIBUTE1, value);
    }

    /**Gets the attribute value for ATTRIBUTE2 using the alias name Attribute2
     */
    public String getAttribute2() {
        return (String) getAttributeInternal(ATTRIBUTE2);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE2 using the alias name Attribute2
     */
    public void setAttribute2(String value) {
        setAttributeInternal(ATTRIBUTE2, value);
    }

    /**Gets the attribute value for ATTRIBUTE3 using the alias name Attribute3
     */
    public String getAttribute3() {
        return (String) getAttributeInternal(ATTRIBUTE3);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE3 using the alias name Attribute3
     */
    public void setAttribute3(String value) {
        setAttributeInternal(ATTRIBUTE3, value);
    }

    /**Gets the attribute value for ATTRIBUTE4 using the alias name Attribute4
     */
    public String getAttribute4() {
        return (String) getAttributeInternal(ATTRIBUTE4);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE4 using the alias name Attribute4
     */
    public void setAttribute4(String value) {
        setAttributeInternal(ATTRIBUTE4, value);
    }

    /**Gets the attribute value for ATTRIBUTE5 using the alias name Attribute5
     */
    public String getAttribute5() {
        return (String) getAttributeInternal(ATTRIBUTE5);
    }

    /**Sets <code>value</code> as attribute value for ATTRIBUTE5 using the alias name Attribute5
     */
    public void setAttribute5(String value) {
        setAttributeInternal(ATTRIBUTE5, value);
    }

    /**Gets the attribute value for CREATED_BY using the alias name CreatedBy
     */
    public Number getCreatedBy() {
        return (Number) getAttributeInternal(CREATEDBY);
    }

    /**Sets <code>value</code> as attribute value for CREATED_BY using the alias name CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**Gets the attribute value for CREATION_DATE using the alias name CreationDate
     */
    public Date getCreationDate() {
        return (Date) getAttributeInternal(CREATIONDATE);
    }

    /**Sets <code>value</code> as attribute value for CREATION_DATE using the alias name CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**Gets the attribute value for LAST_UPDATED_BY using the alias name LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number) getAttributeInternal(LASTUPDATEDBY);
    }

    /**Sets <code>value</code> as attribute value for LAST_UPDATED_BY using the alias name LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**Gets the attribute value for LAST_UPDATE_DATE using the alias name LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date) getAttributeInternal(LASTUPDATEDATE);
    }

    /**Sets <code>value</code> as attribute value for LAST_UPDATE_DATE using the alias name LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**Gets the attribute value for LAST_UPDATE_LOGIN using the alias name LastUpdateLogin
     */
    public Number getLastUpdateLogin() {
        return (Number) getAttributeInternal(LASTUPDATELOGIN);
    }

    /**Sets <code>value</code> as attribute value for LAST_UPDATE_LOGIN using the alias name LastUpdateLogin
     */
    public void setLastUpdateLogin(Number value) {
        setAttributeInternal(LASTUPDATELOGIN, value);
    }

    /**Gets the attribute value for the calculated attribute SustitucionSwitcher
     */
    public String getSustitucionSwitcher() {
        return (String) getAttributeInternal(SUSTITUCIONSWITCHER);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute SustitucionSwitcher
     */
    public void setSustitucionSwitcher(String value) {
        setAttributeInternal(SUSTITUCIONSWITCHER, value);
    }

    /**Gets the attribute value for the calculated attribute Mostrar
     */
    public String getMostrar() {
        return (String) getAttributeInternal(MOSTRAR);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Mostrar
     */
    public void setMostrar(String value) {
        setAttributeInternal(MOSTRAR, value);
    }

    /**Gets the attribute value for the calculated attribute TallaReadonly
     */
    public String getTallaReadonly() {
        return (String) getAttributeInternal(TALLAREADONLY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TallaReadonly
     */
    public void setTallaReadonly(String value) {
        setAttributeInternal(TALLAREADONLY, value);
    }

    /**Gets the attribute value for OBSERVACIONES_SLTUD using the alias name ObservacionesSltud
     */
    public String getObservacionesSltud() {
        return (String) getAttributeInternal(OBSERVACIONESSLTUD);
    }

    /**Sets <code>value</code> as attribute value for OBSERVACIONES_SLTUD using the alias name ObservacionesSltud
     */
    public void setObservacionesSltud(String value) {
        setAttributeInternal(OBSERVACIONESSLTUD, value);
    }

    /**Gets the attribute value for MOTIVO using the alias name Motivo
     */
    public String getMotivo() {
        return (String) getAttributeInternal(MOTIVO);
    }

    /**Sets <code>value</code> as attribute value for MOTIVO using the alias name Motivo
     */
    public void setMotivo(String value) {
        setAttributeInternal(MOTIVO, value);
    }

    /**Gets the attribute value for TALLA_DEV_ID using the alias name TallaDevId
     */
    public Number getTallaDevId() {
        return (Number) getAttributeInternal(TALLADEVID);
    }

    /**Sets <code>value</code> as attribute value for TALLA_DEV_ID using the alias name TallaDevId
     */
    public void setTallaDevId(Number value) {
        setAttributeInternal(TALLADEVID, value);
    }

    /**Gets the attribute value for the calculated attribute TallaNbrDev
     */
    public String getTallaNbrDev() {
        return (String) getAttributeInternal(TALLANBRDEV);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TallaNbrDev
     */
    public void setTallaNbrDev(String value) {
        setAttributeInternal(TALLANBRDEV, value);
    }

    /**Gets the attribute value for QTY_DEV using the alias name QtyDev
     */
    public Number getQtyDev() {
        return (Number) getAttributeInternal(QTYDEV);
    }

    /**Sets <code>value</code> as attribute value for QTY_DEV using the alias name QtyDev
     */
    public void setQtyDev(Number value) {
        setAttributeInternal(QTYDEV, value);
    }

    /**Gets the attribute value for OBSERVACIONES_DEV using the alias name ObservacionesDev
     */
    public String getObservacionesDev() {
        return (String) getAttributeInternal(OBSERVACIONESDEV);
    }

    /**Sets <code>value</code> as attribute value for OBSERVACIONES_DEV using the alias name ObservacionesDev
     */
    public void setObservacionesDev(String value) {
        setAttributeInternal(OBSERVACIONESDEV, value);
    }

    /**Gets the attribute value for the calculated attribute InventoryIdDev
     */
    public Number getInventoryIdDev() {
        return (Number) getAttributeInternal(INVENTORYIDDEV);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute InventoryIdDev
     */
    public void setInventoryIdDev(Number value) {
        setAttributeInternal(INVENTORYIDDEV, value);
    }

    /**Gets the attribute value for the calculated attribute QuantityDelivered
     */
    public Number getQuantityDelivered() {
        return (Number) getAttributeInternal(QUANTITYDELIVERED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute QuantityDelivered
     */
    public void setQuantityDelivered(Number value) {
        setAttributeInternal(QUANTITYDELIVERED, value);
    }

    /**Gets the attribute value for the calculated attribute InventoryCod
     */
    public String getInventoryCod() {
        return (String) getAttributeInternal(INVENTORYCOD);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute InventoryCod
     */
    public void setInventoryCod(String value) {
        setAttributeInternal(INVENTORYCOD, value);
    }

    /**Gets the attribute value for the calculated attribute Description
     */
    public String getDescription() {
        return (String) getAttributeInternal(DESCRIPTION);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Description
     */
    public void setDescription(String value) {
        setAttributeInternal(DESCRIPTION, value);
    }

    /**Gets the attribute value for the calculated attribute MesDotacion
     */
    public String getMesDotacion() {
        return (String) getAttributeInternal(MESDOTACION);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute MesDotacion
     */
    public void setMesDotacion(String value) {
        setAttributeInternal(MESDOTACION, value);
    }

    /**Gets the attribute value for the calculated attribute MonthDota
     */
    public String getMonthDota() {
        return (String) getAttributeInternal(MONTHDOTA);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute MonthDota
     */
    public void setMonthDota(String value) {
        setAttributeInternal(MONTHDOTA, value);
    }

    /**Gets the attribute value for the calculated attribute ReadOnlyBool
     */
    public Boolean getReadOnlyBool() {
        return (Boolean) getAttributeInternal(READONLYBOOL);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ReadOnlyBool
     */
    public void setReadOnlyBool(Boolean value) {
        setAttributeInternal(READONLYBOOL, value);
    }

    /**Gets the attribute value for the calculated attribute CheckBoxSelected
     */
    public String getCheckBoxSelected() {
        return (String) getAttributeInternal(CHECKBOXSELECTED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CheckBoxSelected
     */
    public void setCheckBoxSelected(String value) {
        setAttributeInternal(CHECKBOXSELECTED, value);
    }

    /**Gets the attribute value for the calculated attribute ObservacionesDevDisabled
     */
    public Boolean getObservacionesDevDisabled() {
        return (Boolean) getAttributeInternal(OBSERVACIONESDEVDISABLED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute ObservacionesDevDisabled
     */
    public void setObservacionesDevDisabled(Boolean value) {
        setAttributeInternal(OBSERVACIONESDEVDISABLED, value);
    }

    /**Gets the attribute value for the calculated attribute CantidadDevDisabled
     */
    public Boolean getCantidadDevDisabled() {
        return (Boolean) getAttributeInternal(CANTIDADDEVDISABLED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CantidadDevDisabled
     */
    public void setCantidadDevDisabled(Boolean value) {
        setAttributeInternal(CANTIDADDEVDISABLED, value);
    }

    /**Gets the attribute value for the calculated attribute TallaDevDisabled
     */
    public Boolean getTallaDevDisabled() {
        return (Boolean) getAttributeInternal(TALLADEVDISABLED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute TallaDevDisabled
     */
    public void setTallaDevDisabled(Boolean value) {
        setAttributeInternal(TALLADEVDISABLED, value);
    }

    /**Gets the attribute value for the calculated attribute MotivoDisabled
     */
    public Boolean getMotivoDisabled() {
        return (Boolean) getAttributeInternal(MOTIVODISABLED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute MotivoDisabled
     */
    public void setMotivoDisabled(Boolean value) {
        setAttributeInternal(MOTIVODISABLED, value);
    }

    /**Gets the attribute value for the calculated attribute CheckBoxDisabled
     */
    public Boolean getCheckBoxDisabled() {
        return (Boolean) getAttributeInternal(CHECKBOXDISABLED);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute CheckBoxDisabled
     */
    public void setCheckBoxDisabled(Boolean value) {
        setAttributeInternal(CHECKBOXDISABLED, value);
    }

    /**Gets the attribute value for the calculated attribute DetailFlag
     */
    public Boolean getDetailFlag() {
        return (Boolean) getAttributeInternal(DETAILFLAG);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute DetailFlag
     */
    public void setDetailFlag(Boolean value) {
        setAttributeInternal(DETAILFLAG, value);
    }

    /**Gets the attribute value for the calculated attribute Dummy
     */
    public String getDummy() {
        return (String) getAttributeInternal(DUMMY);
    }

    /**Sets <code>value</code> as the attribute value for the calculated attribute Dummy
     */
    public void setDummy(String value) {
        setAttributeInternal(DUMMY, value);
    }

    /**Gets the associated <code>Row</code> using master-detail link xXGamInvSoliVO
     */
    public Row getxXGamInvSoliVO() {
        return (Row)getAttributeInternal(XXGAMINVSOLIVO);
    }

    /**Gets the associated <code>RowIterator</code> using master-detail link XxgamInvSoliDetailVO
     */
    public RowIterator getXxgamInvSoliDetailVO() {
        return (RowIterator)getAttributeInternal(XXGAMINVSOLIDETAILVO);
    }

    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case SOLIID:
            return getSoliId();
        case SOLIDTLID:
            return getSoliDtlId();
        case NROSOLICITUD:
            return getNroSolicitud();
        case KITID:
            return getKitId();
        case KITCOD:
            return getKitCod();
        case PERSONID:
            return getPersonId();
        case HEADERID:
            return getHeaderId();
        case DOTAID:
            return getDotaId();
        case UNIFORMTYPECOD:
            return getUniformTypeCod();
        case NOMENCLATURE:
            return getNomenclature();
        case NPCOD:
            return getNpCod();
        case MEASUREUNITCOD:
            return getMeasureUnitCod();
        case CYCLECOD:
            return getCycleCod();
        case PLANTAQTY:
            return getPlantaQty();
        case EVENTQTY:
            return getEventQty();
        case QTYPLANTA:
            return getQtyPlanta();
        case QTYEVENTUAL:
            return getQtyEventual();
        case TALLAID:
            return getTallaId();
        case TALLANBR:
            return getTallaNbr();
        case QTYNBR:
            return getQtyNbr();
        case INVENTORYID:
            return getInventoryId();
        case LASTDELIVDATE:
            return getLastDelivDate();
        case LINENUMBER:
            return getLineNumber();
        case STATUS:
            return getStatus();
        case STATUSDSP:
            return getStatusDsp();
        case DOTAIDF:
            return getDotaIdF();
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
        case CREATEDBY:
            return getCreatedBy();
        case CREATIONDATE:
            return getCreationDate();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case SUSTITUCIONSWITCHER:
            return getSustitucionSwitcher();
        case MOSTRAR:
            return getMostrar();
        case TALLAREADONLY:
            return getTallaReadonly();
        case OBSERVACIONESSLTUD:
            return getObservacionesSltud();
        case MOTIVO:
            return getMotivo();
        case TALLADEVID:
            return getTallaDevId();
        case TALLANBRDEV:
            return getTallaNbrDev();
        case QTYDEV:
            return getQtyDev();
        case OBSERVACIONESDEV:
            return getObservacionesDev();
        case INVENTORYIDDEV:
            return getInventoryIdDev();
        case QUANTITYDELIVERED:
            return getQuantityDelivered();
        case INVENTORYCOD:
            return getInventoryCod();
        case DESCRIPTION:
            return getDescription();
        case MESDOTACION:
            return getMesDotacion();
        case MONTHDOTA:
            return getMonthDota();
        case READONLYBOOL:
            return getReadOnlyBool();
        case CHECKBOXSELECTED:
            return getCheckBoxSelected();
        case OBSERVACIONESDEVDISABLED:
            return getObservacionesDevDisabled();
        case CANTIDADDEVDISABLED:
            return getCantidadDevDisabled();
        case TALLADEVDISABLED:
            return getTallaDevDisabled();
        case MOTIVODISABLED:
            return getMotivoDisabled();
        case CHECKBOXDISABLED:
            return getCheckBoxDisabled();
        case DETAILFLAG:
            return getDetailFlag();
        case DUMMY:
            return getDummy();
        case XXGAMINVSOLIVO:
            return getxXGamInvSoliVO();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case SOLIID:
            setSoliId((Number)value);
            return;
        case SOLIDTLID:
            setSoliDtlId((Number)value);
            return;
        case NROSOLICITUD:
            setNroSolicitud((String)value);
            return;
        case KITID:
            setKitId((Number)value);
            return;
        case KITCOD:
            setKitCod((String)value);
            return;
        case PERSONID:
            setPersonId((Number)value);
            return;
        case HEADERID:
            setHeaderId((Number)value);
            return;
        case DOTAID:
            setDotaId((Number)value);
            return;
        case UNIFORMTYPECOD:
            setUniformTypeCod((String)value);
            return;
        case NOMENCLATURE:
            setNomenclature((String)value);
            return;
        case NPCOD:
            setNpCod((String)value);
            return;
        case MEASUREUNITCOD:
            setMeasureUnitCod((String)value);
            return;
        case CYCLECOD:
            setCycleCod((String)value);
            return;
        case PLANTAQTY:
            setPlantaQty((Number)value);
            return;
        case EVENTQTY:
            setEventQty((Number)value);
            return;
        case QTYPLANTA:
            setQtyPlanta((Number)value);
            return;
        case QTYEVENTUAL:
            setQtyEventual((Number)value);
            return;
        case TALLAID:
            setTallaId((Number)value);
            return;
        case TALLANBR:
            setTallaNbr((String)value);
            return;
        case QTYNBR:
            setQtyNbr((Number)value);
            return;
        case INVENTORYID:
            setInventoryId((Number)value);
            return;
        case LASTDELIVDATE:
            setLastDelivDate((Date)value);
            return;
        case LINENUMBER:
            setLineNumber((Number)value);
            return;
        case STATUS:
            setStatus((String)value);
            return;
        case STATUSDSP:
            setStatusDsp((String)value);
            return;
        case DOTAIDF:
            setDotaIdF((Number)value);
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
        case CREATEDBY:
            setCreatedBy((Number)value);
            return;
        case CREATIONDATE:
            setCreationDate((Date)value);
            return;
        case LASTUPDATEDBY:
            setLastUpdatedBy((Number)value);
            return;
        case LASTUPDATEDATE:
            setLastUpdateDate((Date)value);
            return;
        case LASTUPDATELOGIN:
            setLastUpdateLogin((Number)value);
            return;
        case SUSTITUCIONSWITCHER:
            setSustitucionSwitcher((String)value);
            return;
        case MOSTRAR:
            setMostrar((String)value);
            return;
        case TALLAREADONLY:
            setTallaReadonly((String)value);
            return;
        case OBSERVACIONESSLTUD:
            setObservacionesSltud((String)value);
            return;
        case MOTIVO:
            setMotivo((String)value);
            return;
        case TALLADEVID:
            setTallaDevId((Number)value);
            return;
        case TALLANBRDEV:
            setTallaNbrDev((String)value);
            return;
        case QTYDEV:
            setQtyDev((Number)value);
            return;
        case OBSERVACIONESDEV:
            setObservacionesDev((String)value);
            return;
        case INVENTORYIDDEV:
            setInventoryIdDev((Number)value);
            return;
        case QUANTITYDELIVERED:
            setQuantityDelivered((Number)value);
            return;
        case INVENTORYCOD:
            setInventoryCod((String)value);
            return;
        case DESCRIPTION:
            setDescription((String)value);
            return;
        case MESDOTACION:
            setMesDotacion((String)value);
            return;
        case MONTHDOTA:
            setMonthDota((String)value);
            return;
        case READONLYBOOL:
            setReadOnlyBool((Boolean)value);
            return;
        case CHECKBOXSELECTED:
            setCheckBoxSelected((String)value);
            return;
        case OBSERVACIONESDEVDISABLED:
            setObservacionesDevDisabled((Boolean)value);
            return;
        case CANTIDADDEVDISABLED:
            setCantidadDevDisabled((Boolean)value);
            return;
        case TALLADEVDISABLED:
            setTallaDevDisabled((Boolean)value);
            return;
        case MOTIVODISABLED:
            setMotivoDisabled((Boolean)value);
            return;
        case CHECKBOXDISABLED:
            setCheckBoxDisabled((Boolean)value);
            return;
        case DETAILFLAG:
            setDetailFlag((Boolean)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }
}
