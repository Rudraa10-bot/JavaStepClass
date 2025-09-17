class Animal {
    protected String species;
    protected String habitat;
    protected int lifespan;
    protected boolean isWildlife;

    public Animal(String species, String habitat, int lifespan, boolean isWildlife) {
        this.species = species;
        this.habitat = habitat;
        this.lifespan = lifespan;
        this.isWildlife = isWildlife;
        System.out.println("Animal constructor: Creating " + species);
    }

    public void eat() {
        System.out.println("Animal is eating");
    }

    public void sleep() {
        System.out.println("Animal is sleeping");
    }

    public void move() {
        System.out.println("Animal is moving");
    }

    public String getAnimalInfo() {
        return "Species: " + species + ", Habitat: " + habitat + ", Lifespan: " + lifespan + " years, Wildlife: " + isWildlife;
    }
}

class Mammal extends Animal {
    private String furColor;
    private boolean hasWarmBlood;
    private int gestationPeriod;

    public Mammal(String species, String habitat, int lifespan, boolean isWildlife, String furColor, int gestationPeriod) {
        super(species, habitat, lifespan, isWildlife);
        this.furColor = furColor;
        this.hasWarmBlood = true;
        this.gestationPeriod = gestationPeriod;
        System.out.println("Mammal constructor: Adding mammal traits");
    }

    @Override
    public void move() {
        super.move();
        System.out.println("Mammal is walking/running");
    }

    public void nurse() {
        System.out.println("Mammal is nursing offspring");
    }

    public void regulateTemperature() {
        System.out.println("Maintaining body temperature");
    }
}

class Dog extends Mammal {
    private String breed;
    private boolean isDomesticated;
    private int loyaltyLevel;
    private String favoriteActivity;

    public Dog() {
        super("Dog", "Domestic", 13, true, "Brown", 60);
        this.breed = "Unknown";
        this.isDomesticated = true;
        this.loyaltyLevel = 7;
        this.favoriteActivity = "Playing";
        System.out.println("Dog constructor: Creating default dog");
    }

    public Dog(String species, String habitat, int lifespan, boolean isWildlife,
               String furColor, int gestationPeriod, String breed,
               boolean isDomesticated, int loyaltyLevel, String favoriteActivity) {
        super(species, habitat, lifespan, isWildlife, furColor, gestationPeriod);
        this.breed = breed;
        this.isDomesticated = isDomesticated;
        this.loyaltyLevel = loyaltyLevel;
        this.favoriteActivity = favoriteActivity;
        System.out.println("Dog constructor: Creating " + breed + " dog");
    }

    public Dog(Dog other) {
        this(other.species, other.habitat, other.lifespan, other.isWildlife,
             "CopiedFur", 60, other.breed, other.isDomesticated,
             other.loyaltyLevel, other.favoriteActivity);
        System.out.println("Dog copy constructor called");
    }

    @Override
    public void eat() {
        super.eat();
        System.out.println("Dog is eating while wagging tail");
    }

    @Override
    public void move() {
        System.out.println("Dog is running and playing");
    }

    @Override
    public void sleep() {
        System.out.println("Dog is sleeping in doghouse");
    }

    public void bark() {
        System.out.println("Woof! Woof!");
    }

    public void fetch() {
        System.out.println("Dog is fetching the ball");
    }

    public void showLoyalty() {
        System.out.println("Dog's loyalty level: " + loyaltyLevel + "/10");
    }

    public void demonstrateInheritance() {
        eat();
        move();
        sleep();
        nurse();
        regulateTemperature();
        bark();
        fetch();
        showLoyalty();
    }
}

public class MultilevelInheritanceDemo {
    public static void main(String[] args) {
        System.out.println("=== Constructor Chaining Test ===");
        Dog defaultDog = new Dog();
        Dog labrador = new Dog("Dog", "Domestic", 12, true,
                               "Golden", 63, "Labrador",
                               true, 10, "Fetching");
        Dog copyDog = new Dog(labrador);

        System.out.println("\n=== Method Overriding Test ===");
        labrador.eat();
        labrador.move();
        labrador.sleep();

        System.out.println("\n=== Inheritance Demonstration ===");
        labrador.demonstrateInheritance();

        System.out.println("\n=== IS-A Relationship Test ===");
        System.out.println("labrador instanceof Dog: " + (labrador instanceof Dog));
        System.out.println("labrador instanceof Mammal: " + (labrador instanceof Mammal));
        System.out.println("labrador instanceof Animal: " + (labrador instanceof Animal));

        System.out.println("\n=== Animal Info ===");
        System.out.println(labrador.getAnimalInfo());
    }
}
