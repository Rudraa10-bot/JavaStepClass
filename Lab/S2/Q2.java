import java.util.Scanner;

public class Q2{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input
        System.out.print("Enter a text: ");
        String text = scanner.nextLine();

        // Manual conversions
        String upperManual = toUpperCaseManual(text);
        String lowerManual = toLowerCaseManual(text);
        String titleManual = toTitleCaseManual(text);

        // Built-in conversions
        String upperBuiltIn = text.toUpperCase();
        String lowerBuiltIn = text.toLowerCase();

        // Display results
        System.out.println("\n=== Case Conversion Results ===");
        System.out.printf("%-20s %-30s %-30s\n", "Conversion", "Manual Result", "Built-in Result");
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf("%-20s %-30s %-30s\n", "UPPERCASE", upperManual, upperBuiltIn);
        System.out.printf("%-20s %-30s %-30s\n", "lowercase", lowerManual, lowerBuiltIn);
        System.out.printf("%-20s %-30s %-30s\n", "Title Case", titleManual, "(no direct built-in)");

        scanner.close();
    }

    // (b) Convert to UPPERCASE manually using ASCII
    public static String toUpperCaseManual(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                result.append((char) (ch - 32));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    // (c) Convert to lowercase manually using ASCII
    public static String toLowerCaseManual(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                result.append((char) (ch + 32));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    // (d) Convert to Title Case manually using ASCII
    public static String toTitleCaseManual(String text) {
        StringBuilder result = new StringBuilder();
        boolean newWord = true;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isWhitespace(ch)) {
                result.append(ch);
                newWord = true;
            } else {
                if (newWord) {
                    // Convert first letter to uppercase
                    if (ch >= 'a' && ch <= 'z') {
                        result.append((char) (ch - 32));
                    } else {
                        result.append(ch);
                    }
                    newWord = false;
                } else {
                    // Convert rest to lowercase
                    if (ch >= 'A' && ch <= 'Z') {
                        result.append((char) (ch + 32));
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }
}
