
package api.Test;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.Methods.PetUserCRUD_TEST;
import api.Methods.PetUserLoginCr;
import api.Payloads.Category;
import api.Payloads.Pet;
import api.Payloads.PetUserLoginPayload;
import api.Payloads.Tag;
import api.Utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	private PetUserLoginPayload userLoginPayload;
	int petId;
	Pet pet;
	private Logger logger = LogManager.getLogger(DataDrivenTest.class);

	@BeforeClass
	public void setUpData() {

		// userLoginPayload = new PetUserLoginPayload();
		// userLoginPayload.setEmail(api.EndPoints.Routes.email);
		// userLoginPayload.setPassword(api.EndPoints.Routes.password);

		logger.info("Test data initialized successfully");
	}

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void userShouldLoginSuccessfully(String username, String password) {
		logger.info("******** User Should Login Successfully ********");

		// Act
		PetUserLoginCr userLoginMd = new PetUserLoginCr();
		userLoginPayload = new PetUserLoginPayload();
		userLoginPayload.setEmail(username);
		userLoginPayload.setPassword(password);

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

	@Test(dependsOnMethods = "userShouldLoginSuccessfully", priority = 2, dataProvider = "PetData", dataProviderClass = DataProviders.class)
	public void userShouldAbleToCreatePetSuccessfully(String setId, String SetName, String setStatus,
			String setCategoryId, String setCategoryName, String setTagId, String setTagName) {
		{
		}
		logger.info("******** User Should Able To Create Pet with ExcelData Successfully ********");
		int id = Integer.parseInt(setId);
		int categoryId = Integer.parseInt(setCategoryId);
		int tagId = Integer.parseInt(setTagId);

		pet = new Pet();
		pet.setId(id);
		pet.setName(SetName);
		pet.setStatus(setStatus);

//Category
		Category category = new Category();
		category.setId(categoryId);
		category.setName(setCategoryName);
		pet.setCategory(category);
//Photo URLs
		pet.setPhotoUrls(Arrays.asList("http://image1.com"));
//Tags
		Tag tag = new Tag();
		tag.setId(tagId);
		tag.setName(setTagName);
		pet.setTags(Arrays.asList(tag));

		pet.setTags(Arrays.asList(tag));
		// Act
		Response response = PetUserCRUD_TEST.petUserCreate(pet);

		// Assert
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

		petId = response.jsonPath().getInt("id");

		// Category
		int categoryID = response.jsonPath().getInt("category.id");
		String categoryName = response.jsonPath().getString("category.name");

		// Basic fields
		String petName = response.jsonPath().getString("name");
		String status = response.jsonPath().getString("status");

		// photoUrls (array)
		List<String> photoUrls = response.jsonPath().getList("photoUrls");

		// tags (array of objects)
		int tagID = response.jsonPath().getInt("tags[0].id");
		String tagName = response.jsonPath().getString("tags[0].name");

		// ID
		Assert.assertEquals(petId, pet.getId());

		// Category
		Assert.assertEquals(categoryID, pet.getCategory().getId());
		Assert.assertEquals(categoryName, pet.getCategory().getName());

		// Basic fields
		Assert.assertEquals(petName, pet.getName());
		Assert.assertEquals(status, pet.getStatus());

		// photoUrls
		Assert.assertEquals(photoUrls, pet.getPhotoUrls());

		// Tags
		Assert.assertEquals(tagID, pet.getTags().get(0).getId());
		Assert.assertEquals(tagName, pet.getTags().get(0).getName());
	}
	/*
	 * @Test(dependsOnMethods = "userShouldAbleToCreatePetSuccessfully", priority =
	 * 3) public void userShouldViewPetByID() {
	 * logger.info("******** User Should View Pet By ID ********");
	 * 
	 * Response response = PetUserCRUD_TEST.viewById(petId);
	 * 
	 * response.then().log().all(); Assert.assertEquals(response.getStatusCode(),
	 * 200);
	 * 
	 * petId = response.jsonPath().getInt("id");
	 * 
	 * // Category int viewCategoryId = response.jsonPath().getInt("category.id");
	 * String viewCategoryName = response.jsonPath().getString("category.name");
	 * 
	 * // Basic fields String viewPetName = response.jsonPath().getString("name");
	 * String viewStatus = response.jsonPath().getString("status");
	 * 
	 * // photoUrls (array) List<String> viewPhotoUrls =
	 * response.jsonPath().getList("photoUrls");
	 * 
	 * // tags (array of objects) int viewTagId =
	 * response.jsonPath().getInt("tags[0].id"); String viewTagName =
	 * response.jsonPath().getString("tags[0].name");
	 * 
	 * // ID Assert.assertEquals(petId, pet.getId());
	 * 
	 * // Category Assert.assertEquals(viewCategoryId, pet.getCategory().getId());
	 * Assert.assertEquals(viewCategoryName, pet.getCategory().getName());
	 * 
	 * // Basic fields Assert.assertEquals(viewPetName, pet.getName());
	 * Assert.assertEquals(viewStatus, pet.getStatus());
	 * 
	 * // photoUrls Assert.assertEquals(viewPhotoUrls, pet.getPhotoUrls());
	 * 
	 * // Tags Assert.assertEquals(viewTagId, pet.getTags().get(0).getId());
	 * Assert.assertEquals(viewTagName, pet.getTags().get(0).getName()); }
	 * 
	 * @Test(dependsOnMethods = "userShouldAbleToCreatePetSuccessfully", priority =
	 * 4) public void userShouldNotAllowedToViewByInvlidID() {
	 * logger.info("******** user Should Not Allowed To View By Invlid ID ********"
	 * );
	 * 
	 * Response response = PetUserCRUD_TEST.viewById(987);
	 * 
	 * response.then().log().all(); Assert.assertEquals(response.getStatusCode(),
	 * 404);
	 * 
	 * int errorCode = response.jsonPath().getInt("code"); String errorType =
	 * response.jsonPath().getString("type");
	 * 
	 * String errorMessage = response.jsonPath().getString("message");
	 * Assert.assertEquals(errorCode, 1); Assert.assertEquals(errorType, "error");
	 * Assert.assertEquals(errorMessage, "Pet not found"); }
	 */
}