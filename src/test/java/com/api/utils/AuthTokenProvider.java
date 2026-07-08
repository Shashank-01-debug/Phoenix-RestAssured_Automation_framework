package com.api.utils;

import com.api.constants.Roles;
import com.api.pojo.UserCredentials;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class AuthTokenProvider {
	
	private AuthTokenProvider() {
		
	}
	

	public static String getToken(Roles role) {

		// want to make the request and want to extract the token

		UserCredentials userCreds = null ;
		
	if	(role==Roles.FD) {
		userCreds = new UserCredentials("iamfd","password");
	}
	else if(role==Roles.SUP) {
		userCreds = new UserCredentials("iamsup","password");
	}
	else if(role==Roles.ENG) {
		userCreds = new UserCredentials("iameng","password");
		
	}
	else if(role==Roles.QC)
		{
			userCreds= new UserCredentials("iamqc","password");
			
		}
	
		String token = given().
				baseUri(ConfigManager.getProperty("BASE_URI")).
				contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(userCreds)
				.when().
				post("login").
				then().statusCode(200).
				extract().
				body().
				jsonPath()
			   .getString("data.token");

		return token;

	}

}
