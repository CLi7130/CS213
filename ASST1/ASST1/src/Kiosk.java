import java.util.Scanner;
import java.util.StringTokenizer;

/**
This class is the Interface between the user and the program, all error messages and success messages will print from here
Also included is a method run() which will initiate the interface. 
@author Craig Li, Prerak Patel
*/

public class Kiosk {
    private static int serialNum = 10001;
    public static final String delims = ",";

    
    /**
    Runs the whole interface between user and library.
    The user inputs commands either A, R, O, I, PA, 
    PD, PN and additional inputs adn the program will respond 
    */
    public void run(){
        System.out.println("Library Kiosk running ");

        String date = "";
        String book = "";
        Date addDate;
        Library library = new Library();
        Scanner input = new Scanner(System.in);
        while(true) {
            
            String stringInput = input.nextLine();
            StringTokenizer string = new StringTokenizer(stringInput,
                                                         delims, true);
            String action = string.nextToken();

            if(action.contentEquals("A")) {
                Book add = new Book();
                string.nextToken();
                book = string.nextToken();
                string.nextToken();
                date = string.nextToken();
                add.setName(book);
                addDate = new Date(date);
                boolean valid = addDate.isValid();
                if(valid) {
                    add.setDatePublished(addDate);
                    library.add(add);
                    add.setNumber(serialNum + "");
                    serialNum++;
                    System.out.println(book + " added to the library.");
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
                    System.out.println("Book# " + remove.getNumber() 
                                        + " removed.");
                }
                else {
                    System.out.println("Unable to remove, the library does"
                                        + " not have this book.");
                }
                
            }
            else if(action.contentEquals("O")) {
                string.nextToken();
                Book bookToCheckOut = new Book();
                bookToCheckOut.setNumber(string.nextToken().trim());
                
                boolean checkedIn = library.checkOut(bookToCheckOut);

                if(checkedIn){
                    System.out.println("You've checked out Book#" + bookToCheckOut.getNumber() + ". Enjoy!");
                }
                else{
                    System.out.println("Book#" + bookToCheckOut.getNumber() 
                                        + " is not available.");
                }
            }
            else if(action.contentEquals("I")) {
                string.nextToken();
                Book bookToBeReturned = new Book();
                bookToBeReturned.setNumber(string.nextToken().trim());
                
                boolean returnPossible = library.returns(bookToBeReturned);

                if(returnPossible){
                    System.out.println("Book#" + bookToBeReturned.getNumber() 
                                        + " return has completed. Thanks!");
                }
                else{
                    System.out.println("Unable to return Book#" + 
                                        bookToBeReturned.getNumber() + ".");
                }
            }
            else if(action.contentEquals("PA")) {
                if(library.getNumBooks() == 0){
                    System.out.println("Library catalog is empty!");
                }
                else{
                    System.out.println("**List of books in the library.");
                    library.print();
                    System.out.println("**End of list");
                }
            }
            else if(action.contentEquals("PD")) {
                if(library.getNumBooks() == 0){
                    System.out.println("Bookshelf is empty!");
                }
                else{
                    System.out.println("**List of books by the dates "
                                        + "published.");
                    library.printByDate();
                    System.out.println("**End of list");
                }
            }
            else if(action.contentEquals("PN")) {
                if(library.getNumBooks() == 0){
                    System.out.println("Bookshelf is empty!");
                }
                else{
                    System.out.println("**List of books by the book numbers.");
                    library.printByNumber();
                    System.out.println("**End of list");
                }
            }
            else if(action.contentEquals("Q")) {
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