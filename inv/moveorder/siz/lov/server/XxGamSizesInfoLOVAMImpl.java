package xxgam.oracle.apps.inv.moveorder.siz.lov.server;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import oracle.apps.fnd.framework.OAException;
import oracle.apps.fnd.framework.server.OAApplicationModuleImpl;
import oracle.apps.fnd.framework.server.OADBTransaction;

import oracle.jbo.domain.Number;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class XxGamSizesInfoLOVAMImpl extends OAApplicationModuleImpl {
    /**This is the default constructor (do not remove)
     */
    public XxGamSizesInfoLOVAMImpl() {
    }
    
  /**Sample main for debugging Business Components code using the tester.
   */
  public static void main(String[] args) {
      launchTester("xxgam.oracle.apps.inv.moveorder.siz.lov.server", /* package name */
    "XxGamSizesInfoLOVAMLocal" /* Configuration Name */);
  }


  /**Container's getter for XxGamSizeUniformsSizesInfoLOV1
   */
  public XxGamSizeUniformsSizesInfoLOVImpl getXxGamSizeUniformsSizesInfoLOV1() {
      return (XxGamSizeUniformsSizesInfoLOVImpl)findViewObject("XxGamSizeUniformsSizesInfoLOV1");
  }
    
   /* public String initSizesInfo(String pDotaId){
        String methodName = "InitSizesInfo";
        String retval = null;
        
        int counter = 0;
        
        System.out.println("DEBUG GNOSISHCM Into AM method"+methodName);
        
        XxGamSizesInfoVOImpl SizesInfoVOImpl = getXxGamSizesInfoVO1();
        XxGamSizesInfoVORowImpl SizesInfoVORow = null;
        
        if(null!=SizesInfoVOImpl){
                
            if(!SizesInfoVOImpl.isPreparedForExecution()){
              SizesInfoVOImpl.executeQuery();
            }
            
            OADBTransaction oaDBTransaction =  this.getOADBTransaction();
            Connection connection = oaDBTransaction.getJdbcConnection();
            
            String queryStmt =  "  SELECT "+
                                "          XIT.DOTA_ID  "+
                                "        , XIT.TALLA_ID  "+
                                "        , XID.NOMENCLATURE||'  '||XIT.TALLA_NBR DESCRIPTION  "+
                                "        , XIT.KIT_COD  "+
                                "        , XIT.TALLA_NBR  "+
                                "        , XIT.INVENTORY_ID  "+
                                "  FROM   "+
                                "          XXGAM_INV_TALLA XIT  "+
                                "        , XXGAM_INV_DOTA XID  "+
                                "  WHERE 1=1  "+
                                "  AND XIT.DOTA_ID = XID.DOTA_ID  "+
                                "  AND XIT.DOTA_ID = ?  ";
                                
            PreparedStatement prpStmt = null;
            ResultSet resultSet = null;
            
            try{
                prpStmt = connection.prepareStatement(queryStmt,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                prpStmt.setDouble(1, new Double(pDotaId));
                
                resultSet = prpStmt.executeQuery();
                
                while(resultSet.next()){
                    SizesInfoVORow = (XxGamSizesInfoVORowImpl)SizesInfoVOImpl.createRow();
                    SizesInfoVORow.setDotaId(resultSet.getString(1));
                    SizesInfoVORow.setTallaId(resultSet.getString(2));
                    SizesInfoVORow.setDescription(resultSet.getString(3));
                    SizesInfoVORow.setKitCode(resultSet.getString(4));
                    SizesInfoVORow.setTallaNBR(resultSet.getString(5));
                    SizesInfoVORow.setInventoryId(resultSet.getString(6));
                    SizesInfoVOImpl.insertRow(SizesInfoVORow);
                    counter = counter + 1;
                }
            }catch(SQLException sqle){
                System.out.println("DEBUG GNOSISHCM "+ "SQLEXCEPTION"+ sqle.getErrorCode()+" , "+sqle.getMessage());
                retval = "EXCEPTION al obtener las tallas en funcion del accesorio/prenda. "+sqle.getErrorCode()+" , "+sqle.getMessage();
            }
            
            this.closePreparedStatement(prpStmt);
            this.closeResultSet(resultSet);
        }        
        
        if(0==counter){
          retval = "EXCEPTION al obtener los kits disponibles en funcion de organizacion, puesto, genero: No se encontraron registros.";
        }
        
        return retval;
    }*/
    
    public String initSizesInfoNew(String pDotaId){
        String methodName = "initSizesInfoNew";
        String strRetval = "EXCEPTION";
        XxGamSizeUniformsSizesInfoLOVImpl SizeUniformsSizesInfoVO = this.getXxGamSizeUniformsSizesInfoLOV1();

        if (SizeUniformsSizesInfoVO != null) {
            Number numberDotaId = null;
            try{
                numberDotaId = new Number(pDotaId);
                SizeUniformsSizesInfoVO.initQuery(numberDotaId);
                strRetval = "SUCCESS";
            }catch(Exception exception){
                System.out.println("DEBUG GNOSISHCM Exception");
                System.out.println(exception.getCause() + exception.getMessage());
                System.out.println("DEBUG GNOSISHCM Error de conversion String to Number");
            }
        }
        return strRetval;
    }
    
    


    /**
     * Metodo que cierra un Result Set
     * @param pResultSet
     */
    private void closeResultSet(ResultSet pResultSet)
    {
      if(null!=pResultSet){
        try
        {
          pResultSet.close();
        } catch (SQLException sqle)
        {
         throw new OAException(sqle.getErrorCode()+ " , "+sqle.getMessage(),OAException.ERROR);
        }
      }
    }
    
    
    /**
     * Metodo que cierra un Prepared Statement
     * @param pPrepStmt
     */
    private void closePreparedStatement(PreparedStatement pPrepStmt)
    {
       if(null!=pPrepStmt){
        try
        {
          pPrepStmt.close();
        } catch (SQLException sqle)
        {
           throw new OAException(sqle.getErrorCode()+ " , "+sqle.getMessage(),OAException.ERROR);
        }
      }
    }

   

  public void initSizesInfoEmpty()
  {
    XxGamSizeUniformsSizesInfoLOVImpl SizeUniformsSizesInfoVO = this.getXxGamSizeUniformsSizesInfoLOV1();
    if(null!=SizeUniformsSizesInfoVO){
      SizeUniformsSizesInfoVO.initSizesInfoEmpty();
    }
  }
  
}
