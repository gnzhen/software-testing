package courierserviceapp.test;

import courierserviceapp.domain.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class DeliveryStaffTest {
	DeliveryStaff ds = new DeliveryStaff(1, "012-3456789", true);
	
	@Test
	public void testGetId(){
		assertEquals(1, ds.getId());
	}
	
	@Test
	public void testGetHpNo(){
		assertEquals("012-3456789", ds.getHpNo());
	}
	
	@Test
	public void testAvailable(){
		assertTrue(ds.getAvailable());
		
		ds.setAvailability(false);
		assertFalse(ds.getAvailable());
	}
}
