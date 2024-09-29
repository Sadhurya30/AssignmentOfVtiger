package Selenium_Assignment;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class ICCCricket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		
		driver.get("https://www.icc-cricket.com/rankings");
		
	    WebElement TestTeamRankingSA = driver.findElement(By.xpath("//span[text()='South Africa']//ancestor::div[@class='si-table-row']"
	    		+ "//span[text()='104']"));
	    System.out.println("the test team Ranking of South Africa is " + TestTeamRankingSA.getText());
	    
	    WebElement ODITeamRankingNZ = driver.findElement(By.xpath("//span[text()='New Zealand']//ancestor::div[@class='si-table-row']"
	    		+ "//span[text()='101']"));
	    System.out.println("The odi team ranking of NewZealand is " + ODITeamRankingNZ.getText());
	    
	    
	}

}
