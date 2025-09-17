import java.util.Scanner;

public class Q2{
    String fullName;
    String favLang;
    String experience;

    String getFirstName() {
        return fullName.trim().split("\\s+")[0];
    }

    String getLastName() {
        String[] parts = fullName.trim().split("\\s+");
        return (parts.length > 1) ? parts[1] : "";
    }

    String getFavLangUpper() {
        return favLang.toUpperCase();
    }

    int getCharCount() {
        return experience.replace(" ", "").length();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Q2 user = new Q2();

        System.out.print("Enter your full name: ");
        user.fullName = sc.nextLine();

        System.out.print("Enter your favorite programming language: ");
        user.favLang = sc.nextLine();

        System.out.print("Write a sentence about your programming experience: ");
        user.experience = sc.nextLine();

        System.out.println("\n--- Summary ---");
        System.out.println("First Name: " + user.getFirstName());
        System.out.println("Last Name: " + user.getLastName());
        System.out.println("Favorite Language (UPPERCASE): " + user.getFavLangUpper());
        System.out.println("Characters (no spaces): " + user.getCharCount());
        System.out.println("Your experience: \"" + user.experience + "\"");

        sc.close();
    }
}