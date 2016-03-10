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

import com.plc.pageobjects.LoginPage;
import com.plc.pageobjects.MyCashbookPage;
import com.plc.util.DriverScriptExcel;
import com.plc.util.InitializeDriver;
import com.plc.util.ScreenShotScript;


public class PLCTest {
	
		public LoginPage lp;
		public MyCashbookPage mcp;
		public ScreenShotScript sss;
		
		@Parameters("browser")
		@BeforeTest(enabled=true)
		  public void beforeClass(String browser) {
			InitializeDriver.driver = InitializeDriver.launchBrowser(browser);
			InitializeDriver.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			  
			//Test Environment
			InitializeDriver.driver.get("https://burwood.cashbook.dev.myob.com");
			InitializeDriver.driver.manage().window().maximize();
			
			lp = PageFactory.initElements(InitializeDriver.driver, LoginPage.class);
			mcp = PageFactory.initElements(InitializeDriver.driver, MyCashbookPage.class);
			sss = PageFactory.initElements(InitializeDriver.driver, ScreenShotScript.class);
		}
		
		
		/*@Parameters("browser")
		@BeforeTest(enabled=false)
		public void beforeTestInitialize(String browser ) {
			try {
				
				InitializeDriver.getBrowserRemote("https://burwood.cashbook.dev.myob.com", "http://172.25.32.36:5566/wd/hub", browser);
				InitializeDriver.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  
				//http://B1403F2D.TLP.ads.valuelabs.net4445 , http://172.25.32.36:4445
			
				lp = PageFactory.initElements(InitializeDriver.driver, CBPLLoginPage.class);
				hp = PageFactory.initElements(InitializeDriver.driver, CBPLHomePage.class);
				sss = PageFactory.initElements(InitializeDriver.driver, ScreenShotScript.class);
				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
		}*/
			
				 
		@DataProvider (name = "Authentication")
		public Object[][] credentials() throws Exception{
			 return DriverScriptExcel.getTestData(".\\src\\test\\resources\\InputTestData.xls", "login");
		}
		 
		 	 
		//Login the application with valid credentials 
		@Test(dataProvider = "Authentication", priority = 1, enabled = true)
		public void loginToCashbookTest(String username, String passwd) throws Exception{
						
			lp.verifyLoginPageTitle();
		  	Reporter.log("Verified the Login Page Title Successfully...!");
			
			lp.loginToPLC(username, passwd );
			//Reporter.log("Login into Cashbook by : " + clp.userNameText.getText() + " successfully and navigating to home page");
			System.out.println("Login successfully.");
			  	
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
		 
		
		@Test(priority = 2, enabled = true)
		public void addNewBusinessTest(){
		mcp.addNewBusiness("MIKE153", "SelAutoTest", "Construction", "Ledger");
		}
		
		
		@Test(priority = 3, enabled = true)
		public void searchLedgerTest(){
			mcp.searchLedger("MIKE138");
			System.out.println("Searched ledger succefully.");
		}
		
		@Test(priority = 4, enabled = true)
		public void ledgerLinkClickTest(){
			mcp.ledgerClick("MIKE141");
		}
		
		
	/*	
	@Test(priority = 3, enabled = true)
	public void journalTest(){
			mcp.journalTabClick();
		}


	@DataProvider(name = "Transcations")
	public Object[][] transData(){
		
		return ExcelUtil.getTestData(".\\input\\CashbookTestData.xls", "loadone");
		
	}


	@Test(dataProvider = "Transcations", priority = 4, enabled = true)
	public void journalLineEntryTest(String notes, String firstLineDesc, String creditDebitAmount, String secondLineDesc){
		mcp.journalLineEntry(notes, firstLineDesc, creditDebitAmount, secondLineDesc);
		
	}


	@Test(priority = 5, enabled = true)
	public void deleteTest() throws Exception{
		mcp.deleteTransaction();
	}
	

	@Test(priority = 6, enabled = true)
	public void transactionsCountTest() throws Exception{
		mcp.transactionsCount();
		
	}
*/	
	@Test(dependsOnMethods = { "addNewBusinessTest" }, priority = 7, enabled = true)
	public void logOutTest(){
		mcp.logOut();
	}
	
	@AfterMethod(enabled = true)
	public void getFailedScreenShot(ITestResult result) throws Exception{
		
		if(result.FAILURE == result.getStatus()){
			new ScreenShotScript().getScreenShot("yyy-MMM-dd hh-mm-ss", result.getName(), "png");
			
		}
	}

	 
	}

	
	
	
	


