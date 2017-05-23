package courierserviceapp.test;

import courierserviceapp.domain.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.mockito.Matchers.anyString;

@RunWith(JUnitParamsRunner.class)
public class DeliveryStaffListTest {

	DeliveryStaffList dsl = new DeliveryStaffList();
	DeliveryStaff ds1 = new DeliveryStaff(111, "", true);
	DeliveryStaff ds2 = new DeliveryStaff(222, "", true);
	DeliveryStaff ds3 = new DeliveryStaff(333, "", true);

	ArrayList<DeliveryStaff> dss = new ArrayList<DeliveryStaff>();
	
	
	@Test
	public void testGetDStaffs(){
		DeliveryStaffList dsl = new DeliveryStaffList(ds1);
		dss.add(ds1);
		assertEquals(dsl.getDStaffs(), dss); 
	}
	
	@Test
	public void testGetAllId(){
		String[] result = dsl.getAllId();
		String[] expectedResult = {"111", "222", "333"};
		
		assertArrayEquals(expectedResult, result);
	}
	
	@Test
	public void testAddDStaff(){
		DeliveryStaffList dslMock = mock(DeliveryStaffList.class);
		dss.add(ds1);

		when(dslMock.getDStaffs()).thenReturn(dss);
		dslMock.addDStaff(ds1);
		assertEquals(dslMock.getDStaffs().get(0), ds1);
	}
	
	
	private Object[] paramForTestCheckAvailable(){
		return new Object[]{
				new Object[] {ds1, false},
				new Object[] {ds2, true},
				new Object[] {ds2, true},
		};
	}
	
	@Test
	@Parameters(method = "paramForTestCheckAvailable")
	public void testCheckAvailable(DeliveryStaff ds, boolean expectedResult){
		assertEquals(expectedResult, dsl.checkAvailable(ds));
	}
	
	
	@Test
	public void testGetStaffById(){
		dsl.addDStaff(ds1);
		assertEquals(ds1, dsl.getStaffById(111)); 
	}
}
