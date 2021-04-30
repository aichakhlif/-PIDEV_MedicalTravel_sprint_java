/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.dossier;

import entitiesLinda.dossier;
import entitiesLinda.medecin;
import entitiesLinda.rendez_vous;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import servicesLinda.ServiceMedecin;
import servicesLinda.serviceDossier;
import servicesLinda.serviceRendezvous;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ModifierRendezvousController implements Initializable {

    @FXML
    private Button ok;
    @FXML
    private Button ok1;
    @FXML
    private ComboBox<String> combo_med;
    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_heure;
    @FXML
    private TableColumn<?, ?> colmed;
    @FXML
    private TableColumn<?, ?> coldate;
    @FXML
    private TableColumn<?, ?> colheure;
    @FXML
    private Label btnOrders1;
    @FXML
    private Label pays;
    @FXML
    private TableView<rendez_vous> tabRendezvous;
    @FXML
    private TableColumn<rendez_vous, Integer> colid;
    @FXML
    private DatePicker datee;
    @FXML
    private Button home;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceMedecin medec = new ServiceMedecin();
        serviceRendezvous r = new serviceRendezvous();
        
        ObservableList<String> listmed = medec.getValuesMedecin();
                ObservableList<rendez_vous> rendezvous = r.afficherRendezvousList();

        combo_med.setItems(listmed);
        afficherRendezvousList();
        
         home.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/espaceClient.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(RendezvousController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
     
    }    
   
     public void selected_item(int id,String date, String heure , medecin med) {
        serviceRendezvous rendezvous = new serviceRendezvous();
        ServiceMedecin medec = new ServiceMedecin();
        System.out.println(id+date+heure+med );
        txt_id.setText(""+id);
        System.out.println("aaaaa");
        //txt_date.setText(date);
        //datee.setValue(LocalDate.now());
        txt_heure.setText(heure);
        combo_med.setValue(medec.load_data2(med.getIdmed()));
    }
    @FXML
    private void modifierrendezvous(ActionEvent event) {
        
         serviceRendezvous ren = new serviceRendezvous();
                 ServiceMedecin medec = new ServiceMedecin();

        

         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Etes vous sur de modifier cet reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            rendez_vous rendez_vous = new rendez_vous();
        rendez_vous.setId(ren.getIdParDesc(txt_id.getText()));
           
                
         System.out.println(ren.getIdParDesc(txt_id.getText()));
          final DatePicker datePicker = new DatePicker(LocalDate.now());
          LocalDate idd=datee.getValue();
        rendez_vous.setDate(idd.toString());
            System.out.println("linda");
        
        System.out.println(datee.getValue().toString());

        rendez_vous.setHeure(txt_heure.getText());
        System.out.println(txt_heure.getText());
        
               rendez_vous.setMed(medec.load_data(combo_med.getValue()));


        
            System.out.println("yyyyyyyyyy");
        
        ren.modifierRendezvous(rendez_vous);
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/rendezvous.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }else {
            System.out.println("Cancel");
        }
    }
     private void afficherRendezvousList() {
        serviceRendezvous sRen = new serviceRendezvous();
        ObservableList<rendez_vous> rendez_vous = sRen.afficherRendezvousList();
        colmed.setCellValueFactory(new PropertyValueFactory<>("med"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colheure.setCellValueFactory(new PropertyValueFactory<>("fichier"));
        
     //   tabDossier.setItems(dossier);

    }

    @FXML
    private void supprendezv(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Etes vous sur de supprimer cet reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        serviceRendezvous sRen = new serviceRendezvous();
            System.out.println(txt_id.getText());
             System.out.println(sRen.getIdParDesc(txt_id.getText()));
            sRen.supprimerRendezvous(sRen.getIdParDesc(txt_id.getText()));
try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/rendezvous.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        } else {
            System.out.println("Cancel");
        }
    }


    private void handleClicks(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/rendezvous.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void handleClicks1(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/dossier/dossier.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handleClicks4(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/espaceClient.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }
    
}
