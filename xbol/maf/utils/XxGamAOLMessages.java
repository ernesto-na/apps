package xxgam.oracle.apps.xbol.maf.utils;

/**
 * La clase <class>XxGamAOLMessages</class> contiene las constantes
 * de los AOL menssages.
 *
 * @author Manuel Guinto.
 */
public class XxGamAOLMessages {

    /**
     * Constructor por defecto.
     */
    public XxGamAOLMessages() {
    }

    public static class Validation {

        /**
         * Clave de mensaje para No se pudo obtener el origen del aprobador
         */
        public static final String XXGAM_MAF_APPROVER_NF_ERROR = "XXGAM_MAF_APPROVER_NF_ERROR";

        /**
         * Clave de mensaje para Error al inicializar la solicitud de anticipos, intente nuevamente
         */
        public static final String XXGAM_MAF_REQ_INIT_ERROR = "XXGAM_MAF_REQ_INIT_ERROR";

        /**
         * Clave de mensaje para La solicitud de anticipo no es editable
         */
        public static final String XXGAM_MAF_REQ_NOT_EDIT_WARN = "XXGAM_MAF_REQ_NOT_EDIT_WARN";

        /**
         * Clave de mensaje para Acceso denegado por acción no definida
         */
        public static final String XXGAM_MAF_ACCESS_DEN_NA_ERROR = "XXGAM_MAF_ACCESS_DEN_NA_ERROR";

        /**
         * Clave de mensaje para Acceso denegado por uso de responsabilidad no autorizada
         */
        public static final String XXGAM_MAF_ACCESS_DEN_NR_ERROR = "XXGAM_MAF_ACCESS_DEN_NR_ERROR";

        /**
         * Clave de mensaje para Error al inicializar la lista de valores para centro de costos
         */
        public static final String XXGAM_MAF_CC_LOV_INIT_ERROR = "XXGAM_MAF_CC_LOV_INIT_ERROR";

        /**
         * Clave de mensaje para No se encontraron registros para centro de costos flexible
         */
        public static final String XXGAM_MAF_CCF_LOV_NF_ERROR = "XXGAM_MAF_CCF_LOV_NF_ERROR";

        /**
         * Clave de mensaje para No se encontraron registros para plantilla de anticipos
         */
        public static final String XXGAM_MAF_TPT_LOV_NF_ERROR = "XXGAM_MAF_TPT_LOV_NF_ERROR";

        /**
         * Clave de mensaje para Error al inicializar la lista de valores para aprobadores
         */
        public static final String XXGAM_MAF_APH_LOV_INIT_ERROR = "XXGAM_MAF_APH_LOV_INIT_ERROR";

        /**
         * Clave de mensaje para Error al inicializar el detalle de la solicitud de anticipos, no se realizaron cambios, intente nuevamente
         */
        public static final String XXGAM_MAF_REQ_DTAIL_INIT_ERROR = "XXGAM_MAF_REQ_DTAIL_INIT_ERROR";

        /**
         * Clave de mensaje para No se encontraron tipos de anticipos para la plantilla de anticipos
         */
        public static final String XXGAM_MAF_REQ_TYPEPA_NF_ERROR = "XXGAM_MAF_REQ_TYPEPA_NF_ERROR";

        /**
         * Clave de mensaje para La fecha fin no puede ser menor a la fecha inicio
         */
        public static final String XXGAM_MAF_DETAIL_VAL_FH_INIFIN = "XXGAM_MAF_DETAIL_VAL_FH_INIFIN";

        /**
         * Clave de mensaje para La fecha inicio y fecha fin deben ser mayor o igual a la fecha actual
         */
        public static final String XXGAM_MAF_DETAIL_VAL_CURR_DATE = "XXGAM_MAF_DETAIL_VAL_CURR_DATE";

        /**
         * Clave de mensaje para Las lineas de anticipo no pueden estar repetidas
         */
        public static final String XXGAM_MAF_DETAIL_VAL_REPEAT = "XXGAM_MAF_DETAIL_VAL_REPEAT";

