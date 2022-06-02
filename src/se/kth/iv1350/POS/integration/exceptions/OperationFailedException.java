package se.kth.iv1350.POS.integration.exceptions;

/**
 * Is thrown when other exceptions from other type of layers is throwing too detailed exceptions
 * @author abbas
 *
 */
public class OperationFailedException extends Exception {
	
	
	/**
	 * The exception that is thrown
	 * @param msg Information about the thrown exception
	 * @param theCauseException The exceptions that caused this exception to be thrown
	 */
	public OperationFailedException (String msg, Exception theCauseException) {
		super(msg,theCauseException);
		
	}


}
