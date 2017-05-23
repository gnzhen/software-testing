package courierserviceapp.test;

import courierserviceapp.domain.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class PostcodeTest {
	Postcode p = new Postcode(11111);
	
	@Test
	public void testGetPostcode(){
		int expectedResult = 11111;
		int result = p.getPostcode();
		assertEquals(expectedResult, result);
	}
}
