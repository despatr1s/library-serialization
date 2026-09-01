# Library Management System (Java Serialization)

A small object model for a library — books, authors, readers, and lending —
with full state persistence via Java's built-in object serialization.

## Overview

The system models a lending library:

- `Author` — first/last name.
- `Book` — title, year, edition, and a list of `Author`s.
- `Reader` — a registered library member with a list of currently borrowed books.
- `Library` — owns the collection of books and registered readers, and
  handles issuing a book from the shelf to a reader.
- `LibraryDriver` — a runnable demo: builds a sample library, issues some
  books, then serializes the whole object graph to disk and deserializes it
  back to prove state was preserved.

## Architecture

```
Library
 ├── List<Book>
 │     └── List<Author>
 └── List<Reader>
       └── List<Book>   (borrowed books)
```

All model classes implement `Serializable`, so `ObjectOutputStream` can
serialize the entire object graph (library → books → authors, library →
readers → borrowed books) in one call, and `ObjectInputStream` restores it
intact, including cross-references between objects.

## Running it

```bash
javac *.java
java LibraryDriver
```

This will:
1. Build a sample library with 3 books, 3 authors, and 2 readers.
2. Issue two books to readers.
3. Print the library state.
4. Serialize the library to `library.ser`.
5. Deserialize it back into a new object and print it — demonstrating that
   the full state (including which reader is holding which book) survived
   the round trip.

## Notes

- `serialVersionUID` is explicitly declared on every model class, which is
  good practice for classes that will be serialized long-term (protects
  against `InvalidClassException` if the class is later modified).
- Default no-arg constructors are provided on every class specifically to
  satisfy the deserialization contract, while still initializing internal
  lists to avoid `NullPointerException`.
