package selenium;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BacklogPage extends Page {
	
	public BacklogPage(WebDriver driver) {
		super(driver);
	}

	public boolean loggedIn() {
		
		return (driver.findElement(By.id("admin")).getText() == "admin");
	}

}
