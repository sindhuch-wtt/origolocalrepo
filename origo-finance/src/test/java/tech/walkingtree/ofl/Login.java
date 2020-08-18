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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login
{
	RemoteWebDriver driver;
	@BeforeClass
		 
	    public void setupClass() throws Exception 
	      {
		                 
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("http://ofwebqa.walkingtree.tech/#/signin");
			Thread.sleep(10000);
			driver.findElementByXPath("//input[@placeholder='User ID']").click();			
			File f= new File("C:\\Users\\Walkingtree\\Project\\Login\\LoginCredentials.xlsx");
			FileInputStream fi= new FileInputStream(f); //Read permission
			Workbook wb= WorkbookFactory.create(fi);    //Access Excel File
			Sheet sh=wb.getSheet("Sheet1");
			int nour= sh.getPhysicalNumberOfRows();	//Get data from 2nd row onwards in Sheet1
			int nouc =sh.getRow(0).getLastCellNum();
				//System.out.println(nour);
				//System.out.println(nouc);
		    for(int i=1;i<nour;i++)
			 {
		    	
		    	String x= sh.getRow(i).getCell(0).getStringCellValue();
		    	//System.out.println(x);
				driver.findElementByXPath("//input[@placeholder='User ID']").sendKeys(sh.getRow(i).getCell(0).getStringCellValue());
				driver.findElementByXPath("//input[@type='password']").sendKeys(sh.getRow(i).getCell(1).getStringCellValue());					
				driver.findElementByXPath("//span[text()='Sign In']//parent::button").click();
				Thread.sleep(5000);
				fi.close();
		   	}
		 }
	 
	 
		@Test
		public void  EMIAmount() throws Exception
		{		
		   driver.findElement(By.xpath("//div[text()='Receipts']")).click();
		   Thread.sleep(5000);
		   driver.findElement(By.xpath("//b[text()='10000']//preceding-sibling::p[text()='EMI Amount:']")).click();
		   Thread.sleep(5000);
		   String e= driver.findElement(By.xpath("//p[text()='EMI Amount:']//following-sibling::b[text()='10000']")).getText();
		   String e1= "₹";
		   String W_EMIAmount= e;
		   System.out.println(W_EMIAmount);
		   GetValues lg= new GetValues();
		   String[] excel_Array = lg.ReadfromExcel();
		   String E_EMIAmount= excel_Array[0];
		   System.out.println("The read EMI Amount from Excel sheet is "+E_EMIAmount);
		  
		   if(E_EMIAmount==W_EMIAmount)
		   {
			   System.out.println("EMI Amount imported from Excel sheet is passed");
		   }
		   else
		   {
			   System.out.println("Test failed");
		   }
		}
		   		   
		@Test
		public void  ODAmount() throws Exception
		{		
		   driver.findElement(By.xpath("//div[text()='Receipts']")).click();
		   Thread.sleep(5000);
		   driver.findElement(By.xpath("//span/p[contains(text(),'EMI Amount:')]/following-sibling::b")).click();
		   Thread.sleep(5000);
		   String W_ODAmount= driver.findElement(By.xpath("//div[@class='od-amount-cls']//p[contains(text(),'₹ ')]")).getText();
		   System.out.println(W_ODAmount);
		   	 
		  /* String E_ODAmount= excel_Array[1];
		   if(E_EMIAmount==W_EMIAmount)
		   {
			   System.out.println("EMI Amount imported from Excel sheet is passed");
		   }
		   else
		   {
			   System.out.println("Test failed");
		   }*/
		}   
		
		@Test
		public void  TotalLPIDue() throws Exception
		{		
		   driver.findElement(By.xpath("//div[text()='Receipts']")).click();
		   Thread.sleep(5000);
		   driver.findElement(By.xpath("//div[text()='Receipts']")).click();
		   Thread.sleep(5000);
		   driver.findElement(By.xpath("//b[text()='10000']//preceding-sibling::p[text()='EMI Amount:']")).click();
		   Thread.sleep(5000);
		   String W_TotalLPIDue= driver.findElement(By.xpath("//span/p[contains(text(),'Late Penalty')]/following-sibling::b")).getText();
		   System.out.println(W_TotalLPIDue);
		   	 
		  /* String E_ODAmount= excel_Array[1];
		   if(E_EMIAmount==W_EMIAmount)
		   {
			   System.out.println("EMI Amount imported from Excel sheet is passed");
		   }
		   else
		   {
			   System.out.println("Test failed");
		   }*/
		}   
		
	 @AfterMethod
	    public void teardown()
	 {
	        //driver.quit();
	        
	 }
	 
	

}
