package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class MasterAPITest {

	@Test
	public void masterAPITest() {
		
		RestAssured.given().spec(SpecUtil.requestSpecWithAuthAndContentTypeEmpty(Roles.FD))	
		//when there is no body in post request we should give contentType as empty.
		.when().post("master")
		.then().
		spec(SpecUtil.responseSpec()).
		body("message",Matchers.equalTo("Success")).
		body("data",Matchers.notNullValue()).
		body("data", Matchers.hasKey("mst_oem")).
		body("data", Matchers.hasKey("mst_problem")).body("$", Matchers.hasKey("data")).
		body("data.mst_oem.id.size()",Matchers.greaterThan(0)).
		body("data.mst_oem.id", Matchers.everyItem(Matchers.notNullValue())).
		body("data.mst_oem.id",Matchers.notNullValue()).
		body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaResponse/MasterAPIResponseSchema-FD.json"));
			
	}
	
	
	@Test
	public void invalidTokenMasterAPI() {
		
		RestAssured.given().
		spec(SpecUtil.requestSpec()).
		when().post("master").
		then().spec(SpecUtil.responseSpec(401));
		
		
	}
	
}
