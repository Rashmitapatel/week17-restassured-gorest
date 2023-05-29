package testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import testbase.TestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostAsserstionTest   extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void InIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2";
        response = given()

                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200);

    }

    //    1. Verify the if the total record is 25
    @Test
    public void verifyTotal() {
        response.body("size()", equalTo(25));
    }

    // 2. Verify the if the title of id = 39295 is equal to "Sapiente aestivus valde aro vacuus saepe."
    @Test
    public void verifyTitle() {
        response.body("findAll{it.id== 39295}.title", hasItem("Sapiente aestivus valde aro vacuus saepe."));
    }

    @Test
    //  3. Check the single user_id in the Array list ( 2272684)
    public void verifyUserId() {
        response.body("[0].user_id", equalTo(2272684));
    }

    //4. Check the multiple ids in the ArrayList (39292, 39297,39304)
    @Test
    public void verifyMultipleId() {
        response.body("id", hasItems(39292, 39297, 39304));
    }


//5. Verify the body of userid = 2272666 is equal  "Contabesco tamen solio. Vivo tamdiu arbustum. Casus viridis vox. Sub suscipio adsum. Est careo minus. Coniecto terra congregatio. Versus doloremque volubilis. Dedecor corporis victus. Thorax tremo aequus. Vero corroboro dolorem. Solus sursum advenio. Sunt abutor cunabula. At conor est. Pax officiis succedo. Concido sumo atque. Carcer sui delectatio. Deduco agnitio volo. Appello arbustum qui."

    @Test
    public void verifyBody() {
        response.body("findAll{it.user_id==2272666}.body", hasItem("Contabesco tamen solio. Vivo tamdiu arbustum. Casus viridis vox. Sub suscipio adsum. Est careo minus. " +
                "Coniecto terra congregatio. Versus doloremque volubilis. " +
                "Dedecor corporis victus. Thorax tremo aequus." +
                " Vero corroboro dolorem. Solus sursum advenio. " +
                "Sunt abutor cunabula. At conor est. Pax officiis succedo. " +
                "Concido sumo atque. Carcer sui delectatio. " +
                "Deduco agnitio volo. Appello arbustum qui."));
    }
}




