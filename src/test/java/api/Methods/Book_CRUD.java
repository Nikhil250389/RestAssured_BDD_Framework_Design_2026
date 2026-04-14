package api.Methods;

import static io.restassured.RestAssured.given;

import api.Payloads.User;
import api.Payloads.addBookPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Book_CRUD {

	public static Response addBook(addBookPayload payload)

	{

		Response res = given().contentType(ContentType.JSON).body(payload)

				.when().post(Routes.addBook);

		return res;

	}

	public static Response getbookById(String id)

	{

		Response res = given().contentType(ContentType.JSON).queryParam("ID", id)

				.when().get(Routes.getBookById);

		return res;

	}

	public static Response getBookByAutherName(String authorName )

	{

		Response res = given().contentType(ContentType.JSON).
				pathParam("AuthorName", authorName)

				.when().get(Routes.getBookByAutherName);

		return res;

	}
	public static Response deleteBookById(String id )

	{

		Response res = given().contentType(ContentType.JSON).
				//pathParam("AuthorName", authorName)

				when().delete(Routes.deleteBook);

		return res;

	}
}
