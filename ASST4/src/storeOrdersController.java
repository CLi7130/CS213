package src;
/**
 * Sample Skeleton for 'storeOrders.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class storeController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="orderInfoDisplay"
    private TextArea orderInfoDisplay; // Value injected by FXMLLoader

    @FXML // fx:id="removeOrderButton"
    private Button removeOrderButton; // Value injected by FXMLLoader

    @FXML // fx:id="exportOrdersButton"
    private Button exportOrdersButton; // Value injected by FXMLLoader

    @FXML // fx:id="totalDisplay"
    private TextField totalDisplay; // Value injected by FXMLLoader

    @FXML // fx:id="orderNumberMenu"
    private ComboBox<?> orderNumberMenu; // Value injected by FXMLLoader

    @FXML
    void exportOrders(ActionEvent event) {

    }

    @FXML
    void removeOrder(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert orderInfoDisplay != null : "fx:id=\"orderInfoDisplay\" was not injected: check your FXML file 'storeOrders.fxml'.";
        assert removeOrderButton != null : "fx:id=\"removeOrderButton\" was not injected: check your FXML file 'storeOrders.fxml'.";
        assert exportOrdersButton != null : "fx:id=\"exportOrdersButton\" was not injected: check your FXML file 'storeOrders.fxml'.";
        assert totalDisplay != null : "fx:id=\"totalDisplay\" was not injected: check your FXML file 'storeOrders.fxml'.";
        assert orderNumberMenu != null : "fx:id=\"orderNumberMenu\" was not injected: check your FXML file 'storeOrders.fxml'.";
        
        orderInfoDisplay.setEditable(false);
        totalDisplay.setEditable(false);
    }
}