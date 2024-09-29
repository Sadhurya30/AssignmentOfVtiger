package Selenium_Assignment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Olympics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		
		driver.get("https://olympics.com/en/olympic-games/tokyo-2020/medals");
		
		List<WebElement> a =driver.findElements(By.xpath("//div[@data-row-id='country-medal-row-1']//span[@data-cy='country-name']/..//following-sibling::div[@title='Gold']//span//span"));
		
		
		
		List<WebElement> b = driver.findElements(By.xpath("//div[@data-row-id='country-medal-row-1']//span[@data-cy='country-name']/..//following-sibling::div[@title='Silver']//span//span"));
		
		
		List<WebElement> c = driver.findElements(By.xpath("//div[@data-row-id='country-medal-row-1']//span[@data-cy='country-name']/..//following-sibling::div[@title='Bronze']//span//span"));
		
		
		
	}
		
		
		
		

}
