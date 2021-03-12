package Payroll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
Controller Class for Payroll.fmxl handles all the actions that happen on the GUI
@author Craig Li, Prerak Patel
 */
public class PayrollController {
    
    final ToggleGroup group1 = new ToggleGroup();
    final ToggleGroup group2 = new ToggleGroup();
    final ToggleGroup group3 = new ToggleGroup();
    private static final int MANAGER = 1;
    private static final int DPT_HEAD = 2;
    private static final int DIRECTOR = 3;
    
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
    public TextArea messageArea; // Value injected by FXMLLoader

    @FXML // fx:id="setHours"
    private Button setHours; // Value injected by FXMLLoader
    
    public static String text;

    Company company = new Company();

    /**
     * @param texting setting the text
     */
    public static void getText(String texting) {
        text += texting;
    }
    
    /**
    Creates a general profile common to all employees with name, date hired, and department as specified by the user.
    @param profile      Empty profile object that will be filled with 
                        information from GUI.
    @return Method returns false if any of the provided information is invalid,
            true if all information is valid.
    */
    private boolean makeBaseEmp(Profile profile){
        try{
            if(this.name == null || this.name.getText().trim().isEmpty()) {
                messageArea.setText("Enter Name.\n");
                return false;
            }
            String name = this.name.getText();
            String dmpt = group1.getSelectedToggle().getUserData().toString();
            String dateString = date.getValue().toString();
            Date dateHired = new Date(dateString);
    

            if(!dateHired.isValid()){
                messageArea.setText(dateString + " is not a valid"
                                    + " date!\n");
                return false;
            }
            profile.setName(name);
            profile.setDepartment(dmpt);
            profile.setDateHired(dateHired);
        }
        catch(NoSuchElementException exception){
            messageArea.setText("Exception: Unexpected Number " 
                                + "of Data Tokens.\n");
            return false;
        }
        catch(ArrayIndexOutOfBoundsException exception){
            messageArea.setText("Exception: Unexpected Number " 
                                + "of Data Tokens.\n");
            return false;
        }
        catch(NumberFormatException exception){
            messageArea.setText("Exception: Invalid Date.\n");
            return false;
        }
        catch(NullPointerException exception) {
            messageArea.setText("Exception: Invalid Date.\n");
            return false;
        }
        return true;
    }
    
    /**
    Checks if given company has zero employees.
    @param  company takes in a company instance which should include the list of employees
    @return true if company has zero employees, false if otherwise.
    */
    private boolean isCompanyEmpty(Company company){
        if(company.getNumEmployees() == 0){
            messageArea.setText("Employee database is empty.\n");
            return true;
        }
        return false;
    }
    
    /**
    Adds additional information specific to parttime employees based on user input.
    @param employee    Parttime employee object that will be filled with
                       relevant information from GUI.
    @return Method returns false if any of the provided information is invalid,
            true if all information is valid.
    */
    private boolean addParttimeInfo(Parttime employee){
        
        try{
            if(!(this.hoursWorked == null || this.hoursWorked.getText().trim().isEmpty())) {
                isHoursValid(employee);
            }
            if(this.hourlyRate == null || this.hourlyRate.getText().trim().isEmpty()) {
                messageArea.setText("Enter Hourly Rate.\n");
                return false;
            }
            double hourlyRate = Double.parseDouble(this.hourlyRate.getText());
            if(hourlyRate < 0){
                messageArea.setText("Pay rate cannot be negative.\n");
                return false;
            }
            employee.setHourlyRate(hourlyRate);
        }
        catch(NumberFormatException hourlyRate){
            messageArea.setText("Exception: Invalid Hourly Rate.\n"); 
            return false;
        }
        catch(ArrayIndexOutOfBoundsException exception){
            messageArea.setText("Exception: Unexpected Number " 
                                + "of Data Tokens.\n");
            return false;
        }
        catch(NullPointerException exception){
            messageArea.setText("Exception: No data.\n");
            return false;
        }
        return true;
    }
    
    /**
    Adds additional information specific to fulltime employees based on user input.
    @param employee    Fulltime employee object that will be filled with
                       relevant information from GUI.
    @return Method returns false if any of the provided information is invalid,
            true if all information is valid.
    */
    private boolean addFulltimeInfo(Fulltime employee){

        try{
            if(this.annualSalary == null || this.annualSalary.getText().trim().isEmpty()) {
                messageArea.setText("Enter Annual Salary.\n");
                return false;
            }
            int annualSalary = Integer.parseInt(this.annualSalary.getText());
            if(annualSalary < 0){
                messageArea.setText("Salary cannot be negative.\n");
                return false;
                
            }
            employee.setAnnualSalary(annualSalary);
        }
        catch(NoSuchElementException exception){
            messageArea.setText("Exception: Unexpected Number " 
                                + "of Data Tokens.\n");
            return false;
        }
        catch(NumberFormatException annualSalary){
            messageArea.setText("Exception: Invalid Salary.\n");
            return false;
        }
        catch(NullPointerException exception){
            messageArea.setText("Exception: No data.\n");
            return false;
        }
        catch(ArrayIndexOutOfBoundsException exception){
            messageArea.setText("Exception: Unexpected Number " 
                                + "of Data Tokens.\n");
            return false;
        }       
        return true;
    }
    
