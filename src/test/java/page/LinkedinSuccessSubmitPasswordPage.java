package page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinSuccessSubmitPasswordPage extends LinkedinBasePage{

	@FindBy(className = "flow-h1")
	private WebElement infoMessage;

	@FindBy(xpath = "btn-secondary-transparent")
	private WebElement retearnToHomePageLink;

	@FindBy(xpath = "secondary-form-action")
	private WebElement addPhoneLink;

	@FindBy(xpath = "secondary-form-action")
	private WebElement retearnToSessionLink;

	public LinkedinSuccessSubmitPasswordPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean isLoaded() {
		boolean isLoaded;
		try {
			isLoaded = retearnToHomePageLink.isDisplayed();
		}
		catch (NoSuchElementException e){
			isLoaded = false;
		}
		return isLoaded;
	}

}
