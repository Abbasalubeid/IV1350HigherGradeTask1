package se.kth.iv1350.POS.view;

import se.kth.iv1350.POS.integration.exceptions.NotLogicalValueException;

/**
 * A writer that writes to the console whenever it is notified about a payment
 * using the observer pattern functionality. Extends a super class that contains
 * common code between different observers that observes the total revenue. This
 * class implements only unique code about this implementation, the rest is
 * handled by the superclass.
 * 
 * @author abbas
 *
 */
public class TotalRevenueView extends PaymentView {

	/**
	 * Shows the total income to the console. The currentRevenue is calculated by
	 * the superclass but used here to fulfill the unique purpose of this class,
	 * that is to write to the console.
	 * 
	 * @throws NotLogicalValueException if the revenue is negative which should not
	 *                                  happen
	 */
	@Override
	protected void doShowTotalIncome() throws NotLogicalValueException {
		if (currentRevenue < 0) {
			throw new NotLogicalValueException(currentRevenue);
		}

		System.out.println(
				"This is from the TotalRevenueView class, " + " the total revenue is now " + currentRevenue + " \n");

	}

	/**
	 * Writes to the console about the exception that was thrown because the revenue
	 * is a negative value.
	 */
	@Override
	protected void handleErrors(Exception exc) {
		System.out.print("LOG: The revenue is negative!");
		exc.printStackTrace();

	}

}
