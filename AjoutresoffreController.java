/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.offre;

import Service.ServiceClinique;
import Service.ServiceIntervention;
import Service.ServiceMedecin;
import Service.ServiceOffre;
import Service.ServiceReservation;
import Service.ServiceReservationOffre;
import entities.clinique;
import entities.intervention;
import entities.medecin;
import entities.offre;
import entities.reservation;
import entities.reservationoffre;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import medica.reservation.EspaceClientController;
import medica.reservation.PaiementController;
import medica.reservation.ReservationController;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class AjoutresoffreController implements Initializable {

    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_pays;
    @FXML
    private Label pays;
    @FXML
    private Button ok;
    @FXML
    private Button ok1;
    @FXML
    private TextField txt_offre;
    @FXML
    private Label idresoffre;
    @FXML
    private ImageView check;
    @FXML
    private ImageView check1;
    @FXML
    private ImageView check2;
    @FXML
    private Label btnOrders1;
    @FXML
    private Label pays1;
    @FXML
    private Label pays11;
    @FXML
    private Button home;
    @FXML
    private Button payer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
    private void ajouterresoffre(ActionEvent event) {
      
        ServiceOffre offre = new ServiceOffre();
        ServiceReservationOffre Resof = new ServiceReservationOffre();
if(testSaisie()){ 
        reservationoffre Res = new reservationoffre();

        Res.setNom(txt_nom.getText());
        Res.setEmail(txt_email.getText());
        Res.setPays(txt_pays.getText());
       
        Res.setOffre(offre.load_data(parseInt(txt_offre.getText())));


        Resof.addReservationoffre(Res);
        Notifications notificationBuilder = Notifications.create()
                .title("AJOUT RESERVATION")
                .text("votre offre a etait bien enregistrer  !")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showInformation();
try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/offre/AffichageResOffre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutresoffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    }

    @FXML
    private void suppresoffre(ActionEvent event) {
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Etes vous sur de supprimer cet reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        ServiceReservationOffre sRes = new ServiceReservationOffre();
            System.out.println(idresoffre.getText());
             System.out.println(sRes.getIdParDesc(idresoffre.getText()));
            sRes.supprimerReservationOffre(sRes.getIdParDesc(idresoffre.getText()));
            
            Notifications notificationBuilder = Notifications.create()
                .title("SUPPRESION D'OFFRE ")
                .text("vous venez de supprimer votre offre  !")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showInformation();
        
            try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/offre/AffichageResOffre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AjoutresoffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
           
      
        } else {
            System.out.println("Cancel");
        }
        
    }

    
    
    
    public void selected_item(int id ) {
                ServiceOffre of = new ServiceOffre();
              
        System.out.println(id );
         txt_offre.setText(""+id);

    }
     public void selected_item2(int idresof ,String nom, String email, String pays,offre offre ) {
                ServiceReservationOffre of = new ServiceReservationOffre();
                                ServiceOffre off = new ServiceOffre();

              
        System.out.println(idresof +nom +email +pays+ offre );
        idresoffre.setText(""+idresof);
          txt_nom.setText(nom);
        txt_email.setText(email);
        txt_pays.setText(pays);
         txt_offre.setText(offre.toString());

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
      
        return  testNomProm() && testEmail() && testPays();
    }
     
       private Boolean testNomProm() {
        int nbNonChar = 0;
        for (int i = 1; i < txt_nom.getText().trim().length(); i++) {
            char ch = txt_nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && txt_nom.getText().trim().length() >= 3) {
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
        for (int i = 1; i < txt_email.getText().trim().length(); i++) {
            char ch = txt_email.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && txt_email.getText().trim().length() >= 3) {
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

    @FXML
    private void handleClicks(MouseEvent event) {
    }

    @FXML
    private void home(ActionEvent event) {
    }

//    @FXML
//    private void payeroffre(ActionEvent event) throws IOException {
//        
//        System.out.println("elyeeeees");
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/medica/reservation/paiement.fxml"));
//            Parent root = loader.load();
//          PaiementController HomeScene = loader.getController();
//           System.out.println("aaaaa");
//           
//           
//            HomeScene.selected_item(
//            parseInt(txt_offre.getText())
//            );
// System.out.println("---------------------------"+ txt_offre.getText());
//            
//           
//            Stage window = (Stage) payer.getScene().getWindow();
//            window.setScene(new Scene(root, 904, 581));
//    }

    @FXML
    private void payerr(ActionEvent event) throws IOException {    
        System.out.println("elyeeeees");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/medica/reservation/paiement.fxml"));
            Parent root = loader.load();
          PaiementController HomeScene = loader.getController();
           System.out.println("aaaaa");
           
           
            HomeScene.selected_item(
            parseInt(txt_offre.getText())
            );
 System.out.println("---------------------------"+ txt_offre.getText());
            
           
            Stage window = (Stage) payer.getScene().getWindow();
            window.setScene(new Scene(root, 904, 581));
    
    }
           
      
           

   

   
}
