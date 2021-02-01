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
	private int serialNum = 10000; // serial number for books
	//private int grows = 1; // the amount of times the bag has had to have grown
	
	public Library() {
		books = new Book[4];
		numBooks = 0;
	} //default constructor to create an empty bag
	
	/**
    Finds where a book is in the bag
    @param book that you want to find
    @return the index of the book otherwise -1 if not found
    */
	private int find(Book book) {
		for(int i=0; i < books.length; i++) {
			if(books[i] == book) {
				return i;
			}
		}
		return -1; 
	}
	
	/**
     * Returns the array of Books with size 4 greater
     * @return books array with size 4 greater 
     */
	private void grow() { 
		Book[] grow = new Book[books.length + 4];
		for(int i=0; i < books.length; i++) {
			grow[i] = books[i];
		}
		books = grow;
	}
	
	private void trimArray() {
		Book[] trim = new Book[books.length];
		int count = 0;
		for(int i=0; i < books.length; i++) {
			if(books[i] != null) {
				trim[count] = books[i];
			}
			else {
				count--;
			}
			count++;
		}
		books = trim;
	}
	
	
	public void add(Book book) {
		
		// Check if there is room to add a book
		// if not enough room then grow

		// Check valid date
		
		// instantiate book
		serialNum++;
		book.setNumber(serialNum+"");
		book.setCheckedOut(false);
		numBooks++;
		if(numBooks > books.length) {
			grow();
		}
		trimArray();
		books[numBooks-1] = book;
	}
	
	public boolean remove(Book book) { 
		int index = find(book);
		if(index>0) {
			books[index] = null;
			numBooks--;
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
	public void print() {
		for(int i=0; i < books.length; i++) {
			if(books[i] == null) { 
				continue;
			}	
			System.out.println(books[i].toString());
		}
	} //print the list of books in the bag
	public void printByDate() { } //print the list of books by datePublished (ascending)
	public void printByNumber() { } //print the list of books by number (ascending)
	
	//test driver, delete when making final formatting pass
	public void main(){
		Book testBook = new Book();
	    Book testBook2 = new Book();
	    Book testBook3 = new Book();
	    Book testBook4 = new Book();
	    Book testBook5 = new Book();
	    Book testBook6 = new Book();

	    testBook.setName("Born a Crime");
	    testBook.setDatePublished(new Date("11/15/2016"));
	    testBook2.setName("Is it too late to say sorry");
	    testBook2.setDatePublished(new Date("11/15/2017"));
	    testBook3.setName("Boom Boom Pow");
	    testBook3.setDatePublished(new Date("11/15/2018"));
	    testBook4.setName("Bow Chica Wow Wow");
	    testBook4.setDatePublished(new Date("11/15/2019"));
	    testBook5.setName("The Home Depot");
	    testBook5.setDatePublished(new Date("11/15/2020"));
	    testBook6.setName("Megalodon");
	    testBook6.setDatePublished(new Date("11/25/2021"));
	    
	    add(testBook);
	    add(testBook2);
	    add(testBook3);
	    add(testBook4);
	    add(testBook5);
	    add(testBook6);
	    remove(testBook2);
	    trimArray();
	    System.out.println(find(testBook)+" :expected value = 0");
	    System.out.println(find(testBook2)+" :expected value = -1");

	    print();
	}
}
