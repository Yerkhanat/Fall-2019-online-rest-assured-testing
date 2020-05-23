package com.automation.tests.day6;

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

public class POJOPracticeWithSpartanApp {
    @BeforeAll
    public static void beforeAll(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        authentication =basic("admin","admin");

    }

    @Test
    public void addSpartanTest(){

        Map<String, String> spartan = new HashMap<>();
        spartan.put("gender", "Male");
        spartan.put("name", "Jim Halpert");
        spartan.put("phone", "123112312312");
        RequestSpecification requestSpecification =given().
                                                         auth().basic("admin", "admin").
                                                          contentType(ContentType.JSON).
                                                           body(spartan);
        Response response =given().
                                    auth().basic("admin", "admin").
                                    contentType(ContentType.JSON).
                                    body(spartan).
                           when().
                                    post("/spartans").prettyPeek();
        response.then().statusCode(201);
        response.then().body("success", is("A Spartan is Born!"));

        Spartan spartanResponse = response.jsonPath().getObject("data", Spartan.class);
        System.out.println();
        System.out.println(spartanResponse instanceof Spartan);

    }

    @Test
    public void updateSpartanTest(){
        int userToUpdate =191;
        Spartan spartan = new Spartan("Dwight", "Male", 1234567890L);
        Response response =given().
                auth().basic("admin", "admin").
                contentType(ContentType.JSON).
                body(spartan).
                when().
                put("/spartans/{id}",userToUpdate).prettyPeek();
//        response.then().statusCode(200);
        given().
                auth().basic("admin", "admin").
        when().get("/spartans/{id}",userToUpdate).prettyPeek().then().statusCode(200).body("name",is("Dwight"));

    }
    @Test
    @DisplayName("Verify that user can perform PATCH request")
    public void patchUserTest1(){
        //patch - partial update of existing record
        Response response0 = given().accept(ContentType.JSON).when().get("/spartans");
        List<Spartan>allSpartans = response0.jsonPath().getList("",Spartan.class);
       // System.out.println(allSpartans);
        Random random =new Random();

        int randomNum =random.nextInt(allSpartans.size());
        int randomUserId =allSpartans.get(randomNum).getId();
        System.out.println("Name Before"+allSpartans.get(randomNum).getName());

        int userId = 197;
        Map<String, String> update = new HashMap<>();
        update.put("name", "Pam Halpert");

        Response response = given().
                                    contentType(ContentType.JSON).
                                    body(update).
                                    when().
                                         patch("/spartans/{id}",randomUserId);
        response.then().assertThat().statusCode(204);
        given().accept(ContentType.JSON).when().get("spartans/{id}",randomUserId).prettyPeek().then().
                assertThat().statusCode(200).
                body("name", is("Pam Halpert"));



    }


}
