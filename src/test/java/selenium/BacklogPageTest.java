package selenium;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import junitparams.JUnitParamsRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(JUnitParamsRunner.class)
public class BacklogPageTest extends SeleniumHelper {
	private WebDriver driver;

//	@Before
//	public void setUp() throws Exception {
//		driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//	}
	
	@Test
	public void shouldBePossibleToAddNewItemToBacklog()
			throws Exception {
		// given
		LoginPage loginPage = new LoginPage(driver);
		loginPage.open();
		BacklogPage backlogPage = loginPage.loginWithCorrectAdminCredentials();
		// when
		String itemName = generateItemName();
		backlogPage.addNewBackLogItem(itemName);
		// then
		assertTrue(backlogPage.itemExists(itemName));
	}
	
	private String generateItemName() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date d = new Date();
		return "PiotrM " + df.format(d);
	}

//	@After
//	public void tearDown() throws Exception {
//		driver.quit();
//	}
}
