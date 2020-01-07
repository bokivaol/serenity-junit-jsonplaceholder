package com.jsonplaceholder.tests.apiTests;

import com.jsonplaceholder.tests.BaseApiTest;
import com.jsonplaceholder.tests.models.PutUpdatePostRequestAndResponseModel;
import com.jsonplaceholder.tests.steps.GetPostsByUserIdSteps;
import com.jsonplaceholder.tests.steps.GetPostsSteps;
import com.jsonplaceholder.tests.steps.PutUpdatePostSteps;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

/**
 * Created by @Boki on Jan, 2020
 */
public class JsonPlaceholderTests extends BaseApiTest {

    @Steps
    GetPostsSteps getPostsSteps;

    @Steps
    GetPostsByUserIdSteps getPostsByUserIdSteps;

    @Steps
    PutUpdatePostSteps putUpdatePostSteps;

    @Test
    public void T1_get_callAll100BlogPosts_all100BlogPostsAreReceivedInResponse(){
        SoftAssertions softAssertions = new SoftAssertions();
        getPostsSteps.callPosts();

//        Assertions starting point
//        Assertion that Status code is 200
        softAssertions.assertThat(getPostsSteps.getResponse().getStatusCode()).as("Status code is 200.")
                .isEqualTo(200);
//        Assertion that returned number of posts is 100
        softAssertions.assertThat(getPostsSteps.countAllPosts()).as("Number of blog posts").isEqualTo(100);

        softAssertions.assertAll();
    }

    @Test
    public void T1_get_callAll10BlogPostsWrittenByUserId5_all10BlogPostsWrittenByUserId5AreReceivedInResponse(){
        int userId = 5;
        SoftAssertions softAssertions = new SoftAssertions();
        getPostsByUserIdSteps.callPostsByUserId(userId);

//        Assertions starting point
//        Assertion that Status code is 200
        softAssertions.assertThat(getPostsByUserIdSteps.getResponse().getStatusCode()).as("Status code is 200.")
                .isEqualTo(200);
//        Assertion that returned number of blog posts written by UserId=5 is 10
        softAssertions.assertThat(getPostsByUserIdSteps.countAllPosts()).as("Number of blog posts written by user whose userID").isEqualTo(10);

        softAssertions.assertAll();
    }

    @Test
    public void T3_put_idInPostsBodyPayloadIsStringNotInt_blogPostIsSuccessfullyUpdated(){
        SoftAssertions softAssertions = new SoftAssertions();

//        Values of the payloadPostId and resourcePostId must be the same, but the data type is different.
        String bodyPayloadPostId = "1";
        int resourcePostId = 1;
        int userId = 1;
        String title = "Little";
        String body = "Wing";

        PutUpdatePostRequestAndResponseModel putUpdatePostRequest = new PutUpdatePostRequestAndResponseModel();
        putUpdatePostRequest.setBody(body);
        putUpdatePostRequest.setTitle(title);
        putUpdatePostRequest.setId(bodyPayloadPostId);
        putUpdatePostRequest.setUserId(userId);

//        Run PUT request
        putUpdatePostSteps.updatePostByPostId(resourcePostId,putUpdatePostRequest);

//        Serialized response
        PutUpdatePostRequestAndResponseModel putUpdatePostResponse = putUpdatePostSteps.SerializePutUpdateResponse();

        softAssertions.assertThat(putUpdatePostSteps.getResponse().getStatusCode()).as("Status code is 200.")
                .isEqualTo(200);
        softAssertions.assertThat(putUpdatePostResponse.getId()).as("Post id")
                .isEqualTo(resourcePostId);
        softAssertions.assertThat(putUpdatePostResponse.getUserId()).as("User id")
                .isEqualTo(userId);
        softAssertions.assertThat(putUpdatePostResponse.getBody()).as("Body")
                .isEqualTo(body);
        softAssertions.assertThat(putUpdatePostResponse.getTitle()).as("Title")
                .isEqualTo(title);

        softAssertions.assertAll();
    }

    @Test
    public void T4_get_zeroAsBlogPostIdInResourceUri_statusCodeIs404AndEmptyJsonIsReceivedInResponse(){

    }

    @Test
    public void T5_delete_deleteAtOnceAllBlogPostsUnderPostsCollectionResource_receivedAnErrorAndNothingIsDeleted(){

    }
}
