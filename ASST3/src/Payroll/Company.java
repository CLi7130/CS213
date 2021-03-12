package Payroll;

/**
Creates a new Company instance, an array of employees.
Array of employees will automatically grow when maximum capacity is reached,
methods allow the addition/removal of employees from the class, as well as
several different methods of printing all employees in the array.
@author Craig Li, Prerak Patel
*/
public class Company{
    private Employee[] empList;
    private int numEmployee;
    private static final int GROW_AMOUNT = 4;
    private static final int FAIL_CONDITION = -1;
    //private TextArea messageArea;
    
    /**
    Gets number of employees.
    @return numEmployee     int value containing the number of employees
                            at the company.
    */
    public int getNumEmployees(){
        return this.numEmployee;
    }
    
    /**
    Creates new instance of a Company (new employee list).
    */
    public Company() {
        numEmployee = 0;
        empList = new Employee[GROW_AMOUNT];
    }
    
    /**
    Searches employee list to find index of an employee.
    @return index   int value representing index where employee is located.
                    Will have value of -1 (FAILCONDITION) if employee is not found.
    */
    private int find(Employee employee){
        int index = 0;
        for(int i = 0; i < empList.length; i++) {
            if(empList[i] == null){
                continue;
            }
            if(empList[i].getProfile().equals(employee.getProfile())) {
                index = i;
                return index;
            }
        }
        index = FAIL_CONDITION;
        return index;

    }
    
    /**
    Increases capacity of employee list by GROWAMOUNT (4) by creating a larger array, and copying values of the original array over.
    */
    private void grow(){
        Employee[] grow = new Employee[empList.length + GROW_AMOUNT];
        for(int i = 0; i < empList.length; i++) {
            grow[i] = empList[i];
        }
        empList = grow;
    }
    
    /**
    Adds an employee to the employee list array.
    Checks array if employee is already in list, and will grow employee list if at maximum capacity.
    @param employee     employee object to be added to the employee
                        list
    @return false if employee is already in array, true if employee is not 
            in array and is successfully added.
    */
    public boolean add(Employee employee){
        if(find(employee) != FAIL_CONDITION){
            return false;
        }
        numEmployee++;
        if(numEmployee > empList.length) {
            grow();
        }
        empList[numEmployee - 1] = employee;
        return true;
    }
    
    /**
    Removes an employee from the employee list.
    @param employee    employee to be removed from the list.
    @return returns true if employee is successfully located and removed, false
            if the employee is not found.
    */
    public boolean remove(Employee employee){
        final int index = find(employee);
        //System.out.println(index);
        if(index >= 0) {
            empList[index] = null;
            numEmployee--;
            return true;
        }
        return false;
    }
    
    /**
    Sets Hours of a parttime employee to the hours value provided.
    @param employee    employee that requires hours to be set.
    @return true if employee's hours are changed, false if not a parttime
            employee, or employee does not exist in employee list.
    */
    public boolean setHours(Employee employee){
        if (employee instanceof Parttime) {
            final int index = find(employee);
            
            if (index >= 0) {
                ((Parttime) empList[index]).setHours(((Parttime) 
                        employee).getHours());
                return true;
            }
        }
        return false;
    }
    
    /**
    Processes payments for all employees.
    */
    public void processPayments(){
        for(int i = 0; i < numEmployee; i++) {
            if(empList[i] == null) {
                continue;
            }
            empList[i].calculatePayment();
        }
    }
    
    /**
    Shifts an array so that there are no null spaces/gaps.
    */
    private void shiftArray() {
        Employee [] shiftedArray = new Employee[empList.length];
        int count = 0;
        for(int i = 0; i < empList.length; i++) {
            if(empList[i] != null) {
                shiftedArray[count] = empList[i];
            }
            else{
                count--;
            }
            count++;
        }
        empList = shiftedArray;
    }
    
