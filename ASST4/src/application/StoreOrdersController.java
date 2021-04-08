package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import application.Enums.CAKE_DONUTS;
import application.Enums.COFFEE_ADD_INS;
import application.Enums.COFFEE_SIZE;
import application.Enums.DONUT_TYPES;
import application.Enums.YEAST_DONUTS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
/**
 * Controller for the StoreOrders.fxml GUI
 * @author Craig Li, Prerak Patel
 *
 */
public class StoreOrdersController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="removeOrderButton"
    private Button removeOrderButton; // Value injected by FXMLLoader

    @FXML // fx:id="exportOrdersButton"
    private Button exportOrdersButton; // Value injected by FXMLLoader

    @FXML // fx:id="totalDisplay"
    private TextField totalDisplay; // Value injected by FXMLLoader

    @FXML // fx:id="orderNumberMenu"
    private ComboBox<Integer> orderNumberMenu; // Value injected by FXMLLoader
    
    @FXML // fx:id="orderDisplay"
    private TextArea orderDisplay; // Value injected by FXMLLoader
    
    private StoreOrders storeOrders;
    private ObservableList<Integer> orderNumbers = FXCollections.observableArrayList();
    private DecimalFormat money = new DecimalFormat("$#,##0.00");
    
	
	/**
	 * Exports Orders to a text file
	 * @param event	Trigger that causes this method.
	 */
    @FXML
    void exportOrders(ActionEvent event) {
    	FileChooser chooser = new FileChooser();
        
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
        try {
            PrintWriter pw = new PrintWriter(targetFile);
            String text = "";
            text += storeOrders.print();
            pw.print(text);
            pw.close();
            Alerts.makeNewAlert("Orders Exported to " + targetFile, "Message");
            
        } catch (FileNotFoundException e) {
        	Alerts.makeNewWarning("File Not Found.", "Warning");
            
        } catch (NullPointerException e) {
        	Alerts.makeNewWarning("Please Choose a File to Export To.", "Warning");
        }

    }
    /**
     * Removes an Order from the storeOrders list.
     * @param event	Trigger that causes this method.
     */
    @FXML
    void removeOrder(ActionEvent event) {
    	 int orderNumber = orderNumberMenu.getValue();
    	 int index = storeOrders.findOrder(orderNumber);
    	 
    	 Order removeThisOrder = storeOrders.getStoreOrders().get(index);
    	 
    	 storeOrders.remove(removeThisOrder);
    	 
    	 for(int i = 0; i < orderNumbers.size(); i++) {
    		 if(orderNumber == orderNumbers.get(i)) {
    			 orderNumbers.remove(i);
    			 orderNumberMenu.getItems().remove(i);
    			 
    			 orderNumberMenu.getSelectionModel().selectFirst();
    		 }
    	 }
    	 
    	 
    	 Alerts.makeNewAlert("Order Removed From Store Orders.", "Confirmation");
    	 resetFields();
    	 
    }
    /**
     * Resets GUI fields to default values/next order
     */
    private void resetFields() {
    	if(orderNumbers.isEmpty()) {
    		exportOrdersButton.setDisable(true);
    		removeOrderButton.setDisable(true);
    		orderNumberMenu.setDisable(true);
    		totalDisplay.setText(money.format(0));
    		orderDisplay.clear();
    		orderDisplay.setPromptText("There Are Currently No Store Orders.");
    	}
    	else {
    		orderNumberMenu.getSelectionModel().selectFirst();
            setDisplay();
    	}
    }
    /**
     * Sets the order Display to the corresponding order Number.
     * Used to continuously update the order based on user action in the GUI.
     */
    @FXML
    void updateDisplay(ActionEvent event) {
    	setDisplay();
    	
    }
    /**
     * sets the Currently selected order display the the corresponding order
     */
    private void setDisplay() {
    	if(orderNumbers.isEmpty()) {
    		resetFields();
    	}
    	else {
    		try {
            	int orderNumber = orderNumberMenu.getValue();//HOW IS THIS NULL WTF	
            	int index = storeOrders.findOrder(orderNumber);
            	
            	Order displayOrder = storeOrders.getStoreOrders().get(index);
            	
            	orderDisplay.setText(displayOrder.print());
            	
            	double total = displayOrder.getOrderTax() + displayOrder.getSubtotal();
            	totalDisplay.setText(money.format(total));	
    		}
    		catch (NullPointerException e) {
    			/*
    			 * Somehow initialization of orderNumber in this method generates an NPE
    			 * But then the rest of the method generates correctly, and to generate correctly
    			 * it needs the correct orderNumber value. So how is it throwing an NPE? 
    			 * The value of orderNumberMenu needs to be null, however, this NPE is generated
    			 * when the first available order number is removed (first in list from combobox)
    			 * When remove() is called, it removes the specified 
    			 * order number from the orderNumbers List,
    			 * and the orderNumber from the orderNumberMenu, then calls resetFields(),
    			 * WHICH SELECTS THE FIRST ITEM FROM THE COMBOBOX, SO IT'S NOT NULL
    			 * THERE'S A VALUE IN THE COMBOBOX, BUT IT SAYS IT'S NULL AAAAAHHHHHH
    			 * 
    			 * try/catch fixes this, but it's bad coding practice as opposed to finding the
    			 * actual solution.
    			 */
    		}

    	}

    }
    /**
     * Initializes the GUI
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert removeOrderButton != null : "fx:id=\"removeOrderButton\" was not injected: check your FXML file 'StoreOrders.fxml'.";
        assert exportOrdersButton != null : "fx:id=\"exportOrdersButton\" was not injected: check your FXML file 'StoreOrders.fxml'.";
        assert totalDisplay != null : "fx:id=\"totalDisplay\" was not injected: check your FXML file 'StoreOrders.fxml'.";
        assert orderNumberMenu != null : "fx:id=\"orderNumberMenu\" was not injected: check your FXML file 'StoreOrders.fxml'.";
        assert orderDisplay != null : "fx:id=\"orderDisplay\" was not injected: check your FXML file 'StoreOrders.fxml'.";

        storeOrders = MainMenuController.getStoreOrders();

        for(Order order : storeOrders.getStoreOrders()) {
        	orderNumbers.add(order.getOrderNumber());
        }
        orderNumberMenu.getItems().addAll(orderNumbers);
             
        orderNumberMenu.getSelectionModel().selectFirst();
        setDisplay();
    }


}