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

    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUATERCENTENNIAL = 400;
    private static final int THIRTYONEDAYSPERMONTH = 31;
    private static final int THIRTYDAYSPERMONTH = 30;
    private static final int TWENTYEIGHTDAYSPERMONTH = 28;
    private static final int TWENTYNINEDAYSPERMONTH = 29;
    private static final int MONTHMAX = 12;
    private static final int YEARMIN = 1900;

    /**
    Gets day book was published.
    @return day int containing day book was published.
    */
    public int getDay() {
        return this.day;
    }
    
    /**
    Sets day published to input value.
    @param  day specified day book was published.
    */
    public void setDay(int day) {
        this.day = day;
    }
    
    /**
    Gets month book was published.
    @return month   int containing month book was published.
    */
    public int getMonth() {
        return this.month;
    }
    
    /**
    Sets month that book was published to specified input.
    @param  month   specified month book was published.
    */
    public void setMonth(int month) {
        this.month = month;
    }
    
    /**
    Gets year book was published.
    @return year    int containing year book was published.
    */
    public int getYear() {
        return this.year;
    }
    
    /**
    Sets year that book was published to specified input.
    @param  year    specified year book was published.
    */
    public void setYear(int year) {
        this.year = year;
    }
    
    /**
    Function splits an input string of format MM/DD/YYYY and sets each respective date component.
    @param  date    selected/provided publishing date in format MM/DD/YYYY
    */
    public Date(String date){
        String[] splitDateInput = date.split("/");
        final int monthSplit = 0;
        final int daySplit = 1;
        final int yearSplit = 2;

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
    @param  testYear    possible leap year
    @return true    true if testYear is a leap year, false otherwise.
    */
    private boolean isLeapYear(int testYear){

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
    @return true    true if date is valid, false otherwise, or if date is
                    beyond current date.
    */
    public boolean isValid(){

        Calendar currDate = Calendar.getInstance();

        final int yearMax = currDate.get(Calendar.YEAR);
        final int currMonth = currDate.get(Calendar.MONTH) + 1;
        final int currDay = currDate.get(Calendar.DAY_OF_MONTH);

        int daysPerMonthMax = 0;

        int testMonth = this.getMonth();
        int testYear = this.getYear();
        int testDay = this.getDay();
        boolean isLeapYear = false;

        if(testMonth < 1 || testMonth > MONTHMAX){
            return false;
        }
        if(testYear < YEARMIN || testYear > yearMax){
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

        final int JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC;
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

        final int[] monthsWith31Days = {JAN, MAR, MAY, JUL, AUG, OCT, DEC};
        final int[] monthsWith30Days = {APR, JUN, SEP, NOV};

        if(testMonth == FEB){
            isLeapYear = isLeapYear(testYear);

            if(isLeapYear){
                daysPerMonthMax = TWENTYNINEDAYSPERMONTH;
            }
            else{
                daysPerMonthMax = TWENTYEIGHTDAYSPERMONTH;
            }
        }

        for(int i = 0; i < monthsWith31Days.length; i++){
            if(testMonth == monthsWith31Days[i]){
                daysPerMonthMax = THIRTYONEDAYSPERMONTH;
                break;
            }
        }
        for(int i = 0; i < monthsWith30Days.length; i++){
            if(testMonth == monthsWith30Days[i]){
                daysPerMonthMax = THIRTYDAYSPERMONTH;
                break;
            }
        }

        if(testDay < 1 || testDay > daysPerMonthMax){
            return false;
        }

        return true;
    }
    
    /**
    Method returns a formatted string representing a date object.
    For use only in testbed main.
    @param  date    date object that needs to be represented as a String.
    @return formattedDate   string representation of date object input.
    */
    private static String toString(Date date){
        String formattedDate = "";
        final String formatSpacer = "/";

        formattedDate += Integer.toString(date.getMonth());
        formattedDate += formatSpacer;
        formattedDate += Integer.toString(date.getDay());
        formattedDate += formatSpacer;
        formattedDate += Integer.toString(date.getYear());

        return formattedDate;
    }


/**
Testbed main to exercise isValid() method.
Comments and other print objects are added to increase readability.
 */
public static void main(String[] args){
    int testCounter = 1;

    //**********Current Date Testing**********
    System.out.println("**********************");
    System.out.println("Current Date Testing:");
    Date testDate = new Date();
    System.out.println("Test #" + testCounter + " - Current Date: " 
                        + toString(testDate));
    System.out.println("isValid() returns: " + testDate.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //**********Future Date Testing**********
    System.out.println("**********************");
    System.out.println("Future Date Testing:");
    Date futureDate = new Date();
    futureDate.setDay(futureDate.getDay() + 1);
    System.out.println("Test #" + testCounter + " - Future Date: " 
                        + toString(futureDate));
    System.out.println("isValid() returns: " + futureDate.isValid());
    System.out.println("Expected: False");
    System.out.println();  
    testCounter++;

    //**********Month Detection Testing**********
    System.out.println("**********************");
    System.out.println("Month Validity Testing:");
    System.out.println();

    Date testMonthMin = new Date("0/1/2000");
    System.out.println("Test #" + testCounter + " - Month Minimum: " 
                        + toString(testMonthMin));
    System.out.println("isValid() returns: " + testMonthMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testMONTHMAX = new Date("13/1/2000");
    System.out.println("Test #" + testCounter + " - Month Maximum: " 
                        + toString(testMONTHMAX));
    System.out.println("isValid() returns: " + testMONTHMAX.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testValidMonthMin = new Date("1/1/2000");
    System.out.println("Test #" + testCounter + " - Valid Month Minimum: " 
                        + toString(testValidMonthMin));
    System.out.println("isValid() returns: " + testValidMonthMin.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testValidMONTHMAX = new Date("12/1/2000");
    System.out.println("Test #" + testCounter + " - Valid Month Maximum: " 
                        + toString(testValidMONTHMAX));
    System.out.println("isValid() returns: " + testValidMONTHMAX.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //**********31 Day Month Testing**********
    //JAN, MAR, MAY, JUL, AUG, OCT, DEC
    System.out.println("**********************");
    System.out.println("Months with 31 Days Validity Testing:");
    System.out.println("JAN, MAR, MAY, JUL, AUG, OCT, DEC");
    System.out.println();

    //January Day Testing
    Date testJanMin = new Date("1/0/2000");
    System.out.println("Test #" + testCounter + " - JAN minimum: " 
                        + toString(testJanMin));
    System.out.println("isValid() returns: " + testJanMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testJanMax = new Date("1/32/2000");
    System.out.println("Test #" + testCounter + " - JAN maximum: " 
                        + toString(testJanMax));
    System.out.println("isValid() returns: " + testJanMax.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testJan1 = new Date("1/1/2000");
    System.out.println("Test #" + testCounter + " - JAN 1: " 
                        + toString(testJan1));
    System.out.println("isValid() returns: " + testJan1.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testJan31 = new Date("1/31/2000");
    System.out.println("Test #" + testCounter + " - JAN 31: " 
                        + toString(testJan31));
    System.out.println("isValid() returns: " + testJan31.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //March Day Testing
    System.out.println("-------");
    Date testMarchMin = new Date("3/0/2000");
    System.out.println("Test #" + testCounter + " - MAR minimum: " 
                        + toString(testMarchMin));
    System.out.println("isValid() returns: " + testMarchMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testMarchMax = new Date("3/32/2000");
    System.out.println("Test #" + testCounter + " - MAR maximum: " 
                        + toString(testMarchMax));
    System.out.println("isValid() returns: " + testMarchMax.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testMarch1 = new Date("3/1/2000");
    System.out.println("Test #" + testCounter + " - MAR 1: " 
                        + toString(testMarch1));
    System.out.println("isValid() returns: " + testMarch1.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testMar31 = new Date("3/31/2000");
    System.out.println("Test #" + testCounter + " - MAR 31: " 
                        + toString(testMar31));
    System.out.println("isValid() returns: " + testMar31.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //May Day Testing
    System.out.println("-------");
    Date testMayMin = new Date("5/0/2000");
    System.out.println("Test #" + testCounter + " - MAY minimum: " 
                        + toString(testMayMin));
    System.out.println("isValid() returns: " + testMayMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testMayMax = new Date("5/32/2000");
    System.out.println("Test #" + testCounter + " - MAY maximum: " 
                        + toString(testMayMax));
    System.out.println("isValid() returns: " + testMayMax.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testMay1 = new Date("5/1/2000");
    System.out.println("Test #" + testCounter + " - MAY 1: " 
                        + toString(testMay1));
    System.out.println("isValid() returns: " + testMay1.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testMay31 = new Date("5/31/2000");
    System.out.println("Test #" + testCounter + " - MAY 31: " 
                        + toString(testMay31));
    System.out.println("isValid() returns: " + testMay31.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //July Day Testing
    System.out.println("-------");
    Date testJulMin = new Date("7/0/2000");
    System.out.println("Test #" + testCounter + " - JUL minimum: " 
                        + toString(testJulMin));
    System.out.println("isValid() returns: " + testJulMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testJulMax = new Date("7/32/2000");
    System.out.println("Test #" + testCounter + " - JUL maximum: " 
                        + toString(testJulMax));
    System.out.println("isValid() returns: " + testJulMax.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testJul1 = new Date("7/1/2000");
    System.out.println("Test #" + testCounter + " - JUL 1: " 
                        + toString(testJul1));
    System.out.println("isValid() returns: " + testJul1.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testJuly31 = new Date("7/31/2000");
    System.out.println("Test #" + testCounter + " - JUL 31: " 
                        + toString(testJuly31));
    System.out.println("isValid() returns: " + testJuly31.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //August Day Testing
    System.out.println("-------");
    Date testAugMin = new Date("8/0/2000");
    System.out.println("Test #" + testCounter + " - AUG minimum: " 
                        + toString(testAugMin));
    System.out.println("isValid() returns: " + testAugMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testAugMax = new Date("8/32/2000");
    System.out.println("Test #" + testCounter + " - AUG maximum: " 
                        + toString(testAugMax));
    System.out.println("isValid() returns: " + testAugMax.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testAug1 = new Date("8/1/2000");
    System.out.println("Test #" + testCounter + " - AUG 1: " 
                        + toString(testAug1));
    System.out.println("isValid() returns: " + testAug1.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testAug31 = new Date("8/31/2000");
    System.out.println("Test #" + testCounter + " - AUG 31: " 
                        + toString(testAug31));
    System.out.println("isValid() returns: " + testAug31.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //October Day Testing
    System.out.println("-------");
    Date testOctMin = new Date("10/0/2000");
    System.out.println("Test #" + testCounter + " - OCT minimum: " 
                        + toString(testOctMin));
    System.out.println("isValid() returns: " + testOctMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testOctMax = new Date("10/32/2000");
    System.out.println("Test #" + testCounter + " - OCT maximum: " 
                        + toString(testOctMax));
    System.out.println("isValid() returns: " + testOctMax.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testOct1 = new Date("10/1/2000");
    System.out.println("Test #" + testCounter + " - OCT 1: " 
                        + toString(testOct1));
    System.out.println("isValid() returns: " + testOct1.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testOct31 = new Date("10/31/2000");
    System.out.println("Test #" + testCounter + " - OCT 31: " 
                        + toString(testOct31));
    System.out.println("isValid() returns: " + testOct31.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //December Day Testing
    System.out.println("-------");
    Date testDecMin = new Date("12/0/2000");
    System.out.println("Test #" + testCounter + " - DEC minimum: " 
                        + toString(testDecMin));
    System.out.println("isValid() returns: " + testDecMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testDecMax = new Date("12/32/2000");
    System.out.println("Test #" + testCounter + " - DEC maximum: " 
                        + toString(testDecMax));
    System.out.println("isValid() returns: " + testDecMax.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testDec1 = new Date("12/1/2000");
    System.out.println("Test #" + testCounter + " - DEC 1: " 
                        + toString(testDec1));
    System.out.println("isValid() returns: " + testDec1.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testDec31 = new Date("12/31/2000");
    System.out.println("Test #" + testCounter + " - DEC 31: " 
                        + toString(testDec31));
    System.out.println("isValid() returns: " + testDec31.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //**********30 Day Month Testing**********
    //APR, JUN, SEP, NOV
    System.out.println("**********************");
    System.out.println("Months with 30 Days Validity Testing:");
    System.out.println("APR, JUN, SEP, NOV");
    System.out.println();

    //April Day Testing
    Date testAprMin = new Date("4/0/2000");
    System.out.println("Test #" + testCounter + " - APR minimum: " 
                        + toString(testAprMin));
    System.out.println("isValid() returns: " + testAprMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testAprMax = new Date("4/31/2000");
    System.out.println("Test #" + testCounter + " - APR maximum: " 
                        + toString(testAprMax));
    System.out.println("isValid() returns: " + testAprMax.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testApr1 = new Date("4/1/2000");
    System.out.println("Test #" + testCounter + " - APR 1: " 
                        + toString(testApr1));
    System.out.println("isValid() returns: " + testApr1.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testApr30 = new Date("4/30/2000");
    System.out.println("Test #" + testCounter + " - APR 30: " 
                        + toString(testApr30));
    System.out.println("isValid() returns: " + testApr30.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //June Day Testing
    System.out.println("-------");
    Date testJunMin = new Date("6/0/2000");
    System.out.println("Test #" + testCounter + " - JUN minimum: " 
                        + toString(testJunMin));
    System.out.println("isValid() returns: " + testJunMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testJunMax = new Date("6/31/2000");
    System.out.println("Test #" + testCounter + " - JUN maximum: " 
                        + toString(testJunMax));
    System.out.println("isValid() returns: " + testJunMax.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testJun1 = new Date("6/1/2000");
    System.out.println("Test #" + testCounter + " - JUN 1: " 
                        + toString(testJun1));
    System.out.println("isValid() returns: " + testJun1.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testJun30 = new Date("6/30/2000");
    System.out.println("Test #" + testCounter + " - JUN 30: " 
                        + toString(testJun30));
    System.out.println("isValid() returns: " + testJun30.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //September Day Testing
    System.out.println("-------");
    Date testSepMin = new Date("9/0/2000");
    System.out.println("Test #" + testCounter + " - SEP minimum: " 
                        + toString(testSepMin));
    System.out.println("isValid() returns: " + testSepMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testSepMax = new Date("9/31/2000");
    System.out.println("Test #" + testCounter + " - SEP maximum: " 
                        + toString(testSepMax));
    System.out.println("isValid() returns: " + testSepMax.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testSep1 = new Date("9/1/2000");
    System.out.println("Test #" + testCounter + " - SEP 1: " 
                        + toString(testSep1));
    System.out.println("isValid() returns: " + testSep1.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testSep30 = new Date("9/30/2000");
    System.out.println("Test #" + testCounter + " - SEP 30: " 
                        + toString(testSep30));
    System.out.println("isValid() returns: " + testSep30.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //November Day Testing
    System.out.println("-------");
    Date testNovMin = new Date("11/0/2000");
    System.out.println("Test #" + testCounter + " - NOV minimum: " 
                        + toString(testNovMin));
    System.out.println("isValid() returns: " + testNovMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testNovMax = new Date("11/31/2000");
    System.out.println("Test #" + testCounter + " - NOV maximum: " 
                        + toString(testNovMax));
    System.out.println("isValid() returns: " + testNovMax.isValid());
    System.out.println("Expected: False");
    testCounter++;

    Date testNov1 = new Date("11/1/2000");
    System.out.println("Test #" + testCounter + " - NOV 1: " 
                        + toString(testNov1));
    System.out.println("isValid() returns: " + testNov1.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;
    
    Date testNov30 = new Date("11/30/2000");
    System.out.println("Test #" + testCounter + " - NOV 30: " 
                        + toString(testNov30));
    System.out.println("isValid() returns: " + testNov30.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //*********Non Leap Year February TESTING*************
    System.out.println("**********************");
    System.out.println("February Non Leap Year Validity Testing:");
    System.out.println();

    Date testFebMin = new Date("2/0/1999");
    System.out.println("Test #" + testCounter + " - FEB minimum: " 
                        + toString(testFebMin));
    System.out.println("isValid() returns: " + testFebMin.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testFebMax = new Date("2/29/1999");
    System.out.println("Test #" + testCounter + " - FEB maximum: " 
                        + toString(testFebMax));
    System.out.println("isValid() returns: " + testFebMax.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testFeb1 = new Date("2/1/1999");
    System.out.println("Test #" + testCounter + " - FEB 1: " 
                        + toString(testFeb1));
    System.out.println("isValid() returns: " + testFeb1.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testFeb28 = new Date("2/28/1999");
    System.out.println("Test #" + testCounter + " - FEB 28: " 
                        + toString(testFeb28));
    System.out.println("isValid() returns: " + testFeb28.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    //*********February LEAP YEAR TESTING*************
    //FEB only
    System.out.println("**********************");
    System.out.println("February Leap Year Validity Testing:");
    System.out.println();

    Date testValidLeap1 = new Date("2/29/1916");
    System.out.println("Test #" + testCounter + " - Valid Leap Year 1: " 
                        + toString(testValidLeap1));
    System.out.println("isValid() returns: " + testValidLeap1.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testValidLeap2 = new Date("2/29/1940");
    System.out.println("Test #" + testCounter + " - Valid Leap Year 2: " 
                        + toString(testValidLeap2));
    System.out.println("isValid() returns: " + testValidLeap2.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testValidLeap3 = new Date("2/29/2000");
    System.out.println("Test #" + testCounter + " - Valid Leap Year 3: " 
                        + toString(testValidLeap3));
    System.out.println("isValid() returns: " + testValidLeap3.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testInvalidLeap1 = new Date("2/29/1917");
    System.out.println("Test #" + testCounter + " - Invalid Leap Year 1: " 
                        + toString(testInvalidLeap1));
    System.out.println("isValid() returns: " + testInvalidLeap1.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testInvalidLeap2 = new Date("2/29/1941");
    System.out.println("Test #" + testCounter + " - Invalid Leap Year 2: " 
                        + toString(testInvalidLeap2));
    System.out.println("isValid() returns: " + testInvalidLeap2.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testInvalidLeap3 = new Date("2/29/2001");
    System.out.println("Test #" + testCounter + " - Invalid Leap Year 3: " 
                        + toString(testInvalidLeap3));
    System.out.println("isValid() returns: " + testInvalidLeap3.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testInvalidLeap4 = new Date("2/29/1900");
    System.out.println("Test #" + testCounter + " - Invalid Leap Year 4: " 
                        + toString(testInvalidLeap4));
    System.out.println("isValid() returns: " + testInvalidLeap4.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    //**********Valid Year Testing**********
    System.out.println("**********************");
    System.out.println("Non Leap Year Validity Testing:");
    System.out.println();

    Date testYEARMIN = new Date("12/31/1899");
    System.out.println("Test #" + testCounter + " - Year Minimum: " 
                        + toString(testYEARMIN));
    System.out.println("isValid() returns: " + testYEARMIN.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++;

    Date testFutureYear = new Date();
    testFutureYear.setYear(testFutureYear.getYear() + 1);
    System.out.println("Test #" + testCounter + " - Year Maximum: " 
                        + toString(testFutureYear));
    System.out.println("isValid() returns: " + testFutureYear.isValid());
    System.out.println("Expected: False");
    System.out.println();
    testCounter++; 
    
    Date testYear1900 = new Date("1/1/1900");
    System.out.println("Test #" + testCounter + " - Year 1900: " 
                        + toString(testYear1900));
    System.out.println("isValid() returns: " + testYear1900.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

    Date testCurrYear = new Date();
    System.out.println("Test #" + testCounter + " - Current Year: " 
                        + toString(testCurrYear));
    System.out.println("isValid() returns: " + testCurrYear.isValid());
    System.out.println("Expected: True");
    System.out.println();
    testCounter++;

  

}

}