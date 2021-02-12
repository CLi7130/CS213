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
    public void add(Profile Profile) {
        profile = Profile;
    }
    @Override
    public String toString(){
        String employeeInfo = new String("");
        //placeholder

        return profile.getName();
    }
    @Override
    public boolean equals(Employee employee){
    
        return false;
    }






}