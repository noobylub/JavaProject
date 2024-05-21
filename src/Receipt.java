// The final receipt for purchases
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class Receipt {
   
    private String message; 
    private ShoppingBasket basketToClear; 
    
    //Constructor overloading
    //Multiple constructor for credit card 
    public Receipt( Address fullAddress, int creditCardNumber, ShoppingBasket basket) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	String formattedDate = dateFormat.format(new Date());
    	this.basketToClear = basket;
    	
    	//Before modifying database, final check if items are available 
    	 //Then sets the message here 
    	StockManager helperStockManager = new StockManager();
    	if(helperStockManager.checkAvailable(this.basketToClear).equals(false)) {
        	System.out.print("Somethign wrong, unable to proceeed");
        	this.message = "Unable to proceed, please clear your basket";
        }else {
        	String addressString = fullAddress.getStreetAddress() + ", " + fullAddress.getState() + ", " + fullAddress.getPostalCode();
        	this.message = basket.getTotalAmount() + " paid by Credit Card using " + creditCardNumber + " on " + formattedDate + ", and the delivery address isssss " + addressString;
        	clearBasket();
        }
       
        
    }
    
    //Multiple constructor for paypal
    public Receipt(Address fullAddress, String paypalEmail,ShoppingBasket basket) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    	String formattedDate = dateFormat.format(new Date());
    	this.basketToClear = basket; 
    	
    	 //Then sets the message here 
    	StockManager helperStockManager = new StockManager();
    	if(helperStockManager.checkAvailable(this.basketToClear).equals(false)) {
    		this.message = "Unable to proceed, please clear your basket";
    
        }else {
        	String addressString = fullAddress.getStreetAddress() + ", " + fullAddress.getState() + ", " + fullAddress.getPostalCode();
        	this.message = basket.getTotalAmount() + " paid by PayPal using " + paypalEmail + " on " + formattedDate + ", and the delivery address is " + addressString;
        	clearBasket();
        }
       
        
    }
    
    //Helper method, so that it is not repetitive, and put in Receipt so that this method is not repeated into two methods
    //Updates the basket as receipt is the final process
    // Also updates the quantities for the basket
    private void clearBasket() {
        for (Map.Entry<Product, Integer> entry : this.basketToClear.getProductsWithQuantities().entrySet()) {
            Product product = entry.getKey();
            int quantityInBasket = entry.getValue();
            int updatedQuantityInStock = product.getQuantityInStock() - quantityInBasket;
            product.setQuantityInStock(product.getBarcode(),updatedQuantityInStock);
        }
    }
    
  
    //getter for string of Receipt 
    public String getMessage() {
    	return this.message; 
    }

    
    
   

   
}
