import java.util.Calendar;


/*
TODO / NOTES 
     ** delete this section before submission**
    - you CANNOT add other data members to this class
    - you CANNOT use System.out in this class
    formatting for naming for leap year constants:
        public static final int QUADRENNIAL = 4;
        public static final int CENTENNIAL = 100;
        public static final int QUATERCENTENNIAL;
    isValid():
        - dates with a year less than 1900, or beyond today's date are invalid
        - Months with 31 days:
            - Jan, Mar, May, Jul, Aug, Oct, Dec
        - Months with 30 days:
            - Apr, Jun, Sept, Nov
        - February has 28 days in a normal year, and 29 days in a leap year
            - only need to check for leap year if month is feb/02
    Leap year guidelines:
        1. If year % 4 = 0
                - go to step 2
            else
                - go to step 5
        2. if year % 100
                - go to step 3
            else
                - go to step 4
        3. If year % 400
                - go to step 4
            else
                - go to step 5
        4. This is a leap year
        5. This is not a leap year
    potential edge cases for leap year testing: 1900
    - test cases for isValid are worth 10 points, follow test specification section in software development ground rules.
    TODO:
        - implement method headers for Date() and isValid()
*/

/**
    This class instantiates a Date object from a string, creating an object in 
    the format of mm/dd/yyyy.
    Also included is a method to test whether a date is valid given a range of before the year 1900 and the current date.
    @author Craig Li, Prerak Patel
 */
public class Date {
    private int year;
    private int month;
    private int day;

    public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Date(String date){
        //taking mm/dd/yyy and creating a date object
        //assumes correct input, need to use isValid() before using constructor
        String[] splitDateInput = date.split("/");

        this.setMonth(Integer.parseInt(splitDateInput[0]));
        this.setDay(Integer.parseInt(splitDateInput[1]));
        this.setYear(Integer.parseInt(splitDateInput[2]));
    }
    public Date(){
        //today's date
        Calendar currDate = Calendar.getInstance();
        this.setMonth(currDate.get(Calendar.MONTH) + 1);
        this.setDay(currDate.get(Calendar.DAY_OF_MONTH));
        this.setYear(currDate.get(Calendar.YEAR));

    }
    public boolean isValid(){
        return false;
        
    }


/*
//test driver, delete before submitting
public static void main(String[] args){
    Date testDate = new Date();
    System.out.print(testDate.month + "/");
    System.out.print(testDate.day + "/");
    System.out.print(testDate.year);
}
*/
}