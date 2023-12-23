package com.qa.gorest.tests;

import com.qa.gorest.basetest.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;
import lombok.Data;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseTest {


    @BeforeMethod
    public void getUserStepMethod(){
        restClient = new RestClient(prop,baseURI);
    }


    @DataProvider
    public  Object [][] getUserTestData(){
        return new Object[][]{
                {"shobhit","male","active"},
                {"shobhit1","male","inactive"},
                {"shobhit12","male","active"}
        };
    }
    @Test(dataProvider = "getUserTestData")
    public void createUserTest(String name,String gender,String status){

        User user = new User(name,StringUtils.getRandomEmail(),gender,status);

        // POST:
        int userId=restClient.post(GO_REST_ENDPOINT,user,"json",true,true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.CREATED_201.getCode())
                .extract().path("id");

        System.out.println("User id: "+userId);

        // GET:

        RestClient getClient = new RestClient(prop,baseURI);
        getClient.get(GO_REST_ENDPOINT+userId,true,true)
                .then().log().all()
                .assertThat().statusCode(APIHttpStatus.OK_200.getCode())
                .assertThat().body("id",equalTo(userId));

    }

}
