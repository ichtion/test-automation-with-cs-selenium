package selenium;

import static org.junit.Assert.assertEquals;
import junitparams.Parameters;

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
	
	public LoginPage loginWithInorrectCredentials(String login, String password) {
		typeTextIntoAField(login, By.id("login"));
		typeTextIntoAField(password, By.id("password"));
		driver.findElement(By.name("commit")).click();
		return this;
	}

	public boolean notLoggedIn() {
		
		return (driver.findElement(By.id("flash")).getText() == "Login failed");		
	}

}
