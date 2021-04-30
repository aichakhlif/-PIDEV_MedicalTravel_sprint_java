/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.dossier;

import com.itextpdf.text.DocumentException;
import entitiesLinda.dossier;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterAttributes;
import javafx.print.PrinterJob;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import medica.reservation.EspaceClientController;
import medica.reservation.ReservationController;
import servicesLinda.ServicePdf;
import servicesLinda.serviceDossier;

/**
 * FXML Controller class
 *
 * @author DELL
 */

public class DossierController implements Initializable {

    @FXML
    private TableView<dossier> tabdossier;
    @FXML
    private TableColumn<dossier, String> colref;
    @FXML
    private TableColumn<dossier, ImageView> coldossier;
    @FXML
    private Button btn_ajout1;
    @FXML
    private Button modif_dos;
    @FXML
    private TableColumn<dossier, Integer> coliddos;
    @FXML
    private TextField tfrechres;
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
        serviceDossier sDos = new serviceDossier();
        ObservableList<dossier> dossier = sDos.afficherDossierList();
        afficherDossierList();
        
         btn_ajout1.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/medica/dossier/ajoutDossier.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         
         
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
    
    
     private void afficherDossierList() {
        serviceDossier sDos = new serviceDossier();
        ObservableList<dossier> dossier = sDos.afficherDossierList();
         ObservableList<dossier> lista = FXCollections.observableArrayList();
          for (dossier aux : dossier){
              
                        File file = new File(aux.getFichier());
                Image image = new Image(file.toURI().toString());
                System.out.println(image.toString());
                ImageView imageView =new ImageView(image);
                imageView.setImage(image);
                imageView.setFitHeight(1000);
                imageView.setFitWidth(1000);

                aux.setImage(imageView);

            
          lista.add(aux);
          }

        colref.setCellValueFactory(new PropertyValueFactory<>("reference"));
        coldossier.setCellValueFactory(new PropertyValueFactory<>("Image"));
        coliddos.setCellValueFactory(new PropertyValueFactory<>("id"));

        
        tabdossier.setItems(lista);
        
    }
    
    @FXML
    private void modifier_dos(ActionEvent event) throws IOException {
    
        dossier res= new dossier();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/medica/dossier/modifierDossier.fxml"));
            Parent root = loader.load();
          ModifierDossierController HomeScene = loader.getController();
          System.out.println("aaaaa");
          serviceDossier sm= new serviceDossier();
         //String nomMed = sm.load_data2();
            HomeScene.selected_item(
                     
                  tabdossier.getSelectionModel().getSelectedItem().getId(),
                    tabdossier.getSelectionModel().getSelectedItem().getReference(),
                    tabdossier.getSelectionModel().getSelectedItem().getFichier()
            );
                       
                 System.out.println("bbbbbbbbbbbbb");
           
            Stage window = (Stage) modif_dos.getScene().getWindow();
            window.setScene(new Scene(root, 904, 581));
    }

    @FXML
    private void rechercherObjectif(KeyEvent event) {
         System.out.println("recherche");
        serviceDossier sop = new serviceDossier();
        ObservableList<dossier> dossier = sop.rechercherDossier(tfrechres.getText());
                 System.out.println("recherche valide");


        tabdossier.setItems(dossier);
    }
    
    
    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
    }

    
    @FXML
    private void ajout(ActionEvent event) {
    }

    private void handleClicks(ActionEvent event) {
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
    private void PDF(ActionEvent event)throws FileNotFoundException,DocumentException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste des dossiers medicaux?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServicePdf sp= new ServicePdf();
            sp.liste_dossierPDF();
        }
        
        
    }
    
    
    public static void printNode(final Node node) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    Printer printer = Printer.getDefaultPrinter();
    PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);
    PrinterAttributes attr = printer.getPrinterAttributes();
    PrinterJob job = PrinterJob.createPrinterJob();
    double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
    double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
    Scale scale = new Scale(scaleX, scaleY);
    node.getTransforms().add(scale);

    if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
      boolean success = job.printPage(pageLayout, node);
      if (success) {
        job.endJob();

      }
    }
    node.getTransforms().remove(scale);
    
  }

    @FXML
    private void Imprimer(ActionEvent event) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        printNode(tabdossier);
    }

    private void handleClicks1(ActionEvent event) {
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
    private void handleClicks4(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/espaceClient.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(EspaceClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }
    
    
}
