package runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobject.HomePage;
import pageobject.SearchResultPage;
import reusable.AllLogger;
import utility.BaseClass;
import utility.ConfigReader;

public class TC009SearchRentProperties extends BaseClass {
	@Test (dataProvider = "commertialDataProvide")
	public void searchRentProperties(String city, String no) throws IOException {
		HomePage home = new HomePage();
		log.createLogger(getClass());
		exReport.createNewTestReport("Search for Rent properties");
		log.info("Starting rent search test");
		SearchResultPage searchResult = new SearchResultPage();
		boolean result = home.searchRent(city);
		if(Integer.parseInt(no)==searchResult.resultCount()) {
			AllLogger.passLog("Total results found are :"+searchResult.resultCount());
			AllLogger.passLog("Expected Results are :"+Integer.parseInt(no));
			exReport.enterPassLogWithSnap("Rent properties display success");
			log.info("Search for Rent properties Success");
			Assert.assertTrue(result);
		}
		else {
			AllLogger.infoLog("Total results found are :"+searchResult.resultCount());
			
			AllLogger.infoLog("Expected Results are :"+Integer.parseInt(no));
			exReport.enterFailLogWithSnap("Rent properties display  FAILED");
			log.info("Search for Rent properties FAILED");
			Assert.assertTrue(result);
		}
	}

	@DataProvider (name = "commertialDataProvide")
    public static Object[][] dataProviderMethod(ITestContext context) throws IOException {
		List<String> keys = new ArrayList<String>();
		keys.add("addresses");
		keys.add("Number_of_results");
		String testID = context.getCurrentXmlTest().getParameter("TestID");
		Object got[][] = excel.getExcelData(ConfigReader.getExcelPath(), testID, keys);
		return got;
    }

}
