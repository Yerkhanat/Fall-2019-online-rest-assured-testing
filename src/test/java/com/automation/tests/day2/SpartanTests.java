package com.automation.tests.day2;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SpartanTests {

    String BASE_URL = "http://3.90.112.152:8000/";

    @Test
    @DisplayName("Get list of all spartans")
    public void getAllSpartans(){
        given().
                auth().basic("admin", "admin").
                baseUri(BASE_URL).
         when().
                get("api/spartans").prettyPeek().
        then().
                statusCode(200);

    }

    @Test
    @DisplayName("add new Spartan")
    public void addNewSpartan(){
        String body = "{\"gender\": \"Female\", \"name\": \"Kimberely\", \"phone\": 9999999999}";
        File jsonFile = new File(System.getProperty("user.dir")+"/spartan.json");

        given().
                contentType(ContentType.JSON).
                auth().basic("admin", "admin").
                body(jsonFile).
                baseUri(BASE_URL).
        when().
                post("/api/spartans").prettyPeek().
        then().
                statusCode(201);


    }
    @Test
    @DisplayName("Delete Some Spartan")
    public void deleteSpartan(){
                given().
                contentType(ContentType.JSON).
                        auth().basic("admin", "admin").
                        baseUri(BASE_URL).
                when().
                        delete("/api/spartans/{id}",721).prettyPeek().
                then().
                        statusCode(204);


    }
}
