/**
 * Sample Skeleton for 'Payroll.fxml' Controller Class
 */

package Payroll;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class PayrollController {

    final ToggleGroup group1 = new ToggleGroup();
    final ToggleGroup group2 = new ToggleGroup();
    final ToggleGroup group3 = new ToggleGroup();
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="date"
    private DatePicker date; // Value injected by FXMLLoader

    @FXML // fx:id="name"
    private TextField name; // Value injected by FXMLLoader

    @FXML // fx:id="CS"
    private RadioButton CS; // Value injected by FXMLLoader

    @FXML // fx:id="IT"
    private RadioButton IT; // Value injected by FXMLLoader

    @FXML // fx:id="ECE"
    private RadioButton ECE; // Value injected by FXMLLoader

    @FXML // fx:id="fulltime"
    private RadioButton fulltime; // Value injected by FXMLLoader

    @FXML // fx:id="parttime"
    private RadioButton parttime; // Value injected by FXMLLoader

    @FXML // fx:id="management"
    private RadioButton management; // Value injected by FXMLLoader

    @FXML // fx:id="hoursWorked"
    private TextField hoursWorked; // Value injected by FXMLLoader

    @FXML // fx:id="hourlyRate"
    private TextField hourlyRate; // Value injected by FXMLLoader

    @FXML // fx:id="annualSalary"
    private TextField annualSalary; // Value injected by FXMLLoader

    @FXML // fx:id="manager"
    private RadioButton manager; // Value injected by FXMLLoader

    @FXML // fx:id="dpmtHead"
    private RadioButton dpmtHead; // Value injected by FXMLLoader

    @FXML // fx:id="director"
    private RadioButton director; // Value injected by FXMLLoader

    @FXML // fx:id="messageArea"
    private TextArea messageArea; // Value injected by FXMLLoader

    @FXML // fx:id="setHours"
    private Button setHours; // Value injected by FXMLLoader

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void clear(ActionEvent event) {
        messageArea.clear();
        name.clear();
        date.setValue(null);
        annualSalary.clear();
        hoursWorked.clear();
        hourlyRate.clear();
    }

    @FXML
    void fileExport(ActionEvent event) {

    }

    @FXML
    void fileImport(ActionEvent event) {

    }

    @FXML
    void print(ActionEvent event) {

    }

    @FXML
    void printByDate(ActionEvent event) {

    }

    @FXML
    void printByDpmt(ActionEvent event) {

    }

    @FXML
    void processPayments(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }

    @FXML
    void setHours(ActionEvent event) {

    }

    @FXML
    void visibility() {
        System.out.println("A");
        if (group2.getSelectedToggle() != null) {
            String workType = group2.getSelectedToggle().getUserData().toString();
            System.out.println("B");
            if(workType.equals("Fulltime")) {
                System.out.println("C");
                annualSalary.setDisable(false);
                hoursWorked.setDisable(true);
                hourlyRate.setDisable(true);
                manager.setDisable(true);
                dpmtHead.setDisable(true);
                director.setDisable(true);
                setHours.setDisable(true);
            }
            else if(workType.equals("Parttime")) {
                System.out.println("D");
                annualSalary.setDisable(true);
                hoursWorked.setDisable(false);
                hourlyRate.setDisable(false);
                manager.setDisable(true);
                dpmtHead.setDisable(true);
                director.setDisable(true);
                setHours.setDisable(false);
            }
            else if(workType.equals("Management")) {
                System.out.println("E");
                annualSalary.setDisable(false);
                hoursWorked.setDisable(true);
                hourlyRate.setDisable(true);
                manager.setDisable(false);
                dpmtHead.setDisable(false);
                director.setDisable(false);
                setHours.setDisable(true);
            }
            System.out.println("F");
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert date != null : "fx:id=\"date\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert CS != null : "fx:id=\"CS\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert IT != null : "fx:id=\"IT\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert ECE != null : "fx:id=\"ECE\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert fulltime != null : "fx:id=\"fulltime\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert parttime != null : "fx:id=\"parttime\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert management != null : "fx:id=\"management\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert hoursWorked != null : "fx:id=\"hoursWorked\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert hourlyRate != null : "fx:id=\"hourlyRate\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert annualSalary != null : "fx:id=\"annualSalary\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert manager != null : "fx:id=\"manager\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert dpmtHead != null : "fx:id=\"dpmtHead\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert director != null : "fx:id=\"director\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert messageArea != null : "fx:id=\"messageArea\" was not injected: check your FXML file 'Payroll.fxml'.";
        assert setHours != null : "fx:id=\"setHours\" was not injected: check your FXML file 'Payroll.fxml'.";
        CS.setToggleGroup(group1);
        CS.setUserData("CS");
        IT.setToggleGroup(group1);
        IT.setUserData("IT");
        ECE.setToggleGroup(group1);
        ECE.setUserData("ECE");
        fulltime.setToggleGroup(group2);
        fulltime.setUserData("Fulltime");
        parttime.setToggleGroup(group2);
        parttime.setUserData("Parttime");
        management.setToggleGroup(group2);
        management.setUserData("Management");
        manager.setToggleGroup(group3);
        manager.setUserData("Manager");
        dpmtHead.setToggleGroup(group3);
        dpmtHead.setUserData("Department Head");
        director.setToggleGroup(group3);
        director.setUserData("Director");
        CS.setSelected(true);
        fulltime.setSelected(true);
        manager.setSelected(true);
        visibility();
    }
}
