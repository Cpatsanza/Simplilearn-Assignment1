package amazonweb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class amazonObject {

	public static void main(String[] args) throws InterruptedException {
		// set webdriver properties
		System.setProperty("webdriver.chrome.driver","/home/charlespatsanza/selenium/chromedriver");
		//create webdriver object
		WebDriver driver = new ChromeDriver();
		driver.get("https://amazon.in");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//create webelement dropdown object 
		WebElement searchdropdown = driver.findElement(By.id("searchDropdownBox"));
		//select dropdown
		Select product = new Select(searchdropdown);
		product.selectByValue("search-alias=computers");
		//create webelements for search input text box and search
		WebElement findproduct =driver.findElement(By.cssSelector("input[id='twotabsearchtextbox']"));
		findproduct.sendKeys("lenovo laptops");
		WebElement submitfind =driver.findElement(By.cssSelector("input[id='nav-search-submit-button']"));
		submitfind.click();
		//wait 30 seconds for browser to load all products
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//get the number of search results
		List <WebElement> results = driver.findElements(By.xpath("//div[contains(@cel_widget_id,'MAIN-SEARCH_RESULTS-')]"));
		
		
	     int totalsearch = results.size();
		//validate that search results are available
		if (totalsearch > 0) {
			System.out.println("search criteria returned: "+ totalsearch +" items");
		}else {
			System.out.println("no products available from search criteria");
		}
	
        //CREATE A HASHMAP TO STORE search results with 'product name and price' and print all 'search results' & size of hashmap after storing
	    HashMap<String, String> productALL = new HashMap<String,String>();
		
		for ( WebElement laptop:results) {
		
			String productprice =laptop.findElement(By.className("a-price-whole")).getText();
			String  productNAME = laptop.findElement(By.cssSelector("span[class='a-size-medium a-color-base a-text-normal']")).getText();
			
			productALL.put(productprice,productNAME);
			
			System.out.println(productNAME+"......"+ productprice);
			
			//code to check product link failing-so commented it out and would need help
			/*List<WebElement> allthelinks=driver.findElements(By.tagName("a"));
	
			
			for(WebElement links:allthelinks) {

		    links.getAttribute("href");
			links.click();
			Thread.sleep(8000);
			ArrayList <String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));				
			 String producttitle = driver.getTitle();
			 if (producttitle !=null) {
					System.out.println("...product link is working!!! :)");
		 }else {
			
			 System.out.println("...product link is not working!!! :(");
			 driver.close();*/
		 }			    
			
			
			
			
			 			        
			 
		  
				System.out.println(productALL.size() + " :searched items inserted into hashmap");
		
		
			
			 
		}
	}

