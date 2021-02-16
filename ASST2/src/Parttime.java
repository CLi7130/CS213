/*
    NOTES/TODO
    *****Delete upon final formatting pass*****

    - extends Employee class
    - includes specific data and operations to a part time employee


*/

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
        String employeeInfo = super.toString() + "::PART TIME::Hourly Rate $" 
                            + hourlyRate + "::Hours worked this period: " 
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

}

