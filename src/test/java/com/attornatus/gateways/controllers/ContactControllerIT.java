package com.attornatus.gateways.controllers;

import com.attornatus.entities.Contact;
import com.attornatus.gateways.dtos.ContactDto;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactControllerIT {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        RestAssured.port = randomPort;
    }

    @Test
    public void whenGetAllContactsThenOk(){
        RestAssured.when()
                .get("/contacts")
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void whenNewContactThenContactCreated(){
        ContactDto contactDto = new ContactDto();
        LocalDate date = LocalDate.of(2020, 1,1);
        contactDto.setName("Fulano");
        contactDto.setDateOfBirth(date);

        RestAssured.given()
                .body(contactDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/contacts")
                .then().statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void whenDeleteContactThenOk(){
        ContactDto contactDto = new ContactDto();
        LocalDate date = LocalDate.of(2020,1,1);
        contactDto.setName("Fulano");
        contactDto.setDateOfBirth(date);

        String contactId = RestAssured.given()
                .body(contactDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/contacts").getBody().jsonPath().get("id");

        RestAssured.given().delete("/contacts/" + contactId)
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void whenEditContactThenEdited(){
        ContactDto contactDto1 = new ContactDto();
        LocalDate date1 = LocalDate.of(2021,1,1);
        contactDto1.setName("Fulano");
        contactDto1.setDateOfBirth(date1);

        ContactDto contactDto2 = new ContactDto();
        LocalDate date2 = LocalDate.of(2022,2,2);
        contactDto2.setName("Ciclano");
        contactDto2.setDateOfBirth(date2);

        String contactId = RestAssured.given()
                .body(contactDto1)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/contacts").getBody().jsonPath().get("id");

        RequestSpecification request = RestAssured.given()
                .body(contactDto2)
                .contentType(MediaType.APPLICATION_JSON_VALUE);

        Response response = request.put("/contacts/" + contactId);

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        Contact contactResponse = response.getBody().as(Contact.class);

        Assertions.assertEquals(contactId, contactResponse.getId().toString());
        Assertions.assertEquals(contactDto2.getName(), contactResponse.getName());
        Assertions.assertEquals(contactDto2.getDateOfBirth(), contactResponse.getDateOfBirth());
    }
}
