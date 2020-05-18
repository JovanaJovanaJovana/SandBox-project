package SandBoxPageObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Root.ReadFiles;

public class Products {

	WebDriver driver;
	Map<String, String> locators;

	public String name, id, short_description, long_description, price, tangible, recurring, approved_url;

	private static final String PRODUCTS_XPATH = "PRODUCTS_XPATH";
	private static final String ADD_NEW_PRODUCT_XPATH = "ADD_NEW_PRODUCT_XPATH";
	private static final String PRODUCT_NAME_XPATH = "PRODUCT_NAME_XPATH";
	private static final String PRODUCT_ID_XPATH = "PRODUCT_ID_XPATH";
	private static final String PRODUCT_SHORT_DESCR_XPATH = "PRODUCT_SHORT_DESCR_XPATH";
	private static final String PRODUCT_LONG_DESCR_XPATH = "PRODUCT_LONG_DESCR_XPATH";
	private static final String PRODUCT_PRICE_XPATH = "PRODUCT_PRICE_XPATH";
	private static final String PRODUCT_TANGIBLE_RADIO_BTN_XPATH = "PRODUCT_TANGIBLE_RADIO_BTN_XPATH";
	private static final String PRODUCT_RECURRING_RADIO_BTN_XPATH = "PRODUCT_RECURRING_RADIO_BTN_XPATH";
	private static final String PRODUCT_SAVE_BTN = "PRODUCT_SAVE_BTN";
	private static final String PRODUCT_VIEW_NAV_XPATH = "PRODUCT_VIEW_NAV_XPATH";
	private static final String PRODUCT_EDIT_NAV_XPATH = "PRODUCT_EDIT_NAV_XPATH";
	private static final String MALEVICH_PRICE_XPATH = "MALEVICH_PRICE_XPATH";
	private static final String FRANZ_MARC_PRICE_XPATH = "FRANZ_MARC_PRICE_XPATH";
	private static final String KANDINSKY_PRICE_XPATH = "KANDINSKY_PRICE_XPATH";
	private static final String MATISSE_PRICE_XPATH = "MATISSE_PRICE_XPATH";
	private static final String GAUGUIN_PRICE_XPATH = "GAUGUIN_PRICE_XPATH";
	private static final String SAVE_PRODUCT_CHANGES_BTN_XPATH = "SAVE_PRODUCT_CHANGES_BTN_XPATH";

	public Products(String name, String id, String short_description, String long_description, String price,
			String tangible, String recurring, String approved_url) {

		this.name = name;
		this.id = id;
		this.short_description = short_description;
		this.long_description = long_description;
		this.price = price;
		this.tangible = tangible;
		this.recurring = recurring;
		this.approved_url = approved_url;
	}

	public Products(WebDriver driver, Map<String, String> locators) {
		this.driver = driver;
		this.locators = locators;
	}

	public void goToCreateProductPage() {
		driver.findElement(By.xpath(locators.get(PRODUCTS_XPATH))).click();
		driver.findElement(By.xpath(locators.get(ADD_NEW_PRODUCT_XPATH))).click();
	}

	public static String getProductsData(int i, int j) {

		try (FileInputStream fis = new FileInputStream("products.xlsx"); XSSFWorkbook wb = new XSSFWorkbook(fis)) {
			return wb.getSheetAt(0).getRow(i).getCell(j).toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Failed";
		}
	}

	public void createProduct(WebDriver driver, int i) throws InterruptedException {

		driver.findElement(By.xpath(locators.get(PRODUCT_NAME_XPATH))).sendKeys(getProductsData(i, 0));
		driver.findElement(By.xpath(locators.get(PRODUCT_ID_XPATH))).sendKeys(getProductsData(i, 1));
		driver.findElement(By.xpath(locators.get(PRODUCT_SHORT_DESCR_XPATH))).sendKeys(getProductsData(i, 2));
		driver.findElement(By.xpath(locators.get(PRODUCT_LONG_DESCR_XPATH))).sendKeys(getProductsData(i, 3));
		driver.findElement(By.xpath(locators.get(PRODUCT_PRICE_XPATH))).sendKeys(getProductsData(i, 4));
		driver.findElement(By.xpath(locators.get(PRODUCT_TANGIBLE_RADIO_BTN_XPATH))).click();
		driver.findElement(By.xpath(locators.get(PRODUCT_RECURRING_RADIO_BTN_XPATH))).click();
		driver.findElement(By.xpath(locators.get(PRODUCT_SAVE_BTN))).click();

		Thread.sleep(2000);

	}

