package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {

	private String baseUrl = "https://training.bananascrum.com/";;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get(baseUrl + "login");
	}

	public BacklogPage correctLogin() {
		typeTextIntoAField("admin", By.id("login"));
		typeTextIntoAField("password", By.id("password"));
		clickElement(By.name("commit"));
		return new BacklogPage(driver);
	}

}
