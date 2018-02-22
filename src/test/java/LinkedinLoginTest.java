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
    public void successfullLoginTest() throws InterruptedException {

        String initialPageTitle = driver.getTitle();
        String initialPageUrl = driver.getCurrentUrl();

        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up","Login page title is wrong");
        //WebElement emailField = driver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement emailField = driver.findElement (By.id("login-email"));
        WebElement passwordField = driver.findElement (By.id("login-password"));
        WebElement signInButton = driver.findElement (By.id("login-submit"));

        emailField.sendKeys("a.iastreb1234@gmail.com");
        passwordField.sendKeys("itea2018");
        signInButton.click();
        Thread.sleep(5000);
        WebElement homeIcon = driver.findElement(By.id("feed-tab-icon"));
        WebElement userIcon = driver.findElement (By.id("profile-nav-item"));

        //Assert.assertTrue(homeIcon.isDisplayed(), "No Home icon.");
        Assert.assertTrue(userIcon.isDisplayed(), "User icon is not dispayed.");
       // Assert.assertFalse(driver.getTitle().equals(initialPageTitle), "Page title did not changed after login.");
        Assert.assertNotEquals(driver.getTitle(), initialPageTitle, "Page title did not changed after login.");
        Assert.assertNotEquals(driver.getCurrentUrl(), initialPageUrl, "Page url did not changed after login.");
    }


    @Test
    public void negativeLoginTest() {


        WebElement emailField = driver.findElement (By.id("login-email"));
        WebElement passwordField = driver.findElement (By.id("login-password"));
        WebElement signInButton = driver.findElement (By.id("login-submit"));

        emailField.sendKeys("test@ukr.net");
        passwordField.sendKeys("12345");
        signInButton.click();

        WebElement alertMessage = driver.findElement(By.xpath("//div[@id='global-alert-queue']//strong[not(text()='')]"));

        Assert.assertTrue(alertMessage.isDisplayed(), "Alert message is not displayed.");
    }
}
