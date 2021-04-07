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
        	int orderNumber = orderNumberMenu.getValue();	
        	int index = storeOrders.findOrder(orderNumber);
        	
        	Order displayOrder = storeOrders.getStoreOrders().get(index);
        	
        	orderDisplay.setText(displayOrder.print());
        	
        	double total = displayOrder.getOrderTax() + displayOrder.getSubtotal();
        	totalDisplay.setText(money.format(total));
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

        
        storeOrders = MainMenuController.getStoreOrders();//this needs to be set to the actual storeOrders
        
        /*
        //TESTING STARTS HERE - DELETE BEFORE SUBMISSION.
        //Order 1
        Order order1 = new Order();
        Donut or1_donut1 = new Donut();
        Donut or1_donut2 = new Donut();
        or1_donut1.setFlavor(CAKE_DONUTS.APPLE_CRUMB.toString());
        or1_donut1.setType(DONUT_TYPES.CAKE_DONUTS);
        or1_donut1.setQuantity(3);
        or1_donut1.itemPrice();
        order1.add(or1_donut1);
        
        or1_donut2.setFlavor(CAKE_DONUTS.BLUEBERRY.toString());
        or1_donut2.setType(DONUT_TYPES.CAKE_DONUTS);
        or1_donut2.setQuantity(1);
        or1_donut2.itemPrice();
        order1.add(or1_donut2);
        
        storeOrders.add(order1);
        
        storeOrders.print();
        
        //Order 2
        Order order2 = new Order();
        Coffee coffeeO2 = new Coffee();
        Donut donutO2_1 = new Donut();
        Donut donutO2_2 = new Donut();
        
        coffeeO2.setSize(COFFEE_SIZE.VENTI);
        coffeeO2.setQuantity(5);
        coffeeO2.add(COFFEE_ADD_INS.CARAMEL);
        coffeeO2.add(COFFEE_ADD_INS.CREAM);
        coffeeO2.add(COFFEE_ADD_INS.MILK);
        coffeeO2.add(COFFEE_ADD_INS.SYRUP);
        coffeeO2.itemPrice();
        
        donutO2_1.setFlavor(YEAST_DONUTS.MARBLE_FROSTED.toString());
        donutO2_1.setQuantity(3);
        donutO2_1.setType(DONUT_TYPES.YEAST_DONUTS);
        donutO2_1.itemPrice();
        
        donutO2_2.setFlavor(CAKE_DONUTS.BOSTON_CREME.toString());
        donutO2_2.setQuantity(1);
        donutO2_2.setType(DONUT_TYPES.CAKE_DONUTS);
        donutO2_2.itemPrice();
        
        order2.add(donutO2_1);
        order2.add(donutO2_2);
        order2.add(coffeeO2);
        
        storeOrders.add(order2);
        
        //Order 3
        Order order3 = new Order();
        Coffee coffee = new Coffee();
        Donut donut = new Donut();
       
        
        COFFEE_SIZE size = COFFEE_SIZE.GRANDE;
        coffee.setSize(size);
        coffee.add(coffee);
        coffee.setQuantity(1);
        coffee.add(COFFEE_ADD_INS.CARAMEL);
        coffee.add(COFFEE_ADD_INS.CREAM);
        coffee.add(COFFEE_ADD_INS.MILK);
        coffee.add(COFFEE_ADD_INS.SYRUP);
        coffee.itemPrice();
        
        Coffee coffee1 = new Coffee();
        COFFEE_SIZE size1 = COFFEE_SIZE.TALL;
        coffee1.setSize(size1);
        coffee1.add(coffee1);
        coffee1.setQuantity(1);
        coffee1.itemPrice();
        //coffee1.addAddins(COFFEE_ADD_INS.CREAM);
        //coffee1.addAddins(COFFEE_ADD_INS.MILK);

        donut.setType(DONUT_TYPES.CAKE_DONUTS);
        donut.setFlavor("DEEZ NUTZZ");
        donut.setQuantity(1);
        donut.itemPrice();
        donut.add(donut);
       
        order3.add(coffee);
        order3.add(coffee1);
        order3.add(donut);
        
        storeOrders.add(order3);
        
        //storeOrders.remove(order1);
        //TESTING ENDS HERE - DELETE BEFORE SUBMISSION.*/
        
        for(Order order : storeOrders.getStoreOrders()) {
        	orderNumbers.add(order.getOrderNumber());
        }
        orderNumberMenu.getItems().addAll(orderNumbers);
             
        orderNumberMenu.getSelectionModel().selectFirst();
        setDisplay();
    }


}