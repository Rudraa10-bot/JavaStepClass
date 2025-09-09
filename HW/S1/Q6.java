import java.util.Scanner;

public class Q6{

    // a) Method to find unique characters using nested loops
    public static char[] uniqueCharacters(String text) {
        int n = text.length();
        char[] unique = new char[n];
        int uniqueCount = 0;

        for (int i = 0; i < n; i++) {
            char c = text.charAt(i);
            boolean isUnique = true;

            // Check if this character already appeared before
            for (int j = 0; j < i; j++) {
                if (c == text.charAt(j)) {
                    isUnique = false;
                    break;
                }
            }
            if (isUnique) {
                unique[uniqueCount++] = c;
            }
        }

        // Resize array to only hold unique characters
        char[] result = new char[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            result[i] = unique[i];
        }
        return result;
    }

    // b) Method to find frequency of characters using unique characters
    public static String[][] characterFrequencies(String text) {
        // i) Frequency array using ASCII values
        int[] freq = new int[256];
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }

        // iii) Find unique characters
        char[] unique = uniqueCharacters(text);

        // iv) Create 2D array [character, frequency]
        String[][] result = new String[unique.length][2];

        // v) Store character and its frequency
        for (int i = 0; i < unique.length; i++) {
            result[i][0] = String.valueOf(unique[i]);
            result[i][1] = String.valueOf(freq[unique[i]]);
        }
        return result;
    }

    // Method to display result
    public static void displayFrequencies(String[][] freqArray) {
        System.out.println("Character\tFrequency");
        for (int i = 0; i < freqArray.length; i++) {
            System.out.println("    " + freqArray[i][0] + "\t\t    " + freqArray[i][1]);
        }
    }

    // c) Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String text = sc.nextLine();

        String[][] frequencies = characterFrequencies(text);
        displayFrequencies(frequencies);
    }
}
