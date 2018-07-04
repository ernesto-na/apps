package xxgam.oracle.apps.inv.moveorder.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.apps.fnd.framework.OAException;

import oracle.jbo.domain.Number;


public class XxGamInvUtils {

    public String manejoErroresDev(Number status) {
        String msg = null;
        switch (Integer.parseInt(status.toString())) {
        case -10:
            msg = 
" El concurrente \'Inventory transaction worker\' termina en error (2do concurrente) en el Proceso de Devoluciones. ";
            break;
        case -9:
            msg = 
" El concurrente \'Process transaction interface\' termina en error (1er concurrente) en el Proceso de Devoluciones. ";
            break;
        case -8:
            msg = 
" Se presento un error AL INICIAR EL CONCURRENTE en el Proceso de Devoluciones. ";
            break;
        case -7:
            msg = 
" Se presento un error con el INSERSIONES EN LA TABLA INTERFACE en el Proceso de Devoluciones. ";
            break;
        case -6:
            msg = 
" Se presento un error con el CUENTA CONTABLE en el Proceso de Devoluciones. ";
            break;
        case -5:
            msg = 
" Se presento un error con el LOCALIZADOR ON_HAND en el Proceso de Devoluciones. ";
            break;
        case -4:
            msg = 
" Se presento un error con el LOCALIZADOR DE LOOKUP en el Proceso de Devoluciones. ";
            break;
        case -3:
            msg = 
" Se presento un error con el INVENTARIO_ID en el Proceso de Devoluciones.  ";
            break;
        case -2:
            msg = 
" Se presento un error con el SUBINVENTARIO en el Proceso de Devoluciones.  ";
            break;
        case -1:
            msg = 
" Se presento un error con la COMPA\u00d1IA en el Proceso de Devoluciones. ";
            break;
        case 0:
            msg = " Se realizo el Proceso de forma Exitosa. ";
            break;
        case 100:
            msg = "PRENDA SIN EXISTENCIA";
            break;
        default:
            msg = " Se presento un error en el Proceso de Devoluciones. ";
        }

        return msg;
    }

    public String manejoErroresKIT(Number status) {
        String msg = null;
        switch (Integer.parseInt(status.toString())) {
        case -11:
            msg = 
" Se presento un error al obtener el KIT_ID de la tabla de kits en la asignaci\u00f3n de KIT. ";
            break;
        case -10:
            msg = 
" El usuario NO tiene asignado un Kit de Uniformes a su puesto. ";
            break;
        case -9:
            msg = 
" Se presento un error con el LOCALIDAD en la estructura Table HR en la asignaci\u00f3n de KIT. ";
            break;
        case -8:
            msg = 
" Se presento un error con el GRUPO PAGO en la estructura Table HR en la asignaci\u00f3n de KIT. ";
            break;
        case -7:
            msg = 
" Se presento un error con el GENERO en la estructura Table HR en la asignaci\u00f3n de KIT. ";
            break;
        case -6:
            msg = 
" Se presento un error con el TIPO PERSONA en la estructura Table HR en la asignaci\u00f3n de KIT. ";
            break;
        case -5:
            msg = 
" Se presento un error con el PUESTO en la asignaci\u00f3n de KIT. ";
            break;
        case -4:
            msg = 
" Se presento un error con el LOCALIDAD en la asignaci\u00f3n de KIT. ";
            break;
        case -3:
            msg = 
" Se presento un error con el GRUPO PAGO en la asignaci\u00f3n de KIT. ";
            break;
        case -2:
            msg = 
" Se presento un error con el GENERO en la asignaci\u00f3n de KIT. ";
            break;
        case -1:
            msg = 
" Se presento un error con el TIPO PERSONA en la asignaci\u00f3n de KIT. ";
            break;
        default:
            msg = " Se presento un error en la asignaci\u00f3n de KIT. ";
        }

        return msg;
    }


    public static String getNumberUNIS(Number numPersonID, 
                                       Connection connection) {
        String retval = "Success";

        int counter = 0;

        String stmtQuery = 
            "  select count(3) count_solis " + "    from xxgam_inv_soli " + 
            "   where person_id = ? ";

        PreparedStatement prepStatement = null;
        ResultSet resultSet = null;

        try {
            prepStatement = 
                    connection.prepareStatement(stmtQuery, ResultSet.TYPE_FORWARD_ONLY, 
                                                ResultSet.CONCUR_READ_ONLY);
            prepStatement.setDouble(1, numPersonID.doubleValue());
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                counter = counter + 1;
                retval = resultSet.getString("count_solis");
            }

        } catch (SQLException sqle) {
            retval = 
                    "EXCEPTION: al validar UNIS existentes " + sqle.getMessage() + 
                    " , " + sqle.getErrorCode();
            return retval;
        }
        closePreparedStatement(prepStatement);
        closeResultSet(resultSet);
        return retval;

    }

    private static void closePreparedStatement(PreparedStatement pPrepStmt) {
        if (null != pPrepStmt) {
            try {
                pPrepStmt.close();
            } catch (SQLException sqle) {
                throw new OAException(sqle.getErrorCode() + " , " + 
                                      sqle.getMessage(), OAException.ERROR);
            }
        }
    }

    private static void closeResultSet(ResultSet pResultSet) {
        if (null != pResultSet) {
            try {
                pResultSet.close();
            } catch (SQLException sqle) {
                throw new OAException(sqle.getErrorCode() + " , " + 
                                      sqle.getMessage(), OAException.ERROR);
            }
        }
    }

}
