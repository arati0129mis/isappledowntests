package com.apple.asd.restutil;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import junit.framework.Assert;

public class IsApplestoreDownServiceClient {
	private String endPoint;
	
	public IsApplestoreDownServiceClient(String endPoint) {
		this.endPoint = endPoint;
	}

	public Response GetIsApplestoreDownStatus() {
		Response resp	= RestAssured.get(endPoint);
		return resp;
	}
}
