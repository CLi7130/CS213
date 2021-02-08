/* 
This class instantiates a book object to be placed in the library bag data structure,and creates two methods useful in categorization and parsing of the given library.
The book class contains instance variables that encompass the identifiers of a unique book available in the library, including a unique five digit serial number (Book.number), the book's title (Book.name), whether the book is checked in or out of the library (Book.checkedOut), and the publish date 
(Book.datePublished). Other methods included are the equals(Object obj) method and the toString() method, which check if a given book is equivalent to another, or convert relevant information about the book into a String of format "Book# Book.number :: Book.name :: Book.datePublished :: is available/is checked out.".
@author Craig Li, Prerak Patel
 */

public class Book {

    private String number; //a 5-digit serial number unique to the book
    private String name;
    private boolean checkedOut;
    private Date datePublished;
    
    /**
    Gets specified Book's serial number.
    @return number  book serial number
    */
    public String getNumber() {
        return number;
    }

    /**
    Sets specified Book's serial number.
    @param  number  desired serial number for a book object.
     */
    public void setNumber(String number) {
        this.number = number;
    }
    
    /**
    Gets specified book object's title.
    @return name    string containing the book's title.
    */
    public String getName() {
        return name;
    }
    
    /**
    Sets book object's name to a specified string.
    @param  name    name of the book
    */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
    Checks whether a book is checked out of the library.
    @return checkedOut  true if checkedOut, false if otherwise.
    */
    public boolean isCheckedOut() {
        return checkedOut;
    }
    
    /**
    Sets book object's status to either checked In or Out.
    @param  checkedOut  if true, book is listed as checked out in system, if false, listed as not checked out.
    */
    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }
    
    /**
    Gets book's publishing date.
    @return datePublished   Date object containing publishing date.
    */
    public Date getDatePublished() {
        return datePublished;
    }
    
    /**
    Sets datePublished for a specific book to specified date.
    @param  datePublished   specified date of publishing from Date class
    */
    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;	
    }
    
    /**
    Checks if two book objects are equivalent by comparing Book name and date published.
    @param  obj book from Book class to compare against
    @return true    true if books compared have same name and publish date,
                     false if otherwise
     */
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Book) {
            Book book = (Book) obj;
            if(number.contentEquals(book.getNumber())) {
                return true;
            }
            return false;
        }
        return false;
    }
    
    /**
    Returns formatted string containing book's number, name, date published,
    and availability.
    @return String formatted to a specific book, following the format of: 
            "Book# Book.number :: Book.name :: Book.datePublished :: 
            is available/is checked out.".
    */
    @Override
    public String toString(){
        String formattedBookInfo = new String("Book#");
        final String formatSplitter = new String("::");

        formattedBookInfo += this.number;
        formattedBookInfo += formatSplitter;

        formattedBookInfo += this.name;
        formattedBookInfo += formatSplitter;
        
        formattedBookInfo += String.valueOf(this.datePublished.getMonth());
        formattedBookInfo += "/";

        formattedBookInfo += String.valueOf(this.datePublished.getDay());
        formattedBookInfo += "/";

        formattedBookInfo += String.valueOf(this.datePublished.getYear());
        formattedBookInfo += formatSplitter;

        if(!checkedOut){
            formattedBookInfo = formattedBookInfo + "is available.";
        }
        else{
            formattedBookInfo = formattedBookInfo + "is checked out.";
        }

        return formattedBookInfo;
    }

}