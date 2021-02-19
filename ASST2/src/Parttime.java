import java.text.DecimalFormat;
/**
Extends the employee class and includes specific data and operations to a parttime employee.
@author Craig Li, Prerak Patel
*/
public class Parttime extends Employee{
    
    private int hours = 0;
    private double hourlyRate = 0;
    private static final int HOURSUNTILBONUS = 80;
    private static final double BONUSRATE = 1.5;

    /**
    Creates a new parttime employee based on a given profile.
    @param Profile    Profile containing information about a parttime employee.
    */
    public Parttime(Profile Profile) {
        super(Profile);
    }
    
    /**
    Gets the hours a parttime employee has worked during this pay period.
    */
    public int getHours() {
        return hours;
    }
    
    /**
    Sets the hours a parttime employee has worked during this pay period.
    @param hours    provided number of hours worked by the employee.
    */
    public void setHours(int hours) {
        this.hours = hours;
    }
    
    /**
    Gets the hourly wage of a parttime employee.
    @return hourlyRate    The hourly pay rate of a parttime employee.
    */
    public double getHourlyRate() {
        return hourlyRate;
    }
    
    /**
    Sets the parttime employee's hourly pay rate.
    @param hourlyRate    the hourlyRate to set
    */
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    /**
    Creates a formatted string representation of a parttime employee's information.
    @return employeeInfo    String containing a parttime employee's information.
    */
    @Override
    public String toString(){
        DecimalFormat money = new DecimalFormat("#,##0.00");
        String employeeInfo = super.toString()
                            + "::PART TIME::Hourly Rate $" 
                            + money.format(hourlyRate) 
                            + "::Hours worked this period: " 
                            + hours;
        return employeeInfo;
    }
    
    /**
    Checks if an object has the same profile as a parttime employee.
    @param obj    the object whose profile is being compared to a parttime
                  employee's profile.
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Parttime) {
            Parttime employee = (Parttime) obj;
            if(profile.equals(employee.profile)) {
                return true;
            }
        }
        return false;
    }
    
    /**
    Calculates and sets the payment of a parttime employee based on hours worked and hourlyRate.
    Will provide payment at 1.5x normal rate above 80 hours per pay period.
    */
    @Override
    public void calculatePayment(){
        double paymentBonus = 0;
        if (hours > HOURSUNTILBONUS) {
            this.setPayment(HOURSUNTILBONUS * hourlyRate);
            paymentBonus = (hours - HOURSUNTILBONUS) * (BONUSRATE * hourlyRate);
            this.setPayment(this.getPayment() + paymentBonus);
        }
        else {
            this.setPayment(hours * hourlyRate);
        }
    }

}

