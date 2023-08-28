package SeleniumProject.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import SeleniumProject.TestComponents.BaseTest;
import SeleniumProject.pageobjects.CartPage;
import SeleniumProject.pageobjects.CheckoutPage;
import SeleniumProject.pageobjects.ConfirmationPage;
import SeleniumProject.pageobjects.LandingPage;
import SeleniumProject.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;



public class SubmitOrderTest extends BaseTest{


	
	@Test
	public  void submitOrder() throws IOException
	{
		String productName = "ADIDAS ORIGINAL";
		LandingPage landingPage = launchApplication();
		ProductCatalogue productcatalogue = landingPage.loginApplication("saroj.poudel@yopmail.com", "SAroj@123");
		
		List <WebElement> products = productcatalogue.getProductList();
		productcatalogue.addProducttoCart(productName);
		CartPage cartpage = productcatalogue.gotoCartPage();
		
		Boolean match =cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage =cartpage.goTocheckout();
		checkoutPage.selctCounty("ne");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();

		
		
	}

}
