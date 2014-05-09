package selenium.tests;

import org.junit.Test;
import selenium.pages.BacklogPage;
import selenium.pages.LoginPage;

import static org.junit.Assert.assertTrue;

public class LoginPositiveTest extends SeleniumBasedTest {
    @Test
    public void shouldBePossibleToCorrectLogInAsAnAdmin() {
        // given
        LoginPage loginPage = LoginPage.open(driver);
        // when
        BacklogPage backlogPage = loginPage.correctLogIn();
        // then
        assertTrue(backlogPage.isOpen());
    }
}
