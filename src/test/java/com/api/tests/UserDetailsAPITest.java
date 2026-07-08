package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.utils.AuthTokenProvider.*;

import com.api.constants.Roles;
import com.api.utils.ConfigManager;
import static com.api.utils.ConfigManager.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;
import java.io.IOException;


public class UserDetailsAPITest  {
	
	Header authHeader = new Header("Authorization",getToken(Roles.ENG));
	
	@Test
	public void userDetailsAPITest() {
		
		given().
		baseUri(ConfigManager.getProperty("BASE_URI")).
		header(authHeader).
		accept(ContentType.JSON)
		.when().
		get("userdetails").
		then().
		log().all().
		statusCode(200).
		time(Matchers.lessThan(3000L));
		
		
			
	}

}
