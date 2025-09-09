import java.util.*;

public class Q5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> emails = new ArrayList<>();

        System.out.println("Enter email addresses (type 'done' to finish):");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;
            emails.add(input);
        }
        scanner.close();

        List<String[]> results = new ArrayList<>();
        Map<String, Integer> domainCount = new HashMap<>();
        int validCount = 0, invalidCount = 0, totalUsernameLength = 0;

        for (String email : emails) {
            boolean isValid = validateEmail(email);
            String username = "", domain = "", domainName = "", extension = "";
            if (isValid) {
                String[] parts = extractComponents(email);
                username = parts[0];
                domain = parts[1];
                domainName = parts[2];
                extension = parts[3];
                validCount++;
                totalUsernameLength += username.length();
                domainCount.put(domainName, domainCount.getOrDefault(domainName, 0) + 1);
            } else {
                invalidCount++;
            }
            results.add(new String[]{email, username, domain, domainName, extension, isValid ? "Valid" : "Invalid"});
        }

        System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s%n",
                "Email", "Username", "Domain", "Domain Name", "Extension", "Status");
        System.out.println("-----------------------------------------------------------------------------------------------");
        for (String[] row : results) {
            System.out.printf("%-30s %-15s %-25s %-15s %-10s %-10s%n",
                    row[0], row[1], row[2], row[3], row[4], row[5]);
        }

        String mostCommonDomain = domainCount.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey).orElse("None");

        double avgUsernameLength = validCount > 0 ? (double) totalUsernameLength / validCount : 0;

        System.out.println("\n--- Email Analysis Statistics ---");
        System.out.println("Total Valid Emails   : " + validCount);
        System.out.println("Total Invalid Emails : " + invalidCount);
        System.out.println("Most Common Domain   : " + mostCommonDomain);
        System.out.println("Average Username Length: " + avgUsernameLength);
    }

    public static boolean validateEmail(String email) {
        int atIndex = email.indexOf('@');
        int lastAtIndex = email.lastIndexOf('@');
        if (atIndex == -1 || atIndex != lastAtIndex) return false;
        if (atIndex == 0 || atIndex == email.length() - 1) return false;
        int dotIndex = email.indexOf('.', atIndex);
        if (dotIndex == -1 || dotIndex == email.length() - 1) return false;
        return true;
    }

    public static String[] extractComponents(String email) {
        int atIndex = email.indexOf('@');
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);
        int dotIndex = domain.lastIndexOf('.');
        String domainName = (dotIndex != -1) ? domain.substring(0, dotIndex) : domain;
        String extension = (dotIndex != -1) ? domain.substring(dotIndex + 1) : "";
        return new String[]{username, domain, domainName, extension};
    }
}
