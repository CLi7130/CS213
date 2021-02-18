/**
 * 
 */


import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Craig Li, Prerak Patel
 *
 */
public class CompanyTest {

    /**
     * Test method for {@link Company#add(Employee)}.
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
        Employee employee2 = new Employee(profile2);
        Date date2 = new Date("9/23/1999");
        profile2.setName("Jose");
        profile2.setDepartment("IT");
        profile2.setDateHired(date2);
        Profile profile3 = new Profile();
        Employee employee3 = new Employee(profile3);
        Date date3 = new Date("10/5/2005");
        profile3.setName("Richard");
        profile3.setDepartment("ECE");
        profile3.setDateHired(date3);
        Profile profile4 = new Profile();
        Employee employee4 = new Employee(profile4);
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
        
        assertTrue(list.add(employee1)); //Test Case #1 testing add employee
        assertFalse(list.add(employee1)); //Test Case #2 testing add an existing employee
        list.add(employee2);
        list.add(employee3);
        list.add(employee4);
        assertTrue(list.add(employee5)); //Test Case #3 testing grow

    }

    /**
     * Test method for {@link Company#remove(Employee)}.
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
        
        assertFalse(list.remove(employee1)); //Test Case #4 remove employee that is not in the list
        list.add(employee1);
        assertTrue(list.remove(employee1)); //Test Case #5 remove employee in the list
        assertFalse(list.remove(null)); //Test Case #6 remove null employee
    }

    /**
     * Test method for {@link Company#setHours(Employee)}.
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

        list.add(employee2);
        list.add(employee3);
        assertFalse(list.setHours(employee1)); //Test Case #7 setHours for employee not in list
        assertFalse(list.setHours(employee2)); //Test Case #8 setHours for employee that is not Parttime
        assertTrue(list.setHours(employee3)); //Test Case #9 setHours for employee that is Parttime
        assertEquals(employee3.getHours(), 100); // make sure test case 9 actually worked
    }

}
