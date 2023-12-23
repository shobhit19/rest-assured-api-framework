package com.qa.gorest.tests;

import com.qa.gorest.basetest.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.utils.JsonPathValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;

public class CircuitTest extends BaseTest {


    @BeforeMethod
    public void getUserStepMethod(){
        restClient = new RestClient(prop,baseURI);
    }

    @Test
    public void getAllUsersTest(){
        Response response =restClient.get(CIRCUIT_ENDPOINT+"/2019/circuits.json",false,true);
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode,APIHttpStatus.OK_200.getCode());
        JsonPathValidator jsonPathValidator = new JsonPathValidator();
        List<String> countryList=jsonPathValidator.readList(response,"$..Circuits[?(@.circuitId=='shanghai')].Location.country");
        System.out.println(countryList);

        Assert.assertTrue(countryList.contains("China"));


    }

}