    /**
    Checks and sets parttime hours provided by the user if the hours are within the valid range of 0-100.
    @param employee    Parttime employee object that will be filled with
                    relevant information from GUI.
    @return Method returns false if any of the provided information is invalid,
            true if all information is valid.
    */
    private boolean isHoursValid(Parttime employee){

        try{
            if(this.hoursWorked == null || this.hoursWorked.getText().trim().isEmpty()) {
                messageArea.appendText("Enter Hours Worked.\n");
                return false;
            }
            int hours = Integer.parseInt(this.hoursWorked.getText());
            if(hours > 100){
                messageArea.appendText("Invalid Hours: over 100.\n");
                return false;
            }
            else if(hours < 0){
                messageArea.appendText("Working hours cannot be negative\n");
                return false;
            }
            employee.setHours(hours);
            if(company.setHours(employee)){
                return true;
            }
            else{
                messageArea.appendText("Employee is not in list.\n");
                return false;
            }
        }
        catch(NumberFormatException hours){
            messageArea.appendText("Exception: Invalid Hours.\n");
            return false;
        }
        catch(ArrayIndexOutOfBoundsException exception){
            messageArea.appendText("Exception: Unexpected Number " 
                                + "of Data Tokens.\n");
            return false;
        } 
        catch(NoSuchElementException exception){
            messageArea.appendText("Exception: Unexpected Number " 
                                + "of Data Tokens.\n");
            return false;
        }
        catch(ClassCastException exception){
            messageArea.appendText("'" + employee.getProfile().getName()
                                + "' is not a Parttime employee.\n");
            return false;
        }
    }
    
    /**
     * Add will add an employee to the company list on action event
     * @param event takes in the action event
     */
    @FXML
    void add(ActionEvent event) {
        messageArea.clear();
        Profile profile = new Profile();
        if(makeBaseEmp(profile) == false) {
            messageArea.appendText("Employee not Added\n");
            return;
        }
        if (group2.getSelectedToggle().getUserData().toString().equals("Fulltime")) {
            Fulltime fulltime = new Fulltime(profile);
            if(addFulltimeInfo(fulltime)) {
                if (company.add(fulltime)) {
                    messageArea.setText("Employee added\n");
                }
                else {
                    messageArea.appendText("Employee not Added\n");
                }
            }
            else {
                messageArea.appendText("Employee not Added\n");
            }
        }
        else if (group2.getSelectedToggle().getUserData().toString().equals("Parttime")) {
            Parttime parttime = new Parttime(profile);
            if(addParttimeInfo(parttime)) {
                if (company.add(parttime)) {
                    messageArea.setText("Employee added\n");
                }
                else {
                    messageArea.appendText("Employee not Added\n");
                }
            }
            else {
                messageArea.appendText("Employee not Added\n");
            }
        }
        else if (group2.getSelectedToggle().getUserData().toString().equals("Management")) {
            Management management = new Management(profile);
            if(addFulltimeInfo(management)) {
                if(group3.getSelectedToggle().getUserData().toString().equals("Manager")) {
                    management.setRole(MANAGER);
                }
                else if(group3.getSelectedToggle().getUserData().toString().equals("Department Head")) {
                    management.setRole(DPT_HEAD);
                }
                else if(group3.getSelectedToggle().getUserData().toString().equals("Director")) {
                    management.setRole(DIRECTOR);
                }
                
                if (company.add(management)) {
                    messageArea.setText("Employee added\n");
                }
                else {
                    messageArea.appendText("Employee not Added\n");
                }
            }
            else {
                messageArea.appendText("Employee not Added\n");
            }

        }
    }

    /**
     * Clear will clear all data fields and text areas on the GUI
     * @param event takes in the action event
     */
    @FXML
    void clear(ActionEvent event) {
        messageArea.clear();
        name.clear();
        date.setValue(null);
        annualSalary.clear();
        hoursWorked.clear();
        hourlyRate.clear();
        messageArea.setText("Please Enter a Command");
    }

    /**
     * Export the database of employees to a file of your choosing
     * @param event takes in an action event
     */
    @FXML
    void fileExport(ActionEvent event) {
        if(isCompanyEmpty(company)) {
            return;
        }
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file
        try {
            PrintWriter pw = new PrintWriter(targetFile);
            text = "";
            company.exportDatabase();
            pw.print(text);
            pw.close();
            messageArea.setText(targetFile + " Exported");
        } catch (FileNotFoundException e) {
            messageArea.setText("File Not Found");
            e.printStackTrace();
        }
    }

