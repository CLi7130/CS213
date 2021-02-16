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
        String employeeInfo = super.toString() + "::PART TIME::Hourly Rate $" 
                            + money.format(hourlyRate) + "::Hours worked this period: " 
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
        //do not change method signature
        //reuse code in superclass whenever possible

    }

    public static void main(String[] args){
        
        Profile pro1 = new Profile();
        Parttime test1 = new Parttime(pro1);

        pro1.setName("testName");
        pro1.setDepartment("CS");
        pro1.setDateHired(new Date());
        test1.setHourlyRate(60.75);
        test1.setHours(20);

        pro1.toString();
        
    }

}

