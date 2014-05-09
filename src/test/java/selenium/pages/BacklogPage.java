package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.Callable;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;

public class BacklogPage extends Page {
    private By addItemButton = By.className("new-backlog-item");

    private By userStoryTitleInputField = By.id("item_user_story");
    private By userStoryDescriptionInputField = By.id("item_description");
    private By createUserStoryButton = By.name("commit");

    public BacklogPage(WebDriver driver) {
        super(driver);
    }

    public boolean isOpen() {
        return isElementPresent(By.id("admin"));
    }

    public BacklogPage clickAddItemButton() {
        clickElement(addItemButton);
        return this;
    }

    public BacklogPage fillUserStoryDetailsAndClickCreate(String title, String description) {
        typeTextIntoAField(title, userStoryTitleInputField);
        typeTextIntoAField(description, userStoryDescriptionInputField);
        clickElement(createUserStoryButton);
        return this;
    }

    public boolean isBacklogItemWithTitlePresent(String title) {
        return isItemWithTitlePresent(title);
    }

    private Boolean isItemWithTitlePresent(String title) {
        WebElement backlogItem = driver.findElement(By.xpath("//div[text()='" + title + "']"));
        if (null != backlogItem) return true;
        return false;
    }
}
