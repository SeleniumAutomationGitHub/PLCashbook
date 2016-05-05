package com.plc.testscripts;


import com.plc.pageobjects.JournalEntryPage;
import com.plc.pageobjects.LoginPage;
import com.plc.pageobjects.MyCashbookPage;
import com.plc.util.DriverScriptExcel;
import com.plc.util.InitializeDriver;
import com.plc.util.ScreenShotScript;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


public class PLCTest {
	
		private LoginPage lp;
		private MyCashbookPage mcp;
		private JournalEntryPage jep;
		//private ScreenShotScript sss;

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
			jep = PageFactory.initElements(InitializeDriver.driver, JournalEntryPage.class);
			//sss = PageFactory.initElements(InitializeDriver.driver, ScreenShotScript.class);
			
		}
		
		
		/*@Parameters("browser")
		@BeforeTest(enabled=true)
		public void beforeTestInitialize(String browser ) {
			try {
				InitializeDriver.getBrowserRemote("https://burwood.cashbook.dev.myob.com", "http://172.25.32.36:5566/wd/hub", browser);
				InitializeDriver.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			  
				//http://B1403F2D.TLP.ads.valuelabs.net4445 , http://172.25.32.36:4445
			
				lp = PageFactory.initElements(InitializeDriver.driver, LoginPage.class);
				mcp = PageFactory.initElements(InitializeDriver.driver, MyCashbookPage.class);
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
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}
		}
		 
		
		@Test(priority = 2, enabled = true)
		public void addNewBusinessTest(){
		mcp.addNewBusiness("MIKE166", "SelAutoTest", "Construction", "Ledger");
		}
		
		
		@Test(priority = 3, enabled = true)
		public void searchLedgerTest(){
			mcp.searchLedger("MIKE138");
			System.out.println("Searched ledger successfully");
		}
		
		@Test(priority = 4, enabled = true)
		public void ledgerLinkClickTest(){
			mcp.ledgerClick("MIKE165");
		}
		
	
		@Test(priority = 5, enabled = true)
		public void journalTest(){
			jep.journalTabClick();
		}


		//Getting test data for journal entry
		@DataProvider(name = "Transcations")
		public Object[][] transData() throws Exception {
			return DriverScriptExcel.getTestData(".\\src\\test\\resources\\InputTestData.xls", "JournalTestData");
		}

		@Test(dataProvider = "Transcations", priority = 6, enabled = true)
		public void journalLineEntryTest(String notes, String firstLineDesc, String creditDebitAmount, String secondLineDesc){
			jep.journalLineEntry(notes, firstLineDesc, creditDebitAmount, secondLineDesc);
			System.out.println("Journal entries created successfully.");
		}

/*
	@Test(priority = 5, enabled = true)
	public void deleteTest() throws Exception{
		mcp.deleteTransaction();
	}
	

	@Test(priority = 6, enabled = true)
	public void transactionsCountTest() throws Exception{
		mcp.transactionsCount();
		
	}
*/	
	//@Test(dependsOnMethods = { "addNewBusinessTest" }, priority = 7, enabled = true)
	@Test(priority = 7, enabled = true)
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

	
	
	
	


