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



public class Management extends Fulltime{
    private int role = 0;
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
        String employeeInfo = super.toString() + " " + role;
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
        //do not change method signature
        //reuse code in superclass whenever possible

    }

}