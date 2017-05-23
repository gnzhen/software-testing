package courierserviceapp.test;

import courierserviceapp.domain.*;

import org.junit.Test;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeTest {
	DateTime dt = new DateTime();

	@Test
	public void testGetDate(){
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		Date date = new Date();
    	String dateToday = df.format(date);
		assertEquals(dateToday, dt.getDate());
	}
}
