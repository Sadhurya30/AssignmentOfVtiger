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
import com.comcast.crm.objectrepositoryutility.CampaignsInfoPage;
import com.comcast.crm.objectrepositoryutility.CampaignsListPage;
import com.comcast.crm.objectrepositoryutility.CampaignsPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewCampaignPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOpportunityPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OpportunitiesInfoPage;
import com.comcast.crm.objectrepositoryutility.OpportunityPage;
import com.comcast.crm.objectrepositoryutility.OrganisationListPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.comcast.crm.objectrepositoryutility.ProductsListPage;
import com.comcast.crm.objectrepositoryutility.ProductsPage;
import com.comcast.crm.objectrepositoryutility.morePage;

public class CreateNewOpportunityInCRM {

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

		//click on campaigns link
		morePage mp=new morePage(driver);
		mp.getCampaignsLink().click();
		
		//click on create Campaign lkp image
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getCreateCampaignBtn().click();
		
		//enter the campaign name
		String campaignName = eLib.getDataFromExcel("Campaign", 1, 2);
		CreatingNewCampaignPage cncp = new CreatingNewCampaignPage(driver);
		cncp.getCampaignNameField().sendKeys(campaignName);
		
		//select the product which has been created
		cncp.getAddProductBtn().click();
		
		//moving the control to productlistpage
		wLib.childtab(driver);
		
		String productName = eLib.getDataFromExcel("Vendor", 1, 3);
		ProductsListPage plp = new ProductsListPage(driver);
		plp.getSearchFieldinProducts().sendKeys(productName);
		plp.getSearchNowBtn().click();
		
		plp.getSelectProductFromList().click();
		
		wLib.parenttab(driver);
		
		cncp.getSaveBtn().click();
		
		//verifying whether the campaign has been created or not
		CampaignsInfoPage cip = new CampaignsInfoPage(driver);
		String headerMessage = cip.getHeaderMsg().getText();
		if(headerMessage.contains(campaignName))
		{
			System.out.println(campaignName + " campaign is created");
		}
		else {
			System.out.println(campaignName + " campaign is not created");
		}
		
		//click on opportunities link
		hp.getOpportunitiesLink().click();
		
		//click on create opportunity lkp image
		OpportunityPage op = new OpportunityPage(driver);
		op.getCreateOpportunityBtn().click();
		
		//enter the opportunity name
		String opportunityName = eLib.getDataFromExcel("Campaign", 1, 3) ;
		CreatingNewOpportunityPage cnop =  new CreatingNewOpportunityPage(driver);
		cnop.getOpportunityNameField().sendKeys(opportunityName);
		
		//click the organisation lkup image
		CreatingNewOpportunityPage crop = new CreatingNewOpportunityPage(driver);
		crop.getAddOrganisationNameBtn().click();
		
		wLib.childtab(driver);
		
		//select the organization name
		String orgName = eLib.getDataFromExcel("Campaign", 1, 4);
		OrganisationListPage olp = new OrganisationListPage(driver);
		olp.getSearcField().sendKeys(orgName);
		
		olp.getSearchNowBtn().click();
		olp.getSelectOrgNameFromList().click();
		
		wLib.parenttab(driver);
		
		//select the campaign name and save
		crop.getAddCampaigninOpportunityBtn().click();
		
		wLib.childtab(driver);
		
		CampaignsListPage clp = new CampaignsListPage(driver);
		clp.getSearcFieldInCampaigns().sendKeys(campaignName);
		clp.getSearchNowBtn().click();
		
		clp.getSelectCampaignFromList().click();
		
		wLib.parenttab(driver);
		
		crop.getSaveBtn().click();
		
		//check whether the opportunity is created or not
		OpportunitiesInfoPage oip = new OpportunitiesInfoPage(driver);
		String headerMsgInOpportunity =  oip.getHeaderMsg().getText();
		if(headerMsgInOpportunity.contains(opportunityName))
		{
			System.out.println(opportunityName + " opportunity is created == PASS");
		}
		else {
			System.out.println(opportunityName + " opportunity is not created == FAIL");
		}
		
		//logout of the app
		hp.logout();
		
		driver.quit();
	}

}
