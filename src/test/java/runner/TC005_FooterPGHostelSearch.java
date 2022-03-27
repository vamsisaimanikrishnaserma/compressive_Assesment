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

public class TC005_FooterPGHostelSearch extends BaseClass{

	@Test (dataProvider = "pgCityProvider")
	public void footerPGHostels(String city) throws IOException, InterruptedException {
		HomePage home = new HomePage();
		log.createLogger(getClass());
		exReport.createNewTestReport("Pg hostel city search ");
		log.info("Pg hostel city search ");
		boolean result = home.searchFooterPgHostels(city);;
		if(result==true) {
			exReport.enterPassLogWithSnap("Pg hostel city found");
			log.info("New Bilder Properties ");
			Assert.assertTrue(result);
		}
		else {
			exReport.enterFailLogWithSnap("Pg hostel city Not found ");
			log.info("Pg hostel city search success  FAILED");
			Assert.assertTrue(result);
		}
	}
	
	@DataProvider (name = "pgCityProvider")
    public static Object[][] dataProviderMethod(ITestContext context) throws IOException {
		List<String> keys = new ArrayList<String>();
		keys.add("city");
		String testID = context.getCurrentXmlTest().getParameter("TestID");
		Object got[][] = excel.getExcelData(ConfigReader.getExcelPath(), testID, keys);
		return got;
    }


}
