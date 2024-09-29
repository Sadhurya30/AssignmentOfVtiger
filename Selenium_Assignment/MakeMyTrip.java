package Selenium_Assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MakeMyTrip {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.manage().window().maximize();
		
		driver.get("https://www.makemytrip.com/?srsltid=AfmBOooTnFm8tzbIOeFmBOcENawg4q7FZea27IA0iU7KVE2fevKGrnuu");
		
		driver.findElement(By.xpath("//span[@class='commonModal__close']")).click();
		
		driver.findElement(By.xpath("//label[@for='departure']//span[@class='lbl_input appendBottom10']")).click();
		
		driver.findElement(By.xpath("(//div[@class='dateInnerCell'])[19]")).click();
		
	}

}