        /**
         * Clave de mensaje para Falta de divisa
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_CAL_1 = "XXGAM_MAF_DETAIL_VALLINE_CAL_1";

        /**
         * Clave de mensaje para La divisa no esta configurada para esta política
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_CAL_2 = "XXGAM_MAF_DETAIL_VALLINE_CAL_2";

        /**
         * Clave de mensaje para Fondos insuficientes
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_CAL_3 = "XXGAM_MAF_DETAIL_VALLINE_CAL_3";

        /**
         * Clave de mensaje para Inexistencia de flexfield descriptivo para el segmento cuenta
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_CAL_4 = "XXGAM_MAF_DETAIL_VALLINE_CAL_4";

        /**
         * Clave de mensaje para Monto inválido
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_CAL_5 = "XXGAM_MAF_DETAIL_VALLINE_CAL_5";

        /**
         * Clave de mensaje para Error inesperado
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_CAL_6 = "XXGAM_MAF_DETAIL_VALLINE_CAL_6";

        /**
         * Clave de mensaje para Ocurrio un error al llamar el procedimiento de validación
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_CAL_7 = "XXGAM_MAF_DETAIL_VALLINE_CAL_7";

        /**
         * Clave de mensaje para El importe calculado del anticipo es negativo
         */
        public static final String XXGAM_MAF_DETAIL_AMOUNT_NEG = "XXGAM_MAF_DETAIL_AMOUNT_NEG";

        /**
         * Clave de mensaje para El importe calculado del anticipo es nullo
         */
        public static final String XXGAM_MAF_DETAIL_AMOUNT_NULL = "XXGAM_MAF_DETAIL_AMOUNT_NULL";

        /**
         * Clave de mensaje para No se encontró cuenta contable para este tipo de anticipo
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_NCAL1 = "XXGAM_MAF_DETAIL_VALLINE_NCAL1";

        /**
         * Clave de mensaje para No se encontró flexfield descriptivo
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_NCAL2 = "XXGAM_MAF_DETAIL_VALLINE_NCAL2";

        /**
         * Clave de mensaje para La divisa no está configurada para esta política
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_NCAL3 = "XXGAM_MAF_DETAIL_VALLINE_NCAL3";

        /**
         * Clave de mensaje para Fondos insuficientes
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_NCAL4 = "XXGAM_MAF_DETAIL_VALLINE_NCAL4";

        /**
         * Clave de mensaje para Línea no validada, no se pudo obtener descripción del tipo de anticipo
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_TD_NF = "XXGAM_MAF_DETAIL_VALLINE_TD_NF";

        /**
         * Clave de mensaje para Línea no validada, tipo de anticipo de anticipo no encontrado
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_T_NF = "XXGAM_MAF_DETAIL_VALLINE_T_NF";

        /**
         * Clave de mensaje para Línea no validada, no se pudo obtener los datos de la plantilla de anticipo
         */
        public static final String XXGAM_MAF_DETAIL_VALLIN_TP_NF = "XXGAM_MAF_DETAIL_VALLIN_TP_NF";

        /**
         * Clave de mensaje para Tipo de cambio no encontrado
         */
        public static final String XXGAM_MAF_DETAIL_NOT_CONVMX = "XXGAM_MAF_DETAIL_NOT_CONVMX";

        /**
         * Clave de mensaje para No se realizó la asignación de la divisa en una o más líneas de anticipo
         */
        public static final String XXGAM_MAF_DETAIL_CRY_ALL_ERROR = "XXGAM_MAF_DETAIL_CRY_ALL_ERROR";

        /**
         * Clave de mensaje para Error en el proceso de actualización para la validación por línea de anticipo
         */
        public static final String XXGAM_MAF_DETAIL_VALLINE_ERROR = "XXGAM_MAF_DETAIL_VALLINE_ERROR";

        /**
         * Clave de mensaje para Error al calcular el importe total
         */
        public static final String XXGAM_MAF_REQ_TOTAL_CAL_ERROR = "XXGAM_MAF_REQ_TOTAL_CAL_ERROR";

        /**
         * Clave de mensaje para No se puede acceder a la página de Revisión y Propuesta de Pago, una o más líneas de anticipo no son válidas
         */
        public static final String XXGAM_MAF_REQ_GORPP_LINEVAL = "XXGAM_MAF_REQ_GORPP_LINEVAL";

