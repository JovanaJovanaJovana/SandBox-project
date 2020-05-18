package SandBoxTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Root.ReadFiles;
import SandBoxPageObject.Login;

public class LoginTest {

	WebDriver driver;
	
	public String login_url = "https://sandbox.2checkout.com/sandbox";
	By login_error_msg = By.xpath("//div[@id='login-error']");
	
	@Test(priority = 1)
	public void testValidUserLogin() {
		Login login = new Login(driver, ReadFiles.readLocators());
		login.inputUsername("KaziMir18");
		login.inputPassword("Kazimir18");
		login.clickSubmitBtn();
		
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://sandbox.2checkout.com/sandbox/home/dashboard";
		
		Assert.assertEquals(actualUrl, expectedUrl);
	}
	

	@Test(priority = 2)
	public void testUserCannotLoginWithoutRegistration() {
		Login login = new Login(driver, ReadFiles.readLocators());
		login.logout();
		login.inputUsername("Kazimir");
		login.inputPassword("Crnikvadrat18");
		login.clickSubmitBtn();
		
		WebElement errorMessage = driver.findElement(login_error_msg);
	    Assert.assertTrue(errorMessage.isDisplayed());	
	}
	
	@BeforeTest
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "/Users/osx/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(login_url);
	}

	@AfterTest
	public void closeDriver() {
		driver.quit();
	}

}