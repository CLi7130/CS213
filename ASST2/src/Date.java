/*
    NOTES/TODO
    *****Delete upon final formatting pass*****
    - import Date.java from ASST1
    - implement Java Interface Comparable
    
*/

import java.util.Calendar;

public class Date implements Comparable<Date>{

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
    Method returns an integer that represents ordering between two date objects.
    If method returns a -1, the first date is before the second date, if it returns 0, the dates are equal, if it returns a 1, the first date is after the second date.
    @param  date    date object that needs to be compared to another date
                    object.
    @return dateOrder   integer value representing ordering of two dates.
    */
    @Override
    public int compareTo(Date date){
        int firstYear = this.getYear();
        int firstMonth = this.getMonth();
        int firstDay = this.getDay();

        int secondYear = date.getYear();
        int secondMonth = date.getMonth();
        int secondDay = date.getDay();

        int dateOrder = 0;
        int firstDateBeforeSecondDate = -1;
        int equalDates = 0;
        int firstDateAfterSecondDate = 1;

        if(firstYear > secondYear){
            dateOrder = firstDateAfterSecondDate;
        }
        else if(firstYear < secondYear){
            dateOrder = firstDateBeforeSecondDate;
        }
        else{
            if(firstMonth > secondMonth){
                dateOrder = firstDateAfterSecondDate;
            }
            else if(firstMonth < secondMonth){
                dateOrder = firstDateBeforeSecondDate;
            }
            else{
                if(firstDay > secondDay){
                    dateOrder = firstDateAfterSecondDate;
                }
                else if(firstDay < secondDay){
                    dateOrder = firstDateBeforeSecondDate;
                }
                else{
                    dateOrder = equalDates;
                }
            }
        }
        return dateOrder;
    }

    public static void main(String[] args){
        System.out.println("Testing");
        Date testDate = new Date();
        Date testDate2 = new Date();

        Date futureDate = new Date();
        futureDate.setDay(futureDate.getDay() + 1);

        Date pastDate = new Date();
        pastDate.setDay(pastDate.getDay() - 1);

        System.out.println("same date: " + toString(testDate2) 
                            + ", Expected: 0");
        System.out.println("compareTo returns: " 
                            + testDate.compareTo(testDate2));
        System.out.println();

        System.out.println("future date: " + toString(futureDate) 
                            + ", Expected: -1");
        System.out.println("compareTo returns: " 
                            + testDate.compareTo(futureDate)); 
        System.out.println();

        System.out.println("past date: " + toString(pastDate) 
                            + ", Expected: 1");
        System.out.println("compareTo returns: " 
                            + testDate.compareTo(pastDate));
        System.out.println();
    }
}

