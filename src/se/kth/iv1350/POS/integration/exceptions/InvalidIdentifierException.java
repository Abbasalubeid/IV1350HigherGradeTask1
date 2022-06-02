package se.kth.iv1350.POS.integration.exceptions;

import se.kth.iv1350.POS.model.Identifier;

/**
 * A checked exception that is thrown when an item with an invalid identifier is being scanned.
 * @author abbas
 *
 */
public class InvalidIdentifierException extends Exception {
	private Identifier invalidIdentifier;
	
	/**
	 * The exception that is being thrown.
	 * @param invalidIdentifier The invalid identifier that was scanned
	 */
	public InvalidIdentifierException (Identifier invalidIdentifier) {
		super("The identifier: " + invalidIdentifier.getBarcode() + " is not found in the inventory system");
		this.invalidIdentifier = invalidIdentifier;
	}
	
	/**
	 * 
	 * @return Returns the invalid barcode that has been scanned.
	 */
	public String getInvalidbarcode() {
		return invalidIdentifier.getBarcode();
	}

}
