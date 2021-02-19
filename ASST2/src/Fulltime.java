import java.text.DecimalFormat;
/**
Extension of employee class, allows for creation of a fulltime employee.
Methods included allow manipulation of annual salary, as well as a way to calculate payment per pay period.
@author Craig Li, Prerak Patel
 */
public class Fulltime extends Employee{

    private int annualSalary = 0; 
    private static final double PAYPERIODSPERYEAR = 26;
 
    /**
    Gets the Annual salary of a fulltime employee.
    @return the annualSalary
    */
    public int getAnnualSalary() {
        return annualSalary;
    }

    /**
    Sets the annual salary of a fulltime employee.
    @param annualSalary the annualSalary to set
    */
    public void setAnnualSalary(int annualSalary) {
        this.annualSalary = annualSalary;
    }
    
    /**
    Creates a new fulltime employee based on a given profile.
    @param profile provided profile object containing information about
                    the fulltime employee.
    */
    public Fulltime(Profile Profile) {
        super(Profile);
    }
    
    /**
    Creates a formatted string representation of a fulltime employee's information.
    */
    @Override
    public String toString(){
        DecimalFormat money = new DecimalFormat("#,##0.00");
        String employeeInfo = super.toString() +
                            "::FULL TIME::Annual Salary $" 
                            + money.format(annualSalary);
        return employeeInfo;
    }
    
    /**
    Checks if an object has the same profile information as a fulltime employee.
    @param obj Object being compared to a fulltime employee.
    */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Fulltime) {
            Fulltime employee = (Fulltime) obj;
            if(this.getProfile().equals(employee.getProfile())) {
                return true;
            }
        }
        return false;
    }
    
    /**
    Calculates a full time employee's payment per pay period.
    */
    @Override
    public void calculatePayment(){
        this.setPayment(annualSalary / PAYPERIODSPERYEAR);
    }
    
}