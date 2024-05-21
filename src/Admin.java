import java.nio.file.*;
import java.util.*;
import java.io.*;

public class Admin extends UserInformation implements User  {
   

    public Admin(int systemID, Address personAdd, String personName) {
       super(systemID, personAdd, personName);
    }

    //Viewing product specific for admin
    // Written separately as viewing for admin can be configured further if need be in the future
    @Override
    public List<Product> viewAvailableProducts() {
    	List<Product> products = StockManager.readStockFromFile();
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return products;
        }

        // Sort products by retail price
        products.sort(Comparator.comparingDouble(Product::getRetailPrice));

        return products;
    }
    
    // Then add admin specific methods here 
    // Method to add a product to the existing product list (stock), I believe this is highly specific to Admin and only Admin can do it
    // Hence why there is an interaction with database here
    public void addProduct(Product product, int quantityToAdd) {
        List<Product> products = StockManager.readStockFromFile();
        
        // Check if the product barcode already exists
        boolean found = false;
        for (Product p : products) {
            if (p.getBarcode() == product.getBarcode()) {
                // If found, increment the quantity in stock
                p.setQuantityInStock(p.getBarcode(), (p.getQuantityInStock() + quantityToAdd));
                System.out.print(p.getBarcode());
                found = true;
                return;
            }
        }

        // If the product barcode doesn't exist, add the product details at the end
        if (!found) {
            // Write the updated product list to the file
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("Stock.txt", true)))) {
                writer.println(productToString(product, quantityToAdd));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    

    // Convert Product object to string
    private String productToString(Product product, int quantityToAdd) {
        String deviceDetails = "";
        String deviceTypeToPut = "";
        if (product instanceof Keyboard) {
            Keyboard keyboard = (Keyboard) product;
            deviceTypeToPut = String.valueOf(keyboard.getDeviceType());
            deviceDetails =   String.valueOf(keyboard.getCountry());
        } else if (product instanceof Mouse) {
            Mouse mouse = (Mouse) product;
            deviceTypeToPut = String.valueOf(mouse.getDeviceType());
            deviceDetails = String.valueOf(mouse.getButtons());
        }

       
		return product.getBarcode() + ", " +
                product.getCategory() + ", " +
                deviceTypeToPut + ", " +
                product.getBrand() + ", " +
                product.getColor() + ", " +
                product.getConnectivity() + ", " +
                quantityToAdd + ", " +
                product.getOriginalCost() + ", " +
                product.getRetailPrice() + ", " +
                deviceDetails;
    }

    
}
