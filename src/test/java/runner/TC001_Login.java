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

public class TC001_Login extends BaseClass {
	@DataProvider (name = "logInDataProvide")
    public static Object[][] dataProviderMethod(ITestContext context) throws IOException {
		List<String> keys = new ArrayList<String>();
		keys.add("phoneNumber");
		keys.add("password");
		String testID = context.getCurrentXmlTest().getParameter("TestID");
		Object got[][] = excel.getExcelData(ConfigReader.getExcelPath(), testID, keys);
		return got;
    }
	 
	@Test (dataProvider = "logInDataProvide")
	public void loginTest(String phNumber, String password) throws IOException {	
		HomePage home = new HomePage();
		log.createLogger(getClass());
		exReport.createNewTestReport("login test");
		log.info("Login test Started");
		boolean result = home.login(phNumber, password);
		if(result==true) {
			exReport.enterPassLogWithSnap("Login success");
			log.info("Login Success");
			Assert.assertTrue(result);
		}
		else {
			exReport.enterFailLogWithSnap("Login FAILED");
			log.info("Login FAILED");
			Assert.assertTrue(result);
		}
	}
	
	
}
