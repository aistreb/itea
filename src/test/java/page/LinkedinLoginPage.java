package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinLoginPage extends LinkedinBasePage{

	@FindBy(id = "session_key-login")
	private WebElement emailField;

	@FindBy(id = "session_password-login")
	private WebElement passwordField;

	@FindBy(id = "btn-primary")
	private WebElement signInButton;

	@FindBy(id="session_password-login-error")
	private WebElement pwdMessage;

	@FindBy (id ="session_key-login-error")
	private WebElement emailMessage;

	public LinkedinLoginPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isLoginPage() {
		waitUntilElementIsClickable(emailField);
		return emailField.isDisplayed();
	}

	public String getEmailMessage() {
		return emailMessage.getText();
	}

	public String getPwdMessage() {
		return pwdMessage.getText();
	}
}
