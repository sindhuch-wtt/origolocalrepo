package tech.walkingtree.ofl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class GetValues
{
	public String[] ReadfromExcel() throws Exception
	 {
		 File f= new File("E:\\ExcelFiles\\UploadExcelfile.xlsx");
			FileInputStream fi= new FileInputStream(f); //Read permission
			Workbook wb= WorkbookFactory.create(fi);    //Access Excel File
			Sheet sh=wb.getSheet("Sheet1");
			int rowCount= sh.getPhysicalNumberOfRows();	//Get data from 2nd row onwards in Sheet1
			int colCount =sh.getRow(0).getLastCellNum();
			//System.out.println("Total number of Rows are "+ rowCount);
			//System.out.println("Total number of Columns are "+colCount);
			String excel_Array[] = {};
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
			        // convert array to Array list
			        List<String>list = new ArrayList<String>(Arrays.asList(excel_Array)); 		        
			        // Add the new element 
			        list.add(collectiondata); 		 
			        // Convert the Array list back to array 
			        excel_Array = list.toArray(excel_Array); 		 
			      } 					
		     }
			// display the updated array 
	        System.out.println("\nThe data read from Excel is stored in the Array as " + ":" + Arrays.toString(excel_Array)); 
	        //System.out.println(excel_Array[0]);
			Thread.sleep(5000);
			fi.close();
			return excel_Array;	   	
	 }
	
}
