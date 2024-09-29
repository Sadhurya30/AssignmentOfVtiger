package practice.contacttest;


import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDateTest  {

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
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(4);
		String lastName = row.getCell(2).toString() + randomInt;
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
		
		//step 2:  navigate to contacts module
		driver.findElement(By.linkText("Contacts")).click();
		
		//step 3: click on create contact
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//step 4: enter all the details and create new organisation
		
        Date dateObj = new Date();
		
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sim.format(dateObj);

		//SimpleDateFormat sim1 = new SimpleDateFormat("yyyy-mm-dd");
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String endDate= sim.format(cal.getTime());
		
		
		//driver.findElement(By.xpath("//input[@name=\'accountname\']")).sendKeys(orgName);
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
	
		
		//verify header orgname info expected result
		/*
		 * String actLastName =
		 * driver.findElement(By.id("dtlview_Last Name")).getText();
		 * if(actLastName.equals(lastName)) { System.out.println(lastName +
		 * " Information is verified==PASS"); } else { System.out.println(lastName +
		 * " Information is not verified==FAIL"); }
		 */
		
		//
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.equals(startDate))
		{
			System.out.println(startDate + " Information is verified==PASS");
		}
		else {
			System.out.println(startDate + " Information is not verified==FAIL");
		}
		
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEndDate.equals(endDate))
		{
			System.out.println(endDate + " Information is verified==PASS");
		}
		else {
			System.out.println(endDate + " Information is not verified==FAIL");
		}
		
		
		
		
		//Step 5: logout
		
		driver.quit();

	}

}



	