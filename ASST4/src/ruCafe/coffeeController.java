package ruCafe;
/**
 * Sample Skeleton for 'orderingCoffee.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class coffeeController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="coffeeTotal"
    private TextField coffeeTotal; // Value injected by FXMLLoader

    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML // fx:id="addInQuantity"
    private MenuItem addInQuantity; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert coffeeTotal != null : "fx:id=\"coffeeTotal\" was not injected: check your FXML file 'orderingCoffee.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'orderingCoffee.fxml'.";
        assert addInQuantity != null : "fx:id=\"addInQuantity\" was not injected: check your FXML file 'orderingCoffee.fxml'.";

    }
}
