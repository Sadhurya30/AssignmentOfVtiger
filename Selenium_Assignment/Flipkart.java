package Selenium_Assignment;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class Flipkart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.manage().window().maximize();
		
		driver.get("https://www.flipkart.com/");
		////div[@class='DOjaWF gdgoEp']//div[@class='cPHDOP col-12-12']//div[@class='yKfJKb row']//div[@class='hl05eU']//div[@class='Nx9bqj _4b5DiR']
		
		WebElement search =driver.findElement(By.xpath("//input[@class='Pke_EE']"));
		search.sendKeys("iphone price less than 70000");
		search.submit();
		
		List<WebElement> listOfIphonesNames = driver.findElements(By.xpath("//div[@class='KzDlHZ']"));
		for(WebElement ele : listOfIphonesNames)
		{
			System.out.println(ele.getText());
		}
		
		}

}
