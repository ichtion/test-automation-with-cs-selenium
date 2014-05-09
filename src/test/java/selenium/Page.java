package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page {
	
	protected WebDriver driver;
	private String baseUrl;

	public Page(WebDriver driver){
		this.driver = driver;
	}
	
	protected void typeTextIntoField(String Text, By locator) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(Text);
	}

	protected void clickElement(By locator) {
		driver.findElement(locator).click();
	}
	

}
