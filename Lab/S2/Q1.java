import java.util.Scanner;

public class Q1{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take inputs
        System.out.print("Enter the main text: ");
        String text = scanner.nextLine();

        System.out.print("Enter the substring to find: ");
        String find = scanner.nextLine();

        System.out.print("Enter the replacement substring: ");
        String replace = scanner.nextLine();

        // Find occurrences
        int[] positions = findOccurrences(text, find);
        System.out.println("Found " + countValidPositions(positions) + " occurrences.");

        // Replace manually
        String manualResult = manualReplace(text, find, replace);

        // Compare with built-in replace()
        boolean isSame = compareWithBuiltIn(text, find, replace, manualResult);

        // Display results
        System.out.println("\n=== Results ===");
        System.out.println("Manual Replacement Result: " + manualResult);
        System.out.println("Built-in Replacement Result: " + text.replace(find, replace));
        System.out.println("Comparison: " + (isSame ? "MATCH ✅" : "DIFFER ❌"));

        scanner.close();
    }

    // (b) Find all occurrences using indexOf()
    public static int[] findOccurrences(String text, String find) {
        int[] positions = new int[text.length()];
        int count = 0;
        int index = text.indexOf(find);

        while (index != -1) {
            positions[count++] = index;
            index = text.indexOf(find, index + find.length());
        }
        return positions;
    }

    // Helper: Count how many positions are valid (non-zero)
    public static int countValidPositions(int[] positions) {
        int count = 0;
        for (int pos : positions) {
            if (pos > 0) count++;
        }
        return count;
    }

    // (c) Manual replacement
    public static String manualReplace(String text, String find, String replace) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < text.length()) {
            // Check if substring matches at position i
            if (i + find.length() <= text.length() &&
                    text.substring(i, i + find.length()).equals(find)) {
                result.append(replace);  // insert replacement
                i += find.length();      // skip original substring
            } else {
                result.append(text.charAt(i)); // copy original char
                i++;
            }
        }

        return result.toString();
    }

    // (d) Compare with built-in replace()
    public static boolean compareWithBuiltIn(String text, String find, String replace, String manualResult) {
        String builtIn = text.replace(find, replace);
        return builtIn.equals(manualResult);
    }
}
