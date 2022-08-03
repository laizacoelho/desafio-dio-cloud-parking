package one.digitalinnovation.parking.controllers;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.dtos.ParkingRequestDto;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerIT {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest(){
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .when()
                .get("/parkings")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void whenSaveThenCheckResult() {
        var createDto = new ParkingRequestDto();
        createDto.setLicense("CFI5876");
        createDto.setModel("Etios");
        createDto.setState("RJ");
        createDto.setColor("preto");


        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDto)
                .post("/parkings")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("CFI5876"));
    }
}