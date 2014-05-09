package selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BacklogPage extends Page {

	public BacklogPage(WebDriver driver) {
		super(driver);
	}

	public boolean isOpen() {
		return isElementPresent(By.id("admin"));
	}

	public void addNewBackLogItem(String itemName) {
		clickElement(By.className("new-backlog-item-button"));
		typeTextIntoAField(itemName, By.id("item_user_story"));
		typeTextIntoAField("descritpion", By.id("item_description"));
		clickElement(By.name("commit"));
	}

	public boolean itemExists(String itemName) {
		String xpath = String.format("//*[contains(text(), '%s')]", itemName);
		return isElementPresent(By.xpath(xpath));
	}

	public List<WebElement> getItemList() {
		List<WebElement> elements = driver.findElements(By
				.xpath("//div[@class='item-user-story highlight']"));
		for (WebElement element : elements) {
			System.out.println(element.getText());
		}
		return elements;

	}

}
