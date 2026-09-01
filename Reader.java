import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Reader implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private int registrationNumber;
    private ArrayList<Book> borrowedBooks;

    // Default constructor - initialize the list right away
    // so borrowBook() doesn't throw a NullPointerException
    public Reader() {
        this.borrowedBooks = new ArrayList<>();
    }

    public Reader(String firstName, String lastName, int registrationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.registrationNumber = registrationNumber;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getRegistrationNumber() { return registrationNumber; }
    public ArrayList<Book> getBorrowedBooks() { return borrowedBooks; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setRegistrationNumber(int registrationNumber) { this.registrationNumber = registrationNumber; }
    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) { this.borrowedBooks = borrowedBooks; }

    // Issues a book to the reader
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    @Override
    public String toString() {
        // If there are no books - print "no books"
        String booksStr = borrowedBooks.isEmpty()
                ? "no books"
                : borrowedBooks.stream()
                .map(Book::toString)
                .collect(Collectors.joining("\n         "));
        return "Reader #" + registrationNumber + ": " + firstName + " " + lastName + "\n" +
                "   Books: " + booksStr;
    }
}
