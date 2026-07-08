package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import static io.restassured.RestAssured.*;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	
	
	@Test(priority=0)
	public void verifyCountAPIResponse() {
		
		given().
		baseUri(ConfigManager.getProperty("BASE_URI")).
		header("Authorization", AuthTokenProvider.getToken(Roles.FD))
        .when().
         get("/dashboard/count")
        .then().
         log()
        .all().statusCode(200)                          //status code validation
        .body("message", Matchers.equalTo("Success")). //body validation
        time(Matchers.lessThan(2000L))                 //time validation
        .body("data", Matchers.notNullValue()).             
        body("data.size()",Matchers.equalTo(3)).
        body("data.count",Matchers.everyItem(Matchers.greaterThanOrEqualTo(0))).//count validation
		body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString()))).
		body("data.key",Matchers.containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today")).
		body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaResponse/CountAPIResponseSchema-FD.json"));  //json Schema validation
	}
	
	@Test(priority=1)
	public void countAPITest_MissingAuthToken() {
		
		given().
		baseUri(ConfigManager.getProperty("BASE_URI")).
		log().headers().
		log().body().
		log().uri().
		when().
		get("/dashboard/count").
		then().
		log().all().
		statusCode(401);	
	}

}
