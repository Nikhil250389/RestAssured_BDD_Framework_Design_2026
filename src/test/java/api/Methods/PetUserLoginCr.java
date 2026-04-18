package api.Methods;

import static io.restassured.RestAssured.given;

import api.Payloads.PetUserLoginPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetUserLoginCr {

	public static Response petUserLoginMethods(PetUserLoginPayload payload)

	{

		Response res = given().contentType(ContentType.JSON).body(payload)

				.when().post(api.EndPoints.Routes.userLogin);

		return res;

	}

}