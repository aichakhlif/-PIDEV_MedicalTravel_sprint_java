/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.reservation;


import Service.ServiceArticle;
import entities.Article;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZIED
 */
public class BlogFrontController implements Initializable {
    
   ObservableList Article = FXCollections.observableArrayList();
   ServiceArticle SAr = new ServiceArticle();
        ObservableList<String> Article1 = SAr.AfficherTitre();

    @FXML
    private ListView<String> ListeArticle;
    @FXML
    private Button ConsulterBtn;
    @FXML
    private Button home;
    @FXML
    private Label btnOrders2;
    @FXML
    private Label btnOrders1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        LoadData();
        
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
    private void LoadData(){
        Article.removeAll(Article);
        
        
        Article.addAll(Article1);
        ListeArticle.getItems().addAll(Article);
    }
    
    
            
 
    @FXML
    private void DisplaySelected(MouseEvent event) {
        String movie=ListeArticle.getSelectionModel().getSelectedItem();
        System.out.println(movie);
        
    }
    
    
   

    @FXML
    private void ConsulterArticle(ActionEvent event) throws IOException{
        
        Article C = new Article();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/medica/reservation/ArticleSolo.fxml"));
            Parent root = loader.load();
            
            ArticleSoloController HomeScene = loader.getController();
           
          
            ServiceArticle sm = new ServiceArticle();
           
         //String nomMed = sm.load_data2();
            HomeScene.selected_item1(
                    
                    ListeArticle.getSelectionModel().getSelectedItem()
                    
                    
            
            
            
            );
            
            
                    
                    
                  
                 
          
            Stage window = (Stage) ConsulterBtn.getScene().getWindow();
            window.setScene(new Scene(root, 904, 581));
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }
    
    
    
    
    
}
