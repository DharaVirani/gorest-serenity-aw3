package com.gorest.gorestinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {


    @Step("Creating a user with name: {0}, email: {1}, gender: {2}, status: {3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status){

        UserPojo userPojo = UserPojo.getUserPojo(name,email,gender,status);

        return SerenityRest.given()
                .header("Authorization","Bearer b4ca58749ef980c797da415df530cac00dbac642ef3839896b47de0b428a2df5")
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post(EndPoints.GET_ALL_USERS)
                .then().log().all();
    }





    @Step("Updating user information with userId : {0}, name : {1}, email : {2}, gender : {3}, status : {4}")
    public ValidatableResponse updateUser(int userId, String name, String email, String gender, String status) {
        UserPojo userPojo = UserPojo.getUserPojo(name, email, gender, status);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer b4ca58749ef980c797da415df530cac00dbac642ef3839896b47de0b428a2df5")
                .header("Connection", "keep-alive")
                .pathParam("userId", userId)
                .when()
                .body(userPojo)
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then().statusCode(200);
    }


    @Step("Getting the user information with name : {0}")
    public HashMap<String, Object> getUserInfoByName(String name) {
        String s1 = "findAll{it.name == '";
        String s2 = "'}.get(0)";
        return SerenityRest.given()
                .header("Authorization", "Bearer b4ca58749ef980c797da415df530cac00dbac642ef3839896b47de0b428a2df5")
                .header("Connection", "keep-alive")
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then().statusCode(200)
                .extract()
                .path(s1 + name + s2);
    }

    @Step
    public ValidatableResponse deleteUser(int id){
       return SerenityRest.given()
                .header("Authorization","Bearer b4ca58749ef980c797da415df530cac00dbac642ef3839896b47de0b428a2df5")
               .pathParam("userId", id)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then().log().all();
    }


    @Step("Getting the User information with firstName : {0}")
    public HashMap<String, Object> getUserInfoByEmail(String email){
        String s1 = "findAll{it.email == '";
        String s2 = "'}.get(0)";
        return SerenityRest.given()
                .header("Authorization","Bearer b4ca58749ef980c797da415df530cac00dbac642ef3839896b47de0b428a2df5")
                .header("Connection", "keep-alive")
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then()
                .statusCode(200)
                .extract()
                .path(s1 + email + s2);
    }



    @Step("Getting a user by Id")
    public ValidatableResponse getUserById(int id){
        return SerenityRest.given().log().all()
                .pathParam("userId", id)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)//here get single student whatever id we deleted
                .then().log().all();
    }
}
