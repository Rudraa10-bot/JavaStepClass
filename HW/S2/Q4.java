import java.util.*;

public class Q4{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("How many expressions? ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter expression " + i + ": ");
            String expr = sc.nextLine();

            if (!isValid(expr)) {
                System.out.println("Invalid expression!\n");
                continue;
            }

            StringBuilder steps = new StringBuilder();
            double result = evalWithParen(expr, steps);

            System.out.println("\nSteps:\n" + steps);
            System.out.println("Final Result: " + fmt(result) + "\n");
        }
        sc.close();
    }

    // --- Validation ---
    static boolean isValid(String e) {
        int bal = 0; boolean digit = false; char last = 0;
        for (char c : e.toCharArray()) {
            if (Character.isDigit(c)) digit = true;
            if ("+-*/() ".indexOf(c) < 0 && !Character.isDigit(c)) return false;
            if (c == '(') bal++; if (c == ')') bal--;
            if (bal < 0) return false;
            if ("*/".indexOf(c) >= 0 && (last == 0 || "+-*/(".indexOf(last) >= 0)) return false;
            if (c != ' ') last = c;
        }
        return bal == 0 && digit && last != '(' && "+-*/".indexOf(last) < 0;
    }

    // --- Evaluate with parentheses ---
    static double evalWithParen(String e, StringBuilder s) {
        while (e.contains("(")) {
            int st = e.lastIndexOf("("), en = e.indexOf(")", st);
            double v = eval(e.substring(st + 1, en), s);
            e = e.substring(0, st) + v + e.substring(en + 1);
        }
        return eval(e, s);
    }

    // --- Evaluate simple expression ---
    static double eval(String e, StringBuilder s) {
        ArrayList<Double> nums = new ArrayList<>();
        ArrayList<Character> ops = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(e, "+-*/ ", true);

        while (st.hasMoreTokens()) {
            String t = st.nextToken().trim();
            if (t.isEmpty()) continue;
            if ("+-*/".contains(t)) ops.add(t.charAt(0));
            else nums.add(Double.parseDouble(t));
        }

        // * and /
        for (int i = 0; i < ops.size();) {
            char op = ops.get(i);
            if (op == '*' || op == '/') {
                double a = nums.remove(i), b = nums.remove(i);
                double r = (op == '*') ? a * b : a / b;
                nums.add(i, r); ops.remove(i);
                s.append(a).append(" ").append(op).append(" ").append(b)
                        .append(" = ").append(fmt(r)).append("\n");
            } else i++;
        }
        // + and -
        while (!ops.isEmpty()) {
            char op = ops.remove(0);
            double a = nums.remove(0), b = nums.remove(0);
            double r = (op == '+') ? a + b : a - b;
            nums.add(0, r);
            s.append(a).append(" ").append(op).append(" ").append(b)
                    .append(" = ").append(fmt(r)).append("\n");
        }
        return nums.get(0);
    }

    static String fmt(double v) {
        return (v == (long) v) ? String.valueOf((long) v) : String.valueOf(v);
    }
}
