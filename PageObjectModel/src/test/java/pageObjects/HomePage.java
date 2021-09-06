package pageObjects;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class HomePage extends BasePage{
	
	public void enterData() {
		System.out.println("Here we are");
		
	}
	
	public void clickSignIn() {
		
		clickElement(By.xpath("/html//div[@id='root']/div[1]//img[@alt='Sign in']")); 
	
		
	}
	

}
