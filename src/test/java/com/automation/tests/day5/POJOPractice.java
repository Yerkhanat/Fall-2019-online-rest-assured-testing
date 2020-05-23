package com.automation.tests.day5;

import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
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

import org.junit.jupiter.api.BeforeAll;

public class POJOPractice {

    @BeforeAll
    public static void beforeAll(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");

    }
    @Test
    public void getUser(){
        Response response = given().
                                auth().
                                basic("admin", "admin").
                        when().
                                get("/spartans/{id}",13).prettyPeek();

        Spartan spartan = response.as(Spartan.class);
        System.out.println(spartan);

        Map<String, ?> spartansAsMap =response.as(Map.class);
        System.out.println(spartansAsMap);

        assertEquals(13,spartan.getId());
        assertEquals("Jaimie", spartan.getName());
        assertEquals("Female",spartan.getGender());

    }

    @Test
    public void addUser(){

        Spartan spartan = new Spartan("Kimberely", "Female", 1234567891L);
        Gson gson = new Gson();
        String pojoAsJSON = gson.toJson(spartan);
        System.out.println(pojoAsJSON);

        Response response = given().auth().basic("admin", "admin").contentType(ContentType.JSON).
                body(spartan).when().
                post("/spartans").prettyPeek();

        int userId = response.jsonPath().getInt("data.id");
        System.out.println(userId);

        given().auth().basic("admin","admin").when().delete("spartans/{id}",userId).prettyPeek().
            then().assertThat().statusCode(204);


    }



}
