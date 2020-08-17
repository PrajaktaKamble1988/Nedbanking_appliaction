package com.Nedbank.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Nedbank.PageObjects.LoginPage;

public class TC_LoginPage_001 extends BaseClass {

	@Test
	public void loginPage_Test() throws Exception {
		
		logger.info("URL is opned");

		LoginPage LP = new LoginPage(driver);
		LP.setUserNme(UserID);
		logger.info("Enterd username");

		LP.setPassword(App_Password);
		logger.info("Enterd password");
		LP.clickSubmit();
		Thread.sleep(2000);

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("login test pass");
			captureScreenshot(driver, "logintest");

		} else {
			captureScreenshot(driver, "logintest");

			Assert.assertTrue(false);
			logger.warn("login test failed");
			
		}

	}

}
