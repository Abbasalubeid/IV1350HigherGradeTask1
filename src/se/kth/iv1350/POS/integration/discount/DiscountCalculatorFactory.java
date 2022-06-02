package se.kth.iv1350.POS.integration.discount;

/**
 * A factory that handles creation of different types of implementations of
 * discount calculators. All different types must implement
 * <code>DiscountCalculator</code> which is the product of this factory.
 * 
 * @author abbas
 *
 */
public class DiscountCalculatorFactory {


	/**
	 * Returns the correct type of discount calculator based on the description of the algorithm.
	 * @param shortDescription The description of the algorithm
	 * @return The instance of discountCalculator
	 * @throws InstantiationException Is thrown when a description that is not registered is given.
	 */

	public DiscountCalculator getDiscountCalculator(String shortDescription) throws InstantiationException {
		
		if (shortDescription.equals("age")) {
			return new DiscountBasedOnAge();
		}
		
		if (shortDescription.equals("membership")) {
			return new DiscountBasedOnMembership();
		}
		throw new InstantiationException("Did not find a discount with the description " + shortDescription);
	}
}
