package courierserviceapp.domain;

import java.util.Objects;

public class Client {
	private String name;
	private String phone;
	
	public Client(String name, String phone){
		this.name = name;
		this.phone = phone;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPhone(){
		return phone;
	}
	
	@Override
    public boolean equals(Object o) {

		if (o == this) return true;
        if (!(o instanceof Client)) {
            return false;
        }

        Client client = (Client) o;

        return client.name.equals(name) &&
                client.phone.equals(phone);
	}
	
	 @Override
	    public int hashCode() {
	        return Objects.hash(name, phone);
	 }
}
