/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.reservation;

import Service.ServiceMedecin;
import Service.ServicePdf;
import Service.ServiceReservation;
import com.itextpdf.text.DocumentException;
import entities.reservation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ReservationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<reservation> tabReservation;
    @FXML
    private TableColumn<reservation, String> colid_res;
    @FXML
    private TableColumn<reservation, String> colnom;
    @FXML
    private TableColumn<reservation, String> colemail;
    @FXML
    private TableColumn<reservation, String> colpays;
    @FXML
    private TableColumn<reservation, String> colmed;
    @FXML
    private TableColumn<reservation, String> colinter;
    @FXML
    private TableColumn<reservation, String> colclinique;
    @FXML
    private Button btn_ajout1;
    @FXML
    private Button modif_res;
    @FXML
    private Label btnOrders1;
    @FXML
    private TextField tfrechres;
    @FXML
    private ComboBox<String> cbtriObjPred;
    @FXML
    private Button home;
 
  


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceReservation sRes = new ServiceReservation();
        ObservableList<reservation> reservation = sRes.afficherReservationList();
        afficherReservationList();
        
         ObservableList<String> listTriObjPred = FXCollections.observableArrayList("par medecin", "par intervention", "par clinique");
        cbtriObjPred.setItems(listTriObjPred);

        btn_ajout1.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/ajoutReservation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
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

    private void afficherReservationList() {
        ServiceReservation sRes = new ServiceReservation();
        ObservableList<reservation> reservation = sRes.afficherReservationList();
        colid_res.setCellValueFactory(new PropertyValueFactory<>("idres"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colpays.setCellValueFactory(new PropertyValueFactory<>("pays"));
        colinter.setCellValueFactory(new PropertyValueFactory<>("inter"));
        colmed.setCellValueFactory(new PropertyValueFactory<>("med"));
        colclinique.setCellValueFactory(new PropertyValueFactory<>("clinique"));
        tabReservation.setItems(reservation);

    }

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
    }

    @FXML
    private void modifier_res(ActionEvent event) throws IOException {
    
        reservation res= new reservation();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/medica/reservation/modifierReservation.fxml"));
            Parent root = loader.load();
          ModifierReservationController HomeScene = loader.getController();
          System.out.println("aaaaa");
          ServiceMedecin sm= new ServiceMedecin();
         //String nomMed = sm.load_data2();
            HomeScene.selected_item(
                     tabReservation.getSelectionModel().getSelectedItem().getIdres(),
                    tabReservation.getSelectionModel().getSelectedItem().getNom(),
                    tabReservation.getSelectionModel().getSelectedItem().getEmail(),
                    tabReservation.getSelectionModel().getSelectedItem().getPays(),
                   tabReservation.getSelectionModel().getSelectedItem().getMed(),
                   
                  tabReservation.getSelectionModel().getSelectedItem().getInter(),
                  tabReservation.getSelectionModel().getSelectedItem().getClinique());
                 System.out.println("bbbbbbbbbbbbb");
           
            Stage window = (Stage) modif_res.getScene().getWindow();
            window.setScene(new Scene(root, 904, 581));
    }


    @FXML
    private void rechercherObjectif(KeyEvent event) {
         System.out.println("recherche");
        ServiceReservation sop = new ServiceReservation();
        ObservableList<reservation> reservation = sop.rechercherReservation(tfrechres.getText());
                 System.out.println("recherche valide");


        tabReservation.setItems(reservation);
    }

    @FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             ServicePdf sp= new ServicePdf();
        sp.liste_reservationPDF();
        }
        
        
    }

    @FXML
    private void selectTriObjPred(ActionEvent event) {
          ServiceReservation sop = new ServiceReservation();
        ObservableList<reservation> reservation = FXCollections.observableArrayList();
        if (cbtriObjPred.getValue().equals("par medecin")) {
           reservation = sop.trierReservationmedecin();
        } else if (cbtriObjPred.getValue().equals("par intervention")) {
           reservation = sop.trierReservationinter();
        } else if (cbtriObjPred.getValue().equals("par clinique")) {
          reservation = sop.trierReservationclinique();
        }
        tabReservation.setItems(reservation);
    }

   

      @FXML
    private void handleClicks(MouseEvent event) {


            
    }

    @FXML
    private void home(ActionEvent event) {
    }
    
  
    
    

}
