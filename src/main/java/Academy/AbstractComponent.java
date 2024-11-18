package Academy;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AbstractComponent {
	WebDriver driver;
	@FindBy(css = "[routerlink*='cart']")
	//@FindBy(css=".btn.btn-custom[routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css="[routerlink*='myorder']")
	WebElement order;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	
	
	
	public void waitForElementToDissappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		// wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public CartPage goToCartPage() throws InterruptedException {
		Thread.sleep(2000);
		cart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() throws InterruptedException {
		Thread.sleep(2000);
		order.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}