        /**
         * Clave de mensaje para Acceso denegado por tipo de solicitud no definida
         */
        public static final String XXGAM_MAF_ACCESS_TR_ND_ERROR = "XXGAM_MAF_ACCESS_TR_ND_ERROR";

        /**
         * Clave de mensaje para La fecha de llegada no puede ser menor a la fecha de salida
         */
        public static final String XXGAM_MAF_FHD_LESS_FHA_ERROR = "XXGAM_MAF_FHD_LESS_FHA_ERROR";

        /**
         * Clave de mensaje para La fecha de llegada y la fecha de salida deben ser mayor o igual a la fecha actual
         */
        public static final String XXGAM_MAF_TKT_VAL_CURR_DATE = "XXGAM_MAF_TKT_VAL_CURR_DATE";

        /**
         * Clave de mensaje para No se puede generar una solicitud de boletos de avión sin vuelos asignados
         */
        public static final String XXGAM_MAF_TKT_F_NE_ERROR = "XXGAM_MAF_TKT_F_NE_ERROR";

        /**
         * Clave de mensaje para Falta ingresar el valor del número de boleto
         */
        public static final String XXGAM_MAF_TKT_FOLIO_NF_ERRO = "XXGAM_MAF_TKT_FOLIO_NF_ERRO";

        /**
         * Clave de mensaje para Falta fecha inicio
         */
        public static final String XXGAM_MAF_DETAIL_VAL_GET_FHINI = "XXGAM_MAF_DETAIL_VAL_GET_FHINI";

        /**
         * Clave de mensaje para Falta fecha fin
         */
        public static final String XXGAM_MAF_DETAIL_VAL_GET_FHFIN = "XXGAM_MAF_DETAIL_VAL_GET_FHFIN";
        /**
         * Clave de mensaje para validar el monto limite de la politica TRASPORTACION
         */
        public static final String XXGAM_MAF_VAL_LIMIT_AMOUNT_POL = "XXGAM_MAF_VAL_LIMIT_AMOUNT_POL";
         /**
          * Clave de mensaje para buscar el monto limite de la politica TRASPORTACION
          */
         public static final String XXGAM_MAF_GET_LIMIT_AMOUNT_POL = "XXGAM_MAF_GET_LIMIT_AMOUNT_POL";
         
        public static final String XXGAM_OIE_NODATAF_PASSENGERS = "XXGAM_OIE_NODATAF_PASSENGERS";
             
    }

    /**
     * Mensajes de confirmación genericos de los módulos.
     */
    public static class GenericType {
    
        /**
         * Clave de la aplicacion de solicitudes de anticipo
         */
        public static final String SHORT_NAME_SQLAP1 =
            "SQLAP";
            
        /**
         * Clave de la aplicacion de business online
         */
        public static final String SHORT_NAME_XBOL =
            "XBOL";

        /**
         * Mensaje para confirmar La solicitud se duplico satisfactoriamente. Solicitud con número de documento: &NUMBER_PAYMENT
         */
        public static final String XXGAM_MAF_REQ_DUP_CONF_INFO = "XXGAM_MAF_REQ_DUP_CONF_INFO";

        /**
         * Mensaje para pregunta ¿Esta seguro de eliminar la solicitud seleccionada?
         */
        public static final String XXGAM_MAF_REQ_DEL_CONF_QN = "XXGAM_MAF_REQ_DEL_CONF_QN";

        /**
         * Mensaje para confirmar Se elimino satisfactoriamente la solicitud: &NUMBER_PAYMENT
         */
        public static final String XXGAM_MAF_REQ_DEL_CONF_INFO = "XXGAM_MAF_REQ_DEL_CONF_INFO";

        /**
         * Mensaje de error para No es posible eliminar la solicitud seleccionada, la solicitud no sufrio cambios
         */
        public static final String XXGAM_MAF_REQ_DEL_CONF_ERROR = "XXGAM_MAF_REQ_DEL_CONF_ERROR";

        /**
         * Mensaje de error para Error al eliminar la solicitud. Ocurrio error al guardar los cambios
         */
        public static final String XXGAM_MAF_REQ_DEL_SAVE_ERROR = "XXGAM_MAF_REQ_DEL_SAVE_ERROR";

