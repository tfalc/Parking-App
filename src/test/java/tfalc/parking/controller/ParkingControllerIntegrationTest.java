package tfalc.parking.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

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
    }
}