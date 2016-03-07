package com.plc.pages;


import com.plc.util.InitiateBrowser;

public class PLCLoginPage {
	
	public void verifyLoginPageTitle(){
		
		try{
			if(InitiateBrowser.driver.getTitle() !=null)
			{
				Thread.sleep(1000);
				System.out.println(InitiateBrowser.driver.getTitle());
				//Assert.assertEquals(InitiateBrowser.driver.getTitle(), "Sign in to MYOB - MYOB", "Login Title validation failed");
				
			}else{
				System.out.println("URL title can't be blank.");					
			}
		}catch(Exception e){
			System.out.println("URL is not correct");
		}
	}
	
}
