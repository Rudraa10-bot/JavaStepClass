public class AccessModifierDemo {
    // Fields with different access modifiers
    private int privateField;           // Only accessible within this class
    String defaultField;                // Accessible within the same package
    protected double protectedField;    // Accessible within package + subclasses
    public boolean publicField;         // Accessible everywhere

    // Methods with different access modifiers
    private void privateMethod() {
        System.out.println("Private method called");
    }

    void defaultMethod() {
        System.out.println("Default method called");
    }

    protected void protectedMethod() {
        System.out.println("Protected method called");
    }

    public void publicMethod() {
        System.out.println("Public method called");
    }

    // Constructor
    public AccessModifierDemo(int privateField, String defaultField,
                              double protectedField, boolean publicField) {
        this.privateField = privateField;
        this.defaultField = defaultField;
        this.protectedField = protectedField;
        this.publicField = publicField;
    }

    // Method to demonstrate internal access
    public void testInternalAccess() {
        // Accessing fields
        System.out.println("privateField: " + privateField);
        System.out.println("defaultField: " + defaultField);
        System.out.println("protectedField: " + protectedField);
        System.out.println("publicField: " + publicField);

        // Calling methods
        privateMethod();
        defaultMethod();
        protectedMethod();
        publicMethod();
    }

    public static void main(String[] args) {
        AccessModifierDemo demo = new AccessModifierDemo(10, "Hello", 20.5, true);

        // Direct access inside the SAME class
        System.out.println("Accessing fields inside main():");
        System.out.println(demo.privateField);   // ✅ Works (same class)
        System.out.println(demo.defaultField);   // ✅ Works (same class)
        System.out.println(demo.protectedField); // ✅ Works (same class)
        System.out.println(demo.publicField);    // ✅ Works (same class)

        System.out.println("Calling methods inside main():");
        demo.privateMethod();   // ✅ Works
        demo.defaultMethod();   // ✅ Works
        demo.protectedMethod(); // ✅ Works
        demo.publicMethod();    // ✅ Works

        // Demonstrating internal access
        System.out.println("\nCalling testInternalAccess():");
        demo.testInternalAccess();
        SamePackageTest.testAccess();

    }
}

// Second class in the SAME package
class SamePackageTest {
    public static void testAccess() {
        AccessModifierDemo demo = new AccessModifierDemo(1, "World", 3.14, false);

        System.out.println("\n--- SamePackageTest ---");

        // System.out.println(demo.privateField);   // ❌ Error (private)
        System.out.println(demo.defaultField);     // ✅ Works (same package)
        System.out.println(demo.protectedField);   // ✅ Works (same package)
        System.out.println(demo.publicField);      // ✅ Works (public)

        // demo.privateMethod();   // ❌ Error (private)
        demo.defaultMethod();     // ✅ Works (same package)
        demo.protectedMethod();   // ✅ Works (same package)
        demo.publicMethod();      // ✅ Works (public)
    }
}
