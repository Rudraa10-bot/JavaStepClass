import java.util.*;

public class Q5 {

    // Parse CSV data manually without split()
    static String[][] parseCSV(String input) {
        List<String[]> rows = new ArrayList<>();
        List<String> row = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean inQuotes = false;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '"') {
                inQuotes = !inQuotes; // toggle
            } else if (c == ',' && !inQuotes) {
                row.add(field.toString());
                field.setLength(0);
            } else if (c == '\n' && !inQuotes) {
                row.add(field.toString());
                rows.add(row.toArray(new String[0]));
                row.clear();
                field.setLength(0);
            } else {
                field.append(c);
            }
        }
        if (field.length() > 0) row.add(field.toString());
        if (!row.isEmpty()) rows.add(row.toArray(new String[0]));

        return rows.toArray(new String[0][]);
    }

    // Clean and validate data
    static void cleanData(String[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = data[i][j].trim();
                // Basic numeric validation
                if (j > 0) { // assume first col = name, others numeric
                    boolean numeric = true;
                    for (char ch : data[i][j].toCharArray()) {
                        if (ch < '0' || ch > '9') {
                            numeric = false; break;
                        }
                    }
                    if (!numeric && i > 0) data[i][j] = "INVALID";
                }
            }
        }
    }

    // Analyze numeric columns
    static void analyzeData(String[][] data) {
        int cols = data[0].length;
        for (int j = 1; j < cols; j++) {
            List<Integer> nums = new ArrayList<>();
            for (int i = 1; i < data.length; i++) {
                try {
                    nums.add(Integer.parseInt(data[i][j]));
                } catch (Exception e) {}
            }
            if (!nums.isEmpty()) {
                int min = Collections.min(nums);
                int max = Collections.max(nums);
                double avg = nums.stream().mapToInt(x -> x).average().orElse(0);
                System.out.println("Column: " + data[0][j] +
                        " | Min=" + min + " Max=" + max +
                        " Avg=" + String.format("%.2f", avg));
            }
        }
    }

    // Format table neatly
    static void printTable(String[][] data) {
        int cols = data[0].length;
        int[] widths = new int[cols];

        for (int j = 0; j < cols; j++) {
            int maxLen = 0;
            for (int i = 0; i < data.length; i++) {
                maxLen = Math.max(maxLen, data[i][j].length());
            }
            widths[j] = maxLen + 2;
        }

        for (int i = 0; i < data.length; i++) {
            StringBuilder line = new StringBuilder("| ");
            for (int j = 0; j < cols; j++) {
                line.append(String.format("%-" + widths[j] + "s", data[i][j])).append(" | ");
            }
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter CSV-like data (end input with an empty line):");

        StringBuilder input = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            input.append(line).append("\n");
        }

        String[][] data = parseCSV(input.toString());
        cleanData(data);

        System.out.println("\n=== FORMATTED TABLE ===");
        printTable(data);

        System.out.println("\n=== DATA ANALYSIS ===");
        analyzeData(data);
    }
}
