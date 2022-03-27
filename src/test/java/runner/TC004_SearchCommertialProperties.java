package runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobject.HomePage;
import utility.BaseClass;
import utility.ConfigReader;

public class TC004_SearchCommertialProperties extends BaseClass {

	@Test (dataProvider = "commertialDataProvide")
	public void searchCommertialProperties(String address) throws IOException {
		HomePage home = new HomePage();
		log.createLogger(getClass());
		exReport.createNewTestReport("Search for Commertial properties");
		log.info("Starting commertial search test");
		boolean result = home.searchCommertial(address);
		if(result==true) {
			exReport.enterPassLogWithSnap("Search for Rent properties success");
			log.info("Search for Rent properties Success");
			Assert.assertTrue(result);
		}
		else {
			exReport.enterFailLogWithSnap("Search for Rent propertiesFAILED");
			log.info("Search for Rent properties FAILED");
			Assert.assertTrue(result);
		}
	}
	
	@DataProvider (name = "commertialDataProvide")
    public static Object[][] dataProviderMethod(ITestContext context) throws IOException {
		List<String> keys = new ArrayList<String>();
		keys.add("addresses");
		String testID = context.getCurrentXmlTest().getParameter("TestID");
		Object got[][] = excel.getExcelData(ConfigReader.getExcelPath(), testID, keys);
		return got;
    }

}
