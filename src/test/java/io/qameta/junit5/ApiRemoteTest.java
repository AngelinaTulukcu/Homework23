package io.qameta.junit5;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ApiRemoteTest {
    @BeforeEach
    public  void setup() {
        System.out.println("---> Test start");
        RestAssured.baseURI = "http://51.250.6.164:8080/";
    }

    @Test  //homework11
    public void createOrderWithoutHeaderAndCheckStatusCodePost(){
        RandomOrder randomOrder = new RandomOrder();
        Gson gson = new Gson();
        randomOrder.setCustomerName(generatedRandomName());
        randomOrder.setCustomerPhone(generatedRandomPhone());
        randomOrder.setComment(generatedRandomComment());
        given()
                .when()
                .body(gson.toJson(randomOrder))
                .log()
                .all()
                .post("test-orders")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE);
    }

    private Object generatedRandomComment() {
        return null;
    }

    private Object generatedRandomPhone() {
        Object o = null;
        return o;
    }

    private Object generatedRandomName() {
        Object o = null;
        return o;
    }

    @Test
    public void checkResponseBodyIsOpenGet() {
        String status = given()
                .when()
                .log()
                .all()
                .get("test-orders/8")
                .then()
                .log()
                .all()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .extract()
                .path("status");
        Assertions.assertEquals("OPEN", status);
    }

    private class RandomOrder {
        public void setCustomerName(Object o) {

        }

        public void setCustomerPhone(Object o) {

        }

        public void setComment(Object o) {
        }
    }
}