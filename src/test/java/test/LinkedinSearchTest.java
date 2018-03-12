package test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.LinkedinHomePage;
import page.LinkedinLandingPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LinkedinSearchTest {
	WebDriver driver;
	LinkedinLandingPage landingPage;
	String searchTerm;

	@BeforeMethod
	public void beforeTest(){
		driver = new FirefoxDriver();
		driver.get("https://www.linkedin.com/");
		landingPage = new LinkedinLandingPage(driver);
		searchTerm = "hr";
	}

	@AfterMethod
	public void afterTest() {
		driver.close();
	}

	@Test
	public void basicSearchTest() {
		LinkedinHomePage homePage = landingPage.loginAs("a.iastreb1234@gmail.com", "itea2018");
		Assert.assertTrue(homePage.isSignedIn(), "User icon is not dispayed.");
		homePage.goSearch(searchTerm);
		Assert.assertEquals(homePage.countSearchResult(), 10, "Number of results is wrong");

		for (WebElement result: homePage.results) {
			String cardTitle = homePage.scrollSearchResult(result);
			//System.out.println(cardTitle);
			Assert.assertTrue(cardTitle.toLowerCase().contains(searchTerm),
					"Searchterm "+searchTerm+ " not found in cart number ");
		}

	}
}
