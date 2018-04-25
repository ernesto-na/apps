package xxgam.oracle.apps.inv.moveorder.utils;


public class XxGamInvConstantes {

   public static final String PERFIL_UNIFORMES = "PERFIL_UNIFORMES";
   public static final String PERFIL_SUPER = "XXGAM_INV_USUARIO_UNIFORME";
   public static final String STATUS_PERIODO = "NO ENCONTRADO";
   public static final String LINK_CONSULTA = "OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamSolicitudConsultaPG";
   public static final String LINK_DEVOLUCION = "OA.jsp?page=/xxgam/oracle/apps/inv/moveorder/webui/xXGamInvDevolucionesPG";
   public static final String OK_DEV_0 = " Se realizo el Proceso de forma Exitosa. ";
   public static final String ERROR_DEV_1 = " Se presento un error con la COMPA\u00d1IA en el Proceso de Devoluciones. ";
   public static final String ERROR_DEV_2 = " Se presento un error con el SUBINVENTARIO en el Proceso de Devoluciones.  ";
   public static final String ERROR_DEV_3 = " Se presento un error con el INVENTARIO_ID en el Proceso de Devoluciones.  ";
   public static final String ERROR_DEV_4 = " Se presento un error con el LOCALIZADOR DE LOOKUP en el Proceso de Devoluciones. ";
   public static final String ERROR_DEV_5 = " Se presento un error con el LOCALIZADOR ON_HAND en el Proceso de Devoluciones. ";
   public static final String ERROR_DEV_6 = " Se presento un error con el CUENTA CONTABLE en el Proceso de Devoluciones. ";
   public static final String ERROR_DEV_7 = " Se presento un error con el INSERSIONES EN LA TABLA INTERFACE en el Proceso de Devoluciones. ";
   public static final String ERROR_DEV_8 = " Se presento un error AL INICIAR EL CONCURRENTE en el Proceso de Devoluciones. ";
   public static final String ERROR_DEV_9 = " El concurrente \'Process transaction interface\' termina en error (1er concurrente) en el Proceso de Devoluciones. ";
   public static final String ERROR_DEV_10 = " El concurrente \'Inventory transaction worker\' termina en error (2do concurrente) en el Proceso de Devoluciones. ";
   public static final String ERROR_DEV_100 = "PRENDA SIN EXISTENCIA";
   public static final String ERROR_DEV_DEF = " Se presento un error en el Proceso de Devoluciones. ";
   public static final String ERROR_KIT_1 = " Se presento un error con el TIPO PERSONA en la asignaci\u00f3n de KIT. ";
   public static final String ERROR_KIT_2 = " Se presento un error con el GENERO en la asignaci\u00f3n de KIT. ";
   public static final String ERROR_KIT_3 = " Se presento un error con el GRUPO PAGO en la asignaci\u00f3n de KIT. ";
   public static final String ERROR_KIT_4 = " Se presento un error con el LOCALIDAD en la asignaci\u00f3n de KIT. ";
   public static final String ERROR_KIT_5 = " Se presento un error con el PUESTO en la asignaci\u00f3n de KIT. ";
   public static final String ERROR_KIT_6 = " Se presento un error con el TIPO PERSONA en la estructura Table HR en la asignaci\u00f3n de KIT. ";
   public static final String ERROR_KIT_7 = " Se presento un error con el GENERO en la estructura Table HR en la asignaci\u00f3n de KIT. ";
   public static final String ERROR_KIT_8 = " Se presento un error con el GRUPO PAGO en la estructura Table HR en la asignaci\u00f3n de KIT. ";
   public static final String ERROR_KIT_9 = " Se presento un error con el LOCALIDAD en la estructura Table HR en la asignaci\u00f3n de KIT. ";
   public static final String ERROR_KIT_10 = " El usuario NO tiene asignado un Kit de Uniformes a su puesto. ";
   public static final String ERROR_KIT_11 = " Se presento un error al obtener el KIT_ID de la tabla de kits en la asignaci\u00f3n de KIT. ";
   public static final String ERROR_KIT_DEF = " Se presento un error en la asignaci\u00f3n de KIT. ";
   public static final String ERROR_GLOBAL = " Se presento un error  consulte al administrador del Sistema. "; 

