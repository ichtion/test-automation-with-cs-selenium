package selenium;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(JUnitParamsRunner.class)
public class IdeTest extends SeleniumHelper{
//	private WebDriver driver;
//	protected ThreadLocal<WebDriver> 
//
//	@Before
//	public void setUp() throws Exception {
//		driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	}

	@Test
	public void shouldBePossibleToLoginWithCorrectAdminCredidentials()
			throws Exception {
		// given
		LoginPage loginPage = new LoginPage(driver);
		loginPage.open();
		// when
		BacklogPage backlogPage = loginPage.loginWithCorrectAdminCredentials();
		// then
		assertTrue(backlogPage.isOpen());
	}

	@Test
	@Parameters(method = "loginValues")
	public void shouldNotBePossibleToLoginWithWrongCredentials(String login,
			String password) throws Exception {
		// given
		LoginPage loginPage = new LoginPage(driver);
		loginPage.open();
		// when
		LoginPage reloadedLoginPage = loginPage.loginWithInCorrectCredentials(
				login, password);
		// then
		assertTrue(reloadedLoginPage.isOpen());
	}

	private Object loginValues() {
		return $($("admin", "test"), $("admin", "dupa1224"), $("admin", ""));
	}

//	@After
//	public void tearDown() throws Exception {
//		driver.quit();
//	}
}
