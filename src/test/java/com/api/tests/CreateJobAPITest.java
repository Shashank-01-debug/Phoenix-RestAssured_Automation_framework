package com.api.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constants.Roles;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.Customer_Address;
import com.api.pojo.Customer_product;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobAPITest {
	
	
	
	
	@Test
	public void createJobAPITest() {
		//creating the CreateJobPayload Obect
		Problems problems = new Problems(1, "Battery Issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		
		
		
		Customer_product customer_Product = new Customer_product("2025-04-06T18:30:00.000Z", "75897387233126","75897387233126" , "75897387233126", "2025-04-06T18:30:00.000Z", 1, 1);
		Customer_Address customer_address = new Customer_Address("32 UPSC House", "H 1705", "Delhi", "", "Delhi", "411057", "INDIA", "Delhi");
		Customer customer = new Customer("Ron", "Messi", "9900220000", "", "bond@7mail.com", "");
		CreateJobPayload createJobPayLoad = new CreateJobPayload(0, 2, 1, 1, customer, customer_address, customer_Product, problemList);
		
		
	RestAssured.given().
	spec(SpecUtil.requestSpecWithAuth(Roles.FD, createJobPayLoad))
		.log().all()
		.when().
		post("/job/create").
		then().
		spec(SpecUtil.responseSpec());
		
		
		
		
		
	}

}
