package Academy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])")
	WebElement pressOrder;
	
	@FindBy(css = ".action__submit")
	WebElement actionSubmit;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;

	private By results=By.cssSelector(".ta-results");

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void selectCountry(String countryName) {
		Actions a=new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
		
	}
	
	public void placeOrder() {
		pressOrder.click();
	}
	
	public ConfirmationPage actionSumbit() {
		actionSubmit.click();
		return new ConfirmationPage(driver);
	}
}
