package tech.walkingtree.ofl;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataImportedonWebapp {

	public static void main(String[] args) throws Exception
	{
		RemoteWebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("http://ofwebqa.walkingtree.tech/#/signin");
		Thread.sleep(10000);
	   driver.findElement(By.xpath("//div[@class='p-grid']")).click();
	   driver.findElement(By.xpath("//div[text()='Receipts']")).click();
	   String EMIAmount= driver.findElement(By.xpath("//p[text()='EMI Amount:']")).getText();
	   System.out.println(EMIAmount);
	   //String EMIAmount= driver.findElement(By.xpath("//p[text()='EMI Amount:']")).getText();
	   System.out.println(EMIAmount);
	   

	}

}
