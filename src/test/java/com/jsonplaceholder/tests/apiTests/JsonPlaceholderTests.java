package com.jsonplaceholder.tests.apiTests;

import com.jsonplaceholder.tests.BaseApiTest;
import com.jsonplaceholder.tests.steps.GetPostsSteps;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

/**
 * Created by @Boki on Jan, 2020
 */
public class JsonPlaceholderTests extends BaseApiTest {

    @Steps
    GetPostsSteps getPostsSteps;

    @Test
    public void T1_get_all100BlogPosts_all100BlogPostsAreReceivedInResponse(){
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
}
