package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LinkedinBasePage {
    WebDriver driver;

    public LinkedinBasePage(WebDriver driver) {
        this.driver = driver;

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
