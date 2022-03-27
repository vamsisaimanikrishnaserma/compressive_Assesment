package runner;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject.HomePage;
import utility.BaseClass;

public class TC008_DownloadAndrodApp extends BaseClass {

	@Test
	public void downloadAndroidApp() throws IOException {
		HomePage home = new HomePage();
		log.createLogger(getClass());
		exReport.createNewTestReport("Download Android app");
		log.info("Download Android app");
		boolean result = home.downloadAndroidApp();
		if(result==true) {
			exReport.enterPassLogWithSnap("Playstore opened with no broker app");
			log.info("Download Android app");
			Assert.assertTrue(result);
		}
		else {
			exReport.enterFailLogWithSnap("Playstore opened with no broker app FAILED");
			log.info("Download Android app FAILED");
			Assert.assertTrue(result);
		}
	}

}
