
package api.Test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import api.Methods.PetUserLoginCr;

import api.Payloads.PetUserLoginPayload;

import io.restassured.response.Response;

public class PetUserLogin {

 
    private PetUserLoginPayload userLoginPayload;
   
  
    private Logger logger = LogManager.getLogger(PetUserLogin.class);
  
    
    @BeforeClass
    public void setUpData() {

        userLoginPayload = new PetUserLoginPayload();
        userLoginPayload.setEmail(api.EndPoints.Routes.email);
        userLoginPayload.setPassword(api.EndPoints.Routes.password);


        logger.info("Test data initialized successfully");
    }

    @Test ( priority = 1)
    public void userShouldLoginSuccessfully() {
        logger.info("******** User Should Login Successfully ********");

        // Act
        PetUserLoginCr userLoginMd= new PetUserLoginCr();
       
        Response response = userLoginMd.petUserLoginMethods(userLoginPayload);
        

        // Assert
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        String token = response.jsonPath().getString("token");
        String id = response.jsonPath().getString("user._id");
        String firstName = response.jsonPath().getString("user.firstName");
        String lastName = response.jsonPath().getString("user.lastName");
        String email = response.jsonPath().getString("user.email");
        int version = response.jsonPath().getInt("user.__v");
        

        Assert.assertEquals(firstName, api.EndPoints.Routes.firstName);
        Assert.assertEquals(lastName, api.EndPoints.Routes.lastName);
        Assert.assertEquals(email, api.EndPoints.Routes.email);
    }
/*
    @Test(dependsOnMethods = "shouldAddBookSuccessfully", priority = 2)
    public void shouldFetchBookByIdSuccessfully() {
        logger.info("******** Fetching Book By ID ********");

        // Act
        Response response = Book_CRUD.getbookById(bookId);

        // Assert
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        String bookName = response.jsonPath().getString("[0].book_name");
        String isbn = response.jsonPath().getString("[0].isbn");
        String aisle = response.jsonPath().getString("[0].aisle");
        author = response.jsonPath().getString("[0].author");
      
        Assert.assertEquals(bookName, addBookPayload.getName());
        Assert.assertEquals(isbn, addBookPayload.getIsbn());
        Assert.assertEquals(aisle, addBookPayload.getAisle());
        Assert.assertEquals(author, addBookPayload.getAuthor());
    }
		
		
	

@Test(dependsOnMethods = "shouldFetchBookByIdSuccessfully", priority = 3)
public void shouldFetchBookByAutherNameSuccessfully() {
    logger.info("******** Fetching Book By Auther Name ********");

 // Act
 System.out.println("Author used: " + author);
 Response response = Book_CRUD.getBookByAutherName(author);

 // Assert
 response.then().log().all();
 Assert.assertEquals(response.getStatusCode(), 200);
 logger.info("******** Fetching Book By Author Name ********");


 // Get full list
 List<Map<String, String>> books = response.jsonPath().getList("");

 // Expected values (from payload)
 String expectedBookName = addBookPayload.getName();
 String expectedIsbn = addBookPayload.getIsbn();
 String expectedAisle = addBookPayload.getAisle();

 boolean bookFound = false;

 // Loop through response
 for (Map<String, String> book : books) {
     if (book.get("book_name").equals(expectedBookName) &&
         book.get("isbn").equals(expectedIsbn) &&
         book.get("aisle").equals(expectedAisle)) {

         bookFound = true;
         break;
     }
 }

 // Final assertion
 Assert.assertTrue(bookFound, "Added book not found in response");
}

@Test(dependsOnMethods = "shouldFetchBookByAutherNameSuccessfully",
	    priority = 4)
public void shouldDeleteBookSuccessfully() {

    logger.info("******** Deleting a Book ************");
    System.out.println("Delete Payload ID: " + bookId);
   
    deleteBookPayload payload = new deleteBookPayload();
    payload.setID(bookId);
    
    System.out.println("Request JSON: " + new Gson().toJson(payload));  // Act
    Response response = Book_CRUD.deleteBookById(payload);
   // response.then().log().all().extract().asString();

    String res = response.then().log().all().extract().asString();
    System.out.println("Raw response: " + res);
    Assert.assertEquals(response.getStatusCode(), 200);

   // Assert.assertEquals(response.getStatusCode(), 200, "Delete API failed");
	//String status = response.jsonPath().get("msg");
  //  Assert.assertEquals(status, "book is successfully deleted");

  //  Response response = Book_CRUD.deleteBookById(deleteBookPayload);

 // log for debugging
 //response.then().log().all();

 //String res = response.asString();

 // hard validation
 //Assert.assertTrue(res != null && !res.isEmpty(), "Empty response आया है");

 /*safe JSON parsing
 if (res.trim().startsWith("{")) {
     String msg = response.jsonPath().getString("msg");
     Assert.assertEquals(msg, "book is successfully deleted");
 } else {
     Assert.fail("Invalid (non-JSON) response: " + res);
 }
 */
}


