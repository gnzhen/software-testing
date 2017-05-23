package courierserviceapp.domain;

import java.util.ArrayList;

public class PostcodeList {
private ArrayList<Postcode> postcodes;
	
	public PostcodeList(){
		postcodes = new ArrayList<Postcode>();
		postcodes.add(new Postcode(43000));
		postcodes.add(new Postcode(43007));
		postcodes.add(new Postcode(43009));
		postcodes.add(new Postcode(43558));
	}
	
	public ArrayList<Postcode> getPostcodes(){
		return postcodes;
	}
	
	public boolean checkRegion(int postcode){
		boolean available = false;
		
		if(postcode > 0){
			for(Postcode p: postcodes){
				if(p.getPostcode() == postcode)
					available = true;
			}
		}
		else{
			throw new IllegalArgumentException("Illegal postcode!");
		}
		
		return available;
	}
}
