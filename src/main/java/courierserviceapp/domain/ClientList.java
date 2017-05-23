package courierserviceapp.domain;

import java.util.ArrayList;

public class ClientList {
	private ArrayList<Client> clients;
	
	public ClientList(){
		clients = new ArrayList<Client>();
	}
	
	public ArrayList<Client> getClients(){
		return clients;
	}
	
	public void addClient(Client client){
		clients.add(client);
	}
	
	public Client searchClient(String name){
		Client clientFound = null;
		
		for(Client c : clients){
			if(c.getName().equals(name)){
				clientFound = c;
			}
		}
		return clientFound;
	}
	
}
