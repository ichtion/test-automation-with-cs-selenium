package selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

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
		openLoginPage();
		correctLoginWithAdminCredentials();
		assertThatYouAreLogedIn();
	}
	
	@Test
	public void shouldNotBePossibleToLoginWithWrongCredentials() throws Exception {
		openLoginPage();
		incorrectLoginWithAdminCredentials();
		assertThatYouAreNotLogedIn();
	}

	private void incorrectLoginWithAdminCredentials() {
		typeTextIntoAField("admin", By.id("login"));
		typeTextIntoAField("dupa1234", By.id("password"));
		clickElement(By.name("commit"));
		
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
