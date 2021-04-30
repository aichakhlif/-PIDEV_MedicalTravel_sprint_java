/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.offre;

import Service.ServiceMedecin;
import Service.ServiceOffre;
import Service.ServiceReservation;
import entities.offre;
import entities.reservation;
import entities.reservationoffre;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import medica.reservation.ModifierReservationController;
import medica.reservation.ReservationController;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AffichageOffreController implements Initializable {

    @FXML
    private TableView<offre> tabOffre;
    @FXML
    private TableColumn<offre,String> colid_offre;
    @FXML
    private TableColumn<offre,String> colclinique;
    @FXML
    private TableColumn<offre,String> colmed;
    @FXML
    private TableColumn<offre,String> colinter;
    @FXML
    private TableColumn<offre,String> colprix;
    @FXML
    private TableColumn<offre,String> coldate;
    private Button btn_reso;
    @FXML
    private Button btn_resof;
    @FXML
    private Label btnOrders1;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            ServiceOffre o = new ServiceOffre();
        ObservableList<offre> offre = o.afficherOffreList();
        afficherOffreList();
        
         home.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/espaceClient.fxml"));
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
    private void OnMouseObjectifPred(MouseEvent event) {
    }

    
     private void afficherOffreList() {
        ServiceOffre o = new ServiceOffre();
        ObservableList<offre> offre = o.afficherOffreList();
        colid_offre.setCellValueFactory(new PropertyValueFactory<>("idoffre"));
        colclinique.setCellValueFactory(new PropertyValueFactory<>("clinique"));
        colmed.setCellValueFactory(new PropertyValueFactory<>("med"));
        colinter.setCellValueFactory(new PropertyValueFactory<>("inter"));

        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
       
       
        tabOffre.setItems(offre);

    }

    @FXML
    private void recupereroffre(ActionEvent event) throws IOException {
      //  reservationoffre res= new reservationoffre();
                   System.out.println("yyyy");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/medica/offre/ajoutresoffre.fxml"));
            Parent root = loader.load();
          AjoutresoffreController HomeScene = loader.getController();
           System.out.println("aaaaa");
           
           
            HomeScene.selected_item(
                tabOffre.getSelectionModel().getSelectedItem().getIdoffre());
 
            
                 System.out.println("bbbbbbbbbbbbb");
           
            Stage window = (Stage) btn_resof.getScene().getWindow();
            window.setScene(new Scene(root, 904, 581));
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }

    @FXML
    private void home(ActionEvent event) {
    }
}
