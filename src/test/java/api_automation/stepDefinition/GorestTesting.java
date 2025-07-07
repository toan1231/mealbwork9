package api_automation.stepDefinition;

import api_automation.RequestBuilder.GorestRequestBuilder;
import api_automation.utils.TestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;


public class GorestTesting extends TestBase {
    String objectMapper;
    Response response;
    int id;
    @Given("User create request data with {string} , {string} , {string} , {string}")
    public void user_create_request_data_with(String name, String email, String gender, String status) throws JsonProcessingException {
        int a=5;
        GorestRequestBuilder   gorestRequestBuilder = new GorestRequestBuilder();
        gorestRequestBuilder.setEmail(email);
        gorestRequestBuilder.setName(name);
        gorestRequestBuilder.setGender(gender);
        gorestRequestBuilder.setStatus(status);
        ObjectMapper mapper=new ObjectMapper();
            objectMapper=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(gorestRequestBuilder);
/// //////////////////////////////////////////////////////////////////////////////////

    }
    @When("User sumbits POST request to GOREST api")
    public void user_sumbits_post_request_to_gorest_api() {
         response= given()
                .header("Authorization","Bearer "+property.getProperty("gorestAPIKey"))
                .contentType(ContentType.JSON)
                 .body(objectMapper)
                .when()
                .post(property.getProperty("gorestApiURI"));
        response.prettyPrint();



    }
    @When("User validates if statusCode is {int}")
    public void user_validates_if_status_code_is(int statusCode) {
    int actualStatusCode=response.getStatusCode();
        Assert.assertEquals(statusCode,actualStatusCode);
    }
    @Then("User retrieves userID from response")
    public void user_retrieves_user_id_from_response() {
        //saying use read() method from JsonPath class
         id=JsonPath.read(response.asString(),"$.data.id");
        System.out.println("The id from response is: "+id);
    }
    @And("User get the above record")
    public void userGetTheAboveRecord() {
        Response response=given()
                .header("Authorization","Bearer "+property.getProperty("gorestAPIKey"))
                .when()
                //Note: Mistake has been made I put "gorestApiURI/" instead of ("gorestApiURI)"+"/"+id
                .get(property.getProperty("gorestApiURI")+"/"+id);

    }
    @Then("User deletes data with userID")
    public void user_deletes_data_with_user_id() {
       Response response=given()
               .header("Authorization","Bearer "+property.getProperty("gorestAPIKey"))
               .contentType(ContentType.JSON)
               .when()
               .delete(property.getProperty("gorestApiURI")+"/"+id);
    }


}
