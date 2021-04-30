/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medica.dossier;



import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.CalendarAdapter;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.DayOfWeekFormat;
import com.mindfusion.scheduling.ItemMouseEvent;
import com.mindfusion.scheduling.WeekRangeHeaderStyle;
import com.mindfusion.scheduling.model.Appointment;
import com.mindfusion.scheduling.model.Item;
import com.mindfusion.scheduling.model.ItemEvent;
import com.mindfusion.scheduling.model.RecurrenceState;
import com.mindfusion.scheduling.standardforms.AppointmentForm;
import com.mindfusion.scheduling.standardforms.DialogResult;
import entitiesLinda.rendez_vous;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.EnumSet;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.SwingUtilities;
import medica.rendezvous.MailUtil;
import medica.reservation.ReservationController;
import org.controlsfx.control.Notifications;
import servicesLinda.ServiceMedecin;
import servicesLinda.serviceRendezvous;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjoutRendezvousController extends JFrame implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private Button ok;
    @FXML
    private TextField heure;
    @FXML
    private ComboBox<String> med;
    @FXML
    private Label btnOrders1;
    @FXML
    private Button calendrier;
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceMedecin medec = new ServiceMedecin();
       

        ObservableList<String> listmed = medec.getValuesMedecin();
        med.setItems(listmed);
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
    private void ajoutRendezvous(ActionEvent event) throws Exception {
        MailUtil.sendMail("linda.kheder@esprit.tn");
       Image image =new Image("/images/ok.png");
       
        Notifications notifications=Notifications.create();
        notifications.graphic(new ImageView(image));
        notifications.text("ajout avec succees");
        notifications.title("success message");
        notifications.darkStyle();
        notifications.hideAfter(javafx.util.Duration.seconds(4));
        notifications.show();
        
        
         ServiceMedecin medec = new ServiceMedecin();
        
        serviceRendezvous sRen = new serviceRendezvous();

        rendez_vous Ren = new rendez_vous();
final DatePicker datePicker = new DatePicker(LocalDate.now());
        LocalDate id=date.getValue();
        Ren.setDate(id.toString());
        //Ren.setDate(date.getText());
        Ren.setHeure(heure.getText());
        
        Ren.setMed(medec.load_data(med.getValue()));
        

        sRen.addRendezvous(Ren);

        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/rendezvous.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RendezvousController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    private void handleClicks(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/rendezvous/rendezvous.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void handleClicks1(ActionEvent event) {
        
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/medica/dossier/dossier.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void calendrier(ActionEvent event) {
           SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					new medica.rendezvous.AjoutRendezvousController().setVisible(true);
				}
				catch (Exception exp)
				{
                                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				}
			}
		});
    }
    

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void handleClicks(MouseEvent event) {
    }

    
      public AjoutRendezvousController()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 400);
		setTitle("Java Swing Scheduling Library: Appointment Forms");
		
		calendar = new Calendar();
		getContentPane().add(calendar, BorderLayout.CENTER);
		calendar.beginInit();
		calendar.setCurrentView(CalendarView.WeekRange);
		calendar.getWeekRangeSettings().setHeaderStyle(EnumSet.of(WeekRangeHeaderStyle.Title));
		calendar.setDate(new DateTime(2021, 12, 1));
		calendar.setEndDate(new DateTime(2021, 12, 31));
		calendar.setCurrentTime(new DateTime(2021, 04, 28));
		calendar.getWeekRangeSettings().setDayOfWeekFormat(DayOfWeekFormat.Full);
		calendar.setEnableDragCreate(true);
		
		
		calendar.endInit();
		
		calendar.addCalendarListener(new CalendarAdapter(){
			
			private void showForm(Item item)
			{
				AppointmentForm form = new AppointmentForm(calendar.getSchedule());
				form.setAppointment((Appointment)item);
				form.setVisible(true);
				
				form.addWindowListener(new WindowAdapter()
				{
					@Override
					public void windowClosed(WindowEvent we)
					{
						if (form.getDialogResult() == DialogResult.Remove)
						{
							// 'Remove' is returned when the user clicks the 'Delete' button.
							// It is up to us to delete the item from the schedule.
							if (item.getRecurrenceState() == RecurrenceState.Occurrence ||
								item.getRecurrenceState() == RecurrenceState.Exception)
								item.getRecurrence().markException(item, true);
							else
								calendar.getSchedule().getItems().remove(item);
						}
					}
				});		
				
				
			}
			
			@Override
			public void itemClick(ItemMouseEvent e)
			{
				showForm(e.getItem());
				
			}
			
			@Override
			public void itemCreated(ItemEvent e) {
				
				calendar.getSelection().reset();
				calendar.getSelection().add(e.getItem().getStartTime(), 
						e.getItem().getEndTime());
				
				showForm(e.getItem());

			}			
		});
		

	}
	
	private static final long serialVersionUID = 1L;
	private Calendar calendar;
    
    
    
}