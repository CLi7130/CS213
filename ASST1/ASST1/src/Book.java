/* 

TODO / Notes on Book class:

    ** delete this section before submission**

    - implement equals method
        - method checks book number
    - implement toString method
        - format: "Book#10007::Design Patterns::5/30/1996::is available."
                : "Book#<Book.number>::<Book.name>::<Book.datePublished>
                    ::<is/is not available>."
                    - for isAvailable:  - check Book.checkedOut
                                        - check if book is in library at all

    - do not add any other data members/instance variables EXCEPT static variables 
        or constants
    - You CANNOT read from console, or use System.out in this class
        - always return to kiosk class-> read from console/print there?

    - Book.number   
        - starts at 10001
        - will not exceed 5 digits per Project 1 Q&A
        - implement counter in Kiosk class to track # of books?
            - start counter at 10000
            - if command is A / Add - increment bookCounter
                - Book.number = bookCounter;

    Book.name
        - check for empty string

    Book.checkedOut
        - initialize to false
        - make sure to change when checking books in/out
        
*/

/** 
    This class instantiates a book object to be placed in the library bag data structure,and creates two methods useful in categorization and parsing of the given library.
    The book class contains instance variables that encompass the identifiers of a unique book available in the library, including a unique five digit serial number (Book.number), the book's title (Book.name), whether the book is checked in or out of the library (Book.checkedOut), and the publish date 
    (Book.datePublished). Other methods included are the equals(Object obj) method and the toString() method, which check if a given book is equivalent to another, or convert relevant information about the book into a String of format "Book#<Book.number>::<Book.name>::<Book.datePublished>::<is/is not available>.".

    @author Craig Li, Prerak Patel
 */
public class Book {

	private String number; //a 5-digit serial number unique to the book
	private String name;
	private boolean checkedOut;
	private Date datePublished;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isCheckedOut() {
		return checkedOut;
	}
	public void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}
	
	public Date getDatePublished() {
		return datePublished;
	}
	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}
    
    /**
     Checks if two book objects are equivalent by comparing Book name and date published.
     @param book to compare against
     @return true if books compared have same name and publish date, false 
             if otherwise
     */
	//@Override Doesn't compile compiler wants to get rid of override
    public boolean equals(Book book){
        if(number.contentEquals(book.getNumber())) {
        	return true;
        }
        return false;
    }
    /**
     * Returns formatted string containing book's number, name, date published,
     * and availability.
     * @return String formatted to a specific book, following the format of: 
     *         "Book#<Book.number>::<Book.name>::<Book.datePublished>::<is/is 
     *         not available>.".
     */
    @Override
    public String toString(){
        String formattedBookInfo = new String("Book#");
        String formatSplitter = new String("::");

        formattedBookInfo += this.number;
        formattedBookInfo += formatSplitter;

        formattedBookInfo += this.name;
        formattedBookInfo += formatSplitter;
        
        formattedBookInfo += String.valueOf(this.datePublished.getMonth());
        formattedBookInfo += "/";

        formattedBookInfo += String.valueOf(this.datePublished.getDay());
        formattedBookInfo += "/";

        formattedBookInfo += String.valueOf(this.datePublished.getYear());
        //this.datePublished - need to reformat so it prints date object
        formattedBookInfo += formatSplitter;

        if(!checkedOut){
            formattedBookInfo = formattedBookInfo + "is available.";
        }
        else{
            formattedBookInfo = formattedBookInfo + "is not available.";
        }

        return formattedBookInfo;
    }


//test driver, delete when making final formatting pass
    
public static void main(String[] args){
    Book testBook = new Book();
    Book testBook2 = new Book();
    Book testBook3 = new Book();
    testBook.name = "Born a Crime";
    testBook.datePublished = new Date("11/15/2016");
    System.out.println(String.valueOf(testBook.datePublished));
    //need to write date class for this to work - current implementation doesn't
        //print date correctly
    testBook.number = "10001";
    testBook.checkedOut = false;
    testBook2.number = "10001";
    testBook3.number = "10002";

    //toString method tested, all fields but datePublished work
    System.out.println(testBook.toString());
    System.out.println(testBook.equals(testBook2)+ " :expected true");
    System.out.println(testBook.equals(testBook3)+ " :expected false");
    new Library().main();
}

}
