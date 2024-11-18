package Academy;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class CartPage extends AbstractComponent{
	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyProductDisplayed(String productName) {
		Boolean match=cartProducts.stream().anyMatch(product->product.getText().equals(productName));
		return match;
		
	}
	
	public CheckOutPage goToCheckOut() {
		checkOut.click();
		return new CheckOutPage(driver);
	}
	
}
