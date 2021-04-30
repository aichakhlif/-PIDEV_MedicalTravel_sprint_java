/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zerobug69;

import Servicesyassin.ServicesClinique;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import database.ConnexionSingleton;
import entitesyassin.clinique;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import medica.reservation.ReservationController;
import org.controlsfx.control.Notifications;


public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfnom;
    
    @FXML
    private TextField tfemail;
    
    @FXML
    private TableColumn<clinique, Integer> collid;
   
    @FXML
    private TableColumn<clinique,String> collemail;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tftel;
    @FXML
    private TableColumn<clinique,String> coladresse;
    @FXML
    private TableColumn<clinique,Integer> coltel;
    @FXML
    private TableColumn<clinique,String> colnom;
    @FXML
    private TableView<clinique> tab_cli;
    @FXML
    private TextField filtrefield;
    @FXML
    private ImageView checkadr;
    @FXML
    private ImageView checkNom;
    @FXML
    private ImageView checkNum;
    @FXML
    private ImageView emailcheck;
    @FXML
    private TextField tfimage;
    @FXML
    private Button uploadbutton;
    @FXML
    private TableColumn<clinique, ImageView> colimage;
    @FXML
    private Button tri;
    @FXML
    private Button tri1;
    @FXML
    private Button tri2;
    @FXML
    private Button tri22;
    @FXML
    private TableColumn<clinique, String> colspec;
    @FXML
    private TextField tfspec;
    @FXML
    private ImageView checkspec;
    @FXML
    private Button home;
    @FXML
    private Label btnOrders1;
 
  
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException {
      
        if (event.getSource()== btnajout){
            insertclq();
        }
        else if (event.getSource() == btnmodifier){
        updateclq();
    
    }
        else if (event.getSource() == btnsupprimer){
        Deleteclq();
        
    }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showClinique();
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
}   public ObservableList<clinique> getCliniqueList(){
        ObservableList<clinique> cliniques =FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query="SELECT *From clinique";
        Statement st;
        ResultSet rs ;
                try {
                    st= conn.createStatement();
                    rs=st.executeQuery(query);
                    clinique clinique;
                    while(rs.next()){
                    clinique=new clinique(rs.getInt("id"),rs.getString("adresse"),rs.getInt("tel"),rs.getString("nom"),rs.getString("image"),rs.getString("specialite"),rs.getString("email"));
                    cliniques.add(clinique) ;
                            }
                        
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                return cliniques;
                }
    
    @FXML
    public void getCliniqueListTri(){
        ObservableList<clinique> cliniques =FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query="SELECT * FROM `clinique` order by id DESC";
        Statement st;
        ResultSet rs ;
                try {
                    st= conn.createStatement();
                    rs=st.executeQuery(query);
                    clinique clinique;
                    while(rs.next()){
                    clinique=new clinique(rs.getInt("id"),rs.getString("adresse"),rs.getInt("tel"),rs.getString("nom"),rs.getString("image"),rs.getString("specialite"),rs.getString("email"));
                    cliniques.add(clinique) ;
                            }
                        
                }catch (Exception ex){
                    ex.printStackTrace();
                }
collid.setCellValueFactory(new PropertyValueFactory<clinique, Integer>("id"));
coladresse.setCellValueFactory(new PropertyValueFactory<clinique, String>("adresse"));
colnom.setCellValueFactory(new PropertyValueFactory<clinique, String>("nom"));
collemail.setCellValueFactory(new PropertyValueFactory<clinique, String>("email"));
coltel.setCellValueFactory(new PropertyValueFactory<clinique, Integer>("tel"));
colimage.setCellValueFactory(new PropertyValueFactory<clinique,ImageView>("img"));
colspec.setCellValueFactory(new PropertyValueFactory<clinique,String>("specialite"));
tab_cli.setItems(cliniques);

                }
    @FXML
    public void getCliniqueListTri1(){
        ObservableList<clinique> cliniques =FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query="SELECT * FROM `clinique` order by id ASC";
        Statement st;
        ResultSet rs ;
                try {
                    st= conn.createStatement();
                    rs=st.executeQuery(query);
                    clinique clinique;
                    while(rs.next()){
                    clinique=new clinique(rs.getInt("id"),rs.getString("adresse"),rs.getInt("tel"),rs.getString("nom"),rs.getString("image"),rs.getString("specialite"),rs.getString("email"));
                    cliniques.add(clinique) ;
                            }
                        
                }catch (Exception ex){
                    ex.printStackTrace();
                }
collid.setCellValueFactory(new PropertyValueFactory<clinique, Integer>("id"));
coladresse.setCellValueFactory(new PropertyValueFactory<clinique, String>("adresse"));
colnom.setCellValueFactory(new PropertyValueFactory<clinique, String>("nom"));
collemail.setCellValueFactory(new PropertyValueFactory<clinique, String>("email"));
coltel.setCellValueFactory(new PropertyValueFactory<clinique, Integer>("tel"));
colimage.setCellValueFactory(new PropertyValueFactory<clinique,ImageView>("img"));
colspec.setCellValueFactory(new PropertyValueFactory<clinique,String>("specialite"));
tab_cli.setItems(cliniques);

                }
    
public void showClinique(){
ObservableList<clinique> list=getCliniqueList();
ObservableList<clinique> lista = FXCollections.observableArrayList();

        for (clinique aux : list){
                        File file = new File(aux.getImg());
                Image image = new Image(file.toURI().toString());
                System.out.println(image.toString()); 
               ImageView imageView =new ImageView(image);
                imageView.setImage(image);
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);

                aux.setImage(imageView);

            
          lista.add(aux);
        }

collid.setCellValueFactory(new PropertyValueFactory<clinique, Integer>("id"));
coladresse.setCellValueFactory(new PropertyValueFactory<clinique, String>("adresse"));
colnom.setCellValueFactory(new PropertyValueFactory<clinique, String>("nom"));
collemail.setCellValueFactory(new PropertyValueFactory<clinique, String>("email"));
coltel.setCellValueFactory(new PropertyValueFactory<clinique, Integer>("tel"));
colimage.setCellValueFactory(new PropertyValueFactory<clinique,ImageView>("img"));
colspec.setCellValueFactory(new PropertyValueFactory<clinique,String>("specialite"));
tab_cli.setItems(list);
search_prom();


}
private void insertclq() throws SQLException, IOException {
    if (testSaisie()){
ServicesClinique su= new ServicesClinique();
  File f = new File(tfimage.getText());
String adresse=tfadresse.getText();
    String nom=tftel.getText();
     String email=tfnom.getText();
    String tel=tfemail.getText();
    String spec=tfspec.getText();
      String img ="C:\\Users\\admin\\Downloads\\medicatravel - aicha\\medicatravel\\src\\yassinimg\\"+f.getName();
clinique u =new clinique(adresse,parseInt(tel),nom,img,spec,email);
Files.copy(Paths.get(tfimage.getText().replace('\\', '/')),Paths.get("C:\\Users\\admin\\Downloads\\medicatravel - aicha\\medicatravel\\src\\yassinimg\\".replace('\\', '/')+f.getName()),REPLACE_EXISTING);
        
su.AddClinique(u);
Notifications notificationBuilder =Notifications.create()
        .title("Gestion de Clinique")
        .text("ajout de clinique passé avec succés")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT);
notificationBuilder.darkStyle();
notificationBuilder.showInformation();
showClinique();

    }
}
private void updateclq() throws SQLException, IOException{
    if (testSaisie()){
  int id=parseInt(tfid.getText());
  String adresse=tfadresse.getText();
    String nom=tftel.getText();
     String email=tfnom.getText();
    String tel=tfemail.getText();
    String spec=tfspec.getText();
    
    File f = new File(tfimage.getText());
     String img ="C:\\Users\\admin\\Downloads\\medicatravel - aicha\\medicatravel\\src\\yassinimg"+f.getName();
    ServicesClinique su= new ServicesClinique();
    clinique u =new clinique(id,adresse,parseInt(tel),nom,img,spec,email);
    su.update(u);
    Notifications notificationBuilder =Notifications.create()
        .title("Gestion de Clinique")
        .text("Modification de clinique passé avec succés")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT);
notificationBuilder.darkStyle();
notificationBuilder.showInformation();
    
     Files.copy(Paths.get(tfimage.getText()),Paths.get("C:\\Users\\admin\\Downloads\\medicatravel - aicha\\medicatravel\\src\\yassinimg"+f.getName()),REPLACE_EXISTING);

showClinique();}}
private void Deleteclq(){
    String query = "DELETE FROM clinique WHERE id= " +tfid.getText()+"";
    executeQuery(query);
    Notifications notificationBuilder =Notifications.create()
        .title("Gestion de Clinique")
        .text("Suppression de clinique passé avec succés")
        .graphic(null)
        .hideAfter(Duration.seconds(5))
        .position(Pos.BOTTOM_RIGHT);
notificationBuilder.darkStyle();
notificationBuilder.showInformation();


    showClinique();
    
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
        clinique clinique = tab_cli.getSelectionModel().getSelectedItem();
        tfid.setText(""+clinique.getId());
        tfadresse.setText(clinique.getAdresse());
       tftel.setText(clinique.getNom());
        tfnom.setText(clinique.getEmail());
         tfemail.setText(""+clinique.getTel());
         tfspec.setText(""+clinique.getSpecialite());
    }
    public void changeScreenView(ActionEvent event) throws IOException{
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        
    }
       public void changeScreenView1(ActionEvent event) throws IOException{
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("FXMLOFFRE.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        
    }
    void search_prom() {
        clinique p = new clinique();
        
collid.setCellValueFactory(new PropertyValueFactory<clinique, Integer>("id"));
coladresse.setCellValueFactory(new PropertyValueFactory<clinique, String>("adresse"));
colnom.setCellValueFactory(new PropertyValueFactory<clinique, String>("nom"));
collemail.setCellValueFactory(new PropertyValueFactory<clinique, String>("email"));
coltel.setCellValueFactory(new PropertyValueFactory<clinique, Integer>("tel"));

ObservableList<clinique> dataList;


        dataList = getCliniqueList();
       
        tab_cli.setItems(dataList);
       
        FilteredList<clinique> filteredData = new FilteredList<>(dataList, b -> true);
       
        filtrefield.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((clinique promo) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (promo.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
               
                } else if (promo.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                        }
              
                else {
                    return false; // Does not match.
                }
            });
        });
        SortedList<clinique> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tab_cli.comparatorProperty());
        tab_cli.setItems(sortedData);
    }
    private Boolean testTel() {
        if (tfemail.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < tfemail.getText().trim().length(); i++) {
                char ch = tfemail.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
                checkNum.setImage(new Image("yassinimg/checkMark.png") {});
                
                return true;
            } else {
                checkNum.setImage(new Image("yassinimg/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }
        } else if (tfemail.getText().trim().length() != 8) {
//            erreur = erreur + ("Il faut saisir 8 chiffres dans le numéro de telephone\n");
                            checkNum.setImage(new Image("yassinimg/erreurCheckMark.png"));

            return false;
        }

        return true;

    }
private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < tftel.getText().trim().length(); i++) {
            char ch = tftel.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && tftel.getText().trim().length() >= 3) {
            checkNom.setImage(new Image("yassinimg/checkMark.png"));
            
            return true;
        } else {
            checkNom.setImage(new Image("yassinimg/erreurCheckMark.png"));
          
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
private Boolean testPrenom() {
        int nbNonChar = 0;
        for (int i = 1; i < tfadresse.getText().trim().length(); i++) {
            char ch = tfadresse.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && tfadresse.getText().trim().length() >= 3) {
            checkadr.setImage(new Image("yassinimg/checkMark.png"));
            
            
            return true;
        } else {
           checkadr.setImage(new Image("yassinimg/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
private Boolean testSpec() {
        int nbNonChar = 0;
        for (int i = 1; i < tfspec.getText().trim().length(); i++) {
            char ch = tfspec.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && tfspec.getText().trim().length() >= 3) {
            checkspec.setImage(new Image("yassinimg/checkMark.png"));
            
            
            return true;
        } else {
           checkspec.setImage(new Image("yassinimg/erreurCheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }
private Boolean testMail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (tfnom.getText() == null) {
            return false;
        }

        if (pat.matcher(tfnom.getText()).matches() == false) {
            emailcheck.setImage(new Image("yassinimg/erreurCheckMark.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            emailcheck.setImage(new Image("yassinimg/checkMark.png"));
        }
        return true;

    }
private Boolean testSaisie() {
        String erreur = "";
        
        if (!testTel()) {
            erreur = erreur + ("Telephone doit avoir 8 chiffres et ne doit pas contenir des caracteres \n");
        }
        
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
        }
         if (!testMail()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
         }
        if (!testSpec()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
         }

        return   testTel()  && testNom() && testPrenom()&& testMail() &&testSpec();
    }
    @FXML
    private void ExportPDF(ActionEvent event) throws DocumentException, SQLException {
             try {
            String file_name = ("LISTC.pdf");
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            
            document.addTitle("Votre Liste ");
            Paragraph paraHeader2= new Paragraph("                                                                   ".concat("Vos Cliniques"));
             document.add(paraHeader2);
             Paragraph paraHeader3= new Paragraph("             "
                     + ""
                     + ""
             );
               document.add(paraHeader3);
                 Paragraph paraHeader1 = new Paragraph((("Adresse".concat("                    ")).concat("Nom".concat("                    ")).concat("Email".concat("                    ")).concat("Numéro de Telephone".concat("                    "))));
            document.add(paraHeader1);
           //         ObservableList<Message> list =pcr.getMessage();
                  // while(list)

            
                 // ObservableList<Message> ProductList = FXCollections.observableArrayList();
        String requete = "SELECT id,adresse,nom,email,tel FROM clinique";
        PreparedStatement pst = ConnexionSingleton.getInstance().getConnection()
                .prepareStatement(requete);
        //Statement st;
        ResultSet rs;
        try {
            //System.out.println("AHAYYYAA!!!!");
            //st=conn.createStatement();
            //System.out.println("AHAYYYAA222!!!!");
            rs = pst.executeQuery(requete);
            
            Message p;
            
            while (rs.next()) {
                // Message msg = new Message(rs.getInt("id_msg"),rs.getString("message"), rs.getString("id_posteur"),rs.getDate("date_heure_post"));
                // ProductList.add(msg);
                Paragraph paraHeader11 = new Paragraph((rs.getString("adresse")).concat("                ").concat(rs.getString("nom")).concat("                ").concat(rs.getString("email").concat("                ")).concat(rs.getString("tel")));
                document.add(paraHeader11);
            }
            
        } catch (Exception ex) {
            //System.out.println("AHAYYYAA L7KEEEYAAAAA!!!!");
            ex.printStackTrace();
        }
                  
    
            
            
            document.close();
            Desktop.getDesktop().open(new File(file_name));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Uploadfile(ActionEvent event) {
     
           FileChooser fc = new FileChooser();
        String path = fc.showOpenDialog(uploadbutton.getScene().getWindow()).getPath();
       tfimage.setText(path);
    }

    @FXML
    private void getCliniqueListTri2() {
        ObservableList<clinique> cliniques =FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query="SELECT * FROM `clinique` order by adresse DESC";
        Statement st;
        ResultSet rs ;
                try {
                    st= conn.createStatement();
                    rs=st.executeQuery(query);
                    clinique clinique;
                    while(rs.next()){
                    clinique=new clinique(rs.getInt("id"),rs.getString("adresse"),rs.getInt("tel"),rs.getString("nom"),rs.getString("image"),rs.getString("specialite"),rs.getString("email"));
                    cliniques.add(clinique) ;
                            }
                        
                }catch (Exception ex){
                    ex.printStackTrace();
                }
collid.setCellValueFactory(new PropertyValueFactory<clinique, Integer>("id"));
coladresse.setCellValueFactory(new PropertyValueFactory<clinique, String>("adresse"));
colnom.setCellValueFactory(new PropertyValueFactory<clinique, String>("nom"));
collemail.setCellValueFactory(new PropertyValueFactory<clinique, String>("email"));
coltel.setCellValueFactory(new PropertyValueFactory<clinique, Integer>("tel"));
colimage.setCellValueFactory(new PropertyValueFactory<clinique,ImageView>("img"));
colspec.setCellValueFactory(new PropertyValueFactory<clinique,String>("specialite"));
tab_cli.setItems(cliniques);
    }

    @FXML
    private void getCliniqueListTri22() {
        ObservableList<clinique> cliniques =FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query="SELECT * FROM `clinique` order by adresse ASC";
        Statement st;
        ResultSet rs ;
                try {
                    st= conn.createStatement();
                    rs=st.executeQuery(query);
                    clinique clinique;
                    while(rs.next()){
                    clinique=new clinique(rs.getInt("id"),rs.getString("adresse"),rs.getInt("tel"),rs.getString("nom"),rs.getString("image"),rs.getString("specialite"),rs.getString("email"));
                    cliniques.add(clinique) ;
                            }
                        
                }catch (Exception ex){
                    ex.printStackTrace();
                }
collid.setCellValueFactory(new PropertyValueFactory<clinique, Integer>("id"));
coladresse.setCellValueFactory(new PropertyValueFactory<clinique, String>("adresse"));
colnom.setCellValueFactory(new PropertyValueFactory<clinique, String>("nom"));
collemail.setCellValueFactory(new PropertyValueFactory<clinique, String>("email"));
coltel.setCellValueFactory(new PropertyValueFactory<clinique, Integer>("tel"));
colimage.setCellValueFactory(new PropertyValueFactory<clinique,ImageView>("img"));
colspec.setCellValueFactory(new PropertyValueFactory<clinique,String>("specialite"));
tab_cli.setItems(cliniques);
    }

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }

}

