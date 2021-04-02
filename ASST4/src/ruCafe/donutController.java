package ruCafe;

/**
 * Sample Skeleton for 'orderingDonuts.fxml' Controller Class
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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

    @FXML // fx:id="orderedDonuts"
    private TextArea orderedDonuts; // Value injected by FXMLLoader

    @FXML // fx:id="donutChoicesMenu"
    private ComboBox<?> donutChoicesMenu; // Value injected by FXMLLoader

    @FXML // fx:id="donutQuantityMenu"
    private ChoiceBox<?> donutQuantityMenu; // Value injected by FXMLLoader

    @FXML // fx:id="donutTypeMenu"
    private ComboBox<?> donutTypeMenu; // Value injected by FXMLLoader

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert addDonut != null : "fx:id=\"addDonut\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert removeDonut != null : "fx:id=\"removeDonut\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert donutTotal != null : "fx:id=\"donutTotal\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert addToOrder != null : "fx:id=\"addToOrder\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert orderedDonuts != null : "fx:id=\"orderedDonuts\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert donutChoicesMenu != null : "fx:id=\"donutChoicesMenu\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert donutQuantityMenu != null : "fx:id=\"donutQuantityMenu\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        assert donutTypeMenu != null : "fx:id=\"donutTypeMenu\" was not injected: check your FXML file 'orderingDonuts.fxml'.";
        
        donutTypeMenu.getItems().addAll("Yeast Donuts", "Cake Donuts", "Donut Holes");
        donutQuantityMenu.getItems().addAll("1", "2", "3", "4", "5");
        donutTotal.setEditable(false);
        orderedDonuts.setEditable(false);
    }
}


/*

*/