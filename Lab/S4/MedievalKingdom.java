// Medieval Kingdom Builder with Magic System

// Abstract base class
abstract class MagicalStructure {
    protected String structureName;
    protected int magicPower;
    protected String location;
    protected boolean isActive;

    // Constructor chaining with this()
    public MagicalStructure(String structureName) {
        this(structureName, 50, "Unknown", true);
    }

    public MagicalStructure(String structureName, int magicPower) {
        this(structureName, magicPower, "Unknown", true);
    }

    public MagicalStructure(String structureName, int magicPower, String location, boolean isActive) {
        this.structureName = structureName;
        this.magicPower = magicPower;
        this.location = location;
        this.isActive = isActive;
    }

    // Abstract method
    public abstract void castMagicSpell();
}

// WizardTower
class WizardTower extends MagicalStructure {
    private int spellCapacity;
    private String[] knownSpells;

    // Empty tower
    public WizardTower(String name) {
        super(name);
        this.spellCapacity = 0;
        this.knownSpells = new String[]{};
    }

    // Tower with basic spells
    public WizardTower(String name, int spellCapacity, String[] knownSpells) {
        super(name, 100);
        this.spellCapacity = spellCapacity;
        this.knownSpells = knownSpells;
    }

    // Fully equipped tower
    public WizardTower(String name, int spellCapacity, String[] knownSpells, int magicPower) {
        super(name, magicPower, "Sacred Mountain", true);
        this.spellCapacity = spellCapacity;
        this.knownSpells = knownSpells;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " casts from its tower with power " + magicPower);
    }

    public int getSpellCapacity() { return spellCapacity; }
    public void doubleSpellCapacity() { this.spellCapacity *= 2; }
    public String[] getKnownSpells() { return knownSpells; }
}

// EnchantedCastle
class EnchantedCastle extends MagicalStructure {
    private int defenseRating;
    private boolean hasDrawbridge;

    // Simple fort
    public EnchantedCastle(String name) {
        super(name, 80, "Valley", true);
        this.defenseRating = 100;
        this.hasDrawbridge = false;
    }

    // Royal castle
    public EnchantedCastle(String name, int defenseRating) {
        super(name, 120, "Royal Grounds", true);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = true;
    }

    // Impregnable fortress
    public EnchantedCastle(String name, int defenseRating, boolean hasDrawbridge) {
        super(name, 200, "Mountain Stronghold", true);
        this.defenseRating = defenseRating;
        this.hasDrawbridge = hasDrawbridge;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " raises a magical shield of defense " + defenseRating);
    }

    public int getDefenseRating() { return defenseRating; }
    public void tripleDefense() { this.defenseRating *= 3; }
}

// MysticLibrary
class MysticLibrary extends MagicalStructure {
    private int bookCount;
    private String ancientLanguage;

    // Few books
    public MysticLibrary(String name) {
        super(name, 60);
        this.bookCount = 10;
        this.ancientLanguage = "None";
    }

    // Ancient collection
    public MysticLibrary(String name, int bookCount, String ancientLanguage) {
        super(name, 90, "Arcane Valley", true);
        this.bookCount = bookCount;
        this.ancientLanguage = ancientLanguage;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " unlocks forbidden knowledge in " + ancientLanguage);
    }
}

// DragonLair
class DragonLair extends MagicalStructure {
    private String dragonType;
    private int treasureValue;

    // Normal lair
    public DragonLair(String name, String dragonType) {
        super(name, 150, "Cave", true);
        this.dragonType = dragonType;
        this.treasureValue = 500;
    }

    // Different dragon
    public DragonLair(String name, String dragonType, int treasureValue) {
        super(name, 200, "Volcanic Mountain", true);
        this.dragonType = dragonType;
        this.treasureValue = treasureValue;
    }

    @Override
    public void castMagicSpell() {
        System.out.println(structureName + " with " + dragonType + " breathes fire!");
    }
}

// Magical Interactions
class MagicInteractions {
    public static boolean canStructuresInteract(MagicalStructure s1, MagicalStructure s2) {
        return (s1.isActive && s2.isActive);
    }

