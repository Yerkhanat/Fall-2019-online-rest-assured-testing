package com.automation.tests.day6;
import com.automation.pojos.Employee;
import com.automation.pojos.Link;
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
import com.automation.utilities.ConfigurationReader;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class POJOPracticeWithORDS {
    @BeforeAll
    public static void setup(){
        baseURI= ConfigurationReader.getProperty("ORDS.URI");

    }

    @Test
    public void getEmployeeTest(){
        Response response =get("/employees/{id}",100);

        Employee employee = response.as(Employee.class);
        System.out.println(employee);
        List<Link> link =employee.getLinks();
        System.out.println(link);

    }
}
