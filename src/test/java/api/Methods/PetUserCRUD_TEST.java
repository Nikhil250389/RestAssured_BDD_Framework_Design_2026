package api.Methods;

import static io.restassured.RestAssured.given;

import api.Payloads.Pet;
import api.Payloads.PetUserLoginPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetUserCRUD_TEST {

	public static Response petUserLoginMethods(PetUserLoginPayload payload)

	{

		Response res = given().contentType(ContentType.JSON).body(payload)

				.when().post(api.EndPoints.Routes.userLogin);

		return res;

	}
	public static Response petUserCreate(Pet payload)

	{

		Response res = given().contentType(ContentType.JSON).body(payload)

				.when().post(api.EndPoints.Routes.createPetURL);

		return res;

	}public static Response viewById(int id) {

	    Response res = given()
	            .contentType(ContentType.JSON)
	            .pathParam("ID", id)
	    .when()
	            .get(api.EndPoints.Routes.petViewById);

	    return res;
	}
}