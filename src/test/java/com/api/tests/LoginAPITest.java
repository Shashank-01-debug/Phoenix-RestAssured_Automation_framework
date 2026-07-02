package com.api.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import  static io.restassured.RestAssured.*;

import  org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;

public class LoginAPITest {
	
	UserCredentials usercreds = new UserCredentials("iamfd","password");
	
	@Test
	public void loginAPITest() {
		
		given().baseUri("http://64.227.160.186:9000/v1").
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
		body("message", Matchers.equalTo("Success")).and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaResponse/loginResponseSchema.json"));
			
	}

}
