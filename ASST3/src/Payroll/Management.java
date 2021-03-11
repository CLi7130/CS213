package Payroll;
import java.text.DecimalFormat;
/**
Extension of Employee and Fulltime classes.
Allows creation of a management employee with specific bonuses and roles provided.
@author Craig Li, Prerak Patel
*/
public class Management extends Fulltime{
    private int role = 0;
    private double compensation = 0;
    private static final double PAY_PERIODS_PER_YEAR = 26;
    private static final int MANAGER_COMPENSATION = 5000;
    private static final int DPT_HEAD_COMPENSATION = 9500;
    private static final int DIRECTOR_COMPENSATION = 12000;
    private static final int MANAGER = 1;
    private static final int DPT_HEAD = 2;
    private static final int DIRECTOR = 3;
    
    /**
    Gets the management role of a management employee.
    @return role    The role of the management employee.
    */
    public int getRole() {
        return role;
    }

    /**
    Sets the management role of a management employee,and its additional compensation.
    @param role    The management role to set.
    */
    public void setRole(int role) {
        this.role = role;
        setComp();
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
        }
        else if(role == DPT_HEAD){
            title += "Department Head";
        }
        else if(role == DIRECTOR){
            title += "Director";
        }
        String employeeInfo = super.toString() + "::" + title 
                                + " Compensation $" 
                                + money.format(compensation);
        return employeeInfo;
    }
    
    /**
    Checks to see if a given object has the same profile as a managment employee.
    @param obj    Object whose profile is compared to a manager's profile.
    @return true if the object is a Management employee, false if not.
    */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Management) {
            Management employee = (Management) obj;
            if(this.getProfile().equals(employee.getProfile())) {
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
        this.setPayment(this.getPayment() + compensation);
    }
    /**
    Sets Management compensation depending on role.
     */
    private void setComp(){
        if(role == MANAGER){
            compensation = MANAGER_COMPENSATION / PAY_PERIODS_PER_YEAR;
        }
        else if(role == DPT_HEAD){
            compensation = DPT_HEAD_COMPENSATION / PAY_PERIODS_PER_YEAR;
        }
        else if(role == DIRECTOR){
            compensation = DIRECTOR_COMPENSATION / PAY_PERIODS_PER_YEAR;
        }
    }

}