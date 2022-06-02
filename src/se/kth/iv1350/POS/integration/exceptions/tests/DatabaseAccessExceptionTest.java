package se.kth.iv1350.POS.integration.exceptions.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.POS.integration.exceptions.DatabaseAccessException;
import se.kth.iv1350.POS.model.Identifier;

class DatabaseAccessExceptionTest {

	@Test
	void ExceptionIsThrownTest() {
		String barcodeThatNeedDataBaseAccess = "NeedsDataBaseAccess";
		try {
			Identifier identifier = new Identifier(barcodeThatNeedDataBaseAccess);
			fail("scanned an item that need database");
		} catch (DatabaseAccessException exc) {
			assertTrue(exc.getMessage().equals("The database cannot be accessed"), "The message " + "is incorrect");
		}

	}

	@Test
	void ExceptionIsNotThrownTest() {
		try {
			Identifier identifier1 = new Identifier("whatever");
			Identifier identifier2 = new Identifier("anything");
		} catch (DatabaseAccessException exc) {
			fail("An exception was thrown when it should not");
		}

	}

}
