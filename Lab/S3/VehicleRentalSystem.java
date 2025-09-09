class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;

    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static String companyName = "Default Rentals";
    private static int rentalDays = 0;

    public Vehicle(String vehicleId, String brand, String model, double rentPerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        totalVehicles++;
    }

    public double rentVehicle(int days) {
        if (!isAvailable) {
            System.out.println("Vehicle " + vehicleId + " is not available.");
            return 0;
        }
        double rent = calculateRent(days);
        isAvailable = false;
        rentalDays += days;
        return rent;
    }

    public void returnVehicle() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("Vehicle " + vehicleId + " has been returned.");
        } else {
            System.out.println("Vehicle " + vehicleId + " was not rented.");
        }
    }

    public double calculateRent(int days) {
        double rent = rentPerDay * days;
        totalRevenue += rent;
        return rent;
    }

    public void displayVehicleInfo() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Rent per Day: " + rentPerDay);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
        System.out.println("------------------------");
    }

    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        if (rentalDays == 0) return 0;
        return totalRevenue / rentalDays;
    }

    public static void displayCompanyStats() {
        System.out.println("Company: " + companyName);
        System.out.println("Total Vehicles: " + totalVehicles);
        System.out.println("Total Revenue: " + totalRevenue);
        System.out.println("Average Rent per Day: " + getAverageRentPerDay());
        System.out.println("Total Rental Days: " + rentalDays);
        System.out.println("========================");
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle.setCompanyName("ZoomCar Rentals");

        Vehicle v1 = new Vehicle("V001", "Toyota", "Innova", 1500);
        Vehicle v2 = new Vehicle("V002", "Honda", "City", 1200);
        Vehicle v3 = new Vehicle("V003", "Suzuki", "Swift", 1000);

        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        double rent1 = v1.rentVehicle(3);
        System.out.println("Rent for V001: " + rent1);

        double rent2 = v2.rentVehicle(5);
        System.out.println("Rent for V002: " + rent2);

        v1.returnVehicle();
        v1.displayVehicleInfo();

        Vehicle.displayCompanyStats();
    }
}
