import java.util.Scanner;

public class Q3{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user for input
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println("\n--- Character Analysis ---");
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int ascii = (int) ch;

            // 1. Display character and ASCII code
            System.out.println("Character: '" + ch + "' | ASCII: " + ascii);

            // 2. Classify character
            System.out.println("Type: " + classifyCharacter(ch));

            // 3. If letter, show upper & lower case versions with ASCII
            if (Character.isLetter(ch)) {
                char upper = Character.toUpperCase(ch);
                char lower = Character.toLowerCase(ch);
                System.out.println("Upper: " + upper + " (" + (int) upper + "), " +
                        "Lower: " + lower + " (" + (int) lower + ")");
            }

            // 4. Difference between upper & lower case ASCII
            if (Character.isLetter(ch)) {
                int diff = Math.abs((int) Character.toUpperCase(ch) -
                        (int) Character.toLowerCase(ch));
                System.out.println("ASCII difference (upper-lower): " + diff);
            }
            System.out.println();
        }

        // ASCII Table Example
        System.out.println("--- ASCII Table (65 to 90) ---");
        displayASCIITable(65, 90);

        // Caesar Cipher Example
        System.out.println("\n--- Caesar Cipher ---");
        System.out.print("Enter shift value: ");
        int shift = scanner.nextInt();
        String encrypted = caesarCipher(input, shift);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + caesarCipher(encrypted, -shift));

        // Conversion Example
        System.out.println("\n--- String <-> ASCII Conversion ---");
        int[] asciiArray = stringToASCII(input);
        System.out.print("ASCII Array: ");
        for (int val : asciiArray) System.out.print(val + " ");
        System.out.println();

        String backToText = asciiToString(asciiArray);
        System.out.println("Back to String: " + backToText);

        scanner.close();
    }

    // Method to classify character type
    public static String classifyCharacter(char ch) {
        if (Character.isUpperCase(ch)) return "Uppercase Letter";
        else if (Character.isLowerCase(ch)) return "Lowercase Letter";
        else if (Character.isDigit(ch)) return "Digit";
        else return "Special Character";
    }

    // Method to convert case using ASCII manipulation
    public static char toggleCase(char ch) {
        if (Character.isUpperCase(ch)) {
            return (char) (ch + 32); // A->a
        } else if (Character.isLowerCase(ch)) {
            return (char) (ch - 32); // a->A
        } else {
            return ch; // unchanged
        }
    }

    // Method to implement Caesar cipher
    public static String caesarCipher(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                result.append((char) (((ch - 'A' + shift + 26) % 26) + 'A'));
            } else if (Character.isLowerCase(ch)) {
                result.append((char) (((ch - 'a' + shift + 26) % 26) + 'a'));
            } else {
                result.append(ch); // keep digits/specials unchanged
            }
        }
        return result.toString();
    }

    // Method to create ASCII table for a range
    public static void displayASCIITable(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(i + " -> " + (char) i);
        }
    }

    // Method to convert string to ASCII array
    public static int[] stringToASCII(String text) {
        int[] asciiValues = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            asciiValues[i] = (int) text.charAt(i);
        }
        return asciiValues;
    }

    // Method to convert ASCII array back to string
    public static String asciiToString(int[] asciiValues) {
        StringBuilder sb = new StringBuilder();
        for (int val : asciiValues) {
            sb.append((char) val);
        }
        return sb.toString();
    }
}
