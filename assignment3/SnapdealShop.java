package week4.day2.assignment3;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapdealShop {

	public static void main(String[] args) throws InterruptedException, IOException {
		// 1. Launch https://www.snapdeal.com/
		WebDriverManager.chromedriver().setup();

		// to disable notification
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");

		ChromeDriver driver = new ChromeDriver(option);
		driver.get("https://www.snapdeal.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// 2. Go to Mens Fashion
		driver.findElement(By.xpath("//span[contains(text(),'Men')]")).click();

		// 3. Go to Sports Shoes
		driver.findElement(By.xpath("//span[contains(text(),'Sports Shoes')]")).click();

		// 4. Get the count of the sports shoes
		String text = driver.findElement(By.xpath("//span[@Class='category-name category-count']")).getText();

		text = text.substring(2, 7);
		System.out.println("No of Sports Shoes are " + text);
		Thread.sleep(1000);

		// 5. Click Training shoes
		driver.findElement(By.xpath("//div[contains(text(),'Training Shoes')]")).click();
		Thread.sleep(1000);
		// 6. Sort by Low to High
		driver.findElement(By.xpath("//i[@Class='sd-icon sd-icon-expand-arrow sort-arrow']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ul[@Class='sort-value']/li[2]")).click();
		Thread.sleep(2000);

		// 7. Check if the items displayed are sorted correctly

		List<WebElement> element = driver.findElements(By.xpath("//span[@Class='lfloat product-price']"));
		// System.out.println(element.size());

		List<String> stringList = new ArrayList<String>();

		for (WebElement ele : element) {
			stringList.add(ele.getText().toString().substring(4));
			// System.out.println(ele.getText().substring(4));
		}
		List<String> target = new ArrayList<String>(stringList);
		Collections.sort(target);
		boolean sorted = target.equals(stringList);
		// System.out.println(sorted);

		if (sorted)
			System.out.println("validation passed");
		else {
			System.out.println("validation failed");
		}
		// 8.Select the price range (900-1200)
		driver.findElement(By.xpath("//input[@Name='fromVal']")).clear();
		driver.findElement(By.xpath("//input[@Name='fromVal']")).sendKeys("900");
		driver.findElement(By.xpath("//input[@Name='toVal']")).clear();
		driver.findElement(By.xpath("//input[@Name='toVal']")).sendKeys("2000");
		driver.findElement(By.xpath("//div[@Class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
		Thread.sleep(1000);

		// 9.Filter with color Navy
		// driver.findElement(By.xpath("//span[@Class='filter-color-tile Navy ']"));
		WebElement navyColor = driver.findElement(By.xpath("//label[@for='Color_s-Navy']"));
		boolean navyFlag = navyColor.isEnabled();
		if (navyFlag) {
			navyColor.click();
			//System.out.println("Navy filter is used");
		} else {
			System.out.println("Navy color is disabled");
		}
		Thread.sleep(2000);

		// 10 verify the all applied filters
		String price = driver.findElement(By.xpath("//div[@class='navFiltersPill']/a")).getText();
		String color = driver.findElement(By.xpath("//div[@class='navFiltersPill'][2]/a")).getText();
		if ((price.equals("Rs. 900 - Rs. 1949")) && (color.equals("Navy") || navyFlag == false)) {
			System.out.println("All filters are applied");
		} else {
			System.out.println("Missing filters");
		}
//System.out.println(color);
//System.out.println(price);
		// 11. Mouse Hover on first resulting Training shoes & // 12. click QuickView
		// button
		WebElement element2 = driver.findElement(By.xpath("//picture[@Class='picture-elem'][1]/img"));
		WebElement quickView = driver.findElement(By.xpath("//div[contains(text(),'Quick View')]"));

		Actions builder = new Actions(driver);
		builder.moveToElement(element2).pause(1000).click(quickView).perform();

		// 13. Print the cost and the discount percentage
		System.out.println("Price: " + driver
				.findElement(By.xpath("//div[@Class='product-price pdp-e-i-PAY-l clearfix']/span[@Class='payBlkBig']"))
				.getText());
		System.out.println("Discount: " + driver
				.findElement(
						By.xpath("//div[@Class='product-price pdp-e-i-PAY-l clearfix']/span[@Class='percent-desc ']"))
				.getText());

		// 14. Take the snapshot of the shoes.
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./screenshots/shoe.png");
		FileUtils.copyFile(source, destination);

		// 15. Close the current window
		driver.findElement(By.xpath("//div[@class='close close1 marR10']/i")).click();

		// 16.Close the main window
		driver.close();
	}

}
