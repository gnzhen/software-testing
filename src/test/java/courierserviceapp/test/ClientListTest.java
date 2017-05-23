package courierserviceapp.test;

import courierserviceapp.domain.*;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class ClientListTest {
	ClientList cl = new ClientList();
	Client c = new Client("choy","017");
	ArrayList<Client> clients = new ArrayList<Client>();
	
	@Test
	public void testGetClients(){
		cl.addClient(c);
		clients.add(c);
		assertEquals(cl.getClients(), clients); 
	}
	
	@Test
	public void testAddClient(){
		ClientList clMock = mock(ClientList.class);
		clients.add(c);

		when(clMock.getClients()).thenReturn(clients);
		clMock.addClient(c);
		assertEquals(clMock.getClients().get(0), c);
	}
	
	@Test
	public void testSearchClient(){
		cl.addClient(c);
		assertEquals(c, cl.searchClient("choy"));
	}
}
