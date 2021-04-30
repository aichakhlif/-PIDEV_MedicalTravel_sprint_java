/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.reservation;

import Service.ServiceCatégorieArticle;
import entities.CategorieArticle;
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

/**
 * FXML Controller class
 *
 * @author ZIED
 */
public class CategorieController implements Initializable {

    @FXML
    private TableView<CategorieArticle> TabCategorie;
    @FXML
    private TableColumn<CategorieArticle, String> colid;
    @FXML
    private TableColumn<CategorieArticle, String> colnom;
    @FXML
    private Button Ajout_btn;
   
    @FXML
    private Button P;
    @FXML
    private Button home;
    @FXML
    private Label btnOrders1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       ServiceCatégorieArticle Cat = new ServiceCatégorieArticle();
       ObservableList <CategorieArticle> CategorieArticle = Cat.afficherListCategorie() ;
       afficherListCat() ;
       
       Ajout_btn.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/AjoutCat.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
       home.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/EspaceBack.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        
    
}
    
private void afficherListCat() {
     
        ServiceCatégorieArticle Cat = new ServiceCatégorieArticle();
        ObservableList <CategorieArticle> CategorieArticle = Cat.afficherListCategorie() ;
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        TabCategorie.setItems(CategorieArticle);

    }
     


    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
    }

        


@FXML
    private void Modifier_Cat(ActionEvent event) throws IOException {
    
            CategorieArticle C = new CategorieArticle();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/medica/reservation/Modifier_Cat.fxml"));
            Parent root = loader.load();
            System.out.println("aaaaa");
            Modifier_CatController HomeScene = loader.getController();
            System.out.println("cccccccccccccccc");
          
            ServiceCatégorieArticle sm = new ServiceCatégorieArticle();
           
         //String nomMed = sm.load_data2();
            HomeScene.selected_item(
                    TabCategorie.getSelectionModel().getSelectedItem().getId(),
                    TabCategorie.getSelectionModel().getSelectedItem().getNom());
                    System.out.println("cccccccccccccccc");
                    
                  
                 
          
            Stage window = (Stage) P.getScene().getWindow();
            window.setScene(new Scene(root, 904, 581));
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }
    




}