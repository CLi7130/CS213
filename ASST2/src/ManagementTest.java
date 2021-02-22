/**
 * 
 */


import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Craig Li, Prerak Patel
 *
 */
public class ManagementTest {

    /**
     * Test method for {@link Management#calculatePayment()}.
     */
    @Test
    public void testCalculatePayment() {
        Profile profile1 = new Profile();
        Management employee1 = new Management(profile1);
        Date date1 = new Date("7/1/2020");
        profile1.setName("Jemarcus");
        profile1.setDepartment("CS");
        profile1.setDateHired(date1);
        employee1.setRole(1);
        employee1.setAnnualSalary(85000);

        Profile profile2 = new Profile();
        Management employee2 = new Management(profile2);
        Date date2 = new Date("9/23/1999");
        profile2.setName("Jose");
        profile2.setDepartment("IT");
        profile2.setDateHired(date2);
        employee2.setRole(2);
        employee2.setAnnualSalary(85000);

        Profile profile3 = new Profile();
        Management employee3 = new Management(profile3);
        Date date3 = new Date("10/5/2005");
        profile3.setName("Richard");
        profile3.setDepartment("ECE");
        profile3.setDateHired(date3);
        employee3.setRole(3);
        employee3.setAnnualSalary(85000);

        Profile profile4 = new Profile();
        Management employee4 = new Management(profile4);
        Date date4 = new Date("7/1/2018");
        profile4.setName("Kyle");
        profile4.setDepartment("CS");
        profile4.setDateHired(date4);
        employee4.setRole(1);

        Profile profile5 = new Profile();
        Management employee5 = new Management(profile5);
        Date date5 = new Date("9/23/1989");
        profile5.setName("David");
        profile5.setDepartment("IT");
        profile5.setDateHired(date5);
        employee5.setRole(2);

        Profile profile6 = new Profile();
        Management employee6 = new Management(profile6);
        Date date6 = new Date("10/5/2015");
        profile6.setName("Peter");
        profile6.setDepartment("ECE");
        profile6.setDateHired(date6);
        employee6.setRole(3);

        Profile profile7 = new Profile();
        Management employee7 = new Management(profile7);
        Date date7 = new Date("9/23/1979");
        profile7.setName("Eric");
        profile7.setDepartment("IT");
        profile7.setDateHired(date7);
        employee7.setAnnualSalary(85000);
        
        Profile profile8 = new Profile();
        Management employee8 = new Management(profile8);
        Date date8 = new Date("10/15/2015");
        profile8.setName("Steven");
        profile8.setDepartment("ECE");
        profile8.setDateHired(date8);
        
        employee1.calculatePayment();
        employee2.calculatePayment();
        employee3.calculatePayment();
        employee4.calculatePayment();
        employee5.calculatePayment();
        employee6.calculatePayment();
        employee7.calculatePayment();
        employee8.calculatePayment();
        assertEquals(employee1.getPayment(), 3461.54, 0.01); //Test Case #1 payment for Manager
        assertEquals(employee2.getPayment(), 3634.61, 0.01); //Test Case #2 payment for Department Head
        assertEquals(employee3.getPayment(), 3730.77, 0.01); //Test Case #3 payment for Director
        assertEquals(employee4.getPayment(), 192.31, 0.01); //Test Case #4 payment for Manager with no annualSalary
        assertEquals(employee5.getPayment(), 365.38, 0.01); //Test Case #5 payment for Department Head with no annualSalary
        assertEquals(employee6.getPayment(), 461.54, 0.01); //Test Case #6 payment for Director with no annualSalary
        assertEquals(employee7.getPayment(), 3269.23, 0.01); //Test Case #7 payment for no role with annualSalary
        assertEquals(employee8.getPayment(), 0, 0.01); //Test Case #8 payment for no role with no annualSalary
        
    }

}
