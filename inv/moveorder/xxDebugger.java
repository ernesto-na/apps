package xxgam.oracle.apps.inv.moveorder;

import java.sql.SQLException;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OADBTransaction;

import oracle.jdbc.OracleCallableStatement;


public final class xxDebugger {

    public static OADBTransaction oadbtransaction = null;


    public static void Debugger(String texto) {
        String statment = "Begin  debugger(?); End;";
        OracleCallableStatement oraclecallablestatement = null;

        try {
            oraclecallablestatement = 
                    (OracleCallableStatement)oadbtransaction.createCallableStatement(statment, 
                                                                                     1);
            oraclecallablestatement.setString(1, texto);
            oraclecallablestatement.execute();
        } catch (SQLException var11) {
            throw OAException.wrapperException(var11);
        } finally {
            try {
                oraclecallablestatement.close();
            } catch (SQLException var10) {
                throw OAException.wrapperException(var10);
            }
        }

    }

}
