package SandBoxTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Root.ReadFiles;
import SandBoxPageObject.Login;
import SandBoxPageObject.Products;

public class ProductsTest {

	WebDriver driver;
	SoftAssert sa = new SoftAssert();
	
	public String login_url = "https://sandbox.2checkout.com/sandbox";
	By successful = By.xpath("//span[@class='form_valid large']");
	By malevich_price = By.xpath("//input[@value='30100.00']");
	By franz_marc_price = By.xpath("//input[@value='18100.00']");
	By kandinsky_price = By.xpath("//input[@value='20100.00']");
	By matisse_price = By.xpath("//input[@value='25100.00']");
	By gauguin_price = By.xpath("//input[@value='22100.00']");

	@Test(priority = 1)
	public void testGoToCreateProductPage() {
		driver.navigate().to(login_url);

		Login login = new Login(driver, ReadFiles.readLocators());
		login.inputUsername("Kazi555");
		login.inputPassword("Kazi55555");
		login.clickSubmitBtn();

		Products products = new Products(driver, ReadFiles.readLocators());
		products.goToCreateProductPage();

		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://sandbox.2checkout.com/sandbox/products/create_product";

		Assert.assertEquals(actualUrl, expectedUrl);
	}

		@Test(priority = 2)
	public void testCreateProduct() throws InterruptedException {

		for (int i = 1; i <= 5; i++) {
			
			Products products = new Products(driver, ReadFiles.readLocators());
			products.createProduct(driver, i);
			
			WebElement successfulAdd = driver.findElement(successful);
			sa.assertTrue(successfulAdd.isDisplayed());
			sa.assertAll();
			
			products.addNewProduct();
		}
	} 

	@Test(priority = 3)
	public void testUpdatePaintingPrices() {
		Products products = new Products(driver, ReadFiles.readLocators());
		
		products.updateMalevichPrice(driver);
		String actualMalevichUpdatedPrice = driver.findElement(malevich_price).getAttribute("value");
		String expectedMalevichUpdatedPrice = "30100.00";
		sa.assertEquals(actualMalevichUpdatedPrice, expectedMalevichUpdatedPrice);
		
		products.updateFranzMarcPrice(driver);
		String actualFranzMarcUpdatedPrice = driver.findElement(franz_marc_price).getAttribute("value");
		String expectedFranzMarcUpdatedPrice = "18100.00";
		sa.assertEquals(actualFranzMarcUpdatedPrice, expectedFranzMarcUpdatedPrice);
		
		products.updateKandinskyPrice(driver);
		String actualKandinskyUpdatedPrice = driver.findElement(kandinsky_price).getAttribute("value");
		String expectedKandinskyUpdatedPrice = "20100.00";
		sa.assertEquals(actualKandinskyUpdatedPrice, expectedKandinskyUpdatedPrice);
		
		products.updateMatissePrice(driver);
		String actualMatisseUpdatedPrice = driver.findElement(matisse_price).getAttribute("value");
		String expectedMatisseUpdatedPrice = "25100.00";
		sa.assertEquals(actualMatisseUpdatedPrice, expectedMatisseUpdatedPrice);
		
		products.updateGauguinPrice(driver);
		String actualGauguinUpdatedPrice = driver.findElement(gauguin_price).getAttribute("value");
		String expectedGauguinUpdatedPrice = "22100.00";
		sa.assertEquals(actualGauguinUpdatedPrice, expectedGauguinUpdatedPrice);
		
		sa.assertAll();
	} 

	@BeforeTest
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "/Users/osx/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterTest
	public void closeDriver() {
		driver.quit();
	}
}
