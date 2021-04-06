/**
 * Controller for the OrderCoffee.fxml GUI
 * @author Craig Li, Prerak Patel
 */

package application;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Enums.COFFEE_SIZE;
import application.Enums.COFFEE_ADD_INS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderCoffeeController {
	private static final int MIN_NUMBER_OF_COFFEES = 1;
	private static final int MAX_NUMBER_OF_COFFEES = 10;

	private static final DecimalFormat money = new DecimalFormat("$#,##0.00");
	private Coffee currentCoffee;
	protected Order currentOrder;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="coffeeTotalField"
    private TextField coffeeTotalField; // Value injected by FXMLLoader

    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML // fx:id="coffeeSizeMenu"
    private ComboBox<COFFEE_SIZE> coffeeSizeMenu; // Value injected by FXMLLoader

    @FXML // fx:id="coffeeQuantityMenu"
    private ComboBox<Integer> coffeeQuantityMenu; // Value injected by FXMLLoader

    @FXML // fx:id="creamCheckBox"
    private CheckBox creamCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="syrupCheckBox"
    private CheckBox syrupCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="milkCheckBox"
    private CheckBox milkCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="caramelCheckBox"
    private CheckBox caramelCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="whippedCreamCheckBox"
    private CheckBox whippedCreamCheckBox; // Value injected by FXMLLoader
    
    /**
     * Method that allows the main menu to set its order to the coffee Controller.
     * Transfer of data between controllers.
     * @params yourOrder	default order initialized in main menu.
     */
	public void setOrder(Order yourOrder) {
		this.currentOrder = yourOrder;
	}
    /**
     * Adds a coffee item to the current order.
     * @param event		Trigger on the GUI for this method.
     */
    @FXML
    void addToOrder(ActionEvent event) {
    	
    	//currentOrder.add(currentCoffee);
    	//currentOrder.setOrderTotal(currentOrder.getOrderTotal() + currentCoffee.itemPrice());
    	
    	Alerts.makeNewAlert("Coffee Order Confirmed" + '\n' 
    						+ "Your Coffee Order: " + '\n' 
    						+ currentCoffee.print(), 
    						"Order Confirmation");
    	resetFields();
    	 
    }
    /**
     * Resets all GUI fields to a default/blank state
     */
    private void resetFields() {
    	
    	currentCoffee = new Coffee();
    	
    	creamCheckBox.setSelected(false);
    	syrupCheckBox.setSelected(false);
    	milkCheckBox.setSelected(false);
    	caramelCheckBox.setSelected(false);
    	whippedCreamCheckBox.setSelected(false);
    	coffeeQuantityMenu.getSelectionModel().selectFirst();
    	coffeeSizeMenu.getSelectionModel().selectFirst();
    	coffeeTotalField.clear();
    }
    
    /**
     * Reads add ins and sizes selected, then updates the coffeeTotalField in the GUI with the price
     * @params event event that causes the method on the GUI
     */
    @FXML
    void getTotal(ActionEvent event) {
    	
    	currentCoffee.setSize(coffeeSizeMenu.getValue());
    	currentCoffee.setQuantity(coffeeQuantityMenu.getValue());
    	
    	currentCoffee.itemPrice();
    	
    	String totalString = money.format(currentCoffee.getPrice());
    	coffeeTotalField.setText(totalString); 	 
    }
    /**
     * Initializes the GUI, also contains setup for listeners on checkboxes.
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert coffeeTotalField != null : "fx:id=\"coffeeTotalField\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert coffeeSizeMenu != null : "fx:id=\"coffeeSizeMenu\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert coffeeQuantityMenu != null : "fx:id=\"coffeeQuantityMenu\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert creamCheckBox != null : "fx:id=\"creamCheckBox\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert syrupCheckBox != null : "fx:id=\"syrupCheckBox\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert milkCheckBox != null : "fx:id=\"milkCheckBox\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert caramelCheckBox != null : "fx:id=\"caramelCheckBox\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert whippedCreamCheckBox != null : "fx:id=\"whippedCreamCheckBox\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        currentCoffee = new Coffee();
        
        coffeeSizeMenu.getItems().setAll(COFFEE_SIZE.values());
        for(int i = MIN_NUMBER_OF_COFFEES; i <= MAX_NUMBER_OF_COFFEES; i++) {
        	coffeeQuantityMenu.getItems().add(i);
        	//janky implementation, refactor later?
        }
        coffeeQuantityMenu.getSelectionModel().selectFirst();
        coffeeSizeMenu.getSelectionModel().selectFirst();
        coffeeTotalField.setText("$0.00");
        
        creamCheckBox.selectedProperty().addListener(
        		(ObservableValue<? extends Boolean> observed, Boolean oldVal, Boolean newVal) -> {
        			
        			if(newVal) {
        				//new value is true
        				currentCoffee.add(COFFEE_ADD_INS.CREAM);
        				
        			}
        			else {//new value is false
        				currentCoffee.remove(COFFEE_ADD_INS.CREAM);
        				
        			}
        		});
        syrupCheckBox.selectedProperty().addListener(
        		(ObservableValue<? extends Boolean> observed, Boolean oldVal, Boolean newVal) -> {
        			
        			if(newVal) {
        				//new value is true
        				currentCoffee.add(COFFEE_ADD_INS.SYRUP);
        			}
        			else {//new value is false
        				currentCoffee.remove(COFFEE_ADD_INS.SYRUP);
        				
        			}
        		});
        milkCheckBox.selectedProperty().addListener(
        		(ObservableValue<? extends Boolean> observed, Boolean oldVal, Boolean newVal) -> {
        			
        			if(newVal) {
        				//new value is true
        				currentCoffee.add(COFFEE_ADD_INS.MILK);
        				
        			}
        			else {//new value is false
        				currentCoffee.remove(COFFEE_ADD_INS.MILK);
        				
        			}
     
        		});
        caramelCheckBox.selectedProperty().addListener(
        		(ObservableValue<? extends Boolean> observed, Boolean oldVal, Boolean newVal) -> {
        			
        			if(newVal) {
        				//new value is true
        				currentCoffee.add(COFFEE_ADD_INS.CARAMEL);
        				
        			}
        			else {//new value is false
        				currentCoffee.remove(COFFEE_ADD_INS.CARAMEL);
        				
        			}
        		});
        whippedCreamCheckBox.selectedProperty().addListener(
        		(ObservableValue<? extends Boolean> observed, Boolean oldVal, Boolean newVal) -> {
        			
        			if(newVal) {
        				//new value is true
        				currentCoffee.add(COFFEE_ADD_INS.WHIPPED_CREAM);
        				
        			}
        			else {//new value is false
        				currentCoffee.remove(COFFEE_ADD_INS.WHIPPED_CREAM);
        				
        			}
        		});
		
        
        

    }

}
