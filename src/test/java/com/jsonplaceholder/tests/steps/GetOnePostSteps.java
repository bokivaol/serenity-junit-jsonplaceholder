package com.jsonplaceholder.tests.steps;

import com.jsonplaceholder.tests.models.PutUpdatePostRequestAndResponseModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;

/**
 * Created by @Boki on Jan, 2020
 */
public class GetOnePostSteps {

    private Response response;

    @Step("Get particular blog post")
    public void getPostByPostId(int resourcePostId){
        response = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/posts" + "/" + resourcePostId)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .log()
                .ifValidationFails()
                .extract()
                .response();
    }

    public Response getResponse() {
        return response;
    }
}
