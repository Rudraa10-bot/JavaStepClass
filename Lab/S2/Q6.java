import java.util.*;

public class Q6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== TEXT FORMATTER ===");
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter line width: ");
        int width = sc.nextInt();

        String[] words = splitWords(text);

        System.out.println("\n--- Original Text ---");
        System.out.println(text);

        System.out.println("\n--- Left Justified Text ---");
        List<String> justified = justifyText(words, width);
        displayFormatted(justified);

        System.out.println("\n--- Center Aligned Text ---");
        List<String> centered = centerAlign(words, width);
        displayFormatted(centered);

        System.out.println("\n--- Performance Analysis ---");
        comparePerformance(text, width);

        sc.close();
    }

    // Split text into words manually without split()
    public static String[] splitWords(String text) {
        List<String> words = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                if (start < i) {
                    words.add(text.substring(start, i));
                }
                start = i + 1;
            }
        }
        if (start < text.length()) {
            words.add(text.substring(start));
        }
        return words.toArray(new String[0]);
    }

    // Justify text using StringBuilder
    public static List<String> justifyText(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        int i = 0;
        while (i < words.length) {
            int len = words[i].length();
            int j = i + 1;
            while (j < words.length && len + 1 + words[j].length() <= width) {
                len += 1 + words[j].length();
                j++;
            }

            StringBuilder line = new StringBuilder();
            int gaps = j - i - 1;

            if (j == words.length || gaps == 0) { // last line or single word
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) line.append(" ");
                }
                while (line.length() < width) line.append(" "); // pad right
            } else {
                int spaces = (width - len) / gaps + 1;
                int extra = (width - len) % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) {
                        for (int s = 0; s < spaces; s++) line.append(" ");
                        if (extra-- > 0) line.append(" ");
                    }
                }
            }
            lines.add(line.toString());
            i = j;
        }
        return lines;
    }

    // Center-align text
    public static List<String> centerAlign(String[] words, int width) {
        List<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        int len = 0;

        for (String word : words) {
            if (len + word.length() + (len > 0 ? 1 : 0) > width) {
                int pad = (width - len) / 2;
                StringBuilder centered = new StringBuilder();
                for (int i = 0; i < pad; i++) centered.append(" ");
                centered.append(line);
                while (centered.length() < width) centered.append(" ");
                lines.add(centered.toString());
                line = new StringBuilder();
                len = 0;
            }
            if (len > 0) {
                line.append(" ");
                len++;
            }
            line.append(word);
            len += word.length();
        }
        if (len > 0) {
            int pad = (width - len) / 2;
            StringBuilder centered = new StringBuilder();
            for (int i = 0; i < pad; i++) centered.append(" ");
            centered.append(line);
            while (centered.length() < width) centered.append(" ");
            lines.add(centered.toString());
        }
        return lines;
    }

    // Compare StringBuilder vs String concatenation
    public static void comparePerformance(String text, int width) {
        long start, end;

        // String concatenation
        start = System.nanoTime();
        String result = "";
        for (int i = 0; i < 10000; i++) {
            result += text;
        }
        end = System.nanoTime();
        long concatTime = end - start;

        // StringBuilder
        start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append(text);
        }
        sb.toString();
        end = System.nanoTime();
        long sbTime = end - start;

        System.out.printf("%-20s %-15s\n", "Method", "Time (ns)");
        System.out.println("---------------------------------");
        System.out.printf("%-20s %-15d\n", "String (+)", concatTime);
        System.out.printf("%-20s %-15d\n", "StringBuilder", sbTime);
    }

    // Display formatted lines with line numbers
    public static void displayFormatted(List<String> lines) {
        int lineNo = 1;
        for (String line : lines) {
            System.out.printf("Line %2d (%2d chars): %s\n", lineNo++, line.length(), line);
        }
    }
}
