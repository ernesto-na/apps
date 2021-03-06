package xxgam.oracle.apps.xbol.maf.server;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAEntityDefImpl;
import oracle.apps.fnd.framework.server.OAEntityImpl;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.TransactionEvent;

import xxgam.oracle.apps.xbol.maf.utils.XxGamConstantsUtil;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaFlightInf0EOImpl extends OAEntityImpl {
    public static final int ID = 0;
    public static final int TICKETPID = 1;
    public static final int TRAVELINGFROM = 2;
    public static final int TRAVELINGTO = 3;
    public static final int DEPARTUREDATE = 4;
    public static final int RETURNDATE = 5;
    public static final int CREATIONDATE = 6;
    public static final int CREATEDBY = 7;
    public static final int LASTUPDATEDATE = 8;
    public static final int LASTUPDATELOGIN = 9;
    public static final int LASTUPDATEDBY = 10;
    public static final int XXGAMMATICKETPEO = 11;
    public static final int XXGAMMARUTEFLIGHTEO1 = 12;
    public static final int XXGAMMARUTEFLIGHTEO = 13;


    public void postChanges(TransactionEvent transactionEvent) {
        XxGamMaTicketPEOImpl ticketP = getXxGamMaTicketPEO();
        if (ticketP != null && 
            (ticketP.getPostState() == STATUS_NEW || ticketP.getPostState() == 
             STATUS_MODIFIED)) {
            ticketP.postChanges(transactionEvent);
        }
        super.postChanges(transactionEvent);
    }

    protected void validateEntity() {
        String isValidate = 
            (String)getOADBTransaction().getTransientValue("IsValidateEntityFlight");
        if (isValidate == null || !"false".equals(isValidate)) {
            super.validateEntity();
        }
    }


    private static OAEntityDefImpl mDefinitionObject;

    /**This is the default constructor (do not remove)
     */
    public XxGamMaFlightInf0EOImpl() {
    }


    /**Retrieves the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = 
                    (OAEntityDefImpl)EntityDefImpl.findDefObject("xxgam.oracle.apps.xbol.maf.server.XxGamMaFlightInf0EO");
        }
        return mDefinitionObject;
    }

    /**Gets the attribute value for Id, using the alias name Id
     */
    public Number getId() {
        return (Number)getAttributeInternal(ID);
    }

    /**Sets <code>value</code> as the attribute value for Id
     */
    public void setId(Number value) {
        setAttributeInternal(ID, value);
    }

    /**Gets the attribute value for TicketPId, using the alias name TicketPId
     */
    public Number getTicketPId() {
        return (Number)getAttributeInternal(TICKETPID);
    }

    /**Sets <code>value</code> as the attribute value for TicketPId
     */
    public void setTicketPId(Number value) {
        setAttributeInternal(TICKETPID, value);
    }

    /**Gets the attribute value for TravelingFrom, using the alias name TravelingFrom
     */
    public String getTravelingFrom() {
        return (String)getAttributeInternal(TRAVELINGFROM);
    }

    /**Sets <code>value</code> as the attribute value for TravelingFrom
     */
    public void setTravelingFrom(String value) {
        String travTo = getTravelingTo();
        if (travTo != null) {
            if (value.equals(travTo)) {
                throw new OAException("El campo Traveling From no puede ser igual al campo Traveling To", 
                                      OAException.INFORMATION);
            } else {
                setAttributeInternal(TRAVELINGFROM, value);
            }
        } else {
            setAttributeInternal(TRAVELINGFROM, value);
        }

    }

    /**Gets the attribute value for TravelingTo, using the alias name TravelingTo
     */
    public String getTravelingTo() {
        return (String)getAttributeInternal(TRAVELINGTO);
    }

    /**Sets <code>value</code> as the attribute value for TravelingTo
     */
    public void setTravelingTo(String value) {
        String travFrom = getTravelingFrom();
        if (travFrom != null) {
            if (value.equals(travFrom)) {

                throw new OAException("El campo Traveling From no puede ser igual al campo Traveling To", 
                                      OAException.INFORMATION);

            } else {
                setAttributeInternal(TRAVELINGTO, value);
            }
        } else {

        }
        setAttributeInternal(TRAVELINGTO, value);
    }

    /**Gets the attribute value for DepartureDate, using the alias name DepartureDate
     */
    public Date getDepartureDate() {
        return (Date)getAttributeInternal(DEPARTUREDATE);
    }

    /**Sets <code>value</code> as the attribute value for DepartureDate
     */
    public void setDepartureDate(Date value) {
        setAttributeInternal(DEPARTUREDATE, value);
    }

    /**Gets the attribute value for ReturnDate, using the alias name ReturnDate
     */
    public Date getReturnDate() {
        return (Date)getAttributeInternal(RETURNDATE);
    }

    /**Sets <code>value</code> as the attribute value for ReturnDate
     */
    public void setReturnDate(Date value) {
        setAttributeInternal(RETURNDATE, value);
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


    /**getAttrInvokeAccessor: generated method. Do not modify.
     */
    protected Object getAttrInvokeAccessor(int index, 
                                           AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case ID:
            return getId();
        case TICKETPID:
            return getTicketPId();
        case TRAVELINGFROM:
            return getTravelingFrom();
        case TRAVELINGTO:
            return getTravelingTo();
        case DEPARTUREDATE:
            return getDepartureDate();
        case RETURNDATE:
            return getReturnDate();
        case CREATIONDATE:
            return getCreationDate();
        case CREATEDBY:
            return getCreatedBy();
        case LASTUPDATEDATE:
            return getLastUpdateDate();
        case LASTUPDATELOGIN:
            return getLastUpdateLogin();
        case LASTUPDATEDBY:
            return getLastUpdatedBy();
        case XXGAMMATICKETPEO:
            return getXxGamMaTicketPEO();
        case XXGAMMARUTEFLIGHTEO:
            return getXxGamMaRuteFlightEO();
        case XXGAMMARUTEFLIGHTEO1:
            return getXxGamMaRuteFlightEO1();
        default:
            return super.getAttrInvokeAccessor(index, attrDef);
        }
    }

    /**setAttrInvokeAccessor: generated method. Do not modify.
     */
    protected void setAttrInvokeAccessor(int index, Object value, 
                                         AttributeDefImpl attrDef) throws Exception {
        switch (index) {
        case ID:
            setId((Number)value);
            return;
        case TICKETPID:
            setTicketPId((Number)value);
            return;
        case TRAVELINGFROM:
            setTravelingFrom((String)value);
            return;
        case TRAVELINGTO:
            setTravelingTo((String)value);
            return;
        case DEPARTUREDATE:
            setDepartureDate((Date)value);
            return;
        case RETURNDATE:
            setReturnDate((Date)value);
            return;
        case CREATIONDATE:
            setCreationDate((Date)value);
            return;
        case CREATEDBY:
            setCreatedBy((Number)value);
            return;
        default:
            super.setAttrInvokeAccessor(index, value, attrDef);
            return;
        }
    }

    /**Gets the associated entity XxGamMaTicketPEOImpl
     */
    public XxGamMaTicketPEOImpl getXxGamMaTicketPEO() {
        return (XxGamMaTicketPEOImpl)getAttributeInternal(XXGAMMATICKETPEO);
    }

    /**Sets <code>value</code> as the associated entity XxGamMaTicketPEOImpl
     */
    public void setXxGamMaTicketPEO(XxGamMaTicketPEOImpl value) {
        setAttributeInternal(XXGAMMATICKETPEO, value);
    }


    public void setLastUpdatedBy(Number number) {
    }

    /**Add attribute defaulting logic in this method.
     */
    public void create(AttributeList attributeList) {


        //Obtiene la clave primaria
        Number flightId = null;
        flightId = 
                getOADBTransaction().getSequenceValue(XxGamConstantsUtil.XXGAM_MA_FLIGHT_INF0_S);

        //Inicializa la clave primaria
        if (flightId != null)
            setId(flightId);
        System.out.println("FLICH  " + flightId);
        super.create(attributeList);
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

    /**Gets the attribute value for LastUpdatedBy, using the alias name LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }


    /**
     * Validation method for DepartureDate
     */
    public boolean validateDepartureDate(Date data) {

        //Inicializa los valores
        boolean isValido = false;
        Date dDateReturn = null;
        dDateReturn = getReturnDate();

        //Verifica nulidad
        if (dDateReturn == null)
            return isValido;

        //Verifica si cumple con la condición
        if (data.compareTo(dDateReturn) == -1 || 
            data.compareTo(dDateReturn) == 0)
            isValido = true;

        //Regresa la respuesta
        return isValido;
    }


    /**Gets the associated entity XxGamMaRuteFlightEOImpl
     */
    public XxGamMaRuteFlightEOImpl getXxGamMaRuteFlightEO1() {
        return (XxGamMaRuteFlightEOImpl)getAttributeInternal(XXGAMMARUTEFLIGHTEO1);
    }

    /**Sets <code>value</code> as the associated entity XxGamMaRuteFlightEOImpl
     */
    public void setXxGamMaRuteFlightEO1(XxGamMaRuteFlightEOImpl value) {
        setAttributeInternal(XXGAMMARUTEFLIGHTEO1, value);
    }

    /**Gets the associated entity XxGamMaRuteFlightEOImpl
     */
    public XxGamMaRuteFlightEOImpl getXxGamMaRuteFlightEO() {
        return (XxGamMaRuteFlightEOImpl)getAttributeInternal(XXGAMMARUTEFLIGHTEO);
    }

    /**Sets <code>value</code> as the associated entity XxGamMaRuteFlightEOImpl
     */
    public void setXxGamMaRuteFlightEO(XxGamMaRuteFlightEOImpl value) {
        setAttributeInternal(XXGAMMARUTEFLIGHTEO, value);
    }

    /**Creates a Key object based on given key constituents
     */
    public static Key createPrimaryKey(Number id) {
        return new Key(new Object[] { id });
    }
}
