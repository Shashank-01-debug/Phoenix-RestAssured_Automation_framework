package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import  org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest  {
	
	UserCredentials usercreds = new UserCredentials("iamfd","password");
	
	@Test
	public void loginAPITest() {
		
		given().baseUri(ConfigManager.getProperty("BASE_URI")).
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(usercreds).
		log().body().
		log().headers().
		log().uri().
		log().method().
		when().
		post("login").
		then().
		log().all().
		statusCode(200).
		time(Matchers.lessThan(1000L)).
		body("message", Matchers.equalTo("Success")).
		and()
	    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaResponse/loginResponseSchema.json"));
			
	}

}
