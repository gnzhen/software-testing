package courierserviceapp.test;

import courierserviceapp.domain.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
	Client c = new Client("Peter", "012-3456789");
	
	@Test
	public void testGetName(){
		String expectedResult = "Peter";
		String result = c.getName();
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void testGetPhone(){
		String expectedResult = "012-3456789";
		String result = c.getPhone();
		assertEquals(expectedResult, result);
	}
}
