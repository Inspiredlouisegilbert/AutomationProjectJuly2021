package frameWorkClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	//Declare the WebDriver
    public static WebDriver driver;
    
    //constructor of base class
    public BasePage() {
        if (driver == null) {
        	// Get parameter values
        	String browser = getDataConfigProperties("browser");
        	String systemUnderTest = getDataConfigProperties("systemUnderTest");
            String pdriverDir = getDataConfigProperties("driverdir");
    
    		//Check if parameter passed as 'chrome'
    		if(browser.equalsIgnoreCase("chrome")){
    			//set path to chromedriver.exe
    			System.setProperty("webdriver.chrome.driver",pdriverDir+"chromedriver.exe");
    			//create chrome instance
    			driver     = new ChromeDriver();
    			driver.get(systemUnderTest);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    		}
        }
            
    }
    
    
	public String getDataConfigProperties(String propertyName) {
		// Properties setup
		Properties p = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream("config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			p.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return p.getProperty(propertyName);
	}
	

	public void waitForElement(int elementWait,By pLocator) {
		WebDriverWait wait = new WebDriverWait(driver,elementWait);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(pLocator));
	}
	
	public void waitForUrl(int elementWait,String pLocator) {
		WebDriverWait wait = new WebDriverWait(driver,elementWait);
    	wait.until(ExpectedConditions.urlContains(pLocator));
	}
	
	public void waitForClick(int elementWait,By pLocator) {
		WebDriverWait wait = new WebDriverWait(driver,elementWait);
    	wait.until(ExpectedConditions.elementToBeClickable(pLocator));
    	
	}
	
	public void cleanUp() {
		driver.close();
	}
	
	public String getElementText(By pLocator) {
		waitForElement(10,pLocator);
		String h1 = getElement(pLocator).getText();
		return h1;
	}

	public void clickElement(By pLocator)  {
		
		waitForClick(10,pLocator);
		getElement(pLocator).click();

	}
	

	public WebElement getElement(By pLocator) {
		
		waitForElement(10,pLocator);
		return driver.findElement(pLocator);
	}

	public void EnterText(By pLocator,String pText) {
		
		waitForClick(10,pLocator);
		 driver.findElement(pLocator).sendKeys(pText);
	}

	
	public  void selectDropDown(By pLocator, String pValue){		
		// Creates an instance of the dropdown object
		Select sDrpDown = new Select (getElement(pLocator));		
		// Populates the Dropdown
		sDrpDown.selectByVisibleText(pValue);
		
	}

}
