

package application;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.Enums.CAKE_DONUTS;
import application.Enums.COFFEE_SIZE;
import application.Enums.YEAST_DONUTS;
import application.Enums.DONUT_HOLES;
import application.Enums.DONUT_TYPES;
/**
 * Donut controller for OrderDonuts.fxml GUI
 * @author Craig Li, Prerak Patel
 */
public class OrderDonutsController {
	private static final int MAX_DONUTS_PER_ORDER = 12;
	private static final int MIN_DONUTS_PER_ORDER = 1;

	private double currentOrderTotal = 0;
	private static final DecimalFormat money = new DecimalFormat("$#,##0.00");
	private ObservableList<String> formattedOrder = FXCollections.observableArrayList();
	private ArrayList<Donut> donutControllerOrder = new ArrayList<Donut>();
	private Order currentOrder;
	private Donut currentDonut;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="addDonut"
    private Button addDonut; // Value injected by FXMLLoader

    @FXML // fx:id="removeDonut"
    private Button removeDonut; // Value injected by FXMLLoader

    @FXML // fx:id="donutTotal"
    private TextField donutTotal; // Value injected by FXMLLoader

    @FXML // fx:id="addToOrder"
    private Button addToOrder; // Value injected by FXMLLoader

    @FXML // fx:id="donutChoicesMenu"
    private ComboBox<Object> donutFlavorsMenu; // Value injected by FXMLLoader

    @FXML // fx:id="donutQuantityMenu"
    private ChoiceBox<Integer> donutQuantityMenu; // Value injected by FXMLLoader

    @FXML // fx:id="donutTypeMenu"
    private ComboBox<DONUT_TYPES> donutTypeMenu; // Value injected by FXMLLoader

    @FXML // fx:id="donutOrderDisplay"
    private ListView<String> donutOrderDisplay; // Value injected by FXMLLoader
   
    @FXML // fx:id="previewImage"
    private ImageView previewImage; // Value injected by FXMLLoader

    /**
     * Resets Donut GUI default/blank values, and updates the image to correspond.
     */
    private void resetFields() {
    	
    	
    	donutFlavorsMenu.getSelectionModel().selectFirst();
    	donutQuantityMenu.getSelectionModel().selectFirst();
    	donutTypeMenu.getSelectionModel().selectFirst();
    	
    	currentDonut = new Donut();
    	currentDonut.setType(donutTypeMenu.getValue());
    	currentDonut.setFlavor(donutFlavorsMenu.getValue().toString());
    	currentDonut.setQuantity(donutQuantityMenu.getValue());
    	
    	donutTotal.setText(money.format(currentOrderTotal));
    	checkIfEmpty();
    	updateImage();
    }

