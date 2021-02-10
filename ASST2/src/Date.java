/*
    NOTES/TODO
    *****Delete upon final formatting pass*****

    - import Date.java from ASST1
    - implement Java Interface Comparable
    
*/
package ASST1;
import Date.ASST1;

public class Date implements Comparable<Date>{

    @Override
    public int compareTo(Date date){
        //return 1, 0, or -1
    }
    
}

public static void main(String[] args){
    Date testDate = new Date();

    System.out.println(testDate.toString());
}