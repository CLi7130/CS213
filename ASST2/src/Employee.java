/**
Parent or super class of Parttime Fulltime and Management.
Instantiates a profile and payment
@author Craig Li, Prerak Patel
 */

import java.text.DecimalFormat;

/**
Defines common data/operations for all employee types, superclass.
Each new employee is given a profile that defines additional characteristics.
@author Craig Li, Prerak Patel
*/
public class Employee{
    Profile profile = new Profile();
    private double payment = 0;
    
    /**
    Gets the payment for an employee.
    @return the payment
    */
    public double getPayment() {
        return payment;
    }

    /**
    Sets payment rate for an employee.
    @param payment the payment to set
    */
    public void setPayment(double payment) {
        this.payment = payment;
    }
    
    /**
    Creates a new employee based on a given profile
    @param Profile    Profile object that contains information about an
                      employee.
    */
    public Employee(Profile Profile) {
        profile = Profile;
    }
    
    /**
    Converts an Employee's Profile information to a formatted String.
    */
    @Override
    public String toString(){
        DecimalFormat money = new DecimalFormat("#,##0.00");
        String employeeInfo = profile.toString() + "::Payment $" + 
                money.format(payment);
        return employeeInfo;
    }
    
    /**
    Checks if an Employee's prfile is the same as a given object's profile.
    @param obj    Object with a profile to be compared to an employee's profile.
    */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Employee) {
            Employee employee = (Employee) obj;
            if(profile.equals(employee.profile)) {
                return true;
            }
            return false;
        }
        return false;
    }
    /**
    Allows subclasses to calculate their own payments.
    */
    public void calculatePayment() {
        
    }

}