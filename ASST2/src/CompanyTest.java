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
        Profile profile = new Profile();
        Employee employee = new Employee(profile);
        Date date = new Date("7/1/2020");
        profile.setName("Jemarcus");
        profile.setDepartment("CS");
        profile.setDateHired(date);
        assertTrue(list.add(employee));
        //fail("Not yet implemented");
    }

    /**
     * Test method for {@link Company#remove(Employee)}.
     */
    @Test
    public void testRemove() {
        //fail("Not yet implemented");
    }

    /**
     * Test method for {@link Company#setHours(Employee)}.
     */
    @Test
    public void testSetHours() {
        //fail("Not yet implemented");
    }

}
