import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Model Class representing a single Book
class Book {
    private final int id;
    private final String title;
    private final String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        this.isIssued = issued;
    }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Title: %-25s | Author: %-20s | Status: %s",
                id, title, author, (isIssued ? "Issued" : "Available"));
    }
}

// Controller Class managing library operations using java.util
class LibraryManager {
    private final Map<Integer, Book> bookCatalog = new HashMap<>();

    public boolean addBook(Book book) {
        if (bookCatalog.containsKey(book.getId())) {
            return false;
        }
        bookCatalog.put(book.getId(), book);
        return true;
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(bookCatalog.values());
    }

    public Book findBookById(int id) {
        return bookCatalog.get(id);
    }

    public List<Book> searchByTitle(String titleQuery) {
        List<Book> results = new ArrayList<>();
        for (Book b : bookCatalog.values()) {
            if (b.getTitle().toLowerCase().contains(titleQuery.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }

    public boolean issueBook(int id) {
        Book book = bookCatalog.get(id);
        if (book != null && !book.isIssued()) {
            book.setIssued(true);
            return true;
        }
        return false;
    }

    public boolean returnBook(int id) {
        Book book = bookCatalog.get(id);
        if (book != null && book.isIssued()) {
            book.setIssued(false);
            return true;
        }
        return false;
    }
}

// Main Execution Class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();
        Scanner scanner = new Scanner(System.in);

        // Pre-populating sample data
        library.addBook(new Book(101, "Effective Java", "Joshua Bloch"));
        library.addBook(new Book(102, "Clean Code", "Robert C. Martin"));
        library.addBook(new Book(103, "Design Patterns", "Erich Gamma"));

        while (true) {
            System.out.println("\n==========================================");
            System.out.println("       LIBRARY MANAGEMENT SYSTEM          ");
            System.out.println("==========================================");
            System.out.println("1. Add New Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Book ID: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine().trim();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine().trim();

                        if (library.addBook(new Book(id, title, author))) {
                            System.out.println("Success: Book added successfully.");
                        } else {
                            System.out.println("Error: A book with ID " + id + " already exists.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: ID must be an integer.");
                    }
                    break;

                case 2:
                    List<Book> books = library.getAllBooks();
                    if (books.isEmpty()) {
                        System.out.println("No books available in the catalog.");
                    } else {
                        System.out.println("\n--- Current Book Catalog ---");
                        for (Book b : books) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter title keyword to search: ");
                    String query = scanner.nextLine().trim();
                    List<Book> found = library.searchByTitle(query);
                    if (found.isEmpty()) {
                        System.out.println("No books matched the query: " + query);
                    } else {
                        System.out.println("\n--- Search Results ---");
                        for (Book b : found) {
                            System.out.println(b);
                        }
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter Book ID to issue: ");
                        int issueId = Integer.parseInt(scanner.nextLine().trim());
                        if (library.issueBook(issueId)) {
                            System.out.println("Success: Book ID " + issueId + " has been issued.");
                        } else {
                            Book b = library.findBookById(issueId);
                            if (b == null) {
                                System.out.println("Error: Book ID " + issueId + " does not exist.");
                            } else {
                                System.out.println("Error: Book is already issued.");
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: ID must be an integer.");
                    }
                    break;

                case 5:
                    try {
                        System.out.print("Enter Book ID to return: ");
                        int returnId = Integer.parseInt(scanner.nextLine().trim());
                        if (library.returnBook(returnId)) {
                            System.out.println("Success: Book ID " + returnId + " has been returned.");
                        } else {
                            Book b = library.findBookById(returnId);
                            if (b == null) {
                                System.out.println("Error: Book ID " + returnId + " does not exist.");
                            } else {
                                System.out.println("Error: Book is not currently issued.");
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: ID must be an integer.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid selection! Enter a number between 1 and 6.");
            }
        }
    }
}