        /**
         * Mensaje de error para Error al guardar la solicitud duplicada. &MSG_ERROR
         */
        public static final String XXGAM_MAF_REQ_DUP_SAVE_ERROR = "XXGAM_MAF_REQ_DUP_SAVE_ERROR";

        /**
         * Mensaje de error para Error al duplicar el registro seleccionado. &MSG_ERROR
         */
        public static final String XXGAM_MAF_DUP_ROW_ERROR = "XXGAM_MAF_DUP_ROW_ERROR";

        /**
         * Mensaje de error para Error al buscar la solicitud
         */
        public static final String XXGAM_MAF_REQ_NF_ERROR = "XXGAM_MAF_REQ_NF_ERROR";

        /**
         * Mensaje de error para Error al buscar los datos de usuario
         */
        public static final String XXGAM_MAF_USRDAT_NF_ERROR = "XXGAM_MAF_USRDAT_NF_ERROR";

        /**
         * Mensaje de error para Error al buscar el id de persona
         */
        public static final String XXGAM_MAF_IDPER_NF_ERROR = "XXGAM_MAF_IDPER_NF_ERROR";

        /**
         * Mensaje de error para No es posible modificar el estatus de la solicitud
         */
        public static final String XXGAM_MAF_REQ_STATUS_MOD_ERROR = "XXGAM_MAF_REQ_STATUS_MOD_ERROR";


        /**
         * Clave de mensaje para ¿Esta seguro de guardar la información?
         */
        public static final String XXGAM_MAF_REQ_SAVE_CONF_QN = "XXGAM_MAF_REQ_SAVE_CONF_QN";

        /**
         * Clave de mensaje para ¿Esta seguro de cancelar cambios en la solicitu de anticipo?
         */
        public static final String XXGAM_MAF_REQ_CANCEL_CONF_QN = "XXGAM_MAF_REQ_CANCEL_CONF_QN";

        /**
         * Clave de mensaje para ¿Desea reservar los fondos de la solicitud de anticipo?
         */
        public static final String XXGAM_MAF_REQ_RFUNDS_CONF_QN = "XXGAM_MAF_REQ_RFUNDS_CONF_QN";

        /**
         * Clave de mensaje para ¿Estas seguro de eliminar el registro?
         */
        public static final String XXGAM_MAF_ROW_DEL_CONF_QN = "XXGAM_MAF_ROW_DEL_CONF_QN";

        /**
         * Clave de mensaje para Ejecutando proceso para reservar los fondos de la solicitud de anticipos
         */
        public static final String XXGAM_MAF_RFUNDS_CMSG_INFO = "XXGAM_MAF_RFUNDS_CMSG_INFO";

        /**
         * Clave de mensaje para Procesando
         */
        public static final String XXGAM_MAF_RFUNDS_DMSG_INFO = "XXGAM_MAF_RFUNDS_DMSG_INFO";

        /**
         * Clave de mensaje para Proceso de Reserva de Fondos
         */
        public static final String XXGAM_MAF_RFUNDS_PNMSG_INFO = "XXGAM_MAF_RFUNDS_PNMSG_INFO";

        /**
         * Clave de mensaje para Datos guardados correctamente. Solicitud de anticipo con número de documento: &NUMBER_PAYMENT
         */
        public static final String XXGAM_MAF_REQ_SAVE_CONF_INFO = "XXGAM_MAF_REQ_SAVE_CONF_INFO";

        /**
         * Clave de mensaje para Error al guardar los datos de la solicitud de anticipo
         */
        public static final String XXGAM_MAF_REQ_SAVE_CONF_ERROR = "XXGAM_MAF_REQ_SAVE_CONF_ERROR";

        /**
         * Clave de mensaje para Error de configuración de la fecha de solicitud
         */
        public static final String XXGAM_MAF_REQ_DATE_CFIG_ERROR = "XXGAM_MAF_REQ_DATE_CFIG_ERROR";

        /**
         * Clave de mensaje para Error de configuración del número de documento
         */
        public static final String XXGAM_MAF_REQ_NMPAY_CFIG_ERROR = "XXGAM_MAF_REQ_NMPAY_CFIG_ERROR";

