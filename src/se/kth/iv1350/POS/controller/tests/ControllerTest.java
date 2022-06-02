/**
 * 
 */
package se.kth.iv1350.POS.controller.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.POS.controller.Controller;
import se.kth.iv1350.POS.integration.AccountingSystem;
import se.kth.iv1350.POS.integration.InventorySystem;
import se.kth.iv1350.POS.integration.ItemDTO;
import se.kth.iv1350.POS.integration.exceptions.InvalidIdentifierException;
import se.kth.iv1350.POS.integration.exceptions.OperationFailedException;
import se.kth.iv1350.POS.model.Printer;

/**
 * @author abbas
 *
 */
class ControllerTest {
	private InventorySystem inventorySystem;
	private AccountingSystem accountingSystem;
	private Printer printer;
	private Controller controller;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		inventorySystem = new InventorySystem();
		accountingSystem = new AccountingSystem();
		printer = new Printer();
		controller = new Controller(inventorySystem, accountingSystem, printer);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		controller = null;
	}

	/**
	 * Checks if an scanned item is added to the list of items by the controller and
	 * only by using an identifier.
	 * @throws InvalidIdentifierException 
	 * @throws OperationFailedException 
	 */
	@Test
	void testScanItem() throws InvalidIdentifierException, OperationFailedException {
		controller.startSale();
		controller.scanItem("c1020k");
		ItemDTO chipsItem = new ItemDTO("Chips", 14.90, 0.25);
		assertTrue(controller.getSale().getListOfItems().containsKey(chipsItem),
				"The scanned item did is not in added in the item list");
	}

	/**
	 * Checks if an items quantity is increased by the controller correctly.
	 * @throws InvalidIdentifierException 
	 * @throws OperationFailedException 
	 */
	@Test
	void testIncreaseItemOfLastAddedItem() throws InvalidIdentifierException, OperationFailedException {
		controller.startSale();
		controller.scanItem("c1020k");
		ItemDTO chipsItem = new ItemDTO("Chips", 14.90, 0.25);
		controller.IncreaseQuantityOfLastAddedItem(3);
		assertEquals(3, controller.getSale().getListOfItems().get(chipsItem),
				"The controller did not increase the quantity of the last scanned item");
	}

	/**
	 * Checks if a the payment method is updating the cash register correctly
	 */
	@Test
	void testPaymentUodatesCashRegister() {
		controller.startSale();
		controller.payment(40);
		assertEquals(40, controller.getCashRegister().getAmount(),
				"The scanned item did is not in added in the item list");
	}

	/**
	 * Checks if a the payment method is updating the sale correctly
	 */
	@Test
	void testPaymentUpdatesSale() {
		controller.startSale();
		controller.payment(40);
		assertEquals(40, controller.getSale().getPaidAmount(), "The paid amount is not updated correclty in the sale");
	}

	/**
	 * Checks if the payment method returns the change correctly
	 * @throws InvalidIdentifierException 
	 * @throws OperationFailedException 
	 */
	@Test
	void testPaymentsChangeCalculation() throws InvalidIdentifierException, OperationFailedException {
		controller.startSale();
		controller.scanItem("c1020k");
		controller.payment(40);
		assertEquals(18.625, controller.getSale().getChange(), "The change amount is not calculated correclty");
	}

	/**
	 * // * Checks if the endSale method updates the inventory system correctly
	 * @throws InvalidIdentifierException 
	 * @throws OperationFailedException 
	 */
	@Test
	void testEndSaleUpdatesInventory() throws InvalidIdentifierException, OperationFailedException {
		controller.startSale();
		controller.scanItem("c1020k");
		controller.payment(40);
		controller.endSale();
		assertEquals(controller.getSale(), controller.getInventorySystem().getSale(),
				"The sale is not sent correclty to the inventory system");
	}

	/**
	 * Checks if the endSale method updates the accounting system correctly
	 * @throws InvalidIdentifierException 
	 * @throws OperationFailedException 
	 */
	@Test
	void testEndSaleUpdatesAccounting() throws InvalidIdentifierException, OperationFailedException {
		controller.startSale();
		controller.scanItem("c1020k");
		controller.payment(40);
		controller.endSale();
		assertEquals(controller.getSale(), controller.getAccountingSystem().getSale(),
				"The sale is not sent correclty to the accounting system");
	}

	/**
	 * Checks if the endSale method updates the receipt correctly
	 * @throws InvalidIdentifierException 
	 * @throws OperationFailedException 
	 */
	@Test
	void testEndSaleUpdatesReceipt() throws InvalidIdentifierException, OperationFailedException {
		controller.startSale();
		controller.scanItem("c1020k");
		controller.payment(40);
		controller.endSale();
		assertTrue(controller.getSale().getReceipt().toString().contains("Chips"),
				"The sale is not sent correclty to the receipt");
	}

}
