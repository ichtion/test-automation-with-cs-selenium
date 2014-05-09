package selenium;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.System.out;

public class BacklogPage extends Page {
	
	double random = Math.random();
	
	public BacklogPage(WebDriver driver) {
		super(driver);
	}

	public boolean loggedIn() {
		
		return (driver.findElement(By.id("admin")).getText() == "admin");
	}

	public void addItem() {

		driver.findElement(By.linkText("Add item")).click();
		driver.findElement(By.name("item[user_story]")).sendKeys("pawel_item_automat" + random);
		driver.findElement(By.name("commit")).click();
		
	}

	public boolean itemAdded() {
		String title = "pawel_item_automat";
		WebElement item = driver.findElement(By.xpath("//div[text()='" + title + "']"));
		return true;
		
	}

}
