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
    private static final double PAYPERIODSPERYEAR = 26;
    private static final int MANAGERCOMPENSATION = 5000;
    private static final int DPTHEADCOMPENSATION = 9500;
    private static final int DIRECTORCOMPENSATION = 12000;
    private static final int MANAGER = 1;
    private static final int DPTHEAD = 2;
    private static final int DIRECTOR = 3;
    
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
        DecimalFormat money = new DecimalFormat("#,##0.00");

        int mgmtRole = this.getRole();
        if(mgmtRole == MANAGER){
            role += "Manager";
            compensation = MANAGERCOMPENSATION / PAYPERIODSPERYEAR;
        }
        else if(mgmtRole == DPTHEAD){
            role += "Department Head";
            compensation = DPTHEADCOMPENSATION / PAYPERIODSPERYEAR;
        }
        else if(mgmtRole == DIRECTOR){
            role += "Director";
            compensation = DIRECTORCOMPENSATION / PAYPERIODSPERYEAR;
        }
        else{
            role += "INVALID";
        }

        String employeeInfo = super.toString() + "::" + role 
                                + " Compensation $" 
                                + money.format(compensation);
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
        this.setPayment(this.getPayment() + compensation);
    }

}