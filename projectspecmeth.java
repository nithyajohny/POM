package Base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ReadExcelData2;

public class projectspecmeth {
	public String excelFileName="";
	public static ExtentReports extent;
	public static ExtentTest test;
	public String testname,testdescription,testcategory,testauthor;
	public static int i=1;
	//public static ChromeDriver driver;//with this static you can not run parallelly in pageobj model.so use threadloccal class in java.
	private static final ThreadLocal<RemoteWebDriver> rwd=new ThreadLocal<RemoteWebDriver>();
	//setter method for driver
	public void setDriver()
	{
		rwd.set(new ChromeDriver());
	}

	//getter method for driver
	public RemoteWebDriver getDriver()
	{
		return rwd.get();
	}
	public void startreport() {
		// Step1 to Step3: Common for all the test cases
		// Step1: Set up physical report path
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./reports/result.html");
		// to keep the report history
		reporter.setAppendExisting(true);
		// Step2: Create object for ExtentReports
		extent = new ExtentReports();
		// Step3: attach the data with physical file
		extent.attachReporter(reporter);
}
	@BeforeClass
	public void testCaseDetails() 
	{
		test = extent.createTest(testname, testdescription);
		test.assignCategory(testcategory);
		test.assignAuthor(testauthor);
	}

	public int takesnap()
	{
		int ranNum=(int)(Math.random()*999999+1000000);
		File source=getDriver().getScreenshotAs(OutputType.FILE);
		File target=new File("./snaps/img"+ranNum+".png");
		return ranNum;
	}
	
	public void reportStep(String stepDesc,String status)
	{
		int ranNum=takesnap();
		MediaEntityModelProvider img=null;
		try {
			img=MediaEntityBuilder.createScreenCaptureFromPath(".././snaps/desktop.png").build();
		} catch (IOException e) {
			
		}
		
		if(status.equalsIgnoreCase("fail"))
		{
			test.pass(stepDesc,img);
		}else if(status.equalsIgnoreCase("fail"))
				{
			test.fail(stepDesc,img);
        throw new RuntimeException("Look in report for more");
				}
	}
	@BeforeMethod
	public void init()
	{
		WebDriverManager.chromedriver().setup();
		setDriver();
		getDriver().get("http://leaftaps.com/opentaps/control/login"); 
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	@AfterMethod
	public void close()
	{
		getDriver().close();
	}
	@DataProvider(name="fetch")
	public String[][] fetchData() throws IOException
	{
		String[][] data=ReadExcelData2.readData(excelFileName);
		return data;
	}
	@AfterSuite
	public void stopReport()
	{
		extent.flush();
	}

}


