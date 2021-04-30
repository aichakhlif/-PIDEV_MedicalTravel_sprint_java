/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.reservation;

import Service.ServiceArticle;
import Service.ServicePdf;
import com.itextpdf.text.DocumentException;
import entities.Article;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ZIED
 */
public class ArticleController implements Initializable {

    @FXML
    private TableView<Article> ArticleTab;
    @FXML
    private TableColumn<Article,String> ColTitre;
    private TableColumn<Article,String> ColCat;
    @FXML
    private TableColumn<Article,String> ColAuteur;
    @FXML
    private TableColumn<Article,String> ColDate;
    @FXML
    private TableColumn<Article,String> ColContenu;
    @FXML
    private Button AjouterArt_Btn;
    @FXML
    private Button P_Btn;
    @FXML
    private TableColumn<Article,String>ColId;
    @FXML
    private Button btn_nav;
    @FXML
    private TextField barrederecherche;
    @FXML
    private ComboBox<String> cbtriObjPred;
    @FXML
    private Button PDF;
    @FXML
    private Button home;
    @FXML
    private Label btnOrders1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ServiceArticle SAr = new ServiceArticle();
        ObservableList<Article> Article = SAr.afficherArticleList();
        ObservableList<String> listTriObjPred = FXCollections.observableArrayList("Par Auteur", "Par Date", "Par Catégorie", "Par Titre");
        cbtriObjPred.setItems(listTriObjPred);
        afficherArticleList();
        // TODO
    AjouterArt_Btn.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/AjouterArticle.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
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


 @FXML
    private void btn_mesres(ActionEvent event) {

        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/reservation/categorie.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
     private void afficherArticleList() {
         
        ServiceArticle SAr = new ServiceArticle();
        ObservableList<Article> Article = SAr.afficherArticleList();
        ColTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        ColAuteur.setCellValueFactory(new PropertyValueFactory<>("auteur"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        ColContenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        
        ArticleTab.setItems(Article);

    }
     
     @FXML
    private void Modifier_Art(ActionEvent event) throws IOException {
    
            Article C = new Article();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/medica/reservation/ModifierArticle.fxml"));
            Parent root = loader.load();
            
            ModifierArticleController HomeScene = loader.getController();
            System.out.println("partie2");
          
            ServiceArticle sm = new ServiceArticle();
           
         //String nomMed = sm.load_data2();
            HomeScene.selected_item(
                    ArticleTab.getSelectionModel().getSelectedItem().getId(),
                    ArticleTab.getSelectionModel().getSelectedItem().getTitre(),
                    ArticleTab.getSelectionModel().getSelectedItem().getAuteur()
                    ,ArticleTab.getSelectionModel().getSelectedItem().getContenu()
                    
                    
                    
            
            
            
            );
            
            
                    System.out.println("partie1");
                    
                  
                 
          
            Stage window = (Stage) P_Btn.getScene().getWindow();
            window.setScene(new Scene(root, 904, 581));
    }
    
  

     @FXML
    private void selectTriObjPred(ActionEvent event) {
          ServiceArticle Sar = new ServiceArticle();
        ObservableList<Article> Ar = FXCollections.observableArrayList();
        if (cbtriObjPred.getValue().equals("Par Auteur")) {
           Ar = Sar.trierArticleAuteur();
        } else if (cbtriObjPred.getValue().equals("Par Date")) {
           Ar = Sar.trierArticleDate();
        } else if (cbtriObjPred.getValue().equals("Par Titre")) {
          Ar = Sar.trierArticleTitre();
        } else if (cbtriObjPred.getValue().equals("Par Catégorie")) {
          Ar = Sar.trierArticleCat();
        }
        ArticleTab.setItems(Ar);
    }
    
    
    
   

    @FXML
    private void rechercherObjectif(KeyEvent event) {
        
        ServiceArticle sop = new ServiceArticle();
        ObservableList<Article> Article = sop.rechercherArticle(barrederecherche.getText());
        ArticleTab.setItems(Article);
    }

    @FXML
    private void ExportPDF(ActionEvent event) throws FileNotFoundException, DocumentException {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos Articles ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             ServicePdf sp= new ServicePdf();
        sp.liste_ArticlePDF();
        }
        
        
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }
    
    
     
     
    
}
