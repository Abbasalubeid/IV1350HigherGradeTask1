package se.kth.iv1350.POS.integration.discount;

import se.kth.iv1350.POS.integration.exceptions.DiscountException;
import se.kth.iv1350.POS.model.Customer;

public interface DiscountCalculator {
	
	/**
	 * Calculates the price after a discount that is based on the customer.
	 * @param currentPrice The current price
	 * @param customer The current customer.
	 * @return The price after the discount.
	 */
	public double priceAfterDicsount (double currentPrice, Customer customer)throws DiscountException;

}
