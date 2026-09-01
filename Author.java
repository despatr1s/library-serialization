import java.io.Serializable;

public class Author implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;

    // Default constructor is required for deserialization
    public Author() {}

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
