import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

//For interacting with text database for Items 
public class StockManager {
    private static final String FILE_NAME = "Stock.txt";
    
 // Helper method to convert the line into product
    private static Product convertLineToProduct(String[] parts) {
        int barcode = Integer.parseInt(parts[0]);
        String brand = parts[3];
        String color = parts[4];
        ConnectivityType connectivity = ConnectivityType.valueOf(parts[5].toUpperCase());
        int quantityInStock = Integer.parseInt(parts[6]);
        double originalCost = Double.parseDouble(parts[7]);
        double retailPrice = Double.parseDouble(parts[8]);
        ProductCategory category = ProductCategory.valueOf(parts[1].toUpperCase());
        DeviceType deviceType = DeviceType.valueOf(parts[2].toUpperCase());

        if (category == ProductCategory.KEYBOARD) {
            KeyboardLayout layout = KeyboardLayout.valueOf(parts[9].toUpperCase());
            return new Keyboard(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, deviceType, layout);
        } else if (category == ProductCategory.MOUSE) {
            int buttons = Integer.parseInt(parts[9]);
            return new Mouse(barcode, brand, color, connectivity, quantityInStock, originalCost, retailPrice, category, deviceType, buttons);
        } else {
            // Handle other types of products
            return null;
        }
    }


 // Gets all the list of products
    public static  List<Product> readStockFromFile() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
               
