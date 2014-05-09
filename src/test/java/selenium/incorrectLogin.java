package selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class incorrectLogin {
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
	public void shouldLoginAsAdmin() throws Exception {
		// given
		openLoginPage();

		// when
		provideCredentialsAndCommit();

		// then
		assertThatYouLoggedInProperly();
	}

	public void assertThatYouLoggedInProperly() {
		assertEquals("Admin", driver.findElement(By.id("admin")).getText());
	}

	public void openLoginPage() {
		driver.get(baseUrl + "login");
	}

	public void provideCredentialsAndCommit() {
		typeTextIntoAField();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.name("commit")).click();
	}

	public void typeTextIntoAField() {
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys("admin");
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
