import java.util.Scanner;
import java.util.NoSuchElementException;

/**
User facing class that allows for the operation of the program.
Performs all functions associated with creating a payroll, including the addition and removal of parttime/fulltime/management employees, as well as specific operations to each type of employee. This class also allows the user to print employee information based on date hired, department, or array order.
@author Craig Li, Prerak Patel
 */
public class PayrollProcessing{
    private static final int ACTION = 0;
    private static final int LASTNAMEINDEX = 1;
    private static final int FIRSTNAMEINDEX = 2;
    private static final int DEPTINDEX = 3;
    private static final int DATEHIREDINDEX = 4;
    private static final int HOURLYWAGEINDEX = 5;
    private static final int SALARYINDEX = 5;
    private static final int MGMTROLEINDEX = 6;
    private static final int HOURSINDEX = 5;
    /**
    Interface that reads from user input, and performs actions based on commands given.
    Actions allowed are addition/removal of parttime/fulltime/management
    employees, setting hours of parttime employees, calculating payments,
    and printing based on different sorting methods.
    */
    public void run() {
        System.out.println("Payroll Processing starts.");
        Company Company = new Company();
        Scanner input = new Scanner(System.in);
        while(true) {
            String stringInput = input.nextLine();
            if (stringInput.isEmpty()) {
                continue;
            }
            String[] empInfo = stringInput.split(" |,|\t|\n");
            if(empInfo[ACTION].equals("AP")){
                Profile profile = new Profile();
                Parttime employee = new Parttime(profile);
                if(makeBaseEmp(empInfo, profile) 
                    && addParttimeInfo(empInfo, employee))
                {
                    addEmployee(Company, employee);
                }
            }
            else if(empInfo[ACTION].equals("AF")){
                Profile profile = new Profile();
                Fulltime employee = new Fulltime(profile);
                if(makeBaseEmp(empInfo, profile) 
                    && addFullTimeInfo(empInfo, employee))
                {
                    addEmployee(Company, employee);
                }
            }
            else if(empInfo[ACTION].equals("AM")){
                Profile profile = new Profile();
                Management employee = new Management(profile);
                if(makeBaseEmp(empInfo, profile)
                    && addFullTimeInfo(empInfo, employee)
                    && addMgmtInfo(empInfo, employee))
                {
                    addEmployee(Company, employee);
                }
            }
            else if(empInfo[ACTION].equals("S")){
                if(isCompanyEmpty(Company)){
                    continue;
                }
                Profile profile = new Profile();
                Parttime employee = new Parttime(profile);
                if(makeBaseEmp(empInfo, profile)){
                    if(isHoursValid(empInfo, Company, employee)){
                        System.out.println("Working Hours set.");
                    }
                }
            }
            else if (empInfo[ACTION].equals("R")){
                if(isCompanyEmpty(Company)){
                    continue;
                }
                Profile profile = new Profile();
                Employee employee = new Employee(profile);
                if(makeBaseEmp(empInfo, profile)){
                    if(Company.remove(employee)){
                        System.out.println("Employee Removed.");
                    }
                    else{
                        System.out.println("Employee doesn't exist.");
                    }
                }
            }
            else if(empInfo[ACTION].equals("C")) {
                if(isCompanyEmpty(Company)){
                    continue;
                }
                Company.processPayments();
                System.out.println("Calculation of employee payments is done.");
            }
            else if(empInfo[ACTION].equals("PA")){
                if(isCompanyEmpty(Company)){
                    continue;
                }
                else{
                    System.out.println("--Printing earning statements for all "
                                        + "employees--");
                    Company.print();
                }
            }
            else if(empInfo[ACTION].equals("PH")) {
                if(isCompanyEmpty(Company)){
                    continue;
                }
                else{
                    System.out.println("--Printing earning statements by "
                                        + "date hired--");
                    Company.printByDate();
                }
            }
            else if(empInfo[ACTION].equals("PD")) {
                if(isCompanyEmpty(Company)){
                    continue;
                }
                else{
                    System.out.println("--Printing earning statements by "
                                        + "department--");
                    Company.printByDepartment();
                }
            }
            else if(empInfo[ACTION].equals("Q")){
                System.out.println("Payroll Processing completed.");
                break;
            }
            else{
                System.out.println("Command '" + empInfo[ACTION] 
                                    + "' not supported!");
            }
        }
        input.close();
    }
    /**
    Checks if given company has zero employees.
    @return true if company has zero employees, false if otherwise.
    */
    private boolean isCompanyEmpty(Company company){
        if(company.getNumEmployees() == 0){
            System.out.println("Employee database is empty.");
            return true;
        }
        return false;
    }
    /**
    Checks if provided department is valid based on the three given in the 
    assignment.
    The only valid departments are CS, ECE, and IT.
    @param dept    String represented department specified by user.
    @return true if department is in list of acceptable departments, false
                    otherwise.
     */
    private boolean isDeptValid(String dept){
        if(dept.equals("CS") || dept.equals("ECE") || dept.equals("IT")){
            return true;
        }
        System.out.println("'" + dept + "' is not a valid department code.");
        return false;
    }
    /**
    Checks if integer value determining manager role is valid.
    Valid range is 1-3.
    @param role    Integer value specified by user representing management role.
    @return true if management code is within valid range, false if otherwise.
    */
    private boolean isMgmtRoleValid(int role){
        if(role < 1 || role > 3){
            System.out.println("Invalid management code.");
            return false;
        }
        return true;
    }
    /**
    Creates a general profile common to all employees with name, date hired, and department as specified by the user.
    @param empInfo[]    String array that contains all relevant data tokens
                        provided by the user.
    @param profile      Empty profile object that will be filled with 
                        information from empInfo[].
    @return Method returns false if any of the provided information is invalid,
            true if all information is valid.
    */
    private boolean makeBaseEmp(String[] empInfo, Profile profile){

        try{
            String name = empInfo[LASTNAMEINDEX] + "," + empInfo[FIRSTNAMEINDEX];
            String dmpt = empInfo[DEPTINDEX];
            String dateString = empInfo[DATEHIREDINDEX];
            Date dateHired = new Date(dateString);
    
            if(!isDeptValid(dmpt)){
                return false;
            }
            if(!dateHired.isValid()){
                System.out.println(dateString + " is not a valid"
                                    + " date!");
                return false;
            }
            profile.setName(name);
            profile.setDepartment(dmpt);
            profile.setDateHired(dateHired);
        }
        catch(NoSuchElementException exception){
            System.out.println("Exception: Unexpected Number " 
                                + "of Data Tokens.");
            return false;
        }
        catch(ArrayIndexOutOfBoundsException exception){
            System.out.println("Exception: Unexpected Number " 
                                + "of Data Tokens.");
            return false;
        }
        catch(NumberFormatException exception){
            System.out.println("Exception: Invalid Date.");
            return false;
        }
        return true;
    }
    /**
    Adds additional information specific to parttime employees based on user input.
    @param empInfo[]    String array that contains all relevant data tokens
                        provided by the user.
    @param employee    Parttime employee object that will be filled with
                       relevant information from empInfo[].
    @return Method returns false if any of the provided information is invalid,
            true if all information is valid.
    */
    private boolean addParttimeInfo(String[] empInfo, Parttime employee){
        
        try{
            double hourlyRate = Double.parseDouble(empInfo[HOURLYWAGEINDEX]);
            if(hourlyRate < 0){
                System.out.println("Pay rate cannot be negative.");
                return false;
            }
            employee.setHourlyRate(hourlyRate);
        }
        catch(NumberFormatException hourlyRate){
            System.out.println("Exception: Invalid Hourly Rate."); 
            return false;
        }
        catch(ArrayIndexOutOfBoundsException exception){
            System.out.println("Exception: Unexpected Number " 
                                + "of Data Tokens.");
            return false;
        }
        catch(NullPointerException exception){
            System.out.println("Exception: No data.");
            return false;
        }
        return true;
    }
    /**
    Adds additional information specific to fulltime employees based on user input.
    @param empInfo[]    String array that contains all relevant data tokens
                        provided by the user.
    @param employee    Fulltime employee object that will be filled with
                       relevant information from empInfo[].
    @return Method returns false if any of the provided information is invalid,
            true if all information is valid.
    */
    private boolean addFullTimeInfo(String[] empInfo, Fulltime employee){

        try{
            int annualSalary = Integer.parseInt(empInfo[SALARYINDEX]);
            if(annualSalary < 0){
                System.out.println("Salary cannot be negative.");
                return false;
                
            }
            employee.setAnnualSalary(annualSalary);
        }
        catch(NoSuchElementException exception){
            System.out.println("Exception: Unexpected Number " 
                                + "of Data Tokens.");
            return false;
        }
        catch(NumberFormatException annualSalary){
            System.out.println("Exception: Invalid Salary.");
            return false;
        }
        catch(NullPointerException exception){
            System.out.println("Exception: No data.");
            return false;
        }
        catch(ArrayIndexOutOfBoundsException exception){
            System.out.println("Exception: Unexpected Number " 
                                + "of Data Tokens.");
            return false;
        }       
        return true;
    }
    /**
    Adds additional information specific to management employees based on user input.
    @param empInfo[]    String array that contains all relevant data tokens
                        provided by the user.
    @param employee    Management employee object that will be filled with
                       relevant information from empInfo[].
    @return Method returns false if any of the provided information is invalid,
            true if all information is valid.
    */
    private boolean addMgmtInfo(String[] empInfo, Management employee){

        try{
            int role = Integer.parseInt(empInfo[MGMTROLEINDEX]);
            if(!isMgmtRoleValid(role)){
                return false;
            }
            employee.setRole(role);
        }
        catch(NoSuchElementException exception){
            System.out.println("Exception: Unexpected Number " 
                                + "of Data Tokens.");
            return false;
        }
        catch(NumberFormatException role){
            System.out.println("Exception: Invalid Role.");
            return false;
        }
        catch(NullPointerException exception){
            System.out.println("Exception: No data.");
            return false;
        }
        catch(ArrayIndexOutOfBoundsException exception){
            System.out.println("Exception: Unexpected Number " 
                                + "of Data Tokens.");
            return false;
        }   
        return true;
    }
    /**
    Checks and sets parttime hours provided by the user if the hours are within the valid range of 0-100.
    @param empInfo[]    String array that contains all relevant data tokens
                        provided by the user.
    @param company    Company that contains the parttime employee.
    @param employee    Parttime employee object that will be filled with
                    relevant information from empInfo[].
    @return Method returns false if any of the provided information is invalid,
            true if all information is valid.
    */
    private boolean isHoursValid(String[] empInfo, Company company, 
                                    Parttime employee){

        try{
            int hours = Integer.parseInt(empInfo[HOURSINDEX]);
            if(hours > 100){
                System.out.println("Invalid Hours: over 100.");
                return false;
            }
            else if(hours < 0){
                System.out.println("Working hours cannot be negative");
                return false;
            }
            employee.setHours(hours);
            if(company.setHours(employee)){
                return true;
            }
            else{
                System.out.println("Employee is not in list.");
                return false;
            }
        }
        catch(NumberFormatException hours){
            System.out.println("Exception: Invalid Hours.");
            return false;
        }
        catch(ArrayIndexOutOfBoundsException exception){
            System.out.println("Exception: Unexpected Number " 
                                + "of Data Tokens.");
            return false;
        } 
        catch(NoSuchElementException exception){
            System.out.println("Exception: Unexpected Number " 
                                + "of Data Tokens.");
            return false;
        }
        catch(ClassCastException exception){
            System.out.println("'" + employee.getProfile().getName()
                                + "' is not a Parttime employee.");
            return false;
        }
    }
    /**
    Checks a provided company to see if an employee is present, and adds them to the company if they are not in the database.
    @param company    Company the employee is to be added to.
    @param employee    Employee that is to be added to the company.
    */
    private void addEmployee(Company company, Employee employee){
        boolean added = company.add(employee);
        if(added){
            System.out.println("Employee added.");
        }
        else{
            System.out.println("Employee is already in the list.");
        }
    }
}
