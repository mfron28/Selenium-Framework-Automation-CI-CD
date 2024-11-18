package Academy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {

	public static ExtentReports getReportObject() {
		
		ExtentReports extent = new ExtentReports();
		String path = System.getProperty("user.dir") + "\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation reports");
		reporter.config().setDocumentTitle("Test Results");
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Madalina");
		
		return extent;
	}
}
