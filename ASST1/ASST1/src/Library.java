
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
