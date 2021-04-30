/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.reservation;

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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class EspaceClientController implements Initializable {

    @FXML
    private Button btn_res;
    private AnchorPane rootLayout;
    private Stage primaryStage;
    @FXML
    private Button btn_resof;
    @FXML
    private Button btn_resback1;
    @FXML
    private Button btn_rec;
    @FXML
    private Button btn_profil;
    @FXML
    private Button btn_med;
    @FXML
    private Button btn_med1;
    @FXML
    private Button btn_med2;
    @FXML
    private Button btn_med3;
    @FXML
    private Button btn_med4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        

    }

    @FXML
    private void btn_mesres(ActionEvent event) {

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
;

    @FXML
    private void btn_mesresof(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/offre/AffichageResOffre.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btn_resback(ActionEvent event) {
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/backres.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_Blog(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/BlogFront.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void btn_login(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/com/xemacscode/main.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_rec(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/com/xemacscode/reclamation.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_profil(ActionEvent event) {
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/com/xemacscode/AfficherUserFront.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_med(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/gui/test.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_cli(ActionEvent event) {
          try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/zerobug69/FXMLm.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_offre(ActionEvent event) {
        
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/zerobug69/FXMLm.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void btn_RD(ActionEvent event) {
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/rendezvous.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_Dos(ActionEvent event) {
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/dossier/dossier.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
