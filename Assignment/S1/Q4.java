import java.util.*;

public class Q4{

    public static String cleanInput(String input) {
        return input.trim().replaceAll("\\s+", " ");
    }

    public static void analyzeText(String text) {

        String[] words = text.split(" ");
        int wordCount = words.length;

        int charCount = text.replace(" ", "").length();

        String longestWord = "";
        for (String w : words) {
            if (w.length() > longestWord.length()) {
                longestWord = w;
            }
        }

        System.out.println("Words: " + wordCount);
        System.out.println("Characters (no spaces): " + charCount);
        System.out.println("Longest word: " + longestWord);
    }

    public static String[] getWordsSorted(String text) {
        String[] words = text.split(" ");
        Arrays.sort(words);
        return words;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a paragraph:");
        String input = scanner.nextLine();

        String cleaned = cleanInput(input);

        analyzeText(cleaned);

        // Show sorted words
        String[] sortedWords = getWordsSorted(cleaned);
        System.out.println("Words in A-Z order:");
        for (String w : sortedWords) {
            System.out.println(w);
        }

        // Search for a word
        System.out.print("Search for a word: ");
        String search = scanner.nextLine();
        boolean found = false;
        for (String w : sortedWords) {
            if (w.equalsIgnoreCase(search)) {
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Word found!");
        } else {
            System.out.println("Word not found!");
        }

        scanner.close();
    }
}