package com.apple.asd.tests;

import org.testng.annotations.Test;

import com.apple.asd.restutil.IsApplestoreDownServiceClient;

import  io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

import java.util.Iterator;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AppleStoreStatusTest {
	private ITestContext testContext; 
	
	@BeforeClass
	public void setUp(ITestContext context) {
		testContext = context;
	}

	@Test
	@Parameters
	public void testAppleStoreStatus() {
		String endPoint = testContext.getCurrentXmlTest().getParameter("endpoint");
		IsApplestoreDownServiceClient client = new IsApplestoreDownServiceClient(endPoint);
		Response response = client.GetIsApplestoreDownStatus();
		
		int code = response.getStatusCode();
		Assert.assertEquals(200, code);
		String data = response.asString();
		
		JSONObject object = new JSONObject(data);
		Iterator<String> keyList = object.keys();
		JSONObject value;
		String result="n";
		while (keyList.hasNext()){
		     String key = keyList.next();
		     value = (JSONObject)object.get(key);
		     if(value.get("status").equals("y")) {
		    	 result = "y";
		    	 break;
		     }
		}
		Assert.assertEquals("y", result);
	}
}
