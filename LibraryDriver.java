import java.io.*;

public class LibraryDriver {

    // серіалізація
    // ObjectOutputStream це потік який перетворює об'єкт в байти і записує у файл
    public static void serialize(Library library, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(library);
            System.out.println("Бібліотеку збережено у файл: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка серіалізації: " + e.getMessage());
        }
    }

    // десеріалізація
    // ObjectInputStream зворотній процес, читає байти з файлу і відновлює об'єкт
    public static Library deserialize(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Library library = (Library) ois.readObject(); // (Library) — це каст, бо readObject повертає Object
            System.out.println("Бібліотеку відновлено з файлу: " + filename);
            return library;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Помилка десеріалізації: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {

        Author shevchenko = new Author("Тарас", "Шевченко");
        Author franko = new Author("Іван", "Франко");
        Author lesia = new Author("Леся", "Українка");

        Book kobzar = new Book("Кобзар", new java.util.ArrayList<>(), 1840, 1);
        kobzar.addAuthor(shevchenko);

        Book lisovaPisnia = new Book("Лісова пісня", new java.util.ArrayList<>(), 1911, 2);
        lisovaPisnia.addAuthor(lesia);

        Book zakhar = new Book("Захар Беркут", new java.util.ArrayList<>(), 1883, 3);
        zakhar.addAuthor(franko);

        Reader reader1 = new Reader("Олег", "Коваль", 1);
        Reader reader2 = new Reader("Марія", "Бондар", 2);

        Library library = new Library("Національна бібліотека");
        library.addBook(kobzar);
        library.addBook(lisovaPisnia);
        library.addBook(zakhar);
        library.registerReader(reader1);
        library.registerReader(reader2);

        System.out.println("=== СТАН ДО ВИДАЧІ КНИГ ===");
        System.out.println(library);

        System.out.println("\n=== ВИДАЧА КНИГ ===");
        library.issueBook(reader1, kobzar);
        library.issueBook(reader2, lisovaPisnia);

        System.out.println("\n=== СТАН ПІСЛЯ ВИДАЧІ КНИГ ===");
        System.out.println(library);

        // Серіалізуємо - зберігаємо у файл
        System.out.println("\n=== СЕРІАЛІЗАЦІЯ ===");
        serialize(library, "library.ser");

        // Десеріалізуємо - відновлюємо з файлу
        System.out.println("\n=== ДЕСЕРІАЛІЗАЦІЯ ===");
        Library restoredLibrary = deserialize("library.ser");

        System.out.println("\n=== СТАН ПІСЛЯ ДЕСЕРІАЛІЗАЦІЇ ===");
        System.out.println(restoredLibrary);
    }
}