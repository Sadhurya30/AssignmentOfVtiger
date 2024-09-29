package practice.contacttest;


	import java.io.FileInputStream;
	import java.io.IOException;
	import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
	import java.util.Random;
import java.util.Set;

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

	public class CreatecontactWithOrgTest {

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
			Row row = sh.getRow(7);
			String orgName = row.getCell(2).toString() + randomInt;
			String contactLastName =  row.getCell(3).getStringCellValue();
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
			driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
			
			//verify header message expected result
			String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(headerInfo.contains(orgName))
			{
				System.out.println(orgName + "Header is verified==PASS");
			}
			else {
				System.out.println(orgName + "Header is not verified==FAIL");
			}
			
			//step 5 :  navigate to contact module
			driver.findElement(By.linkText("Contacts")).click();
			
			//step 6: click on create contact
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			
			//step 7: enter all the details and create new contact
			//driver.findElement(By.xpath("//input[@name=\'accountname\']")).sendKeys(orgName);
			driver.findElement(By.name("lastname")).sendKeys(contactLastName);
			
			driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
			
			//switch to child window
			
			Set<String> set = driver.getWindowHandles();
			Iterator<String> it = set.iterator();
			
			while(it.hasNext())
			{
				String windowId = it.next();
				driver.switchTo().window(windowId);
				String actUrl = driver.getCurrentUrl();
				if(actUrl.contains("module=Accounts"))
				{
					break;
				}
			}
			
			driver.findElement(By.name("search_text")).sendKeys(orgName);
			driver.findElement(By.name("search")).click();
			//dynamic xpath for opening the organisation in the create contact page
			driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
			
			//switch to parent window
			
			Set<String> set1 = driver.getWindowHandles();
			Iterator<String> it1 = set.iterator();
			
			while(it1.hasNext())
			{
				String windowId = it1.next();
				driver.switchTo().window(windowId);
				String actUrl = driver.getCurrentUrl();
				if(actUrl.contains("Contacts&action"))
				{
					break;
				}
			}
			
			driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
			
			headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(headerInfo.contains(contactLastName))
			{
				System.out.println(contactLastName + "header is verified==PASS");
			}
			else {
				System.out.println(contactLastName + "header is not verified==FAIL");
			}
			
			//verify header phonenumber  info expected result
			String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
			if(actOrgName.trim().equals(orgName))
			{
				System.out.println(orgName + " information is created==PASS");
			}
			else {
				System.out.println(orgName + "information is created==FAIL");
			}
			
		
			
			//verify header orgname info expected result
			/*
			 * String actLastName =
			 * driver.findElement(By.id("dtlview_Last Name")).getText();
			 * if(actLastName.equals(lastName)) { System.out.println(lastName +
			 * "is created==PASS"); } else { System.out.println(lastName +
			 * "is created==FAIL"); }
			 */
			//Contacts&action - parent window
			//Accounts&action- child window
			driver.quit();
			
		}
	}