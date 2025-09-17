// File: ECommerceOrderSystem.java
import java.time.LocalDateTime;
import java.util.*;

/*
 Single-file implementation for:
 Assignment Problem 2: E-Commerce Order Processing with Immutable Products
*/

// ==========================
// a. Immutable Product class
// ==========================
final class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final String manufacturer;
    private final double basePrice;
    private final double weight;
    private final String[] features;
    private final Map<String, String> specifications;

    private Product(String productId, String name, String category, String manufacturer,
                    double basePrice, double weight, String[] features, Map<String, String> specifications) {
        if (productId == null || productId.isBlank()) throw new IllegalArgumentException("productId required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
        if (basePrice < 0 || weight < 0) throw new IllegalArgumentException("price/weight invalid");

        this.productId = productId;
        this.name = name;
        this.category = category == null ? "General" : category;
        this.manufacturer = manufacturer == null ? "Unknown" : manufacturer;
        this.basePrice = basePrice;
        this.weight = weight;
        this.features = features == null ? new String[0] : Arrays.copyOf(features, features.length); // defensive copy
        this.specifications = specifications == null ? Collections.emptyMap() : Collections.unmodifiableMap(new HashMap<>(specifications));
    }

    // Factory methods
    public static Product createElectronics(String name, String manufacturer, double price, double weight, String[] features, Map<String,String> specs) {
        return new Product("ELEC-" + UUID.randomUUID().toString().substring(0,6), name, "Electronics", manufacturer, price, weight, features, specs);
    }

    public static Product createClothing(String name, String manufacturer, double price, double weight, String[] features, Map<String,String> specs) {
        return new Product("CLOT-" + UUID.randomUUID().toString().substring(0,6), name, "Clothing", manufacturer, price, weight, features, specs);
    }

    public static Product createBooks(String name, String manufacturer, double price, double weight, String[] features, Map<String,String> specs) {
        return new Product("BOOK-" + UUID.randomUUID().toString().substring(0,6), name, "Books", manufacturer, price, weight, features, specs);
    }

    // Getters (defensive where necessary)
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getManufacturer() { return manufacturer; }
    public double getBasePrice() { return basePrice; }
    public double getWeight() { return weight; }
    public String[] getFeatures() { return Arrays.copyOf(features, features.length); }
    public Map<String,String> getSpecifications() { return new HashMap<>(specifications); }

    // Business rule: tax calculation is final and cannot be overridden
    public final double calculateTax(String region) {
        // Simple illustrative region-based tax rules
        double rate = switch ((region == null ? "DEFAULT" : region.toUpperCase())) {
            case "US" -> 0.07;
            case "EU" -> 0.20;
            case "IN" -> 0.18;
            default -> 0.10;
        };
        return Math.round(basePrice * rate * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Product{" + productId + "," + name + "," + category + "," + basePrice + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return productId.equals(p.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }
}

// ==========================
// b. Customer class with privacy tiers
// ==========================
class Customer {
    private final String customerId;
    private final String email;
    private String name;
    private String phoneNumber;
    private String preferredLanguage;
    private final String accountCreationDate; // immutable

    // internal credit rating (package-private access)
    int creditRating; // 300-850 typical range, package-private

    // Constructor chaining for different tiers

    // Guest checkout (minimal)
    public Customer(String email) {
        this(UUID.randomUUID().toString(), email, "Guest", null, "en", nowDateString());
        this.creditRating = 0;
    }

    // Registered customer
    public Customer(String email, String name, String phoneNumber, String preferredLanguage) {
        this(UUID.randomUUID().toString(), email, name, phoneNumber, preferredLanguage == null ? "en" : preferredLanguage, nowDateString());
        this.creditRating = 600; // default
    }

    // Premium member (special perks)
    public Customer(String email, String name, String phoneNumber) {
        this(UUID.randomUUID().toString(), email, name, phoneNumber, "en", nowDateString());
        this.creditRating = 750;
    }

    // Corporate account (special constructor)
    public Customer(String companyId, String email, boolean isCorporate) {
        this(companyId == null ? UUID.randomUUID().toString() : companyId, email, "Corporate", null, "en", nowDateString());
        this.creditRating = 800;
    }

    // Main private constructor
    private Customer(String customerId, String email, String name, String phoneNumber, String preferredLanguage, String accountCreationDate) {
        if (customerId == null || customerId.isBlank()) throw new IllegalArgumentException("customerId required");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("email required");
        this.customerId = customerId;
        this.email = email;
        this.name = name == null ? "Unknown" : name;
        this.phoneNumber = phoneNumber;
        this.preferredLanguage = preferredLanguage == null ? "en" : preferredLanguage;
        this.accountCreationDate = accountCreationDate == null ? nowDateString() : accountCreationDate;
    }

    // JavaBean getters/setters for modifiable data
    public String getCustomerId() { return customerId; }
    public String getEmail() { return email; }

    public String getName() { return name; }
    public void setName(String name) { if (name != null && !name.isBlank()) this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }

    public String getAccountCreationDate() { return accountCreationDate; }

    // Package-private credit rating accessor for internal ops
    int getCreditRating() { return creditRating; }

    // Public safe profile
    public String getPublicProfile() {
        return "User{" + (name == null ? "Guest" : name) + ", joined=" + accountCreationDate + "}";
    }

    private static String nowDateString() {
        return java.time.LocalDate.now().toString();
    }

    @Override
    public String toString() {
        return "Customer{" + customerId + "," + maskEmail(email) + "," + name + "}";
    }

    private String maskEmail(String e) {
        if (e == null) return "N/A";
        int at = e.indexOf('@');
        if (at <= 1) return "****";
        return e.charAt(0) + "****" + e.substring(at);
    }
}

// ==========================
// c. ShoppingCart class with access control
// ==========================
class ShoppingCart {
    private final String cartId;
    private final String customerId;
    private final List<Object> items; // stores Product or other product types; we validate with instanceof
    private double totalAmount;
    private int itemCount;

    public ShoppingCart(String customerId) {
        this.cartId = "CART-" + UUID.randomUUID().toString().substring(0,6);
        this.customerId = customerId;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
        this.itemCount = 0;
    }

    public String getCartId() { return cartId; }
    public String getCustomerId() { return customerId; }

    // addItem validates type and quantity
    public boolean addItem(Object product, int quantity) {
        if (!(product instanceof Product)) {
            System.out.println("Invalid product type: " + product);
            return false;
        }
        if (quantity <= 0) return false;

        Product p = (Product) product;
        // Add multiple references to items list for simplicity (could use map Product->qty)
        for (int i = 0; i < quantity; i++) items.add(p);
        itemCount += quantity;
        totalAmount += p.getBasePrice() * quantity;
        // apply discount recalculation if needed (kept private)
        totalAmount -= calculateDiscount();
        return true;
    }

    // private discount logic
    private double calculateDiscount() {
        // Simple rule: 5% off if more than 10 items, else 0
        if (itemCount > 10) {
            double discount = totalAmount * 0.05;
            return Math.round(discount * 100.0) / 100.0;
        }
        return 0.0;
    }

    // Package-private cart summary for checkout (returns safe snapshot)
    Map<String,Object> getCartSummary() {
        Map<String,Object> summary = new HashMap<>();
        summary.put("cartId", cartId);
        summary.put("customerId", customerId);
        summary.put("itemCount", itemCount);
        summary.put("totalAmount", Math.round(totalAmount*100.0)/100.0);
        // build product id list
        List<String> pids = new ArrayList<>();
        for (Object o : items) if (o instanceof Product) pids.add(((Product)o).getProductId());
        summary.put("productIds", pids);
        return summary;
    }

    public double getTotalAmount() { return totalAmount; }
    public int getItemCount() { return itemCount; }

    @Override
    public String toString() {
        return "ShoppingCart{" + cartId + ", items=" + itemCount + ", total=" + totalAmount + "}";
    }
}

// ==========================
// e. Order processing classes
// ==========================
class Order {
    private final String orderId;
    private final LocalDateTime orderTime;
    private final String customerId;
    private final Map<String, Integer> items; // productId -> qty
    private final double totalAmount;

    public Order(String customerId, Map<String,Integer> items, double totalAmount) {
        this.orderId = "ORD-" + UUID.randomUUID().toString().substring(0,6);
        this.orderTime = LocalDateTime.now();
        this.customerId = customerId;
        this.items = Collections.unmodifiableMap(new HashMap<>(items));
        this.totalAmount = totalAmount;
    }

    public String getOrderId() { return orderId; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public String getCustomerId() { return customerId; }
    public Map<String,Integer> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }

    @Override
    public String toString() {
        return "Order{" + orderId + ", customer=" + customerId + ", total=" + totalAmount + "}";
    }
}

class PaymentProcessor {
    private final String processorId;
    private final String securityKey;

    public PaymentProcessor(String processorId, String securityKey) {
        this.processorId = processorId;
        this.securityKey = securityKey;
    }

    // Simulated payment processing (private securityKey used internally)
    boolean processPayment(Order order, Customer customer) {
        // Basic business rules: check order total and customer credit rating
        if (order.getTotalAmount() <= 0) return false;
        int credit = customer == null ? 0 : customer.getCreditRating();
        // fail if credit < 200 and amount > 1000
        if (credit < 200 && order.getTotalAmount() > 1000) return false;
        // simulate success
        System.out.println("[Payment] Processor " + processorId + " processed order " + order.getOrderId());
        return true;
    }
}

class ShippingCalculator {
    private final Map<String, Double> shippingRates; // region -> rate per kg

    public ShippingCalculator(Map<String, Double> shippingRates) {
        this.shippingRates = shippingRates == null ? Collections.emptyMap() : Collections.unmodifiableMap(new HashMap<>(shippingRates));
    }

    public double estimateShipping(String region, double weightKg) {
        double rate = shippingRates.getOrDefault(region == null ? "DEFAULT" : region.toUpperCase(), shippingRates.getOrDefault("DEFAULT", 5.0));
        double cost = rate * weightKg;
        return Math.round(cost * 100.0) / 100.0;
    }
}

// ==========================
// f. ECommerceSystem final class
// ==========================
final class ECommerceSystem {
    private ECommerceSystem() {} // no instantiation

    // Catalog and simple inventory
    private static final Map<String, Product> productCatalog = new HashMap<>();
    private static final Map<String, Integer> inventory = new HashMap<>();

    // Initialize sample products
    static {
        Product p1 = Product.createElectronics("Smartphone X", "PhoneCo", 699.99, 0.2,
                new String[]{"Touch","5G"}, Map.of("CPU","OctaCore","RAM","8GB"));
        Product p2 = Product.createClothing("Jeans Classic", "DenimCorp", 49.99, 0.6,
                new String[]{"Blue","Slim"}, Map.of("Material","Denim","Fit","Slim"));
        Product p3 = Product.createBooks("Learn Java", "EduPub", 29.99, 0.5,
                new String[]{"Paperback"}, Map.of("ISBN","123-4567890123"));

        productCatalog.put(p1.getProductId(), p1);
        productCatalog.put(p2.getProductId(), p2);
        productCatalog.put(p3.getProductId(), p3);

        inventory.put(p1.getProductId(), 20);
        inventory.put(p2.getProductId(), 100);
        inventory.put(p3.getProductId(), 50);
    }

    // Public API to add product to catalog (admin-like operation)
    public static void addProductToCatalog(Product p, int qty) {
        if (p == null || qty <= 0) return;
        productCatalog.put(p.getProductId(), p);
        inventory.put(p.getProductId(), inventory.getOrDefault(p.getProductId(), 0) + qty);
    }

    public static Optional<Product> lookupProduct(String productId) {
        return Optional.ofNullable(productCatalog.get(productId));
    }

    // Process order (basic business rules)
    public static boolean processOrder(Order order, Customer customer, PaymentProcessor processor, ShippingCalculator shipCalc, String region) {
        // Basic validations
        if (order == null || customer == null || processor == null) return false;
        // Check inventory for each item
        for (Map.Entry<String,Integer> e : order.getItems().entrySet()) {
            String pid = e.getKey();
            int qty = e.getValue();
            if (inventory.getOrDefault(pid,0) < qty) {
                System.out.println("[ECommerce] Insufficient inventory for " + pid);
                return false;
            }
        }
        // Process payment
        boolean paid = processor.processPayment(order, customer);
        if (!paid) {
            System.out.println("[ECommerce] Payment failed for order " + order.getOrderId());
            return false;
        }
        // Reserve / reduce inventory
        for (Map.Entry<String,Integer> e : order.getItems().entrySet()) {
            inventory.put(e.getKey(), inventory.get(e.getKey()) - e.getValue());
        }
        // Estimate shipping & finalize
        double totalWeight = 0.0;
        for (Map.Entry<String,Integer> e : order.getItems().entrySet()) {
            Product p = productCatalog.get(e.getKey());
            if (p != null) totalWeight += p.getWeight() * e.getValue();
        }
        double shipCost = shipCalc == null ? 0.0 : shipCalc.estimateShipping(region, totalWeight);
        System.out.printf("[ECommerce] Order %s processed for %s. Shipping=%.2f. Thank you.%n", order.getOrderId(), customer.getCustomerId(), shipCost);
        return true;
    }

    // Inventory helper
    public static int getInventory(String productId) {
        return inventory.getOrDefault(productId, 0);
    }

    public static Map<String, Product> getCatalogSnapshot() {
        return Collections.unmodifiableMap(new HashMap<>(productCatalog));
    }
}

// ==========================
// Public runner class
// ==========================
public class ECommerceOrderSystem {
    public static void main(String[] args) {
        // Create a customer
        Customer alice = new Customer("alice@example.com", "Alice", "555-0001", "en");

        // Create a cart for Alice and add products by looking up catalog
        Map<String, Product> catalog = ECommerceSystem.getCatalogSnapshot();
        Product first = catalog.values().iterator().next(); // pick first product
        ShoppingCart cart = new ShoppingCart(alice.getCustomerId());
        cart.addItem(first, 2);

        System.out.println("Cart: " + cart);
        Map<String,Object> summary = cart.getCartSummary();
        System.out.println("Cart summary (internal): " + summary);

        // Build Order from cart summary (safe casting, no unchecked warnings)
        Map<String,Integer> orderItems = new HashMap<>();

        // Safely extract productIds list
        Object pidsObj = summary.get("productIds");
        List<String> productIds = new ArrayList<>();
        if (pidsObj instanceof List<?>) {
            for (Object idObj : (List<?>) pidsObj) {
                if (idObj instanceof String) productIds.add((String) idObj);
            }
        }

        // Add each product id with qty=1
        for (String pid : productIds) {
            orderItems.put(pid, orderItems.getOrDefault(pid, 0) + 1);
        }

        // Safely extract totalAmount
        Object totalObj = summary.get("totalAmount");
        double totalAmount = 0.0;
        if (totalObj instanceof Number) {
            totalAmount = ((Number) totalObj).doubleValue();
        } else if (totalObj instanceof String) {
            try {
                totalAmount = Double.parseDouble((String) totalObj);
            } catch (NumberFormatException ignored) {}
        }

        // Create order with safe total
        Order order = new Order(alice.getCustomerId(), orderItems, totalAmount);

        // Payment processor and shipping calculator
        PaymentProcessor pp = new PaymentProcessor("PP-01", "SEC-KEY");
        ShippingCalculator sc = new ShippingCalculator(Map.of("DEFAULT", 10.0, "US", 8.0));

        // Process order
        boolean ok = ECommerceSystem.processOrder(order, alice, pp, sc, "US");
        System.out.println("Order processed: " + ok);

        // Show a product tax calculation
        Product sample = first;
        System.out.println("Tax on " + sample.getName() + " for US: " + sample.calculateTax("US"));

        // Print inventory
        System.out.println("Inventory for sample: " + ECommerceSystem.getInventory(sample.getProductId()));
    }
}
