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
        String firstToken = "";

        while(!firstToken.contentEquals("Q")) {
            Scanner input = new Scanner(System.in);
            String stringInput = input.nextLine();
    		StringTokenizer string = new StringTokenizer(stringInput, delims, true);
            firstToken = string.nextToken();
            if(firstToken.contentEquals("A")) {
            	while (input.hasNext() == true ) {
                    String s1 = input.next();
                    System.out.println(s1);
                }
                System.out.println(firstToken);
            }
            else if(firstToken.contentEquals("R")) {
            	while (input.hasNext() == true ) {
                    String s1 = input.next();
                    System.out.println(s1);
                }
                System.out.println(firstToken);
            }
            else if(firstToken.contentEquals("O")) {
            	while (input.hasNext() == true ) {
                    String s1 = input.next();
                    System.out.println(s1);
                }
                System.out.println(firstToken);
            }
            else if(firstToken.contentEquals("I")) {
            	while (input.hasNext() == true ) {
                    String s1 = input.next();
                    System.out.println(s1);
                }
                System.out.println(firstToken);
            }
            else if(firstToken.contentEquals("PA")) {
            	while (input.hasNext() == true ) {
                    String s1 = input.next();
                    System.out.println(s1);
                }
                System.out.println(firstToken);
            }
            else if(firstToken.contentEquals("PD")) {
            	while (input.hasNext() == true ) {
                    String s1 = input.next();
                    System.out.println(s1);
                }
                System.out.println(firstToken);
            }
            else if(firstToken.contentEquals("PN")) {
            	while (input.hasNext() == true ) {
                    String s1 = input.next();
                    System.out.println(s1);
                }
                System.out.println(firstToken);
            }
            else if(firstToken.contentEquals("Q")) {
                System.out.println(firstToken);
            }
            else {
                System.out.println("Invalid Command!");
            }
            
        }
    	System.out.println("Kiosk session ended.");


    }

}