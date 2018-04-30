package xxgam.oracle.apps.xbol.maf.lov.server;

import oracle.apps.fnd.framework.server.OAViewObjectImpl;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaRow;
import oracle.jbo.domain.Number;
import oracle.jbo.Row;

// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------

public class XxGamMaLimitScheduleOptionVOImpl extends OAViewObjectImpl
{
  /**This is the default constructor (do not remove)
   */
  public XxGamMaLimitScheduleOptionVOImpl()
  {
  }

  void searchLimitSchedule(Number templateId
                         , Number typePayment
                         , String typePaymentDesc
                         , String currencyCode)
  {
  
    ViewCriteria vc = null;
    ViewCriteriaRow vcRow = null;
    vc = createViewCriteria();
    vcRow = vc.createViewCriteriaRow();
     
     if (templateId != null) {
                              vcRow.setAttribute("ExpenseReportId", templateId);
                            }
     if (typePayment != null) {
                              vcRow.setAttribute("ParameterId", typePayment);
                            }     
     if (typePaymentDesc != null) {
                              vcRow.setAttribute("Prompt", typePaymentDesc);
                            }  
     if (currencyCode != null) {
                              vcRow.setAttribute("CurrencyCode", currencyCode);
                            }                         
     try
        {
          vc.addElement(vcRow);
          applyViewCriteria(vc);
        }
        catch (Exception exception) {
          System.out.println("Exception from searchLimitSchedule "+exception.getMessage()); 
          clearViewCriterias();
        }  
                    executeQuery();
                    while (hasNext()) {
                      Row curLimitAmount = next();
                      System.out.println(curLimitAmount.getAttribute("ExpenseReportId") + " " + curLimitAmount.getAttribute("ParameterId") + " " + curLimitAmount.getAttribute("Prompt")+ " " + curLimitAmount.getAttribute("CurrencyCode"));
                      break; 
             }  
                            
   }

}