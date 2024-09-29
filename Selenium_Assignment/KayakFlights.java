package Selenium_Assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class KayakFlights {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


        WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		
		driver.get("https://www.kayak.co.in/flights");
		
		driver.findElement(By.className("c_neb-item-icon")).click();
		
		driver.findElement(By.xpath("(//div[@class='pM26']//input)[1]")).sendKeys("New delhi");
		
		driver.findElement(By.xpath("(//div[@class='pM26']//input)[2]")).sendKeys("Singapore");
		
		//driver.findElement(By.xpath("(//div[@class='SVL4-button-content'])[1]")).click();
		
		//driver.findElement(By.xpath("(//div[@class='JONo-button'])[1]")).click();
		
		//driver.findElement(By.xpath("//td[@class='vn3g vn3g-t-unselected vn3g-r-full']/div[contains(@aria-label,'15 September')]")).click();
		
		driver.findElement(By.xpath("//span[@class='gI3K-submit']")).click();
		
		
	}

}
