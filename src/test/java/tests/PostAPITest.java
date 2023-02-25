package tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import client.RestClient;
import data.Users;
import restAssuredTests.TestBase;

public class PostAPITest extends TestBase{

	TestBase testBase;
	String serviceUrl, apiUrl, url, email1, fName1, lName1, avatar1;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		email1 = prop.getProperty("email1");
		fName1 = prop.getProperty("fName1");
		lName1 = prop.getProperty("lName1");
		avatar1 = prop.getProperty("avatar1");
		url = serviceUrl+apiUrl;
		
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//jackson API:
		ObjectMapper mapper = new ObjectMapper();
		Users user = new Users("Thamilalagan", "Test Engineer"); // expected users object
		
		//convert java object to json file
		mapper.writeValue(new File("C:\\Users\\Thamilalagan.Annadur\\eclipse-workspace\\RestAssuredAPI_BDD\\src\\test\\java\\data\\users.json"), user);
		
		//convert object to json string
		String usersJsonString = mapper.writeValueAsString(user);
		System.out.println(usersJsonString);
		
		closeableHttpResponse = restClient.post(url, usersJsonString, headerMap);
		
		//to get the status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testBase.ResponseStatusCode_201);
		
		//json string
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response json is "+responseJson );
		
		//convert json to java object
		Users userResObj = mapper.readValue(responseString, Users.class); //actual users object
		System.out.println(userResObj);
	}


}
