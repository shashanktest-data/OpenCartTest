package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="testdata")
	public String[][] getData()
	{
		
		String path=".\\testdata\\data1.xlsx";// taking xlsx file
		ExcelUtility eu=new ExcelUtility(path); //creating an objectof excel utility
		int totalRows=eu.getRowCount("sheet1");
		int totalColumns=eu.getCellCount("sheet1",1);
		String logindata[][]=new String[totalRows][totalColumns];//create 2d array with same size
		//read data from excel and store in 2D array
		for(int i=1;i<=totalRows;i++)
		{
			for(int j=0;j<totalColumns;j++)
			{
				logindata[i-1][j]	=eu.getCellData("sheet1", i, j);
			}
		}
		return logindata; //return 2d array
		
	}

}
