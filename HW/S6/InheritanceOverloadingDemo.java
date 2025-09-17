// Parent class: BasicMath
class BasicMath {

    // Overloaded calculate methods
    public int calculate(int a, int b) {
        return a + b; // addition
    }

    public int calculate(int a, int b, int c) {
        return a + b + c; // sum of three numbers
    }

    public double calculate(double a, double b) {
        return a * b; // multiplication
    }
}

// Child class: AdvancedMath
class AdvancedMath extends BasicMath {

    // New overloaded method in child class
    public double calculate(double a, double b, double c) {
        return a * b * c; // product of three doubles
    }

    public int calculate(int a) {
        return a * a; // square
    }
}

// Main class to test
public class InheritanceOverloadingDemo {
    public static void main(String[] args) {
        BasicMath bm = new BasicMath();
        AdvancedMath am = new AdvancedMath();

        System.out.println("--- BasicMath ---");
        System.out.println("Sum of 5 and 10: " + bm.calculate(5, 10));
        System.out.println("Sum of 3, 4, 5: " + bm.calculate(3, 4, 5));
        System.out.println("Multiplication of 2.5 and 4.0: " + bm.calculate(2.5, 4.0));

        System.out.println("\n--- AdvancedMath ---");
        System.out.println("Sum of 7 and 8 (inherited): " + am.calculate(7, 8));
        System.out.println("Sum of 1, 2, 3 (inherited): " + am.calculate(1, 2, 3));
        System.out.println("Multiplication of 1.5 and 2.0 (inherited): " + am.calculate(1.5, 2.0));
        System.out.println("Product of 1.2, 2.0, 3.0 (new): " + am.calculate(1.2, 2.0, 3.0));
        System.out.println("Square of 6 (new): " + am.calculate(6));
    }
}
