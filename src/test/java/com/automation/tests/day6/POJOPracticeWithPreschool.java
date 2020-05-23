package com.automation.tests.day6;
import com.automation.pojos.Employee;
import com.automation.pojos.Link;
import com.automation.pojos.Spartan;
import com.automation.pojos.Student;
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

import java.io.File;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;


import  static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import com.automation.utilities.ConfigurationReader;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class POJOPracticeWithPreschool {
    @BeforeAll
    public static void setup(){

        baseURI =ConfigurationReader.getProperty("PRESCHOOL.URI");

    }
    @Test
    public void addStudentTest() {
        File file = new File("student.json");


        Response response =given().contentType(ContentType.JSON).
                            when().body(file).
                                    post("/student/create").prettyPeek();

        int studentId = response.jsonPath().getInt("studentId");
        System.out.println("studentId = " + studentId);


    }

    @Test
    public void getStudentTest(){
        Response response = get("/student/{id}",11613);
        Student student = response.jsonPath().getObject("students[0]",Student.class);
        System.out.println(student);

    }
}