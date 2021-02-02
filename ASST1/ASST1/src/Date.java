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
    private boolean isLeapYear(int testYear){
        int QUADRENNIAL = 4;
        int CENTENNIAL = 100;
        int QUATERCENTENNIAL = 400;

        if(testYear % QUADRENNIAL == 0){
            if(testYear % CENTENNIAL == 0){
                if(testYear % QUATERCENTENNIAL != 0){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public boolean isValid(){
        int monthMax = 12;
        int yearMin = 1900;
        int yearMax = Calendar.getInstance().get(Calendar.YEAR);

        int daysPerMonthMax = 0;
        int thirtyOneDays = 31;
        int thirtyDays = 30;
        int twentyEightDays = 28;
        int twentyNineDays = 29;

        int testMonth = this.getMonth();
        int testYear = this.getYear();
        int testDay = this.getDay();
        boolean isLeapYear = false;

        if(testMonth < 1 || testMonth > monthMax){
            return false;
        }
        if(testYear < yearMin || testYear > yearMax){
            return false;
        }

        int JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC;
        JAN = 1;
        FEB = 2;
        MAR = 3;
        APR = 4;
        MAY = 5;
        JUN = 6;
        JUL = 7;
        AUG = 8;
        SEP = 9;
        OCT = 10;
        NOV = 11;
        DEC = 12;

        int[] monthsWith31Days = {JAN, MAR, MAY, JUL, AUG, OCT, DEC};
        int[] monthsWith30Days = {APR, JUN, SEP, NOV};

        if(testMonth == FEB){
            isLeapYear = isLeapYear(testYear);

            if(isLeapYear){
                daysPerMonthMax = twentyNineDays;
            }
            else{
                daysPerMonthMax = twentyEightDays;
            }
        }

        for(int i = 0; i < monthsWith31Days.length; i++){
            if(testMonth == monthsWith31Days[i]){
                daysPerMonthMax = thirtyOneDays;
                break;
            }
        }
        for(int i = 0; i < monthsWith30Days.length; i++){
            if(testMonth == monthsWith30Days[i]){
                daysPerMonthMax = thirtyDays;
                break;
            }
        }

        if(testDay < 1 || testDay > daysPerMonthMax){
            return false;
        }

        return true;
    }



//test driver, delete before submitting
public static void main(String[] args){
    Date testDate = new Date();

    String currDate = new String("");
    currDate = currDate + testDate.month + "/" + testDate.day + "/" + testDate.year;

    String testDate1 = new String("13/1/2000");
    String testDate2 = new String("1/32/2000");
    String testDate3 = new String("1/1/1899");

    String leapYear1 = new String("2/29/1988");
    String leapYear2 = new String("2/29/2000");

    String invalidLeap1 = new String("2/29/1989");
    String invalidLeap2 = new String("2/29/1900");

    Date monthCheck = new Date(testDate1);
    Date dayCheck = new Date(testDate2);
    Date yearCheck = new Date(testDate3);

    Date validLeapCheck1 = new Date(leapYear1);
    Date validLeapCheck2 = new Date(leapYear2);

    Date invalidLeapCheck1 = new Date(invalidLeap1);
    Date invalidLeapCheck2 = new Date(invalidLeap2);

    System.out.println("Current Date: " + currDate);
    System.out.println("Current Date Valid: " + testDate.isValid() + ", Expected: true");
    System.out.println();

    System.out.println("monthCheck: " + testDate1);
    System.out.println("monthCheck Valid: " + monthCheck.isValid() + ", Expected: False");
    System.out.println();

    System.out.println("dayCheck: " + testDate2);
    System.out.println("dayCheck Valid: " + dayCheck.isValid() + ", Expected: False");
    System.out.println();

    System.out.println("yearCheck: " + testDate3);
    System.out.println("yearCheck valid: " + yearCheck.isValid() + ", Expected: False");
    System.out.println();

    System.out.println("leap1: " + leapYear1);
    System.out.println("leap1 valid: " + validLeapCheck1.isValid() + ", Expected: True");
    System.out.println();

    System.out.println("leap2: " + leapYear2);
    System.out.println("leap2 valid: " + validLeapCheck2.isValid() + ", Expected: True");
    System.out.println();

    System.out.println("invalidLeap1: " + invalidLeap1);
    System.out.println("invalidLeap1 valid: " + invalidLeapCheck1.isValid() + ", Expected: False");
    System.out.println();

    System.out.println("invalidLeap2: " + invalidLeap2);
    System.out.println("invalidLeap2 valid: " + invalidLeapCheck2.isValid() + ", Expected: False");
    System.out.println();



}

}