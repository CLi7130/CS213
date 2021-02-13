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
    private Employee[] emplist;
    private int numEmployee;
    private static final int GROWAMOUNT = 4;
    private static final int FAILCONDITION = -1;

    
    public Company() {
        numEmployee = 0;
        emplist = new Employee[GROWAMOUNT];
    }
    
    private int find(Employee employee){
        for(int i = 0; i < emplist.length; i++) {
            if(emplist[i] == null){
                continue;
            }
            if(emplist[i].equals(employee)) {
                return i;
            }
        }
        return FAILCONDITION;

    }
    
    private void grow(){
        Employee[] grow = new Employee[emplist.length + GROWAMOUNT];
        for(int i = 0; i < emplist.length; i++) {
            grow[i] = emplist[i];
        }
        emplist = grow;
    }
    
    public boolean add(Employee employee){
        numEmployee++;
        if(numEmployee > emplist.length) {
            grow();
        }
        emplist[numEmployee - 1] = employee;
        return true;//check the profile before adding
    }
    
    public boolean remove(Employee employee){
        final int index = find(employee);
        System.out.println(index);
        if(index >= 0) {
            emplist[index] = null;
            numEmployee--;
            return true;
        }
        return false;
    }
    
    public boolean setHours(Employee employee){
        return false;
        //set working hours for a part time

    }
    public void processPayments(){//process payments for all employees

    }
    
    private void shiftArray() {
        Employee [] shiftedBooks = new Employee[emplist.length];
        int count = 0;
        for(int i = 0; i < emplist.length; i++) {
            if(emplist[i] != null) {
                shiftedBooks[count] = emplist[i];
            }
            else{
                count--;
            }
            count++;
        }
        emplist = shiftedBooks;
    }
    
    public void print(){ //print earnings statements for all employees
        shiftArray();
        for(int i = 0; i < emplist.length; i++) {
            if(emplist[i] == null) { 
                continue;
            }
            System.out.println(emplist[i].toString());
        }
    }
    public void printByDepartment(){//print earnings statements by department

    }
    public void printByDate(){//print earning statements by date hired

    }


}