        /**
         * Clave de mensaje para Error de configuración al actualizar los importes en divisa mexicana de las líneas de anticipo
         */
        public static final String XXGAM_MAF_REQ_AMX_CFIG_ERROR = "XXGAM_MAF_REQ_AMX_CFIG_ERROR";

        /**
         * Clave de mensaje para Error de configuración actualizar el importe total de la solicitud
         */
        public static final String XXGAM_MAF_REQ_TOTAL_CFIG_ERROR = "XXGAM_MAF_REQ_TOTAL_CFIG_ERROR";

        /**
         * Clave de mensaje para Error de configuración al actualizar el estatus de la solicitud
         */
        public static final String XXGAM_MAF_REQ_STATUS_CFIG_ERRO = "XXGAM_MAF_REQ_STATUS_CFIG_ERRO";

        /**
         * Clave de mensaje para El registro se eliminó correctamente
         */
        public static final String XXGAM_MAF_ROW_DEL_CONF_INFO = "XXGAM_MAF_ROW_DEL_CONF_INFO";

        /**
         * Clave de mensaje para No es posible eliminar el registro
         */
        public static final String XXGAM_MAF_ROW_DEL_CONF_ERROR = "XXGAM_MAF_ROW_DEL_CONF_ERROR";

        /**
         * Clave de mensaje para Error al asignar el registro del detalle de anticipo
         */
        public static final String XXGAM_MAF_DETAIL_IDTOTICKET_ER = "XXGAM_MAF_DETAIL_IDTOTICKET_ER";

        /**
         * Clave de mensaje para Error al obtener el id del detalle de anticipo
         */
        public static final String XXGAM_MAF_DETAIL_ID_NF_ERROR = "XXGAM_MAF_DETAIL_ID_NF_ERROR";

        /**
         * Clave de mensaje para Su solicitud &NUMBER_PAYMENT se está procesando, por favor espere
         */
        public static final String XXGAM_MAF_RFUNDS_CONF_INFO = "XXGAM_MAF_RFUNDS_CONF_INFO";

        /**
         * Clave de mensaje para Error al intentar reservar los fondos, debido a que el importe total no aplica para reserva de fondos, los datos fueron guardados automaticamente
         */
        public static final String XXGAM_MAF_RFUNDS_CONF_ERROR1 = "XXGAM_MAF_RFUNDS_CONF_ERROR1";

        /**
         * Clave de mensaje para Error al reservar los fondos, debido a insuficiencia presupuestal en las líneas de anticipo, los datos fueron guardados automaticamente
         */
        public static final String XXGAM_MAF_RFUNDS_CONF_ERROR2 = "XXGAM_MAF_RFUNDS_CONF_ERROR2";

        /**
         *  Clave de mensaje para Error al intentar reservar los fondos, ocurrio error al guardar los datos. &MSG_ERROR
         */
        public static final String XXGAM_MAF_RFUNDS_CONF_ERROR3 = "XXGAM_MAF_RFUNDS_CONF_ERROR3";

        /**
         * Clave de mensaje para Error al procesar la reserva de fondos, consulte el estatus y notificación de su solicitud. Los datos fueron guardados
         */
        public static final String XXGAM_MAF_RFUNDS_INTER_ERROR1 = "XXGAM_MAF_RFUNDS_INTER_ERROR1";

        /**
         * Clave de mensaje para Error al intentar reservar los fondos. Ocurrio error al consultar el id de la solicitud. Los datos fueron guardados
         */
        public static final String XXGAM_MAF_RFUNDS_INTER_ERROR2 = "XXGAM_MAF_RFUNDS_INTER_ERROR2";

        /**
         * Clave de mensaje para Error al intentar reservar los fondos. No se encontrarón líneas de anticipos en la solicitud para realizar la reserva de fondos. Los datos fueron guardados
         */
        public static final String XXGAM_MAF_RFUNDS_INTER_ERROR3 = "XXGAM_MAF_RFUNDS_INTER_ERROR3";

        /**
         * Clave de mensaje para No es posible crear una nueva solicitud de boleto de avión AM
         */
        public static final String XXGAM_MAF_TKT_CREATE_ERROR = "XXGAM_MAF_TKT_CREATE_ERROR";

