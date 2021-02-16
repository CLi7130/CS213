/*
    NOTES/TODO
    *****Delete upon final formatting pass*****

    - extends Employee class
    - includes specific data and operations to Full Time employee


*/

public class Fulltime extends Employee{
    private int annualSalary = 0; 
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
        String employeeInfo = super.toString() + /*payment goes here*/ 
                            "::FULL TIME::Annual Salary $" + annualSalary;
        return employeeInfo;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Fulltime) {
            Fulltime employee = (Fulltime) obj;
            if(profile.equals(employee.profile)) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    @Override
    public void calculatePayment(){
        //do not change method signature
        //reuse code in superclass whenever possible

    }
}