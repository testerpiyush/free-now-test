package com.freenow.apitest.utility;

import com.freenow.apitest.constants.ApiConstants;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class RestUtil {

    public static Response callGet(String baseUrl, String path) {
        Response response = RestAssured.given().contentType(ContentType.JSON).log().all().when()
                                    .get(baseUrl + path);
        response.then().statusCode(ApiConstants.STATUS_CODE_200).log().all();
        return response;
    }

    public static Response callGet(String baseUrl, String path, String paramName, String paramValue) {
        Response response = RestAssured.given().contentType(ContentType.JSON).queryParam(paramName, paramValue).log().all().when()
                                    .get(baseUrl + path);
        response.then().log().all();
        return response;
    }


}
