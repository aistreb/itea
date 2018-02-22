import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LinkedinSearchTest {
    WebDriver driver;

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
    public void basicSearchTest () throws InterruptedException {
        LinkedinLoginPage loginPage = new LinkedinLoginPage(driver);
        loginPage.loginAs("a.iastreb1234@gmail.com","itea2018");
        Thread.sleep(5000);

        //search
        //input [@placeholder='Search']
        //*[@type 'search-icon']
        //div[contains(@class,'search-result-person');

        WebElement searchField = driver.findElement (By.xpath("//*[@placeholder='Поиск']"));
        searchField.sendKeys("HR");
        WebElement searchButton = driver.findElement (By.xpath("//*[@type='search-icon']"));
        searchButton.click();
        Thread.sleep(5000);
        java.util.List<WebElement> searchResult = driver.findElements(By.xpath("//*[@class='name actor-name']"));

        System.out.println(searchResult.size());
        Assert.assertEquals(searchResult.size(),4,"Search results are Not equal to 10");

        for (int i = 0; i<searchResult.size(); i++)
        {
            Assert.assertTrue(searchResult.get(i).getText().contains("HR"), "Search result doesn't contain HR");
        }



    }
}
