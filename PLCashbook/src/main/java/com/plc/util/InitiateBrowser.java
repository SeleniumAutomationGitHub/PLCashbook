package com.plc.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class InitiateBrowser {
	
	
	public static WebDriver driver;
	
	public static WebDriver launchBrowser(String browser){
		
			if("firefox".equalsIgnoreCase(browser)){
				driver = new FirefoxDriver();
			}
			
			else if("ie".equalsIgnoreCase(browser)){
				System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			
			else if("CHROME".equalsIgnoreCase(browser)){
				System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			
			else{
				driver = new FirefoxDriver();
			}
				return driver;
		}
	
	
	
	

}
