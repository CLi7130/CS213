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
    
    public Company() {
        numEmployee = 0;
        emplist = new Employee[4];
    }
    
    private int find(Employee employee){
        return 0;

    }
    private void grow(){

    }
    public boolean add(Employee employee){
        numEmployee++;
        emplist[numEmployee - 1] = employee;
        return true;//check the profile before adding
    }
    public boolean remove(Employee employee){
        return false;//maintain the original sequence

    }
    public boolean setHours(Employee employee){
        return false;
        //set working hours for a part time

    }
    public void processPayments(){//process payments for all employees

    }
    public void print(){ //print earnings statements for all employees
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
