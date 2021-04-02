package src;

/**

 * Sample Skeleton for 'mainMenu.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PleaseProvideControllerClassName {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="orderCoffeeButton"
    private Button orderCoffeeButton; // Value injected by FXMLLoader

    @FXML // fx:id="orderDonutButton"
    private Button orderDonutButton; // Value injected by FXMLLoader

    @FXML // fx:id="currOrderButton"
    private Button currOrderButton; // Value injected by FXMLLoader

    @FXML // fx:id="storeOrdersButton"
    private Button storeOrdersButton; // Value injected by FXMLLoader

    @FXML
    void switchScene(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert orderCoffeeButton != null : "fx:id=\"orderCoffeeButton\" was not injected: check your FXML file 'mainMenu.fxml'.";
        assert orderDonutButton != null : "fx:id=\"orderDonutButton\" was not injected: check your FXML file 'mainMenu.fxml'.";
        assert currOrderButton != null : "fx:id=\"currOrderButton\" was not injected: check your FXML file 'mainMenu.fxml'.";
        assert storeOrdersButton != null : "fx:id=\"storeOrdersButton\" was not injected: check your FXML file 'mainMenu.fxml'.";

    }
}
