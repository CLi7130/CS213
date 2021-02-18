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
        employee1.calculatePayment();
        employee2.calculatePayment();
        employee3.calculatePayment();
        assertEquals(employee1.getPayment(), 3461.54, 0.01); // make sure test case 9 actually worked
        assertEquals(employee2.getPayment(), 3634.61, 0.01); // make sure test case 9 actually worked
        assertEquals(employee3.getPayment(), 3730.77, 0.01); // make sure test case 9 actually worked
        
        
        
    }

}
