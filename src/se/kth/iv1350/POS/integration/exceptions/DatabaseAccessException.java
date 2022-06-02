package se.kth.iv1350.POS.integration.exceptions;

/**
 * An unchecked exception that is thrown when database access us unsuccessful.
 * @author abbas
 *
 */
public class DatabaseAccessException extends RuntimeException {
	
		/**
		 * Is thrown when something is wrong with accessing the database.
		 * @param msg The message that explains why the exception is thrown.
		 */
		public DatabaseAccessException(String msg) {
			super(msg);
		}
	}


