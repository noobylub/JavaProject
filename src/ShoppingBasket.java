import java.util.*;
// The shopping basket information 
class ShoppingBasket {
    private Map<Product, Integer> items; // Map to store product and its quantity

    public ShoppingBasket() {
        items = new HashMap<>();
    }
    public boolean isBasketEmpty() {
        return items.isEmpty();
    }


    public void addProductToBasket(Product product) {
        // Check if a product with the same characteristics is already in the basket
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product existingProduct = entry.getKey();
            int existingQuantity = entry.getValue();
            if (product.getBarcode() == existingProduct.getBarcode()) {
                // If found, increment the quantity and update the map
                items.put(existingProduct, existingQuantity + 1);
                return; // Exit the method after updating the quantity
            }
        }
        
        // If the product is not already in the basket, add it with quantity 1
        items.put(product, 1);
    }

    // viewing basket contents by means of console only, for testing purposes
    public void viewBasketContents() {
        System.out.println("Shopping Basket Contents:");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            System.out.println(product.getBrand() + " - " + product.getRetailPrice() + " x " + quantity);
        }
    }
    
    // Clearing basket
    public void clearBasket() {
        items.clear();
    }

    //Get total for the basket
    public double getTotalAmount() {
        double totalAmount = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            totalAmount += product.getRetailPrice() * quantity;
        }
        return totalAmount;
    }
    // Return the quantity if the product is found in the basket, for displaying purposes
    public int getProductCount(Product product) {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product basketProduct = entry.getKey();
            int quantity = entry.getValue();
            if (basketProduct.getBarcode() == product.getBarcode()) {
                return quantity; 
            }
        }
        return 0; // Return 0 if the product is not found in the basket
    }


    // Method to return list of products with their quantities
    public Map<Product, Integer> getProductsWithQuantities() {
        return new HashMap<>(items); // Return a new HashMap to avoid direct manipulation of internal state
    }
    
    public List<Product> getItems() {
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            for (int i = 0; i < quantity; i++) {
                productList.add(product);
            }
        }
        return productList;
    }
}
