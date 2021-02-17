/*
    NOTES/TODO
    *****Delete upon final formatting pass*****

    - extends Employee class
    - includes specific data and operations to a part time employee


*/

import java.text.DecimalFormat;

public class Parttime extends Employee{
    
    public Parttime(Profile Profile) {
        super(Profile);
    }
    private int hours = 0;
    private double hourlyRate = 0;
    private double payment = 0;
    private static final int HOURSUNTILBONUS = 80;
    private static final double BONUSRATE = 1.5;
    
    public int getHours() {
        return hours;
    }
    public void setHours(int hours) {
        this.hours = hours;
    }
    /**
     * @return the hourlyRate
     */
    public double getHourlyRate() {
        return hourlyRate;
    }
    /**
     * @param hourlyRate the hourlyRate to set
     */
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
    @Override
    public String toString(){

        DecimalFormat money = new DecimalFormat("#,###.00");
        String employeeInfo = super.toString() + "::Payment $" 
                            + money.format(payment) 
                            + "::PART TIME::Hourly Rate $" 
                            + money.format(hourlyRate) 
                            + "::Hours worked this period: " 
                            + hours;
        return employeeInfo;
    }
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Parttime) {
            Parttime employee = (Parttime) obj;
            if(profile.equals(employee.profile)) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    @Override
    public void calculatePayment(){
        double paymentBonus = 0;
        if (hours > HOURSUNTILBONUS) {
            payment = HOURSUNTILBONUS * hourlyRate;
            paymentBonus = (hours - HOURSUNTILBONUS) * (BONUSRATE * hourlyRate);
            payment += paymentBonus;
        }
        else {
            payment = hours * hourlyRate;
        }
    }

}

