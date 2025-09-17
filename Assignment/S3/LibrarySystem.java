import java.util.Scanner;

// Book class
class Book {
    private String title;
    private String author;
    private String isbn;
    private double price;
    private int quantity;

    public Book(String title, String author, String isbn, double price, int quantity) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    // Methods
    public void addStock(int qty) {
        this.quantity += qty;
    }

    public double totalValue() {
        return price * quantity;
    }

    public void displayBook() {
        System.out.println("Title: " + title + ", Author: " + author +
                ", ISBN: " + isbn + ", Price: " + price +
                ", Quantity: " + quantity);
    }
}

// Library class
class Library {
    private String libraryName;
    private Book[] books;
    private int totalBooks;

    public Library(String libraryName, int capacity) {
        this.libraryName = libraryName;
        this.books = new Book[capacity]; // fixed size
        this.totalBooks = 0;
    }

    // Add a book
    public void addBook(Book book) {
        if (totalBooks < books.length) {
            books[totalBooks++] = book;
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Library is full. Cannot add more books.");
        }
    }

    // Search book by title or author
    public void searchBook(String keyword) {
        boolean found = false;
        for (int i = 0; i < totalBooks; i++) {
            if (books[i].getTitle().equalsIgnoreCase(keyword) ||
                    books[i].getAuthor().equalsIgnoreCase(keyword)) {
                books[i].displayBook();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found with keyword: " + keyword);
        }
    }

    // Display all books
    public void displayInventory() {
        if (totalBooks == 0) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("\n--- " + libraryName + " Inventory ---");
        for (int i = 0; i < totalBooks; i++) {
            books[i].displayBook();
        }
    }

    // Calculate total value of all books
    public double calculateTotalValue() {
        double totalValue = 0;
        for (int i = 0; i < totalBooks; i++) {
            totalValue += books[i].totalValue();
        }
        return totalValue;
    }
}

// Main system
public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library("City Central Library", 50);

        int choice;
        do {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Calculate Total Value");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: // Add Book
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Enter price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    sc.nextLine();
                    Book newBook = new Book(title, author, isbn, price, qty);
                    library.addBook(newBook);
                    break;

                case 2: // Search Book
                    System.out.print("Enter title or author to search: ");
                    String keyword = sc.nextLine();
                    library.searchBook(keyword);
                    break;

                case 3: // Display All Books
                    library.displayInventory();
                    break;

                case 4: // Calculate Total Value
                    System.out.println("Total value of all books: $" + library.calculateTotalValue());
                    break;

                case 5: // Exit
                    System.out.println("Exiting Library System...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
