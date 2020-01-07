package com.jsonplaceholder.tests.common;

import io.restassured.response.Response;

import java.util.List;

/**
 * Created by @Boki on Jan, 2020
 */
public class CommonMethods {

    public static  int countJsonObjectsInResponse(Response response){
        List<String> jresponse = response.jsonPath().getList("id");
        int numberOfPosts = jresponse.size();
        return numberOfPosts;
    }
}
