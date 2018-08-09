package xxgam.oracle.apps.xbol.maf.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.Row;
import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import xxgam.oracle.apps.xbol.maf.utils.XxGamConstantsUtil;


// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamMaGeneralAndTicketPVOImpl extends OAViewObjectImpl {
    /**This is the default constructor (do not remove)
     */
    public XxGamMaGeneralAndTicketPVOImpl() {
    }

    /**
     * Busca las solicitudes de boletos de avión.
     * 
     * @param nameRequester Nombre del solicitante.
     * @param numberPayment contiene el numero de documento de la solicitud de anticipo
     * @param typeEm Tipo de emisión.
     * @param statusReq Estatus de la solicitud.
     * @param fromDate Fecha inicio.
     * @param toDate Fecha fin.
     * @param officeUser contiene nombre de usuario de oficina de boletos
     * @param statusNotiCode contiene codigo del estatus de notificacion
     */
    public void searchTicket(String nameRequester, String numberPayment, 
                             String typeEm, String statusReq, Date fromDate, 
                             Date toDate, String officeUser, 
                             String statusNotiCode) {

        //Verifica si existe el aprovador
        if (officeUser == null)
            return;

        //setpOfficeUser(officeUser);  

        //Declaración de los recursos
        ViewCriteria vcSolicitudes = null;
        ViewCriteriaRow vcSolicitudesRow = null;

        //Crea el view criteria
        vcSolicitudes = createViewCriteria();
        vcSolicitudesRow = vcSolicitudes.createViewCriteriaRow();

        //Inicializa el valor del nombre del solicitante
        if (nameRequester != null && !nameRequester.equals(""))
            vcSolicitudesRow.setAttribute("RequesterName", 
                                          XxGamConstantsUtil.LIKE_OPEN + 
                                          nameRequester + 
                                          XxGamConstantsUtil.LIKE_CLOSE);


        //Inicializa el valor del numero de documento
        if (numberPayment != null && !numberPayment.equals(""))
            vcSolicitudesRow.setAttribute("NumberPayment", 
                                          XxGamConstantsUtil.LIKE_OPEN + 
                                          numberPayment + 
                                          XxGamConstantsUtil.LIKE_CLOSE);

        //Inicializa el valor del tipo de emisión
        if (typeEm != null && !typeEm.equals(""))
            vcSolicitudesRow.setAttribute("TypeEmission", 
                                          XxGamConstantsUtil.LIKE_OPEN + 
                                          typeEm + 
                                          XxGamConstantsUtil.LIKE_CLOSE);

        //Inicializa el valor del status
        if (statusReq != null && !statusReq.equals(""))
            vcSolicitudesRow.setAttribute("StatusRequest", 
                                          XxGamConstantsUtil.LIKE_OPEN + 
                                          statusReq + 
                                          XxGamConstantsUtil.LIKE_CLOSE);

        //Inicializa las fechas
        if (fromDate != null && toDate != null)
            vcSolicitudesRow.setAttribute("RequestPaymentDate", 
                                          "BETWEEN " + getClausuleWhereDate(fromDate) + 
                                          " AND " + 
                                          getClausuleWhereDate(toDate));

        if (statusNotiCode != null && !"".equals(statusNotiCode)) {
            vcSolicitudesRow.setAttribute("StatusNotification", 
                                          XxGamConstantsUtil.LIKE_OPEN + 
                                          statusNotiCode + 
                                          XxGamConstantsUtil.LIKE_CLOSE);
        }

        //Aplica el criteria y ejecuta los criterios
        vcSolicitudes.addElement(vcSolicitudesRow);
        applyViewCriteria(vcSolicitudes);
        executeQuery();
        clearViewCriterias();
    }

    /**
     * Construye la clausula where.
     *
     * @param dDate Fecha.
     * @return clausula where.
     */
    private String getClausuleWhereDate(Date dDate) {

        if (dDate == null)
            return null;

        //Construye la clausula where
        String sClausulePart1 = null;
        String sClausulePart2 = null;
        String sClausuleWhere = null;
        sClausulePart1 = "TO_CHAR(to_DATE('";
        sClausulePart2 = "','yyyy-mm-dd'),'yyyy-mm-dd')";


        //Construye la clausula y la regresa
        sClausuleWhere = sClausulePart1 + dDate + sClausulePart2;
        return sClausuleWhere;
    }

    public void cancelFlightFranchise(Number FlightId) {
        Row[] cancelRows = null;
        cancelRows = getFilteredRows("Id", FlightId);
        if (cancelRows != null) {
            if (cancelRows.length > 0) {
                cancelRows[0].setAttribute("StatusNotification", "CAN");
            }
        }

    }

    public String findGeneralReqIdF() {
        String GeneralReqId = null;
        XxGamMaGeneralAndTicketPVORowImpl voXxGamMaGeneralAndTicketP = 
            (XxGamMaGeneralAndTicketPVORowImpl)getCurrentRow();

        if (voXxGamMaGeneralAndTicketP != null)
            GeneralReqId = 
                    voXxGamMaGeneralAndTicketP.getGeneralReqId().toString();

        return GeneralReqId;
    }


    /**10Jul2015
     * Metodo sobrecargado para agregar el criterio de busqueda Unidad Operativa y tipos de Franquicias  
     * @param nameRequester
     * @param numberPayment
     * @param typeEm
     * @param statusReq
     * @param fromDate
     * @param toDate
     * @param officeUser
     * @param statusNotiCode
     * @param operatingUnit
     * @param franchiseType
     * @param requestType
     */
    void searchTicket(String nameRequester, String numberPayment, 
                      String typeEm, String statusReq, Date fromDate, 
                      Date toDate, String officeUser, String statusNotiCode, 
                      String operatingUnit, String franchiseType, 
                      String requestType) {
        //Verifica si existe el aprovador
        if (officeUser == null)
            return;

        //setpOfficeUser(officeUser);  

        //Declaración de los recursos
        ViewCriteria vcSolicitudes = null;
        ViewCriteriaRow vcSolicitudesRow = null;

        //Crea el view criteria
        vcSolicitudes = createViewCriteria();
        vcSolicitudesRow = vcSolicitudes.createViewCriteriaRow();

        //Inicializa el valor del nombre del solicitante
        if (nameRequester != null && !nameRequester.equals(""))
            vcSolicitudesRow.setAttribute("RequesterName", 
                                          XxGamConstantsUtil.LIKE_OPEN + 
                                          nameRequester + 
                                          XxGamConstantsUtil.LIKE_CLOSE);


        //Inicializa el valor del numero de documento
        if (numberPayment != null && !numberPayment.equals(""))
            vcSolicitudesRow.setAttribute("NumberPayment", 
                                          XxGamConstantsUtil.LIKE_OPEN + 
                                          numberPayment + 
                                          XxGamConstantsUtil.LIKE_CLOSE);

        //Inicializa el valor del tipo de emisión
        if (typeEm != null && !typeEm.equals(""))
            vcSolicitudesRow.setAttribute("TypeEmission", 
                                          XxGamConstantsUtil.LIKE_OPEN + 
                                          typeEm + 
                                          XxGamConstantsUtil.LIKE_CLOSE);

        //Inicializa el valor del status
        if (statusReq != null && !statusReq.equals(""))
            vcSolicitudesRow.setAttribute("StatusRequest", 
                                          XxGamConstantsUtil.LIKE_OPEN + 
                                          statusReq + 
                                          XxGamConstantsUtil.LIKE_CLOSE);

        //Inicializa las fechas
        if (fromDate != null && toDate != null)
            vcSolicitudesRow.setAttribute("RequestPaymentDate", 
                                          "BETWEEN " + getClausuleWhereDate(fromDate) + 
                                          " AND " + 
                                          getClausuleWhereDate(toDate));

        if (statusNotiCode != null && !"".equals(statusNotiCode)) {
            vcSolicitudesRow.setAttribute("StatusNotification", 
                                          XxGamConstantsUtil.LIKE_OPEN + 
                                          statusNotiCode + 
                                          XxGamConstantsUtil.LIKE_CLOSE);
        }

        if (operatingUnit != null && !"".equals(operatingUnit)) {
            vcSolicitudesRow.setAttribute("OperatingUnit", 
                                          XxGamConstantsUtil.LIKE_OPEN + 
                                          operatingUnit + 
                                          XxGamConstantsUtil.LIKE_CLOSE);
        }

        if (franchiseType != null && !"".equals(franchiseType)) {
            vcSolicitudesRow.setAttribute("Origin", 
                                          XxGamConstantsUtil.LIKE_OPEN + 
                                          franchiseType + 
                                          XxGamConstantsUtil.LIKE_CLOSE);
        }

        //Aplica el criteria y ejecuta los criterios
        vcSolicitudes.addElement(vcSolicitudesRow);
        applyViewCriteria(vcSolicitudes);
        executeQuery();
        clearViewCriterias();

    }

}
