package se.kth.iv1350.POS.integration.exceptions;

/**
 * A checked exception that is thrown when an instance of a class is not created as desired.
 * @author abbas
 *
 */
public class InstantiationException extends Exception {
	
	public InstantiationException(String msg) {
		super(msg);
	}
}
