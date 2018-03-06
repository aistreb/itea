import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedinFailedLoginPage extends LinkedinLoginPage {
    WebDriver driver;
    private WebElement allertMesssage;

    public LinkedinFailedLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;

    /*private void initElements() {
        emailField = driver.findElement(By.id("session_key-login"));
        passwordField = driver.findElement(By.id("session_password-login"));
        signInButton = driver.findElement(By.id("btn-primary"));
    }

    private void tryLogin(String email, String password){
        initElements();
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
    }

    public LinkedinBasePage loginAs(String email, String password) {
        tryLogin(email, password);
        return new LinkedinBasePage(driver);
    }*/

    private void findAllert(){
        allertMesssage = driver.findElement (By.xpath("//div[@id='global-alert-queue']//strong[not(text()='')]"));
    }

    public boolean isLoginFailed () {
        findAllert();
        return allertMesssage.isDisplayed();
    }
}