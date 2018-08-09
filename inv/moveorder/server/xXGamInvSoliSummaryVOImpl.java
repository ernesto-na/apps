package xxgam.oracle.apps.inv.moveorder.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.domain.Date;


public class xXGamInvSoliSummaryVOImpl extends OAViewObjectImpl {

    /**This is the default constructor (do not remove)
     */
    public xXGamInvSoliSummaryVOImpl() {
    }

    public void initQuery(String pNroSoliDesde, String pNroSoliHasta, 
                          Date pFechaDesde, Date pFechaHasta, String pStatus, 
                          String pCvEmp) {
        StringBuffer lWhere = new StringBuffer();
        StringBuffer lWhereFecha = new StringBuffer();
        StringBuffer lWhereNroSoli = new StringBuffer();
        StringBuffer lWhereNroSoliOnly = new StringBuffer();
        String lStatus = pStatus;
        String lCvEmp = pCvEmp;
        String reglaAnios = 
            "AND to_char(sysdate, \'YYYY\')-5 <= to_char(SOLI_DATE, \'YYYY\')";
        if (pStatus == null || "".equals(pStatus)) {
            lStatus = "%";
        }

        lWhere.append("status like :1 ");
        if (pCvEmp == null || "".equals(pCvEmp)) {
            lCvEmp = "%";
        }

        lWhere.append(" and employee_number like :2 ");
        String lWF = null;
        if (pNroSoliDesde == null && pNroSoliHasta == null && 
            "".equals(pNroSoliDesde) && "".equals(pNroSoliHasta) && 
            pFechaDesde == null && pFechaHasta == null && 
            "".equals(pFechaDesde) && "".equals(pFechaHasta) && 
            pCvEmp == null) {
            this.setWhereClause("status like :1 " + reglaAnios);
            this.setWhereClauseParams((Object[])null);
            this.setWhereClauseParam(0, lStatus);
            this.executeQuery();
        } else if (pNroSoliDesde == null && pNroSoliHasta == null && 
                   "".equals(pNroSoliDesde) && "".equals(pNroSoliHasta) && 
                   pFechaDesde == null && pFechaHasta == null && 
                   "".equals(pFechaDesde) && "".equals(pFechaHasta) && 
                   pStatus == null) {
            this.setWhereClause(" employee_number like :1 " + reglaAnios);
            this.setWhereClauseParams((Object[])null);
            this.setWhereClauseParam(0, lStatus);
            this.executeQuery();
        } else {
            if (pFechaDesde != null && pFechaHasta != null && 
                !"".equals(pFechaDesde) && !"".equals(pFechaHasta)) {
                lWhereFecha.append(" and soli_date between :3 and :4 ");
                lWF = "Y";
            } else if (pFechaDesde != null || pFechaHasta != null || 
                       !"".equals(pFechaDesde) || !"".equals(pFechaHasta)) {
                lWhereFecha.append(" and soli_date between nvl(:3,to_date(\'01/01/1950\',\'dd/mm/yyyy\')) and nvl(:4,to_date(\'31/12/2950\',\'dd/mm/yyyy\')) ");
                lWF = "Y";
            }

            if (pNroSoliDesde != null && pNroSoliHasta != null && 
                !"".equals(pNroSoliDesde) && !"".equals(pNroSoliHasta)) {
                if ("Y".equals(lWF)) {
                    lWhereNroSoli.append(" and TO_NUMBER( SUBSTR( nro_solicitud, 5)) between TO_NUMBER( SUBSTR( :5, 5)) and TO_NUMBER( SUBSTR( :6, 5)) ");
                } else {
                    lWhereNroSoli.append(" and TO_NUMBER( SUBSTR( nro_solicitud, 5)) between TO_NUMBER( SUBSTR( :3, 5)) and TO_NUMBER( SUBSTR( :4, 5)) ");
                }
            } else if (pNroSoliDesde != null && !"".equals(pNroSoliDesde) && 
                       (pNroSoliHasta == null || "".equals(pNroSoliHasta))) {
                if ("Y".equals(lWF)) {
                    lWhereNroSoliOnly.append(" and UPPER(nro_solicitud) like UPPER(:5) ");
                } else {
                    lWhereNroSoliOnly.append(" and UPPER(nro_solicitud) like UPPER(:3) ");
                }
            }

            String lW1 = lWhereFecha.toString();
            if (lW1 != null && !lW1.trim().equals("")) {
                lWhere.append(lW1);
            }

            String lW2 = lWhereNroSoli.toString();
            if (lW2 != null && !lW2.trim().equals("")) {
                lWhere.append(lW2);
            }

            String lW3 = lWhereNroSoliOnly.toString();
            if (lW3 != null && !lW3.trim().equals("")) {
                lWhere.append(lW3);
            }

            this.setWhereClause((String)null);
            this.setWhereClause(lWhere.toString() + " " + reglaAnios);
            this.setWhereClauseParams((Object[])null);
            this.setWhereClauseParam(0, lStatus);
            this.setWhereClauseParam(1, lCvEmp);
            if (lW1 != null && !lW1.trim().equals("")) {
                this.setWhereClauseParam(2, pFechaDesde);
                this.setWhereClauseParam(3, pFechaHasta);
            }

            if (lW3 != null && !lW3.trim().equals("")) {
                if (lW1 != null && !lW1.trim().equals("")) {
                    this.setWhereClauseParam(4, pNroSoliDesde);
                } else {
                    this.setWhereClauseParam(2, pNroSoliDesde);
                }
            } else if (lW2 != null && !lW2.trim().equals("")) {
                if (lW1 != null && !lW1.trim().equals("")) {
                    this.setWhereClauseParam(4, pNroSoliDesde);
                    this.setWhereClauseParam(5, pNroSoliHasta);
                } else {
                    this.setWhereClauseParam(2, pNroSoliDesde);
                    this.setWhereClauseParam(3, pNroSoliHasta);
                }
            }

            this.executeQuery();
        }

    }
}
