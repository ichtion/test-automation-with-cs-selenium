package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {

	private String baseUrl = "https://training.bananascrum.com";

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public LoginPage open() {
		driver.get(baseUrl + "/login");
		return this;
	}

	public BacklogPage loginWithCorrectAdminCredentials() {
		typeTextIntoAField("admin", By.id("login"));
		typeTextIntoAField("password", By.id("password"));
		clickElement(By.name("commit"));
		return new BacklogPage(driver);
	}

	public LoginPage loginWithInCorrectCredentials(String login, String password) {
		typeTextIntoAField(login, By.id("login"));
		typeTextIntoAField(password, By.id("password"));
		clickElement(By.name("commit"));
		return new LoginPage(driver);
	}

	public boolean isOpen() {
		return isElementPresent(By.name("commit"));
	}

}
