package Academy;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Academy.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		// String filePath =
		// System.getProperty("user.dir")+"/src/main/java/Academy/resources";

		// FileInputStream input = new FileInputStream(filePath);

		ProductCatalogue productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));
		productCatalogue.addProductToCart(input.get("productName"));

		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplayed(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("Ind");
		checkOutPage.actionSumbit();
		ConfirmationPage confirmationPage = checkOutPage.actionSumbit();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() throws IOException, InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApp("mada@yahoo.com", "Madalina9");
		// from any page object(productCatalogue) you can go to other page
		// once you go to other page, see what that page returns
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplayed(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//Academy//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

		/*
		 * HashMap<String,String> map= new HashMap<String,String>(); map.put("email",
		 * "mada@yahoo.com"); map.put("password", "Madalina9"); map.put("product",
		 * "ZARA COAT 3");
		 * 
		 * 
		 * HashMap<String,String> map1= new HashMap<String,String>(); map1.put("email",
		 * "shetty@gmail.com"); map1.put("password", "Iamking@000"); map1.put("product",
		 * "ADIDAS ORIGINAL");
		 * 
		 * //two dimensial array, take each data set and run them //Object accept each
		 * kind of data type return new Object[][] {{map},{map1} };
		 * 
		 */

	}
}
