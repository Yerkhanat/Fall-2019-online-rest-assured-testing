package com.automation.tests.day3;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;


import  static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class ORDSTestsDay3{

    @BeforeAll
    public static void setup(){
        baseURI = "http://54.224.118.38:1000/ords/hr";

    }
    /*
    *given resource path is "/regions/{id}"
    *
    */
    @Test
    public void verifyFirstRegion(){
        given().pathParam("id",1).
                when().
                    get("/regions/{id}").prettyPeek().then().assertThat().statusCode(200).and().
                body("region_name",is("Europe")).
                body("region_id", is(1)).
                time(lessThan(5L), TimeUnit.SECONDS);
    }

    @Test
    public void verifyEmployeeInfo(){
        Response response = given().
                                accept(ContentType.JSON).
                            when().
                                get("/employees");

        JsonPath jsonPath = response.jsonPath();
        String nameOfFirstEmployee = jsonPath.getString("items[0].first_name");
        System.out.println("First name of 1st employee: "+nameOfFirstEmployee);
        String nameOfLastEmployee =jsonPath.getString("items[-1].first_name");
        System.out.println("First name of the last employee: "+nameOfLastEmployee);
        
//        Map<String, Object> firstEmployee = jsonPath.get("items[0]");
//        System.out.println(firstEmployee);



    }




}

