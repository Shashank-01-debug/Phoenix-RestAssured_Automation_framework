package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.utils.AuthTokenProvider.*;

import com.api.constants.Roles;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import static com.api.utils.ConfigManager.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;
import static io.restassured.RestAssured.*;
import java.io.IOException;


public class UserDetailsAPITest  {
	
	
	
	@Test
	public void userDetailsAPITest() {
		
		given().spec(SpecUtil.requestSpecWithAuth(Roles.FD))
		.when().
		get("userdetails").
		then().
		spec(SpecUtil.responseSpec());
		
		
		
			
	}

}
