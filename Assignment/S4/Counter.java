import java.util.Scanner;

public class Counter {
    static int count = 0;

    Counter() {
        count++;
    }

    public static int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter how many objects to create: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            new Counter();
        }

        System.out.println("Number of objects created: " + Counter.getCount());
    }
}
