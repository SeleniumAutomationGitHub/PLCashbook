package com.plc.pageobjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.plc.util.InitializeDriver;


public class LoginPage {
	
	private WebDriverWait wait = new WebDriverWait(InitializeDriver.driver, 10);
	
	@FindBy(id="UserName")
	public WebElement userName;
	
	@FindBy(name="Password")
	private WebElement password;
	
	@FindBy(xpath="html/body/div[1]/div/div/div/div/form/button")
	public static WebElement loginBtn;
	
		
	public void verifyLoginPageTitle(){
		try{
			if(InitializeDriver.driver.getTitle() !=null)
			{
				wait.until(ExpectedConditions.visibilityOf(loginBtn));
				Assert.assertEquals(InitializeDriver.driver.getTitle(), "Sign in to MYOB - MYOB", "Login Title validation failed");
			}else{
				System.out.println("URL title can't be blank.");					
			}
		}catch(Exception e){
			System.out.println("URL is not correct");
		}
	}
		
		
	
	public void loginToPLC(String username, String passwd){
		try{
			wait.until(ExpectedConditions.visibilityOf(loginBtn));
			if(username !=null && !username.equals("") && passwd !=null && !passwd.equals("")){
				userName.clear();
				userName.sendKeys(username);
				password.clear();
				password.sendKeys(passwd);
				loginBtn.click();
				wait.until(ExpectedConditions.visibilityOf(MyCashbookPage.getAddNewBusinessBtn()));
				Assert.assertEquals(InitializeDriver.driver.getTitle(), "Cashbook", "Failed login page");
			}else{
				System.out.println("Username and password can't be null.");
			}
		}catch (NoSuchElementException e) {
			System.out.println("Element is not found ");
		} catch (Exception e) {
			System.out.println("Unable to click on element");
		}
	}

}