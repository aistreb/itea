package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LinkedinHomePage extends LinkedinBasePage {

    @FindBy(id = "profile-nav-item")
    private WebElement userIcon;

    @FindBy (xpath ="//li[contains(@class,'search-result__occluded-item')]")
    public List<WebElement> results;

    @FindBy (className ="search-results")
    private WebElement searchResult;

    public LinkedinHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isSignedIn () {
        waitUntilElemenIsCliclable(userIcon, 5);
        return userIcon.isDisplayed();
    }

    public void goSearch (String searchTerm){
        driver.findElement(By.xpath("//input[@placeholder='Поиск']")).sendKeys(searchTerm);
        driver.findElement(By.xpath("//*[@type='search-icon']")).click();
    }

    public int countSearchResult () {
        waitUntilElemenIsCliclable(searchResult, 10);
        return results.size();
    }

    public String scrollSearchResult (WebElement result){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", result);
        return result.getText();
    }
}
