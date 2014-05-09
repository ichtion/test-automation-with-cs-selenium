package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page {

	protected WebDriver driver;

	public Page(WebDriver driver) {
		this.driver = driver;
	}
	
	protected void clickElement(By locator) {
		driver.findElement(locator).click();
	}

	protected void typeTextIntoAField(String text, By locator) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(text);
	}

}
