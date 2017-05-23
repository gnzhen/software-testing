package courierserviceapp.test;

import courierserviceapp.domain.*;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AppControllerMockTest {
	PostcodeList postcodeList;
	ClientList clientList;
	DeliveryStaff dStaff;
	Client client;
	Service service;
	
	@Before
	public void initializeClass(){
		postcodeList = new PostcodeList();
		clientList = new ClientList();
		dStaff = new DeliveryStaff(111, "012", true);
		client = new Client("choy","017");
		service = new Service(1,"","","",1,client);
	}
	
	@InjectMocks
	AppController apMock = new AppController();

	@Mock
	DeliveryStaff dsMock;
	@Mock
	ServiceList slMock;
	@Mock
	DateTime dateMock;
	@Mock
	DeliveryStaffList dslMock;
	@Mock
	PostcodeList pclMock;
	@Mock
	ClientList clMock;
	@Mock
	Service sMock;
	@Mock
	Client cMock;
	
	@Test
	public void testGetHpNo(){
		apMock.setCurrentDStaff(dsMock);
		when(dsMock.getHpNo()).thenReturn("0");
		
		assertEquals("0",apMock.getHpNo());
		verify(dsMock).getHpNo();
	}
	
	@Test
	public void testGetDate(){
		when(dateMock.getDate()).thenReturn("1/1/2018");
		assertEquals("1/1/2018", apMock.getDate());
		verify(dateMock).getDate();
	}
	
	@Test
	public void testGetAllId(){
		String[] expectedResult = {"111", "222", "333"};
		when(dslMock.getAllId()).thenReturn(expectedResult);
		
		assertArrayEquals(expectedResult, apMock.getAllId());
		verify(dslMock).getAllId();
	}
	
	@Test
	public void testCheckRegion(){
		when(pclMock.checkRegion(43000)).thenReturn(true);
		
		assertEquals(true, apMock.checkRegion(43000));
		verify(pclMock).checkRegion(43000);
	}
	
	@Test
	public void testAddClient(){
		apMock.setCurrentClient(client);
		assertEquals(client, apMock.getCurrentClient());
		apMock.addClient(client.getName(), client.getPhone());
		verify(clMock).addClient(apMock.getCurrentClient());
	}
	
	@Test
	public void testAddService(){
		apMock.setCurrentService(service);
		assertEquals(service, apMock.getCurrentService());
		
		
		apMock.addService(service.getTrackNo(), service.getPickupAddr(), service.getDeliveryAddr(),
				service.getDate(), service.getCharge(), service.getClient());
		
		verify(slMock).addService(apMock.getCurrentService());
	}
	
	@Test
	public void testSearchClient(){
		when(clMock.searchClient("")).thenReturn(client);
		assertEquals(client, apMock.searchClient(""));
		
		verify(clMock).searchClient("");
	}
	
	@Test
	public void testTrackService(){
		when(slMock.trackService(0)).thenReturn(sMock);
		assertEquals(sMock, apMock.trackService(0));
		assertEquals(sMock, apMock.getCurrentService());
		verify(slMock).trackService(0);
		

		verify(sMock).getClient();
		when(sMock.getClient()).thenReturn(client);
		//assertEquals(client, apMock.getCurrentClient());

		verify(sMock).getDStaff();
		when(sMock.getDStaff()).thenReturn(dStaff);
		//assertEquals(dStaff, apMock.getCurrentDStaff());
	}
	
	@Test
	public void testCheckAvailable(){
		when(dslMock.getStaffById(0)).thenReturn(dStaff);
		apMock.checkAvailable(0);
		assertEquals(dStaff, apMock.getCurrentDStaff());
		
		verify(dslMock).getStaffById(0);
	}
	
	@Test
	public void testAssignStaff(){
		apMock.assignDStaff(service, dStaff);
		verify(slMock).assignDStaff(service, dStaff);
		assertEquals(false, dStaff.getAvailable());
	}
	
	@Test
	public void testUpdateDelivered(){
		apMock.updateDelivered(sMock);
		verify(sMock).updateDelivered();
		apMock.setCurrentDStaff(dStaff);
		assertEquals(true, apMock.getCurrentDStaff().getAvailable());
	}
}
