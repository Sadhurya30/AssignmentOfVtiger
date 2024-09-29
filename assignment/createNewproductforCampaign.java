package assignment;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewProductPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewVendorPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.ProductsPage;
import com.comcast.crm.objectrepositoryutility.VendorInfoPage;
import com.comcast.crm.objectrepositoryutility.VendorListPage;
import com.comcast.crm.objectrepositoryutility.VendorPage;
import com.comcast.crm.objectrepositoryutility.morePage;

public class createNewproductforCampaign {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub


		FileUtility fLib = new FileUtility();
		ExcelUtility eLib =  new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
	        
	    String BROWSER = fLib.getDataFromPropertiesFile("browser");
	    String URL = fLib.getDataFromPropertiesFile("url");
	    String USERNAME = fLib.getDataFromPropertiesFile("username");
	    String PASSWORD = fLib.getDataFromPropertiesFile("password");
		
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
		
		wLib.waitForPageToLoad(driver);
		driver.get(URL);
		
		//login to vtiger app
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//click on more link
		HomePage hp=new HomePage(driver);
		hp.getMoreLink().click();
		
		//click on vendors link
		morePage mp=new morePage(driver);
		mp.getVendor().click();
		
		//click on create vendor lkp image
		VendorPage vp=new VendorPage(driver);
		vp.getNewvendor().click();
		
		//enter vendor name and save
		String vendorName =  eLib.getDataFromExcel("Vendor", 1, 2);
		
		CreatingNewVendorPage cnvp = new CreatingNewVendorPage(driver);
		cnvp.getVendorName().sendKeys(vendorName);
		cnvp.getSaveBtn().click();
		
		//verify the vendor is created or not
		VendorInfoPage vip = new VendorInfoPage(driver);
		String headerMsg = vip.getHeaderMsginVendorInfoPage().getText();
		if(headerMsg.contains(vendorName))
		{
			System.out.println(vendorName + " is verified");
		}
		else {
			System.out.println(vendorName + " is not verified");
		}
		
		//click on products link
		hp.getProductsLink().click();
		
		//click  create product lkp image
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreatproductBtn().click();
		
		//enter the product name
		String productName = eLib.getDataFromExcel("Vendor", 1, 3);
		CreatingNewProductPage cpp = new CreatingNewProductPage(driver);
		cpp.getProductNameField().sendKeys(productName);
		
		//enter sales start date and sales end date
		String salesstartDate = jLib.getSystemDateYYYYDDMM();
		String salesendDate = jLib.getRequiredDateYYYYDDMM(10);
		
		cpp.getSalesEndDate().sendKeys(salesstartDate);
		cpp.getSalesEndDate().sendKeys(salesendDate);
	
		//select the created vendor
		cpp.getAddvendorButton().click();
		
		wLib.childtab(driver);
		
		VendorListPage vlp = new VendorListPage(driver);
		vlp.getSearchField().sendKeys(vendorName);
		vlp.getSearchNowBtn().click();
		
		//cpp.getSelectVendorNameFromList().click();
		driver.findElement(By.xpath("//a[text()='"+vendorName+"']")).click();
		//selectvendorNameFromList();
		
		wLib.parenttab(driver);
		
		//upload the product image
		cpp.getProductImage(driver).sendKeys("C:\\Users\\Admin\\Desktop\\dataforvtiger\\productImageReynolds.jpeg");
		
		//save 
        cpp.getSaveBtn().click();
        
        //check whether the product is created or not
        String headerMessageOfProd = cpp.getHeaderMsgOfProduct().getText();
        if(headerMessageOfProd.contains(productName))
        {
        	System.out.println(productName + " is created");
        }
        else {
        	System.out.println(productName + " is not created");
        }
        
        //logout
        hp.logout();
		
	    driver.quit();
		
	}

}
