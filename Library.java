import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Library implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private ArrayList<Book> books;
    private ArrayList<Reader> readers;

    public Library() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
    }

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
    }

    public String getName() { return name; }
    public ArrayList<Book> getBooks() { return books; }
    public ArrayList<Reader> getReaders() { return readers; }

    public void setName(String name) { this.name = name; }
    public void setBooks(ArrayList<Book> books) { this.books = books; }
    public void setReaders(ArrayList<Reader> readers) { this.readers = readers; }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerReader(Reader reader) {
        readers.add(reader);
    }

    // Видає книгу читачу: прибирає з бібліотеки, додає читачу
    public void issueBook(Reader reader, Book book) {
        if (books.contains(book)) {
            books.remove(book);
            reader.borrowBook(book);
            System.out.println("Книгу " + book.getTitle() + " видано читачу " + reader.getFirstName());
        } else {
            System.out.println("Книга " + book.getTitle() + " недоступна у бібліотеці");
        }
    }

    @Override
    public String toString() {
        String booksStr = books.isEmpty()
                ? "немає книг"
                : books.stream()
                .map(Book::toString)
                .collect(Collectors.joining("\n   "));

        String readersStr = readers.isEmpty()
                ? "немає читачів"
                : readers.stream()
                .map(Reader::toString)
                .collect(Collectors.joining("\n   "));

        return "=== Бібліотека: " + name + " ===\n" +
                "Книги:\n   " + booksStr + "\n" +
                "Читачі:\n   " + readersStr;
    }
}