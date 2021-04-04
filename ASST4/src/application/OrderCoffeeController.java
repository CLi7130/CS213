/**
 * Sample Skeleton for 'OrderCoffee.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import application.Enums.COFFEE_SIZE;
import application.Enums.COFFEE_ADD_INS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderCoffeeController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="coffeeTotal"
    private TextField coffeeTotal; // Value injected by FXMLLoader

    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML // fx:id="coffeeSizeMenu"
    private ComboBox<Enums.COFFEE_SIZE> coffeeSizeMenu; // Value injected by FXMLLoader

    @FXML // fx:id="CREAM"
    private CheckBox CREAM; // Value injected by FXMLLoader

    @FXML // fx:id="SYRUP"
    private CheckBox SYRUP; // Value injected by FXMLLoader

    @FXML // fx:id="MILK"
    private CheckBox MILK; // Value injected by FXMLLoader

    @FXML // fx:id="CARAMEL"
    private CheckBox CARAMEL; // Value injected by FXMLLoader

    @FXML // fx:id="WHIPPED_CREAM"
    private CheckBox WHIPPED_CREAM; // Value injected by FXMLLoader

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void getTotal(ActionEvent event) {
    	//add to order, clear all checkboxes/reset size
    }

    @FXML
    void updateCoffeeSize(ActionEvent event) {
    	coffeeSizeMenu.setPromptText(coffeeSizeMenu.getValue().toString());
    	//update total here based on coffee size - use coffeeSizeMenu.getValue()
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert coffeeTotal != null : "fx:id=\"coffeeTotal\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert coffeeSizeMenu != null : "fx:id=\"coffeeSizeMenu\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert CREAM != null : "fx:id=\"CREAM\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert SYRUP != null : "fx:id=\"SYRUP\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert MILK != null : "fx:id=\"MILK\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert CARAMEL != null : "fx:id=\"CARAMEL\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        assert WHIPPED_CREAM != null : "fx:id=\"WHIPPED_CREAM\" was not injected: check your FXML file 'OrderCoffee.fxml'.";
        
        coffeeSizeMenu.getItems().setAll(Enums.COFFEE_SIZE.values());
    }
}

/*
 * 
     //replace this list with enums for coffee size?
      ObservableList<String> coffeeSizes = FXCollections.observableArrayList("Short", "Tall", "Venti", "Grande");
      coffeeSizeMenu.setItems(coffeeSizes);

 */
