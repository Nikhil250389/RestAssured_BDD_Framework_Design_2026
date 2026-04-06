package TestBasicDay1;
import static io.restassured.RestAssured.*;
import static org.testng.Assert. *;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
public class addPlaceApis {
	
	@Test
	
	void addPlaceApi() {
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
	String  response=	 given().log().all().queryParam("key", "qaclick123").body("{\r\n"
		 		+ "  \"location\": {\r\n"
		 		+ "    \"lat\": -38.383494,\r\n"
		 		+ "    \"lng\": 33.427362\r\n"
		 		+ "  },\r\n"
		 		+ "  \"accuracy\": 50,\r\n"
		 		+ "  \"name\": \"Frontline house\",\r\n"
		 		+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
		 		+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
		 		+ "  \"types\": [\r\n"
		 		+ "    \"shoe park\",\r\n"
		 		+ "    \"shop\"\r\n"
		 		+ "  ],\r\n"
		 		+ "  \"website\": \"http://google.com\",\r\n"
		 		+ "  \"language\": \"French-IN\"\r\n"
		 		+ "}")
		 .when().post("maps/api/place/add/json")
		 .then().log().all().assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)"
		 		+ "").extract().response().asString()
		 ;
	
	JsonPath js= new JsonPath(response);
	String placeId=js.getString("place_id");
	System.out.println(placeId);
	
	
	//}
	String  response2=	 given().log().all().queryParam("key", "qaclick123").body("{\r\n"
			+ "\"place_id\":\""+placeId+"\",\r\n"
			+ "\"address\":\"70 winter walk, USA\",\r\n"
			+ "\"key\":\"qaclick123\"\r\n"
			+ "}")
	 .when().put("maps/api/place/update/json")
	 .then().log().all().assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)"
	 		+ "").extract().response().asString()
	 ;
	JsonPath js1= new JsonPath(response2);
	String msg=js1.getString("msg");
	System.out.println(msg);
	
	String  response3=	 given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
	 .when().get("maps/api/place/get/json")
	 .then().log().all().assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)"
	 		+ "").extract().response().asString()
	 ;
	JsonPath js2= new JsonPath(response3);
	String name=js2.getString("name");
	System.out.println(name);
	System.out.println(name);
	Assert.assertEquals("Frontline house", name);
}}
