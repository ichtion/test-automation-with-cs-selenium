package selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.DesiredCapabilities.*;

public class SeleniumBasedTest {

    protected WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = new RemoteWebDriver(new URL("http://localhost:6666/wd/hub"), firefox());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
