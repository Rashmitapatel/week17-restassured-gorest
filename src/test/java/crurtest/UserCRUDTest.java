package crurtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import testbase.TestBase;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static String name = "Rashmita" + TestUtils.getRandomValue();
    static String email = "Rashmita" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "Female";
    static String status = "active";
    static int   id;
    @Test
    public void test001() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        ValidatableResponse response = given()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .header("Authorization", "Bearer 69972522887ad71c7da77162df05b6b2f191d46d40d9e444ceff69bfdf098e25")
                .when()
                .body(userPojo)
                .post()
                .then().log().body().statusCode(201);
        id= response.extract().path("id");
    }
    @Test
    public  void test002(){
        int uId=given()
                .header("Authorization", "Bearer 69972522887ad71c7da77162df05b6b2f191d46d40d9e444ceff69bfdf098e25")
                .pathParams("id",id)
                .when()
                .get("/{id}")
                .then().statusCode(200)
                .extract()
                .path("id");
        Assert.assertEquals(uId,id);

    }
    @Test
    public void test003(){
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Trushar");
        userPojo.setEmail("Trushar"+ TestUtils.getRandomValue()+"@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("inactive");
        Response response= given()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer 69972522887ad71c7da77162df05b6b2f191d46d40d9e444ceff69bfdf098e25")
                .pathParams("id", id)
                .when()
                .body(userPojo)
                .put("/{id}");
        response.then().log().body().statusCode(200);
    }
    @Test
 public  void test004(){
     given()
             .header("Authorization","Bearer 69972522887ad71c7da77162df05b6b2f191d46d40d9e444ceff69bfdf098e25")
             .pathParam("id", id)
             .when()
             .delete("/{id}")
             .then()
             .statusCode(204);

     given()
             .header("Authorization","Bearer 69972522887ad71c7da77162df05b6b2f191d46d40d9e444ceff69bfdf098e25")
             .pathParam("id", id)
             .when()
             .get("/{id}")
             .then().statusCode(404);

 }
}



