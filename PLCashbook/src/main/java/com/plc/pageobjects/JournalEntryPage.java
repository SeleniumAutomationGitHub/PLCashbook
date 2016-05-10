package com.plc.pageobjects;

import com.plc.util.InitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class JournalEntryPage {
	
	private WebDriverWait wait = new WebDriverWait(InitializeDriver.driver, 10);
	private Actions actObj = new Actions(InitializeDriver.driver);
	
	
	@FindBy(how = How.XPATH,  using = "//*[@id='business-nav-menu']/ul/li[3]/a[@class='general-journals-link']")
	private WebElement journalTabLink;
	
	@FindBy(how = How.XPATH,  using = "//*[@id='add-new-journal-entry']")
	private WebElement journaladdEntryBtn;
	
	@FindBy(how = How.CSS, using = ".title.pull-left")
	private WebElement generalJournalsText;
	
	@FindBy(how = How.XPATH,  using = "//*[@id='date']")
	private WebElement dateField;
	
	@FindBy(how = How.XPATH,  using = "//*[@id='index-page']/body/div[8]/div[1]/table/tbody/tr[5]/td[2]")
	private WebElement dateSelect;
	
	@FindBy(how = How.XPATH,  using = "//*[@id='new-journal-entry-details']/div/div/div[2]/div[3]/div/input[@id='description']")
	private WebElement notesField;
	
	
	//First line entry
	
	@FindBy(how = How.XPATH,  using = "//*[@id='general-journal-lines-table']/div/table/tbody/tr[1]/td[1]/div[@data-cid='view1907']")
	private WebElement firstLineAccountCode;
	
	@FindBy(how = How.XPATH,  using = "//*[@id='general-journal-lines-table']/div/table/tbody/tr[1]/td[1]/div/div/span/i")
	private WebElement firstLineAccountCodeDrop;
	
	@FindBy(how = How.XPATH,  using = "//*[@id='general-journal-lines-table']/div/table/tbody/tr[1]/td[1]/div/div/div/table/tbody/tr[1]/td[1]/a['1-1100']")
	private WebElement firstLineAccountCodeSelect;
	
	@FindBy(how = How.XPATH,  using = "//*[@id='general-journal-lines-table']/div/table/tbody/tr[1]/td[2]/div/input[@id='description']")
	private WebElement firstLineAccountCodeDescription;
	
	@FindBy(how = How.XPATH,  using = "//*[@id='general-journal-lines-table']/div/table/tbody/tr[1]/td[4]/div/input[@id='credit_amount']")
	private WebElement firstLineAccountCodeCredit;
	
	
	
	//Second line entry
	
	@FindBy(how = How.XPATH,  using = "//*[@id='general-journal-lines-table']/div/table/tbody/tr[2]/td[1]/div[@data-cid='view1942']")
	private WebElement secondLineAccountCode;
	
	@FindBy(how = How.XPATH,  using = "//*[@id='general-journal-lines-table']/div/table/tbody/tr[2]/td[1]/div/div/span/i")
	private WebElement secondLineAccountCodeDrop;
	
	@FindBy(how = How.XPATH,  using = "//*[@id='general-journal-lines-table']/div/table/tbody/tr[2]/td[1]/div/div/div/table/tbody/tr[4]/td[1]/a['1-1800']")
	private WebElement secondLineAccountCodeSelect;
	
	@FindBy(how = How.XPATH,  using = "//*[@id='general-journal-lines-table']/div/table/tbody/tr[2]/td[2]/div/input[@id='description']")
	private WebElement secondLineAccountCodeDescription;
	
	@FindBy(how = How.XPATH,  using = "//*[@id='general-journal-lines-table']/div/table/tbody/tr[2]/td[3]/div/input[@id='debit_amount']")
	private WebElement firstLineAccountCodeDebit;
	
	//@FindBy(how = How.XPATH,  using = "//*[@id='new-journal-entry-details']/div/div/div[6]/div[2]/div/a[2]")
	//@FindBy(how = How.XPATH,  using = "//*[@id='new-journal-entry-details']/div/div/div[6]/div[2]/div/a[2][@class='btn btn-primary save-general-journal']")
	@FindBy(how = How.CSS, using = "a[class='btn btn-primary save-general-journal']")
	private WebElement addBtn;
	
	
	//Journal entry transactions tables
	
			@FindBy(how = How.XPATH,  using = ".//*[@id='page-content']/div/div[2]/table/tbody/tr")
			private List<WebElement> rowsCount;
			
			@FindBy(how=How.XPATH, using=".//*[@id='page-content']/div/div[2]/table/tbody/tr[1]/td[5]/a/span")
			private WebElement lineTrans;
			
			@FindBy(how=How.XPATH, using=".//*[@id='page-content']/div/div[2]/table/tbody/tr[2]/td/div/div[6]/div[1]/div/a[text()='Delete']")
			private WebElement deleteLineTrans;
			
			@FindBy(how=How.XPATH, using=".//*[@id='page-content']/div/div[2]/table/tbody/tr[2]/td/div/div[6]/div[1]/div/div/div[2]/a[1]")
			private WebElement confirmDeleteLineTrans;
			
			@FindBy(xpath = ".//*[@id='page-content']/div/div[2]/i[@class='fa fa-spinner fa-spin fa-3x center-block']")
			private WebElement spinner;
			
	
	
	
	public void journalTabClick(){
		try{
			if(journalTabLink.isDisplayed() && journalTabLink.isEnabled()){
				journalTabLink.click();
				wait.until(ExpectedConditions.visibilityOf(journaladdEntryBtn));
				Assert.assertEquals(generalJournalsText.getText(), "General journals",  "Not navigated to Journal Page");
				System.out.println("Journal tab clicked successfully.");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Journal tab not found.");
		}
	}
	
	public void journalLineEntry(String notes, String firstLineDesc, String creditDebitAmount, String secondLineDesc  ){
		
		try
		{
			Thread.sleep(3000);
			journaladdEntryBtn.click();
			
			Thread.sleep(3000);
			//dateField.click();
			//dateSelect.click();
			
			Thread.sleep(1000);
			notesField.sendKeys(notes);
			
			Thread.sleep(2000);
			firstLineAccountCodeDrop.click();
			
			Thread.sleep(1000);
			firstLineAccountCodeSelect.click();
			
			Thread.sleep(1000);
			firstLineAccountCodeDescription.sendKeys(firstLineDesc);
			
			Thread.sleep(1000);
			firstLineAccountCodeCredit.sendKeys(creditDebitAmount);
			
			
			Thread.sleep(2000);
			secondLineAccountCodeDrop.click();
			
			Thread.sleep(2000);
			secondLineAccountCodeSelect.click();
			
			Thread.sleep(3000);
			secondLineAccountCodeDescription.sendKeys(secondLineDesc);
			
			Thread.sleep(1000);
			firstLineAccountCodeDebit.sendKeys(creditDebitAmount);
			
			JavascriptExecutor js = (JavascriptExecutor)InitializeDriver.driver;
			js.executeScript("window.scrollBy(0,100)","");
			addBtn.click();
			//Control will wait untill the Cancel button is not visible
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".btn.btn-default.cancel-general-journal")));
			Assert.assertEquals(journaladdEntryBtn.isDisplayed(), true, "Journal not added.");
			
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Line missing");
		}
	}


	
	
	public void deleteTransaction() throws Exception{
		int rowSize=0;
		try{
			for(int i=0; i<3000; i++){
				//actObj.keyDown(Keys.PAGE_DOWN).build().perform();
				actObj.sendKeys(Keys.PAGE_DOWN).build().perform();
				Thread.sleep(500);
			}
			
			rowSize = rowsCount.size();
			//System.out.println("Row size is : " + rowSize );
			
			for(int i = 0; i < rowSize || i > rowSize; i++){
				
				if(rowSize > 49){
					actObj.sendKeys(Keys.ARROW_DOWN).build().perform();
					Thread.sleep(500);
				}
				
				//actObj.sendKeys(Keys.PAGE_UP).build().perform();
				if(lineTrans.isDisplayed() && lineTrans.isEnabled() ){
					
					lineTrans.click();
					//Thread.sleep(500);
					wait.until(ExpectedConditions.visibilityOf(deleteLineTrans));
					
					deleteLineTrans.click();
					//Thread.sleep(200);
					wait.until(ExpectedConditions.visibilityOf(confirmDeleteLineTrans));
					
					confirmDeleteLineTrans.click();
					//System.out.println("Yes Delete");
				}
			}
			System.out.println("Row size is : " + rowSize );
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	
	
	public void transactionsCount() throws Exception{
		int counter =0;
		boolean spin;
		int previousCount = 0;
		//while(previousCount != rowsCount.size()){
		while(!(previousCount == rowsCount.size())){
			 previousCount = rowsCount.size();
			 
			 actObj.sendKeys(Keys.END).build().perform();
			
			 /*spin = spinner.isDisplayed();
			 System.out.println("Spinner is displayed: " + spin);
			 counter++;*/
			 Thread.sleep(4000);
		}
		System.out.println("Row size is : " + rowsCount.size() );
		System.out.println("Transactions Count is : " + previousCount );
		if( previousCount == rowsCount.size()){
			System.out.println("Pass");
			
		}else{
			System.out.println("Fail");
		}
	}

}
