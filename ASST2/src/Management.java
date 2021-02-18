import java.text.DecimalFormat;
/**
Extension of Employee and Fulltime classes.
Allows creation of a management employee with specific bonuses and roles provided.
@author Craig Li, Prerak Patel
*/
public class Management extends Fulltime{
    private int role = 0;
    private double compensation = 0;
    private static final double PAYPERIODSPERYEAR = 26;
    private static final int MANAGERCOMPENSATION = 5000;
    private static final int DPTHEADCOMPENSATION = 9500;
    private static final int DIRECTORCOMPENSATION = 12000;
    private static final int MANAGER = 1;
    private static final int DPTHEAD = 2;
    private static final int DIRECTOR = 3;
    
    /**
    Gets the management role of a management employee.
    @return the management role
    */
    public int getRole() {
        return role;
    }

    /**
    Sets the management role of a management employee.
    @param role the role to set
    */
    public void setRole(int role) {
        this.role = role;
    }

    /**
    Creates a new employee based on a given profile
    @param Profile    Profile object that contains information about an
                      employee.
    */
    public Management(Profile Profile) {
        super(Profile);
    }
    
    /**
    Creates a formatted string that displays a management employee's information.
    Includes management title, as well as additional management compensation.
    @return employeeInfo    formatted string containing information about the
                            given management employee.
    */
    @Override
    public String toString(){
        String title = new String("");
        DecimalFormat money = new DecimalFormat("#,##0.00");

        if(role == MANAGER){
            title += "Manager";
            compensation = MANAGERCOMPENSATION / PAYPERIODSPERYEAR;
        }
        else if(role == DPTHEAD){
            title += "Department Head";
            compensation = DPTHEADCOMPENSATION / PAYPERIODSPERYEAR;
        }
        else if(role == DIRECTOR){
            title += "Director";
            compensation = DIRECTORCOMPENSATION / PAYPERIODSPERYEAR;
        }
        else{
            title += "INVALID";
        }

        String employeeInfo = super.toString() + "::" + title 
                                + " Compensation $" 
                                + money.format(compensation);
        return employeeInfo;
    }
    
    /**
    Checks to see if a given object has the same profile as a managment employee.
    @param obj Object whose profile is compared to a manager's profile.
    */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Management) {
            Management employee = (Management) obj;
            if(profile.equals(employee.profile)) {
                return true;
            }
        }
        return false;
    }
    
    /**
    Calculates the payment of a management employee.
    */ 
    @Override
    public void calculatePayment(){
        super.calculatePayment();
        //when calculate payment called before toString compensation = 0 still
        if(role == MANAGER){
            compensation = MANAGERCOMPENSATION / PAYPERIODSPERYEAR;
        }
        else if(role == DPTHEAD){
            compensation = DPTHEADCOMPENSATION / PAYPERIODSPERYEAR;
        }
        else if(role == DIRECTOR){
            compensation = DIRECTORCOMPENSATION / PAYPERIODSPERYEAR;
        }
        this.setPayment(this.getPayment() + compensation);
    }

}