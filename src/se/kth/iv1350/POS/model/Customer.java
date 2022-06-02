package se.kth.iv1350.POS.model;

/**
 * This class represents a customer with a unique customerID
 * 
 * @author abbas
 *
 */
public class Customer {
	private String customerID;
	private int age;
	private int membershipLenght;

	/**
	 * This is a constructor that creates an instance of the class Customer.
	 * 
	 * @param customerID is a unique String object that every customer has so that
	 *                   they can get identified.
	 */
	public Customer(String customerID, int age, int membershipLength) {
		this.customerID = customerID;
		this.age = age;
		this.membershipLenght = membershipLength;
	}

	/**
	 * @return The customerID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @return The age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return The membershipLenght
	 */
	public int getMembershipLenght() {
		return membershipLenght;
	}

}
