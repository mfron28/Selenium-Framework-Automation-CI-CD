package Academy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Academy.CartPage;
import Academy.CheckOutPage;
import Academy.ConfirmationPage;
import Academy.LandingPage;
import Academy.ProductCatalogue;
import Academy.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
		//code
	}

	
	@Given("Logged in with username {string} and password {string}")
	public void logged_in_username_and_password(String username, String password)
	{
		productCatalogue = landingPage.loginApp(username,password);
	}
	
	
	@When("I add product {string} to Cart")
	public void i_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("Checkout {string} and submit the order")
	public void checkout_submit_order(String productName) throws InterruptedException
	{
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplayed(productName);
		Assert.assertTrue(match);
		CheckOutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.actionSumbit();
	}
	

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
    	String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
    }
    
    @Then("{string} message is displayed")
    public void something_message_is_displayed(String strArg1) throws Throwable {
   
    	Assert.assertEquals(strArg1, landingPage.getErrorMessage());
    	driver.close();
    }	

}