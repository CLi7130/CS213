/**
 * Sample Skeleton for 'OrderCoffee.fxml' Controller Class
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
import javafx.scene.control.TextField;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderCoffeeController {
	private static final int MIN_NUMBER_OF_ADD_INS = 1;
	private static final int MAX_NUMBER_OF_ADD_INS = 5;
	private static final double ADD_IN_COST = 0.20;
	private static final double COFFEE_SIZE_INCREASE = 0.50;
	private static final double COFFEE_BASE_COST = 1.99;
	private static final int FAIL_CONDITION = -1;
	private static final DecimalFormat money = new DecimalFormat("#,##0.00");
	
	private int currNumAddIns = 0;
	private boolean[] testAddInBool = new boolean[5];
	private String[] testPossibleAddIns = {"Cream", "Syrup", "Milk", "Caramel", "Whipped Cream"};
	private ArrayList<String> testAddIns = new ArrayList<String>();
	
	
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
    
    @FXML // fx:id="addInQuantity"
    private ComboBox<Integer> addInQuantity; // Value injected by FXMLLoader

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

    @FXML
    void add(ActionEvent event) {
    	/*
    	 *1. get all fields, convert to string, add to order
    	 *	1a. coffee size
    	 *	1b. coffee add in selection
    	 *	1c. coffee add in quantity
    	 *	1d. total from total field
    	 *2. clear all values
    	 *	2a. set all checkboxes to null/unselected
    	 *	2b. set add in quantity to 1
    	 *	2c. set coffee size to short
    	 *		
    	 *		coffeeTotalField.clear();
    	 *-when exporting, make consistent with donut order - include price with addins/etc
    	 */
    	
    	Alerts.makeNewAlert("Coffee Order Confirmed", "Order Confirmation");
    	//send information to current order
    	resetFields();

    	
    }
    
    private void resetFields() {
    	
    	creamCheckBox.setSelected(false);
    	syrupCheckBox.setSelected(false);
    	milkCheckBox.setSelected(false);
    	caramelCheckBox.setSelected(false);
    	whippedCreamCheckBox.setSelected(false);
    	addInQuantity.getSelectionModel().selectFirst();
    	coffeeSizeMenu.getSelectionModel().selectFirst();
    	coffeeTotalField.setText("$" + money.format(COFFEE_BASE_COST));
    }
    
    /*
     * Reads add ins and sizes selected, then updates the coffeeTotalField in the GUI with the price
     * @params event event that causes the method on the GUI
     */
    @FXML
    void getTotal(ActionEvent event) {
    	
    	
    	double coffeeTotal = COFFEE_BASE_COST;
    	double addInCost = 0;
    	
    	
    	coffeeSizeMenu.setPromptText(coffeeSizeMenu.getValue().toString());
    	coffeeSizeMenu.setPromptText(coffeeSizeMenu.getValue().toString());
    	
    	coffeeTotal += COFFEE_SIZE_INCREASE * getSizeMod(coffeeSizeMenu.getValue().toString());
    	
    	addInCost += (ADD_IN_COST * (double) getTypesOfAddIns()) * (double) addInQuantity.getValue();
    	coffeeTotal += addInCost;
    
    	String totalString = "$" + money.format(coffeeTotal);
    	coffeeTotalField.setText(totalString);
    	
    	//set totalString to coffeeTotalField
    	//add to order, clear all checkboxes/reset size
    }
    
    private int getTypesOfAddIns() {
    	int numAddIns = 0;
    	
    	if(creamCheckBox.isSelected()) {
    		numAddIns++;
    	}
    	if(syrupCheckBox.isSelected()) {
    		numAddIns++;
    	}
    	if(milkCheckBox.isSelected()) {
    		numAddIns++;
    	}
    	if(caramelCheckBox.isSelected()) {
    		numAddIns++;
    	}
    	if(whippedCreamCheckBox.isSelected()) {
    		numAddIns++;
    	}
    	return numAddIns;
    }
    /*
     * Gets the size increase of a coffee from the base size of SHORT
     * @param size	string that has the selection on the coffeeSizeMenu
     */
    private static int getSizeMod(String size) {
    	int sizeMod = 0;
    	for(COFFEE_SIZE enumValue : COFFEE_SIZE.values()) {
    		
    		if(size.equals(enumValue.toString())){
    			return sizeMod;
    		}
    		sizeMod++;
    	}
    	sizeMod = FAIL_CONDITION; //returns -1 on error
    	return sizeMod;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert coffeeTotalField != null : "fx:id=\"coffeeTotalField\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert coffeeSizeMenu != null : "fx:id=\"coffeeSizeMenu\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert addInQuantity != null : "fx:id=\"addInQuantity\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert creamCheckBox != null : "fx:id=\"CREAM\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert syrupCheckBox != null : "fx:id=\"SYRUP\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert milkCheckBox != null : "fx:id=\"MILK\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert caramelCheckBox != null : "fx:id=\"CARAMEL\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert whippedCreamCheckBox != null : "fx:id=\"WHIPPED_CREAM\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        
        coffeeSizeMenu.getItems().setAll(COFFEE_SIZE.values());
        for(int i = MIN_NUMBER_OF_ADD_INS; i <= MAX_NUMBER_OF_ADD_INS; i++) {
        	addInQuantity.getItems().add(i);
        	//janky implementation, refactor later?
        }
        //addInQuantity.setPromptText("Quantity");
        //coffeeSizeMenu.setPromptText("Size");
        addInQuantity.getSelectionModel().selectFirst();
        coffeeSizeMenu.getSelectionModel().selectFirst();
        coffeeTotalField.setText("$" + money.format(COFFEE_BASE_COST));
        
        /*
        	code for observable value for listener - use for checkboxes when implementing final code for 
        	coffee objects.
         * creamCheckBox.selectedProperty().addListener(
        		(ObservableValue<? extends Boolean> obsVal, Boolean oldVal, Boolean newVal) -> {
        			
        			if(newVal) { //new value is TRUE
        				//currentCoffee.add(COFFEE_ADD_INS.CREAM);
        			}
        			else {//new value is false
        				//currentCoffee.remove(COFFEE_ADD_INS.CREAM);
        			}
        			currentTotal.setText(format.format(currentCoffee.itemPrice()));
        		});
        */
    }
}
