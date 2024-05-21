import java.text.SimpleDateFormat;
import java.util.*;
// 	I purposesly used inheritance and abstraction as this will make creating a new type of users very modular
//	Analytics user can be a type of user that does not need UserInformation but needs the viewproducts method
public class Customer extends UserInformation implements User {
	//Shopping basket implementation in another file
	 private ShoppingBasket basket;
	
	 // Constructor 
	 public Customer(int systemID, Address personAdd, String personName) {
	        super(systemID, personAdd, personName);
	        this.basket = new ShoppingBasket();
	    }
	//Add a check here if quantity is available
	public void addToBasket(Product product) {
        basket.addProductToBasket(product);
    }

    public void viewBasketContents() {
        basket.viewBasketContents();
    }
    
    public boolean checkBasketEmpty() {
    	return basket.isBasketEmpty();
    }
    
    public Map<Product, Integer> getShoppingBasket() {
        return basket.getProductsWithQuantities(); // Return a new HashMap to avoid direct manipulation of internal state
    }
    
    public int shoppingBasketProduct(Product product) {
        return basket.getProductCount(product); // Return quantity of the product, or 0 if not found
    }
    
    public void cancelShoppingBasket() {
        basket.clearBasket();
        System.out.println("Shopping basket cleared.");
    }
    public ShoppingBasket returnBasket() {
    	return this.basket;
    }
    
    public Product getByBarcode(Integer barcode) {
    	return StockManager.getStockFromFileByBarcode(barcode);
    }
    
    public List<Product> filterMiceByButtons(Integer buttonNo) {
    	return StockManager.filterMiceByButtons(buttonNo);
    }
    
    public boolean checkAvailableItems() {
    	if(StockManager.checkAvailable(this.basket).equals(false)) {
        	System.out.print("Somethign wro ng, unable to proceeed");
        	return false;
        }
    	else {
    		return true;
    	}
    }
    
    

	
    public Receipt completePayment(PaymentType payWith,  int cardNumber, int threeDigit, String email) {
    	// Retrieved helper methods from stockmanager 
    	
        // Check stock availability
        if(StockManager.checkAvailable(this.basket).equals(false)) {
        	System.out.print("Somethign wrong, unable to proceeed");
        	return null;
        }
        
        System.out.println("-----------ffffff");
        if (payWith == PaymentType.CREDITCARD) {
            PaymentCreditCard newPayment = new PaymentCreditCard(this.basket,cardNumber, threeDigit);
            Receipt paymentReceipt = newPayment.processPayment(cardNumber, getPersonAdd());
            System.out.print(paymentReceipt.getMessage());
            basket.clearBasket();
            return paymentReceipt;
        } else if (payWith == PaymentType.PAYPAL) {
        	System.out.print("fdsalfj");
        	PaymentPaypal newPayment = new PaymentPaypal(this.basket, email);
            Receipt paymentReceipt = newPayment.processPayment(cardNumber, getPersonAdd());
            System.out.print(paymentReceipt.getMessage());
            basket.clearBasket();
            return paymentReceipt;
            
        }

        
        // Clear basket
        
        return null;
    }
    
    public Receipt paymentPaypal(String email) { 
    	if(StockManager.checkAvailable(this.basket).equals(false)) {
    	
    		System.out.print("Somethign wrong, unable to proceeed");
        	return null;
        }
    	
        System.out.print("fdsalfj");
    	PaymentPaypal newPayment = new PaymentPaypal(this.basket, email);
        Receipt paymentReceipt = newPayment.processPayment(60, getPersonAdd());
        System.out.print(paymentReceipt.getMessage());
        basket.clearBasket();
        return paymentReceipt;
    }
    
    
    
    

    
	
    // Implementing the interface method
	@Override
	public  List<Product> viewAvailableProducts() {
		List<Product> products = StockManager.readStockFromFile();
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return products;
        }

        // Sort products by retail price
        products.sort(Comparator.comparingDouble(Product::getRetailPrice));

        return products;
		
		
	}
	

}
