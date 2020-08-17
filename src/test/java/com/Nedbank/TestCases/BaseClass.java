package com.Nedbank.TestCases;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.commons.io.FileUtils;

import com.Nedbank.PageObjects.LoginPage;
import com.Nedbank.Utilites.XLUtils;
import com.aventstack.extentreports.utils.FileUtil;
//import com.sun.jna.platform.FileUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public String App_URL = "http://demo.guru99.com/v4/";
	public String UserID = "mngr277704";
	public String App_Password = "ApEzEme";

	public static WebDriver driver;
	public static Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {

		logger = Logger.getLogger("NedBanking");
		PropertyConfigurator.configure("log4j.properties");

		if (br.equals("chrome")) {
			// System.getProperty("webdriver.chrome.driver", ("user.dir")
			// +"\\Drivers_Nedbank\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		else if (br.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (br.equals("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

		}
		driver.get(App_URL);
	}

	@AfterClass
	public void endtest() {
		driver.quit();

	}

	public void captureScreenshot(WebDriver driver, String tname) throws Exception {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshotes_Nedbank/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot is Taken");

	}

/*	public void writeResultXLSheet() throws Exception {

		LoginPage lp = new LoginPage(driver);

		String XLSheet_Path = System.getProperty("user.dir") + "\\NedBank_XLSheets\\NedBank_LginXL_File.xlsx";

		for (int i = 0; i <= XLUtils.getRow_Count(XLSheet_Path, "Sheet1"); i++) {
			for (int j = 0; j < XLUtils.getColumn_Count(XLSheet_Path, "Sheet1", 1); j++) {

				if (isAlertPresent() == true) {
					driver.switchTo().alert().accept(); // Need to close that Alert
					driver.switchTo().defaultContent(); // Then Goto defalt Login Page for new login testdata.
					XLUtils.setCellData(XLSheet_Path, "Sheet1", j, i, "pass");

					
					 * XLUtils.ws.createRow(i); XLUtils.row.createCell(j);
					 * XLUtils.cell.setCellValue("PassTestcase"); XLUtils.wb.write(XLUtils.fo);
					 * //Main Line driver.switchTo().alert().accept();
					 * driver.switchTo().defaultContent();
					 // XLUtils.fo.close();
					System.out.println("PassedTestcase");

				} else {
					Assert.assertTrue(true);
					lp.click_Logout();
					driver.switchTo().alert().accept();// Logout Alert
					driver.switchTo().defaultContent();
					XLUtils.setCellData(XLSheet_Path, "Sheet1", j, i, "faild");
					System.out.println("FailedTestcse");

				}

				i++;
				j++;
				// XLUtils.fo.close();
			}

		}

	}*/

	
}
