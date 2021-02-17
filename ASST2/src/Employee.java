/*
    NOTES/TODO
    *****Delete upon final formatting pass*****

    - defines common data and operations for all employee types;
    - each employee has a profile that uniquely identifies employee

    - DO NOT USE getClass() to check employee type.
        - use equals() method to check type
        
*/

public class Employee{
    Profile profile = new Profile();
    
    public Employee(Profile Profile) {
        profile = Profile;
    }
    
    @Override
    public String toString(){
        String employeeInfo = profile.toString();
        //placeholder

        return employeeInfo;
    }
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Employee) {
            Employee employee = (Employee) obj;
            if(profile.equals(employee.profile)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void calculatePayment() {
        
    }

}