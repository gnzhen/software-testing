package courierserviceapp.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses(value = {AppControllerMockTest.class, 
		AppControllerTest.class,
		ClientListTest.class,
		ClientTest.class,
		DateTimeTest.class,
		DeliveryStaffListTest.class,
		DeliveryStaffTest.class,
		PostcodeListTest.class,
		PostcodeTest.class,
		ServiceListTest.class,
		ServiceTest.class
})
public class RegressionUnitTestSuite {

}
