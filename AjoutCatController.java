/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.reservation;


import Service.ServiceCatégorieArticle;
import entities.CategorieArticle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZIED
 */
public class AjoutCatController implements Initializable {

    @FXML
    private TextField NomCat;
    @FXML
    private Button btnajout;
    @FXML
    private Button home;
    @FXML
    private Label btnOrders1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         home.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/EspaceBack.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }  

    @FXML
    private void AjoutAction(ActionEvent event) {
        
        ServiceCatégorieArticle SCat = new ServiceCatégorieArticle();

        CategorieArticle Cat = new CategorieArticle();

        Cat.setNom(NomCat.getText());
      
        SCat.addCat(Cat);
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/categorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

      

    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }
    
    

    
}
