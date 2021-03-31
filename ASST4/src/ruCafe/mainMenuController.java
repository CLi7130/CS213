package ruCafe;
/**
 * Sample Skeleton for 'mainMenu.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class mainMenuController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="currOrder"
    private Button currOrder; // Value injected by FXMLLoader

    @FXML // fx:id="orderCoffee"
    private Button orderCoffee; // Value injected by FXMLLoader

    @FXML // fx:id="orderDonuts"
    private Button orderDonuts; // Value injected by FXMLLoader

    @FXML // fx:id="storeOrders"
    private Button storeOrders; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert currOrder != null : "fx:id=\"currOrder\" was not injected: check your FXML file 'mainMenu.fxml'.";
        assert orderCoffee != null : "fx:id=\"orderCoffee\" was not injected: check your FXML file 'mainMenu.fxml'.";
        assert orderDonuts != null : "fx:id=\"orderDonuts\" was not injected: check your FXML file 'mainMenu.fxml'.";
        assert storeOrders != null : "fx:id=\"storeOrders\" was not injected: check your FXML file 'mainMenu.fxml'.";

    }
}
