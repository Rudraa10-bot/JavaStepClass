import java.util.Scanner;

class Employee {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;
    private static int totalEmployees = 0;

    public Employee(String empName, String department, double baseSalary) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = "Full-Time";
        totalEmployees++;
    }

    public Employee(String empName, String department, double hourlyRate, int hours) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = hourlyRate * hours;
        this.empType = "Part-Time";
        totalEmployees++;
    }

    public Employee(String empName, String department, double contractAmount, boolean isContract) {
        this.empId = generateEmpId();
        this.empName = empName;
        this.department = department;
        this.baseSalary = contractAmount;
        this.empType = "Contract";
        totalEmployees++;
    }

    public double calculateSalary(double bonus) {
        if (empType.equals("Full-Time")) return baseSalary + bonus;
        return baseSalary;
    }

    public double calculateSalary() {
        return baseSalary;
    }

    public double calculateTax(double rate) {
        return baseSalary * rate;
    }

    public double calculateTax() {
        if (empType.equals("Full-Time")) return baseSalary * 0.2;
        else if (empType.equals("Part-Time")) return baseSalary * 0.1;
        else return baseSalary * 0.05;
    }

    public void generatePaySlip(double bonus) {
        double salary = calculateSalary(bonus);
        double tax = calculateTax();
        System.out.println("Pay Slip for " + empName);
        System.out.println("Employee ID: " + empId);
        System.out.println("Type: " + empType);
        System.out.println("Department: " + department);
        System.out.println("Gross Salary: " + salary);
        System.out.println("Tax: " + tax);
        System.out.println("Net Salary: " + (salary - tax));
        System.out.println("-------------------");
    }

    public void generatePaySlip() {
        double salary = calculateSalary();
        double tax = calculateTax();
        System.out.println("Pay Slip for " + empName);
        System.out.println("Employee ID: " + empId);
        System.out.println("Type: " + empType);
        System.out.println("Department: " + department);
        System.out.println("Gross Salary: " + salary);
        System.out.println("Tax: " + tax);
        System.out.println("Net Salary: " + (salary - tax));
        System.out.println("-------------------");
    }

    public void displayEmployeeInfo() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + empName);
        System.out.println("Department: " + department);
        System.out.println("Type: " + empType);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("-------------------");
    }

    private static String generateEmpId() {
        return String.format("E%03d", totalEmployees + 1);
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }
}

public class PayrollSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Employee e1 = new Employee("Alice", "IT", 50000);
        Employee e2 = new Employee("Bob", "HR", 500, 80);
        Employee e3 = new Employee("Charlie", "Finance", 40000, true);

        System.out.println("--- Employee Info ---");
        e1.displayEmployeeInfo();
        e2.displayEmployeeInfo();
        e3.displayEmployeeInfo();

        System.out.println("--- Pay Slips ---");
        e1.generatePaySlip(5000);
        e2.generatePaySlip();
        e3.generatePaySlip();

        System.out.println("Total Employees: " + Employee.getTotalEmployees());
    }
}
