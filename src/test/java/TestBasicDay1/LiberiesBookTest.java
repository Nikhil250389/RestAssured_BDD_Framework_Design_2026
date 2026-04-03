package TestBasicDay1;
import static io.restassured.RestAssured.*;
import static org.testng.Assert. *;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import api.Payloads.LiberiesPayload;
public class LiberiesBookTest {
	
	String ID;
	String autherName;
	@Test
	
	void Addbook() {
		LiberiesPayload lp= new LiberiesPayload();
		lp.setName("Nik Book 10");
		lp.setAisle("2875");
		lp.setAuthor("Nikhil Kharkhodia");
		lp.setIsbn("h7");
		RestAssured.baseURI= "https://rahulshettyacademy.com";
	String  response=	 given().log().all().body(lp)
		 .when().post("/Library/Addbook.php")
		 .then().log().all().assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)"
		 		+ "").extract().response().asString()
		 ;
	
	JsonPath js= new JsonPath(response);
	String Msg=js.getString("Msg");
	System.out.println(Msg);
	 ID=js.getString("ID");
	System.out.println(ID);
	}
	


@Test
	
	void viewBook() {
	String  response3=	 given().log().all().queryParam("ID", ID)
	 .when().get("/Library/GetBook.php")
	 .then().log().all().assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)"
	 		+ "").extract().response().asString()
	 ;
	JsonPath js2= new JsonPath(response3);
	String book_name=js2.getString("[0].book_name");
	autherName=js2.getString("[0].author");
	System.out.println(book_name);
	Assert.assertEquals("Nik Book 9", book_name);
}

@Test

void viewBookByAutherName() {
String  response4=	 given().log().all().queryParam("AuthorName", autherName)
 .when().get("/Library/GetBook.php")
 .then().log().all().assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)"
 		+ "").extract().response().asString()
 ;
JsonPath js4= new JsonPath(response4);

System.out.println(js4);
}

@Test




void deleteBook() {

    String response5 = given().log().all()
            .body("{\r\n" +
                    "    \"ID\": \"hyt2876\"\r\n" +
                    "}")
            .when()
            .delete("/Library/DeleteBook.php")
            .then().log().all()
            .assertThat().statusCode(201)
            .header("Server", "Apache/2.4.52 (Ubuntu)")
            .extract().response().asString();

    JsonPath js5 = new JsonPath(response5);

    System.out.println(js5.prettify());
}

}

