package selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.openqa.selenium.By.id;

public class RecordedTest {
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
        //given
        openLoginPage();

        //when
        loginWithCorrectAdminCredentials();

        //then
        assertThatYouAreLoggedInAsAdmin();
    }

    @Test
    public void shouldNotLoginWithIncorrectAdminCredentials() {
        //given
        openLoginPage();

        //when
        loginWithIncorrectAdminCredentials();

        //then
        assertThatLoginFailed();
    }

    private void assertThatLoginFailed() {
        String userMessage = driver.findElement(id("flash")).getText();
        assertThat(userMessage, is(equalTo("Login failed")));
    }

    private void assertThatThereIsNoElementWithNameAdmin() {
        assertThat(isElementPresent(id("admin")), is(equalTo(false)));
    }

    private void loginWithIncorrectAdminCredentials() {
        typeTextIntoAField("admin", id("login"));
        typeTextIntoAField("incorrectPassword", id("password"));
        clickElement(By.name("commit"));
    }

    private void assertThatYouAreLoggedInAsAdmin() {
        assertEquals("Admin", driver.findElement(id("admin")).getText());
    }

    private void openLoginPage() {
        driver.get(baseUrl + "login");
    }

    private void loginWithCorrectAdminCredentials() {
        typeTextIntoAField("admin", id("login"));
        typeTextIntoAField("password", id("password"));
        clickElement(By.name("commit"));
    }

    private void clickElement(By locator) {
        driver.findElement(locator).click();
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
