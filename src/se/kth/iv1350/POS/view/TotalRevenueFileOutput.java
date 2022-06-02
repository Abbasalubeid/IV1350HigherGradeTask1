package se.kth.iv1350.POS.view;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import se.kth.iv1350.POS.integration.exceptions.NotLogicalValueException;

/**
 * A fileWriter that can write to a file whenever it is notified about a payment using the observer
 * pattern functionality. Extends a super class that contains common code between different observers
 * that observes the total revenue. This class implements only unique code about this implementation,
 * the rest is handled by the superclass.
 *
 * @author abbas
 *
 */
public class TotalRevenueFileOutput extends PaymentView{

	/**
	 * Shows the total income to a file. The currentRevenue is calculated by the superclass
	 * but used here to fulfill the unique purpose of this class, that is to write to a file
	 * @throws NotLogicalValueException if the revenue is negative which should not happen 
	 */
	@Override
	protected void doShowTotalIncome() throws NotLogicalValueException {
		if (currentRevenue < 0) {
			throw new NotLogicalValueException(currentRevenue);
		}
		try {
			FileWriter outputFile = new FileWriter("TotalRevenueOutput.txt");
			BufferedWriter writer = new BufferedWriter(outputFile);
			writer.write("The total revenue is now: " +	currentRevenue);
			writer.close();
		} catch(IOException exc) {
			System.out.print("Could not write to the file");
			exc.printStackTrace();
		}
		
	}

	/**
	 * Writes to the same file as the total revenue if an exception is thrown about a negative revenue
	 */
	@Override
	protected void handleErrors(Exception exc) {
		try {
			FileWriter outputFile = new FileWriter("TotalRevenueOutput.txt");
			BufferedWriter writer = new BufferedWriter(outputFile);
			writer.write("The revenue is negative");
			writer.close();
		} catch(IOException e) {
			System.out.print("Could not write to the file");
			exc.printStackTrace();
		}
		
	}

}
