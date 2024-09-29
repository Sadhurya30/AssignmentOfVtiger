package com.comcast.crm.generic.webdriverutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class WebDriverUtility {
	String parent;
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElementPresent(WebDriver driver, WebElement element)
	{
		WebDriverWait wait =  new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
    }
	public void childtab(WebDriver driver) {
		 parent = driver.getWindowHandle();
		 this.parent=parent;
		Set<String> child = driver.getWindowHandles();
		
		
		for(String a:child)
		{
			driver.switchTo().window(a);
		}
	}
	
	public void parenttab(WebDriver driver)
	{
		driver.switchTo().window(parent);
	}
	
	public void switchToTabOnURL(WebDriver driver,String partialURL) {
		//switch to child window
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext())
		{
			String windowId = it.next();
			driver.switchTo().window(windowId);
			String actUrl = driver.getCurrentUrl();
			if(actUrl.contains(partialURL))
			{
				break;
			}
		}
	}
	
	
	//switch to child window
	public void switchToTabOnTitle(WebDriver driver,String partialTitle) 
	{
	    Set<String> set = driver.getWindowHandles();
	    Iterator<String> it = set.iterator();
	
     	while(it.hasNext())
	  {
		String windowId = it.next();
		driver.switchTo().window(windowId);
		String actUrl = driver.getCurrentUrl();
		if(actUrl.contains(partialTitle))
		{
			break;
		}
	 }
	}
	
	//screenshot
		public void takeScreenshot(WebDriver driver, String path) throws IOException
		{
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			File dest = new File(path);
			FileHandler.copy(src,dest);
		}
		
		
		
	//overloaded  methods - switch to frame
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver, String nameID)
	{
		driver.switchTo().frame(nameID);
	}
	
	public void switchToFrame(WebDriver driver, WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	//Alert
	public void switchtoAlertandAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	public void switchtoAlertAndCancel(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public void select(WebElement element, String text)
	{
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
	public void select(WebElement element, int index)
	{
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	
	public void moveToElement(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void doubleclick(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	public void scrollToElement(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.scrollToElement(element).perform();
	}
	
	public void scrollByAmount(WebDriver driver,int x,int y)
	{
		Actions act = new Actions(driver);
		act.scrollByAmount(x, y);
		
	}
	
	
	
}
