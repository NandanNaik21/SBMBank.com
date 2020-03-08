package com.SBM.qa.Generic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtility extends BaseTest {
	public static long PagetloadTimeout=30;
	public static long ImplicitWait =30;
	public static long Explicitwait =30;
	//Time stamp for extent report generation
	public static String getSystemDate() {
		DateFormat dateFormat=new SimpleDateFormat("_ddMMyyyy_HHmmss");
		Date date= new Date();
		return dateFormat.format(date);
	}
	
	//Explicit wait for Element to be visible on WebPage
	public static void waitforWebElement(WebDriver driver,WebElement element) {
		WebDriverWait wait=new WebDriverWait(driver, Explicitwait);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
