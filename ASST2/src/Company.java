/*
    NOTES/TODO
    *****Delete upon final formatting pass*****

    - array based container class that implements employee database
        - array stores list of employees, including FT, PT, Management
        - initial capacity of 4, grows by 4 if full

    - cannot add additional instance variables or change method signatures
    - Can add additional methods

    - need to create a JUnit test class
        - write test cases for:
            - add()
            - remove()
            - setHours()


*/

public class Company{
    private Employee[] empList;
    private int numEmployee;
    private static final int GROWAMOUNT = 4;
    private static final int FAILCONDITION = -1;
    
    public int getNumEmployees(){
        return this.numEmployee;
    }
    
    public Company() {
        numEmployee = 0;
        empList = new Employee[GROWAMOUNT];
    }
    
    private int find(Employee employee){
        for(int i = 0; i < empList.length; i++) {
            if(empList[i] == null){
                continue;
            }
            if(empList[i].equals(employee)) {
                return i;
            }
        }
        return FAILCONDITION;

    }
    
    private void grow(){
        Employee[] grow = new Employee[empList.length + GROWAMOUNT];
        for(int i = 0; i < empList.length; i++) {
            grow[i] = empList[i];
        }
        empList = grow;
    }
    
    public boolean add(Employee employee){
        numEmployee++;
        if(numEmployee > empList.length) {
            grow();
        }
        empList[numEmployee - 1] = employee;
        return true;//check the profile before adding
    }
    
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
    
    public boolean setHours(Employee employee){
        if (employee instanceof Parttime) {
            final int index = find(employee);
            
            if (index >= 0) {
                ((Parttime) empList[index]).setHours(((Parttime) 
                        employee).getHours());
                //System.out.println(((Parttime) empList[index]).getHourlyRate());
                return true;
            }
            return false;
        }
        return false;
        //set working hours for a part time

    }
    public void processPayments(){//process payments for all employees
        for(int i = 0; i < numEmployee; i++) {
            empList[i].calculatePayment();
        }
    }
    
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
    
    public void print(){ //print earnings statements for all employees
        shiftArray();
        for(int i = 0; i < empList.length; i++) {
            if(empList[i] == null) { 
                continue;
            }
            System.out.println(empList[i].toString());
        }
    }
    public void printByDepartment(){//print earnings statements by department
        sortByDept();
        print();
    }
    public void printByDate(){
        //print earning statements by date hired, oldest first
        sortByDate();
        print();

    }
    /**
    Sorts empList array by hire date from oldest to newest.
    Assumes there are only three departments (CS, ECE, IT).
    */
    private void sortByDept(){
        shiftArray();

        Employee[] CS = new Employee[empList.length];
        Employee[] ECE = new Employee[empList.length];
        Employee[] IT = new Employee[empList.length];
        int CScount = 0;
        int ECEcount = 0;
        int ITcount = 0;

        Employee[] sortedByDept = new Employee[empList.length];

        for(int i = 0; i < empList.length; i++){
            if(empList[i] == null){
                continue;
            }
            if(empList[i].profile.getDepartment().equals("CS")){
                CS[CScount] = empList[i];
                CScount++;
            }
            else if(empList[i].profile.getDepartment().equals("ECE")){
                ECE[ECEcount] = empList[i];
                ECEcount++;
            }
            else{
                IT[ITcount] = empList[i];
                ITcount++;
            }
        }
        for(int i = 0; i < CScount; i++){
            sortedByDept[i] = CS[i];
        }
        for(int i = 0; i < ECEcount; i++){
            sortedByDept[i + CScount] = ECE[i];
        }
        for(int i = 0; i < ITcount; i++){
            sortedByDept[i + CScount + ECEcount] = IT[i];
        }

        empList = sortedByDept;
    }
    /**
    Helper method that compares the hire dates of two employees.
    @param  first   iterative employee being compared to current oldest
                    employee.
    @param  second  current oldest employee found so far in array.
    @return true    true if first is older than second, false otherwise.
    */
    private boolean isOlder(Employee first, Employee second){
        final int firstYear = first.profile.getDateHired().getYear();
        final int firstMonth = first.profile.getDateHired().getMonth();
        final int firstDay = first.profile.getDateHired().getDay();

        final int secondYear = second.profile.getDateHired().getYear();
        final int secondMonth = second.profile.getDateHired().getMonth();
        final int secondDay = second.profile.getDateHired().getDay();

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
                    final String firstName = first.profile.getName();
                    final String secondName = second.profile.getName();

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

        for(int i = 0; i < empList.length-1; i++){

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
    /*
        //*******Delete Before Submission 
    public static void main(String[] args){
        Profile profile = new Profile();
        Parttime test1 = new Parttime(profile);
        profile.setDateHired(new Date());
        profile.setDepartment("IT");
        profile.setName("Smith,John");
        test1.setHourlyRate(50.20);
        test1.setHours(101);

        Profile profile2 = new Profile();
        Parttime test2 = new Parttime(profile2);
        profile2.setDateHired(new Date("4/25/2000"));
        profile2.setDepartment("CS");
        profile2.setName("Smithers,John");
        test2.setHourlyRate(50.20);
        test2.setHours(40);

        Profile mgmtTest1 = new Profile();
        Management mgmt1 = new Management(mgmtTest1);
        mgmtTest1.setDateHired(new Date("3/2/2000"));
        mgmtTest1.setDepartment("ECE");
        mgmtTest1.setName("Jameson,J. Jonah");
        mgmt1.setAnnualSalary(250000);
        mgmt1.setRole(2);

        Profile pro3 = new Profile();
        Management mgmt2 = new Management(pro3);
        pro3.setDateHired(new Date("5/3/2000"));
        pro3.setDepartment("ECE");
        pro3.setName("Chang,Lily");
        mgmt2.setAnnualSalary(800000);
        mgmt2.setRole(3);

        Profile pro4 = new Profile();
        Management mgmt3 = new Management(pro4);
        pro4.setDateHired(new Date("1/1/1900"));
        pro4.setDepartment("IT");
        pro4.setName("Venopugal,Sesh");
        mgmt3.setAnnualSalary(475000);
        mgmt3.setRole(1);

        System.out.println(test1.toString());
        System.out.println(test2.toString());
        System.out.println("test1 is equal to test2: " 
                            + test1.equals(test2));

        System.out.println();
        System.out.println("testCompany:");
        Company testCompany = new Company();
        testCompany.add(test1);
        testCompany.add(test2);
        testCompany.add(mgmt1);
        testCompany.add(mgmt3);
        testCompany.add(mgmt2);
        testCompany.print();
        System.out.println();

        System.out.println("Sort By Dept:");
        testCompany.printByDepartment();
        System.out.println();

        System.out.println("Sort By Date:");
        testCompany.printByDate();
        System.out.println();


        testCompany.processPayments();
        testCompany.print();
        System.out.println();


    }*/
}
