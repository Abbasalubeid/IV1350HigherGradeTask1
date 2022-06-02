package se.kth.iv1350.POS.controller;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.POS.integration.AccountingSystem;
import se.kth.iv1350.POS.integration.InventorySystem;
import se.kth.iv1350.POS.integration.exceptions.DatabaseAccessException;
import se.kth.iv1350.POS.integration.exceptions.DiscountException;
import se.kth.iv1350.POS.integration.exceptions.InvalidIdentifierException;
import se.kth.iv1350.POS.integration.exceptions.OperationFailedException;
import se.kth.iv1350.POS.model.CashRegister;
import se.kth.iv1350.POS.model.Customer;
import se.kth.iv1350.POS.model.Identifier;
import se.kth.iv1350.POS.model.PaymentObserver;
import se.kth.iv1350.POS.model.Printer;
import se.kth.iv1350.POS.model.Sale;

/**
 * This the application's only controller. All calls to the model pass through
 * this class.
 * 
 * @author abbas
 *
 */

public class Controller {
	private Sale sale;
	private CashRegister cashRegister;
	private InventorySystem inventorySystem;
	private AccountingSystem accountingSystem;
	private Printer printer;
	private List<PaymentObserver> paymentObservers = new ArrayList<>();

	/**
	 * Creates an instance of the controller
	 * 
	 * @param inventorySystem  The inventorySystem that holds information about
	 *                         certain items.
	 * @param accountingSystem The accounting system that holds information about
	 *                         all the sales.
	 * @param printer          The printer that has access to a receipt and can
	 *                         print it.
	 */
	public Controller(InventorySystem inventorySystem, AccountingSystem accountingSystem, Printer printer) {
		this.inventorySystem = inventorySystem;
		this.accountingSystem = accountingSystem;
		this.printer = printer;
	}

	/**
	 * Starts a new sale. This method must be called before doing anything else
	 * during a sale.
	 */
	public void startSale() {
		sale = new Sale();
		cashRegister = new CashRegister();
		sale.addPaymentObservers(paymentObservers);
	}

	/**
	 * @return the inventorySystem
	 */
	public InventorySystem getInventorySystem() {
		return inventorySystem;
	}

	/**
	 * @return the accountingSystem
	 */
	public AccountingSystem getAccountingSystem() {
		return accountingSystem;
	}

	/**
	 * @return the sale
	 */
	public Sale getSale() {
		return sale;
	}

	/**
	 * 
	 * @return The cashRegister
	 */
	public CashRegister getCashRegister() {
		return cashRegister;
	}

	/**
	 * Adds a specific item in the sale.
	 * 
	 * @param barcode A unique code that identifies a certain item.
	 * @throws InvalidIdentifierException Is thrown when an identifier does not exist in the inventory system.
	 * @throws OperationFailedException  Is thrown to make the exception about database access more suitable for users.
	 */
	public void scanItem(String barcode) throws InvalidIdentifierException, OperationFailedException {
		try {
			Identifier identifier = new Identifier(barcode);
			sale.addItem(inventorySystem.getItemDTO(identifier));		
		}
		catch(DatabaseAccessException exp) {
			throw new OperationFailedException("Access denied", exp);
		}
	}

	/**
	 * Increases the quantity of the last added item in the sale.
	 * 
	 * @param quantity The amount to increase with.
	 */
	public void IncreaseQuantityOfLastAddedItem(int quantity) {
		sale.addItemQuantity(quantity);
	}
	
	/**
	 * Calculates the price after a certain discount if this customer is eligible for the type of the discount.
	 * @param typeOfDiscount The type of discount for this particular customer.
	 * @param customer The customer of the sale.
	 * @throws InstantiationException Is thrown when trying to create a type of discount that is not implemented.
	 * @throws DiscountException Is thrown when this customer is not eligible for a discount of this type.
	 */
	public void calculatePriceAfterDiscount(String typeOfDiscount, Customer customer ) throws InstantiationException, DiscountException {
		sale.setPriceAfterDiscount(typeOfDiscount, customer);
	}

	/**
	 * This method is called when the all items in the sale has been added.
	 * 
	 * @return The total price to pay.
	 */
	public double getTotalPrice() {
		return sale.getTotalPrice();
	}

	/**
	 * Represents a payment from the customer, this method updates the cash register
	 * and the sale with the paid amount, the method also returns the calculated
	 * change.
	 * 
	 * @param paidAmount The amount that the customer paid.
	 * @return The change that the customer is getting back.
	 */
	public double payment(double paidAmount) {
		cashRegister.increaseAmount(paidAmount);
		sale.paymentByCustomer(paidAmount);
		return sale.calculateChange();
	}

	public void endSale() {
		inventorySystem.setSale(sale);
		accountingSystem.setSale(sale);
		sale.updateReceipt();
		printer.printReceipt(sale.getReceipt());
	}
	
	/**
	 * Adds a certain observer to the list of observers.
	 * @param observerToadd The certain observer to add.
	 */
	public void addObserver(PaymentObserver observerToadd) {
	paymentObservers.add(observerToadd);
		
	}

}
