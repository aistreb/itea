import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LinkedinLoginTest {
    WebDriver driver;

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
    }

    @AfterMethod
    public void afterTest () {
        driver.close();
    }

    @Test
    public void successfullLoginTest() {

        LinkedinLoginPage loginPage = new LinkedinLoginPage(driver);
        String initialPageTitle = loginPage.getPageTitle();
        String initialPageUrl = loginPage.getPageUrl();

        Assert.assertEquals(initialPageTitle, "LinkedIn: Log In or Sign Up","Login page title is wrong");

        LinkedinBasePage homePage = loginPage.loginAs("a.iastreb1234@gmail.com", "itea2018");

        Assert.assertTrue(homePage.isSignedIn(), "User icon is not dispayed.");
        Assert.assertNotEquals(homePage.getPageTitle(), initialPageTitle, "Page title did not changed after login.");
        Assert.assertNotEquals(homePage.getPageUrl(), initialPageUrl, "Page url did not changed after login.");
    }


    @Test
    public void negativeLoginTest() {

        LinkedinLoginPage loginPage = new LinkedinLoginPage(driver);
        LinkedinFailedLoginPage failedLoginPage = loginPage.failedLoginAs("a.iastreb1234@gmail.com", "itea1234");
        Assert.assertTrue(failedLoginPage.isLoginFailed(), "Alert message is not displayed.");
        //failedLoginPage.loginAs("a.iastreb1234@gmail.com", "itea2018");
    }
}
