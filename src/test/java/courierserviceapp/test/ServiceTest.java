package courierserviceapp.test;

import courierserviceapp.domain.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {
	Client client = new Client("", "");
	DeliveryStaff ds = new DeliveryStaff(111, "", true);
	Service s = new Service(1, "No1", "No2", "1/1/2018", 100, client);
	
	@Test
	public void testStatus(){
		assertEquals("pending", s.getStatus());

		s.updateDelivered();
		assertEquals("delivered", s.getStatus());
	}
	
	@Test
	public void testDeliveryStaff(){
		s.setDeliveryStaff(ds);
		assertEquals(ds, s.getDStaff());
		assertEquals("delivering", s.getStatus());
	}
	
	@Test
	public void testGetTrackNo(){
		assertEquals(1, s.getTrackNo());
	}
	
	@Test
	public void testGetPickupAddr(){
		assertEquals("No1", s.getPickupAddr());
	}
	
	@Test
	public void testGetDeliveryAddr(){
		assertEquals("No2", s.getDeliveryAddr());
	}
	
	@Test
	public void testGetDate(){
		assertEquals("1/1/2018", s.getDate());
	}
	
	@Test
	public void testGetCharge(){
		assertEquals(100, s.getCharge(), 0.0001);
	}
	
	@Test
	public void testGetClient(){
		assertSame(client, s.getClient());
	}
	
}
