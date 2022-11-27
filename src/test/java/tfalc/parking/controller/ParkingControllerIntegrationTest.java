package tfalc.parking.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import tfalc.parking.dto.ParkingCreateDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerIntegrationTest {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUp() {
        System.out.println(randomPort);
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {

        RestAssured.given()
                .when()
                .get("/parking/parked")
                .then()
                .body("license[0]", Matchers.equalTo("KOF-9828"));
    }

    @Test
    void createParked() {

        var createDTO = new ParkingCreateDTO();

        createDTO.setColor("Yellow");
        createDTO.setLicense("Placa");
        createDTO.setModel("Etios");
        createDTO.setState("Novo");

        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/createParked")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license", Matchers.equalTo("Placa"))
                .body("color", Matchers.equalTo("Yellow"));
    }
}