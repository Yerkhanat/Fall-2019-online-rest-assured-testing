package com.automation.tests.day7;
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

public class BasicAuthentication {

    @Test
    public void spartanAuthentication(){
        baseURI=ConfigurationReader.getProperty("SPARTAN.URI");
        given().
                auth().basic("user","user").
        when().
                get("/spartans").prettyPeek().then().statusCode(200);
    }

    @Test
    public void authorizationTest(){

        Spartan spartan = new Spartan("Sheldon", "Male", 2342342342L);
        baseURI=ConfigurationReader.getProperty("SPARTAN.URI");
        given().
                auth().basic("user","user").body(spartan).contentType(ContentType.JSON).
                when().
                post("/spartans").prettyPeek().then().statusCode(403);
    }
    @Test
    public void authenticationTest(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        //if don't provide credentials, we must get 401 status code
        get("/spartans").prettyPeek().then().statusCode(401);
    }

    @Test
    public void authenticationTest2(){
        baseURI = "http://practice.cybertekschool.com";

        given().auth().basic("admin", "admin").
                when().
                    get("/basic_auth").prettyPeek().
                then().
                    statusCode(200).contentType(ContentType.HTML);

    }
}
