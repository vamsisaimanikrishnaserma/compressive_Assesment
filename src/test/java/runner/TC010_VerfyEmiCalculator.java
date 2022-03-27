package runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobject.EmiCalculatorPage;
import pageobject.HomePage;
import utility.BaseClass;
import utility.ConfigReader;

public class TC010_VerfyEmiCalculator extends BaseClass{

	@Test (dataProvider = "emiDetailsProvider")
	public void emiCalculator(String loanAmount,String rateOfIntrest, String loanTenure, String monthlyEmi, String principalAmount, String totalIntrest, String totalAmount) throws IOException, InterruptedException {
		boolean result=true;
		HomePage home = new HomePage();
		log.createLogger(getClass());
		exReport.createNewTestReport("Emi CalCulator");
		log.info("Emi CalCulator");
		exReport.enterInfoLog("Emi CalCulator");
		home.openEmiCalculator();
		EmiCalculatorPage emiCalculator = new EmiCalculatorPage();
		emiCalculator.calculate(loanAmount, rateOfIntrest, loanTenure);
		String actualPrincipalAmount=emiCalculator.getPrincipalAmount().substring(1);
		String actualTotalIntrest = emiCalculator.getTotalIntrest().substring(1);
		String actualTotalAmount=emiCalculator.getTotalAmount().substring(1);
		String actualMonthlyEmi=emiCalculator.getMonthlyEmi().substring(1);
		
		if (actualPrincipalAmount.equalsIgnoreCase(principalAmount)) {
			log.info("Expected principal Amount: "+principalAmount);
			log.info("Actual principal Amount: "+emiCalculator.getPrincipalAmount());
			exReport.enterPassLog("Expected principal Amount: "+principalAmount+"<br />"+"Actual principal Amount: "+actualPrincipalAmount);
		}
		else {
			log.info("Expected principal Amount: "+principalAmount);
			log.info("Actual principal Amount: "+emiCalculator.getPrincipalAmount());
			exReport.enterFailLog("Expected principal Amount: "+principalAmount+"<br />"+"Actual principal Amount: "+actualPrincipalAmount);
			result=false;
		}
		if (actualMonthlyEmi.equalsIgnoreCase(monthlyEmi)) {
			log.info("Expected montly Emi: "+monthlyEmi);
			log.info("Actual montly Emi: "+actualMonthlyEmi);
			exReport.enterPassLog("Expected montly emi: "+monthlyEmi+"<br />"+"Actual montly Emi: "+actualMonthlyEmi);
		}
		else {
			log.info("Expected montly Emi: "+monthlyEmi);
			log.info("Actual montly Emi: "+actualMonthlyEmi);
			exReport.enterFailLog("Expected montly emi: "+monthlyEmi+"<br />"+"Actual montly Emi: "+actualMonthlyEmi);
			result=false;
			
		}
		if (actualTotalIntrest.equalsIgnoreCase(totalIntrest)) {
			log.info("Expected Total Intrest: "+totalIntrest);
			log.info("Actual Total Intrest: "+actualTotalIntrest);
			exReport.enterPassLog("Expected Total Intrest: "+totalIntrest+"<br />"+"Actual Total Intrest: "+actualTotalIntrest);
		}
		else {
			log.info("Expected Total Intrest: "+totalIntrest);
			log.info("Actual Total Intrest: "+actualTotalIntrest);
			exReport.enterFailLog("Expected Total Intrest: "+totalIntrest+"<br />"+"Actual Total Intrest: "+actualTotalIntrest);
			result=false;
		}
		if(actualTotalAmount.equalsIgnoreCase(totalAmount)) {
			log.info("Expected Total amount: "+totalAmount);
			log.info("Actual Total Amount: "+actualTotalAmount);
			exReport.enterPassLog("Expected Total Amount: "+totalAmount+"<br />"+"Actual Total Intrest: "+actualTotalAmount);
		}
		else {
			log.info("Expected Total amount: "+totalAmount);
			log.info("Actual Total Amount: "+actualTotalAmount);
			exReport.enterFailLog("Expected Total Amount: "+totalAmount+"<br />"+"Actual Total Intrest: "+actualTotalAmount);
			result=false;
			
		}
		if(result==true) {
			exReport.enterPassLogWithSnap("Calculations are as expected");
			Assert.assertTrue(result);
		}
		else {
			exReport.enterFailLogWithSnap("Expected and Acutual values are not matching");
			Assert.assertTrue(result);
		}

	}
	@DataProvider (name = "emiDetailsProvider")
    public static Object[][] dataProviderMethod(ITestContext context) throws IOException {
		List<String> keys = new ArrayList<String>();
		keys.add("Loan_Amount");
		keys.add("Rate_of_Interest");
		keys.add("Loan_Tenure");
		keys.add("Monthly EMI");
		keys.add("Principal_Amount");
		keys.add("TotalInterest");
		keys.add("Total Amount");
		String testID = context.getCurrentXmlTest().getParameter("TestID");
		Object got[][] = excel.getExcelData(ConfigReader.getExcelPath(), testID, keys);
		return got;
    }

}
