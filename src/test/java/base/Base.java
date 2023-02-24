package base;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.*;

public class Base {
	protected static ReadingPropertiesFile readingPropertiesFile=new ReadingPropertiesFile();
	public static ExtentReports extent;
	public static ExtentTest test;
	protected static RequestSpecification httpRequest;
	public static JsonPath jsonResponse;
	public static Logger logger=LogManager.getLogger(Base.class);
	
	@BeforeSuite
	public static void init() {
		Utils.deleteReport();
		extent= ExtentManager.getInstance();		
		//PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/main/resources/log4j2.xml");
	}
	
	@BeforeMethod
	public void startTest(ITestResult result ) {
		
		httpRequest=RestAssured.given();		
		test=extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
	}
	
	public static String getSessionToken() throws Exception {
		String baseUrl=readingPropertiesFile.getProperty("baseurl");
		RequestSpecification httpRequest;
		httpRequest=RestAssured.given();
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Authorization", "Token token=\"c6ecab90b2d6238740faee3226b05fc1\"");
		
		RestClientMethods restClient=new RestClientMethods(baseUrl,httpRequest);
		String body=Utils.generateStringFromJSON("/testData/sessionBody.json");
		
		Response serverResponse=restClient.post(Resources.SessionEndPoint, body);
		serverResponse.prettyPrint();
		jsonResponse=Utils.rawToJason(serverResponse);
		Assert.assertEquals(serverResponse.getStatusCode(), 200,"Status is wrong.");
		return jsonResponse.getString("User-Token");		
		
	}
	
	@AfterMethod
	public void onFinish(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable().getMessage());
		}
		else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable().getMessage());
		}
		else
			test.log(Status.PASS, result.getName()+" execution is passed successfully.");
		log("Finished execution of method " + result.getMethod().getMethodName());
		extent.flush();
	}
	
	public void log(String message) {
		logger.info(message);
		test.log(Status.INFO, message);
	}
	
	
}


