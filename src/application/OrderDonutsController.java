/**
 * Sample Skeleton for 'OrderDonuts.fxml' Controller Class
 */

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

public class OrderDonutsController {
	private static final int MAX_DONUTS_PER_ORDER = 12;
	private static final int MIN_DONUTS_PER_ORDER = 1;
	private static final double YEAST_DONUT_COST = 1.39;
	private static final double CAKE_DONUT_COST = 1.59;
	private static final double DONUT_HOLE_COST = 0.33;
	private static final int ITEM_COST_LOCATION = 2;
	private double currentOrderTotal = 0;
	private static final DecimalFormat money = new DecimalFormat("#,##0.00");
	private ObservableList<String> currentOrder = FXCollections.observableArrayList();
	
	
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
    private ComboBox<Object> donutChoicesMenu; // Value injected by FXMLLoader

    @FXML // fx:id="donutQuantityMenu"
    private ChoiceBox<Integer> donutQuantityMenu; // Value injected by FXMLLoader

    @FXML // fx:id="donutTypeMenu"
    private ComboBox<DONUT_TYPES> donutTypeMenu; // Value injected by FXMLLoader

    @FXML // fx:id="donutOrderDisplay"
    private ListView<String> donutOrderDisplay; // Value injected by FXMLLoader
   
    @FXML // fx:id="previewImage"
    private ImageView previewImage; // Value injected by FXMLLoader
    
    /*
     * Resets Donut GUI to first choices of each menu, and updates the image to correspond.
     */
    private void resetFields() {
    	
    	donutChoicesMenu.getSelectionModel().selectFirst();
    	donutQuantityMenu.getSelectionModel().selectFirst();
    	donutTypeMenu.getSelectionModel().selectFirst();
    	updateImage();
    }

    /*
     * Adds donut(s) to the current order, which is displayed on the listview (donutOrderDisplay).
     * Added in a format of <DONUT_NAME> (<QUANTITY>) ($<PRICE>)
     * @params event	event in the GUI that adds the donut (clicking addDonut button)
     */
    @FXML
    void add(ActionEvent event) {
    	//check for null values in donut quantity and choice first
    	//update Price in listview
    	String currentDonut = "";
    	double currentDonutCost = 0;

    	
    	String donutType = donutTypeMenu.getValue().toString();
    	currentDonutCost = getDonutCost(donutType);
    	currentDonutCost = currentDonutCost * (double) donutQuantityMenu.getValue();
    	
    	
    	currentDonut += donutChoicesMenu.getValue().toString() + " (" 
				+ donutQuantityMenu.getValue().toString() + ") "
				+ "($" + money.format(currentDonutCost) + ")";
    	currentOrder.add(currentDonut);
    	currentOrderTotal += currentDonutCost;
    	donutTotal.setText("$" + money.format(currentOrderTotal));
    	resetFields();
    }
    /*
     * Removes donut(s) from the order display, and updates the current total to match.
     * @params event	event on the GUI that causes the method (clicking removeDonut Button)
     */
    @FXML
    void remove(ActionEvent event) {
    	//check for invalid quantities to remove, or item isn't in order
    	//update Price in listview
    	try {
    		String itemToBeRemoved = donutOrderDisplay.getSelectionModel().getSelectedItem().toString();
    		String[] splitStringArr = itemToBeRemoved.split("[( $)]+");
    		
    		double removedOrderCost = Double.parseDouble(splitStringArr[ITEM_COST_LOCATION]);
    		
        	currentOrder.remove(donutOrderDisplay.getSelectionModel().getSelectedItem());
        	currentOrderTotal -= removedOrderCost;
        	donutTotal.setText("$" + money.format(currentOrderTotal));
        	
        	resetFields();
    	}
    	catch(NullPointerException e){
    		if(currentOrder.size() == 0) {
    			Alerts.makeNewWarning("There are No Donuts In Your Order.", "Warning");
    		}
    		else {
    			Alerts.makeNewWarning("Please Select a Donut to Remove", "Warning");
    		}
    		
    	}
    }
    
    
    //FIXME not yet implemented
    
