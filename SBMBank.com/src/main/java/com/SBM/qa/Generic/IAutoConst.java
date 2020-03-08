package com.SBM.qa.Generic;

public interface IAutoConst {
	
	String CHROME_KEY="webdriver.chrome.driver";
	String CHROME_VALUE=(System.getProperty("user.dir"))+"/src/test/resources/drivers/chromedriver.exe";
	
	String GECKO_KEY="webdriver.gecko.driver";
	String GECKO_VALUE=(System.getProperty("user.dir"))+"/src/test/resources/drivers/geckodriver.exe";
	
	String IE_KEY="webdriver.ie.driver";
	String IE_VALUE=(System.getProperty("user.dir"))+"/src/test/resources/drivers/IEDriverServer.exe";
	
	String CSVIN_PATH=(System.getProperty("user.dir"))+"/src/test/resources/TestData/IN.csv";
	String CSVIT_PATH=(System.getProperty("user.dir"))+"/src/test/resources/TestData/IT.csv";;
	
	String OutputFolder_PATH=(System.getProperty("user.dir"))+"/TestOutput"+"/"+"Screenshots/";
	
	String EXTENT_PATh=(System.getProperty("user.dir"))+"/TestOutput/ExtentReport/SBMReport"+TestUtility.getSystemDate()+".html";

}
