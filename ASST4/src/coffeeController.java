package src;
/**
 * Sample Skeleton for 'orderingCoffee.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class PleaseProvideControllerClassName {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="coffeeTotal"
    private TextField coffeeTotal; // Value injected by FXMLLoader

    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML // fx:id="coffeeSizeMenu"
    private ComboBox<?> coffeeSizeMenu; // Value injected by FXMLLoader

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

    @FXML // fx:id="addInQuantity"
    private MenuItem addInQuantity; // Value injected by FXMLLoader

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void getTotal(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert coffeeTotal != null : "fx:id=\"coffeeTotal\" was not injected: check your FXML file 'orderingCoffee.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'orderingCoffee.fxml'.";
        assert coffeeSizeMenu != null : "fx:id=\"coffeeSizeMenu\" was not injected: check your FXML file 'orderingCoffee.fxml'.";
        assert creamCheckBox != null : "fx:id=\"creamCheckBox\" was not injected: check your FXML file 'orderingCoffee.fxml'.";
        assert syrupCheckBox != null : "fx:id=\"syrupCheckBox\" was not injected: check your FXML file 'orderingCoffee.fxml'.";
        assert milkCheckBox != null : "fx:id=\"milkCheckBox\" was not injected: check your FXML file 'orderingCoffee.fxml'.";
        assert caramelCheckBox != null : "fx:id=\"caramelCheckBox\" was not injected: check your FXML file 'orderingCoffee.fxml'.";
        assert whippedCreamCheckBox != null : "fx:id=\"whippedCreamCheckBox\" was not injected: check your FXML file 'orderingCoffee.fxml'.";
        assert addInQuantity != null : "fx:id=\"addInQuantity\" was not injected: check your FXML file 'orderingCoffee.fxml'.";
        
        coffeeSizeMenu.getItems().addAll("Short", "Tall", "Grande", "Venti"); //initialize size choices
        coffeeTotal.setEditable(false); //disable editing in text field?
    }
}