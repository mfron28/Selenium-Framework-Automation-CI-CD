package Academy;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest1 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Users\\mfron\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		String productName = "ZARA COAT 3";

		LandingPage landingPage=new LandingPage(driver);
		// login
		driver.findElement(By.id("userEmail")).sendKeys("mada@yahoo.com");
		driver.findElement(By.id("userPassword")).sendKeys("Madalina9");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		// use stream for finding an element Zara Coat
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement zaraProduct = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst()
				.orElse(null);
		// click on Add to cart
		zaraProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// we check the green bar from the bottom
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// check the elements from cart
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		Assert.assertTrue(match);

		// click on checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();

		// fill in the details in the checkout page
		//driver.findElement(By.cssSelector(".ng-touched")).sendKeys("mada@yahoo.com");
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Romania").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}
}
