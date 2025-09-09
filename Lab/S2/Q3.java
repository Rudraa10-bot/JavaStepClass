import java.util.Scanner;

public class Q3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of iterations (e.g., 1000, 10000, 100000): ");
        int iterations = scanner.nextInt();

        // Run tests
        Result stringResult = testStringConcatenation(iterations);
        Result builderResult = testStringBuilder(iterations);
        Result bufferResult = testStringBuffer(iterations);

        // Display results
        System.out.println("\n=== STRING PERFORMANCE COMPARISON ===");
        System.out.printf("%-15s %-20s %-20s\n", "Method", "Time Taken (ms)", "Final Length");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-15s %-20d %-20d\n", "String (+)", stringResult.timeTaken, stringResult.length);
        System.out.printf("%-15s %-20d %-20d\n", "StringBuilder", builderResult.timeTaken, builderResult.length);
        System.out.printf("%-15s %-20d %-20d\n", "StringBuffer", bufferResult.timeTaken, bufferResult.length);

        scanner.close();
    }

    // Result structure
    static class Result {
        long timeTaken;
        int length;

        Result(long timeTaken, int length) {
            this.timeTaken = timeTaken;
            this.length = length;
        }
    }

    // (b) String concatenation using +
    public static Result testStringConcatenation(int iterations) {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < iterations; i++) {
            s += "abc";
        }
        long end = System.currentTimeMillis();
        return new Result(end - start, s.length());
    }

    // (c) StringBuilder.append()
    public static Result testStringBuilder(int iterations) {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("abc");
        }
        long end = System.currentTimeMillis();
        return new Result(end - start, sb.length());
    }

    // (d) StringBuffer.append()
    public static Result testStringBuffer(int iterations) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("abc");
        }
        long end = System.currentTimeMillis();
        return new Result(end - start, sb.length());
    }
}
