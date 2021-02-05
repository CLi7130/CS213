/** 
    This class allows for the creation of the library object to hold all book objects that are added, as well as allowing operations to simulate a physical library.
    Methods included in this class allow users to find books, add and remove books from the library, as well as check out and return books.  The library also includes several different functionalities for printing the current catalogue, including printing by date, and by book number.
    @author	Craig Li, Prerak Patel 
 */

public class Library {
	private Book[] books; // array-based implementation of the bag data structure
	private int numBooks; // the number of books currently in the bag
	private final int growAmount = 4;
	private final int fail = -1;
	
	/**
    Constructor for new Library Array
     */
	public Library() {
		books = new Book[4];
		numBooks = 0;
    }
	
    /**
    Returns number of books currently in library
    @return	numBooks	int value containing number of Books in library.
     */
    public int getNumBooks(){
        return numBooks;
    }
	
	/**
    Finds where a book is in the bag
    @param	book	The book from Book class that you want to find
    @return	int	the index of the book otherwise -1 if not found
    */
	public int find(Book book) {
		for(int i=0; i < books.length; i++) {
            if(books[i] == null){
                continue;
            }
			if(books[i].equals(book)) {
				return i;
			}
		}
		return fail; 
	}
	
	/**
     * Returns the array of books with size 4 greater 
     */
	private void grow() { 
		Book[] grow = new Book[books.length + growAmount];
		for(int i=0; i < books.length; i++) {
			grow[i] = books[i];
		}
		books = grow;
	}
	
	/**
    Gets rid of any spaces inside the array where we removed
    and the bag will no longer have spaces inbetween books
    */
	private void shiftArray() {
        Book [] shiftedBooks = new Book[books.length];
        int count = 0;
		for(int i=0; i < books.length; i++) {
			if(books[i] != null) {
                shiftedBooks[count] = books[i];
			}
            else{
                count--;
            }
            count++;
		}
        books = shiftedBooks;
	}
	
    /**
    Sorts book array by Date in ascending order via selection sort.
    */
	private void sortByDate() {

        for(int i = 0; i < books.length-1; i++){

            Book oldestBook = books[i];
            int oldestBookIndex = i;

            for(int j = i + 1; j < books.length; j++){
                if(books[j] == null){
                    break;
                }

                Book iterator = books[j];
                if(isOlder(iterator, oldestBook)){
                    oldestBookIndex = j;
                    oldestBook = books[j];
                }
            }
            Book swap = books[oldestBookIndex];
            books[oldestBookIndex] = books[i];
            books[i] = swap;
        }
    }
	
    /**
     * Helper method that compares the publishing dates of two books.
     @param	first	iterative book being compared to current oldest book.
     @param	second	current oldest book found so far in array.
     @return	true	true if first is older than second, false otherwise.
     */
    private boolean isOlder(Book first, Book second){
        int firstYear = first.getDatePublished().getYear();
        int firstMonth = first.getDatePublished().getMonth();
        int firstDay = first.getDatePublished().getDay();

        int secondYear = second.getDatePublished().getYear();
        int secondMonth = second.getDatePublished().getMonth();
        int secondDay = second.getDatePublished().getDay();

        if(firstYear < secondYear){
            return true;
        }
        else if(firstYear == secondYear){
            if(firstMonth < secondMonth){
                return true;
            }
            else if(firstMonth == secondMonth){
                if(firstDay < secondDay){
                    return true;
                }
                else if(firstDay == secondDay){
                    //sort alphabetically if exact same publishing date
                    String firstName = first.getName();
                    String secondName = second.getName();

                    int alphabetOrder = firstName.compareTo(secondName);

                    if(alphabetOrder < 0){
                        return true;
                    }
                }
            }
        }
        return false;
    }
	
	/**
	Sorts the bag by the serial numbers ascending order. Implemented using insertion sort.
	 */
	private void sortByNumber() {
        
        for(int i = 1; i < books.length; i++){
            if(books[i] == null){
                break;
            }
            int key = Integer.parseInt(books[i].getNumber());
            Book unsortedBook = books[i];
            int sortedMax = i-1;

            int currSortedBook = Integer.parseInt(books[sortedMax].getNumber());
            
            while(sortedMax>=0 && currSortedBook > key){
                books[sortedMax+1] = books[sortedMax];
                sortedMax--;
                if(sortedMax < 0){
                    break;
                }
                currSortedBook = Integer.parseInt(books[sortedMax].getNumber());
            }
            books[sortedMax+1] = unsortedBook;
        }
	}
	
	/**
    Adds a book to the end of the bag
    @param	book	book of object Book to be added to the bag
    */
	public void add(Book book) {
		book.setCheckedOut(false);
		numBooks++;
		if(numBooks > books.length) {
			grow();
		}
		shiftArray();
		books[numBooks-1] = book;
	}
	
	/**
    Removes a book from the bag and does not reorder the bag
    Uses the find helper method to remove the book
    @param	book	book from the book class to be removed
    @return	true	true if the book was found and removed and false if the book was not found
    */
	public boolean remove(Book book) { 
		int index = find(book);
		if(index>=0) {
			books[index] = null;
			numBooks--;
			return true;
		}
		return false;
	}
	
	/**
   	Checks the book out if possible
    @param	book	book from Book class to be checked out
    @return	true	true if possible and false otherwise
    */
	public boolean checkOut(Book book) {

        int index = find(book);
		if((find(book) < 0) || books[index].isCheckedOut()){
			return false;
        }
		else {
			books[index].setCheckedOut(true);
			return true;
		}
	}
	
	/**
   	Returns the book out if possible
    @param	book	book from Book class to be returned
    @return	true	true if possible and false otherwise
    */
	public boolean returns(Book book) {
        int index = find(book);
		if((find(book) < 0) || !books[index].isCheckedOut()){
			return false;
        }
        books[index].setCheckedOut(false);
		return true;
	}
	
	/**
   	Prints the contents of the bag
    */
	public void print() {
        shiftArray();
		for(int i=0; i < books.length; i++) {
			if(books[i] == null) { 
				continue;
			}	
			System.out.println(books[i].toString());
		}
	}
	
	/**
	Print the list of books by datePublished (ascending)
    */
	public void printByDate() {
		sortByDate();
		print();
	}
	
	/**
	Print the list of books by number (ascending)
	*/
	public void printByNumber() {
		sortByNumber();
		print();
	} 
	
}