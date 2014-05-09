package selenium.tests;

import org.junit.Before;
import org.junit.Test;
import selenium.pages.BacklogPage;
import selenium.pages.LoginPage;
import selenium.pages.StoryEstimate;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static selenium.pages.StoryEstimate.ONE;

public class BacklogPageTest extends SeleniumBasedTest {
    private BacklogPage backlogPage;

    @Before
    public void getToTheBacklogPage() {
        LoginPage loginPage = LoginPage.open(driver);
        backlogPage = loginPage.correctLogIn();
    }

    @Test
    public void shouldBePossibleToAddNewBacklogItem() {
        //given
        String title = UUID.randomUUID().toString();
        String description = "That is the best story";

        //when
        backlogPage = backlogPage.clickAddItemButton();
        backlogPage = backlogPage.fillUserStoryDetailsAndClickCreate(title, description);

        //then
        assertThat(backlogPage.isBacklogItemWithTitlePresent(title), is(true));
    }

}
