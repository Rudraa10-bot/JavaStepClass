import java.util.Random;

public class Vehicle{
    protected String brand;
    protected String model;
    protected int year;
    protected String engineType;

    private String registrationNumber;
    private boolean isRunning;

    public Vehicle(){
        this.brand="Unknown";
        this.model="Unknown";
        this.year=0;
        this.engineType="Unkown";
        this.registrationNumber=generateRandomRegNumber();
        this.isRunning=false;
        System.out.println("Vehicle Default Constructor Called.");
    }

    public Vehicle(String brand, String model, int year, String engineType){
        this.brand=brand;
        this.model=model;
        this.year=year;
        this.engineType=engineType;
        this.registrationNumber=generateRandomRegNumber();
        this.isRunning=false;
        System.out.println("Vehicle parameterized constructor called.");
    }

    private String generateRandomRegNumber(){
        Random rand = new Random();
        return "REG" + (1000+rand.nextInt(9000));
    }

    public void start(){
        isRunning=true;
        System.out.println("Vehicle Started");
    }

    public void stop(){
        isRunning=false;
        System.out.println("Vehicle stopped");
    }

    public String getVehicleInfo(){
        return "Brand:"+brand+", Model:"+model+", Year:"+year+", Engine"+engineType+", Reg No:"+registrationNumber+", Running:"+isRunning;
    }

    public void displaySpecs(){
        System.out.println("Vehicle Specs:");
        System.out.println("Brand:"+brand);
        System.out.println("Model:"+model);
        System.out.println("Year:"+year);
        System.out.println("Engine:"+engineType);
    }

    public String getRegistrationNumber(){
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber){
        this.registrationNumber=registrationNumber;
    }

    public boolean isRunning(){
        return isRunning;
    }
}

     class Car extends Vehicle{
        private int numberOfDoors;
        private String  fuelType;
        private String transmissionType;

        public Car(){
            super();
            this.numberOfDoors=4;
            this.fuelType="Petrol";
            this.transmissionType="Manual";
            System.out.println("Car default constructor called.");
        }

        public Car(String brand, String model, int year, String engineType,
               int numberOfDoors, String fuelType, String transmissionType){
                super(brand, model, year, engineType);
                this.numberOfDoors=numberOfDoors;
                this.fuelType=fuelType;
                this.transmissionType=transmissionType;
                System.out.println("Parameterized Car Constructor called.");
        }

        public void start(){
            super.start();
            System.out.println("Car-specific startup sequence: Buckle up and enjoy the drive!");
        }

        public void displaySpecs(){
            super.displaySpecs();
            System.out.println("Car Specs:");
            System.out.println("Doors:"+numberOfDoors);
            System.out.println("Fuel Type:"+fuelType);
            System.out.println("Transmission:"+transmissionType);
        }

        public void openTrunk(){
            System.out.println("Trunk opened.");
        }

        public void playRadio(){
            System.out.println("Radio is playing music.");
        }

        public static void main(String[] args){
            System.out.println("Default Constructor Test");
            Car defaultCar=new Car();
            defaultCar.displaySpecs();
            defaultCar.start();
            defaultCar.openTrunk();
            defaultCar.playRadio();
            defaultCar.stop();
            System.out.println();


            System.out.println("Parameterized Constructor Test");
            Car paraCar=new Car("Toyota", "Corolla", 2023, "Hybrid", 4, "Petrol", "Automatic");
            paraCar.displaySpecs();
            paraCar.start();
            paraCar.openTrunk();
            paraCar.playRadio();
            paraCar.stop();
            System.out.println();

            System.out.println("Accessing Protected Fields");
            System.out.println("Brand:"+paraCar.brand);
            System.out.println("Model:"+paraCar.model);
            System.out.println();

            System.out.println("Accessing Getter for Private Fields");
            System.out.println("Registration Number:"+paraCar.getRegistrationNumber());

        }
    }