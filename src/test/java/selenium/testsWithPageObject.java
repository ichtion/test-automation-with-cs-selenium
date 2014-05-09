package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(JUnitParamsRunner.class)
public class testsWithPageObject {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://training.bananascrum.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test	
	public void shouldLogInWithCorrectCredentials() {
		
		//given
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openLoginPage();

		// when
		BacklogPage backlogPage = loginPage.loginWithCorrectCredentials();

		// then
		backlogPage.loggedIn();
	}
	
	@Test
	@Parameters({ "Admin, password", "admin, bad_password",
		"bad_login, password", })
	public void shouldNotLoginWithIncorrectCredentials(String login,
			String password) {
		
		//given
		LoginPage loginPage = new LoginPage(driver);
		loginPage.openLoginPage();

		// when
		
		loginPage = loginPage.loginWithInorrectCredentials(login, password);

		// then
		loginPage.notLoggedIn();
		
	}


	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
