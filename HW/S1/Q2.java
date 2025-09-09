
import java.util.Scanner;

public class Q2{

    // a. Method to find frequency of characters
    public static String[][] findFrequencies(String str) {
        int[] freq = new int[256]; // ASCII character frequencies

        // Count frequencies using charAt()
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            freq[ch]++;
        }

        // Count unique characters
        int uniqueCount = 0;
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                uniqueCount++;
            }
        }

        // Prepare result array [character, frequency]
        String[][] result = new String[uniqueCount][2];
        int index = 0;

        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                result[index][0] = String.valueOf((char) i);
                result[index][1] = String.valueOf(freq[i]);
                index++;
            }
        }

        return result;
    }

    // b. Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String[][] frequencies = findFrequencies(input);

        System.out.println("\nCharacter\tFrequency");
        System.out.println("-----------------------------");
        for (int i = 0; i < frequencies.length; i++) {
            System.out.println(frequencies[i][0] + "\t\t" + frequencies[i][1]);
        }

        sc.close();
    }
}
