package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;

/**
The main class that runs the GUI
@author Craig Li, Prerak Patel
 */
public class Main extends Application {
	private static Stage PRIMARY_STAGE; //wonky constant? check if you need to fix.

    /**
     * The method starts the GUI
     * @param   primaryStage    this parameter sets the stage
     */
	@Override
	public void start(Stage primaryStage) {
		try {
		    Parent root = (Parent) FXMLLoader.load(Main.class.getResource("/application/MainMenu.fxml"));
		    Scene scene = new Scene(root, 666, 444);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Rutgers Cafe");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			PRIMARY_STAGE = primaryStage;
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Main method to launch the GUI
	 * @param args arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * Returns the primary stage for the program.
	 * @return PRIMARY_STAGE parent stage for the program (main menu in this assignment)
	 */
	public static Stage getPrimaryStage() {
		return PRIMARY_STAGE;
	}

}