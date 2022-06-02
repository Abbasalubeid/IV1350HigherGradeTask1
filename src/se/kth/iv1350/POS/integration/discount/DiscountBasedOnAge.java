package se.kth.iv1350.POS.integration.discount;

import se.kth.iv1350.POS.integration.exceptions.DiscountException;
import se.kth.iv1350.POS.model.Customer;

/**
 * Represents a calculator for discounts based on age.
 * 
 * @author abbas
 *
 */
public class DiscountBasedOnAge implements DiscountCalculator {

	DiscountBasedOnAge(){

	}
	
	/**
	 * An algorithm to calculate the price after a discount that is based on the age
	 * of the customer. If the customer does not have a discount an exception is
	 * thrown.
	 * 
	 * @throws DiscountException The exception that is thrown when a customer is not
	 *                           eligible for a discount
	 */
	@Override
	public double priceAfterDicsount(double currentPrice, Customer customer) throws DiscountException {
		if (customer.getAge() >= 40) {
			return currentPrice * 0.9;
		}

		if (customer.getAge() >= 60) {
			return currentPrice * 0.8;
		}
		throw new DiscountException(customer);

	}

}
