import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinLoginPage extends LinkedinBasePage{
    WebDriver driver;
    private WebElement allertMesssage;

    public LinkedinLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;

    private void initElements() {

        emailField = driver.findElement(By.id("login-email"));
        waitUntilElemenIsCliclable(emailField, 5);
        passwordField = driver.findElement(By.id("login-password"));
        signInButton = driver.findElement(By.id("login-submit"));
    }

    public void tryLogin(String email, String password){
        initElements();
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
    }

    public LinkedinBasePage loginAs(String email, String password) {
        tryLogin(email, password);
        return new LinkedinBasePage(driver);
    }

    public LinkedinFailedLoginPage failedLoginAs(String email, String password) {
        tryLogin(email, password);
        return new LinkedinFailedLoginPage(driver);
    }



}
