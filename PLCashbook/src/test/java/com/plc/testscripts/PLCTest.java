package com.plc.testscripts;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import jxl.read.biff.BiffException;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import com.plc.pageobjects.PLCLoginPage;
import com.plc.pageobjects.PLCHomePage;
import com.plc.util.DriverScriptExcel;
import com.plc.util.InitializeDriver;
import com.plc.util.ScreenShotScript;


public class PLCTest {
	
	
		public PLCLoginPage clp;
		public PLCHomePage chp;
		public ScreenShotScript sss;
		
		@Parameters("browser")
		@BeforeTest(enabled=true)
		  public void beforeClass(String browser) {
			InitializeDriver.driver = InitializeDriver.launchBrowser(browser);
			InitializeDriver.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  
			//Test Environment
			InitializeDriver.driver.get("https://burwood.cashbook.dev.myob.com");
			InitializeDriver.driver.manage().window().maximize();
			
			clp = PageFactory.initElements(InitializeDriver.driver, PLCLoginPage.class);
			chp = PageFactory.initElements(InitializeDriver.driver, PLCHomePage.class);
			sss = PageFactory.initElements(InitializeDriver.driver, ScreenShotScript.class);
		}
		
		
		/*@Parameters("browser")
		@BeforeTest(enabled=true)
		public void beforeTestInitialize(String browser ) {
			try {
				
				InitializeDriver.getBrowserRemote("https://burwood.cashbook.dev.myob.com", "http://172.25.32.36:5566/wd/hub", browser);
				InitializeDriver.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  
				//http://B1403F2D.TLP.ads.valuelabs.net4445 , http://172.25.32.36:4445
			
				clp = PageFactory.initElements(InitializeDriver.driver, CBPLLoginPage.class);
				chp = PageFactory.initElements(InitializeDriver.driver, CBPLHomePage.class);
				sss = PageFactory.initElements(InitializeDriver.driver, ScreenShotScript.class);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
		}*/
			
				 
		@DataProvider (name = "Authentication")
		public Object[][] credentials() throws Exception{
			 return DriverScriptExcel.getTestData(".\\src\\test\\resources\\InputTestData.xls", "Sheet1");
		}
		 
		 	 
		
	//Login the application with valid credentials 
	@Test(dataProvider = "Authentication", priority=1, enabled=true )
	public void loginToCashbookTest(String username, String passwd) throws Exception{
			
			 	clp.verifyLoginPageTitle();
			  	Reporter.log("Verified the Login Page Title Successfully...!");
			  
			  	clp.loginToCashbook(username, passwd );
			  	//Reporter.log("Login into Cashbook by : " + clp.userNameText.getText() + " successfully and navigating to home page");
			  	
			  	System.out.println("Login successfully.");
			  	
			  	try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					//e.printStackTrace();
				}
		}
		 
		
	//Test Cases: 01 - Verify that without any filter, all dimensions open without any error.
	@Test(priority = 2, enabled = true)
	public void ledgerTest(){
			chp.searchLedgerClick("MIKE17");
		}
		
		
		
	@Test(priority = 3, enabled = true)
	public void journalTest(){
			chp.journalTabClick();
		}


	/*@DataProvider(name = "Transcations")
	public Object[][] transData(){
		
		return ExcelUtil.getTestData(".\\input\\CashbookTestData.xls", "loadone");
		
	}*/


	@Test(dataProvider = "Transcations", priority = 4, enabled = true)
	public void journalLineEntryTest(String notes, String firstLineDesc, String creditDebitAmount, String secondLineDesc){
		chp.journalLineEntry(notes, firstLineDesc, creditDebitAmount, secondLineDesc);
		
	}


	@Test(priority = 5, enabled = true)
	public void deleteTest() throws Exception{
		chp.deleteTransaction();
	}
	

	@Test(priority = 6, enabled = true)
	public void transactionsCountTest() throws Exception{
		chp.transactionsCount();
		
	}
	
	@AfterMethod(enabled = true)
	public void getFailedScreenShot(ITestResult result) throws Exception{
		
		if(result.FAILURE == result.getStatus()){
			new ScreenShotScript().getScreenShot("yyy-MMM-dd hh-mm-ss", result.getName(), "png");
			
		}
	}

	 
	}

	
	
	
	


