package selenium;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import junitparams.JUnitParamsRunner;

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
public class IdeTest {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://training.bananascrum.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void shouldBePossibleToLogin() throws Exception {
		//given
		LoginPage loginPage = new LoginPage(driver);
		loginPage.open();
		//when
		BacklogPage backlogPage = loginPage.loginWithCorrectAdminCredentials();
		//then
		assertTrue(backlogPage.isOpen());
	}

//	@Test
//	//@Parameters({ "admin, dup1234", "admin1, password" })
//	@Parameters(method="loginValues")
//	public void shouldNotBePossibleToLoginWithWrongCredentials(String login,
//			String password) throws Exception {
//		openLoginPage();
//		incorrectLoginWithAdminCredentials(login, password);
//		assertThatYouAreNotLogedIn();
//	}

	private void incorrectLoginWithAdminCredentials(String login,
			String password) {
		typeTextIntoAField(login, By.id("login"));
		typeTextIntoAField(password, By.id("password"));
		clickElement(By.name("commit"));

	}
	
	private Object loginValues(){
		return $(
				$("admin","test"),
				$("admin","dupa1224"),
				$("admin",""));
	}

	private void assertThatYouAreLogedIn() {
		assertEquals("Admin", driver.findElement(By.id("admin")).getText());
	}

	private void assertThatYouAreNotLogedIn() {
		assertEquals(true, driver.findElement(By.name("commit")).isDisplayed());
	}

	private void openLoginPage() {
		driver.get(baseUrl + "/login");
	}

	private void correctLogout() {
		clickElement(By.linkText("Logout"));
	}

	private void clickElement(By locator) {
		driver.findElement(locator).click();
	}

	private void correctLoginWithAdminCredentials() {
		typeTextIntoAField("admin", By.id("login"));
		typeTextIntoAField("password", By.id("password"));
		clickElement(By.name("commit"));
	}

	private void typeTextIntoAField(String text, By locator) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(text);
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
