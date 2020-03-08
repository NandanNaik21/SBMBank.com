package com.SBM.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class HomePage {

	@FindBy (id="logoutLink")
	private WebElement logout;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void verifytitle(WebDriver driver,String title) {
		String actualpt = driver.getTitle();
		Assert.assertEquals(actualpt, title);
		System.out.println("You are in Home Page");
	}	
	public void clickonlogout() {
		logout.click();
	}
}
