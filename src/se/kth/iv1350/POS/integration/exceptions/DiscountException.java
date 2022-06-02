package se.kth.iv1350.POS.integration.exceptions;

import se.kth.iv1350.POS.model.Customer;

/**
 * A checked exception that is thrown when something went wrong while working with discounts.
 * @author abbas
 *
 */

public class DiscountException extends Exception {
	private Customer customer;
	
	public DiscountException(Customer customer) {
		super("Could not find a discount for customerID " + customer.getCustomerID());
		this.customer = customer;
	}

	/**
	 * @return the customer
	 */
	public String getCustomerID() {
		return customer.getCustomerID();
	}
}
