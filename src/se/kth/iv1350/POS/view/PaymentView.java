package se.kth.iv1350.POS.view;

import se.kth.iv1350.POS.integration.exceptions.NotLogicalValueException;
import se.kth.iv1350.POS.model.PaymentObserver;

/**
 * A template method class that contains reused code and structure for all observer classes. Code that has 
 * different implementations by the subclasses is declared as abstract so the subclasses can override it.
 * @author abbas
 *
 */

public abstract class PaymentView implements PaymentObserver {
	protected double currentRevenue;
	
	/**
	 * The method from the observer interface that is called when the the observed classes is getting a payment
	 */
	@Override
	public void newPayment(double payment) {
		currentRevenue = payment;
		showTotalIncome();
	}
	
	/**
	 * Shows the payment to a user interface, this method has different implementations and can write to
	 * different user interfaces depending on the object that the method is called from. 
	 */
	private void showTotalIncome()  {
		try {
			doShowTotalIncome();
		}
		catch (Exception exc) {
			handleErrors(exc);
		}
	}
	
	/**
	 * Shows the current total revenue to a users interface, every subclass has to implement this method.
	 * @throws Exception Is thrown when trying to show a negative value as the total revenue
	 */
	protected abstract void doShowTotalIncome() throws NotLogicalValueException;

	/**
	 * Handles the exception that is thrown from the method "doShowTotalIncome", every subclass has to
	 * implement this method
	 * @param exc The exception that is thrown from the method "doShowTotalIncome"
	 */
	protected abstract void handleErrors(Exception exc);
	

}
