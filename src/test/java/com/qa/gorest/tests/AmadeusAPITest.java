package com.qa.gorest.tests;

import com.qa.gorest.basetest.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class AmadeusAPITest extends BaseTest {

    private String accessToken;
    @Parameters({"baseURI","grantType","clientId","clientSecret"})
    @BeforeMethod
    public void flightAPISetup(String baseURI,String grantType,String clientId,String clientSecret){
        restClient = new RestClient(prop,baseURI);
        accessToken = restClient.getAccessToken(AMADEUS_TOKEN_ENDPOINT,grantType, clientId, clientSecret);
    }

    @Test
    public void getFlightInfoTest(){
        RestClient getRestClientFlight = new RestClient(prop,baseURI);

        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("origin","PAR");
        queryParams.put("maxPrice",200);

        Map<String,String> headersMap = new HashMap<>();
        headersMap.put("Authorization","Bearer "+accessToken);

        Response flightDataResponse =getRestClientFlight.get(AMADEUS_FLIGHTBOOKING_ENDPOINT,headersMap,queryParams,true,false)
                .then().log().all()
                .assertThat()
                    .statusCode(APIHttpStatus.OK_200.getCode())
                .and()
                .extract()
                .response();
        ;
        JsonPath js  =flightDataResponse.jsonPath();
        String type = js.get("data[0].type");
        System.out.println(type);
    }


}
