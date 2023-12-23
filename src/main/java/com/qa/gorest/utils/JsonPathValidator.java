package com.qa.gorest.utils;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class JsonPathValidator {

    private String getJsonResponseAsString(Response response){
        return response.getBody().asString();
    }

    public <T> T read(Response response,String jsonPath){

        return JsonPath.read(getJsonResponseAsString(response),jsonPath);
    }

    public <T> List<T> readList(Response response, String jsonPath){
        return JsonPath.read(getJsonResponseAsString(response),jsonPath);
    }

    public <T> List<Map<String,T>> readListOfMaps(Response response, String jsonPath){
        return JsonPath.read(getJsonResponseAsString(response),jsonPath);
    }
}
