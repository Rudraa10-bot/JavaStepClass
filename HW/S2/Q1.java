import java.util.*;

public class Q1{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step a: Take user input
        System.out.println("=== SIMPLE SPELL CHECKER ===");
        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();

        // Example dictionary
        String[] dictionary = {"java", "is", "a", "powerful", "programming", "language"};

        // Extract words
        String[] words = extractWords(sentence);

        System.out.println("\n--- SPELL CHECK REPORT ---");
        System.out.printf("%-15s %-15s %-10s %-12s\n", "Word", "Suggestion", "Distance", "Status");
        System.out.println("---------------------------------------------------------");

        for (String word : words) {
            if (word.isEmpty()) continue;
            String suggestion = findClosestWord(word.toLowerCase(), dictionary);
            int distance = suggestion.equalsIgnoreCase(word) ? 0 : calculateDistance(word.toLowerCase(), suggestion);
            String status = (distance == 0) ? "Correct" : "Misspelled";
            System.out.printf("%-15s %-15s %-10d %-12s\n", word, suggestion, distance, status);
        }

        scanner.close();
    }

    // b. Method to extract words without split()
    public static String[] extractWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (!Character.isLetter(ch)) {
                if (start < i) {
                    words.add(text.substring(start, i));
                }
                start = i + 1;
            }
        }
        if (start < text.length()) {
            words.add(text.substring(start));
        }
        return words.toArray(new String[0]);
    }

    // c. Method to calculate string distance (basic edit distance)
    public static int calculateDistance(String w1, String w2) {
        int[][] dp = new int[w1.length() + 1][w2.length() + 1];

        for (int i = 0; i <= w1.length(); i++) {
            for (int j = 0; j <= w2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j; // insertions
                } else if (j == 0) {
                    dp[i][j] = i; // deletions
                } else if (w1.charAt(i - 1) == w2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[w1.length()][w2.length()];
    }

    // d. Method to find closest dictionary word
    public static String findClosestWord(String word, String[] dictionary) {
        String closest = word;
        int minDistance = Integer.MAX_VALUE;

        for (String dictWord : dictionary) {
            int distance = calculateDistance(word, dictWord);
            if (distance < minDistance) {
                minDistance = distance;
                closest = dictWord;
            }
        }

        return (minDistance <= 2) ? closest : word;
    }
}
