package utilities;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClientMethods {
	
	public String resource;
	public String baseUrl;
	
	private RequestSpecification request;
	private Response restResponse;
	
	public RestClientMethods(String baseUrl,RequestSpecification request) {
		this.request=request;
		this.request.baseUri(baseUrl);
	}
	
//------------------Rest API Get Method-----------------------------
	public Response get(String resource)throws Exception {
		restResponse=request.when().get(resource);
		return restResponse;
	}
	
//------------------Rest API Post Method-----------------------------
	public Response post(String resource, String bodyString)throws Exception {
		restResponse=request.when().body(bodyString).post(resource);
		return restResponse;
	}
	
//------------------Rest API Update Method-----------------------------
	public Response put(String resource, String bodyString)throws Exception {
		restResponse=request.when().body(bodyString).put(resource);
		return restResponse;
	}
	
//------------------Rest API Delete Method-----------------------------
	public Response delete(String resource)throws Exception {
		restResponse=request.when().delete(resource);
		return restResponse;
	}
}
