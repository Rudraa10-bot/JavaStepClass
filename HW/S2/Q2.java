import java.util.*;

public class Q2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step a: Take user input for multiple passwords
        System.out.print("Enter number of passwords to analyze: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] passwords = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter password " + (i + 1) + ": ");
            passwords[i] = sc.nextLine();
        }

        System.out.println("\n--- PASSWORD STRENGTH REPORT ---");
        System.out.printf("%-15s %-7s %-7s %-7s %-7s %-10s %-7s %-10s\n",
                "Password", "Len", "Upper", "Lower", "Digit", "Special", "Score", "Strength");
        System.out.println("-------------------------------------------------------------------");

        for (String pass : passwords) {
            int[] analysis = analyzePassword(pass);
            int score = calculateScore(pass, analysis);
            String strength = getStrength(score);

            System.out.printf("%-15s %-7d %-7d %-7d %-7d %-10d %-7d %-10s\n",
                    pass, pass.length(), analysis[0], analysis[1], analysis[2], analysis[3], score, strength);
        }

        // Generate strong password
        System.out.print("\nEnter desired length for generated strong password: ");
        int length = sc.nextInt();
        String generatedPassword = generatePassword(length);
        System.out.println("Generated Strong Password: " + generatedPassword);

        sc.close();
    }

    // b. Analyze password using ASCII values
    public static int[] analyzePassword(String password) {
        int upper = 0, lower = 0, digit = 0, special = 0;
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            int ascii = (int) ch;

            if (ascii >= 65 && ascii <= 90) upper++;         // Uppercase
            else if (ascii >= 97 && ascii <= 122) lower++;   // Lowercase
            else if (ascii >= 48 && ascii <= 57) digit++;    // Digit
            else if (ascii >= 33 && ascii <= 126) special++; // Special
        }
        return new int[]{upper, lower, digit, special};
    }

    // c. Calculate password score
    public static int calculateScore(String password, int[] analysis) {
        int score = 0;

        // Length points
        if (password.length() > 8) {
            score += (password.length() - 8) * 2;
        }

        // Variety points
        if (analysis[0] > 0) score += 10;
        if (analysis[1] > 0) score += 10;
        if (analysis[2] > 0) score += 10;
        if (analysis[3] > 0) score += 10;

        // Deduct points for common patterns
        String lowerPass = password.toLowerCase();
        String[] weakPatterns = {"123", "abc", "qwerty", "password"};
        for (String pattern : weakPatterns) {
            if (lowerPass.contains(pattern)) {
                score -= 10;
            }
        }

        return score;
    }

    // Strength Level
    public static String getStrength(int score) {
        if (score <= 20) return "Weak";
        else if (score <= 50) return "Medium";
        else return "Strong";
    }

    // d. Generate strong password using StringBuilder
    public static String generatePassword(int length) {
        if (length < 4) length = 4; // minimum to include all categories

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String special = "!@#$%^&*()-_=+[]{}|;:,.<>?/";

        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        // Ensure at least one from each category
        sb.append(upper.charAt(rand.nextInt(upper.length())));
        sb.append(lower.charAt(rand.nextInt(lower.length())));
        sb.append(digits.charAt(rand.nextInt(digits.length())));
        sb.append(special.charAt(rand.nextInt(special.length())));

        // Fill remaining with random mix
        String all = upper + lower + digits + special;
        for (int i = sb.length(); i < length; i++) {
            sb.append(all.charAt(rand.nextInt(all.length())));
        }

        // Shuffle characters
        List<Character> chars = new ArrayList<>();
        for (char c : sb.toString().toCharArray()) chars.add(c);
        Collections.shuffle(chars);

        StringBuilder shuffled = new StringBuilder();
        for (char c : chars) shuffled.append(c);

        return shuffled.toString();
    }
}
