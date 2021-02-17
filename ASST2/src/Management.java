/*
    NOTES/TODO
    *****Delete upon final formatting pass*****

    - extends Fulltime class
        - includes specific data/operations to a full time employee
            with a management role.

    - need to create a JUnit test class
        - write test cases for:
            - calculatePayment()


*/

import java.text.DecimalFormat;

public class Management extends Fulltime{
    private int role = 0;
    private double compensation = 0;
    private static final double PAYMENTPERIOD = 1/26;
    private static final int MANAGERCOMPENSATION = 5000;
    private static final int DPTHEADCOMPENSATION = 95000;
    private static final int DIRECTORCOMPENSATION = 12000;
    private static final int manager = 1;
    private static final int dptHead = 2;
    private static final int director = 3;
    
    /**
     * @return the role
     */
    public int getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(int role) {
        this.role = role;
    }

    public Management(Profile Profile) {
        super(Profile);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String toString(){
        String role = new String("");
        DecimalFormat money = new DecimalFormat("#,###.00");
        int manager = 1;
        int dptHead = 2;
        int director = 3;

        int mgmtRole = this.getRole();
        if(mgmtRole == manager){
            role += "Manager";
        }
        else if(mgmtRole == dptHead){
            role += "Department Head";
        }
        else if(mgmtRole == director){
            role += "Director";
        }
        else{
            role += "INVALID";
        }

        String employeeInfo = super.toString() + "::" + role 
                                + " Compensation $" + money.format(compensation);//add management bonus here
        return employeeInfo;
    }
    
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Management) {
            Management employee = (Management) obj;
            if(profile.equals(employee.profile)) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    @Override
    public void calculatePayment(){
        super.calculatePayment();
        if(role == manager){
            compensation = MANAGERCOMPENSATION * PAYMENTPERIOD;
        }
        else if(role == dptHead){
            compensation = DPTHEADCOMPENSATION * PAYMENTPERIOD;
        }
        else if(role == director){
            compensation = DIRECTORCOMPENSATION * PAYMENTPERIOD;
        }

    }

}