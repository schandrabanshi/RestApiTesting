package testcases;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;


import base.Base;
import utilities.RestClientMethods;

public class Users extends Base{
	
	private static RestClientMethods restClient;
	private static String baseUrl;
	
	String bodyRequest="";
	String sessionToken="";
	
	@BeforeClass
	public void setupTest() {
		baseUrl=readingPropertiesFile.getProperty("baseurl");
	}
	
	@BeforeMethod
	public void setupRequest() {
		httpRequest.header("Content-Type", "application/json");
		httpRequest.header("Authorization", "Token token=\"c6ecab90b2d6238740faee3226b05fc1\"");
		restClient=new RestClientMethods(baseUrl,httpRequest);
	}
	
	@Test
	public void createUser() throws Exception {
		getSessionToken();
	}
	
	
	
}
