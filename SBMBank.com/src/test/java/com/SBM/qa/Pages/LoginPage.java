package com.SBM.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(id="username")
	private WebElement username;
	
	@FindBy(xpath="//input[@name='pwd']")
	private WebElement password;
	
	@FindBy(id="loginButton")
	private WebElement logbt;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void setLogin(String un, String pw) {
	username.sendKeys(un);
	password.sendKeys(pw);
	logbt.click();
	
	}
}
