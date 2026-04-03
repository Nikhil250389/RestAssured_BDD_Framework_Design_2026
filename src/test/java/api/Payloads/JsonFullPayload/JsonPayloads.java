package api.Payloads.JsonFullPayload;

public class JsonPayloads {

	public static String updatePlace(String place_id) {
		return "{\r\n" + "\"place_id\":\"" + place_id + "\",\r\n" + "\"address\":\"702 winter walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n" + "}";
	}

	public static String loanDirsbument(String bindingId, String TrxId)

	{
		return "{\r\n" + " \"bindingId\": \"" + bindingId + "\",\r\n" + "  \"partnerTrxId\": \"" + TrxId + "\",\r\n"
				+ "  \"disbursementAmount\": 500000,\r\n" + "  \"term\": 3,\r\n" + "  \"productType\": 1\r\n" + "}\r\n"
				+ "";

	}
	public static String nestedJson()

	{
		return "{\r\n"
				+ "\r\n"
				+ "\"dashboard\": {\r\n"
				+ "\r\n"
				+ "\"purchaseAmount\": 910,\r\n"
				+ "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "\"courses\": [\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Selenium Python\",\r\n"
				+ "\r\n"
				+ "\"price\": 50,\r\n"
				+ "\r\n"
				+ "\"copies\": 6\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"Cypress\",\r\n"
				+ "\r\n"
				+ "\"price\": 40,\r\n"
				+ "\r\n"
				+ "\"copies\": 4\r\n"
				+ "\r\n"
				+ "},\r\n"
				+ "\r\n"
				+ "{\r\n"
				+ "\r\n"
				+ "\"title\": \"RPA\",\r\n"
				+ "\r\n"
				+ "\"price\": 45,\r\n"
				+ "\r\n"
				+ "\"copies\": 10\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "]\r\n"
				+ "\r\n"
				+ "}\r\n"
				+ "\r\n"
				+ "";

	}
	public static String Addbook(String name, String isbn, String aisle, String auther)

	{
		return "{\r\n"
				+ "\"name\":\"Learn Appium Automation with "+name+"\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+aisle+"\",\r\n"
				+ "\"author\":\""+auther+"\"\r\n"
				+ "}\r\n"
				+ "";

	}
}
