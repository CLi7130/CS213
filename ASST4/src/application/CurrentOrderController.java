package application;

/*
 * Controller class for the OrderCoffee.fxml GUI
 * @author Craig Li, Prerak Patel
 */

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    private ListView<?> orderDisplay; // Value injected by FXMLLoader
    
    Order currentOrder;
    StoreOrders storeOrders;
    DecimalFormat money = new DecimalFormat("$#,##0.00");
    
    /*
     * Sets the Given order to the one provided by the main menu
     * @params yourOrder	The order that represents the current order
     */
	public void setOrder(Order yourOrder) {
		// TODO Auto-generated method stub
		this.currentOrder = yourOrder;
	}
    /*
     * Sets the StoreOrders in this scene to the one referenced by the rest
     * of the program.
     * @param storeOrder	The storeOrder used by the project.
     */
	public void setStoreOrders(StoreOrders storeOrder) {
		// TODO Auto-generated method stub
		this.storeOrders = storeOrder;
	}
	
	/*
	 * Adds a current Order to the list of Store orders
	 * @param event 	The event on the GUI that causes this method.
	 */
    @FXML
    void addToStoreOrders(ActionEvent event) {
    	storeOrders.add(currentOrder);
    }
    
    /*
     * Removes an item from your order
     * @params event	An event on the GUI that launches this method.
     */
    @FXML
    void remove(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert placeCurrOrder != null : "fx:id=\"placeCurrOrder\" was not injected: check your FXML file 'currentOrder.fxml'.";
        assert currOrderSubtotal != null : "fx:id=\"currOrderSubtotal\" was not injected: check your FXML file 'currentOrder.fxml'.";
        assert currOrderSalesTax != null : "fx:id=\"currOrderSalesTax\" was not injected: check your FXML file 'currentOrder.fxml'.";
        assert currOrderTotal != null : "fx:id=\"currOrderTotal\" was not injected: check your FXML file 'currentOrder.fxml'.";
        assert removeOrderItem != null : "fx:id=\"removeOrderItem\" was not injected: check your FXML file 'currentOrder.fxml'.";
        assert orderDisplay != null : "fx:id=\"orderDisplay\" was not injected: check your FXML file 'currentOrder.fxml'.";
        
        //currOrderSubtotal.setText(money.format(currentOrder.getOrderTotal()));
        
        /*
         
        1. get current order instance and convert to string
       	2. get subtotal of current order
       	3. populate sales tax and total field
       		3a. update on
        */
    }





}
