

/*
    NOTES/TODO
    *****Delete upon final formatting pass*****

    - user interface class that handles read/write to/from console
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

    COMMAND LIST
    - A
        - add a new employee to database
        - Management Role (integer code):
            - 1 - Manager
            - 2 - Department Head
            - 3 - Director
            - add integer code to end of A command input, see examples
            - Display "invalid management code." if integer code is not    supported
        - Department codes:
            - CS
            - ECE
            - IT
            - Display "invalid department code." if department code is not    supported
        - if employee profile exists in database, display "Employee is alread in the list."
        - if employee profile is not in database, display "Employee added."

        - Hourly rate and/or Salary cannot be negative

        EXAMPLE INPUT:
        AP Doe,Jane CS 7/1/2020 45.9 //part time employee with hourly pay rate
        AF Doe,Jane CS 1/1/2005 85000 //full time employee with annual salary
        AM Doe,Jane CS 2/28/2012 85000 1 //full time employee w/ "Manager" role
        AM Doe,John CS 2/28/2012 85000 2 //FT employee w/ "Department Head" role
        AM Doe,John ECE 2/28/2012 85000 3 //FT employee w/ "Director" role
    
    - R
        - remove an employee from company database.
        - if employee is not found, display "Employee doesn't exist."
        - if employee is found, display "Employee removed."

        Example input:
        R Doe,Jane CS 7/1/2020 //finds employee with same profile and removes it

    - C
        - calculate payments for all employees
            - processPayments() from Company class?
    - S
        - set working hours for a part time employee; number of hours cannot be negative

        Example input:
        S Doe,Jane CS 7/1/2020 //set the hours to 100 with the employee profile
    - PA
        - print earning statements for all employees
    - PH
        - print earning statements for all employees in the order of date hired
    - PD
        - print earning statements for all employees grouped by department.
    - Q
        - display "Payroll Processing completed."
        - quit program execution.

*/

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.NoSuchElementException;

public class PayrollProcessing{
    
    private static final String DELIMS = ", \t\n";
    
    public void run() {
        System.out.println("Payroll Processing starts.");
        Company Company = new Company();
        Scanner input = new Scanner(System.in);
        int count = 0;
        while(true) {
            String stringInput = input.nextLine();
            //stringInput.trim();
            if (stringInput.isEmpty()) {
                continue;
            }
            StringTokenizer string = new StringTokenizer(stringInput,
                    DELIMS, false);
            String action = string.nextToken();
            //if(count > 0) {
            //    action = action.substring(2);
            //}
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
                        System.out.println(dateString + " is not a valid date!");
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
                    Company.add(employee);
                    System.out.println("Employee added.");
                }
                catch(NoSuchElementException exception){
                    System.out.println("Exception: Invalid Input.");
                    continue;
                }
                catch(NumberFormatException hourlyRate){
                    System.out.println("Exception: Invalid Hourly Rate.");
                    continue;
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
                        System.out.println(dateString + "is not a valid date!");
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
                    Company.add(employee);
                    System.out.println("Employee added.");
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
                        System.out.println(dateString + "is not a valid date!");
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
                    Company.add(employee);
                    System.out.println("Employee added.");
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
            }
            else if(action.contentEquals("S")) {
                if(Company.getNumEmployees() == 0){
                    System.out.println("Employee database is empty.");
                    continue;
                }
                //find and then set hours?
                Profile profile = new Profile();
                Parttime employee = new Parttime(profile);
                try{
                    String name = string.nextToken() + "," + string.nextToken();
                    String dmpt = string.nextToken();
                    String dateString = string.nextToken();
                    Date dateHired = new Date(dateString);
                    if(!dateHired.isValid()){
                        System.out.println(dateString + "is not a valid date!");
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
                    Company.setHours(employee);
                    System.out.println("Working hours set.");
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

            }
            else if(action.contentEquals("R")){
                if(Company.getNumEmployees() == 0){
                    System.out.println("Employee database is empty.");
                    continue;
                }
                
                Profile profile = new Profile();
                Parttime employee = new Parttime(profile);
                String name = string.nextToken() + "," + string.nextToken();
                String dmpt = string.nextToken();
                String dateString = string.nextToken();
                Date dateHired = new Date(dateString);

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
            count++;

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
