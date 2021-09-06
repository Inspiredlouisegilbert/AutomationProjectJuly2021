package pageObjects;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class SignIn extends BasePage{
	
	
	public void enterPassword(String moviePassword) {

		EnterText(By.xpath("//input[@id='Password']"),moviePassword);
	}
	
	public void enterUserName(String movieUser) {

		EnterText(By.xpath("//input[@id='EmailOrMobileNumber']"),movieUser);
	}
	
	public void clickSignInButton() {

		clickElement(By.xpath("//button[@id='btnSubmit']"));
	
	}
	
	public String checkError() {

		return getElementText(By.xpath("//span[@id='EmailOrMobileNumber-error']"));
	}
	
	public String checkSignInError() {

		return getElementText(By.xpath("//p[@class='error_msg global']"));
	}
	
	public void goHome() {
		String sysUnderTest = getDataConfigProperties("systemUnderTest");
		driver.get(sysUnderTest);
	}
	
	

}
