package runner;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageobject.HomePage;
import utility.BaseClass;

public class TC003_VerfiyFanPagesWorking extends BaseClass{

	@Test
	public void verifyFanPages() throws IOException, InterruptedException {
		boolean flag=true;
		HomePage home = new HomePage();	
		log.createLogger(getClass());
		exReport.createNewTestReport("Verifying fan pages");
		log.info("Verifying fan pages");
		exReport.enterInfoLog("Verifying fan pages");
		if(home.checkFaceBook()) {
			webDriver.waitUntilCompletelyLoad();
			exReport.enterPassLogWithSnap("Opened Facebook");
			log.info("Opened Facebook");
		}
		else {
			webDriver.waitUntilCompletelyLoad();
			exReport.enterFailLogWithSnap("Unable to open Facebook");
			log.info("Unable to open Facebook");
			flag=false;
		}
		webDriver.switchTab(0);
		if(home.checkTwitter()) {
			webDriver.waitUntilCompletelyLoad();
			exReport.enterPassLogWithSnap("Opened Twitter");
			log.info("Opened Twitter");
			
		}
		else {
			webDriver.waitUntilCompletelyLoad();
			exReport.enterFailLogWithSnap("Unable to open Twitter");
			log.info("Unable to open Twitter");
			flag=false;
		}
		webDriver.switchTab(0);
		if(home.checkLinkdin()) {
			webDriver.waitUntilCompletelyLoad();
			exReport.enterPassLogWithSnap("Opened Linkdin");
			log.info("Opened Linkdin");
			
		}
		else {
			webDriver.waitUntilCompletelyLoad();
			exReport.enterFailLogWithSnap("Unable to open Linkdin");
			log.info("Unable to open Linkdin");
			flag=false;
		}
		webDriver.switchTab(0);
		if(home.checkYouTube()) {
			webDriver.waitUntilCompletelyLoad();
			exReport.enterPassLogWithSnap("Opened YouTube");
			log.info("Opened YouTube");
			
		}
		else {
			webDriver.waitUntilCompletelyLoad();
			exReport.enterFailLogWithSnap("Unable to open YouTube");
			log.info("Unable to open YouTube");
			flag=false;
		}
		webDriver.switchTab(0);
		if(home.checkInsta()) {
			webDriver.waitUntilCompletelyLoad();
			exReport.enterPassLogWithSnap("Opened Insta");
			log.info("Opened Insta");
			
		}
		else {
			webDriver.waitUntilCompletelyLoad();
			exReport.enterFailLogWithSnap("Unable to open Insta");
			log.info("Unable to open Insta");
			flag=false;
		}
		
		if(flag==false)
			Assert.assertFalse(flag);
	}
	

}
