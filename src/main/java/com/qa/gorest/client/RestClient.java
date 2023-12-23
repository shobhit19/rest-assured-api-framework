package com.qa.gorest.client;

import com.qa.gorest.exceptions.APIFrameworkException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class RestClient {

    private static RequestSpecBuilder requestSpecBuilder;
    private boolean isAuthorizationHeaderAdded = false;
//    private static final String BASE_URI = "https://gorest.co.in";
//    private static final String BEARER_TOKEN = "56ac3ee0caddbd695b03ee69b35b2df32afcb0f661e6bafb1bfdf0d1179a40cf";

//    static {
//        requestSpecBuilder = new RequestSpecBuilder();
//    }

    private Properties prop;
    private String baseURI;
    public RestClient(Properties prop, String baseURI){
        requestSpecBuilder = new RequestSpecBuilder();
        this.prop = prop;
        this.baseURI = baseURI;
    }



    public  void addAuthorizationHeader(){
        if(!isAuthorizationHeaderAdded) {
            requestSpecBuilder.addHeader("Authorization", "Bearer " + prop.getProperty("tokenId"));
            isAuthorizationHeaderAdded=true;
        }

    }

    private void setRequestContentType(String contentType){
       switch(contentType.toLowerCase()){
           case "json":
               requestSpecBuilder.setContentType(ContentType.JSON);
               break;
           case "xml":
               requestSpecBuilder.setContentType(ContentType.XML);
                break;
           case "text":
               requestSpecBuilder.setContentType(ContentType.TEXT);
               break;
           case "multipart":
               requestSpecBuilder.setContentType(ContentType.MULTIPART);
               break;
           default:
               System.out.println("pls pass the right content type");
               throw new APIFrameworkException("INVALIDCONTENTTYPE");
       }
    }
    public  RequestSpecification createReqSpec(boolean includeAuth){
            requestSpecBuilder
                    .setBaseUri(baseURI);
            if(includeAuth) {
                addAuthorizationHeader();
            }
            return requestSpecBuilder.build();
    }

    public  RequestSpecification createReqSpec(Map<String,String> headers,boolean includeAuth){
        requestSpecBuilder
                .setBaseUri(baseURI);
        addAuthorizationHeader();
        if(headers!=null){
            requestSpecBuilder.addHeaders(headers);
        }
        return requestSpecBuilder.build();
    }
    public  RequestSpecification createReqSpec(Map<String,String> headers,Map<String,Object> queryParams,boolean includeAuth){
        requestSpecBuilder
                .setBaseUri(baseURI);
        if(includeAuth) {
            addAuthorizationHeader();
        }
        if(headers!=null){
            requestSpecBuilder.addHeaders(headers);
        }
        if(queryParams!=null){
            requestSpecBuilder.addQueryParams(queryParams);
        }
        return requestSpecBuilder.build();
    }
    public RequestSpecification createReqSpec(Object requestBody,String contentType,boolean includeAuth){
        requestSpecBuilder
                .setBaseUri(baseURI);
        addAuthorizationHeader();
        setRequestContentType(contentType);
        if(requestBody!=null) {
            requestSpecBuilder.setBody(requestBody);
        }
        return requestSpecBuilder.build();
    }
    public RequestSpecification createReqSpec(Object requestBody,String contentType,Map<String,String> headers,boolean includeAuth){
        requestSpecBuilder.setBaseUri(baseURI);
        addAuthorizationHeader();
        setRequestContentType(contentType);
        if(headers!=null){
            requestSpecBuilder.addHeaders(headers);
        }
        if(requestBody!=null) {
            requestSpecBuilder.setBody(requestBody);
        }
        return requestSpecBuilder.build();
    }

    // http methods util

    public Response get(String serviceUrl, boolean includeAuth,boolean log){
        if(log) {
           return given().log().all()
                    .spec(createReqSpec(includeAuth))
                    .when()
                    .get(serviceUrl);
        }
        return given()
                .spec(createReqSpec(includeAuth))
                .when()
                .get(serviceUrl);
    }
    public Response get(String serviceUrl,Map<String,String> headers,boolean log,boolean includeAuth){
        if(log) {
            return given().log().all()
                    .spec(createReqSpec(headers,includeAuth))
                    .when()
                    .get(serviceUrl);
        }
        return given()
                .spec(createReqSpec(includeAuth))
                .when()
                .get(serviceUrl);
    }
    public Response get(String serviceUrl,Map<String,String> headers,Map<String,Object> queryParams,boolean log,boolean includeAuth){
        if(log) {
            return given().log().all()
                    .spec(createReqSpec(headers,queryParams,includeAuth))
                    .when()
                    .get(serviceUrl);
        }
        return given()
                .spec(createReqSpec(includeAuth))
                .when()
                .get(serviceUrl);
    }

    public Response post(String serviceUrl,Object requestBody,String contentType,boolean log,boolean includeAuth){
         if(log) {
             return given().log().all()
                    .spec(createReqSpec(requestBody,contentType,includeAuth))
                    .when()
                    .post(serviceUrl);
        }
        return given()
                .spec(createReqSpec(includeAuth))
                .when()
                .post(serviceUrl);
    }

    public Response post(String serviceUrl,Map<String,String> headers,Object requestBody,String contentType,boolean log,boolean includeAuth){
        if(log) {
            return given().log().all()
                    .spec(createReqSpec(requestBody,contentType,headers,includeAuth))
                    .when()
                    .post(serviceUrl);
        }
        return given()
                .spec(createReqSpec(includeAuth))
                .when()
                .post(serviceUrl);
    }
    public Response put(String serviceUrl,Object requestBody,String contentType,boolean log,boolean includeAuth){
        if(log) {
            return given().log().all()
                    .spec(createReqSpec(requestBody,contentType,includeAuth))
                    .when()
                    .put(serviceUrl);
        }
        return given()
                .spec(createReqSpec(includeAuth))
                .when()
                .put(serviceUrl);
    }

    public Response put(String serviceUrl,Map<String,String> headers,Object requestBody,String contentType,boolean log,boolean includeAuth){
        if(log) {
            return given().log().all()
                    .spec(createReqSpec(requestBody,contentType,headers,includeAuth))
                    .when()
                    .put(serviceUrl);
        }
        return given()
                .spec(createReqSpec(includeAuth))
                .when()
                .put(serviceUrl);
    }
    public Response patch(String serviceUrl,Object requestBody,String contentType,boolean log,boolean includeAuth){
        if(log) {
            return given().log().all()
                    .spec(createReqSpec(requestBody,contentType,includeAuth))
                    .when()
                    .patch(serviceUrl);
        }
        return given()
                .spec(createReqSpec(includeAuth))
                .when()
                .patch(serviceUrl);
    }

    public Response patch(String serviceUrl,Map<String,String> headers,Object requestBody,String contentType,boolean log,boolean includeAuth){
        if(log) {
            return given().log().all()
                    .spec(createReqSpec(requestBody,contentType,headers,includeAuth))
                    .when()
                    .patch(serviceUrl);
        }
        return given()
                .spec(createReqSpec(includeAuth))
                .when()
                .patch(serviceUrl);
    }

    public Response delete(String serviceUrl,boolean log,boolean includeAuth){
        if(log) {
            return given().log().all()
                    .spec(createReqSpec(includeAuth))
                    .when()
                    .delete(serviceUrl);
        }
        return given()
                .spec(createReqSpec(includeAuth))
                .when()
                .delete(serviceUrl);
    }

    public String getAccessToken(String serviceURL,String grantType,String clientId,String clientSecret){

        RestAssured.baseURI="https://test.api.amadeus.com";

        String accessToken = given().log().all()
                .contentType(ContentType.URLENC)
                .formParam("grant_type",grantType)
                .formParam("client_id",clientId)
                .formParam("client_secret",clientSecret)
                .when()
                    .post(serviceURL)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().path("access_token");

        System.out.println("access token: "+accessToken);
        return accessToken;
    }
}
