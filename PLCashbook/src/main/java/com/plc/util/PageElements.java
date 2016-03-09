package com.plc.util;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class PageElements {
	
	public static boolean isElementPresent(WebElement element){
		try{
			return element.isDisplayed();
		}catch(NoSuchElementException nse){
			return false;
		}
	}

}
