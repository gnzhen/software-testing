package courierserviceapp.test;

import courierserviceapp.domain.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class ServiceListTest {
	ServiceList sl;
	ArrayList<Service> services;
	Service s;
	DeliveryStaff ds;
	
	@Before
	public void initializeClass(){
		sl = new ServiceList();
		services = new ArrayList<Service>();
		s = new Service(1,"","","",1,null);
		ds = new DeliveryStaff(111,"",true);
	}
	
	@InjectMocks
	AppController apMock;
	
	@Mock
	ServiceList slMock;
	
	@Mock
	Service sMock;
	
	@Mock
	ArrayList<Service> ssMock;
	
	@Test
	public void testGetService(){
		sl.addService(s);
		services.add(s);
		assertEquals(sl.getServices(), services); 
	}
	
	@Test
	public void testAddService(){
		slMock.addService(s);
		services.add(s);
		when(slMock.getServices()).thenReturn(services);
		assertEquals(s, slMock.getServices().get(0));
	}
	
	@Test
	public void testTrackService(){
		sl.addService(s);
		assertEquals(s, sl.trackService(1));
	}
	
	@Test
	public void testAssignDStaff(){
		sl.assignDStaff(sMock, ds);
		verify(sMock).setDeliveryStaff(ds);
	}
}
