package Payroll;
/**
Holds all of the basic, unique information about an employee, including name, date hired, and department.
Other methods in this class allow these fields to be changed based on user specification in the PayrollProcessing class.
@author Craig Li, Prerak Patel
*/

public class Profile{
    private String name; //employee's name in the form "lastname,firstname"
    private String department; //department code: CS, ECE, IT
    private Date dateHired;

    /**
    Gets the name of an employee via their profile.
    @return name    Name of the employee.
    */
    public String getName() {
        return name;
    }
    
    /**
    Sets the name of an employee via their profile.
    @param name    String containing the employee's name.
    */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
    Gets the department the employee works in.
    @return department    The department the employee works in.
    */
    public String getDepartment() {
        return department;
    }
    
    /**
    Sets the department the employee works in.
    @param department    String containing the department the employee works in.
    */
    public void setDepartment(String department) {
        this.department = department;
    }
    
    /**
    Gets the hire date of an employee.
    @return dateHired    Date object containing an employee's hire date.
    */
    public Date getDateHired() {
        return dateHired;
    }
    
    /**
    Sets the date hired of an employee.
    @param dateHired    Date object containing the hire date of an employee.
    */
    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }
    
    /**
    Creates a formatted string containing the employee's name, department, and date hired.
    @return employeeInfo    String containing employee's name, department and
                            hire date.
    */
    @Override
    public String toString(){
        String employeeInfo = name + "::" + department 
                            + "::" + dateToString(dateHired);
        return employeeInfo;
    }
    
    /**
    Checks if a given object's profile is the same as an employee's profile.
    Compares name, date hired, and department.
    @param obj    Object to be compared to an employee.
    @return isEqual    boolean value that is true if the two profiles have the
                       same name, date hired, and department, false if they do not.
    */
    @Override
    public boolean equals(Object obj){
        boolean isEqual = false;
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            String subjectDate = dateToString(dateHired);
            String comparandDate = dateToString(profile.getDateHired());

            if(name.equals(profile.getName())
                && department.equals(profile.getDepartment())
                && subjectDate.equals(comparandDate))
            {
                isEqual = true;
            }
        }
        return isEqual;
    }
    
    /**
    Method returns a formatted string representing a date object.
    @param  date    date object that needs to be represented as a String.
    @return formattedDate   string representation of date object input.
    */
    private static String dateToString(Date date){
        String formattedDate = "";
        final String formatSpacer = "/";

        formattedDate += Integer.toString(date.getMonth());
        formattedDate += formatSpacer;
        formattedDate += Integer.toString(date.getDay());
        formattedDate += formatSpacer;
        formattedDate += Integer.toString(date.getYear());

        return formattedDate;
    }

}