    /*
     * Adds all donuts on the current order display to the current store order.
     */
    @FXML
    void addToOrder() {
    	//clear donutOrderDisplay and add to current order.
    	//use currentOrder arraylist?
    	//make sure you clear list afterwards
		if(currentOrder.size() == 0) {
			Alerts.makeNewWarning("There are No Donuts In Your Order.", "Warning");
		}
		else {
			//actual export operation goes here
			Alerts.makeNewAlert("Donut Order Confirmed", "Order Confirmation");
		}
    	
    	
    }
    /*
     * Optional image switcher that updates an imageview on the GUI to match the current donut choice.
     */
    @FXML
    void updateImage() {
    	String fileLocation = "file:src\\Images\\orderDonuts\\donutChoices\\";
    	
    	fileLocation += donutChoicesMenu.getValue().toString();
    	fileLocation += ".jpeg";
    	try {
    		Image image = new Image(fileLocation);
    		previewImage.setImage(image);
    	}
    	catch(IllegalArgumentException e){
    		Alerts.makeNewWarning("File Not Found.", "Exception");
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    /*
     * Sets the donut choice given a user input.
     * @params event	event caused by selection of a donut by the user.
     */
    @FXML
    void setDonutChoice(ActionEvent event) {
    	String donutChoice = donutChoicesMenu.getValue().toString();
    	donutChoicesMenu.setPromptText(donutChoice);
    	//implement image switcher?
    	updateImage();
    }
    /*
     * Gets the base cost of a donut depending on the type of donut (cake/yeast/donut hole)
     * @params donutType String containing the donut type.
     */
    private double getDonutCost(String donutType) {
    	double cost = 0;
    	
    	if(donutType.equals("CAKE_DONUTS")) {
    		cost = CAKE_DONUT_COST;
    	}
    	else if(donutType.equals("DONUT_HOLES")){
    		cost = DONUT_HOLE_COST;
    	}
    	else if(donutType.equals("YEAST_DONUTS")){
    		cost = YEAST_DONUT_COST;
    	}
    	return cost;
    }
    /*
     * Changes the donut choices available based on the donut type (yeast donuts are different than cake donuts).
     * @params event	event caused by a selection of different donut type.
     */
    @FXML
    void changeDonutSelection(ActionEvent event) {
    	String donutType = donutTypeMenu.getValue().toString();
    	donutTypeMenu.setPromptText(donutType);
    	
    	donutChoicesMenu.setDisable(false);
    	donutQuantityMenu.setDisable(false);
        addDonut.setDisable(false);
        removeDonut.setDisable(false);
        addToOrder.setDisable(false);
    	
    	
    	if(donutType.equals("CAKE_DONUTS")) {
    		donutChoicesMenu.getItems().setAll(CAKE_DONUTS.values());
    	}
    	else if(donutType.equals("DONUT_HOLES")){
    		donutChoicesMenu.getItems().setAll(DONUT_HOLES.values());
    	}
    	else if(donutType.equals("YEAST_DONUTS")){
    		donutChoicesMenu.getItems().setAll(YEAST_DONUTS.values());
    	}
    	donutChoicesMenu.getSelectionModel().selectFirst();
    	donutQuantityMenu.getSelectionModel().selectFirst();
    	updateImage();
    }
    /*
     * Initializes the GUI of OrderDonuts.fxml
     */
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert addDonut != null : "fx:id=\"addDonut\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert removeDonut != null : "fx:id=\"removeDonut\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert donutTotal != null : "fx:id=\"donutTotal\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert addToOrder != null : "fx:id=\"addToOrder\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert donutChoicesMenu != null : "fx:id=\"donutChoicesMenu\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert donutQuantityMenu != null : "fx:id=\"donutQuantityMenu\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert donutTypeMenu != null : "fx:id=\"donutTypeMenu\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        assert donutOrderDisplay != null : "fx:id=\"donutOrderDisplay\" was not injected: check your FXML file 'OrderDonuts.fxml'.";
        
        donutChoicesMenu.setDisable(true);
        donutQuantityMenu.setDisable(true);
        addDonut.setDisable(true);
        removeDonut.setDisable(true);
        addToOrder.setDisable(true);
        
        donutTypeMenu.getItems().setAll(DONUT_TYPES.values());
        donutOrderDisplay.setItems(currentOrder);
        
        for(int i = MIN_DONUTS_PER_ORDER; i <= MAX_DONUTS_PER_ORDER; i++) {
        	donutQuantityMenu.getItems().add(i);
        	//janky implementation, refactor later?
        }
        
        
        
        
    }
}
