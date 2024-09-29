package practice.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrgWithPhoneNumberTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
        FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\Desktop\\dataforvtiger\\commondataforvtiger.properties");
		Properties pobj = new Properties();
		pobj.load(fis);
		
		String BROWSER = pobj.getProperty("browser");
		String URL = pobj.getProperty("url") ;
		String USERNAME = pobj.getProperty("username");
		String PASSWORD = pobj.getProperty("password");
		
		//generate the random number
		Random random = new Random();
		int randomInt = random.nextInt();
		
		//Read testScriptdata from excel file
		FileInputStream fis1 = new FileInputStream("C:\\Users\\Admin\\Desktop\\dataforvtiger\\testscriptdataforvtiger.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("org");
		Row row = sh.getRow(7);
		String orgName = row.getCell(2).toString() + randomInt;
		String phoneNumber = row.getCell(3).getStringCellValue(); 
		//while storing the numeric data in excel convert it to string dont convert it in code
		wb.close();
		
	   WebDriver driver = null;
		if(BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver =  new FirefoxDriver();
		}
		else if(BROWSER.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		else {
			driver = new ChromeDriver();
		}
		
		
		//step1: login to application
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		driver.manage().window().maximize();
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//step 2:  navigate to organisations module
		driver.findElement(By.linkText("Organizations")).click();
		
		//step 3: click on create organisation
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//step 4: enter all the details and create new organisation
		//driver.findElement(By.xpath("//input[@name=\'accountname\']")).sendKeys(orgName);
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		driver.findElement(By.id("phone")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		//verify header msg expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerInfo.contains(orgName))
		{
			System.out.println(orgName + "header is verified==PASS");
		}
		else {
			System.out.println(orgName + "header is not verified==FAIL");
		}
		
		//verify header phonenumber  info expected result
		String actPhoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
		if(actPhoneNumber.equals(phoneNumber))
		{
			System.out.println(phoneNumber + " information is created==PASS");
		}
		else {
			System.out.println(phoneNumber + "information is created==FAIL");
		}
		
		//step 5 :logout
		/*
		 * Actions act = new Actions(driver); Thread.sleep(3000);
		 * act.moveToElement(driver.findElement(By.xpath(
		 * "//img[@src=\"themes/softed/images/user.PNG\"]"))).perform();
		 * driver.findElement(By.xpath("a[text()='Sign Out")).click();
		 */
		
		//driver.findElement(By.linkText("Sign Out")).click();
		//Thread.sleep(1000);	
		
		driver.quit();

	}

}
