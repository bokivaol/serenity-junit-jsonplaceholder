package com.jsonplaceholder.tests.steps;

import com.jsonplaceholder.tests.models.PutUpdatePostRequestAndResponseModel;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;

/**
 * Created by @Boki on Jan, 2020
 */
public class PutUpdatePostSteps {
    private Response response;

    @Step("Update particular blog post")
    public void callUpdatePostByPostId(int resourcePostId, PutUpdatePostRequestAndResponseModel bodyPayload){
        response = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .when()
                .body(bodyPayload)
                .put("/posts" + "/" + resourcePostId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log()
                .ifValidationFails()
                .extract()
                .response();
    }

    @Step("Update data under invalid resource Id")
    public void callUpdateNonexistentResource(String resourcePostId, PutUpdatePostRequestAndResponseModel bodyPayload){
        response = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .when()
                .body(bodyPayload)
                .put("/posts" + "/" + resourcePostId)
                .then()
//                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log()
                .ifValidationFails()
                .extract()
                .response();
    }

    @Step("GSON - Serialize PUT /posts response")
    public PutUpdatePostRequestAndResponseModel SerializePutUpdateResponse(){
        PutUpdatePostRequestAndResponseModel putRespModel = response
                .then()
                .extract()
                .as(PutUpdatePostRequestAndResponseModel.class);
        return putRespModel;
    }

    public Response getResponse() {
        return response;
    }
}