    /**
     * Adds donut(s) to the current order, which is displayed on the listview (donutOrderDisplay).
     * Added in a format of <DONUT_NAME> (<QUANTITY>) ($<PRICE>)
     * @params event	event in the GUI that adds the donut (clicking addDonut button)
     */
    @FXML
    void add(ActionEvent event) {
    	//check for null values in donut quantity and choice first
    	//update Price in listview

    	currentDonut.setType(donutTypeMenu.getValue());
    	currentDonut.setFlavor(donutFlavorsMenu.getValue().toString());
    	currentDonut.setQuantity(donutQuantityMenu.getValue());
    	currentDonut.itemPrice();
    	currentOrderTotal += currentDonut.getPrice();
    	donutTotal.setText(money.format(currentOrderTotal));
  
    	formattedOrder.add(currentDonut.print());
    	
    	donutControllerOrder.add(currentDonut);//change this to actual order
    	
    	checkIfEmpty();
    	
    	currentDonut = null;
    	resetFields();

    }
    /**
     * Removes donut(s) from the order display, and updates the current total to match.
     * @params event	event on the GUI that causes the method (clicking removeDonut Button)
     */
    @FXML
    void remove(ActionEvent event) {
    	
    	try {
    		Object donut = donutOrderDisplay.getSelectionModel().getSelectedItem();
    		String itemToRemove = donutOrderDisplay.getSelectionModel().getSelectedItem().toString();
    	
        	for(int i = 0; i < donutControllerOrder.size(); i++) {
        		
        		if(itemToRemove.equals(donutControllerOrder.get(i).print())){
        			double removedOrderCost = donutControllerOrder.get(i).getPrice();
        			donutControllerOrder.remove(i);
        			formattedOrder.remove(donut);
        			currentOrderTotal -= removedOrderCost;
        			
        			donutTotal.setText(money.format(currentOrderTotal));
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
     * Adds all donuts on the current order display to the current store order.
     */
    @FXML
    void addToOrder() {
    	
		if(donutControllerOrder.size() == 0) {
			Alerts.makeNewWarning("There are No Donuts In Your Order.", "Warning");
		}
		else {
			
	    	for(Donut donut : donutControllerOrder) {
	    		donut.itemPrice();
	    		currentOrder.add(donut);
	    	}
	    	
	    	Alerts.makeNewAlert("Donut Order Confirmed", "Order Confirmation");
		}
		formattedOrder.removeAll(formattedOrder);
		donutControllerOrder.removeAll(donutControllerOrder);
		
		currentOrderTotal = 0;
    	resetFields();
    	
    }
    /**
     * Optional image switcher that updates an imageview on the GUI to match the current donut choice.
     */
    @FXML
    void updateImage() {
    	String fileLocation = "file:src\\Images\\orderDonuts\\donutChoices\\";
    	
    	fileLocation += donutFlavorsMenu.getValue().toString();
    	fileLocation += ".jpeg";
    	try {
    		Image image = new Image(fileLocation);
    		previewImage.setImage(image);
    	}
    	catch(IllegalArgumentException e){
    		Alerts.makeNewWarning("File Not Found.", "Exception");
    	}
    	catch(Exception e){
    		Alerts.makeNewWarning("Error", "Error");
    	}
    }
    /**
     * Sets the donut choice given a user input.
     * @params event	event caused by selection of a donut by the user.
     */
    @FXML
    void setDonutFlavor(ActionEvent event) {
    	String donutChoice = donutFlavorsMenu.getValue().toString();
    	donutFlavorsMenu.setPromptText(donutChoice);
    	//implement image switcher?
    	updateImage();
    }
    /**
     * Checks if the order is empty, disables add to order and remove buttons if empty.
     * 
     */
    private void checkIfEmpty() {
    	if(formattedOrder.isEmpty()) {
    		addToOrder.setDisable(true);
    		removeDonut.setDisable(true);
    	}
    	else {
    		addToOrder.setDisable(false);
    		removeDonut.setDisable(false);
    	}
    }

    /**
     * Changes the donut choices available based on the donut type 
     * (yeast donuts are different than cake donuts).
     * @params event	event caused by a selection of different donut type.
     */
    @FXML
    void changeDonutSelection(ActionEvent event) {
    	String donutType = donutTypeMenu.getValue().toString();
    	donutTypeMenu.setPromptText(donutType);
    	
    	donutFlavorsMenu.setDisable(false);
    	donutQuantityMenu.setDisable(false);
        addDonut.setDisable(false);
        
        checkIfEmpty();
        
    	if(donutType.equals("CAKE_DONUTS")) {
    		donutFlavorsMenu.getItems().setAll(CAKE_DONUTS.values());
    	}
    	else if(donutType.equals("DONUT_HOLES")){
    		donutFlavorsMenu.getItems().setAll(DONUT_HOLES.values());
    	}
    	else if(donutType.equals("YEAST_DONUTS")){
    		donutFlavorsMenu.getItems().setAll(YEAST_DONUTS.values());
    	}
    	donutFlavorsMenu.getSelectionModel().selectFirst();
    	donutQuantityMenu.getSelectionModel().selectFirst();
    	
    	updateImage();
    }
    /**
     * Initializes the GUI of OrderDonuts.fxml
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert addDonut != null : "fx:id=\"addDonut\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert removeDonut != null : "fx:id=\"removeDonut\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert donutTotal != null : "fx:id=\"donutTotal\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert addToOrder != null : "fx:id=\"addToOrder\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert donutFlavorsMenu != null : "fx:id=\"donutFlavorsMenu\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert donutQuantityMenu != null : "fx:id=\"donutQuantityMenu\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert donutTypeMenu != null : "fx:id=\"donutTypeMenu\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert donutOrderDisplay != null : "fx:id=\"donutOrderDisplay\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        
        currentDonut = new Donut();
	        
        currentOrder = MainMenuController.getCurrentOrder();
        
        donutFlavorsMenu.setDisable(true);
        donutQuantityMenu.setDisable(true);
        addDonut.setDisable(true);
        removeDonut.setDisable(true);
        addToOrder.setDisable(true);
        
        donutTypeMenu.getItems().setAll(DONUT_TYPES.values());
        donutOrderDisplay.setItems(formattedOrder);
        
        for(int i = MIN_DONUTS_PER_ORDER; i <= MAX_DONUTS_PER_ORDER; i++) {
        	donutQuantityMenu.getItems().add(i);
        }

    }


}