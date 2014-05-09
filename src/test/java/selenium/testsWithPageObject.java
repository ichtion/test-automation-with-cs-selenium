package selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.URL;
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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import static org.openqa.selenium.remote.DesiredCapabilities.*;

@RunWith(JUnitParamsRunner.class)
public class testsWithPageObject {
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	protected WebDriver driver;
	protected ThreadLocal<WebDriver> threadLocalWebDriver = 
	new ThreadLocal<WebDriver>();

	@Before
	public void setUp() throws Exception {
		threadLocalWebDriver.set(new RemoteWebDriver(new URL(
				"http://localhost:6666/wd/hub"), new DesiredCapabilities(
				firefox(), chrome(), internetExplorer())));
		driver = threadLocalWebDriver.get();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void shouldLogInWithCorrectCredentials() {

		// given
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

		// given
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
