package Encapsulation;

// ECommerceSystem.java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * ECommerceSystem.java
 * Single-file implementation for Assignment Problem 2.
 */
public final class ECommerceSystem {

    // ---------------------------
    // Immutable Product
    // ---------------------------
    public static final class Product {
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
            this.productId = productId;
            this.name = name;
            this.category = category;
            this.manufacturer = manufacturer;
            this.basePrice = basePrice;
            this.weight = weight;
            this.features = features == null ? new String[0] : Arrays.copyOf(features, features.length);
            this.specifications = specifications == null ? new HashMap<>() : new HashMap<>(specifications);
        }

        // Factory methods
        public static Product createElectronics(String id, String name, String manufacturer, double price, double weight, Map<String, String> specs) {
            return new Product(id, name, "Electronics", manufacturer, price, weight, new String[]{"warranty"}, specs);
        }

        public static Product createClothing(String id, String name, String manufacturer, double price, double weight, String[] features) {
            return new Product(id, name, "Clothing", manufacturer, price, weight, features, null);
        }

        public static Product createBook(String id, String name, String author, double price, double weight) {
            Map<String, String> spec = Map.of("Author", author);
            return new Product(id, name, "Books", author, price, weight, new String[]{}, spec);
        }

        // Defensive getters
        public String getProductId() { return productId; }
        public String getName() { return name; }
        public String getCategory() { return category; }
        public String getManufacturer() { return manufacturer; }
        public double getBasePrice() { return basePrice; }
        public double getWeight() { return weight; }
        public String[] getFeatures() { return Arrays.copyOf(features, features.length); }
        public Map<String, String> getSpecifications() { return Collections.unmodifiableMap(new HashMap<>(specifications)); }

        // Business rule: final tax calculation method
        public final double calculateTax(String region) {
            // simplified tax rules
            double rate = 0.12; // default 12%
            if ("IN".equalsIgnoreCase(region)) rate = 0.18;
            if ("VAT_FREE".equalsIgnoreCase(region)) rate = 0.0;
            return basePrice * rate;
        }

