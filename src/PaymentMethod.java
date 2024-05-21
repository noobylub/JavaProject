// The interface that needs to be implemented for payment
public interface PaymentMethod {
	public Receipt processPayment(double amount, Address fullAdress);
}
