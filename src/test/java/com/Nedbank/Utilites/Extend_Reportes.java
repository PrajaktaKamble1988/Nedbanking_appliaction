package com.Nedbank.Utilites;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extend_Reportes extends TestListenerAdapter  {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext testcontext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// Show Time Format
		String reportName = "Prajakta-Test-Report-" + timeStamp + ".html";

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/NedBank_ExtentReport/" + reportName);// Where to Save Our Report(ie Location)
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "\\extent-config.xml");// Path of entent.XMl file.

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("PrajaktaK", "LOCALHOST");
		extent.setSystemInfo("Enviornment", "QA_AUTOMATION");
		extent.setSystemInfo("User", "PRAJAKTA");

		htmlReporter.config().setDocumentTitle("NEDBANK_AUTOMATION_TEST");// Title Of Report..
		htmlReporter.config().setReportName("NEDBANK_AUTOMATION_SCRIPT_REPORT");// Name of the Report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);// Location of the chart
		htmlReporter.config().setTheme(Theme.DARK);

	}
	public void onTestSuccess(ITestResult result) {
		logger = extent.createTest(result.getName());// create new Entry in the report.
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName(), ExtentColor.PURPLE));// Send the Passed

		String passScrenshot = System.getProperty("user.dir") + "/Screenshotes_Nedbank/" + result.getName() + ".png"; // Information.
		// logger.log(Status.PASS,result.getThrowable());
		File f = new File(passScrenshot);
		if (f.exists()) {
			try {

				logger.pass("PASSED_TC_SCREENSHOT" + logger.addScreenCaptureFromPath(passScrenshot));

			} catch (Exception e) {
				e.getMessage();

			}

		}

	}
	
	public void onTestfailuer(ITestResult result) {
		logger = extent.createTest(result.getName());
		// logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(),
		// ExtentColor.RED));
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));

		String screenshotPath = System.getProperty("user.dir") + "/Screenshotes_Nedbank/" + result.getName() + ".png";

		File file = new File(screenshotPath);
		if (file.exists()) {
			try {

				logger.fail("FAILED_TC_SCREENSHOT_IS_BELOW" + logger.addScreenCaptureFromPath(screenshotPath));
				logger.log(Status.FAIL, result.getThrowable());
			} catch (Exception e) {
				e.getMessage();
			}
		}

	}
	
	public void onTestSkipped(ITestResult result) {
		logger = extent.createTest(result.getName());
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName(), ExtentColor.YELLOW));

	}

	public void onFinish(ITestContext context) {
		extent.flush();

	}

}

