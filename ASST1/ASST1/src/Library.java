/*
TODO / NOTES 
    ** delete this section before submission**
    - DO NOT ADD:
        - other data members
            - ONLY EXCEPTION IS CONSTANTS
        - Do not change signatures of methods listed
            - can add other methods if necessary(private/helper)
    
    - remove() method calls helper method find(), to find index of book 
        to be removed, method must maintain the current order of books
        in the array after removal.
    - can use any algorithm for sorting
    - check for bad input/commands
    - you CANNOT use System.out in this class
        - System.out.print() methods are OK
    TODO:
        - implement headers for each method
        
*/

/** 
    This class allows for the creation of the library object to hold all book objects that are added, as well as allowing operations to simulate a physical library.
    Methods included in this class allow users to find books, add and remove books from the library, as well as check out and return books.  The library also includes several different functionalities for printing the current catalogue, including printing by date, and by book number.
    @author Craig Li, Prerak Patel
 */
public class Library {
	private Book[] books; // array-based implementation of the bag data structure
	private int numBooks; // the number of books currently in the bag
	
	public Library() {
		Book[] books = new Book[4];
		numBooks = 0;
	} //default constructor to create an empty bag
	
	private int find(Book book) {
		for(int i=0; i <= numBooks; i++) {
			if(books[i].equals(book)) {
				return i;
			}
		}
		return -1; 
	} // helper method to find a book in the bag
	private void grow() { 
		Book[] grow = new Book[numBooks + 4];
		for(int i=0; i <= numBooks; i++) {
			books[i] = grow[i];
		}
		books = grow;
	} // helper method to grow the capacity by 4
	
	public void add(Book book) {
		int serialNum = 10000;
		// Check if there is room to add a book
		if(numBooks >= books.length) {
			grow();
		}
		// Check valid date
		
		// instantiate book
		serialNum++;
		book.setNumber(serialNum+"");
		book.setCheckedOut(false);
		
		for(int i=0; i <= numBooks; i++) {
			if(books[i] == null) {
				books[i] = book;
			}
		}
	}
	public boolean remove(Book book) { 
		int index = find(book);
		if(index>0) {
			books[index] = null;
			return true;
		}
		return false;
	}
	
	public boolean checkOut(Book book) {
		if((find(book) < 0) || !book.isCheckedOut()){
			return false;
		}
		else {
			book.setCheckedOut(true);
			return true;
		}
	}
	public boolean returns(Book book) {
		if((find(book) < 0) || !book.isCheckedOut()){
			return false;
		}
		return true;
	}
	public void print() { } //print the list of books in the bag
	public void printByDate() { } //print the list of books by datePublished (ascending)
	public void printByNumber() { } //print the list of books by number (ascending)
}
