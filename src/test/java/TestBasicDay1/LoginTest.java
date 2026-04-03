package TestBasicDay1;

import static io.restassured.RestAssured.*;
import static org.testng.Assert. *;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class LoginTest {

	String token;
	String _id;
    @Test (priority=1)
    public void userLogin() {

        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

        String requestBody = """
                {
                  "email": "nikhil.kharkhodia@gmail.com",
                  "password": "Nikhil@8"
                }
                """;

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .accept("*/*")
                        .body(requestBody)
                .when()
                        .post("/users/login");

        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getString("user.firstName"), "Nikhil");
        assertEquals(response.jsonPath().getString("user.lastName"), "Kharkhodia");
        assertEquals(response.jsonPath().getString("user.email"), "nikhil.kharkhodia@gmail.com");
        token = response.jsonPath().getString("token");
        


        response.prettyPrint();
    }
    @Test (priority=2)
    public void createContact() {

    	RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

    	String requestBody = """
    	        {
    	          "firstName": "John",
    	          "lastName": "Kh",
    	          "birthdate": "1986-10-08",
    	          "email": "nikhil34@yopmail.com",
    	          "phone": "88776667",
    	          "street1": "G-238",
    	          "street2": "E-4",
    	          "city": "Ghaziabad",
    	          "stateProvince": "Uttar Pradesh",
    	          "postalCode": "201013",
    	          "country": "India"
    	        }
    	        """;

    	Response response =
    	        given()
    	                .contentType(ContentType.JSON)
    	                .accept("*/*")
    	                .header("Authorization", "Bearer " + token)
    	                .cookie("token", token)
    	                .body(requestBody)
    	        .when()
    	                .post("/contacts")
    	        .then().log().all()
    	                .statusCode(201)
    	                .extract()
    	                .response();
    	

    	// Assertions (AFTER response extraction)
    	assertEquals(response.jsonPath().getString("firstName"), "John");
    	assertEquals(response.jsonPath().getString("lastName"), "Kh");
    	assertEquals(response.jsonPath().getString("email"), "nikhil34@yopmail.com");
    	String id = response.jsonPath().getString("_id");
    	assertTrue(id.matches("^[a-f0-9]{24}$"), "Invalid Mongo ObjectId format");
    	_id = response.jsonPath().getString("_id");
    	System.out.println("User Id is:" +_id);
    	System.out.println(response.asPrettyString());
 
    }


    @Test (priority=3)
    public void deleteContact() {

        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

       // String token = "<PUT_VALID_TOKEN_HERE>";
       // String contactId = "6969af890505430015c4f565";

        given()
                .accept("*/*")
                .header("Authorization", "Bearer " + token)
                .cookie("token", token)   // optional
        .when()
                .delete("/contacts/" + _id)
        .then()
                .statusCode(200);
    }
}







