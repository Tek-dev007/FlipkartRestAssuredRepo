package flipkart_Project;

import java.io.File;
import java.time.Duration;
 

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FlipKart_Assgn {

	public static void main(String[] args) {
				
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

	        try {
	         
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	            
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));	       
	            driver.get("https://www.flipkart.com/");
	           // for input element searchbox 
	            driver.findElement(By.xpath("//input[@class='Pke_EE']")).sendKeys("Laptop");
	            driver.findElement(By.xpath("//button[@type='submit']")).click();
	       
	            // Select min price range from dropdown filter
	            WebElement dropdown=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@class='Gn+jFg']")));
                Select select = new Select(dropdown);
	            select.selectByVisibleText("₹50000");
	       
	            // Select max parice range from dropdown filter
	            dropdown= driver.findElement(By.xpath("//div[@class='tKgS7w']/select"));
	            Select select2 = new Select(dropdown);
	            Thread.sleep(3000);
	            select2.selectByVisibleText("₹75000");
	          
	            // Select the product brand 
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Brand']"))).click();
	            driver.findElement(By.xpath("//div[text()='ASUS']")).click();
	            Thread.sleep(3000);
	            // Select the product from the filter result, verify product name and price
	            String mainTab = driver.getWindowHandle();
	            WebElement element=  driver.findElement(By.xpath("//div[@data-id='COMHY74JXH3KZRWM']//div[@class='col col-7-12']//div[@class='KzDlHZ']"));
	            String productName= element.getText();
	            element.click();
	            System.out.println("ProductName:"+productName);
	            
	            element=  driver.findElement(By.xpath("//div[@data-id='COMHY74JXH3KZRWM']//div[@class='col col-5-12 BfVC2z']//div[@class='Nx9bqj _4b5DiR']"));
	            String productPrice= element.getText();
	            System.out.println("ProductPrice:"+productPrice);
	             // using the for loop for handleing new tab   
	            for (String tabHandle : driver.getWindowHandles()) {
	                if (!tabHandle.equals(mainTab)) {
	                    driver.switchTo().window(tabHandle);  // Switch to the new tab
	                    break;
	                }
	            }
	            //element handling by the new tab
	            System.out.println("New Tab Title: " + driver.getTitle());
	            // check the productTitlename again in new tab
	            String productNewTabTitle=	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='VU-ZEz']"))).getText().toString();
  	            System.out.println("ProductNewTabTitle: " + productNewTabTitle);
	            // check the ProductPrice again in new tab
	            String ProductNewTabPrice = driver.findElement(By.xpath("//div[@class='Nx9bqj CxhGGd']")).getText();
	            System.out.println("ProductNewTab Price: " + ProductNewTabPrice);
	            // verify the ProductTitlename and Product price
 	            Assert.assertEquals(productNewTabTitle.contains("ASUS TUF Gaming F17 - AI Powered Gaming Intel Core i5 11th Gen 11400H - (16 GB/512 GB SSD/Windows 11"), true, "Product title doesn't contain");
	            Assert.assertEquals(productPrice.contains(ProductNewTabPrice), true, "Product price doesn't contain");
  	          
	            // put the pin code and click
	            driver.findElement(By.id("pincodeInputId")).sendKeys("211001");
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//span[@class='i40dM4']")).click();
	            Thread.sleep(2000);
	            // click add to cart
	            driver.findElement(By.xpath("//button[@class='QqFHMw vslbG+ In9uk2']")).click();
	            Thread.sleep(2000);
	            // In addtoCart Page check ProductnameTittle
	            element= driver.findElement(By.xpath("//a[@class='T2CNXf QqLTQ-']"));
	            String AddtoCartName= element.getText();
		        System.out.println("AddtoProductName:"+AddtoCartName);
		        //In addtoCart page check ProductPrice
		        element=  driver.findElement(By.xpath("//span[@class='LAlF6k re6bBo']"));
		        String AddtocartPrice= element.getText();
		        System.out.println("AddtoProductPrice:"+AddtocartPrice);
	            // In addtocart page verify productTitle and and price
		        Assert.assertEquals(productNewTabTitle.contains("ASUS TUF Gaming F17 - AI Powered Gaming Intel Core i5 11th Gen 11400H - (16 GB/512 GB SSD/Windows 11"), true, "Product title doesn't contain");
		        Assert.assertEquals(productPrice.contains(AddtocartPrice), true, "Product price doesn't contain");

		         // Take a screenshot scuessfully added to cart
			    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			    FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"/ScreenshotsFolder/"+"Screenshots.png"));
		          
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           
	     }   
	        
	        System.out.println("----------------------------The END------------------------------");       
	}

}
