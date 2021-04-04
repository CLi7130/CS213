/**
 * Sample Skeleton for 'OrderDonuts.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import application.Enums.YEAST_DONUTS;
import application.Enums.DONUT_HOLES;
import application.Enums.DONUT_TYPES;

public class OrderDonutsController {
	
	//private static final int TOTAL_DONUT_CHOICES = 9;
	//private Image[] donutImages = "../"{};
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
    private ComboBox<Enums.DONUT_TYPES> donutTypeMenu; // Value injected by FXMLLoader

    @FXML // fx:id="donutOrderDisplay"
    private ListView<String> donutOrderDisplay; // Value injected by FXMLLoader
   
    @FXML // fx:id="previewImage"
    private ImageView previewImage; // Value injected by FXMLLoader
    
    
    //rivate Image[] donutImages = new Image[];

    @FXML
    void add(ActionEvent event) {
    	//check for null values in donut quantity and choice first
    }

    @FXML
    void remove(ActionEvent event) {
    	//check for invalid quantities to remove, or item isn't in order
    }
    
    @FXML
    void updateImage() {
    	//update image based on setDonutChoice
    	String donutChoice = donutChoicesMenu.getValue().toString();
    	donutChoice += ".jpeg";
    	
    }

    @FXML
    void setDonutChoice(ActionEvent event) {
    	String donutChoice = donutChoicesMenu.getValue().toString();
    	donutChoicesMenu.setPromptText(donutChoice);
    	//implement image switcher?
    }

    @FXML
    void changeDonutSelection(ActionEvent event) {
    	String donutType = donutTypeMenu.getValue().toString();
    	donutTypeMenu.setPromptText(donutType);
    	
    	donutChoicesMenu.setDisable(false);
    	donutQuantityMenu.setDisable(false);
    	
    	
    	if(donutType.equals("CAKE_DONUTS")) {
    		donutChoicesMenu.getItems().setAll(Enums.CAKE_DONUTS.values());
    	}
    	else if(donutType.equals("DONUT_HOLES")){
    		donutChoicesMenu.getItems().setAll(Enums.DONUT_HOLES.values());
    	}
    	else if(donutType.equals("YEAST_DONUTS")){
    		donutChoicesMenu.getItems().setAll(Enums.YEAST_DONUTS.values());
    	}
    	donutChoicesMenu.getSelectionModel().selectFirst();
    	donutQuantityMenu.getSelectionModel().selectFirst();
    }

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
        
        donutTypeMenu.getItems().setAll(Enums.DONUT_TYPES.values());
        

        for(int i = 1; i < 13; i++) {
        	donutQuantityMenu.getItems().add(i);
        	//janky implementation, refactor later?
        }
        
        
        
    }
}
