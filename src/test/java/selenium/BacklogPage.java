package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class BacklogPage extends Page {

	public BacklogPage(WebDriver driver) {
		super(driver);
	}

	public boolean isOpen() {
		return isElementPresent(By.id("admin"));
	}
	
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
