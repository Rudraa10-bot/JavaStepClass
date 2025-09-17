public class Q1{
    public static void main(String[] args) {

        String str1 = "Java Programming";

        String str2 = new String("Java Programming");

        char[] charArray = {'J','a','v','a',' ','P','r','o','g','r','a','m','m','i','n','g'};
        String str3 = new String(charArray);

        System.out.println("Comparing str1 and str2 using == : " + (str1 == str2));
        System.out.println("Comparing str1 and str2 using .equals(): " + str1.equals(str2));
        System.out.println("Comparing str1 and str3 using == : " + (str1 == str3));

        System.out.println("Comparing str1 and str3 using .equals(): " + str1.equals(str3)); // true

        String quote = "Programming Quote:\n\"Code is poetry\" - Unknown\nPath: C:\\Java\\Projects";

        System.out.println("\n" + quote);
    }
}