package runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobject.HomePage;
import pageobject.ProfilePage;
import utility.BaseClass;
import utility.ConfigReader;

public class TC006_LoginAndVerifyDetails extends BaseClass {

	@Test (dataProvider = "loginVerifyDetails")
	public void loginAndVerifyDetails(String phoneNumber, String password, String name,String email,String phone) throws IOException, InterruptedException {
		boolean result=false;
		HomePage home = new HomePage();
		log.createLogger(getClass());
		exReport.createNewTestReport("Login and verify Details");
		log.info("Entering in to login Test");
		exReport.enterInfoLog("Entering in to login Test");
		boolean resultLogin = home.login(phoneNumber, password);
		if(resultLogin==true) {
			ProfilePage profile= new ProfilePage();
			result=profile.verifyDeatils(name,email,phone);
		}
		if(result==true) {
			exReport.enterPassLogWithSnap("Deatils Matched ");
			log.info("Login and verify Details");
			Assert.assertTrue(result);
		}
		else {
			exReport.enterFailLogWithSnap("Deatils doesnt Matched");
			log.info("Login and verify Details  FAILED");
			Assert.assertTrue(result);
		}
	}

	@DataProvider (name = "loginVerifyDetails")
    public static Object[][] dataProviderMethod(ITestContext context) throws IOException {
		List<String> keys = new ArrayList<String>();
		keys.add("phoneNumber");
		keys.add("password");
		keys.add("name");
		keys.add("email");
		keys.add("phone");
		String testID = context.getCurrentXmlTest().getParameter("TestID");
		Object got[][] = excel.getExcelData(ConfigReader.getExcelPath(), testID, keys);
		return got;
    }
	

}
