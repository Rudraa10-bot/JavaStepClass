import java.util.Scanner;

public class Q4 {

    // a. Method to find frequency of characters
    public static String[] findFrequency(String text) {
        char[] chars = text.toCharArray();
        int[] freq = new int[chars.length];

        // ii. Outer loop for each character
        for (int i = 0; i < chars.length; i++) {
            freq[i] = 1; // initialize frequency
            if (chars[i] == '0') continue; // skip if already counted

            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    freq[i]++;
                    chars[j] = '0'; // mark as counted
                }
            }
        }

        // iii. Create result array
        String[] result = new String[chars.length];
        int index = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '0') {
                result[index] = chars[i] + " - " + freq[i];
                index++;
            }
        }

        // shrink result array to correct size
        String[] finalResult = new String[index];
        for (int i = 0; i < index; i++) {
            finalResult[i] = result[i];
        }

        return finalResult;
    }

    // b. Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        String[] frequencies = findFrequency(input);

        System.out.println("Character Frequencies:");
        for (String entry : frequencies) {
            System.out.println(entry);
        }

        sc.close();
    }
}
