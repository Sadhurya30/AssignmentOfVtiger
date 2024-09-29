package Selenium_Assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AutomationDemoWebSite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.manage().window().maximize();
		
		driver.get("https://demo.automationtesting.in/Register.html");
		
		driver.findElement(By.xpath("//div[@class='col-md-4 col-xs-4 col-sm-4']/input[@ng-model='FirstName']")).sendKeys("Sadhurya");
		
		driver.findElement(By.xpath("//div[@class='col-md-4 col-xs-4 col-sm-4']/input[@ng-model='LastName']")).sendKeys("P");
		
        driver.findElement(By.xpath("//textarea[@ng-model='Adress']")).sendKeys("No.18/41 1st cross street,Srinivasa nagar kolathur chennai - 99");
        
        driver.findElement(By.xpath("//input[@ng-model='EmailAdress']")).sendKeys("sadhuryapalani@gmail.com");
        
        driver.findElement(By.xpath("//input[@ng-model='Phone']")).sendKeys("8825958171");
        
        driver.findElement(By.xpath("//input[@value='FeMale']")).click();
        
        driver.findElement(By.id("checkbox1")).click();
        driver.findElement(By.id("checkbox2")).click();
       /* 
        driver.findElement(By.xpath("//div[@id='msdd']")).click();
       WebElement a=  driver.findElement(By.xpath("(//a[@class='ui-corner-all'])[8]"));
       
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
       wait.until(ExpectedConditions.elementToBeClickable(a));
       
       a.click();
       */
        
        WebElement skills = driver.findElement(By.id("Skills"));
        Select s = new Select(skills);
        s.selectByVisibleText("Java");
      /* 
       WebElement countries = driver.findElement(By.xpath("//span[@class='select2-selection select2-selection--single']"));
        Select s1 = new Select(countries);
        s1.selectByVisibleText("India");
      */
        
        //driver.findElement(By.className("select2-search__field")).click();
        //driver.findElement(By.xpath("//b[@role='presentation']")).click();
        
        WebElement countries=driver.findElement(By.id("countries"));
        Select s1 = new Select(countries);
        s1.selectByIndex(0);
        
        WebElement year = driver.findElement(By.id("yearbox"));
        Select s2 = new Select(year);
        s2.selectByVisibleText("1999");
        
        WebElement month = driver.findElement(By.xpath("//select[@ng-model='monthbox']"));
        Select s3 = new Select(month);
        s3.selectByVisibleText("August");
        
        
        WebElement date = driver.findElement(By.xpath("//select[@ng-model='daybox']"));
        Select s4 = new Select(date);
        s4.selectByVisibleText("30");
        
        driver.findElement(By.id("firstpassword")).sendKeys("Sadhu@123");
        
        driver.findElement(By.id("secondpassword")).sendKeys("Sadhu@123");
        
        driver.findElement(By.id("submitbtn")).click();     
	}

}
