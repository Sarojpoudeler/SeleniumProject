package SeleniumProject.tests;


import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumProject.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;



public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName = "ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup(); // calling chromedriver usingwebdriver manager
		WebDriver driver = new ChromeDriver();
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		
		driver.get("https://rahulshettyacademy.com/client/");
		
		LandingPage landingpage = new LandingPage(driver);  // creating object for class
		
		driver.findElement(By.id("userEmail")).sendKeys("saroj.poudel@yopmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("SAroj@123");
		
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
	   
		
		WebElement prod = products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match =cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		
		assertEquals(true, match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "nepal").build().perform();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(".ta-results")));
		
		//driver.findElement(By.cssSelector("button:nth-child(2) span:nth-child(1)")).click();
		//System.out.println("hello");
		
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[1]")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmedMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmedMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();

		
		
	}

}
