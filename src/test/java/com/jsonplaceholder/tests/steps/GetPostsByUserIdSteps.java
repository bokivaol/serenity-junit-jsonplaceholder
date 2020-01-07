package com.jsonplaceholder.tests.steps;

import com.jsonplaceholder.tests.common.CommonMethods;
import com.jsonplaceholder.tests.models.GetPostsRespModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;

/**
 * Created by @Boki on Jan, 2020
 */
public class GetPostsByUserIdSteps {

    private Response response;

    @Step("Call \"/posts\" for particular user")
    public void callPostsByUserId(int userId){
        response = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .when()
                .queryParam("userId", userId)
                .get("/posts")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log()
                .ifValidationFails()
                .extract()
                .response();
    }

    @Step("GSON - Serialize GET /posts response")
    public GetPostsRespModel SerializeGetPostsResponse(){
        GetPostsRespModel postsRespModel = response
                .then()
                .extract()
                .as(GetPostsRespModel.class);
        return postsRespModel;
    }

    @Step("Count all blog post received in response")
    public int countAllPosts(){
        int numberOfPosts = CommonMethods.countJsonObjectsInResponse(response);
        return numberOfPosts;
    }

    public Response getResponse() {
        return response;
    }
}
