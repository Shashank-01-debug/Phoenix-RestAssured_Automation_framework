package com.api.utils;

import javax.management.relation.Role;

import org.hamcrest.Matchers;

import com.api.constants.Roles;
import com.api.pojo.UserCredentials;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtil {

	
	public static RequestSpecification requestSpec() {
		
RequestSpecification requestSpecification = new RequestSpecBuilder().
setBaseUri(ConfigManager.getProperty("BASE_URI")).
 setContentType(ContentType.JSON).
 setAccept(ContentType.JSON).
 log(LogDetail.BODY).
 log(LogDetail.URI).
 log(LogDetail.HEADERS).
 log(LogDetail.METHOD).build();
 
 return requestSpecification;
		
	}
	
	//method for post/pull request
	
	public static RequestSpecification requestSpec(Object payload) {
		
		RequestSpecification requestSpecification = new RequestSpecBuilder().
		setBaseUri(ConfigManager.getProperty("BASE_URI")).
		 setContentType(ContentType.JSON).
		 setAccept(ContentType.JSON).
		 setBody(payload). 
		 log(LogDetail.BODY).
		 log(LogDetail.URI).
		 log(LogDetail.HEADERS).
		 log(LogDetail.METHOD).build();
		 
		 return requestSpecification;
				
			}
	
public static RequestSpecification requestSpecWithAuth(Roles role) {
		
		RequestSpecification requestSpecification = new RequestSpecBuilder().
		setBaseUri(ConfigManager.getProperty("BASE_URI")).
		addHeader("Authorization", AuthTokenProvider.getToken(role)).
		 setContentType(ContentType.JSON).
		 setAccept(ContentType.JSON).
		 log(LogDetail.BODY).
		 log(LogDetail.URI).
		 log(LogDetail.HEADERS).
		 log(LogDetail.METHOD).build();
		 
		 return requestSpecification;
				
			}


public static RequestSpecification requestSpecWithAuthAndContentTypeEmpty(Roles role) {
	
	RequestSpecification requestSpecification = new RequestSpecBuilder().
	setBaseUri(ConfigManager.getProperty("BASE_URI")).
	addHeader("Authorization", AuthTokenProvider.getToken(role)).
	 setContentType("").
	 setAccept(ContentType.JSON).
	 log(LogDetail.BODY).
	 log(LogDetail.URI).
	 log(LogDetail.HEADERS).
	 log(LogDetail.METHOD).build();
	 
	 return requestSpecification;
			
		}
	
	
	
public static ResponseSpecification responseSpec() {
	
	ResponseSpecification responseSpecification = new ResponseSpecBuilder().
			expectContentType(ContentType.JSON).
			expectStatusCode(200).
	expectResponseTime(Matchers.lessThan(2000L)).
	log(LogDetail.ALL).build();
	
	return responseSpecification;
				
			}


public static ResponseSpecification responseSpec(int statusCode) {
	
	ResponseSpecification responseSpecification = new ResponseSpecBuilder().
			expectStatusCode(401).
	expectResponseTime(Matchers.lessThan(2000L)).
	log(LogDetail.ALL).build();
	
	return responseSpecification;
		
				
			}
	
	
	
}
