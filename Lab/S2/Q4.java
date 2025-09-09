import java.util.Scanner;

public class Q4{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // a. Take user input
        System.out.print("Enter text to encrypt: ");
        String text = scanner.nextLine();
        System.out.print("Enter shift value: ");
        int shift = scanner.nextInt();

        // b. Encrypt
        String encrypted = encrypt(text, shift);

        // c. Decrypt
        String decrypted = decrypt(encrypted, shift);

        // d. Display ASCII values
        System.out.println("\n=== ASCII VALUE COMPARISON ===");
        displayASCII(text, "Original");
        displayASCII(encrypted, "Encrypted");
        displayASCII(decrypted, "Decrypted");

        // e. Validate
        System.out.println("\n=== VALIDATION ===");
        if (text.equals(decrypted)) {
            System.out.println("Decryption successful (Original text restored)");
        } else {
            System.out.println("Decryption failed");
        }

        scanner.close();
    }

    // b. Encrypt using ASCII values
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                // Wrap around for A-Z
                char encryptedChar = (char) ((ch - 'A' + shift) % 26 + 'A');
                result.append(encryptedChar);
            } else if (Character.isLowerCase(ch)) {
                // Wrap around for a-z
                char encryptedChar = (char) ((ch - 'a' + shift) % 26 + 'a');
                result.append(encryptedChar);
            } else {
                // Keep non-letters unchanged
                result.append(ch);
            }
        }
        return result.toString();
    }

    // c. Decrypt by reversing shift
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26)); // reverse shift
    }

    // d. Display ASCII values of characters
    public static void displayASCII(String text, String label) {
        System.out.println("\n" + label + " Text: " + text);
        System.out.print(label + " ASCII: ");
        for (char ch : text.toCharArray()) {
            System.out.print((int) ch + " ");
        }
        System.out.println();
    }
}
