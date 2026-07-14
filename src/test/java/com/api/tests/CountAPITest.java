package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import static io.restassured.RestAssured.*;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {
	
	
	@Test(priority=0)
	public void verifyCountAPIResponse() {
		
		given().spec(SpecUtil.requestSpecWithAuth(Roles.FD))
        .when().
         get("/dashboard/count")
        .then().spec(SpecUtil.responseSpec())                            
        .body("message", Matchers.equalTo("Success")). //body validation       
        body("data", Matchers.notNullValue()).             
        body("data.size()",Matchers.equalTo(3)).
        body("data.count",Matchers.everyItem(Matchers.greaterThanOrEqualTo(0))).//count validation
		body("data.label",Matchers.everyItem(Matchers.not(Matchers.blankOrNullString()))).
		body("data.key",Matchers.containsInAnyOrder("pending_for_delivery","pending_fst_assignment","created_today")).
		body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaResponse/CountAPIResponseSchema-FD.json"));  //json Schema validation
	}
	
	@Test(priority=1)
	public void countAPITest_MissingAuthToken() {
		
		given().
		spec(SpecUtil.requestSpec()).
		when().
		get("/dashboard/count").
		then().
		spec(SpecUtil.responseSpec(401));
	}

}
