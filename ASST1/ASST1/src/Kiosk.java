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
        
        
        A command line always begins with a command and followed by a comma and some data tokens. Each data token is
also delimited by a comma. Some examples of valid command lines are demonstrated below. All commands are case-
sensitive, which means the commands with lowercase letters are invalid. You are required to deal with bad commands
that are not supported.
*/
public class Kiosk {
	
	public static String delims = ",";

    public void run(){
    	System.out.println("Library Kiosk running ");

        String date = "";
        String book = "";
        Date addDate;
        Library library = new Library();
        Scanner input = new Scanner(System.in);
        while(true) {
            String stringInput = input.nextLine();
    		StringTokenizer string = new StringTokenizer(stringInput, delims, true);
            String action = string.nextToken();

            if(action.contentEquals("A")) {
            	Book add = new Book();
            	string.nextToken();
            	book = string.nextToken();
            	string.nextToken();
            	date = string.nextToken();
            	add.setName(book);
            	//add.setDatePublished(new Date(date));
            	addDate = new Date(date);
            	boolean valid = addDate.isValid();
            	if(valid) {
            		add.setDatePublished(addDate);
                	library.add(add);
                	System.out.println(book + " added to the library!");
            	}
            	else {
                	System.out.println("Invalid Date!");
            	}
            }
            else if(action.contentEquals("R")) {
            	string.nextToken();
            	Book remove = new Book();
            	remove.setNumber(string.nextToken().trim());
            	boolean removed = library.remove(remove);
            	if(removed) {
            		System.out.println("Book# " + remove.getNumber() + "removed.");
            	}
            	else {
            		System.out.println("Unable to remove, the library does not have this book.");
            	}
            	
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
            	library.print();
            }
            else if(action.contentEquals("PD")) {
            	library.printByDate();
            }
            else if(action.contentEquals("PN")) {
            	library.printByNumber();
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
        input.close();


    }

}