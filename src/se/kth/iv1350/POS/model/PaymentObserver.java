package se.kth.iv1350.POS.model;

/**
 * An interface for notifying when a payment occurs. Any class that needs to get such notification can 
 * implement this interface
 * @author abbas
 *
 */
public interface PaymentObserver {
	
	/**
	 * Is called when a class want to notify an observer about a payment
	 */
	public void newPayment(double payment);

}
