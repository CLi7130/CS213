

/*
    NOTES/TODO
    *****Delete upon final formatting pass*****

    - Handles all exceptions and invalid data BEFORE calling methods in 
        Company class to complete command
        - IE: InputMismatchException, NumberFormatException, invalid dates,
            invalid department codes, invalid codes for management roles, and negative values.
                - upon reading an error, or throwing an exception, display a
                    message on the console.

                - -2 points for:
                    - each exception not caught
                    - invalid data not checked.
                    - message not displayed
    - command lines always begins with command in UPPERCASE letters, followed
        by data tokens delimited by whitespaces
            - whitespaces can be single space, multiple spaces, tab or a newline (\n)
            - commands are case sensitive (lower case is invalid)
            - required to handle bad/unsupported commands
 */

/**
User facing class that allows for the operation of the program.
Performs all functions associated with creating a payroll, including the addition and removal of parttime/fulltime/management employees, as well as specific operations to each type of employee. This class also allows the user to print employee information based on date hired, department, or array order.
@author Craig Li, Prerak Patel
 */
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.NoSuchElementException;

public class PayrollProcessing{
    
    private static final String DELIMS = ", \t\n";
    
    public void run() {
        System.out.println("Payroll Processing starts.");
        Company Company = new Company();
        Scanner input = new Scanner(System.in);
        while(true) {
            String stringInput = input.nextLine();
            //stringInput.trim();
            if (stringInput.isEmpty()) {
                continue;
            }
            StringTokenizer string = new StringTokenizer(stringInput,
                    DELIMS, false);
            String action = string.nextToken();

            if(action.contentEquals("AP")) {
                // proof of concept 
                // makes a profile with credentials -> puts profile into employee class -> puts employee into Company array of objects
                Profile profile = new Profile();
                Parttime employee = new Parttime(profile);

                try{
                    String name = string.nextToken() + "," + string.nextToken();
                    String dmpt = string.nextToken();
                    if(!isDeptValid(dmpt)){
                        continue;
                    }
                    String dateString = string.nextToken();
                    Date dateHired = new Date(dateString);
                    if(!dateHired.isValid()){
                        System.out.println(dateString + " is not a valid"
                                            + " date!");
                        continue;
                    }
                    double hourlyRate = Double.parseDouble(string.nextToken());
                    if(hourlyRate < 0){
                        System.out.println("Pay rate cannot be negative.");
                        continue;
                    }
                    employee.setHourlyRate(hourlyRate);
                    profile.setName(name);
                    profile.setDepartment(dmpt);
                    profile.setDateHired(dateHired);
                    boolean added = Company.add(employee);
                    if(added){
                        System.out.println("Employee added.");
                    }
                    else{
                        System.out.println("Employee is already in the list.");
                    }
                }
                catch(NoSuchElementException exception){
                    System.out.println("Exception: Unexpected Number " 
                                        + "of Data Tokens.");
                    continue;
                }
                catch(NumberFormatException hourlyRate){
                    System.out.println("Exception: Invalid Hourly Rate.");
                    continue;
                }
                catch(ArrayIndexOutOfBoundsException dateHired){
                    System.out.println("Exception: Invalid Date.");
                }
            }
            else if(action.contentEquals("AF")) {
                // proof of concept 
                // makes a profile with credentials -> puts profile into employee class -> puts employee into Company array of objects
                Profile profile = new Profile();
                Fulltime employee = new Fulltime(profile);

                try{
                    String name = string.nextToken() + "," + string.nextToken();
                    String dmpt = string.nextToken();
                    if(!isDeptValid(dmpt)){
                        continue;
                    }
                    String dateString = string.nextToken();
                    Date dateHired = new Date(dateString);
                    if(!dateHired.isValid()){
                        System.out.println(dateString + " is not a valid"
                                            + " date!");
                        continue;
                    }
                    int annualSalary = Integer.parseInt(string.nextToken());
                    if(annualSalary < 0){
                        System.out.println("Salary cannot be negative.");
                        continue;
                    }
                    employee.setAnnualSalary(annualSalary);
                    profile.setName(name);
                    profile.setDateHired(dateHired);
                    profile.setDepartment(dmpt);
                    boolean added = Company.add(employee);
                    if(added){
                        System.out.println("Employee added.");
                    }
                    else{
                        System.out.println("Employee is already in the list.");
                    }
                }
                catch(NoSuchElementException exception){
                    System.out.println("Exception: Unexpected Number " 
                                        + "of Data Tokens.");
                    continue;
                }
                catch(NumberFormatException annualSalary){
                    System.out.println("Exception: Invalid Salary.");
                    continue;
                }
                catch(ArrayIndexOutOfBoundsException dateHired){
                    System.out.println("Exception: Invalid Date.");
                }

            }
            else if(action.contentEquals("AM")) {
                // proof of concept 
                // makes a profile with credentials -> puts profile into employee class -> puts employee into Company array of objects
                Profile profile = new Profile();
                Management employee = new Management(profile);
                
                try{
                    String name = string.nextToken() + "," + string.nextToken();
                    String dmpt = string.nextToken();
                    if(!isDeptValid(dmpt)){
                        continue;
                    }

                    String dateString = string.nextToken();
                    Date dateHired = new Date(dateString);
                    if(!dateHired.isValid()){
                        System.out.println(dateString + " is not a valid"
                                            + " date!");
                        continue;
                    }

                    int annualSalary = Integer.parseInt(string.nextToken());
                    if(annualSalary < 0){
                        System.out.println("Salary cannot be negative.");
                        continue;
                    }

                    try{
                        int role = Integer.parseInt(string.nextToken());
                        if(!isMgmtRoleValid(role)){
                            continue;
                        }
                        employee.setRole(role);
                    }
                    catch(NumberFormatException role){
                        System.out.println("Exception: Invalid Manager Code.");
                        continue;
                    }
                    
                    employee.setAnnualSalary(annualSalary);
                    profile.setName(name);
                    profile.setDateHired(dateHired);
                    profile.setDepartment(dmpt);
                    boolean added = Company.add(employee);
                    if(added){
                        System.out.println("Employee added.");
                    }
                    else{
                        System.out.println("Employee is already in the list.");
                    }
                    
                }
                catch(NoSuchElementException exception){
                    System.out.println("Exception: Unexpected Number " 
                                        + "of Data Tokens.");
                    continue;
                }
                catch(NumberFormatException annualSalary){
                    System.out.println("Exception: Invalid Salary.");
                    continue;
                }
                catch(ArrayIndexOutOfBoundsException dateHired){
                    System.out.println("Exception: Invalid Date.");
                }
            }
            else if(action.contentEquals("S")) {
                if(Company.getNumEmployees() == 0){
                    System.out.println("Employee database is empty.");
                    continue;
                }
                Profile profile = new Profile();
                Parttime employee = new Parttime(profile);
                try{
                    String name = string.nextToken() + "," + string.nextToken();
                    String dmpt = string.nextToken();
                    if(!isDeptValid(dmpt)){
                        continue;
                    }
                    String dateString = string.nextToken();
                    Date dateHired = new Date(dateString);
                    if(!dateHired.isValid()){
                        System.out.println(dateString + " is not a valid"
                                            + " date!");
                        continue;
                    }
                    
                    int hours = Integer.parseInt(string.nextToken());
                    if(!isHoursValid(hours)){
                        continue;
                    }
                    profile.setName(name);
                    profile.setDateHired(dateHired);
                    profile.setDepartment(dmpt);
                    employee.setHours(hours);
                    try{
                        boolean setHours = Company.setHours(employee);
                        if(setHours){
                            System.out.println("Working hours set.");
                        }
                        else{
                            System.out.println("Employee is not in list.");
                        }
                        
                    }
                    catch(ClassCastException setHours){
                        System.out.println("Employee is not Parttime.");
                    }
                }
                catch(NoSuchElementException exception){
                    System.out.println("Exception: Unexpected Number " 
                                        + "of Data Tokens.");
                    continue;
                }
                catch(NumberFormatException hours){
                    System.out.println("Exception: Invalid Hours.");
                    continue;
                }
                catch(ArrayIndexOutOfBoundsException dateHired){
                    System.out.println("Exception: Invalid Date.");
                }

            }
            else if(action.contentEquals("R")){
                if(Company.getNumEmployees() == 0){
                    System.out.println("Employee database is empty.");
                    continue;
                }

                Profile profile = new Profile();
                Employee employee = new Employee(profile);
                try{
                    String name = string.nextToken() + "," + string.nextToken();
                    String dmpt = string.nextToken();
                    if(!isDeptValid(dmpt)){
                        continue;
                    }
                    String dateString = string.nextToken();
                    Date dateHired = new Date(dateString);
                    if(!dateHired.isValid()){
                        System.out.println(dateString + " is not a valid date!");
                        continue;
                    }
                    profile.setName(name);
                    profile.setDateHired(dateHired);
                    profile.setDepartment(dmpt);

                    if(Company.remove(employee)){
                        System.out.println("Employee removed.");
                    }
                    else{
                        System.out.println("Employee doesn't exist.");
                    }
                }
                catch(NoSuchElementException exception){
                    System.out.println("Exception: Unexpected Number " 
                                        + "of Data Tokens.");
                    continue;
                }
                catch(ArrayIndexOutOfBoundsException dateHired){
                    System.out.println("Exception: Invalid Date.");
                }
            }
            else if(action.contentEquals("C")) {
                if(Company.getNumEmployees() == 0){
                    System.out.println("Employee database is empty.");
                    continue;
                }
                Company.processPayments();
                System.out.println("Calculation of employee payments is done.");
            }
            else if(action.contentEquals("PA")) {
                if(Company.getNumEmployees() == 0){
                    System.out.println("Employee database is empty.");
                    continue;
                }
                else{
                    System.out.println("--Printing earning statements for all "
                                        + "employees--");
                    Company.print();
                }
            }
            else if(action.contentEquals("PH")) {
                if(Company.getNumEmployees() == 0){
                    System.out.println("Employee database is empty.");
                    continue;
                }
                else{
                    System.out.println("--Printing earning statements by "
                                        + "date hired--");
                    Company.printByDate();
                }
            }
            else if(action.contentEquals("PD")) {
                if(Company.getNumEmployees() == 0){
                    System.out.println("Employee database is empty.");
                    continue;
                }
                else{
                    System.out.println("--Printing earning statements by "
                                        + "department--");
                    Company.printByDepartment();
                }
            }
            else if(action.contentEquals("Q")) {
                System.out.println("Payroll Processing completed.");
                break;
            }
            else {
                System.out.println("Command '" + action + "' not supported!");
                continue;
            }

        }
        input.close();

    }
    private boolean isHoursValid(int hours){

        if(hours > 100){
            System.out.println("Invalid Hours: over 100.");
            return false;
        }
        if(hours < 0){
            System.out.println("Working hours cannot be negative");
            return false;
        }
        return true;

    }
    private boolean isDeptValid(String dept){
        if(dept.equals("CS") || dept.equals("ECE") || dept.equals("IT")){
            return true;
        }
        System.out.println("'" + dept + "' is not a valid department code.");
        return false;
    }
    private boolean isMgmtRoleValid(int role){
        if(role < 1 || role > 3){
            System.out.println("Invalid management code.");
            return false;
        }
        return true;
    }

}
