package application;


import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
/**
 * Controller for the OrderCoffee.fxml GUI
 * @author Craig Li, Prerak Patel
 */
public class CurrentOrderController {
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="placeCurrOrder"
    private Button placeCurrOrder; // Value injected by FXMLLoader

    @FXML // fx:id="currOrderSubtotal"
    private TextField currOrderSubtotal; // Value injected by FXMLLoader

    @FXML // fx:id="currOrderSalesTax"
    private TextField currOrderSalesTax; // Value injected by FXMLLoader

    @FXML // fx:id="currOrderTotal"
    private TextField currOrderTotal; // Value injected by FXMLLoader

    @FXML // fx:id="removeOrderItem"
    private Button removeOrderItem; // Value injected by FXMLLoader

    @FXML // fx:id="orderDisplay"
    private ListView<String> orderDisplay; // Value injected by FXMLLoader
    private ObservableList<String> formattedOrder = FXCollections.observableArrayList();
    
    private Order currentOrder;
    private StoreOrders storeOrders;
    private DecimalFormat money = new DecimalFormat("$#,##0.00");
    /**
     * Clears all fields on GUI
     * 
     */
    private void resetFields() {
    	formattedOrder.clear();
    	currentOrder = new Order();
    	updateCosts();
    }
    /**
     * Sets the Given order to the one provided by the main menu
     * @param yourOrder	The order that represents the current order
     */
	public void setOrder(Order yourOrder) {
		// TODO Auto-generated method stub
		this.currentOrder = yourOrder;
	}
    /**
     * Sets the StoreOrders in this scene to the one referenced by the rest
     * of the program.
     * @param storeOrder	The storeOrder used by the project.
     */
	public void setStoreOrders(StoreOrders storeOrder) {
		// TODO Auto-generated method stub
		this.storeOrders = storeOrder;
	}
	
	/**
	 * Adds a current Order to the list of Store orders
	 * @param event 	The event on the GUI that causes this method.
	 */
    @FXML
    void addToStoreOrders(ActionEvent event) {
    	storeOrders.add(currentOrder);
    	currentOrder = new Order();
    	MainMenuController.setCurrentOrder(currentOrder);
    	
    	//FIXME
    	
    	Alerts.makeNewAlert("Your Order has been added!", "Confirmation");
    	resetFields();
    }
    
    /**
     * Removes an item from your order
     * @param event	An event on the GUI that launches this method.
     */
    @FXML
    void remove(ActionEvent event) {
    	try {
    		Object obj = orderDisplay.getSelectionModel().getSelectedItem();
    		String itemToRemove = orderDisplay.getSelectionModel().getSelectedItem().toString();
    	
        	for(int i = 0; i < currentOrder.getOrderList().size(); i++) {
        		
        		if(itemToRemove.equals(currentOrder.getOrderList().get(i).print())){
        			currentOrder.getOrderList().remove(i);
        			formattedOrder.remove(obj);
        			updateCosts();
        			break;
        		}
        	}
        	checkIfEmpty();
    	}
    	catch(NullPointerException e){
    		if(formattedOrder.size() != 0) {
    			Alerts.makeNewWarning("Please Select an item to Remove", "Warning");
    			
    		}
    		else {
    			checkIfEmpty();
    		}
    	}
    }
    
    /**
     * Checks to see if the order is empty, disables buttons if order is empty.
     * 
     */
    private void checkIfEmpty() {
    	if(formattedOrder.isEmpty()) {
    		removeOrderItem.setDisable(true);
    		placeCurrOrder.setDisable(true);
    	}

    }
    
    /**
     * 
     * Updates the Current Order GUI with the current subtotal, tax and total of the 
     * current order.
     */
    private void updateCosts() {
    	
        double subTotal = currentOrder.updateOrderTotal();
        double orderTax = currentOrder.getOrderTax();
        double orderTotal = subTotal + orderTax;
        
        currOrderSubtotal.setText(money.format(subTotal));
        currOrderSalesTax.setText(money.format(orderTax));
        currOrderTotal.setText(money.format(orderTotal));
        
    }
    
    /**
     * Initializes the GUI of OrderDonuts.fxml
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert placeCurrOrder != null : "fx:id=\"placeCurrOrder\" was not injected: check your FXML file 'currentOrder.fxml'.";
        assert currOrderSubtotal != null : "fx:id=\"currOrderSubtotal\" was not injected: check your FXML file 'currentOrder.fxml'.";
        assert currOrderSalesTax != null : "fx:id=\"currOrderSalesTax\" was not injected: check your FXML file 'currentOrder.fxml'.";
        assert currOrderTotal != null : "fx:id=\"currOrderTotal\" was not injected: check your FXML file 'currentOrder.fxml'.";
        assert removeOrderItem != null : "fx:id=\"removeOrderItem\" was not injected: check your FXML file 'currentOrder.fxml'.";
        assert orderDisplay != null : "fx:id=\"orderDisplay\" was not injected: check your FXML file 'currentOrder.fxml'.";
        
        currentOrder = MainMenuController.getCurrentOrder();
        storeOrders = MainMenuController.getStoreOrders();
        
        orderDisplay.setItems(formattedOrder);
        updateCosts();
        
        for(MenuItem item : currentOrder.getOrderList()) {
            formattedOrder.add(item.print());
        }
        checkIfEmpty();
        
    }

}
