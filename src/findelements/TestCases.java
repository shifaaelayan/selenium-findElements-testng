package findelements;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class TestCases {

	WebDriver driver = new EdgeDriver();
	String WebsiteUrl = "https://www.saucedemo.com/";
	
	String username = "standard_user";
	String password = "secret_sauce";
	
	
	@BeforeTest
	public void setup()
	{
		driver.get(WebsiteUrl);
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); 
	}
	
	@Test(priority = 1)
	public void login()
	{
		WebElement usernameF = driver.findElement(By.id("user-name"));
		usernameF.sendKeys(username);
		
		WebElement passwordF = driver.findElement(By.id("password"));
		passwordF.sendKeys(password);
		
		WebElement loginBtn = driver.findElement(By.id("login-button"));
		loginBtn.click();
		
	}
	
	@Test(priority = 2)
	public void addElements() // Buttons are all have the same classes (all Buttons have the same behavior)
	{
		//driver.findElements(By.className("btn btn_primary btn_small btn_inventory")); --> more than one class 
		
		// use all classes. to use it, you must use a cssSelector locator and remove space between classes and put . instead
		List <WebElement> addToCart = driver.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")); // 4 classes
		//findElements return List of WebElement
		
		
		// using for loop:
		for(int i=0; i<addToCart.size(); i++) // addToCart.size() --> the size of list
		{
			addToCart.get(i).click();
		} // and all items will add.
	}
		
		@Test(priority = 3) 
		public void removeElements() throws InterruptedException // one class changed after the add (btn_primary to btn_secondary)
		// Show an alert for each item after it is removed
		{
 
			List<WebElement> removeFromCart = driver.findElements(By.className("btn_secondary"));

			JavascriptExecutor js = (JavascriptExecutor) driver; // to execute JS code in my driver
			List<WebElement> itemNames = driver.findElements(By.className("inventory_item_name"));

			for (int i = 0; i < removeFromCart.size(); i++) 
			{
			    removeFromCart.get(i).click(); 

			    String itemName = itemNames.get(i).getText();

			    Thread.sleep(1000);
			    js.executeScript("alert(arguments[0] + ' has been removed')", itemName);
			    Thread.sleep(1000);

			    driver.switchTo().alert().accept(); // click OK on alerts
			}

			
		}
		
	
	
	@AfterTest
	public void cleanup()
	{
		
	}
}
