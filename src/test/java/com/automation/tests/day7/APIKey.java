package com.automation.tests.day7;

import org.junit.jupiter.api.BeforeAll;
import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;


import  static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class APIKey {

    private final String API_KEY = "27fd7755";

    @BeforeAll
    public static void setup(){
        baseURI = "http://omdbapi.com/";
    }

    @Test
    public void getMovieTest(){
        String itemToSearch = "Frozen";
        Response response = given().
                queryParam("t", itemToSearch).
                queryParam("apikey", API_KEY).
                when().
                get().prettyPeek();

                response.then().
                assertThat().
                statusCode(200).
                body("Title", containsString(itemToSearch));

                List<Map<String,String>> ratings = response.jsonPath().get("Ratings");
        System.out.println(ratings);
    }
    @Test
    public void authenticationTest(){
        String itemToSearch = "Frozen";
        Response response = given().
                queryParam("t", itemToSearch).
                when().
                get().prettyPeek();

        response.then().
                assertThat().
                statusCode(401);


    }
}