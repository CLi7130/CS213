package application;
/**
 * Sample Skeleton for 'storeOrders.fxml' Controller Class
 */

import java.net.URL;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private ListView<String> orderDisplay; // Value injected by FXMLLoader
    
    protected StoreOrders storeOrders;
    private ObservableList<String> formattedOrder = FXCollections.observableArrayList();
    /**
     * Allows main menu to transfer store order data to the store orders controller.
     * @params storeOrder	store orders generated.
     */
	public void setStoreOrders(StoreOrders storeOrder) {
		this.storeOrders = storeOrder;
	}
	
	/**
	 * Exports Orders to a text file
	 * @param event	Trigger that causes this method.
	 */
    @FXML
    void exportOrders(ActionEvent event) {

    }
    /**
     * Removes an Order from the storeOrders list.
     * @param event	Trigger that causes this method.
     */
    @FXML
    void removeOrder(ActionEvent event) {
    	
    }
    /**
     * Sets the order Display to the corresponding order Number
     */
    void setDisplay() {
    	int orderNumber = orderNumberMenu.getSelectionModel().getSelectedItem();
    	int index = storeOrders.findOrder(orderNumber);
    	
    	formattedOrder.add(storeOrders.getStoreOrders().get(index).print());
    	
    	
    }
    /**
     * Initializes the GUI
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert removeOrderButton != null : "fx:id=\"removeOrderButton\" was not injected: check your FXML file 'storeOrders.fxml'.";
        assert exportOrdersButton != null : "fx:id=\"exportOrdersButton\" was not injected: check your FXML file 'storeOrders.fxml'.";
        assert totalDisplay != null : "fx:id=\"totalDisplay\" was not injected: check your FXML file 'storeOrders.fxml'.";
        assert orderNumberMenu != null : "fx:id=\"orderNumberMenu\" was not injected: check your FXML file 'storeOrders.fxml'.";
        assert orderDisplay != null : "fx:id=\"orderDisplay\" was not injected: check your FXML file 'storeOrders.fxml'.";

        orderDisplay.setEditable(false);
        totalDisplay.setEditable(false);
        StoreOrders storeOrders = new StoreOrders();
        
        
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
        
        System.out.println(storeOrders.print());
        
        //TESTING ENDS HERE - DELETE BEFORE SUBMISSION.
        
        for(Order order : storeOrders.getStoreOrders()) {
        	int orderNumber = order.getOrderNumber();
        	orderNumberMenu.getItems().add(orderNumber);
        	
        	int index = storeOrders.findOrder(orderNumber);
        	formattedOrder.add(storeOrders.getStoreOrders().get(index).print());
        }
        orderNumberMenu.getSelectionModel().selectFirst();
        //add to formatted order
    }


}