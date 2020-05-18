package SandBoxPageObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Registration {

	WebDriver driver;
	Map<String, String> locators;

	private static final String USERNAME_XPATH = "USERNAME_XPATH";
	private static final String EMAIL_XPATH = "EMAIL_XPATH";
	private static final String PASSWORD_XPATH = "PASSWORD_XPATH";
	private static final String CONFIRM_PASSWORD_XPATH = "CONFIRM_PASSWORD_XPATH";
	private static final String ABOUT_YOU_MENU_XPATH = "ABOUT_YOU_MENU_XPATH";
	private static final String ABOUT_YOU_OPTION_XPATH = "ABOUT_YOU_OPTION_XPATH";
	private static final String SUBMIT_BTN = "SUBMIT_BTN";
	private static final String SIGN_UP_BTN_XPATH = "SIGN_UP_BTN_XPATH";
	private static final String ACCOUNT_AVATAR_XPATH = "ACCOUNT_AVATAR_XPATH";
	private static final String LOGOUT_BTN_XPATH = "LOGOUT_BTN_XPATH";

	public Registration(WebDriver driver, Map<String, String> locators) {
		this.driver = driver;
		this.locators = locators;
	}

	public void goToRegPage(WebDriver driver) {
		driver.findElement(By.xpath(locators.get(SIGN_UP_BTN_XPATH))).click();
	}

	public void inputUsername(String data) {
		driver.findElement(By.xpath(locators.get(USERNAME_XPATH))).sendKeys(data);
	}

	public void inputEmail(String data) {
		driver.findElement(By.xpath(locators.get(EMAIL_XPATH))).sendKeys(data);
	}

	public void inputPassword(String data) {
		driver.findElement(By.xpath(locators.get(PASSWORD_XPATH))).sendKeys(data);
	}

	public void inputConfirmPassword(String data) {
		driver.findElement(By.xpath(locators.get(CONFIRM_PASSWORD_XPATH))).sendKeys(data);
	}
	
	public void chooseAboutYouOpt() {
		Select aboutYou = new Select(driver.findElement(By.xpath(locators.get(ABOUT_YOU_MENU_XPATH))));
		aboutYou.selectByVisibleText("I just want to play in the sandbox");
	}

	public void clickSubmitBtn() {
		driver.findElement(By.xpath(locators.get(SUBMIT_BTN))).click();
	}

	public static String getUsersData(int i, int j) {
		 
		try	(FileInputStream fis = new FileInputStream("users.xlsx");
			XSSFWorkbook wb = new XSSFWorkbook(fis)) {
			return wb.getSheetAt(0).getRow(i).getCell(j).toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Failed";
		} catch (IOException e) {
			e.printStackTrace();
			return "Failed";
		}
	}

	public String Reg30Users(WebDriver driver, int i) throws InterruptedException {

		driver.findElement(By.xpath(locators.get(SIGN_UP_BTN_XPATH))).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath(locators.get(USERNAME_XPATH))).sendKeys(getUsersData(i, 1));
		driver.findElement(By.xpath(locators.get(EMAIL_XPATH))).sendKeys(getUsersData(i, 2));
		driver.findElement(By.xpath(locators.get(PASSWORD_XPATH))).sendKeys(getUsersData(i, 3));
		driver.findElement(By.xpath(locators.get(CONFIRM_PASSWORD_XPATH))).sendKeys(getUsersData(i, 4));
		driver.findElement(By.xpath(locators.get(ABOUT_YOU_MENU_XPATH))).click();
		driver.findElement(By.xpath(locators.get(ABOUT_YOU_OPTION_XPATH))).click();

		Thread.sleep(3000);
		
		driver.findElement(By.xpath(locators.get(SUBMIT_BTN))).click();

		return driver.getTitle();

	}
	
	public void logout() {
		driver.findElement(By.xpath(locators.get(ACCOUNT_AVATAR_XPATH))).click();
		driver.findElement(By.xpath(locators.get(LOGOUT_BTN_XPATH))).click();
	}
}