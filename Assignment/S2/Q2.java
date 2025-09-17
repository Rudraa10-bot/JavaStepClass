public class Q2{
    public static void main(String[] args) {
        String sampleText = " Java Programming is Fun and Challenging! ";

        // 1. Original string length (including spaces)
        System.out.println("1. Original length: " + sampleText.length());

        // 2. Remove leading and trailing spaces
        String trimmed = sampleText.trim();
        System.out.println("2. Trimmed string: \"" + trimmed + "\"");
        System.out.println("   New length: " + trimmed.length());

        // 3. Character at index 5
        System.out.println("3. Character at index 5: " + sampleText.charAt(5));

        // 4. Extract substring "Programming"
        String substring = trimmed.substring(5, 16); // "Programming"
        System.out.println("4. Substring: " + substring);

        // 5. Index of "Fun"
        int indexFun = trimmed.indexOf("Fun");
        System.out.println("5. Index of \"Fun\": " + indexFun);

        // 6. Check if contains "Java"
        System.out.println("6. Contains \"Java\": " + trimmed.contains("Java"));

        // 7. Check if starts with "Java"
        System.out.println("7. Starts with \"Java\": " + trimmed.startsWith("Java"));

        // 8. Check if ends with "!"
        System.out.println("8. Ends with '!': " + trimmed.endsWith("!"));

        // 9. Uppercase
        System.out.println("9. Uppercase: " + trimmed.toUpperCase());

        // 10. Lowercase
        System.out.println("10. Lowercase: " + trimmed.toLowerCase());

        // Custom methods
        System.out.println("\n--- Custom Methods ---");

        // Count vowels
        int vowelCount = countVowels(trimmed);
        System.out.println("Vowel count: " + vowelCount);

        // Find all occurrences of a character
        System.out.print("Occurrences of 'a': ");
        findAllOccurrences(trimmed, 'a');
    }

    // Method to count vowels in a string
    public static int countVowels(String text) {
        int count = 0;
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    // Method to find all positions of a character
    public static void findAllOccurrences(String text, char target) {
        text = text.toLowerCase();
        target = Character.toLowerCase(target);
        boolean found = false;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                System.out.print(i + " ");
                found = true;
            }
        }
        if (!found) {
            System.out.print("Not found");
        }
        System.out.println();
    }
}
