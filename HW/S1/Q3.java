import java.util.Scanner;

public class Q3{

    // b. Method to check if two texts are anagrams
    public static boolean areAnagrams(String text1, String text2) {
        // i. Check if lengths are equal
        if (text1.length() != text2.length()) {
            return false;
        }

        // ii. Create arrays for frequency of characters
        int[] freq1 = new int[256]; // ASCII characters
        int[] freq2 = new int[256];

        // iii. Count frequencies using charAt()
        for (int i = 0; i < text1.length(); i++) {
            freq1[text1.charAt(i)]++;
            freq2[text2.charAt(i)]++;
        }

        // iv. Compare frequencies
        for (int i = 0; i < 256; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }

        return true;
    }

    // c. Main function
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first text: ");
        String text1 = sc.nextLine();

        System.out.print("Enter second text: ");
        String text2 = sc.nextLine();

        if (areAnagrams(text1, text2)) {
            System.out.println("The texts are anagrams.");
        } else {
            System.out.println("The texts are not anagrams.");
        }

        sc.close();
    }
}
