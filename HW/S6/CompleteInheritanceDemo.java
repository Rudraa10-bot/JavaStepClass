// Base class: Weather
class Weather {
    protected String condition;

    public Weather() {
        System.out.println("Weather default constructor called");
    }

    public Weather(String condition) {
        this.condition = condition;
        System.out.println("Weather parameterized constructor called: " + condition);
    }

    // Method to describe weather
    public void showCondition() {
        System.out.println("Weather condition: " + condition);
    }
}

// Multilevel: Storm → Thunderstorm
class Storm extends Weather {
    protected int windSpeed;

    public Storm(String condition, int windSpeed) {
        super(condition); // call Weather constructor
        this.windSpeed = windSpeed;
        System.out.println("Storm constructor called. Wind Speed: " + windSpeed + " km/h");
    }

    @Override
    public void showCondition() {
        System.out.println("Storm with wind speed: " + windSpeed + " km/h");
    }
}

class Thunderstorm extends Storm {
    private boolean lightning;

    public Thunderstorm(String condition, int windSpeed, boolean lightning) {
        super(condition, windSpeed); // call Storm constructor
        this.lightning = lightning;
        System.out.println("Thunderstorm constructor called. Lightning: " + lightning);
    }

    @Override
    public void showCondition() {
        super.showCondition(); // call Storm version
        System.out.println("Thunderstorm lightning present: " + lightning);
    }
}

// Hierarchical: Sunshine
class Sunshine extends Weather {
    private int uvIndex;

    public Sunshine(String condition, int uvIndex) {
        super(condition);
        this.uvIndex = uvIndex;
        System.out.println("Sunshine constructor called. UV Index: " + uvIndex);
    }

    @Override
    public void showCondition() {
        System.out.println("Sunny weather with UV Index: " + uvIndex);
    }
}

// Main class to test
public class CompleteInheritanceDemo {
    public static void main(String[] args) {
        System.out.println("--- Creating Weather objects ---");
        Weather w1 = new Weather("Cloudy");

        System.out.println("\n--- Creating Storm objects ---");
        Storm s1 = new Storm("Windy", 80);

        System.out.println("\n--- Creating Thunderstorm objects ---");
        Thunderstorm t1 = new Thunderstorm("Heavy Rain", 90, true);

        System.out.println("\n--- Creating Sunshine objects ---");
        Sunshine sun1 = new Sunshine("Bright Sun", 7);

        System.out.println("\n--- Polymorphic array of Weather references ---");
        Weather[] weatherArray = new Weather[4];
        weatherArray[0] = w1;
        weatherArray[1] = s1;
        weatherArray[2] = t1;
        weatherArray[3] = sun1;

        System.out.println("\n--- Showing weather conditions polymorphically ---");
        for (Weather w : weatherArray) {
            System.out.println();
            w.showCondition(); // polymorphic call
        }
    }
}
