package selenium;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import static org.openqa.selenium.remote.DesiredCapabilities.*;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumHelper {
	protected WebDriver driver;
	protected ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<WebDriver>();

    @Before
    public void setUp() throws Exception {
        threadLocalWebDriver.set(new RemoteWebDriver(new URL("http://localhost:6666/wd/hub"), new DesiredCapabilities(firefox(), chrome(), internetExplorer())));
        driver = threadLocalWebDriver.get();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
