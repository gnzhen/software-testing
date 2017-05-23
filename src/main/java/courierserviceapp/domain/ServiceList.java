package courierserviceapp.domain;

import java.util.ArrayList;

public class ServiceList {

	private ArrayList<Service> services;
	
	public ServiceList(){
		services = new ArrayList<Service>();
	}
	
	public ArrayList<Service> getServices(){
		return services;
	}
	
	public void addService(Service service){
		services.add(service);
	}
	
	public Service trackService(int trackNo){
		Service service = null;
		for(Service s : services){
			if(trackNo == s.getTrackNo()){
				service = s;
			}
		}
		return service;
	}
	
	public void assignDStaff(Service service, DeliveryStaff dStaff){
		service.setDeliveryStaff(dStaff);
	}
}
