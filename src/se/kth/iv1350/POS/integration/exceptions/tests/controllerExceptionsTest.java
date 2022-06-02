package se.kth.iv1350.POS.integration.exceptions.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.POS.controller.Controller;
import se.kth.iv1350.POS.integration.AccountingSystem;
import se.kth.iv1350.POS.integration.InventorySystem;
import se.kth.iv1350.POS.integration.exceptions.DiscountException;
import se.kth.iv1350.POS.integration.exceptions.InvalidIdentifierException;
import se.kth.iv1350.POS.integration.exceptions.OperationFailedException;
import se.kth.iv1350.POS.model.Printer;

class controllerExceptionsTest {
	private InventorySystem inventorySystem;
	private AccountingSystem accountingSystem;
	private Printer printer;
	private Controller controller;

	@BeforeEach
	void setUp() throws Exception {
		inventorySystem = new InventorySystem();
		accountingSystem = new AccountingSystem();
		printer = new Printer();
		controller = new Controller(inventorySystem, accountingSystem, printer);
	}

	@AfterEach
	void tearDown() throws Exception {
		controller = null;
	}

	/**
	 * The operationFailed exception is thrown every time a DatabaseException is
	 * thrown to the controller to make the exception more suitable for classes that
	 * call the controller, thats why the test is about testing a case when an
	 * access attempt to the database is done.
	 */
	@Test
	void OperationFailedExceptionIsThrownTest() throws InvalidIdentifierException {
		String barcodeThatNeedDataBaseAccess = "NeedsDataBaseAccess";
		controller.startSale();
		try {
			controller.scanItem(barcodeThatNeedDataBaseAccess);
			fail("scanned an item that need database");
		} catch (OperationFailedException exc) {
			assertTrue(exc.getMessage().equals("Access denied"), "The message " + "is incorrect");

			assertTrue(exc.getCause().getMessage().equals("The database cannot be accessed"),
					"The exception was caused by another reason than expected");
		}

	}

	/**
	 * The operationFailed exception is thrown every time a DatabaseException is
	 * thrown to the controller to make the exception more suitable for classes that
	 * call the controller, thats why the test is about testing a case when an
	 * access attempt to the database is done.
	 */
	@Test
	void OperationFailedExceptionIsNotThrownTest() throws InvalidIdentifierException {
		String barcodeThatDontNeedDataBaseAccess = "t1020k";
		controller.startSale();
		try {
			controller.scanItem(barcodeThatDontNeedDataBaseAccess);

		} catch (OperationFailedException exc) {
			fail("Exception is thrown when it should not");
		}

	}

	@Test
	void InvalidIdentifierExceptionIsThrownTest() throws OperationFailedException {
		controller.startSale();
		try {
			controller.scanItem("anybarcode");
			fail("scanned an item that dosent exist");
		} catch (InvalidIdentifierException exc) {
			assertTrue(exc.getMessage().equals("The identifier: anybarcode is not found in the inventory system"),
					"The message of the exception is incorrect");
		}

	}

	@Test
	void InvalidIdentifierExceptionIsNotThrownTest() throws OperationFailedException {
		String barcodeThatExist = "t1020k";
		controller.startSale();
		try {
			controller.scanItem(barcodeThatExist);

		} catch (InvalidIdentifierException exc) {
			fail("Exception is thrown when it should not");
		}

	}

	@Test
	void DiscountExceptionIsThrownTest() throws OperationFailedException, InvalidIdentifierException, InstantiationException {
		controller.startSale();
		controller.scanItem("t1020k");
		String discountType = "age";
		
		try {
			controller.calculatePriceAfterDiscount(discountType, 
											controller.getAccountingSystem().getCustomer("181"));
			fail("Customer that is not eligible for discount got a discount");
		}catch(DiscountException exc) {
			assertTrue(exc.getMessage().equals("Could not find a discount for customerID 181"), "The "
					+ "message of the exception is incorrect");
		}

	
	}

	@Test
	void DiscountExceptionIsNotThrownTest() throws OperationFailedException, InvalidIdentifierException, InstantiationException {
		controller.startSale();
		controller.scanItem("t1020k");
		String discountType = "age";
		
		try {
			controller.calculatePriceAfterDiscount(discountType, 
											controller.getAccountingSystem().getCustomer("662"));
			
		}catch(DiscountException exc) {
			fail("There was no discount for customer that is eligible for a discount");
		}

	}
	
	@Test
	void InstantiationExceptionIsThrownTest() throws OperationFailedException, InvalidIdentifierException, DiscountException  {
		controller.startSale();
		controller.scanItem("t1020k");
		String discountType = "citizenship";
		
		try {
			controller.calculatePriceAfterDiscount(discountType, 
											controller.getAccountingSystem().getCustomer("181"));
			fail("A discount type that is not implemented yet got created by the factory");
		}catch(InstantiationException exc) {
			assertTrue(exc.getMessage().equals("Did not find a discount with the description citizenship"), "The "
					+ "message of the exception is incorrect");
		}

	
	}

	@Test
	void InstantiationExceptionIsNotThrownTest() throws OperationFailedException, InvalidIdentifierException, DiscountException {
		controller.startSale();
		controller.scanItem("t1020k");
		String discountType = "age";
		
		try {
			controller.calculatePriceAfterDiscount(discountType, 
											controller.getAccountingSystem().getCustomer("662"));
			
		}catch(InstantiationException exc) {
			fail("No instance is created by the factory even tho the type of discount is implemented");
		}

	}

}
