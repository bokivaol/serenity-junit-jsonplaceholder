package com.jsonplaceholder.tests;

import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.mapper.ObjectMapperType;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.RestDefaultsChained;
import net.thucydides.core.util.EnvironmentVariables;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Created by @Boki on Jan, 2020
 * Class is abstract to tell Junit to ignore this class as a Test(not to run it)
 */
@RunWith(SerenityRunner.class)
public abstract class BaseApiTest {

    protected EnvironmentVariables environmentVariables;
    protected static boolean initialized = false;

    @BeforeClass
    public static void setup() {
        new RestDefaultsChained()
                .useRelaxedHTTPSValidation()
                //needed only for JUnit report, in case that Serenity report fails to create
                .enableLoggingOfRequestAndResponseIfValidationFails()
                .setDefaultConfig(RestAssured.config().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON)));
    }

    @Before
    public void init(){
        //initialize only once.
        //It can't be done within @BeforeClass, because Serenity environmentVariables can't be used from a static context
        if(! initialized) {
            RestAssured.baseURI = EnvironmentSpecificConfiguration
                    .from(environmentVariables)
                    .getProperty("domain");
            System.out.println("Base URI: " + RestAssured.baseURI);

            initialized = true;
        }
    }
}
