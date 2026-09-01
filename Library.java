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

    // Issues a book to a reader: removes it from the library, adds it to the reader
    public void issueBook(Reader reader, Book book) {
        if (books.contains(book)) {
            books.remove(book);
            reader.borrowBook(book);
            System.out.println("Book " + book.getTitle() + " issued to reader " + reader.getFirstName());
        } else {
            System.out.println("Book " + book.getTitle() + " is not available in the library");
        }
    }

    @Override
    public String toString() {
        String booksStr = books.isEmpty()
                ? "no books"
                : books.stream()
                .map(Book::toString)
                .collect(Collectors.joining("\n   "));

        String readersStr = readers.isEmpty()
                ? "no readers"
                : readers.stream()
                .map(Reader::toString)
                .collect(Collectors.joining("\n   "));

        return "=== Library: " + name + " ===\n" +
                "Books:\n   " + booksStr + "\n" +
                "Readers:\n   " + readersStr;
    }
}
