package se.kth.iv1350.POS.integration.discount;

import se.kth.iv1350.POS.integration.exceptions.DiscountException;
import se.kth.iv1350.POS.model.Customer;

/**
 * Represents a calculator for discounts based on the length of the membership.
 * that a customer has.
 * 
 * @author abbas
 *
 */
public class DiscountBasedOnMembership implements DiscountCalculator {

	DiscountBasedOnMembership(){
		
	}

	/**
	 * An algorithm to calculate the price after a discount that is based the length (in months)
	 * of the membership that a customer has . If the customer does not have a
	 * discount an exceptions is thrown.
	 * 
	 * @throws DiscountException The exception that is thrown when a customer is not
	 *                           eligible for a discount
	 */
	@Override
	public double priceAfterDicsount(double currentPrice, Customer customer) throws DiscountException {
		if (customer.getMembershipLenght() >= 12) {
			return currentPrice * 0.95;
		}

		if (customer.getMembershipLenght() >= 24) {
			return currentPrice * 0.90;
		}
		throw new DiscountException(customer);

	}

}
