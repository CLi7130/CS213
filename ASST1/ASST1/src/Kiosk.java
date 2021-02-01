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

    public void run(){
    	System.out.print("Library Kiosk running ");
        String firstToken = "";

        while(!firstToken.contentEquals("Q")) {
            Scanner input = new Scanner(System.in);
            String stringInput = input.nextLine();
            StringTokenizer string = new StringTokenizer(stringInput);
            firstToken = string.nextToken();
            System.out.println(firstToken);

        }
    	
    	
    }

}