        /**
         * Clave de mensaje para Error al obtener información de la solicitud
         */
        public static final String XXGAM_MAF_TKT_REQ_NF_ERROR = "XXGAM_MAF_TKT_REQ_NF_ERROR";

        /**
         * Clave de mensaje para Error al obtener el número de boleto de la solicitud
         */
        public static final String XXGAM_MAF_TKT_FOLIO_NF_ERROR = "XXGAM_MAF_TKT_FOLIO_NF_ERROR";

        /**
         * Clave de mensaje para No es posible crear el registro de vuelo
         */
        public static final String XXGAM_MAF_TKT_CREATE_F_ERROR = "XXGAM_MAF_TKT_CREATE_F_ERROR";

        /**
         * Clave de mensaje para ¿Esta seguro de cancelar cambios en la solicitud de boleto de avión AM?
         */
        public static final String XXGAM_MAF_TKT_CANCEL_QN = "XXGAM_MAF_TKT_CANCEL_QN";

        /**
         * Clave de mensaje para ¿Esta seguro de enviar la confirmación?
         */
        public static final String XXGAM_MAF_TKT_SEND_C_QN = "XXGAM_MAF_TKT_SEND_C_QN";
        
        /**
         * Clave de mensaje para ¿Esta seguro de enviar la cancelación?
         */
        public static final String XXGAM_MAF_TKT_SEND_X_QN = "XXGAM_MAF_TKT_SEND_X_QN";

        /**
         * Clave de mensaje para ¿Esta seguro de enviar?
         */
        public static final String XXGAM_MAF_TKT_SEND_QN = "XXGAM_MAF_TKT_SEND_QN";

        /**
         * Clave de mensaje para El número de boleto fue registrado correctamente. La notificación fue procesada con éxito
         */
        public static final String XXGAM_MAF_TKT_FOLIO_SAVE = "XXGAM_MAF_TKT_FOLIO_SAVE";

        /**
         * Clave de mensaje para Ocurrio error al procesar la notificación. Los datos fueron guardados
         */
        public static final String XXGAM_MAF_TKT_SAVE_NOTF_ERR = "XXGAM_MAF_TKT_SAVE_NOTF_ERR";

        /**
         * Clave de mensaje para No es posible redirigir a la página de consulta de solicitudes de boletos de avión
         */
        public static final String XXGAM_MAF_TKT_NAV_DETAIL_ER = "XXGAM_MAF_TKT_NAV_DETAIL_ER";

        /**
         * Clave de mensaje para Error al procesar la notificación
         */
        public static final String XXGAM_MAF_TKT_NOTF_ERROR = "XXGAM_MAF_TKT_NOTF_ERROR";

        /**
         * Clave de mensaje para El envío de notificaciones fue exitoso
         */
        public static final String XXGAM_MAF_TKT_NOTF_INFO = "XXGAM_MAF_TKT_NOTF_INFO";

        /**
         * Clave de mensaje para Registro de vuelo no eliminado
         */
        public static final String XXGAM_MAF_TKT_F_DEL_ERROR = "XXGAM_MAF_TKT_F_DEL_ERROR";

        /**
         * Clave de mensaje para Registro de vuelo eliminado con éxito
         */
        public static final String XXGAM_MAF_TKT_F_DEL_INFO = "XXGAM_MAF_TKT_F_DEL_INFO";

        /**
         * Clave de mensaje para Registro de vuelo no encontrado
         */
        public static final String XXGAM_MAF_TKT_F_NF_ERROR = "XXGAM_MAF_TKT_F_NF_ERROR";

        /**
         * Clave de mensaje para Error al obtener los datos de la solicitud
         */
        public static final String XXGAM_MAF_REQ_DATANF_ERROR = "XXGAM_MAF_REQ_DATANF_ERROR";

        /**
         * Clave de mensaje para Error al generar la exportación del reporte
         */
        public static final String XXGAM_MAF_AD_XPORT_ERROR = "XXGAM_MAF_AD_XPORT_ERROR";

        /**
         * Clave de mensaje para No es posible obtener los datos del reporte
         */
        public static final String XXGAM_MAF_AD_DATA_XPORT_ERROR = "XXGAM_MAF_AD_DATA_XPORT_ERROR";


    }
}
