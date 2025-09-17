import java.util.Scanner;

class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}

public class TestInstanceof {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of animals: ");
        int n = sc.nextInt();
        sc.nextLine();

        Animal[] animals = new Animal[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter type (dog/cat/animal): ");
            String type = sc.nextLine().toLowerCase();
            if (type.equals("dog")) {
                animals[i] = new Dog();
            } else if (type.equals("cat")) {
                animals[i] = new Cat();
            } else {
                animals[i] = new Animal();
            }
        }

        int dogCount = 0, catCount = 0;
        for (Animal a : animals) {
            if (a instanceof Dog) dogCount++;
            else if (a instanceof Cat) catCount++;
        }

        System.out.println("Number of Dogs: " + dogCount);
        System.out.println("Number of Cats: " + catCount);
    }
}
