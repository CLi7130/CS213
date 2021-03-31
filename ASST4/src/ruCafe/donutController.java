package ruCafe;

/**
 * Sample Skeleton for 'orderingDonuts.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class donutController {

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

    @FXML // fx:id="quantityDonut"
    private MenuButton quantityDonut; // Value injected by FXMLLoader

    @FXML // fx:id="orderedDonuts"
    private TextArea orderedDonuts; // Value injected by FXMLLoader

    @FXML // fx:id="donutType"
    private MenuButton donutType; // Value injected by FXMLLoader

    @FXML // fx:id="yeastDonut"
    private MenuItem yeastDonut; // Value injected by FXMLLoader

    @FXML // fx:id="cakeDonut"
    private MenuItem cakeDonut; // Value injected by FXMLLoader

    @FXML // fx:id="donutHoles"
    private MenuItem donutHoles; // Value injected by FXMLLoader

    @FXML // fx:id="donutChoices"
    private ComboBox<?> donutChoices; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert addDonut != null : "fx:id=\"addDonut\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert removeDonut != null : "fx:id=\"removeDonut\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert donutTotal != null : "fx:id=\"donutTotal\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert addToOrder != null : "fx:id=\"addToOrder\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert quantityDonut != null : "fx:id=\"quantityDonut\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert orderedDonuts != null : "fx:id=\"orderedDonuts\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert donutType != null : "fx:id=\"donutType\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert yeastDonut != null : "fx:id=\"yeastDonut\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert cakeDonut != null : "fx:id=\"cakeDonut\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert donutHoles != null : "fx:id=\"donutHoles\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert donutChoices != null : "fx:id=\"donutChoices\" was not injected: check your FXML file 'orderingDonuts.fxml'.";

    }
}
