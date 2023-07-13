package question1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Question1Test {
	private WebDriver driver;
	
	
	public static void main(String[]args) throws Exception {
		Question1Test obj = new Question1Test();
		obj.setUp();
		obj.login();
		obj.featuredItems();
		obj.Navigate();
		obj.CartExecution();
		obj.EnterCardDetails();
		obj.QuitBrowser();
	}
	
	public void setUp() throws Exception {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();	
		driver.get("https://www.automationexercise.com/");
		driver.manage().window().maximize();
		}
	
	public void login() throws Exception {
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("test@mailinator.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("12345");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();	
}
	
	public void featuredItems()throws Exception {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		List<WebElement> featItems = driver.findElements(By.xpath("/html/body/section[2]/div[1]/div/div[2]/div[1]/div"));	
		// Iterate over the featured items
        for (WebElement item : featItems) {
            // Extract the labels
            WebElement nameElement = item.findElement(By.tagName("p"));
            //Print the name and price
            String name = nameElement.getText();
            System.out.println("Label: " + name);   
        }
            int i;
            WebElement priceElement;
            String price;
            String before_xpath= "/html/body/section[2]/div[1]/div/div[2]/div[1]/div[";
        	String after_xpath= "]/div/div[1]/div[1]/h2";
            
            for(i=2;i<=34;i++) {
            	priceElement= driver.findElement(By.xpath(before_xpath + i + after_xpath));
            	price = priceElement.getText();
            	
            	System.out.println("Price: " + price);
            }
            System.out.println("-----------------------");
        }
	
	public void Navigate() {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		WebElement women = driver.findElement(By.xpath("//a[normalize-space()='Tops']"));
		js.executeScript("arguments[0].click()", women);
		
		WebElement tops = driver.findElement(By.xpath("//a[normalize-space()='Tops']"));
		js.executeScript("arguments[0].click()", tops);
		
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		 //Add fancy green to cart
		 WebElement addtocart = driver.findElement(By.xpath("//body[1]/section[1]/div[1]/div[2]/div[2]/div[1]/div[6]/div[1]/div[1]/div[1]/a[1]"));
		 
		 js.executeScript("arguments[0].scrollIntoView();", addtocart);
		 js.executeScript("arguments[0].click();", addtocart);
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("button"))).click();
		 
		 //Add Summer to cart
		 WebElement addtocart2 = driver.findElement(By.xpath("/html/body/section//div[2]/div[2]//div[4]//div[1]/a"));
		 js.executeScript("arguments[0].scrollIntoView();", addtocart2);
		 js.executeScript("arguments[0].click();", addtocart2);	
	}
	public void CartExecution() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//p[@class='text-center']//a")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("/html/body/section/div/section/div[1]/div//a")).click();
		driver.findElement(By.name("message")).sendKeys("Order placed");
		driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
	}
	public void EnterCardDetails() {
		driver.findElement(By.xpath("//input[@name='name_on_card']")).sendKeys("Test Card");
		driver.findElement(By.xpath("//input[@name='card_number']")).sendKeys("4100 0000 0000");
		driver.findElement(By.xpath("//input[@placeholder='ex. 311']")).sendKeys("123");
		driver.findElement(By.xpath("//input[@placeholder='MM']")).sendKeys("01");
		driver.findElement(By.xpath("//input[@placeholder='YYYY']")).sendKeys("1900]");
		driver.findElement(By.xpath("//button[@id='submit']")).submit();
		
	}
	
	public void QuitBrowser() {
		driver.quit();
	}
	
}
		
		
	

