package se.kth.iv1350.POS.integration.exceptions;

/**
 * An unchecked exception that is thrown when the revenue is negative, the revenue should
 * be either zero or positive.
 * 
 * @author abbas
 *
 */
public class NotLogicalValueException extends RuntimeException {
	private double notLogicValue;
	
	public NotLogicalValueException(double notLogicValue) {
		super("The value " + notLogicValue + "is not logical in this case");
		this.notLogicValue = notLogicValue;
	}

}
