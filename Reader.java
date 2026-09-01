import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Reader implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private int registrationNumber;
    private ArrayList<Book> borrowedBooks;

    // Конструктор за замовчуванням - ініціалізуємо список одразу
    // щоб borrowBook() не кинув NullPointerException
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

    // Метод видачі книги читачу
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    @Override
    public String toString() {
        // Якщо книг немає - виводимо "немає книг"
        String booksStr = borrowedBooks.isEmpty()
                ? "немає книг"
                : borrowedBooks.stream()
                .map(Book::toString)
                .collect(Collectors.joining("\n         "));
        return "Читач #" + registrationNumber + ": " + firstName + " " + lastName + "\n" +
                "   Книги: " + booksStr;
    }
}