// Fitness Tracker App Simulation

class Workout {
    private String activityName;
    private int durationInMinutes;
    private int caloriesBurned;

    // 1. Default constructor → "Walking", 30 mins, 100 calories
    public Workout() {
        this.activityName = "Walking";
        this.durationInMinutes = 30;
        this.caloriesBurned = 100;
    }

    // 2. Constructor with activity name → assigns default duration = 30 mins
    public Workout(String activityName) {
        this.activityName = activityName;
        this.durationInMinutes = 30;
        this.caloriesBurned = durationInMinutes * 5; // formula
    }

    // 3. Constructor with activity and duration → calories = duration × 5
    public Workout(String activityName, int durationInMinutes) {
        this.activityName = activityName;
        this.durationInMinutes = durationInMinutes;
        this.caloriesBurned = durationInMinutes * 5;
    }

    // Method to display workout details
    public void displayWorkout() {
        System.out.println("Activity: " + activityName);
        System.out.println("Duration: " + durationInMinutes + " minutes");
        System.out.println("Calories Burned: " + caloriesBurned);
        System.out.println("------------------------");
    }
}

// Main class
public class FitnessTracker {
    public static void main(String[] args) {
        // Using default constructor
        Workout w1 = new Workout();

        // Constructor with only activity name
        Workout w2 = new Workout("Running");

        // Constructor with activity and duration
        Workout w3 = new Workout("Cycling", 45);

        // Display all workouts
        w1.displayWorkout();
        w2.displayWorkout();
        w3.displayWorkout();
    }
}
