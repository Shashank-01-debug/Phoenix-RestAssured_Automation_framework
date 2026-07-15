package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import  org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.pojo.UserCredentials;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginAPITest  {
	
	UserCredentials usercreds = new UserCredentials("iamfd","password");
	
	@Test
	public void loginAPITest() {
		
		given().spec(SpecUtil.requestSpec(usercreds)).
		when().
		post("login").
		then().spec(SpecUtil.responseSpec()).
		body("message", Matchers.equalTo("Success")).
		and()
	    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath
	    		("jsonSchemaResponse/loginResponseSchema.json"));
			
	}

}
