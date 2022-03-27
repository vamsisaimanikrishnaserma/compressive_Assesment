package runner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import utility.BaseClass;


public class TestRunner extends BaseClass {

	@DataProvider (name = "data-provider")
    public Object[][] dpMethod(){
	 return new Object[][] {{"First-Value"}, {"Second-Value"}};
    }
	
   @Test (dataProvider = "data-provider")
   public void myTest (String val) {
	   log.createLogger(getClass());
	   log.info(val);
       System.out.println("Passed Parameter Is : " + val);
   }
}
