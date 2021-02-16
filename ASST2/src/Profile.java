public class Profile{
    private String name; //employee's name in teh form "lastname,firstname"
    private String department; //department code: CS, ECE, IT
    private Date dateHired;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getDateHired() {
        return dateHired;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }
    
    @Override
    public String toString(){
        String employeeInfo = name + "::" + department + "::" + dateHired.toString() 
            + "::";
        return employeeInfo;
    }
    
    @Override
    public boolean equals(Object obj){
        //compare name, department and dateHired
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            if(name.equals(profile.getName())) {
                
                return true;
            }
            return false;
        }
        return false;
    }

}
