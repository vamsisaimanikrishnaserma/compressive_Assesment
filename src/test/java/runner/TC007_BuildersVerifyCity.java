package runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobject.BuilderPropertiesPage;
import pageobject.HomePage;
import utility.BaseClass;
import utility.ConfigReader;

public class TC007_BuildersVerifyCity extends BaseClass {
	
	@Test (dataProvider = "cityData")
	public void getBuilderProperties(String city) throws IOException, InterruptedException {
		HomePage home = new HomePage();	
		log.createLogger(getClass());
		exReport.createNewTestReport("New Project location");
		log.info("New Project location verify started");
		exReport.enterInfoLog("New Project location verify started");
		home.openBuilderProjects(city);
		BuilderPropertiesPage builderPage = new BuilderPropertiesPage();
		String result = builderPage.getHeadingOfResults();
		
		if(result.contains(city)) {
			exReport.enterPassLogWithSnap("New Projects are displaying according to cites ");
			log.info("New Projects are displaying according to cites ");
			Assert.assertTrue(true);
		}
		else {
			exReport.enterFailLogWithSnap("New Projects are not displaying according to cites ");
			log.info("Builder Project");
			Assert.assertTrue(false);
		}
	}
	@DataProvider (name = "cityData")
    public static Object[][] dataProviderMethod(ITestContext context) throws IOException {
		List<String> keys = new ArrayList<String>();
		keys.add("city");
		String testID = context.getCurrentXmlTest().getParameter("TestID");
		Object got[][] = excel.getExcelData(ConfigReader.getExcelPath(), testID, keys);
		return got;
    }

}
