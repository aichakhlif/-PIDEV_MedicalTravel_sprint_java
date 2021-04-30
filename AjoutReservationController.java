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
import static com.sun.deploy.net.CrossDomainXML.check;
import entities.medecin;
import entities.reservation;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.collections.FXCollections.observableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author admin
 */
public class AjoutReservationController implements Initializable {

    @FXML
    private ComboBox<String> inter;
    @FXML
    private ComboBox<String> med;
    @FXML
    private ComboBox<String> clinique;
    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField pays;
    @FXML
    private Button ok;
    @FXML
    private ImageView check;
    @FXML
    private ImageView check1;
    @FXML
    private ImageView check2;
    @FXML
    private ImageView check21;
    @FXML
    private ImageView check211;
    @FXML
    private ImageView check212;
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
        med.setItems(listmed);
        ObservableList<String> listinter = interv.getValuesIntervention();
        inter.setItems(listinter);
        ObservableList<String> listclinique = cliniquee.getValuesClinique();
        clinique.setItems(listclinique);
        
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
    private void ajoutreservation(ActionEvent event) {
        ServiceMedecin medec = new ServiceMedecin();
        ServiceIntervention interv = new ServiceIntervention();
        ServiceClinique cliniquee = new ServiceClinique();
        ServiceReservation sRes = new ServiceReservation();

        if(testSaisie()){ 
            reservation Res = new reservation();

        Res.setNom(nom.getText());
        Res.setEmail(email.getText());
        Res.setPays(pays.getText());
       
        Res.setInter(interv.load_data(inter.getValue()));
        Res.setMed(medec.load_data(med.getValue()));
        Res.setClinique(cliniquee.load_data(clinique.getValue()));

        sRes.addReservation(Res);
           Notifications notificationBuilder = Notifications.create()
                .title("AJOUT RESERVATION")
                .text("votre reservation a etait bien enregistrer  !")
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
        }

    }

/********************CONTRLE DE SAISIE***************************/
    
     private Boolean testSaisie() {
        String erreur = "";
      
        if (!testNomProm()) {
            erreur = erreur + ("Veuillez verifier votre Nom \n");
        }
       
        if (!testEmail()) {
            erreur = erreur + ("Veuillez verifier votre email: \n");
        }
         if (!testPays()) {
            erreur = erreur + ("Veuillez saisir votre pays \n");
        }
          if (!testinter()) {
            erreur = erreur + ("Veuillez choisir le type de l inter  \n");
        }
          if (!testmed()) {
            erreur = erreur + ("Veuillez choisir le medecin  \n");
        }
          if (!testclinique()) {
            erreur = erreur + ("Veuillez choisir la clinique \n");
        }
        return  testNomProm() && testEmail() && testPays()&& testinter()&& testmed() && testclinique();
    }
     
       private Boolean testNomProm() {
        int nbNonChar = 0;
        for (int i = 1; i < nom.getText().trim().length(); i++) {
            char ch = nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && nom.getText().trim().length() >= 3) {
            check.setImage(new Image("images/checkMark.png"));
            return true;
        } else {
            check.setImage(new Image("images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
   
       
         private Boolean testEmail() {
        int nbNonChar = 0;
        for (int i = 1; i < email.getText().trim().length(); i++) {
            char ch = email.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && email.getText().trim().length() >= 3) {
            check1.setImage(new Image("images/checkMark.png"));
            return true;
        } else {
            check1.setImage(new Image("images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
         
         
           private Boolean testPays() {
        int nbNonChar = 0;
        for (int i = 1; i < pays.getText().trim().length(); i++) {
            char ch = pays.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && pays.getText().trim().length() >= 3) {
            check2.setImage(new Image("images/checkMark.png"));
            return true;
        } else {
            check2.setImage(new Image("images/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
    }
           
            private Boolean testinter() {
        
        if (inter.getValue() != null) {
                check21.setImage(new Image("images/checkMark.png"));
                return true;
            } else {
                check21.setImage(new Image("images/erreurCheckMark.png"));
            }
                return false;
    }
            
             private Boolean testmed() {
        
        if (med.getValue() != null) {
                check211.setImage(new Image("images/checkMark.png"));
                return true;
            } else {
                check211.setImage(new Image("images/erreurCheckMark.png"));
            }
                return false;
    }
             
               private Boolean testclinique() {
        
        if (clinique.getValue() != null) {
                check212.setImage(new Image("images/checkMark.png"));
                return true;
            } else {
                check212.setImage(new Image("images/erreurCheckMark.png"));
            }
                return false;
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }

    @FXML
    private void home(ActionEvent event) {
    }
        
} 
