package Academy;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> cartProducts;
	
	public OrderPage(WebDriver driver) {
		super(driver);	
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyOrderDisplayed(String productName) {
		Boolean match=cartProducts.stream().anyMatch(product->product.getText().equals(productName));
		return match;
		
	}
}
