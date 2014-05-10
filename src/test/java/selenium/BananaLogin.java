package selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class BananaLogin {
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
	public void shouldLoginAsAdminWithAppropriateCredentials() throws Exception {
		openLoginPage();

		loginWithCorrectAdminCredentials();

		assertThatYouAreLoggedInAsAdmin();
		

	}

	private void assertThatYouAreLoggedInAsAdmin() {
		assertEquals("Admin", driver.findElement(By.id("admin")).getText());
	}

	private void openLoginPage() {

		driver.get(baseUrl + "login");
	}

	private void loginWithCorrectAdminCredentials() throws Exception {
	
		typeTextIntoField("admin", By.id("login"));
		typeTextIntoField("admin", By.id("login"));
		clickElement(By.name("commit"));
		
	
	}
	
	private void assertThatLoginFailed()
	{
		String userMessage = driver.findElement(By.id("flash")).getText();
		assertEquals(userMessage, "Login failed");
		
		
	}
	
   

	private void logout() {
		clickElement(By.linkText("Logout"));

	}

	private void typeTextIntoField(String Text, By locator) {
		driver.get(baseUrl + "login");
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
