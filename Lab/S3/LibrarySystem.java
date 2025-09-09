import java.util.Scanner;

class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;
    private static int totalBooks = 0;
    private static int availableBooks = 0;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.bookId = generateBookId();
        this.isAvailable = true;
        totalBooks++;
        availableBooks++;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getBookId() {
        return bookId;
    }

    public void issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
        }
    }

    public void displayBookInfo() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
        System.out.println("-------------------");
    }

    private static String generateBookId() {
        return String.format("B%03d", totalBooks + 1);
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public static int getAvailableBooks() {
        return availableBooks;
    }
}

class Member {
    private String memberId;
    private String memberName;
    private String[] booksIssued;
    private int bookCount;
    private static int totalMembers = 0;

    public Member(String memberName) {
        this.memberName = memberName;
        this.memberId = generateMemberId();
        this.booksIssued = new String[5];
        this.bookCount = 0;
        totalMembers++;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable() && bookCount < booksIssued.length) {
            book.issueBook();
            booksIssued[bookCount++] = book.getBookId();
            System.out.println(memberName + " borrowed " + book.getBookId());
        } else {
            System.out.println("Cannot borrow book. Either unavailable or limit reached.");
        }
    }

    public void returnBook(String bookId, Book[] books) {
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                booksIssued[i] = booksIssued[bookCount - 1];
                booksIssued[bookCount - 1] = null;
                bookCount--;
                found = true;
                break;
            }
        }
        if (found) {
            for (Book b : books) {
                if (b.getBookId().equals(bookId)) {
                    b.returnBook();
                    System.out.println(memberName + " returned " + bookId);
                    break;
                }
            }
        } else {
            System.out.println("Book not found in issued list.");
        }
    }

    private static String generateMemberId() {
        return String.format("M%03d", totalMembers + 1);
    }

    public void displayMemberInfo() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + memberName);
        System.out.print("Books Issued: ");
        for (int i = 0; i < bookCount; i++) {
            System.out.print(booksIssued[i] + " ");
        }
        System.out.println("\n-------------------");
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of books: ");
        int nb = sc.nextInt();
        sc.nextLine();
        Book[] books = new Book[nb];
        for (int i = 0; i < nb; i++) {
            System.out.print("Enter book title: ");
            String title = sc.nextLine();
            System.out.print("Enter book author: ");
            String author = sc.nextLine();
            books[i] = new Book(title, author);
        }

        System.out.print("Enter number of members: ");
        int nm = sc.nextInt();
        sc.nextLine();
        Member[] members = new Member[nm];
        for (int i = 0; i < nm; i++) {
            System.out.print("Enter member name: ");
            String name = sc.nextLine();
            members[i] = new Member(name);
        }

        System.out.println("\n--- Books Info ---");
        for (Book b : books) b.displayBookInfo();

        System.out.println("\n--- Members Info ---");
        for (Member m : members) m.displayMemberInfo();

        System.out.print("\nEnter member index (0-" + (nm - 1) + ") to borrow: ");
        int mi = sc.nextInt();
        System.out.print("Enter book index (0-" + (nb - 1) + ") to borrow: ");
        int bi = sc.nextInt();
        members[mi].borrowBook(books[bi]);

        System.out.print("\nEnter member index (0-" + (nm - 1) + ") to return: ");
        mi = sc.nextInt();
        System.out.print("Enter book ID to return: ");
        sc.nextLine();
        String bid = sc.nextLine();
        members[mi].returnBook(bid, books);

        System.out.println("\n--- Updated Books Info ---");
        for (Book b : books) b.displayBookInfo();

        System.out.println("Total Books: " + Book.getTotalBooks());
        System.out.println("Available Books: " + Book.getAvailableBooks());
    }
}