                products.add(convertLineToProduct(parts));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }   
        return products;
       
    }
    
 // Method to update the amount in stock
    public static void setQuantityInStockFromFile(int barcode, int newQuantity) {
        List<String> updatedLines = new ArrayList<>();
        Path filePath = Paths.get("Stock.txt");

        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String[] parts = line.split(", ");
                int productBarcode = Integer.parseInt(parts[0]);
                if (productBarcode == barcode) {
                    parts[6] = Integer.toString(newQuantity); // Update the quantity field
                    line = String.join(", ", parts);
                }
                updatedLines.add(line);
            }
            Files.write(filePath, updatedLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    //These two view methods placed here as they could be used for other purposes in the future that is unspecified
    //For example, used for analytics
    
    
 // Gets a product by ID
    public static Product getStockFromFileByBarcode(Integer barcode) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                Integer barcodeText = Integer.parseInt(parts[0]);
                if (barcodeText.equals(barcode)) {
                    return convertLineToProduct(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
 // Method to filter mice by quantity of mouse buttons
    public static List<Product> filterMiceByButtons(int quantity) {
        List<Product> filteredMice = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts[1].equalsIgnoreCase("mouse")) {
                	
                    if (Integer.parseInt(parts[9]) == quantity) {
                        filteredMice.add(convertLineToProduct(parts));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filteredMice;
    }
    
    
    
//    //Adds new products
//    public static void writeStockToFile(List<Product> products) {
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
//            for (Product product : products) {
//                bw.write(product.getBarcode() + ", " + product.getCategory() + ", " + product.getBrand() + ", " +
//                        product.getColor() + ", " + product.getConnectivity() + ", " + product.getQuantityInStock() +
//                        ", " + product.getOriginalCost() + ", " + product.getRetailPrice());
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
  
    //Check availability of stocks given a basket of items
    public static Boolean checkAvailable(ShoppingBasket basket) {
    	boolean availableOrNot = true;
        List<Product> products = readStockFromFile();
        for (Product product : basket.getItems()) {
            for (Product p : products) {
                if (p.getBarcode() == product.getBarcode()) {
                    if (p.getQuantityInStock() <= 0 || basket.getProductCount(product) > p.getQuantityInStock()) {
                        System.out.println("Error: Product " + p.getBrand() + " is not available in sufficient quantity.");
                        availableOrNot = false; // Stop payment process
                    }
                    break;
                }
            }
        }
        return availableOrNot;
    }
    
    // Return a list of products that are not available
    public static Boolean checkAvailableReturnProd(ShoppingBasket basket) {
    	boolean availableOrNot = true;
        List<Product> products = readStockFromFile();
        for (Product product : basket.getItems()) {
            for (Product p : products) {
                if (p.getBarcode() == product.getBarcode()) {
                    if (p.getQuantityInStock() <= 0 || basket.getProductCount(product) > p.getQuantityInStock()) {
                        System.out.println("Error: Product " + p.getBrand() + " is not available in sufficient quantity.");
                        availableOrNot = false; // Stop payment process
                    }
                    break;
                }
            }
        }
        return availableOrNot;
    }
    
    
    
    //Helper method to print out product 
    public static void printProduct(Product product) {
    	System.out.println("Barcode: " + product.getBarcode());
        System.out.println("Category: " + product.getCategory());
        System.out.println("Brand: " + product.getBrand());
        System.out.println("Color: " + product.getColor());
        System.out.println("Connectivity: " + product.getConnectivity());
        System.out.println("Quantity in Stock: " + product.getQuantityInStock());
        System.out.println("Original Cost: " + product.getOriginalCost());
        System.out.println("Retail Price: " + product.getRetailPrice());
        if (product instanceof Keyboard) {
            System.out.println("Keyboard Type: " + ((Keyboard) product).getCountry());
        } else if (product instanceof Mouse) {
            System.out.println("Number of Buttons: " + ((Mouse) product).getButtons());
        }
        System.out.println();
        
    }
    
//  ONLY USED FOR TESTING 
    public static void main(String[] args) {
    	
    	//get a list of user 
    	
    	//List<User> users = UserManager.readUserFromFile();
//    	Admin mainAdmin = (Admin) users.get(0);
//    	System.out.print(mainAdmin.getSystemID());
//    	Keyboard keyboard = new Keyboard(1234886, "BrandX", "Black", ConnectivityType.WIRED, 10, 20.0, 30.0, ProductCategory.KEYBOARD,DeviceType.FLEXIBLE, KeyboardLayout.US);
//    	Mouse mouse = new Mouse(789012, "BrandY", "Gray", ConnectivityType.WIRELESS, 5, 15.0, 25.0, ProductCategory.MOUSE, DeviceType.FLEXIBLE, 3);
//    	mainAdmin.addProduct(keyboard, 5);
//    	mainAdmin.addProduct(mouse, 3);
    	//Product productToAdd = new Keyboard(123456, "BrandX", "Black", ConnectivityType.WIRED, 10, 20.0, 30.0, ProductCategory.KEYBOARD, "Mechanical", "USA"), 5, DeviceType.EXTERNAL, "Mechanical", 0
    	
    	
//    	Customer mainCustomer = (Customer) users.get(2);
//    	
//    	List<Product> products = readStockFromFile(); 
//    	
//    	 for (Product product : products) {
//            printProduct(product);
//         }
//    	 System.out.println("----");
//    	
//    	Product exampleProd = mainCustomer.getByBarcode(1);
//    	 
//    	 mainCustomer.addToBasket(exampleProd);
//    	 Product exampleProds = mainCustomer.getByBarcode(1);
//    	 mainCustomer.addToBasket(exampleProds);
//    	 
//    	 exampleProd = products.get(0);
//    	
//    	 mainCustomer.addToBasket(products.get(1));
//    	 mainCustomer.viewBasketContents();
    	 //ShoppingBasket testing = mainCustomer.testBasket();
    	 //int test = testing.getProductCount(mainCustomer.getByBarcode(1));
    	 //System.out.println(test);
    	

//    	 System.out.println("-----------TEST VIEW MICE"); 
//    	 List<Product> Miceproducts = filterMiceByButtons(5);
//    	 for(Product p: Miceproducts) {
//    		 printProduct(p);
//    	 }
    	 //mainCustomer.viewBasketContents();
//    	 
//    	 System.out.println("-----------TEST VIEW ALL PRODUCTS BY PRICE"); 
//    	 for(Product p : mainCustomer.viewAvailableProducts()) {
//    		 printProduct(p);
//    	 }
//    	 
//    	 
//    	 
    	 
//    	 
//    	 for(Product p:mainCustomer.testBasket().getItems()) {
//    		 printProduct(p);
//    	 }
//    	 System.out.println("----");
    	 //mainCustomer.completePayment(PaymentType.PAYPAL, 131231, 111, "adsfas");
//    	 System.out.println("----");
    	 //mainCustomer.viewBasketContents();
    	
    	 
    	 //System.out.print(mainCustomer.checkAvailableItems());

    	 
//    	 mainCustomer.addToBasket(products.get(0));
//    	 System.out.print("----------");
//    	 //updating a product stock number 

    }
}

