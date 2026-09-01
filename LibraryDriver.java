import java.io.*;

public class LibraryDriver {

    // serialization
    // ObjectOutputStream converts an object into bytes and writes it to a file
    public static void serialize(Library library, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(library);
            System.out.println("Library saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Serialization error: " + e.getMessage());
        }
    }

    // deserialization
    // ObjectInputStream is the reverse process: reads bytes from a file and restores the object
    public static Library deserialize(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Library library = (Library) ois.readObject(); // cast, since readObject returns Object
            System.out.println("Library restored from file: " + filename);
            return library;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Deserialization error: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {

        Author shevchenko = new Author("Taras", "Shevchenko");
        Author franko = new Author("Ivan", "Franko");
        Author lesia = new Author("Lesia", "Ukrainka");

        Book kobzar = new Book("Kobzar", new java.util.ArrayList<>(), 1840, 1);
        kobzar.addAuthor(shevchenko);

        Book lisovaPisnia = new Book("Forest Song", new java.util.ArrayList<>(), 1911, 2);
        lisovaPisnia.addAuthor(lesia);

        Book zakhar = new Book("Zakhar Berkut", new java.util.ArrayList<>(), 1883, 3);
        zakhar.addAuthor(franko);

        Reader reader1 = new Reader("Oleh", "Koval", 1);
        Reader reader2 = new Reader("Maria", "Bondar", 2);

        Library library = new Library("National Library");
        library.addBook(kobzar);
        library.addBook(lisovaPisnia);
        library.addBook(zakhar);
        library.registerReader(reader1);
        library.registerReader(reader2);

        System.out.println("=== STATE BEFORE ISSUING BOOKS ===");
        System.out.println(library);

        System.out.println("\n=== ISSUING BOOKS ===");
        library.issueBook(reader1, kobzar);
        library.issueBook(reader2, lisovaPisnia);

        System.out.println("\n=== STATE AFTER ISSUING BOOKS ===");
        System.out.println(library);

        // Serialize - save to file
        System.out.println("\n=== SERIALIZATION ===");
        serialize(library, "library.ser");

        // Deserialize - restore from file
        System.out.println("\n=== DESERIALIZATION ===");
        Library restoredLibrary = deserialize("library.ser");

        System.out.println("\n=== STATE AFTER DESERIALIZATION ===");
        System.out.println(restoredLibrary);
    }
}
