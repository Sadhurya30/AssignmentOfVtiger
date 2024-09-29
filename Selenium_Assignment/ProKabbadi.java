package Selenium_Assignment;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProKabbadi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.manage().window().maximize();
		
		driver.get("https://www.prokabaddi.com/");
		
		List<WebElement> teamName = driver.findElements(By.xpath("//div[@class='row-head']//div[@class='team-name']"));
		
		List<WebElement> matches = driver.findElements(By.xpath("//div[@class='table-data matches-play']//p[@class='count']"));
		
		List<WebElement> won = driver.findElements(By.xpath("//div[@class='table-data matches-won']//p[@class='count']"));
		
		List<WebElement> lost = driver.findElements(By.xpath("//div[@class='table-data matches-lost']/p[@class='count']"));
		
		List<WebElement> tie =  driver.findElements(By.xpath("//div[@class='table-data matches-draw']/p[@class='count']"));
		
		List<WebElement> form = driver.findElements(By.xpath("//div[@class='table-data form']//ul[@class='form-listing']"));
		
		
		List<WebElement> totalPoints = driver.findElements(By.xpath("//div[@class='table-data points']/p[@class='count']"));
		
		String s = "Haryana Steelers";
		//Scanner sc = new Scanner(System.in);
		//System.out.println("Enter the team name");
		//String team =  sc.next();
		
		
		for(int i=0,j=0,k=0,l=0,m=0,n=0,p=0;i<teamName.size();i++)
		{
			if(s.equalsIgnoreCase(teamName.get(i).getText()))
			{	
				
			    System.out.println("the team name is "+teamName.get(i).getText() + " "
			    		         + " Total matches played " + matches.get(j).getText()
				               	 + " Matches won " + won.get(k).getText()
				               	 + " Matches Lost" + lost.get(l).getText() 
				               	 + " Matches draw "+tie.get(m).getText()
					             + " TotalPoints " + totalPoints.get(n).getText() 
			                     + " The form " + form.get(p).getText());
			}
	}
	}
}
