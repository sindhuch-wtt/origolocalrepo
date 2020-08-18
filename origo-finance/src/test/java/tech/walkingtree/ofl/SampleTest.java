package tech.walkingtree.ofl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTest 
{
	public static void main(String[] args) throws Exception
	{
		RemoteWebDriver driver;
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
			System.out.println(nour);
			System.out.println(nouc);
	    for(int i=1;i<nour;i++)
		 {
	    	
	    	String x= sh.getRow(i).getCell(0).getStringCellValue();
	    	System.out.println(x);
			driver.findElementByXPath("//input[@placeholder='User ID']").sendKeys(sh.getRow(i).getCell(0).getStringCellValue());
			driver.findElementByXPath("//input[@type='password']").sendKeys(sh.getRow(i).getCell(1).getStringCellValue());					
			driver.findElementByXPath("//span[text()='Sign In']/parent::button").click();
			Thread.sleep(5000);
			fi.close();
	    	
			
		}
		
	}

}
