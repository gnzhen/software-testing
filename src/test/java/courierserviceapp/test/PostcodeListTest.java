package courierserviceapp.test;

import courierserviceapp.domain.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class PostcodeListTest {
	
	@Test
	@Parameters({"43000, true", "40000, false"})
	public void testCheckRegion(int postcode, boolean expectedResult){
		PostcodeList pl = new PostcodeList();
		boolean result = pl.checkRegion(postcode);
		
		assertEquals(expectedResult, result);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCheckRegionInvalid(){
		PostcodeList pl = new PostcodeList();
		pl.checkRegion(-1);
	}
}
