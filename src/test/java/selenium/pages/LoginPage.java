package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.name;

public class LoginPage extends Page {
    private static String baseUrl = "https://training.bananascrum.com/";

    private final By userNameInputField = id("login");
    private final By passwordInputField = id("password");
    private final By loginButton = name("commit");

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";

    private LoginPage(WebDriver driver) {
        super(driver);
    }

    public static LoginPage open(WebDriver driver) {
        driver.get(baseUrl);
        return new LoginPage(driver);
    }

    public BacklogPage correctLogIn() {
        loginWithCredentials(ADMIN_USERNAME, ADMIN_PASSWORD);
        return new BacklogPage(driver);
    }

    private void loginWithCredentials(String username, String password) {
        typeTextIntoAField(username, userNameInputField);
        typeTextIntoAField(password, passwordInputField);
        clickElement(loginButton);
    }

    public LoginPage tryToLogin(String userName, String password) {
        loginWithCredentials(userName, password);
        return this;
    }

    public boolean isOpen() {
        return isElementPresent(userNameInputField) && isElementPresent(passwordInputField);
    }
}
