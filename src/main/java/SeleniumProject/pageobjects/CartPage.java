package SeleniumProject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumProject.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	public CartPage(WebDriver driver) 
	{
		super(driver);
		//initialization driver
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	@FindBy(css = ".totalRow button")
	WebElement checkOutEle;
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> productTitles;
	
	public Boolean VerifyProductDisplay(String productName)
	{
		boolean match =productTitles.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goTocheckout()
	{
		checkOutEle.click();
		return new CheckoutPage(driver);
	}
	

}
