import java.util.Scanner;

public class Student {
    // Private instance variables
    private String studentId;
    private String name;
    private double grade;
    private String course;

    // Default constructor
    public Student() {
        this.studentId = "";
        this.name = "";
        this.grade = 0.0;
        this.course = "";
    }

    // Parameterized constructor
    public Student(String studentId, String name, double grade, String course) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.course = course;
    }

    // Getters and Setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getGrade() { return grade; }
    public void setGrade(double grade) { this.grade = grade; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    // Calculate letter grade
    public String calculateLetterGrade() {
        if (grade >= 90) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }

    // Display student details
    public void displayStudent() {
        System.out.println("Student Information:");
        System.out.println(" ID: " + studentId);
        System.out.println(" Name: " + name);
        System.out.println(" Course: " + course);
        System.out.println(" Grade: " + grade);
        System.out.println(" Letter Grade: " + calculateLetterGrade());
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask how many students
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        // Create an array of Student objects
        Student[] students = new Student[n];

        // Input loop
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Student " + (i + 1) + ":");
            System.out.print("ID: ");
            String id = sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Course: ");
            String course = sc.nextLine();
            System.out.print("Grade: ");
            double grade = sc.nextDouble();
            sc.nextLine(); // consume newline

            // Store in array using parameterized constructor
            students[i] = new Student(id, name, grade, course);
        }

        // Display loop
        System.out.println("\n--- Student Records ---");
        for (int i = 0; i < n; i++) {
            students[i].displayStudent();
        }

        sc.close();
    }
}
