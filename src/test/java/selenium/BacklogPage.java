package selenium;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BacklogPage extends Page {

	public BacklogPage(WebDriver driver) {
		super(driver);
	}

	public boolean isOpen() {
		return driver.findElement(By.id("admin")).getText().equals("Admin");
	}
	


}
