package com.plc.pageobjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.plc.util.InitializeDriver;


public class MyCashbookPage {
	
	private WebDriverWait wait = new WebDriverWait(InitializeDriver.driver, 10);
	private Select accountsTemplate;
	
	@FindBy(css="#keyword") // or @FindBy(css="input[id='keyword']")
	private WebElement keywordLedger;
		
	@FindBy(xpath="//*[@id='businesses-list']/div[1]/div[1]/div[1]/div[2]/h4")
	private WebElement firstRow;
		
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
	
	@FindBy(how = How.XPATH, using = "//*[@id='top-nav-menu']/ul/li[2]/a/b")
	private WebElement logOutIcon;
		
	@FindBy(xpath="//*[@id='top-nav-menu']/ul/li[2]/ul/li[4]/a[@data-event='logout']")
	private WebElement logOutLink;
	
	
	public static WebElement getAddNewBusinessBtn(){
		return addNewBusinessBtn;
	}
	
	
	public void addNewBusiness(String businessName, String clientCode, String accountsTemplate, String productType){
		try{
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
			Thread.sleep(1000);
			searchLedgerClick(businessName);
			System.out.println("Ledger " + businessName  + " added successfully.");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

//Search the ledger and click the ledger
	public void searchLedgerClick(String ledgerName){
		
		try{
			if(allLedgersRow.size()>0){
				keywordLedger.sendKeys(ledgerName);
				keywordLedger.sendKeys(Keys.ENTER);
				Thread.sleep(500);
					
				if(allLedgersRow.size()>0){
					firstRow.click();
					}else{
						System.out.println("No records found for this ledger: " + ledgerName);
					}
				}else{
					System.out.println("No records found.");
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
