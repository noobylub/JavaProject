// Payment using paypal 
public class PaymentPaypal implements PaymentMethod {
	private ShoppingBasket basket; 
	private String paypalEmail; 
	
	public PaymentPaypal(ShoppingBasket _basket, String _email) {
		this.basket = _basket; 
		this.paypalEmail = _email;
	}

	@Override
	public Receipt processPayment(double amount, Address fullAddress) {
		Receipt finalBill = new Receipt(fullAddress, this.paypalEmail,this.basket);
		// TODO Auto-generated method stub
		return finalBill;
	}

}
