import static org.junit.Assert.*;

import org.junit.Test;

/**
JUnit Testing for Company class, tests add and remove methods.
@author Craig Li, Prerak Patel
*/
public class CompanyTest {

    /**
    Test method for {@link Company#add(Employee)}.
    */
    @Test
    public void testAdd() {
        Company list = new Company();
        Profile profile1 = new Profile();
        Employee employee1 = new Employee(profile1);
        Date date1 = new Date("7/1/2020");
        profile1.setName("Jemarcus");
        profile1.setDepartment("CS");
        profile1.setDateHired(date1);

        Profile profile2 = new Profile();
        Parttime employee2 = new Parttime(profile2);
        Date date2 = new Date("9/23/1999");
        profile2.setName("Jose");
        profile2.setDepartment("IT");
        profile2.setDateHired(date2);
        
        Profile profile3 = new Profile();
        Fulltime employee3 = new Fulltime(profile3);
        Date date3 = new Date("10/5/2005");
        profile3.setName("Richard");
        profile3.setDepartment("ECE");
        profile3.setDateHired(date3);

        Profile profile4 = new Profile();
        Management employee4 = new Management(profile4);
        Date date4 = new Date("9/15/2001");
        profile4.setName("Jack");
        profile4.setDepartment("CS");
        profile4.setDateHired(date4);

        Profile profile5 = new Profile();
        Employee employee5 = new Employee(profile5);
        Date date5 = new Date("6/15/2010");
        profile5.setName("Lemarcus");
        profile5.setDepartment("IT");
        profile5.setDateHired(date5);
        
        //Test Case #1 testing add employee
        assertTrue(list.add(employee1)); 

        //Test Case #2 testing add a Parttime Employee
        assertTrue(list.add(employee2)); 

        //Test Case #3 testing add a Fulltime Employee
        assertTrue(list.add(employee3)); 

        //Test Case #4 testing add a Management Employee
        assertTrue(list.add(employee4)); 

        //Test Case #5 testing grow
        assertTrue(list.add(employee5)); 

        //Test Case #6 testing add an existing Employee
        assertFalse(list.add(employee1)); 

        //Test Case #7 testing add an existing Parttime Employee
        assertFalse(list.add(employee2)); 

        //Test Case #8 testing add an existing Fulltime Employee
        assertFalse(list.add(employee3)); 

        //Test Case #9 testing add an existing Management Employee
        assertFalse(list.add(employee4)); 

        //Test Case #10 remove null employee
        assertFalse(list.add(null)); 

    }

    /**
    Test method for {@link Company#remove(Employee)}.
    */
    @Test
    public void testRemove() {
        Company list = new Company();
        Profile profile1 = new Profile();
        Employee employee1 = new Employee(profile1);
        Date date1 = new Date("7/1/2020");
        profile1.setName("Jemarcus");
        profile1.setDepartment("CS");
        profile1.setDateHired(date1);

        Profile profile2 = new Profile();
        Parttime employee2 = new Parttime(profile2);
        Date date2 = new Date("9/23/1999");
        profile2.setName("Jose");
        profile2.setDepartment("IT");
        profile2.setDateHired(date2);

        Profile profile3 = new Profile();
        Fulltime employee3 = new Fulltime(profile3);
        Date date3 = new Date("10/5/2005");
        profile3.setName("Richard");
        profile3.setDepartment("ECE");
        profile3.setDateHired(date3);

        Profile profile4 = new Profile();
        Management employee4 = new Management(profile4);
        Date date4 = new Date("9/15/2001");
        profile4.setName("Jack");
        profile4.setDepartment("CS");
        profile4.setDateHired(date4);
        
        //Test Case #11 remove employee that is not in the list
        assertFalse(list.remove(employee1)); 

        //Test Case #12 remove Parttime employee that is not in the list
        assertFalse(list.remove(employee2)); 
        
        //Test Case #13 remove Fulltime employee that is not in the list
        assertFalse(list.remove(employee3)); 

        //Test Case #14 remove Management employee that is not in the list
        assertFalse(list.remove(employee4)); 

        //Test Case #15 remove general employee in the list
        list.add(employee1);
        assertTrue(list.remove(employee1)); 

        //Test Case #16 remove Parttime employee in the list
        list.add(employee2);
        assertTrue(list.remove(employee2)); 

        //Test Case #17 remove Fulltime employee in the list
        list.add(employee3);
        assertTrue(list.remove(employee3)); 

        //Test Case #18 remove Managemnt employee in the list
        list.add(employee4);
        assertTrue(list.remove(employee4));
        
        //Test Case #19 remove employee when list is empty
        assertFalse(list.remove(employee4)); 
    }

    /**
    Test method for {@link Company#setHours(Employee)}.
    */
    @Test
    public void testSetHours() {
        Company list = new Company();
        Profile profile1 = new Profile();
        Parttime employee1 = new Parttime(profile1);
        Date date1 = new Date("7/1/2020");
        profile1.setName("Jemarcus");
        profile1.setDepartment("CS");
        profile1.setDateHired(date1);

        Profile profile2 = new Profile();
        Employee employee2 = new Employee(profile2);
        Date date2 = new Date("9/23/1999");
        profile2.setName("Jose");
        profile2.setDepartment("IT");
        profile2.setDateHired(date2);

        Profile profile3 = new Profile();
        Parttime employee3 = new Parttime(profile3);
        Date date3 = new Date("10/5/2005");
        profile3.setName("Richard");
        profile3.setDepartment("ECE");
        profile3.setDateHired(date3);
        employee3.setHours(100);

        Profile profile4 = new Profile();
        Fulltime employee4 = new Fulltime(profile4);
        Date date4 = new Date("9/23/1989");
        profile4.setName("Kyle");
        profile4.setDepartment("IT");
        profile4.setDateHired(date4);

        Profile profile5 = new Profile();
        Management employee5 = new Management(profile5);
        Date date5 = new Date("10/5/2015");
        profile5.setName("Montgomery");
        profile5.setDepartment("ECE");
        profile5.setDateHired(date5);

        list.add(employee2);
        list.add(employee3);
        list.add(employee4);
        list.add(employee5);

        //Test Case #20 setHours for employee not in list
        assertFalse(list.setHours(employee1)); 

        //Test Case #21 setHours for Employee
        assertFalse(list.setHours(employee2)); 

        //Test Case #22 setHours for Parttime
        assertTrue(list.setHours(employee3)); 
        // make sure test case 22 actually worked
        assertEquals(employee3.getHours(), 100);

        //Test Case #23 setHours for Fulltime
        assertFalse(list.setHours(employee4)); 

        //Test Case #24 setHours for Management
        assertFalse(list.setHours(employee5)); 
    }

}