        @Override public String toString() {
            return "Product{" + productId + "," + name + "," + category + ",price=" + basePrice + "}";
        }
    }

    // ---------------------------
    // Customer class with privacy tiers
    // ---------------------------
    public static class Customer {
        private final String customerId;
        private final String email;
        private String name;
        private String phoneNumber;
        private String preferredLanguage;
        private final String accountCreationDate; // immutable history
        private int creditRating; // internal metric - package-private getter

        // Guest checkout constructor (minimal)
        public Customer(String email) {
            this.customerId = "GUEST-" + UUID.randomUUID();
            this.email = email;
            this.name = "Guest";
            this.phoneNumber = null;
            this.preferredLanguage = null;
            this.accountCreationDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
            this.creditRating = 0;
        }

        // Registered
        public Customer(String customerId, String email, String name, String phone, String language) {
            this.customerId = customerId;
            this.email = email;
            this.name = name;
            this.phoneNumber = phone;
            this.preferredLanguage = language;
            this.accountCreationDate = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
            this.creditRating = 650; // default baseline
        }

        // Corporate / premium can be created externally via builder; omitted for brevity

        // Public safe profile
        public String getPublicProfile() {
            return "CustomerPublic{id=" + customerId + ", name=" + name + "}";
        }

        // Package-private credit rating
        int getCreditRating() { return creditRating; }

        public String getCustomerId() { return customerId; }
        public String getEmail() { return email; }

        // Validated setters
        public void setName(String name) { if (name == null || name.isBlank()) throw new IllegalArgumentException("Invalid"); this.name = name; }
        public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
        public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }

        @Override public String toString() {
            return "Customer[" + customerId + "," + email + ",created=" + accountCreationDate + "]";
        }
    }

    // ---------------------------
    // ShoppingCart
    // ---------------------------
    public static class ShoppingCart {
        private final String cartId;
        private final String customerId;
        private final List<CartItem> items = new ArrayList<>();
        private double totalAmount = 0.0;
        private int itemCount = 0;

        private static class CartItem {
            final Product product;
            final int quantity;
            CartItem(Product p, int q) { product = p; quantity = q; }
        }

        public ShoppingCart(String cartId, String customerId) {
            this.cartId = cartId;
            this.customerId = customerId;
        }

        // Add item with validation
        public boolean addItem(Object productObj, int quantity) {
            if (!(productObj instanceof Product)) {
                System.out.println("Invalid product type");
                return false;
            }
            if (quantity <= 0) return false;
            Product p = (Product) productObj;
            items.add(new CartItem(p, quantity));
            itemCount += quantity;
            totalAmount += p.getBasePrice() * quantity;
            return true;
        }

        // Remove all items for demo
        public void clear() {
            items.clear();
            totalAmount = 0.0;
            itemCount = 0;
        }

        // private discount calculation
        private double calculateDiscount() {
            // simple rule: >5 items => 5% discount
            if (itemCount > 5) return totalAmount * 0.05;
            return 0.0;
        }

        // package-private summary used by checkout
        String getCartSummary() {
            return "Cart[" + cartId + ", customer=" + customerId + ", items=" + itemCount +
                    ", total=" + totalAmount + ", discount=" + calculateDiscount() + "]";
        }

        public double getPayableAmount() {
            return totalAmount - calculateDiscount();
        }

        @Override public String toString() {
            return getCartSummary();
        }
    }

    // ---------------------------
    // Order, PaymentProcessor, ShippingCalculator
    // ---------------------------
    public static class Order {
        private final String orderId;
        private final LocalDateTime orderTime;
        private final String customerId;
        private final ShoppingCart cart;

        public Order(String orderId, String customerId, ShoppingCart cart) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.orderTime = LocalDateTime.now();
            this.cart = cart;
        }

        public String getOrderId() { return orderId; }
        public double getAmount() { return cart.getPayableAmount(); }

        @Override public String toString() {
            return "Order[" + orderId + ",cust=" + customerId + ",time=" + orderTime + ",amount=" + getAmount() + "]";
        }
    }

    public static class PaymentProcessor {
        private final String processorId;
        private final String securityKey;

        public PaymentProcessor(String processorId, String securityKey) {
            this.processorId = processorId;
            this.securityKey = securityKey;
        }

        public boolean charge(double amount, String cardToken) {
            // fake charge logic for demo
            System.out.println("Charging " + amount + " using " + processorId);
            return true; // always success in demo
        }
    }

    public static class ShippingCalculator {
        private final Map<String, Double> shippingRates;

        public ShippingCalculator(Map<String, Double> shippingRates) {
            this.shippingRates = new HashMap<>(shippingRates);
        }

        public double estimate(String region, double weight) {
            double base = shippingRates.getOrDefault(region, 50.0);
            return base + weight * 2.0;
        }
    }

    // ---------------------------
    // ECommerceSystem static catalog + order processing
    // ---------------------------
    private static final Map<String, Product> productCatalog = new HashMap<>();

    static {
        // Demo seed products
        productCatalog.put("P-EL-001", Product.createElectronics("P-EL-001", "Smartphone X", "MakerCo", 300.0, 0.4,
                Map.of("RAM", "8GB", "Storage", "128GB")));
        productCatalog.put("P-CL-001", Product.createClothing("P-CL-001", "T-Shirt", "ClothMaker", 20.0, 0.2, new String[]{"cotton","unisex"}));
        productCatalog.put("P-BK-001", Product.createBook("P-BK-001", "Java Patterns", "Author A", 35.0, 0.6));
    }

    public static boolean processOrder(Object orderObj, Object customerObj) {
        if (!(orderObj instanceof Order) || !(customerObj instanceof Customer)) {
            System.out.println("Invalid order or customer");
            return false;
        }
        Order order = (Order) orderObj;
        Customer cust = (Customer) customerObj;

        // Business rules example: registered customer credit rating must be above threshold
        if (cust.getCreditRating() < 300) {
            System.out.println("Customer credit rating too low");
            // proceed but flag or require payment pre-authorization
        }

        // Payment processing (demo)
        PaymentProcessor processor = new PaymentProcessor("PP-01", "securekey");
        boolean paid = processor.charge(order.getAmount(), "token-demo");
        if (!paid) {
            System.out.println("Payment failed");
            return false;
        }

        // Shipping calculation
        ShippingCalculator shipCalc = new ShippingCalculator(Map.of("DEFAULT", 30.0, "IN", 20.0));
        double shipping = shipCalc.estimate("DEFAULT", 1.2));
        System.out.println("Shipping estimate: " + shipping);

        // finalize order: reduce inventory (omitted), notify customer, etc.
        System.out.println("Order processed: " + order);
        return true;
    }

    // ---------------------------
    // Demo main
    // ---------------------------
    public static void main(String[] args) {
        System.out.println("=== ECommerceSystem Demo ===");
        Product p1 = productCatalog.get("P-EL-001");
        Product p2 = productCatalog.get("P-CL-001");

        Customer guest = new Customer("guest@example.com");
        ShoppingCart cart = new ShoppingCart("CART-1", guest.getCustomerId());
        cart.addItem(p1, 1);
        cart.addItem(p2, 2);

        System.out.println(cart);
        Order order = new Order("ORD-1001", guest.getCustomerId(), cart);

        // process order
        boolean result = processOrder(order, guest);
        System.out.println("Process result: " + result);
    }
}

