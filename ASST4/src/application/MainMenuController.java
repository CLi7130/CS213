

/**
 * Main menu controller for Project, allows opening different scenes based on activity.
 * @author Craig Li, Prerak Patel
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="orderCoffeeButton"
    private Button orderCoffeeButton; // Value injected by FXMLLoader

    @FXML // fx:id="orderDonutButton"
    private Button orderDonutButton; // Value injected by FXMLLoader

    @FXML // fx:id="currOrderButton"
    private Button currOrderButton; // Value injected by FXMLLoader

    @FXML // fx:id="storeOrdersButton"
    private Button storeOrdersButton; // Value injected by FXMLLoader
    
    /*
    Opens a new stage/scene based on the button pressed.
    @param event	 event of pressing a button, used to determine which window is opened.
    */
    @FXML
    void makeNewStage(ActionEvent event) {
    	try {
    		Button buttonPressed = (Button) event.getSource();
    		String fxID = buttonPressed.getId();
    		
			Parent root = FXMLLoader.load(getClass().getResource(getFileName(fxID)));
			Stage stage = new Stage();
			stage.setTitle(getWindowTitle(fxID));
			stage.setScene(new Scene(root, 600, 400));
			stage.initOwner(Main.getPrimaryStage());
			stage.initModality(Modality.WINDOW_MODAL);
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    /*
    Returns the correct fxml file to be opened based on the fxID of the button pressed.
    @param fxID	name of the button pressed on the main menu.
    */
    private static String getFileName(String fxID) {
    	String fileName = "";
    	
    	if(fxID.equals("orderCoffeeButton")) {
    		fileName += "OrderCoffee.fxml";
    	}
    	else if(fxID.equals("orderDonutButton")) {
    		fileName += "OrderDonuts.fxml";
    	}
    	else if(fxID.equals("currOrderButton")) {
    		fileName += "CurrentOrder.fxml";
    	}
    	else if(fxID.equals("storeOrdersButton")) {
    		fileName += "StoreOrders.fxml";
    	}
    	return fileName;
    }
    /*
   	Returns the correct window title based on the fxID of the button pressed.
    @param fxID	name of the button pressed on the main menu.
    */
    private static String getWindowTitle(String fxID) {
    	String title = "";
    	
    	if(fxID.equals("orderCoffeeButton")) {
    		title += "Order Coffee";
    	}
    	else if(fxID.equals("orderDonutButton")) {
    		title += "Order Donuts";
    	}
    	else if(fxID.equals("currOrderButton")) {
    		title += "Current Order";
    	}
    	else if(fxID.equals("storeOrdersButton")) {
    		title += "Store Orders";
    	}
    	return title;
    }

    /*
    Initializes the GUI.
    */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert orderCoffeeButton != null : "fx:id=\"orderCoffeeButton\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert orderDonutButton != null : "fx:id=\"orderDonutButton\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert currOrderButton != null : "fx:id=\"currOrderButton\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert storeOrdersButton != null : "fx:id=\"storeOrdersButton\" was not injected: check your FXML file 'MainMenu.fxml'.";

    }
}
