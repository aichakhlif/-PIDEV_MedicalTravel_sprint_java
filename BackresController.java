/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.reservation;

import Service.ServiceClinique;
import Service.ServiceMail;
import Service.ServiceMedecin;
import Service.ServicePdf;
import Service.ServiceReservation;
import com.itextpdf.text.DocumentException;
import entities.reservation;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultKeyedValuesDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class BackresController implements Initializable {

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
    private TextField tfrechres;
    @FXML
    private Label btnOrders1;
    @FXML
    private ComboBox< String> cbtriObjPred;
    @FXML
    private Button envoyer_mail_client;
    @FXML
    private TextField object_mail_client;
    @FXML
    private TextArea subject_mail_client;
    @FXML
    private TextField emaill;
    private GridPane grid_obj;

    private List<reservation> res = new ArrayList<>();
    @FXML
    private Label btnOrders2;
    @FXML
    private Button home;

   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceReservation sRes = new ServiceReservation();
        ObservableList<reservation> reservation = sRes.afficherReservationList();
        afficherReservationList();

        ObservableList<String> listTriObjPred = FXCollections.observableArrayList("par medecin", "par intervention", "par clinique");
        cbtriObjPred.setItems(listTriObjPred);
        
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

    @FXML
    private void OnMouseObjectifPred(MouseEvent event) {
        reservation res = tabReservation.getSelectionModel().getSelectedItem();
        emaill.setText(res.getEmail());
    }


    @FXML
    private void rechercherObjectif(KeyEvent event) {
        System.out.println("recherche");
        ServiceReservation sop = new ServiceReservation();
        ObservableList<reservation> reservation = sop.rechercherReservation(tfrechres.getText());
        System.out.println("recherche valide");

        tabReservation.setItems(reservation);
    }

    @FXML
    private void PDF(ActionEvent event) throws FileNotFoundException, DocumentException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de création du PDF");
        alert.setHeaderText("Etes vous sur de vouloir créer un PDF qui contient la liste de vos reservation?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServicePdf sp = new ServicePdf();
            sp.liste_reservationPDF();
        }

    }

    @FXML
    private void selectTriObjPred(ActionEvent event) {
        ServiceReservation sop = new ServiceReservation();
        ObservableList<reservation> reservation = FXCollections.observableArrayList();
        if (cbtriObjPred.getValue().equals("par medecin")) {
            reservation = sop.trierReservationmedecin();
        } else if (cbtriObjPred.getValue().equals("par intervention")) {
            reservation = sop.trierReservationinter();
        } else if (cbtriObjPred.getValue().equals("par clinique")) {
            reservation = sop.trierReservationclinique();
        }
        tabReservation.setItems(reservation);
    }

    @FXML
    private void envoyer_mail_client(ActionEvent event) {
        ServiceReservation sc = new ServiceReservation();
        ServiceMail sm = new ServiceMail();

        //Client c = sc.load_data_modify(id);
        String email = emaill.getText();
        String object = object_mail_client.getText();
        String subject = subject_mail_client.getText();
        System.out.println(emaill.getText());
        System.out.println(object_mail_client.getText());
        System.out.println(subject_mail_client.getText());

        sm.envoyerMail(email, object, subject);
        System.out.println("envoyer !!!");
        // information_Box("Sucees", "Votre mail est bien recu");
        emaill.setText(null);
        subject_mail_client.setText(null);
        object_mail_client.setText(null);

    }

    @FXML
    private void click_tab_patient(MouseEvent event) {
    }

    @FXML
    private void btn_bilan(ActionEvent event) {
        //reservation obj = new reservation();
       // ServiceReservation sr = new ServiceReservation();
        ServiceClinique clini = new ServiceClinique();
        DefaultPieDataset pieDataset = new DefaultKeyedValuesDataset();
        //    int med = obj.getIdres();
       for (int i = 0; i <clini.nb_clinique(); i++)
       { String msg="";
           msg=clini.getValuesClinique().get(i) + " [ " + clini.getnb_reservation_Clinique().get(i)+ " ] ";
           pieDataset.setValue(msg,clini.getnb_reservation_Clinique().get(i) );
        System.out.println(clini.getValuesClinique().get(i));
                System.out.println(clini.getnb_reservation_Clinique().get(i));
              System.out.println  (clini.nb_clinique());

       }
        //   pieDataset.setValue("les jasmins",60);

        //   pieDataset.setValue("Fait", (ss.getValeur(id, cb.getValue()) * 100) / sp.getRepObj(id));
        JFreeChart chart = ChartFactory.createPieChart("Bilan de reservation: ", pieDataset);
        PiePlot P = (PiePlot) chart.getPlot();
        ChartFrame frame = new ChartFrame("Bilan reseration", chart);
        frame.setVisible(true);
        frame.setSize(450, 500);
    }
@FXML
    private void btn_bilan_medecin(ActionEvent event) {
        reservation obj = new reservation();
        ServiceReservation sr = new ServiceReservation();
        ServiceMedecin med = new ServiceMedecin();
        DefaultPieDataset pieDataset = new DefaultKeyedValuesDataset();
        //    int med = obj.getIdres();
       for (int i = 0; i <med.nb_medecins(); i++)
       { 
           String msg="";
           msg=med.getValuesMedecin().get(i) + " [ " + med.getnb_reservation_Medecins().get(i)+ " ] ";
           pieDataset.setValue(msg,med.getnb_reservation_Medecins().get(i) );
        System.out.println(med.getValuesMedecin().get(i));
               

       }
          // pieDataset.setValue("les jasmins",60);

        //   pieDataset.setValue("Fait", (ss.getValeur(id, cb.getValue()) * 100) / sp.getRepObj(id));
        JFreeChart chart = ChartFactory.createPieChart("Bilan de reservation: ", pieDataset);
        PiePlot P = (PiePlot) chart.getPlot();
        ChartFrame frame = new ChartFrame("Bilan reseration", chart);
        frame.setVisible(true);
        frame.setSize(450, 500);
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }

    @FXML
    private void home(ActionEvent event) {
    }
}
