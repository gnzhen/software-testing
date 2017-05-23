package courierserviceapp.domain;

public class AppController {
	private PostcodeList postcodeList;
	private Postcode postcode;
	private ClientList clientList;
	private Client currentClient;
	private DeliveryStaffList dStaffList;
	private DeliveryStaff currentDStaff;
	private ServiceList serviceList;
	private Service currentService;
	private DateTime date;
	
	public AppController(){
		postcodeList = new PostcodeList();
		clientList = new ClientList();
		dStaffList = new DeliveryStaffList();
		serviceList = new ServiceList();
		date = new DateTime();
	}
	
	public void setCurrentClient(Client client){
		currentClient = client;
	}
	
	public void setCurrentService(Service service){
		currentService = service;
	}
	
	public void setCurrentDStaff(DeliveryStaff dStaff){
		currentDStaff = dStaff;
	}

	public String getHpNo(){
		return currentDStaff.getHpNo();
	}
	
	public Client getCurrentClient(){
		return currentClient;
	}

	public Service getCurrentService(){
		return currentService;
	}
	
	public DeliveryStaff getCurrentDStaff(){
		return currentDStaff;
	}
	
	public String getDate(){
		return date.getDate();
	}
	
	public String[] getAllId(){
		return dStaffList.getAllId();
	}	
	
	public boolean checkRegion(int postcode){
		return postcodeList.checkRegion(postcode);
	}
	
	public double computeCharge(double distance, double weight){
		double charge = 0;
		
		if(weight > 0 && distance > 0){
			if(weight > 5000){
				if(distance > 30)
					charge = 50;
				else if(distance >= 10)
					charge = 20;
				else
					charge = 15;
			}
			else if(weight > 3000){
				if(distance > 30)
					charge = 40;
				else if(distance >= 10)
					charge = 15;
				else
					charge = 10;
			}
			else if(weight > 1000){
				if(distance > 30)
					charge = 30;
				else if(distance >= 10)
					charge = 12;
				else
					charge = 8;
			}
			else if(weight >= 300){
				if(distance > 30)
					charge = 20;
				else if(distance >= 10)
					charge = 10;
				else
					charge = 8;
			}
			else{
				charge = 5;
			}
		}
		else{
			throw new IllegalArgumentException("Weight or distance not in range.");
		}
		return charge;
	}
	
	public void addClient(String name, String phone){
		if(name == null || phone == null){
			throw new IllegalArgumentException("Null string.");
		}
		else{
			currentClient = new Client(name, phone);
			clientList.addClient(currentClient);
		}
	}
	
	public void addService(int trackNo, String pAddr, String dAddr, 
		String date, double charge, Client client){
		if(pAddr == null || dAddr == null || date == null || client == null){
			throw new IllegalArgumentException();
		}
		else{
			currentService = new Service(trackNo, pAddr, dAddr, date, charge, currentClient);
			serviceList.addService(currentService);
		}
	}
	
	public Client searchClient(String name){
		if(name == null){
			throw new IllegalArgumentException();
		}
		else{
			currentClient = clientList.searchClient(name);
			return currentClient;
		}
	}
	
	public Service trackService(int trackNo){
		currentService = serviceList.trackService(trackNo);
		
		if(currentService != null){
			currentClient = currentService.getClient();
			if(currentService.getDStaff() != null)
				currentDStaff = currentService.getDStaff();
		}
		return currentService;
	}
	
	public boolean checkAvailable(int id){
		currentDStaff = dStaffList.getStaffById(id);
		return dStaffList.checkAvailable(currentDStaff);
	}
	
	public void assignDStaff(Service service, DeliveryStaff dStaff){
		serviceList.assignDStaff(service, dStaff);
		dStaff.setAvailability(false);
	}
	
	public void updateDelivered(Service service){
		currentService = service;
		service.updateDelivered();
		currentDStaff.setAvailability(true);
	}
}
