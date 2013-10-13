package ihm;  
  
import controller.DocController;
import dao.DocDAO;
import java.io.Serializable;  
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
  
import org.primefaces.model.chart.PieChartModel;  

@Named(value = "chartBean")
@SessionScoped
public class ChartBean implements Serializable {  
  
    private PieChartModel pieModel;  
  
    public ChartBean() {  
        createPieModel();  
    }  
  
    public PieChartModel getPieModel() { 
        ChartBean chart = new ChartBean();
        return chart.pieModel;  
    }  
  
    private void createPieModel() {  
        pieModel = new PieChartModel();
        
        DocDAO docDao = new DocDAO();
        
   
        pieModel.set("ADMINISTRATIF", docDao.getNbResult("facture")+docDao.getNbResult("salaire"));  
        pieModel.set("PRETS", docDao.getNbResult("pret"));  
        pieModel.set("ETATS DE COMPTES", docDao.getNbResult("compte"));  
      
    }  
}  