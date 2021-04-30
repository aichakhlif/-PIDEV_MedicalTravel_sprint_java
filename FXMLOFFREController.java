/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zerobug69;
import javafx.util.Duration;
import Servicesyassin.ServicesOffre;
import database.ConnexionSingleton;
import entitesyassin.JavaMailUtil;
import entitesyassin.clinique;
import entitesyassin.intervention;
import entitesyassin.medecin;
import entitesyassin.offre;
import static java.awt.SystemColor.text;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import medica.reservation.ReservationController;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FXMLOFFREController implements Initializable {

    @FXML
    private TextField ipid;
   
    @FXML
    private TextField ippr;
    @FXML
    private TableColumn<offre,Integer> afid;
    @FXML
    private TableColumn<offre,Integer> afcl;
    @FXML
    private TableColumn<offre,Integer> afmed;
    @FXML
    private TableColumn<offre,Integer> afint;
    @FXML
    private TableColumn<offre,Integer> afpr;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TableView<offre> tab_off;
    @FXML
    private ComboBox<String> cmbclq;
    @FXML
    private ComboBox<String> cmbmed;
    @FXML
    private ComboBox<String> cmbinter;
    @FXML
    private DatePicker tfedate;
    @FXML
    private TableColumn<offre, Date> afdate;
    @FXML
    private Button home;
    @FXML
    private Label btnOrders1;
      @FXML
   private void handleButtonAction(ActionEvent event) throws SQLException, MessagingException {
        
        if (event.getSource()== btnajout){
            insertoff();
        }
        else if (event.getSource() == btnmodifier){
        updateoff();
    
    }
        else if (event.getSource() == btnsupprimer){
        Deleteoff();
    }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showoffre();
        FillComboBox();
        FillComboBox1();
        FillComboBox2();
        
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
    public Connection getConnection(){
        Connection conn;
                try{
conn= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/medicatravel3", "root", "");
return conn ; 
}catch(Exception ex){
    System.out.println("Eroor de connexion");
    return null ; 
}
}
    public ObservableList<offre> getoffreList(){
        ObservableList<offre> cliniques =FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query="SELECT a.id, b.nom, c.nom as nom1, d.type as nom2, a.prix, a.date FROM offre as a, clinique as b, medecin as c, intervention as d WHERE a.clinique_id=b.id and a.medecin_id=c.id and a.intervention_id=d.id";
        Statement st;
        ResultSet rs ;
                try {
                    st= conn.createStatement();
                    rs=st.executeQuery(query);
                    offre offre;
                    while(rs.next()){
                    offre=new offre(rs.getInt("id"),rs.getString("nom"),rs.getString("nom1"),rs.getString("nom2"),rs.getInt("prix"),rs.getDate("date"));
                    cliniques.add(offre) ;
                            }
                        
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                return cliniques;
                }
    
public void showoffre(){
ObservableList<offre> list=(ObservableList<offre>) getoffreList();

afid.setCellValueFactory(new PropertyValueFactory<offre, Integer>("id"));
afcl.setCellValueFactory(new PropertyValueFactory<offre, Integer>("clinique_id"));
afmed.setCellValueFactory(new PropertyValueFactory<offre, Integer>("medecin_id"));
afint.setCellValueFactory(new PropertyValueFactory<offre, Integer>("intervention_id"));
afpr.setCellValueFactory(new PropertyValueFactory<offre, Integer>("prix"));
afdate.setCellValueFactory(new PropertyValueFactory<offre, Date>("date"));
tab_off.setItems(list);
}
private void insertoff() throws SQLException, MessagingException{
    Statement stz;
    ResultSet rs;
    Connection conn  ;
    String result1="";
            conn =ConnexionSingleton.getInstance().getConnection();
ServicesOffre su= new ServicesOffre();
LocalDate D =tfedate.getValue();
offre u =new offre(cmbclq.getValue(),cmbmed.getValue(),cmbinter.getValue(),parseInt(ippr.getText()),Date.valueOf(D));
stz=conn.createStatement();
su.AddOffre(u);
showoffre();
String result="SELECT c.email from clinique as c , offre as o where c.nom='"+cmbclq.getValue()+"' and o.clinique_id=c.id ";
rs =stz.executeQuery(result);
 while(rs.next()){
         result1 = rs.getString(1);
              }
 JavaMailUtil.sendmail(result1,"Nouvelle offre","Votre clinique est ajouté dans une nouvelle offre");

Notifications notificationBuilder =Notifications.create()
        .title("Gestion d'Offre")
        .text("Ajout d'offre passé avec succés")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT);
notificationBuilder.darkStyle();
notificationBuilder.showInformation();




}
private void updateoff() throws SQLException{
int id=parseInt(ipid.getText());
  String cli=cmbclq.getValue();
    String med=cmbmed.getValue();
     String inter=cmbinter.getValue();
    String prix=ippr.getText();
    LocalDate date=tfedate.getValue();
    ServicesOffre su= new ServicesOffre();
    offre u =new offre(id,cli,med,inter,parseInt(prix),Date.valueOf(date));
    su.update(u);
Notifications notificationBuilder =Notifications.create()
        .title("Gestion d'Offre")
        .text("Modification d'offre passé avec succés")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT);
notificationBuilder.darkStyle();
notificationBuilder.showInformation();
showoffre();

}
private void Deleteoff(){
    String query = "DELETE FROM offre WHERE id= " +ipid.getText()+"";
    executeQuery(query);
    Notifications notificationBuilder =Notifications.create()
        .title("Gestion d'Offre")
        .text("Suppression d'offre passé avec succés")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT);
notificationBuilder.darkStyle();
notificationBuilder.showInformation();
    showoffre();
    
}

    private void executeQuery(String query) {
        Connection conn= getConnection();
        Statement st;
        try{
            st=conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        offre offre = tab_off.getSelectionModel().getSelectedItem();
        ipid.setText(""+offre.getId());
        cmbclq.setValue(""+offre.getClinique_id());
       cmbmed.setValue(""+offre.getMedecin_id());
        cmbinter.setValue(""+offre.getIntervention_id());
         ippr.setText(""+offre.getPrix());
         
    }
    
    public void changeScreenView(ActionEvent event) throws IOException{
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        
    }
    public void changeScreenView1(ActionEvent event) throws IOException{
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("FXMLOffre.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        
    }
    public void FillComboBox() {

        try {
            String query = " SELECT * FROM clinique";
            PreparedStatement pst = ConnexionSingleton.getInstance().getConnection()
                    .prepareStatement(query);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                clinique c1 = new clinique( rs.getInt(1),rs.getString(2),rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7));

                cmbclq.getItems().add(rs.getString("nom"));
                

               

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }}
        public void FillComboBox1() {

        try {
            String query = " SELECT * FROM medecin";
            PreparedStatement pst = ConnexionSingleton.getInstance().getConnection()
                    .prepareStatement(query);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                medecin med1 = new medecin(rs.getInt(1), rs.getString(2));

                cmbmed.getItems().add(rs.getString("nom"));
                

               

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }}
        
public void FillComboBox2() {

        try {
            String query = " SELECT * FROM intervention";
            PreparedStatement pst = ConnexionSingleton.getInstance().getConnection()
                    .prepareStatement(query);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                intervention inter1 = new intervention(rs.getInt(1), rs.getString(2));

                cmbinter.getItems().add(rs.getString("type"));
                

               

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicesOffre.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }
}

