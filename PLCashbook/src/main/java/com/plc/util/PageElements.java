package com.plc.util;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoAlertPresentException;

public class PageElements {
	
	public static boolean isElementPresent(WebElement element){
		try{
			return element.isDisplayed();
		}catch(NoSuchElementException nse){
			return false;
		}
	}



	public static boolean isAlertPresent()
	{
		try
		{
			InitializeDriver.driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException Ex)
		{
			return false;
		}
	}

}
