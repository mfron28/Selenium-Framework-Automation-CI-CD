package Academy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class LandingPage extends AbstractComponent{
	//pageObject doesn't hold any data
	WebDriver driver;
	//parameterized constructor, have the values from the driver from another class
	public LandingPage(WebDriver driver) {
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
//	WebElement userEmail=driver.findElement(By.id("userEmail"));
	//another way for storying your elements
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement userEmail;

	@FindBy(id="userPassword")
	WebElement passwordElement;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		errorMessage.getText();
		return errorMessage.getText();
	}

	public ProductCatalogue loginApp(String email,String password) {
		userEmail.sendKeys(email);
		passwordElement.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue= new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goToLandingPage() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	

}
