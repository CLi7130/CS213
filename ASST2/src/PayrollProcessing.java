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
    
    public static final String DELIMS = ", \t\n";
    
    public void run() {
        Scanner input = new Scanner(System.in);
        Company Company = new Company();
        while(true) {   
            String stringInput = input.nextLine();
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
                Employee employee = new Employee();
                String name = string.nextToken() + "," + string.nextToken();
                String dmpt = string.nextToken();
                String dateHired = string.nextToken();
                profile.setName(name);;
                profile.setDepartment(dmpt);
                System.out.println(name + dmpt + dateHired);
                employee.add(profile);
                Company.add(employee);
                Company.print();
            }
            
        }
    }

}
