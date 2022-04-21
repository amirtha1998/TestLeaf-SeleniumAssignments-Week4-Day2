package week4.day2.assignment2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NykaaShop {
public static void main(String[] args) throws InterruptedException {
	
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	driver.get("https://www.nykaa.com/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	/*
	 * 1) Go to https://www.nykaa.com/ 
	 * 2) Mouseover on Brands and Search L'Oreal Paris 
	 * 3) Click L'Oreal Paris 
	 * 4) Check the title contains L'Oreal Paris(Hint-GetTitle) 
	 * 5) Click sort By and select customer top rated 
	 * 6) Click Category and click Hair->Click haircare->Shampoo 
	 * 7) Click->Concern->Color Protection 
	 * 8)check whether the Filter is applied with Shampoo 
	 * 9) Click on L'Oreal Paris Colour Protect Shampoo 
	 * 10) GO to the new window and select size as 175ml 
	 * 11) Print the MRP of the product 
	 * 12) Click on ADD to BAG 
	 * 13) Go to Shopping Bag 
	 * 14) Print the Grand Total amount 
	 * 15) Click Proceed 
	 * 16) Click on Continue as Guest 
	 * 17) Check if this grand total is the same in step 14 
	 * 18)Close all windows
	 */
	
	WebElement element = driver.findElement(By.xpath("//ul[@Class='HeaderNav css-f7ogli'][2]//a"));
	
	Actions builder=new Actions(driver);
	
	builder.moveToElement(element).pause(2000).click(driver.findElement(By.xpath("//ul[@Class='l-vertical-list']/li[5]//img"))).perform();
	
	String title = driver.getTitle();
	if(title.contains("L'Oreal Paris")) System.out.println("Title contains L'Oreal Paris");
	else	System.out.println("Title doesnot contain L'Oreal Paris");
	
	builder.click(driver.findElement(By.xpath("//div[@Id='list-wrapper']//span"))).perform();
	builder.click(driver.findElement(By.xpath("//ul[@Class='css-z5o5ca']/div[4]//span"))).perform();
	//builder.click(driver.findElement(By.xpath("//div[@Class='filter-open css-1kwl9pj']/span"))).perform();//Category
	builder.click(driver.findElement(By.xpath("//span[contains(text(),'Category')]"))).perform();

	builder.click(driver.findElement(By.xpath("//span[contains(text(),'Hair')]"))).pause(1000).perform();
	builder.click(driver.findElement(By.xpath("//span[contains(text(),'Hair Care')]"))).pause(1000).perform();
	builder.click(driver.findElement(By.xpath("//span[contains(text(),'Shampoo')]"))).pause(1000).perform();
	Thread.sleep(1000);
	//builder.click(driver.findElement(By.xpath("//span[contains(text(),'Hair')]"))).perform();
	builder.click(driver.findElement(By.xpath("//span[contains(text(),'Concern')]"))).perform();
	builder.click(driver.findElement(By.xpath("//span[contains(text(),'Color Protection')]"))).perform();	

	String text = driver.findElement(By.xpath("//div[@Class='css-1emjbq5']//span[contains(text(),'Shampoo')]")).getText();
	if(text.contains("Shampoo"))	System.out.println("Filters applied with Shampoo");
	else	System.out.println("Filters not applied with Shampoo");
	
	builder.click(driver.findElement(By.xpath("//div[@Class='css-43m2vm']"))).perform();
	

	Set<String> allHandles = driver.getWindowHandles();
	List<String> listHandles = new ArrayList<String>(allHandles);
	
	driver.switchTo().window(listHandles.get(1));
	
	WebElement eleSource = driver.findElement(By.xpath("//select[@Class='css-2t5nwu']"));
	Select dd = new Select(eleSource);
	dd.selectByVisibleText("175ml");
	
	String text2 = driver.findElement(By.xpath("//span[@Class='css-1jczs19']")).getText();
	System.out.println("MRP of L'Oreal Paris Colour Protect Shampoo is "+text2);
	
	driver.findElement(By.xpath("//span[contains(text(),'ADD TO BAG')]")).click();
	
	driver.findElement(By.xpath("//button[@Class='css-g4vs13']")).click();
	
	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[1]")));
	
	String text3 = driver.findElement(By.xpath("//div[@Class='first-col']/div")).getText();
	String t=text3.substring(1);
	System.out.println("Grand Total Amount is "+t);
	//button[@Class='btn full fill no-radius proceed ']//span[@Class='vernacular-string']
	driver.findElement(By.xpath("//button[@Class='btn full fill no-radius proceed ']")).click();
	driver.findElement(By.xpath("//button[contains(text(),'CONTINUE AS GUEST')]")).click();
	
	String text4 = driver.findElement(By.xpath("//div[@Class='payment-details-tbl grand-total-cell prl20']//span")).getText();
	//System.out.println(text4);
	if(t.equals(text4)) System.out.println("Grand Total in both are same");
	else	System.out.println("Not Valid");
	
	driver.close();
	driver.switchTo().window(listHandles.get(0));
	driver.close();
}
}
