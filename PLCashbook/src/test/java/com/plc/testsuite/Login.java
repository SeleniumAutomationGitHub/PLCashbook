package com.plc.testsuite;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import com.plc.pages.PLCLoginPage;
import com.plc.util.InitiateBrowser;


public class Login {
	public WebDriver driver;
	public PLCLoginPage plc;
	
	
  @Test
  public void test() throws Exception {
	  
	  Thread.sleep(1000);
	  plc.verifyLoginPageTitle();
  }
  
  
  
  @BeforeTest
  public void beforeTest() {
	  
	  com.plc.util.InitiateBrowser.driver = InitiateBrowser.launchBrowser("firefox");
	  InitiateBrowser.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		//Test Environment
	  InitiateBrowser.driver.get("https://burwood.cashbook.dev.myob.com");
	  InitiateBrowser.driver.manage().window().maximize();
	  plc = PageFactory.initElements(InitiateBrowser.driver, PLCLoginPage.class);
	  
  }

}
