package se.kth.iv1350.POS.integration;

import java.util.HashMap;

import se.kth.iv1350.POS.model.Customer;
import se.kth.iv1350.POS.model.Sale;

/**
 * Represents the accounting system that holds information about a specific
 * sale.
 * 
 * @author abbas
 *
 */
public class AccountingSystem {
	private Sale sale;
	private HashMap<String, Customer> customerList = new HashMap<>();

	/**
	 * Creates an instance of accounting system with some hardcoded customers in sake of testing.
	 */
	public AccountingSystem() {
		registerExistingCustomers();
	}
	
	private void registerExistingCustomers() {
		Customer youngCustomerWithLongMembership = new Customer("1913", 19, 13);
		Customer youngCustomerWithVeryLongMembership = new Customer("2225", 22, 25);
		Customer middleAgeCustomerWithShortMembership = new Customer("411", 41, 1);
		Customer oldCustomerWithShortMembership = new Customer("662", 66, 2);
		Customer customerWithNoDicsount = new Customer("181", 18, 1);

		customerList.put("1913", youngCustomerWithLongMembership);
		customerList.put("2225", youngCustomerWithVeryLongMembership);
		customerList.put("411", middleAgeCustomerWithShortMembership);
		customerList.put("662", oldCustomerWithShortMembership);
		customerList.put("181", customerWithNoDicsount);
		
	}										   																		    															

	/**
	 * Sets the info of the given sale to this accounting system
	 * 
	 * @param sale The sale that contains certain information that needs to be
	 *             replace the old information of this accounting system
	 */
	public void setSale(Sale sale) {
		this.sale = sale;

	}

	/**
	 * @return the sale
	 */
	public Sale getSale() {
		return sale;
	}
	
	/**
	 * Gets the right customer based on a customer id.
	 * @param customerID The customer id.
	 * @return The customer that has this unique id.
	 */
	public Customer getCustomer(String customerID) {
		return customerList.get(customerID);
	}

}
