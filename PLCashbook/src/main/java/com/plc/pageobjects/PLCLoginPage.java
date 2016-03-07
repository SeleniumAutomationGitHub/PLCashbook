package com.plc.pageobjects;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import com.plc.util.InitializeDriver;


public class PLCLoginPage {
	
	//ScreenShotScript sss = new ScreenShotScript();
	
	@FindBy(id="UserName")
	public WebElement userName;
	
	@FindBy(name="Password")
	private WebElement password;
	
	@FindBy(xpath="html/body/div[1]/div/div/div/div/form/button")
	private WebElement loginBtn;
	
		
	public void verifyLoginPageTitle(){
		
		try{
			if(com.plc.util.InitializeDriver.driver.getTitle() !=null)
			{
				Thread.sleep(1000);
				//ScreenShotScript.getScreenShot("yyy-MMM-dd hh-mm-ss", "CBTitle", "png");
				Assert.assertEquals(com.plc.util.InitializeDriver.driver.getTitle(), "Sign in to MYOB - MYOB", "Login Title validation failed");
			}else{
				System.out.println("URL title can't be blank.");					
			}
		}catch(Exception e){
			System.out.println("URL is not correct");
		}
	}
		
			
	
	
	public void loginToCashbook(String username, String passwd){
				
		try{
			
			Thread.sleep(3000);
			
			if(username !=null && !username.equals("") && passwd !=null && !passwd.equals("")  ){
				
				
				userName.clear();
				userName.sendKeys(username);
				password.clear();
				password.sendKeys(passwd);
				
				if(loginBtn.isDisplayed() && loginBtn.isEnabled()){
					loginBtn.click();
					//Thread.sleep(2000);
					Assert.assertEquals(InitializeDriver.driver.getTitle(), "Cashbook", "Failed login page");
					
				}else{
					System.out.println("Login button is not displayed or not enabled.");					
				}
				
			}else{
				System.out.println("Username and password can't be null.");
			}
		}catch (NoSuchElementException e) {
			System.out.println("Element is not found ");
		} catch (Exception e) {
			System.out.println("Unable to click on element ");
		}
	}

}
