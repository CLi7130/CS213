package Payroll;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

/**
The main class that runs the GUI
@author Craig Li, Prerak Patel
 */
public class Main extends Application {
    /**
     * The method starts the GUI
     * @param   primaryStage    this parameter sets the stage
     */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Payroll.fxml"));
			Scene scene = new Scene(root,600,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Payroll Processing");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * launches he GUI
	 * @param args arguements
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
