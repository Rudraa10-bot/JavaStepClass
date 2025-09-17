// Parent class: Tool
class Tool {
    private String serialNumber;   // private: not accessible directly in child
    protected String type;         // protected: accessible in child
    public String brand;           // public: accessible anywhere

    // Constructor
    public Tool(String serialNumber, String type, String brand) {
        this.serialNumber = serialNumber;
        this.type = type;
        this.brand = brand;
    }

    // Getter for private field
    public String getSerialNumber() {
        return serialNumber;
    }

    // Method to show all fields
    public void showToolInfo() {
        System.out.println("Serial Number: " + serialNumber);
        System.out.println("Type: " + type);
        System.out.println("Brand: " + brand);
    }
}

// Child class: Hammer
class Hammer extends Tool {
    private int weight;

    public Hammer(String serialNumber, String type, String brand, int weight) {
        super(serialNumber, type, brand);
        this.weight = weight;
    }

    // Method to show accessible fields in child
    public void showHammerInfo() {
        System.out.println("---- Accessing fields from Hammer ----");

        // System.out.println("Serial Number: " + serialNumber); // Not accessible (private)
        System.out.println("Serial Number (via getter): " + getSerialNumber()); // Accessible via getter

        System.out.println("Type (protected): " + type);    // Accessible (protected)
        System.out.println("Brand (public): " + brand);    // Accessible (public)
        System.out.println("Weight: " + weight);
    }
}

// Main class to test
public class AccessModifiersDemo {
    public static void main(String[] args) {
        Hammer h = new Hammer("SN1234", "Claw Hammer", "Stanley", 2);
        h.showHammerInfo();
    }
}
