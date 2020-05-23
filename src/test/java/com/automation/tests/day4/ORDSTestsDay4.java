package com.automation.tests.day4;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;


import  static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;



public class ORDSTestsDay4 {

    @BeforeAll
    public static void setup(){
        baseURI= ConfigurationReader.getProperty("ORDS.URI");

    }

    @Test
    public void warmUp(){
        Response response=given().accept(ContentType.JSON).

                            when().
                              get("/employees").prettyPeek();


        response.then().assertThat().statusCode(200).and().time(lessThan(3L),TimeUnit.SECONDS);

    }

    /**
     *
     Given accept type is JSON
     And parameters: q = {"country_id":"US"}
     When users sends a GET request to "/countries"
     Then status code is 200
     And Content type is application/json
     And country_name from payload is "United States of America"
     *
     */

//    @DisplayName("Verify country name, content type and status code for country with id US")
//    @Test
//    public void countryID(){
//
//         given().accept(ContentType.JSON).
//                queryParam("q","{\"country_id\":\"US\"}").
//                when().get("/countries").prettyPeek().
//                then().assertThat().statusCode(200).
//                contentType(ContentType.JSON).body("items[0].country_name", is("United States Of America"));
//
//    }

    @DisplayName("Verify country name, content type and status code for country with ID US")
    @Test
    public void verifyCountriesTest1(){
        given().
                accept(ContentType.JSON).
                queryParam("q", "{\"country_id\":\"US\"}").
                when().
                get("/countries").
                then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("items[0].country_name", is("United States of America"));

        ///SECOND REQUEST
        Response response = given().
                accept(ContentType.JSON).
                when().
                get("/countries");


        String countryName = response.jsonPath().getString("items.find{it.country_id=='US'}.country_name");
        Map<String, Object> countryUS = response.jsonPath().get("items.find{it.country_id=='US'}");
        List<String> countryNames = response.jsonPath().getList("items.findAll{it.region_id ==2}.country_name");
        System.out.println("country name = " + countryName);
        System.out.println(countryUS);
        System.out.println(countryNames);
        for (Map.Entry<String, Object>entry: countryUS.entrySet()){

            System.out.printf("=key = %s, value = %s\n",entry.getKey(),entry.getValue());
        }
    }

    @Test
    public void getHighestSalary(){
        Response  response = when().get("/employees");
        Map<String,Object>bestEmployee = response.jsonPath().get("items.max{it.salary}");
        Map<String,Object>poorGuy = response.jsonPath().get("items.min{it.salary}");
        int companiesPayroll = response.jsonPath().get("items.collect{it.salary}.sum()");

        System.out.println(bestEmployee);
        System.out.println(poorGuy);
        System.out.println("Company's Payroll = " + companiesPayroll);


    }

    /*
    * given path parameter is "/employees"
    * when user makes get request
    * then assert that status code is 200
    * then user verifies that every employee has positive salary
    *
    * */
@Test
@DisplayName("Verify that every employee has positive salary")
public void testSalary(){
        when().
                get("/employees").
        then().assertThat().
                            statusCode(200).
                            body("items.salary", everyItem(greaterThan(0))).log().ifError();

}

@Test
    public void verifyPhoneNumber(){
    Response response =when().
                            get("/employees/{id}",101);
//    response.then().assertThat().body("items",is(515-123-4568));
    String expected ="515-123-4568";
    String actual = response.jsonPath().getString("phone_number").replace(".","-");
    assertEquals(200,response.statusCode());
    assertEquals(expected,actual);


}





}
