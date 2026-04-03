package TestBasicDay1;
import static io.restassured.RestAssured.*;
import static org.testng.Assert. *;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import api.Payloads.JsonFullPayload.JsonPayloads;
public class LibraryTest {
	
	@Test
	
	void addPlaceApi() {
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
	String  response=	 given().log().all().body(JsonPayloads.Addbook("nik4", "AVY", "4575", "Agastya4"))
		 .when().post("Library/Addbook.php")
		 .then().log().all().assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)"
		 		+ "").extract().response().asString()
		 ;
	
	JsonPath js= new JsonPath(response);
	String Msg=js.getString("Msg");
	System.out.println(Msg);
	String ID=js.getString("ID");
	System.out.println(ID);
	
	
	
	
	String  Getresponse=	 given().log().all().queryParam("ID", ID)
			 .when().get("Library/GetBook.php")
			 .then().log().all().assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)"
			 		+ "").extract().response().asString()
			 ;
		
		JsonPath js2= new JsonPath(Getresponse);
		String book_name=js2.getString("[0].book_name");
		System.out.println(book_name);
		String isbn=js2.getString("[0].isbn");
		System.out.println(isbn);
		String aisle=js2.getString("[0].aisle");
		System.out.println(aisle);
		String author=js2.getString("[0].author");
		System.out.println(author);
		
		
		String  GetresponseAuther=	 given().log().all().queryParam("AuthorName", author)
				 .when().get("https://rahulshettyacademy.com/Library/GetBook.php")
				 .then().log().all().assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)"
				 		+ "").extract().response().asString()
				 ;
			
			JsonPath js3= new JsonPath(Getresponse);
			String book_name3=js3.getString("[0].book_name");
			System.out.println(book_name3);
			String isbn3=js3.getString("[0].isbn");
			System.out.println(isbn3);
			String aisle3=js3.getString("[0].aisle");
			System.out.println(aisle3);
			//String author=js3.getString("[0].author");
			//System.out.println(author);
	/*
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
	Assert.assertEquals("Frontline house", name);
	*/
}}
