// Movie Ticket Booking System

class MovieTicket {
    private String movieName;
    private String theatreName;
    private int seatNumber;
    private double price;

    // 1. Default constructor
    public MovieTicket() {
        this.movieName = "Unknown";
        this.theatreName = "Not Assigned";
        this.seatNumber = 0;
        this.price = 0.0;
    }

    // 2. Constructor with movie name → default price = 200
    public MovieTicket(String movieName) {
        this.movieName = movieName;
        this.theatreName = "Not Assigned";
        this.seatNumber = 0;
        this.price = 200.0;
    }

    // 3. Constructor with movie name and seat number → default theatre = "PVR"
    public MovieTicket(String movieName, int seatNumber) {
        this.movieName = movieName;
        this.theatreName = "PVR";
        this.seatNumber = seatNumber;
        this.price = 250.0; // assume default price for PVR
    }

    // 4. Full constructor
    public MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    // Method to print ticket details
    public void printTicket() {
        System.out.println("Movie: " + movieName);
        System.out.println("Theatre: " + theatreName);
        System.out.println("Seat No: " + seatNumber);
        System.out.println("Price: ₹" + price);
        System.out.println("-----------------------");
    }
}

// Main class
public class MovieTicketBooking {
    public static void main(String[] args) {
        // Using default constructor
        MovieTicket t1 = new MovieTicket();

        // Using constructor with movie name
        MovieTicket t2 = new MovieTicket("Inception");

        // Using constructor with movie name + seat number
        MovieTicket t3 = new MovieTicket("Interstellar", 12);

        // Using full constructor
        MovieTicket t4 = new MovieTicket("Avengers: Endgame", "INOX", 25, 350.0);

        // Print tickets
        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
        t4.printTicket();
    }
}
