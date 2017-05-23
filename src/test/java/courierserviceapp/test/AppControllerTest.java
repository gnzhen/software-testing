package courierserviceapp.test;

import courierserviceapp.domain.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class AppControllerTest {
	
	AppController ap;
	DeliveryStaff dStaff;
	Client client;
	Service service;
	
	@Before
	public void initializeClass(){
		ap = new AppController();
		dStaff = new DeliveryStaff(111, "012", true);
		client = new Client("choy","017");
		service = new Service(1,"","","",1,null);
	}
	
	@Test
	public void testCurrentClient(){
		ap.setCurrentClient(client);
		assertEquals(ap.getCurrentClient(), client);
	}
	
	@Test
	public void testCurrentService(){
		ap.setCurrentService(service);
		assertEquals(ap.getCurrentService(), service);
	}
	
	@Test
	public void testCurrentDStaff(){
		ap.setCurrentDStaff(dStaff);
		assertEquals(ap.getCurrentDStaff(), dStaff);
	}
	
	@Test
	@Parameters({"1,1,5", "299,50,5",
		"300,1,8", "300,9,8", "300,10,10", "1000,30,10", "1000,31,20",
		"1001,1,8", "1001,9,8", "1001,10,12", "3000,30,12", "3000,31,30",
		"3001,1,10", "3001,9,10", "3001,10,15", "5000,30,15", "5000,31,40",
		"5001,1,15", "5001,9,15", "5001,10,20", "6000,30,20", "10000,31,50"})
	public void testComputeCharge(double weight, double distance, double expectedResult){
		double result = ap.computeCharge(distance, weight);
		assertEquals(expectedResult, result, 0.001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	@Parameters({"-1,1", "0,1", "1,0", "1,-1"})
	public void testComputeChargeInvalid(double weight, double distance){
		ap.computeCharge(distance, weight);
	}
	
	private Object[] paramAddClientInvalid(){
		return new Object[]{
				new Object[] {null, "0"},
				new Object[] {"0", null},
		};
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "paramAddClientInvalid")
	public void testAddClientInvalid(String name, String phone){
		ap.addClient(name, phone);
	}
	

	
	private Object[] paramAddServiceInvalid(){
		return new Object[]{
				new Object[] {null, "0", "0", client},
				new Object[] {"0", null, "0", client},
				new Object[] {"0", "0", null, client},
				new Object[] {"0", "0", "0", null}
		};
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "paramAddServiceInvalid")
	public void testAddServiceInvalid(String pAddr, String dAddr, String date, Client client){
		ap.addService(1, pAddr, dAddr, date, 0, client);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSearchClientInvalid(){
		ap.searchClient(null);
	}
	
}
