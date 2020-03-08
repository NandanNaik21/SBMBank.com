package com.SBM.qa.Generic;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest implements IAutoConst{

	public WebDriver driver;
	public int iTestCaseRow;
	public String sTestCasename;
	public String sTestInstance;
	public String environment;
	public String Browser;
	public ExtentHtmlReporter reporter;
	public ExtentReports report;
	public ExtentTest test;
	
	static {
	System.setProperty(CHROME_KEY, CHROME_VALUE);
	System.setProperty(GECKO_KEY ,GECKO_VALUE);
	System.setProperty(IE_KEY, IE_VALUE);
	}

	@BeforeTest
	public void setExtent(){
	reporter = new ExtentHtmlReporter(EXTENT_PATh);
	reporter.config().setTheme(Theme.DARK);
	report =new ExtentReports();
	report.attachReporter(reporter);
	}

	@Parameters({"browser","instance","Environment"})
	@BeforeMethod
	public void launchbrowser (String browser, String instance, String Envirorment) throws Exception{
	sTestCasename=CSV_Reader.getTestCaseName(this.toString());
	sTestInstance=instance;
	environment=Envirorment;
	Browser=browser;

	test=report.createTest(sTestCasename);
	if(browser.equalsIgnoreCase("Chrome")){
	driver=new ChromeDriver();
	}
	
	else if(browser.equalsIgnoreCase("Firefox")){
	driver=new FirefoxDriver();
	}
	
	else if (browser.equalsIgnoreCase("IE")){
	driver=new InternetExplorerDriver();
	}
	
	if(environment.equalsIgnoreCase("IN")){
		CSV_Reader.proValue=CSVIN_PATH;
	}
	else if(environment.equalsIgnoreCase("IT")){
		CSV_Reader.proValue=CSVIT_PATH;
	}
	
	iTestCaseRow = CSV_Reader.getRowContains(sTestCasename, instance);
	String appURL = CSV_Reader.csv_value(iTestCaseRow, CSV_Reader.getColumnIndex("url"));
	
	driver.get(appURL);
	driver.manage().window().maximize();
	getPhoto(driver,Browser);
	driver.manage().deleteAllCookies ();
	driver.manage().timeouts().pageLoadTimeout (TestUtility.PagetloadTimeout, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(TestUtility.ImplicitWait, TimeUnit.SECONDS);
	
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		
	if(result.getStatus()==ITestResult. FAILURE){
	test.log (Status. FAIL, "Test case failed is "+ result.getName());
	test.log(Status.FAIL, "Test case failed is "+result.getThrowable().getMessage());
	String screenshot = getPhoto(driver, Browser);
	test.fail("Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshot).build());
	}
	
	else if(result.getStatus()==ITestResult.SKIP){
	test.log(Status.SKIP, "Test Case Skipped is "+ result.getName());
	}
	
	else if(result.getStatus()==ITestResult.SUCCESS) {
	test.log(Status.PASS, "Test Case Passed is "+ result.getName());
	}
	
	report.flush();
	driver.quit();
	}

	public String getPhoto(WebDriver driver, String Browser) {
	String dateTime =new Date().toString().replaceAll(":","_");
	String path = OutputFolder_PATH+environment+"-"+Browser+"/"+sTestCasename +"/"+sTestInstance+"/"+sTestCasename+dateTime+".png";
	try {
		TakesScreenshot t=(TakesScreenshot)driver;
	 File srcFile= t.getScreenshotAs(OutputType.FILE);
	if (Browser.equalsIgnoreCase("Chrome")){
	File destFile = new File(path);
	FileUtils.copyFile(srcFile, destFile);
	}
	else if (Browser.equalsIgnoreCase("IE")){
	File destFile = new File(path);
	FileUtils.copyFile(srcFile, destFile);
	}
	}
	catch(Exception e) {
	e.printStackTrace();
	}
	return path;
	}
}


	
