package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {

	@Test(priority=0)
	public void masterAPITest() {
		
		RestAssured.given().
		baseUri(ConfigManager.getProperty("BASE_URI")).
		header("Authorization",AuthTokenProvider.getToken(Roles.FD)).
		contentType("")//when there is no body in post request we should give contentType as empty.
		.log().all()
		.when().post("master")
		.then().log().all().statusCode(200).time(Matchers.lessThan(2000L)).
		body("message",Matchers.equalTo("Success")).
		body("data",Matchers.notNullValue()).
		body("data", Matchers.hasKey("mst_oem")).
		body("data", Matchers.hasKey("mst_problem")).body("$", Matchers.hasKey("data")).
		body("data.mst_oem.id.size()",Matchers.greaterThan(0)).
		body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue())).
		body("data.mst_oem.id",Matchers.notNullValue()).
		body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaResponse/MasterAPIResponseSchema-FD.json"));
			
	}
	
	
	@Test(priority=1)
	public void invalidTokenMasterAPI() {
		
		RestAssured.given().baseUri(ConfigManager.getProperty("BASE_URI")).log().all().
		header("Authorization","").contentType("").
		when().post("master").
		then().log().all().statusCode(401);
		
	}
	
}
