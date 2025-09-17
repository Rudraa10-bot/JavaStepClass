// Abstract class: Food
abstract class Food {

    // Template method (final to prevent overriding)
    public final void prepare() {
        wash();
        cook();
        serve();
        System.out.println("------------------------");
    }

    // Steps to be implemented/overridden by child classes
    protected abstract void wash();
    protected abstract void cook();
    protected abstract void serve();
}

// Child class: Pizza
class Pizza extends Food {

    @Override
    protected void wash() {
        System.out.println("Washing dough and vegetables for Pizza.");
    }

    @Override
    protected void cook() {
        System.out.println("Baking the Pizza in the oven.");
    }

    @Override
    protected void serve() {
        System.out.println("Serving the Pizza on a plate.");
    }
}

// Child class: Soup
class Soup extends Food {

    @Override
    protected void wash() {
        System.out.println("Washing vegetables for Soup.");
    }

    @Override
    protected void cook() {
        System.out.println("Boiling the Soup in a pot.");
    }

    @Override
    protected void serve() {
        System.out.println("Serving the Soup in a bowl.");
    }
}

// Main class to test
public class TemplateMethodDemo {
    public static void main(String[] args) {
        Food pizza = new Pizza();
        Food soup = new Soup();

        System.out.println("--- Preparing Pizza ---");
        pizza.prepare();

        System.out.println("--- Preparing Soup ---");
        soup.prepare();
    }
}
