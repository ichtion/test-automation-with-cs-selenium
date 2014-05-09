package selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import junitparams.JUnitParamsRunner;
import static junitparams.JUnitParamsRunner.$;
import junitparams.Parameters;

import org.junit.*;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
@RunWith(JUnitParamsRunner.class)


public class loginIncorrectParameters {
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
	@Parameters({
		"wrongLogin, wrongPassword",
		"admin, wrongPassword",
		"wrongLogin, password",

	})

/*	@Parameters(method = "credentialValues")
	private Object[] credentialValues() {
        return $(
                     $("admin", "wrongPassword"),
                     $("admin", ""),
                     $("", "password"),
                     $("admin", "password")
                );
    }
	*/
	
	public void shouldNotLoginWhenCredentialIsNotCorrect(String login, String password) throws Exception {
		// given
		openLoginPage();

		// when
		//provideIncorrectCredentialsAndCommitWithParameters();
		provideIncorrectCredentialsAndCommitWithParameters(login, password);
		

		// then
		assertThatYouDidNotLoggedIn();
	}

	public void assertThatYouDidNotLoggedIn() {
		assertEquals("Forgot your password? Click here.", driver.findElement(By.id("forgot-password")).getText());
	}

	public void openLoginPage() {
		driver.get(baseUrl + "login");
	}

	public void provideIncorrectCredentialsAndCommitWithParameters(String login, String password) {
		driver.findElement(By.id("login")).clear();
		driver.findElement(By.id("login")).sendKeys(login);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("commit")).click();
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
