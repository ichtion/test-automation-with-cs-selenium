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

import static junitparams.JUnitParamsRunner.$;

@RunWith(JUnitParamsRunner.class)
public class SimpleTest {

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
	@Parameters(method = "allLoginTestScenarios")
	public void shouldNotLoginAsAdminWithWrongCredentials(String admin, String password) throws Exception {
		openLoginPage();

		tryTologinWithInCorrectAdminCredentials(admin, password);

		assertThatLoginFailed();

	}
	
	private Object[] allLoginTestScenarios(){
		
		return $(
                $("admin", "philFailed"),
                $("Zoran", "password"),
                $("", ""),
                $("admin", "")
                
           );

	}
	

	private void assertThatYouAreLoggedInAsAdmin() {
		assertEquals("Admin", driver.findElement(By.id("admin")).getText());
	}

	private void openLoginPage() {

		driver.get(baseUrl + "login");
	}

	private void tryTologinWithInCorrectAdminCredentials(String admin, String password) throws Exception {

		typeTextIntoField(admin, By.id("login"));
		typeTextIntoField(password, By.id("password"));
		clickElement(By.name("commit"));
	}

	private void assertThatLoginFailed() {
		String userMessage = driver.findElement(By.id("flash")).getText();
		assertEquals(userMessage, "Login failed");

	}
	
    private void logout() {
		clickElement(By.linkText("Logout"));

	}

	private void typeTextIntoField(String Text, By locator) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(Text);
	}

	private void clickElement(By locator) {
		driver.findElement(locator).click();
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
