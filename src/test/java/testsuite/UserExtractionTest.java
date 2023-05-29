package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI= "https://gorest.co.in/";;
        RestAssured.basePath="public/v2";

        response = given()
                .queryParam("page",1)
                .queryParam("per_page",20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }
    // 1. Extract  All Ids
    @Test
    public void test001(){
        List<Integer> id = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total is are : " + id);
        System.out.println("------------------End of Test---------------------------");
    }
    //2. Extract the all Names
    @Test
    public void test002(){
        List<String> name = response.extract().path("name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total name are : " + name);
        System.out.println("------------------End of Test---------------------------");
    }
    //3. Extract the name of 5th object
    @Test
    public void test003(){
        String name = response.extract().path("[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the name of 5 th object " + name);
        System.out.println("------------------End of Test---------------------------");
    }
    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004(){
        List<String>name = response.extract().path("findAll{it.status=='inactive'}.name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status = inactive " + name);
        System.out.println("------------------End of Test---------------------------");

    }
    //5. Extract the gender of all the object whose status = active
    public void test005(){
    List<String> genderName = response.extract().path("findAll{it.status=='active'}.gender");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender of all object whose status = active " + genderName);
        System.out.println("------------------End of Test---------------------------");
}
    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> gender = response.extract().path("findAll{it.gender=='female'}.name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the object whose gender = female" + gender);
        System.out.println("------------------End of Test---------------------------");
    }
    //7. Get all the emails of the object where status = inactive

    @Test
    public void test007() {
        List<?> email = response.extract().path("findAll{it.status=='inactive'}.email");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" All the emails of the object where status = inactive" + email);
        System.out.println("------------------End of Test---------------------------");
    }
      //8. Get the ids of the object where gender = male
      @Test
      public void test008() {
          List<?> ids = response.extract().path("findAll{it.gender=='male'}.id");


          System.out.println("------------------StartingTest---------------------------");
          System.out.println("  the ids of the object where gender = male" + ids);
          System.out.println("------------------End of Test---------------------------");
      }
    //9. Get all the status
    public void test009() {
        List<?> status = response.extract().path("status");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  the ids of the object where gender = female" +status);
        System.out.println("------------------End of Test---------------------------");
    }
    //10. Get email of the object where name = Navin Nayar III

    @Test
    public void test010() {
     List<String> email = response.extract().path("findAll{it.name='Navin Nayar III'}.email");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  the ids of the object where gender = male" +email);
        System.out.println("------------------End of Test---------------------------");
    }
    //11. Get gender of id =  2178422
    @Test
    public void test011() {
        List<?>  id = response.extract().path("findAll{it.id=' 2178422'}.id");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  the gender of id   = 2178422" + id);
        System.out.println("------------------End of Test---------------------------");
    }

}
