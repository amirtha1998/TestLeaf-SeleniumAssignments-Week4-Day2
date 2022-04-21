package week4.day2.assignment1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Resizable {

	public static void main(String[] args) {
		// Assignment 1: (Actions)
		// 1.https://jqueryui.com/resizable
		// 2.http://www.leafground.com/pages/drag.html
		// 3.http://www.leafground.com/pages/drop.html
		// 4.http://www.leafground.com/pages/selectable.html
		// 5.http://www.leafground.com/pages/sortable.html

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/resizable");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.switchTo().frame(0);

		WebElement element = driver.findElement(
				By.xpath("//div[@Class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
		// System.out.println(element.getSize());
		Actions action = new Actions(driver);
		action.clickAndHold(element).moveByOffset(10, 80).perform();
	}

}
