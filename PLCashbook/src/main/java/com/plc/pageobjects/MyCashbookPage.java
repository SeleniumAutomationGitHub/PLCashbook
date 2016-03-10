package com.plc.pageobjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.plc.util.InitializeDriver;
import com.plc.util.PageElements;


public class MyCashbookPage {
	
	private WebDriverWait wait = new WebDriverWait(InitializeDriver.driver, 100);
	
	
	//@FindBy(how = How.XPATH, using = "//input[@id='keyword']")
	
	//@FindBy(how = How.CSS, using = "#keyword")
	//@FindBy(how = How.CSS, using = "input#keyword")
	//@FindBy(how = How.CSS, using = "input[id='keyword']")
	//@FindBy(css="input[id='keyword']")
	//@FindBy(css="input#keyword")
	@FindBy(css="#keyword")
	private WebElement keywordLedger;
		
	@FindBy(xpath="//*[@id='businesses-list']/div[1]/div[1]/div[1]/div[2]/h4")
	private WebElement firstRow;
	
	@FindBy(xpath="//ul[@class='nav navbar-nav']/li/a/span")
	private WebElement searchedBusinessName;
	
	@FindBy(xpath="//*[@id='businesses-list']/div")	
	private List<WebElement> allLedgersRow;
		
	@FindBy(how = How.XPATH, using = "//*[@id='addBusiness']")
	private static WebElement addNewBusinessBtn;
	
	@FindBy(how = How.CSS, using = "#name")
	private WebElement businessName;
	
	@FindBy(how = How.CSS, using = "#client_code")
	private WebElement clientCode;
	
	@FindBy(how = How.CSS, using = "")
	private WebElement abnNumber;
	
	@FindBy(how = How.XPATH, using = "//select[@id='business_type']")
	private WebElement accountsTemplateDropdown;
	
	@FindBy(how = How.XPATH, using = "//*[@id='licence_code']")
	private WebElement productTypeDropdown;
	
	//@FindBy(css = "button[class='btn.btn-primary.create']")
	//@FindBy(xpath = "//button[@class='btn.btn-primary.create']")
	@FindBy(how = How.CSS, using = ".btn.btn-primary.create")
	private WebElement addBusinessBtn;
	
	@FindBy(how = How.CSS, using = ".cancel.btn.btn-large.btn-default")
	private WebElement cancelBtn;
	
	@FindBy(how = How.XPATH, using = "//*[@id='top-nav-menu']/ul/li[2]/a/b")
	private WebElement logOutIcon;
		
	@FindBy(xpath="//*[@id='top-nav-menu']/ul/li[2]/ul/li[4]/a[@data-event='logout']")
	private WebElement logOutLink;
	
	
	public static WebElement getAddNewBusinessBtn(){
		return addNewBusinessBtn;
	}
	
	

// Add a new business in PL Cashbook
	public void addNewBusiness(String businessName, String clientCode, String accountsTemplate, String productType){
		try{
			//InitializeDriver.driver.findElement(By.cssSelector("#addBusiness")).click();
			//InitializeDriver.driver.findElement(By.cssSelector("button#addBusiness")).click();
			//InitializeDriver.driver.findElement(By.cssSelector("button[id='addBusiness']")).click();
			
			addNewBusinessBtn.click();
			Thread.sleep(500);
			this.businessName.sendKeys(businessName);
			Thread.sleep(500);
			this.clientCode.sendKeys(clientCode);
			//this.abnNumber.sendKeys(abnNumber); //This field is optional
			new Select(accountsTemplateDropdown).selectByValue(accountsTemplate);
			new Select(productTypeDropdown).selectByVisibleText(productType);
			Thread.sleep(200);
			addBusinessBtn.click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cancel.btn.btn-large.btn-default")));
			Assert.assertEquals(searchLedger(businessName), true, businessName + " not created successfully.");
			System.out.println("Ledger " + businessName  + " added successfully.");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	
//Search the ledger and click the ledger
	public boolean searchLedger(String ledgerName){
		
		try{
			if(allLedgersRow.size()>0){
				keywordLedger.clear();
				keywordLedger.sendKeys(ledgerName);
				keywordLedger.sendKeys(Keys.ENTER);
				Thread.sleep(500);
				if(allLedgersRow.size()>0){
					Assert.assertEquals(firstRow.getText(), ledgerName, "Ledger is not available.");
					return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}catch(Exception ex){
				//ex.printStackTrace();
				System.out.println(ledgerName + " Not Found.");
				return false;
			}
		}

	
//Search the ledger and click the ledger
	public void ledgerClick(String ledgerName){
			
		try{
			if(searchLedger(ledgerName)){
				firstRow.click();
				Assert.assertEquals(searchedBusinessName.getText(), ledgerName, "Wrong ledger clicked");
				System.out.println("Clicked the ledger sucessfully.");
				}else{
					System.out.println("No records found for this ledger: " + ledgerName);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
	
//Logout functionality
	public void logOut(){
		try{
			Thread.sleep(1000);
			if(logOutIcon.isDisplayed() && logOutIcon.isEnabled()){
				logOutIcon.click();
				logOutLink.click();
				wait.until(ExpectedConditions.visibilityOf(LoginPage.loginBtn));
				Assert.assertEquals(InitializeDriver.driver.getTitle(), "Sign in to MYOB - MYOB", "Logout Failed");
				System.out.println("Logout Successfully.");
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	

}
