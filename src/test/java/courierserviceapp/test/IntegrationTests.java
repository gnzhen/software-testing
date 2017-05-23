package courierserviceapp.test;

import java.util.ArrayList;
import java.util.Arrays;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import courierserviceapp.domain.*;
import junit.framework.ComparisonFailure;
import courierserviceapp.GUI.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InOrder;

import static org.junit.Assert.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyListOf;
import static org.mockito.Mockito.anyObject;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.inOrder;

@RunWith(JUnitParamsRunner.class)
public class IntegrationTests {
	
	AppController ap;
	Client client = new Client("Choy", "012-3456789");
	Service service;
	DeliveryStaff dStaff;
	DeliveryStaffList dStaffList;
	
	@Before
	public void setupForAllTests(){
		ap = new AppController();
		service = new Service(1, "No1", "No2", "1/1/2018", 5, client);
		dStaffList = new DeliveryStaffList();
		dStaff = new DeliveryStaff(222, "017-1234567", true);
	}
	
	private Object[] getParamsForCheckRegionAvailable() {
		return new Object[] {
				new Object[] {43000, true},
				new Object[] {43009, true},
				new Object[] {45000, false}
		};
	}
	
	@Test
	@Parameters(method = "getParamsForCheckRegionAvailable")
	public void testCheckRegionAvailable(int postcode, boolean expectedResult){
		boolean result = ap.checkRegion(postcode);
		assertEquals(expectedResult, result);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCheckRegionAvailableError() {
		ap.checkRegion(-1);
	}
	
	
	private Object[] getParamsForComputeCharge() {
		return new Object[] {
				new Object[] {1,1,5}, new Object[] {299,50,5},
				new Object[] {300,1,8},	new Object[] {300,9,8}, new Object[] {300,10,10},
				new Object[] {1000,30,10}, new Object[] {1000,31,20},
				new Object[] {1001,1,8}, new Object[] {1001,9,8}, new Object[] {1001,10,12},
				new Object[] {3000,30,12}, new Object[] {3000,31,30},
				new Object[] {3001,1,10}, new Object[] {3001,9,10}, new Object[] {3001,10,15},
				new Object[] {5000,30,15}, new Object[] {5000,31,40},
				new Object[] {5001,1,15}, new Object[] {5001,9,15}, new Object[] {5001,10,20},
				new Object[] {6000,30,20}, new Object[] {10000,31,50}
		};
	}
	
	@Test
	@Parameters(method = "getParamsForComputeCharge")
	public void testComputeCharge(double weight, double distance, double expectedResult){
		double result = ap.computeCharge(distance, weight);
		assertEquals(expectedResult, result, 0.0001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	@Parameters({"-1,1", "0,1", "1,0", "1,-1"})
	public void testComputeChargeError(double weight, double distance){
		ap.computeCharge(distance, weight);
	}
	
	
	
	@Test
	public void testAddAClient(){
		Client expectedResult = client;
		ap.addClient("Choy", "012-3456789");
		Client result = ap.getCurrentClient();
		assertEquals(expectedResult, result);
	}
	
	private Object[] paramForAddClientError(){
		return new Object[]{
				new Object[] {null, "0"},
				new Object[] {"0", null},
		};
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "paramForAddClientError")
	public void testAddClientError(String name, String phone){
		ap.addClient(name, phone);
	}
	
	
	private Object[] getParamsForSearchClient() {
		return new Object[] {
				new Object[] {"Choy", client},
				new Object[] {"Teh", null}
		};
	}
	
	@Test
	@Parameters(method="getParamsForSearchClient")
	public void testSearchClient(String name, Client expectedResult){
		ap.addClient("Choy", "012-3456789");
		Client result = ap.searchClient(name);
		assertEquals(expectedResult, result);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSearchClientError(){
		ap.searchClient(null);
	}
	
	//Add service after add client
	@Test
	public void testAddAService(){
		ap.addClient("Choy", "012-3456789");
		ap.searchClient("Choy");
		if(ap.trackService(1) == null){
			ap.addService(1, "No1", "No2", "1/1/2018", 5, ap.getCurrentClient());
			Service expectedResult = service;
			assertEquals(expectedResult, ap.getCurrentService());
		}
	}
	
	private Object[] paramForAddAServiceError(){
		return new Object[]{
				new Object[] {null, "0", "0", client},
				new Object[] {"0", null, "0", client},
				new Object[] {"0", "0", null, client},
				new Object[] {"0", "0", "0", null}
		};
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "paramForAddAServiceError")
	public void testAddAServiceError(String pAddr, String dAddr, String date, Client client){
		ap.addService(1, pAddr, dAddr, date, 0, client);
	}
	
	//search service with dStaff assigned
	@Test
	public void testSearchAService(){
		ap.addClient("Choy", "012-3456789");
		ap.searchClient("Choy");
		ap.addService(1, "No1", "No2", "1/1/2018", 5, ap.getCurrentClient());
		
		if(ap.trackService(1) != null){
			Service expectedResult = new Service(1, "No1", "No2", "1/1/2018", 5, ap.getCurrentClient());
			assertEquals(expectedResult, ap.getCurrentService());
			
			assertEquals("Choy", ap.getCurrentClient().getName());
			assertEquals("No1", ap.getCurrentService().getPickupAddr());
			assertEquals("No2", ap.getCurrentService().getDeliveryAddr());
			assertEquals("1/1/2018", ap.getCurrentService().getDate());
			assertEquals(5, ap.getCurrentService().getCharge(), 0.0001);
			assertEquals("pending", ap.getCurrentService().getStatus());

			ap.assignDStaff(ap.getCurrentService(), dStaff);
			assertEquals("delivering", ap.getCurrentService().getStatus());
			
			if(ap.getCurrentDStaff() != null){
				assertEquals(222, ap.getCurrentDStaff().getId());
			}
		}
	}
	
	//check staff availability
	@Test
	@Parameters({"111,false","222,true","333,true"})
	public void testCheckDStaffAvailability(int id, boolean expectedResult){
		assertEquals(expectedResult, ap.checkAvailable(id));
	}
	
	//assign staff
	@Test
	public void testAssignStaff(){
		ap.addClient("Choy", "012-3456789");
		ap.searchClient("Choy");
		ap.addService(1, "No1", "No2", "1/1/2018", 5, ap.getCurrentClient());
		ap.trackService(1);
		if(ap.checkAvailable(222)){
			assertEquals(dStaff, ap.getCurrentDStaff());
			assertEquals(service, ap.getCurrentService());
			ap.assignDStaff(service, dStaff);
			assertEquals(false, dStaff.getAvailable());
		}
	}
	
	//update status after assign staff
	@Test
	public void testUpdateStatus(){
		ap.addClient("Choy", "012-3456789");
		ap.searchClient("Choy");
		ap.addService(1, "No1", "No2", "1/1/2018", 5, ap.getCurrentClient());
		ap.trackService(1);
		ap.checkAvailable(222);
		ap.assignDStaff(service, dStaff);
		
		assertEquals(service, ap.getCurrentService());
		ap.updateDelivered(service);
		assertEquals(true, ap.getCurrentDStaff().getAvailable());
		assertEquals("delivered", ap.getCurrentService().getStatus());
	}
}
