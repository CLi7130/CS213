/*
    NOTES/TODO
    *****Delete upon final formatting pass*****

    - extends Employee class
    - includes specific data and operations to Full Time employee


*/
import java.text.DecimalFormat;

public class Fulltime extends Employee{

    private int annualSalary = 0; 
    private static final double PAYPERIODSPERYEAR = 26;
 
    /**
     * @return the annualSalary
     */
    public int getAnnualSalary() {
        return annualSalary;
    }

    /**
     * @param annualSalary the annualSalary to set
     */
    public void setAnnualSalary(int annualSalary) {
        this.annualSalary = annualSalary;
    }

    public Fulltime(Profile Profile) {
        super(Profile);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String toString(){
        DecimalFormat money = new DecimalFormat("#,###.00");
        String employeeInfo = super.toString() +
                            "::FULL TIME::Annual Salary $" 
                            + money.format(annualSalary);
        return employeeInfo;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Fulltime) {
            Fulltime employee = (Fulltime) obj;
            if(profile.equals(employee.profile)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void calculatePayment(){
        this.setPayment(annualSalary / PAYPERIODSPERYEAR);
    }
}