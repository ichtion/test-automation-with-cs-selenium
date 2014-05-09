package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {
	
	public String baseUrl = "https://training.bananascrum.com/";

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void openLoginPage() {
		driver.get(baseUrl + "login");
	}

	public BacklogPage loginWithCorrectCredentials() {
		typeTextIntoAField("admin", By.id("login"));
		typeTextIntoAField("password", By.id("password"));
		driver.findElement(By.name("commit")).click();
		return new BacklogPage(driver);
	}
	
	public void typeTextIntoAField(String text, By location) {
		driver.findElement(location).clear();
		driver.findElement(location).sendKeys(text);
	}
}
