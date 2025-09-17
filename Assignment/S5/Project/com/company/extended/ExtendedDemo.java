package com.company.extended;

import com.company.security.AccessModifierDemo;

// Subclass in different package
public class ExtendedDemo extends AccessModifierDemo {
    public ExtendedDemo(int privateField, String defaultField, double protectedField, boolean publicField) {
        super(privateField, defaultField, protectedField, publicField);
    }

    public void testInheritedAccess() {
        System.out.println("--- Cross-Package Subclass Test ---");

        // System.out.println(privateField);   // ❌ ERROR: private is not inherited
        // System.out.println(defaultField);   // ❌ ERROR: default not visible outside package
        System.out.println(protectedField);    // ✅ Works (protected accessible through inheritance in subclass)
        System.out.println(publicField);       // ✅ Works (public always accessible)

        // privateMethod();   // ❌ ERROR: private not accessible
        // defaultMethod();   // ❌ ERROR: default not accessible
        protectedMethod();   // ✅ Works (subclass can access inherited protected)
        publicMethod();      // ✅ Works (public)
    }

    // Overriding protected method
    @Override
    protected void protectedMethod() {
        System.out.println("Overridden protected method in ExtendedDemo");
    }

    public static void main(String[] args) {
        ExtendedDemo child = new ExtendedDemo(1, "Child", 3.14, true);
        AccessModifierDemo parent = new AccessModifierDemo(2, "Parent", 9.81, false);

        // Test child (subclass)
        child.testInheritedAccess();

        System.out.println("\n--- Parent object access in ExtendedDemo ---");
        // System.out.println(parent.protectedField);  // ❌ ERROR: protected not accessible via object in different package
        System.out.println(parent.publicField);       // ✅ Works

        // parent.protectedMethod();   // ❌ ERROR: protected not accessible via object outside package
        parent.publicMethod();         // ✅ Works
    }
}
