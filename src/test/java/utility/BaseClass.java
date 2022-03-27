package utility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import reusable.WebDriverHelper;
public class BaseClass {
	static public WebDriverHelper webDriver;
	static public WebDriver driver;
	static public Log log;
	static public ExtentReport exReport;
	static public WebDriverWait wait;
	static public ExcelDataReader excel;

	@BeforeMethod
	public void required() {
		System.setProperty("extent.reporter.avent.config","C:\\Users\\91984\\eclipse-workspace\\Comprehensive Hybrid\\configs\\config.properties");
		log.createLogger(getClass());
	
		webDriver = new WebDriverHelper();
	
		if (ConfigReader.getBrowser().contains("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\91984\\eclipse-workspace\\Comprehensive Hybrid\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			log.info("Driver set as Chrome");
		} else if (ConfigReader.getBrowser().contains("firefox")) {
			System.setProperty("webdriver.gecko.driver","C:\\Users\\91984\\eclipse-workspace\\Comprehensive Hybrid\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Driver set as firefox");
		}
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver,Duration.ofSeconds(ConfigReader.getExplicitWait()));
		log.info("Driver Maximizes");
		
	}

	@BeforeClass
	public void driverSetUp() throws IOException {
		excel = new ExcelDataReader();
		
	}
	@BeforeSuite
	public void extendReportSetup() {
		System.setProperty("log4j2.configurationFile","C:\\Users\\91984\\eclipse-workspace\\Comprehensive Hybrid\\configs\\log4j2-config.xml");
		log = new Log();
		exReport = new ExtentReport();
	}

	@AfterMethod
	public void driverExit() throws IOException {
		//exReport.endTest();
		driver.quit();
	}
	@AfterSuite
	public void flush() {
		exReport.flush();
	}

}
