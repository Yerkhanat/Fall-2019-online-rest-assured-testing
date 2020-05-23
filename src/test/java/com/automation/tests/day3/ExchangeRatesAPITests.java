package com.automation.tests.day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.*;


import  static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ExchangeRatesAPITests {

    @BeforeAll
    public static void setup(){

        baseURI ="http://api.openrates.io/";


    }

    @Test
    public void getLatestRates(){
    Response response =given().
                            queryParam("base","USD").
                        when().
                            get("/latest").prettyPeek();
            Headers headers = response.getHeaders();

            String contentType = headers.getValue("Content-Type");
        System.out.println(contentType);
            response.then().assertThat().statusCode(200);
            response.then().assertThat().body("base",is("USD"));
            String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            response.then().assertThat().body("date", containsString("2020-05-19"));

    }

    //get history of rates for 2008

    @Test
    public void getHistoryOfRates(){
        Response response =given().queryParam("base","USD").when().get("/2008-01-02").prettyPeek();
        Headers headers =response.getHeaders();
        response.then().assertThat().statusCode(200).
                                    and()
                                    .body("date", is("2008-01-02")).and().
                body("rates.USD", is(1.0f));

        Float parameter =response.jsonPath().get("rates.EUR");
        System.out.println("parameter = " + parameter);

    }
}
