/**
 * 
 */
package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * @author sarojchandrabanshi
 *
 */
public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		if(extent==null) {
			ExtentSparkReporter reporter=new ExtentSparkReporter("./reports/API"+Utils.getRandomNumber()+"ExecutionReport.html");
			reporter.config().setReportName("HTML Execution Report");
			extent=new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Task", "NAGP Rest Assured");
			extent.setSystemInfo("Author", "Saroj Chandrabanshi");
			return extent;

		}
		return extent;
	}

}
