package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinSubmitNewPasswordPage extends LinkedinBasePage{

	@FindBy(id = "new_password-newPassword-passwordReset")
	private WebElement newPasswordField;

	@FindBy(xpath = "new_password_again-newPassword-passwordReset")
	private WebElement repeatPasswordField;

	@FindBy(className= "btn-submit")
	private WebElement submitButton;

	public LinkedinSubmitNewPasswordPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isLoaded() {
		boolean isLoaded;
		try {
			isLoaded = newPasswordField.isDisplayed();
		}
		catch (NoSuchElementException e){
			isLoaded = false;
		}
		return isLoaded;
	}

	public LinkedinSuccessSubmitPasswordPage submitNewPassword(String newPassword) {
		newPasswordField.sendKeys(newPassword);
		repeatPasswordField.sendKeys(newPassword);
		submitButton.click();
		return new LinkedinSuccessSubmitPasswordPage(driver);
	}


}
