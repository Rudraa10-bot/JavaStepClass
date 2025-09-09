import java.util.Scanner;

public class Q3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step a: Input text
        System.out.print("Enter text to compress: ");
        String text = sc.nextLine();

        // Step b: Count frequency
        Object[] freqResult = countFrequency(text);
        char[] chars = (char[]) freqResult[0];
        int[] freqs = (int[]) freqResult[1];

        // Step c: Generate codes
        String[][] mapping = generateCodes(chars, freqs);

        // Step d: Compress
        String compressed = compressText(text, mapping);

        // Step e: Decompress
        String decompressed = decompressText(compressed, mapping);

        // Step f: Display Analysis
        displayAnalysis(text, compressed, decompressed, chars, freqs, mapping);
    }

    // b. Count character frequency
    public static Object[] countFrequency(String text) {
        char[] chars = new char[text.length()];
        int[] freqs = new int[text.length()];
        int uniqueCount = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // Check if already counted
            int index = -1;
            for (int j = 0; j < uniqueCount; j++) {
                if (chars[j] == ch) {
                    index = j;
                    break;
                }
            }

            if (index == -1) {
                chars[uniqueCount] = ch;
                freqs[uniqueCount] = 1;
                uniqueCount++;
            } else {
                freqs[index]++;
            }
        }

        // Resize arrays to unique size
        char[] uniqueChars = new char[uniqueCount];
        int[] uniqueFreqs = new int[uniqueCount];
        System.arraycopy(chars, 0, uniqueChars, 0, uniqueCount);
        System.arraycopy(freqs, 0, uniqueFreqs, 0, uniqueCount);

        return new Object[]{uniqueChars, uniqueFreqs};
    }

    // c. Generate compression codes
    public static String[][] generateCodes(char[] chars, int[] freqs) {
        int n = chars.length;
        String[][] mapping = new String[n][2];

        // Assign shorter codes to higher frequency
        for (int i = 0; i < n; i++) {
            mapping[i][0] = String.valueOf(chars[i]);
            mapping[i][1] = String.valueOf(i); // code is index string
        }
        return mapping;
    }

    // d. Compress text
    public static String compressText(String text, String[][] mapping) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            for (String[] map : mapping) {
                if (map[0].charAt(0) == ch) {
                    sb.append(map[1]);
                    sb.append(" "); // add space for decoding
                    break;
                }
            }
        }
        return sb.toString().trim();
    }

    // e. Decompress text
    public static String decompressText(String compressed, String[][] mapping) {
        StringBuilder sb = new StringBuilder();
        String[] codes = compressed.split(" ");

        for (String code : codes) {
            for (String[] map : mapping) {
                if (map[1].equals(code)) {
                    sb.append(map[0]);
                    break;
                }
            }
        }
        return sb.toString();
    }

    // f. Display Analysis
    public static void displayAnalysis(String original, String compressed, String decompressed,
                                       char[] chars, int[] freqs, String[][] mapping) {
        System.out.println("\n--- CHARACTER FREQUENCY TABLE ---");
        System.out.printf("%-10s %-10s\n", "Character", "Frequency");
        for (int i = 0; i < chars.length; i++) {
            System.out.printf("%-10s %-10d\n", (chars[i] == ' ' ? "[space]" : chars[i]), freqs[i]);
        }

        System.out.println("\n--- COMPRESSION MAPPING ---");
        System.out.printf("%-10s %-10s\n", "Character", "Code");
        for (String[] map : mapping) {
            System.out.printf("%-10s %-10s\n", (map[0].equals(" ") ? "[space]" : map[0]), map[1]);
        }

        System.out.println("\n--- COMPRESSION RESULTS ---");
        System.out.println("Original Text   : " + original);
        System.out.println("Compressed Text : " + compressed);
        System.out.println("Decompressed Text : " + decompressed);

        int originalSize = original.length();
        int compressedSize = compressed.length();
        double efficiency = ((double) (originalSize - compressedSize) / originalSize) * 100;
        System.out.printf("Compression Efficiency: %.2f%%\n", efficiency);
    }
}
