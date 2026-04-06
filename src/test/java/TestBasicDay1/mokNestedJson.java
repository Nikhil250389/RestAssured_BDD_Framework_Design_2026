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
public class mokNestedJson {
	
	@Test
	
	void nestedJsonTest() {
		// retrive total price
		JsonPath js= new JsonPath(JsonPayloads.nestedJson());
		String TotalpurchaseAmount= js.getString("dashboard.purchaseAmount");
		System.out.println("purchaseAmount is " + TotalpurchaseAmount);
		
		//retrive total course contain
		
		int courseSize= js.getInt("courses.size()");
		System.out.println("Total Course are " +courseSize);
		System.out.println("Total Course are " +courseSize);
		//3. Print Title of the first course
		
		String courseOnZeroIndux=js.getString("courses[0].title");
		System.out.println("Course On Zero Indux " +courseOnZeroIndux);
		

		//4. Print All course titles and their respective Prices
		
		for (int i=0;i<courseSize;i++)
		{
			
		//String  totalCourseName=	js.getString("courses" ["+i+"].title);
		String totalCourseName = js.getString("courses[" + i + "].title");
		String price = js.getString("courses[" + i + "].price");
		String copies = js.getString("courses[" + i + "].copies");
		System.out.println(totalCourseName);
		System.out.println(price);
		System.out.println(copies);
		}

		//5. Print no of copies sold by RPA Course
		
		for (int i=0;i<courseSize;i++)
		{
			

		String totalCourseName = js.getString("courses[" + i + "].title");
		if (totalCourseName.equalsIgnoreCase("RPA"))
		{
			
			String RPACopies = js.getString("courses[" + i + "].copies");
			System.out.println("Total RPA Copies= " + RPACopies);
			break;
		}
		
		}

		//6. Verify if Sum of all Course prices matches with Purchase Amount
}}
