package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import testbase.TestBase;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest extends TestBase {
  static ValidatableResponse response;

      @BeforeClass
      public static void inIt() {
          RestAssured.baseURI = "https://gorest.co.in/";
          RestAssured.basePath = "public/v2";

     response = given()
              .queryParam("page", 1)
              .queryParam("per_page", 25)
              .when()
              .get("/posts")
              .then().statusCode(200);

  }
    // 1. Extract the title
    @Test
    public void test001(){
        List<String>title = response.extract().path("title");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title are : " + title);
        System.out.println("------------------End of Test---------------------------");

    }
    //2. Extract the total number of record
    @Test
    public void test002() {
        List<Integer>totalNo = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total no of data: " + totalNo.size());
        System.out.println("------------------End of Test---------------------------");

    }
    //3. Extract the body of 15th record
    public void test003(){

      String record = response.extract().path("[15].body");

      System.out.println("------------------StartingTest---------------------------");
      System.out.println("The body of 15th record :" + record);
      System.out.println("------------------End of Test---------------------------");

        }
        //4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer>userId = response.extract().path("user_id");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The user_id of all the records :" + userId);
        System.out.println("------------------End of Test---------------------------");

    }
    //5. Extract the title of all the records
    @Test
    public void test005() {
        List<String> records = response.extract().path("title");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  title of all the records :" +records);
        System.out.println("------------------End of Test---------------------------");

    }
    //6. Extract the title of all records whose user_id =  2272670
    @Test
    public void test006() {
        List<String> titleOfId = response.extract().path("findAll{it.user_id==2272670}.title");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  title of all the records :" +titleOfId);
        System.out.println("------------------End of Test---------------------------");

    }
    //7. Extract the body of all records whose id = 2272669
    @Test
    public void test007() {
        List<String> titleOfId = response.extract().path("findAll{it.id==2272669}.body");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The  title of all the records :" +titleOfId);
        System.out.println("------------------End of Test---------------------------");

    }

}







