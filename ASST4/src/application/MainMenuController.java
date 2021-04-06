

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
    
    protected static StoreOrders storeOrder;
    protected static Order yourOrder;
    
    /*
    Opens a new stage/scene based on the button pressed.
    @param event	 event of pressing a button, used to determine which window is opened.
    */
    @FXML
    void makeNewStage(ActionEvent event) {
    	try {
    		Button buttonPressed = (Button) event.getSource();
    		String fxID = buttonPressed.getId();
    		
			Stage newStage = new Stage();
			newStage = setStageParams(fxID, newStage);
			newStage.showAndWait();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    /*
    * Sets Stage window title and fxml file for switching from main menu.
    * @params fxID	String containing fxID of button pressed on main menu
    * 			stage	stage object to be modified.
    */
    private Stage setStageParams(String fxID, Stage stage){
    	String fileName = "";
    	String title = "";
    	try {
    	
    	
	    	if(fxID.equals("orderCoffeeButton")) {
	    		fileName += "OrderCoffee.fxml";
	    		title += "Order Coffee";	
	    	}
	    	else if(fxID.equals("orderDonutButton")) {
	    		fileName += "OrderDonuts.fxml";
	    		title += "Order Donuts";
	    	}
	    	else if(fxID.equals("currOrderButton")) {
	    		fileName += "CurrentOrder.fxml";
	    		title += "Current Order";
	    	}
	    	else if(fxID.equals("storeOrdersButton")) {
	    		fileName += "StoreOrders.fxml";
	    		title += "Store Orders";
	    	}

	    	//controllerTransfer(fileName);
    	
			Parent root = FXMLLoader.load(getClass().getResource(fileName));
			stage.setScene(new Scene(root, 600, 400));
			stage.setTitle(title);
			stage.initOwner(Main.getPrimaryStage());
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setResizable(false);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alerts.makeNewWarning("IOException Generated, Invalid Input.", "Warning");
		}
    	return stage;
    }
    /**
     * Function to transfer objects to another scene
     * @param fileName	string containing a fileName to determine which
     * 					controller to use.
     */
   /* private static void controllerTransfer(String fileName){

	    	try {
	        	
	        	FXMLLoader loader = new FXMLLoader();
	        	
	        	//String path = "../" + fileName;
	        	
	        	String filePath = "src/application/" + fileName;
	        	//System.out.println(path);
	        	
	        	
	    		loader.setLocation(Main.class.getResource(filePath));
	    		
				if(fileName.equals("OrderCoffee.fxml")) {
					OrderCoffeeController coffeeController = loader.getController();
					coffeeController.setOrder(yourOrder);
				}
				else if(fileName.equals("OrderDonuts.fxml")) {
					OrderDonutsController donutController = loader.getController();
					donutController.setOrder(yourOrder);
				}
				else if(fileName.equals("CurrentOrder.fxml")) {
					CurrentOrderController currOrderController = loader.getController();
					currOrderController.setOrder(yourOrder);
					currOrderController.setStoreOrders(storeOrder);
				}
				else if(fileName.equals("StoreOrders.fxml")) {
					StoreOrdersController storeOrderController = loader.getController();
					storeOrderController.setStoreOrders(storeOrder);
					
				}
	    			
    		} catch (NullPointerException e) {
    			e.printStackTrace();
    			Alerts.makeNewWarning("NullPointerException: Bad Address.", "Warning");
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
	    	
    }*/

    /*
    Initializes the GUI.
    */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert orderCoffeeButton != null : "fx:id=\"orderCoffeeButton\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert orderDonutButton != null : "fx:id=\"orderDonutButton\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert currOrderButton != null : "fx:id=\"currOrderButton\" was not injected: check your FXML file 'MainMenu.fxml'.";
        assert storeOrdersButton != null : "fx:id=\"storeOrdersButton\" was not injected: check your FXML file 'MainMenu.fxml'.";
        
        yourOrder = new Order();
        storeOrder = new StoreOrders();

    }
}
