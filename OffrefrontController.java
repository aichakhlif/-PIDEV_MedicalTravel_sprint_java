/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zerobug69;
import entitesyassin.MyListener;
import entitesyassin.offre;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import medica.reservation.ReservationController;
   
/**
 * FXML Controller class
 *
 * @author Asus
 */
public class OffrefrontController implements Initializable {
 @FXML
    private VBox chosenclinique;

    @FXML
    private Label clq;

    @FXML
    private Label med;

    @FXML
    private Label inter;

    @FXML
    private Label pr;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;
    private MyListener mylistener;
    @FXML
    private Button home;
    



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO off.addAll(getData());
        if(off.size()>0){
            setchosenclq(off.get(0));
            mylistener=new MyListener(){
          @Override
          public void onClickListener(offre offre){
            setchosenclq(offre);
        }
            };
        int column=0;
        int row=1;
        try {
        for(int i=0;i<off.size();i++){
            FXMLLoader fxmlloader = new FXMLLoader();
            fxmlloader.setLocation(getClass().getResource("ModelOffre.fxml"));
            
                AnchorPane anchorpane=fxmlloader.load();
           
            ModelOffreController moc=fxmlloader.getController();
            moc.setData(off.get(i),mylistener);
            if(column==3){
                column=0;
                row++;
            }
            
            grid.add(anchorpane, column++, row);
               grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                 grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

            GridPane.setMargin(anchorpane,new Insets(10));
        }
          } catch (IOException ex) {
                Logger.getLogger(FXMLmController.class.getName()).log(Level.SEVERE, null, ex);
            }   
            
        
      
        }
        
        
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
    private void setchosenclq(offre offre){
       clq.setText(offre.getClinique_id());
       med.setText(offre.getMedecin_id());
       inter.setText(offre.getIntervention_id());
       pr.setText(""+offre.getPrix());
       chosenclinique.setStyle("-fx-background-color: #171717;\n"+
    "-fx-background-radius: 30;");
   
    
    } public Connection getConnection(){
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
                    offre=new offre(rs.getInt("id"),rs.getString("nom"),rs.getString("nom1"),rs.getString("nom2"),rs.getInt("prix"));
                    cliniques.add(offre) ;
                            }
                        
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                return cliniques;
                }
    private List<offre> off =getoffreList();
     private List<offre> getData(){
         List<offre> off =new ArrayList<>();
         offre offre;
       return off;
     }
       @FXML
        public void changeScreenView(ActionEvent event) throws IOException{
        Parent tableViewParent=FXMLLoader.load(getClass().getResource("FXMLm.fxml"));
        Scene tableViewScene= new Scene(tableViewParent);
        Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
        
    }

    @FXML
    private void home(ActionEvent event) {
    }
    
}
