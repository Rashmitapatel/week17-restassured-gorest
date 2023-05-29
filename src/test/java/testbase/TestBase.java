package testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {
   @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2/users";
    }
}
