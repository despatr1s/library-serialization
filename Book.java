import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private ArrayList<Author> authors;
    private int year;
    private int edition;

    // default constructor
    public Book() {
        this.authors = new ArrayList<>();
        // initialize right away to avoid a NullPointerException
        // when calling addAuthor() on a freshly created object.
    }

    public Book(String title, ArrayList<Author> authors, int year, int edition) {
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.edition = edition;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public ArrayList<Author> getAuthors() { return authors; }
    public void setAuthors(ArrayList<Author> authors) { this.authors = authors; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getEdition() { return edition; }
    public void setEdition(int edition) { this.edition = edition; }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    @Override
    public String toString() {
        String authorsStr = authors.stream()
                .map(Author::toString)
                .collect(java.util.stream.Collectors.joining(", "));
        return "\"" + title + "\" (" + year + ", ed." + edition + ") - " + authorsStr;
    }
}
