package week4.day2.assignment4;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sortable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/sortable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.switchTo().frame(0);

		WebElement item1 = driver.findElement(By.xpath("//li[contains(text(),'Item 1')]"));
		WebElement item2 = driver.findElement(By.xpath("//li[contains(text(),'Item 2')]"));
		WebElement item3 = driver.findElement(By.xpath("//li[contains(text(),'Item 3')]"));
		WebElement item5 = driver.findElement(By.xpath("//li[contains(text(),'Item 5')]"));
		WebElement item6 = driver.findElement(By.xpath("//li[contains(text(),'Item 6')]"));
		WebElement item7 = driver.findElement(By.xpath("//li[contains(text(),'Item 7')]"));

		Actions builder = new Actions(driver);
		builder.dragAndDrop(item1, item7).pause(2000).dragAndDrop(item2, item6).pause(2000).dragAndDrop(item3, item5)
				.pause(1000).dragAndDrop(item5, item3).pause(2000).dragAndDrop(item6, item2).pause(2000)
				.dragAndDrop(item7, item1).perform();

	}

}
