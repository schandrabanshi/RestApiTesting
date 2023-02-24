package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Utils {
	
	
//------------------Generate String from JSON test data file--------------
	public static String generateStringFromJSON(String relativePath) throws IOException {
		relativePath=System.getProperty("user.dir")+"/src/test/java"+relativePath;
		return new String(Files.readAllBytes(Paths.get(relativePath)));
	}
	
//------------------Convert Raw to JSON------------------------------------
	public static JsonPath rawToJason(Response response) {
		return new JsonPath(response.asString());
	}
//------------------Generate randon number---------------------------------
	public static long getRandomNumber() {
		Random rand=new Random();
		long randomNumber=rand.nextInt(10000);
		return randomNumber;
	}
//------------------Delete exiting HTML execution report--------------------
	public static void deleteReport() {
		File reportPath = new File("./reports");		
		try {
		    FileUtils.cleanDirectory(reportPath);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
