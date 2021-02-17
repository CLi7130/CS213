import java.text.DecimalFormat;

/*
    NOTES/TODO
    *****Delete upon final formatting pass*****

    - defines common data and operations for all employee types;
    - each employee has a profile that uniquely identifies employee

    - DO NOT USE getClass() to check employee type.
        - use equals() method to check type
        
*/

public class Employee{
    Profile profile = new Profile();
    private double payment = 0;
    /**
     * @return the payment
     */
    public double getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(double payment) {
        this.payment = payment;
    }
    
    
    public Employee(Profile Profile) {
        profile = Profile;
    }
    
    @Override
    public String toString(){
        DecimalFormat money = new DecimalFormat("#,###.00");
        String employeeInfo = profile.toString() + "::Payment $" + 
                money.format(payment);
        return employeeInfo;
    }
    
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

    public void calculatePayment() {
        payment = 0;
    }

}