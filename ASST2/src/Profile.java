public class Profile{

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

    private String name; //employee's name in teh form "lastname,firstname"
    private String department; //department code: CS, ECE, IT
    private Date dateHired;

    @Override
    public String toString(){

    }
    
    @Override
    public boolean equals(Object obj){
        //compare name, department and dateHired

    }

}
