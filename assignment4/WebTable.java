package week4.day2.assignment4;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTable {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Get the count of number of columns
		List<WebElement> columnSize = driver.findElements(By.tagName("th"));
		int colSize = columnSize.size();
		System.out.println("Column Size " + colSize);
		System.out.println("*************************************");
		// Get the count of number of rows
		List<WebElement> tableRows = driver.findElements(By.xpath("//table//tr"));
		int rowSize = tableRows.size();
		System.out.println("No of Rows " + rowSize);
		System.out.println("*************************************");
		// Get the progress value of 'Learn to interact with Elements'
		List<WebElement> element = driver.findElements(By.xpath("//tr/td[1]/font"));
		List<WebElement> element2 = driver.findElements(By.xpath("//tr/td[2]/font"));
		System.out.println("progress value of 'Learn to interact with Elements:'");
		for (int i = 1; i < element.size() - 1; i++) {
			System.out.println(element.get(i).getText() + " - " + element2.get(i).getText());
		}
		System.out.println("*************************************");
		// System.out.println("progress value of all are : ");
		// Check the vital task for the least completed progress.
		List<WebElement> progress = driver.findElements(By.xpath("//tr/td[2]/font"));
		List<Integer> ProgressValue = new ArrayList<Integer>();
		for (int i = 0; i < progress.size(); i++) {
			String text = progress.get(i).getText();
			int value = Integer.parseInt(text.substring(0, text.length() - 1));
			ProgressValue.add(value);
		}
		Collections.sort(ProgressValue);
		int val = ProgressValue.get(0);

		for (int i = 1; i <= progress.size(); i++) {
			String value = driver.findElement(By.xpath("(//tr)[" + (i + 1) + "]/td[2]/font")).getText();
			if (value.contains(Integer.toString(val))) {
				driver.findElement(By.xpath("(//tr)[" + (i + 1) + "]/td[3]/input")).click();
				System.out.println(driver.findElement(By.xpath("(//tr)[" + (i + 1) + "]/td[1]/font")).getText());
				break;
			}

		}
	}
}
