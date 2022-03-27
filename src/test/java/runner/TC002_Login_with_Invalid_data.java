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

public class TC002_Login_with_Invalid_data extends BaseClass {
	 
	@Test (dataProvider = "logInDataProvide")
	public void loginTest(String phNumber, String password) throws IOException {
		System.out.println(phNumber);
		System.out.println(password);
		
		HomePage home = new HomePage();
		log.createLogger(getClass());
		exReport.createNewTestReport("login with invalid test");
		log.info("Login test Started");
		boolean result = home.login(phNumber, password);
		if(result==false) {
			exReport.enterPassLogWithSnap("It doesnt allowed to login");
			log.info("Login Success");
			Assert.assertTrue(true);
		}
		else {
			exReport.enterFailLogWithSnap("Login success with invalid data FAILED");
			log.info("Login success with invalid data FAILED");
			Assert.assertTrue(result);
		}
	}
	
	@DataProvider (name = "logInDataProvide")
    public static Object[][] dataProviderMethod(ITestContext context) throws IOException {
		List<String> keys = new ArrayList<String>();
		keys.add("phoneNumber");
		keys.add("password");
		String testID = context.getCurrentXmlTest().getParameter("TestID");
		Object got[][] = excel.getExcelData(ConfigReader.getExcelPath(), testID, keys);
		return got;
    }
}
