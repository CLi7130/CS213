import java.util.Scanner;
import java.util.StringTokenizer;



/*
TODO / NOTES 
     ** delete this section before submission**
     - use RunProject1.java as a driver
     - kiosk processes command lines from console
        - user interface class handling input/output
    - you can define the data members you need and define some private methods to handle the commands
        - follow ground rules and programming style.
*/
public class Kiosk {
	
	public static String delims = " ,\t*+-/()[]";

    public void run(){
    	System.out.println("Library Kiosk running ");
        String action = "";

        while(true) {
            Scanner input = new Scanner(System.in);
            String stringInput = input.nextLine();
    		StringTokenizer string = new StringTokenizer(stringInput, delims, true);
            action = string.nextToken();
            if(action.contentEquals("A")) {
                while (string.hasMoreElements()) {
                    System.out.println(string.nextElement());
                }
                System.out.println(action);
            }
            else if(action.contentEquals("R")) {
                while (string.hasMoreElements()) {
                    System.out.println(string.nextElement());
                }
                System.out.println(action);
            }
            else if(action.contentEquals("O")) {
                while (string.hasMoreElements()) {
                    System.out.println(string.nextElement());
                }
                System.out.println(action);
            }
            else if(action.contentEquals("I")) {
                while (string.hasMoreElements()) {
                    System.out.println(string.nextElement());
                }
                System.out.println(action);
            }
            else if(action.contentEquals("PA")) {
                while (string.hasMoreElements()) {
                    System.out.println(string.nextElement());
                }
                System.out.println(action);
            }
            else if(action.contentEquals("PD")) {
                while (string.hasMoreElements()) {
                    System.out.println(string.nextElement());
                }
                System.out.println(action);
            }
            else if(action.contentEquals("PN")) {
                while (string.hasMoreElements()) {
                    System.out.println(string.nextElement());
                }
                System.out.println(action);
            }
            else if(action.contentEquals("Q")) {
                System.out.println(action);
                break;
            }
            else {
                System.out.println("Invalid Command!");
            }
        }
    	System.out.println("Kiosk session ended.");


    }

}