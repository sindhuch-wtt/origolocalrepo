package tech.walkingtree.ofl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tech.walkingtree.ofl.Login;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellUtil;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.graphbuilder.math.func.LgFunction;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadUploadedExcel 
{

	public static void main(String[] args) throws Exception
	{
		RemoteWebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://ofwebqa.walkingtree.tech/#/signin");
		Thread.sleep(10000);
		driver.findElementByXPath("//input[@placeholder='User ID']").click();
		
		File f= new File("C:\\Users\\Walkingtree\\Project\\Login\\UploadExcelfile.xlsx");
		FileInputStream fi= new FileInputStream(f); //Read permission
		Workbook wb= WorkbookFactory.create(fi);    //Access Excel File
		Sheet sh=wb.getSheet("Sheet1");
		int rowCount= sh.getPhysicalNumberOfRows();	//Get data from 2nd row onwards in Sheet1
		int colCount =sh.getRow(0).getLastCellNum();
		System.out.println("Total number of Rows are "+ rowCount);
		System.out.println("Total number of Columns are "+colCount);
		String odd_Array[] = {};
		for (int j = 0; j < colCount; j++) 	    
		 {
			for(int i=1;i<rowCount;i++)
	    	  {
				Row row = sh.getRow(i);
				Cell cell = row.getCell(j);			
				Cell dataij= sh.getRow(i).getCell(j);
				//System.out.println("Data in row "+i+" Column "+j+" is "+dataij);				
				DataFormatter formatter = new DataFormatter();
				String collectiondata = formatter.formatCellValue(dataij);				
		        // convert array to Arraylist
		        List<String>oddlist = new ArrayList<String>(Arrays.asList(odd_Array)); 		        
		        // Add the new element 
		        oddlist.add(collectiondata); 		 
		        // Convert the Arraylist back to array 
		        odd_Array = oddlist.toArray(odd_Array); 		 
		      } 					
	     }
		// display the updated array 
        System.out.println("\nArray after adding element " + ":" + Arrays.toString(odd_Array)); 
        String E_EMIAmount= odd_Array[0]; 
		Thread.sleep(5000);
		fi.close();	   	
		
		
	}
}
