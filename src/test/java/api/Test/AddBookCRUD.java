
package api.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.Methods.Book_CRUD;
import api.Methods.UserEndPoints;
import api.Payloads.User;
import api.Payloads.addBookPayload;
import io.restassured.response.Response;

public class AddBookCRUD {

	static Faker faker;
	static addBookPayload addBook;
	String bookId;
	public static Logger logger; // for logs

	@BeforeClass
	public static void setUpData() {
		faker = new Faker();
		addBook = new addBookPayload();
		addBook.setName(faker.name().firstName());
		addBook.setIsbn(faker.regexify("[a-z]{3}"));
		addBook.setAisle(faker.number().digits(4));
		addBook.setAuthor(faker.name().firstName());
		logger = LogManager.getLogger(AddBookCRUD.class.getName());

		logger.debug("debugging.....");
	}

	@Test(priority = 1)

	public void addBook() {
		logger.info("************* Adding a book************");
		Response responce = Book_CRUD.addBook(addBook);
		responce.then().log().all().extract().asString();

		String Message = responce.jsonPath().getString("Msg");
		//String bookId = responce.jsonPath().get("ID");
		//bookId = responce.jsonPath().get("ID");
		bookId = responce.jsonPath().getString("ID");   // ✅ IMPORTANT
		System.out.println("Generated Book ID: " + bookId);

		logger.info("************* Validating the User after creation************");
		Assert.assertEquals(responce.getStatusCode(), 200);
		Assert.assertEquals(Message, "successfully added");
		//Assert.assertEquals(bookId, this.addBook.getJob());
	}

	
	@Test(priority = 2)

	public void viewBookByID() {

		logger.info("************* View Book By ID************");
		
		Response responce = Book_CRUD.getbookById(bookId);
		responce.then().log().all().extract().asString();
		// System.out.println("Update Name Is>>>" + responce.jsonPath().get("Name"));

		logger.info("************* Validating the book info after add book sucessfully by Id************");
		
		
		String book_name = responce.jsonPath().getString("[0].book_name");
		String isbn = responce.jsonPath().getString("[0].isbn");
		String aisle = responce.jsonPath().getString("[0].aisle");
		String author = responce.jsonPath().getString("[0].author");
		Assert.assertEquals(responce.getStatusCode(), 200);
		Assert.assertEquals(book_name, addBook.getName());
		Assert.assertEquals(isbn, addBook.getIsbn());
		Assert.assertEquals(aisle, addBook.getAisle());
		Assert.assertEquals(author, addBook.getAuthor());
		
		
	}
/*
	// @Test(priority = 3)

	public void getUser() {
		logger.info("************* Validating by view User info************");

		Response responce = UserEndPoints.getUser(userid);
		responce.then().log().all().extract().asString();
		String id = responce.jsonPath().get("data.id");
		Assert.assertEquals(id, userid);
	}
	*/
}