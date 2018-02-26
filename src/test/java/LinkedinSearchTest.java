import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LinkedinSearchTest {
	WebDriver driver;

	@BeforeMethod
	public void beforeTest(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.linkedin.com/");
	}

	@AfterMethod
	public void afterTest() {
		driver.close();
	}

	@Test
	public void basicSearchTest() throws InterruptedException {
		LinkedinLoginPage loginPage = new LinkedinLoginPage(driver);
		loginPage.loginAs("a.iastreb1234@gmail.com", "itea2018");

		//search
		String searchTerm = "HR";

		driver.findElement(By.xpath("//input[@placeholder='Поиск']")).sendKeys(searchTerm);
		driver.findElement(By.xpath("//*[@type='search-icon']")).click();
         //[contains(@class,'search-result__occluded-item')]

		List<WebElement> results = driver.findElements(By.xpath("//li[contains(@class,'search-result__occluded-item')]"));
		int currentResultsNumber = results.size();
		Assert.assertEquals(results.size(), 10, "Number of results is wrong");


		for (int i = 0; i < results.size(); i++) {
			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", results.get(i));
            int j=i+1;
			String cardTitle = driver.findElement(
					By.xpath("//li[contains(@class,'search-result__occluded-item')]["+j+"]//span[contains(@class, 'actor-name')]")).getText();
			System.out.println(j+" : "+cardTitle);
			Assert.assertTrue(cardTitle.contains(searchTerm),
					"Searchterm "+searchTerm+ " not found in cart number "+ Integer.toString(j));
		}



		//List<WebElement> cardTitles = driver.findElements(By.xpath("//li[contains(@class,'search-result__occluded-item')]//span[contains(@class, 'actor-name')]"));





		//input[@placeholder='Search']
		//*[@type='search-icon']
		//div[contains(@class,'search-result--person')]



	}
}
