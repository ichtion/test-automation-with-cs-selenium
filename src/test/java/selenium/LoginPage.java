package selenium;

import junitparams.Parameters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page{
	private String baseUrl;
	
	public LoginPage(WebDriver driver){
		super(driver);
	}
	
	public LoginPage open() {
		
		baseUrl = "https://training.bananascrum.com/";
		driver.get(baseUrl);
		return this;
	}

	public BacklogPage correctLogIn() {
		typeTextIntoField("admin", By.id("login"));
		typeTextIntoField("password", By.id("password"));
		clickElement(By.name("commit"));
		return new BacklogPage(driver);
	}
	public LoginPage inCorrectLogIn(String login, String pw) {
		typeTextIntoField(login, By.id("login"));
		typeTextIntoField(pw, By.id("password"));
		clickElement(By.name("commit"));
		return this;
	}

	public boolean isOpen() {
	
	  return driver.findElement(By.id("login")).isDisplayed();
	}

	
	
	
	

}
