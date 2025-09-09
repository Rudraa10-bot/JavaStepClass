import java.util.*;

public class Q6{

    // Structure to store file info
    static class FileInfo {
        String originalName;
        String baseName;
        String extension;
        String category;
        String newName;
        boolean valid;

        FileInfo(String name) {
            this.originalName = name;
        }
    }

    // Extract file components (without split)
    static FileInfo extractFileInfo(String fileName) {
        FileInfo fi = new FileInfo(fileName);
        int dot = fileName.lastIndexOf(".");
        if (dot == -1 || dot == 0 || dot == fileName.length() - 1) {
            fi.valid = false;
            fi.baseName = fileName;
            fi.extension = "";
        } else {
            fi.baseName = fileName.substring(0, dot);
            fi.extension = fileName.substring(dot).toLowerCase();
            fi.valid = true;
        }
        return fi;
    }

    // Categorize files by extension
    static String categorize(String ext) {
        if (ext.equals(".txt") || ext.equals(".doc")) return "Document";
        if (ext.equals(".jpg") || ext.equals(".png")) return "Image";
        if (ext.equals(".mp3")) return "Audio";
        if (ext.equals(".mp4") || ext.equals(".mkv")) return "Video";
        if (ext.equals(".java") || ext.equals(".py")) return "Code";
        return "Unknown";
    }

    // Generate new names using StringBuilder
    static String generateNewName(String category, String baseName, String ext, int counter) {
        String date = "2025"; // simple placeholder
        StringBuilder sb = new StringBuilder();
        sb.append(category).append("_").append(date).append("_").append(baseName);
        if (counter > 1) sb.append("_").append(counter);
        sb.append(ext);
        return sb.toString();
    }

    // Simulated content analysis
    static String analyzeContent(FileInfo fi) {
        if (!fi.extension.equals(".txt")) return "N/A";
        String name = fi.baseName.toLowerCase();
        if (name.contains("resume")) return "Resume";
        if (name.contains("report")) return "Report";
        if (name.contains("code")) return "Code";
        return "General";
    }

    // Display report
    static void displayReport(List<FileInfo> files) {
        System.out.println("\n=== FILE ORGANIZATION REPORT ===");
        System.out.printf("%-20s %-12s %-25s\n", "Original Name", "Category", "New Name");
        System.out.println("-----------------------------------------------------------");
        for (FileInfo fi : files) {
            System.out.printf("%-20s %-12s %-25s\n", fi.originalName, fi.category, fi.newName);
        }

        // Category counts
        Map<String, Integer> counts = new HashMap<>();
        for (FileInfo fi : files) {
            counts.put(fi.category, counts.getOrDefault(fi.category, 0) + 1);
        }

        System.out.println("\n=== CATEGORY COUNTS ===");
        for (String cat : counts.keySet()) {
            System.out.println(cat + " : " + counts.get(cat));
        }

        // Invalid / Unknown
        System.out.println("\n=== FILES NEEDING ATTENTION ===");
        for (FileInfo fi : files) {
            if (!fi.valid || fi.category.equals("Unknown")) {
                System.out.println(fi.originalName + " -> Invalid/Unknown");
            }
        }
    }

    // Generate rename commands
    static void generateCommands(List<FileInfo> files) {
        System.out.println("\n=== BATCH RENAME COMMANDS ===");
        for (FileInfo fi : files) {
            System.out.println("rename \"" + fi.originalName + "\" \"" + fi.newName + "\"");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<FileInfo> files = new ArrayList<>();

        System.out.println("Enter file names (end with empty line):");
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            FileInfo fi = extractFileInfo(line);
            fi.category = categorize(fi.extension);
            files.add(fi);
        }

        // Generate new names
        Map<String, Integer> counters = new HashMap<>();
        for (FileInfo fi : files) {
            int count = counters.getOrDefault(fi.baseName, 0) + 1;
            fi.newName = generateNewName(fi.category, fi.baseName, fi.extension, count);
            counters.put(fi.baseName, count);
        }

        // Report
        displayReport(files);

        // Commands
        generateCommands(files);
    }
}
