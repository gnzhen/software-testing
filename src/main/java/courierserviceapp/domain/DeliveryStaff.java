package courierserviceapp.domain;

import java.util.Objects;

public class DeliveryStaff {
	private int id;
	private String hpNo;
	private boolean available;
	
	public DeliveryStaff(int id, String hpNo, boolean available){
		this.id = id;
		this.hpNo = hpNo;
		this.available = available;
	}
	
	public int getId(){
		return id;
	}
	
	public String getHpNo(){
		return hpNo;
	}
	
	public boolean getAvailable(){
		return available;
	}
	
	public void setAvailability(boolean availability){
		this.available = availability;
	}
	
	@Override
    public boolean equals(Object o) {

		if (o == this) return true;
        if (!(o instanceof DeliveryStaff)) {
            return false;
        }

        DeliveryStaff dStaff = (DeliveryStaff) o;

        return dStaff.id == id &&
        		dStaff.hpNo.equals(hpNo) &&
        		dStaff.available == available;
	}
	
	 @Override
	    public int hashCode() {
	        return Objects.hash(id, hpNo, available);
	 }
}
