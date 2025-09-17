import java.util.Scanner;

// Base class
public class Vehicle {
    protected String make;
    protected String model;
    protected int year;
    protected double fuelLevel;

    public Vehicle(String make, String model, int year, double fuelLevel) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelLevel = fuelLevel;
    }

    public void startVehicle() {
        System.out.println(year + " " + make + " " + model + " started.");
    }

    public void stopVehicle() {
        System.out.println(year + " " + make + " " + model + " stopped.");
    }

    public void refuel(double amount) {
        fuelLevel += amount;
        System.out.println(make + " " + model + " refueled. Current fuel: " + fuelLevel + " liters.");
    }

    public void displayVehicleInfo() {
        System.out.println("Vehicle Info:");
        System.out.println(" Make: " + make);
        System.out.println(" Model: " + model);
        System.out.println(" Year: " + year);
        System.out.println(" Fuel Level: " + fuelLevel + " liters");
        System.out.println("----------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vehicles: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        Vehicle[] vehicles = new Vehicle[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Vehicle " + (i + 1));
            System.out.print("Type (Car/Truck/Motorcycle): ");
            String type = sc.nextLine();

            System.out.print("Make: ");
            String make = sc.nextLine();
            System.out.print("Model: ");
            String model = sc.nextLine();
            System.out.print("Year: ");
            int year = sc.nextInt();
            System.out.print("Fuel Level: ");
            double fuel = sc.nextDouble();
            sc.nextLine(); // consume newline

            // Create specific vehicle type
            if (type.equalsIgnoreCase("Car")) {
                System.out.print("Number of doors: ");
                int doors = sc.nextInt();
                sc.nextLine();
                vehicles[i] = new Car(make, model, year, fuel, doors);

            } else if (type.equalsIgnoreCase("Truck")) {
                System.out.print("Payload capacity (kg): ");
                double payload = sc.nextDouble();
                sc.nextLine();
                vehicles[i] = new Truck(make, model, year, fuel, payload);

            } else if (type.equalsIgnoreCase("Motorcycle")) {
                System.out.print("Has fairing? (true/false): ");
                boolean fairing = sc.nextBoolean();
                sc.nextLine();
                vehicles[i] = new Motorcycle(make, model, year, fuel, fairing);

            } else {
                System.out.println("Unknown type, creating as generic Vehicle.");
                vehicles[i] = new Vehicle(make, model, year, fuel);
            }
        }

        // Demonstrate polymorphism
        System.out.println("\n--- Vehicle Records ---");
        for (Vehicle v : vehicles) {
            v.startVehicle();
            v.displayVehicleInfo();
            v.refuel(10);
            v.stopVehicle();
            System.out.println();
        }

        sc.close();
    }
}

// Subclass: Car
class Car extends Vehicle {
    private int doors;

    public Car(String make, String model, int year, double fuelLevel, int doors) {
        super(make, model, year, fuelLevel);
        this.doors = doors;
    }


    public void displayVehicleInfo() {
        super.displayVehicleInfo();
        System.out.println(" Doors: " + doors);
        System.out.println("----------------------------");
    }
}

// Subclass: Truck
class Truck extends Vehicle {
    private double payloadCapacity;

    public Truck(String make, String model, int year, double fuelLevel, double payloadCapacity) {
        super(make, model, year, fuelLevel);
        this.payloadCapacity = payloadCapacity;
    }


    public void displayVehicleInfo() {
        super.displayVehicleInfo();
        System.out.println(" Payload Capacity: " + payloadCapacity + " kg");
        System.out.println("----------------------------");
    }
}

// Subclass: Motorcycle
class Motorcycle extends Vehicle {
    private boolean hasFairing;

    public Motorcycle(String make, String model, int year, double fuelLevel, boolean hasFairing) {
        super(make, model, year, fuelLevel);
        this.hasFairing = hasFairing;
    }


    public void displayVehicleInfo() {
        super.displayVehicleInfo();
        System.out.println(" Has Fairing: " + (hasFairing ? "Yes" : "No"));
        System.out.println("----------------------------");
    }
}
