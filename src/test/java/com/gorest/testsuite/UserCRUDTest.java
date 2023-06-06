package com.gorest.testsuite;

import com.gorest.gorestinfo.UserSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {



    static String name = "Piyush";
    static String email = "piyush" + TestUtils.getRandomValue() +"gorasiya@gmail.com";
    static String gender = "female";
    static String status = "active";
    static String token = "b4ca58749ef980c797da415df530cac00dbac642ef3839896b47de0b428a2df5";
    static int user_id;

    @Steps
    UserSteps userSteps;

    //verifyCreateUserSuccessfully
    @Title("This will create a user")
    @Test
    public void a1() {
        ValidatableResponse response = userSteps.createUser(name, email, gender, status).statusCode(201);
        user_id = response.extract().path("id");

    }



    @Title("Verify if the user was added to the application")
    @Test
    public void a2(){
        HashMap<String, Object> usersMap = userSteps.getUserInfoByEmail(email);
        Assert.assertThat(usersMap, hasValue(email));
        user_id = (int) usersMap.get("id");
    }




    @Title("Update the user information and verify the updated information")
    @Test()
    public void a3() {
        //update the user
        name = name + TestUtils.getRandomValue();
        userSteps.updateUser(user_id, name, email, gender, status);

        //verify the user is updated correctly
        HashMap<String, Object> userMap = userSteps.getUserInfoByName(name);
        Assert.assertThat(userMap, hasValue(name));

    }

    //verifyUserDeleteSuccessfully()
    @Test
    public void a4(){
        //delete the user
        userSteps.deleteUser(user_id).statusCode(204);

        //verify that it is deleted
        userSteps.getUserById(user_id).statusCode(404);
    }

}
