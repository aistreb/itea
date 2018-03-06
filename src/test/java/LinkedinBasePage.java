import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedinBasePage {
    WebDriver driver;
    private WebElement userIcon;

    public LinkedinBasePage(WebDriver driver) {
        this.driver = driver;
    }

    private void initElements(){
       userIcon = driver.findElement (By.id("profile-nav-item"));
    }

    public boolean isSignedIn () {
        initElements();
        waitUntilElemenIsCliclable(userIcon);
        return userIcon.isDisplayed();
    }

    public String getPageTitle () {
        return driver.getTitle();
    }

    public String getPageUrl () {
        return driver.getCurrentUrl();
    }

    public void waitUntilElemenIsCliclable(WebElement webElement) {
        waitUntilElemenIsCliclable (webElement,10);
    }

    public void waitUntilElemenIsCliclable(WebElement webElement, int sec) {
        WebDriverWait wait = new WebDriverWait(driver, sec);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }


}
