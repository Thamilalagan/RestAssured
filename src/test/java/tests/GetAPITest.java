package tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import client.RestClient;
import restAssuredTests.TestBase;
import utility.TestUtil;

public class GetAPITest extends TestBase{
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
	
	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		
		//get status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code "+statusCode);
		
		Assert.assertEquals(statusCode,ResponseStatusCode_200, "Status code is not 200");

		//Json string
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API "+responseJson);
		
		//to validate the value of per_page
		String perPageValue = TestUtil.getValueByJpath(responseJson, "/per_page");
		System.out.println("Value of per_page is "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		//to validate the value of total
		String total = TestUtil.getValueByJpath(responseJson, "/total");
		System.out.println("Value of total is "+total);
		Assert.assertEquals(Integer.parseInt(total), 12);

		//get the value from array
		String id = TestUtil.getValueByJpath(responseJson, "/data[0]/id");
		String email = TestUtil.getValueByJpath(responseJson, "/data[0]/email");
		String fName = TestUtil.getValueByJpath(responseJson, "/data[0]/first_name");
		String lName = TestUtil.getValueByJpath(responseJson, "/data[0]/last_name");
		String avatar = TestUtil.getValueByJpath(responseJson, "/data[0]/avatar");
		
		System.out.println("The data in the array is "+id+" "+email+" "+fName+" "+lName+" "+avatar+" ");
		Assert.assertEquals(Integer.parseInt(id), 1);
		Assert.assertEquals(email, email1);
		Assert.assertEquals(fName, fName1);
		Assert.assertEquals(lName, lName1);
		Assert.assertEquals(avatar, avatar1);
		
		
		//All headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header: headersArray)
		{
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array "+allHeaders);

		
	}
	
	@Test(priority=2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-type", "application/json");
		
		closeableHttpResponse = restClient.get(url);
		
		//get status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code "+statusCode);
		
		Assert.assertEquals(statusCode,ResponseStatusCode_200, "Status code is not 200");

		//Json string
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API "+responseJson);
		
		//to validate the value of per_page
		String perPageValue = TestUtil.getValueByJpath(responseJson, "/per_page");
		System.out.println("Value of per_page is "+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		//to validate the value of total
		String total = TestUtil.getValueByJpath(responseJson, "/total");
		System.out.println("Value of total is "+total);
		Assert.assertEquals(Integer.parseInt(total), 12);

		//get the value from array
		String id = TestUtil.getValueByJpath(responseJson, "/data[0]/id");
		String email = TestUtil.getValueByJpath(responseJson, "/data[0]/email");
		String fName = TestUtil.getValueByJpath(responseJson, "/data[0]/first_name");
		String lName = TestUtil.getValueByJpath(responseJson, "/data[0]/last_name");
		String avatar = TestUtil.getValueByJpath(responseJson, "/data[0]/avatar");
		
		System.out.println("The data in the array is "+id+" "+email+" "+fName+" "+lName+" "+avatar+" ");
		Assert.assertEquals(Integer.parseInt(id), 1);
		Assert.assertEquals(email, email1);
		Assert.assertEquals(fName, fName1);
		Assert.assertEquals(lName, lName1);
		Assert.assertEquals(avatar, avatar1);
		
		
		//All headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header header: headersArray)
		{
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array "+allHeaders);

		
	}


}
