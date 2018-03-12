package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LinkedinBasePage;
import page.LinkedinHomePage;
import page.LinkedinLandingPage;

public class LinkedinLoginTest {
    WebDriver driver;
    LinkedinLandingPage landingPage;
    String initialPageTitle;
    String initialPageUrl;

    @BeforeClass
    public void beforeClass () {
    }

    @AfterClass
    public void afterClass () {
    }

    @BeforeMethod
    public void beforeTest () {
        driver = new FirefoxDriver();
        driver.get("https://www.linkedin.com/");
        landingPage = new LinkedinLandingPage(driver);
        initialPageTitle = landingPage.getPageTitle();
        initialPageUrl = landingPage.getPageUrl();
    }

    @AfterMethod
    public void afterTest () {
        driver.close();
    }

    @Test
    public void successfullLoginTest() {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up","Login page title is wrong");

        LinkedinHomePage homePage = landingPage.loginAs("a.iastreb1234@gmail.com", "itea2018");

        Assert.assertTrue(homePage.isSignedIn(), "User icon is not dispayed.");
        Assert.assertNotEquals(homePage.getPageTitle(), initialPageTitle, "Page title did not changed after login.");
        Assert.assertNotEquals(homePage.getPageUrl(), initialPageUrl, "Page url did not changed after login.");
    }


    @Test
    public void negativeLoginTest() {
        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up","Login page title is wrong");

        LinkedinHomePage homePage = landingPage.loginAs("a.iastreb1234@gmail.com", "itea1234");

        Assert.assertFalse(homePage.isSignedIn(), "User icon is not dispayed.");
        Assert.assertNotEquals(homePage.getPageTitle(), initialPageTitle, "Page title did not changed after login.");
        Assert.assertNotEquals(homePage.getPageUrl(), initialPageUrl, "Page url did not changed after login.");
    }
}
