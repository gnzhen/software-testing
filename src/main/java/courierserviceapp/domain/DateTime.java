package courierserviceapp.domain;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateTime {
	
	private static DateFormat df = new SimpleDateFormat("dd/MM/yy");
    private static Date date;
    private String dateToday;
    
    public DateTime(){
    	date = new Date();
    	dateToday = df.format(date);	
    }
    
    public String getDate(){
    	return dateToday;
    }
}	

