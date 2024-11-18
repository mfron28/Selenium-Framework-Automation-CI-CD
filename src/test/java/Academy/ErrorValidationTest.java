package Academy;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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
import org.testng.annotations.Test;

import Academy.TestComponents.BaseTest;
import Academy.TestComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseTest{

		@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
		public void submitOrder() throws IOException, InterruptedException {
		landingPage.loginApp("madsdsda@yahoo.com", "Madalina9");
		landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());

	}
}