   /**
    * Nuevos mensajes de error, requerimento (GSS/Multisolutions - OCT-2016)
    * */
   public static final String ERROR_KIT_DETAIL_1 = " No tiene TIPO DE EMPLEADO asignado, " +
                                                   " Solicita a tu jefe que pida a Recursos Humanos la correcci\u00f3n de este dato. ";// " No tiene TIPO DE EMPLEADO asignado, acudir con Recursos Humanos para su actualizaci\u00f3n. ";
   public static final String ERROR_KIT_DETAIL_2 = " No tiene G\u00c9NERO asignado, " +
                                                   " Solicita a tu jefe que pida a Recursos Humanos la correcci\u00f3n de este dato. ";//" No tiene G\u00c9NERO asignado, acudir con Recursos Humanos para su actualizaci\u00f3n. ";
   public static final String ERROR_KIT_DETAIL_3 = " No tiene GRUPO DE PAGO asignado, " +
                                                   " Solicita a tu jefe que pida a Recursos Humanos la correcci\u00f3n de este dato.";//" No tiene GRUPO DE PAGO asignado, acudir con Recursos Humanos para su actualizaci\u00f3n.";
   public static final String ERROR_KIT_DETAIL_4 = " No tiene LOCALIDAD asignada, " +
                                                   " Solicita a tu jefe que pida a Recursos Humanos la correcci\u00f3n de este dato. "; //No tiene LOCALIDAD asignada, acudir con Recursos Humanos para su actualizaci\u00f3n. ";
//   public static final String ERROR_KIT_DETAIL_5 = " No tiene PUESTO asignado, " +
//                                                   " Solicita a tu jefe que pida a Recursos Humanos la correcci\u00f3n de este dato."; //" No tiene PUESTO asignado, acudir con Recursos Humanos para su actualizaci\u00f3n.";
    public static final String ERROR_KIT_DETAIL_5 = " Error no tienes un Kit de Uniforme Asignado, Enviar a planeaci\u00f3n tu solicitud, amplanmatgral@aeromexico.com (Sindicalizados) " + 
                                                    " Si eres personal No  sindicalizado revisar link de pol\u00edtica. " + 
                                                    " Si estas en la pol\u00edtica enviar un correo a amplanmatgral@aeromexico.com " + 
                                                    " Si no estas en la pol\u00edtica contactar a tu BP (Business Parther)";
   
   public static final String ERROR_KIT_DETAIL_6 = " Error no tienes un Kit de Uniforme Asignado, Enviar a planeaci\u00f3n tu solicitud, amplanmatgral@aeromexico.com (Sindicalizados) " + 
                                                   " Si eres personal No  sindicalizado revisar link de pol\u00edtica. " + 
                                                   " Si estas en la pol\u00edtica enviar un correo a amplanmatgral@aeromexico.com " + 
                                                   " Si no estas en la pol\u00edtica contactar a tu BP (Business Parther)";
                                                   

    public static final String ERROR_NOT_ACCOUNT_DEF = "  Sin cuenta de Gasto por Defecto, acudir al \u00c1rea de Recursos Humanos para asignarla.";
    
    public static final String ERROR_EMPLOYEE_NOT_ACTIVE = " Empleado Inactivo, acudir al \u00c1rea de Recursos Humanos para su Reactivaci\u00f3n.";    
    
    public static final String ERROR_KIT_PERIOD_CLOSE = "No se podr\u00e1n grabar los datos, el periodo del Kit asignado se encuentra cerrado";
                                                      //" No se podr\u00e1n grabar los datos ya que el periodo del Kit se encuentra cerrado.";    
}
