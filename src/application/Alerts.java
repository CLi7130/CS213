package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/*
 * Creates alerts based on given user input
 * @author Craig Li, Prerak Patel
 */
public class Alerts {
	
    /*
     * Generates a confirmation alert/message
     * @params message	message to be displayed
     * 			windowTitle	title of the window of the alert.
     */
    protected static void makeNewAlert(String message, String windowTitle) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setContentText(message);
    	alert.setTitle(windowTitle);
    	alert.showAndWait();
    }

    /*
     * Generates a warning alert/message
     * @params message	message to be displayed
     * 			windowTitle	title of the window of the alert.
     */
    protected static void makeNewWarning(String message, String windowTitle) {
    	Alert alert = new Alert(AlertType.WARNING);
    	alert.setContentText(message);
    	alert.setTitle(windowTitle);
    	alert.showAndWait();
    }
    
}
