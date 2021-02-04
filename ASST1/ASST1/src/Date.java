import java.util.Calendar;


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

    /**
    Gets day book was published.
    @return : int containing day book was published.
     */
    public int getDay() {
		return this.day;
    }
    /**
    Sets day published to input value.
     * @param day : specified day book was published.
     */
	public void setDay(int day) {
		this.day = day;
    }
    /**
    Gets month book was published.
    @return : int containing month book was published.
     */
	public int getMonth() {
		return this.month;
    }
    /**
    Sets month that book was published to specified input.
    @param month : specified month book was published.
     */
	public void setMonth(int month) {
		this.month = month;
    }
    /**
    Gets year book was published.
    @return : int containing year book was published.
     */
	public int getYear() {
		return this.year;
    }
    /**
    Sets year that book was published to specified input.
    @param year : specified year book was published.
     */
	public void setYear(int year) {
		this.year = year;
    }
    /**
    Function splits an input string of format MM/DD/YYYY and sets each respective date component.
    @param date : selected/provided publishing date in format MM/DD/YYYY
     */
	public Date(String date){
        String[] splitDateInput = date.split("/");
        int monthSplit = 0;
        int daySplit = 1;
        int yearSplit = 2;

        this.setMonth(Integer.parseInt(splitDateInput[monthSplit]));
        this.setDay(Integer.parseInt(splitDateInput[daySplit]));
        this.setYear(Integer.parseInt(splitDateInput[yearSplit]));
    }
    /**
    Initializes date to current date, sets year, month, day accordingly.
     */
    public Date(){
        Calendar currDate = Calendar.getInstance();
        this.setMonth(currDate.get(Calendar.MONTH) + 1);
        this.setDay(currDate.get(Calendar.DAY_OF_MONTH));
        this.setYear(currDate.get(Calendar.YEAR));
    }
    /**
    Determines whether a provided year (testYear) is a leap year.
    @param testYear : possible leap year
    @return Returns True if testYear is a leap year, false otherwise.
     */
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
    /**
    Checks if a provided date is valid based off of the Gregorian Calendar.  Also checks against current date.
    @return Returns true if date is valid, false otherwise, or if date is  beyond current date.
     */
    public boolean isValid(){
        Calendar currDate = Calendar.getInstance();
        int monthMax = 12;
        int yearMin = 1900;
        int yearMax = currDate.get(Calendar.YEAR);

        int currMonth = currDate.get(Calendar.MONTH) + 1;
        int currDay = currDate.get(Calendar.DAY_OF_MONTH);

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
        if(testYear == yearMax){
            if(testMonth > currMonth){
                return false;
            }
            else if(testMonth == currMonth){
                if(testDay > currDay){
                    return false;
                }
            }
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
    /**
     * Method returns a formatted string representing a date object.
     * For use only in testbed main.
     * @return : returns string representation of date object input.
     */
    private static String toString(Date date){
        String formattedDate = "";
        String formatSpacer = "/";

        formattedDate += Integer.toString(date.getMonth());
        formattedDate += formatSpacer;
        formattedDate += Integer.toString(date.getDay());
        formattedDate += formatSpacer;
        formattedDate += Integer.toString(date.getYear());

        return formattedDate;
    }



//testbed main as a driver to exercise isValid() method.
public static void main(String[] args){
    //**********Current Date Testing**********
    System.out.println("**********************");
    System.out.println("Current Date Testing:");
    Date testDate = new Date();
    System.out.println("Current Date: " + toString(testDate));
    System.out.println("isValid() returns: " + testDate.isValid());
    System.out.println("Expected: True");
    System.out.println();

    //**********Future Date Testing**********
    System.out.println("**********************");
    System.out.println("Future Date Testing:");
    Date futureDate = new Date();
    futureDate.setDay(futureDate.getDay() + 1);
    System.out.println("Future Date: " + toString(futureDate));
    System.out.println("isValid() returns: " + futureDate.isValid());
    System.out.println("Expected: False");
    System.out.println();  

    //**********Month Detection Testing**********
    System.out.println("**********************");
    System.out.println("Month Validity Testing:");
    System.out.println();

    Date testMonthMin = new Date("0/1/2000");
    System.out.println("Test - Month Minimum Edge: " + toString(testMonthMin));
    System.out.println("isValid() returns: " + testMonthMin.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testMonthMax = new Date("13/1/2000");
    System.out.println("Test - Month Maximum Edge: " + toString(testMonthMax));
    System.out.println("isValid() returns: " + testMonthMax.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testValidMonthMin = new Date("1/1/2000");
    System.out.println("Test - Valid Month Minimum: " + toString(testValidMonthMin));
    System.out.println("isValid() returns: " + testValidMonthMin.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testValidMonthMax = new Date("12/1/2000");
    System.out.println("Test - Valid Month Maximum: " + toString(testValidMonthMax));
    System.out.println("isValid() returns: " + testValidMonthMax.isValid());
    System.out.println("Expected: True");
    System.out.println();

    //**********31 Day Month Testing**********
    //JAN, MAR, MAY, JUL, AUG, OCT, DEC
    System.out.println("**********************");
    System.out.println("Months with 31 Days Validity Testing:");
    System.out.println("JAN, MAR, MAY, JUL, AUG, OCT, DEC");
    System.out.println();

    //January Day Testing
    Date testJanMin = new Date("1/0/2000");
    System.out.println("Test - JAN minimum: " + toString(testJanMin));
    System.out.println("isValid() returns: " + testJanMin.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testJan1 = new Date("1/1/2000");
    System.out.println("Test - JAN 1: " + toString(testJan1));
    System.out.println("isValid() returns: " + testJan1.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testJan31 = new Date("1/31/2000");
    System.out.println("Test - JAN 31: " + toString(testJan31));
    System.out.println("isValid() returns: " + testJan31.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testJanMax = new Date("1/32/2000");
    System.out.println("Test - JAN maximum: " + toString(testJanMax));
    System.out.println("isValid() returns: " + testJanMax.isValid());
    System.out.println("Expected: False");
    System.out.println();

    //March Day Testing
    System.out.println("-------");
    Date testMarchMin = new Date("3/0/2000");
    System.out.println("Test - MAR minimum: " + toString(testMarchMin));
    System.out.println("isValid() returns: " + testMarchMin.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testMarch1 = new Date("3/1/2000");
    System.out.println("Test - MAR 1: " + toString(testMarch1));
    System.out.println("isValid() returns: " + testMarch1.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testMar31 = new Date("3/31/2000");
    System.out.println("Test - MAR 31: " + toString(testMar31));
    System.out.println("isValid() returns: " + testMar31.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testMarchMax = new Date("3/32/2000");
    System.out.println("Test - MAR maximum: " + toString(testMarchMax));
    System.out.println("isValid() returns: " + testMarchMax.isValid());
    System.out.println("Expected: False");
    System.out.println();

    //May Day Testing
    System.out.println("-------");
    Date testMayMin = new Date("5/0/2000");
    System.out.println("Test - MAY minimum: " + toString(testMayMin));
    System.out.println("isValid() returns: " + testMayMin.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testMay1 = new Date("5/1/2000");
    System.out.println("Test - MAY 1: " + toString(testMay1));
    System.out.println("isValid() returns: " + testMay1.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testMay31 = new Date("5/31/2000");
    System.out.println("Test - MAY 31: " + toString(testMay31));
    System.out.println("isValid() returns: " + testMay31.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testMayMax = new Date("5/32/2000");
    System.out.println("Test - MAY maximum: " + toString(testMayMax));
    System.out.println("isValid() returns: " + testMayMax.isValid());
    System.out.println("Expected: False");
    System.out.println();

    //July Day Testing
    System.out.println("-------");
    Date testJulMin = new Date("7/0/2000");
    System.out.println("Test - JUL minimum: " + toString(testJulMin));
    System.out.println("isValid() returns: " + testJulMin.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testJul1 = new Date("7/1/2000");
    System.out.println("Test - JUL 1: " + toString(testJul1));
    System.out.println("isValid() returns: " + testJul1.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testJuly31 = new Date("7/31/2000");
    System.out.println("Test - JUL 31: " + toString(testJuly31));
    System.out.println("isValid() returns: " + testJuly31.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testJulMax = new Date("7/32/2000");
    System.out.println("Test - JUL maximum: " + toString(testJulMax));
    System.out.println("isValid() returns: " + testJulMax.isValid());
    System.out.println("Expected: False");
    System.out.println();

    //August Day Testing
    System.out.println("-------");
    Date testAugMin = new Date("8/0/2000");
    System.out.println("Test - AUG minimum: " + toString(testAugMin));
    System.out.println("isValid() returns: " + testAugMin.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testAug1 = new Date("8/1/2000");
    System.out.println("Test - AUG 1: " + toString(testAug1));
    System.out.println("isValid() returns: " + testAug1.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testAug31 = new Date("8/31/2000");
    System.out.println("Test - AUG 31: " + toString(testAug31));
    System.out.println("isValid() returns: " + testAug31.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testAugMax = new Date("8/32/2000");
    System.out.println("Test - AUG maximum: " + toString(testAugMax));
    System.out.println("isValid() returns: " + testAugMax.isValid());
    System.out.println("Expected: False");
    System.out.println();

    //October Day Testing
    System.out.println("-------");
    Date testOctMin = new Date("10/0/2000");
    System.out.println("Test - OCT minimum: " + toString(testOctMin));
    System.out.println("isValid() returns: " + testOctMin.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testOct1 = new Date("10/1/2000");
    System.out.println("Test - OCT 1: " + toString(testOct1));
    System.out.println("isValid() returns: " + testOct1.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testOct31 = new Date("10/31/2000");
    System.out.println("Test - OCT 31: " + toString(testOct31));
    System.out.println("isValid() returns: " + testOct31.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testOctMax = new Date("10/32/2000");
    System.out.println("Test - OCT maximum: " + toString(testOctMax));
    System.out.println("isValid() returns: " + testOctMax.isValid());
    System.out.println("Expected: False");
    System.out.println();

    //December Day Testing
    System.out.println("-------");
    Date testDecMin = new Date("12/0/2000");
    System.out.println("Test - DEC minimum: " + toString(testDecMin));
    System.out.println("isValid() returns: " + testDecMin.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testDec1 = new Date("12/1/2000");
    System.out.println("Test - DEC 1: " + toString(testDec1));
    System.out.println("isValid() returns: " + testDec1.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testDec31 = new Date("12/31/2000");
    System.out.println("Test - DEC 31: " + toString(testDec31));
    System.out.println("isValid() returns: " + testDec31.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testDecMax = new Date("12/32/2000");
    System.out.println("Test - DEC maximum: " + toString(testDecMax));
    System.out.println("isValid() returns: " + testDecMax.isValid());
    System.out.println("Expected: False");

    //**********30 Day Month Testing**********
    //APR, JUN, SEP, NOV
    System.out.println("**********************");
    System.out.println("Months with 30 Days Validity Testing:");
    System.out.println("APR, JUN, SEP, NOV");
    System.out.println();

    //April Day Testing
    Date testAprMin = new Date("4/0/2000");
    System.out.println("Test - APR minimum: " + toString(testAprMin));
    System.out.println("isValid() returns: " + testAprMin.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testApr1 = new Date("4/1/2000");
    System.out.println("Test - APR 1: " + toString(testApr1));
    System.out.println("isValid() returns: " + testApr1.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testApr30 = new Date("4/30/2000");
    System.out.println("Test - APR 30: " + toString(testApr30));
    System.out.println("isValid() returns: " + testApr30.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testAprMax = new Date("4/31/2000");
    System.out.println("Test - APR maximum: " + toString(testAprMax));
    System.out.println("isValid() returns: " + testAprMax.isValid());
    System.out.println("Expected: False");
    System.out.println();

    //June Day Testing
    System.out.println("-------");
    Date testJunMin = new Date("6/0/2000");
    System.out.println("Test - JUN minimum: " + toString(testJunMin));
    System.out.println("isValid() returns: " + testJunMin.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testJun1 = new Date("6/1/2000");
    System.out.println("Test - JUN 1: " + toString(testJun1));
    System.out.println("isValid() returns: " + testJun1.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testJun30 = new Date("6/30/2000");
    System.out.println("Test - JUN 30: " + toString(testJun30));
    System.out.println("isValid() returns: " + testJun30.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testJunMax = new Date("6/31/2000");
    System.out.println("Test - JUN maximum: " + toString(testJunMax));
    System.out.println("isValid() returns: " + testJunMax.isValid());
    System.out.println("Expected: False");
    System.out.println();
    
    //September Day Testing
    System.out.println("-------");
    Date testSepMin = new Date("9/0/2000");
    System.out.println("Test - SEP minimum: " + toString(testSepMin));
    System.out.println("isValid() returns: " + testSepMin.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testSep1 = new Date("9/1/2000");
    System.out.println("Test - SEP 1: " + toString(testSep1));
    System.out.println("isValid() returns: " + testSep1.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testSep30 = new Date("9/30/2000");
    System.out.println("Test - SEP 30: " + toString(testSep30));
    System.out.println("isValid() returns: " + testSep30.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testSepMax = new Date("9/31/2000");
    System.out.println("Test - SEP maximum: " + toString(testSepMax));
    System.out.println("isValid() returns: " + testSepMax.isValid());
    System.out.println("Expected: False");
    System.out.println();

    //November Day Testing
    System.out.println("-------");
    Date testNovMin = new Date("11/0/2000");
    System.out.println("Test - NOV minimum: " + toString(testNovMin));
    System.out.println("isValid() returns: " + testNovMin.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testNov1 = new Date("11/1/2000");
    System.out.println("Test - NOV 1: " + toString(testNov1));
    System.out.println("isValid() returns: " + testNov1.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testNov30 = new Date("11/30/2000");
    System.out.println("Test - NOV 30: " + toString(testNov30));
    System.out.println("isValid() returns: " + testNov30.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testNovMax = new Date("11/31/2000");
    System.out.println("Test - NOV maximum: " + toString(testNovMax));
    System.out.println("isValid() returns: " + testNovMax.isValid());
    System.out.println("Expected: False");
    
    //*********Non Leap Year February TESTING*************
    System.out.println("**********************");
    System.out.println("February Non Leap Year Validity Testing:");
    System.out.println();

    Date testFebMin = new Date("2/0/1999");
    System.out.println("Test - FEB minimum: " + toString(testFebMin));
    System.out.println("isValid() returns: " + testFebMin.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testFeb1 = new Date("2/1/2000");
    System.out.println("Test - FEB 1: " + toString(testFeb1));
    System.out.println("isValid() returns: " + testFeb1.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testFeb28 = new Date("2/28/1999");
    System.out.println("Test - FEB 28: " + toString(testFeb28));
    System.out.println("isValid() returns: " + testFeb28.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testFebMax = new Date("2/29/1999");
    System.out.println("Test - FEB maximum: " + toString(testFebMax));
    System.out.println("isValid() returns: " + testFebMax.isValid());
    System.out.println("Expected: False");

    //*********February LEAP YEAR TESTING*************
    //FEB only
    System.out.println("**********************");
    System.out.println("February Leap Year Validity Testing:");
    System.out.println();

    Date testValidLeap1 = new Date("2/29/1916");
    System.out.println("Test - Valid Leap Year 1: " + toString(testValidLeap1));
    System.out.println("isValid() returns: " + testValidLeap1.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testValidLeap2 = new Date("2/29/1940");
    System.out.println("Test - Valid Leap Year 2: " + toString(testValidLeap2));
    System.out.println("isValid() returns: " + testValidLeap2.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testValidLeap3 = new Date("2/29/2000");
    System.out.println("Test - Valid Leap Year 3: " + toString(testValidLeap3));
    System.out.println("isValid() returns: " + testValidLeap3.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testInvalidLeap1 = new Date("2/29/1917");
    System.out.println("Test - Invalid Leap Year 1: " + toString(testInvalidLeap1));
    System.out.println("isValid() returns: " + testInvalidLeap1.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testInvalidLeap2 = new Date("2/29/1941");
    System.out.println("Test - Invalid Leap Year 2: " + toString(testInvalidLeap2));
    System.out.println("isValid() returns: " + testInvalidLeap2.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testInvalidLeap3 = new Date("2/29/2001");
    System.out.println("Test - Invalid Leap Year 3: " + toString(testInvalidLeap3));
    System.out.println("isValid() returns: " + testInvalidLeap3.isValid());
    System.out.println("Expected: False");
    System.out.println();

    Date testInvalidLeap4 = new Date("2/29/1900");
    System.out.println("Test - Invalid Leap Year 4: " + toString(testInvalidLeap4));
    System.out.println("isValid() returns: " + testInvalidLeap4.isValid());
    System.out.println("Expected: False");
    System.out.println();

    //**********Valid Year Testing**********
    System.out.println("**********************");
    System.out.println("Non Leap Year Validity Testing:");
    System.out.println();

    Date testYearMin = new Date("12/31/1899");
    System.out.println("Test - Year Minimum: " + toString(testYearMin));
    System.out.println("isValid() returns: " + testYearMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    
    Date testYear1900 = new Date("1/1/1900");
    System.out.println("Test - Year 1900: " + toString(testYear1900));
    System.out.println("isValid() returns: " + testYear1900.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testCurrYear = new Date();
    System.out.println("Test - Current Year: " + toString(testCurrYear));
    System.out.println("isValid() returns: " + testCurrYear.isValid());
    System.out.println("Expected: True");
    System.out.println();

    Date testFutureYear = new Date();
    testFutureYear.setYear(testFutureYear.getYear() + 1);
    System.out.println("Test - Year Maximum: " + toString(testFutureYear));
    System.out.println("isValid() returns: " + testFutureYear.isValid());
    System.out.println("Expected: False");
    System.out.println();   

}

}