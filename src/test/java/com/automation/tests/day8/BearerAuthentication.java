package com.automation.tests.day8;
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

public class BearerAuthentication {

    @BeforeAll
    public static void setup(){
        baseURI = "https://cybertek-reservation-api-qa.herokuapp.com/";

    }
    @Test
    public void loginTest(){

       Response response = given().
                queryParam("email","teacherva5@gmail.com").
                queryParam("password","maxpayne").
        when().
                get("/sign");

       String token = response.jsonPath().getString("accessToken");
        System.out.println("Token ::"+token);
    }

    @Test
    @DisplayName("Negative test: attempt to retrieve list of rooms without authentication token")

    public void getRoomsTest(){
        get("/api/rooms").prettyPeek().then().statusCode(422);
    }

    @Test
    public void getRoomTest2(){
        Response response = given().
                queryParam("email","teacherva5@gmail.com").
                queryParam("password","maxpayne").
                when().
                get("/sign");

        response.then().log().ifError();


        String token = response.jsonPath().getString("accessToken");
        System.out.println("Token ::"+token);
        Response response1 = given().auth().
                                    oauth2(token).
                get("/api/rooms").prettyPeek();
    }

    @Test
    public void getAllTeamstest(){

     Response response =   given().
                header("Authorization","Bearer "+getToken()).
        when().
                get("/api/teams").prettyPeek();
     response.then().statusCode(200);

    }

    public String getToken() {
        Response response = given().
                queryParam("email", "teacherva5@gmail.com").
                queryParam("password", "maxpayne").
                when().
                get("/sign");
        response.then().log().ifError();
        String token = response.jsonPath().getString("accessToken");
        System.out.println("Token :: " + token);
        return token;
    }
    public String getToken(String email, String password) {
        Response response = given().
                queryParam("email", email).
                queryParam("password", password).
                when().
                get("/sign");
        response.then().log().ifError();
        String token = response.jsonPath().getString("accessToken");
        System.out.println("Token :: " + token);
        return token;
    }

    }
