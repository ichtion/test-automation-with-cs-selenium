package selenium.tests;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import selenium.pages.LoginPage;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class LoginNegativeTest extends SeleniumBasedTest {

    private Object[] invalidCredentials() {
        return $(
                $("admin", ""),
                $("Admin", "password"),
                $(new String(), new String())
        );
    }
    @Test
    @Parameters(method = "invalidCredentials")
    public void shouldNotLoginWithIncorrectAdminCredentials(String userName, String password) {
        // given
        LoginPage loginPage = LoginPage.open(driver);
        // when
        loginPage = loginPage.tryToLogin(userName, password);
        // then
        assertTrue(loginPage.isOpen());
    }
}
