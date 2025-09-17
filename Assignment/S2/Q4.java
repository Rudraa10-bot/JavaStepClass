import java.util.Scanner;

public class Q4{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== ADVANCED STRING ANALYZER ===");

        // Ask user for two strings
        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();

        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();

        // Perform comprehensive comparison analysis
        performAllComparisons(str1, str2);

        // Similarity percentage calculation
        double similarity = calculateSimilarity(str1, str2);
        System.out.printf("Similarity: %.2f%%\n", similarity);

        // Memory usage analysis
        analyzeMemoryUsage(str1, str2);

        // Optimized string processing demo
        String[] sampleInputs = {"Java", "String", "Optimization", "Test"};
        String optimizedResult = optimizedStringProcessing(sampleInputs);
        System.out.println("Optimized String Processing Result: " + optimizedResult);

        // Demonstrate intern()
        demonstrateStringIntern();

        scanner.close();
    }

    // Method to calculate string similarity percentage (Levenshtein Distance)
    public static double calculateSimilarity(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }

        int distance = dp[len1][len2];
        int maxLen = Math.max(len1, len2);
        return ((double) (maxLen - distance) / maxLen) * 100;
    }

    // Method to perform all comparisons
    public static void performAllComparisons(String str1, String str2) {
        System.out.println("\n=== Comparison Results ===");
        System.out.println("Reference Equality (==): " + (str1 == str2));
        System.out.println("Content Equality (equals): " + str1.equals(str2));
        System.out.println("Case-Insensitive Equality: " + str1.equalsIgnoreCase(str2));
        System.out.println("Lexicographic Comparison: " + str1.compareTo(str2));
        System.out.println("Case-Insensitive Lexicographic Comparison: " + str1.compareToIgnoreCase(str2));
    }

    // Method to analyze string memory usage (approximation)
    public static void analyzeMemoryUsage(String... strings) {
        System.out.println("\n=== Memory Usage Analysis ===");
        for (String s : strings) {
            int approxBytes = 40 + s.length() * 2; // Rough estimate
            System.out.println("String: \"" + s + "\" | Length: " + s.length() + " | Approx Memory: " + approxBytes + " bytes");
        }
    }

    // Method to optimize string operations using StringBuilder
    public static String optimizedStringProcessing(String[] inputs) {
        StringBuilder sb = new StringBuilder();
        for (String s : inputs) {
            sb.append(s).append(" ");
        }
        return sb.toString().trim();
    }

    // Method to demonstrate intern() method
    public static void demonstrateStringIntern() {
        System.out.println("\n=== Demonstrating intern() ===");
        String s1 = new String("Java");
        String s2 = "Java";

        System.out.println("Before intern: s1 == s2 ? " + (s1 == s2));

        s1 = s1.intern();
        System.out.println("After intern: s1 == s2 ? " + (s1 == s2));
    }
}
