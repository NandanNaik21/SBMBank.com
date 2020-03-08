package com.SBM.qa.TestCases;

import org.testng.annotations.Test;

import com.SBM.qa.Generic.BaseTest;
import com.SBM.qa.Generic.CSV_Reader;
import com.SBM.qa.Pages.HomePage;
import com.SBM.qa.Pages.LoginPage;
import com.aventstack.extentreports.Status;

public class ValidLogin extends BaseTest{
	@Test
	public void testValidLogin() {
	try {
		String un=CSV_Reader.csv_value(iTestCaseRow, CSV_Reader.getColumnIndex("username"));
		String pw=CSV_Reader.csv_value(iTestCaseRow, CSV_Reader.getColumnIndex("password"));
		String pt=CSV_Reader.csv_value(iTestCaseRow, CSV_Reader.getColumnIndex("Pagetitle"));
		test.log(Status.INFO, "Username Entered");
		test.log(Status.INFO, "Password Entered");
		LoginPage l=new LoginPage(driver);
		l.setLogin(un, pw);
		test.log(Status.PASS, "Username and password Entered");
		getPhoto(driver, Browser);	
		HomePage H=new HomePage(driver);
		H.verifytitle(driver,pt);
		getPhoto(driver, Browser);	
		test.log(Status.PASS, "We are in Home page");
		H.clickonlogout();
		getPhoto(driver, Browser);	
		test.log(Status.PASS, "Clicked on log out button");
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	}

}
