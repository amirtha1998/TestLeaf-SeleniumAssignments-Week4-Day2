package week4.day2.assignment1;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafgroundSortable {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/sortable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement eleSource = driver.findElement(By.xpath("//li[text()='Item 1']"));
		WebElement eleTarget = driver.findElement(By.xpath("//li[text()='Item 4']"));

		// Create object for Actions class
		Actions builder = new Actions(driver);
		builder.dragAndDrop(eleSource, eleTarget).pause(10000).perform();
	}

}
