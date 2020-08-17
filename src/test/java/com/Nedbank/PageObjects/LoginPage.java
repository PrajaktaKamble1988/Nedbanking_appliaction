package com.Nedbank.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	WebDriver ldriver;
	//git repo=https://github.com/PrajaktaKamble1988/Nedbanking_appliaction.git

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}

	/* 1st Inspect the webelements on the Login Page */

	// @FindBy(name="uid")
	@FindBy(how = How.NAME, using = "uid")
	@CacheLookup
	WebElement txtUsername;

	@FindBy(how = How.NAME, using = "password")

	@CacheLookup
	WebElement txtpassword;

	@FindBy(how = How.NAME, using = "btnLogin")
	@CacheLookup
	WebElement btnLogin;

	@FindBy(how = How.NAME, using = "btnReset")
	WebElement btnReset;

	@FindBy(xpath = "//a[@ href='Logout.php']")
	@CacheLookup
	WebElement btn_Logout;

	/* Actions Methods For All the above WEBElementes */
	public void setUserNme(String uname) {
		txtUsername.sendKeys(uname);
	}

	public void setPassword(String pwd) {
		txtpassword.sendKeys(pwd);
	}

	public void clickSubmit() {
		btnLogin.click();

	}

	public void click_Logout() {
		btn_Logout.click();
	}

	public void clickResart() {
		btnReset.click();
		WebDriverWait wait = new WebDriverWait(ldriver, 5);
		wait.until(ExpectedConditions
				.refreshed(ExpectedConditions.visibilityOfElementLocated(By.xpath("anElelmentinthePage"))));

	}
}
