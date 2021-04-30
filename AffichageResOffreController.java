/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.offre;

import Service.ServiceOffre;
import Service.ServiceReservationOffre;
import entities.offre;
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
import medica.reservation.ReservationController;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AffichageResOffreController implements Initializable {

    @FXML
    private TableView<reservationoffre> tabResoffre;
    @FXML
    private TableColumn<reservationoffre, String> colid_resof;
    @FXML
    private TableColumn<reservationoffre, String> colnom;
    @FXML
    private TableColumn<reservationoffre, String> colemail;
    @FXML
    private TableColumn<reservationoffre, String> colpays;
    @FXML
    private TableColumn<reservationoffre, String> coloffre;
    @FXML
    private Button btn_choixof;
    @FXML
    private Button btn_deleteres;
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
             ServiceReservationOffre ResO = new ServiceReservationOffre();
 ObservableList<reservationoffre> resof = ResO.afficherResOffreList();       
 afficherResOffreList();
 
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

    
       private void afficherResOffreList() {
        ServiceReservationOffre ResO = new ServiceReservationOffre();
        ObservableList<reservationoffre> resof = ResO.afficherResOffreList();
        
        colid_resof.setCellValueFactory(new PropertyValueFactory<>("idresof"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        coloffre.setCellValueFactory(new PropertyValueFactory<>("offre"));
       
       
        tabResoffre.setItems(resof);

    }
    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
    }


    @FXML
    private void choisirOffre(ActionEvent event) {
        
     
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/medica/offre/affichageOffre.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }

    @FXML
    private void deleteresoffre(ActionEvent event) throws IOException {
        
         //  reservationoffre res= new reservationoffre();
                   System.out.println("yyyy");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/medica/offre/ajoutresoffre.fxml"));
            Parent root = loader.load();
          AjoutresoffreController HomeScene = loader.getController();
           System.out.println("aaaaa");
           
           
            HomeScene.selected_item2(
                tabResoffre.getSelectionModel().getSelectedItem().getIdresof(),
                      tabResoffre.getSelectionModel().getSelectedItem().getNom(),
                    tabResoffre.getSelectionModel().getSelectedItem().getEmail(),
                    tabResoffre.getSelectionModel().getSelectedItem().getPays(),
                   tabResoffre.getSelectionModel().getSelectedItem().getOffre()

                    );
 
            
                 System.out.println("bbbbbbbbbbbbb");
           
            Stage window = (Stage) btn_deleteres.getScene().getWindow();
            window.setScene(new Scene(root, 904, 581));
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }

    @FXML
    private void home(ActionEvent event) {
    }
    
}
