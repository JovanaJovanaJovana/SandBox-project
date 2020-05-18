package SandBoxPageObject;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

	WebDriver driver;
	Map<String, String> locators;

	private static final String USERNAME_XPATH = "USERNAME_XPATH";
	private static final String PASSWORD_XPATH = "PASSWORD_XPATH";
	private static final String SUBMIT_BTN = "SUBMIT_BTN";
	private static final String ACCOUNT_AVATAR_XPATH = "ACCOUNT_AVATAR_XPATH";
	private static final String LOGOUT_BTN_XPATH = "LOGOUT_BTN_XPATH";


	public Login(WebDriver driver, Map<String, String> locators) {
		this.driver = driver;
		this.locators = locators;
	}

	public void inputUsername(String data) {
		driver.findElement(By.xpath(locators.get(USERNAME_XPATH))).sendKeys(data);
	}

	public void inputPassword(String data) {
		driver.findElement(By.xpath(locators.get(PASSWORD_XPATH))).sendKeys(data);
	}

	public void clickSubmitBtn() {
		driver.findElement(By.xpath(locators.get(SUBMIT_BTN))).click();
	}

	public void logout() {
		driver.findElement(By.xpath(locators.get(ACCOUNT_AVATAR_XPATH))).click();
		driver.findElement(By.xpath(locators.get(LOGOUT_BTN_XPATH))).click();
	}
}