    /**
    Prints the earning statements for employees based on ordering in the array.
    */
    public void print(){
        shiftArray();
        for(int i = 0; i < empList.length; i++) {
            if(empList[i] == null) {
                continue;
            }
            PayrollController.getText(empList[i].toString() + '\n');
        }
    }
    
    /**
    Prints earning statements for employees alphabetically organized by Department.
    */
    public void printByDepartment(){
        sortByDept();
        print();
    }
    
    /**
    Prints earning statements for employees based on date hired (ascending).
    */
    public void printByDate(){
        sortByDate();
        print();
    }
    
    /**
    Sorts employee list array by department in alphabetical order via selection sort.
    */
    private void sortByDept(){
        shiftArray();
        for(int i = 0; i < empList.length-1; i++){

            Employee oldestEmployee = empList[i];
            int oldestEmployeeIndex = i;

            for(int j = i + 1; j < empList.length; j++){
                if(empList[j] == null){
                    break;
                }

                Employee iterator = empList[j];
                if(deptCheck(iterator, oldestEmployee)){
                    oldestEmployeeIndex = j;
                    oldestEmployee = empList[j];
                }
            }
            Employee swap = empList[oldestEmployeeIndex];
            empList[oldestEmployeeIndex] = empList[i];
            empList[i] = swap;
        }
    }
    /**
    Checks if an employee (first) has a department that comes lexicographically before another employee's (second).
    @param first original employee to compare departments against
    @param second comparator employee against first.
    @return true if the first employee's department is before the second
            employee's department, false if not.
    */
    private boolean deptCheck(Employee first, Employee second){
        String firstDept = first.getProfile().getDepartment();
        String secondDept = second.getProfile().getDepartment();

        int alphabetOrder = firstDept.compareTo(secondDept);
        if(alphabetOrder <= 0){
            return true;
        }
        return false;
    }
    /**
    Helper method that compares the hire dates of two employees.
    @param  first   iterative employee being compared to current oldest
                    employee.
    @param  second  current oldest employee found so far in array.
    @return true    true if first is older than second, false otherwise.
    */
    private boolean isOlder(Employee first, Employee second){
        final int firstYear = first.getProfile().getDateHired().getYear();
        final int firstMonth = first.getProfile().getDateHired().getMonth();
        final int firstDay = first.getProfile().getDateHired().getDay();

        final int secondYear = second.getProfile().getDateHired().getYear();
        final int secondMonth = second.getProfile().getDateHired().getMonth();
        final int secondDay = second.getProfile().getDateHired().getDay();

        if(firstYear < secondYear){
            return true;
        }
        else if(firstYear == secondYear){
            if(firstMonth < secondMonth){
                return true;
            }
            else if(firstMonth == secondMonth){
                if(firstDay < secondDay){
                    return true;
                }
                else if(firstDay == secondDay){
                    //sort alphabetically if exact same hire date
                    final String firstName = first.getProfile().getName();
                    final String secondName = second.getProfile().getName();

                    int alphabetOrder = firstName.compareTo(secondName);

                    if(alphabetOrder < 0){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
    Sorts Employee array by Hire Date in ascending order via selection sort.
    */
    private void sortByDate() {

        for(int i = 0; i < empList.length - 1; i++){

            Employee oldestEmp = empList[i];
            int oldestEmpIndex = i;

            for(int j = i + 1; j < empList.length; j++){
                if(empList[j] == null){
                    break;
                }

                Employee iterator = empList[j];
                if(isOlder(iterator, oldestEmp)){
                    oldestEmpIndex = j;
                    oldestEmp = empList[j];
                }
            }
            Employee swap = empList[oldestEmpIndex];
            empList[oldestEmpIndex] = empList[i];
            empList[i] = swap;
        }
    }
    
    /**
    Gets the data from all the employees in the comapny and 
    sends them to the controler to become a string to be exported.
    */
    public void exportDatabase() {
        shiftArray();
        for(int i = 0; i < empList.length; i++) {
            if(empList[i] == null) {
                continue;
            }
            PayrollController.getText(empList[i].toString() + '\n');
        }
    }
    
}