    /**
     * Imports a file of your choosing and ads all the employees from the file
     * @param event takes in an action event
     */
    @FXML
    void fileImport(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Source File for the Import");
        chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File sourceFile = chooser.showOpenDialog(stage); //get the reference of the source file
        System.out.println(sourceFile);
        try {
            Scanner input = new Scanner(sourceFile);
            while(input.hasNextLine()) {
                final String DELIMS = ",";
                String stringInput = input.nextLine();
                StringTokenizer string = new StringTokenizer(stringInput,
                        DELIMS, false);
                String action = string.nextToken().toString();
                if (action.equals("P")) {
                    Profile profile = new Profile();
                    Parttime parttime = new Parttime(profile);
                    profile.setName(string.nextToken().toString());
                    profile.setDepartment(string.nextToken().toString());
                    Date date = new Date(string.nextToken().toString());
                    profile.setDateHired(date);
                    parttime.setHourlyRate(Double.parseDouble(string.nextToken().toString()));
                    company.add(parttime);
                }
                else if (action.equals("F")) {
                    Profile profile = new Profile();
                    Fulltime fulltime = new Fulltime(profile);
                    profile.setName(string.nextToken().toString());
                    profile.setDepartment(string.nextToken().toString());
                    Date date = new Date(string.nextToken().toString());
                    profile.setDateHired(date);
                    fulltime.setAnnualSalary(Integer.parseInt(string.nextToken().toString()));
                    company.add(fulltime);
                }
                else if (action.equals("M")) {
                    Profile profile = new Profile();
                    Management management = new Management(profile);
                    profile.setName(string.nextToken().toString());
                    profile.setDepartment(string.nextToken().toString());
                    Date date = new Date(string.nextToken().toString());
                    profile.setDateHired(date);
                    management.setAnnualSalary(Integer.parseInt(string.nextToken().toString()));
                    management.setRole(Integer.parseInt(string.nextToken().toString()));
                    company.add(management);
                }
            }
            messageArea.setText(sourceFile + " Imported");
            input.close();
        } catch (FileNotFoundException e) {
            messageArea.setText("File Not Found");
            e.printStackTrace();
        }
    }

    /**
     * prints the company list to the text area
     * @param event takes in an action event
     */
    @FXML
    void print(ActionEvent event) {
        if(isCompanyEmpty(company)) {
            return;
        }
        text = "";
        company.print();
        messageArea.setText(text);
    }

    /**
     * prints the comapny list to the text area sorted by date
     * @param event taks in an ction event
     */
    @FXML
    void printByDate(ActionEvent event) {
        if(isCompanyEmpty(company)) {
            return;
        }
        text = "";
        company.printByDate();
        messageArea.setText(text);
    }

    /**
     * prints the comapny list to the text area sorted by department
     * @param event taks in an ction event
     */
    @FXML
    void printByDpmt(ActionEvent event) {
        if(isCompanyEmpty(company)) {
            return;
        }
        text = "";
        company.printByDepartment();
        messageArea.setText(text);
    }

    /**
     * Processes the payments for the employees
     * @param event takes in an action event
     */
    @FXML
    void processPayments(ActionEvent event) {
        if(isCompanyEmpty(company)) {
            return;
        }
        company.processPayments();
        messageArea.setText("Payment for employees processed");
    }

    /**
     * removes the employee with the matching profile
     * @param event takes in an action event
     */
    @FXML
    void remove(ActionEvent event) {
        if(isCompanyEmpty(company)) {
            return;
        }
        Profile profile = new Profile();
        Employee employee = new Employee(profile);
        if(makeBaseEmp(profile)){
            if(company.remove(employee)){
                messageArea.setText("Employee removed.");
            }
            else{
                messageArea.setText("Employee doesn't exist.");
            }
        }
    }

    /**
     * Sets the hours for a Parttime employee
     * @param event takes in an ction event
     */
    @FXML
    void setHours(ActionEvent event) {
        if(isCompanyEmpty(company)) {
            return;
        }
        Profile profile = new Profile();
        Parttime employee = new Parttime(profile);
        if(makeBaseEmp(profile)){
            if(isHoursValid(employee)){
                messageArea.setText("Working hours set.");
            }
        }
    }

    /**
     * sets the visibility of data fields to disabled depending on the employee type
     */
    @FXML
    void visibility() {
        if (group2.getSelectedToggle() != null) {
            String workType = group2.getSelectedToggle().getUserData().toString();
            if(workType.equals("Fulltime")) {
                annualSalary.setDisable(false);
                hoursWorked.setDisable(true);
                hourlyRate.setDisable(true);
                manager.setDisable(true);
                dpmtHead.setDisable(true);
                director.setDisable(true);
                setHours.setDisable(true);
            }
            else if(workType.equals("Parttime")) {
                annualSalary.setDisable(true);
                hoursWorked.setDisable(false);
                hourlyRate.setDisable(false);
                manager.setDisable(true);
                dpmtHead.setDisable(true);
                director.setDisable(true);
                setHours.setDisable(false);
            }
            else if(workType.equals("Management")) {
                annualSalary.setDisable(false);
                hoursWorked.setDisable(true);
                hourlyRate.setDisable(true);
                manager.setDisable(false);
                dpmtHead.setDisable(false);
                director.setDisable(false);
                setHours.setDisable(true);
            }
        }
    }

    /**
     * initialize the GUI and set all the radio buttons to be apart of a toggle group
     */
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
        messageArea.setEditable(false);
        date.setEditable(false);
        messageArea.setText("Please Enter a Command");
        visibility();
    }
}
