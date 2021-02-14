import java.util.Scanner;
import java.util.StringTokenizer;

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





public class PayrollProcessing{
    
    private static final String DELIMS = ", \t\n";
    /**
    public void run(){
        System.out.println("Library Kiosk running ");
        Scanner input = new Scanner(System.in);
        String stringInput;
        String action = "";
        String alpha = "";
        StringTokenizer string;
        String AP = "AP";
        int count = 0;
        while(true) {
            
            stringInput = input.nextLine();
            if (stringInput.isEmpty()) {
                continue;
            }
            action = "";
            alpha = "";
            string = new StringTokenizer(stringInput, DELIMS, false);
            action = string.nextToken();
            System.out.println(action.length());
            action.trim();
            System.out.println(action.length());
            System.out.println("action before " + action);
            //for(int i = 0; i < action.length(); i++) {
                if(action.substring(0, 2).equals(AP)) {
                    alpha = action.substring(0, 2);
                }
                else {
                    alpha = action.substring(2, 4);
                }
            //}
            System.out.println("alpha before " + alpha);
            System.out.println(alpha.length());
            if(alpha.equals(AP)) {
                System.out.println(action);
            }
            else if(action.contentEquals("Q")) {
                break;
            }
            else {
                System.out.println("Invalid Command!");
            }
            action = "AP";
            System.out.println(action.equals(AP));
        }
        System.out.println("Kiosk session ended.");
        input.close();


    }
    */
    
    public void run() {
        System.out.println("Doing stuff ");
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
            if(count > 0) {
                action = action.substring(2);
            }
            if(action.contentEquals("AP")) {
                // proof of concept 
                // makes a profile with credentials -> puts profile into employee class -> puts employee into Company array of objects
                Profile profile = new Profile();
                Parttime employee = new Parttime(profile);
                String name = string.nextToken() + "," + string.nextToken();
                String dmpt = string.nextToken();
                String dateHired = string.nextToken();
                double hourlyRate = Double.parseDouble(string.nextToken());
                profile.setName(name);;
                profile.setDepartment(dmpt);
                employee.setHourlyRate(hourlyRate);
                Company.add(employee);
            }
            else if(action.contentEquals("S")) {
                Profile profile = new Profile();
                Parttime employee = new Parttime(profile);
                String name = string.nextToken() + "," + string.nextToken();
                String dmpt = string.nextToken();
                String dateHired = string.nextToken();
                int hours = Integer.parseInt(string.nextToken());
                profile.setName(name);;
                profile.setDepartment(dmpt);
                employee.setHours(hours);
                Company.setHours(employee);
            }
            else if(action.contentEquals("P")) {
                Company.print();
            }
            else if(action.contentEquals("Q")) {
                break;
            }
            else {
                System.out.println("Invalid Command!");
            }
            count++;

        }
        input.close();
        Profile profile1 = new Profile();
        Employee employee1 = new Employee(profile1);
        profile1.setName("Demarcus");
        Company.add(employee1);
        Profile profile2 = new Profile();
        Employee employee2 = new Employee(profile2);
        profile2.setName("Jemarcus");
        Company.add(employee2);
        Profile profile3 = new Profile();
        Parttime employee3 = new Parttime(profile3);
        profile3.setName("Lemarcus");
        employee3.setHourlyRate(45.3);
        Company.add(employee3);
        Company.remove(employee2);
        Company.print();

    }

}
