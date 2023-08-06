package io.qameta.junit5;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ApiRemoteTest {

    private static final String BASE_URL = "http://51.250.6.164:3000";


    @Test
    public void testDeleteUser() {
        given()
                .header("Content-Type", "application/json")
                .baseUri(BASE_URL)
                .when()
                .delete("/api/users/456")
                .then()
                .statusCode(200);
    }
    @Test
    public void createOrderAndCheckStatusCodePost() {
        RandomOrder randomOrder = new RandomOrder();
        Gson gson = new Gson();
        randomOrder.setId(generatedRandomId());
        randomOrder.setCustomerName(generatedRandomName());
        randomOrder.setCustomerPhone(generatedRandomPhone());
        randomOrder.setComment(generatedRandomComment());

        given()
                .log().all()
                .header("Content-Type", "application/json")
                .body(gson.toJson(randomOrder))
                .when()
                .post(BASE_URL + "/test-orders")
                .then()
                .log().all()
                .statusCode(200);
    }

    private String generatedRandomName() {
        String o = null;
        return o;
    }

    private String generatedRandomComment() {
        String s = null;
        return s;
    }

    private Object generatedRandomPhone() {
        Object o = null;
        return o;
    }

    private int generatedRandomId() {
        return 0;
    }



    private class HttpStatus {
        public static final int SC_OK = 200;
        public static final int SC_UNSUPPORTED_MEDIA_TYPE = 400;
    }

    private static class RandomOrder {
        private int id;
        private String customerName;
        private String customerPhone;
        private String comment;

        public void setId(int i) {

        }

        public void setCustomerName(String s) {

        }

        public void setCustomerPhone(Object o) {

        }

        public void setComment(String s) {

        }
        private Object generatedRandomPhone() {
            Object o = null;
            return o;
        }

        private int generatedRandomId() {
            return 0;
        }

        private String generatedRandomComment() {
            return null;
        }

        private String generatedRandomName() {
            return null;
        }

    }
}
