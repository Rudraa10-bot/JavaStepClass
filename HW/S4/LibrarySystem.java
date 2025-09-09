// Library Book Management System

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    // 1. Default constructor → empty book
    public Book() {
        this.title = "Unknown";
        this.author = "Unknown";
        this.isbn = "0000";
        this.isAvailable = true; // By default available
    }

    // 2. Constructor with title and author
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isbn = "0000"; // default ISBN
        this.isAvailable = true;
    }

    // 3. Constructor with all details
    public Book(String title, String author, String isbn, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = isAvailable;
    }

    // Method to borrow book
    public void borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println(title + " has been borrowed.");
        } else {
            System.out.println(title + " is not available right now.");
        }
    }

    // Method to return book
    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println(title + " has been returned.");
        } else {
            System.out.println(title + " was not borrowed.");
        }
    }

    // Method to display book info
    public void displayBookInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("------------------------");
    }
}

// Main class
public class LibrarySystem {
    public static void main(String[] args) {
        // Using default constructor
        Book b1 = new Book();

        // Using constructor with title & author
        Book b2 = new Book("1984", "George Orwell");

        // Using full constructor
        Book b3 = new Book("The Hobbit", "J.R.R. Tolkien", "12345", true);

        // Borrow & Return operations
        b2.borrowBook();
        b2.returnBook();
        b3.borrowBook();

        // Display all books
        b1.displayBookInfo();
        b2.displayBookInfo();
        b3.displayBookInfo();
    }
}
