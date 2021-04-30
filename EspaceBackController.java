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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class EspaceBackController implements Initializable {

    @FXML
    private Button btn_resback;
    @FXML
    private Button btn_resof;
    @FXML
    private Button btn_rec;
    @FXML
    private Button btn_resback1;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_med;
    @FXML
    private Button btn_offre;
    @FXML
    private Button btn_clinique;
    @FXML
    private Button btn_RD;
    @FXML
    private Button btn_D;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
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
    private void btn_rec(ActionEvent event) {
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/com/xemacscode/ReclamationBack.fxml"));
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
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/Article.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    @FXML
    private void btn_user(ActionEvent event) {
         try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/com/xemacscode/AfficherUser.fxml"));
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
            Parent page1 = FXMLLoader.load(getClass().getResource("/gui/specialite.fxml"));
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
            Parent page1 = FXMLLoader.load(getClass().getResource("/zerobug69/FXMLOFFRE.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_clinique(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/zerobug69/FXMLDocument.fxml"));
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
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/back.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_D(ActionEvent event) {
        
          try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/dossier/back.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
