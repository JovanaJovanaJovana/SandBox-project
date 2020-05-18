package SandBoxTest;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Root.ReadFiles;
import SandBoxPageObject.Registration;

public class RegistrationTest {

	WebDriver driver;
	WebDriverWait wait;
	SoftAssert sa = new SoftAssert();

	public String login_url = "https://sandbox.2checkout.com/sandbox";
	public String sign_up_url = "https://sandbox.2checkout.com/sandbox/signup";
	public String home_page_url = "https://sandbox.2checkout.com/sandbox/home/dashboard";
	
	By username_error_msg = By.xpath("//div[@id='username_message']");
	By email_error_msg = By.xpath("//div[@id='email_error']");
	By password_error_msg = By.xpath("//div[@id='password_error']");
	By confirm_password_error_msg = By.xpath("//div[@id='confirm_error']");
	By about_you_error_msg = By.xpath("//div[@id='aboutyou_error']");

	@Test(priority = 1)
	public void testGoToRegPage() {
		driver.navigate().to(login_url);
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.goToRegPage(driver);

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = sign_up_url;

		Assert.assertEquals(actualUrl, expectedUrl);
	}

	@Test(priority = 2)
	public void testValidUserReg() throws InterruptedException {
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.inputUsername("Kazimir");
		registration.inputEmail("crnikvadrat@gmail.com");
		registration.inputPassword("crniKvadrat18");
		registration.inputConfirmPassword("crniKvadrat18");
		registration.chooseAboutYouOpt();
		registration.clickSubmitBtn();

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = home_page_url;

		Assert.assertEquals(actualUrl, expectedUrl);
	}

	@Test(priority = 3)
	public void testThatUserCannotRegWithoutUsernameData() throws InterruptedException {
		driver.navigate().to(sign_up_url);
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.inputEmail("crnikvadrat@gmail.com");
		registration.inputPassword("crniKvadrat18");
		registration.inputConfirmPassword("crniKvadrat18");
		registration.chooseAboutYouOpt();
		registration.clickSubmitBtn();

		WebElement usernameErrorMessage = driver.findElement(username_error_msg);
		Assert.assertTrue(usernameErrorMessage.isDisplayed());
	}

	@Test(priority = 4)
	public void testThatUserCannotRegWithoutEmailData() throws InterruptedException {
		driver.navigate().to(sign_up_url);
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.inputUsername("Kazimir");
		registration.inputPassword("crniKvadrat18");
		registration.inputConfirmPassword("crniKvadrat18");
		registration.chooseAboutYouOpt();
		registration.clickSubmitBtn();

		WebElement emailErrorMessage = driver.findElement(email_error_msg);
		Assert.assertTrue(emailErrorMessage.isDisplayed());
	}

	@Test(priority = 5)
	public void testThatUserCannotRegWithoutPasswordData() throws InterruptedException {
		driver.navigate().to(sign_up_url);
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.inputUsername("Kazimir");
		registration.inputEmail("crnikvadratt@gmail.com");
		registration.inputConfirmPassword("crniKvadrat18");
		registration.chooseAboutYouOpt();
		registration.clickSubmitBtn();

		WebElement passwordErrorMessage = driver.findElement(password_error_msg);
		Assert.assertTrue(passwordErrorMessage.isDisplayed());
	}

	@Test(priority = 6)
	public void testThatUserCannotRegWithoutConfirmPasswordData() throws InterruptedException {
		driver.navigate().to(sign_up_url);
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.inputUsername("Kazimir");
		registration.inputEmail("crnikvadrat@gmail.com");
		registration.inputPassword("crniKvadrat18");
		registration.chooseAboutYouOpt();
		registration.clickSubmitBtn();

		WebElement confirmPasswordErrorMessage = driver.findElement(confirm_password_error_msg);
		Assert.assertTrue(confirmPasswordErrorMessage.isDisplayed());
	}

	@Test(priority = 7)
	public void testThatUserCannotRegWithoutChoosingAboutYouOpt() throws InterruptedException {
		driver.navigate().to(sign_up_url);
		Registration registration = new Registration(driver, ReadFiles.readLocators());
		registration.inputUsername("Kazimir");
		registration.inputEmail("crnikvadrat@gmail.com");
		registration.inputPassword("crniKvadrat18");
		registration.inputConfirmPassword("crniKvadrat18");
		registration.clickSubmitBtn();

		WebElement aboutYouErrorMessage = driver.findElement(about_you_error_msg);
		Assert.assertTrue(aboutYouErrorMessage.isDisplayed());
	}

	@Test(priority = 8)
	public void testReg30Users() throws InterruptedException {

		for (int i = 1; i <= 30; i++) {

			driver.navigate().to(sign_up_url);
			Registration registration = new Registration(driver, ReadFiles.readLocators());
			
			String actualTitle = registration.Reg30Users(driver, i);
			sa.assertEquals(actualTitle, "Seller Area / Home");
			sa.assertAll();
			
			registration.logout();
		}
	}

	@BeforeClass
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "/Users/osx/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void closeDriver() {
		driver.quit();
	}

}
