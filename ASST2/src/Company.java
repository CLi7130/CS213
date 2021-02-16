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
import java.text.DecimalFormat;

public class Company{
    private Employee[] empList;
    private int numEmployee;
    private static final int GROWAMOUNT = 4;
    private static final int FAILCONDITION = -1;
    

    
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
        System.out.println(index);
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

    }
    public void printByDate(){
        //print earning statements by date hired, oldest first

    }

        //*******Delete Before Submission */
        public static void main(String[] args){
            Profile profile = new Profile();
            Parttime test1 = new Parttime(profile);
            profile.setDateHired(new Date());
            profile.setDepartment("CS");
            profile.setName("Smith,John");
            test1.setHourlyRate(50.20);
    
            Profile profile2 = new Profile();
            Parttime test2 = new Parttime(profile2);
            profile2.setDateHired(new Date());
            profile2.setDepartment("CS");
            profile2.setName("Smithers,John");
            test2.setHourlyRate(50.20);

            Profile mgmtTest1 = new Profile();
            Management mgmtEmpTest1 = new Management(mgmtTest1);
            mgmtTest1.setDateHired(new Date("1/1/2000"));
            mgmtTest1.setDepartment("ECE");
            mgmtTest1.setName("Jameson,J. Jonah");
            mgmtEmpTest1.setAnnualSalary(250000);
            mgmtEmpTest1.setRole(2);
    
            System.out.println(test1.toString());
            System.out.println(test2.toString());
            System.out.println("test1 is equal to test2: " 
                                + test1.equals(test2));

            System.out.println();
            System.out.println("testCompany:");
            Company testCompany = new Company();
            testCompany.add(test1);
            testCompany.add(test2);
            testCompany.add(mgmtEmpTest1);
            testCompany.print();
    

    
        }
}
