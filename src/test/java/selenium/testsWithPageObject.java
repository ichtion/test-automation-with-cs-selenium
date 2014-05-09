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

	public void assertThatYouLoggedInProperly() {
		assertEquals("Admin", driver.findElement(By.id("admin")).getText());
	}
	
	public void assertThatYouNotLoggedInProperly() {
		assertEquals("Login failed", driver.findElement(By.id("flash")).getText());
	}

	public void openLoginPage() {
		driver.get(baseUrl + "login");
	}

	public void provideCredentialsAndCommit(String login, String password) {
		typeTextIntoAField(login, By.id("login"));
		typeTextIntoAField(password, By.id("password"));
		driver.findElement(By.name("commit")).click();
	}

	public void typeTextIntoAField(String text, By location) {
		driver.findElement(location).clear();
		driver.findElement(location).sendKeys(text);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
