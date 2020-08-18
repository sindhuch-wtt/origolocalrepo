package tech.walkingtree.ofl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Usercreation 
{
	public static void main(String[] args) throws Exception
	{
		RemoteWebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://ofwebqa.walkingtree.tech/#/signin");
		Thread.sleep(10000);
		
        File f= new File("C:\\Users\\Walkingtree\\Project\\Login\\LoginCredentials.xlsx");
		
		//Read permission
		FileInputStream fi= new FileInputStream(f);
		
		//Access Excel File
		Workbook wb= WorkbookFactory.create(fi);
		
		Sheet sh=wb.getSheet("Sheet1");
		
		int nour= sh.getPhysicalNumberOfRows();
		
		//Get data from 2nd row onwards in Sheet1
		
		for(int i=1;i<nour;i++)
		{
			driver.findElementByXPath("//input[@placeholder='User ID']").sendKeys("username");
		//driver.findElementByXPath("//input[@placeholder='User ID']").sendKeys(sh.getRow(i).getCell(0).getStringCellValue());
		
		
		//write permission
				FileOutputStream fo= new FileOutputStream(f);
				wb.write(fo);
				fi.close();
				fo.close();
		}
		
	}

}
