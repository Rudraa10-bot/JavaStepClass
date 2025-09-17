import java.util.Scanner;

public class GameController {
    private String controllerBrand;
    private String connectionType;
    private boolean hasVibration;
    private int batteryLevel;
    private double sensitivity;

    public GameController() {
        this.controllerBrand = "GenericPad";
        this.connectionType = "USB";
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }

    public GameController(String controllerBrand, String connectionType, boolean hasVibration, int batteryLevel, double sensitivity) {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = hasVibration;
        this.batteryLevel = (batteryLevel < 0) ? 0 : Math.min(batteryLevel, 100);
        this.sensitivity = (sensitivity < 0.1) ? 0.1 : Math.min(sensitivity, 3.0);
    }

    public GameController(String brand, String connectionType) {
        this.controllerBrand = brand;
        this.connectionType = connectionType;
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }

    public void calibrateController() {
        System.out.println("Calibrating " + controllerBrand + " controller...");
    }

    public void displayConfiguration() {
        System.out.println("Brand: " + controllerBrand);
        System.out.println("Connection: " + connectionType);
        System.out.println("Vibration: " + (hasVibration ? "Enabled" : "Disabled"));
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Sensitivity: " + sensitivity);
        System.out.println("-------------------");
    }

    public void testVibration() {
        if (hasVibration) {
            System.out.println("*BUZZ* Vibration test successful!");
        } else {
            System.out.println("Vibration disabled on this controller.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        GameController c1 = new GameController();

        System.out.print("Enter brand: ");
        String brand = sc.nextLine();
        System.out.print("Enter connection type: ");
        String connection = sc.nextLine();
        System.out.print("Has vibration (true/false): ");
        boolean vibration = sc.nextBoolean();
        System.out.print("Enter battery level (0-100): ");
        int battery = sc.nextInt();
        System.out.print("Enter sensitivity (0.1-3.0): ");
        double sens = sc.nextDouble();
        sc.nextLine();

        GameController c2 = new GameController(brand, connection, vibration, battery, sens);

        System.out.print("Enter brand for convenience controller: ");
        String brand2 = sc.nextLine();
        System.out.print("Enter connection type: ");
        String connection2 = sc.nextLine();

        GameController c3 = new GameController(brand2, connection2);

        System.out.println("=== GAMING CONTROLLER SETUP ===");
        c1.displayConfiguration();
        c1.calibrateController();
        c1.testVibration();

        c2.displayConfiguration();
        c2.calibrateController();
        c2.testVibration();

        c3.displayConfiguration();
        c3.calibrateController();
        c3.testVibration();
    }
}
