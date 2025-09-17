// Parent class: Game
class Game {
    protected String name;
    protected int players;

    public Game(String name, int players) {
        this.name = name;
        this.players = players;
    }

    // Override toString()
    @Override
    public String toString() {
        return "Game[name=" + name + ", players=" + players + "]";
    }

    // Override equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;               // same reference
        if (obj == null || getClass() != obj.getClass()) return false;
        Game other = (Game) obj;
        return players == other.players && name.equals(other.name);
    }

    // Override hashCode() to maintain contract with equals()
    @Override
    public int hashCode() {
        return name.hashCode() + players;
    }
}

// Child class: CardGame
class CardGame extends Game {
    private int deckSize;

    public CardGame(String name, int players, int deckSize) {
        super(name, players);
        this.deckSize = deckSize;
    }

    // Override toString() and call super.toString()
    @Override
    public String toString() {
        return super.toString() + ", CardGame[deckSize=" + deckSize + "]";
    }

    // Override equals()
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false; // check parent fields
        if (getClass() != obj.getClass()) return false;
        CardGame other = (CardGame) obj;
        return deckSize == other.deckSize;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + deckSize;
    }
}

// Main class to test
public class OverridingObjectMethodsDemo {
    public static void main(String[] args) {
        Game g1 = new Game("Soccer", 11);
        Game g2 = new Game("Soccer", 11);

        CardGame c1 = new CardGame("Poker", 4, 52);
        CardGame c2 = new CardGame("Poker", 4, 52);
        CardGame c3 = new CardGame("Uno", 4, 108);

        System.out.println("--- Testing toString() ---");
        System.out.println(g1);
        System.out.println(c1);

        System.out.println("\n--- Testing equals() ---");
        System.out.println("g1 equals g2: " + g1.equals(g2));   // true
        System.out.println("c1 equals c2: " + c1.equals(c2));   // true
        System.out.pri// Abstract class: Food
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
        ntln("c1 equals c3: " + c1.equals(c3));   // false
        System.out.println("g1 equals c1: " + g1.equals(c1));   // false
    }
}
