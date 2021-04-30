/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import EntitiesO.Mapa;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Marker;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import medica.reservation.ReservationController;

/**
 * FXML Controller class
 *
 * @author BENJOU
 */
public class MapCordController implements Initializable {

    @FXML
    private TextField longitude1;
    @FXML
    private TextField latitude1;
    @FXML
    private TextField longitude;
    @FXML
    private TextField latitude;
    @FXML
    private CheckBox cercle_check;
    boolean  test = false;
    @FXML
    private Button home;
    @FXML
    private Label btnOrders1;

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
    private void AfficherMap(ActionEvent event) {
         Mapa example = new Mapa("MAP");
           LatLng pos= new LatLng(Double.valueOf(longitude.getText()), Double.valueOf(latitude.getText()));
          LatLng pos2= new LatLng(Double.valueOf(longitude1.getText()), Double.valueOf(latitude1.getText()));

           Marker m = example.generateMarker(pos);
           if (test)
           {example.generateArea(pos,0.2);}
           example.generateSimplePath(pos,pos2, true);
    }

    @FXML
    private void TestCercle(ActionEvent event) {
        test=true;
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }
    
    
}