	public void addNewProduct() {
		driver.findElement(By.xpath(locators.get(PRODUCT_VIEW_NAV_XPATH))).click();
		driver.findElement(By.xpath(locators.get(ADD_NEW_PRODUCT_XPATH))).click();
	}

	public void updateMalevichPrice(WebDriver driver) {
		driver.findElement(By.xpath(locators.get(PRODUCT_EDIT_NAV_XPATH))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String malevich = driver.findElement(By.xpath(locators.get(MALEVICH_PRICE_XPATH))).getAttribute("value");
		double malPrice = Double.parseDouble(malevich);
		double malNewPrice = malPrice + 100;
		String malevichUpdated = String.valueOf(malNewPrice);

		WebElement malevichNewPrice = driver.findElement(By.xpath(locators.get(MALEVICH_PRICE_XPATH)));
		malevichNewPrice.clear();
		malevichNewPrice.sendKeys(malevichUpdated);

		driver.findElement(By.xpath(locators.get(SAVE_PRODUCT_CHANGES_BTN_XPATH))).click();
	}

	public void updateFranzMarcPrice(WebDriver driver) {
		driver.findElement(By.xpath(locators.get(PRODUCT_EDIT_NAV_XPATH))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String fMarc = driver.findElement(By.xpath(locators.get(FRANZ_MARC_PRICE_XPATH))).getAttribute("value");
		double fMarcPrice = Double.parseDouble(fMarc);
		double fMarcNewPrice = fMarcPrice + 100;
		String fMarcUpdated = String.valueOf(fMarcNewPrice);

		WebElement franzMarcNewPrice = driver.findElement(By.xpath(locators.get(FRANZ_MARC_PRICE_XPATH)));
		franzMarcNewPrice.clear();
		franzMarcNewPrice.sendKeys(fMarcUpdated);

		driver.findElement(By.xpath(locators.get(SAVE_PRODUCT_CHANGES_BTN_XPATH))).click();
	}

	public void updateKandinskyPrice(WebDriver driver) {
		driver.findElement(By.xpath(locators.get(PRODUCT_EDIT_NAV_XPATH))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String kandinsky = driver.findElement(By.xpath(locators.get(KANDINSKY_PRICE_XPATH))).getAttribute("value");
		double kandPrice = Double.parseDouble(kandinsky);
		double kandNewPrice = kandPrice + 100;
		String kandUpdated = String.valueOf(kandNewPrice);

		WebElement kandinskyNewPrice = driver.findElement(By.xpath(locators.get(KANDINSKY_PRICE_XPATH)));
		kandinskyNewPrice.clear();
		kandinskyNewPrice.sendKeys(kandUpdated);

		driver.findElement(By.xpath(locators.get(SAVE_PRODUCT_CHANGES_BTN_XPATH))).click();
	}

	public void updateMatissePrice(WebDriver driver) {
		driver.findElement(By.xpath(locators.get(PRODUCT_EDIT_NAV_XPATH))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String matisse = driver.findElement(By.xpath(locators.get(MATISSE_PRICE_XPATH))).getAttribute("value");
		double matPrice = Double.parseDouble(matisse);
		double matNewPrice = matPrice + 100;
		String matUpdated = String.valueOf(matNewPrice);

		WebElement matisseNewPrice = driver.findElement(By.xpath(locators.get(MATISSE_PRICE_XPATH)));
		matisseNewPrice.clear();
		matisseNewPrice.sendKeys(matUpdated);

		driver.findElement(By.xpath(locators.get(SAVE_PRODUCT_CHANGES_BTN_XPATH))).click();
	}

	public void updateGauguinPrice(WebDriver driver) {
		driver.findElement(By.xpath(locators.get(PRODUCT_EDIT_NAV_XPATH))).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		String gauguin = driver.findElement(By.xpath(locators.get(GAUGUIN_PRICE_XPATH))).getAttribute("value");
		double gPrice = Double.parseDouble(gauguin);
		double gNewPrice = gPrice + 100;
		String gUpdated = String.valueOf(gNewPrice);

		WebElement gauguinNewPrice = driver.findElement(By.xpath(locators.get(GAUGUIN_PRICE_XPATH)));
		gauguinNewPrice.clear();
		gauguinNewPrice.sendKeys(gUpdated);

		driver.findElement(By.xpath(locators.get(SAVE_PRODUCT_CHANGES_BTN_XPATH))).click();
	} 
}
