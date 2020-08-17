package com.Nedbank.TestCases;

import java.awt.Label;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Nedbank.PageObjects.LoginPage;
import com.Nedbank.Utilites.XLUtils;

public class TC_Lgin_DDT_002 extends BaseClass {
	/*
	 * 1.Read the data form XLFile 2.Store that data into 2Diamentional Array(using
	 * DataProvider) 3.Then return to the Actual Test Method(loginDDt()) 4.In Case
	 * Of Invalid TestData it shows POPUPS
	 */

	@Test(dataProvider = "LoginData")
	public void loginDDT(String user, String pass) throws Exception {
		String XLSheet_Path = System.getProperty("user.dir") + "\\NedBank_XLSheets\\NedBank_LginXL_File.xlsx";

		LoginPage lp = new LoginPage(driver);
		
		lp.setUserNme(user);
		logger.info("userid is provided");
		lp.setPassword(pass);
		lp.clickSubmit();
		Thread.sleep(2000);

		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept(); // Need to close that Alert
			driver.switchTo().defaultContent(); // Then Goto defalt Login Page for new login testdata.
			logger.warn("login is failed..(:");

		} else {

			Assert.assertTrue(true);
			lp.click_Logout();
			driver.switchTo().alert().accept();// Logout Alert
			driver.switchTo().defaultContent();
			logger.info("login passed..");

		}

	}

	// Create User_Define() For cheking Alert Is present or not
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();

			return true;

		} catch (NoAlertPresentException e) {

			return false;
		}

	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws Exception {

		String XLSheet_Path = System.getProperty("user.dir") + "\\NedBank_XLSheets\\NedBank_LginXL_File.xlsx";
		int noOFRows = XLUtils.getRow_Count(XLSheet_Path, "Sheet1");
		int noOfColumn = XLUtils.getColumn_Count(XLSheet_Path, "Sheet1", 1);

		String logindata[][] = new String[noOFRows][noOfColumn];

		for (int i = 1; i <= noOFRows; i++) {

			for (int j = 0; j < noOfColumn; j++) {

				logindata[i - 1][j] = XLUtils.getCoumn_Data(XLSheet_Path, "Sheet1", i, j);
				
			}

		}
		return logindata;

	}

}
