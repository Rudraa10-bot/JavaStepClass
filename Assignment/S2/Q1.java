import java.util.Scanner;
import java.util.StringJoiner;
import java.util.HashMap;

public class Q1{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user to enter a sentence
        System.out.print("Enter a sentence with mixed formatting: ");
        String input = scanner.nextLine();

        // 1. trim() - Remove extra spaces at start/end
        String trimmed = input.trim();
        System.out.println("\nAfter trim(): " + trimmed);

        // 2. replace() - Replace spaces with underscores
        String replacedSpaces = trimmed.replace(" ", "_");
        System.out.println("After replace(): " + replacedSpaces);

        // 3. replaceAll() - Remove all digits using regex
        String noDigits = trimmed.replaceAll("\\d", "");
        System.out.println("After replaceAll() (remove digits): " + noDigits);

        // 4. split() - Split into words
        String[] words = trimmed.split("\\s+"); // split on spaces
        System.out.println("After split(): ");
        for (String w : words) {
            System.out.println(" - " + w);
        }

        // 5. join() - Rejoin words with " | "
        String joined = String.join(" | ", words);
        System.out.println("After join(): " + joined);

        // --- Additional Processing ---
        System.out.println("\nAdditional Processing:");

        // Remove punctuation
        String noPunct = removePunctuation(trimmed);
        System.out.println("Without punctuation: " + noPunct);

        // Capitalize each word
        String capitalized = capitalizeWords(noPunct);
        System.out.println("Capitalized words: " + capitalized);

        // Reverse word order
        String reversedOrder = reverseWordOrder(noPunct);
        System.out.println("Reversed word order: " + reversedOrder);

        // Word frequency
        System.out.println("\nWord Frequency:");
        countWordFrequency(noPunct);

        scanner.close();
    }

    // Method to remove punctuation
    public static String removePunctuation(String text) {
        return text.replaceAll("\\p{Punct}", ""); // remove punctuation
    }

    // Method to capitalize each word
    public static String capitalizeWords(String text) {
        String[] words = text.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            if (w.length() > 0) {
                sb.append(Character.toUpperCase(w.charAt(0)))
                        .append(w.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return sb.toString().trim();
    }

    // Method to reverse word order
    public static String reverseWordOrder(String text) {
        String[] words = text.split("\\s+");
        StringJoiner sj = new StringJoiner(" ");
        for (int i = words.length - 1; i >= 0; i--) {
            sj.add(words[i]);
        }
        return sj.toString();
    }

    // Method to count word frequency
    public static void countWordFrequency(String text) {
        String[] words = text.toLowerCase().split("\\s+");
        HashMap<String, Integer> freqMap = new HashMap<>();

        for (String w : words) {
            freqMap.put(w, freqMap.getOrDefault(w, 0) + 1);
        }

        for (String key : freqMap.keySet()) {
            System.out.println(key + " : " + freqMap.get(key));
        }
    }
}
