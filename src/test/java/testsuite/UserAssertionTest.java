package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import testbase.TestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;


    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2";

        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    //    1. Verify the if the total record is 20
    @Test
    public void verifyTotalRecord() {
        response.body("size", equalTo(20));
    }

    //    2. Verify the if the name of id = 2178430 is equal to ”Kanti Deshpande”
    public void verifyName() {
        response.body("find{it.id==2178430}.name", equalTo("Kanti Deshpande"));
    }

    //   3.Check the single ‘Name’ in the Array list ("Enakshi Talwar")
    public void verifySingleName() {
        response.body("[5].name", equalTo("Enakshi Talwar"));
    }

    //  4.Check the multiple ‘Names’ in the ArrayList ("Kanti Deshpande", "Enakshi Talwar")
    public void verifyMulipleNames() {
        response.body("name", hasItems("Kanti Deshpande", "Enakshi Talwar"));
    }

    // 5. Verify the emai of userid = 2178430 is equal “deshpande_kanti@durgan.example”
    public void verifyEmailId() {
        response.body("findAll{it.id== 2178430}.email", hasItem("deshpande_kanti@durgan.example"));
    }

    // 6. Verify the status is “Active” of user name is “Enakshi Talwar”
    public void verifyStatus() {
        response.body("findAll{it.name=='Enakshi Talwar'}.status", hasItem("active"));
    }

    //   7. Verify the Gender = male of user name is “Enakshi Talwar"
    public void verifyGender() {
        response.body("findAll{it.name= 'Enakshi Talwar'}.gender", hasItem("female"));
    }

}








