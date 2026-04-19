package api.Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {
		String path = System.getProperty("user.dir") + "//api.ExcelTestData//TestData.xlsx";
		XLUtility xl = new XLUtility(path);

		int rownum = xl.getRowCount("Sheet2");
		int colcount = xl.getCellCount("Sheet2", 1);

		String apidata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				apidata[i - 1][j] = xl.getCellData("Sheet2", i, j);
			}
		}

		return apidata;
	}
	@DataProvider(name = "PetData")
	public String[][] getAllPetData() throws IOException {
		String path = System.getProperty("user.dir") + "//api.ExcelTestData//TestData.xlsx";
		XLUtility xl = new XLUtility(path);

		int rownum = xl.getRowCount("Sheet3");
		int colcount = xl.getCellCount("Sheet3", 1);

		String apidata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				apidata[i - 1][j] = xl.getCellData("Sheet3", i, j);
			}
		}

		return apidata;
	}

	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException {
		String path = System.getProperty("user.dir") + "//api.ExcelTestData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);

		int rownum = xl.getRowCount("Sheet2");

		String apidata[] = new String[rownum];

		for (int i = 1; i <= rownum; i++) {
			apidata[i - 1] = xl.getCellData("Sheet2", i, 1);

		}

		return apidata;
	}

	@DataProvider(name = "UserPassword")
	public String[] getUserPassword() throws IOException {
		String path = System.getProperty("user.dir") + "//api.ExcelTestData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);

		int rownum = xl.getRowCount("Sheet2");

		String apidata[] = new String[rownum];

		for (int i = 1; i <= rownum; i++) {
			apidata[i - 1] = xl.getCellData("Sheet2", i, 1);

		}

		return apidata;
	}

}
