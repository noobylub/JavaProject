import java.util.*;
// Handles payment method by credit card
public class PaymentCreditCard implements PaymentMethod{
	
	private ShoppingBasket basket; 
	private Integer creditCardNumber; 
	
	public PaymentCreditCard(ShoppingBasket basketToBuy, int personCreditCard, int threeDigit) {
		this.basket = basketToBuy;
		this.creditCardNumber = personCreditCard;
	}
	
	@Override
	public Receipt processPayment(double amount, Address fullAddress) {
		//Create a new receipt 
		Receipt finalBill = new Receipt(fullAddress, this.creditCardNumber, this.basket);
		// Update stock
        
		return finalBill;
	}
	
	

	
	 

}
