package com.jsonplaceholder.tests;

import com.jsonplaceholder.tests.steps.GetPostsSteps;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;

/**
 * Created by @Boki on Jan, 2020
 */
public class JsonPlaceholderTests extends BaseApiTest {

    @Steps
    GetPostsSteps getPostsSteps;

    @Test
    public void T1_get_all100BlogPosts_all100BlogPostsAreReceivedInResponse(){
        getPostsSteps.callPosts();
        System.out.println("Number of posts: " + getPostsSteps.countAllPosts());
    }
}
