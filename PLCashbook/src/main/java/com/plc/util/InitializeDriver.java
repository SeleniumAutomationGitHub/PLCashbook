package com.plc.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class InitializeDriver {
	
	public static WebDriver driver;
	public static DesiredCapabilities capability;
	
	
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
	
	
	
	private static DesiredCapabilities setBrowserRemote(String browser){
		
		if("firefox".equalsIgnoreCase(browser)){
			capability = DesiredCapabilities.firefox();
			return capability;
		}
		
		else if("ie".equalsIgnoreCase(browser)){
			capability = DesiredCapabilities.internetExplorer();
			return capability;
		}
		
		else if("CHROME".equalsIgnoreCase(browser)){
			capability = DesiredCapabilities.chrome();
			capability.setPlatform(Platform.WINDOWS);
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			return capability;
		}
		
		else{
			return null;
		}
		
	}
	
	
	
	public static void getBrowserRemote(String baseURL, String nodeURL, String browser) throws MalformedURLException{
		
		driver = new RemoteWebDriver(new URL(nodeURL), InitializeDriver.capability = InitializeDriver.setBrowserRemote(browser));
		driver.get(baseURL);
		driver.manage().window().maximize();
	}

}
