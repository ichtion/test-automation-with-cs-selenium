package selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.*;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static junitparams.JUnitParamsRunner.$;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

@RunWith(JUnitParamsRunner.class)
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
	@Parameters(method = "allLoginTestScenarios")
	public void inCorrectLoginTest(String admin, String password) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.open();

		loginPage.inCorrectLogIn(admin, password);

		assertTrue(loginPage.isOpen());
	}

	private Object[] allLoginTestScenarios() {

		return $($("admin", "philFailed"), $("Zoran", "password"), $("", ""),
				$("admin", "")

		);

	}

	@Test
	public void pageObjectTest() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.open();

		BacklogPage backlogPage = loginPage.correctLogIn();

		assertTrue(backlogPage.isOpen());
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
