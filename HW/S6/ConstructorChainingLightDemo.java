// Parent class: Light
class Light {
    protected String brand;
    protected int wattage;

    // Default constructor
    public Light() {
        System.out.println("Light default constructor called");
    }

    // Constructor with brand
    public Light(String brand) {
        this(); // calls default constructor of Light
        this.brand = brand;
        System.out.println("Light constructor with brand called: " + brand);
    }

    // Constructor with brand and wattage
    public Light(String brand, int wattage) {
        this(brand); // calls constructor with brand
        this.wattage = wattage;
        System.out.println("Light constructor with brand and wattage called: " + wattage + "W");
    }
}

// Child class: LED
class LED extends Light {
    private String color;

    // Default constructor
    public LED() {
        super(); // calls Light default constructor
        System.out.println("LED default constructor called");
    }

    // Constructor with color
    public LED(String color) {
        this(); // calls LED default constructor
        this.color = color;
        System.out.println("LED constructor with color called: " + color);
    }

    // Constructor with brand, wattage, and color
    public LED(String brand, int wattage, String color) {
        super(brand, wattage); // calls Light constructor with brand & wattage
        this.color = color;
        System.out.println("LED constructor with brand, wattage, and color called: " + color);
    }

    // Method to display LED details
    public void showDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Wattage: " + wattage);
        System.out.println("Color: " + color);
    }
}

// Main class to test
public class ConstructorChainingLightDemo {
    public static void main(String[] args) {
        System.out.println("--- Creating Light with brand and wattage ---");
        Light l1 = new Light("Philips", 60);

        System.out.println("\n--- Creating LED with default constructor ---");
        LED led1 = new LED();

        System.out.println("\n--- Creating LED with color ---");
        LED led2 = new LED("Warm White");

        System.out.println("\n--- Creating LED with brand, wattage, and color ---");
        LED led3 = new LED("Syska", 12, "Cool White");
        led3.showDetails();
    }
}
