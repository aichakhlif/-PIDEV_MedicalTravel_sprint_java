/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.reservation;

import Service.ServiceClinique;
import Service.ServiceIntervention;
import Service.ServiceMedecin;
import Service.ServiceReservation;
import entities.clinique;
import entities.intervention;
import entities.medecin;
import entities.reservation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ModifierReservationController implements Initializable {

    private TextField nom;
    private TextField email;
    @FXML
    private Label pays;
    @FXML
    private Button ok;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_pays;
    @FXML
    private ComboBox<String> combo_inter;
    @FXML
    private ComboBox<String> combo_med;
    @FXML
    private ComboBox<String> combo_clinique;
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
    private TableColumn<reservation, String> colinter;
    @FXML
    private TableColumn<reservation, String> colmed;
    @FXML
    private TableColumn<reservation, String> colclinique;
    @FXML
    private Button ok1;
    @FXML
    private TextField tfid_res;
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

        ServiceMedecin medec = new ServiceMedecin();
        ServiceIntervention interv = new ServiceIntervention();
        ServiceClinique cliniquee = new ServiceClinique();

        ObservableList<String> listmed = medec.getValuesMedecin();
        combo_med.setItems(listmed);
        ObservableList<String> listinter = interv.getValuesIntervention();
        combo_inter.setItems(listinter);
        ObservableList<String> listclinique = cliniquee.getValuesClinique();
        combo_clinique.setItems(listclinique);
             ServiceReservation sRes = new ServiceReservation();
        ObservableList<reservation> reservation = sRes.afficherReservationList();
        afficherReservationList();
        
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
    private void modifierreservation(ActionEvent event)  {
        ServiceMedecin medec = new ServiceMedecin();
        ServiceIntervention interv = new ServiceIntervention();
        ServiceClinique cliniquee = new ServiceClinique();
        ServiceReservation sRes = new ServiceReservation();

         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation modification");
        alert.setHeaderText("Etes vous sur de modifier cet reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            reservation Res = new reservation();
        Res.setIdres(sRes.getIdParDesc(tfid_res.getText()));
         System.out.println(sRes.getIdParDesc(tfid_res.getText()));

        Res.setNom(txt_nom.getText());
        System.out.println(txt_nom.getText());

        Res.setEmail(txt_email.getText());
        System.out.println(txt_email.getText());

        Res.setPays(txt_pays.getText());
        System.out.println(txt_pays.getText());

           Res.setMed(medec.load_data_modify(combo_med.getValue()));
        System.out.println(medec.load_data_modify(combo_med.getValue()));
        
        Res.setInter(interv.load_data_modify(combo_inter.getValue()));
        System.out.println(interv.load_data_modify(combo_inter.getValue()));


        Res.setClinique(cliniquee.load_data_modify(combo_clinique.getValue()));
        System.out.println(cliniquee.load_data_modify(combo_clinique.getValue()));

            System.out.println("yyyyyyyyyy");
        
        sRes.modifierReservation(Res);
        Notifications notificationBuilder = Notifications.create()
                .title("MODIFICATON RESERVATION")
                .text("votre reservation a était bien modifier  !")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showInformation();
        
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/reservation.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
        }else {
            System.out.println("Cancel");
        }
        
     
        

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

    public void selected_item(int id ,String nom, String email, String pays, medecin med,intervention inter , clinique cli) {
                ServiceMedecin medec = new ServiceMedecin();
                   ServiceIntervention interv = new ServiceIntervention();
        ServiceClinique cliniquee = new ServiceClinique();

        System.out.println(id +nom + email + pays + med +inter +cli );
         tfid_res.setText(""+id);
        txt_nom.setText(nom);
        txt_email.setText(email);
        txt_pays.setText(pays);
         combo_med.setValue(medec.load_data2(med.getIdmed()));
       combo_inter.setValue(interv.load_data2(inter.getIdinter()));
          combo_clinique.setValue(cliniquee.load_data2(cli.getIdclinique()));

    }

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
      
    }

    @FXML
    private void suppreservation(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Etes vous sur de supprimer cet reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        ServiceReservation sRes = new ServiceReservation();
            System.out.println(tfid_res.getText());
             System.out.println(sRes.getIdParDesc(tfid_res.getText()));
            sRes.supprimerReservation(sRes.getIdParDesc(tfid_res.getText()));
            Notifications notificationBuilder = Notifications.create()
                .title("SUPPRESSION D'UNE RESERVATION")
                .text("votre reservation a était supprimer  !")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showInformation();
            afficherReservationList();
             try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/reservation.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
      
        } else {
            System.out.println("Cancel");
        }
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }

    @FXML
    private void home(ActionEvent event) {
    }
    
   

}