    public static String performMagicBattle(MagicalStructure attacker, MagicalStructure defender) {
        if (attacker.magicPower > defender.magicPower) {
            return attacker.structureName + " wins against " + defender.structureName;
        } else if (attacker.magicPower < defender.magicPower) {
            return defender.structureName + " withstands the attack!";
        } else {
            return "It's a draw between " + attacker.structureName + " and " + defender.structureName;
        }
    }

    public static int calculateKingdomMagicPower(MagicalStructure[] structures) {
        int total = 0;
        for (MagicalStructure s : structures) total += s.magicPower;
        return total;
    }

    // Special effects
    public static void applySpecialEffects(MagicalStructure[] structures) {
        boolean hasTower = false, hasLibrary = false, hasCastle = false, hasLair = false;
        int towerCount = 0;

        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower) { hasTower = true; towerCount++; }
            if (s instanceof MysticLibrary) hasLibrary = true;
            if (s instanceof EnchantedCastle) hasCastle = true;
            if (s instanceof DragonLair) hasLair = true;
        }

        // Tower + Library
        if (hasTower && hasLibrary) {
            for (MagicalStructure s : structures) {
                if (s instanceof WizardTower) {
                    ((WizardTower) s).doubleSpellCapacity();
                }
            }
            System.out.println("Knowledge boost! Towers’ spell capacity doubled!");
        }

        // Castle + DragonLair
        if (hasCastle && hasLair) {
            for (MagicalStructure s : structures) {
                if (s instanceof EnchantedCastle) {
                    ((EnchantedCastle) s).tripleDefense();
                }
            }
            System.out.println("Dragon guard! Castle defense tripled!");
        }

        // Multiple Towers
        if (towerCount > 1) {
            System.out.println("Magic network formed! Towers share a spell pool.");
        }
    }
}

// KingdomManager
class KingdomManager {
    public static void categorize(MagicalStructure s) {
        if (s instanceof WizardTower) System.out.println(s.structureName + " is a Wizard Tower.");
        else if (s instanceof EnchantedCastle) System.out.println(s.structureName + " is an Enchanted Castle.");
        else if (s instanceof MysticLibrary) System.out.println(s.structureName + " is a Mystic Library.");
        else if (s instanceof DragonLair) System.out.println(s.structureName + " is a Dragon Lair.");
    }

    public static double calculateTax(MagicalStructure s) {
        if (s instanceof WizardTower) return 100;
        if (s instanceof EnchantedCastle) return 200;
        if (s instanceof MysticLibrary) return 150;
        if (s instanceof DragonLair) return 300;
        return 50;
    }

    public static String determineSpecialization(MagicalStructure[] structures) {
        int magic = 0, defense = 0;
        for (MagicalStructure s : structures) {
            if (s instanceof WizardTower || s instanceof MysticLibrary) magic++;
            if (s instanceof EnchantedCastle || s instanceof DragonLair) defense++;
        }
        if (magic > defense) return "Magic-focused Kingdom";
        else if (defense > magic) return "Defense-focused Kingdom";
        else return "Balanced Kingdom";
    }
}

// Main Class
public class MedievalKingdom {
    public static void main(String[] args) {
        WizardTower tower1 = new WizardTower("Arcane Spire", 5, new String[]{"Fireball", "Lightning"});
        WizardTower tower2 = new WizardTower("Crystal Tower");
        EnchantedCastle castle = new EnchantedCastle("Royal Bastion", 300, true);
        MysticLibrary library = new MysticLibrary("Ancient Archive", 200, "Elder Runes");
        DragonLair lair = new DragonLair("Inferno Den", "Red Dragon", 1000);

        MagicalStructure[] kingdom = {tower1, tower2, castle, library, lair};

        // Cast spells + categorize + tax
        for (MagicalStructure s : kingdom) {
            s.castMagicSpell();
            KingdomManager.categorize(s);
            System.out.println("Tax: " + KingdomManager.calculateTax(s));
            System.out.println();
        }

        // Apply special effects
        MagicInteractions.applySpecialEffects(kingdom);

        // Battles
        System.out.println(MagicInteractions.performMagicBattle(tower1, lair));
        System.out.println(MagicInteractions.performMagicBattle(castle, tower2));

        // Kingdom Stats
        System.out.println("Total Magic Power: " + MagicInteractions.calculateKingdomMagicPower(kingdom));
        System.out.println("Kingdom Type: " + KingdomManager.determineSpecialization(kingdom));
    }
}
